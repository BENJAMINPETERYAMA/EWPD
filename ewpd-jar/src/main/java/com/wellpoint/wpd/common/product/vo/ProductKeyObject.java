/*
 * ProductKeyObject.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.vo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductKeyObject {
    private int productId;
    private List businessDomains;
    private String productName;
    private int version;
    private String status;
    private int parentId;
    private String state;
    private String productType;

    
    /**
     * @see java.lang.Object#toString()
     * @return
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("productId = ").append(productId);
        buffer.append(", businessDomains = ").append(businessDomains);
        buffer.append(", productName = ").append(productName);
        buffer.append(", version = ").append(version);
        buffer.append(", status = ").append(status);
        buffer.append(", parentId = ").append(parentId);
        buffer.append(", state = ").append(state);
        buffer.append(", productType = ").append(productType);
        return buffer.toString();
    }
    
    /**
     * Returns the parentId
     * @return int parentId.
     */
    public int getParentId() {
        return parentId;
    }
    
    /**
     * Sets the parentId
     * @param parentId.
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    
    /**
     * Returns the productId
     * @return int productId.
     */
    public int getProductId() {
        return productId;
    }
    
    /**
     * Sets the productId
     * @param productId.
     */
    public void setProductId(int productId) {
        this.productId = productId;
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
     * Returns the version
     * @return int version.
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * Sets the version
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * Returns the status
     * @return String status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status
     * @param status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Returns the state
     * @return String state.
     */
    public String getState() {
        return state;
    }
    
    /**
     * Sets the state
     * @param state.
     */
    public void setState(String state) {
        this.state = state;
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
}
