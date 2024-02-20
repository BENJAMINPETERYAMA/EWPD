/*
 * DeleteTaskAssociationRequest.java
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
public class DeleteTaskAssociationRequest extends WPDRequest {
    private int roleModTaskId;
    
    private int subTaskId;
    
    private int taskId;
    
    private int action;
    
    private int moduleId;
    
    private String srdaFlag;
    
    private List taskIdList;
    
    /**
     * @return moduleId
     * 
     * Returns the moduleId.
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * @param moduleId
     * 
     * Sets the moduleId.
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
    
    
    /**
     * @return action
     * 
     * Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action
     * 
     * Sets the action.
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     * @return taskId
     * 
     * Returns the taskId.
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * @param taskId
     * 
     * Sets the taskId.
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
   public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

    /**
     * @return Returns the subTaskId.
     * @return int subTaskId
     */
    public int getSubTaskId() {
        return subTaskId;
    }
    /**
     * Sets the subTaskId
     * @param subTaskId
     */
    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }
    
    /**
     * @return roleModTaskId
     * Returns the roleModTaskId.
     */
    public int getRoleModTaskId() {
        return roleModTaskId;
    }
    /**
     * @param roleModTaskId
     * Sets the roleModTaskId.
     */
    public void setRoleModTaskId(int roleModTaskId) {
        this.roleModTaskId = roleModTaskId;
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
