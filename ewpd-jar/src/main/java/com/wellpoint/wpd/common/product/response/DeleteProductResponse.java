/*
 * DeleteProductResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteProductResponse extends WPDResponse {

	 private boolean valid;
	 private boolean success;	
	 
	
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
	 * @return Returns the valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * @param valid The valid to set.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
