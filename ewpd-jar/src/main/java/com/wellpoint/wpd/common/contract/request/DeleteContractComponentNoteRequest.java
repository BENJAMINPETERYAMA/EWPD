/*
 * DeleteContractComponentNoteRequest.java
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
public class DeleteContractComponentNoteRequest  extends ContractRequest {
    
    private int benefitComponentId;
	private List noteList;
	private int action;
	private String noteId;
	private String benefitComponentNoteId;

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
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
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
	 * @return Returns the benefitComponentNoteId.
	 */
	public String getBenefitComponentNoteId() {
		return benefitComponentNoteId;
	}
	/**
	 * @param benefitComponentNoteId The benefitComponentNoteId to set.
	 */
	public void setBenefitComponentNoteId(String benefitComponentNoteId) {
		this.benefitComponentNoteId = benefitComponentNoteId;
	}

}
