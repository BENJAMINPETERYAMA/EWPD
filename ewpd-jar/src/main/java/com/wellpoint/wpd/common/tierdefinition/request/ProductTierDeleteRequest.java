package com.wellpoint.wpd.common.tierdefinition.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.request.ProductRequest;

public class ProductTierDeleteRequest extends ProductRequest{
	
	private int productTierSysId;
	private int benefitComponentSysId;
	
	/**
	 * @return Returns the benefitCompSysId.
	 */
	
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	public int getProductTierSysId() {
		return productTierSysId;
	}

	public void setProductTierSysId(int productTierSysId) {
		this.productTierSysId = productTierSysId;
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
}
