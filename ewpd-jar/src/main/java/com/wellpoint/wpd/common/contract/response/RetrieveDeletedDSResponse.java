/*
 * RetrieveDeletedDSResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveDeletedDSResponse extends ContractResponse {

	private List SearchResultsList;

	/**
	 * @return Returns the searchResultsList.
	 */
	public List getSearchResultsList() {
		return SearchResultsList;
	}

	/**
	 * @param searchResultsList
	 *            The searchResultsList to set.
	 */
	public void setSearchResultsList(List searchResultsList) {
		SearchResultsList = searchResultsList;
	}
}