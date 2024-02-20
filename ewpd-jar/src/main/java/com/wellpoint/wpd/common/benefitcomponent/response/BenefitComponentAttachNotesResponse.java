/*
 * Created on May 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;

/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitComponentAttachNotesResponse extends WPDResponse{
	
	private AttachedNotesBO attachedNotesBO;
	
	private List notesList;

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
     * @return attachedNotesBO
     * 
     * Returns the attachedNotesBO.
     */
    public AttachedNotesBO getAttachedNotesBO() {
        return attachedNotesBO;
    }
    /**
     * @param attachedNotesBO
     * 
     * Sets the attachedNotesBO.
     */
    public void setAttachedNotesBO(AttachedNotesBO attachedNotesBO) {
        this.attachedNotesBO = attachedNotesBO;
    }
}
