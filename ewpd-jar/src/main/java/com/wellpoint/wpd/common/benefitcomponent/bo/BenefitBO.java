/*
 * BenefitBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

/**
 * Business Object for benefit
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitBO {

    private int benefitId;

    private String benefitName;
    
    private int benefitParentId;


    /**
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }


    /**
     * @param benefitId
     *            The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }


    /**
     * @return Returns the benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }


    /**
     * @param benefitName
     *            The benefitName to set.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }
	/**
	 * @return Returns the benefitParentId.
	 */
	public int getBenefitParentId() {
		return benefitParentId;
	}
	/**
	 * @param benefitParentId The benefitParentId to set.
	 */
	public void setBenefitParentId(int benefitParentId) {
		this.benefitParentId = benefitParentId;
	}
}