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
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;

import java.util.List;

/**
 * Response for Admin Option View
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ViewAdminOptionResponse extends WPDResponse {

    private AdminOptionBO adminOptionBO;

    private AdminOptionVO adminOptionVO;

    List viewResponseList;


    /**
     * Returns the adminOptionBO.
     * 
     * @return adminOptionBO
     */
    public AdminOptionBO getAdminOptionBO() {
        return adminOptionBO;
    }


    /**
     * The adminOptionBO to set.
     * 
     * @param adminOptionBO
     */
    public void setAdminOptionBO(AdminOptionBO adminOptionBO) {
        this.adminOptionBO = adminOptionBO;
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
     * Returns the viewResponseList.
     * 
     * @return viewResponseList
     */
    public List getViewResponseList() {
        return viewResponseList;
    }


    /**
     * The viewResponseList to set.
     * 
     * @param viewResponseList
     */
    public void setViewResponseList(List viewResponseList) {
        this.viewResponseList = viewResponseList;
    }
}