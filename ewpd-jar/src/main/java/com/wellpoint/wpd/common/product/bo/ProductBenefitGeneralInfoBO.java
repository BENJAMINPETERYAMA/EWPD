/*
 * Created on Oct 22, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitGeneralInfoBO extends BusinessObject {

	
	/**
	 * @return Returns the productRuleIdBO.
	 */
	public ProductRuleIdBO getProductRuleIdBO() {
		return productRuleIdBO;
	}
	/**
	 * @param productRuleIdBO The productRuleIdBO to set.
	 */
	public void setProductRuleIdBO(ProductRuleIdBO productRuleIdBO) {
		this.productRuleIdBO = productRuleIdBO;
	}
	/**
	 * @return Returns the productTierDefnOverrideBO.
	 */
	public ProductTierDefnOverrideBO getProductTierDefnOverrideBO() {
		return productTierDefnOverrideBO;
	}
	/**
	 * @param productTierDefnOverrideBO The productTierDefnOverrideBO to set.
	 */
	public void setProductTierDefnOverrideBO(
			ProductTierDefnOverrideBO productTierDefnOverrideBO) {
		this.productTierDefnOverrideBO = productTierDefnOverrideBO;
	}
	private ProductTierDefnOverrideBO productTierDefnOverrideBO;
	private ProductRuleIdBO productRuleIdBO;	

	public ProductBenefitGeneralInfoBO() {
	}

	/**
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 * @param object
	 * @return
	 */
	public boolean equals(BusinessObject object) {		
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 * @return
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();		
		return buffer.toString();
	}

	
}