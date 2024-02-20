/*
 * ModuleLocateCriteria.java
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
public class ModuleLocateCriteria extends LocateCriteria {

    private String moduleName;
    
    private int moduleId;
    
    private List taskList;
    
    private int associatedTaskId;
     private String srdaFlag;
	
    /**
	 * @return Returns the associatedTaskId.
	 */
	public int getAssociatedTaskId() {
		return associatedTaskId;
	}
	/**
	 * @param associatedTaskId The associatedTaskId to set.
	 */
	public void setAssociatedTaskId(int associatedTaskId) {
		this.associatedTaskId = associatedTaskId;
	}
	/**
	 * @return Returns the moduleId.
	 */
	public int getModuleId() {
		return moduleId;
	}
	/**
	 * @param moduleId The moduleId to set.
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * @return Returns the moduleName.
	 */
	public String getModuleName() {
		return moduleName;
	}
	/**
	 * @param moduleName The moduleName to set.
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	/**
	 * @return Returns the taskList.
	 */
	public List getTaskList() {
		return taskList;
	}
	/**
	 * @param taskList The taskList to set.
	 */
	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}
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
}
    