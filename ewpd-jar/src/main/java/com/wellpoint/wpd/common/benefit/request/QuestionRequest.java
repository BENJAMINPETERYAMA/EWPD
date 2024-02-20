/*
 * QuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionRequest extends WPDRequest {
	
	private String question;

	private String dataType;

	List answerList;
	
	/**
	 * @return Returns the answerList.
	 */
	public List getAnswerList() {
		return answerList;
	}
	/**
	 * @param answerList The answerList to set.
	 */
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return Returns the question.
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question The question to set.
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
