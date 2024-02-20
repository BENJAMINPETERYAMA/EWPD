/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.product.bo.AdminBO;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminAttachmentResponse extends WPDResponse {

	private List adminList;
	
	private AdminBO adminBO;
	
	
	/**
	 * @return Returns the adminBO.
	 */
	public AdminBO getAdminBO() {
		return adminBO;
	}
	/**
	 * @param adminBO The adminBO to set.
	 */
	public void setAdminBO(AdminBO adminBO) {
		this.adminBO = adminBO;
	}
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
}
