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
 * Locate criteria class for Non adj benefit mandate
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NonAdjBenefitMandateLocateCriteria extends LocateCriteria {
    private int benefitSystemId;


    /**
     * @return Returns the benefitSystemId.
     */
    public int getBenefitSystemId() {
        return benefitSystemId;
    }


    /**
     * @param benefitSystemId
     *            The benefitSystemId to set.
     */
    public void setBenefitSystemId(int benefitSystemId) {
        this.benefitSystemId = benefitSystemId;
    }
}