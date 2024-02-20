/*
 * AdminOptionViewResponse.java
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
 * Response for Admin Option View
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionViewResponse extends WPDResponse {

    private List adminOptionResultList;


    /**
     * Returns the adminOptionResultList.
     * @return adminOptionResultList
     */
    public List getAdminOptionResultList() {
        return adminOptionResultList;
    }


    /**
     * The adminOptionResultList to set.
     * @param adminOptionResultList
     */
    public void setAdminOptionResultList(List adminOptionResultList) {
        this.adminOptionResultList = adminOptionResultList;
    }
}