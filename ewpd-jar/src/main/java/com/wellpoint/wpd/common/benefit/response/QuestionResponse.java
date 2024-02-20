/*
 * QuestionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefit.response;

import com.wellpoint.wpd.common.benefit.bo.Question;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionResponse extends WPDResponse{
	Question question;
	
	/**
	 * @return Returns the question.
	 */
	public Question getQuestion() {
		return question;
	}
	/**
	 * @param question The question to set.
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
}
