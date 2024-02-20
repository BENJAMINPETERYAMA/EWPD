/*
 * BenefitComponentDeleteRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;


/**
 * Request for Contact Note Delete
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractNotesDeleteRequest extends ContractRequest{
	
	String noteId = null;
	int primaryEntityId;
	String primaryEntityType = null;
	int secondaryEntityId;
	String secondaryEntityType = null;
	int benefitComponentId;
	String action = null;
	int dateSegmentId;
	String entityType;
		
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
	 * @return Returns the primaryEntityId.
	 */
	public int getPrimaryEntityId() {
		return primaryEntityId;
	}
	/**
	 * @param primaryEntityId The primaryEntityId to set.
	 */
	public void setPrimaryEntityId(int primaryEntityId) {
		this.primaryEntityId = primaryEntityId;
	}
	/**
	 * @return Returns the primaryEntityType.
	 */
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	/**
	 * @param primaryEntityType The primaryEntityType to set.
	 */
	public void setPrimaryEntityType(String primaryEntityType) {
		this.primaryEntityType = primaryEntityType;
	}
	/**
	 * @return Returns the secondaryEntityId.
	 */
	public int getSecondaryEntityId() {
		return secondaryEntityId;
	}
	/**
	 * @param secondaryEntityId The secondaryEntityId to set.
	 */
	public void setSecondaryEntityId(int secondaryEntityId) {
		this.secondaryEntityId = secondaryEntityId;
	}
	/**
	 * @return Returns the secondaryEntityType.
	 */
	public String getSecondaryEntityType() {
		return secondaryEntityType;
	}
	/**
	 * @param secondaryEntityType The secondaryEntityType to set.
	 */
	public void setSecondaryEntityType(String secondaryEntityType) {
		this.secondaryEntityType = secondaryEntityType;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
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
}
