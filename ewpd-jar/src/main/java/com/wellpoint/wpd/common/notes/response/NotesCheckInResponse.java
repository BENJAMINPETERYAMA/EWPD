/*
 * Created on May 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesCheckInResponse extends WPDResponse{
	
	private int noteId;
	
	private String name;
	
	private String text;
	
	private String type;
	
	private List systemDomain;
	
	private boolean createFlag;
	
	private int version = -1;
	
	private String status;
	
	private String state;
	
	private boolean action;

	/**
	 * @return Returns the createFlag.
	 */
	public boolean isCreateFlag() {
		return createFlag;
	}
	/**
	 * @param createFlag The createFlag to set.
	 */
	public void setCreateFlag(boolean createFlag) {
		this.createFlag = createFlag;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
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
	 * @return Returns the systemDomain.
	 */
	public List getSystemDomain() {
		return systemDomain;
	}
	/**
	 * @param systemDomain The systemDomain to set.
	 */
	public void setSystemDomain(List systemDomain) {
		this.systemDomain = systemDomain;
	}
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
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
	public boolean isAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(boolean action) {
		this.action = action;
	}
}
