/*
 * StandardBenefitBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitBO extends BusinessObject {

	private int standardBenefitKey;

	private String benefitIdentifier;

	private String description;

	private BenefitDefinitionBO benefitDefinition;

	// for retrieving the nonAdjMandateDetails
	private BenefitMandateBO benefitMandateBOImpl;

	private List ruleList;
	private List ruleTyepLst;

	private int parentSystemId;

	private String mandateType;

	private String benefitType;

	private List termList = null;

	private List qualifierList = null;

	private List PVAList = null;

	private List dataTypeList = null;

	private List ruleTypeList = null;

	private String stateCode;

	private String mandateDesc;

	private String stateDesc;

	private String ruleId;
	
	private String strRuleType;
	

	private String action;

	private String lineOfBusiness;

	private String businessEntity;

	private String businessGroup;
	
	private String marketBusinessUnit;

	//single list for business domain
	private List businessDomains = null;

	private String benefitHideFlag;

	private boolean BenefitVisibilityStatus;

	private List lobList;

	private List beList;

	private List bgList;
	
	private List marketBusinessUnitList;

	private String benefitMeaning;

	private List ruleIdList;

	private List ruleNameList;

	private String benefitCategory;

	private String benefitCategoryDesc;
	
	private DomainDetail domainDetail;
	
	private List tierDefinitionList;
	


	/**
	 * @return Returns the benefitCategoryDesc.
	 */
	public String getBenefitCategoryDesc() {
		return benefitCategoryDesc;
	}

	/**
	 * @param benefitCategoryDesc
	 *            The benefitCategoryDesc to set.
	 */
	public void setBenefitCategoryDesc(String benefitCategoryDesc) {
		this.benefitCategoryDesc = benefitCategoryDesc;
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
	 * @return Returns the beList.
	 */
	public List getBeList() {
		return beList;
	}

	/**
	 * @param beList
	 *            The beList to set.
	 */
	public void setBeList(List beList) {
		this.beList = beList;
	}

	/**
	 * @return Returns the bgList.
	 */
	public List getBgList() {
		return bgList;
	}

	/**
	 * @param bgList
	 *            The bgList to set.
	 */
	public void setBgList(List bgList) {
		this.bgList = bgList;
	}

	/**
	 * @return Returns the lobList.
	 */
	public List getLobList() {
		return lobList;
	}

	/**
	 * @param lobList
	 *            The lobList to set.
	 */
	public void setLobList(List lobList) {
		this.lobList = lobList;
	}

	/**
	 * @return businessEntity
	 * 
	 * Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}

	/**
	 * @param businessEntity
	 * 
	 * Sets the businessEntity.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	/**
	 * @return businessGroup
	 * 
	 * Returns the businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}

	/**
	 * @param businessGroup
	 * 
	 * Sets the businessGroup.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	/**
	 * @return lineOfBusiness
	 * 
	 * Returns the lineOfBusiness.
	 */
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	/**
	 * @param lineOfBusiness
	 * 
	 * Sets the lineOfBusiness.
	 */
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
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
	 * @return Returns the mandateDesc.
	 */
	public String getMandateDesc() {
		return mandateDesc;
	}

	/**
	 * @param mandateDesc
	 *            The mandateDesc to set.
	 */
	public void setMandateDesc(String mandateDesc) {
		this.mandateDesc = mandateDesc;
	}

	/**
	 * @return Returns the stateDesc.
	 */
	public String getStateDesc() {
		return stateDesc;
	}

	/**
	 * @param stateDesc
	 *            The stateDesc to set.
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	/**
	 * @return Returns the stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode
	 *            The stateCode to set.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
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
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}

	/**
	 * @param mandateType
	 *            The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	/**
	 * @return Returns the ruleTypeList.
	 */
	public List getRuleTypeList() {
		return ruleTypeList;
	}

	/**
	 * @param ruleTypeList
	 *            The ruleTypeList to set.
	 */
	public void setRuleTypeList(List ruleTypeList) {
		this.ruleTypeList = ruleTypeList;
	}

	/**
	 * Returns the standardBenefitKey
	 * 
	 * @return int standardBenefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}

	/**
	 * Sets the standardBenefitKey
	 * 
	 * @param standardBenefitKey.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		if (object instanceof StandardBenefitBO) {
			StandardBenefitBO standardBenefitBO = (StandardBenefitBO) object;
			if (this.standardBenefitKey == standardBenefitBO
					.getStandardBenefitKey())
				return true;
		}
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("StandardBenefitKey = ").append(
				this.getStandardBenefitKey());
		buffer.append("StandardBenefitParentKey = ").append(
				this.getParentSystemId());
		buffer.append(", StandardBenefitName = ").append(
				this.getBenefitIdentifier());
		buffer.append(", description = ").append(this.getDescription());
		buffer.append(", version = ").append(this.getVersion());
		buffer.append(", status = ").append(this.getStatus());
		buffer.append(", domainValue = ").append(this.getBusinessDomains());
		buffer.append(", term = ").append(this.getTermList());
		buffer.append(", qualifier = ").append(this.getQualifierList());
		buffer.append(", provider arrangement = ").append(this.getPVAList());
		buffer.append(", datatype = ").append(this.getDataTypeList());
		buffer.append(", createdUser = ").append(this.getCreatedUser());
		buffer.append(", createdTimestamp = ").append(
				this.getCreatedTimestamp());
		buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
		buffer.append(", lastUpdatedTimestamp = ").append(
				this.getLastUpdatedTimestamp());
		return buffer.toString();
	}

	/**
	 * Returns the benefitIdentifier
	 * 
	 * @return String benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}

	/**
	 * Sets the benefitIdentifier
	 * 
	 * @param benefitIdentifier.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}

	/**
	 * Returns the dataTypeList
	 * 
	 * @return List dataTypeList.
	 */
	public List getDataTypeList() {
		return dataTypeList;
	}

	/**
	 * Sets the dataTypeList
	 * 
	 * @param dataTypeList.
	 */
	public void setDataTypeList(List dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	/**
	 * Returns the description
	 * 
	 * @return String description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * 
	 * @param description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the pVAList
	 * 
	 * @return List pVAList.
	 */
	public List getPVAList() {
		return PVAList;
	}

	/**
	 * Sets the pVAList
	 * 
	 * @param pVAList.
	 */
	public void setPVAList(List list) {
		PVAList = list;
	}

	/**
	 * Returns the qualifierList
	 * 
	 * @return List qualifierList.
	 */
	public List getQualifierList() {
		return qualifierList;
	}

	/**
	 * Sets the qualifierList
	 * 
	 * @param qualifierList.
	 */
	public void setQualifierList(List qualifierList) {
		this.qualifierList = qualifierList;
	}

	/**
	 * Returns the termList
	 * 
	 * @return List termList.
	 */
	public List getTermList() {
		return termList;
	}

	/**
	 * Sets the termList
	 * 
	 * @param termList.
	 */
	public void setTermList(List termList) {
		this.termList = termList;
	}

	/**
	 * Returns the benefitDefinition
	 * 
	 * @return BenefitDefinitionBO benefitDefinition.
	 */
	public BenefitDefinitionBO getBenefitDefinition() {
		return benefitDefinition;
	}

	/**
	 * Sets the benefitDefinition
	 * 
	 * @param benefitDefinition.
	 */
	public void setBenefitDefinition(BenefitDefinitionBO benefitDefinition) {
		this.benefitDefinition = benefitDefinition;
	}

	/**
	 * @return Returns the benefitMandateBOImpl.
	 */
	public BenefitMandateBO getBenefitMandateBOImpl() {
		return benefitMandateBOImpl;
	}

	/**
	 * @param benefitMandateBOImpl
	 *            The benefitMandateBOImpl to set.
	 */
	public void setBenefitMandateBOImpl(BenefitMandateBO benefitMandateBOImpl) {
		this.benefitMandateBOImpl = benefitMandateBOImpl;
	}

	/**
	 * Returns the parentSystemId
	 * 
	 * @return int parentSystemId.
	 */
	public int getParentSystemId() {
		return parentSystemId;
	}

	/**
	 * Sets the parentSystemId
	 * 
	 * @param parentSystemId.
	 */
	public void setParentSystemId(int parentSystemId) {
		this.parentSystemId = parentSystemId;
	}

	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}

	/**
	 * @param businessDomains
	 *            The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}

	/**
	 * @return Returns the ruleIdList.
	 */
	public List getRuleIdList() {
		return ruleIdList;
	}

	/**
	 * @param ruleIdList
	 *            The ruleIdList to set.
	 */
	public void setRuleIdList(List ruleIdList) {
		this.ruleIdList = ruleIdList;
	}

	/**
	 * @return Returns the ruleNameList.
	 */
	public List getRuleNameList() {
		return ruleNameList;
	}

	/**
	 * @param ruleNameList
	 *            The ruleNameList to set.
	 */
	public void setRuleNameList(List ruleNameList) {
		this.ruleNameList = ruleNameList;
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
	 * Returns the ruleList
	 * 
	 * @return List ruleList.
	 */
	public List getRuleList() {
		return ruleList;
	}

	/**
	 * Sets the ruleList
	 * 
	 * @param ruleList.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the benefitHideFlag.
	 */
	public String getBenefitHideFlag() {
		return benefitHideFlag;
	}

	/**
	 * @param benefitHideFlag
	 *            The benefitHideFlag to set.
	 */
	public void setBenefitHideFlag(String benefitHideFlag) {
		this.benefitHideFlag = benefitHideFlag;
	}

	/**
	 * @return Returns the benefitVisibilityStatus.
	 */
	public boolean isBenefitVisibilityStatus() {
		return BenefitVisibilityStatus;
	}

	/**
	 * @param benefitVisibilityStatus
	 *            The benefitVisibilityStatus to set.
	 */
	public void setBenefitVisibilityStatus(boolean benefitVisibilityStatus) {
		BenefitVisibilityStatus = benefitVisibilityStatus;
	}
	/**
	 * @return Returns the domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}
	/**
	 * @param domainDetail The domainDetail to set.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
	/**
	 * @return Returns the strRuleType.
	 */
	public String getStrRuleType() {
		return strRuleType;
	}
	/**
	 * @param strRuleType The strRuleType to set.
	 */
	public void setStrRuleType(String strRuleType) {
		this.strRuleType = strRuleType;
	}
	/**
	 * @return Returns the ruleTyepLst.
	 */
	public List getRuleTyepLst() {
		return ruleTyepLst;
	}
	/**
	 * @param ruleTyepLst The ruleTyepLst to set.
	 */
	public void setRuleTyepLst(List ruleTyepLst) {
		this.ruleTyepLst = ruleTyepLst;
	}

	public List getTierDefinitionList() {
		return tierDefinitionList;
	}

	public void setTierDefinitionList(List tierDefinitionList) {
		this.tierDefinitionList = tierDefinitionList;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}