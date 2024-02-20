/*
 * LoadContractProductComponentNoteRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LoadContractComponentNoteRequest extends ContractRequest {

	private int benefitComponentId;
	private List noteList;
	private int action;
	private List benefitComponentList;
	
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

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
    /**
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * Returns the noteList
     * @return List noteList.
     */
    public List getNoteList() {
        return noteList;
    }
    /**
     * Sets the noteList
     * @param noteList.
     */
    public void setNoteList(List noteList) {
        this.noteList = noteList;
    }
	/**
	 * @return Returns the benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}
	/**
	 * @param benefitComponentList The benefitComponentList to set.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
}
