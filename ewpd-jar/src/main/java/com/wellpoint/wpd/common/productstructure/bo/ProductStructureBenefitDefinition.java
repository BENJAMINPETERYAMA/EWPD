/*
 * ProductStructureBenefitDefinition.java
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
public class ProductStructureBenefitDefinition {

    private int productStructureId;

    private int benefitComponentId;

    private int benefitLineId;

    private String benefitValue;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;


    /**
     *  
     */
    public ProductStructureBenefitDefinition() {
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
     * Returns the benefitLineId
     * 
     * @return int benefitLineId.
     */

    public int getBenefitLineId() {
        return benefitLineId;
    }


    /**
     * Sets the benefitLineId
     * 
     * @param benefitLineId.
     */

    public void setBenefitLineId(int benefitLineId) {
        this.benefitLineId = benefitLineId;
    }


    /**
     * Returns the benefitValue
     * 
     * @return String benefitValue.
     */

    public String getBenefitValue() {
        return benefitValue;
    }


    /**
     * Sets the benefitValue
     * 
     * @param benefitValue.
     */

    public void setBenefitValue(String benefitValue) {
        this.benefitValue = benefitValue;
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
        buffer.append(", benefitLineId = ").append(this.getBenefitLineId());
        buffer.append(", benefitValue = ").append(this.getBenefitValue());
        buffer.append(", createdUser = ").append(this.getCreatedUser());
        buffer.append(", createdTimestamp = ").append(
                this.getCreatedTimestamp());
        buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
        buffer.append(", lastUpdatedTimestamp = ").append(
                this.getLastUpdatedTimestamp());
        return buffer.toString();

    }
}