/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethodmaintain.response;

import java.util.List;

import com.wellpoint.wpd.common.adminmethodmaintain.bo.AdminMethodMaintainBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U17066
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodEditResponse extends WPDResponse {
	private List editList;
	private boolean status;
	private AdminMethodMaintainBO adminMethodMaintainBO;
	/**
	 * @return Returns the status.
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return Returns the editList.
	 */
	public List getEditList() {
		return editList;
	}
	/**
	 * @param editList The editList to set.
	 */
	public void setEditList(List editList) {
		this.editList = editList;
	}
	/**
	 * @return Returns the adminMethodMaintainBO.
	 */
	public AdminMethodMaintainBO getAdminMethodMaintainBO() {
		return adminMethodMaintainBO;
	}
	/**
	 * @param adminMethodMaintainBO The adminMethodMaintainBO to set.
	 */
	public void setAdminMethodMaintainBO(
			AdminMethodMaintainBO adminMethodMaintainBO) {
		this.adminMethodMaintainBO = adminMethodMaintainBO;
	}
}
