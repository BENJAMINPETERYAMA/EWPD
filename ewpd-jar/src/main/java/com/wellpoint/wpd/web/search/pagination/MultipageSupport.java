/*
 * MultipageSupport.java
 * 
 * � 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.pagination;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MultipageSupport.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public interface MultipageSupport {
    int getTotalNumberOfPages();
    int getCurrentPageNumber();
    Page getNextPage();
    Page getPreviousPage();
    Page getFirstPage();
    Page getLastPage();
    Page getPage(int number);
}
