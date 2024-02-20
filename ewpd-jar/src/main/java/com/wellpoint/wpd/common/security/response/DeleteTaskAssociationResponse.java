/*
 * DeleteTaskAssociationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteTaskAssociationResponse extends WPDResponse {
    private boolean success;
    
    /**
     * @return success
     * Returns the success.
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * @param success
     * Sets the success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
