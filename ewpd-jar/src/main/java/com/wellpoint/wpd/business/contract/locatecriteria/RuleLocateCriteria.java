/*
 * RuleLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleLocateCriteria extends LocateCriteria {

	private int productSysId;
	private int dateSegmentId;
	private int productRuleSysId;
	private String ruleType;
	private String ruleId; 
	
	//ICD10 Enhancements -- Extract Rule Criteria
	private String extractAllChecked;
	private String extractExclusionChecked;
	private String extractDenialChecked;
	private String extractUMChecked;
	private String extractHeaderChecked;
	private String ruleTypeDescription;
	private String headerRuleAssociated;
	private String benefitComponentName;
	private String benefitComponentId;
	private String benefitName;
	
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
     * @return ruleType
     * 
     * Returns the ruleType.
     */
    public String getRuleType() {
        return ruleType;
    }
    /**
     * @param ruleType
     * 
     * Sets the ruleType.
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
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
	public String getExtractAllChecked() {
		return extractAllChecked;
	}
	public void setExtractAllChecked(String extractAllChecked) {
		this.extractAllChecked = extractAllChecked;
	}
	public String getExtractExclusionChecked() {
		return extractExclusionChecked;
	}
	public void setExtractExclusionChecked(String extractExclusionChecked) {
		this.extractExclusionChecked = extractExclusionChecked;
	}
	public String getExtractDenialChecked() {
		return extractDenialChecked;
	}
	public void setExtractDenialChecked(String extractDenialChecked) {
		this.extractDenialChecked = extractDenialChecked;
	}
	public String getExtractUMChecked() {
		return extractUMChecked;
	}
	public void setExtractUMChecked(String extractUMChecked) {
		this.extractUMChecked = extractUMChecked;
	}
	public String getExtractHeaderChecked() {
		return extractHeaderChecked;
	}
	public void setExtractHeaderChecked(String extractHeaderChecked) {
		this.extractHeaderChecked = extractHeaderChecked;
	}
	public void setRuleTypeDescription(String ruleTypeDescription) {
		this.ruleTypeDescription = ruleTypeDescription;
	}
	public String getRuleTypeDescription() {
		return ruleTypeDescription;
	}
	public void setHeaderRuleAssociated(String headerRuleAssociated) {
		this.headerRuleAssociated = headerRuleAssociated;
	}
	public String getHeaderRuleAssociated() {
		return headerRuleAssociated;
	}
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
}
