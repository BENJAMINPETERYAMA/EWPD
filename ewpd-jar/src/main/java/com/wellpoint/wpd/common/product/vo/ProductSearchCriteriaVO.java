/*
 * ProductSearchCriteriaVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.vo;

import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductSearchCriteriaVO {

    private List lineOfBusinessList;

    private List businessEntityList;

    private List businessGroupList;

    private String productName;

    private Date effectiveDate;

    private Date expiryDate;

    private List productStructureList;

    private List productFamilyList;
    //CARS START
    private List marketBusinessUnitList;
    //CARS END
   
    
    /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("lineOfBusinessList = ").append(lineOfBusinessList);
        buffer.append(", businessEntityList = ").append(businessEntityList);
        buffer.append(", businessGroupList = ").append(businessGroupList);
        buffer.append(", marketBusinessUnitList = ").append(marketBusinessUnitList);
        buffer.append(", productName = ").append(productName);
        buffer.append(", effectiveDate = ").append(effectiveDate);
        buffer.append(", expiryDate = ").append(expiryDate);
        buffer.append(", productStructureList = ").append(productStructureList);
        buffer.append(", productFamilyList = ").append(productFamilyList);
        return buffer.toString();
    }
    
    /**
     * Returns the businessEntityList
     * @return List businessEntityList.
     */

    public List getBusinessEntityList() {
        return businessEntityList;
    }

    /**
     * Sets the businessEntityList
     * @param businessEntityList.
     */

    public void setBusinessEntityList(List businessEntityList) {
        this.businessEntityList = businessEntityList;
    }

    /**
     * Returns the businessGroupList
     * @return List businessGroupList.
     */

    public List getBusinessGroupList() {
        return businessGroupList;
    }

    /**
     * Sets the businessGroupList
     * @param businessGroupList.
     */

    public void setBusinessGroupList(List businessGroupList) {
        this.businessGroupList = businessGroupList;
    }

    /**
     * Returns the effectiveDate
     * @return Date effectiveDate.
     */

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the effectiveDate
     * @param effectiveDate.
     */

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Returns the expiryDate
     * @return Date expiryDate.
     */

    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiryDate
     * @param expiryDate.
     */

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the lineOfBusinessList
     * @return List lineOfBusinessList.
     */

    public List getLineOfBusinessList() {
        return lineOfBusinessList;
    }

    /**
     * Sets the lineOfBusinessList
     * @param lineOfBusinessList.
     */

    public void setLineOfBusinessList(List lineOfBusinessList) {
        this.lineOfBusinessList = lineOfBusinessList;
    }

    /**
     * Returns the productFamilyList
     * @return List productFamilyList.
     */

    public List getProductFamilyList() {
        return productFamilyList;
    }

    /**
     * Sets the productFamilyList
     * @param productFamilyList.
     */

    public void setProductFamilyList(List productFamilyList) {
        this.productFamilyList = productFamilyList;
    }

    /**
     * Returns the productName
     * @return String productName.
     */

    public String getProductName() {
        return productName;
    }

    /**
     * Sets the productName
     * @param productName.
     */

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Returns the productStructureList
     * @return List productStructureList.
     */

    public List getProductStructureList() {
        return productStructureList;
    }

    /**
     * Sets the productStructureList
     * @param productStructureList.
     */

    public void setProductStructureList(List productStructureList) {
        this.productStructureList = productStructureList;
    }

	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}