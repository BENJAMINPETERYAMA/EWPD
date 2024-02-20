/*
 * Created on Jun 30, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Message implements Serializable{
	private String ruleCategoryName;
	private String ruleCategoryDescription;
	private String validationMessage;
	

	/**
	 * @return Returns the ruleCategoryDescription.
	 */
	public String getRuleCategoryDescription() {
		return ruleCategoryDescription;
	}
	/**
	 * @param ruleCategoryDescription The ruleCategoryDescription to set.
	 */
	public void setRuleCategoryDescription(String ruleCategoryDescription) {
		this.ruleCategoryDescription = ruleCategoryDescription;
	}
	/**
	 * @return Returns the ruleCategoryName.
	 */
	public String getRuleCategoryName() {
		return ruleCategoryName;
	}
	/**
	 * @param ruleCategoryName The ruleCategoryName to set.
	 */
	public void setRuleCategoryName(String ruleCategoryName) {
		this.ruleCategoryName = ruleCategoryName;
	}
	/**
	 * @return Returns the validationMessage.
	 */
	public String getValidationMessage() {
		return validationMessage;
	}
	/**
	 * @param validationMessage The validationMessage to set.
	 */
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
}
