/*
 * ProductStructureShowHiddenLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or 
 * use Confidential Information without the express written
 *  agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.productstructure.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureShowHiddenLocateCriteria extends LocateCriteria{
	
    private int productStructureId;

    private int benefitComponentId;

    private boolean hiddenFlag;
    
    private boolean hierarchyFlag;

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
	 * @return Returns the hiddenFlag.
	 */
	public boolean isHiddenFlag() {
		return hiddenFlag;
	}
	/**
	 * @param hiddenFlag The hiddenFlag to set.
	 */
	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}
	/**
	 * @return Returns the productStructureId.
	 */
	public int getProductStructureId() {
		return productStructureId;
	}
	/**
	 * @param productStructureId The productStructureId to set.
	 */
	public void setProductStructureId(int productStructureId) {
		this.productStructureId = productStructureId;
	}
	/**
	 * @return Returns the hierarchyFlag.
	 */
	public boolean isHierarchyFlag() {
		return hierarchyFlag;
	}
	/**
	 * @param hierarchyFlag The hierarchyFlag to set.
	 */
	public void setHierarchyFlag(boolean hierarchyFlag) {
		this.hierarchyFlag = hierarchyFlag;
	}
}
