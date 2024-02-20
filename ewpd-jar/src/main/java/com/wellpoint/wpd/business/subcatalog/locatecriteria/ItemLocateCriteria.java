/*
 * Created on Jul 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.subcatalog.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

public class ItemLocateCriteria extends LocateCriteria{
   
    private String parentCatalogId;
    
    private int subCatalogId;
    
    /**
     * @return subCatalogId
     * Returns the subCatalogId.
     */
    public int getSubCatalogId() {
        return subCatalogId;
    }
    /**
     * @param subCatalogId
     * Sets the subCatalogId.
     */
    public void setSubCatalogId(int subCatalogId) {
        this.subCatalogId = subCatalogId;
    }
    /**
     * @return Returns the parentCatalogId.
     * @return String parentCatalogId
     */
    public String getParentCatalogId() {
        return parentCatalogId;
    }
    /**
     * Sets the parentCatalogId
     * @param parentCatalogId
     */
    public void setParentCatalogId(String parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }
        
}

