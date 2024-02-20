/*
 * SaveProductStructureResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductStructureResponse extends WPDResponse {

    private ProductStructureBO productStructure;

    boolean success = true;

    private DomainDetail domainDetail = null;
    
    private List benefitComponentList = null;
    
    private List componentInvalidDateList = null;
    
    private int condition;


    // Constructor
    public SaveProductStructureResponse() {
        super();
    }


    /**
     * Returns the productStructure
     * 
     * @return ProductStructureBO productStructure.
     */

    public ProductStructureBO getProductStructure() {
        return productStructure;
    }


    /**
     * Sets the productStructure
     * 
     * @param productStructure.
     */

    public void setProductStructure(ProductStructureBO productStructure) {
        this.productStructure = productStructure;
    }


    /**
     * Returns the success
     * 
     * @return boolean success.
     */

    public boolean isSuccess() {
        return success;
    }


    /**
     * Sets the success
     * 
     * @param success.
     */

    public void setSuccess(boolean success) {
        this.success = success;
    }


    /**
     * Returns the domainDetail
     * 
     * @return DomainDetail domainDetail.
     */

    public DomainDetail getDomainDetail() {
        return domainDetail;
    }


    /**
     * Sets the domainDetail
     * 
     * @param domainDetail.
     */

    public void setDomainDetail(DomainDetail domainDetail) {
        this.domainDetail = domainDetail;
    }
	/**
	 * @return Returns the benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}
	/**
	 * @param benefitComponentList The benefitComponentList to set.
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}
	/**
	 * @return Returns the componentInvalidDateList.
	 */
	public List getComponentInvalidDateList() {
		return componentInvalidDateList;
	}
	/**
	 * @param componentInvalidDateList The componentInvalidDateList to set.
	 */
	public void setComponentInvalidDateList(List componentInvalidDateList) {
		this.componentInvalidDateList = componentInvalidDateList;
	}
	/**
	 * @return Returns the condition.
	 */
	public int getCondition() {
		return condition;
	}
	/**
	 * @param condition The condition to set.
	 */
	public void setCondition(int condition) {
		this.condition = condition;
	}
}