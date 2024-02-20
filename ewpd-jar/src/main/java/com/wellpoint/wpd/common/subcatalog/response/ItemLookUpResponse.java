/*
 * Created on Jul 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


public class ItemLookUpResponse extends WPDResponse{

    public List itemList;
    
    
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
