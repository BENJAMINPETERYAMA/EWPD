/*
 * RetrieveAssociationResponse.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.response;

import com.wellpoint.wpd.common.search.result.ObjectDetail;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: RetrieveAssociationResponse.java 24880 2007-06-22 05:49:55Z u12046 $
 */
public class RetrieveAssociationResponse extends SearchResponse {
    protected ObjectDetail detail;
    /**
     * @return Returns the detail.
     */
    public ObjectDetail getDetail() {
        return detail;
    }
    /**
     * @param detail The detail to set.
     */
    public void setDetail(ObjectDetail detail) {
        this.detail = detail;
    }
}
