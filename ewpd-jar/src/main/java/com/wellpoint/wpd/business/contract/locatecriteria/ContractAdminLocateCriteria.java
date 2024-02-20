/*
 * ComponentLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractAdminLocateCriteria extends LocateCriteria {
    int productKey;
    int contractDateSegmentSysKey;
//  --------------------------------- getters/setters -----------------------	    
    /**
     * Returns the productKey
     * @return int productKey.
     */
    public int getProductKey() {
        return productKey;
    }
    /**
     * Sets the productKey
     * @param productKey.
     */
    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }    
	/**
	 * Returns the contractDateSegmentSysKey
	 * @return int contractDateSegmentSysKey.
	 */
	public int getContractDateSegmentSysKey() {
		return contractDateSegmentSysKey;
	}
	/**
	 * Sets the contractDateSegmentSysKey
	 * @param contractDateSegmentSysKey.
	 */
	public void setContractDateSegmentSysKey(int contractDateSegmentSysKey) {
		this.contractDateSegmentSysKey = contractDateSegmentSysKey;
	}
}
