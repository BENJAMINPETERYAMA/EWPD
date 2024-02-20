/*
 * Created on Oct 18, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QuestionAnswerGroupBO extends BusinessObject {


	private String questionId;	
	private String possibleAnswerId;
	private String searchCriteria;
	private String questionDesc;
	//private String possibleAnswerDescList;
	private String possibleAnswerDesc;
	private int rownum;
	private String possibleAnswerId2;
	private String possibleAnswerId3;
	private List possibleAnswerIdList;
	private List possibleAnswerDescList;
	private String possibleAnswerDesc2;
	private String possibleAnswerDesc3;
	
	private boolean questionChecked=false;
	private boolean answerChecked=false;
	private boolean answerChecked1=false;
	private boolean answerChecked2=false;


	
	/**
	 * @return Returns the rownum.
	 */
	public int getRownum() {
		return rownum;
	}
	/**
	 * @param rownum The rownum to set.
	 */
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	/**
	 * @return Returns the possibleAnswerDescList.
	 */
	public List getPossibleAnswerDescList() {
		return possibleAnswerDescList;
	}
	/**
	 * @param possibleAnswerDescList The possibleAnswerDescList to set.
	 */
	public void setPossibleAnswerDescList(List possibleAnswerDescList) {
		this.possibleAnswerDescList = possibleAnswerDescList;
	}
	/**
	 * @return Returns the possibleAnswerId.
	 */
	public String getPossibleAnswerId() {
		return possibleAnswerId;
	}
	/**
	 * @param possibleAnswerId The possibleAnswerId to set.
	 */
	public void setPossibleAnswerId(String possibleAnswerId) {
		this.possibleAnswerId = possibleAnswerId;
	}
	/**
	 * @return Returns the possibleAnswerIdList.
	 */
	public List getPossibleAnswerIdList() {
		return possibleAnswerIdList;
	}
	/**
	 * @param possibleAnswerIdList The possibleAnswerIdList to set.
	 */
	public void setPossibleAnswerIdList(List possibleAnswerIdList) {
		this.possibleAnswerIdList = possibleAnswerIdList;
	}
	/**
	 * @return Returns the questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}
	/**
	 * @param questionDesc The questionDesc to set.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	/**
	 * @return Returns the questionId.
	 */
	public String getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 *//* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the searchCriteria.
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}
	/**
	 * @param searchCriteria The searchCriteria to set.
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	/**
	 * @return Returns the possibleAnswerDesc.
	 */
	public String getPossibleAnswerDesc() {
		return possibleAnswerDesc;
	}
	/**
	 * @param possibleAnswerDesc The possibleAnswerDesc to set.
	 */
	public void setPossibleAnswerDesc(String possibleAnswerDesc) {
		this.possibleAnswerDesc = possibleAnswerDesc;
	}
	/**
	 * @return Returns the possibleAnswerDesc2.
	 */
	public String getPossibleAnswerDesc2() {
		return possibleAnswerDesc2;
	}
	/**
	 * @param possibleAnswerDesc2 The possibleAnswerDesc2 to set.
	 */
	public void setPossibleAnswerDesc2(String possibleAnswerDesc2) {
		this.possibleAnswerDesc2 = possibleAnswerDesc2;
	}
	/**
	 * @return Returns the possibleAnswerDesc3.
	 */
	public String getPossibleAnswerDesc3() {
		return possibleAnswerDesc3;
	}
	/**
	 * @param possibleAnswerDesc3 The possibleAnswerDesc3 to set.
	 */
	public void setPossibleAnswerDesc3(String possibleAnswerDesc3) {
		this.possibleAnswerDesc3 = possibleAnswerDesc3;
	}
	/**
	 * @return Returns the possibleAnswerId2.
	 */
	public String getPossibleAnswerId2() {
		return possibleAnswerId2;
	}
	/**
	 * @param possibleAnswerId2 The possibleAnswerId2 to set.
	 */
	public void setPossibleAnswerId2(String possibleAnswerId2) {
		this.possibleAnswerId2 = possibleAnswerId2;
	}
	/**
	 * @return Returns the possibleAnswerId3.
	 */
	public String getPossibleAnswerId3() {
		return possibleAnswerId3;
	}
	/**
	 * @param possibleAnswerId3 The possibleAnswerId3 to set.
	 */
	public void setPossibleAnswerId3(String possibleAnswerId3) {
		this.possibleAnswerId3 = possibleAnswerId3;
	}
	
	/**
	 * @return Returns the answerChecked.
	 */
	public boolean isAnswerChecked() {
		return answerChecked;
	}
	/**
	 * @param answerChecked The answerChecked to set.
	 */
	public void setAnswerChecked(boolean answerChecked) {
		this.answerChecked = answerChecked;
	}
	/**
	 * @return Returns the answerChecked1.
	 */
	public boolean isAnswerChecked1() {
		return answerChecked1;
	}
	/**
	 * @param answerChecked1 The answerChecked1 to set.
	 */
	public void setAnswerChecked1(boolean answerChecked1) {
		this.answerChecked1 = answerChecked1;
	}
	/**
	 * @return Returns the answerChecked2.
	 */
	public boolean isAnswerChecked2() {
		return answerChecked2;
	}
	/**
	 * @param answerChecked2 The answerChecked2 to set.
	 */
	public void setAnswerChecked2(boolean answerChecked2) {
		this.answerChecked2 = answerChecked2;
	}
	/**
	 * @return Returns the questionChecked.
	 */
	public boolean isQuestionChecked() {
		return questionChecked;
	}
	/**
	 * @param questionChecked The questionChecked to set.
	 */
	public void setQuestionChecked(boolean questionChecked) {
		this.questionChecked = questionChecked;
	}
}
