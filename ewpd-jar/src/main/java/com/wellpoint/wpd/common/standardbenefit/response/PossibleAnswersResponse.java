/*
 * PossibleAnswersResponse.java
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
public class PossibleAnswersResponse extends WPDResponse{
    
    // variable declaration
    List possibleAnswers;

    /**
     * @return Returns the possibleAnswers.
     */
    public List getPossibleAnswers() {
        return possibleAnswers;
    }
    /**
     * @param possibleAnswers The possibleAnswers to set.
     */
    public void setPossibleAnswers(List possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
