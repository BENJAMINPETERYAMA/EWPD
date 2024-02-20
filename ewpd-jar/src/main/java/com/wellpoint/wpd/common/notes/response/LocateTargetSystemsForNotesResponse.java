/*
 * LocateTargetSystemsForNotesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.notes.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateTargetSystemsForNotesResponse extends WPDResponse{

    private List targetSystemsList;
    /**
     * Returns the targetSystemsList
     * @return List targetSystemsList.
     */

    public List getTargetSystemsList() {
        return targetSystemsList;
    }
    /**
     * Sets the targetSystemsList
     * @param targetSystemsList.
     */

    public void setTargetSystemsList(List targetSystemsList) {
        this.targetSystemsList = targetSystemsList;
    }
}
