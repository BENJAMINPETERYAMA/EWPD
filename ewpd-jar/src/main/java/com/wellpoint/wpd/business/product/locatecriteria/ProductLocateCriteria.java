/*
 * ProductLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

import java.util.Date;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductLocateCriteria extends LocateCriteria {

	private String productDesc;
	private String productName;
	private List lineOfBusinessList;
	    
    private List businessEntityList;
    
    private List businessGroupList;
    private List productStructureList;
    
    private List productFamilyList;
    private Date effectiveDate;
    
    private Date expiryDate;
    
    private String productType;
    //CARS START
    private List marketBusinessUnitList;
	//CARS END

	/**
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the businessEntityList.
	 */
	public List getBusinessEntityList() {
		return businessEntityList;
	}
	/**
	 * @param businessEntityList The businessEntityList to set.
	 */
	public void setBusinessEntityList(List businessEntityList) {
		this.businessEntityList = businessEntityList;
	}
	/**
	 * @return Returns the businessGroupList.
	 */
	public List getBusinessGroupList() {
		return businessGroupList;
	}
	/**
	 * @param businessGroupList The businessGroupList to set.
	 */
	public void setBusinessGroupList(List businessGroupList) {
		this.businessGroupList = businessGroupList;
	}
	/**
	 * @return Returns the lineOfBusinessList.
	 */
	public List getLineOfBusinessList() {
		return lineOfBusinessList;
	}
	/**
	 * @param lineOfBusinessList The lineOfBusinessList to set.
	 */
	public void setLineOfBusinessList(List lineOfBusinessList) {
		this.lineOfBusinessList = lineOfBusinessList;
	}
	/**
	 * @return Returns the productFamilyList.
	 */
	public List getProductFamilyList() {
		return productFamilyList;
	}
	/**
	 * @param productFamilyList The productFamilyList to set.
	 */
	public void setProductFamilyList(List productFamilyList) {
		this.productFamilyList = productFamilyList;
	}
	/**
	 * @return Returns the productStructureList.
	 */
	public List getProductStructureList() {
		return productStructureList;
	}
	/**
	 * @param productStructureList The productStructureList to set.
	 */
	public void setProductStructureList(List productStructureList) {
		this.productStructureList = productStructureList;
	}
	/**
	 * @return Returns the productDesc.
	 */
	public String getProductDesc() {
		return productDesc;
	}
	/**
	 * @param productDesc The productDesc to set.
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
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
