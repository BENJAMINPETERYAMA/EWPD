/*
 * PublishAdminOptionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

/**
 * Response for Publishing Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PublishAdminOptionResponse extends WPDResponse {

    private AdminOptionVO adminOptionVO;

    private boolean publishErrorFlag = false;


    /**
     * Returns the publishErrorFlag.
     * 
     * @return publishErrorFlag
     */
    public boolean isPublishErrorFlag() {
        return publishErrorFlag;
    }


    /**
     * The publishErrorFlag to set.
     * 
     * @param publishErrorFlag
     */
    public void setPublishErrorFlag(boolean publishErrorFlag) {
        this.publishErrorFlag = publishErrorFlag;
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
}