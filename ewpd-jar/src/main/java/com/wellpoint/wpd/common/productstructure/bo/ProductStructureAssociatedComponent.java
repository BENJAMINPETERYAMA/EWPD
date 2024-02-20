/*
 * ProductStructureAssociatedComponent.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureAssociatedComponent extends BusinessObject{
	
	private int productStructureId;
	
	private int benefitComponentId;
	
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
     * Overriding equals method
     * 
     * @return boolean.
     */
    public boolean equals(BusinessObject object) {
        if (object instanceof ProductStructureBO) {
            ProductStructureBO productStructure = (ProductStructureBO) object;
            if (this.productStructureId == productStructure
                    .getProductStructureId())
                return true;
        }
        return false;
    }


    /**
     * Overriding hashCode method
     * 
     * @return int.
     */
    public int hashCode() {
        return 0;
    }
    
    /**
     * Overriding toString method
     * 
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ProductStructureId = ").append(
                this.getProductStructureId());
        buffer.append(", version = ").append(this.getVersion());
        buffer.append(", status = ").append(this.getStatus());
        buffer.append(", createdUser = ").append(this.getCreatedUser());
        buffer.append(", createdTimestamp = ").append(
                this.getCreatedTimestamp());
        buffer.append(", lastUpdatedUser = ").append(this.getLastUpdatedUser());
        buffer.append(", lastUpdatedTimestamp = ").append(
                this.getLastUpdatedTimestamp());
        return buffer.toString();

    }

}
