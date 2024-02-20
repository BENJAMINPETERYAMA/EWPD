/*
 * Question.java
 * 
 * Created on Feb 20, 2007
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class Question {
	
	private int seqNumber;
	
	private String question;
	
	private String answer;
	
	private String questionNumber;
	
	private int adminOptionSysId;
	
	private int answerId;
	
	private int systemId;
	
	private String isOpen;
	
	private List answerList;
	
// Enhancement	
	private String referenceId;
	private String referenceDesc;
	//modified for solving performance issue on 13th Dec 2007	 	
	private boolean isModified;
	//modification ends
    /**
     * @return isModified
     * 
     * Returns the isModified.
     */
    public boolean isModified() {
        return isModified;
    }
    /**
     * @param isModified
     * 
     * Sets the isModified.
     */
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
    /**
     * @return answerList
     * 
     * Returns the answerList.
     */
    public List getAnswerList() {
        return answerList;
    }
    /**
     * @param answerList
     * 
     * Sets the answerList.
     */
    public void setAnswerList(List answerList) {
        this.answerList = answerList;
    }
    /**
     * @return isOpen
     * 
     * Returns the isOpen.
     */
    public String getIsOpen() {
        return isOpen;
    }
    /**
     * @param isOpen
     * 
     * Sets the isOpen.
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
// End - Enhancement	
	
    /**
     * @return Returns the answerId.
     */
    public int getAnswerId() {
        return answerId;
    }
    /**
     * @param answerId The answerId to set.
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
    /**
     * @return Returns the adminOptionSysId.
     */
    public int getAdminOptionSysId() {
        return adminOptionSysId;
    }
    /**
     * @param adminOptionSysId The adminOptionSysId to set.
     */
    public void setAdminOptionSysId(int adminOptionSysId) {
        this.adminOptionSysId = adminOptionSysId;
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
     * @return Returns the questionNumber.
     */
    public String getQuestionNumber() {
        return questionNumber;
    }
    /**
     * @param questionNumber The questionNumber to set.
     */
    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
    /**
     * @return Returns the seqNumber.
     */
    public int getSeqNumber() {
        return seqNumber;
    }
    /**
     * @param seqNumber The seqNumber to set.
     */
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

	/**
	 * @return Returns the systemId.
	 */
	public int getSystemId() {
		return systemId;
	}
	/**
	 * @param systemId The systemId to set.
	 */
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}
/**
 * @return Returns the referenceId.
 */
public String getReferenceId() {
	return referenceId;
}
/**
 * @param referenceId The referenceId to set.
 */
public void setReferenceId(String referenceId) {
	this.referenceId = referenceId;
}
}

