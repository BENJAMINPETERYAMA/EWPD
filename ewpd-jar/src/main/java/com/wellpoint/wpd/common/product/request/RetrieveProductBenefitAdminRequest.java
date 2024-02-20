/*
 * RetrieveProductBenefitAdminRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductBenefitAdminRequest extends ProductRequest {

	
	private int productBenefitAdminId;
	private int productAdminOptionId;
	private int benefitComponentId;
	
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	 private int action;
	
	  /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    }

	
	/**
	 * Returns the productBenefitAdminId
	 * @return int productBenefitAdminId.
	 */
	public int getProductBenefitAdminId() {
		return productBenefitAdminId;
	}
	/**
	 * Sets the productBenefitAdminId
	 * @param productBenefitAdminId.
	 */
	public void setProductBenefitAdminId(int productBenefitAdminId) {
		this.productBenefitAdminId = productBenefitAdminId;
	}
	/**
	 * Returns the productAdminOptionId
	 * @return int productAdminOptionId.
	 */
	public int getProductAdminOptionId() {
		return productAdminOptionId;
	}
	/**
	 * Sets the productAdminOptionId
	 * @param productAdminOptionId.
	 */
	public void setProductAdminOptionId(int productAdminOptionId) {
		this.productAdminOptionId = productAdminOptionId;
	}
	/**
	 * Returns the benefitComponentId
	 * @return int benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * Sets the benefitComponentId
	 * @param benefitComponentId.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
}
