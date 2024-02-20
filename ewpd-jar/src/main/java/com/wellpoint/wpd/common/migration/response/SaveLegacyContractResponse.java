/*
 * SaveLegacyContractResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.response;

import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveLegacyContractResponse extends MigrationContractResponse {

    private CP2000Contract cp2000Contract;


    /**
     * @return cp2000Contract
     * 
     * Returns the cp2000Contract.
     */
    public CP2000Contract getCp2000Contract() {
        return cp2000Contract;
    }


    /**
     * @param cp2000Contract
     * 
     * Sets the cp2000Contract.
     */
    public void setCp2000Contract(CP2000Contract cp2000Contract) {
        this.cp2000Contract = cp2000Contract;
    }
}