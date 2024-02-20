/*
 * ScheduleForTestAdminOptionResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

/**
 * Response for Admin Option Schedule for Tset
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ScheduleForTestAdminOptionResponse extends WPDResponse {

    private AdminOptionVO adminOptionVO;

    private boolean scheduleForTestErrorFlag = false;


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
     * Returns the scheduleForTestErrorFlag.
     * 
     * @return scheduleForTestErrorFlag
     */
    public boolean isScheduleForTestErrorFlag() {
        return scheduleForTestErrorFlag;
    }


    /**
     * The scheduleForTestErrorFlag to set.
     * 
     * @param scheduleForTestErrorFlag
     */
    public void setScheduleForTestErrorFlag(boolean scheduleForTestErrorFlag) {
        this.scheduleForTestErrorFlag = scheduleForTestErrorFlag;
    }
}