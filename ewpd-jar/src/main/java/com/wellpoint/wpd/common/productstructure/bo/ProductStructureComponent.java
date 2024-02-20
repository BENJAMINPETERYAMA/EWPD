/*
 * ProductStructureComponent.java
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
public class ProductStructureComponent extends BusinessObject {

    private int productStructureId;


    /**
     * Overriding equals method
     * 
     * @return boolean.
     */
    public boolean equals(BusinessObject object) {
        if (object instanceof ProductStructureComponent) {
            ProductStructureComponent productStructureComponent = (ProductStructureComponent) object;
            if (this.productStructureId == productStructureComponent
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
        return buffer.toString();

    }


    /**
     * @return productStructureId
     * 
     * Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * @param productStructureId
     * 
     * Sets the productStructureId.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }
}