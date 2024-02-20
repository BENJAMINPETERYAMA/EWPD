/*
 * Created on Apr 23, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u13531
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceTypeMappingDeleteRequest extends WPDRequest {
	
	private int mappingSysId;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the mappingSysId.
	 */
	public int getMappingSysId() {
		return mappingSysId;
	}
	/**
	 * @param mappingSysId The mappingSysId to set.
	 */
	public void setMappingSysId(int mappingSysId) {
		this.mappingSysId = mappingSysId;
	}
}
