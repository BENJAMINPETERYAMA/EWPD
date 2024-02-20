/*
 * NotesLocateCriteriaForView.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.notes.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesLocateCriteriaForView extends LocateCriteria{
    
    private String notesId;
    /**
     * Returns the notesId
     * @return Integer notesId.
     */

   
	/**
	 * @return Returns the notesId.
	 */
	public String getNotesId() {
		return notesId;
	}
	/**
	 * @param notesId The notesId to set.
	 */
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
}
