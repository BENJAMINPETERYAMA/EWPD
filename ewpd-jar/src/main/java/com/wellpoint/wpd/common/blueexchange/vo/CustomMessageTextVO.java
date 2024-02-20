/*
 * CustomMessageTextVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.vo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageTextVO {
	
	private String spsParameterId;
	
	private String spsParameterDesc;
	
	private String headerRuleId;
	
	private String headerRuleDesc;
	
	private String messagetext;
	
	private String messageReqIndicator;
	
	private String createdUser;
	
	private Date createdTimestamp;
	
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimestamp;
	
	private List spsParameterList;
	
	private List headerRuleList;
	
	private String noteTypeCode; 
	
	private String noteTypeDesc;

	/**
	 * @return Returns the headerRuleDesc.
	 */
	public String getHeaderRuleDesc() {
		return headerRuleDesc;
	}
	/**
	 * @param headerRuleDesc The headerRuleDesc to set.
	 */
	public void setHeaderRuleDesc(String headerRuleDesc) {
		this.headerRuleDesc = headerRuleDesc;
	}
	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	
	/**
	 * @return Returns the messageReqIndicator.
	 */
	public String getMessageReqIndicator() {
		return messageReqIndicator;
	}
	/**
	 * @param messageReqIndicator The messageReqIndicator to set.
	 */
	public void setMessageReqIndicator(String messageReqIndicator) {
		this.messageReqIndicator = messageReqIndicator;
	}
	/**
	 * @return Returns the messagetext.
	 */
	public String getMessagetext() {
		return messagetext;
	}
	/**
	 * @param messagetext The messagetext to set.
	 */
	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}
	/**
	 * @return Returns the spsParameterDesc.
	 */
	public String getSpsParameterDesc() {
		return spsParameterDesc;
	}
	/**
	 * @param spsParameterDesc The spsParameterDesc to set.
	 */
	public void setSpsParameterDesc(String spsParameterDesc) {
		this.spsParameterDesc = spsParameterDesc;
	}
	/**
	 * @return Returns the spsParameterId.
	 */
	public String getSpsParameterId() {
		return spsParameterId;
	}
	/**
	 * @param spsParameterId The spsParameterId to set.
	 */
	public void setSpsParameterId(String spsParameterId) {
		this.spsParameterId = spsParameterId;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the spsParameterList.
	 */
	public List getSpsParameterList() {
		return spsParameterList;
	}
	/**
	 * @param spsParameterList The spsParameterList to set.
	 */
	public void setSpsParameterList(List spsParameterList) {
		this.spsParameterList = spsParameterList;
	}
	/**
	 * @return Returns the headerRuleList.
	 */
	public List getHeaderRuleList() {
		return headerRuleList;
	}
	/**
	 * @param headerRuleList The headerRuleList to set.
	 */
	public void setHeaderRuleList(List headerRuleList) {
		this.headerRuleList = headerRuleList;
	}
	/**
	 * @return Returns the noteTypeCode.
	 */
	public String getNoteTypeCode() {
		return noteTypeCode;
	}
	/**
	 * @param noteTypeCode The noteTypeCode to set.
	 */
	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}
	/**
	 * @return Returns the noteTypeDesc.
	 */
	public String getNoteTypeDesc() {
		return noteTypeDesc;
	}
	/**
	 * @param noteTypeDesc The noteTypeDesc to set.
	 */
	public void setNoteTypeDesc(String noteTypeDesc) {
		this.noteTypeDesc = noteTypeDesc;
	}
}
