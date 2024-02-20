/*
 * Created on Aug 11, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.tierdefinition.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u20776
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TierDefinitionRetrieveRequest extends WPDRequest{

	private int productSysId;
	
	private int benefitComponentSysId;
	
	private int benefitDefinitionSysId;
	
	
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
	/**
	 * @return Returns the benefitDefinitionSysId.
	 */
	public int getBenefitDefinitionSysId() {
		return benefitDefinitionSysId;
	}
	/**
	 * @param benefitDefinitionSysId The benefitDefinitionSysId to set.
	 */
	public void setBenefitDefinitionSysId(int benefitDefinitionSysId) {
		this.benefitDefinitionSysId = benefitDefinitionSysId;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
