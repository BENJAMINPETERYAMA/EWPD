/*
 * RuleLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locateresult;

import java.util.Date;

import com.wellpoint.wpd.common.bo.LocateResult;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleLocateResult extends LocateResult {

	
	
	private int productRuleSysId;
	private int productSysId;
	private String ruleId;
	private String rulePVA;
	private String generatedRuleId;
	private String deleteFlag;
	private String ruleOverRideValue;
	private String ruleShortDescription;
	private String ruleType;
	private String lastUpdatedUser;
	private String createdUser;
	private Date lastUpdatedTime;
	private Date createdTime;
	private String ruleTypeDescn;
	private int dateSegmentId;
	private int contractRuleSysId;
	private String ruleGroupIndicator;
	private String effectiveDate;
	private String expiryDate;
	/**
	 * @return Returns the deleteFlag.
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return Returns the generatedRuleId.
	 */
	public String getGeneratedRuleId() {
		return generatedRuleId;
	}
	/**
	 * @param generatedRuleId The generatedRuleId to set.
	 */
	public void setGeneratedRuleId(String generatedRuleId) {
		this.generatedRuleId = generatedRuleId;
	}
	/**
	 * @return Returns the productRuleSysId.
	 */
	public int getProductRuleSysId() {
		return productRuleSysId;
	}
	/**
	 * @param productRuleSysId The productRuleSysId to set.
	 */
	public void setProductRuleSysId(int productRuleSysId) {
		this.productRuleSysId = productRuleSysId;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the ruleOverRideValue.
	 */
	public String getRuleOverRideValue() {
		return ruleOverRideValue;
	}
	/**
	 * @param ruleOverRideValue The ruleOverRideValue to set.
	 */
	public void setRuleOverRideValue(String ruleOverRideValue) {
		this.ruleOverRideValue = ruleOverRideValue;
	}
	/**
	 * @return Returns the rulePVA.
	 */
	public String getRulePVA() {
		return rulePVA;
	}
	/**
	 * @param rulePVA The rulePVA to set.
	 */
	public void setRulePVA(String rulePVA) {
		this.rulePVA = rulePVA;
	}
	/**
	 * @return Returns the ruleShortDescription.
	 */
	public String getRuleShortDescription() {
		return ruleShortDescription;
	}
	/**
	 * @param ruleShortDescription The ruleShortDescription to set.
	 */
	public void setRuleShortDescription(String ruleShortDescription) {
		this.ruleShortDescription = ruleShortDescription;
	}
	/**
	 * @return Returns the createdTime.
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime The createdTime to set.
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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
	 * @return Returns the lastUpdatedTime.
	 */
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	/**
	 * @param lastUpdatedTime The lastUpdatedTime to set.
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
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
	 * @return Returns the ruleTypeDescn.
	 */
	public String getRuleTypeDescn() {
		return ruleTypeDescn;
	}
	/**
	 * @param ruleTypeDescn The ruleTypeDescn to set.
	 */
	public void setRuleTypeDescn(String ruleTypeDescn) {
		this.ruleTypeDescn = ruleTypeDescn;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the contractRuleSysId.
	 */
	public int getContractRuleSysId() {
		return contractRuleSysId;
	}
	/**
	 * @param contractRuleSysId The contractRuleSysId to set.
	 */
	public void setContractRuleSysId(int contractRuleSysId) {
		this.contractRuleSysId = contractRuleSysId;
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
     * @return ruleGroupIndicator
     * 
     * Returns the ruleGroupIndicator.
     */
    public String getRuleGroupIndicator() {
        return ruleGroupIndicator;
    }
    /**
     * @param ruleGroupIndicator
     * 
     * Sets the ruleGroupIndicator.
     */
    public void setRuleGroupIndicator(String ruleGroupIndicator) {
        this.ruleGroupIndicator = ruleGroupIndicator;
    }
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
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
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
}
