/*
 * BenefitHierarchyAssociationBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

import java.util.Date;

/**
 * Business Object for BenefitHierarchyAssociation
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyAssociationBO {

    private int sequenceNumber;

    private String benefitName;

    private int benefitId;

    private int benefitHierarchyId;

    private int benefitComponentId;

    private int benefitHierarchyAssociationId;

    private String createdUser;

    private String lastUpdatedUser;

    private Date createdTimestamp;

    private Date lastUpdatedTimestamp;

    private String benefitValid;
    
    /**
     * @return Returns the benefitHierarchyAssociationId.
     */
    public int getBenefitHierarchyAssociationId() {
        return benefitHierarchyAssociationId;
    }


    /**
     * @param benefitHierarchyAssociationId
     *            The benefitHierarchyAssociationId to set.
     */
    public void setBenefitHierarchyAssociationId(
            int benefitHierarchyAssociationId) {
        this.benefitHierarchyAssociationId = benefitHierarchyAssociationId;
    }


    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     *            The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return Returns the benefitHierarchyId.
     */
    public int getBenefitHierarchyId() {
        return benefitHierarchyId;
    }


    /**
     * @param benefitHierarchyId
     *            The benefitHierarchyId to set.
     */
    public void setBenefitHierarchyId(int benefitHierarchyId) {
        this.benefitHierarchyId = benefitHierarchyId;
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
     * @return Returns the sequenceNumber.
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }


    /**
     * @param sequenceNumber
     *            The sequenceNumber to set.
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("sequenceNumber = ").append(this.sequenceNumber);
        buffer.append(", benefitName = ").append(this.benefitName);
        buffer.append(", benefitId = ").append(this.benefitId);
        buffer.append(", benefitHierarchyId = ")
                .append(this.benefitHierarchyId);
        buffer.append(", benefitComponentId = ")
                .append(this.benefitComponentId);
        buffer.append(", benefitHierarchyAssociationId = ").append(
                this.benefitHierarchyAssociationId);
        buffer.append(", isBenefitValid = ").append(this.benefitValid);
        return buffer.toString();
    }


    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
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
     * @param createdUser
     *            The createdUser to set.
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
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
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
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

	/**
	 * Returns the benefitValid
	 * @return String benefitValid.
	 */
	public String getBenefitValid() {
		return benefitValid;
	}
	/**
	 * Sets the benefitValid
	 * @param benefitValid.
	 */
	public void setBenefitValid(String benefitValid) {
		this.benefitValid = benefitValid;
	}
}