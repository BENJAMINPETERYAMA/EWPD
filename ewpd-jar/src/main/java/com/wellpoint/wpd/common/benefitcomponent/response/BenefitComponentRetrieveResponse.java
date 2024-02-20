/*
 * BenefitComponentRetrieveResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;


/**
 * Response for Benefit Component Retrieve
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentRetrieveResponse extends WPDResponse{
	
	private StandardBenefitBO standardBenefitBO;
    private boolean success;    
    private DomainDetail domainDetail = null;
    private BenefitMandateBO benefitMandateBO;

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
	/**
	 * @return Returns the benefitMandateBO.
	 */
	public BenefitMandateBO getBenefitMandateBO() {
		return benefitMandateBO;
	}
	/**
	 * @param benefitMandateBO The benefitMandateBO to set.
	 */
	public void setBenefitMandateBO(BenefitMandateBO benefitMandateBO) {
		this.benefitMandateBO = benefitMandateBO;
	}
}
