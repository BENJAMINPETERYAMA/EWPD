/*
 * SearchService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.search.builder.SearchBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.exception.SearchCriteriaValidationException;
import com.wellpoint.wpd.common.search.request.AdvancedSearchRequest;
import com.wellpoint.wpd.common.search.request.BasicSearchRequest;
import com.wellpoint.wpd.common.search.request.BenefitComponentSearchViewRequest;
import com.wellpoint.wpd.common.search.request.BenefitViewRequest;
import com.wellpoint.wpd.common.search.request.ContractViewRequest;
import com.wellpoint.wpd.common.search.request.ProductStructureViewRequest;
import com.wellpoint.wpd.common.search.request.DomainFetchRequest;
import com.wellpoint.wpd.common.search.request.ProductViewRequest;
import com.wellpoint.wpd.common.search.request.RetrieveAssociationRequest;
import com.wellpoint.wpd.common.search.request.RetrieveAttachmentRequest;
import com.wellpoint.wpd.common.search.request.RetrieveRequest;
import com.wellpoint.wpd.common.search.request.SortRequest;
import com.wellpoint.wpd.common.search.response.DomainFetchResponse;
import com.wellpoint.wpd.common.search.response.RetrieveAssociationResponse;
import com.wellpoint.wpd.common.search.response.RetrieveAttachmentResponse;
import com.wellpoint.wpd.common.search.response.SearchResponse;
import com.wellpoint.wpd.common.search.result.ObjectDetail;
import com.wellpoint.wpd.common.search.result.ObjectIdentifier;
import com.wellpoint.wpd.common.search.result.SearchResult;
import com.wellpoint.wpd.common.search.result.SearchResultDetail;
import com.wellpoint.wpd.common.search.result.SearchResultSummary;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * This class contains the service implementations for the Search
 * functionalities.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchService extends WPDBusinessService {
    public static final int ASSOCIATION_RESULT_COUNT_LIMIT = 300;

    public static final int BASIC_SEARCH_RESULT_COUNT_LIMIT = 300;

    public static final int ADVANCED_SEARCH_RESULT_COUNT_LIMIT = 300;

    public static final int ATTACHMENT_RESULT_COUNT_LIMIT = 300;
    /**
     * 
     * @param basicSearchRequest
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(BasicSearchRequest basicSearchRequest)
            throws ServiceException {
        if (basicSearchRequest == null
                || basicSearchRequest.getBasicSearchCriteria() == null) {
            throw new IllegalArgumentException(
                    "execute method in SearchService.  basicSearchRequest is null or there is no criteria set.");
        }
        SearchController controller = new SearchController();
        SearchResponse response = new SearchResponse();
        List searchResults = new ArrayList();
        BasicSearchCriteria bsc = basicSearchRequest.getBasicSearchCriteria();
        if (bsc.getResultCountLimit() == 0) {
            bsc.setResultCountLimit(BASIC_SEARCH_RESULT_COUNT_LIMIT);
        }
        try {
            searchResults = controller.basicSearch(bsc);
            response.setSearchResults(searchResults);
        } catch (SearchCriteriaValidationException e) {
            ErrorMessage em = new ErrorMessage(e.getUserMessage());
            em.setParameters(e.getUserParameters());
            response.addMessage(em);
            List logParameters = new ArrayList();
            logParameters.add(basicSearchRequest);
            ServiceException se = new ServiceException(
                    "Search Criteria Validation Exception while processing basic search",
                    logParameters, e);
            Logger.logException(se);
        } catch (Exception e) {
            List logParameters = new ArrayList();
            logParameters.add(basicSearchRequest);
            String logMessage = "Failed while processing Basic Search";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param advancedSearchRequest
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(AdvancedSearchRequest advancedSearchRequest)
            throws ServiceException {
        if (advancedSearchRequest == null
                || advancedSearchRequest.getAdvancedSearchCriteria() == null) {
            throw new IllegalArgumentException(
                    "execute method in SearchService.  advancedSearchRequest is null or there is no criteria set.");
        }
        SearchController controller = new SearchController();
        SearchResponse response = new SearchResponse();
        List searchResults = new ArrayList();
        AdvancedSearchCriteria asc = advancedSearchRequest
                .getAdvancedSearchCriteria();
        if (asc.getResultCountLimit() == 0) {
            asc.setResultCountLimit(ADVANCED_SEARCH_RESULT_COUNT_LIMIT);
        }
        try {
            searchResults = controller.advancedSearch(asc);
            response.setSearchResults(searchResults);
        } catch (SearchCriteriaValidationException e) {
            ErrorMessage em = new ErrorMessage(e.getUserMessage());
            em.setParameters(e.getUserParameters());
            response.addMessage(em);
            List logParameters = new ArrayList();
            logParameters.add(advancedSearchRequest);
            ServiceException se = new ServiceException(
                    "Search Criteria Validation Exception while processing advanced search",
                    logParameters, e);
            Logger.logException(se);
        } catch (Exception e) {
            List logParameters = new ArrayList();
            logParameters.add(advancedSearchRequest);
            String logMessage = "Failed while processing Advanced Search";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param retrieveRequest
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(RetrieveRequest retrieveRequest)
            throws ServiceException {

        SearchController controller = new SearchController();
        try {
            SearchResult result = controller.retrieveObjects(retrieveRequest
                    .getObjectIdentifiers());
            SearchResponse response = new SearchResponse();

            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
            return response;
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(retrieveRequest);
            String logMessage = "Failed while processing retrieve details";
            throw new ServiceException(logMessage, logParameters, e);
        }
    }

    /**
     * Service to retrieve associations of a business object.
     * 
     * @param rar
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(RetrieveAssociationRequest rar)
            throws ServiceException {
        try {
            SearchController controller = new SearchController();
            SearchResult result = controller
                    .getAssociationForIdentifier(
                            rar.getObjectIdentifier(),
                            (rar.getResultCountLimit() == 0 ? ASSOCIATION_RESULT_COUNT_LIMIT
                                    : rar.getResultCountLimit()));
            RetrieveAssociationResponse response = new RetrieveAssociationResponse();
            if (result == null || result.getNumberOfResults() == 0) {
                response.addMessage(new InformationalMessage(
                        "search.association.message1"));
            } else {
                response.addSearchResult(result);
                List temp = new ArrayList();
                temp.add(rar.getObjectIdentifier());
                SearchResult objDet = controller.retrieveObjects(temp);
                if (objDet != null) {
                    if (objDet.getResults() != null) {
                        response.setDetail((ObjectDetail) objDet.getResults()
                                .get(0));
                    }
                }
            }

            return response;
        } catch (Exception e) {
            List logParameters = new ArrayList();
            logParameters.add(rar);
            String logMessage = "Error executing SearchService for RetrieveAssociationRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(SortRequest request) throws ServiceException {
        SearchResponse response = new SearchResponse();
        SearchController controller = new SearchController();
        try {
            SearchResultSummary result = controller.sort(request
                    .getObjectIdentifiers(), request.getObjectType(), request
                    .getFieldToSort(), request.getSortOrder());
            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for SortRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public RetrieveAttachmentResponse execute(RetrieveAttachmentRequest request)
            throws ServiceException {
        if (request == null || request.getIdentifier() == null) {
            throw new IllegalArgumentException(
                    "execute method in SearchService.  retrieveAttachmentRequest is null or there is no identifier set.");
        }
        SearchController controller = new SearchController();
        RetrieveAttachmentResponse response = new RetrieveAttachmentResponse();
        List searchResults = new ArrayList();
        try {
            List authorizedModules = getAuthorizedModules(request.getUser());
            searchResults = controller.retrieveAttachments(request
                    .getIdentifier(), authorizedModules,
                    ATTACHMENT_RESULT_COUNT_LIMIT);
            if (searchResults == null) {
                response.addMessage(new InformationalMessage(
                        "search.attachment.message"));
            } else {
                response.setSearchResults(searchResults);

                List temp = new ArrayList();
                temp.add(request.getIdentifier());
                SearchResult objDet = controller.retrieveObjects(temp);
                if (objDet != null) {
                    if (objDet.getResults() != null) {
                        response.setDetail((ObjectDetail) objDet.getResults()
                                .get(0));
                    }
                }
            }
        } catch (Exception e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while retrieving attachments";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }

    /**
     * @param user
     * @return
     */
    private List getAuthorizedModules(User user) {
        if (null != user) {
            List authModules = new ArrayList();
            if (user.isAuthorized(WebConstants.CONTRACT_MODULE,
                    WebConstants.MAINTAIN_TASK)) {
                authModules.add(SearchConstants.CONTRACT);
            }
            if (user.isAuthorized(WebConstants.PRODUCT_MODULE,
                    WebConstants.MAINTAIN_TASK)) {
                authModules.add(SearchConstants.PRODUCT);
            }
            if (user.isAuthorized(WebConstants.BENEFIT_COMPONENTS_MODULE,
                    WebConstants.MAINTAIN_TASK)) {
                authModules.add(SearchConstants.BENEFIT_COMPONENTS);
            }
            if (user.isAuthorized(WebConstants.BENEFIT_MODULE,
                    WebConstants.MAINTAIN_TASK)) {
                authModules.add(SearchConstants.BENEFIT);
                authModules.add(SearchConstants.BENEFIT_LEVEL);
            }
            return authModules;
        }
        return null;
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(ProductViewRequest request)
            throws ServiceException {
        SearchResponse response = new SearchResponse();
        SearchController controller = new SearchController();
        try {
            SearchResultDetail result = (SearchResultDetail) controller
                    .getproductViewDetails(request.getProductKey());
            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for BenefitViewRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(BenefitViewRequest request)
            throws ServiceException {
        SearchResponse response = new SearchResponse();
        SearchController controller = new SearchController();
        try {
            SearchResultDetail result = (SearchResultDetail) controller
                    .getBenefitViewDetails(request.getStandardBenefitKey());
            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for BenefitViewRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(ContractViewRequest request)
            throws ServiceException {
        SearchResponse response = new SearchResponse();
        SearchController controller = new SearchController();
        try {
            SearchResultDetail result = (SearchResultDetail) controller
                    .getContractViewDetails(request.getContractKey(), request
                            .getDataSegIdentifier());
            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for ContractViewRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public SearchResponse execute(BenefitComponentSearchViewRequest request)
            throws ServiceException {
        SearchResponse response = new SearchResponse();
        SearchController controller = new SearchController();
        try {
            SearchResultDetail result = (SearchResultDetail) controller
                    .getBenefitComponentViewDetails(request
                            .getBenefitComponentKey());
            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for SortRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
   /**
    * 
    * @param request
    * @return
    * @throws ServiceException
    */
    public SearchResponse execute(ProductStructureViewRequest request)
            throws ServiceException {
        SearchResponse response = new SearchResponse();
        SearchController controller = new SearchController();
        try {
            SearchResultDetail result = (SearchResultDetail) controller
                    .getProductStructureViewDetails(request
                            .getProductStructureId());
            List searchResults = new ArrayList();
            searchResults.add(result);
            response.setSearchResults(searchResults);
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for SortRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }
    /**
     * 
     * @param request
     * @return
     * @throws ServiceException
     */
    public DomainFetchResponse execute(DomainFetchRequest request)
            throws ServiceException {
        DomainFetchResponse response = new DomainFetchResponse();
        ObjectIdentifier identifier = request.getIdentifier();
        try {
            SearchBuilder builder = new SearchBuilder();
            response.setLob(builder.getLineOfBusiness(identifier));
            response.setBusinessEntity(builder.getBusinessEntity(identifier));
            response.setBusinessGroup(builder.getBusinessGroup(identifier));
        } catch (Exception e) {
        	Logger.logError(e);
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Error executing SearchService for DomainFetchRequest";
            ServiceException se = new ServiceException(logMessage,
                    logParameters, e);
            Logger.logException(se);
            throw se;
        }
        return response;
    }

}