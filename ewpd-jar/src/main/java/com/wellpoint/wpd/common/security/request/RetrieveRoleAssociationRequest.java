/*
 * RetrieveRoleAssociationRequest.java
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

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveRoleAssociationRequest  extends WPDRequest{
    private int roleId;
    private int associatedModuleId;
    private int associatedTaskId;
    private String  subEntityType;
    private String  action;
    private String srdaFlag;
    
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    
   
    /**
     * @return action
     * Returns the action.
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action
     * Sets the action.
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * @return associatedModuleId
     * 
     * Returns the associatedModuleId.
     */
    public int getAssociatedModuleId() {
        return associatedModuleId;
    }
    /**
     * @param associatedModuleId
     * 
     * Sets the associatedModuleId.
     */
    public void setAssociatedModuleId(int associatedModuleId) {
        this.associatedModuleId = associatedModuleId;
    }
    /**
     * @return associatedTaskId
     * 
     * Returns the associatedTaskId.
     */
    public int getAssociatedTaskId() {
        return associatedTaskId;
    }
    /**
     * @param associatedTaskId
     * 
     * Sets the associatedTaskId.
     */
    public void setAssociatedTaskId(int associatedTaskId) {
        this.associatedTaskId = associatedTaskId;
    }
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
     * @return subEntityType
     * 
     * Returns the subEntityType.
     */
    public String getSubEntityType() {
        return subEntityType;
    }
    /**
     * @param subEntityType
     * 
     * Sets the subEntityType.
     */
    public void setSubEntityType(String subEntityType) {
        this.subEntityType = subEntityType;
    }


	/**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}


	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
    
    
}
