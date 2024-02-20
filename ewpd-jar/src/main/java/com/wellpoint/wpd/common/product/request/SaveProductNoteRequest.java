/*
 * Created on May 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveProductNoteRequest extends ProductRequest {
	
	private int action;
	private List noteList;
	private List versionList;
	
	public static final int SAVE_NOTE = 1;
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the noteList.
	 */
	public List getNoteList() {
		return noteList;
	}
	/**
	 * @param noteList The noteList to set.
	 */
	public void setNoteList(List noteList) {
		this.noteList = noteList;
	}
	/**
	 * @return Returns the versionList.
	 */
	public List getVersionList() {
		return versionList;
	}
	/**
	 * @param versionList The versionList to set.
	 */
	public void setVersionList(List versionList) {
		this.versionList = versionList;
	}
}
