/*
 * RoleVO.java
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
public class RoleVO {

    private String roleName;

    private int roleId;

    private String description;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;

    private int maxLocateResultSize;

    private List moduleNameList;

    private List taskNameList;

    private List subTaskNameList;
    
    private List moduleIdList;


    /**
     * @return subTaskNameList
     * 
     * Returns the subTaskNameList.
     */
    public List getSubTaskNameList() {
        return subTaskNameList;
    }


    /**
     * @param subTaskNameList
     * 
     * Sets the subTaskNameList.
     */
    public void setSubTaskNameList(List subTaskNameList) {
        this.subTaskNameList = subTaskNameList;
    }


    /**
     * @return maxLocateResultSize
     * 
     * Returns the maxLocateResultSize.
     */
    public int getMaxLocateResultSize() {
        return maxLocateResultSize;
    }


    /**
     * @param maxLocateResultSize
     * 
     * Sets the maxLocateResultSize.
     */
    public void setMaxLocateResultSize(int maxLocateResultSize) {
        this.maxLocateResultSize = maxLocateResultSize;
    }


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
     * @return moduleNameList
     * 
     * Returns the moduleNameList.
     */
    public List getModuleNameList() {
        return moduleNameList;
    }


    /**
     * @param moduleNameList
     * 
     * Sets the moduleNameList.
     */
    public void setModuleNameList(List moduleNameList) {
        this.moduleNameList = moduleNameList;
    }


    /**
     * @return taskNameList
     * 
     * Returns the taskNameList.
     */
    public List getTaskNameList() {
        return taskNameList;
    }


    /**
     * @param taskNameList
     * 
     * Sets the taskNameList.
     */
    public void setTaskNameList(List taskNameList) {
        this.taskNameList = taskNameList;
    }
    /**
     * @return Returns the moduleIdList.
     * @return List moduleIdList
     */
    public List getModuleIdList() {
        return moduleIdList;
    }
    /**
     * Sets the moduleIdList
     * @param moduleIdList
     */
    public void setModuleIdList(List moduleIdList) {
        this.moduleIdList = moduleIdList;
    }
}