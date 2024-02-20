package com.wellpoint.wpd.common.security.bo;

import java.util.Date;
import java.util.List;

public class ModuleConfigSrdaBO {

    private int moduleId;
    
    private int taskId;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List taskIdList;

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
     * @return Returns the taskId.
     * @return int taskId
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * Sets the taskId
     * @param taskId
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    
	/**
	 * @return Returns the taskIdList.
	 */
	public List getTaskIdList() {
		return taskIdList;
	}
	/**
	 * @param taskIdList The taskIdList to set.
	 */
	public void setTaskIdList(List taskIdList) {
		this.taskIdList = taskIdList;
	}


}
