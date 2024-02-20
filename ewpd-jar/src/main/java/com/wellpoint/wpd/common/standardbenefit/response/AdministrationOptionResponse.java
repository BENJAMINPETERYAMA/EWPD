/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;


/**
 * @author u14617
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdministrationOptionResponse extends WPDResponse{
	
	private AdministrationOptionBO administrationOptionBO;
	
	private List adminOptionList;
	
	/**
	 * @return Returns the administrationOptionBO.
	 */
	public AdministrationOptionBO getAdministrationOptionBO() {
		return administrationOptionBO;
	}
	/**
	 * @param administrationOptionBO The administrationOptionBO to set.
	 */
	public void setAdministrationOptionBO(
			AdministrationOptionBO administrationOptionBO) {
		this.administrationOptionBO = administrationOptionBO;
	}
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
