/*
 * BenefitComponentBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

import java.util.Date;
import java.util.List;

/**
 * Business Object for benefit Component
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentBO extends BusinessObject {

    StandardBenefitBO standardBenefitBO;

    //Refers to BNFT_CMPNT_SYS_ID in BCM_BNFT_CMPNT_MSTR
    private int benefitComponentId;

    private int benefitComponentParentId;

    private String name;

    private String description;

    private int sequenceNum;

    private Date effectiveDate;

    private Date expiryDate;

    private List lineOfBusiness;

    private List businessEntity;

    private List businessGroup;

    private List marketBusinessUnit;
    
    private List businessDomainList;

    private String componentType;

    private String componentDesc;

    private String mandateType;

    private String mandateDesc;

    private List ruleList;
	/**
	 * @return Returns the ruleTypeList.
	 */
	public List getRuleTypeList() {
		return ruleTypeList;
	}
	/**
	 * @param ruleTypeList The ruleTypeList to set.
	 */
	public void setRuleTypeList(List ruleTypeList) 
	{	
		
		this.ruleTypeList = ruleTypeList;
	}
    private List ruleTypeList;

    private String ruleId;
    private String ruleType;

	
    private List ruleNameList;

    private String stateId;

    private String stateDesc;

    private String action;

    BenefitMandateBO benefitMandateBO;
    private DomainDetail domainDetail = null;
    
    private String lob;
    
    private String ruleDesc;
    
    
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
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
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
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }


    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Returns the sequenceNum.
     */
    public int getSequenceNum() {
        return sequenceNum;
    }


    /**
     * @param sequenceNum
     *            The sequenceNum to set.
     */
    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }


    
    
    /**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
    /**
     * @return Returns the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * @param effectiveDate
     *            The effectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * @return Returns the expiryDate.
     */
    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * @param expiryDate
     *            The expiryDate to set.
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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
     * @return Returns the businessEntity.
     */
    public List getBusinessEntity() {
        return businessEntity;
    }


    /**
     * @param businessEntity
     *            The businessEntity to set.
     */
    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * @return Returns the businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }


    /**
     * @param businessGroup
     *            The businessGroup to set.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * @return Returns the lineOfBusiness.
     */
    public List getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * @param lineOfBusiness
     *            The lineOfBusiness to set.
     */
    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param object
     * @return
     */
    public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return
     */
    public int hashCode() {
        return 0;
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
        buffer.append(", name = ").append(this.name);
        buffer.append(", description = ").append(this.description);
        buffer.append(", sequenceNum = ").append(this.sequenceNum);
        buffer.append(", effectiveDate = ").append(this.effectiveDate);
        buffer.append(", expiryDate = ").append(this.expiryDate);
        buffer.append(", businessDomainList = ")
                .append(this.businessDomainList);
        return buffer.toString();
    }


    /**
     * @return Returns the standardBenefitBO.
     */
    public StandardBenefitBO getStandardBenefitBO() {
        return standardBenefitBO;
    }


    /**
     * @param standardBenefitBO
     *            The standardBenefitBO to set.
     */
    public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
        this.standardBenefitBO = standardBenefitBO;
    }


    /**
     * Returns the benefitComponentParentId
     * 
     * @return int benefitComponentParentId.
     */

    public int getBenefitComponentParentId() {
        return benefitComponentParentId;
    }


    /**
     * Sets the benefitComponentParentId
     * 
     * @param benefitComponentParentId.
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
     * @return Returns the ruleList.
     */
    public List getRuleList() {
        return ruleList;
    }


    /**
     * @param ruleList
     *            The ruleList to set.
     */
    public void setRuleList(List ruleList) {
        this.ruleList = ruleList;
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
     * @return Returns the componentDesc.
     */
    public String getComponentDesc() {
        return componentDesc;
    }


    /**
     * @param componentDesc
     *            The componentDesc to set.
     */
    public void setComponentDesc(String componentDesc) {
        this.componentDesc = componentDesc;
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
     * @return Returns the benefitMandateBO.
     */
    public BenefitMandateBO getBenefitMandateBO() {
        return benefitMandateBO;
    }


    /**
     * @param benefitMandateBO
     *            The benefitMandateBO to set.
     */
    public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
        this.benefitMandateBO = benefitMandateBO;
    }
	/**
	 * @return Returns the ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}
	/**
	 * @param ruleDesc The ruleDesc to set.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}