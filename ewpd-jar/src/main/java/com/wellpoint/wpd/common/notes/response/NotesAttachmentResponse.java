/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.notes.bo.NoteBO;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesAttachmentResponse extends WPDResponse {

	private List notesList;
	
	private NoteBO noteBO;
	
	/**
	 * @return Returns the noteBO.
	 */
	public NoteBO getNoteBO() {
		return noteBO;
	}
	/**
	 * @param noteBO The noteBO to set.
	 */
	public void setNoteBO(NoteBO noteBO) {
		this.noteBO = noteBO;
	}
	/**
	 * @return Returns the notesList.
	 */
	public List getNotesList() {
		return notesList;
	}
	/**
	 * @param notesList The notesList to set.
	 */
	public void setNotesList(List notesList) {
		this.notesList = notesList;
	}
}
