/*
 * ProductRuleBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRuleBO {
	
	private String flag;
	private String ruleCode;
	private String ruleDescription;
	private String providerArrangement;
	private String groupIndicator;
	private int productRuleSysID;
	private String ruleParamType;
	private String ruleParamId;
	private String ruleParamCode;
	
	
//	--------------------------------- getters/setters -----------------------	
	
	
	/**
	 * Returns the flag
	 * @return String flag.
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * Sets the flag
	 * @param flag.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * Returns the ruleCode
	 * @return String ruleCode.
	 */
	public String getRuleCode() {
		return ruleCode;
	}
	/**
	 * Sets the ruleCode
	 * @param ruleCode.
	 */
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	/**
	 * Returns the ruleDescription
	 * @return String ruleDescription.
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}
	/**
	 * Sets the ruleDescription
	 * @param ruleDescription.
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	/**
	 * Returns the groupIndicator
	 * @return String groupIndicator.
	 */
	public String getGroupIndicator() {
		return groupIndicator;
	}
	/**
	 * Sets the groupIndicator
	 * @param groupIndicator.
	 */
	public void setGroupIndicator(String groupIndicator) {
		this.groupIndicator = groupIndicator;
	}
	/**
	 * Returns the providerArrangement
	 * @return String providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * Sets the providerArrangement
	 * @param providerArrangement.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * Returns the productRuleSysID
	 * @return int productRuleSysID.
	 */
	public int getProductRuleSysID() {
		return productRuleSysID;
	}
	/**
	 * Sets the productRuleSysID
	 * @param productRuleSysID.
	 */
	public void setProductRuleSysID(int productRuleSysID) {
		this.productRuleSysID = productRuleSysID;
	}
	/**
	 * 
	 * Rule parameter Code
	 * <i>P</i>
	 * @return Returns the ruleParamCode.
	 */
	public String getRuleParamCode() {
		return ruleParamCode;
	}
	/**
	 * @param ruleParamCode The ruleParamCode to set.
	 */
	public void setRuleParamCode(String ruleParamCode) {
		this.ruleParamCode = ruleParamCode;
	}
	/**
	 * Rule parameter Id
	 * <i>PAR</i>
	 * @return Returns the ruleParamId.
	 */
	public String getRuleParamId() {
		return ruleParamId;
	}
	/**
	 * @param ruleParamId The ruleParamId to set.
	 */
	public void setRuleParamId(String ruleParamId) {
		this.ruleParamId = ruleParamId;
	}
	/**
	 * Rule parameter Type
	 * <i>PVA</i>
	 * @return Returns the ruleParamType.
	 */
	public String getRuleParamType() {
		return ruleParamType;
	}
	/**
	 * @param ruleParamType The ruleParamType to set.
	 */
	public void setRuleParamType(String ruleParamType) {
		this.ruleParamType = ruleParamType;
	}
}
