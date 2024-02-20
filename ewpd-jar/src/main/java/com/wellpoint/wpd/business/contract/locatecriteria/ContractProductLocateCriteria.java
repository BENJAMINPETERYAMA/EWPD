/*
 * ContractProductLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductLocateCriteria extends LocateCriteria{
    
    List lineOfBusiness;
    List businessEntity;
    List businessGroup;
    List domains;
    Date effectiveDate;
    Date expiryDate;
    String productType;
    String testContractState;
    String state;
    /*START CARS*/
    List marketBusinessUnit;
    /*END CARS*/
    
   
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
     * Returns the businessEntity
     * @return List businessEntity.
     */
    public List getBusinessEntity() {
        return businessEntity;
    }
    /**
     * Sets the businessEntity
     * @param businessEntity.
     */
    public void setBusinessEntity(List businessEntity) {
        this.businessEntity = businessEntity;
    }
    /**
     * Returns the businessGroup
     * @return List businessGroup.
     */
    public List getBusinessGroup() {
        return businessGroup;
    }
    /**
     * Sets the businessGroup
     * @param businessGroup.
     */
    public void setBusinessGroup(List businessGroup) {
        this.businessGroup = businessGroup;
    }
    /**
     * Returns the domains
     * @return List domains.
     */
    public List getDomains() {
        return domains;
    }
    /**
     * Sets the domains
     * @param domains.
     */
    public void setDomains(List domains) {
        this.domains = domains;
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
     * Returns the lineOfBusiness
     * @return List lineOfBusiness.
     */
    public List getLineOfBusiness() {
        return lineOfBusiness;
    }
    /**
     * Sets the lineOfBusiness
     * @param lineOfBusiness.
     */
    public void setLineOfBusiness(List lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
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
	 * @return Returns the testContractState.
	 */
	public String getTestContractState() {
		return testContractState;
	}
	/**
	 * @param testContractState The testContractState to set.
	 */
	public void setTestContractState(String testContractState) {
		this.testContractState = testContractState;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(List marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
}
