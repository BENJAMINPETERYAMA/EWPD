/*
 * Created on Aug 21, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.contract.request.ContractRequest;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractTeiredNotesToQuestionAttachmentRequest extends ContractRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	private int questionId;
	private String noteId;
	private int primaryId;
	private int secondaryId;
	private int benefitCompId;
	private int bnftDefId;
	private String noteOverrideStatus;
	private int noteVersionNumber;
	private String primaryEntityType;
	private String secondaryEntityType;
	private int requestType;
	
	private boolean insertRequest;
	private boolean updateRequest;
	private boolean deleteRequest;
	
	private String cntrId;
	private String dateSegmentEffectiveDate;
	
	private String dateSegmentType;
	
	private int tierSysId;
	

	public int getBenefitCompId() {
		return benefitCompId;
	}
	public void setBenefitCompId(int benefitCompId) {
		this.benefitCompId = benefitCompId;
	}
	public int getBnftDefId() {
		return bnftDefId;
	}
	public void setBnftDefId(int bnftDefId) {
		this.bnftDefId = bnftDefId;
	}
	public String getCntrId() {
		return cntrId;
	}
	public void setCntrId(String cntrId) {
		this.cntrId = cntrId;
	}
	public String getDateSegmentEffectiveDate() {
		return dateSegmentEffectiveDate;
	}
	public void setDateSegmentEffectiveDate(String dateSegmentEffectiveDate) {
		this.dateSegmentEffectiveDate = dateSegmentEffectiveDate;
	}
	public String getDateSegmentType() {
		return dateSegmentType;
	}
	public void setDateSegmentType(String dateSegmentType) {
		this.dateSegmentType = dateSegmentType;
	}
	public boolean isDeleteRequest() {
		return deleteRequest;
	}
	public void setDeleteRequest(boolean deleteRequest) {
		this.deleteRequest = deleteRequest;
	}
	public boolean isInsertRequest() {
		return insertRequest;
	}
	public void setInsertRequest(boolean insertRequest) {
		this.insertRequest = insertRequest;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getNoteOverrideStatus() {
		return noteOverrideStatus;
	}
	public void setNoteOverrideStatus(String noteOverrideStatus) {
		this.noteOverrideStatus = noteOverrideStatus;
	}
	public int getNoteVersionNumber() {
		return noteVersionNumber;
	}
	public void setNoteVersionNumber(int noteVersionNumber) {
		this.noteVersionNumber = noteVersionNumber;
	}
	public String getPrimaryEntityType() {
		return primaryEntityType;
	}
	public void setPrimaryEntityType(String primaryEntityType) {
		this.primaryEntityType = primaryEntityType;
	}
	public int getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(int primaryId) {
		this.primaryId = primaryId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getSecondaryEntityType() {
		return secondaryEntityType;
	}
	public void setSecondaryEntityType(String secondaryEntityType) {
		this.secondaryEntityType = secondaryEntityType;
	}
	public int getSecondaryId() {
		return secondaryId;
	}
	public void setSecondaryId(int secondaryId) {
		this.secondaryId = secondaryId;
	}
	public boolean isUpdateRequest() {
		return updateRequest;
	}
	public void setUpdateRequest(boolean updateRequest) {
		this.updateRequest = updateRequest;
	}
	public int getTierSysId() {
		return tierSysId;
	}
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
}
