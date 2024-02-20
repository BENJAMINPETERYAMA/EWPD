/*
 * RuleResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleResponse extends WPDResponse{
	
	private List ruleList = null;

	/**
	 * Returns the ruleList
	 * @return List ruleList.
	 */
	public List getRuleList() {
		return ruleList;
	}
	/**
	 * Sets the ruleList
	 * @param ruleList.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
}
