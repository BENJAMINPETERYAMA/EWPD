/*
 * BenefitBO.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

/**
 * Business Object for benefit hierarchy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchy {

    private String benefit;


    /**
     * @return Returns the benefit.
     */
    public String getBenefit() {
        return benefit;
    }


    /**
     * @param benefit
     *            The benefit to set.
     */
    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
}