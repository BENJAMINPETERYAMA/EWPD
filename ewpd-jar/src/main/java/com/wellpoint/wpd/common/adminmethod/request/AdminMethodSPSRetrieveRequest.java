/*
 * Created on Jan 19, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSPSRetrieveRequest extends WPDRequest {

	
	private int entityId;
	private int prodSysId;
	private int benCompSysId;
	private int benSysyId;
	private int benAdminId;
	/**
	 * @return Returns the benAdminId.
	 */
	public int getBenAdminId() {
		return benAdminId;
	}
	/**
	 * @param benAdminId The benAdminId to set.
	 */
	public void setBenAdminId(int benAdminId) {
		this.benAdminId = benAdminId;
	}
	/**
	 * @return Returns the benCompSysId.
	 */
	public int getBenCompSysId() {
		return benCompSysId;
	}
	/**
	 * @param benCompSysId The benCompSysId to set.
	 */
	public void setBenCompSysId(int benCompSysId) {
		this.benCompSysId = benCompSysId;
	}
	/**
	 * @return Returns the benSysyId.
	 */
	public int getBenSysyId() {
		return benSysyId;
	}
	/**
	 * @param benSysyId The benSysyId to set.
	 */
	public void setBenSysyId(int benSysyId) {
		this.benSysyId = benSysyId;
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
	/**
	 * @return Returns the prodSysId.
	 */
	public int getProdSysId() {
		return prodSysId;
	}
	/**
	 * @param prodSysId The prodSysId to set.
	 */
	public void setProdSysId(int prodSysId) {
		this.prodSysId = prodSysId;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
