/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubCatalogSearchResponse extends WPDResponse {

    private List subCatalogList;
    
    /**
     * @return subCatalogList
     * 
     * Returns the subCatalogList.
     */
    public List getSubCatalogList() {
        return subCatalogList;
    }
    /**
     * @param subCatalogList
     * 
     * Sets the subCatalogList.
     */
    public void setSubCatalogList(List subCatalogList) {
        this.subCatalogList = subCatalogList;
    }
}
