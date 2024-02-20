/*
 * Created on Jun 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.vo;

import java.util.List;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesToQuestionAttachmentVO {

	
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
	
	private String bcName;
	private List businessDomainList;
	private int bcVersion;
	private int dateSegmentVersion;
	private String cntrId;
	private String dateSegmentEffectiveDate;
	private int cntrSysId;
	private int prodVersion;
	private String prodName;
	private boolean insertRequest;
	private boolean updateRequest;
	private boolean deleteRequest;
	
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
	public boolean isUpdateRequest() {
		return updateRequest;
	}
	public void setUpdateRequest(boolean updateRequest) {
		this.updateRequest = updateRequest;
	}
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

	public String getBcName() {
		return bcName;
	}
	public void setBcName(String bcName) {
		this.bcName = bcName;
	}
	public List getBusinessDomainList() {
		return businessDomainList;
	}
	public void setBusinessDomainList(List businessDomainList) {
		this.businessDomainList = businessDomainList;
	}
	public int getBcVersion() {
		return bcVersion;
	}
	public void setBcVersion(int bcVersion) {
		this.bcVersion = bcVersion;
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
	public int getDateSegmentVersion() {
		return dateSegmentVersion;
	}
	public void setDateSegmentVersion(int dateSegmentVersion) {
		this.dateSegmentVersion = dateSegmentVersion;
	}
	public int getCntrSysId() {
		return cntrSysId;
	}
	public void setCntrSysId(int cntrSysId) {
		this.cntrSysId = cntrSysId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdVersion() {
		return prodVersion;
	}
	public void setProdVersion(int prodVersion) {
		this.prodVersion = prodVersion;
	}
}
