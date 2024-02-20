/*
 * Created on Feb 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

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
	
	private String answer;
	
	private String answerForDelete;

	List answerList = null;

	private List validationMessages = null;

	private boolean requiredQuestion = false;

	private boolean requiredDataType = false;

	private boolean requiredAnswers = false;

	public CreateQuestionsBackingBean() {	
		this.setBreadCrumbText("Administration >> Question >> Create");	
		answerList=new ArrayList();

	}

	public String create() {
		StringTokenizer st =new StringTokenizer(answer,"~");
		String answerString;
		while (st.hasMoreTokens()) {
			answerString=st.nextToken();
			if(!(answerList.contains(answerString))){
		      answerList.add(answerString);
			}
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
	
	public String addAnswers(){
		StringTokenizer st =new StringTokenizer(answer,"~");
		String answerString;
		while (st.hasMoreTokens()) {
			answerString=st.nextToken();
			if(!(answerList.contains(answerString))){
		      answerList.add(answerString);
			}
		 }

		Iterator itr=answerList.iterator();
		while(itr.hasNext()){

		}
		return"";
	}
	
	public String deleteAnswer(){
		answer=answer.replaceAll(answerForDelete,"");
		String answerString;
		StringTokenizer st =new StringTokenizer(answer,"~");
		while (st.hasMoreTokens()) {
			answerString=st.nextToken();
			if(!(answerList.contains(answerString))){
				answerList.add(answerString);
			}
		}
		return "";
	}
	/**
	 * @return Returns the answerList.
	 */
	public List getAnswerList() {
		if(this.answerList != null && this.answerList.size() == 0)
			return null;
		
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

		validationMessages = new ArrayList(1);

		boolean requiredField = false;

		if (this.getQuestion() == null || this.getQuestion().trim().equals("")) {

			requiredQuestion = true;

			requiredField = true;

		}
		if (this.getDataType() == null || this.getDataType().trim().equals("")) {

			requiredDataType = true;

			requiredField = true;
		}
		if (this.getAnswerList() == null) {

			requiredAnswers = true;

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
	 * @return Returns the answerForDelete.
	 */
	public String getAnswerForDelete() {
		return answerForDelete;
	}
	/**
	 * @param answerForDelete The answerForDelete to set.
	 */
	public void setAnswerForDelete(String answerForDelete) {
		this.answerForDelete = answerForDelete;
	}
}