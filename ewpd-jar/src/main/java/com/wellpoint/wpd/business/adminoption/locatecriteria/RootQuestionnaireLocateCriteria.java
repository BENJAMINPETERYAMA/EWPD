/*
 * RootQuestionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.adminoption.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate Criteria class for Root Questionnaire
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RootQuestionnaireLocateCriteria extends LocateCriteria{
	
	private int questionnaireHierarchySystemId = 0;

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
