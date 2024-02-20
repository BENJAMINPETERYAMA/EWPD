/*
 * TestFailQuestionResponse.java
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
 * Response for Question Test Fail
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TestFailQuestionResponse extends WPDResponse {

    private QuestionVO questionVO;

    private boolean testFailErrorFlag = false;


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
     * Returns the testFailErrorFlag.
     * @return testFailErrorFlag
     */
    public boolean isTestFailErrorFlag() {
        return testFailErrorFlag;
    }


    /**
     * The testFailErrorFlag to set.
     * @param testFailErrorFlag
     */
    public void setTestFailErrorFlag(boolean testFailErrorFlag) {
        this.testFailErrorFlag = testFailErrorFlag;
    }
}