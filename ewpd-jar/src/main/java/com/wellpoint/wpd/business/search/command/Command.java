/*
 * Command.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.command;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.search.result.SearchResult;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Command.java 42077 2008-01-25 11:52:19Z u13547 $
 */
public abstract class Command implements Runnable{
    protected String objectType;
    protected List exceptions;
    protected SearchResult searchResult;
    protected int resultCountLimit;
    
    /**
     * @return Returns the objectTypeAndPageNumber.
     */
    public String getObjectType() {
        return objectType;
    }
    /**
     * @param objectType The objectType to set.
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
    
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
        exceptions = this.exceptions;
    }
    
    
    /**
     * @return Returns the resultCountLimit.
     */
    public int getResultCountLimit() {
        return resultCountLimit;
    }
    
    /**
     * @param resultCountLimit The resultCountLimit to set.
     */
    public void setResultCountLimit(int resultCountLimit) {
        this.resultCountLimit = resultCountLimit;
    }
    public void addException(Exception e){
        if(exceptions == null){
            exceptions = new ArrayList();
        }
        exceptions.add(e);
    }
    
    public boolean hasErrors(){
        return (exceptions != null && exceptions.size() > 0);
    }
    
    public void run(){
        try{
            execute();
        }catch(Exception e){
        	Logger.logError(e);
            addException(e);
        }
    }
    
    public abstract void execute() throws SevereException;
	/**
	 * @return Returns the searchResults.
	 */
	public SearchResult getSearchResults() {
		return searchResult;
	}
	/**
	 * @param searchResult The searchResult to set.
	 */
	public void setSearchResults(SearchResult searchResult) {
		this.searchResult = searchResult;
	}
}
