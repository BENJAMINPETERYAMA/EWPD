/*
 * AddRootQuestionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AddRootQuestionResponse extends WPDResponse{
	private List rootQuestionList;
	/**
	 * @return Returns the rootQuestionList.
	 */
	public List getRootQuestionList() {
		return rootQuestionList;
	}
	/**
	 * @param rootQuestionList The rootQuestionList to set.
	 */
	public void setRootQuestionList(List rootQuestionList) {
		this.rootQuestionList = rootQuestionList;
	}
}
