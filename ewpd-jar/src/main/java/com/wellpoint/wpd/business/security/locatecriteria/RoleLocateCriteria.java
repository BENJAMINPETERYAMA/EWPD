/*
 * RoleLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.security.locatecriteria;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleLocateCriteria  extends LocateCriteria{
    private String roleName;
	
    private int roleId;
    
	private String moduleName;
	
	private String taskName;
	
	private String subTaskName;
	
	private List moduleNameList;
    
    private List  taskNameList;
    
    private List subTaskNameList;
    
    private int associatedModuleId;
    
    private int associatedTaskId;
    
    private String subEntityType;
    
    private String action;
    
     private String srdaFlag;
   
     
    /**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}
	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
	/**
     * @return action
     * Returns the action.
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action
     * Sets the action.
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * @return subEntityType
     * Returns the subEntityType.
     */
    public String getSubEntityType() {
        return subEntityType;
    }
    /**
     * @param subEntityType
     * Sets the subEntityType.
     */
    public void setSubEntityType(String subEntityType) {
        this.subEntityType = subEntityType;
    }
    /**
     * @return associatedTaskId
     * Returns the associatedTaskId.
     */
    public int getAssociatedTaskId() {
        return associatedTaskId;
    }
    /**
     * @param associatedTaskId
     * Sets the associatedTaskId.
     */
    public void setAssociatedTaskId(int associatedTaskId) {
        this.associatedTaskId = associatedTaskId;
    }
    /**
     * @return associatedModuleId
     * 
     * Returns the associatedModuleId.
     */
    public int getAssociatedModuleId() {
        return associatedModuleId;
    }
    /**
     * @param associatedModuleId
     * 
     * Sets the associatedModuleId.
     */
    public void setAssociatedModuleId(int associatedModuleId) {
        this.associatedModuleId = associatedModuleId;
    }

    /**
     * @return moduleName
     * 
     * Returns the moduleName.
     */
    public String getModuleName() {
        return moduleName;
    }
    /**
     * @param moduleName
     * 
     * Sets the moduleName.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    /**
     * @return roleName
     * 
     * Returns the roleName.
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * @param roleName
     * 
     * Sets the roleName.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * @return subTaskName
     * 
     * Returns the subTaskName.
     */
    public String getSubTaskName() {
        return subTaskName;
    }
    /**
     * @param subTaskName
     * 
     * Sets the subTaskName.
     */
    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }
    /**
     * @return taskName
     * 
     * Returns the taskName.
     */
    public String getTaskName() {
        return taskName;
    }
    /**
     * @param taskName
     * 
     * Sets the taskName.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
}
