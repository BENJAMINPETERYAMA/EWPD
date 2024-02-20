/*
 * TestPassAdminOptionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

/**
 * Response for Admin Option Test Pass
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TestPassAdminOptionResponse extends WPDResponse {
    private AdminOptionVO adminOptionVO;

    private boolean testPassErrorFlag = false;


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
     * Returns the testPassErrorFlag.
     * 
     * @return
     */
    public boolean isTestPassErrorFlag() {
        return testPassErrorFlag;
    }


    /**
     * The testPassErrorFlag to set.
     * 
     * @param testPassErrorFlag
     */
    public void setTestPassErrorFlag(boolean testPassErrorFlag) {
        this.testPassErrorFlag = testPassErrorFlag;
    }
}