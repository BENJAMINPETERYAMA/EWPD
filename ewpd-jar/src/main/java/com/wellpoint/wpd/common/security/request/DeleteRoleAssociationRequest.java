/*
 * DeleteRoleAssociationRequest.java
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
public class DeleteRoleAssociationRequest extends WPDRequest {
    
    private int entityId;
    
    private String entityType;
    
    private int roleId;
    
    private int associatedModuleId;
    
    private int associatedTaskId;
    
    private int associatedSubTaskId;

    /**
     * @return associatedModuleId
     * Returns the associatedModuleId.
     */
    public int getAssociatedModuleId() {
        return associatedModuleId;
    }
    /**
     * @param associatedModuleId
     * Sets the associatedModuleId.
     */
    public void setAssociatedModuleId(int associatedModuleId) {
        this.associatedModuleId = associatedModuleId;
    }
    /**
     * @return associatedSubTaskId
     * Returns the associatedSubTaskId.
     */
    public int getAssociatedSubTaskId() {
        return associatedSubTaskId;
    }
    /**
     * @param associatedSubTaskId
     * Sets the associatedSubTaskId.
     */
    public void setAssociatedSubTaskId(int associatedSubTaskId) {
        this.associatedSubTaskId = associatedSubTaskId;
    }
    /**
     * @return associatedTaskId
     * Returns the associatedTaskId.
     */
    public int getAssociatedTaskId() {
        return associatedTaskId;
    }
    /**
     * @param associatedTaskId
     * Sets the associatedTaskId.
     */
    public void setAssociatedTaskId(int associatedTaskId) {
        this.associatedTaskId = associatedTaskId;
    }
    /**
     * @return entityId
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * @return entityType
     * Returns the entityType.
     */
    public String getEntityType() {
        return entityType;
    }
    /**
     * @param entityType
     * Sets the entityType.
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    /**
     * @return roleId
     * Returns the roleId.
     */
    public int getRoleId() {
        return roleId;
    }
    /**
     * @param roleId
     * Sets the roleId.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

}
