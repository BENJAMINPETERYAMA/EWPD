/*
 * RetrieveContractStandardBenefitResponse.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractStandardBenefitResponse extends ContractResponse {
	
	private StandardBenefitBO standardBenefitBO;
	private DomainDetail domainDetail = null;
	

	/**
	 * Returns the standardBenefitBO
	 * @return StandardBenefitBO standardBenefitBO.
	 */
	public StandardBenefitBO getStandardBenefitBO() {
		return standardBenefitBO;
	}
	/**
	 * Sets the standardBenefitBO
	 * @param standardBenefitBO.
	 */
	public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
		this.standardBenefitBO = standardBenefitBO;
	}
	
	/**
	 * Returns the domainDetail
	 * @return DomainDetail domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}
	/**
	 * Sets the domainDetail
	 * @param domainDetail.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
}
