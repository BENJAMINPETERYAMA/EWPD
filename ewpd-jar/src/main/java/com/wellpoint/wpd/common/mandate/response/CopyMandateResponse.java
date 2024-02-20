/*
 * Created on Mar 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.mandate.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CopyMandateResponse extends WPDResponse {
	
	private List mandateSearchResultList;
	
	

	/**
	 * Returns the mandateSearchResultList
	 * @return List mandateSearchResultList.
	 */
	public List getMandateSearchResultList() {
		return mandateSearchResultList;
	}
	/**
	 * Sets the mandateSearchResultList
	 * @param mandateSearchResultList.
	 */
	public void setMandateSearchResultList(List mandateSearchResultList) {
		this.mandateSearchResultList = mandateSearchResultList;
	}
}
