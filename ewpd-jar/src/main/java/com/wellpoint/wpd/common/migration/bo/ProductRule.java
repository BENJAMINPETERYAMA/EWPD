/*
 * ProductRule.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class ProductRule implements Comparable{
	private int productSysId;
	private int productRuleSysId;
	private String generatedId;
	private String ruleId;
	private String value;
	
	public ProductRule(){
	}
	public ProductRule(ProductRule productRule) {
		this.productSysId = productRule.productSysId;
		this.productRuleSysId = productRule.productRuleSysId;
		this.generatedId = productRule.generatedId;
		this.ruleId = productRule.ruleId;
		this.value = productRule.value;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(20);
		buffer.append("productRuleSysId = " + productRuleSysId);
		buffer.append(", generatedId = "+ generatedId);
		buffer.append(", ruleId = "+ ruleId);
		buffer.append(", value = "+ value);
		return buffer.toString();
	}
	
	public boolean equals(Object obj) {
		ProductRule rule = (ProductRule)obj;
		if(this.generatedId.equals(rule.generatedId))
			return true;
		return false;
	}
	
	public int compareTo(Object o) {
		ProductRule rule = (ProductRule) o;
		if(this.generatedId.equalsIgnoreCase(rule.generatedId))
			return 0;
		else 
			return this.getGeneratedId().compareTo(rule.generatedId);
	}
	/**
	 * Returns the productSysId.
	 * @return int productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * Sets the productSysId.
	 * @param productSysId.
	 */
	
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}

	/**
	 * Returns the ruleId.
	 * @return String ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * Sets the ruleId.
	 * @param ruleId.
	 */
	
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * Returns the generatedId.
	 * @return String generatedId.
	 */
	public String getGeneratedId() {
		return generatedId;
	}
	/**
	 * Sets the generatedId.
	 * @param generatedId.
	 */
	
	public void setGeneratedId(String generatedId) {
		this.generatedId = generatedId;
	}
	/**
	 * Returns the value.
	 * @return String value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * Sets the value.
	 * @param value.
	 */
	
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * Returns the productRuleSysId.
	 * @return int productRuleSysId.
	 */
	public int getProductRuleSysId() {
		return productRuleSysId;
	}
	/**
	 * Sets the productRuleSysId.
	 * @param productRuleSysId.
	 */
	
	public void setProductRuleSysId(int productRuleSysId) {
		this.productRuleSysId = productRuleSysId;
	}

}
