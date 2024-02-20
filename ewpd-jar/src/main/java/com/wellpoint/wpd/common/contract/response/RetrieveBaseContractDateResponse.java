/*
 * RetrieveBaseContractDateResponse.java
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
public class RetrieveBaseContractDateResponse extends ContractResponse {
	
	private List baseContractDateList;
	
	
	/**
	 * Returns the baseContractDateList
	 * @return List baseContractDateList.
	 */
	public List getBaseContractDateList() {
		return baseContractDateList;
	}
	/**
	 * Sets the baseContractDateList
	 * @param baseContractDateList.
	 */
	public void setBaseContractDateList(List baseContractDateList) {
		this.baseContractDateList = baseContractDateList;
	}
}
