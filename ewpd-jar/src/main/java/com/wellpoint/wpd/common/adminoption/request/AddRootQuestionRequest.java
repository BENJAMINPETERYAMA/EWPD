/*
 * AddRootQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.request;

import java.util.List;

import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AddRootQuestionRequest extends WPDRequest{
	private List rootQuestionList;
	
	private AdminOptionBO adminOptionBO;
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
	 /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }
	/**
	 * @return Returns the adminOptionBO.
	 */
	public AdminOptionBO getAdminOptionBO() {
		return adminOptionBO;
	}
	/**
	 * @param adminOptionBO The adminOptionBO to set.
	 */
	public void setAdminOptionBO(AdminOptionBO adminOptionBO) {
		this.adminOptionBO = adminOptionBO;
	}
}
