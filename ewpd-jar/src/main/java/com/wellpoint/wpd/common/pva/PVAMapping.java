/*
 * Created on Jul 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.pva;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PVAMapping {
	
	private String businessEntityId;
	
	private String productFamilyId;
	
	private String PVAId;

	/**
	 * @return Returns the businessEntityId.
	 */
	public String getBusinessEntityId() {
		return businessEntityId;
	}
	/**
	 * @param businessEntityId The businessEntityId to set.
	 */
	public void setBusinessEntityId(String businessEntityId) {
		this.businessEntityId = businessEntityId;
	}
	/**
	 * @return Returns the productFamilyId.
	 */
	public String getProductFamilyId() {
		return productFamilyId;
	}
	/**
	 * @param productFamilyId The productFamilyId to set.
	 */
	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
	}
	/**
	 * @return Returns the pVAId.
	 */
	public String getPVAId() {
		return PVAId;
	}
	/**
	 * @param id The pVAId to set.
	 */
	public void setPVAId(String id) {
		PVAId = id;
	}
}
