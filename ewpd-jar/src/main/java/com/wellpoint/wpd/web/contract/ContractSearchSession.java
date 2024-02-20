/*
 * ContractSearchSession.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchSession {

    private int benefitId = -1;
	private int benefitComponentId = -1;
	private int productId;
	private String productName;
	

	private ContractSearchScreenValueObject contractSearchScreenValueObject; 




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
     * Returns the benefitComponentId
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * Sets the benefitComponentId
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * Returns the benefitId
     * @return int benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * Sets the benefitId
     * @param benefitId.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }

	/**
	 * Returns the contractSearchScreenValueObject
	 * @return ContractSearchScreenValueObject contractSearchScreenValueObject.
	 */
	public ContractSearchScreenValueObject getContractSearchScreenValueObject() {
		return contractSearchScreenValueObject;
	}
	/**
	 * Sets the contractSearchScreenValueObject
	 * @param contractSearchScreenValueObject.
	 */
	public void setContractSearchScreenValueObject(
			ContractSearchScreenValueObject contractSearchScreenValueObject) {
		this.contractSearchScreenValueObject = contractSearchScreenValueObject;
	}

}
