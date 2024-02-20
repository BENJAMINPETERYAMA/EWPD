/*
 * BenefitComponentLocateResult.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.benefitcomponent.locateresult;

import com.wellpoint.wpd.common.bo.LocateResult;

/**
 * Locate Results class for benefit component Delete
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentLocateResult extends LocateResult {

    private int benefitComponentKey;

    private String benefitComponentName;


    /**
     * @return Returns the benefitComponentKey.
     */
    public int getBenefitComponentKey() {
        return benefitComponentKey;
    }


    /**
     * @param benefitComponentKey
     *            The benefitComponentKey to set.
     */
    public void setBenefitComponentKey(int benefitComponentKey) {
        this.benefitComponentKey = benefitComponentKey;
    }


    /**
     * @return Returns the benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }


    /**
     * @param benefitComponentName
     *            The benefitComponentName to set.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
}