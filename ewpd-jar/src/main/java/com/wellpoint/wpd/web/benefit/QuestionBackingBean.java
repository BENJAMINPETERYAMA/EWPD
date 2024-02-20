/*
 * QuestionBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefit;

import com.wellpoint.wpd.common.benefit.request.QuestionRequest;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionBackingBean extends WPDBackingBean{


	private String question;

	private String dataType;

	private String defaultAnswer;

	private String answer;

	private String selectedAnswers;

	List answerList;

	private List validationMessages = null;

	private boolean requiredQuestion = false;

	private boolean requiredDataType = false;

	private boolean requiredAnswers = false;

	/**
	 * 
	 * Default Constructor 
	 */
	public QuestionBackingBean() {
	

		//answerList=new ArrayList();

	}

	/**
	 * 
	 * @return String
	 */
	public String create() {
		StringTokenizer st;
		st = new StringTokenizer(selectedAnswers, "~");
		answerList = new ArrayList();
		while (st.hasMoreTokens()) {
			answerList.add(st.nextToken());
		}

		Iterator itr = answerList.iterator();
		String ans = new String();
		while (itr.hasNext()) {
			ans = (String) itr.next();
		}
		if (!validateField()) {
			addAllMessagesToRequest(validationMessages);
		}
		// Place the content here start
		QuestionRequest questionRequest = getQuestionRequest();
		this.executeService(questionRequest);
		return "";
	}

	/**
	 * 
	 * @return QuestionRequest
	 */
   private QuestionRequest getQuestionRequest(){
	   	QuestionRequest questionRequest = (QuestionRequest) this.getServiceRequest(ServiceManager.CREATE_QUESTION);
		questionRequest.setQuestion(this.getQuestion());
		questionRequest.setDataType(this.getDataType());
		questionRequest.setAnswerList(this.getAnswerList());
		return questionRequest;
    }
   
	/**
	 * @return Returns the answer.
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer The answer to set.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

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
	 * @return Returns the selectedAnswers.
	 */
	public String getSelectedAnswers() {
		return selectedAnswers;
	}

	/**
	 * @param selectedAnswers The selectedAnswers to set.
	 */
	public void setSelectedAnswers(String selectedAnswers) {
		this.selectedAnswers = selectedAnswers;
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
	 * @return Returns the defaultAnswer.
	 */
	public String getDefaultAnswer() {
		return defaultAnswer;
	}

	/**
	 * @param defaultAnswer The defaultAnswer to set.
	 */
	public void setDefaultAnswer(String defaultAnswer) {
		this.defaultAnswer = defaultAnswer;
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

	/**
	 * @return Returns the requiredAnswers.
	 */
	public boolean isRequiredAnswers() {
		return requiredAnswers;
	}

	/**
	 * @param requiredAnswers The requiredAnswers to set.
	 */
	public void setRequiredAnswers(boolean requiredAnswers) {
		this.requiredAnswers = requiredAnswers;
	}

	/**
	 * @return Returns the requiredDataType.
	 */
	public boolean isRequiredDataType() {
		return requiredDataType;
	}

	/**
	 * @param requiredDataType The requiredDataType to set.
	 */
	public void setRequiredDataType(boolean requiredDataType) {
		this.requiredDataType = requiredDataType;
	}

	/**
	 * @return Returns the requiredQuestion.
	 */
	public boolean isRequiredQuestion() {
		return requiredQuestion;
	}

	/**
	 * @param requiredQuestion The requiredQuestion to set.
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
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * Method to validate the fields
	 * @return boolean
	 */
	private boolean validateField() {

		validationMessages = new ArrayList();

		boolean requiredField = false;

		if (this.getQuestion() == null || this.getQuestion().trim().equals("")) {

			requiredQuestion = true;

			requiredField = true;

		}
		if (this.getDataType() == null || this.getDataType().trim().equals("")) {

			requiredDataType = true;

			requiredField = true;
		}
		if (this.getAnswerList().isEmpty() == true) {

			requiredAnswers = true;

			requiredField = true;
		}
		if (requiredField) {

			validationMessages.add(new ErrorMessage(
					"please.enter.the.mandatory.field"));

			return false;

		}

		return true;

	}

}
