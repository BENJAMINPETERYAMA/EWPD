/*
 * RetrieveBenefitHeadingsResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitHeadingsResponse extends ContractResponse {
    
    private List benefitHeadingsList;

    /**
     * Returns the benefitHeadingsList
     * @return List benefitHeadingsList.
     */
    public List getBenefitHeadingsList() {
        return benefitHeadingsList;
    }
    /**
     * Sets the benefitHeadingsList
     * @param benefitHeadingsList.
     */
    public void setBenefitHeadingsList(List benefitHeadingsList) {
        this.benefitHeadingsList = benefitHeadingsList;
    }
}
