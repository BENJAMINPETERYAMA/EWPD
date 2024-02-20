/*
 * ProductStructureLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitComponentLocateCriteria extends
        LocateCriteria {

    private String productStructureName;

    private List lineOfBusiness;

    private List businessEntity;

    private List businessGroup;

    private String effectiveDate;

    private String expiryDate;

    private int productStructureId;


    /**
     * Returns the businessEntity
     * 
     * @return List businessEntity.
     */

    public List getBusinessEntity() {
        return businessEntity;
    }


    /**
     * Sets the businessEntity
     * 
     * @param businessEntity.
     */

    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * Returns the businessGroup
     * 
     * @return List businessGroup.
     */

    public List getBusinessGroup() {
        return businessGroup;
    }


    /**
     * Sets the businessGroup
     * 
     * @param businessGroup.
     */

    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     */

    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the lineOfBusiness
     * 
     * @return List lineOfBusiness.
     */

    public List getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * Sets the lineOfBusiness
     * 
     * @param lineOfBusiness.
     */

    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * Returns the productStructureName
     * 
     * @return String productStructureName.
     */

    public String getProductStructureName() {
        return productStructureName;
    }


    /**
     * Sets the productStructureName
     * 
     * @param productStructureName.
     */

    public void setProductStructureName(String productStructureName) {
        this.productStructureName = productStructureName;
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
}