/*
 * BenefitAdministrationAdminOption.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

/**
 * The Business Object contains all the attributes
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitAdministrationAdminOptionImpl {

    private String adminName;

    private String adminLevel;

    private String benefitLevel;

    private String reference;


    /**
     * Returns the adminLevel
     * 
     * @return String adminLevel.
     */
    public String getAdminLevel() {
        return adminLevel;
    }


    /**
     * Sets the adminLevel
     * 
     * @param adminLevel.
     */
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }


    /**
     * Returns the adminName
     * 
     * @return String adminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * Sets the adminName
     * 
     * @param adminName.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * Returns the benefitLevel
     * 
     * @return String benefitLevel.
     */
    public String getBenefitLevel() {
        return benefitLevel;
    }


    /**
     * Sets the benefitLevel
     * 
     * @param benefitLevel.
     */
    public void setBenefitLevel(String benefitLevel) {
        this.benefitLevel = benefitLevel;
    }


    /**
     * Returns the reference
     * 
     * @return String reference.
     */
    public String getReference() {
        return reference;
    }


    /**
     * Sets the reference
     * 
     * @param reference.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}
