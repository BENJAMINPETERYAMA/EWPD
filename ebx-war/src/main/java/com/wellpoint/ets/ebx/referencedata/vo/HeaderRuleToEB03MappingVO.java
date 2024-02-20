package com.wellpoint.ets.ebx.referencedata.vo;


/**
 * VO Object which holds the EB03 Header Rule Association attributes.
 * 
 * 
 */
public class HeaderRuleToEB03MappingVO {
	
	private String eb03Value;
	private String eb03Description;
	private String headerRuleValue;
	private String headerRuleDescription;
	private String commaSeperatedHeaderRules;
	private String userComments;
	private String systemComments;
	private String operationMessages;
	private String lastUpdatedUser;
	private String updatedDateAndTime;
	
	
	
	public String getEb03Value() {
		return eb03Value;
	}
	public void setEb03Value(String eb03Value) {
		this.eb03Value = eb03Value;
	}
	public String getEb03Description() {
		return eb03Description;
	}
	public void setEb03Description(String eb03Description) {
		this.eb03Description = eb03Description;
	}
	public String getHeaderRuleValue() {
		return headerRuleValue;
	}
	public void setHeaderRuleValue(String headerRuleValue) {
		this.headerRuleValue = headerRuleValue;
	}
	public String getHeaderRuleDescription() {
		return headerRuleDescription;
	}
	public void setHeaderRuleDescription(String headerRuleDescription) {
		this.headerRuleDescription = headerRuleDescription;
	}
	public String getCommaSeperatedHeaderRules() {
		return commaSeperatedHeaderRules;
	}
	public void setCommaSeperatedHeaderRules(String commaSeperatedHeaderRules) {
		this.commaSeperatedHeaderRules = commaSeperatedHeaderRules;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	public String getUserComments() {
		return userComments;
	}
	public void setOperationMessages(String operationMessages) {
		this.operationMessages = operationMessages;
	}
	public String getOperationMessages() {
		return operationMessages;
	}
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	public String getSystemComments() {
		return systemComments;
	}
	public void setSystemComments(String systemComments) {
		this.systemComments = systemComments;
	}
	public String getUpdatedDateAndTime() {
		return updatedDateAndTime;
	}
	public void setUpdatedDateAndTime(String updatedDateAndTime) {
		this.updatedDateAndTime = updatedDateAndTime;
	}

	
}
