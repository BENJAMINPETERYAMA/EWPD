/*
 * Created on Jun 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;

/**
 * @author U12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitQuestionnaireAssnBO extends Questionnaire{
	
	private int adminOptionId;
	
	private int answerId;
	
	private int questionId;
	
	private String questionDesc;
	
	private String answerDesc;
	
	private String referenceId;
	
	private String referenceDesc;
	
	private int adminLvlOptionAssnSysId;
	
	private String createdUser;
	
	private String lastUpdatedUser;
	
	private Date createdTimestamp;
	
	private Date lastUpdatedTimestamp; 
	
	private List possibleAnswerList;
	
	private int questionOrder;
	
	private int benefitId;
	
	private int benefitAdministrationId;
	
	private List questionnaireList;
	
	private List newQuestions;
	
	private List modifiedQuestions;
	
	private List removedQuestions;
	
	private int sequenceNumber;
	
	private String notes_exists;
	
	private String validDomainToAttach;
	
	private String noteImageStatus;
	
	private boolean unsavedData = true;
	
	/**
	 * @return Returns the questionnareList.
	 */
	public List getQuestionnaireList() {
		return questionnaireList;
	}
	/**
	 * @param questionnareList The questionnareList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	/**
	 * @return Returns the adminOptionId.
	 */
	public int getAdminOptionId() {
		return adminOptionId;
	}
	/**
	 * @param adminOptionId The adminOptionId to set.
	 */
	public void setAdminOptionId(int adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	/**
	 * @return Returns the benefitAdministrationId.
	 */
	public int getBenefitAdministrationId() {
		return benefitAdministrationId;
	}
	/**
	 * @param benefitAdministrationId The benefitAdministrationId to set.
	 */
	public void setBenefitAdministrationId(int benefitAdministrationId) {
		this.benefitAdministrationId = benefitAdministrationId;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}
	/**
	 * @param questionDesc The questionDesc to set.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	/**
	 * @return Returns the questionId.
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return Returns the answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}
	/**
	 * @param answerDesc The answerDesc to set.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
	/**
	 * @return Returns the referenceId.
	 */
	public String getReferenceId() {
		return referenceId;
	}
	/**
	 * @param referenceId The referenceId to set.
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	/**
	 * @return Returns the adminLvlOptionAssnSysId.
	 */
	public int getAdminLvlOptionAssnSysId() {
		return adminLvlOptionAssnSysId;
	}
	/**
	 * @param adminLvlOptionAssnSysId The adminLvlOptionAssnSysId to set.
	 */
	public void setAdminLvlOptionAssnSysId(int adminLvlOptionAssnSysId) {
		this.adminLvlOptionAssnSysId = adminLvlOptionAssnSysId;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}
	/**
	 * @return Returns the possibleAnswerList.
	 */
	public List getPossibleAnswerList() {
		return possibleAnswerList;
	}
	/**
	 * @param possibleAnswerList The possibleAnswerList to set.
	 */
	public void setPossibleAnswerList(List possibleAnswerList) {
		this.possibleAnswerList = possibleAnswerList;
	}
	/**
	 * @return Returns the questionOrder.
	 */
	public int getQuestionOrder() {
		return questionOrder;
	}
	/**
	 * @param questionOrder The questionOrder to set.
	 */
	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}
	/**
	 * @return Returns the sequenceNumber.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return Returns the notes_exists.
	 */
	public String getNotes_exists() {
		return notes_exists;
	}
	/**
	 * @param notes_exists The notes_exists to set.
	 */
	public void setNotes_exists(String notes_exists) {
		this.notes_exists = notes_exists;
	}
    /**
     * @return Returns the validDomainToAttach.
     */
    public String getValidDomainToAttach() {
        return validDomainToAttach;
    }
    /**
     * @param validDomainToAttach The validDomainToAttach to set.
     */
    public void setValidDomainToAttach(String validDomainToAttach) {
        this.validDomainToAttach = validDomainToAttach;
    }
	/**
	 * @return Returns the noteImageStatus.
	 */
	public String getNoteImageStatus() {
		return noteImageStatus;
	}
	/**
	 * @param noteImageStatus The noteImageStatus to set.
	 */
	public void setNoteImageStatus(String noteImageStatus) {
		this.noteImageStatus = noteImageStatus;
	}
	/**
	 * @return Returns the unsavedData.
	 */
	public boolean isUnsavedData() {
		return unsavedData;
	}
	/**
	 * @param unsavedData The unsavedData to set.
	 */
	public void setUnsavedData(boolean unsavedData) {
		this.unsavedData = unsavedData;
	}
	/**
	 * @return Returns the modifiedQuestions.
	 */
	public List getModifiedQuestions() {
		return modifiedQuestions;
	}
	/**
	 * @param modifiedQuestions The modifiedQuestions to set.
	 */
	public void setModifiedQuestions(List modifiedQuestions) {
		this.modifiedQuestions = modifiedQuestions;
	}
	/**
	 * @return Returns the newQuestions.
	 */
	public List getNewQuestions() {
		return newQuestions;
	}
	/**
	 * @param newQuestions The newQuestions to set.
	 */
	public void setNewQuestions(List newQuestions) {
		this.newQuestions = newQuestions;
	}
	/**
	 * @return Returns the removedQuestions.
	 */
	public List getRemovedQuestions() {
		return removedQuestions;
	}
	/**
	 * @param removedQuestions The removedQuestions to set.
	 */
	public void setRemovedQuestions(List removedQuestions) {
		this.removedQuestions = removedQuestions;
	}
}
