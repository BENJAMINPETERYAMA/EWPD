/*
 * MigrationProductInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;

import java.util.List;

import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationProductInfoResponse extends MigrationContractResponse{
	
	private int migratedContractSysID;
	private int ewpdProdSysId;
	private int migratedProdStructureMappingSysID;
	private String lineOfBusiness;
	private String businessEntity;
	private String businessGroup;
	private String effectiveDate;
	private String expiryDate;
	private String legacyStructure;
	private String productName;
	private String productFamily;
	private String legacySourceSystem;
	private String lockStatusFlag;
	private boolean success;
	private boolean persistStatus;
	private List migrationDomainInfoList;
	private MigrationDateSegment migDateSegment;
	private List validProductList;
	private boolean BYCYConflict = false;
	/**
	 * Returns the businessEntity
	 * @return String businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * Sets the businessEntity
	 * @param businessEntity.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * Returns the businessGroup
	 * @return String businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * Sets the businessGroup
	 * @param businessGroup.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * Returns the effectiveDate
	 * @return String effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * Returns the expiryDate
	 * @return String expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the legacyStructure
	 * @return String legacyStructure.
	 */
	public String getLegacyStructure() {
		return legacyStructure;
	}
	/**
	 * Sets the legacyStructure
	 * @param legacyStructure.
	 */
	public void setLegacyStructure(String legacyStructure) {
		this.legacyStructure = legacyStructure;
	}
	/**
	 * Returns the lineOfBusiness
	 * @return String lineOfBusiness.
	 */
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	/**
	 * Sets the lineOfBusiness
	 * @param lineOfBusiness.
	 */
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	/**
	 * Returns the productFamily
	 * @return String productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * Sets the productFamily
	 * @param productFamily.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 * Returns the productName
	 * @return String productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * Sets the productName
	 * @param productName.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * Returns the success
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Sets the success
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * Returns the migratedContractSysID
	 * @return int migratedContractSysID.
	 */
	public int getMigratedContractSysID() {
		return migratedContractSysID;
	}
	/**
	 * Sets the migratedContractSysID
	 * @param migratedContractSysID.
	 */
	public void setMigratedContractSysID(int migratedContractSysID) {
		this.migratedContractSysID = migratedContractSysID;
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
	 * Returns the legacySourceSystem
	 * @return String legacySourceSystem.
	 */
	public String getLegacySourceSystem() {
		return legacySourceSystem;
	}
	/**
	 * Sets the legacySourceSystem
	 * @param legacySourceSystem.
	 */
	public void setLegacySourceSystem(String legacySourceSystem) {
		this.legacySourceSystem = legacySourceSystem;
	}
	/**
	 * Returns the lockStatusFlag
	 * @return String lockStatusFlag.
	 */
	public String getLockStatusFlag() {
		return lockStatusFlag;
	}
	/**
	 * Sets the lockStatusFlag
	 * @param lockStatusFlag.
	 */
	public void setLockStatusFlag(String lockStatusFlag) {
		this.lockStatusFlag = lockStatusFlag;
	}
	/**
	 * Returns the persistStatus
	 * @return boolean persistStatus.
	 */
	public boolean isPersistStatus() {
		return persistStatus;
	}
	/**
	 * Sets the persistStatus
	 * @param persistStatus.
	 */
	public void setPersistStatus(boolean persistStatus) {
		this.persistStatus = persistStatus;
	}
	/**
	 * Returns the ewpdProdSysId
	 * @return int ewpdProdSysId.
	 */
	public int getEwpdProdSysId() {
		return ewpdProdSysId;
	}
	/**
	 * Sets the ewpdProdSysId
	 * @param ewpdProdSysId.
	 */
	public void setEwpdProdSysId(int ewpdProdSysId) {
		this.ewpdProdSysId = ewpdProdSysId;
	}
	/**
	 * Returns the migrationDomainInfoList
	 * @return List migrationDomainInfoList.
	 */
	public List getMigrationDomainInfoList() {
		return migrationDomainInfoList;
	}
	/**
	 * Sets the migrationDomainInfoList
	 * @param migrationDomainInfoList.
	 */
	public void setMigrationDomainInfoList(List migrationDomainInfoList) {
		this.migrationDomainInfoList = migrationDomainInfoList;
	}
	/**
	 * Returns the migDateSegment
	 * @return MigrationDateSegment migDateSegment.
	 */
	public MigrationDateSegment getMigDateSegment() {
		return migDateSegment;
	}
	/**
	 * Sets the migDateSegment
	 * @param migDateSegment.
	 */
	public void setMigDateSegment(MigrationDateSegment migDateSegment) {
		this.migDateSegment = migDateSegment;
	}
	/**
	 * Returns the validProductList.
	 * @return List validProductList.
	 */
	public List getValidProductList() {
		return validProductList;
	}
	/**
	 * Sets the validProductList.
	 * @param validProductList.
	 */

	public void setValidProductList(List validProductList) {
		this.validProductList = validProductList;
	}
	/**
	 * @return Returns the bYCYConflict.
	 */
	public boolean isBYCYConflict() {
		return BYCYConflict;
	}
	/**
	 * @param conflict The bYCYConflict to set.
	 */
	public void setBYCYConflict(boolean conflict) {
		BYCYConflict = conflict;
	}
}
