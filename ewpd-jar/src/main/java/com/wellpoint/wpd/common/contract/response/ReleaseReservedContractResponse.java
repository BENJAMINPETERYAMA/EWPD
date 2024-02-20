/*
 * ReleaseReservedContractResponse.java
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
public class ReleaseReservedContractResponse extends WPDResponse {

	public static final int RETRIEVE_RESERVED_CONTRACT = 1;
	public static final int RELEASE_RESERVED_CONTRACT = 2;
	private boolean success;
	private List contractIdList;
	private int action; 
	
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
	 * @return Returns the contractIdList.
	 */
	public List getContractIdList() {
		return contractIdList;
	}
	/**
	 * @param contractIdList The contractIdList to set.
	 */
	public void setContractIdList(List contractIdList) {
		this.contractIdList = contractIdList;
	}
	
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
}
