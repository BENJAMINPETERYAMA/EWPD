/*
 * SearchResultDetail.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchResultDetail.java 41576 2008-01-10 10:16:23Z U12238 $
 */
public class SearchResultDetail extends SearchResult {
	private List objectDetails;
	
	/**
	 * @return Returns the objectDetails.
	 */
	public List getObjectDetails() {
		return objectDetails;
	}
	/**
	 * @param objectDetail The objectDetails to set.
	 */
	public void setObjectDetails(List objectDetail) {
		this.objectDetails = objectDetail;
	}
    /**
     * @see com.wellpoint.wpd.common.search.result.SearchResult#getNumberOfResults()
     * @return
     */
    public int getNumberOfResults() {
        // TODO Auto-generated method stub
        return 0;
    }
    /**
     * @see com.wellpoint.wpd.common.search.result.SearchResult#getResults()
     * @return
     */
    public List getResults() {
        return objectDetails;
    }
}
