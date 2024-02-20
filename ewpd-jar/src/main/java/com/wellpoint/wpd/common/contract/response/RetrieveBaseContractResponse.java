/*
 * RetrieveBaseContractResponse.java
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
public class RetrieveBaseContractResponse extends ContractResponse{
	
	private List baseContractList;
	

	/**
	 * Returns the baseContyractList
	 * @return List baseContyractList.
	 */
	public List getBaseContractList() {
		return baseContractList;
	}
	/**
	 * Sets the baseContyractList
	 * @param baseContyractList.
	 */
	public void setBaseContractList(List baseContractList) {
		this.baseContractList = baseContractList;
	}
}
