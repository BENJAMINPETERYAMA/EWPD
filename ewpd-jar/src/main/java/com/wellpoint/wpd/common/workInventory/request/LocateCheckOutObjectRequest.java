/*
 * LocateCheckOutObjectRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.workInventory.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.security.domain.User;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LocateCheckOutObjectRequest.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LocateCheckOutObjectRequest extends WPDRequest {

	private User user;

	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            The user to set.
	 */
	public void setUser(User user) {
		this.user = user;
	}
}