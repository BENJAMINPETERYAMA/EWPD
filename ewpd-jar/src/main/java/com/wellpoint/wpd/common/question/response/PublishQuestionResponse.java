/*
 * PublishQuestionResponse.java
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
 * Response for Question Publish
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PublishQuestionResponse extends WPDResponse {

    private QuestionVO questionVO;

    private boolean publishErrorFlag = false;


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
     * Returns the publishErrorFlag
     * 
     * @return boolean publishErrorFlag.
     */
    public boolean isPublishErrorFlag() {
        return publishErrorFlag;
    }


    /**
     * Sets the publishErrorFlag
     * 
     * @param publishErrorFlag.
     */
    public void setPublishErrorFlag(boolean publishErrorFlag) {
        this.publishErrorFlag = publishErrorFlag;
    }
}