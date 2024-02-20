/*
 * DeleteModuleTaskAssociationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteModuleTaskAssociationResponse extends WPDResponse {
    private boolean success;
    private List taskList;
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
     * @return Returns the taskList.
     * @return List taskList
     */
    public List getTaskList() {
        return taskList;
    }
    /**
     * Sets the taskList
     * @param taskList
     */
    public void setTaskList(List taskList) {
        this.taskList = taskList;
    }
}
