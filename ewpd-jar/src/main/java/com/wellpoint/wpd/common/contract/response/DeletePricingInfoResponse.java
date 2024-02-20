/*
 * DeletePricingInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeletePricingInfoResponse extends ContractResponse {
	private java.util.List pricingInformationList;
	  
//		---------------------------------getters/setters-----------------------
	/**
	 * Returns the pricingInformationList
	 * @return java.util.List pricingInformationList.
	 */
	public java.util.List getPricingInformationList() {
		return pricingInformationList;
	}
	/**
	 * Sets the pricingInformationList
	 * @param pricingInformationList.
	 */
	public void setPricingInformationList(java.util.List pricingInformationList) {
		this.pricingInformationList = pricingInformationList;
	}
}
