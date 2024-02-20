/*
 * RetrieveProductBenefitDefinitionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductBenefitDefinitionResponse extends WPDResponse{

    private List benefitDefinitionsList;
    
    private List productDuplicateReferenceList;
    
    private List criteriaList;
    
    private List lvlLineList;
    
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
	 * @return Returns the productDuplicateReferenceList.
	 */
	public List getProductDuplicateReferenceList() {
		return productDuplicateReferenceList;
	}
	/**
	 * @param productDuplicateReferenceList The productDuplicateReferenceList to set.
	 */
	public void setProductDuplicateReferenceList(
			List productDuplicateReferenceList) {
		this.productDuplicateReferenceList = productDuplicateReferenceList;
	}
	/**
	 * @return Returns the criteriaList.
	 */
	public List getCriteriaList() {
		return criteriaList;
	}
	/**
	 * @param criteriaList The criteriaList to set.
	 */
	public void setCriteriaList(List criteriaList) {
		this.criteriaList = criteriaList;
	}
	/**
	 * @return Returns the lvlLineList.
	 */
	public List getLvlLineList() {
		return lvlLineList;
	}
	/**
	 * @param lvlLineList The lvlLineList to set.
	 */
	public void setLvlLineList(List lvlLineList) {
		this.lvlLineList = lvlLineList;
	}
}
