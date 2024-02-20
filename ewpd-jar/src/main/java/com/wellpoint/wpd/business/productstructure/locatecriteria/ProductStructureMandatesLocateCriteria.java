/*
 * ProductStructureMandatesLocateCriteria.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureMandatesLocateCriteria extends LocateCriteria {

    private int benefitId;

    private int benefitMandateId;


    /**
     * @return benefitMandateId
     * 
     * Returns the benefitMandateId.
     */
    public int getBenefitMandateId() {
        return benefitMandateId;
    }


    /**
     * @param benefitMandateId
     * 
     * Sets the benefitMandateId.
     */
    public void setBenefitMandateId(int benefitMandateId) {
        this.benefitMandateId = benefitMandateId;
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
}