/*
 * Created on Sep 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import com.wellpoint.wpd.business.adminmethod.locatecriteria.AdminMethodLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U15701
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodOverrideRequest extends WPDRequest{
	
	public AdminMethodLocateCriteria adminMethodLocateCriteria;
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

	/**
	 * @return Returns the adminMethodLocateCriteria.
	 */
	public AdminMethodLocateCriteria getAdminMethodLocateCriteria() {
		return adminMethodLocateCriteria;
	}
	/**
	 * @param adminMethodLocateCriteria The adminMethodLocateCriteria to set.
	 */
	public void setAdminMethodLocateCriteria(
			AdminMethodLocateCriteria adminMethodLocateCriteria) {
		this.adminMethodLocateCriteria = adminMethodLocateCriteria;
	}
}
