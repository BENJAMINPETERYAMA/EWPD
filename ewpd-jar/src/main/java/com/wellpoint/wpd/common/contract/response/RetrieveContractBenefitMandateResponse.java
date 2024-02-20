/*
 * ContractNonAdjBenefitMandateResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractBenefitMandateResponse extends ContractResponse {
	
	private List nonAdjMandateList = null;
	

	/**
	 * Returns the nonAdjMandateList
	 * @return List nonAdjMandateList.
	 */
	public List getNonAdjMandateList() {
		return nonAdjMandateList;
	}
	/**
	 * Sets the nonAdjMandateList
	 * @param nonAdjMandateList.
	 */
	public void setNonAdjMandateList(List nonAdjMandateList) {
		this.nonAdjMandateList = nonAdjMandateList;
	}
}
