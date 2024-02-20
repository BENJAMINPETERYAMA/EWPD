/*
 * EntityBenefitDefenition.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EntityBenefitDefenition {
    private String entityType;
    private String entitySystemId;
    private List benefitLevels;
    /**
     * Returns the benefitLevels
     * @return List benefitLevels.
     */
    public List getBenefitLevels() {
        return benefitLevels;
    }
    /**
     * Sets the benefitLevels
     * @param benefitLevels.
     */
    public void setBenefitLevels(List benefitLevels) {
        this.benefitLevels = benefitLevels;
    }
    /**
     * Returns the entitySystemId
     * @return String entitySystemId.
     */
    public String getEntitySystemId() {
        return entitySystemId;
    }
    /**
     * Sets the entitySystemId
     * @param entitySystemId.
     */
    public void setEntitySystemId(String entitySystemId) {
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
}
