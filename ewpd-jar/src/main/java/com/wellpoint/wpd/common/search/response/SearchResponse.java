/*
 * SearchResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.search.result.SearchResult;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchResponse.java 24880 2007-06-22 05:49:55Z u12046 $
 */
public class SearchResponse extends WPDResponse {
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
	
	public boolean addSearchResult(SearchResult result){
	    if(result != null){
	        if(searchResults == null) searchResults = new ArrayList();
	        searchResults.add(result);
	        return true;
	    }
	    return false;
	}
}
