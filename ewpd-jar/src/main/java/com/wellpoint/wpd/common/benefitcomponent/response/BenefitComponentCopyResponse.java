/*
 * BenefitComponentCopyResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Benefit Component Copy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentCopyResponse extends WPDResponse{
	
	private BenefitComponentBO benefitComponentBO;
	
	private DomainDetail domailDetail;

	/**
	 * @return Returns the domailDetail.
	 */
	public DomainDetail getDomailDetail() {
		return domailDetail;
	}
	/**
	 * @param domailDetail The domailDetail to set.
	 */
	public void setDomailDetail(DomainDetail domailDetail) {
		this.domailDetail = domailDetail;
	}
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
}
