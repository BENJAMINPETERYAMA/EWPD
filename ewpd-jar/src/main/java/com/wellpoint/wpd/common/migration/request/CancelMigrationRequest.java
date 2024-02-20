/*
 * CancelMigrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CancelMigrationRequest extends MigrationContractRequest{
    
    private int migDateSegmentSysId;
    
    private int migContractSysId;
    
    private String migContractId;
    
    //added for 2 nd migration
    private String doneFlag;
    
    private String system;
        
    private String option;
       
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
     * Returns the system
     * @return String system.
     */
    public String getSystem() {
        return system;
    }
    /**
     * Sets the system
     * @param system.
     */
    public void setSystem(String system) {
        this.system = system;
    }
    /**
     * Returns the migContractId
     * @return String migContractId.
     */
    public String getMigContractId() {
        return migContractId;
    }
    /**
     * Sets the migContractId
     * @param migContractId.
     */
    public void setMigContractId(String migContractId) {
        this.migContractId = migContractId;
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
	/**
	 * @return Returns the option.
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option The option to set.
	 */
	public void setOption(String option) {
		this.option = option;
	}
}
