/*
 * BenefitComponentDeleteLocateCriteria.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.benefitcomponent.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * Locate criteria class for benefit component Delete
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentDeleteLocateCriteria extends LocateCriteria {

    private int benefitComponentKey;


    /**
     * Returns the benefitComponentKey
     * 
     * @return benefitComponentKey
     */
    public int getBenefitComponentKey() {
        return benefitComponentKey;
    }


    /**
     * Sets the benefitComponentKey
     * 
     * @param benefitComponentKey
     *            The benefitComponentKey to set.
     */
    public void setBenefitComponentKey(int benefitComponentKey) {
        this.benefitComponentKey = benefitComponentKey;
    }
}