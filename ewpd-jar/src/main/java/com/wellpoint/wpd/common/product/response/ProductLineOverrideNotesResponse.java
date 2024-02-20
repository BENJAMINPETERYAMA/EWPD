/*
 * ContractAttachNotesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductLineOverrideNotesResponse extends WPDResponse{
    
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
