/*
 * RefDataDomainValidationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.refdata.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RefDataDomainValidationResponse extends WPDResponse {

	 private boolean success;
	 
	 private StringBuffer errorMessage;
	 
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
    /**
     * @return errorMessage
     * 
     * Returns the errorMessage.
     */
    public StringBuffer getErrorMessage() {
        return errorMessage;
    }
    /**
     * @param errorMessage
     * 
     * Sets the errorMessage.
     */
    public void setErrorMessage(StringBuffer errorMessage) {
        this.errorMessage = errorMessage;
    }
}
