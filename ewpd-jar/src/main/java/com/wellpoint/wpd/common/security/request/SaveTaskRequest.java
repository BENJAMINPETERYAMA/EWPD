/*
 * SaveTaskRequest.java
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
import com.wellpoint.wpd.common.security.vo.TaskVO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveTaskRequest extends WPDRequest {
    
    private boolean createFlag;
    
    private TaskVO taskVO;
    
    private String srdaFlag;
    
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    /**
     * @return Returns the createFlag.
     * @return boolean createFlag
     */
    public boolean isCreateFlag() {
        return createFlag;
    }
    /**
     * Sets the createFlag
     * @param createFlag
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
    }
    
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
	public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
