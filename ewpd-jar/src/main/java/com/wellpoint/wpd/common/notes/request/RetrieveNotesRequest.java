/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import java.util.List;

import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveNotesRequest extends WPDRequest{
	
	private String noteId;
	
	private String noteParentId;
	
	private String noteName;
	
	private String noteText;
	
	private String noteType;
	
	private List noteSystemDomain;
	
	private String status;
	
	private int version;
	
	private State state;
	
	private String action;
	
	private boolean View;
	
	private boolean edit;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		
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
	 * @return Returns the state.
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(State state) {
		this.state = state;
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
	 * @return Returns the view.
	 */
	public boolean isView() {
		return View;
	}
	/**
	 * @param view The view to set.
	 */
	public void setView(boolean view) {
		View = view;
	}
	/**
	 * @return Returns the edit.
	 */
	public boolean isEdit() {
		return edit;
	}
	/**
	 * @param edit The edit to set.
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
}
