/*
 * Created on Mar 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.request;


import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;



/**
 * @author U17810
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminOptionQuestionnaireRequest extends WPDRequest {
	
	private String adminOptionName = null;
	
	private int adminOptionVersion = 0;
	
	private int adminOptionId = 0;
	
	private boolean searchChilds = false;

	private List childQuestions;		
	
	private Map questionnaireIdPRMap;

	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return Returns the childQuestions.
	 */
	public List getChildQuestions() {
		return childQuestions;
	}
	/**
	 * @param childQuestions The childQuestions to set.
	 */
	public void setChildQuestions(List childQuestions) {
		this.childQuestions = childQuestions;
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
	 * @return Returns the searchChilds.
	 */
	public boolean isSearchChilds() {
		return searchChilds;
	}
	/**
	 * @param searchChilds The searchChilds to set.
	 */
	public void setSearchChilds(boolean searchChilds) {
		this.searchChilds = searchChilds;
	}

	/**
	 * @return Returns the questionnaireIdPRMap.
	 */
	public Map getQuestionnaireIdPRMap() {
		return questionnaireIdPRMap;
	}
	/**
	 * @param questionnaireIdPRMap The questionnaireIdPRMap to set.
	 */
	public void setQuestionnaireIdPRMap(Map questionnaireIdPRMap) {
		this.questionnaireIdPRMap = questionnaireIdPRMap;
	}
}
