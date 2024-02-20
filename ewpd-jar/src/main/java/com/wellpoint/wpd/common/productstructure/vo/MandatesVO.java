/*
 * MandatesVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.vo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandatesVO {

    private int benefitId;

    private String optionalIdentifier;

    private Date effectiveDate;

    private Date expiryDate;

    private String benefitName;


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
}