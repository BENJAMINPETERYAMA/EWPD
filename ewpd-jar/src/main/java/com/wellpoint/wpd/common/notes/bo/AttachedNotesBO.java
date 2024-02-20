/*
 * Created on May 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U12238
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AttachedNotesBO {

	private String noteId;

	private String noteName;

	private String noteText;

	private int entityId;

	private String entityType;

	private String createdUser;

	private String lastUpdatedUser;

	private Date createdTimestamp;

	private Date lastUpdatedTimestamp;

	private int version = -1;

	private List attachNotes;

	//added 28thNov
	private String benefitDefinitionKey;

	private String legacyIndicator;

	/**
	 * @return Returns the legacyIndicator.
	 */
	public String getLegacyIndicator() {
		return legacyIndicator;
	}

	/**
	 * @param legacyIndicator The legacyIndicator to set.
	 */
	public void setLegacyIndicator(String legacyIndicator) {
		this.legacyIndicator = legacyIndicator;
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
	 * @return entityType
	 * 
	 * Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType
	 * 
	 * Sets the entityType.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
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
	 * @return noteName
	 * 
	 * Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}

	/**
	 * @param noteName
	 * 
	 * Sets the noteName.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	/**
	 * @return noteText
	 * 
	 * Returns the noteText
	 */
	public String getNoteText() {
		return noteText;
	}

	/**
	 * @param noteText
	 * 
	 * Sets the noteText.
	 */
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 * @return
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
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
	 * Returns the attachNotes
	 * @return List attachNotes.
	 */

	public List getAttachNotes() {
		return attachNotes;
	}

	/**
	 * Sets the attachNotes
	 * @param attachNotes.
	 */

	public void setAttachNotes(List attachNotes) {
		this.attachNotes = attachNotes;
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