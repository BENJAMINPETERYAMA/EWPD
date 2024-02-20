/*
 * Created on Aug 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAdminOptionLocateCriteria  extends LocateCriteria{
	
	private String entiityType;
	
	private int entityId;
	
	private int adminOptSysId;
	
	
	
	

	/**
	 * @return Returns the adminOptSysId.
	 */
	public int getAdminOptSysId() {
		return adminOptSysId;
	}
	/**
	 * @param adminOptSysId The adminOptSysId to set.
	 */
	public void setAdminOptSysId(int adminOptSysId) {
		this.adminOptSysId = adminOptSysId;
	}
	/**
	 * @return Returns the entiityType.
	 */
	public String getEntiityType() {
		return entiityType;
	}
	/**
	 * @param entiityType The entiityType to set.
	 */
	public void setEntiityType(String entiityType) {
		this.entiityType = entiityType;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
}
