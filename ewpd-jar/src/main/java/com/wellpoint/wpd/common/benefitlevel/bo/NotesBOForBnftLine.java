/*
 * NotesBOForBnftLine.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitlevel.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesBOForBnftLine {
    
    private String noteId;
    
    private String noteName;
    
    private int version;
    
    private String status;

    
    /**
     * Returns the noteName
     * @return String noteName.
     */

    public String getNoteName() {
        return noteName;
    }
    /**
     * Sets the noteName
     * @param noteName.
     */

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    /**
     * Returns the version
     * @return int version.
     */

    public int getVersion() {
        return version;
    }
    /**
     * Sets the version
     * @param version.
     */

    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * Returns the noteId
     * @return String noteId.
     */

    public String getNoteId() {
        return noteId;
    }
    /**
     * Sets the noteId
     * @param noteId.
     */

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
    /**
     * Returns the status
     * @return String status.
     */

    public String getStatus() {
        return status;
    }
    /**
     * Sets the status
     * @param status.
     */

    public void setStatus(String status) {
        this.status = status;
    }
}
