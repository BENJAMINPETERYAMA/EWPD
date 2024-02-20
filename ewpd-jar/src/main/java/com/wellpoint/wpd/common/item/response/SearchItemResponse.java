/*
 * Created on May 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchItemResponse extends WPDResponse{
    private List itemList;
    /**
     * Returns the itemList
     * @return List itemList.
     */

    public List getItemList() {
        return itemList;
    }
    /**
     * Sets the itemList
     * @param itemList.
     */

    public void setItemList(List itemList) {
        this.itemList = itemList;
    }
}
