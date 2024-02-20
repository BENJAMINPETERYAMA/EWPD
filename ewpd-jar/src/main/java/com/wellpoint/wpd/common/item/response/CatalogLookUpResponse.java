/*
 * ItemSearchResponse.java
 *  © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.item.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

public class CatalogLookUpResponse extends WPDResponse{
	private List catalogList;

	/**
	 * @param catalogList The catalogList to set.
	 */
	public void setCatalogList(List catalogList) {
		this.catalogList = catalogList;
	}

	/**
	 * @return Returns the catalogList.
	 */
	public List getCatalogList() {
		return catalogList;
	}

}
