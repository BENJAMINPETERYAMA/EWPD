/*
 * <DataTypeToEB01MappingVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.vo;


/**
 * @author UST-GLOBAL DataTypeToEB01MappingVO
 * 
 */
public class DataTypeToEB01MappingVO {
	
	private String eb01value;
	
	private String eb01Description;
	
	private String dataTypeValue;
	
	private String dataTypeDescription;
	
	private String userComments;
	
	private String operationMessages;
	
	private String lastUpdatedUser;
	
	private String updatedDateAndTime;
	
	private String userId;
	
	private String systemComments;

	/**
	 * @return the eb01value
	 */
	public String getEb01value() {
		return eb01value;
	}

	/**
	 * @param eb01value the eb01value to set
	 */
	public void setEb01value(String eb01value) {
		this.eb01value = eb01value;
	}

	/**
	 * @return the eb01Description
	 */
	public String getEb01Description() {
		return eb01Description;
	}

	/**
	 * @param eb01Description the eb01Description to set
	 */
	public void setEb01Description(String eb01Description) {
		this.eb01Description = eb01Description;
	}

	/**
	 * @return the dataTypeValue
	 */
	public String getDataTypeValue() {
		return dataTypeValue;
	}

	/**
	 * @param dataTypeValue the dataTypeValue to set
	 */
	public void setDataTypeValue(String dataTypeValue) {
		this.dataTypeValue = dataTypeValue;
	}

	/**
	 * @return the dataTypeDescription
	 */
	public String getDataTypeDescription() {
		return dataTypeDescription;
	}

	/**
	 * @param dataTypeDescription the dataTypeDescription to set
	 */
	public void setDataTypeDescription(String dataTypeDescription) {
		this.dataTypeDescription = dataTypeDescription;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * @return the userComments
	 */
	public String getUserComments() {
		return userComments;
	}

	/**
	 * @param userComments the userComments to set
	 */
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public void setOperationMessages(String operationMessages) {
		this.operationMessages = operationMessages;
	}

	public String getOperationMessages() {
		return operationMessages;
	}

	/**
	 * @return the updatedDateAndTime
	 */
	public String getUpdatedDateAndTime() {
		return updatedDateAndTime;
	}

	/**
	 * @param updatedDateAndTime the updatedDateAndTime to set
	 */
	public void setUpdatedDateAndTime(String updatedDateAndTime) {
		this.updatedDateAndTime = updatedDateAndTime;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the systemComments
	 */
	public String getSystemComments() {
		return systemComments;
	}

	/**
	 * @param systemComments the systemComments to set
	 */
	public void setSystemComments(String systemComments) {
		this.systemComments = systemComments;
	}
	
}
