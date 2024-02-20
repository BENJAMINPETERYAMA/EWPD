/*
 * Created on Apr 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u13531
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceTypeMappingDeleteResponse extends WPDResponse {

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
}
