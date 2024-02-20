/*
 * Created on Mar 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.mandate.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CopyMandateRequest extends WPDRequest {
	
	private int mandateId;
	
	/**
	 * @return Returns the mandateId.
	 */
	public int getMandateId() {
		return mandateId;
	}
	/**
	 * @param mandateId The mandateId to set.
	 */
	public void setMandateId(int mandateId) {
		this.mandateId = mandateId;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
