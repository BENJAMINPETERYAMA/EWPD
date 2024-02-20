/*
 * ProductComponentVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductAdminVO {

	private int productKey;
    private int productStructureKey;
    private int adminKey;
    private String adminId;
    private int adminVersion;
    private String adminDesc;    
    private int sequence;
	
	
	
	/**
	 * @return Returns the sequence.
	 */
	public int getSequence() {
		return sequence;
	}
	/**
	 * @param sequence The sequence to set.
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
	/**
	 * @return Returns the productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * @param productKey The productKey to set.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
	/**
	 * @return Returns the productStructureKey.
	 */
	public int getProductStructureKey() {
		return productStructureKey;
	}
	/**
	 * @param productStructureKey The productStructureKey to set.
	 */
	public void setProductStructureKey(int productStructureKey) {
		this.productStructureKey = productStructureKey;
	}
	/**
	 * @return Returns the adminDesc.
	 */
	public String getAdminDesc() {
		return adminDesc;
	}
	/**
	 * @param adminDesc The adminDesc to set.
	 */
	public void setAdminDesc(String adminDesc) {
		this.adminDesc = adminDesc;
	}
	/**
	 * @return Returns the adminId.
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId The adminId to set.
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return Returns the adminKey.
	 */
	public int getAdminKey() {
		return adminKey;
	}
	/**
	 * @param adminKey The adminKey to set.
	 */
	public void setAdminKey(int adminKey) {
		this.adminKey = adminKey;
	}
	/**
	 * @return Returns the adminVersion.
	 */
	public int getAdminVersion() {
		return adminVersion;
	}
	/**
	 * @param adminVersion The adminVersion to set.
	 */
	public void setAdminVersion(int adminVersion) {
		this.adminVersion = adminVersion;
	}
}
