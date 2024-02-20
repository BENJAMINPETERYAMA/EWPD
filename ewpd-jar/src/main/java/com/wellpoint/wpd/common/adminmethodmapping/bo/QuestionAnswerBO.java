/*
 * Created on Oct 16, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;



/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QuestionAnswerBO extends BusinessObject implements Comparable {

	private int questionId;
	private int possibleAnswerId;
	private String  questionDesc;
	private String possibleAnswer;
	
	
	public int getPossibleAnswerId() {
		return possibleAnswerId;
	}
	public void setPossibleAnswerId(int possibleAnswerId) {
		this.possibleAnswerId = possibleAnswerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getPossibleAnswer() {
		return possibleAnswer;
	}
	public void setPossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		QuestionAnswerBO questionAnswerBO = (QuestionAnswerBO)obj;
		return(this.getQuestionDesc().compareToIgnoreCase(questionAnswerBO.getQuestionDesc()));
	}
}
