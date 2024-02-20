/*
 * RetrieveDateSegmentCommentResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.contract.bo.Contract;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveDateSegmentCommentResponse   extends ContractResponse{
	
	private Contract contract;
	
	

	/**
	 * @return Returns the contract.
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param contract The contract to set.
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
