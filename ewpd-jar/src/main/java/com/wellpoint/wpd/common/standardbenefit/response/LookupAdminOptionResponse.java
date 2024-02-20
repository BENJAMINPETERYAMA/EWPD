/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LookupAdminOptionResponse extends WPDResponse {
	
	private List lookupAdminOption;
	

	/**
	 * @return Returns the lookupAdminOption.
	 */
	public List getLookupAdminOption() {
		return lookupAdminOption;
	}
	/**
	 * @param lookupAdminOption The lookupAdminOption to set.
	 */
	public void setLookupAdminOption(List lookupAdminOption) {
		this.lookupAdminOption = lookupAdminOption;
	}
}
