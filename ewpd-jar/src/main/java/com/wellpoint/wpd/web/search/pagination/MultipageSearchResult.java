/*
 * MultipageSearchResult.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.pagination;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MultipageSearchResult.java 24880 2007-06-22 05:49:55Z u12046 $
 */
public class MultipageSearchResult implements MultipageSupport{
    protected Page[] pages;
    protected int currentPageNumber;
    protected int totalNumberOfResults;
    protected String type;
    protected int queryResultCount;
    
    /**
     * @return Returns the pages.
     */
    public Page[] getPages() {
        return pages;
    }
    /**
     * @param pages The pages to set.
     */
    public void setPages(Page[] pages) {
        this.pages = pages;
    }
    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getTotalNumberOfPages()
     * @return
     */
    public int getTotalNumberOfPages() {
        if(pages == null) return 0;
        return pages.length;
    }

    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getCurrentPageNumber()
     * @return
     */
    public int getCurrentPageNumber() {
        return currentPageNumber;
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
     * @return Returns the totalNumberOfResults.
     */
    public int getTotalNumberOfResults() {
        return totalNumberOfResults;
    }
    /**
     * @param totalNumberOfResults The totalNumberOfResults to set.
     */
    public void setTotalNumberOfResults(int totalNumberOfResults) {
        this.totalNumberOfResults = totalNumberOfResults;
    }
    
    /**
     * @return Returns the queryResultCount.
     */
    public int getQueryResultCount() {
        return queryResultCount;
    }
    /**
     * @param queryResultCount The queryResultCount to set.
     */
    public void setQueryResultCount(int queryResultCount) {
        this.queryResultCount = queryResultCount;
    }
    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getPage(int)
     * @param number
     * @return
     */
    public Page getPage(int number) {
        if(pages == null) return null;
        if(number > pages.length){
            number = pages.length;
        }else if(number == 0){
            number = 1;   
        }
        currentPageNumber = number;
        return pages[number -1];
        
    }
    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getNextPage()
     * @return
     */
    public Page getNextPage() {
        if(pages == null) return null;
        currentPageNumber++;
        if(currentPageNumber > pages.length){
            currentPageNumber = pages.length;
        }
        return pages[currentPageNumber - 1];
    }
    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getPreviousPage()
     * @return
     */
    public Page getPreviousPage() {
        if(pages == null) return null;
        currentPageNumber--;
        if(currentPageNumber < 0){
            currentPageNumber = 1;
        }
        return pages[currentPageNumber - 1];
    }
    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getFirstPage()
     * @return
     */
    public Page getFirstPage() {
        if(pages == null) return null;
        currentPageNumber = 1;
        return pages[currentPageNumber - 1];
    }
    /**
     * @see com.wellpoint.wpd.web.search.pagination.MultipageSupport#getLastPage()
     * @return
     */
    public Page getLastPage() {
        if(pages == null) return null;
        currentPageNumber = pages.length;
        return pages[currentPageNumber - 1];
    }
}
