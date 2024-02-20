/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;

/**
 * @author u15434
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ItemAssociationDeleteResponse extends WPDResponse{
    
    private SubCatalogBO subCatalogBO;
    
    private List itemList;
    

    /**
     * @return Returns the subCatalogBO.
     * @return SubCatalogBO subCatalogBO
     */
    public SubCatalogBO getSubCatalogBO() {
        return subCatalogBO;
    }
    /**
     * Sets the subCatalogBO
     * @param subCatalogBO
     */
    public void setSubCatalogBO(SubCatalogBO subCatalogBO) {
        this.subCatalogBO = subCatalogBO;
    }
    
    
    /**
     * @return Returns the itemList.
     * @return List itemList
     */
    public List getItemList() {
        return itemList;
    }
    /**
     * Sets the itemList
     * @param itemList
     */
    public void setItemList(List itemList) {
        this.itemList = itemList;
    }
}
