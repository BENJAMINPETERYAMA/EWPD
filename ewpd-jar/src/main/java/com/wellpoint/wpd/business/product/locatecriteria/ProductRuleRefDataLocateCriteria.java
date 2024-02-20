/*
 * ProductRuleRefDataLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductRuleRefDataLocateCriteria extends LocateCriteria {
	
	private String queryName;
	private String ruleType;
	private int key;
//--------------------------------- getters/setters -----------------------	
	/**
	 * Returns the queryName
	 * @return String queryName.
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * Sets the queryName
	 * @param queryName.
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
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
	 * Returns the key
	 * @return int key.
	 */
	public int getKey() {
		return key;
	}
	/**
	 * Sets the key
	 * @param key.
	 */
	public void setKey(int key) {
		this.key = key;
	}
}
