/*
 * LocateSelectedQuestionListResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateSelectedQuestionListResponse extends WPDResponse{
    // variable declarations
    List selectedQuestionList;
    /**
     * @return Returns the selectedQuestionList.
     */
    public List getSelectedQuestionList() {
        return selectedQuestionList;
    }
    /**
     * @param selectedQuestionList The selectedQuestionList to set.
     */
    public void setSelectedQuestionList(List selectedQuestionList) {
        this.selectedQuestionList = selectedQuestionList;
    }
}
