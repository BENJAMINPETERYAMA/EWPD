/*
 * Created on Nov 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;


import java.util.List;

import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HideAdminOptionRequest extends WPDRequest{
	
	public void validate(){
		
	}
	
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
