/*
 * RetrieveContractNoteRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractNoteRequest extends ContractRequest{

	int action;
	List noteList;
	int dateSegmentId;
	String entityType;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the action
	 * @return int action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * Sets the action
	 * @param action.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * Returns the noteList
	 * @return List noteList.
	 */
	public List getNoteList() {
		return noteList;
	}
	/**
	 * Sets the noteList
	 * @param noteList.
	 */
	public void setNoteList(List noteList) {
		this.noteList = noteList;
	}
	/**
	 * Returns the dateSegmentId
	 * @return int dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * Sets the dateSegmentId
	 * @param dateSegmentId.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * Returns the entityType
	 * @return String entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * Sets the entityType
	 * @param entityType.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
}
