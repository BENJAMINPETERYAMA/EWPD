/*
 * Created on Oct 14, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import com.wellpoint.wpd.business.util.BusinessConstants;


/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleDetailBO {
	
	private String contractId;
	private int dateSegmentId;
	private String dateSegment;
	private int productId;
	private String productName;
	private int benefitComponentId;
 	private String benefitComponentName;
	private int benefitId;
	private String benefitName;
	private String ruleId;
	private String ruleDesc;
	private String ruleType;
	private String ruleTypeName;
	
	private int seqNumber;
	
	
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
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
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
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
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
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
	 * @return Returns the dateSegment.
	 */
	public String getDateSegment() {
		return dateSegment;
	}
	/**
	 * @param dateSegment The dateSegment to set.
	 */
	public void setDateSegment(String dateSegment) {
		this.dateSegment = dateSegment;
	}
	/**
	 * The method returns whether the rule is a Header or Blaze rule.
	 * This is for display purposes in the rule popup.
	 * @param ruleType
	 * @return
	 */
	public String getRuleTypeName() {
		//if rule type is "WPDAUTOBG",then return "HEADER"
		if(BusinessConstants.RULE_TYPE_CD_WPDAUTOBG.equals(this.ruleType)){
			this.ruleType = BusinessConstants.RULE_TYPE_HEADER;
		}else if(BusinessConstants.RULE_TYPE_CD_BLZWPDAB.equals(this.ruleType)){
         //if rule type is "BLZWPDAB",then return "BLAZE"
			this.ruleType = BusinessConstants.RULE_TYPE_BLAZE;
		}
		return ruleType;
	}
	/**
	 * @param ruleTypeName The ruleTypeName to set.
	 */
	public void setRuleTypeName(String ruleTypeName) {
		this.ruleTypeName = ruleTypeName;
	}
	/**
	 * @return Returns the seqNumber.
	 */
	public int getSeqNumber() {
		return seqNumber;
	}
	/**
	 * @param seqNumber The seqNumber to set.
	 */
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}
}
