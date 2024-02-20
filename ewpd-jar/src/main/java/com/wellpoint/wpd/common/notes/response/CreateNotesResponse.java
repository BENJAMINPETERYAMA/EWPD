/*
 * Created on May 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.State;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateNotesResponse extends WPDResponse {
	
	private boolean errorFlag;
	
	private String noteId;
	
	private String noteParentId;
	
	private String noteName;
	
	private String noteText;
	
	private String noteType;
	
	private List noteSystemDomain;
	
	private int version;
	
	private String status;
	
	private State stateObject;
	
	private String state;
	
	private String createdUser;
	
	private Date createdTimestamp;
	
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimestamp;
	
	private boolean keyValueChanged;
	

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
	
	/**
	 * Returns the keyValueChanged
	 * @return boolean keyValueChanged.
	 */
	public boolean isKeyValueChanged() {
		return keyValueChanged;
	}
	/**
	 * Sets the keyValueChanged
	 * @param keyValueChanged.
	 */
	public void setKeyValueChanged(boolean keyValueChanged) {
		this.keyValueChanged = keyValueChanged;
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
}
