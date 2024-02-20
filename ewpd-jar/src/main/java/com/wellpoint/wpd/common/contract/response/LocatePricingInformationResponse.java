/*
 * LocatePricingInformationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.Contract;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocatePricingInformationResponse extends ContractResponse {
	
	private List pricingInformationList;
	private Contract contract;

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
	 * Returns the contract
	 * @return Contract contract.
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * Sets the contract
	 * @param contract.
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
