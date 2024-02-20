/*
 * NotesDetail.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesDetail extends ObjectDetail {
	private String noteName;
	private String noteId;
	private int version;
	private String noteText;
	private String status;
	private String noteType;
	private NotesIdentifier ni;
	
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		if(ni!=null){
			return ni.getIdentifier();
		}
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
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
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#getIdentifier()
     * @return
     */
    public ObjectIdentifier getIdentifier() {
        return ni;
    }
    /**
     * @see com.wellpoint.wpd.common.search.result.ObjectDetail#setIdentifier(com.wellpoint.wpd.common.search.result.ObjectIdentifier)
     * @param identifier
     */
    public void setIdentifier(ObjectIdentifier identifier) {
        if(identifier != null && identifier instanceof NotesIdentifier){
            ni = (NotesIdentifier)identifier;
        }else{
            throw new IllegalArgumentException(
                    "setIdentifier method in NotesDetail.  Parameter is null or of incorrect type "
                            + identifier);
        }
        
    }
}
