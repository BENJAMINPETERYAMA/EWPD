/*
 * Created on May 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.notes.bo.NoteBO;

/**
 * @author U11442
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveNotesResponse extends WPDResponse {

	private NoteBO noteBO;
	
	private boolean notesInActivelyUsedStatus;

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
	 * @return Returns the notesInActivelyUsedStatus.
	 */
	public boolean isNotesInActivelyUsedStatus() {
		return notesInActivelyUsedStatus;
	}
	/**
	 * @param notesInActivelyUsedStatus The notesInActivelyUsedStatus to set.
	 */
	public void setNotesInActivelyUsedStatus(boolean notesInActivelyUsedStatus) {
		this.notesInActivelyUsedStatus = notesInActivelyUsedStatus;
	}
}
