/*
 * ProductRuleRefDataRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRuleRefDataRequest extends ProductRequest {
	public static final int RULE_TYPE = 1;
	public static final int RULE_ID = 2;
    public static final int PRODUCT_RULES_RETRIEVE = 3;
	
	private int action;
	private String ruleType;
	
	private int pageno;

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
	 * Returns the ruleType
	 * @return String ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * Sets the ruleType
	 * @param ruleType.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * @return Returns the pageno.
	 */
	public int getPageno() {
		return pageno;
	}
	/**
	 * @param pageno The pageno to set.
	 */
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
}
