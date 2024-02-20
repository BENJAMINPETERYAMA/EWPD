/*
 * Created on Jan 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.product.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author U14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductBenefitLocateCriteria extends LocateCriteria{
	
	 private int benefitComponentId;
	 
     private int productId;
     
     private boolean benefitVisibilityStatus;

     
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the broductId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param broductId The broductId to set.
	 */
	public void setProductId(int broductId) {
		this.productId = broductId;
	}
	
	
	/**
	 * @return Returns the benefitVisibilityStatus.
	 */
	public boolean isBenefitVisibilityStatus() {
		return benefitVisibilityStatus;
	}
	/**
	 * @param benefitVisibilityStatus The benefitVisibilityStatus to set.
	 */
	public void setBenefitVisibilityStatus(boolean benefitVisibilityStatus) {
		this.benefitVisibilityStatus = benefitVisibilityStatus;
	}
}
