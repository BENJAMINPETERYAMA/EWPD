/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Rule implements Serializable{
	
	private String ruleType;
	
	private String ewpdRuleId;
	
	private String ruleId;
	
	private String description;
	
	private String pva;
	
	private String groupIndicator;
	
	private String value;
	
	

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the ewpdRuleId.
	 */
	public String getEwpdRuleId() {
		return ewpdRuleId;
	}
	/**
	 * @param ewpdRuleId The ewpdRuleId to set.
	 */
	public void setEwpdRuleId(String ewpdRuleId) {
		this.ewpdRuleId = ewpdRuleId;
	}
	/**
	 * @return Returns the groupIndicator.
	 */
	public String getGroupIndicator() {
		return groupIndicator;
	}
	/**
	 * @param groupIndicator The groupIndicator to set.
	 */
	public void setGroupIndicator(String groupIndicator) {
		this.groupIndicator = groupIndicator;
	}
	/**
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
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
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
