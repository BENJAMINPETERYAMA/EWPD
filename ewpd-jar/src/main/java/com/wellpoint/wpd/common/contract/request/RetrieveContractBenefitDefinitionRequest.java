/*
 * RetrieveContractProductBenefitDefinitionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractBenefitDefinitionRequest extends ContractRequest {

    private int benefitId;
    private int benefitComponentId;
    private int productId;
    private int action = 1;
    //Used for CR Contract Reference Validation 
    private String contractId;
    private boolean duplicateRefPopup;
    
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
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    
    

    /**
     * Returns the action
     * @return int action.
     */
    public int getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action.
     */
    public void setAction(int action) {
        this.action = action;
    }
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the duplicateRefPopup.
	 */
	public boolean isDuplicateRefPopup() {
		return duplicateRefPopup;
	}
	/**
	 * @param duplicateRefPopup The duplicateRefPopup to set.
	 */
	public void setDuplicateRefPopup(boolean duplicateRefPopup) {
		this.duplicateRefPopup = duplicateRefPopup;
	}
}
