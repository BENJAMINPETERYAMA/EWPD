/*
 * DeleteCustomMessageResponse.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.blueexchange.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * DeleteCustomMessageResponse is the class for getting response from business layer 
 * 
 * @see this class extends (com.wellpoint.wpd.common.framework.response.WPDResponse)
 *
 * 
 */
public class DeleteCustomMessageResponse extends WPDResponse{
	
	private List searchResults;

	/**
	 * @return Returns the searchResults.
	 */
	public List getSearchResults() {
		return searchResults;
	}
	/**
	 * @param searchResults The searchResults to set.
	 */
	public void setSearchResults(List searchResults) {
		this.searchResults = searchResults;
	}
}
