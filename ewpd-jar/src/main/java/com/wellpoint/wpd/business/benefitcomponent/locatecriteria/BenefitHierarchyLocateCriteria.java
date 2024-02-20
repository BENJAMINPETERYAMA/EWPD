/*
 * BenefitHierarchyLocateCriteria.java
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
 * Locate criteria class for benefit Hierarchy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyLocateCriteria extends LocateCriteria {

    private int benefitHierarchyId;

    private int benefitComponentId;

    private boolean validationFlag = false;

    private boolean generalBenefitFlag = false;

    private List lobList;

    private List businessEntityList;

    private List businessGroupList;
    
    private List marketBusinessUnitList;


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
     * Returns the validationFlag
     * 
     * @return validationFlag
     */
    public boolean isValidationFlag() {
        return validationFlag;
    }


    /**
     * @param validationFlag
     *            The validationFlag to set.
     */
    public void setValidationFlag(boolean validationFlag) {
        this.validationFlag = validationFlag;
    }


    /**
     * Returns the benefitHierarchyId
     * 
     * @return benefitHierarchyId
     */
    public int getBenefitHierarchyId() {
        return benefitHierarchyId;
    }


    /**
     * Sets the benefitHierarchyId
     * 
     * @param benefitHierarchyId
     */
    public void setBenefitHierarchyId(int benefitHierarchyId) {
        this.benefitHierarchyId = benefitHierarchyId;
    }


    /**
     * Returns the benefitComponentId.
     * 
     * @return benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * @param benefitComponentId
     *            The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the generalBenefitFlag.
     * 
     * @return generalBenefitFlag
     */
    public boolean isGeneralBenefitFlag() {
        return generalBenefitFlag;
    }


    /**
     * @param generalBenefitFlag
     *            The generalBenefitFlag to set.
     */
    public void setGeneralBenefitFlag(boolean generalBenefitFlag) {
        this.generalBenefitFlag = generalBenefitFlag;
    }
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}