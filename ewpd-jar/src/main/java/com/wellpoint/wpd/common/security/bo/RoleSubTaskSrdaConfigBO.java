package com.wellpoint.wpd.common.security.bo;

import java.util.Date;
import java.util.List;

public class RoleSubTaskSrdaConfigBO {
	

    
    private int rolModTaskId;
    
    private int subTaskId;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int roleSubTaskId;
    
    private List taskIdList;

    /**
     * @return roleSubTaskId
     * 
     * Returns the roleSubTaskId.
     */
    public int getRoleSubTaskId() {
        return roleSubTaskId;
    }
    /**
     * @param roleSubTaskId
     * 
     * Sets the roleSubTaskId.
     */
    public void setRoleSubTaskId(int roleSubTaskId) {
        this.roleSubTaskId = roleSubTaskId;
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
     * @return rolModTaskId
     * Returns the rolModTaskId.
     */
    public int getRolModTaskId() {
        return rolModTaskId;
    }
    /**
     * @param rolModTaskId
     * Sets the rolModTaskId.
     */
    public void setRolModTaskId(int rolModTaskId) {
        this.rolModTaskId = rolModTaskId;
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
