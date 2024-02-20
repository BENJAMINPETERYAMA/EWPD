/*
 * ProductStructureBOImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBOImpl implements ProductStructureBO {

    private String productStructureDescription = null;

    private int productStructureCode;

    private int productStructureId;

    private String productStructureName;

    private int version;

    /**
     * 
     * Returns the productStructureCode
     * 
     * @return int productStructureCode.
     *  
     */

    public int getProductStructureCode() {
        return productStructureCode;
    }

    /**
     * 
     * Returns the productStructureDescription
     * 
     * @return String productStructureDescription.
     *  
     */

    public String getProductStructureDescription() {
        return productStructureDescription;
    }

    /**
     * 
     * Sets the productStructureCode
     * 
     * @param productStructureCode.
     *  
     */

    public void setProductStructureCode(int productStructureCode) {
        this.productStructureCode = productStructureCode;
    }

    /**
     * 
     * Sets the productStructureDescription
     * 
     * @param productStructureDescription.
     *  
     */

    public void setProductStructureDescription(
            String productStructureDescription) {
        this.productStructureDescription = productStructureDescription;
    }

    /**
     * Returns the productStructureId
     * 
     * @return int productStructureId.
     */

    public int getProductStructureId() {
        return productStructureId;
    }

    /**
     * Sets the productStructureId
     * 
     * @param productStructureId.
     */

    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }

    /**
     * Returns the productStructureName
     * 
     * @return String productStructureName.
     */

    public String getProductStructureName() {
        return productStructureName;
    }

    /**
     * Sets the productStructureName
     * 
     * @param productStructureName.
     */

    public void setProductStructureName(String productStructureName) {
        this.productStructureName = productStructureName;
    }

    /**
     * Returns the version
     * 
     * @return int version.
     */

    public int getVersion() {
        return version;
    }

    /**
     * Sets the version
     * 
     * @param version.
     */

    public void setVersion(int version) {
        this.version = version;
    }
}