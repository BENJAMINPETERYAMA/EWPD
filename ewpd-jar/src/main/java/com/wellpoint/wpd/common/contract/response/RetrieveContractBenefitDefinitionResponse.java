/*
 * RetrieveContractBenefitDefinitionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractBenefitDefinitionResponse extends ContractResponse {

    private List benefitDefinitionsList;
    private List contractDuplicateReferenceList;
    private String productName;
    private List tierCriteriaList;
    private List benefitLvlLineList;
    
    /**
     * Returns the benefitDefinitionsList
     * @return List benefitDefinitionsList.
     */
    public List getBenefitDefinitionsList() {
        return benefitDefinitionsList;
    }
    /**
     * Sets the benefitDefinitionsList
     * @param benefitDefinitionsList.
     */
    public void setBenefitDefinitionsList(List benefitDefinitionsList) {
        this.benefitDefinitionsList = benefitDefinitionsList;
    }
	/**
	 * @return Returns the contractDuplicateReferenceList.
	 */
	public List getContractDuplicateReferenceList() {
		return contractDuplicateReferenceList;
	}
	/**
	 * @param contractDuplicateReferenceList The contractDuplicateReferenceList to set.
	 */
	public void setContractDuplicateReferenceList(
			List contractDuplicateReferenceList) {
		this.contractDuplicateReferenceList = contractDuplicateReferenceList;
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
	 * @return Returns the tierCriteriaList.
	 */
	public List getTierCriteriaList() {
		return tierCriteriaList;
	}
	/**
	 * @param tierCriteriaList The tierCriteriaList to set.
	 */
	public void setTierCriteriaList(List tierCriteriaList) {
		this.tierCriteriaList = tierCriteriaList;
	}
	/**
	 * @return Returns the benefitLvlLineList.
	 */
	public List getBenefitLvlLineList() {
		return benefitLvlLineList;
	}
	/**
	 * @param benefitLvlLineList The benefitLvlLineList to set.
	 */
	public void setBenefitLvlLineList(List benefitLvlLineList) {
		this.benefitLvlLineList = benefitLvlLineList;
	}
}
