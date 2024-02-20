/*
 * SummaryCommand.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.command;

import java.util.List;

import com.wellpoint.wpd.business.search.builder.SearchBuilder;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.search.criteria.LimitedTo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SummaryCommand.java 26313 2007-07-05 21:28:59Z U12046 $
 */
public class SummaryCommand extends Command {
	private LimitedTo limitedTo;
	private List attributes;
	
	private int searchType;
	
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
     * @throws SevereException
     * @see com.wellpoint.wpd.business.search.command.Command#execute()
     * 
     */
    public void execute() throws SevereException {
    	SearchBuilder builder = new SearchBuilder();
    	searchResult =  builder.search(this);
    }
	/**
	 * @return Returns the searchType.
	 */
	public int getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType The searchType to set.
	 */
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return Returns the attributes.
	 */
	public List getAttributes() {
		return attributes;
	}
	/**
	 * @param attributes The attributes to set.
	 */
	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}
}
