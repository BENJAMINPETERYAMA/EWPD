/*
 * DeleteAdminOptionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
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
 * Request for deleting Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteAdminOptionRequest extends WPDRequest {

    AdminOptionVO adminOptionVO;


    /**
     * Default Constructor
     *  
     */
    public DeleteAdminOptionRequest() {
    }


    /**
     * Returns the adminOptionVO.
     * 
     * @return adminOptionVO
     */
    public AdminOptionVO getAdminOptionVO() {
        return adminOptionVO;
    }


    /**
     * The adminOptionVO to set.
     * 
     * @param adminOptionVO
     */
    public void setAdminOptionVO(AdminOptionVO adminOptionVO) {
        this.adminOptionVO = adminOptionVO;
    }


    /**
     * Method for validation
     * 
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        if (null == this.adminOptionVO.getAdminName()
                || "".equals(this.adminOptionVO.getAdminName()))
            throw new ValidationException("Admin Name is missing", null, null);
        if (-1 == this.adminOptionVO.getAdminOptionId())
            throw new ValidationException("Admin Option Id is missing", null,
                    null);

    }

}