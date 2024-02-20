/*
 * ContractProductAdmin.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductAdmin {
	
	private List adminList; 
	
	private int action;

	/**
	 * @return Returns the adminList.
	 */
	public List getAdminList() {
		return adminList;
	}
	/**
	 * @param adminList The adminList to set.
	 */
	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("adminList = ").append(adminList);
        return buffer.toString();
    }
}
