/*
 * TaskVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.vo;

import java.util.Date;
import java.util.List;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskVO {
    
    private int taskId;
    
    private int selectedTaskId;
    
    private int parentTaskId;
    
    private int moduleId;
    
    private String selectedTaskType;
    
    private String taskName;
    
    private String description;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List selectedSubTaskList;
    
    private int action;

    /**
     * @return moduleId
     * Returns the moduleId.
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * @param moduleId
     * Sets the moduleId.
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
    /**
     * @return parentTaskId
     * Returns the parentTaskId.
     */
    public int getParentTaskId() {
        return parentTaskId;
    }
    /**
     * @param parentTaskId
     * Sets the parentTaskId.
     */
    public void setParentTaskId(int parentTaskId) {
        this.parentTaskId = parentTaskId;
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
	 * @return Returns the selectedTaskId.
	 */
	public int getSelectedTaskId() {
		return selectedTaskId;
	}
	/**
	 * @param selectedTaskId The selectedTaskId to set.
	 */
	public void setSelectedTaskId(int selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}
	/**
	 * @return Returns the selectedTaskType.
	 */
	public String getSelectedTaskType() {
		return selectedTaskType;
	}
	/**
	 * @param selectedTaskType The selectedTaskType to set.
	 */
	public void setSelectedTaskType(String selectedTaskType) {
		this.selectedTaskType = selectedTaskType;
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
	 * @return Returns the selectedSubTaskList.
	 */
	public List getSelectedSubTaskList() {
		return selectedSubTaskList;
	}
	/**
	 * @param selectedSubTaskList The selectedSubTaskList to set.
	 */
	public void setSelectedSubTaskList(List selectedSubTaskList) {
		this.selectedSubTaskList = selectedSubTaskList;
	}
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
}
