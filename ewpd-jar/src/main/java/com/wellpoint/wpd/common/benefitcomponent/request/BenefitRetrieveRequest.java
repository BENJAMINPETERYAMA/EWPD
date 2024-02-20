/*
 * BenefitRetrieveRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request to Retrieve Benefit.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitRetrieveRequest extends WPDRequest{

	private int benefitComponentId;
	
	private List businessDomainList;
	
    /**
     * Returns the businessDomainList
     * @return List businessDomainList.
     */
    public List getBusinessDomainList() {
        return businessDomainList;
    }
    /**
     * Sets the businessDomainList
     * @param businessDomainList.
     */
    public void setBusinessDomainList(List businessDomainList) {
        this.businessDomainList = businessDomainList;
    }
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
}
