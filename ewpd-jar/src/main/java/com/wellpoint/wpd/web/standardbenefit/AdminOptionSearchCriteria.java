/*
 * AdminOptionSearchCriteria.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.adapter.metadata.SearchCriteria;


/**
 * 
 * Search Criteria for Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionSearchCriteria extends SearchCriteria{
	
	private int adminOptionSysId=11;
	
	

	/**
	 * @return Returns the adminOptionSysId.
	 */
	public int getAdminOptionSysId() {
		return adminOptionSysId;
	}
	
	/**
	 * @param adminOptionSysId The adminOptionSysId to set.
	 */
	public void setAdminOptionSysId(int adminOptionSysId) {
		this.adminOptionSysId = adminOptionSysId;
	}
}
