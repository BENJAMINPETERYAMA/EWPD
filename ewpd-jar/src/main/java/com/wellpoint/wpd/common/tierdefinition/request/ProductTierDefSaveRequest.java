/*
 * Created on Aug 14, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.tierdefinition.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.request.ProductRequest;

/**
 * @author u20776
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductTierDefSaveRequest extends ProductRequest{

	private int productSysId;
	
	private int benefitComponentSysId;
	
	private int benefitDefinitionSysId;
	
	private int benefitDefinitionLevelId;
	
	private int tierDefinitionId;
	
	private String isTierDefExits;
	
	private String benefitCriteriaSaveString;
	
	
	/**
	 * @return Returns the tierDefinitionId.
	 */
	public int getTierDefinitionId() {
		return tierDefinitionId;
	}
	/**
	 * @param tierDefinitionId The tierDefinitionId to set.
	 */
	public void setTierDefinitionId(int tierDefinitionId) {
		this.tierDefinitionId = tierDefinitionId;
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
	/**
	 * @return Returns the benefitCriteriaSaveString.
	 */
	public String getBenefitCriteriaSaveString() {
		return benefitCriteriaSaveString;
	}
	/**
	 * @param benefitCriteriaSaveString The benefitCriteriaSaveString to set.
	 */
	public void setBenefitCriteriaSaveString(String benefitCriteriaSaveString) {
		this.benefitCriteriaSaveString = benefitCriteriaSaveString;
	}
	/**
	 * @return Returns the benefitDefinitionLevelId.
	 */
	public int getBenefitDefinitionLevelId() {
		return benefitDefinitionLevelId;
	}
	/**
	 * @param benefitDefinitionLevelId The benefitDefinitionLevelId to set.
	 */
	public void setBenefitDefinitionLevelId(int benefitDefinitionLevelId) {
		this.benefitDefinitionLevelId = benefitDefinitionLevelId;
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
	 * @return Returns the isTierDefExits.
	 */
	public String getTierDefExits() {
		return isTierDefExits;
	}
	/**
	 * @param isTierDefExits The isTierDefExits to set.
	 */
	public void setTierDefExits(String isTierDefExits) {
		this.isTierDefExits = isTierDefExits;
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
