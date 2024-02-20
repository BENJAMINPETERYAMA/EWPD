/*
 * SaveBenefitAdministrationResponse.java
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
public class SaveBenefitAdministrationResponse extends WPDResponse {

    private List benefitAdminList;


    /**
     * @return Returns the benefitAdminList.
     */
    public List getBenefitAdminList() {
        return benefitAdminList;
    }


    /**
     * @param benefitAdminList
     *            The benefitAdminList to set.
     */
    public void setBenefitAdminList(List benefitAdminList) {
        this.benefitAdminList = benefitAdminList;
    }
}