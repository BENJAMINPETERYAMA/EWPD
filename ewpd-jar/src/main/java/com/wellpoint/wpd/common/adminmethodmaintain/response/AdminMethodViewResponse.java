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
public class AdminMethodViewResponse extends WPDResponse {
	private List viewResult;
	private  AdminMethodMaintainBO adminMethodMaintainBO;

	/**
	 * @return Returns the viewResult.
	 */
	public List getViewResult() {
		return viewResult;
	}
	/**
	 * @param viewResult The viewResult to set.
	 */
	public void setViewResult(List viewResult) {
		this.viewResult = viewResult;
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
