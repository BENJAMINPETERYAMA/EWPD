/*
 * DeleteContractRequest.java
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
public class DeleteContractRequest extends ContractRequest {

	public static final int SELECTED_DATE_SEGMENT = 1;
	public static final int ALL_DATE_SEGMENTS = 2;
	private int action;
	private int contractId; 
	private int contractDateSegmentSysId;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
//	--------------------------------- getters/setters -----------------------	
	/**
	 * Returns the contractId
	 * @return int contractId.
	 */
	public int getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the contractDateSegmentSysId
	 * @return int contractDateSegmentSysId.
	 */
	public int getContractDateSegmentSysId() {
		return contractDateSegmentSysId;
	}
	/**
	 * Sets the contractDateSegmentSysId
	 * @param contractDateSegmentSysId.
	 */
	public void setContractDateSegmentSysId(int contractDateSegmentSysId) {
		this.contractDateSegmentSysId = contractDateSegmentSysId;
	}
	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
}
