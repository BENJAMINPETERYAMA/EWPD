/*
 * MandateDefenitionAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.MandateListLocateCriteria;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateDefenitionAdapterManager {
    
    private AdapterServicesAccess getAdapterService(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }
    
    /**
	 * This method is to get the mandate list from the table
	 * @param locateCriteria
	 * @return
	 * @throws WPDException
	 */
	public LocateResults locateMandateList(LocateCriteria locateCriteria) throws WPDException {
	    // create the object for the locate results
	    LocateResults locateResults = new LocateResults();
	    // create a list
	    List locateResultList = new ArrayList();
	    // create the reference of the SearchResults
	    SearchResults searchResults = null;
	    // create the object for the search criteria
	    SearchCriteria searchCriteria = new SearchCriteriaImpl();
	    // set the required things in the search criteria
	    searchCriteria.setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.MandateListBOImpl");
	    searchCriteria.setMaxSearchResultSize(100);
	    searchCriteria.setSearchDomain("medical");
	    searchCriteria.setSearchQueryName("searchMandateList");
	    // create the referenceValueSet Hashmap
	    HashMap refValSet = new HashMap();
	    MandateListLocateCriteria mandateListLocateCriteria = (MandateListLocateCriteria) locateCriteria;
	    searchCriteria.setReferenceValueSet(refValSet);
	    try {
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
        	Logger.logError(e);
		}
		// set the locateResultList to the locateResults
		locateResults.setLocateResults(searchResults.getSearchResults());
	    // return locate results
	    return locateResults;
	}
    
}
