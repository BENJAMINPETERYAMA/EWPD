/*
 * BenefitComponentAttachNotesRequest.java
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
public class ContractBenefitComponentAttachNotesRequest extends ContractRequest  {
    
    private List notes;
    
    private int action;
    
    private int benefitComponentId;
    
    public static final int SAVE_NOTE = 1;
	public static final int OVERRIDE_NOTE = 2;
    
   
	 private String overrideStatus;
	 private List versionList;
	 
	 
    /**
     * Returns the overrideStatus
     * @return String overrideStatus.
     */
    public String getOverrideStatus() {
        return overrideStatus;
    }
    /**
     * Sets the overrideStatus
     * @param overrideStatus.
     */
    public void setOverrideStatus(String overrideStatus) {
        this.overrideStatus = overrideStatus;
    }
    /**
     * Returns the versionList
     * @return List versionList.
     */
    public List getVersionList() {
        return versionList;
    }
    /**
     * Sets the versionList
     * @param versionList.
     */
    public void setVersionList(List versionList) {
        this.versionList = versionList;
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
     * Returns the notes
     * @return List notes.
     */
    public List getNotes() {
        return notes;
    }
    /**
     * Sets the notes
     * @param notes.
     */
    public void setNotes(List notes) {
        this.notes = notes;
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
    public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

    
    
}
