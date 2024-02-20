/*
 * Created on Jul 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


public class ItemSoftDeleteResponse extends WPDResponse{

    private boolean success;
    
    
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
}
