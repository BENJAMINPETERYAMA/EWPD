/*
 * ModuleDeleteRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.request;

import com.wellpoint.wpd.business.security.locatecriteria.ModuleLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.security.vo.ModuleVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleDeleteRequest extends WPDRequest {

    private ModuleVO moduleVO;
    private ModuleLocateCriteria moduleLocateCriteria;
    

	/**
	 * @return Returns the moduleLocateCriteria.
	 */
	public ModuleLocateCriteria getModuleLocateCriteria() {
		return moduleLocateCriteria;
	}
	/**
	 * @param moduleLocateCriteria The moduleLocateCriteria to set.
	 */
	public void setModuleLocateCriteria(
			ModuleLocateCriteria moduleLocateCriteria) {
		this.moduleLocateCriteria = moduleLocateCriteria;
	}
	/**
	 * @return Returns the moduleVO.
	 */
	public ModuleVO getModuleVO() {
		return moduleVO;
	}
	/**
	 * @param moduleVO The moduleVO to set.
	 */
	public void setModuleVO(ModuleVO moduleVO) {
		this.moduleVO = moduleVO;
	}
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
}
    