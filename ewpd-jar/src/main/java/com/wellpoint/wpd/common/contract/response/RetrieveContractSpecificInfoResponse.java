/*
 * RetrieveContractSpecificInfoResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.contract.bo.AdaptedInfoBO;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractSpecificInfoResponse extends ContractResponse {
	private Contract contract;
	
	private DateSegment dateSegment;
	
	private AdaptedInfoBO adaptedInfoBO;
	
	private DomainDetail domainDetail;

	/**
	 * Returns the contract
	 * @return Contract contract.
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * Sets the contract
	 * @param contract.
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
    /**
     * Returns the dateSegment
     * @return DateSegment dateSegment.
     */
    public DateSegment getDateSegment() {
        return dateSegment;
    }
    /**
     * Sets the dateSegment
     * @param dateSegment.
     */
    public void setDateSegment(DateSegment dateSegment) {
        this.dateSegment = dateSegment;
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
	/**
	 * @return Returns the adaptedInfoBO.
	 */
	public AdaptedInfoBO getAdaptedInfoBO() {
		return adaptedInfoBO;
	}
	/**
	 * @param adaptedInfoBO The adaptedInfoBO to set.
	 */
	public void setAdaptedInfoBO(AdaptedInfoBO adaptedInfoBO) {
		this.adaptedInfoBO = adaptedInfoBO;
	}
}
