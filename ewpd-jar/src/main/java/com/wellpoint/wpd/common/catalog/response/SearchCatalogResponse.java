/*
 * Created on May 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.catalog.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchCatalogResponse extends WPDResponse {
    private List catalogList;

    /**
     * Returns the catalogList
     * 
     * @return List catalogList.
     */

    public List getCatalogList() {
        return catalogList;
    }

    /**
     * Sets the catalogList
     * 
     * @param catalogList.
     */

    public void setCatalogList(List catalogList) {
        this.catalogList = catalogList;
    }
}