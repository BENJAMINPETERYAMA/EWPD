/*
 * ContractBenefitDefinitions.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitDefinitions {
    private List updatedBenefitLines;
    
    private List savedBenefitLines;
    
    private List deletedBenefitLines;
    
    private List deletedBenefitLevels;
    
    private int benefitComponentId;
    
    private int dateSegmentId;
    
    private int productSysId;
    
    private int bnftSysId;
    
    private int bnftDefnSysId;
    
    private List benefitTierDefinitionList;
    
    private List benefitTierLevelList;

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
	 * Sets the deletedBenefitLevels
     * @param deletedBenefitLevels
	 */
	public List getDeletedBenefitLevels() {
		return deletedBenefitLevels;
	}
	
	/**
	 * Returns the deletedBenefitLevels
     * @return List deletedBenefitLevels.
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
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
	/**
	 * @return Returns the savedBenefitLines.
	 */
	public List getSavedBenefitLines() {
		return savedBenefitLines;
	}
	/**
	 * @param savedBenefitLines The savedBenefitLines to set.
	 */
	public void setSavedBenefitLines(List savedBenefitLines) {
		this.savedBenefitLines = savedBenefitLines;
	}
	/**
	 * @return Returns the deletedBenefitLines.
	 */
	public List getDeletedBenefitLines() {
		return deletedBenefitLines;
	}
	/**
	 * @param deletedBenefitLines The deletedBenefitLines to set.
	 */
	public void setDeletedBenefitLines(List deletedBenefitLines) {
		this.deletedBenefitLines = deletedBenefitLines;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the bnftSysId.
	 */
	public int getBnftSysId() {
		return bnftSysId;
	}
	/**
	 * @param bnftSysId The bnftSysId to set.
	 */
	public void setBnftSysId(int bnftSysId) {
		this.bnftSysId = bnftSysId;
	}
	/**
	 * @return Returns the bnftDefnSysId.
	 */
	public int getBnftDefnSysId() {
		return bnftDefnSysId;
	}
	/**
	 * @param bnftDefnSysId The bnftDefnSysId to set.
	 */
	public void setBnftDefnSysId(int bnftDefnSysId) {
		this.bnftDefnSysId = bnftDefnSysId;
	}
}
