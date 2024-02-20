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
public class ProductComponentVO {

	private int productKey;
    private int productStructureKey;
    private int componentKey;
    private String componentId;
    private int componentVersion;
    private String componentDesc;    
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
	 * @return Returns the componentDesc.
	 */
	public String getComponentDesc() {
		return componentDesc;
	}
	/**
	 * @param componentDesc The componentDesc to set.
	 */
	public void setComponentDesc(String componentDesc) {
		this.componentDesc = componentDesc;
	}
	/**
	 * @return Returns the componentId.
	 */
	public String getComponentId() {
		return componentId;
	}
	/**
	 * @param componentId The componentId to set.
	 */
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	/**
	 * @return Returns the componentKey.
	 */
	public int getComponentKey() {
		return componentKey;
	}
	/**
	 * @param componentKey The componentKey to set.
	 */
	public void setComponentKey(int componentKey) {
		this.componentKey = componentKey;
	}
	/**
	 * @return Returns the componentVersion.
	 */
	public int getComponentVersion() {
		return componentVersion;
	}
	/**
	 * @param componentVersion The componentVersion to set.
	 */
	public void setComponentVersion(int componentVersion) {
		this.componentVersion = componentVersion;
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
}
