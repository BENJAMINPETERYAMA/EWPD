/*
 * ProductTreeBenefitAdministration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.tree.bo;

import java.util.HashMap;
import java.util.List;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeBenefitAdministration {
    
    private int benefitComponentId;
    
    private int benefitDefinitionId;
	
	private int benefitAdministrationId;
	
	private String effectiveDate;
	
	private String expiryDate;
	
	private List adminOptionIdsForBenefit;
	    
	private List adminOptionIdsForBenefitLevel;
	
    private List adminMethodValues;
   
    //CARS START
    private HashMap adminMethodsValuesMap;
    //CARS EBD
	 /**
     * Returns the adminMethodValues
     * @return List adminMethodValues.
     */
    public List getAdminMethodValues() {
        return adminMethodValues;
    }
    /**
     * Sets the adminMethodValues
     * @param adminMethodValues.
     */
    public void setAdminMethodValues(List adminMethodValues) {
        this.adminMethodValues = adminMethodValues;
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
     * Returns the benefitAdministrationId
     * @return int benefitAdministrationId.
     */
    public int getBenefitAdministrationId() {
        return benefitAdministrationId;
    }
    
    /**
     * Sets the benefitAdministrationId
     * @param benefitAdministrationId.
     */
    public void setBenefitAdministrationId(int benefitAdministrationId) {
        this.benefitAdministrationId = benefitAdministrationId;
    }
    
    /**
     * Returns the benefitDefinitionId
     * @return int benefitDefinitionId.
     */
    public int getBenefitDefinitionId() {
        return benefitDefinitionId;
    }
    
    /**
     * Sets the benefitDefinitionId
     * @param benefitDefinitionId.
     */
    public void setBenefitDefinitionId(int benefitDefinitionId) {
        this.benefitDefinitionId = benefitDefinitionId;
    }
    
    /**
     * Returns the effectiveDate
     * @return String effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }
    
    /**
     * Sets the effectiveDate
     * @param effectiveDate.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    /**
     * Returns the expiryDate
     * @return String expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }
    
    /**
     * Sets the expiryDate
     * @param expiryDate.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
   
    
    /**
     * Returns the adminOptionIdsForBenefit
     * @return List adminOptionIdsForBenefit.
     */
    public List getAdminOptionIdsForBenefit() {
        return adminOptionIdsForBenefit;
    }
    /**
     * Sets the adminOptionIdsForBenefit
     * @param adminOptionIdsForBenefit.
     */
    public void setAdminOptionIdsForBenefit(List adminOptionIdsForBenefit) {
        this.adminOptionIdsForBenefit = adminOptionIdsForBenefit;
    }
    /**
     * Returns the adminOptionIdsForBenefitLevel
     * @return List adminOptionIdsForBenefitLevel.
     */
    public List getAdminOptionIdsForBenefitLevel() {
        return adminOptionIdsForBenefitLevel;
    }
    /**
     * Sets the adminOptionIdsForBenefitLevel
     * @param adminOptionIdsForBenefitLevel.
     */
    public void setAdminOptionIdsForBenefitLevel(
            List adminOptionIdsForBenefitLevel) {
        this.adminOptionIdsForBenefitLevel = adminOptionIdsForBenefitLevel;
    }
	/**
	 * @return Returns the adminMethodsValuesMap.
	 */
	public HashMap getAdminMethodsValuesMap() {
		return adminMethodsValuesMap;
	}
	/**
	 * @param adminMethodsValuesMap The adminMethodsValuesMap to set.
	 */
	public void setAdminMethodsValuesMap(HashMap adminMethodsValuesMap) {
		this.adminMethodsValuesMap = adminMethodsValuesMap;
	}
}
