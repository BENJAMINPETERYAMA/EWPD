/*
 * SaveReservedContractResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveReservedContractResponse extends WPDResponse {

	
	private boolean success;
	
	private List contractList;
	
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * @return Returns the contractList.
	 */
	public List getContractList() {
		return contractList;
	}
	/**
	 * @param contractList The contractList to set.
	 */
	public void setContractList(List contractList) {
		this.contractList = contractList;
	}
}
