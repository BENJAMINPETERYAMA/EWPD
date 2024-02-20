/*
 * UpdateMigrationContractStatus.java
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
public class UpdateMigrationContractStatus {
	private int migrationSystemId;
	private String contractId;
	private String doneFlag;
	private String option;
	private String lastUpdatedUser;
	private java.util.Date lastUpdatedTimeStamp;

	
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
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
	 * Returns the lastUpdatedTimeStamp
	 * @return java.util.Date lastUpdatedTimeStamp.
	 */
	public java.util.Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * Sets the lastUpdatedTimeStamp
	 * @param lastUpdatedTimeStamp.
	 */
	public void setLastUpdatedTimeStamp(java.util.Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the migrationSystemId
	 * @return int migrationSystemId.
	 */
	public int getMigrationSystemId() {
		return migrationSystemId;
	}
	/**
	 * Sets the migrationSystemId
	 * @param migrationSystemId.
	 */
	public void setMigrationSystemId(int migrationSystemId) {
		this.migrationSystemId = migrationSystemId;
	}
	/**
	 * Returns the option
	 * @return String option.
	 */
	public String getOption() {
		return option;
	}
	/**
	 * Sets the option
	 * @param option.
	 */
	public void setOption(String option) {
		this.option = option;
	}
}
