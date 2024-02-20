/*
 * TreeBenefitComponent.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TreeBenefitComponent {

    private int benefitComponentId;

    private int benefitId;

    private String benefitName;


    /**
     * Returns the benefitComponentId
     * 
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId
     * 
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the benefitId
     * 
     * @return int benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }


    /**
     * Sets the benefitId
     * 
     * @param benefitId.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }


    /**
     * Returns the benefitName
     * 
     * @return String benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }


    /**
     * Sets the benefitName
     * 
     * @param benefitName.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }
}