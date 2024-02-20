/*
 * BenefitLineOverrideInfo.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.Date;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLineOverrideInfo {
    private int entitySystemId;
    private String entityType;
    private int benefitLineId;
    private String benefitValue;
    private String createdUser;
    private Date createdTime;
    private String lastUpdatedUser;
    private Date lastUpdatedTime;

    /**
     * Returns the benefitLineId
     * @return int benefitLineId.
     */
    public int getBenefitLineId() {
        return benefitLineId;
    }
    /**
     * Sets the benefitLineId
     * @param benefitLineId.
     */
    public void setBenefitLineId(int benefitLineId) {
        this.benefitLineId = benefitLineId;
    }
    /**
     * Returns the benefitValue
     * @return String benefitValue.
     */
    public String getBenefitValue() {
        return benefitValue;
    }
    /**
     * Sets the benefitValue
     * @param benefitValue.
     */
    public void setBenefitValue(String benefitValue) {
        this.benefitValue = benefitValue;
    }
    /**
     * Returns the createdTime
     * @return Date createdTime.
     */
    public Date getCreatedTime() {
        return createdTime;
    }
    /**
     * Sets the createdTime
     * @param createdTime.
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * Returns the entitySystemId
     * @return int entitySystemId.
     */
    public int getEntitySystemId() {
        return entitySystemId;
    }
    /**
     * Sets the entitySystemId
     * @param entitySystemId.
     */
    public void setEntitySystemId(int entitySystemId) {
        this.entitySystemId = entitySystemId;
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
     * Returns the lastUpdatedTime
     * @return Date lastUpdatedTime.
     */
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }
    /**
     * Sets the lastUpdatedTime
     * @param lastUpdatedTime.
     */
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    /**
     * Returns the lastUpdatedUser
     * @return String lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
}
