/*
 * Rule.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class Rule {
	private String ruleId;
	private String ruleType;
	private String groupInd;
	private String deleteFlag;
	private List sequences;
	// For group rule
	private List childRules;
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
	 * Returns the ruleType.
	 * @return String ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * Sets the ruleType.
	 * @param ruleType.
	 */
	
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * Returns the groupInd.
	 * @return String groupInd.
	 */
	public String getGroupInd() {
		return groupInd;
	}
	/**
	 * Sets the groupInd.
	 * @param groupInd.
	 */
	
	public void setGroupInd(String groupInd) {
		this.groupInd = groupInd;
	}
	/**
	 * Returns the deleteFlag.
	 * @return String deleteFlag.
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * Sets the deleteFlag.
	 * @param deleteFlag.
	 */
	
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * Returns the sequences.
	 * @return List sequences.
	 */
	public List getSequences() {
		return sequences;
	}
	/**
	 * Sets the sequences.
	 * @param sequences.
	 */
	
	public void setSequences(List sequences) {
		this.sequences = sequences;
	}
	/**
	 * Returns the childRules.
	 * @return List childRules.
	 */
	public List getChildRules() {
		return childRules;
	}
	/**
	 * Sets the childRules.
	 * @param childRules.
	 */
	
	public void setChildRules(List childRules) {
		this.childRules = childRules;
	}

	
}
