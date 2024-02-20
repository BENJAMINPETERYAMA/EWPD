/*
 * RetrieveQuestionRequest.java
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
 * Request for Question Retrieve
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveQuestionRequest extends WPDRequest {

    private QuestionVO questionVO;

    private boolean updateFlag = false;

    private boolean checkOutFlag = false;


    /**
     * Returns the updateFlag
     * 
     * @return boolean updateFlag.
     */
    public boolean isUpdateFlag() {
        return updateFlag;
    }


    /**
     * Sets the updateFlag
     * 
     * @param updateFlag.
     */
    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
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
     * Returns the checkOutFlag
     * 
     * @return boolean checkOutFlag.
     */
    public boolean isCheckOutFlag() {
        return checkOutFlag;
    }


    /**
     * Sets the checkOutFlag
     * 
     * @param checkOutFlag.
     */
    public void setCheckOutFlag(boolean checkOutFlag) {
        this.checkOutFlag = checkOutFlag;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }
}