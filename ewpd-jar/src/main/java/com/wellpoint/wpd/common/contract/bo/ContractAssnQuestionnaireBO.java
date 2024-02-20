/*
 * ContractAssnQuesitionnaireBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;

/**
 * Business Object for Contract Level Questionnaire
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractAssnQuestionnaireBO extends Questionnaire {
    
    private int questionId;
    
    private int selectedAnswerid;
    
    private List possibleAnswerList;
    
    private String questionName;
    
    private String selectedAnswerDesc;
    
    private int questionOrder;
    
    private String referenceId;
    
    private String referenceDesc;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int dateSegmentId;

    private int sequenceNumber;
    
    private int adminOptionId;
    
    private List questionnaireList;
    
    private int parentAdminOptionId;
    
    private String dataTypeLegend;
    
    private String providerArrangement;
    
    private String displayIndicator;
    
    private String notesExists;

    private String validDomainToAttach;
    
    private boolean unsavedData = true;
    
	/**
	 * @return Returns the dataTypeLegend.
	 */
	public String getDataTypeLegend() {
		return dataTypeLegend;
	}
	/**
	 * @param dataTypeLegend The dataTypeLegend to set.
	 */
	public void setDataTypeLegend(String dataTypeLegend) {
		this.dataTypeLegend = dataTypeLegend;
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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
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
	 * @return Returns the questionName.
	 */
	public String getQuestionName() {
		return questionName;
	}
	/**
	 * @param questionName The questionName to set.
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
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
	 * @return Returns the selectedAnswerDesc.
	 */
	public String getSelectedAnswerDesc() {
		return selectedAnswerDesc;
	}
	/**
	 * @param selectedAnswerDesc The selectedAnswerDesc to set.
	 */
	public void setSelectedAnswerDesc(String selectedAnswerDesc) {
		this.selectedAnswerDesc = selectedAnswerDesc;
	}
	/**
	 * @return Returns the selectedAnswerid.
	 */
	public int getSelectedAnswerid() {
		return selectedAnswerid;
	}
	/**
	 * @param selectedAnswerid The selectedAnswerid to set.
	 */
	public void setSelectedAnswerid(int selectedAnswerid) {
		this.selectedAnswerid = selectedAnswerid;
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
	 * @return Returns the questionnaireList.
	 */
	public List getQuestionnaireList() {
		return questionnaireList;
	}
	/**
	 * @param questionnaireList The questionnaireList to set.
	 */
	public void setQuestionnaireList(List questionnaireList) {
		this.questionnaireList = questionnaireList;
	}
	/**
	 * @return Returns the parentAdminOptionId.
	 */
	public int getParentAdminOptionId() {
		return parentAdminOptionId;
	}
	/**
	 * @param parentAdminOptionId The parentAdminOptionId to set.
	 */
	public void setParentAdminOptionId(int parentAdminOptionId) {
		this.parentAdminOptionId = parentAdminOptionId;
	}
	/**
	 * @return Returns the providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * @param providerArrangement The providerArrangement to set.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * @return Returns the displayIndicator.
	 */
	public String getDisplayIndicator() {
		return displayIndicator;
	}
	/**
	 * @param displayIndicator The displayIndicator to set.
	 */
	public void setDisplayIndicator(String displayIndicator) {
		this.displayIndicator = displayIndicator;
	}
	public String getNotesExists() {
		return notesExists;
	}
	public void setNotesExists(String notesExists) {
		this.notesExists = notesExists;
	}
	public String getValidDomainToAttach() {
		return validDomainToAttach;
	}
	public void setValidDomainToAttach(String validDomainToAttach) {
		this.validDomainToAttach = validDomainToAttach;
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
}
