/*
 * BenefitComponentRetrieveResponseForEdit.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Benefit Component Retrieve
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentRetrieveResponseForEdit extends WPDResponse{
    
    // variable declaration
    private BenefitComponentBO benefitComponentBO;
    
    private boolean lockAquired;

    /**
     * @return Returns the benefitComponentBO.
     */
    public BenefitComponentBO getBenefitComponentBO() {
        return benefitComponentBO;
    }
    /**
     * @param benefitComponentBO The benefitComponentBO to set.
     */
    public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
        this.benefitComponentBO = benefitComponentBO;
    }
	
	/**
	 * @return lockAquired
	 * 
	 * Returns the lockAquired.
	 */
	public boolean isLockAquired() {
		return lockAquired;
	}
	/**
	 * @param lockAquired
	 * 
	 * Sets the lockAquired.
	 */
	public void setLockAquired(boolean lockAquired) {
		this.lockAquired = lockAquired;
	}
}
