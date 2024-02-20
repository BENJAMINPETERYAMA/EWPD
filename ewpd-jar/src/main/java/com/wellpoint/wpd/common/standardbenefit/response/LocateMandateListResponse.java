/*
 * LocateMandateListResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateMandateListResponse extends WPDResponse{
    // variable declaration
    List mandateList;
    /**
     * @return Returns the mandateList.
     */
    public List getMandateList() {
        return mandateList;
    }
    /**
     * @param mandateList The mandateList to set.
     */
    public void setMandateList(List mandateList) {
        this.mandateList = mandateList;
    }
}
