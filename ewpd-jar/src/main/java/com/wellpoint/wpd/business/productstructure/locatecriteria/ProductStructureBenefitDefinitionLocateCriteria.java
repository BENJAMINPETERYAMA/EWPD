/*
 * ProductStructureBenefitDefinitionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitDefinitionLocateCriteria extends
        LocateCriteria {

    private int standardBenefitId;

    private int benefitComponentId;

    private int productStructureId;

    //Flag  values for retrieving the definition
    private String benefitLevelHideFlag;
    
    private String benefitLineHideFlag;

	/**
	 * @return Returns the benefitLevelHideFlag.
	 */
	public String getBenefitLevelHideFlag() {
		return benefitLevelHideFlag;
	}
	/**
	 * @param benefitLevelHideFlag The benefitLevelHideFlag to set.
	 */
	public void setBenefitLevelHideFlag(String benefitLevelHideFlag) {
		this.benefitLevelHideFlag = benefitLevelHideFlag;
	}
	/**
	 * @return Returns the benefitLineHideFlag.
	 */
	public String getBenefitLineHideFlag() {
		return benefitLineHideFlag;
	}
	/**
	 * @param benefitLineHideFlag The benefitLineHideFlag to set.
	 */
	public void setBenefitLineHideFlag(String benefitLineHideFlag) {
		this.benefitLineHideFlag = benefitLineHideFlag;
	}
    /**
     * @return Returns the benefitComponentId.
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
     * @return Returns the standardBenefitId.
     */
    public int getStandardBenefitId() {
        return standardBenefitId;
    }


    /**
     * @param standardBenefitId
     *            The standardBenefitId to set.
     */
    public void setStandardBenefitId(int standardBenefitId) {
        this.standardBenefitId = standardBenefitId;
    }


    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * @param productStructureId
     *            The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }
}