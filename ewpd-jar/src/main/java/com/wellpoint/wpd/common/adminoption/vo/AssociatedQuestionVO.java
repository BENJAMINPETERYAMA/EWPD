/*
 * AssociatedQuestionVO.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.vo;

import java.util.List;

/**
 * Value Object for the associated question for an Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AssociatedQuestionVO {

    private int adminOptionId = -1;

    private String adminOptionName;

    private int associatedQuestionId = -1;

    private int seqNumber = -1;

    private String referenceId;

    private List associatedQuestionsList;

    private int version;
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
     * Returns the associatedQuestionsList
     * 
     * @return List associatedQuestionsList.
     */
    public List getAssociatedQuestionsList() {
        return associatedQuestionsList;
    }


    /**
     * Sets the associatedQuestionsList
     * 
     * @param associatedQuestionsList.
     */
    public void setAssociatedQuestionsList(List associatedQuestionsList) {
        this.associatedQuestionsList = associatedQuestionsList;
    }


    /**
     * Returns the version.
     * 
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version
     * 
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
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
        buffer.append(", seqNumber = ").append(this.seqNumber);
        buffer.append(", version = ").append(this.version);
        return buffer.toString();
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
}