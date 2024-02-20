/*
 * ModuleVO.java
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

public class ModuleVO {
    
    private String moduleName;
    
    private List taskIdList;
    
    private String description;
    
    private int selectedModuleId;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private int moduleId;
    
    

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
     * @return Returns the moduleName.
     * @return String moduleName
     */
    public String getModuleName() {
        return moduleName;
    }
    /**
     * Sets the moduleName
     * @param moduleName
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    /**
     * @return Returns the selectedModuleId.
     * @return int selectedModuleId
     */
    public int getSelectedModuleId() {
        return selectedModuleId;
    }
    /**
     * Sets the selectedModuleId
     * @param selectedModuleId
     */
    public void setSelectedModuleId(int selectedModuleId) {
        this.selectedModuleId = selectedModuleId;
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
}
