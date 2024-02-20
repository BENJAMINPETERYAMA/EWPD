/*
 * ScheduleForTestQuestionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.question.vo.QuestionVO;

/**
 * Response for Question Schedule For Test
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ScheduleForTestQuestionResponse extends WPDResponse {

    private QuestionVO questionVO;

    private boolean scheduleForTestErrorFlag = false;

    private boolean validationErrorFlag = false;


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
     * Returns the scheduleForTestErrorFlag
     * 
     * @return boolean scheduleForTestErrorFlag.
     */
    public boolean isScheduleForTestErrorFlag() {
        return scheduleForTestErrorFlag;
    }


    /**
     * Sets the scheduleForTestErrorFlag
     * 
     * @param scheduleForTestErrorFlag.
     */
    public void setScheduleForTestErrorFlag(boolean scheduleForTestErrorFlag) {
        this.scheduleForTestErrorFlag = scheduleForTestErrorFlag;
    }


    /**
     * Returns the validationErrorFlag
     * 
     * @return boolean validationErrorFlag.
     */
    public boolean isValidationErrorFlag() {
        return validationErrorFlag;
    }


    /**
     * Sets the validationErrorFlag
     * 
     * @param validationErrorFlag.
     */
    public void setValidationErrorFlag(boolean validationErrorFlag) {
        this.validationErrorFlag = validationErrorFlag;
    }
}