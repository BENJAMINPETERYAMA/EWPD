/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import java.util.List;


/**
 * @author u14617
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdministrationOptionVO {
	
	 private int adminLevelOptionAssnSystemId;
	 
	 private int benefitAdminSystemId;
	 private int adminLevelSystemId;
	 private int benefitLevelSystemId;
	 private int adminOptionSystemId;
	 //Previous value of adminOptSysId
	 private int adminOptionSystemIdForUpdate;
	 
	 private int benefitMasterSystemId;
	 private int masterVersion;
	 private String benefitIdentifier;
	 private int masterKeyUsedForUpdate;
	 
	private int standardBenefitParentKey;
		
	private String standardBenefitStatus;
		
	private List businessDomains;
	private String isOpen;
	 private int sequenceNumber;
	 
	 private int standardBenefitId;
	 private int standardBenefitParentId;
	 private String standardBenefitName;
	 private int standardBenefitVersion;
	 private List sbBusinessDomains;
	 
	 //for enhancement
	 
	 private String hideFlag;
	 
	 private int entitySysId;
	 
	 private String entityType;

	 private String questionHideFlag;
	 //modified for solving performance issue on 13th Dec 2007	 	 
	 private boolean isModified;
	 
	 private int benefitDefinitionkey;
	 //modification ends
	 
	 private List adminOptionsList;
    /**
     * @return isModified
     * 
     * Returns the isModified.
     */
    public boolean isModified() {
        return isModified;
    }
    /**
     * @param isModified
     * 
     * Sets the isModified.
     */
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
    /**
     * @return isOpen
     * 
     * Returns the isOpen.
     */
    public String getIsOpen() {
        return isOpen;
    }
    /**
     * @param isOpen
     * 
     * Sets the isOpen.
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
	/**
	 * @return Returns the adminLevelOptionAssnSystemId.
	 */
	public int getAdminLevelOptionAssnSystemId() {
		return adminLevelOptionAssnSystemId;
	}
	/**
	 * @param adminLevelOptionAssnSystemId The adminLevelOptionAssnSystemId to set.
	 */
	public void setAdminLevelOptionAssnSystemId(int adminLevelOptionAssnSystemId) {
		this.adminLevelOptionAssnSystemId = adminLevelOptionAssnSystemId;
	}
	/**
	 * @return Returns the adminLevelSystemId.
	 */
	public int getAdminLevelSystemId() {
		return adminLevelSystemId;
	}
	/**
	 * @param adminLevelSystemId The adminLevelSystemId to set.
	 */
	public void setAdminLevelSystemId(int adminLevelSystemId) {
		this.adminLevelSystemId = adminLevelSystemId;
	}
	/**
	 * @return Returns the adminOptionSystemId.
	 */
	public int getAdminOptionSystemId() {
		return adminOptionSystemId;
	}
	/**
	 * @param adminOptionSystemId The adminOptionSystemId to set.
	 */
	public void setAdminOptionSystemId(int adminOptionSystemId) {
		this.adminOptionSystemId = adminOptionSystemId;
	}
	/**
	 * @return Returns the benefitAdminSystemId.
	 */
	public int getBenefitAdminSystemId() {
		return benefitAdminSystemId;
	}
	/**
	 * @param benefitAdminSystemId The benefitAdminSystemId to set.
	 */
	public void setBenefitAdminSystemId(int benefitAdminSystemId) {
		this.benefitAdminSystemId = benefitAdminSystemId;
	}
	/**
	 * @return Returns the benefitLevelSystemId.
	 */
	public int getBenefitLevelSystemId() {
		return benefitLevelSystemId;
	}
	/**
	 * @param benefitLevelSystemId The benefitLevelSystemId to set.
	 */
	public void setBenefitLevelSystemId(int benefitLevelSystemId) {
		this.benefitLevelSystemId = benefitLevelSystemId;
	}
	/**
	 * @return Returns the benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * @param benefitIdentifier The benefitIdentifier to set.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * @return Returns the benefitMasterSystemId.
	 */
	public int getBenefitMasterSystemId() {
		return benefitMasterSystemId;
	}
	/**
	 * @param benefitMasterSystemId The benefitMasterSystemId to set.
	 */
	public void setBenefitMasterSystemId(int benefitMasterSystemId) {
		this.benefitMasterSystemId = benefitMasterSystemId;
	}
	/**
	 * @return Returns the masterVersion.
	 */
	public int getMasterVersion() {
		return masterVersion;
	}
	/**
	 * @param masterVersion The masterVersion to set.
	 */
	public void setMasterVersion(int masterVersion) {
		this.masterVersion = masterVersion;
	}
	/**
	 * @return Returns the masterKeyUsedForUpdate.
	 */
	public int getMasterKeyUsedForUpdate() {
		return masterKeyUsedForUpdate;
	}
	/**
	 * @param masterKeyUsedForUpdate The masterKeyUsedForUpdate to set.
	 */
	public void setMasterKeyUsedForUpdate(int masterKeyUsedForUpdate) {
		this.masterKeyUsedForUpdate = masterKeyUsedForUpdate;
	}
	/**
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * Returns the standardBenefitParentKey
	 * @return int standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * Sets the standardBenefitParentKey
	 * @param standardBenefitParentKey.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * Returns the standardBenefitStatus
	 * @return String standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * Sets the standardBenefitStatus
	 * @param standardBenefitStatus.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
	/**
	 * @return Returns the adminOptionSystemIdForUpdate.
	 */
	public int getAdminOptionSystemIdForUpdate() {
		return adminOptionSystemIdForUpdate;
	}
	/**
	 * @param adminOptionSystemIdForUpdate The adminOptionSystemIdForUpdate to set.
	 */
	public void setAdminOptionSystemIdForUpdate(int adminOptionSystemIdForUpdate) {
		this.adminOptionSystemIdForUpdate = adminOptionSystemIdForUpdate;
	}
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return Returns the sbBusinessDomains.
	 */
	public List getSbBusinessDomains() {
		return sbBusinessDomains;
	}
	/**
	 * @param sbBusinessDomains The sbBusinessDomains to set.
	 */
	public void setSbBusinessDomains(List sbBusinessDomains) {
		this.sbBusinessDomains = sbBusinessDomains;
	}
	/**
	 * @return Returns the standardBenefitId.
	 */
	public int getStandardBenefitId() {
		return standardBenefitId;
	}
	/**
	 * @param standardBenefitId The standardBenefitId to set.
	 */
	public void setStandardBenefitId(int standardBenefitId) {
		this.standardBenefitId = standardBenefitId;
	}
	
	/**
	 * @return Returns the standardBenefitName.
	 */
	public String getStandardBenefitName() {
		return standardBenefitName;
	}
	/**
	 * @param standardBenefitName The standardBenefitName to set.
	 */
	public void setStandardBenefitName(String standardBenefitName) {
		this.standardBenefitName = standardBenefitName;
	}
	/**
	 * @return Returns the standardBenefitParentId.
	 */
	public int getStandardBenefitParentId() {
		return standardBenefitParentId;
	}
	/**
	 * @param standardBenefitParentId The standardBenefitParentId to set.
	 */
	public void setStandardBenefitParentId(int standardBenefitParentId) {
		this.standardBenefitParentId = standardBenefitParentId;
	}
	/**
	 * @return Returns the standardBenefitVersion.
	 */
	public int getStandardBenefitVersion() {
		return standardBenefitVersion;
	}
	/**
	 * @param standardBenefitVersion The standardBenefitVersion to set.
	 */
	public void setStandardBenefitVersion(int standardBenefitVersion) {
		this.standardBenefitVersion = standardBenefitVersion;
	}
	/**
	 * @return Returns the hideFlag.
	 */
	public String getHideFlag() {
		return hideFlag;
	}
	/**
	 * @param hideFlag The hideFlag to set.
	 */
	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}
	/**
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	 
	/**
	 * @return Returns the questionHideFlag.
	 */
	public String getQuestionHideFlag() {
		return questionHideFlag;
	}
	/**
	 * @param questionHideFlag The questionHideFlag to set.
	 */
	public void setQuestionHideFlag(String questionHideFlag) {
		this.questionHideFlag = questionHideFlag;
	}
	/**
	 * @return Returns the benefitDefinitionkey.
	 */
	public int getBenefitDefinitionkey() {
		return benefitDefinitionkey;
	}
	/**
	 * @param benefitDefinitionkey The benefitDefinitionkey to set.
	 */
	public void setBenefitDefinitionkey(int benefitDefinitionkey) {
		this.benefitDefinitionkey = benefitDefinitionkey;
	}
	/**
	 * @return Returns the adminOptionsList.
	 */
	public List getAdminOptionsList() {
		return adminOptionsList;
	}
	/**
	 * @param adminOptionsList The adminOptionsList to set.
	 */
	public void setAdminOptionsList(List adminOptionsList) {
		this.adminOptionsList = adminOptionsList;
	}
}

