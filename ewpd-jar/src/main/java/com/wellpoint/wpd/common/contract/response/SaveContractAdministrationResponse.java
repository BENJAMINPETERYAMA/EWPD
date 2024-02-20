/*
 * SaveContractAdministrationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractAdministrationResponse extends WPDResponse {
    
    private List contractAdminList;
    
    

    /**
     * Returns the contractAdminList
     * @return List contractAdminList.
     */
    public List getContractAdminList() {
        return contractAdminList;
    }
    /**
     * Sets the contractAdminList
     * @param contractAdminList.
     */
    public void setContractAdminList(List contractAdminList) {
        this.contractAdminList = contractAdminList;
    }
}
