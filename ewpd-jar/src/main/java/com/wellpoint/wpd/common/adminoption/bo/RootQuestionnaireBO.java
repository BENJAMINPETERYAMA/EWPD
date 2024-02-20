/*
 * RootQuestionBO.java
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
 * Business Object for the Root Question Details.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RootQuestionnaireBO implements Comparable{
	
	private int questionnaireHierachySystemId;
	
	private int parentHierachySystemId;
	
	private int adminOptionSystemId;
	
	private int questionNumber;
	
	private int sequenceNumber;
	
	private String questionDescription;
	
	private String referenceId;
	
	private String referenceDescription;
	
	private List possibleAnswerList;
	
	private List lob;
	
	private List businessGroup;
	
	private List businessEntity;
	
	private List marketBusinessUnit;
	
	private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List rootQuestionnaires;
    
    private String questionnairesToDelete;
    
    private List functionalDomain;
    
    private String spsReference;
    
    private String referenceDesc;
		
    /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		
		RootQuestionnaireBO rootQuestionnaireBO= (RootQuestionnaireBO)obj;
		if(this.referenceId ==null || "null".equalsIgnoreCase(this.referenceId))
			return false;
		else if(this.referenceId.equalsIgnoreCase(rootQuestionnaireBO.getReferenceId()))
			return true;
		return super.equals(obj);
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
	 * @return Returns the questionnaireHierachySystemId.
	 */
	public int getQuestionnaireHierachySystemId() {
		return questionnaireHierachySystemId;
	}
	/**
	 * @param questionnaireHierachySystemId The questionnaireHierachySystemId to set.
	 */
	public void setQuestionnaireHierachySystemId(
			int questionnaireHierachySystemId) {
		this.questionnaireHierachySystemId = questionnaireHierachySystemId;
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
	 * @return Returns the parentHierachySystemId.
	 */
	public int getParentHierachySystemId() {
		return parentHierachySystemId;
	}
	/**
	 * @param parentHierachySystemId The parentHierachySystemId to set.
	 */
	public void setParentHierachySystemId(int parentHierachySystemId) {
		this.parentHierachySystemId = parentHierachySystemId;
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
	 * @return Returns the questionnairesToDelete.
	 */
	public String getQuestionnairesToDelete() {
		return questionnairesToDelete;
	}
	/**
	 * @param questionnairesToDelete The questionnairesToDelete to set.
	 */
	public void setQuestionnairesToDelete(String questionnairesToDelete) {
		this.questionnairesToDelete = questionnairesToDelete;
	}
	/**
	 * @return Returns the rootQuestionnaires.
	 */
	public List getRootQuestionnaires() {
		return rootQuestionnaires;
	}
	/**
	 * @param rootQuestionnaires The rootQuestionnaires to set.
	 */
	public void setRootQuestionnaires(List rootQuestionnaires) {
		this.rootQuestionnaires = rootQuestionnaires;
	}
	/**
	 * Method to compare the objects in the child questionnaires list and sort the list.
	 * @return compareValue.
	 */
	public int compareTo(Object rootQuestionBO) {
		int compareValue = 0;
		RootQuestionnaireBO rootQuestionnaireBO = (RootQuestionnaireBO)rootQuestionBO;
		if(rootQuestionnaireBO.getSequenceNumber() > this.sequenceNumber)
			compareValue = -1;
		else if(rootQuestionnaireBO.getSequenceNumber() < this.sequenceNumber)
			compareValue = 1;
		else
			compareValue = 0;
		return compareValue;
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
	public String getSpsReference() {
		return spsReference;
	}
	public void setSpsReference(String spsReference) {
		this.spsReference = spsReference;
	}
	public String getReferenceDesc() {
		return referenceDesc;
	}
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
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
