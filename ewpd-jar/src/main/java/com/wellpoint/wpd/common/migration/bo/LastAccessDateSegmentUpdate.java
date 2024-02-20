/*
 * LastAccessDateSegmentUpdate.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LastAccessDateSegmentUpdate {

    int migDateSegmentSysId;

    String lastUpdatedUser;

    java.util.Date lastUpdatedTimeStamp;


    /**
     * Returns the lastUpdatedTimeStamp
     * 
     * @return java.util.Date lastUpdatedTimeStamp.
     */
    public java.util.Date getLastUpdatedTimeStamp() {
        return lastUpdatedTimeStamp;
    }


    /**
     * Sets the lastUpdatedTimeStamp
     * 
     * @param lastUpdatedTimeStamp.
     */
    public void setLastUpdatedTimeStamp(java.util.Date lastUpdatedTimeStamp) {
        this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
    }


    /**
     * Returns the lastUpdatedUser
     * 
     * @return String lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * Sets the lastUpdatedUser
     * 
     * @param lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * Returns the migDateSegmentSysId
     * 
     * @return int migDateSegmentSysId.
     */
    public int getMigDateSegmentSysId() {
        return migDateSegmentSysId;
    }


    /**
     * Sets the migDateSegmentSysId
     * 
     * @param migDateSegmentSysId.
     */
    public void setMigDateSegmentSysId(int migDateSegmentSysId) {
        this.migDateSegmentSysId = migDateSegmentSysId;
    }
}