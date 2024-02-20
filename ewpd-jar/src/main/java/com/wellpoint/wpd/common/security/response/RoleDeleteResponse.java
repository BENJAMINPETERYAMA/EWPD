/*
 * RoleDeleteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.RoleBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleDeleteResponse extends WPDResponse{
    
    private RoleBO roleBO;
	
	private List roleList;

	private boolean success;
	

    /**
     * @return roleBO
     * 
     * Returns the roleBO.
     */
    public RoleBO getRoleBO() {
        return roleBO;
    }
    /**
     * @param roleBO
     * 
     * Sets the roleBO.
     */
    public void setRoleBO(RoleBO roleBO) {
        this.roleBO = roleBO;
    }
    /**
     * @return roleList
     * 
     * Returns the roleList.
     */
    public List getRoleList() {
        return roleList;
    }
    /**
     * @param roleList
     * 
     * Sets the roleList.
     */
    public void setRoleList(List roleList) {
        this.roleList = roleList;
    }
    /**
     * @return success
     * 
     * Returns the success.
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * @param success
     * 
     * Sets the success.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
   
}
