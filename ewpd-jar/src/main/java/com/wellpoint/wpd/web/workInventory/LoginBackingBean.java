/*
 * LoginBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.workInventory;

import com.wellpoint.wpd.web.framework.WPDBackingBean;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LoginBackingBean extends WPDBackingBean {
	private String userId;

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String gotoLaunchPage() {
		return "success";
	}
}