/*
 * QuestionSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.QuestionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.QuestionBOImpl;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Bean to search Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionSearchBackingBean extends WPDBackingBean {

	private QuestionBO questionbo = null;

	private String criteriaQuestion = null;
	
	private String criteriaDataType = null;

	private List questionSearchResultList = null;

	private List validationMessages = null;

	private boolean requiredQuestion = false;
	
	/**
	 * Constructor
	 *
	 */
	public QuestionSearchBackingBean() {

	}

	/**
	 * @return Returns the criteriaQuestion.
	 */
	public String getCriteriaQuestion() {
		return criteriaQuestion;
	}

	/**
	 * @param criteriaQuestion
	 *            The criteriaQuestion to set.
	 */
	public void setCriteriaQuestion(String criteriaQuestion) {
		this.criteriaQuestion = criteriaQuestion;
	}

	/**
	 * @return Returns the questionSearchResultList.
	 */
	public List getQuestionSearchResultList() {
		if(this.questionSearchResultList != null && this.questionSearchResultList.size() == 0)
			return null;
		return questionSearchResultList;
	}

	/**
	 * @param questionSearchResultList
	 *            The questionSearchResultList to set.
	 */
	public void setQuestionSearchResultList(List questionSearchResultList) {
		this.questionSearchResultList = questionSearchResultList;
	}

	public String editQuestion() {
		return "";
	}

	public String deleteQuestion() {
		return "";
	}

	/**
	 * Method to search Question
	 * @return String
	 */
	public String search() {
		if (!validateField()) {

			addAllMessagesToRequest(validationMessages);

		}
		else{
			questionSearchResultList = new ArrayList(2);
			QuestionBO question1 = new QuestionBOImpl();
			QuestionBO question2 = new QuestionBOImpl();

			question1.setQuestion("Who am I?");
			question1.setDataType("Boolean");

			question2.setQuestion("Who are u?");
			question2.setDataType("Boolean");

			questionSearchResultList.add(question1);
			questionSearchResultList.add(question2);

		
		}
		return "";
	}

	/**
	 * @return Returns the questionbo.
	 */
	public QuestionBO getQuestionbo() {
		return questionbo;
	}

	/**
	 * @param questionbo
	 *            The questionbo to set.
	 */
	public void setQuestionbo(QuestionBO questionbo) {
		this.questionbo = questionbo;
	}

	/**
	 * @return Returns the requiredQuestion.
	 */
	public boolean isRequiredQuestion() {
		return requiredQuestion;
	}

	/**
	 * @param requiredQuestion
	 *            The requiredQuestion to set.
	 */
	public void setRequiredQuestion(boolean requiredQuestion) {
		this.requiredQuestion = requiredQuestion;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * Method to validate fields
	 * @return boolean
	 */
	private boolean validateField() {

		validationMessages = new ArrayList(1);

		boolean requiredField = false;

		if (this.getCriteriaQuestion() == null
				|| this.getCriteriaQuestion().trim().equals("")) {

			requiredQuestion = true;

			requiredField = true;

		}
		if (requiredField) {

			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));

			return false;

		}

		return true;

	}
	/**
	 * @return Returns the criteriaDataType.
	 */
	public String getCriteriaDataType() {
		return criteriaDataType;
	}
	/**
	 * @param criteriaDataType The criteriaDataType to set.
	 */
	public void setCriteriaDataType(String criteriaDataType) {
		this.criteriaDataType = criteriaDataType;
	}
}