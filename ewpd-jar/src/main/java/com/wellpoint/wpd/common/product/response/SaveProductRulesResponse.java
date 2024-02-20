/*
 * SaveProductRulesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductRulesResponse extends WPDResponse {
	 private boolean valid;
	 private boolean success;	
     private java.util.List validateRulesList;

//	--------------------------------- getters/setters -----------------------	
	/**
	 * Returns the success
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Sets the success
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * Returns the valid
	 * @return boolean valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * Sets the valid
	 * @param valid.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	/**
	 * Returns the validateRulesList
	 * @return java.util.List validateRulesList.
	 */
	public java.util.List getValidateRulesList() {
		return validateRulesList;
	}
	/**
	 * Sets the validateRulesList
	 * @param validateRulesList.
	 */
	public void setValidateRulesList(java.util.List validateRulesList) {
		this.validateRulesList = validateRulesList;
	}
}
