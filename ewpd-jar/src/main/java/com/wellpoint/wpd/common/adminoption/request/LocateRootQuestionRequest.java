/*
 * LocateRootQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateRootQuestionRequest extends WPDRequest{
	
	private int adminOptionId;
	
	 /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }
	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
}
