/*
 * RetrieveProdStructureComponentHierarchyResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or 
 * use Confidential Information without the express written
 *  agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProdStructureComponentHierarchyResponse extends WPDResponse{
	
	private List componentHierarchyList; 
	
	/**
	 * @return Returns the componentHierarchyList.
	 */
	public List getComponentHierarchyList() {
		return componentHierarchyList;
	}
	/**
	 * @param componentHierarchyList The componentHierarchyList to set.
	 */
	public void setComponentHierarchyList(List componentHierarchyList) {
		this.componentHierarchyList = componentHierarchyList;
	}
}

