/*
 * PersistChildQuestionnaireRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 *//*
 * Created on Jun 30, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request for Persisting/Updating the Child Questionnaire.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PersistChildQuestionnaireRequest extends WPDRequest{
	
	private List childQuestionnaires = null;
	
	private boolean insertFlag = false;
	
	private String adminOptionName = null;
	
	private int adminOptionVersion = 0;
	
	private String questionnairesToDeleted = null;
	
	private int adminOptionId = 0;
	
	private int parentQuestionnaireId = 0;
	
	private boolean deleteFlag = false;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

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
	/**
	 * @return Returns the insertFlag.
	 */
	public boolean isInsertFlag() {
		return insertFlag;
	}
	/**
	 * @param insertFlag The insertFlag to set.
	 */
	public void setInsertFlag(boolean insertFlag) {
		this.insertFlag = insertFlag;
	}
	/**
	 * @return Returns the adminOptionName.
	 */
	public String getAdminOptionName() {
		return adminOptionName;
	}
	/**
	 * @param adminOptionName The adminOptionName to set.
	 */
	public void setAdminOptionName(String adminOptionName) {
		this.adminOptionName = adminOptionName;
	}
	/**
	 * @return Returns the adminOptionVersion.
	 */
	public int getAdminOptionVersion() {
		return adminOptionVersion;
	}
	/**
	 * @param adminOptionVersion The adminOptionVersion to set.
	 */
	public void setAdminOptionVersion(int adminOptionVersion) {
		this.adminOptionVersion = adminOptionVersion;
	}
	/**
	 * @return Returns the questionnairesToDeleted.
	 */
	public String getQuestionnairesToDeleted() {
		return questionnairesToDeleted;
	}
	/**
	 * @param questionnairesToDeleted The questionnairesToDeleted to set.
	 */
	public void setQuestionnairesToDeleted(String questionnairesToDeleted) {
		this.questionnairesToDeleted = questionnairesToDeleted;
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
	/**
	 * @return Returns the parentQuestionnaireId.
	 */
	public int getParentQuestionnaireId() {
		return parentQuestionnaireId;
	}
	/**
	 * @param parentQuestionnaireId The parentQuestionnaireId to set.
	 */
	public void setParentQuestionnaireId(int parentQuestionnaireId) {
		this.parentQuestionnaireId = parentQuestionnaireId;
	}
	/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
