/*
 * ContractBenefitComponentRetrieveResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveContractBenefitComponentResponse  extends WPDResponse {
	
	private Contract contract;
	
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	private BenefitComponentBO benefitComponent;

	private DomainDetail detail;
	
    private boolean success;    
    
    
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
	
	/**
	 * Returns the detail
	 * @return DomainDetail detail.
	 */
	public DomainDetail getDetail() {
		return detail;
	}
	/**
	 * Sets the detail
	 * @param detail.
	 */
	public void setDetail(DomainDetail detail) {
		this.detail = detail;
	}
	/**
	 * Returns the benefitComponent
	 * @return BenefitComponentBO benefitComponent.
	 */
	public BenefitComponentBO getBenefitComponent() {
		return benefitComponent;
	}
	/**
	 * Sets the benefitComponent
	 * @param benefitComponent.
	 */
	public void setBenefitComponent(BenefitComponentBO benefitComponent) {
		this.benefitComponent = benefitComponent;
	}
	/**
	 * Returns the success
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Sets the success
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
