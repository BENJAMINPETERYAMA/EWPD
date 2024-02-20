/*
 * DeleteNotesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteNotesRequest extends WPDRequest{
    
    private String notesId;
    private String notesName;
    private int versionNumber;
    private String domainId;
    private String domainType;
    private boolean dataDomainDelete;
    private String state;
    
    private List noteDomains;

	/**
	 * @return Returns the domainId.
	 */
	public String getDomainId() {
		return domainId;
	}
	/**
	 * @param domainId The domainId to set.
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	/**
	 * @return Returns the domainType.
	 */
	public String getDomainType() {
		return domainType;
	}
	/**
	 * @param domainType The domainType to set.
	 */
	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}
    /**
     * @return Returns the versionNumber.
     */
    public int getVersionNumber() {
        return versionNumber;
    }
    /**
     * @param versionNumber The versionNumber to set.
     */
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }
    /**
     * @return Returns the notesName.
     */
    public String getNotesName() {
        return notesName;
    }
    /**
     * @param notesName The notesName to set.
     */
    public void setNotesName(String notesName) {
        this.notesName = notesName;
    }
    
	/**
	 * @return Returns the notesId.
	 */
	public String getNotesId() {
		return notesId;
	}
	/**
	 * @param notesId The notesId to set.
	 */
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

	/**
	 * @return Returns the dataDomainDelete.
	 */
	public boolean isDataDomainDelete() {
		return dataDomainDelete;
	}
	/**
	 * @param dataDomainDelete The dataDomainDelete to set.
	 */
	public void setDataDomainDelete(boolean dataDomainDelete) {
		this.dataDomainDelete = dataDomainDelete;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the noteDomains.
	 */
	public List getNoteDomains() {
		return noteDomains;
	}
	/**
	 * @param noteDomains The noteDomains to set.
	 */
	public void setNoteDomains(List noteDomains) {
		this.noteDomains = noteDomains;
	}
}
