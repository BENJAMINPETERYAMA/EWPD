/*
 * AdminOptionSearchBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */

package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionBOImpl;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * Backing bean for admin options search.
 * 
 * This bean will bind with the jap pages.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionSearchBackingBean extends WPDBackingBean {
	
	private String adminNameCriteria;
	
	private String criteriaTerm;
	
	private String criteriaQualifier;

	private List adminOptionSearchResultList;

	private List validationMessages = null;

	private boolean requiredAdminName = false;

	/**
	 * @return Returns the adminNameCriteria.
	 */
	public String getAdminNameCriteria() {
		return adminNameCriteria;
	}

	/**
	 * @param adminNameCriteria
	 *            The adminNameCriteria to set.
	 */
	public void setAdminNameCriteria(String adminNameCriteria) {
		this.adminNameCriteria = adminNameCriteria;
	}

	/**
	 * @return Returns the adminOptionSearchResultList.
	 */
	public List getAdminOptionSearchResultList() {
	
		if(this.adminOptionSearchResultList != null && this.adminOptionSearchResultList.size() == 0)
			return null;
		
		return adminOptionSearchResultList;
	}

	/**
	 * @param adminOptionSearchResultList
	 *            The adminOptionSearchResultList to set.
	 */
	public void setAdminOptionSearchResultList(List adminOptionSearchResultList) {
		this.adminOptionSearchResultList = adminOptionSearchResultList;
	}

	public String editAdminOption() {
		return "";
	}

	public String deleteAdminOption() {
		return "";
	}
	
	
	/**
	 * Methos to Search Admin Option
	 * @return Srting for page navigation
	 */
	public String search() {
		if (!validateField()) {

			addAllMessagesToRequest(validationMessages);
			
		}
		else{
			adminOptionSearchResultList = new ArrayList(2);
			AdminOptionBO adminOption1 = new AdminOptionBOImpl();
			AdminOptionBO adminOption2 = new AdminOptionBOImpl();

			adminOption1.setAdminName("Admin Name1");
			adminOption1.setTerm("Copay");
			adminOption1.setQualifier("Boolean");

			adminOption2.setAdminName("Admin Name2");
			adminOption2.setTerm("Deductible");
			adminOption2.setQualifier("Indvidual");

			adminOptionSearchResultList.add(adminOption1);
			adminOptionSearchResultList.add(adminOption2);
			
		}
		return "";
	}

	/**
	 * @return Returns the requiredAdminName.
	 */
	public boolean isRequiredAdminName() {
		return requiredAdminName;
	}

	/**
	 * @param requiredAdminName
	 *            The requiredAdminName to set.
	 */
	public void setRequiredAdminName(boolean requiredAdminName) {
		this.requiredAdminName = requiredAdminName;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * 
	 * Method to Validate the Required field
	 * @return boolean
	 */
	private boolean validateField() {

		validationMessages = new ArrayList(1);

		boolean requiredField = false;

		if (this.getAdminNameCriteria() == null
				|| this.getAdminNameCriteria().trim().equals("")) {

			requiredAdminName = true;

			requiredField = true;

		}

		if (requiredField) {

			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));

			return false;

		}

		return true;

	}
	
	/**
	 * @return Returns the criteriaQualifier.
	 */
	public String getCriteriaQualifier() {
		return criteriaQualifier;
	}
	
	/**
	 * @param criteriaQualifier The criteriaQualifier to set.
	 */
	public void setCriteriaQualifier(String criteriaQualifier) {
		this.criteriaQualifier = criteriaQualifier;
	}
	
	/**
	 * @return Returns the criteriaTerm.
	 */
	public String getCriteriaTerm() {
		return criteriaTerm;
	}
	
	/**
	 * @param criteriaTerm The criteriaTerm to set.
	 */
	public void setCriteriaTerm(String criteriaTerm) {
		this.criteriaTerm = criteriaTerm;
	}
	
}