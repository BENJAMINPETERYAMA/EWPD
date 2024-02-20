/*
 * ContractTreeProducts.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractTreeProducts {
    
    private int contractId;
    
    private int productId;
    
    private String productName;
    
    private int dateSegmentId;
   
    /**
     * Returns the contractId
     * @return int contractId.
     */
    public int getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
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
     * @return dateSegmentId
     * 
     * Returns the dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * @param dateSegmentId
     * 
     * Sets the dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
}
