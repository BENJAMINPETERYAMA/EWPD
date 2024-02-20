/*
 * NotesSearchResponse
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u11628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesSearchResponse extends WPDResponse {
	
	private boolean errorFlag;
	
	private int noteId;
	
	private int noteParentId;
	
	private String noteName;
	
	private String noteText;
	
	private String noteType;
	
	private List noteSystemDomain;
	
	private int version;
	
	private String status;
	
	private State stateObject;
	
	private String state;
	
	private List notesSearchResultList;
	
	/**
	 * @return Returns the notesSearchResultList.
	 */
	public List getNotesSearchResultList() {
		return notesSearchResultList;
	}
	/**
	 * @param notesSearchResultList The notesSearchResultList to set.
	 */
	public void setNotesSearchResultList(List notesSearchResultList) {
		this.notesSearchResultList = notesSearchResultList;
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
	/**
	 * @return Returns the noteId.
	 */
	public int getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(int noteId) {
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
	 * @return Returns the noteParentId.
	 */
	public int getNoteParentId() {
		return noteParentId;
	}
	/**
	 * @param noteParentId The noteParentId to set.
	 */
	public void setNoteParentId(int noteParentId) {
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
	 * @return Returns the errorFlag.
	 */
	public boolean isErrorFlag() {
		return errorFlag;
	}
	/**
	 * @param errorFlag The errorFlag to set.
	 */
	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}
	/**
	 * @return Returns the stateObject.
	 */
	public State getStateObject() {
		return stateObject;
	}
	/**
	 * @param stateObject The stateObject to set.
	 */
	public void setStateObject(State stateObject) {
		this.stateObject = stateObject;
	}
	
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		if (null != stateObject && !"".equals(stateObject.getState())) {
			stateObject.getState();
		}
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
}
