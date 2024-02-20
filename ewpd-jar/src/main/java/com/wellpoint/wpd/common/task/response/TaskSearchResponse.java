/*
 * TaskSearchResponse.java
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
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskSearchResponse extends WPDResponse {

	 private List taskList;
    
    
	/**
	 * @return Returns the taskList.
	 */
	public List getTaskList() {
		return taskList;
	}
	/**
	 * @param taskList The taskList to set.
	 */
	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}
}
