/*
 * DeleteBenefitComponentNotesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteBenefitComponentNotesRequest extends WPDRequest{
    
    private String benefitComponentNoteId;
    private List benefitComponentNotesIdsList;
    private int entityId;
    private int version;
    private String status;
    private List businessDomains;
    private String benefitComponentName;
    
    // For NotesOverride
    private int secEntityId;
    private String secEntityType;
    private int benefitComponentId;
    private int action;
    private int noteVersion;
    
    private List notesList;;
    
    //for multiple delete of notes
    //private List notedIds;
    
    /**
     * @return benefitComponentName
     * 
     * Returns the benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }
    /**
     * @param benefitComponentName
     * 
     * Sets the benefitComponentName.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
    /**
     * @return businessDomains
     * 
     * Returns the businessDomains.
     */
    public List getBusinessDomains() {
        return businessDomains;
    }
    /**
     * @param businessDomains
     * 
     * Sets the businessDomains.
     */
    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }
    /**
     * @return status
     * 
     * Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status
     * 
     * Sets the status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return version
     * 
     * Returns the version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version
     * 
     * Sets the version.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
	/**
	 * @return Returns the benefitComponentNoteId.
	 */
	public String getBenefitComponentNoteId() {
		return benefitComponentNoteId;
	}
	/**
	 * @param benefitComponentNoteId The benefitComponentNoteId to set.
	 */
	public void setBenefitComponentNoteId(String benefitComponentNoteId) {
		this.benefitComponentNoteId = benefitComponentNoteId;
	}
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

    /**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * @return action
     * 
     * Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action
     * 
     * Sets the action.
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     * @return benefitComponentId
     * 
     * Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId
     * 
     * Sets the benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * @return secEntityId
     * 
     * Returns the secEntityId.
     */
    public int getSecEntityId() {
        return secEntityId;
    }
    /**
     * @param secEntityId
     * 
     * Sets the secEntityId.
     */
    public void setSecEntityId(int secEntityId) {
        this.secEntityId = secEntityId;
    }
    /**
     * @return secEntityType
     * 
     * Returns the secEntityType.
     */
    public String getSecEntityType() {
        return secEntityType;
    }
    /**
     * @param secEntityType
     * 
     * Sets the secEntityType.
     */
    public void setSecEntityType(String secEntityType) {
        this.secEntityType = secEntityType;
    }
	/**
	 * @return Returns the noteVersion.
	 */
	public int getNoteVersion() {
		return noteVersion;
	}
	/**
	 * @param noteVersion The noteVersion to set.
	 */
	public void setNoteVersion(int noteVersion) {
		this.noteVersion = noteVersion;
	}
	/**
	 * @return Returns the benefitComponentNotesIdsList.
	 */
	public List getBenefitComponentNotesIdsList() {
		return benefitComponentNotesIdsList;
	}
	/**
	 * @param benefitComponentNotesIdsList The benefitComponentNotesIdsList to set.
	 */
	public void setBenefitComponentNotesIdsList(
			List benefitComponentNotesIdsList) {
		this.benefitComponentNotesIdsList = benefitComponentNotesIdsList;
	}
	
	
	/**
	 * @return Returns the notesList.
	 */
	public List getNotesList() {
		return notesList;
	}
	/**
	 * @param notesList The notesList to set.
	 */
	public void setNotesList(List notesList) {
		this.notesList = notesList;
	}
}
