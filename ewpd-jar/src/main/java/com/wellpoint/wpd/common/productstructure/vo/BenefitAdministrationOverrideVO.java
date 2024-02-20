/*
 * BenefitAdministrationOverrideVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitAdministrationOverrideVO {

    private int entityId;

    private int questionId;

    private int answerId;

    private String answerDesc;
    
    private String questionHideFlag;
  
    //enhancement 
    private String adminOptionHideFlag ;
	//modified for solving performance issue on 13thDec 2007   
    private boolean isModified;
    //modification ends
    
    // modified to separate the display messages
    private boolean ishideModified;
    
    private boolean isAnswerModified;
    
    private boolean isHideView;
    
    private int answerOvrdCstmzdId;
    
    

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
     * Returns the answerDesc
     * 
     * @return String answerDesc.
     */

    public String getAnswerDesc() {
        return answerDesc;
    }


    /**
     * Sets the answerDesc
     * 
     * @param answerDesc.
     */

    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc;
    }


    /**
     * Returns the answerId
     * 
     * @return int answerId.
     */

    public int getAnswerId() {
        return answerId;
    }


    /**
     * Sets the answerId
     * 
     * @param answerId.
     */

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }


    /**
     * Returns the entityId
     * 
     * @return int entityId.
     */

    public int getEntityId() {
        return entityId;
    }


    /**
     * Sets the entityId
     * 
     * @param entityId.
     */

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }


    /**
     * Returns the questionId
     * 
     * @return int questionId.
     */

    public int getQuestionId() {
        return questionId;
    }


    /**
     * Sets the questionId
     * 
     * @param questionId.
     */

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
	/**
	 * @return Returns the questionHideFlag.
	 */
	public String getQuestionHideFlag() {
		return questionHideFlag;
	}
	/**
	 * @param questionHideFlag The questionHideFlag to set.
	 */
	public void setQuestionHideFlag(String questionHideFlag) {
		this.questionHideFlag = questionHideFlag;
	}
	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}
	/**
	 * @param adminOptionHideFlag The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}
	
	/**
	 * @return Returns the ishideModified.
	 */
	public boolean isIshideModified() {
		return ishideModified;
	}
	/**
	 * @param ishideModified The ishideModified to set.
	 */
	public void setIshideModified(boolean ishideModified) {
		this.ishideModified = ishideModified;
	}
	/**
	 * @return Returns the isAnswerModified.
	 */
	public boolean isAnswerModified() {
		return isAnswerModified;
	}
	/**
	 * @param isAnswerModified The isAnswerModified to set.
	 */
	public void setAnswerModified(boolean isAnswerModified) {
		this.isAnswerModified = isAnswerModified;
	}
	/**
	 * @return Returns the isHideView.
	 */
	public boolean isHideView() {
		return isHideView;
	}
	/**
	 * @param isHideView The isHideView to set.
	 */
	public void setHideView(boolean isHideView) {
		this.isHideView = isHideView;
	}
	/**
	 * @return answerOvrdCstmzdId
	 * 
	 * Returns the answerOvrdCstmzdId.
	 */
	public int getAnswerOvrdCstmzdId() {
		return answerOvrdCstmzdId;
	}
	/**
	 * @param answerOvrdCstmzdId
	 * 
	 * Sets the answerOvrdCstmzdId.
	 */
	public void setAnswerOvrdCstmzdId(int answerOvrdCstmzdId) {
		this.answerOvrdCstmzdId = answerOvrdCstmzdId;
	}
}