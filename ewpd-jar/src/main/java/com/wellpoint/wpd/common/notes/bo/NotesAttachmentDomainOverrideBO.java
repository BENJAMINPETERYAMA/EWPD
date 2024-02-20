/*
 * Created on May 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.State;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesAttachmentDomainOverrideBO {
	
	private String noteId;
	
	private String noteName;
	
	private String noteDesc;
	
	private int primaryEntityId;
	
	private String primaryEntityType;
	
	private int secondaryEntityId;
	
	private String secondaryEntityType;
	
	private int benefitComponentId;
	
	private String term;
	
	private String qualifier;
	
	private boolean attachNote = false;
	
	private List notesList;
	
	private State state;

    private int version = -1 ;

    private String status;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int benefitComponentHirAssnId;
    
//  added for multiple deleteion 
    private List benefitComponentDeleteList;

    /**
     * @return notesList
     * 
     * Returns the notesList.
     */
    public List getNotesList() {
        return notesList;
    }
    /**
     * @param notesList
     * 
     * Sets the notesList.
     */
    public void setNotesList(List notesList) {
        this.notesList = notesList;
    }
    /**
     * @return attachNote
     * 
     * Returns the attachNote.
     */
    public boolean isAttachNote() {
        return attachNote;
    }
    /**
     * @param attachNote
     * 
     * Sets the attachNote.
     */
    public void setAttachNote(boolean attachNote) {
        this.attachNote = attachNote;
    }
    /**
     * @return createdTimestamp
     * 
     * Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp
     * 
     * Sets the createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return createdUser
     * 
     * Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser
     * 
     * Sets the createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return lastUpdatedTimestamp
     * 
     * Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp
     * 
     * Sets the lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return lastUpdatedUser
     * 
     * Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser
     * 
     * Sets the lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return state
     * 
     * Returns the state.
     */
    public State getState() {
        return state;
    }
    /**
     * @param state
     * 
     * Sets the state.
     */
    public void setState(State state) {
        this.state = state;
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
	 * @return Returns the noteDesc.
	 */
	public String getNoteDesc() {
		return noteDesc;
	}
	/**
	 * @param noteDesc The noteDesc to set.
	 */
	public void setNoteDesc(String noteDesc) {
		this.noteDesc = noteDesc;
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
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the primaryEntityId.
	 */
	public int getPrimaryEntityId() {
		return primaryEntityId;
	}
	/**
	 * @param primaryEntityId The primaryEntityId to set.
	 */
	public void setPrimaryEntityId(int primaryEntityId) {
		this.primaryEntityId = primaryEntityId;
	}
	/**
	 * @return Returns the primaryEntityType.
	 */
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	/**
	 * @param primaryEntityType The primaryEntityType to set.
	 */
	public void setPrimaryEntityType(String primaryEntityType) {
		this.primaryEntityType = primaryEntityType;
	}
	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the secondaryEntityId.
	 */
	public int getSecondaryEntityId() {
		return secondaryEntityId;
	}
	/**
	 * @param secondaryEntityId The secondaryEntityId to set.
	 */
	public void setSecondaryEntityId(int secondaryEntityId) {
		this.secondaryEntityId = secondaryEntityId;
	}
	/**
	 * @return Returns the secondaryEntityType.
	 */
	public String getSecondaryEntityType() {
		return secondaryEntityType;
	}
	/**
	 * @param secondaryEntityType The secondaryEntityType to set.
	 */
	public void setSecondaryEntityType(String secondaryEntityType) {
		this.secondaryEntityType = secondaryEntityType;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
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

	/**
	 * @return Returns the benefitComponentHirAssnId.
	 */
	public int getBenefitComponentHirAssnId() {
		return benefitComponentHirAssnId;
	}
	/**
	 * @param benefitComponentHirAssnId The benefitComponentHirAssnId to set.
	 */
	public void setBenefitComponentHirAssnId(int benefitComponentHirAssnId) {
		this.benefitComponentHirAssnId = benefitComponentHirAssnId;
	}
/**
 * @return Returns the benefitComponentDeleteList.
 */
public List getBenefitComponentDeleteList() {
	return benefitComponentDeleteList;
}
/**
 * @param benefitComponentDeleteList The benefitComponentDeleteList to set.
 */
public void setBenefitComponentDeleteList(List benefitComponentDeleteList) {
	this.benefitComponentDeleteList = benefitComponentDeleteList;
}
}
