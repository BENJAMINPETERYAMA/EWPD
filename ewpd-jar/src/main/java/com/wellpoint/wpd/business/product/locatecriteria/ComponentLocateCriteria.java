/*
 * ComponentLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ComponentLocateCriteria extends LocateCriteria {
    int productKey;
    private boolean flag;

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
	 * @return Returns the flag.
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
