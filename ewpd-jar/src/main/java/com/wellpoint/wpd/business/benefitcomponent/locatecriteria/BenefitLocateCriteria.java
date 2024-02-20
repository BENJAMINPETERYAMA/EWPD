/*
 * BenefitLocateCriteria.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.benefitcomponent.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate criteria class for benefit
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLocateCriteria extends LocateCriteria {

    private int benefitComponentId;

    private List lobList;

    private List businessEntityList;

    private List businessGroupList;
    
    private List marketBusinessUnit;


    /**
     * Returns the businessEntityList
     * 
     * @return List businessEntityList.
     */
    public List getBusinessEntityList() {
        return businessEntityList;
    }


    /**
     * Sets the businessEntityList
     * 
     * @param businessEntityList.
     */
    public void setBusinessEntityList(List businessEntityList) {
        this.businessEntityList = businessEntityList;
    }


    /**
     * Returns the businessGroupList
     * 
     * @return List businessGroupList.
     */
    public List getBusinessGroupList() {
        return businessGroupList;
    }


    /**
     * Sets the businessGroupList
     * 
     * @param businessGroupList.
     */
    public void setBusinessGroupList(List businessGroupList) {
        this.businessGroupList = businessGroupList;
    }


    /**
     * Returns the lobList
     * 
     * @return List lobList.
     */
    public List getLobList() {
        return lobList;
    }


    /**
     * Sets the lobList
     * 
     * @param lobList.
     */
    public void setLobList(List lobList) {
        this.lobList = lobList;
    }


    /**
     * @return Returns the benefitCopmonentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitCopmonentId
     *            The benefitCopmonentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
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