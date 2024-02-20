/*
 * DeletePricingInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeletePricingInfoRequest  extends ContractRequest {
	
	private List contractCoverage;

	private List contractPricing;

	private List contractNetwork;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

//	--------------------------------------------------- Setter/Getter -----------------------------------------  

	/**
	 * @return Returns the contractCoverage.
	 */
	public List getContractCoverage() {
		return contractCoverage;
	}
	/**
	 * @param contractCoverage The contractCoverage to set.
	 */
	public void setContractCoverage(List contractCoverage) {
		this.contractCoverage = contractCoverage;
	}
	/**
	 * @return Returns the contractNetwork.
	 */
	public List getContractNetwork() {
		return contractNetwork;
	}
	/**
	 * @param contractNetwork The contractNetwork to set.
	 */
	public void setContractNetwork(List contractNetwork) {
		this.contractNetwork = contractNetwork;
	}
	/**
	 * @return Returns the contractPricing.
	 */
	public List getContractPricing() {
		return contractPricing;
	}
	/**
	 * @param contractPricing The contractPricing to set.
	 */
	public void setContractPricing(List contractPricing) {
		this.contractPricing = contractPricing;
	}
}
