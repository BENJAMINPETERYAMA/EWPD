/*
 * RetrieveModuleAssociationResponse.java
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
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveModuleAssociationResponse extends WPDResponse {
    private List moduleAssociationList;
    private ModuleSrdaBO moduleSrdaBO;
    private ModuleBO moduleBO;
    /**
     * @return Returns the moduleAssociationList.
     * @return List moduleAssociationList
     */
    public List getModuleAssociationList() {
        return moduleAssociationList;
    }
    /**
     * Sets the moduleAssociationList
     * @param moduleAssociationList
     */
    public void setModuleAssociationList(List moduleAssociationList) {
        this.moduleAssociationList = moduleAssociationList;
    }
    
    
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
	public ModuleSrdaBO getModuleSrdaBO() {
		return moduleSrdaBO;
	}
	public void setModuleSrdaBO(ModuleSrdaBO moduleSrdaBO) {
		this.moduleSrdaBO = moduleSrdaBO;
	}
}
