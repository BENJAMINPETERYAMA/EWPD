/*
 * CheckInAdminOptionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
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
 * Response for Check In Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CheckInAdminOptionResponse extends WPDResponse {

    private boolean errorFlag;

    private AdminOptionBO adminOptionBO;

    private AdminOptionVO adminOptionVO;


    /**
     * Returns the errorFlag.
     * 
     * @return errorFlag
     */
    public boolean isErrorFlag() {
        return errorFlag;
    }


    /**
     * Sets the errorFlag.
     * 
     * @param errorFlag
     */
    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }


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
}