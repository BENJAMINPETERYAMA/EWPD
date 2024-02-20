/*
 * SearchResults.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.util;

import java.util.ArrayList;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchResults {
	
	
	public static final int MAX_NO =20;
	private List objectIdentifiers;
	private List pages;
	private int totalPages;
	private int currentPageNumber=0;
	/**
	 * 
	 */
	public SearchResults(List objectIdentifiers) {
		this.objectIdentifiers=objectIdentifiers;
		if(null != objectIdentifiers && objectIdentifiers.size() > 0){
			//initializing page count
			int pages, remainder = 0;
			pages = objectIdentifiers.size() / MAX_NO;
			remainder = objectIdentifiers.size() % MAX_NO;
			totalPages = pages;
			if( remainder > 0 ){
				totalPages ++;
			}
			
			//initializing pages
			int startIndex = 0, endIndex = MAX_NO;
			if(totalPages == 1){
				endIndex = remainder;
			}
			//iterates till the last but one page
			List pageList = null;
			this.pages = new ArrayList();
			for(int page=0 ; page < totalPages -1 ; page++ , startIndex+=MAX_NO , endIndex+=MAX_NO){
				pageList = objectIdentifiers.subList(startIndex, endIndex);
				this.pages.add(pageList);
			}
			//adding last page
			pageList = objectIdentifiers.subList(startIndex, objectIdentifiers.size());
			if(pageList.size() > 0){
				this.pages.add(pageList);
			}
			currentPageNumber = 1;
		}
	}
	
	/**
	 * 
	 * @return int
	 */
	public int getCurrentPageNumber(){
		return currentPageNumber;
	}
	/**
	 * 
	 * @return int
	 */
	
	public int getNumberOfPages(){
		return totalPages;
	}
	/**
	 * 
	 * @return List
	 */
	public List getPage(int pageNumber){
		currentPageNumber=pageNumber;
		List page = null;
		if(null!=pages && pageNumber > 0 && pageNumber <= totalPages){
			page=(List)pages.get( pageNumber);
		}
		return page;
	}
	
	public List getCurrentPage(){
		if(currentPageNumber > 0 && currentPageNumber <= totalPages){
			return getPage(currentPageNumber);
		}
		return null;
	}
	
	/**
	 * 
	 * @return List
	 */
	
	public List getNextPage(){
		if(currentPageNumber==totalPages){
			return null;
		}
		return getPage(currentPageNumber+1);
	}
	/**
	 * 
	 * @return List 
	 */
	
	public List getPreviousPage(){
		if(currentPageNumber==1){
			return null;
		}
		return getPage(currentPageNumber-1);
	}
   public int getTotalResults(){
   	return objectIdentifiers.size();
   }
}
