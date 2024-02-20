/*
 * SearchAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.engine.AdapterServicesController;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.search.criteria.LimitedTo;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * The SearchAdapterManager does all adapter functionalities 
 * related to search engine.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchAdapterManager.java 57544 2009-11-08 07:52:56Z U20780 $
 */
public class SearchAdapterManager {

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults productsBasicSearch(LimitedTo limitedTo, String query,
            int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ProductIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchProductIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productName", query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults contractsBasicSearch(LimitedTo limitedTo,
            String query, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ContractIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchContractIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("contractId", query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws ServiceException
     */
    public SearchResults productStructuresBasicSearch(LimitedTo limitedTo,
            String query, int recordCountLimit) throws ServiceException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ProductStructureIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchProductStructureIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productStructureName", query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);

        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }

    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws ServiceException
     */
    public SearchResults benefitComponentsBasicSearch(LimitedTo limitedTo,
            String query, int recordCountLimit) throws ServiceException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchBenefitComponentIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("name", query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);

        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }

    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws ServiceException
     */
    public SearchResults benefitsBasicSearch(LimitedTo limitedTo, String query,
            int recordCountLimit) throws ServiceException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchBenefitIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("name", query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);

        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }

    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws ServiceException
     */
    public SearchResults benefitLevelsBasicSearch(LimitedTo limitedTo,
            String query, int recordCountLimit) throws ServiceException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchBenefitLevelIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("description", query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);

        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }

    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws ServiceException
     */
    public SearchResults notesBasicSearch(LimitedTo limitedTo, String query,
            int recordCountLimit) throws ServiceException {

        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.NotesIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchNotesIdentifiersForBasicSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("noteName", query);

        searchCriteria.setReferenceValueSet(referenceValueSet);

        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults productsAdvancedSearch(LimitedTo limitedTo, Map query,
            int recordCountLimit) throws SevereException {

        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ProductIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchProductIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();

        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults contractsAdvancedSearch(LimitedTo limitedTo,
            Map query, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ContractIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchContractIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults productStructuresAdvancedSearch(LimitedTo limitedTo,
            Map query, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ProductStructureIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchProductStructureIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);

        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults benefitComponentsAdvancedSearch(LimitedTo limitedTo,
            Map query, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchBenefitComponentIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults benefitsAdvancedSearch(LimitedTo limitedTo, Map query,
            int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchBenefitIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults benefitLevelsAdvancedSearch(LimitedTo limitedTo,
            Map query, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchBenefitLevelIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        /*START CARS*/
        //Market Business Unit
        referenceValueSet.put(WebConstants.MARKET_BUSINESS_UNIT_SEARCH, limitedTo
                .getPermutedListForMarketBusinessUnit());
        /*END CARS*/

        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param limitedTo
     * @param query
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults notesAdvancedSearch(LimitedTo limitedTo, Map query,
            int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.NotesIdentifier";
        String domainName = "medical";
        String searchQueryName = "searchNotesIdentifiersForAdvancedSearch";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.putAll(query);
        referenceValueSet.put("lineOfBusinessesToSearch", limitedTo
                .getPermutedListForLineOfBusiness());
        referenceValueSet.put("businessGroupsToSearch", limitedTo
                .getPermutedListForBusinessGroup());
        referenceValueSet.put("businessEntitiesToSearch", limitedTo
                .getPermutedListForBusinessEntity());
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param type
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getNotesRefItemDetails(String type,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.NotesDomainDetail";
        String domainName = "medical";
        String searchQueryName = type;
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitDataTypeDetails(String description,
            int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchDataTypeDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getNotesRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.NoteItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchTermItem";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getContractRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ContractItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getProductRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ProductItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getProductStructureRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ProductStructureItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitComponentRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitComponentItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getContractDomainItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.ContractDomainItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefProdItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return  adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }

    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitLevelRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        String businessObjectName = "com.wellpoint.wpd.common.search.result.BenefitLevelItemDetail";
        String domainName = "medical";
        String searchQueryName = "searchRefItemDetails";
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domainName);
        searchCriteria.setSearchQueryName(searchQueryName);
        searchCriteria.setMaxSearchResultSize(recordCountLimit);
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("catalogId", new Integer(catalogId));
        referenceValueSet.put("description", description);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        try {
            return adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(searchCriteria);
            throw new ServiceException("Adapter Exception occured",
                    errorParams, adapterException);
        }
    }
}