/*
 * TaskSearchRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.task.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.security.vo.TaskVO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskSearchRequest extends WPDRequest {

	private TaskVO taskVO;
	
	private String srdaFlag;
	
	
	/**
	 * @return Returns the taskVO.
	 */
	public TaskVO getTaskVO() {
		return taskVO;
	}
	/**
	 * @param taskVO The taskVO to set.
	 */
	public void setTaskVO(TaskVO taskVO) {
		this.taskVO = taskVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	/**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}
	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}

}
