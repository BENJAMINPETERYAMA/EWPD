package com.wellpoint.wpd.common.accumulator.bo;

import java.util.List;

import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchResultSetImpl implements SearchResultSet {

    private List searchResult;

    /**
     * Returns the searchResult.
     * @return List searchResult.
     */
    public List getSearchResult() {
        return searchResult;
    }

    /**
     * sets searchResult.
     * @param searchResult.
     */
    public void setSearchResult(List searchResult) {
        this.searchResult = searchResult;
    }

    /**
     * Overriding toString method
     * @return String.
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("searchResult = ").append(searchResult).append(" ");
        return buffer.toString();
    }
}