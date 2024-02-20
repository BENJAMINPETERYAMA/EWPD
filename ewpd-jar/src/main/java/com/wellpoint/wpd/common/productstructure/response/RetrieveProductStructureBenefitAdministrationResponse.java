/*
 * RetrieveProductStructureBenefitAdministrationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductStructureBenefitAdministrationResponse extends
        WPDResponse {

    private List benefitAdministrationList;


    /**
     * @return Returns the benefitAdministrationList.
     */
    public List getBenefitAdministrationList() {
        return benefitAdministrationList;
    }


    /**
     * @param benefitAdministrationList
     *            The benefitAdministrationList to set.
     */
    public void setBenefitAdministrationList(List benefitAdministrationList) {
        this.benefitAdministrationList = benefitAdministrationList;
    }
}