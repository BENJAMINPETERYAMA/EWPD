package com.wellpoint.wpd.common.security.bo;

import java.util.Date;
import java.util.List;

public class SubTaskSrdaBO {


    private String subTaskName;
	
	private int subTaskId;
	
	private int moduleId;
	
	private String moduleName;
	
	private int taskId;
	
	private String taskName;
	
	private String taskType;
	
	private String description;
	
	private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List subTaskList;
    
    private int action;
    
    /**
     * @return taskType
     * Returns the taskType.
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * @param taskType
     * Sets the taskType.
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    /**
     * @return moduleName
     * Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }
    /**
     * @param moduleName
     * Sets the moduleName.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    /**
     * @return taskName
     * Returns the taskName.
     */
    public String getTaskName() {
        return taskName;
    }
    /**
     * @param taskName
     * Sets the taskName.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    /**
     * @return createdTimestamp
     * Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @param createdTimestamp
     * Sets the createdTimestamp.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * @return createdUser
     * Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * @param createdUser
     * Sets the createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * @return description
     * Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description
     * Sets the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return lastUpdatedTimestamp
     * Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * @param lastUpdatedTimestamp
     * Sets the lastUpdatedTimestamp.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return lastUpdatedUser
     * Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * @param lastUpdatedUser
     * Sets the lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
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
     * @return subTaskId
     * Returns the subTaskId.
     */
    public int getSubTaskId() {
        return subTaskId;
    }
    /**
     * @param subTaskId
     * Sets the subTaskId.
     */
    public void setSubTaskId(int subTaskId) {
        this.subTaskId = subTaskId;
    }
    /**
     * @return subTaskName
     * Returns the subTaskName.
     */
    public String getSubTaskName() {
        return subTaskName;
    }
    /**
     * @param subTaskName
     * Sets the subTaskName.
     */
    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }
    /**
     * @return taskId
     * Returns the taskId.
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * @param taskId
     * Sets the taskId.
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
