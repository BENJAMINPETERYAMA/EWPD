/*
 * ProductRuleAssociation.java
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
public class ProductRuleAssociation {
	private java.util.List rulesList; 
	private int productKey;
	private int action;
	
//	--------------------------------- getters/setters -----------------------	
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * Returns the rulesList
	 * @return java.util.List rulesList.
	 */
	public java.util.List getRulesList() {
		return rulesList;
	}
	/**
	 * Sets the rulesList
	 * @param rulesList.
	 */
	public void setRulesList(java.util.List rulesList) {
		this.rulesList = rulesList;
	}
	/**
	 * Returns the productKey
	 * @return int productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * Sets the productKey
	 * @param productKey.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
}
