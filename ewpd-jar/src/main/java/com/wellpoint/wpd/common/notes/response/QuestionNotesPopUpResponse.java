/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class QuestionNotesPopUpResponse extends WPDResponse {

	private List notesList;
	
	private boolean recordsGrtThanMaxSize;
	
	
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
   
    /**
     * @return Returns the recordsGrtThanMaxSize.
     */
    public boolean isRecordsGrtThanMaxSize() {
        return recordsGrtThanMaxSize;
    }
    /**
     * @param recordsGrtThanMaxSize The recordsGrtThanMaxSize to set.
     */
    public void setRecordsGrtThanMaxSize(boolean recordsGrtThanMaxSize) {
        this.recordsGrtThanMaxSize = recordsGrtThanMaxSize;
    }
}
