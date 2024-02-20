/*
 * SaveProductRulesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductRulesRequest extends ProductRequest {
	public static final int ADD_PRODUCT_RULES = 1;
	public static final int UPDATE_PRODUCT_RULES = 2;
	
	private int action;
	private List rulesList;
	private List pvaList;
	private List rulesIdList;
	private String ruleType;

	
	

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
	 * @return List rulesList.
	 */
	public List getRulesList() {
		return rulesList;
	}
	/**
	 * Sets the rulesList
	 * @param rulesList.
	 */
	public void setRulesList(List rulesList) {
		this.rulesList = rulesList;
	}
	/**
	 * @return Returns the pvaList.
	 */
	public List getPvaList() {
		return pvaList;
	}
	/**
	 * @param pvaList The pvaList to set.
	 */
	public void setPvaList(List pvaList) {
		this.pvaList = pvaList;
	}
	/**
	 * @return Returns the rulesIdList.
	 */
	public List getRulesIdList() {
		return rulesIdList;
	}
	/**
	 * @param rulesIdList The rulesIdList to set.
	 */
	public void setRulesIdList(List rulesIdList) {
		this.rulesIdList = rulesIdList;
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
}
