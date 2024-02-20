/*
 * SearchQuestionResponse.java
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

/**
 * Response for Question Search
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchQuestionResponse extends WPDResponse {
    private List questionSearchResultsList;


    /**
     * Returns the questionSearchResultsList.
     * 
     * @return questionSearchResultsList
     */
    public List getQuestionSearchResultsList() {
        return questionSearchResultsList;
    }


    /**
     * The questionSearchResultsList to set.
     * 
     * @param questionSearchResultsList
     */
    public void setQuestionSearchResultsList(List questionSearchResultsList) {
        this.questionSearchResultsList = questionSearchResultsList;
    }
}

