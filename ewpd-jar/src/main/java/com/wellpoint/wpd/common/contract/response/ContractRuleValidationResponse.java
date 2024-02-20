/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContractRuleValidationResponse extends WPDResponse {

	private List searchResults;

	private boolean valid;

	/**
	 * @return Returns the valid.
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid
	 *            The valid to set.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}

}