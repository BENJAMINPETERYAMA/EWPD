/*
 * Created on May 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.notes.vo.NotesVO;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateNotesRequest extends WPDRequest {
	
	private NotesVO oldNotesVO;
	
	private NotesVO notesVO;
	
	private String noteId;
	
	private String name;
	
	private String text;
	
	private String type;
	
	private List systemDomain;
	
	private boolean createFlag;
	
	private int version = -1;
	
	private String status;
	
	private String state;
	
	
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * Returns the notesVO
	 * @return NotesVO notesVO.
	 */
	public NotesVO getNotesVO() {
		return notesVO;
	}
	/**
	 * Sets the notesVO
	 * @param notesVO.
	 */
	public void setNotesVO(NotesVO notesVO) {
		this.notesVO = notesVO;
	}
	/**
	 * Returns the oldNotesVO
	 * @return NotesVO oldNotesVO.
	 */
	public NotesVO getOldNotesVO() {
		return oldNotesVO;
	}
	/**
	 * Sets the oldNotesVO
	 * @param oldNotesVO.
	 */
	public void setOldNotesVO(NotesVO oldNotesVO) {
		this.oldNotesVO = oldNotesVO;
	}
}
