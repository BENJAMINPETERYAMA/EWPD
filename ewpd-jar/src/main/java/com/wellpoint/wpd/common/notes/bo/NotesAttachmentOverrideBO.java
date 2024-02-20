/*
 * NotesAttachmentOverrideBO.java
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

import com.wellpoint.wpd.common.bo.State;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesAttachmentOverrideBO {

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

	private boolean checkedNoteId = false;

	private List notesList;

	private State state;

	private int version = -1;

	private String status;

	private String createdUser;

	private Date createdTimestamp;

	private String lastUpdatedUser;

	private String overrideStatus = "N";

	private Date lastUpdatedTimestamp;

	private String note_status;

	private int dateSegmentId;

	private String action;

	private String referenceCode;

	private String legacyIndicator;

	private String bnftDefnIdString;

	private int questionNumber;

	private List EntityIdsList;

	private String entityType;

	private String entityId;

	private String searchString;

	private int searchAction;
	
	private int sequenceNumber;
	
	private int questionnaireSequenceNumber;
	
	private String isdelete;
	
	private String isInsert;
	
	private String isUpdate;
	
	private int tierSysId;

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
	 * Returns the checkedNoteId
	 * @return boolean checkedNoteId.
	 */
	public boolean isCheckedNoteId() {
		return checkedNoteId;
	}

	/**
	 * Sets the checkedNoteId
	 * @param checkedNoteId.
	 */
	public void setCheckedNoteId(boolean checkedNoteId) {
		this.checkedNoteId = checkedNoteId;
	}

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
	 * @param integer The primaryEntityId to set.
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
	 * @return Returns the overrideStatus.
	 */
	public String getOverrideStatus() {
		return overrideStatus;
	}

	/**
	 * @param overrideStatus The overrideStatus to set.
	 */
	public void setOverrideStatus(String overrideStatus) {
		this.overrideStatus = overrideStatus;
	}

	/**
	 * @return Returns the note_status.
	 */
	public String getNote_status() {
		return note_status;
	}

	/**
	 * @param note_status The note_status to set.
	 */
	public void setNote_status(String note_status) {
		this.note_status = note_status;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the referenceCode.
	 */
	public String getReferenceCode() {
		return referenceCode;
	}

	/**
	 * @param referenceCode The referenceCode to set.
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

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
	 * @return Returns the bnftDefnIdString.
	 */
	public String getBnftDefnIdString() {
		return bnftDefnIdString;
	}

	/**
	 * @param bnftDefnIdString The bnftDefnIdString to set.
	 */
	public void setBnftDefnIdString(String bnftDefnIdString) {
		this.bnftDefnIdString = bnftDefnIdString;
	}

	/**
	 * @return Returns the questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * @param questionNumber The questionNumber to set.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * @return Returns the entityIdsList.
	 */
	public List getEntityIdsList() {
		return EntityIdsList;
	}

	/**
	 * @param entityIdsList The entityIdsList to set.
	 */
	public void setEntityIdsList(List entityIdsList) {
		EntityIdsList = entityIdsList;
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
	 * @return Returns the entityId.
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return Returns the searchAction.
	 */
	public int getSearchAction() {
		return searchAction;
	}

	/**
	 * @param searchAction The searchAction to set.
	 */
	public void setSearchAction(int searchAction) {
		this.searchAction = searchAction;
	}
	/**
	 * @return Returns the questionnaireSequenceNumber.
	 */
	public int getQuestionnaireSequenceNumber() {
		return questionnaireSequenceNumber;
	}
	/**
	 * @param questionnaireSequenceNumber The questionnaireSequenceNumber to set.
	 */
	public void setQuestionnaireSequenceNumber(int questionnaireSequenceNumber) {
		this.questionnaireSequenceNumber = questionnaireSequenceNumber;
	}
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return Returns the isdelete.
	 */
	public String getIsdelete() {
		return isdelete;
	}
	/**
	 * @param isdelete The isdelete to set.
	 */
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	/**
	 * @return Returns the isInsert.
	 */
	public String getIsInsert() {
		return isInsert;
	}
	/**
	 * @param isInsert The isInsert to set.
	 */
	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}
	/**
	 * @return Returns the isUpdate.
	 */
	public String getIsUpdate() {
		return isUpdate;
	}
	/**
	 * @param isUpdate The isUpdate to set.
	 */
	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
	/**
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
}