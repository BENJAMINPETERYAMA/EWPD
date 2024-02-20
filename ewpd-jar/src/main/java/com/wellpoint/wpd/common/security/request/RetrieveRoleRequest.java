/*
 * RetrieveRoleRequest.java
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
public class RetrieveRoleRequest extends WPDRequest{
    private int action;
    private String srdaFlag;
    private List roleList;
    private RoleVO roleVO;
    private int roleId;
    
    /**
     * @return roleId
     * 
     * Returns the roleId.
     */
    public int getRoleId() {
        return roleId;
    }
    /**
     * @param roleId
     * 
     * Sets the roleId.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    /**
     * @return action
     * Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action
     * Sets the action.
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
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
	public void validate() throws ValidationException {
    	
    }

	/**
	 * @return Returns the roleList.
	 */
	public List getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList The roleList to set.
	 */
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}
	public String getSrdaFlag() {
		return srdaFlag;
	}
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
