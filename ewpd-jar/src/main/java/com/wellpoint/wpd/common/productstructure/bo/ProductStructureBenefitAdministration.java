/*
 * ProductStructureBenefitAdministration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitAdministration {

    private int productStructureId;

    private int benefitComponentId;

    private int adminLevelOptionId;

    private int questionId;

    private int answerId;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private String adminOptionHideFlag;   
    
    private int enityid;
    
    private String entityType;


    /**
     *  
     */
    public ProductStructureBenefitAdministration() {
        super();
    }


    /**
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
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
     * Returns the benefitComponentId
     * 
     * @return int benefitComponentId.
     */

    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId
     * 
     * @param benefitComponentId.
     */

    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the adminLevelOptionId
     * 
     * @return int adminLevelOptionId.
     */

    public int getAdminLevelOptionId() {
        return adminLevelOptionId;
    }


    /**
     * Sets the adminLevelOptionId
     * 
     * @param adminLevelOptionId.
     */

    public void setAdminLevelOptionId(int adminLevelOptionId) {
        this.adminLevelOptionId = adminLevelOptionId;
    }


    /**
     * Returns the answerId
     * 
     * @return int answerId.
     */

    public int getAnswerId() {
        return answerId;
    }


    /**
     * Sets the answerId
     * 
     * @param answerId.
     */

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }


    /**
     * Returns the questionId
     * 
     * @return int questionId.
     */

    public int getQuestionId() {
        return questionId;
    }


    /**
     * Sets the questionId
     * 
     * @param questionId.
     */

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("productStructureId = ").append(
                this.getProductStructureId());
        buffer.append("benefitComponentId = ").append(
                this.getBenefitComponentId());
        buffer.append(", adminLevelOptionId = ").append(
                this.getAdminLevelOptionId());
        buffer.append(", questionId = ").append(this.getQuestionId());
        buffer.append(", answerId; = ").append(this.getAnswerId());
        buffer.append(", createdUser = ").append(this.getCreatedUser());
        buffer.append(", createdTimestamp = ").append(
                this.getCreatedTimestamp());
        buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
        buffer.append(", lastUpdatedTimestamp = ").append(
                this.getLastUpdatedTimestamp());
        return buffer.toString();

    }
	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}
	/**
	 * @param adminOptionHideFlag The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}
	
	
	/**
	 * @return Returns the enityid.
	 */
	public int getEnityid() {
		return enityid;
	}
	/**
	 * @param enityid The enityid to set.
	 */
	public void setEnityid(int enityid) {
		this.enityid = enityid;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
}