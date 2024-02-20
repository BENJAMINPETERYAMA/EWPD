/*
 * Created on May 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.List;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAdmin {
	
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
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("adminList = ").append(adminList);
        return buffer.toString();
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
}
