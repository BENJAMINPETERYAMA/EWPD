/*
 * PopupAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.popup.adapter;


import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PopupAdapterManager {
	
	/**
	 *  
	 * @param productId
	 * @return List
	 * @throws SevereException
	 */
	public List getSearchResults(PopupFilterBO pupupBO)
			throws SevereException {
		Logger
				.logInfo("Entering the method for getting valid search result for forpopup ");
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				PopupFilterBO.class.getName(),
				pupupBO.getQueryName(), pupupBO.getHashMap());
		
		if("locateRuleId".equals(pupupBO.getQueryName())){
			searchCriteria.setMaxSearchResultSize(51);
		}
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for getting valid search result for forpopup");
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;
	}

}
