/*
 * MigrationProductStructureMappingUpdate.java
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
public class MigrationProductStructureMappingUpdate {
	private int migratedProdStructureMappingSysID;
	private String prodStructureLockStatus;
	private String lastUpdatedUser;
	private java.util.Date lastUpdatedTimestamp;

	/**
	 * Returns the lastUpdatedTimestamp
	 * @return java.util.Date lastUpdatedTimestamp.
	 */
	public java.util.Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(java.util.Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
	 * Returns the migratedProdStructureMappingSysID
	 * @return int migratedProdStructureMappingSysID.
	 */
	public int getMigratedProdStructureMappingSysID() {
		return migratedProdStructureMappingSysID;
	}
	/**
	 * Sets the migratedProdStructureMappingSysID
	 * @param migratedProdStructureMappingSysID.
	 */
	public void setMigratedProdStructureMappingSysID(
			int migratedProdStructureMappingSysID) {
		this.migratedProdStructureMappingSysID = migratedProdStructureMappingSysID;
	}
	/**
	 * Returns the prodStructureLockStatus
	 * @return String prodStructureLockStatus.
	 */
	public String getProdStructureLockStatus() {
		return prodStructureLockStatus;
	}
	/**
	 * Sets the prodStructureLockStatus
	 * @param prodStructureLockStatus.
	 */
	public void setProdStructureLockStatus(String prodStructureLockStatus) {
		this.prodStructureLockStatus = prodStructureLockStatus;
	}
}
