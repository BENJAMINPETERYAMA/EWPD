/*
 * AdminLevelResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminLevelResponse extends WPDResponse {
	
	private List adminLevelList;

	/**
	 * Returns the adminLevelList
	 * @return List adminLevelList.
	 */
	public List getAdminLevelList() {
		return adminLevelList;
	}
	/**
	 * Sets the adminLevelList
	 * @param adminLevelList.
	 */
	public void setAdminLevelList(List adminLevelList) {
		this.adminLevelList = adminLevelList;
	}
}
