/*
 * Created on Jul 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

public class SaveItemAssociationResponse extends WPDResponse {
    public boolean success;
    
    private List itemList;

    /**
     * @return Returns the success.
     * @return boolean success
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * Sets the success
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
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
