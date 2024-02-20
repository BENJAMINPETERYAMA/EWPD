/*
 * ApplicationStatusBackingBean.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ibm.websphere.cache.DistributedMap;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ApplicationStatusBackingBean.java 27450 2007-07-18 23:18:24Z
 *          U10347 $
 */
public class ApplicationStatusBackingBean extends WPDBackingBean {

    private String status;

    private Date expiryTime;

    private String adminUser;

    private Date statusChangedTime;

    private int lockDuration;

    /**
     * @return Returns the adminUser.
     */
    public String getAdminUser() {
        DistributedMap applicationLockMap = getApplicationLockMap();
        if (applicationLockMap != null) {
            adminUser = (String) applicationLockMap.get("LOCKED_USER");
        }
        return adminUser;
    }

    /**
     * @param adminUser
     *            The adminUser to set.
     */
    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    /**
     * @return Returns the expiryTime.
     */
    public Date getExpiryTime() {
        DistributedMap applicationLockMap = getApplicationLockMap();
        if (applicationLockMap != null) {
            expiryTime = (Date) applicationLockMap.get("LOCK_EXPIRY_TIME");
        }
        return expiryTime;
    }

    /**
     * @param expiryTime
     *            The expiryTime to set.
     */
    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        DistributedMap applicationLockMap = getApplicationLockMap();
        if (applicationLockMap != null) {
            String lockedFlag = (String) applicationLockMap.get("LOCKED_FLAG");
            if ("Y".equals(lockedFlag)) {
                status = "Disabled";
            } else {
                status = "Enabled";
            }
        }
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Returns the statusChangedTime.
     */
    public Date getStatusChangedTime() {
        DistributedMap applicationLockMap = getApplicationLockMap();
        if (applicationLockMap != null) {
            statusChangedTime = (Date) applicationLockMap.get("LOCKED_TIME");
        }
        return statusChangedTime;
    }

    /**
     * @param statusChangedTime
     *            The statusChangedTime to set.
     */
    public void setStatusChangedTime(Date statusChangedTime) {
        this.statusChangedTime = statusChangedTime;
    }

    public String disable() {
        try {
            DistributedMap applicationLockMap = getApplicationLockMap();
            if (applicationLockMap != null) {
                String userId = getUser().getUserId();
                applicationLockMap.put("LOCKED_FLAG", "Y");
                long currentTime = System.currentTimeMillis();
                statusChangedTime = new Date(currentTime);
                applicationLockMap.put("LOCKED_TIME", statusChangedTime);
                applicationLockMap.put("LOCKED_USER", userId);
                int disabledStateExpirationTimeInterval = 0;
                if (lockDuration <= 0) {
                    disabledStateExpirationTimeInterval = Integer
                            .parseInt(getApplicationContext().getInitParameter(
                                    "disabledStateExpirationTimeInterval"));
                    lockDuration = disabledStateExpirationTimeInterval;
                } else {
                    disabledStateExpirationTimeInterval = lockDuration;
                }
                if (statusChangedTime != null) {
                    expiryTime = new Date(statusChangedTime.getTime()
                            + disabledStateExpirationTimeInterval * 60 * 1000);
                }
                applicationLockMap.put("LOCK_EXPIRY_TIME", expiryTime);
            } else {
                return "error";
            }
        } catch (SevereException se) {
        	if (se.getLogId() == null || se.getLogId().trim().length() == 0) {
                Logger.logException(se);
            }
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    se.getLogId());
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);   
            return "error";
        }
        return "success";
    }

    public String enable() {
        DistributedMap applicationLockMap = getApplicationLockMap();
        if (applicationLockMap != null) {
            applicationLockMap.put("LOCKED_FLAG", "N");
            applicationLockMap.put("LOCKED_TIME", null);
            applicationLockMap.put("LOCK_EXPIRY_TIME", null);
            applicationLockMap.put("LOCKED_USER", null);
        } else {
            return "error";
        }
        return "success";
    }

    private DistributedMap getApplicationLockMap() {
        DistributedMap applicationLockMap = null;
        try {
            InitialContext ic = new InitialContext();
            applicationLockMap = (DistributedMap) ic
                    .lookup("java:comp/env/wsbEwpd/application_lock_cache");
        } catch (NamingException e) {
            return null;
        }
        return applicationLockMap;
    }

    /**
     * @return Returns the lockDuration.
     */
    public int getLockDuration() {
        if (lockDuration <= 0) {
            lockDuration = Integer.parseInt(getApplicationContext()
                    .getInitParameter("disabledStateExpirationTimeInterval"));
        }
        return lockDuration;
    }

    /**
     * @param lockDuration
     *            The lockDuration to set.
     */
    public void setLockDuration(int lockDuration) {
        this.lockDuration = lockDuration;
    }

    public boolean isAccessDisabled() {
        DistributedMap applicationLockMap = getApplicationLockMap();
        if (applicationLockMap != null) {
            String lockedFlag = (String) applicationLockMap.get("LOCKED_FLAG");
            return "Y".equalsIgnoreCase(lockedFlag);
        }
        return false;
    }
}