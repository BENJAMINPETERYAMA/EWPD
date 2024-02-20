package com.wellpoint.ets.ebx.referencedata.vo;

public class StandardMessageVO {
	
	private String standardMessageId;
	
	private String standardMessage;
	
	private String userId;
	
	private String lastUpdatedUser;
	
	private String updatedDateAndTime;
	
	private String userComments;
	
	private String systemComments;
	
	private String operationMessages;
	
	/**
	 * @return the standardMessageId
	 */
	public String getStandardMessageId() {
		return standardMessageId;
	}

	/**
	 * @param standardMessageId the standardMessageId to set
	 */
	public void setStandardMessageId(String standardMessageId) {
		this.standardMessageId = standardMessageId;
	}

	public String getStandardMessage() {
		return standardMessage;
	}

	public void setStandardMessage(String standardMessage) {
		this.standardMessage = standardMessage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public String getUpdatedDateAndTime() {
		return updatedDateAndTime;
	}

	public void setUpdatedDateAndTime(String updatedDateAndTime) {
		this.updatedDateAndTime = updatedDateAndTime;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getSystemComments() {
		return systemComments;
	}

	public void setSystemComments(String systemComments) {
		this.systemComments = systemComments;
	}

	public String getOperationMessages() {
		return operationMessages;
	}

	public void setOperationMessages(String operationMessages) {
		this.operationMessages = operationMessages;
	}
}
