/*
 * SelectedQuestionListBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SelectedQuestionListBO {
    // variable declarations
    private int adminOptionsSysId;
    private int sequenceNumber;
    private String questionDesc;
    private int questionNumber;
    private List previousQnNumber;
    private String answer;
    private List questionList;
    private int answerId;
    private int systemId;
    private String createdUser;
    private String lastUpdatedUser;
    private Date createdDate;
    private Date lastChangedDate;
    private String isOpen;
    
    
    // For persisting from ADMIN_OPT_TO_QSTN_ASSN   
    private int adminLevelOptionSysAssnId;
    
 // Enhancement
    private String referenceId;
    private String referenceDesc;
    /**
     * @return isOpen
     * 
     * Returns the isOpen.
     */
    public String getIsOpen() {
        return isOpen;
    }
    /**
     * @param isOpen
     * 
     * Sets the isOpen.
     */
    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
 // End - Enhancement   
    
	
	/**
	 * @return Returns the adminLevelOptionSysAssnId.
	 */
	public int getAdminLevelOptionSysAssnId() {
		return adminLevelOptionSysAssnId;
	}
	/**
	 * @param adminLevelOptionSysAssnId The adminLevelOptionSysAssnId to set.
	 */
	public void setAdminLevelOptionSysAssnId(int adminLevelOptionSysAssnId) {
		this.adminLevelOptionSysAssnId = adminLevelOptionSysAssnId;
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
     * @return Returns the answer.
     */
    public String getAnswer() {
        return answer;
    }
    /**
     * @param answer The answer to set.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
     * @return Returns the questionList.
     */
    public List getQuestionList() {
        return questionList;
    }
    /**
     * @param questionList The questionList to set.
     */
    public void setQuestionList(List questionList) {
        this.questionList = questionList;
    }
	/**
	 * Returns the systemId
	 * @return int systemId.
	 */
	public int getSystemId() {
		return systemId;
	}
	/**
	 * Sets the systemId
	 * @param systemId.
	 */
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	/**
	 * Returns the adminOptionsSysId
	 * @return int adminOptionsSysId.
	 */
	public int getAdminOptionsSysId() {
		return adminOptionsSysId;
	}
	/**
	 * Sets the adminOptionsSysId
	 * @param adminOptionsSysId.
	 */
	public void setAdminOptionsSysId(int adminOptionsSysId) {
		this.adminOptionsSysId = adminOptionsSysId;
	}
    /**
     * @return Returns the createdDate.
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    /**
     * @param createdDate The createdDate to set.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
     * @return Returns the lastChangedDate.
     */
    public Date getLastChangedDate() {
        return lastChangedDate;
    }
    /**
     * @param lastChangedDate The lastChangedDate to set.
     */
    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
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
	 * @return Returns the previousQnNumber.
	 */
	public List getPreviousQnNumber() {
		return previousQnNumber;
	}
	/**
	 * @param previousQnNumber The previousQnNumber to set.
	 */
	public void setPreviousQnNumber(List previousQnNumber) {
		this.previousQnNumber = previousQnNumber;
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
}
