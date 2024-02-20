/*
 * QuestionViewRequest.java
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
 * Request class to view Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionViewRequest extends WPDRequest {

    private QuestionVO questionVO;

    private String action;


    /**
     * Returns the action.
     * 
     * @return action
     */
    public String getAction() {
        return action;
    }


    /**
     * The action to set.
     * 
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }


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
}