/*
 * TreeAdminOptions.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TreeAdminOptions {
	
	private int benefitAdministrationId;
	
	private int AdminOptionId;
	
	private int AdminLevelType;
	
	private String adminOptionDesc;
	
	private int adminOptionAssnId;




	/**
	 * Returns the benefitAdministrationId
	 * @return int benefitAdministrationId.
	 */
	public int getBenefitAdministrationId() {
		return benefitAdministrationId;
	}
	/**
	 * Sets the benefitAdministrationId
	 * @param benefitAdministrationId.
	 */
	public void setBenefitAdministrationId(int benefitAdministrationId) {
		this.benefitAdministrationId = benefitAdministrationId;
	}
	/**
	 * Returns the adminLevelType
	 * @return int adminLevelType.
	 */
	public int getAdminLevelType() {
		return AdminLevelType;
	}
	/**
	 * Sets the adminLevelType
	 * @param adminLevelType.
	 */
	public void setAdminLevelType(int adminLevelType) {
		AdminLevelType = adminLevelType;
	}
	/**
	 * Returns the adminOptionId
	 * @return int adminOptionId.
	 */
	public int getAdminOptionId() {
		return AdminOptionId;
	}
	/**
	 * Sets the adminOptionId
	 * @param adminOptionId.
	 */
	public void setAdminOptionId(int adminOptionId) {
		AdminOptionId = adminOptionId;
	}

	/**
	 * Returns the adminOptionDesc
	 * @return String adminOptionDesc.
	 */
	public String getAdminOptionDesc() {
		return adminOptionDesc;
	}
	/**
	 * Sets the adminOptionDesc
	 * @param adminOptionDesc.
	 */
	public void setAdminOptionDesc(String adminOptionDesc) {
		this.adminOptionDesc = adminOptionDesc;
	}
	/**
	 * Returns the adminOptionAssnId
	 * @return int adminOptionAssnId.
	 */
	public int getAdminOptionAssnId() {
		return adminOptionAssnId;
	}
	/**
	 * Sets the adminOptionAssnId
	 * @param adminOptionAssnId.
	 */
	public void setAdminOptionAssnId(int adminOptionAssnId) {
		this.adminOptionAssnId = adminOptionAssnId;
	}
}
