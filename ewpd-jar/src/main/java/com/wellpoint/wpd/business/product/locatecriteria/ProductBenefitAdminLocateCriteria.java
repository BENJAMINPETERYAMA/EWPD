/*
 * ProductBenefitAdminLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitAdminLocateCriteria extends LocateCriteria {
	
	
	private int productId;
	private int benefitAdminId;
	private int adminOptionId;
	private int benefitComponentId;
	

	/**
	 * Returns the adminOptionId
	 * @return int adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * Sets the adminOptionId
	 * @param adminOptionId.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
	/**
	 * Returns the benefitAdminId
	 * @return int benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * Sets the benefitAdminId
	 * @param benefitAdminId.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
	}
	/**
	 * Returns the productId
	 * @return int productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * Sets the productId
	 * @param productId.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
