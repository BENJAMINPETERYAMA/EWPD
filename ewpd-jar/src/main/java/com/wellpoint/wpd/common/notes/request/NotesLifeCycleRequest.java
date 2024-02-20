/*
 * NotesLifeCycleRequest.java
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
public class NotesLifeCycleRequest extends WPDRequest{
	
	private NotesVO notesVO;
	
	private int maxSize;
	
	private String action;
	
	/**
	 * Returns the notesVO
	 * @return notesVO NotesVO.
	 */
	public NotesVO getNotesVO() {
		return notesVO;
	}
	/**
	 * Sets the NotesVO
	 * @param notesVO.
	 */
	public void setNotesVO(NotesVO notesVO) {
		this.notesVO = notesVO;
	}
	
	
	public void validate() throws ValidationException {

	}

	/**
	 * @return Returns the maxSize.
	 */
	public int getMaxSize() {
		return maxSize;
	}
	/**
	 * @param maxSize The maxSize to set.
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
    /**
     * Returns the action
     * @return String action.
     */
    public String getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action.
     */
    public void setAction(String action) {
        this.action = action;
    }
}
