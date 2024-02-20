/*
 * CancelSecondMigration.java
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
public class CancelSecondMigration {
    
    private int migDateSegmentSysId;
    
    private int migContractSysId;
    
    private String doneFlag;
    
    
    /**
     * Returns the doneFlag
     * @return String doneFlag.
     */
    public String getDoneFlag() {
        return doneFlag;
    }
    /**
     * Sets the doneFlag
     * @param doneFlag.
     */
    public void setDoneFlag(String doneFlag) {
        this.doneFlag = doneFlag;
    }
    /**
     * Returns the migContractSysId
     * @return int migContractSysId.
     */
    public int getMigContractSysId() {
        return migContractSysId;
    }
    /**
     * Sets the migContractSysId
     * @param migContractSysId.
     */
    public void setMigContractSysId(int migContractSysId) {
        this.migContractSysId = migContractSysId;
    }
    /**
     * Returns the migDateSegmentSysId
     * @return int migDateSegmentSysId.
     */
    public int getMigDateSegmentSysId() {
        return migDateSegmentSysId;
    }
    /**
     * Sets the migDateSegmentSysId
     * @param migDateSegmentSysId.
     */
    public void setMigDateSegmentSysId(int migDateSegmentSysId) {
        this.migDateSegmentSysId = migDateSegmentSysId;
    }
}
