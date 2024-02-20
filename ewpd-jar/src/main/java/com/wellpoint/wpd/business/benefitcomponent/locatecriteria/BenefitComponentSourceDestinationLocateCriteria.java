/*
 * BenefitComponentSourceDestinationLocateCriteria.java
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
 * Locate criteria class for benefit component source/destination
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSourceDestinationLocateCriteria extends
        LocateCriteria {

    private int sourceKey;

    private int destinationKey;

    private String createdUser;


    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the destinationKey.
     */
    public int getDestinationKey() {
        return destinationKey;
    }


    /**
     * @param destinationKey
     *            The destinationKey to set.
     */
    public void setDestinationKey(int destinationKey) {
        this.destinationKey = destinationKey;
    }


    /**
     * @return Returns the sourceKey.
     */
    public int getSourceKey() {
        return sourceKey;
    }


    /**
     * @param sourceKey
     *            The sourceKey to set.
     */
    public void setSourceKey(int sourceKey) {
        this.sourceKey = sourceKey;
    }
}