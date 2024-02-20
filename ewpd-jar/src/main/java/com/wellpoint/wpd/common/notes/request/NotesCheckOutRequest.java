/*
 * NotesCheckOutRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.notes.vo.NotesVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesCheckOutRequest extends WPDRequest {

	private NotesVO notesVO;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the notesVO
	 * @return NotesVO notesVO.
	 */
	public NotesVO getNotesVO() {
		return notesVO;
	}
	/**
	 * Sets the notesVO
	 * @param notesVO.
	 */
	public void setNotesVO(NotesVO notesVO) {
		this.notesVO = notesVO;
	}
}
