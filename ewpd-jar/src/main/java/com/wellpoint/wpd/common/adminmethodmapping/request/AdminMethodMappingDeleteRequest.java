/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmapping.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingDeleteRequest extends WPDRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
	private String adminMethodsysID;

	/**
	 * @return Returns the adminMethodsysID.
	 */
	public String getAdminMethodsysID() {
		return adminMethodsysID;
	}
	/**
	 * @param adminMethodsysID The adminMethodsysID to set.
	 */
	public void setAdminMethodsysID(String adminMethodsysID) {
		this.adminMethodsysID = adminMethodsysID;
	}
}
