/*
 * AdminOptionVO.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.vo;

import java.util.Date;
import java.util.List;
import com.wellpoint.wpd.common.bo.State;

/**
 * Value Object for Admin Options
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionVO {

    private int adminOptionId = -1;

    private String adminName;

    private String termId;

    private String qualifierId;

    private String referenceId;

    private String termDesc;

    private String qualifierDesc;

    private String referenceDesc;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private int version = -1;

    private String status;

    private List benefitTermList;

    private List benefitQualifierList;

    private State stateObject;

    private String stateValue;

    private int maxLocateResultSize;

    private List associatedQuestionsList;

    private int adminOptionParentSysId;
    
    /**
     * Returns the adminOptionId
     * 
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }


    /**
     * Sets the adminOptionId
     * 
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }


    /**
     * Returns the adminName
     * 
     * @return String adminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * Sets the adminName
     * 
     * @param adminName.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * Returns the qualifierId
     * 
     * @return String qualifierId.
     */
    public String getQualifierId() {
        return qualifierId;
    }


    /**
     * Sets the qualifierId
     * 
     * @param qualifierId.
     */
    public void setQualifierId(String qualifierId) {
        this.qualifierId = qualifierId;
    }


    /**
     * Returns the referenceId
     * 
     * @return String referenceId.
     */
    public String getReferenceId() {
        return referenceId;
    }


    /**
     * Sets the referenceId
     * 
     * @param referenceId.
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }


    /**
     * Returns the termId
     * 
     * @return String termId.
     */
    public String getTermId() {
        return termId;
    }


    /**
     * Sets the termId
     * 
     * @param termId.
     */
    public void setTermId(String termId) {
        this.termId = termId;
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
     * Returns the qualifierDesc
     * 
     * @return String qualifierDesc.
     */
    public String getQualifierDesc() {
        return qualifierDesc;
    }


    /**
     * Sets the qualifierDesc
     * 
     * @param qualifierDesc.
     */
    public void setQualifierDesc(String qualifierDesc) {
        this.qualifierDesc = qualifierDesc;
    }


    /**
     * Returns the referenceDesc
     * 
     * @return String referenceDesc.
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }


    /**
     * Sets the referenceDesc
     * 
     * @param referenceDesc.
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }


    /**
     * Returns the termDesc
     * 
     * @return String termDesc.
     */
    public String getTermDesc() {
        return termDesc;
    }


    /**
     * Sets the termDesc
     * 
     * @param termDesc.
     */
    public void setTermDesc(String termDesc) {
        this.termDesc = termDesc;
    }


    /**
     * Returns the benefitTermList
     * 
     * @return List benefitTermList.
     */
    public List getBenefitTermList() {
        return benefitTermList;
    }


    /**
     * Sets the benefitTermList
     * 
     * @param benefitTermList.
     */
    public void setBenefitTermList(List benefitTermList) {
        this.benefitTermList = benefitTermList;
    }


    /**
     * Returns the benefitQualifierList
     * 
     * @return List benefitQualifierList.
     */
    public List getBenefitQualifierList() {
        return benefitQualifierList;
    }


    /**
     * Sets the benefitQualifierList
     * 
     * @param benefitQualifierList.
     */
    public void setBenefitQualifierList(List benefitQualifierList) {
        this.benefitQualifierList = benefitQualifierList;
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
     * @return Returns the stateObject.
     */
    public State getStateObject() {
        return stateObject;
    }


    /**
     * @param stateObject
     *            The stateObject to set.
     */
    public void setStateObject(State stateObject) {
        this.stateObject = stateObject;
    }


    /**
     * @return Returns the stateValue.
     */
    public String getStateValue() {
        return stateValue;
    }


    /**
     * @param stateValue
     *            The stateValue to set.
     */
    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }


    /**
     * Returns the maxLocateResultSize.
     * 
     * @return maxLocateResultSize
     */
    public int getMaxLocateResultSize() {
        return maxLocateResultSize;
    }


    /**
     * Sets the maxLocateResultSize.
     * 
     * @param maxLocateResultSize
     */
    public void setMaxLocateResultSize(int maxLocateResultSize) {
        this.maxLocateResultSize = maxLocateResultSize;
    }


    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return String
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("adminOptionId = ").append(this.adminOptionId);
        buffer.append(", adminName = ").append(this.adminName);
        buffer.append(", termId = ").append(this.termId);
        buffer.append(", termDesc = ").append(this.termDesc);
        buffer.append(", qualifierId = ").append(this.qualifierId);
        buffer.append(", qualifierDesc = ").append(this.qualifierDesc);
        buffer.append(", referenceId = ").append(this.referenceId);
        buffer.append(", referenceDesc = ").append(this.referenceDesc);
        buffer.append(", version = ").append(this.version);
        buffer.append(", status = ").append(this.status);
        buffer.append(", stateObject = ").append(this.stateObject);
        buffer.append(", stateValue = ").append(this.stateValue);
        return buffer.toString();
    }


    /**
     * Returns the associatedQuestionsList.
     * 
     * @return associatedQuestionsList
     */
    public List getAssociatedQuestionsList() {
        return associatedQuestionsList;
    }


    /**
     * Sets the associatedQuestionsList.
     * 
     * @param associatedQuestionsList
     */
    public void setAssociatedQuestionsList(List associatedQuestionsList) {
        this.associatedQuestionsList = associatedQuestionsList;
    }
	/**
	 * @return Returns the adminOptionParentSysId.
	 */
	public int getAdminOptionParentSysId() {
		return adminOptionParentSysId;
	}
	/**
	 * @param adminOptionParentSysId The adminOptionParentSysId to set.
	 */
	public void setAdminOptionParentSysId(int adminOptionParentSysId) {
		this.adminOptionParentSysId = adminOptionParentSysId;
	}
  
}