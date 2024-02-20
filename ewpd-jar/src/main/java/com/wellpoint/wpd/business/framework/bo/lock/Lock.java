/*
 * Lock.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo.lock;

import java.sql.Timestamp;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Lock.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class Lock {

    private String businessObjectName;
    private String concatenatedKey;
    private String lockUserId;
    private int duration;
    private Timestamp lockTimestamp;
    
    public Lock(){
        businessObjectName = "";
        concatenatedKey = "";
        lockUserId = "";
        duration = 0;
        lockTimestamp = null;
    }
    
    public Lock(String businessObjectName, String concatenatedKey,
            String lockUserId, int duration, Timestamp lockTimestamp) {
        this.businessObjectName = businessObjectName;
        this.concatenatedKey = concatenatedKey;
        this.lockUserId = lockUserId;
        this.duration = duration;
        this.lockTimestamp = lockTimestamp;
    }
    
    /**
     * @return Returns the businessObjectName.
     */
    public String getBusinessObjectName() {
        return businessObjectName;
    }
    /**
     * @return Returns the concatenatedKey.
     */
    public String getConcatenatedKey() {
        return concatenatedKey;
    }
    /**
     * @return Returns the duration.
     */
    public int getDuration() {
        return duration;
    }
    /**
     * @return Returns the lockTimestamp.
     */
    public Timestamp getLockTimestamp() {
        return lockTimestamp;
    }
    /**
     * @return Returns the lockUserId.
     */
    public String getLockUserId() {
        return lockUserId;
    }
    /**
     * @param businessObjectName The businessObjectName to set.
     */
    public void setBusinessObjectName(String businessObjectName) {
        this.businessObjectName = businessObjectName;
    }
    /**
     * @param concatenatedKey The concatenatedKey to set.
     */
    public void setConcatenatedKey(String concatenatedKey) {
        this.concatenatedKey = concatenatedKey;
    }
    /**
     * @param duration The duration to set.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    /**
     * @param lockTimestamp The lockTimestamp to set.
     */
    public void setLockTimestamp(Timestamp lockTimestamp) {
        this.lockTimestamp = lockTimestamp;
    }
    /**
     * @param lockUserId The lockUserId to set.
     */
    public void setLockUserId(String lockUserId) {
        this.lockUserId = lockUserId;
    } 
    
    public String toString(){
        return "Business Object Name:" + businessObjectName + " Key:"
                + concatenatedKey + " lock user:" + lockUserId + " duration:"
                + duration + " timestamp:" + lockTimestamp;
    }
}
