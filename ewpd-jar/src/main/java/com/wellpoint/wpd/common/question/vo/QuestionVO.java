/*
 * QuestionVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.vo;

import java.util.Date;
import java.util.List;
import com.wellpoint.wpd.common.bo.State;

/**
 * Value object for question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionVO {

    private int questionNumber;

    private String questionDesc;

    private int dataTypeId;

    List answerList;

    private int version = -1;

    private String status;

    private State state;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private int questionNumberParentSysId;
    
    private String pva;
    
    private String functionalDomainCD;
    
    private String functionalDomainDesc;
    
    private List functionalDomainCDList;
    
    private List functionalDomainDescList;
    
    
    
    private String qualifier;
    
    private String term;
    
    private String spsReference;
    
    private String benefitLineDataType;
    
    

	/**
	 * @return Returns the functionalDomainDesc.
	 */
	public String getFunctionalDomainDesc() {
		return functionalDomainDesc;
	}
	/**
	 * @param functionalDomainDesc The functionalDomainDesc to set.
	 */
	public void setFunctionalDomainDesc(String functionalDomainDesc) {
		this.functionalDomainDesc = functionalDomainDesc;
	}
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
	 * @return Returns the functionalDomainCDList.
	 */
	public List getFunctionalDomainCDList() {
		return functionalDomainCDList;
	}
	/**
	 * @param functionalDomainCDList The functionalDomainCDList to set.
	 */
	public void setFunctionalDomainCDList(List functionalDomainCDList) {
		this.functionalDomainCDList = functionalDomainCDList;
	}
	/**
	 * @return Returns the functionalDomainDescList.
	 */
	public List getFunctionalDomainDescList() {
		return functionalDomainDescList;
	}
	/**
	 * @param functionalDomainDescList The functionalDomainDescList to set.
	 */
	public void setFunctionalDomainDescList(List functionalDomainDescList) {
		this.functionalDomainDescList = functionalDomainDescList;
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
     * Returns the version
     * 
     * @return int version.
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version
     * 
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Returns the status
     * 
     * @return String status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status
     * 
     * @param status.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Returns the createdTimestamp
     * 
     * @return Date createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * Sets the createdTimestamp
     * 
     * @param createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * Returns the createdUser
     * 
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * Sets the createdUser
     * 
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * Returns the lastUpdatedTimestamp
     * 
     * @return Date lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * Sets the lastUpdatedTimestamp
     * 
     * @param lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * Returns the lastUpdatedUser
     * 
     * @return String lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * Sets the lastUpdatedUser
     * 
     * @param lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * Returns the state
     * 
     * @return State state.
     */
    public State getState() {
        return state;
    }


    /**
     * Sets the state
     * 
     * @param state.
     */
    public void setState(State state) {
        this.state = state;
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
        buffer.append(", version = ").append(this.version);
        buffer.append(", status = ").append(this.status);
        buffer.append("state = ").append(this.state);
        buffer.append(", answerList = ").append(this.answerList);
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
	 * @return Returns the pva.
	 */
	public String getPva() {
		return pva;
	}
	/**
	 * @param pva The pva to set.
	 */
	public void setPva(String pva) {
		this.pva = pva;
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
	public String getBenefitLineDataType() {
		return benefitLineDataType;
	}
	public void setBenefitLineDataType(String benefitLineDataType) {
		this.benefitLineDataType = benefitLineDataType;
	}
	
	
}