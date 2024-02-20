/*
 * StandardBenefitResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitResponse extends WPDResponse {
    private StandardBenefitBO standardBenefitBO;
	private DomainDetail domainDetail = null;
	private boolean success;
	private boolean lockAcquired = true;
    
	
	/**
	 * @return Returns the lockAcquired.
	 */
	public boolean isLockAcquired() {
		return lockAcquired;
	}
	/**
	 * @param lockAcquired The lockAcquired to set.
	 */
	public void setLockAcquired(boolean lockAcquired) {
		this.lockAcquired = lockAcquired;
	}
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
    /**
     * @return Returns the standardBenefitBO.
     */
    public StandardBenefitBO getStandardBenefitBO() {
        return standardBenefitBO;
    }
    /**
     * @param standardBenefitBO The standardBenefitBO to set.
     */
    public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
        this.standardBenefitBO = standardBenefitBO;
    }
    
	/**
	 * @return Returns the domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}
	/**
	 * @param domainDetail The domainDetail to set.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
}
