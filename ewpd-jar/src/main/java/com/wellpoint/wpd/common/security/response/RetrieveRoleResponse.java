/*
 * RetrieveRoleResponse.java
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
import com.wellpoint.wpd.common.security.bo.RoleSrdaBo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveRoleResponse extends WPDResponse{
    
    private List rolelist;
    private RoleBO roleBO;
    
    private RoleSrdaBo roleSrdaBo;
    
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
	/**
	 * @return Returns the rolelist.
	 */
	public List getRolelist() {
		return rolelist;
	}
	/**
	 * @param rolelist The rolelist to set.
	 */
	public void setRolelist(List rolelist) {
		this.rolelist = rolelist;
	}
	/**
	 * @return the roleSrdaBo
	 */
	public RoleSrdaBo getRoleSrdaBo() {
		return roleSrdaBo;
	}
	/**
	 * @param roleSrdaBo the roleSrdaBo to set
	 */
	public void setRoleSrdaBo(RoleSrdaBo roleSrdaBo) {
		this.roleSrdaBo = roleSrdaBo;
	}
}
