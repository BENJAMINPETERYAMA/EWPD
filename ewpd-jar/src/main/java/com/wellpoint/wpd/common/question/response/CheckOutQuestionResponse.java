/*
 * CheckOutQuestionResponse.java
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
 * Response for Question Check Out
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CheckOutQuestionResponse extends WPDResponse {

    private QuestionVO questionVO;

    private boolean checkOutErrorFlag = false;

    private QuestionBO questionBO;
    
    

	/**
	 * @return Returns the questionBO.
	 */
	public QuestionBO getQuestionBO() {
		return questionBO;
	}
	/**
	 * @param questionBO The questionBO to set.
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
}