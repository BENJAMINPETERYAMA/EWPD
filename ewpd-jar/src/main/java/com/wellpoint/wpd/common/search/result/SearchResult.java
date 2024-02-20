/*
 * SearchResult.java
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
 * @version $Id: SearchResult.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public abstract class SearchResult {
    
    protected String type;
    protected int resultCount; 
    
    protected List exceptions;
    
	/**
	 * @return Returns the exceptions.
	 */
	public List getExceptions() {
		return exceptions;
	}
	/**
	 * @param exceptions The exceptions to set.
	 */
	public void setExceptions(List exceptions) {
		this.exceptions = exceptions;
	}
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return Returns the resultCount.
     */
    public int getResultCount() {
        return resultCount;
    }
    /**
     * @param resultCount The resultCount to set.
     */
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
    public abstract int getNumberOfResults();
    public abstract List getResults();
    
    
}
