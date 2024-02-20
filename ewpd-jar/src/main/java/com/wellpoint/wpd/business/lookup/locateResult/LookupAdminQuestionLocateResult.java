/*
 * LookupAdminQuestionLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.lookup.locateResult;

import com.wellpoint.wpd.common.bo.LocateResult;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdminQuestionLocateResult extends LocateResult {
	
	private int questionNumber;
	
	private int seqNumber;
	
	private String questionDescription;
	
	private String spsId;
	
	private String description;

	
	/**
	 * Returns the seqNumber
	 * @return int seqNumber.
	 */
	public int getSeqNumber() {
		return seqNumber;
	}
	/**
	 * Sets the seqNumber
	 * @param seqNumber.
	 */
	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
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
	 * Returns the questionNumber
	 * @return int questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * Sets the questionNumber
	 * @param questionNumber.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
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
