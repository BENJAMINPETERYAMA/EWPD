/*
 * ProductStructureLocateCriteriaVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.vo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureLocateCriteriaVO {

    private String productStructureName;

    private int version;

    private String effectiveDate;

    private String expiryDate;

    private List lineOfBusiness;

    private List businessEntity;

    private List businessGroup;

    private List associatedBenefitComponentList;
    //CARS START
    private List marketBusinessUnit;
    //CARS END

    /**
     * Returns the associatedBenefitComponentList
     * 
     * @return List associatedBenefitComponentList.
     */

    public List getAssociatedBenefitComponentList() {
        return associatedBenefitComponentList;
    }


    /**
     * Sets the associatedBenefitComponentList
     * 
     * @param associatedBenefitComponentList.
     */

    public void setAssociatedBenefitComponentList(
            List associatedBenefitComponentList) {
        this.associatedBenefitComponentList = associatedBenefitComponentList;
    }


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
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}