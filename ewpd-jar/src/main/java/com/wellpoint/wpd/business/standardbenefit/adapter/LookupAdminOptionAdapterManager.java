/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.standardbenefit.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.AdminLevelLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.LookupAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.standardbenefit.bo.LookupAdminOptionBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author u13664
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LookupAdminOptionAdapterManager {
    /**
     * @param locateCriteria
     * @return
     * @throws SevereException
     *             Connect to the adapter for searching the admin options
     */
    public LocateResults locate(LocateCriteria locateCriteria)
            throws SevereException {
        List locateResultsList = new ArrayList();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        LocateResults locateResults = new LocateResults();
        SearchResults searchResults = null;
        LookupAdminOptionLocateCriteria lookupAdminOptionLocateCriteria = (LookupAdminOptionLocateCriteria) locateCriteria;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.LOOK_UP_ADMIN_OPTION_BO);
        searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
        // **Change
        //searchCriteria.setSearchQueryName(BusinessConstants.getAdminOptionList);
        List benefitTerms = null;
        List benefitQualifiers=null;
        if (lookupAdminOptionLocateCriteria.getBenefitLevelSystemId() == 0) {
            searchCriteria
                    .setSearchQueryName(BusinessConstants.getAdminOptionList);
        } else {
            searchCriteria
                    .setSearchQueryName("getAdminOptionListForBenefitLevel");
        }
        // **End
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        if (lookupAdminOptionLocateCriteria.getBenefitLevelSystemId() == 0) {
            refValSet.put(BusinessConstants.BENEFIT_LEVEL_SYS_ID, null);
        } else {
            refValSet.put(BusinessConstants.BENEFIT_LEVEL_SYS_ID, new Integer(
                    lookupAdminOptionLocateCriteria.getBenefitLevelSystemId()));
            // **Change
            // get the benefit terms list
            benefitTerms = getBenefitTerms(locateCriteria);
            refValSet.put("benfitTerms", benefitTerms);
            // **End
//          get the benefit terms list
            benefitQualifiers = getBenefitQualifiers(locateCriteria);
            refValSet.put("benefitQualifiers", benefitQualifiers);
        }
        refValSet.put(BusinessConstants.BENEFIT_ADMIN_SYS_ID, new Integer(
                lookupAdminOptionLocateCriteria.getBenefitAdminSystemId()));
        //refValSet.put(BusinessConstants.ADMIN_OPT_REF_ID, new Integer(1));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        return locateResults;
    }


    // **Change
    /**
     *  
     */
    private List getBenefitTerms(LocateCriteria locateCriteria)
            throws ServiceException {
        List benefitTerms = null;
        List locateResultsList = new ArrayList();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LookupAdminOptionLocateCriteria lookupAdminOptionLocateCriteria = (LookupAdminOptionLocateCriteria) locateCriteria;
        searchCriteria
                .setBusinessObjectName(BusinessConstants.LOOK_UP_ADMIN_OPTION_BO);
        searchCriteria.setMaxSearchResultSize(lookupAdminOptionLocateCriteria
                .getMaximumResultSize());
        searchCriteria.setSearchQueryName("getTermsList");
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();
        refValSet.put(BusinessConstants.BENEFIT_LEVEL_SYS_ID, new Integer(
                lookupAdminOptionLocateCriteria.getBenefitLevelSystemId()));
        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = getAdapterService().searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        if (null != searchResults.getSearchResults()
                && !searchResults.getSearchResults().isEmpty()) {
            benefitTerms = new ArrayList();
            LookupAdminOptionBO adminOptionBO = (LookupAdminOptionBO) searchResults
                    .getSearchResults().get(0);
            String benefitTerm = adminOptionBO.getBenefitTerms();
            if (null != benefitTerm && !"".equals(benefitTerm)) {
                StringTokenizer tokenizer = new StringTokenizer(benefitTerm,
                        ",");
                while (tokenizer.hasMoreTokens()) {
                    benefitTerms.add(tokenizer.nextToken());
                }
            }
        }
        return benefitTerms;
    }
    
    // **End
    
    private List getBenefitQualifiers(LocateCriteria locateCriteria)
    throws ServiceException {
	List benefitQualifiers = null;
	List locateResultsList = new ArrayList();
	SearchCriteria searchCriteria = new SearchCriteriaImpl();
	SearchResults searchResults = null;
	LookupAdminOptionLocateCriteria lookupAdminOptionLocateCriteria = (LookupAdminOptionLocateCriteria) locateCriteria;
	searchCriteria
	        .setBusinessObjectName(BusinessConstants.LOOK_UP_ADMIN_OPTION_BO);
	searchCriteria.setMaxSearchResultSize(lookupAdminOptionLocateCriteria
	        .getMaximumResultSize());
	searchCriteria.setSearchQueryName("getQualifiersList");
	searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
	HashMap refValSet = new HashMap();
	refValSet.put(BusinessConstants.BENEFIT_LEVEL_SYS_ID, new Integer(
	        lookupAdminOptionLocateCriteria.getBenefitLevelSystemId()));
	searchCriteria.setReferenceValueSet(refValSet);
	try {
	    searchResults = getAdapterService().searchObject(searchCriteria);
	} catch (AdapterException adapterException) {
	    logAdapterExceptionForSearch(
	            searchCriteria.getBusinessObjectName(), searchCriteria
	                    .getSearchQueryName(), adapterException);
	}
	if (null != searchResults.getSearchResults()
	        && !searchResults.getSearchResults().isEmpty()) {
		benefitQualifiers = new ArrayList();
	    LookupAdminOptionBO adminOptionBO = (LookupAdminOptionBO) searchResults
	            .getSearchResults().get(0);
	    String benefitQualifier = adminOptionBO.getBenefitQualifiers();
	    if (null != benefitQualifier && !"".equals(benefitQualifier)) {
	        StringTokenizer tokenizer = new StringTokenizer(benefitQualifier,
	                ",");
	        while (tokenizer.hasMoreTokens()) {
	        	benefitQualifiers.add(tokenizer.nextToken());
	        }
	    }
	}
	return benefitQualifiers;
}

    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
     * @param locateCriteria
     * @return
     * @throws WPDException
     */
    public LocateResults locate(AdminLevelLocateCriteria locateCriteria)
            throws SevereException {
        com.wellpoint.wpd.common.framework.logging.Logger
                .logInfo("BenefitDefinitionAdapterManager");
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        List locateResultsList = new ArrayList();
        searchCriteria
                .setBusinessObjectName(BusinessConstants.AdminLevelLocateCriteria);
        searchCriteria.setMaxSearchResultSize(100);
        searchCriteria
                .setSearchQueryName(BusinessConstants.getAssociatedAdminLevels);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        HashMap refValSet = new HashMap();

        searchCriteria.setReferenceValueSet(refValSet);
        try {
            searchResults = AdapterUtil.getAdapterService().searchObject(
                    searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterExceptionForSearch(
                    searchCriteria.getBusinessObjectName(), searchCriteria
                            .getSearchQueryName(), adapterException);
        }
        locateResults.setLocateResults(searchResults.getSearchResults());
        locateResults
                .setTotalResultsCount(searchResults.getSearchResultCount());
        return locateResults;
    }


    private AdapterServicesAccess getAdapterService() {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }


    private void logAdapterExceptionForSearch(String businessObjectName,
            String queryName, AdapterException adapterException)
            throws ServiceException {
        List errorParams = new ArrayList(2);
        errorParams.add(businessObjectName);
        errorParams.add(queryName);
        throw new ServiceException("Adapter Exception occured", errorParams,
                adapterException);
    }

}