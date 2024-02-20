/*
 * RetrieveTaskResponse.java
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
import com.wellpoint.wpd.common.security.bo.TaskBO;
import com.wellpoint.wpd.common.security.bo.TaskSrdaBO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveTaskResponse extends WPDResponse {
    
    private TaskBO taskBO;

    private TaskSrdaBO taskSrdaBo;
    
    private SubTaskBO subTaskBO;
    
    /**
     * @return subTaskBO
     * Returns the subTaskBO.
     */
    public SubTaskBO getSubTaskBO() {
        return subTaskBO;
    }
    /**
     * @param subTaskBO
     * Sets the subTaskBO.
     */
    public void setSubTaskBO(SubTaskBO subTaskBO) {
        this.subTaskBO = subTaskBO;
    }
	/**
	 * @return Returns the taskBO.
	 */
	public TaskBO getTaskBO() {
		return taskBO;
	}
	/**
	 * @param taskBO The taskBO to set.
	 */
	public void setTaskBO(TaskBO taskBO) {
		this.taskBO = taskBO;
	}
	public TaskSrdaBO getTaskSrdaBo() {
		return taskSrdaBo;
	}
	public void setTaskSrdaBo(TaskSrdaBO taskSrdaBo) {
		this.taskSrdaBo = taskSrdaBo;
	}
}
