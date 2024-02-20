/*
 * SaveProductBenefitAdminResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductBenefitAdminResponse extends WPDResponse {

	private boolean valid;
	private boolean success;
	private List benefitAdministrationList;
	
	
	/**
	 * Returns the benefitAdministrationList
	 * @return List benefitAdministrationList.
	 */
	public List getBenefitAdministrationList() {
		return benefitAdministrationList;
	}
	/**
	 * Sets the benefitAdministrationList
	 * @param benefitAdministrationList.
	 */
	public void setBenefitAdministrationList(List benefitAdministrationList) {
		this.benefitAdministrationList = benefitAdministrationList;
	}
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
}
