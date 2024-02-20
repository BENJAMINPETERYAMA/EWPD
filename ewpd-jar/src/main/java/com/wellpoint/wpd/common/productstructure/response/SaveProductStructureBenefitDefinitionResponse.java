/*
 * SaveProductStructureBenefitDefinitionResponse.java
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
public class SaveProductStructureBenefitDefinitionResponse extends WPDResponse {

    private boolean success;

    private List benefitDefiniitonList;


    /**
     * @return Returns the benefitDefiniitonList.
     */
    public List getBenefitDefiniitonList() {
        return benefitDefiniitonList;
    }


    /**
     * @param benefitDefiniitonList
     *            The benefitDefiniitonList to set.
     */
    public void setBenefitDefiniitonList(List benefitDefiniitonList) {
        this.benefitDefiniitonList = benefitDefiniitonList;
    }


    /**
     * Returns the success
     * 
     * @return boolean success.
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * Sets the success
     * 
     * @param success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

}