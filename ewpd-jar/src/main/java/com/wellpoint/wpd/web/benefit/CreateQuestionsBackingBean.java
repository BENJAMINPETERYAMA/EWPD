/*
 * Created on Feb 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.benefit;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateQuestionsBackingBean extends WPDBackingBean {

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

	public CreateQuestionsBackingBean() {
	

		//answerList=new ArrayList();

	}

	public String create() {
		String tempValue;
		StringTokenizer st;
		st = new StringTokenizer(selectedAnswers, "~");
		answerList = new ArrayList();
		while (st.hasMoreTokens()) {
			answerList.add(st.nextToken());
		}

		Iterator itr = answerList.iterator();
		while (itr.hasNext()) {

			String ans = (String) itr.next();


		}
		if (!validateField()) {

			addAllMessagesToRequest(validationMessages);

		}

		/* 
		 String [] ans = selectedAnswers.split("~");
		 for (int i = 0; i < ans.length; i++) {
		 } */
		return "";
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
	 * @return Returns the question.
	 */

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
	 * @return Returns the test.
	 */

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