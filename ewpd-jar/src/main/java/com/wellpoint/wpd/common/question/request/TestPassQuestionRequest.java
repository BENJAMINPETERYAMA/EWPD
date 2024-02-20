/*
 * TestPassQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.question.vo.QuestionVO;

/**
 * Request for Question Test Pass
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TestPassQuestionRequest extends WPDRequest {

    private QuestionVO questionVO;


    /**
     * Returns the questionVO.
     * 
     * @return questionVO
     */
    public QuestionVO getQuestionVO() {
        return questionVO;
    }


    /**
     * The questionVO to set.
     * 
     * @param questionVO
     */
    public void setQuestionVO(QuestionVO questionVO) {
        this.questionVO = questionVO;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }

}