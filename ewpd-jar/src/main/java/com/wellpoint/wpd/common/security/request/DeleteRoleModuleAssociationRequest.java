/*
 * DeleteRoleModuleAssociationRequest.java © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.security.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteRoleModuleAssociationRequest extends WPDRequest {
	private int roleModuleId;
	private int moduleId;
	private List moduleIdList;
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the moduleId.
	 */
	public int getModuleId() {
		return moduleId;
	}
	/**
	 * @param moduleId The moduleId to set.
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * @return Returns the roleModuleId.
	 */
	public int getRoleModuleId() {
		return roleModuleId;
	}
	/**
	 * @param roleModuleId The roleModuleId to set.
	 */
	public void setRoleModuleId(int roleModuleId) {
		this.roleModuleId = roleModuleId;
	}
	
	/**
	 * @return Returns the moduleIdList.
	 */
	public List getModuleIdList() {
		return moduleIdList;
	}
	/**
	 * @param moduleIdList The moduleIdList to set.
	 */
	public void setModuleIdList(List moduleIdList) {
		this.moduleIdList = moduleIdList;
	}
}
