/*
 * UserImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.security.domain;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.util.Environment;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: UserImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class UserImpl implements User {

    private List roles;
    private String userId = "USER";

    /**
     *  
     */
    public UserImpl() {
        super();
    }

    /**
     * @return Returns the role.
     */
    public List getRoles() {
        return roles;
    }

    /**
     * @param roles The role to set.
     */
    public void setRoles(List roles) {
        this.roles = roles;
    }
    
    public void addRole(Role role){
        if(roles == null){
            roles = new ArrayList();
        }
        roles.add(role);
    }

    /**
     * @see com.wellpoint.wpd.common.framework.security.domain.User#getUserId()
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @see com.wellpoint.wpd.common.framework.security.domain.User#isAuthorized(long)
     * @param module
     * @return
     */
    public boolean isAuthorized(String module) {
        return isAuthorized(module, null, null);
    }

    /**
     * @see com.wellpoint.wpd.common.framework.security.domain.User#isAuthorized(long,
     *      long)
     * @param module
     * @param task
     * @return
     */
    public boolean isAuthorized(String module, String task) {
        return isAuthorized(module, task, null);
    }
    /**
     * 
     * @see com.wellpoint.wpd.common.framework.security.domain.User#isAuthorized(java.lang.String, java.lang.String, java.lang.String)
     * @param module
     * @param task
     * @param subtask
     * @return
     */
    public boolean isAuthorized(String module, String task, String subtask){
        if(roles == null || roles.isEmpty()){
            if(Environment.isDevelopment() || Environment.isSystemTest()){
                return true;
            }
            return false;
        }
        for(int i=0;i<roles.size();i++){
            Role role = (Role)roles.get(i);
            if(role.isAuthorized(module, task, subtask)){
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("userId=").append(userId).append(",");
        if (roles != null) {
            sb.append("roles=").append(roles.toString());
        }
        return sb.toString();
    }

}