/*
 * ProductVO.java
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
public class ProductVO {

	private int productKey;

	private String productName;

	private String productDesc;

	private int productStructureKey;

	private Date effectiveDate;

	private Date expiryDate;

	private String productFamilyId;

	//new fields

	private String productType;

	private String mandateType;

	private String mandateDesc;

	private String stateId;

	private String stateDesc;

	private String stateCode;

	//    private List lineOfBusinessList;
	//    
	//    private List businessEntityList;
	//    
	//    private List businessGroupList;

	private List businessDomains;
	
	private String hiddenProductType;

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("productKey = ").append(this.getProductKey());
		buffer.append(", productName = ").append(productName);
		buffer.append(", productDesc = ").append(productDesc);
		buffer.append(", productStructureKey = ").append(productStructureKey);
		buffer.append(", effectiveDate = ").append(effectiveDate);
		buffer.append(", expiryDate = ").append(expiryDate);
		buffer.append(", productFamilyId = ").append(productFamilyId);
		buffer.append(", businessDomains = ").append(businessDomains);
		return buffer.toString();
	}

	//    /**
	//     * Returns the businessEntityList
	//     * @return List businessEntityList.
	//     */
	//    public List getBusinessEntityList() {
	//        return businessEntityList;
	//    }
	//    
	//    /**
	//     * Sets the businessEntityList
	//     * @param businessEntityList.
	//     */
	//    public void setBusinessEntityList(List businessEntityList) {
	//        this.businessEntityList = businessEntityList;
	//    }
	//    
	//    /**
	//     * Returns the businessGroupList
	//     * @return List businessGroupList.
	//     */
	//    public List getBusinessGroupList() {
	//        return businessGroupList;
	//    }
	//    
	//    /**
	//     * Sets the businessGroupList
	//     * @param businessGroupList.
	//     */
	//    public void setBusinessGroupList(List businessGroupList) {
	//        this.businessGroupList = businessGroupList;
	//    }

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

	//    /**
	//     * Returns the lineOfBusinessList
	//     * @return List lineOfBusinessList.
	//     */
	//    public List getLineOfBusinessList() {
	//        return lineOfBusinessList;
	//    }
	//    
	//    /**
	//     * Sets the lineOfBusinessList
	//     * @param lineOfBusinessList.
	//     */
	//    public void setLineOfBusinessList(List lineOfBusinessList) {
	//        this.lineOfBusinessList = lineOfBusinessList;
	//    }

	/**
	 * Returns the productDesc
	 * @return String productDesc.
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * Sets the productDesc
	 * @param productDesc.
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * Returns the productFamilyId
	 * @return String productFamilyId.
	 */
	public String getProductFamilyId() {
		return productFamilyId;
	}

	/**
	 * Sets the productFamilyId
	 * @param productFamilyId.
	 */
	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
	}

	/**
	 * Returns the productKey
	 * @return int productKey.
	 */
	public int getProductKey() {
		return productKey;
	}

	/**
	 * Sets the productKey
	 * @param productKey.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
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
	 * Returns the productStructureKey
	 * @return int productStructureKey.
	 */
	public int getProductStructureKey() {
		return productStructureKey;
	}

	/**
	 * Sets the productStructureKey
	 * @param productStructureKey.
	 */
	public void setProductStructureKey(int productStructureKey) {
		this.productStructureKey = productStructureKey;
	}

	/**
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}

	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}

	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}

	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
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
	 * @return Returns the stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode The stateCode to set.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return Returns the mandateDesc.
	 */
	public String getMandateDesc() {
		return mandateDesc;
	}

	/**
	 * @param mandateDesc The mandateDesc to set.
	 */
	public void setMandateDesc(String mandateDesc) {
		this.mandateDesc = mandateDesc;
	}

	/**
	 * @return Returns the stateDesc.
	 */
	public String getStateDesc() {
		return stateDesc;
	}

	/**
	 * @param stateDesc The stateDesc to set.
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	/**
	 * @return Returns the stateId.
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId The stateId to set.
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return Returns the hiddenProductType.
	 */
	public String getHiddenProductType() {
		return hiddenProductType;
	}
	/**
	 * @param hiddenProductType The hiddenProductType to set.
	 */
	public void setHiddenProductType(String hiddenProductType) {
		this.hiddenProductType = hiddenProductType;
	}
}