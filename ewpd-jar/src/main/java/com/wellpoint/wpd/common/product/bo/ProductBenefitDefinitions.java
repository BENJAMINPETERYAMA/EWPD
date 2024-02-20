/*
 * ProductBenefitDefinitions.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitDefinitions {

    private List updatedBenefitLines;
    
    private List deletedBenefitLevels;
    
    private List benefitTierDefinitionList;
    
    private List benefitTierLevelList;
    
    private int benefitComponentId;
    
    private int benefitId;
    
    private int benefitDefnSysId;


	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	
	/**
	 * Returns the deletedBenefitLevels
     * @return List deletedBenefitLevels.
	 */
	public List getDeletedBenefitLevels() {
		return deletedBenefitLevels;
	}
	
	/**
	 * Sets the deletedBenefitLevels
     * @param deletedBenefitLevels
	 */
	public void setDeletedBenefitLevels(List deletedBenefitLevels) {
		this.deletedBenefitLevels = deletedBenefitLevels;
	}
	
    /**
     * Returns the updatedBenefitLines
     * @return List updatedBenefitLines.
     */
    public List getUpdatedBenefitLines() {
        return updatedBenefitLines;
    }
    
    /**
     * Sets the updatedBenefitLines
     * @param updatedBenefitLines.
     */
    public void setUpdatedBenefitLines(List updatedBenefitLines) {
        this.updatedBenefitLines = updatedBenefitLines;
    }
	/**
	 * @return Returns the benefitDefnSysId.
	 */
	public int getBenefitDefnSysId() {
		return benefitDefnSysId;
	}
	/**
	 * @param benefitDefnSysId The benefitDefnSysId to set.
	 */
	public void setBenefitDefnSysId(int benefitDefnSysId) {
		this.benefitDefnSysId = benefitDefnSysId;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	public List getBenefitTierDefinitionList() {
		return benefitTierDefinitionList;
	}
	public void setBenefitTierDefinitionList(List benefitTierDefinitionList) {
		this.benefitTierDefinitionList = benefitTierDefinitionList;
	}
	public List getBenefitTierLevelList() {
		return benefitTierLevelList;
	}
	public void setBenefitTierLevelList(List benefitTierLevelList) {
		this.benefitTierLevelList = benefitTierLevelList;
	}
}
