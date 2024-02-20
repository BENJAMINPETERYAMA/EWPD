/*
 * RetrieveProductBenefitDefinitionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveProductBenefitResponse extends WPDResponse{

	private StandardBenefitBO standardBenefitBO = null;
	private DomainDetail domainDetail ;
	
	private List nonAdjMandateList = null;
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
	 * @return Returns the nonAdjMandateList.
	 */
	public List getNonAdjMandateList() {
		return nonAdjMandateList;
	}
	/**
	 * @param nonAdjMandateList The nonAdjMandateList to set.
	 */
	public void setNonAdjMandateList(List nonAdjMandateList) {
		this.nonAdjMandateList = nonAdjMandateList;
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
