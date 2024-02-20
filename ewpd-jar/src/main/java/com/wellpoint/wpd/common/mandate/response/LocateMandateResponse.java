/*
 * LocateMandateResponse.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.mandate.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateMandateResponse extends WPDResponse {
	
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
