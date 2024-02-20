/*
 * MigrationProductInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;



import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationProductInfoRequest extends MigrationContractRequest{
	
	private int migratedContractSysID;
	private int migratedProdStructureMappingSysID;
	private String migratedDateSegmentId;
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
	private int ewpdProductSysId;
	private int action;
	private boolean insertFlag;
	
    public static final int MIGRATION_SAVE_PRODUCT_INFO = 2;
    public static final int MIGRATION_RETRIEVE_PRODUCT_INFO = 1;
    public static final int MIGRATION_UPDATE_STEP_NUMBER_COMPLETED = 3;
    public static final int PRODUCT_POPUP = 10;
    public static final int RETRIEVE_BY_CY_CONFLICT_MESSAGE = 20;
    
    public static final int REMOVE_PRODUCT_INFO = 33;
    
    private int productParentSysId;
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
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
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
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
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
	 * Returns the insertFlag
	 * @return boolean insertFlag.
	 */
	public boolean isInsertFlag() {
		return insertFlag;
	}
	/**
	 * Sets the insertFlag
	 * @param insertFlag.
	 */
	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}
	/**
	 * Sets the ewpdProductSysId
	 * @param ewpdProductSysId.
	 */
	public void setEwpdProductSysId(int ewpdProductSysId) {
		this.ewpdProductSysId = ewpdProductSysId;
	}
	/**
	 * Returns the ewpdProductSysId
	 * @return int ewpdProductSysId.
	 */
	public int getEwpdProductSysId() {
		return ewpdProductSysId;
	}
	/**
	 * Returns the migratedDateSegmentId
	 * @return String migratedDateSegmentId.
	 */
	public String getMigratedDateSegmentId() {
		return migratedDateSegmentId;
	}
	/**
	 * Sets the migratedDateSegmentId
	 * @param migratedDateSegmentId.
	 */
	public void setMigratedDateSegmentId(String migratedDateSegmentId) {
		this.migratedDateSegmentId = migratedDateSegmentId;
	}
	/**
	 * @return Returns the productParentSysId.
	 */
	public int getProductParentSysId() {
		return productParentSysId;
	}
	/**
	 * @param productParentSysId The productParentSysId to set.
	 */
	public void setProductParentSysId(int productParentSysId) {
		this.productParentSysId = productParentSysId;
	}
}
