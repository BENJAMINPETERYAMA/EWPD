/*
 * RetrieveUserRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.request;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: UserObjectRequest.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class RetrieveUserRequest extends WPDRequest {

    private String userId;

    private List roleNames;

    /**
     * @return Returns the roleName.
     */
    public List getRoleNames() {
        return roleNames;
    }

    /**
     * @param roleNames
     *            The roleName to set.
     */
    public void setRoleNames(List roleNames) {
        this.roleNames = roleNames;
    }

    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *  
     */
    public RetrieveUserRequest() {
        super();
    }
    
    public void addRoleName(String roleName){
        if(roleNames == null){
            roleNames = new ArrayList();
        }
        roleNames.add(roleName);
    }

    public void validate() throws ValidationException {
        if (userId == null || roleNames == null || userId.trim().length() == 0
                || roleNames.size() == 0) {
            List params = new ArrayList();
            params.add(this);
            throw new ValidationException("Not enough data.  userId=" + userId
                    + " roleNames=" + roleNames, params, null);
        }
    }
}