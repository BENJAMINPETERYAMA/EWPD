/*
 * ChildQuestionBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.bo;

import java.util.Date;
import java.util.List;

/**
 * Business Object for the associated child question details.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ChildQuestionnaireBO implements Comparable{
	
	private int sequenceNumber;
	
	private int answerId;
	
	private int questionNumber;
	
	private String questionDescription;
	
	private String referenceId;
	
	private String referenceDescription;
	
	private List lob;
	
	private List businessEntity;
	
	private List businessGroup;
	
	private List marketBusinessUnit;
	
	private int questionnaireHierarchySystemId;
	
	private int adminOptionSystemId;
	
	private int parentQuestionnaireId;
	
	private String createdUser;
	
	private Date createdTimeStamp;
	
	private String lastChangedUser;
	
	private Date lastChangedTimeStamp;
	
	private char deleteChildFlag;
	
	private List childQuestionnaires;
	
	private String childsToDeleted;
	
	private boolean deleteFlag;
	
	private String parentRequired; 
	
	private List functionalDomain;
	
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
	 * @return Returns the questionDescription.
	 */
	public String getQuestionDescription() {
		return questionDescription;
	}
	/**
	 * @param questionDescription The questionDescription to set.
	 */
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	/**
	 * @return Returns the questionNumber.
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber The questionNumber to set.
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * @return Returns the referenceDescription.
	 */
	public String getReferenceDescription() {
		return referenceDescription;
	}
	/**
	 * @param referenceDescription The referenceDescription to set.
	 */
	public void setReferenceDescription(String referenceDescription) {
		this.referenceDescription = referenceDescription;
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
	 * @return Returns the adminOptionSystemId.
	 */
	public int getAdminOptionSystemId() {
		return adminOptionSystemId;
	}
	/**
	 * @param adminOptionSystemId The adminOptionSystemId to set.
	 */
	public void setAdminOptionSystemId(int adminOptionSystemId) {
		this.adminOptionSystemId = adminOptionSystemId;
	}
	/**
	 * @return Returns the createdTimeStamp.
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * @param createdTimeStamp The createdTimeStamp to set.
	 */
	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
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
	 * @return Returns the lastChangedTimeStamp.
	 */
	public Date getLastChangedTimeStamp() {
		return lastChangedTimeStamp;
	}
	/**
	 * @param lastChangedTimeStamp The lastChangedTimeStamp to set.
	 */
	public void setLastChangedTimeStamp(Date lastChangedTimeStamp) {
		this.lastChangedTimeStamp = lastChangedTimeStamp;
	}
	/**
	 * @return Returns the lastChangedUser.
	 */
	public String getLastChangedUser() {
		return lastChangedUser;
	}
	/**
	 * @param lastChangedUser The lastChangedUser to set.
	 */
	public void setLastChangedUser(String lastChangedUser) {
		this.lastChangedUser = lastChangedUser;
	}
	/**
	 * @return Returns the parentQuestionnaireId.
	 */
	public int getParentQuestionnaireId() {
		return parentQuestionnaireId;
	}
	/**
	 * @param parentQuestionnaireId The parentQuestionnaireId to set.
	 */
	public void setParentQuestionnaireId(int parentQuestionnaireId) {
		this.parentQuestionnaireId = parentQuestionnaireId;
	}
	/**
	 * @return Returns the questionnaireHierarchySystemId.
	 */
	public int getQuestionnaireHierarchySystemId() {
		return questionnaireHierarchySystemId;
	}
	/**
	 * @param questionnaireHierarchySystemId The questionnaireHierarchySystemId to set.
	 */
	public void setQuestionnaireHierarchySystemId(
			int questionnaireHierarchySystemId) {
		this.questionnaireHierarchySystemId = questionnaireHierarchySystemId;
	}
	/**
	 * @return Returns the deleteChildFlag.
	 */
	public char getDeleteChildFlag() {
		return deleteChildFlag;
	}
	/**
	 * @param deleteChildFlag The deleteChildFlag to set.
	 */
	public void setDeleteChildFlag(char deleteChildFlag) {
		this.deleteChildFlag = deleteChildFlag;
	}
	/**
	 * @return Returns the businessEntity.
	 */
	public List getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(List businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the businessGroup.
	 */
	public List getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(List businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the lob.
	 */
	public List getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(List lob) {
		this.lob = lob;
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
	 * @return Returns the childQuestionnaires.
	 */
	public List getChildQuestionnaires() {
		return childQuestionnaires;
	}
	/**
	 * @param childQuestionnaires The childQuestionnaires to set.
	 */
	public void setChildQuestionnaires(List childQuestionnaires) {
		this.childQuestionnaires = childQuestionnaires;
	}
	/**
	 * @return Returns the childsToDeleted.
	 */
	public String getChildsToDeleted() {
		return childsToDeleted;
	}
	/**
	 * @param childsToDeleted The childsToDeleted to set.
	 */
	public void setChildsToDeleted(String childsToDeleted) {
		this.childsToDeleted = childsToDeleted;
	}
	/**
	 * Method to compare the objects in the child questionnaires list and sort the list.
	 * @return compareValue.
	 */
	public int compareTo(Object childBO) {
		int compareValue = 0;
		ChildQuestionnaireBO childQuestionnaireBO = (ChildQuestionnaireBO)childBO;
		if(childQuestionnaireBO.getSequenceNumber() > this.sequenceNumber)
			compareValue = -1;
		else if(childQuestionnaireBO.getSequenceNumber() < this.sequenceNumber)
			compareValue = 1;
		else
			compareValue = 0;
		return compareValue;
	}
	/**
	 * @return Returns the deleteFlag.
	 */
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param deleteFlag The deleteFlag to set.
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return Returns the parentRequired.
	 */
	public String getParentRequired() {
		return parentRequired;
	}
	/**
	 * @param parentRequired The parentRequired to set.
	 */
	public void setParentRequired(String parentRequired) {
		this.parentRequired = parentRequired;
	}
	/**
	 * @return Returns the functionalDomain.
	 */
	public List getFunctionalDomain() {
		return functionalDomain;
	}
	/**
	 * @param functionalDomain The functionalDomain to set.
	 */
	public void setFunctionalDomain(List functionalDomain) {
		this.functionalDomain = functionalDomain;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
