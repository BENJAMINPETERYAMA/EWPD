/*
 * LookupAdminQuestionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.lookup.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupAdminQuestionResponse extends WPDResponse{
	
	private List adminOptionQuestionList;
	
	

	/**
	 * Returns the adminOptionQuestionList
	 * @return List adminOptionQuestionList.
	 */
	public List getAdminOptionQuestionList() {
		return adminOptionQuestionList;
	}
	/**
	 * Sets the adminOptionQuestionList
	 * @param adminOptionQuestionList.
	 */
	public void setAdminOptionQuestionList(List adminOptionQuestionList) {
		this.adminOptionQuestionList = adminOptionQuestionList;
	}
}
