/*
 * SavePricingInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SavePricingInfoRequest extends ContractRequest
{
    public static final int SAVE_ADD_PRICING_INFO = 1;

	private int action;

	private String contractCoverage;

	private String contractPricing;

	private String contractNetwork;
  //sscr 17571
    
    private String carvedConfirm;

    
	public String getCarvedConfirm() {
		return carvedConfirm;
	}

	public void setCarvedConfirm(String carvedConfirm) {
		this.carvedConfirm = carvedConfirm;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
//	--------------------------------------------------- Setter/Getter -----------------------------------------  
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * Returns the contractCoverage
	 * @return String contractCoverage.
	 */
	public String getContractCoverage() {
		return contractCoverage;
	}
	/**
	 * Sets the contractCoverage
	 * @param contractCoverage.
	 */
	public void setContractCoverage(String contractCoverage) {
		this.contractCoverage = contractCoverage;
	}
	/**
	 * Returns the contractNetwork
	 * @return String contractNetwork.
	 */
	public String getContractNetwork() {
		return contractNetwork;
	}
	/**
	 * Sets the contractNetwork
	 * @param contractNetwork.
	 */
	public void setContractNetwork(String contractNetwork) {
		this.contractNetwork = contractNetwork;
	}
	/**
	 * Returns the contractPricing
	 * @return String contractPricing.
	 */
	public String getContractPricing() {
		return contractPricing;
	}
	/**
	 * Sets the contractPricing
	 * @param contractPricing.
	 */
	public void setContractPricing(String contractPricing) {
		this.contractPricing = contractPricing;
	}
}
