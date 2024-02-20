/*
 * CreateDateSegmentResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateDateSegmentResponse extends WPDResponse {
    private Contract contract;
	 private boolean success;
	 private int dateSegmentSysId;
	 private boolean isLockAcquired = true;
	 private boolean valid;
	 
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
	 * @return Returns the dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * @param dateSegmentSysId The dateSegmentSysId to set.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
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
	/**
	 * @return Returns the isLockAcquired.
	 */
	public boolean isLockAcquired() {
		return isLockAcquired;
	}
	/**
	 * @param isLockAcquired The isLockAcquired to set.
	 */
	public void setLockAcquired(boolean isLockAcquired) {
		this.isLockAcquired = isLockAcquired;
	}
	/**
	 * @return Returns the valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * @param valid The valid to set.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
