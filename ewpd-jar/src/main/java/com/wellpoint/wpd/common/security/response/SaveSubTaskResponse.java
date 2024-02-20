/*
 * SaveSubTaskResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.SubTaskBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveSubTaskResponse extends WPDResponse {
    
    private SubTaskBO subTaskBO;
    
    private boolean success;

    /**
     * @return taskBO
     * Returns the taskBO.
     */
    public SubTaskBO getSubTaskBO() {
        return subTaskBO;
    }
    /**
     * @param taskBO
     * Sets the taskBO.
     */
    public void setSubTaskBO(SubTaskBO taskBO) {
        this.subTaskBO = taskBO;
    }
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
