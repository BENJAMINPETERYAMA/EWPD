/*
 * RetrieveRootQuestionResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * Response for retrieving Parent Question Details
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveRootQuestionnaireResponse extends WPDResponse{
	
	private RootQuestionnaireBO rootQuestionnaireBO = null;
	/**
	 * @return Returns the rootQuestionnaireBO.
	 */
	public RootQuestionnaireBO getRootQuestionnaireBO() {
		return rootQuestionnaireBO;
	}
	/**
	 * @param rootQuestionnaireBO The rootQuestionnaireBO to set.
	 */
	public void setRootQuestionnaireBO(RootQuestionnaireBO rootQuestionnaireBO) {
		this.rootQuestionnaireBO = rootQuestionnaireBO;
	}
}
