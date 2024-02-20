/*
 * RetrieveRootQuestionRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request for Retreiveing Parent Question Details
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveRootQuestionnaireRequest extends WPDRequest{
	
	private int questionnaireHierarchySystemId = 0;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the questionnaireHierarchySystemId.
	 */
	public int getQuestionnaireHierarchySystemId() {
		return questionnaireHierarchySystemId;
	}
	/**
	 * @param questionnaireHierarchySystemId The questionnaireHierarchySystemId to set.
	 */
	public void setQuestionnaireHierarchySystemId(
			int questionnaireHierarchySystemId) {
		this.questionnaireHierarchySystemId = questionnaireHierarchySystemId;
	}
}
