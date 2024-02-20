/*
 * RetrieveAdminOptionResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

/**
 * Response for retrieving Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveAdminOptionResponse extends WPDResponse {

    private AdminOptionBO adminOptionBO;

    private AdminOptionVO adminOptionVO;
    
    private boolean unlockFlag;


    /**
     * Returns the adminOptionBO
     * 
     * @return AdminOptionBO adminOptionBO.
     */
    public AdminOptionBO getAdminOptionBO() {
        return adminOptionBO;
    }


    /**
     * Sets the adminOptionBO
     * 
     * @param adminOptionBO.
     */
    public void setAdminOptionBO(AdminOptionBO adminOptionBO) {
        this.adminOptionBO = adminOptionBO;
    }


    /**
     * Returns the adminOptionVO
     * 
     * @return AdminOptionVO adminOptionVO.
     */
    public AdminOptionVO getAdminOptionVO() {
        return adminOptionVO;
    }


    /**
     * Sets the adminOptionVO
     * 
     * @param adminOptionVO.
     */
    public void setAdminOptionVO(AdminOptionVO adminOptionVO) {
        this.adminOptionVO = adminOptionVO;
    }
    
    
	/**
	 * @return Returns the unlockFlag.
	 */
	public boolean isUnlockFlag() {
		return unlockFlag;
	}
	/**
	 * @param unlockFlag The unlockFlag to set.
	 */
	public void setUnlockFlag(boolean unlockFlag) {
		this.unlockFlag = unlockFlag;
	}
}