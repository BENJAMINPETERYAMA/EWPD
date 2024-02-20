/*
 * QuestionViewResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.response;

import java.util.List;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.question.bo.QuestionBO;

/**
 * Response class to view Question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionViewResponse extends WPDResponse {

    private List questionResultList;

    private QuestionBO question;


    /**
     * Returns the questionResultList.
     * 
     * @return questionResultList
     */
    public List getQuestionResultList() {
        return questionResultList;
    }


    /**
     * The questionResultList to set.
     * 
     * @param questionResultList
     */
    public void setQuestionResultList(List questionResultList) {
        this.questionResultList = questionResultList;
    }


    /**
     * Returns the question.
     * 
     * @return question
     */
    public QuestionBO getQuestion() {
        return question;
    }


    /**
     * The question to set.
     * 
     * @param question
     */
    public void setQuestion(QuestionBO question) {
        this.question = question;
    }
}