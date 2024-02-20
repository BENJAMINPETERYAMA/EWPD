/*
 * SubCatalogLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.subcatalog.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubCatalogLocateCriteria extends LocateCriteria {

    private String subCatalogName;
    
    private List catalogList;
    
    private int subCatalogId;
    
    /**
     * @return catalogList
     * Returns the catalogList.
     */
    public List getCatalogList() {
        return catalogList;
    }
    /**
     * @param catalogList
     * Sets the catalogList.
     */
    public void setCatalogList(List catalogList) {
        this.catalogList = catalogList;
    }
    /**
     * @return subCatalogName
     * Returns the subCatalogName.
     */
    public String getSubCatalogName() {
        return subCatalogName;
    }
    /**
     * @param subCatalogName
     * Sets the subCatalogName.
     */
    public void setSubCatalogName(String subCatalogName) {
        this.subCatalogName = subCatalogName;
    }
    
    /**
     * @return Returns the subCatalogId.
     * @return int subCatalogId
     */
    public int getSubCatalogId() {
        return subCatalogId;
    }
    /**
     * Sets the subCatalogId
     * @param subCatalogId
     */
    public void setSubCatalogId(int subCatalogId) {
        this.subCatalogId = subCatalogId;
    }
}
