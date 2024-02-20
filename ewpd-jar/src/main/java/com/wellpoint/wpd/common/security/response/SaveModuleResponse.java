/*
 * SaveModuleResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.ModuleBO;
import com.wellpoint.wpd.common.security.bo.ModuleSrdaBO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveModuleResponse extends WPDResponse {
    
    private ModuleBO moduleBO;
    
    private ModuleSrdaBO moduleSrdaBO;
    
    private boolean success;

    /**
     * @return Returns the moduleBO.
     * @return ModuleBO moduleBO
     */
    public ModuleBO getModuleBO() {
        return moduleBO;
    }
    /**
     * Sets the moduleBO
     * @param moduleBO
     */
    public void setModuleBO(ModuleBO moduleBO) {
        this.moduleBO = moduleBO;
    }
    /**
     * @return Returns the success.
     * @return boolean success
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * Sets the success
     * @param success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
	public ModuleSrdaBO getModuleSrdaBO() {
		return moduleSrdaBO;
	}
	public void setModuleSrdaBO(ModuleSrdaBO moduleSrdaBO) {
		this.moduleSrdaBO = moduleSrdaBO;
	}
}
