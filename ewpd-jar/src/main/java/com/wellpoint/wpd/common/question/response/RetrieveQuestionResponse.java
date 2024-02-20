/*
 * RetrieveQuestionResponse.java
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
 * Response for Question Retrieve
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveQuestionResponse extends WPDResponse {

    private QuestionBO questionBO;

    private QuestionVO questionVO;

    private boolean checkOutErrorFlag = false;
    
    private boolean unlockFlag;


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
     * Returns the checkOutErrorFlag
     * 
     * @return boolean checkOutErrorFlag.
     */
    public boolean isCheckOutErrorFlag() {
        return checkOutErrorFlag;
    }


    /**
     * Sets the checkOutErrorFlag
     * 
     * @param checkOutErrorFlag.
     */
    public void setCheckOutErrorFlag(boolean checkOutErrorFlag) {
        this.checkOutErrorFlag = checkOutErrorFlag;
    }
    
	/**
	 * @return Returns the unlockFlag.
	 */
	public boolean isUnlockFlag() {
		return unlockFlag;
	}
	/**
	 * @param unlockFlag The unlockFlag to set.
	 */
	public void setUnlockFlag(boolean unlockFlag) {
		this.unlockFlag = unlockFlag;
	}
}