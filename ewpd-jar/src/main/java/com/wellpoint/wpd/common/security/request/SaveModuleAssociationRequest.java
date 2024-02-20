/*
 * SaveModuleAssociationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.security.vo.ModuleVO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveModuleAssociationRequest extends WPDRequest {
    private ModuleVO moduleVO;
    
    private String srdaFlag;

    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }

  
    /**
     * @return Returns the moduleVO.
     * @return ModuleVO moduleVO
     */
    public ModuleVO getModuleVO() {
        return moduleVO;
    }
    /**
     * Sets the moduleVO
     * @param moduleVO
     */
    public void setModuleVO(ModuleVO moduleVO) {
        this.moduleVO = moduleVO;
    }


	public String getSrdaFlag() {
		return srdaFlag;
	}


	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
}
