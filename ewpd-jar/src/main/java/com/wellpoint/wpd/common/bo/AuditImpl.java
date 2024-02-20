/*
 * AuditImpl.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

import java.util.Date;
import java.util.TimeZone;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: AuditImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class AuditImpl implements Audit {
	
	private String user;
	private Date time;
	private TimeZone timeZone;
	
	/**
     * @see com.wellpoint.wpd.common.bo.Audit#getUser()
     * @return
     */
    public String getUser() {
        return user;
    }
    /**
     * @see com.wellpoint.wpd.common.bo.Audit#setUser(java.lang.String)
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }
    /**
     * @see com.wellpoint.wpd.common.bo.Audit#getTime()
     * @return
     */
    public Date getTime() {
        return time;
    }
    /**
     * @see com.wellpoint.wpd.common.bo.Audit#setTime(java.util.Date)
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
    /**
     * @see com.wellpoint.wpd.common.bo.Audit#getTimeZone()
     * @return
     */
    public TimeZone getTimeZone() {
        return this.timeZone;
    }
    
    
    /**
     * @param timeZone The timeZone to set.
     */
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }
}
