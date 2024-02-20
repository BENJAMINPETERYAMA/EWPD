/*
 * LocateRootQuestionResponse.java
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
public class LocateRootQuestionResponse extends WPDResponse{

	private List rootQuestionsList;
	
	/**
	 * @return Returns the rootQuestionsList.
	 */
	public List getRootQuestionsList() {
		return rootQuestionsList;
	}
	/**
	 * @param rootQuestionsList The rootQuestionsList to set.
	 */
	public void setRootQuestionsList(List rootQuestionsList) {
		this.rootQuestionsList = rootQuestionsList;
	}
}
