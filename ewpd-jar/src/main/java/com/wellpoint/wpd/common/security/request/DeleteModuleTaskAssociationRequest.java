/*
 * DeleteModuleTaskAssociationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteModuleTaskAssociationRequest extends WPDRequest {
    private int taskId;
    private int moduleId;
    private String srdaFlag;
    private List taskIdList;
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    /**
     * @return Returns the moduleId.
     * @return int moduleId
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * Sets the moduleId
     * @param moduleId
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
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
    
	/**
	 * @return Returns the taskIdList.
	 */
	public List getTaskIdList() {
		return taskIdList;
	}
	/**
	 * @param taskIdList The taskIdList to set.
	 */
	public void setTaskIdList(List taskIdList) {
		this.taskIdList = taskIdList;
	}
	public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
