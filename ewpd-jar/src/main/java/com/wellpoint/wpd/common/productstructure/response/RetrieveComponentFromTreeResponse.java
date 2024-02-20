/*
 * RetrieveComponentFromTreeResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.response;

import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveComponentFromTreeResponse extends WPDResponse {

    private BenefitComponentBO benefitComponent;

    private boolean success;

    private DomainDetail detail;
    
    private List benefitDetails;


    /**
     * @return Returns the success.
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * @param success
     *            The success to set.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }


    /**
     * Returns the benefitComponent
     * 
     * @return ProductStructureBenefitComponent benefitComponent.
     */

    public BenefitComponentBO getBenefitComponent() {
        return benefitComponent;
    }


    /**
     * Sets the benefitComponent
     * 
     * @param benefitComponent.
     */

    public void setBenefitComponent(BenefitComponentBO benefitComponent) {
        this.benefitComponent = benefitComponent;
    }


    /**
     * Returns the detail
     * 
     * @return DomainDetail detail.
     */

    public DomainDetail getDetail() {
        return detail;
    }


    /**
     * Sets the detail
     * 
     * @param detail.
     */

    public void setDetail(DomainDetail detail) {
        this.detail = detail;
    }
    
    
	/**
	 * @return Returns the benefitDetails.
	 */
	public List getBenefitDetails() {
		return benefitDetails;
	}
	/**
	 * @param benefitDetails The benefitDetails to set.
	 */
	public void setBenefitDetails(List benefitDetails) {
		this.benefitDetails = benefitDetails;
	}
}