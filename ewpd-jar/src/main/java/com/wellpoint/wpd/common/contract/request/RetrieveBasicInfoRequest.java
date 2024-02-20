/*
 * RetrieveBasicInfoRequest.java
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
public class RetrieveBasicInfoRequest extends ContractRequest{
    
    private int contractId;
    
    private int dateSegmentId;
    
    private int contractSysId;
    
    private boolean editMode;
    
    private boolean migCompletFlag;
    
    private int action;
    /**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
    
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
     * Returns the contractSysId
     * @return int contractSysId.
     */
    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */
    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
	/**
	 * Returns the migCompletFlag
	 * @return boolean migCompletFlag.
	 */
	public boolean isMigCompletFlag() {
		return migCompletFlag;
	}
	/**
	 * Sets the migCompletFlag
	 * @param migCompletFlag.
	 */
	public void setMigCompletFlag(boolean migCompletFlag) {
		this.migCompletFlag = migCompletFlag;
	}
	/**
	 * @return Returns the editMode.
	 */
	public boolean isEditMode() {
		return editMode;
	}
	/**
	 * @param editMode The editMode to set.
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
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
