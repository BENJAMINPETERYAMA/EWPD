/*
 * Paginator.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.pagination;

import java.util.List;

import com.wellpoint.wpd.common.search.result.SearchResult;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Paginator.java 24880 2007-06-22 05:49:55Z u12046 $
 */
public class Paginator {

    private static final int RECORDS_PER_PAGE = 20;
    
    public MultipageSearchResult paginate(SearchResult result){
        int resultCount = result.getNumberOfResults();
        int totalNumberOfPages = 0;
        Page[] pages = null;
        if(resultCount > 0){
            totalNumberOfPages = resultCount / RECORDS_PER_PAGE;
            if(resultCount % RECORDS_PER_PAGE != 0){
                totalNumberOfPages++;
            }
            pages = new Page[totalNumberOfPages];
            List results = result.getResults();
            
            int index = 0;
            for(int i=0;i<pages.length;i++){
                pages[i] = new Page();
                pages[i].setNumber(i);
                Object[] temp = null;
                if((resultCount - index) >= RECORDS_PER_PAGE){
                    temp = new Object[RECORDS_PER_PAGE];
                }else{
                    temp = new Object[resultCount - index];
                }
                for(int j=0;j<temp.length;j++){
                    temp[j] = results.get(index);
                    index++;
                }
                pages[i].setObjects(temp);
            }
        }
        MultipageSearchResult msr = new MultipageSearchResult();
        msr.setPages(pages);
        msr.setType(result.getType());
        msr.setTotalNumberOfResults(result.getNumberOfResults());
        msr.setQueryResultCount(result.getResultCount());
        return msr;
    }
}
