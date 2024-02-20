/*
 * RoleBO.java
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
public class RoleBO {

    private String roleName;

    private int roleId;

    private String description;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private List associatedModules;
    
    private List associatedTasks;
    
    private List associatedSubtasks;
    
    

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return Returns the roleId.
     */
    public int getRoleId() {
        return roleId;
    }


    /**
     * @param roleId
     *            The roleId to set.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    /**
     * @return Returns the roleName.
     */
    public String getRoleName() {
        return roleName;
    }


    /**
     * @param roleName
     *            The roleName to set.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    

	/**
	 * @return Returns the associatedModules.
	 */
	public List getAssociatedModules() {
		return associatedModules;
	}
	/**
	 * @param associatedModules The associatedModules to set.
	 */
	public void setAssociatedModules(List associatedModules) {
		this.associatedModules = associatedModules;
	}
	/**
	 * @return Returns the associatedSubtasks.
	 */
	public List getAssociatedSubtasks() {
		return associatedSubtasks;
	}
	/**
	 * @param associatedSubtasks The associatedSubtasks to set.
	 */
	public void setAssociatedSubtasks(List associatedSubtasks) {
		this.associatedSubtasks = associatedSubtasks;
	}
	/**
	 * @return Returns the associatedTasks.
	 */
	public List getAssociatedTasks() {
		return associatedTasks;
	}
	/**
	 * @param associatedTasks The associatedTasks to set.
	 */
	public void setAssociatedTasks(List associatedTasks) {
		this.associatedTasks = associatedTasks;
	}
}