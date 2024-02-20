/*
 * DeleteMandateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.mandate.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteMandateResponse extends WPDResponse{
	
	private boolean deleteFlag;
	
	private boolean errorFlag;

	/**
	 * Returns the deleteFlag
	 * @return boolean deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * Sets the deleteFlag
	 * @param deleteFlag.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
    /**
     * Returns the errorFlag
     * @return boolean errorFlag.
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }
    
    /**
     * Sets the errorFlag
     * @param errorFlag.
     */
    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }
}
