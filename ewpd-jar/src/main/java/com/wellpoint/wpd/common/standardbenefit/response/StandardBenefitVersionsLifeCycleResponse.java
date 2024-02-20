/*
 * Created on Apr 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;


/**
 * @author U11648
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitVersionsLifeCycleResponse extends
		StandardBenefitResponse {
    private List searchResultList;
	
	/**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
}
