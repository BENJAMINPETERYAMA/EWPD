/*
 * PossibleAnswerVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PossibleAnswerVO {

    private int questionNumber;

    private int possibleAnswerId;

    private String possibleAnswerDesc;


    /**
     * Returns the possibleAnswerDesc
     * 
     * @return String possibleAnswerDesc.
     */
    public String getPossibleAnswerDesc() {
        return possibleAnswerDesc;
    }


    /**
     * Sets the possibleAnswerDesc
     * 
     * @param possibleAnswerDesc.
     */
    public void setPossibleAnswerDesc(String possibleAnswerDesc) {
        this.possibleAnswerDesc = possibleAnswerDesc;
    }


    /**
     * Returns the possibleAnswerId
     * 
     * @return int possibleAnswerId.
     */
    public int getPossibleAnswerId() {
        return possibleAnswerId;
    }


    /**
     * Sets the possibleAnswerId
     * 
     * @param possibleAnswerId.
     */
    public void setPossibleAnswerId(int possibleAnswerId) {
        this.possibleAnswerId = possibleAnswerId;
    }


    /**
     * Returns the questionNumber
     * 
     * @return int questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }


    /**
     * Sets the questionNumber
     * 
     * @param questionNumber.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("questionNumber = ").append(this.questionNumber);
        buffer.append(", possibleAnswerId = ").append(this.possibleAnswerId);
        buffer.append(", possibleAnswerDesc = ")
                .append(this.possibleAnswerDesc);
        return buffer.toString();
    }

}