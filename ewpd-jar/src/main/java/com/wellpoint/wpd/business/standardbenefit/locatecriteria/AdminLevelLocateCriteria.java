/*
 * AdminLevelLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminLevelLocateCriteria extends LocateCriteria {

	private int adminLevelId;
	private String adminLevelDesc;
	/**
	 * Returns the adminLevelDesc
	 * @return String adminLevelDesc.
	 */
	public String getAdminLevelDesc() {
		return adminLevelDesc;
	}
	/**
	 * Sets the adminLevelDesc
	 * @param adminLevelDesc.
	 */
	public void setAdminLevelDesc(String adminLevelDesc) {
		this.adminLevelDesc = adminLevelDesc;
	}
	/**
	 * Returns the adminLevelId
	 * @return int adminLevelId.
	 */
	public int getAdminLevelId() {
		return adminLevelId;
	}
	/**
	 * Sets the adminLevelId
	 * @param adminLevelId.
	 */
	public void setAdminLevelId(int adminLevelId) {
		this.adminLevelId = adminLevelId;
	}
}
