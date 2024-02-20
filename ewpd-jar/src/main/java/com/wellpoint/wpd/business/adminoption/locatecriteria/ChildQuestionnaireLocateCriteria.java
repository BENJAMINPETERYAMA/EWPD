/*
 * ChildQuestionnaireLocateCriteria.java
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
 * Locate Criteria class for Child Questions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ChildQuestionnaireLocateCriteria extends LocateCriteria{
	
	private int parentQuestionnaireSysId = 0;
	
	private String action = null;
	
	private int adminOptionId = 0;

	/**
	 * @return Returns the parentQuestionnaireSysId.
	 */
	public int getParentQuestionnaireSysId() {
		return parentQuestionnaireSysId;
	}
	/**
	 * @param parentQuestionnaireSysId The parentQuestionnaireSysId to set.
	 */
	public void setParentQuestionnaireSysId(int parentQuestionnaireSysId) {
		this.parentQuestionnaireSysId = parentQuestionnaireSysId;
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
}
