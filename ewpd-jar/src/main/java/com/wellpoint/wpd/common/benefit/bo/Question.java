/*
 * Question.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefit.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class Question extends BusinessObject{
	
	private int questionNumber = (int)(Math.random()*10000);
	
	private String questionDesc;
	
	private int dataTypeId;

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
	public String getQuestionDesc() {
		return questionDesc;
	}
	/**
	 * @param question The question to set.
	 */
	public void setQuestionDesc(String question) {
		this.questionDesc = question;
	}
	
    public boolean equals(BusinessObject object) {
        return true;
    }

    public  int hashCode() {
        return 1;
    }

    public  String toString() {
       return "" ;
    }


	/**
	 * @return Returns the key.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param key The key to set.
	 */
	public void setQuestionNumber(int key) {
		this.questionNumber = key;
	}
	/**
	 * @return Returns the dataTypeId.
	 */
	public int getDataTypeId() {
		return dataTypeId;
	}
	/**
	 * @param dataTypeId The dataTypeId to set.
	 */
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
}
