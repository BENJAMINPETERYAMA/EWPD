/*
 * RetrieveOpenQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.business.standardbenefit.locatecriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveOpenQuestionRequest extends WPDRequest{

    QuestionLocateCriteria openQuestionLocateCriteria;
    /**
     * 
     */
    public RetrieveOpenQuestionRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

    /**
     * @return Returns the openQuestionLocateCriteria.
     */
    public QuestionLocateCriteria getOpenQuestionLocateCriteria() {
        return openQuestionLocateCriteria;
    }
    /**
     * @param openQuestionLocateCriteria The openQuestionLocateCriteria to set.
     */
    public void setOpenQuestionLocateCriteria(
            QuestionLocateCriteria openQuestionLocateCriteria) {
        this.openQuestionLocateCriteria = openQuestionLocateCriteria;
    }
}
