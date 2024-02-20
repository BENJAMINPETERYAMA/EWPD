/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditIndicativeMappingResponse extends WPDResponse {

	boolean success;
	
	/**
	 * @return Returns the success.
	 */
	public boolean getSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
