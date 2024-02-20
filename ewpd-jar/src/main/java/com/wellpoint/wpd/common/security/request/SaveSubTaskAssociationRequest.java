/*
 * SaveSubTaskAssociationRequest.java
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
import com.wellpoint.wpd.common.security.vo.RoleVO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveSubTaskAssociationRequest extends WPDRequest {

    private int roleModTaskId;
    
    private int taskId;
    
    private RoleVO roleVO;
    
    /**
     * @return subTaskId
     * 
     * Returns the subTaskId.
     */
    public List getSubTaskId() {
        return subTaskId;
    }
    /**
     * @param subTaskId
     * 
     * Sets the subTaskId.
     */
    public void setSubTaskId(List subTaskId) {
        this.subTaskId = subTaskId;
    }
    private List subTaskId;
    
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
    
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    /**
     * @return roleVO
     * 
     * Returns the roleVO.
     */
    public RoleVO getRoleVO() {
        return roleVO;
    }
    /**
     * @param roleVO
     * 
     * Sets the roleVO.
     */
    public void setRoleVO(RoleVO roleVO) {
        this.roleVO = roleVO;
    }
}
