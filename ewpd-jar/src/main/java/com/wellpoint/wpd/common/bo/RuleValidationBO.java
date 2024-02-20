/*
 * RuleBO.java
 * 
 * Created on 8/5/2009
 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 * 
 */
package com.wellpoint.wpd.common.bo;

/**
 * 
 * Business Object for Rule
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class RuleValidationBO {
	
	private String ruleId;
	private String ruleType; //WPDAUTOBG or BLZWPDAB
	private String ruleDescription;
	

	/**
	 * @return Returns the ruleDescription.
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}
	/**
	 * @param ruleDescription The ruleDescription to set.
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
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
}
