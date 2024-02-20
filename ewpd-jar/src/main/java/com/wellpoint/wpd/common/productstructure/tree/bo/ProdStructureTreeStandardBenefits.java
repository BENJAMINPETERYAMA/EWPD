/*
 * ProdStructureTreeStandardBenefits.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureTreeStandardBenefits {

    private int benefitCmpntId;

    private int standardBenefitId;

    private String standardBenefitDesc;

    private int productStructure;
    
    

	/**
	 * @return Returns the productStructure.
	 */
	public int getProductStructure() {
		return productStructure;
	}
	/**
	 * @param productStructure The productStructure to set.
	 */
	public void setProductStructure(int productStructure) {
		this.productStructure = productStructure;
	}
    /**
     * @return Returns the benefitCmpntId.
     */
    public int getBenefitCmpntId() {
        return benefitCmpntId;
    }


    /**
     * @param benefitCmpntId
     *            The benefitCmpntId to set.
     */
    public void setBenefitCmpntId(int benefitCmpntId) {
        this.benefitCmpntId = benefitCmpntId;
    }


    /**
     * @return Returns the standardBenefitDesc.
     */
    public String getStandardBenefitDesc() {
        return standardBenefitDesc;
    }


    /**
     * @param standardBenefitDesc
     *            The standardBenefitDesc to set.
     */
    public void setStandardBenefitDesc(String standardBenefitDesc) {
        this.standardBenefitDesc = standardBenefitDesc;
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
}