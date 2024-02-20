/*
 * QuestionBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.bo;

import java.util.List;
import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * Business object for questions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionBO extends BusinessObject {

    private int questionNumber;

    private String questionDesc;

    private int dataTypeId;

    private String dataTypeName;

    private List answerList;
    
    private String displayQuestion;
    
    private int questionNumberParentSysId;
    
    private String providerArrangement;
    
    private List functionalDomainList;
    
    private String functionalDomainCD;
    
    private String domainDesc;
    
    private String noteId;
    
    private String qualifier;
    
    private String term;
    
    private String spsReference;
    
    private String description;
    
    private boolean adminMethodMapped;
    
    private String adminMethodMapIndicator;
    
    private boolean deleteFlag;
    
    private String benefitLineDataType;
    
	/**
	 * @return Returns the functionalDomainCD.
	 */
	public String getFunctionalDomainCD() {
		return functionalDomainCD;
	}
	/**
	 * @param functionalDomainCD The functionalDomainCD to set.
	 */
	public void setFunctionalDomainCD(String functionalDomainCD) {
		this.functionalDomainCD = functionalDomainCD;
	}
    /**
     * @return displayQuestion
     * 
     * Returns the displayQuestion.
     */
    public String getDisplayQuestion() {
        return displayQuestion;
    }
    /**
     * @param displayQuestion
     * 
     * Sets the displayQuestion.
     */
    public void setDisplayQuestion(String displayQuestion) {
        this.displayQuestion = displayQuestion;
    }
    /**
     * Returns the dataTypeName.
     * 
     * @return dataTypeName
     */
    public String getDataTypeName() {
        return dataTypeName;
    }


    /**
     * Set the dataTypeName
     * 
     * @param dataTypeName
     */
    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }


    /**
     * Returns the dataTypeId
     * 
     * @return int dataTypeId.
     */
    public int getDataTypeId() {
        return dataTypeId;
    }


    /**
     * Sets the dataTypeId
     * 
     * @param dataTypeId.
     */
    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }


    /**
     * Returns the questionDesc
     * 
     * @return String questionDesc.
     */
    public String getQuestionDesc() {
        return questionDesc;
    }


    /**
     * Sets the questionDesc
     * 
     * @param questionDesc.
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }


    /**
     * Returns the questionNumber
     * 
     * @return int questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }


    /**
     * Sets the questionNumber
     * 
     * @param questionNumber.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    /**
     * Returns the answerList
     * 
     * @return List answerList.
     */
    public List getAnswerList() {
        return answerList;
    }


    /**
     * Sets the answerList
     * 
     * @param answerList.
     */
    public void setAnswerList(List answerList) {
        this.answerList = answerList;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param object
     * @return boolean
     */
    public boolean equals(BusinessObject object) {
        return true;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return int
     */
    public int hashCode() {
        return 1;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("questionNumber = ").append(this.questionNumber);
        buffer.append(", questionDesc = ").append(this.questionDesc);
        buffer.append(", dataTypeId = ").append(this.dataTypeId);
        buffer.append(", dataTypeName = ").append(this.dataTypeName);
        return buffer.toString();
    }
	/**
	 * @return Returns the questionNumberParentSysId.
	 */
	public int getQuestionNumberParentSysId() {
		return questionNumberParentSysId;
	}
	/**
	 * @param questionNumberParentSysId The questionNumberParentSysId to set.
	 */
	public void setQuestionNumberParentSysId(int questionNumberParentSysId) {
		this.questionNumberParentSysId = questionNumberParentSysId;
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
	 * @return Returns the functionalDomainList.
	 */
	public List getFunctionalDomainList() {
		return functionalDomainList;
	}
	/**
	 * @param functionalDomainList The functionalDomainList to set.
	 */
	public void setFunctionalDomainList(List functionalDomainList) {
		this.functionalDomainList = functionalDomainList;
	}
	/**
	 * @return Returns the domainDesc.
	 */
	public String getDomainDesc() {
		return domainDesc;
	}
	/**
	 * @param domainDesc The domainDesc to set.
	 */
	public void setDomainDesc(String domainDesc) {
		this.domainDesc = domainDesc;
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
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public String getSpsReference() {
		return spsReference;
	}
	public void setSpsReference(String spsReference) {
		this.spsReference = spsReference;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isAdminMethodMapped() {
		return adminMethodMapped;
	}
	public void setAdminMethodMapped(boolean adminMethodMapped) {
		this.adminMethodMapped = adminMethodMapped;
	}
	public String getAdminMethodMapIndicator() {
		return adminMethodMapIndicator;
	}
	public void setAdminMethodMapIndicator(String adminMethodMapIndicator) {
		this.adminMethodMapIndicator = adminMethodMapIndicator;
		if("Y".equalsIgnoreCase(adminMethodMapIndicator)){
			this.adminMethodMapped = false;
			deleteFlag = false;
		}
		else if (("N".equalsIgnoreCase(adminMethodMapIndicator) || null==this.adminMethodMapIndicator)){
			this.adminMethodMapped = true;
			deleteFlag = true;
		}
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
	public String getBenefitLineDataType() {
		return benefitLineDataType;
	}
	public void setBenefitLineDataType(String benefitLineDataType) {
		this.benefitLineDataType = benefitLineDataType;
	}
	
	
}