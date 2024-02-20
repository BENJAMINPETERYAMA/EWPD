/*
 * MigrationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.response;

import java.util.List;

import com.wellpoint.wpd.common.migration.response.MigrationContractResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveLegacyContractResponse extends MigrationContractResponse {

    private List dateSegmentList;
    
    private boolean isLocked;
    
    private boolean isContractExists;
    
    private String doneFlag;
    
    private boolean isMigStarted;
    
    private String option;
    
    private List migrationdateSegmentList; 
    
    boolean newMigration;
    
	boolean afterMigrationDateSegmentExist = false;
	
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
	/**
	 * @return Returns the isMigStarted.
	 */
	public boolean isMigStarted() {
		return isMigStarted;
	}
	/**
	 * @param isMigStarted The isMigStarted to set.
	 */
	public void setMigStarted(boolean isMigStarted) {
		this.isMigStarted = isMigStarted;
	}
   /**
     * @return doneFlag
     * 
     * Returns the doneFlag.
     */
    public String getDoneFlag() {
        return doneFlag;
    }
    /**
     * @param doneFlag
     * 
     * Sets the doneFlag.
     */
    public void setDoneFlag(String doneFlag) {
        this.doneFlag = doneFlag;
    }
    /**
     * @return isContractExists
     * 
     * Returns the isContractExists.
     */
    public boolean isContractExists() {
        return isContractExists;
    }
    /**
     * @param isContractExists
     * 
     * Sets the isContractExists.
     */
    public void setContractExists(boolean isContractExists) {
        this.isContractExists = isContractExists;
    }
    /**
     * @return isLocked
     * 
     * Returns the isLocked.
     */
    public boolean isLocked() {
        return isLocked;
    }
    /**
     * @param isLocked
     * 
     * Sets the isLocked.
     */
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
    /**
     * @return dateSegmentList
     * 
     * Returns the dateSegmentList.
     */
    public List getDateSegmentList() {
        return dateSegmentList;
    }


    /**
     * @param dateSegmentList
     * 
     * Sets the dateSegmentList.
     */
    public void setDateSegmentList(List dateSegmentList) {
        this.dateSegmentList = dateSegmentList;
    }
	/**
	 * @return Returns the migrationdateSegmentList.
	 */
	public List getMigrationdateSegmentList() {
		return migrationdateSegmentList;
	}
	/**
	 * @param migrationdateSegmentList The migrationdateSegmentList to set.
	 */
	public void setMigrationdateSegmentList(List migrationdateSegmentList) {
		this.migrationdateSegmentList = migrationdateSegmentList;
	}
	/**
	 * Returns the newMigration.
	 * @return boolean newMigration.
	 */
	public boolean isNewMigration() {
		return newMigration;
	}
	/**
	 * Sets the newMigration.
	 * @param newMigration.
	 */

	public void setNewMigration(boolean newMigration) {
		this.newMigration = newMigration;
	}
	/**
	 * Returns the afterMigrationDateSegmentExist
	 * @return boolean afterMigrationDateSegmentExist.
	 */
	public boolean isAfterMigrationDateSegmentExist() {
		return afterMigrationDateSegmentExist;
	}
	/**
	 * Sets the afterMigrationDateSegmentExist
	 * @param afterMigrationDateSegmentExist.
	 */
	public void setAfterMigrationDateSegmentExist(
			boolean afterMigrationDateSegmentExist) {
		this.afterMigrationDateSegmentExist = afterMigrationDateSegmentExist;
	}
}