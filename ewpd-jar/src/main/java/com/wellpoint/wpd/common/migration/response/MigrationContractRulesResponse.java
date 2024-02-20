/*
 * MigrationContractRulesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationContractRulesResponse extends MigrationContractResponse {

     private java.util.List ruleList;

//	--------------------------------- getters/setters -----------------------	
	/**
	 * Returns the ruleList
	 * @return java.util.List ruleList.
	 */
	public java.util.List getRuleList() {
		return ruleList;
	}
	/**
	 * Sets the ruleList
	 * @param ruleList.
	 */
	public void setRuleList(java.util.List ruleList) {
		this.ruleList = ruleList;
	}
}
