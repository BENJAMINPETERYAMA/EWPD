package com.wellpoint.wpd.business.contract.locateresult;

/*
 * Created on July 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


import com.wellpoint.wpd.common.bo.LocateResult;

/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelOverrideLocateResult extends LocateResult{
	
	boolean checkedNoteId;
	private String noteId;
	private String noteName;
	
	/**
	 * @return Returns the checkedNoteId.
	 */
	public boolean isCheckedNoteId() {
		return checkedNoteId;
	}
	/**
	 * @param checkedNoteId The checkedNoteId to set.
	 */
	public void setCheckedNoteId(boolean checkedNoteId) {
		this.checkedNoteId = checkedNoteId;
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
}
