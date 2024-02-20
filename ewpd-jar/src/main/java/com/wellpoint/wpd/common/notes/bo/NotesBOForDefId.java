/*
 * Created on Jan 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.Date;
import java.util.List;

/**
 * @author U15701
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesBOForDefId {
	
	private String noteId;

    private String noteName;
    
    private String noteText;

    private int entityId;

    private String entityType;
    
    private String createdUser;
	private String lastUpdatedUser;
	private Date createdTimestamp;
	private Date lastUpdatedTimestamp; 
	private int  version=-1;
	
	private List attachNotes;
	private String benefitDefinitionKey;

	/**
	 * @return Returns the attachNotes.
	 */
	public List getAttachNotes() {
		return attachNotes;
	}
	/**
	 * @param attachNotes The attachNotes to set.
	 */
	public void setAttachNotes(List attachNotes) {
		this.attachNotes = attachNotes;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
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
	 * @return Returns the noteText.
	 */
	public String getNoteText() {
		return noteText;
	}
	/**
	 * @param noteText The noteText to set.
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
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
	/**
	 * @return Returns the benefitDefinitionKey.
	 */
	public String getBenefitDefinitionKey() {
		return benefitDefinitionKey;
	}
	/**
	 * @param benefitDefinitionKey The benefitDefinitionKey to set.
	 */
	public void setBenefitDefinitionKey(String benefitDefinitionKey) {
		this.benefitDefinitionKey = benefitDefinitionKey;
	}
}
