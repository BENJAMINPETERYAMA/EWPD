/*
 * RetrieveOpenQuestionResponse.java
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
public class RetrieveOpenQuestionResponse extends WPDResponse{
    
    List openQuestionList;

    /**
     * 
     */
    public RetrieveOpenQuestionResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the openQuestionList.
     */
    public List getOpenQuestionList() {
        return openQuestionList;
    }
    /**
     * @param openQuestionList The openQuestionList to set.
     */
    public void setOpenQuestionList(List openQuestionList) {
        this.openQuestionList = openQuestionList;
    }
}
