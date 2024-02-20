/*
 * BenefitComponentNotesLocateCriteria.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.productstructure.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate criteria class for benefit component Notes
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureNotesLocateCriteria extends LocateCriteria {

    private String entityType;

    private int entityId;

    private int secEntityId;

    private String secEntityType;

    private int benefitComponentId;

    private int action;


    /**
     * Gets the entity type
     * 
     * @return entityType.
     */
    public String getEntityType() {
        return entityType;
    }


    /**
     * @param entityType
     *            The entityType to set.
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }


    /**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }


    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }


    /**
     * @return benefitComponentId
     * 
     * Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     * 
     * Sets the benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return secEntityId
     * 
     * Returns the secEntityId.
     */
    public int getSecEntityId() {
        return secEntityId;
    }


    /**
     * @param secEntityId
     * 
     * Sets the secEntityId.
     */
    public void setSecEntityId(int secEntityId) {
        this.secEntityId = secEntityId;
    }


    /**
     * @return secEntityType
     * 
     * Returns the secEntityType.
     */
    public String getSecEntityType() {
        return secEntityType;
    }


    /**
     * @param secEntityType
     * 
     * Sets the secEntityType.
     */
    public void setSecEntityType(String secEntityType) {
        this.secEntityType = secEntityType;
    }


    /**
     * @return action
     * 
     * Returns the action.
     */
    public int getAction() {
        return action;
    }


    /**
     * @param action
     * 
     * Sets the action.
     */
    public void setAction(int action) {
        this.action = action;
    }
}