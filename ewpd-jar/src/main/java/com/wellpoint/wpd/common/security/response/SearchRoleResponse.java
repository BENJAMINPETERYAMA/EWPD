/*
 * SearchRoleResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchRoleResponse extends WPDResponse {
    private List roleSearchResultList;

    /**
     * @return roleSearchResultList
     * 
     * Returns the roleSearchResultList.
     */
    public List getRoleSearchResultList() {
        return roleSearchResultList;
    }
    /**
     * @param roleSearchResultList
     * 
     * Sets the roleSearchResultList.
     */
    public void setRoleSearchResultList(List roleSearchResultList) {
        this.roleSearchResultList = roleSearchResultList;
    }
}
