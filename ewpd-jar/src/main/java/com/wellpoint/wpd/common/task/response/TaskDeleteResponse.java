/*
 * TaskDeleteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.task.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.TaskBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskDeleteResponse extends WPDResponse {
	
	private TaskBO securityBO;
	
	private List TaskList;
	
	private boolean taskSuccess;
	
	private boolean subTaskSuccess;
	
	/**
	 * @return Returns the securityBO.
	 */
	public TaskBO getSecurityBO() {
		return securityBO;
	}
	/**
	 * @param securityBO The securityBO to set.
	 */
	public void setSecurityBO(TaskBO securityBO) {
		this.securityBO = securityBO;
	}
	
    /**
     * @return subTaskSuccess
     * Returns the subTaskSuccess.
     */
    public boolean isSubTaskSuccess() {
        return subTaskSuccess;
    }
    /**
     * @param subTaskSuccess
     * Sets the subTaskSuccess.
     */
    public void setSubTaskSuccess(boolean subTaskSuccess) {
        this.subTaskSuccess = subTaskSuccess;
    }
    /**
     * @return taskSuccess
     * Returns the taskSuccess.
     */
    public boolean isTaskSuccess() {
        return taskSuccess;
    }
    /**
     * @param taskSuccess
     * Sets the taskSuccess.
     */
    public void setTaskSuccess(boolean taskSuccess) {
        this.taskSuccess = taskSuccess;
    }
	/**
	 * @return Returns the taskList.
	 */
	public List getTaskList() {
		return TaskList;
	}
	/**
	 * @param taskList The taskList to set.
	 */
	public void setTaskList(List taskList) {
		TaskList = taskList;
	}
}
