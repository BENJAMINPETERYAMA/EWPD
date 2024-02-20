/*
 * MigrationContractRulesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.request;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationContractRulesRequest extends MigrationContractRequest {

	public static final int RETRIEVE_CONTRACT_RULES = 1;
	public static final int ADD_CONTRACT_RULES = 2;
	public static final int UPDATE_CONTRACT_RULES = 3;
	public static final int DELETE_CONTRACT_RULES = 4;
	public static final int RULE_TYPE = 5;
	public static final int RULE_ID = 6;
	public static final int RULES_UPDATE_STEP_COMPLETED = 7;
	private int action;
	private java.util.List rulesList;
	private java.lang.String ruleType;
	private int dateSegmentKey;
 	private String ewpdGenRuleID;
 	private boolean nextFlag;

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
	 * Returns the ruleType
	 * @return java.lang.String ruleType.
	 */
	public java.lang.String getRuleType() {
		return ruleType;
	}
	/**
	 * Sets the ruleType
	 * @param ruleType.
	 */
	public void setRuleType(java.lang.String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * Returns the dateSegmentKey
	 * @return int dateSegmentKey.
	 */
	public int getDateSegmentKey() {
		return dateSegmentKey;
	}
	/**
	 * Sets the dateSegmentKey
	 * @param dateSegmentKey.
	 */
	public void setDateSegmentKey(int dateSegmentKey) {
		this.dateSegmentKey = dateSegmentKey;
	}
	/**
	 * Returns the ewpdGenRuleID
	 * @return String ewpdGenRuleID.
	 */
	public String getEwpdGenRuleID() {
		return ewpdGenRuleID;
	}
	/**
	 * Sets the ewpdGenRuleID
	 * @param ewpdGenRuleID.
	 */
	public void setEwpdGenRuleID(String ewpdGenRuleID) {
		this.ewpdGenRuleID = ewpdGenRuleID;
	}
	/**
	 * @return Returns the nextFlag.
	 */
	public boolean isNextFlag() {
		return nextFlag;
	}
	/**
	 * @param nextFlag The nextFlag to set.
	 */
	public void setNextFlag(boolean nextFlag) {
		this.nextFlag = nextFlag;
	}
}
