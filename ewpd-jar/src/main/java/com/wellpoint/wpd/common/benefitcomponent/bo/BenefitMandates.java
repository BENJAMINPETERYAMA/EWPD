/*
 * BenefitMandates.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

/**
 * Business Object for benefitMandates
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitMandates {

    private String optionalIdentifier;

    private String mandate;

    private String description;

    private String effectiveDate;

    private String expiryDate;


    /**
     * @return Returns the optionalIdentifier.
     */
    public String getOptionalIdentifier() {
        return optionalIdentifier;
    }


    /**
     * @param optionalIdentifier
     *            The optionalIdentifier to set.
     */
    public void setOptionalIdentifier(String optionalIdentifier) {
        this.optionalIdentifier = optionalIdentifier;
    }


    /**
     * @return Returns the mandate.
     */
    public String getMandate() {
        return mandate;
    }


    /**
     * @param mandate
     *            The mandate to set.
     */
    public void setMandate(String mandate) {
        this.mandate = mandate;
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * @param effectiveDate
     *            The effectiveDate to set.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * @return Returns the expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * @param expiryDate
     *            The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}