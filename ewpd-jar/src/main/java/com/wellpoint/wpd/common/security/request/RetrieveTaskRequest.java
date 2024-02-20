/*
 * RetrieveTaskRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class RetrieveTaskRequest extends WPDRequest{
    
    private int taskId;
    
    private String taskType;
    
    private String srdaFlag;
    
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * @return taskType
     * Returns the taskType.
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * @param taskType
     * Sets the taskType.
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    /**
     * @return Returns the taskId.
     * @return int taskId
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * Sets the taskId
     * @param taskId
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

	public String getSrdaFlag() {
		return srdaFlag;
	}

	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
