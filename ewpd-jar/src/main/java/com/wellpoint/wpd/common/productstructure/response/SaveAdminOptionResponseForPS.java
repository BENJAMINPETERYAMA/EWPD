/*
 * Created on Dec 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminOptionResponseForPS extends WPDResponse{
	

	private List adminOptionList;
	
	


	/**
	 * @return Returns the adminOptionList.
	 */
	public List getAdminOptionList() {
		return adminOptionList;
	}
	/**
	 * @param adminOptionList The adminOptionList to set.
	 */
	public void setAdminOptionList(List adminOptionList) {
		this.adminOptionList = adminOptionList;
	}
}
