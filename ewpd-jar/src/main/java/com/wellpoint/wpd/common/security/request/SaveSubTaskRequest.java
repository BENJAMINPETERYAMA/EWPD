/*
 * SaveSubTaskRequest.java
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
public class SaveSubTaskRequest extends WPDRequest {

    private TaskVO taskVO;
    
    private boolean createFlag;

    /**
     * @return createFlag
     * Returns the createFlag.
     */
    public boolean isCreateFlag() {
        return createFlag;
    }
    /**
     * @param createFlag
     * Sets the createFlag.
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
    }
    /**
     * @return taskVO
     * Returns the taskVO.
     */
    public TaskVO getTaskVO() {
        return taskVO;
    }
    /**
     * @param taskVO
     * Sets the taskVO.
     */
    public void setTaskVO(TaskVO taskVO) {
        this.taskVO = taskVO;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    
}
