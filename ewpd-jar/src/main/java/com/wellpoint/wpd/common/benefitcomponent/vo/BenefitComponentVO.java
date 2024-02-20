/*
 * BenefitComponentVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.vo;

import java.util.Date;
import java.util.List;

/**
 * VO for Benifit Component
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentVO {

    //	Bnft Id and Name in BCM_BNFT_CMPNT_MSTR
    private int benefitComponentId;

    private int benefitComponentParentId;

    private String benefitComponentName;

    private int requestFlag;

    private int refereneCode;

    private List lineOfBusinessCodeList = null;

    private List businessEntityCodeList = null;

    private List businessGroupCodeList = null;
    
    private List marketBusinessUnitCodeList = null;

    private List businessDomainList;

    private Date effectivedate;

    private Date expirydate;

    //  standardBenefitKey refers to the value obtained from the tree(session)
    //	Id in BMST_BNFT_MSTR
    private int standardBenefitKey;

    private String benefitIdentifier = "";

    private String description;

    private boolean CheckInOpted;

    private List termList = null;

    private List qualifierList = null;

    private List PVAList = null;

    private List dataTypeList = null;

    private String state = null;

    private String status = null;

    private int version;

    private boolean latestVersion;

    private boolean editFlag;

    private boolean checkoutFlag;

    private boolean sendForTestFlag;

    private List selectednotesIdList;

    private String componentType;

    private String mandateType;

    private String stateId;

    private String stateDesc;

    private List ruleIdList = null;

    private List ruleNameList = null;

    private List versionList = null;

    private String action;

    private List benefitComponentFlagList = null;

    /**
     * @return Returns the checkoutFlag.
     */
    public boolean getCheckoutFlag() {
        return checkoutFlag;
    }


    /**
     * @param checkoutFlag
     *            The checkoutFlag to set.
     */
    public void setCheckoutFlag(boolean checkoutFlag) {
        this.checkoutFlag = checkoutFlag;
    }


    /**
     * @return Returns the editFlag.
     */
    public boolean getEditFlag() {
        return editFlag;
    }


    /**
     * @param editFlag
     *            The editFlag to set.
     */
    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }


    /**
     * @return Returns the latestVersion.
     */
    public boolean getLatestVersion() {
        return latestVersion;
    }


    /**
     * @param latestVersion
     *            The latestVersion to set.
     */
    public void setLatestVersion(boolean latestVersion) {
        this.latestVersion = latestVersion;
    }


    /**
     * @return Returns the sendForTestFlag.
     */
    public boolean getSendForTestFlag() {
        return sendForTestFlag;
    }


    /**
     * @param sendForTestFlag
     *            The sendForTestFlag to set.
     */
    public void setSendForTestFlag(boolean sendForTestFlag) {
        this.sendForTestFlag = sendForTestFlag;
    }


    /**
     * Returns the benefitComponentId
     * 
     * @return int benefitComponentId.
     */

    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId
     * 
     * @param benefitComponentId.
     */

    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
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
     * @return Returns the effectivedate.
     */
    public Date getEffectivedate() {
        return effectivedate;
    }


    /**
     * @param effectivedate
     *            The effectivedate to set.
     */
    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }


    /**
     * @return Returns the expirydate.
     */
    public Date getExpirydate() {
        return expirydate;
    }


    /**
     * @param expirydate
     *            The expirydate to set.
     */
    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }


    /**
     * Returns the businessEntityCodeList
     * 
     * @return List businessEntityCodeList.
     */
    public List getBusinessEntityCodeList() {
        return businessEntityCodeList;
    }


    /**
     * Sets the businessEntityCodeList
     * 
     * @param businessEntityCodeList.
     */
    public void setBusinessEntityCodeList(List businessEntityCodeList) {
        this.businessEntityCodeList = businessEntityCodeList;
    }


    /**
     * Returns the businessGroupCodeList
     * 
     * @return List businessGroupCodeList.
     */
    public List getBusinessGroupCodeList() {
        return businessGroupCodeList;
    }


    /**
     * Sets the businessGroupCodeList
     * 
     * @param businessGroupCodeList.
     */
    public void setBusinessGroupCodeList(List businessGroupCodeList) {
        this.businessGroupCodeList = businessGroupCodeList;
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
     * @return Returns the lineOfBusinessCodeList.
     */
    public List getLineOfBusinessCodeList() {
        return lineOfBusinessCodeList;
    }


    /**
     * @param lineOfBusinessCodeList
     *            The lineOfBusinessCodeList to set.
     */
    public void setLineOfBusinessCodeList(List lineOfBusinessCodeList) {
        this.lineOfBusinessCodeList = lineOfBusinessCodeList;
    }


    /**
     * Returns the refereneCode
     * 
     * @return int refereneCode.
     */
    public int getRefereneCode() {
        return refereneCode;
    }


    /**
     * Sets the refereneCode
     * 
     * @param refereneCode.
     */
    public void setRefereneCode(int refereneCode) {
        this.refereneCode = refereneCode;
    }


    /**
     * Returns the requestFlag
     * 
     * @return int requestFlag.
     */
    public int getRequestFlag() {
        return requestFlag;
    }


    /**
     * Sets the requestFlag
     * 
     * @param requestFlag.
     */
    public void setRequestFlag(int requestFlag) {
        this.requestFlag = requestFlag;
    }


    /**
     * @return Returns the benefitIdentifier.
     */
    public String getBenefitIdentifier() {
        return benefitIdentifier;
    }


    /**
     * @param benefitIdentifier
     *            The benefitIdentifier to set.
     */
    public void setBenefitIdentifier(String benefitIdentifier) {
        this.benefitIdentifier = benefitIdentifier;
    }


    /**
     * @return Returns the dataTypeList.
     */
    public List getDataTypeList() {
        return dataTypeList;
    }


    /**
     * @param dataTypeList
     *            The dataTypeList to set.
     */
    public void setDataTypeList(List dataTypeList) {
        this.dataTypeList = dataTypeList;
    }


    /**
     * @return Returns the pVAList.
     */
    public List getPVAList() {
        return PVAList;
    }


    /**
     * @param list
     *            The pVAList to set.
     */
    public void setPVAList(List list) {
        PVAList = list;
    }


    /**
     * @return Returns the qualifierList.
     */
    public List getQualifierList() {
        return qualifierList;
    }


    /**
     * @param qualifierList
     *            The qualifierList to set.
     */
    public void setQualifierList(List qualifierList) {
        this.qualifierList = qualifierList;
    }


    /**
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }


    /**
     * @param standardBenefitKey
     *            The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }


    /**
     * @return Returns the termList.
     */
    public List getTermList() {
        return termList;
    }


    /**
     * @param termList
     *            The termList to set.
     */
    public void setTermList(List termList) {
        this.termList = termList;
    }


    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }


    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return Returns the benefitComponentParentId.
     */
    public int getBenefitComponentParentId() {
        return benefitComponentParentId;
    }


    /**
     * @param benefitComponentParentId
     *            The benefitComponentParentId to set.
     */
    public void setBenefitComponentParentId(int benefitComponentParentId) {
        this.benefitComponentParentId = benefitComponentParentId;
    }


    /**
     * @return Returns the businessDomainList.
     */
    public List getBusinessDomainList() {
        return businessDomainList;
    }


    /**
     * @param businessDomainList
     *            The businessDomainList to set.
     */
    public void setBusinessDomainList(List businessDomainList) {
        this.businessDomainList = businessDomainList;
    }


    /**
     * Returns the checkInOpted
     * 
     * @return boolean checkInOpted.
     */
    public boolean isCheckInOpted() {
        return CheckInOpted;
    }


    /**
     * Sets the checkInOpted
     * 
     * @param checkInOpted.
     */
    public void setCheckInOpted(boolean checkInOpted) {
        CheckInOpted = checkInOpted;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitComponentId = ").append(this.benefitComponentId);
        buffer.append(", benefitComponentParentId = ").append(
                this.benefitComponentParentId);
        buffer.append(", benefitComponentName = ").append(
                this.benefitComponentName);
        buffer.append(", description = ").append(this.description);
        buffer.append(", requestFlag = ").append(this.requestFlag);
        buffer.append(", effectiveDate = ").append(this.effectivedate);
        buffer.append(", expiryDate = ").append(this.expirydate);
        buffer.append(", businessDomainList = ")
                .append(this.businessDomainList);
        buffer.append("refereneCode = ").append(this.refereneCode);
        buffer.append(", standardBenefitKey = ")
                .append(this.standardBenefitKey);
        buffer.append(", benefitIdentifier = ").append(this.benefitIdentifier);
        buffer.append(", CheckInOpted = ").append(this.CheckInOpted);
        buffer.append(", termList = ").append(this.termList);
        buffer.append(", qualifierList = ").append(this.qualifierList);
        buffer.append(", PVAList = ").append(this.PVAList);
        buffer.append(", dataTypeList = ").append(this.dataTypeList);
        buffer.append(", state = ").append(this.state);
        buffer.append(", status = ").append(this.status);
        buffer.append(", version = ").append(this.version);
        return buffer.toString();
    }


    /**
     * @return Returns the selectednotesIdList.
     */
    public List getSelectednotesIdList() {
        return selectednotesIdList;
    }


    /**
     * @param selectednotesIdList
     *            The selectednotesIdList to set.
     */
    public void setSelectednotesIdList(List selectednotesIdList) {
        this.selectednotesIdList = selectednotesIdList;
    }


    /**
     * @return Returns the componentType.
     */
    public String getComponentType() {
        return componentType;
    }


    /**
     * @param componentType
     *            The componentType to set.
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
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
     * @return Returns the stateId.
     */
    public String getStateId() {
        return stateId;
    }


    /**
     * @param stateId
     *            The stateId to set.
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }


    /**
     * @return Returns the versionList.
     */
    public List getVersionList() {
        return versionList;
    }


    /**
     * @param versionList
     *            The versionList to set.
     */
    public void setVersionList(List versionList) {
        this.versionList = versionList;
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
	 * @return Returns the benefitComponentFlagList.
	 */
	public List getBenefitComponentFlagList() {
		return benefitComponentFlagList;
	}
	/**
	 * @param benefitComponentFlagList The benefitComponentFlagList to set.
	 */
	public void setBenefitComponentFlagList(List benefitComponentFlagList) {
		this.benefitComponentFlagList = benefitComponentFlagList;
	}
	/**
	 * @return Returns the marketBusinessUnitCodeList.
	 */
	public List getMarketBusinessUnitCodeList() {
		return marketBusinessUnitCodeList;
	}
	/**
	 * @param marketBusinessUnitCodeList The marketBusinessUnitCodeList to set.
	 */
	public void setMarketBusinessUnitCodeList(List marketBusinessUnitCodeList) {
		this.marketBusinessUnitCodeList = marketBusinessUnitCodeList;
	}
}