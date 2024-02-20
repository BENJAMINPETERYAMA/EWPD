/*
 * <Rule.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.entity;
/**
 * @author UST-GLOBAL
 * This is an entity class to represent a Rule i.e a id and description
 */
public class Rule {
	
	private String headerRuleId;
	
	private String ruleDesc;	
	
	private String notApplicable;

	/**
	 * 
	 * @return
	 */
	public String getNotApplicable() {
		return notApplicable;
	}

	/**
	 * 
	 * @param notApplicable
	 */
	public void setNotApplicable(String notApplicable) {
		this.notApplicable = notApplicable;
	}


	/**
	 * @return
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}

	/**
	 * @param headerRuleId
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

	/**
	 * @return
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}

	/**
	 * @param ruleDesc
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	
	
}
