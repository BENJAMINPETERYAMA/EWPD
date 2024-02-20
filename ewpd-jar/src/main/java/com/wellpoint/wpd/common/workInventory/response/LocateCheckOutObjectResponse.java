/*
 * LocateCheckOutObjectResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.workInventory.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: LocateCheckOutObjectResponse.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class LocateCheckOutObjectResponse extends WPDResponse {

	private List businessObjectList;

	/**
	 * @return Returns the businessObjectList.
	 */
	public List getBusinessObjectList() {
		return businessObjectList;
	}
	/**
	 * @param businessObjectList The businessObjectList to set.
	 */
	public void setBusinessObjectList(List businessObjectList) {
		this.businessObjectList = businessObjectList;
	}
}