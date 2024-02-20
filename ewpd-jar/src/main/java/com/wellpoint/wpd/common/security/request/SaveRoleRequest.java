/*
 * SaveRoleRequest.java
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
import com.wellpoint.wpd.common.security.vo.RoleVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveRoleRequest extends WPDRequest{
	
	private String srdaFlag;
	
	private boolean createFlag;
	
	private RoleVO roleVO;
	
		/**
	 * @return Returns the createFlag.
	 */
	public boolean isCreateFlag() {
		return createFlag;
	}
	/**
	 * @param createFlag The createFlag to set.
	 */
	public void setCreateFlag(boolean createFlag) {
		this.createFlag = createFlag;
	}
	/**
	 * @return Returns the roleVO.
	 */
	public RoleVO getRoleVO() {
		return roleVO;
	}
	/**
	 * @param roleVO The roleVO to set.
	 */
	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}
	
	public void validate() throws ValidationException {
    	
    }
	public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}

}
