/*
 * SearchAdminOptionResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.adminoption.response;

import java.util.List;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * Response for searching Admin Option
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchAdminOptionResponse extends WPDResponse {

    private List adminOptionSearchResultList;


    /**
     * Returns the adminOptionSearchResultList.
     * 
     * @return adminOptionSearchResultList
     */
    public List getAdminOptionSearchResultList() {
        return adminOptionSearchResultList;
    }


    /**
     * Sets the adminOptionSearchResultList.
     * 
     * @param adminOptionSearchResultList
     */
    public void setAdminOptionSearchResultList(List adminOptionSearchResultList) {
        this.adminOptionSearchResultList = adminOptionSearchResultList;
    }

}