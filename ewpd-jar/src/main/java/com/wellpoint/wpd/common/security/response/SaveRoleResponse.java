/*
 * SaveRoleResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.RoleBO;
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveRoleResponse extends WPDResponse{
	
	private RoleBO roleBO;
	private RoleSrdaBo roleSrdaBO;
	private boolean success;

	
	/**
	 * @return Returns the roleBO.
	 */
	public RoleBO getRoleBO() {
		return roleBO;
	}
	/**
	 * @param roleBO The roleBO to set.
	 */
	public void setRoleBO(RoleBO roleBO) {
		this.roleBO = roleBO;
	}
	/*
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public RoleSrdaBo getRoleSrdaBO() {
		return roleSrdaBO;
	}
	public void setRoleSrdaBO(RoleSrdaBo roleSrdaBO) {
		this.roleSrdaBO = roleSrdaBO;
	}
    
}
