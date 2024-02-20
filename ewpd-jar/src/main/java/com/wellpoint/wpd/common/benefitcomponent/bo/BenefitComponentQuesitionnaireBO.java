/*
 * BenefitBO.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;

/**
 * Business Object for benefit
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentQuesitionnaireBO  extends Questionnaire{

    private int benefitId;

    private String benefitName;
    
    private int questionId;
    
    private int selectedAnswerid;
    
    private List possibleAnswerList;
    
    private String questionName;
    
    private String selectedAnswerDesc;
    
    private int questionOrder;
    
    private int adminLevelOptionSysId;
    
    private String referenceId;
    
    private String referenceDesc;
    
    private int benefitComponentId;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private String notesExists;
    
    private String bnftDefId;

	private boolean noteAttached = false;

	private boolean noNoteAttached = true;
	
	private String validDomainToAttach;
	
	private boolean unsavedData = true;
	
	public boolean isNoNoteAttached() {
		
		if(("N").equalsIgnoreCase(this.getValidDomainToAttach()))
			return false;

		if ("Y".equalsIgnoreCase(this.notesExists)&& ("Y").equalsIgnoreCase(this.getValidDomainToAttach()))
			noNoteAttached = false;

		return noNoteAttached;
	}

	public void setNoNoteAttached(boolean noNoteAttached) {
		this.noNoteAttached = noNoteAttached;
	}

	public boolean isNoteAttached() {

		if(("N").equalsIgnoreCase(this.getValidDomainToAttach()))
			return false;
		
		if ("Y".equalsIgnoreCase(this.notesExists))
			noteAttached = true;

		return noteAttached;
	}

	public void setNoteAttached(boolean noteAttached) {
		this.noteAttached = noteAttached;
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
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }


    /**
     * @param benefitId
     *            The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }


    /**
     * @return Returns the benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }


    /**
     * @param benefitName
     *            The benefitName to set.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
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
	 * @return Returns the adminLevelOptionSysId.
	 */
	public int getAdminLevelOptionSysId() {
		return adminLevelOptionSysId;
	}
	/**
	 * @param adminLevelOptionSysId The adminLevelOptionSysId to set.
	 */
	public void setAdminLevelOptionSysId(int adminLevelOptionSysId) {
		this.adminLevelOptionSysId = adminLevelOptionSysId;
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
	public String getNotesExists() {
		return notesExists;
	}
	public void setNotesExists(String notesExists) {
		this.notesExists = notesExists;
	}
	public String getBnftDefId() {
		return bnftDefId;
	}
	public void setBnftDefId(String bnftDefId) {
		this.bnftDefId = bnftDefId;
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