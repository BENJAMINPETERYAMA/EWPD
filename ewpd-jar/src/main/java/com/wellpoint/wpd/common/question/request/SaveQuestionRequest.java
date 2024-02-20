/*
 * SaveQuestionRequest.java
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
 * Request for save Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveQuestionRequest extends WPDRequest {

    private QuestionVO questionVO;

    private boolean updateFlag = false;

    private boolean checkInFlag = false;

    private boolean checkOutFlag = false;

    private boolean validationFlag = false;


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
     * Returns the checkInFlag
     * 
     * @return boolean checkInFlag.
     */
    public boolean isCheckInFlag() {
        return checkInFlag;
    }


    /**
     * Sets the checkInFlag
     * 
     * @param checkInFlag.
     */
    public void setCheckInFlag(boolean checkInFlag) {
        this.checkInFlag = checkInFlag;
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
     * Returns the validationFlag
     * 
     * @return boolean validationFlag.
     */
    public boolean isValidationFlag() {
        return validationFlag;
    }


    /**
     * Sets the validationFlag
     * 
     * @param validationFlag.
     */
    public void setValidationFlag(boolean validationFlag) {
        this.validationFlag = validationFlag;
    }


    /**
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (!this.checkInFlag) {
            if (null == this.questionVO.getQuestionDesc()
                    || "".equals(this.questionVO.getQuestionDesc()))
                throw new ValidationException(
                        "Question description is missing", null, null);
            if (-1 == this.questionVO.getDataTypeId())
                throw new ValidationException("Data Type is missing", null,
                        null);
            if (null == this.questionVO.getAnswerList()
                    || this.questionVO.getAnswerList().size() == 0)
                throw new ValidationException("Answer list is missing", null,
                        null);
        }

    }

}