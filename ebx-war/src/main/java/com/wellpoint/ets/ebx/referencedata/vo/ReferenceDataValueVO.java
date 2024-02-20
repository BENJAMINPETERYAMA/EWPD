/*
 * <ReferenceDataValueVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.vo;


public class ReferenceDataValueVO {

	private String catalogName;
	
	private String primaryCode;
	
	private String secondaryCode;

	private String description;

	private String additionalComments;

	private String userID;
	
	private String dateAndTime;
	
	private String auditStatus;
	
	private int numberOfSPS;
	
	private int numberOfVariables;
	
	private int numberOfHeaderRules;
	
	private int numberOfMessages;
	
	private int catalogMinLength;
	
	private int catalogMaxLength;	
		
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public int getCatalogMinLength() {
		return catalogMinLength;
	}

	public void setCatalogMinLength(int catalogMinLength) {
		this.catalogMinLength = catalogMinLength;
	}

	public int getCatalogMaxLength() {
		return catalogMaxLength;
	}

	public void setCatalogMaxLength(int catalogMaxLength) {
		this.catalogMaxLength = catalogMaxLength;
	}

	public int getNumberOfSPS() {
		return numberOfSPS;
	}

	public void setNumberOfSPS(int numberOfSPS) {
		this.numberOfSPS = numberOfSPS;
	}

	public int getNumberOfVariables() {
		return numberOfVariables;
	}

	public void setNumberOfVariables(int numberOfVariables) {
		this.numberOfVariables = numberOfVariables;
	}

	public int getNumberOfHeaderRules() {
		return numberOfHeaderRules;
	}

	public void setNumberOfHeaderRules(int numberOfHeaderRules) {
		this.numberOfHeaderRules = numberOfHeaderRules;
	}

	public int getNumberOfMessages() {
		return numberOfMessages;
	}

	public void setNumberOfMessages(int numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}
	
	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getPrimaryCode() {
		return primaryCode;
	}

	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}

	public String getSecondaryCode() {
		return secondaryCode;
	}

	public void setSecondaryCode(String secondaryCode) {
		this.secondaryCode = secondaryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
}
