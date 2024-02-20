/*
 * CustomMessageTextBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * CustomMessageTextBO is the class for creating business object which can be
 * used for data trasfer from one layer to another layer.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageTextBO extends BusinessObject {

	private String spsParameterId;

	private String spsParameterDesc;

	private String headerRuleId;

	private String headerRuleDesc;

	private String messagetext;

	private String messageReqIndicator;

	private List spsParameterList;

	private List headerRuleList;
	
	private String noteTypeCode;
	
	private String noteTypeDesc;
	
	// SSCR19537 April04 - changes for BX data feed
	private String eb03;


	/**
	 * @return Returns the headerRuleDesc.
	 */
	public String getHeaderRuleDesc() {
		return headerRuleDesc;
	}

	/**
	 * @param headerRuleDesc
	 *            The headerRuleDesc to set.
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
	 * @param headerRuleId
	 *            The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

	/**
	 * @return Returns the messagetext.
	 */
	public String getMessagetext() {
		return messagetext;
	}

	/**
	 * @param messagetext
	 *            The messagetext to set.
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
	 * @param spsParameterDesc
	 *            The spsParameterDesc to set.
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
	 * @param spsParameterId
	 *            The spsParameterId to set.
	 */
	public void setSpsParameterId(String spsParameterId) {
		this.spsParameterId = spsParameterId;
	}

	/**
	 * Overriding equals method
	 * 
	 * @return boolean.
	 */
	public boolean equals(BusinessObject object) {
		return false;
	}

	/**
	 * Overriding hashCode() method
	 * 
	 * @return int.
	 */
	public int hashCode() {
		return 0;
	}

	/**
	 * Overriding toString() method
	 * 
	 * @return String.
	 */
	public String toString() {
		return null;
	}

	/**
	 * @return Returns the messageReqIndicator.
	 */
	public String getMessageReqIndicator() {
		return messageReqIndicator;
	}

	/**
	 * @param messageReqIndicator
	 *            The messageReqIndicator to set.
	 */
	public void setMessageReqIndicator(String messageReqIndicator) {
		this.messageReqIndicator = messageReqIndicator;
	}

	/**
	 * @return Returns the headerRuleList.
	 */
	public List getHeaderRuleList() {
		return headerRuleList;
	}

	/**
	 * @param headerRuleList
	 *            The headerRuleList to set.
	 */
	public void setHeaderRuleList(List headerRuleList) {
		this.headerRuleList = headerRuleList;
	}

	/**
	 * @return Returns the spsParameterList.
	 */
	public List getSpsParameterList() {
		return spsParameterList;
	}

	/**
	 * @param spsParameterList
	 *            The spsParameterList to set.
	 */
	public void setSpsParameterList(List spsParameterList) {
		this.spsParameterList = spsParameterList;
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

	public String getEb03() {
		return eb03;
	}

	public void setEb03(String eb03) {
		this.eb03 = eb03;
	}
}