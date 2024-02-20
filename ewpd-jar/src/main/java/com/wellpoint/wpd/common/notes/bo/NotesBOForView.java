/*
 * NotesBOForView.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesBOForView extends BusinessObject{

    private String noteId;
	
	private String noteName;
	
	private String noteText;
	
	private String noteType;
	
	private List noteSystemDomain;
	
	private String createdUserId;
	
	private Date createdDate;
	
	private String lastUpdatedUSerId;
	
	private Date lastUpdatedDate;
	
	private int version;
	
	private String status;
	
	
	
    /**
     * Returns the createdDate
     * @return Date createdDate.
     */

    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * Sets the createdDate
     * @param createdDate.
     */

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /**
     * Returns the createdUserId
     * @return String createdUserId.
     */

    public String getCreatedUserId() {
        return createdUserId;
    }
    /**
     * Sets the createdUserId
     * @param createdUserId.
     */

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
    /**
     * Returns the lastUpdatedDate
     * @return Date lastUpdatedDate.
     */

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    /**
     * Sets the lastUpdatedDate
     * @param lastUpdatedDate.
     */

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    /**
     * Returns the lastUpdatedUSerId
     * @return String lastUpdatedUSerId.
     */

    public String getLastUpdatedUSerId() {
        return lastUpdatedUSerId;
    }
    /**
     * Sets the lastUpdatedUSerId
     * @param lastUpdatedUSerId.
     */

    public void setLastUpdatedUSerId(String lastUpdatedUSerId) {
        this.lastUpdatedUSerId = lastUpdatedUSerId;
    }
    
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
    /**
     * Returns the noteName
     * @return String noteName.
     */

    public String getNoteName() {
        return noteName;
    }
    /**
     * Sets the noteName
     * @param noteName.
     */

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    /**
     * @return Returns the noteSystemDomain.
     */
    public List getNoteSystemDomain() {
        return noteSystemDomain;
    }
    /**
     * @param noteSystemDomain The noteSystemDomain to set.
     */
    public void setNoteSystemDomain(List noteSystemDomain) {
        this.noteSystemDomain = noteSystemDomain;
    }
    /**
     * Returns the noteText
     * @return String noteText.
     */

    public String getNoteText() {
        return noteText;
    }
    /**
     * Sets the noteText
     * @param noteText.
     */

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    /**
     * Returns the noteType
     * @return String noteType.
     */

    public String getNoteType() {
        return noteType;
    }
    /**
     * Sets the noteType
     * @param noteType.
     */

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
	
	
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

}
