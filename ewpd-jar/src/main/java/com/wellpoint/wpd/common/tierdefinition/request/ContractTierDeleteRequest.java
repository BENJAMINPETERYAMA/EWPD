package com.wellpoint.wpd.common.tierdefinition.request;

import com.wellpoint.wpd.common.contract.request.ContractRequest;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

public class ContractTierDeleteRequest extends ContractRequest{
	
	private int contractTierSysId;
	private int benefitComponentSysId;
    private int benefitId;
    private int productSysId;
	
    
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the benefitComponentSysId.
	 */
	public int getBenefitComponentSysId() {
		return benefitComponentSysId;
	}
	/**
	 * @param benefitComponentSysId The benefitComponentSysId to set.
	 */
	public void setBenefitComponentSysId(int benefitComponentSysId) {
		this.benefitComponentSysId = benefitComponentSysId;
	}
	public int getContractTierSysId() {
		return contractTierSysId;
	}

	public void setContractTierSysId(int contractTierSysId) {
		this.contractTierSysId = contractTierSysId;
	}

	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	public int getProductSysId() {
		return productSysId;
	}
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
}
