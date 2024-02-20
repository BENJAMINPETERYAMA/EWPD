/*
 * SearchResultSummary.java
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
 * @version $Id: SearchResultSummary.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class SearchResultSummary extends SearchResult {
	
	private List objectIdentifiers;

	/**
	 * @return Returns the objectIdentifiers.
	 */
	public List getObjectIdentifiers() {
		return objectIdentifiers;
	}
	/**
	 * @param objectIdentifiers The objectIdentifiers to set.
	 */
	public void setObjectIdentifiers(List objectIdentifiers) {
		this.objectIdentifiers = objectIdentifiers;
	}
	
	public int getNumberOfResults(){
	    if(objectIdentifiers != null){
	        return objectIdentifiers.size();
	    }
	    return 0;
	}
    /**
     * @see com.wellpoint.wpd.common.search.result.SearchResult#getResults()
     * @return
     */
    public List getResults() {
        return objectIdentifiers;
    }
}
