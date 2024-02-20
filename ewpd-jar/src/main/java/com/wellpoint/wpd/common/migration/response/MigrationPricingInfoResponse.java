/*
 * LocateMigrationPricingInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;

import java.util.List;
	

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationPricingInfoResponse extends MigrationContractResponse{

	private List pricingInformationList;
    protected boolean valid;
    protected boolean success;
	/**
	 * Returns the pricingInformationList
	 * @return List pricingInformationList.
	 */
	public List getPricingInformationList() {
		return pricingInformationList;
	}
	/**
	 * Sets the pricingInformationList
	 * @param pricingInformationList.
	 */
	public void setPricingInformationList(List pricingInformationList) {
		this.pricingInformationList = pricingInformationList;
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
