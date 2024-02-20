/*
 * SearchCriteria.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.criteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchCriteria.java 74835 2011-10-11 12:57:44Z u23599 $
 */
public abstract class SearchCriteria {
	private LimitedTo limitedTo;
	protected int resultCountLimit;
	/**
	 * @return Returns the limitedTo.
	 */
	public LimitedTo getLimitedTo() {
		return limitedTo;
	}
	/**
	 * @param limitedTo The limitedTo to set.
	 */
	public void setLimitedTo(LimitedTo limitedTo) {
		this.limitedTo = limitedTo;
	}

    /**
     * Returns the maximum number of records to be retrieved for a Search query. 
     * @return Returns the resultCountLimit.
     */
    public int getResultCountLimit() {
        return resultCountLimit;
    }
    
    
    /**
     * Sets the maximum number of records to be retrieved for a Search query. 
     * @param resultCountLimit The resultCountLimit to set.
     */
    public void setResultCountLimit(int resultCountLimit) {
        this.resultCountLimit = resultCountLimit;
    }
}
