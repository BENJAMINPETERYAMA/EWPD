/*
 * TaskBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.bx.mapping.application.security;

import java.util.Date;
import java.util.List;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskBO {
    
    private int taskId;
    
    private String taskName;
    
    private String description;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int moduleId;
    
    private int roleId;
    
    private List subTaskList;
    

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
     * @return Returns the createdTimestamp.
     * @return Date createdTimestamp
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
	/**
	 * @return Returns the createdUser.
	 * @return String createdUser
	 */
	public String getCreatedUser() {
	    return createdUser;
	}
	/**
	 * Sets the createdUser
	 * @param createdUser
	 */
	public void setCreatedUser(String createdUser) {
	    this.createdUser = createdUser;
	}
    /**
     * @return Returns the description.
     * @return String description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the lastUpdatedTimestamp.
     * @return Date lastUpdatedTimestamp
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return Returns the lastUpdatedUser.
     * @return String lastUpdatedUser
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    
    
	/**
	 * @return Returns the taskId.
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId The taskId to set.
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return Returns the taskName.
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName The taskName to set.
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
    /**
     * @return Returns the moduleId.
     * @return int moduleId
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * Sets the moduleId
     * @param moduleId
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
    
	/**
	 * @return Returns the subTaskList.
	 */
	public List getSubTaskList() {
		return subTaskList;
	}
	/**
	 * @param subTaskList The subTaskList to set.
	 */
	public void setSubTaskList(List subTaskList) {
		this.subTaskList = subTaskList;
	}
}
