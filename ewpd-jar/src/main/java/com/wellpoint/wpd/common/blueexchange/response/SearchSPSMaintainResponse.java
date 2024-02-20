/*
 * SearchSPSMaintainResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchSPSMaintainResponse extends WPDResponse {
	
	private List searchList ;
	
	
	/**
	 * @return Returns the searchList.
	 */
	public List getSearchList() {
		return searchList;
	}
	/**
	 * @param searchList The searchList to set.
	 */
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}
}
