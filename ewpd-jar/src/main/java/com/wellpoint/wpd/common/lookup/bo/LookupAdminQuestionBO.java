/*
 * LookupAdminQuestionBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.lookup.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdminQuestionBO {
	
	private int questionNo;
	
	private int sequenceNo;
	
	private String questionDescription;
	
	private String spsId;
	
	private String description;

	/**
	 * Returns the sequenceNo
	 * @return int sequenceNo.
	 */
	public int getSequenceNo() {
		return sequenceNo;
	}
	/**
	 * Sets the sequenceNo
	 * @param sequenceNo.
	 */
	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	/**
	 * Returns the questionDescription
	 * @return String questionDescription.
	 */
	public String getQuestionDescription() {
		return questionDescription;
	}
	/**
	 * Sets the questionDescription
	 * @param questionDescription.
	 */
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	/**
	 * Returns the questionNo
	 * @return int questionNo.
	 */
	public int getQuestionNo() {
		return questionNo;
	}
	/**
	 * Sets the questionNo
	 * @param questionNo.
	 */
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getSpsId() {
		return spsId;
	}
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
