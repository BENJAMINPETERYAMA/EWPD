/*
 * RetrieveExistingContractIdResponse.java
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
public class RetrieveExistingContractIdResponse extends WPDResponse{
    
    private List contractIdExistingList;
    
   /**
     * Returns the contractIdExistingList
     * @return List contractIdExistingList.
     */
    public List getContractIdExistingList() {
        return contractIdExistingList;
    }
    /**
     * Sets the contractIdExistingList
     * @param contractIdExistingList.
     */
    public void setContractIdExistingList(List contractIdExistingList) {
        this.contractIdExistingList = contractIdExistingList;
    }
}
