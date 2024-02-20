/*
 * ModuleDeleteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import java.util.List;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.ModuleBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleDeleteResponse extends WPDResponse {
	private ModuleBO moduleBO;
	
	private List moduleList;

	private boolean isModuleRole;
	
	private boolean isModuleTask; 
	
	
	/**
	 * @return Returns the moduleBO.
	 */
	public ModuleBO getModuleBO() {
		return moduleBO;
	}
	/**
	 * @param moduleBO The moduleBO to set.
	 */
	public void setModuleBO(ModuleBO moduleBO) {
		this.moduleBO = moduleBO;
	}
	/**
	 * @return Returns the moduleList.
	 */
	public List getModuleList() {
		return moduleList;
	}
	/**
	 * @param moduleList The moduleList to set.
	 */
	public void setModuleList(List moduleList) {
		this.moduleList = moduleList;
	}
	
	/**
	 * @return Returns the isModuleRole.
	 */
	public boolean isModuleRole() {
		return isModuleRole;
	}
	/**
	 * @param isModuleRole The isModuleRole to set.
	 */
	public void setModuleRole(boolean isModuleRole) {
		this.isModuleRole = isModuleRole;
	}
	/**
	 * @return Returns the isModuleTask.
	 */
	public boolean isModuleTask() {
		return isModuleTask;
	}
	/**
	 * @param isModuleTask The isModuleTask to set.
	 */
	public void setModuleTask(boolean isModuleTask) {
		this.isModuleTask = isModuleTask;
	}
}
	
	