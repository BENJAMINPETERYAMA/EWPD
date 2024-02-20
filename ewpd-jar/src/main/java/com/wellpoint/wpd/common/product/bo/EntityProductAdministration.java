/*
 * EntityProductAdministration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.override.benefit.bo.Questionnaire;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EntityProductAdministration  extends Questionnaire{
    
    private int entityId;
	private String entityType;
    private int questionNumber;
    private String questionDesc;
    private String dataTypeId;
    private String dataTypeDesc;
    private int answerId;
    private String answerDesc;
    private List answers;
    private String referenceId;
    private String referenceDesc;
    private int beneftComponentId;
    private int adminOptSysId;
    private String dataTypeLegend;
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimeStamp;
    
	private String possibleAnsId;
	
	
	private List possibleAnswerList;
	
	
	private int parentQuestionnaireId;
	
	private int selectedAnswerid;
	
    private String notesExists;
    
	private boolean noteAttached = false;

	private boolean noNoteAttached = true;
	
	private String validDomainToAttach;
	
	private int productId;
	
	private boolean unsavedData = true;

	public boolean isNoNoteAttached() {

		if(("N").equalsIgnoreCase(this.getValidDomainToAttach()))
			return false;
		
		if ("Y".equalsIgnoreCase(this.notesExists))
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
	 * @return Returns the possibleAnsId.
	 */
	public String getPossibleAnsId() {
		return possibleAnsId;
	}
	/**
	 * @param possibleAnsId The possibleAnsId to set.
	 */
	public void setPossibleAnsId(String possibleAnsId) {
		this.possibleAnsId = possibleAnsId;
	}
	
    /**
     * Returns the adminOptSysId
     * @return int adminOptSysId.
     */
    public int getAdminOptSysId() {
        return adminOptSysId;
    }
    /**
     * Sets the adminOptSysId
     * @param adminOptSysId.
     */
    public void setAdminOptSysId(int adminOptSysId) {
        this.adminOptSysId = adminOptSysId;
    }
    /**
     * Returns the answerDesc
     * @return String answerDesc.
     */
    public String getAnswerDesc() {
        return answerDesc;
    }
    /**
     * Sets the answerDesc
     * @param answerDesc.
     */
    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc;
    }
    /**
     * Returns the answerId
     * @return int answerId.
     */
    public int getAnswerId() {
        return answerId;
    }
    /**
     * Sets the answerId
     * @param answerId.
     */
    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
    /**
     * Returns the answers
     * @return List answers.
     */
    public List getAnswers() {
        return answers;
    }
    /**
     * Sets the answers
     * @param answers.
     */
    public void setAnswers(List answers) {
        this.answers = answers;
    }
    /**
     * Returns the beneftComponentId
     * @return int beneftComponentId.
     */
    public int getBeneftComponentId() {
        return beneftComponentId;
    }
    /**
     * Sets the beneftComponentId
     * @param beneftComponentId.
     */
    public void setBeneftComponentId(int beneftComponentId) {
        this.beneftComponentId = beneftComponentId;
    }
    /**
     * Returns the dataTypeDesc
     * @return String dataTypeDesc.
     */
    public String getDataTypeDesc() {
        return dataTypeDesc;
    }
    /**
     * Sets the dataTypeDesc
     * @param dataTypeDesc.
     */
    public void setDataTypeDesc(String dataTypeDesc) {
        this.dataTypeDesc = dataTypeDesc;
    }
    /**
     * Returns the dataTypeId
     * @return String dataTypeId.
     */
    public String getDataTypeId() {
        return dataTypeId;
    }
    /**
     * Sets the dataTypeId
     * @param dataTypeId.
     */
    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
    /**
     * Returns the entityId
     * @return int entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * Sets the entityId
     * @param entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
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
     * Returns the questionDesc
     * @return String questionDesc.
     */
    public String getQuestionDesc() {
        return questionDesc;
    }
    /**
     * Sets the questionDesc
     * @param questionDesc.
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }
    /**
     * Returns the questionNumber
     * @return int questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }
    /**
     * Sets the questionNumber
     * @param questionNumber.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
    /**
     * Returns the referenceDesc
     * @return String referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }
    /**
     * Sets the referenceDesc
     * @param referenceDesc.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }
    /**
     * Returns the referenceId
     * @return int referenceId.
     */
    public String getReferenceId() {
        return referenceId;
    }
    /**
     * Sets the referenceId
     * @param referenceId.
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
    
    public String getDataTypeLegend() {
        return dataTypeLegend;
    }
    
    public void setDataTypeLegend(String dataTypeLegend) {
        this.dataTypeLegend = dataTypeLegend;
    }
	/**
	 * @return Returns the lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * @param lastUpdatedTimeStamp The lastUpdatedTimeStamp to set.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
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
	public String getValidDomainToAttach() {
		return validDomainToAttach;
	}
	public void setValidDomainToAttach(String validDomainToAttach) {
		this.validDomainToAttach = validDomainToAttach;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
