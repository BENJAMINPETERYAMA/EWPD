/*
 * NotesCopyResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.notes.bo.NoteBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesCopyResponse extends WPDResponse {
	
	private NoteBO noteBO;
	private boolean success;
	

	/**
	 * Returns the noteBO
	 * @return NoteBO noteBO.
	 */
	public NoteBO getNoteBO() {
		return noteBO;
	}
	/**
	 * Sets the noteBO
	 * @param noteBO.
	 */
	public void setNoteBO(NoteBO noteBO) {
		this.noteBO = noteBO;
	}
	
	/**
	 * Returns the success
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Sets the success
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
