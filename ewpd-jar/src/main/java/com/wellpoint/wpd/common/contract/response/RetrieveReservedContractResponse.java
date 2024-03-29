/*
 * RetrieveReservedContractResponse.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.ReserveContractId;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveReservedContractResponse extends WPDResponse {

	private List searchResultList;
	private String contractId;
	private int contractSysId;

    private ReserveContractId  reserveContractId;
    
    private DomainDetail domainDetail;
	
	
	private boolean success;
	
	
	/**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
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
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	 * @return Returns the reserveContractId.
	 */
	public ReserveContractId getReserveContractId() {
		return reserveContractId;
	}
	/**
	 * @param reserveContractId The reserveContractId to set.
	 */
	public void setReserveContractId(ReserveContractId reserveContractId) {
		this.reserveContractId = reserveContractId;
	}
	/**
	 * Returns the contractSysId
	 * @return int contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * Sets the contractSysId
	 * @param contractSysId.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
}
