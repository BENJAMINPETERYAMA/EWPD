/*
 * ContractRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.web.contract.ContractKeyObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public abstract class ContractRequest extends WPDRequest {
    ContractKeyObject contractKeyObject;

    /**
     * Returns the contractKeyObject
     * @return ContractKeyObject contractKeyObject.
     */
    public ContractKeyObject getContractKeyObject() {
        return contractKeyObject;
    }
    /**
     * Sets the contractKeyObject
     * @param contractKeyObject.
     */
    public void setContractKeyObject(ContractKeyObject contractKeyObject) {
        this.contractKeyObject = contractKeyObject;
    }
}
