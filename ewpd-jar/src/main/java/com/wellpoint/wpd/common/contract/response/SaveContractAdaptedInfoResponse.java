/*
 * Created on Dec 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;

/**
 * @author u13680
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveContractAdaptedInfoResponse extends ContractResponse {

	private DateSegment dateSegment;
	
	private Contract contract;
	
	private DomainDetail domainDetail;
	/**
	 * @return Returns the dateSegment.
	 */
	public DateSegment getDateSegment() {
		return dateSegment;
	}
	/**
	 * @param dateSegment The dateSegment to set.
	 */
	public void setDateSegment(DateSegment dateSegment) {
		this.dateSegment = dateSegment;
	}
	/**
	 * @return Returns the contract.
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param contract The contract to set.
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
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
