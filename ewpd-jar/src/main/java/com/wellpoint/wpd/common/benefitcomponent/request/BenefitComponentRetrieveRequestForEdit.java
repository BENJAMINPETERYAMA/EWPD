/*
 * BenefitComponentRetrieveRequestForEdit.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

import java.util.List;


/**
 * Request for Retrieve Benefit Componet to Edit
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentRetrieveRequestForEdit extends WPDRequest{
    
    // variable declarations
    private int retrieveKey;
    private String retrieveName;
    private int versionNumber;
    private List domainList;
    private boolean editMode;

    /**
     * @return Returns the retrieveName.
     */
    public String getRetrieveName() {
        return retrieveName;
    }
    /**
     * @param retrieveName The retrieveName to set.
     */
    public void setRetrieveName(String retrieveName) {
        this.retrieveName = retrieveName;
    }
    /**
     * @return Returns the retrieveKey.
     */
    public int getRetrieveKey() {
        return retrieveKey;
    }
    /**
     * @param retrieveKey The retrieveKey to set.
     */
    public void setRetrieveKey(int retrieveKey) {
        this.retrieveKey = retrieveKey;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

    /**
     * Returns the versionNumber
     * @return int versionNumber.
     */
    public int getVersionNumber() {
        return versionNumber;
    }
    /**
     * Sets the versionNumber
     * @param versionNumber.
     */
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }
    
    
	/**
	 * @return Returns the domainList.
	 */
	public List getDomainList() {
		return domainList;
	}
	/**
	 * @param domainList The domainList to set.
	 */
	public void setDomainList(List domainList) {
		this.domainList = domainList;
	}
	/**
	 * @return editMode
	 * 
	 * Returns the editMode.
	 */
	public boolean isEditMode() {
		return editMode;
	}
	/**
	 * @param editMode
	 * 
	 * Sets the editMode.
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}
