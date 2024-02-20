/*
 * RetrieveReservedContractIdResponse.java
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
public class RetrieveReservedContractIdResponse extends ContractResponse{
	
	private List ReservedContractIds;
	

	/**
	 * Returns the reservedContractIds
	 * @return List reservedContractIds.
	 */
	public List getReservedContractIds() {
		return ReservedContractIds;
	}
	/**
	 * Sets the reservedContractIds
	 * @param reservedContractIds.
	 */
	public void setReservedContractIds(List reservedContractIds) {
		ReservedContractIds = reservedContractIds;
	}
}
