/*
 * PaginationEngine.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.util;

import java.util.List;





/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PaginationEngine {

	
	public SearchResults paginate(List objectidentifiers){		
		SearchResults searchResults=new SearchResults(objectidentifiers);
		return searchResults;
	}
}
