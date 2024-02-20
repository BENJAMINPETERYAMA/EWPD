/*
 * Created on Aug 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.tierdefinition.request;

import com.wellpoint.wpd.common.product.request.ProductRequest;

/**
 * @author u20656
 *
 * Request for removing a level from Tier
 */


public class DeleteLevelFromTierRequest extends ProductRequest {


	private int productSysId;
	private int benefitComponentSysId;
	private int benefitSysId;
	private int levelSysId;
	
	
	
	
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
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return Returns the levelSysId.
	 */
	public int getLevelSysId() {
		return levelSysId;
	}
	/**
	 * @param levelSysId The levelSysId to set.
	 */
	public void setLevelSysId(int levelSysId) {
		this.levelSysId = levelSysId;
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
}
