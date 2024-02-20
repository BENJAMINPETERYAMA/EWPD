/*
 * SaveTaskAssociationRequest.java
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
public class SaveTaskAssociationRequest extends WPDRequest {
    
    private List taskId;
    private int moduleId;
    private int roleModTaskId;
    private String srdaFlag;
    private RoleVO roleVO;
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
    
    /**
     * @return roleModTaskId
     * 
     * Returns the roleModTaskId.
     */
    public int getRoleModTaskId() {
        return roleModTaskId;
    }
    /**
     * @param roleModTaskId
     * 
     * Sets the roleModTaskId.
     */
    public void setRoleModTaskId(int roleModTaskId) {
        this.roleModTaskId = roleModTaskId;
    }
    

    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

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
     * @return taskId
     * 
     * Returns the taskId.
     */
    public List getTaskId() {
        return taskId;
    }
    /**
     * @param taskId
     * 
     * Sets the taskId.
     */
    public void setTaskId(List taskId) {
        this.taskId = taskId;
    }
	public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
