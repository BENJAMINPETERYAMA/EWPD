/*
 * LocateSubTaskResponse.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
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
public class LocateSubTaskResponse extends WPDResponse {
    private List subTaskList;
    
    /**
     * @return Returns the subTaskList.
     * @return List subTaskList
     */
    public List getSubTaskList() {
        return subTaskList;
    }
    /**
     * Sets the subTaskList
     * @param subTaskList
     */
    public void setSubTaskList(List subTaskList) {
        this.subTaskList = subTaskList;
    }
}
