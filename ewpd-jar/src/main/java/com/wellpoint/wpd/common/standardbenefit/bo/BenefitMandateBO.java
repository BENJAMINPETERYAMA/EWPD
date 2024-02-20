/*
 * Created on Feb 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;
import java.util.List;

/**
 * @author u13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitMandateBO{
	private Date effectiveDate;
    private Date expiryDate;
    private String optionalIdentifier;
    private String mandate;
    
    private int benefitMandateMasterKey;
    private int mandateMasterId;   
    private int benefitMandateId;
    private int benefitSystemId;
    private	String benefitIdName;
    
    private String createdUser;
	private String lastUpdatedUser;
	private Date createdTimestamp;
	private Date lastUpdatedTimestamp;

	//new fields
	private List citationNumberList = null;
    private String fundingArrangement;
    private String fundingArrangementDesc;
    private String effectiveness;
    private String text;
    private String mandateCategory;
    private String mandateEffectivenessDesc;
    private String mandateCategoryDesc;
	private List stateDescList;
	 private String mandateType;
    private List benefitStateCodeList;
    private List flagList;
    
    private String stateId;
    
    private List stateList;
	
	
	public BenefitMandateBO() {
	}
    /**
     * Returns the effectiveDate
     * @return String effectiveDate.
     */

    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * Sets the effectiveDate
     * @param effectiveDate.
     */

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * Returns the expiryDate
     * @return String expiryDate.
     */

    public Date getExpiryDate() {
        return expiryDate;
    }
    /**
     * Sets the expiryDate
     * @param expiryDate.
     */

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * Returns the mandate
     * @return String mandate.
     */

    public String getMandate() {
        return mandate;
    }
    /**
     * Sets the mandate
     * @param mandate.
     */

    public void setMandate(String mandate) {
        this.mandate = mandate;
    }
    /**
     * Returns the optionalIdentifier
     * @return String optionalIdentifier.
     */

    public String getOptionalIdentifier() {
        return optionalIdentifier;
    }
    /**
     * Sets the optionalIdentifier
     * @param optionalIdentifier.
     */

    public void setOptionalIdentifier(String optionalIdentifier) {
        this.optionalIdentifier = optionalIdentifier;
    }
	/**
	 * @return Returns the benefitIdNm.
	 */
	public String getBenefitIdName() {
		return benefitIdName;
	}
	/**
	 * @param benefitIdNm The benefitIdNm to set.
	 */
	public void setBenefitIdName(String benefitIdName) {
		this.benefitIdName = benefitIdName;
	}
	/**
	 * @return Returns the benefitSystemId.
	 */
	public int getBenefitSystemId() {
		return benefitSystemId;
	}
	/**
	 * @param benefitSystemId The benefitSystemId to set.
	 */
	public void setBenefitSystemId(int benefitSystemId) {
		this.benefitSystemId = benefitSystemId;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * @param benefitMandateId The benefitMandateId to set.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}
	/**
	 * @return Returns the mandateMasterId.
	 */
	
	public int getMandateMasterId() {
		return mandateMasterId;
	}
	/**
	 * @param mandateMasterId The mandateMasterId to set.
	 */
	public void setMandateMasterId(int mandateMasterId) {
		this.mandateMasterId = mandateMasterId;
	}
	
	/**
	 * @return Returns the benefitMandateMasterKey.
	 */
	public int getBenefitMandateMasterKey() {
		return benefitMandateMasterKey;
	}
	/**
	 * @param benefitMandateMasterKey The benefitMandateMasterKey to set.
	 */
	public void setBenefitMandateMasterKey(int benefitMandateMasterKey) {
		this.benefitMandateMasterKey = benefitMandateMasterKey;
	}
	/**
	 * Returns the citationNumberList
	 * @return List citationNumberList.
	 */
	public List getCitationNumberList() {
		return citationNumberList;
	}
	/**
	 * Sets the citationNumberList
	 * @param citationNumberList.
	 */
	public void setCitationNumberList(List citationNumberList) {
		this.citationNumberList = citationNumberList;
	}
	/**
	 * Returns the fundingArrangement
	 * @return String fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}
	/**
	 * Sets the fundingArrangement
	 * @param fundingArrangement.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}
	/**
	 * Returns the effectiveness
	 * @return String effectiveness.
	 */
	public String getEffectiveness() {
		return effectiveness;
	}
	/**
	 * Sets the effectiveness
	 * @param effectiveness.
	 */
	public void setEffectiveness(String effectiveness) {
		this.effectiveness = effectiveness;
	}
	/**
	 * Returns the text
	 * @return String text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * Sets the text
	 * @param text.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Returns the mandateCategory
	 * @return String mandateCategory.
	 */
	public String getMandateCategory() {
		return mandateCategory;
	}
	/**
	 * Sets the mandateCategory
	 * @param mandateCategory.
	 */
	public void setMandateCategory(String mandateCategory) {
		this.mandateCategory = mandateCategory;
	}
	/**
	 * Returns the fundingArrangementDesc
	 * @return String fundingArrangementDesc.
	 */
	public String getFundingArrangementDesc() {
		return fundingArrangementDesc;
	}
	/**
	 * Sets the fundingArrangementDesc
	 * @param fundingArrangementDesc.
	 */
	public void setFundingArrangementDesc(String fundingArrangementDesc) {
		this.fundingArrangementDesc = fundingArrangementDesc;
	}
	/**
	 * @return Returns the mandateCategoryDesc.
	 */
	public String getMandateCategoryDesc() {
		return mandateCategoryDesc;
	}
	/**
	 * @param mandateCategoryDesc The mandateCategoryDesc to set.
	 */
	public void setMandateCategoryDesc(String mandateCategoryDesc) {
		this.mandateCategoryDesc = mandateCategoryDesc;
	}
	/**
	 * @return Returns the mandateEffectivenessDesc.
	 */
	public String getMandateEffectivenessDesc() {
		return mandateEffectivenessDesc;
	}
	/**
	 * @param mandateEffectivenessDesc The mandateEffectivenessDesc to set.
	 */
	public void setMandateEffectivenessDesc(String mandateEffectivenessDesc) {
		this.mandateEffectivenessDesc = mandateEffectivenessDesc;
	}
	/**
	 * @return Returns the stateDescList.
	 */
	public List getStateDescList() {
		return stateDescList;
	}
	/**
	 * @param stateDescList The stateDescList to set.
	 */
	public void setStateDescList(List stateDescList) {
		this.stateDescList = stateDescList;
	}
	/**
	 * Returns the benefitStateCodeList
	 * @return List benefitStateCodeList.
	 */
	public List getBenefitStateCodeList() {
		return benefitStateCodeList;
	}
	/**
	 * Sets the benefitStateCodeList
	 * @param benefitStateCodeList.
	 */
	public void setBenefitStateCodeList(List benefitStateCodeList) {
		this.benefitStateCodeList = benefitStateCodeList;
	}
	/**
	 * Returns the mandateType
	 * @return String mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * Sets the mandateType
	 * @param mandateType.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
	/**
	 * Returns the flagList
	 * @return List flagList.
	 */
	public List getFlagList() {
		return flagList;
	}
	/**
	 * Sets the flagList
	 * @param flagList.
	 */
	public void setFlagList(List flagList) {
		this.flagList = flagList;
	}
	/**
	 * @return Returns the stateId.
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId The stateId to set.
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return Returns the stateList.
	 */
	public List getStateList() {
		return stateList;
	}
	/**
	 * @param stateList The stateList to set.
	 */
	public void setStateList(List stateList) {
		this.stateList = stateList;
	}
}
