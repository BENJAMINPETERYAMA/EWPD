/*
 * SearchCriteriaValidationException.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.exception;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchCriteriaValidationException extends SevereException {

    String userMessage= null;
    String[] userParameters = null;
    /**
     * @param logMessage
     * @param logParameters
     * @param cause
     */
    public SearchCriteriaValidationException(String logMessage, List logParameters, Throwable cause) {
        super(logMessage, logParameters, cause);
    }
    
    public SearchCriteriaValidationException(String logMessage, List logParameters, Throwable cause, String userMessage, String[] userParameters) {
        super(logMessage, logParameters, cause);
        this.userMessage = userMessage;
        this.userParameters = userParameters;
    }
    
    
	/**
	 * @return Returns the userMessage.
	 */
	public String getUserMessage() {
		return userMessage;
	}
	/**
	 * @return Returns the userParameters.
	 */
	public String[] getUserParameters() {
		return userParameters;
	}
}
