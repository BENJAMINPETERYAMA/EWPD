/*
 * SearchQuestionRequest.java
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
import com.wellpoint.wpd.common.question.vo.QuestionSearchCriteriaVO;

/**
 * Request for Question Search
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchQuestionRequest extends WPDRequest {

    private QuestionSearchCriteriaVO questionSearchCriteriaVO;


    /**
     * Returns the questionSearchCriteriaVO.
     * 
     * @return questionSearchCriteriaVO
     */
    public QuestionSearchCriteriaVO getQuestionSearchCriteriaVO() {
        return questionSearchCriteriaVO;
    }


    /**
     * The questionSearchCriteriaVO to set.
     * 
     * @param questionSearchCriteriaVO
     */
    public void setQuestionSearchCriteriaVO(
            QuestionSearchCriteriaVO questionSearchCriteriaVO) {
        this.questionSearchCriteriaVO = questionSearchCriteriaVO;
    }


    /**
     * Method to validate the request
     * 
     * @return void.
     */
    public void validate() throws ValidationException {

    }
}