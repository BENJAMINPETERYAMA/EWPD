/*
 * RetrieveRulesResponse.java
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
public class RetrieveRulesResponse  extends ContractResponse {

	private boolean success;
	private List searchResultList;
	
	/**
	 * 
	 */
	public RetrieveRulesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

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
