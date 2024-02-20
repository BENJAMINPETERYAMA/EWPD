/*
 * SaveQuestionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.question.vo.QuestionVO;

/**
 * Response for Question Save
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveQuestionResponse extends WPDResponse {

    private boolean errorFlag = false;

    private QuestionBO questionBO;

    private QuestionVO questionVO;

    private boolean checkInErrorFlag = false;

    private boolean checkInSuccessFlag = false;


    /**
     * Returns the questionBO
     * 
     * @return QuestionBO questionBO.
     */
    public QuestionBO getQuestionBO() {
        return questionBO;
    }


    /**
     * Sets the questionBO
     * 
     * @param questionBO.
     */
    public void setQuestionBO(QuestionBO questionBO) {
        this.questionBO = questionBO;
    }


    /**
     * Returns the questionVO
     * 
     * @return QuestionVO questionVO.
     */
    public QuestionVO getQuestionVO() {
        return questionVO;
    }


    /**
     * Sets the questionVO
     * 
     * @param questionVO.
     */
    public void setQuestionVO(QuestionVO questionVO) {
        this.questionVO = questionVO;
    }


    /**
     * Returns the errorFlag
     * 
     * @return boolean errorFlag.
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }


    /**
     * Sets the errorFlag
     * 
     * @param errorFlag.
     */
    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }


    /**
     * Returns the checkInErrorFlag
     * 
     * @return boolean checkInErrorFlag.
     */
    public boolean isCheckInErrorFlag() {
        return checkInErrorFlag;
    }


    /**
     * Sets the checkInErrorFlag
     * 
     * @param checkInErrorFlag.
     */
    public void setCheckInErrorFlag(boolean checkInErrorFlag) {
        this.checkInErrorFlag = checkInErrorFlag;
    }


    /**
     * Returns the checkInSuccessFlag
     * 
     * @return boolean checkInSuccessFlag.
     */
    public boolean isCheckInSuccessFlag() {
        return checkInSuccessFlag;
    }


    /**
     * Sets the checkInSuccessFlag
     * 
     * @param checkInSuccessFlag.
     */
    public void setCheckInSuccessFlag(boolean checkInSuccessFlag) {
        this.checkInSuccessFlag = checkInSuccessFlag;
    }
}