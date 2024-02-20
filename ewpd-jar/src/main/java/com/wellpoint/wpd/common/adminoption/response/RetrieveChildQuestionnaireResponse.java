/*
 * RetrieveChildQuestionnaireResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * Response for Retreiveing Child Question Details
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveChildQuestionnaireResponse extends WPDResponse{
	
	private List childQuestionnaires = null;

	/**
	 * @return Returns the childQuestionnaires.
	 */
	public List getChildQuestionnaires() {
		return childQuestionnaires;
	}
	/**
	 * @param childQuestionnaires The childQuestionnaires to set.
	 */
	public void setChildQuestionnaires(List childQuestionnaires) {
		this.childQuestionnaires = childQuestionnaires;
	}
}
