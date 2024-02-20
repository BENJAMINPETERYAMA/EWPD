/*
 * Created on Jun 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.bo;

import java.util.Date;




/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesToQuestionAttachmentBenefitBO{

	
	private int questionId;
	private String noteId;
	private int primaryId;
	private int secondaryId;
	private String  benefitCompId;
	private String  bnftDefId;
	private String noteOverrideStatus;
	private int noteVersionNumber;
	private String primaryEntityType;
	private String secondaryEntityType;
	private int requestType;
	
	private Date lastUpdatedTimestamp;

	private String lastUpdatedUser;

	private Date createdTimestamp;

	private String createdUser;
	
	
	public String getBenefitCompId() {
		return benefitCompId;
	}
	public void setBenefitCompId(String benefitCompId) {
		this.benefitCompId = benefitCompId;
	}
	public String getBnftDefId() {
		return bnftDefId;
	}
	public void setBnftDefId(String bnftDefId) {
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

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
}

