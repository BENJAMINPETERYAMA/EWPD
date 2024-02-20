/*
 * ContractModel.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.model;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractModel {

	private String regulatoryAgency;

	private String complianceStatus;

	private String productNameCode;

	private Date contractTermDate;

	private String multiPlanIndicator;

	private String performanceGuarentee;

	private Date salesMarketDate;

	private String caseNumber;

	private String caseName;

	private Date caseeffectiveCancelDate;

	private String caseHQState;

	private String groupNumber;

	private String groupName;

	private Date groupeffectiveCancelDate;

	private String fundingArrangement;

	private String mbuCodeValue;

	private String rerateCodeValue;

	private String ruleType;

	private String ewpdRuleId;

	private String groupIndicator;

	private String value;

	private int benefitComponentParentSysId;

	private int productParentSysId;

	private int benefitParentSysId;

	private int benefitAdminSysid;

	private int contractSysId;

	private int contractParentSysId;

	private int dateSegmentId;

	private int keyValue;

	private String term;

	private String qualifier;

	private String pva;

	private String format;

	private String reference;

	private String benefitValue;

	private String benefitLevelDescription;

	private int benefitLevelId;

	private String noteId;

	private String noteName;

	private int adminOptionId;

	private String adminOptionLevel;

	private String adminOptionName;

	private String question;

	private String answer;

	private String spsName;

	private String adminMethodName;

	private String benefitName;

	private String benefitMeaning;

	private String benefitType;

	private String benefitCategory;

	private String description;

	private int version;

	private int benefitSysId;

	private String catalogType;

	private String catalogDesc;

	private String lobNm;

	private String BusinessEntityNm;

	private String BusinessGroupNm;

	private String ruleId;

	private String ruleDesc;

	private String benefitComponentName;

	private String benefitComponentType;

	private String benefitComponentDescription;

	private String effectiveDate;

	private String expiryDate;

	private int benefitComponentId;

	private String benefitComponentStatus;

	private int prodId;

	private String productName;

	private String productFamily;

	private String productType;

	private String productStructureName;

	private int productStructureVersion;

	private String productStructureDescription;

	private String contractId;

	private String contractType;

	private String baseContractId;

	private Date baseContractEffectiveDate;

	private String groupSize;

	private String productCode;

	private String standardPlanCode;

	private String benefitPlan;

	private String corporatePlanCode;

	private String brandName;

	private String cobAdjudicationIndcator;

	private String medicareAdjudicationIndicator;

	private String itsAdjudicationIndicator;

	private String pricing;

	private String coverage;

	private String network;

	private int tierSysId;

	private String tierCode;

	private String tierDataType;

	private String criteriaName;

	private int displaySequenceNumber;

	private String criteriaValue;

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
	 * @return Returns the criteriaName.
	 */
	public String getCriteriaName() {
		return criteriaName;
	}

	/**
	 * @param criteriaName
	 *            The criteriaName to set.
	 */
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}

	/**
	 * @return Returns the criteriaValue.
	 */
	public String getCriteriaValue() {
		return criteriaValue;
	}

	/**
	 * @param criteriaValue
	 *            The criteriaValue to set.
	 */
	public void setCriteriaValue(String criteriaValue) {
		this.criteriaValue = criteriaValue;
	}

	/**
	 * @return Returns the tierDataType.
	 */
	public String getTierDataType() {
		return tierDataType;
	}

	/**
	 * @param tierDataType
	 *            The tierDataType to set.
	 */
	public void setTierDataType(String tierDataType) {
		this.tierDataType = tierDataType;
	}

	/**
	 * @return Returns the displaySequenceNumber.
	 */
	public int getDisplaySequenceNumber() {
		return displaySequenceNumber;
	}

	/**
	 * @param displaySequenceNumber
	 *            The displaySequenceNumber to set.
	 */
	public void setDisplaySequenceNumber(int displaySequenceNumber) {
		this.displaySequenceNumber = displaySequenceNumber;
	}

	/**
	 * @return Returns the tierCode.
	 */
	public String getTierCode() {
		return tierCode;
	}

	/**
	 * @param tierCode
	 *            The tierCode to set.
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}

	/**
	 * @return Returns the benefitComponentParentSysId.
	 */
	public int getBenefitComponentParentSysId() {
		return benefitComponentParentSysId;
	}

	/**
	 * @param benefitComponentParentSysId
	 *            The benefitComponentParentSysId to set.
	 */
	public void setBenefitComponentParentSysId(int benefitComponentParentSysId) {
		this.benefitComponentParentSysId = benefitComponentParentSysId;
	}

	/**
	 * @return Returns the productParentSysId.
	 */
	public int getProductParentSysId() {
		return productParentSysId;
	}

	/**
	 * @param productParentSysId
	 *            The productParentSysId to set.
	 */
	public void setProductParentSysId(int productParentSysId) {
		this.productParentSysId = productParentSysId;
	}

	/**
	 * @return Returns the salesMarketDate.
	 */
	public Date getSalesMarketDate() {
		return salesMarketDate;
	}

	/**
	 * @param salesMarketDate
	 *            The salesMarketDate to set.
	 */
	public void setSalesMarketDate(Date salesMarketDate) {
		this.salesMarketDate = salesMarketDate;
	}

	/**
	 * @return Returns the complianceStatus.
	 */
	public String getComplianceStatus() {
		return complianceStatus;
	}

	/**
	 * @param complianceStatus
	 *            The complianceStatus to set.
	 */
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

	/**
	 * @return Returns the contractTermDate.
	 */
	public Date getContractTermDate() {
		return contractTermDate;
	}

	/**
	 * @param contractTermDate
	 *            The contractTermDate to set.
	 */
	public void setContractTermDate(Date contractTermDate) {
		this.contractTermDate = contractTermDate;
	}

	/**
	 * @return Returns the multiPlanIndicator.
	 */
	public String getMultiPlanIndicator() {
		return multiPlanIndicator;
	}

	/**
	 * @param multiPlanIndicator
	 *            The multiPlanIndicator to set.
	 */
	public void setMultiPlanIndicator(String multiPlanIndicator) {
		this.multiPlanIndicator = multiPlanIndicator;
	}

	/**
	 * @return Returns the performanceGuarentee.
	 */
	public String getPerformanceGuarentee() {
		return performanceGuarentee;
	}

	/**
	 * @param performanceGuarentee
	 *            The performanceGuarentee to set.
	 */
	public void setPerformanceGuarentee(String performanceGuarentee) {
		this.performanceGuarentee = performanceGuarentee;
	}

	/**
	 * @return Returns the productNameCode.
	 */
	public String getProductNameCode() {
		return productNameCode;
	}

	/**
	 * @param productNameCode
	 *            The productNameCode to set.
	 */
	public void setProductNameCode(String productNameCode) {
		this.productNameCode = productNameCode;
	}

	/**
	 * @return Returns the regulatoryAgency.
	 */
	public String getRegulatoryAgency() {
		return regulatoryAgency;
	}

	/**
	 * @param regulatoryAgency
	 *            The regulatoryAgency to set.
	 */
	public void setRegulatoryAgency(String regulatoryAgency) {
		this.regulatoryAgency = regulatoryAgency;
	}

	/**
	 * @return Returns the caseeffectiveCancelDate.
	 */
	public Date getCaseeffectiveCancelDate() {
		return caseeffectiveCancelDate;
	}

	/**
	 * @param caseeffectiveCancelDate
	 *            The caseeffectiveCancelDate to set.
	 */
	public void setCaseeffectiveCancelDate(Date caseeffectiveCancelDate) {
		this.caseeffectiveCancelDate = caseeffectiveCancelDate;
	}

	/**
	 * @return Returns the caseHQState.
	 */
	public String getCaseHQState() {
		return caseHQState;
	}

	/**
	 * @param caseHQState
	 *            The caseHQState to set.
	 */
	public void setCaseHQState(String caseHQState) {
		this.caseHQState = caseHQState;
	}

	/**
	 * @return Returns the caseName.
	 */
	public String getCaseName() {
		return caseName;
	}

	/**
	 * @param caseName
	 *            The caseName to set.
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @param caseNumber
	 *            The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	/**
	 * @return Returns the fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}

	/**
	 * @param fundingArrangement
	 *            The fundingArrangement to set.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}

	/**
	 * @return Returns the groupeffectiveCancelDate.
	 */
	public Date getGroupeffectiveCancelDate() {
		return groupeffectiveCancelDate;
	}

	/**
	 * @param groupeffectiveCancelDate
	 *            The groupeffectiveCancelDate to set.
	 */
	public void setGroupeffectiveCancelDate(Date groupeffectiveCancelDate) {
		this.groupeffectiveCancelDate = groupeffectiveCancelDate;
	}

	/**
	 * @return Returns the groupName.
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            The groupName to set.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return Returns the groupNumber.
	 */
	public String getGroupNumber() {
		return groupNumber;
	}

	/**
	 * @param groupNumber
	 *            The groupNumber to set.
	 */
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	/**
	 * @return Returns the mbuCodeValue.
	 */
	public String getMbuCodeValue() {
		return mbuCodeValue;
	}

	/**
	 * @param mbuCodeValue
	 *            The mbuCodeValue to set.
	 */
	public void setMbuCodeValue(String mbuCodeValue) {
		this.mbuCodeValue = mbuCodeValue;
	}

	/**
	 * @return Returns the rerateCodeValue.
	 */
	public String getRerateCodeValue() {
		return rerateCodeValue;
	}

	/**
	 * @param rerateCodeValue
	 *            The rerateCodeValue to set.
	 */
	public void setRerateCodeValue(String rerateCodeValue) {
		this.rerateCodeValue = rerateCodeValue;
	}

	/**
	 * @return Returns the contractParentSysId.
	 */
	public int getContractParentSysId() {
		return contractParentSysId;
	}

	/**
	 * @param contractParentSysId
	 *            The contractParentSysId to set.
	 */
	public void setContractParentSysId(int contractParentSysId) {
		this.contractParentSysId = contractParentSysId;
	}

	/**
	 * @return Returns the ewpdRuleId.
	 */
	public String getEwpdRuleId() {
		return ewpdRuleId;
	}

	/**
	 * @param ewpdRuleId
	 *            The ewpdRuleId to set.
	 */
	public void setEwpdRuleId(String ewpdRuleId) {
		this.ewpdRuleId = ewpdRuleId;
	}

	/**
	 * @return Returns the groupIndicator.
	 */
	public String getGroupIndicator() {
		return groupIndicator;
	}

	/**
	 * @param groupIndicator
	 *            The groupIndicator to set.
	 */
	public void setGroupIndicator(String groupIndicator) {
		this.groupIndicator = groupIndicator;
	}

	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}

	/**
	 * @param ruleType
	 *            The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the coverage.
	 */
	public String getCoverage() {
		return coverage;
	}

	/**
	 * @param coverage
	 *            The coverage to set.
	 */
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	/**
	 * @return Returns the network.
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 *            The network to set.
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return Returns the pricing.
	 */
	public String getPricing() {
		return pricing;
	}

	/**
	 * @param pricing
	 *            The pricing to set.
	 */
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}

	/**
	 * @return Returns the benefitParentSysId.
	 */
	public int getBenefitParentSysId() {
		return benefitParentSysId;
	}

	/**
	 * @param benefitParentSysId
	 *            The benefitParentSysId to set.
	 */
	public void setBenefitParentSysId(int benefitParentSysId) {
		this.benefitParentSysId = benefitParentSysId;
	}

	/**
	 * @return Returns the benefitAdminSysid.
	 */
	public int getBenefitAdminSysid() {
		return benefitAdminSysid;
	}

	/**
	 * @param benefitAdminSysid
	 *            The benefitAdminSysid to set.
	 */
	public void setBenefitAdminSysid(int benefitAdminSysid) {
		this.benefitAdminSysid = benefitAdminSysid;
	}

	/**
	 * @return Returns the baseContractEffectiveDate.
	 */
	public Date getBaseContractEffectiveDate() {
		return baseContractEffectiveDate;
	}

	/**
	 * @param baseContractEffectiveDate
	 *            The baseContractEffectiveDate to set.
	 */
	public void setBaseContractEffectiveDate(Date baseContractEffectiveDate) {
		this.baseContractEffectiveDate = baseContractEffectiveDate;
	}

	/**
	 * @return Returns the baseContractId.
	 */
	public String getBaseContractId() {
		return baseContractId;
	}

	/**
	 * @param baseContractId
	 *            The baseContractId to set.
	 */
	public void setBaseContractId(String baseContractId) {
		this.baseContractId = baseContractId;
	}

	/**
	 * @return Returns the benefitPlan.
	 */
	public String getBenefitPlan() {
		return benefitPlan;
	}

	/**
	 * @param benefitPlan
	 *            The benefitPlan to set.
	 */
	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
	}

	/**
	 * @return Returns the brandName.
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName
	 *            The brandName to set.
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return Returns the cobAdjudicationIndcator.
	 */
	public String getCobAdjudicationIndcator() {
		return cobAdjudicationIndcator;
	}

	/**
	 * @param cobAdjudicationIndcator
	 *            The cobAdjudicationIndcator to set.
	 */
	public void setCobAdjudicationIndcator(String cobAdjudicationIndcator) {
		this.cobAdjudicationIndcator = cobAdjudicationIndcator;
	}

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId
	 *            The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}

	/**
	 * @param contractType
	 *            The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * @return Returns the corporatePlanCode.
	 */
	public String getCorporatePlanCode() {
		return corporatePlanCode;
	}

	/**
	 * @param corporatePlanCode
	 *            The corporatePlanCode to set.
	 */
	public void setCorporatePlanCode(String corporatePlanCode) {
		this.corporatePlanCode = corporatePlanCode;
	}

	/**
	 * @return Returns the groupSize.
	 */
	public String getGroupSize() {
		return groupSize;
	}

	/**
	 * @param groupSize
	 *            The groupSize to set.
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	/**
	 * @return Returns the itsAdjudicationIndicator.
	 */
	public String getItsAdjudicationIndicator() {
		return itsAdjudicationIndicator;
	}

	/**
	 * @param itsAdjudicationIndicator
	 *            The itsAdjudicationIndicator to set.
	 */
	public void setItsAdjudicationIndicator(String itsAdjudicationIndicator) {
		this.itsAdjudicationIndicator = itsAdjudicationIndicator;
	}

	/**
	 * @return Returns the medicareAdjudicationIndicator.
	 */
	public String getMedicareAdjudicationIndicator() {
		return medicareAdjudicationIndicator;
	}

	/**
	 * @param medicareAdjudicationIndicator
	 *            The medicareAdjudicationIndicator to set.
	 */
	public void setMedicareAdjudicationIndicator(
			String medicareAdjudicationIndicator) {
		this.medicareAdjudicationIndicator = medicareAdjudicationIndicator;
	}

	/**
	 * @return Returns the productCode.
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            The productCode to set.
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return Returns the standardPlanCode.
	 */
	public String getStandardPlanCode() {
		return standardPlanCode;
	}

	/**
	 * @param standardPlanCode
	 *            The standardPlanCode to set.
	 */
	public void setStandardPlanCode(String standardPlanCode) {
		this.standardPlanCode = standardPlanCode;
	}

	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}

	/**
	 * @param dateSegmentId
	 *            The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}

	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}

	/**
	 * @param contractSysId
	 *            The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}

	/**
	 * @return Returns the prodId.
	 */
	public int getProdId() {
		return prodId;
	}

	/**
	 * @param prodId
	 *            The prodId to set.
	 */
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}

	/**
	 * @param productFamily
	 *            The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return Returns the productStructureDescription.
	 */
	public String getProductStructureDescription() {
		return productStructureDescription;
	}

	/**
	 * @param productStructureDescription
	 *            The productStructureDescription to set.
	 */
	public void setProductStructureDescription(
			String productStructureDescription) {
		this.productStructureDescription = productStructureDescription;
	}

	/**
	 * @return Returns the productStructureName.
	 */
	public String getProductStructureName() {
		return productStructureName;
	}

	/**
	 * @param productStructureName
	 *            The productStructureName to set.
	 */
	public void setProductStructureName(String productStructureName) {
		this.productStructureName = productStructureName;
	}

	/**
	 * @return Returns the productStructureVersion.
	 */
	public int getProductStructureVersion() {
		return productStructureVersion;
	}

	/**
	 * @param productStructureVersion
	 *            The productStructureVersion to set.
	 */
	public void setProductStructureVersion(int productStructureVersion) {
		this.productStructureVersion = productStructureVersion;
	}

	/**
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
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
	 * @return Returns the benefitComponentDescription.
	 */
	public String getBenefitComponentDescription() {
		return benefitComponentDescription;
	}

	/**
	 * @param benefitComponentDescription
	 *            The benefitComponentDescription to set.
	 */
	public void setBenefitComponentDescription(
			String benefitComponentDescription) {
		this.benefitComponentDescription = benefitComponentDescription;
	}

	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}

	/**
	 * @param benefitComponentName
	 *            The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}

	/**
	 * @return Returns the benefitComponentType.
	 */
	public String getBenefitComponentType() {
		return benefitComponentType;
	}

	/**
	 * @param benefitComponentType
	 *            The benefitComponentType to set.
	 */
	public void setBenefitComponentType(String benefitComponentType) {
		this.benefitComponentType = benefitComponentType;
	}

	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return Returns the ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}

	/**
	 * @param ruleDesc
	 *            The ruleDesc to set.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId
	 *            The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return Returns the businessEntityNm.
	 */
	public String getBusinessEntityNm() {
		return BusinessEntityNm;
	}

	/**
	 * @param businessEntityNm
	 *            The businessEntityNm to set.
	 */
	public void setBusinessEntityNm(String businessEntityNm) {
		BusinessEntityNm = businessEntityNm;
	}

	/**
	 * @return Returns the businessGroupNm.
	 */
	public String getBusinessGroupNm() {
		return BusinessGroupNm;
	}

	/**
	 * @param businessGroupNm
	 *            The businessGroupNm to set.
	 */
	public void setBusinessGroupNm(String businessGroupNm) {
		BusinessGroupNm = businessGroupNm;
	}

	/**
	 * @return Returns the lobNm.
	 */
	public String getLobNm() {
		return lobNm;
	}

	/**
	 * @param lobNm
	 *            The lobNm to set.
	 */
	public void setLobNm(String lobNm) {
		this.lobNm = lobNm;
	}

	/**
	 * @return Returns the catalogDesc.
	 */
	public String getCatalogDesc() {
		return catalogDesc;
	}

	/**
	 * @param catalogDesc
	 *            The catalogDesc to set.
	 */
	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}

	/**
	 * @return Returns the catalogType.
	 */
	public String getCatalogType() {
		return catalogType;
	}

	/**
	 * @param catalogType
	 *            The catalogType to set.
	 */
	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}

	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}

	/**
	 * @param benefitCategory
	 *            The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}

	/**
	 * @return Returns the benefitMeaning.
	 */
	public String getBenefitMeaning() {
		return benefitMeaning;
	}

	/**
	 * @param benefitMeaning
	 *            The benefitMeaning to set.
	 */
	public void setBenefitMeaning(String benefitMeaning) {
		this.benefitMeaning = benefitMeaning;
	}

	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}

	/**
	 * @param benefitName
	 *            The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}

	/**
	 * @return Returns the benefitType.
	 */
	public String getBenefitType() {
		return benefitType;
	}

	/**
	 * @param benefitType
	 *            The benefitType to set.
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return Returns the adminMethodName.
	 */
	public String getAdminMethodName() {
		return adminMethodName;
	}

	/**
	 * @param adminMethodName
	 *            The adminMethodName to set.
	 */
	public void setAdminMethodName(String adminMethodName) {
		this.adminMethodName = adminMethodName;
	}

	/**
	 * @return Returns the spsName.
	 */
	public String getSpsName() {
		return spsName;
	}

	/**
	 * @param spsName
	 *            The spsName to set.
	 */
	public void setSpsName(String spsName) {
		this.spsName = spsName;
	}

	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}

	/**
	 * @param adminOptionId
	 *            The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}

	/**
	 * @return Returns the adminOptionLevel.
	 */
	public String getAdminOptionLevel() {
		return adminOptionLevel;
	}

	/**
	 * @param adminOptionLevel
	 *            The adminOptionLevel to set.
	 */
	public void setAdminOptionLevel(String adminOptionLevel) {
		this.adminOptionLevel = adminOptionLevel;
	}

	/**
	 * @return Returns the answer.
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            The answer to set.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return Returns the question.
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            The question to set.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return Returns the keyValue.
	 */
	public int getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue
	 *            The keyValue to set.
	 */
	public void setKeyValue(int keyValue) {
		this.keyValue = keyValue;
	}

	/**
	 * @return Returns the benefitLevelDescription.
	 */
	public String getBenefitLevelDescription() {
		return benefitLevelDescription;
	}

	/**
	 * @param benefitLevelDescription
	 *            The benefitLevelDescription to set.
	 */
	public void setBenefitLevelDescription(String benefitLevelDescription) {
		this.benefitLevelDescription = benefitLevelDescription;
	}

	/**
	 * @return Returns the benefitValue.
	 */
	public String getBenefitValue() {
		return benefitValue;
	}

	/**
	 * @param benefitValue
	 *            The benefitValue to set.
	 */
	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}

	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}

	/**
	 * @param noteId
	 *            The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}

	/**
	 * @param noteName
	 *            The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}

	/**
	 * @param pva
	 *            The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
	}

	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * @param qualifier
	 *            The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * @return Returns the reference.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            The reference to set.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * @param term
	 *            The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
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
	 * @return Returns the benefitComponentStatus.
	 */
	public String getBenefitComponentStatus() {
		return benefitComponentStatus;
	}

	/**
	 * @param benefitComponentStatus
	 *            The benefitComponentStatus to set.
	 */
	public void setBenefitComponentStatus(String benefitComponentStatus) {
		this.benefitComponentStatus = benefitComponentStatus;
	}

	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		return adminOptionName;
	}

	/**
	 * @param adminOptionName
	 *            The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
	}

	/**
	 * @return Returns the benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}

	/**
	 * @param benefitLevelId The benefitLevelId to set.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
}