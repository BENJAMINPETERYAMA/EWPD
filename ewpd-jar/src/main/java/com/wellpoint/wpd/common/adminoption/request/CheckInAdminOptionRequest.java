/*
 * CheckInAdminOptionRequest.java
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
 * Request for Check In Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CheckInAdminOptionRequest extends WPDRequest {

    private boolean checkInOpted = false;

    private AdminOptionVO adminOptionVO;


    /**
     * Returns the checkInOpted.
     * 
     * @return checkInOpted
     */
    public boolean isCheckInOpted() {
        return checkInOpted;
    }


    /**
     * Sets the checkInOpted.
     * 
     * @param checkInOpted
     */
    public void setCheckInOpted(boolean checkInOpted) {
        this.checkInOpted = checkInOpted;
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
        if (-1 == this.adminOptionVO.getAdminOptionId())
            throw new ValidationException("Admin Option Id is missing", null,
                    null);
    }
}