/*
 * CreateAdminOptionRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

/**
 * Request for creating Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateAdminOptionRequest extends WPDRequest {

    private boolean createFlag;

    private AdminOptionVO adminOptionVO;


    /**
     * Returns the createFlag
     * 
     * @return boolean createFlag.
     */
    public boolean isCreateFlag() {
        return createFlag;
    }


    /**
     * Sets the createFlag
     * 
     * @param createFlag.
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
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
     * Method to validate the request Returns the void
     * 
     * @return void.
     */
    public void validate() throws ValidationException {
        if (null == this.adminOptionVO.getAdminName()
                || "".equals(this.adminOptionVO.getAdminName()))
            throw new ValidationException("Admin Name is missing", null, null);
    }
}