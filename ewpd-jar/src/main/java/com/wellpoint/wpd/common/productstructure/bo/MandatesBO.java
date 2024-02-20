/*
 * ManadatesBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandatesBO {

    private int benefitId;

    private String benefitName;

    private Date effectiveDate;

    private Date expiryDate;

    private String optionalIdentifier;

    private String description;

    private String jurisdiction;


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


    /**
     * Returns the effectiveDate
     * 
     * @return Date effectiveDate.
     */

    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate
     * 
     * @return Date expiryDate.
     */

    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the optionalIdentifier
     * 
     * @return String optionalIdentifier.
     */

    public String getOptionalIdentifier() {
        return optionalIdentifier;
    }


    /**
     * Sets the optionalIdentifier
     * 
     * @param optionalIdentifier.
     */

    public void setOptionalIdentifier(String optionalIdentifier) {
        this.optionalIdentifier = optionalIdentifier;
    }


    /**
     * Returns the description
     * 
     * @return String description.
     */

    public String getDescription() {
        return description;
    }


    /**
     * Sets the description
     * 
     * @param description.
     */

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the jurisdiction
     * 
     * @return String jurisdiction.
     */

    public String getJurisdiction() {
        return jurisdiction;
    }


    /**
     * Sets the jurisdiction
     * 
     * @param jurisdiction.
     */

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }


    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("benefitId = ").append(this.getBenefitId());
        buffer.append("benefitName = ").append(this.getBenefitName());
        buffer.append(", description = ").append(description);
        buffer.append(", jurisdiction = ").append(jurisdiction);
        buffer.append(", effectiveDate = ").append(effectiveDate);
        buffer.append(", expiryDate = ").append(expiryDate);
        buffer.append(", optionalIdentifier = ").append(
                this.getOptionalIdentifier());
        return buffer.toString();

    }
}