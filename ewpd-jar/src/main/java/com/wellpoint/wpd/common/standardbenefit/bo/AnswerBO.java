/*
 * AnswerBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AnswerBO {
    // variable declaration
    private int answerId;
    private String answerDesc;
    
    /**
     * @return Returns the answerDesc.
     */
    public String getAnswerDesc() {
        return answerDesc;
    }
    /**
     * @param answerDesc The answerDesc to set.
     */
    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc;
    }
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
}
