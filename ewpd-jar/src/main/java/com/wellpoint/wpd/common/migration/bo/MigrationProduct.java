/*
 * MigrationProduct.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationProduct {

	private int migratedContractSysID;
	private int migratedProdStructureMappingSysID;
	private int ewpdProductSysId;;
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
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String createdUser;
	private Date createdTimestamp;
	private int baseContrProductSysID;
	
	private int mgrtdDateSegmentId;
    
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
	 * Returns the createdTimestamp
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * Sets the createdTimestamp
	 * @param createdTimestamp.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * Returns the createdUser
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser
	 * @param createdUser.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the lastUpdatedTimestamp
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * Sets the lastUpdatedTimestamp
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
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
	 * Returns the ewpdProductSysId
	 * @return int ewpdProductSysId.
	 */
	public int getEwpdProductSysId() {
		return ewpdProductSysId;
	}
	/**
	 * Sets the ewpdProductSysId
	 * @param ewpdProductSysId.
	 */
	public void setEwpdProductSysId(int ewpdProductSysId) {
		this.ewpdProductSysId = ewpdProductSysId;
	}
    /**
     * @return baseContrProductSysID
     * 
     * Returns the baseContrProductSysID.
     */
    public int getBaseContrProductSysID() {
        return baseContrProductSysID;
    }
    /**
     * @param baseContrProductSysID
     * 
     * Sets the baseContrProductSysID.
     */
    public void setBaseContrProductSysID(int baseContrProductSysID) {
        this.baseContrProductSysID = baseContrProductSysID;
    }
	/**
	 * @return Returns the mgrtdDateSegmentId.
	 */
	public int getMgrtdDateSegmentId() {
		return mgrtdDateSegmentId;
	}
	/**
	 * @param mgrtdDateSegmentId The mgrtdDateSegmentId to set.
	 */
	public void setMgrtdDateSegmentId(int mgrtdDateSegmentId) {
		this.mgrtdDateSegmentId = mgrtdDateSegmentId;
	}
}
