/*
 * Created on May 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NoteBO extends BusinessObject {
	
	private String noteId;
	
	private String noteParentId;
	
	private String noteName;
	
	private String noteText;
	
	private String noteType;
	
	private String noteTypeDesc;
	
	private List noteSystemDomain;
	
	private boolean action;
	
	private String notesAction = "notes";
	
	private String compareAction;
	
	private String legacyIndicator;
	
	private String oldNoteName;
	
	public String getOldNoteName() {
		return oldNoteName;
	}
	public void setOldNoteName(String oldNoteName) {
		this.oldNoteName = oldNoteName;
	}
	/**
	 * @return Returns the notesAction.
	 */
	public String getNotesAction() {
		return notesAction;
	}
	/**
	 * @param notesAction The notesAction to set.
	 */
	public void setNotesAction(String notesAction) {
		this.notesAction = notesAction;
	}
	/**
	 * @return Returns the noteTypeDesc.
	 */
	public String getNoteTypeDesc() {
		return noteTypeDesc;
	}
	/**
	 * @param noteTypeDesc The noteTypeDesc to set.
	 */
	public void setNoteTypeDesc(String noteTypeDesc) {
		this.noteTypeDesc = noteTypeDesc;
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
	 * @return Returns the noteType.
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType The noteType to set.
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	
	/**
	 * @return Returns the noteParentId.
	 */
	public String getNoteParentId() {
		return noteParentId;
	}
	/**
	 * @param noteParentId The noteParentId to set.
	 */
	public void setNoteParentId(String noteParentId) {
		this.noteParentId = noteParentId;
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
	 * @return Returns the action.
	 */
	public boolean isAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(boolean action) {
		this.action = action;
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
	 * @return Returns the compareAction.
	 */
	public String getCompareAction() {
		return compareAction;
	}
	/**
	 * @param compareAction The compareAction to set.
	 */
	public void setCompareAction(String compareAction) {
		this.compareAction = compareAction;
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
}
