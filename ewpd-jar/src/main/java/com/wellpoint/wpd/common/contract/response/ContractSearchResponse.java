/*
 * ContractSearchResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchResponse extends WPDResponse{
	
	private List searchResultList;
	
	 private boolean success;

	/**
	 * Returns the searchResultList
	 * @return List searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * Sets the searchResultList
	 * @param searchResultList.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
