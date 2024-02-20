/*
 * RoleTaskConfigBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.bo;

import java.util.Date;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleTaskConfigBO {
    
   
    private int rolModTaskId;
    
    
    private int taskId;
    
    private List taskIdList;
    
    private int moduleId;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int roleTaskId;
    
    private List moduleIdList;
    
    /**
     * @return roleTaskId
     * 
     * Returns the roleTaskId.
     */
    public int getRoleTaskId() {
        return roleTaskId;
    }
    /**
     * @param roleTaskId
     * 
     * Sets the roleTaskId.
     */
    public void setRoleTaskId(int roleTaskId) {
        this.roleTaskId = roleTaskId;
    }
    /**
     * @return createdTimestamp
     * 
     * Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp
     * 
     * Sets the createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return createdUser
     * 
     * Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser
     * 
     * Sets the createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return lastUpdatedTimestamp
     * 
     * Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp
     * 
     * Sets the lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return lastUpdatedUser
     * 
     * Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser
     * 
     * Sets the lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return rolModTaskId
     * 
     * Returns the rolModTaskId.
     */
    public int getRolModTaskId() {
        return rolModTaskId;
    }
    /**
     * @param rolModTaskId
     * 
     * Sets the rolModTaskId.
     */
    public void setRolModTaskId(int rolModTaskId) {
        this.rolModTaskId = rolModTaskId;
    }
    
    /**
     * @return moduleId
     * 
     * Returns the moduleId.
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * @param moduleId
     * 
     * Sets the moduleId.
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
    
    /**
     * @return taskId
     * 
     * Returns the taskId.
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * @param taskId
     * 
     * Sets the taskId.
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    /**
     * @return taskIdList
     * 
     * Returns the taskIdList.
     */
    public List getTaskIdList() {
        return taskIdList;
    }
    /**
     * @param taskIdList
     * 
     * Sets the taskIdList.
     */
    public void setTaskIdList(List taskIdList) {
        this.taskIdList = taskIdList;
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
