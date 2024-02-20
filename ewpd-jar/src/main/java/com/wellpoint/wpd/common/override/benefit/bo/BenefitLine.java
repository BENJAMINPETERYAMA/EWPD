/*
 * BenefitLine.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLine {
	String entityType;

	int entitySysId;

	//Level Info
	private int levelSysId;

	private String levelDesc;

	private String termDesc;

	private String qualifierDesc;

	private String termCode;

	private String qualifierCode;

	private String referenceDesc;

	private String referenceCode;

	//Line Info
	private int lineSysId;

	private String providerArrangementId;

	private String providerArrangementDesc;

	private String dataTypeId;

	private String dataTypeDesc;

	private String benefitValue;

	private String dataTypeLegend;

	private String createdUser;

	private Date createdTimestamp;

	private String lastUpdatedUser;

	private Date lastUpdatedTimestamp;

	private int benefitComponentId;

	private String benefitType;

	private String mandateType;

	private String stateCode;

	private String bnftLineNotesExist;

	private int sequenceId;

	//  Change Level/line hide
	private boolean lineHideStatus;

	private boolean levelHideStatus;

	private String levelHide;

	private String lineHide;

	private int benefitSysId;

	private int benefitDefinitionId;

	//benefit customized sys id
	private int customizedSysId;

	// added on 10thDec 2007
	private boolean isModified;

	private int benefitLineCount;

	private int refCount;

	private String benefitHide;

	private String lineValue;

	private String termCombined;

	private String qualifierCombined;

	private int tierSysId;

	private int tierIndicator;

	private String tierTypeIndicator;

	private String isTierDefExist;

	private String isTierLevelExist;

	private int productSysId;
	//CARS START
    //DESCRIPTION : Variable to hold frequnecy value.
    private int frequencyValue;
    
    private int lowerLevelFrequencyValue;
    
    private String lowerLevelDescValue;
    //CARS END
    
    private String benefitName;
    
    private String noteText;
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
	 * @return Returns the benefitDefinitionId.
	 */
	public int getBenefitDefinitionId() {
		return benefitDefinitionId;
	}

	/**
	 * @param benefitDefinitionId
	 *            The benefitDefinitionId to set.
	 */
	public void setBenefitDefinitionId(int benefitDefinitionId) {
		this.benefitDefinitionId = benefitDefinitionId;
	}

	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}

	/**
	 * @param benefitSysId
	 *            The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}

	/**
	 * @return Returns the levelHideStatus.
	 */
	public boolean isLevelHideStatus() {
		return levelHideStatus;
	}

	/**
	 * @param levelHideStatus
	 *            The levelHideStatus to set.
	 */
	public void setLevelHideStatus(boolean levelHideStatus) {
		this.levelHideStatus = levelHideStatus;
	}

	/**
	 * @return Returns the levelHide.
	 */
	public String getLevelHide() {
		return levelHide;
	}

	/**
	 * @param levelHide
	 *            The levelHide to set.
	 */
	public void setLevelHide(String levelHide) {
		this.levelHide = levelHide;
	}

	/**
	 * @return Returns the lineHide.
	 */
	public String getLineHide() {
		return lineHide;
	}

	/**
	 * @param lineHide
	 *            The lineHide to set.
	 */
	public void setLineHide(String lineHide) {
		this.lineHide = lineHide;
	}

	/**
	 * @return Returns the lineHideStatus.
	 */
	public boolean isLineHideStatus() {
		return lineHideStatus;
	}

	/**
	 * @param lineHideStatus
	 *            The lineHideStatus to set.
	 */
	public void setLineHideStatus(boolean lineHideStatus) {
		this.lineHideStatus = lineHideStatus;
	}

	/**
	 * Returns the bnftLineNotesExist
	 * 
	 * @return String bnftLineNotesExist.
	 */

	public String getBnftLineNotesExist() {
		return bnftLineNotesExist;
	}

	/**
	 * Sets the bnftLineNotesExist
	 * 
	 * @param bnftLineNotesExist.
	 */

	public void setBnftLineNotesExist(String bnftLineNotesExist) {
		this.bnftLineNotesExist = bnftLineNotesExist;
	}

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}

	/**
	 * @param benefitComponentId
	 *            The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	/**
	 * Returns the benefitValue
	 * 
	 * @return String benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}

	/**
	 * Sets the benefitValue
	 * 
	 * @param benefitValue.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}

	/**
	 * Returns the dataTypeDesc
	 * 
	 * @return String dataTypeDesc.
	 */
	public String getDataTypeDesc() {
		return dataTypeDesc;
	}

	/**
	 * Sets the dataTypeDesc
	 * 
	 * @param dataTypeDesc.
	 */
	public void setDataTypeDesc(String dataTypeDesc) {
		this.dataTypeDesc = dataTypeDesc;
	}

	/**
	 * Returns the dataTypeId
	 * 
	 * @return String dataTypeId.
	 */
	public String getDataTypeId() {
		return dataTypeId;
	}

	/**
	 * Sets the dataTypeId
	 * 
	 * @param dataTypeId.
	 */
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	/**
	 * Returns the dataTypeLegend
	 * 
	 * @return String dataTypeLegend.
	 */
	public String getDataTypeLegend() {
		return dataTypeLegend;
	}

	/**
	 * Sets the dataTypeLegend
	 * 
	 * @param dataTypeLegend.
	 */
	public void setDataTypeLegend(String dataTypeLegend) {
		this.dataTypeLegend = dataTypeLegend;
	}

	/**
	 * Returns the providerArrangementDesc
	 * 
	 * @return String providerArrangementDesc.
	 */
	public String getProviderArrangementDesc() {
		return providerArrangementDesc;
	}

	/**
	 * Sets the providerArrangementDesc
	 * 
	 * @param providerArrangementDesc.
	 */
	public void setProviderArrangementDesc(String providerArrangementDesc) {
		this.providerArrangementDesc = providerArrangementDesc;
	}

	/**
	 * Returns the providerArrangementId
	 * 
	 * @return String providerArrangementId.
	 */
	public String getProviderArrangementId() {
		return providerArrangementId;
	}

	/**
	 * Sets the providerArrangementId
	 * 
	 * @param providerArrangementId.
	 */
	public void setProviderArrangementId(String providerArrangementId) {
		this.providerArrangementId = providerArrangementId;
	}

	/**
	 * Returns the levelDesc
	 * 
	 * @return String levelDesc.
	 */
	public String getLevelDesc() {
		return levelDesc;
	}

	/**
	 * Sets the levelDesc
	 * 
	 * @param levelDesc.
	 */
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

	/**
	 * Returns the levelSysId
	 * 
	 * @return int levelSysId.
	 */
	public int getLevelSysId() {
		return levelSysId;
	}

	/**
	 * Sets the levelSysId
	 * 
	 * @param levelSysId.
	 */
	public void setLevelSysId(int levelSysId) {
		this.levelSysId = levelSysId;
	}

	/**
	 * Returns the lineSysId
	 * 
	 * @return int lineSysId.
	 */
	public int getLineSysId() {
		return lineSysId;
	}

	/**
	 * Sets the lineSysId
	 * 
	 * @param lineSysId.
	 */
	public void setLineSysId(int lineSysId) {
		this.lineSysId = lineSysId;
	}

	/**
	 * Returns the qualifierDesc
	 * 
	 * @return String qualifierDesc.
	 */
	public String getQualifierDesc() {
		return qualifierDesc;
	}

	/**
	 * Sets the qualifierDesc
	 * 
	 * @param qualifierDesc.
	 */
	public void setQualifierDesc(String qualifierDesc) {
		this.qualifierDesc = qualifierDesc;
	}

	/**
	 * Returns the referenceDesc
	 * 
	 * @return String referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}

	/**
	 * Sets the referenceDesc
	 * 
	 * @param referenceDesc.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}

	/**
	 * Returns the termDesc
	 * 
	 * @return String termDesc.
	 */
	public String getTermDesc() {
		return termDesc;
	}

	/**
	 * Sets the termDesc
	 * 
	 * @param termDesc.
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}

	/**
	 * Returns the createdTimestamp
	 * 
	 * @return Date createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/**
	 * Sets the createdTimestamp
	 * 
	 * @param createdTimestamp.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	/**
	 * Returns the createdUser
	 * 
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * Sets the createdUser
	 * 
	 * @param createdUser.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * Returns the lastUpdatedTimestamp
	 * 
	 * @return Date lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	/**
	 * Sets the lastUpdatedTimestamp
	 * 
	 * @param lastUpdatedTimestamp.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
	 * Returns the entitySysId
	 * 
	 * @return int entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}

	/**
	 * Sets the entitySysId
	 * 
	 * @param entitySysId.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
	}

	/**
	 * Returns the entityType
	 * 
	 * @return String entityType.
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * Sets the entityType
	 * 
	 * @param entityType.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return benefitType
	 * 
	 * Returns the benefitType.
	 */
	public String getBenefitType() {
		return benefitType;
	}

	/**
	 * @param benefitType
	 * 
	 * Sets the benefitType.
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	/**
	 * @return mandateType
	 * 
	 * Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}

	/**
	 * @param mandateType
	 * 
	 * Sets the mandateType.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	/**
	 * @return stateCode
	 * 
	 * Returns the stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode
	 * 
	 * Sets the stateCode.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * Returns the referenceCode
	 * 
	 * @return String referenceCode.
	 */
	public String getReferenceCode() {
		return referenceCode;
	}

	/**
	 * Sets the referenceCode
	 * 
	 * @param referenceCode.
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	/**
	 * @return Returns the sequenceId.
	 */
	public int getSequenceId() {
		return sequenceId;
	}

	/**
	 * @param sequenceId
	 *            The sequenceId to set.
	 */
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * @return Returns the customizedSysId.
	 */
	public int getCustomizedSysId() {
		return customizedSysId;
	}

	/**
	 * @param customizedSysId
	 *            The customizedSysId to set.
	 */
	public void setCustomizedSysId(int customizedSysId) {
		this.customizedSysId = customizedSysId;
	}

	/**
	 * @return Returns the benefitLineCount.
	 */
	public int getBenefitLineCount() {
		return benefitLineCount;
	}

	/**
	 * @param benefitLineCount
	 *            The benefitLineCount to set.
	 */
	public void setBenefitLineCount(int benefitLineCount) {
		this.benefitLineCount = benefitLineCount;
	}

	/**
	 * @return Returns the refCount.
	 */
	public int getRefCount() {
		return refCount;
	}

	/**
	 * @param refCount
	 *            The refCount to set.
	 */
	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}

	/**
	 * @return Returns the benefitHide.
	 */
	public String getBenefitHide() {
		return benefitHide;
	}

	/**
	 * @param benefitHide
	 *            The benefitHide to set.
	 */
	public void setBenefitHide(String benefitHide) {
		this.benefitHide = benefitHide;
	}

	public String getLineValue() {
		return lineValue;
	}

	public void setLineValue(String lineValue) {
		this.lineValue = lineValue;
	}

	/**
	 * @return Returns the qualifierCode.
	 */
	public String getQualifierCode() {
		return qualifierCode;
	}

	/**
	 * @param qualifierCode
	 *            The qualifierCode to set.
	 */
	public void setQualifierCode(String qualifierCode) {
		this.qualifierCode = qualifierCode;
	}

	/**
	 * @return Returns the termCode.
	 */
	public String getTermCode() {
		return termCode;
	}

	/**
	 * @param termCode
	 *            The termCode to set.
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	/**
	 * @return Returns the qualifierCombined.
	 */
	public String isQualifierCombined() {
		return qualifierCombined;
	}

	/**
	 * @param qualifierCombined
	 *            The qualifierCombined to set.
	 */
	public void setQualifierCombined(String qualifierCombined) {
		this.qualifierCombined = qualifierCombined;
	}

	/**
	 * @return Returns the termCombined.
	 */
	public String isTermCombined() {
		return termCombined;
	}

	/**
	 * @param termCombined
	 *            The termCombined to set.
	 */
	public void setTermCombined(String termCombined) {
		this.termCombined = termCombined;
	}

	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}

	/**
	 * @param tierSysId
	 *            The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}

	/**
	 * @return Returns the tierIndicator.
	 */
	public int getTierIndicator() {
		return tierIndicator;
	}

	/**
	 * @param tierIndicator
	 *            The tierIndicator to set.
	 */
	public void setTierIndicator(int tierIndicator) {
		this.tierIndicator = tierIndicator;
	}

	/**
	 * @return Returns the tierTypeIndicator.
	 */
	public String getTierTypeIndicator() {
		return tierTypeIndicator;
	}

	/**
	 * @param tierTypeIndicator
	 *            The tierTypeIndicator to set.
	 */
	public void setTierTypeIndicator(String tierTypeIndicator) {
		this.tierTypeIndicator = tierTypeIndicator;
	}

	/**
	 * @return Returns the isTierDefExist.
	 */
	public String getIsTierDefExist() {
		return isTierDefExist;
	}

	/**
	 * @param isTierDefExist
	 *            The isTierDefExist to set.
	 */
	public void setIsTierDefExist(String isTierDefExist) {
		this.isTierDefExist = isTierDefExist;
	}

	/**
	 * @return Returns the isTierLevelExist.
	 */
	public String getIsTierLevelExist() {
		return isTierLevelExist;
	}

	/**
	 * @param isTierLevelExist
	 *            The isTierLevelExist to set.
	 */
	public void setIsTierLevelExist(String isTierLevelExist) {
		this.isTierLevelExist = isTierLevelExist;
	}

	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}

	/**
	 * @param productSysId
	 *            The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the frequencyValue.
	 */
	public int getFrequencyValue() {
		return frequencyValue;
	}
	/**
	 * @param frequencyValue The frequencyValue to set.
	 */
	public void setFrequencyValue(int frequencyValue) {
		this.frequencyValue = frequencyValue;
	}
	/**
	 * @return Returns the lowerLevelDescValue.
	 */
	public String getLowerLevelDescValue() {
		return lowerLevelDescValue;
	}
	/**
	 * @param lowerLevelDescValue The lowerLevelDescValue to set.
	 */
	public void setLowerLevelDescValue(String lowerLevelDescValue) {
		this.lowerLevelDescValue = lowerLevelDescValue;
	}
	/**
	 * @return Returns the lowerLevelFrequencyValue.
	 */
	public int getLowerLevelFrequencyValue() {
		return lowerLevelFrequencyValue;
	}
	/**
	 * @param lowerLevelFrequencyValue The lowerLevelFrequencyValue to set.
	 */
	public void setLowerLevelFrequencyValue(int lowerLevelFrequencyValue) {
		this.lowerLevelFrequencyValue = lowerLevelFrequencyValue;
	}

	public String getBenefitName() {
		return benefitName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

}