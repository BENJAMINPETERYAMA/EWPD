/*
 * Created on Jun 26, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminOption implements Serializable{
	
	private List questions;//List of questions
	
	private String adminLevel;
	
	private String adminOptionName;
	
	
	/**
	 * @return Returns the adminLevel.
	 */
	public String getAdminLevel() {
		return adminLevel;
	}
	/**
	 * @param adminLevel The adminLevel to set.
	 */
	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}
	/**
	 * @return Returns the questions.
	 */
	public List getQuestions() {
		return questions;
	}
	/**
	 * @param questions The questions to set.
	 */
	public void setQuestions(List questions) {
		this.questions = questions;
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
}
