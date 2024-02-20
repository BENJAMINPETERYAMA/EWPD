/*
 * AssociatedQuestionBO.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.bo;

import java.util.Date;
import java.util.List;
import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * Business Object for the associated question for an Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AssociatedQuestionBO {

    private int adminOptionId = -1;

    private String adminOptionName;

    private int associatedQuestionId = -1;

    private String associatedQuestionDesc;

    private int seqNumber = -1;

    private String createdUser;

    private String lastUpdatedUser;

    private Date createdTimestamp;

    private Date lastUpdatedTimestamp;

    private String referenceId;

    private String referenceDesc;

    private List associatedQuestionList;
    //added new field
    private boolean isModified;


    /**
     * @return isModified
     * 
     * Returns the isModified.
     */
    public boolean isModified() {
        return isModified;
    }
    /**
     * @param isModified
     * 
     * Sets the isModified.
     */
    public void setModified(boolean isModified) {
        this.isModified = isModified;
    }
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
     * Returns the adminOptionName
     * 
     * @return String adminOptionName.
     */
    public String getAdminOptionName() {
        return adminOptionName;
    }


    /**
     * Sets the adminOptionName
     * 
     * @param adminOptionName.
     */
    public void setAdminOptionName(String adminOptionName) {
        this.adminOptionName = adminOptionName;
    }


    /**
     * Returns the associatedQuestionId
     * 
     * @return int associatedQuestionId.
     */
    public int getAssociatedQuestionId() {
        return associatedQuestionId;
    }


    /**
     * Sets the associatedQuestionId
     * 
     * @param associatedQuestionId.
     */
    public void setAssociatedQuestionId(int associatedQuestionId) {
        this.associatedQuestionId = associatedQuestionId;
    }


    /**
     * Returns the seqNumber
     * 
     * @return int seqNumber.
     */
    public int getSeqNumber() {
        return seqNumber;
    }


    /**
     * Sets the seqNumber
     * 
     * @param seqNumber.
     */
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }


    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param object
     * @return boolean
     */
    public boolean equals(BusinessObject object) {
        return true;
    }


    /**
     * Implementation of the abstract method of the super class.
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return int
     */
    public int hashCode() {
        return 1;
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
        buffer.append(", adminOptionName = ").append(this.adminOptionName);
        buffer.append(", associatedQuestionId = ").append(
                this.associatedQuestionId);
        buffer.append(", associatedQuestionDesc = ").append(
                this.associatedQuestionDesc);
        buffer.append(", seqNumber = ").append(this.seqNumber);
        return buffer.toString();
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
     * Returns the associatedQuestionDesc
     * 
     * @return String associatedQuestionDesc.
     */
    public String getAssociatedQuestionDesc() {
        return associatedQuestionDesc;
    }


    /**
     * Sets the associatedQuestionDesc
     * 
     * @param associatedQuestionDesc.
     */
    public void setAssociatedQuestionDesc(String associatedQuestionDesc) {
        this.associatedQuestionDesc = associatedQuestionDesc;
    }


    /**
     * Returns the referenceDesc.
     * 
     * @return referenceDesc
     */
    public String getReferenceDesc() {
        return referenceDesc;
    }


    /**
     * Sets the referenceDesc
     * 
     * @param referenceDesc
     */
    public void setReferenceDesc(String referenceDesc) {
        this.referenceDesc = referenceDesc;
    }


    /**
     * Returns the referenceId.
     * 
     * @return referenceId
     */
    public String getReferenceId() {
        return referenceId;
    }


    /**
     * Sets the referenceId
     * 
     * @param referenceId
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }


    /**
     * Returns the associatedQuestionList
     * 
     * @return List associatedQuestionList.
     */
    public List getAssociatedQuestionList() {
        return associatedQuestionList;
    }


    /**
     * Sets the associatedQuestionList
     * 
     * @param associatedQuestionList.
     */
    public void setAssociatedQuestionList(List associatedQuestionList) {
        this.associatedQuestionList = associatedQuestionList;
    }
}