/*
 * CatalogBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.catalog.service;

import java.util.ArrayList;
import java.util.List;
import com.wellpoint.wpd.business.catalog.builder.CatalogBusinessObjectBuilder;
import com.wellpoint.wpd.business.catalog.locatecriteria.CatalogLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.bo.SrdaCatalogBO;
import com.wellpoint.wpd.common.catalog.request.CatalogDeleteRequest;
import com.wellpoint.wpd.common.catalog.request.CatalogRetrieveRequest;
import com.wellpoint.wpd.common.catalog.request.EditCatalogRequest;
import com.wellpoint.wpd.common.catalog.request.SaveCatalogRequest;
import com.wellpoint.wpd.common.catalog.request.SearchCatalogRequest;
import com.wellpoint.wpd.common.catalog.request.SrdaCatalogRetrieveRequest;
import com.wellpoint.wpd.common.catalog.response.CatalogDeleteResponse;
import com.wellpoint.wpd.common.catalog.response.CatalogRetrieveResponse;
import com.wellpoint.wpd.common.catalog.response.EditCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SaveCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SearchCatalogResponse;
import com.wellpoint.wpd.common.catalog.response.SrdaCatalogRetrieveResponse;
import com.wellpoint.wpd.common.catalog.vo.CatalogLocateCriteriaVO;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.security.bo.RoleBO;

/**
 * Base class for catalog business service
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class CatalogBusinessService extends WPDService {

    /**
     * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
     * @param request
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(WPDRequest request) throws ServiceException {
        throw new ServiceException("Unknown Request Type", null, null);
    }

    /**
     * Method to save catalog
     * 
     * @param request
     * @return
     * @throws SevereException
     */
    public WPDResponse execute(SaveCatalogRequest request)
            throws ServiceException {
        Logger
                .logInfo("CatalogBusinessService - Entering execute(): Catalog Create");
        SaveCatalogResponse saveCatalogResponse = null;
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        CatalogBO catalogBO = getCatalogObject(catalogVO);
        saveCatalogResponse = (SaveCatalogResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SAVE_CATALOG_RESPONSE);
        saveCatalogResponse = (SaveCatalogResponse) new ValidationServiceController()
                .execute(request);
        if (saveCatalogResponse.isSuccess()) {
            try {
                catalogBusinessObjectBuilder.persist(catalogBO, request
                        .getUser(), true);
                catalogBO = catalogBusinessObjectBuilder.retrieve(catalogBO, request.getUser());
            } catch (SevereException e) {
                List logParameters = new ArrayList();
                logParameters.add(request);
                String logMessage = "Failed while processing SaveCatalogRequest";
                throw new ServiceException(logMessage, logParameters, e);
            }
            saveCatalogResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_CATALOG_SAVE_SUCCESS));
        }

        saveCatalogResponse.setCatalogBO(catalogBO);
        Logger
                .logInfo("CatalogBusinessService - Returning execute(): Catalog Create");
        return saveCatalogResponse;
    }

    /**
     * Method to delete catalog
     * 
     * @param request
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(CatalogDeleteRequest request)
            throws ServiceException {
        Logger
                .logInfo("CatalogBusinessService - Entering execute(): Catalog Delete");
        CatalogDeleteResponse catalogDeleteResponse = null;
        List locateResultList = null;
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        CatalogBO catalogBO = getCatalogObject(catalogVO);
        catalogDeleteResponse = (CatalogDeleteResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.DELETE_CATALOG_RESPONSE);
        try {
			catalogDeleteResponse = (CatalogDeleteResponse) new ValidationServiceController()
			        .execute(request);
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        if (catalogDeleteResponse.isSuccess()) {
            try {
                catalogBusinessObjectBuilder.delete(catalogBO, request
                        .getUser());

            } catch (SevereException e) {
                List logParameters = new ArrayList();
                logParameters.add(request);
                String logMessage = "Failed while processing CatalogDeleteRequest";
                throw new ServiceException(logMessage, logParameters, e);
            }
            catalogDeleteResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_CATALOG_DELETE_SUCCESS));

        }

        try {
            CatalogLocateCriteria catalogLocateCriteria = (CatalogLocateCriteria) getCatalogLocateCriteriaBO(request
                    .getCatalogLocateCriteriaVO());
            LocateResults locateResults = catalogBusinessObjectBuilder.locate(
                    catalogLocateCriteria, request.getUser());
            if (null != locateResults
                    && locateResults.getLocateResults().size() != 0) {
                locateResultList = locateResults.getLocateResults();
                catalogDeleteResponse.setCatalogList(locateResultList);
            }
        } catch (SevereException e2) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogDeleteRequest";
            throw new ServiceException(logMessage, logParameters, e2);
        }

        Logger
                .logInfo("CatalogBusinessService - Returning execute(): Catalog Delete");
        return catalogDeleteResponse;
    }

    /**
     * Method to retrieve catalog
     * 
     * @param request
     * @return WPDResponse
     * @throws SevereException
     */
    public WPDResponse execute(CatalogRetrieveRequest request)
            throws ServiceException {
        Logger
                .logInfo("CatalogBusinessService - Entering execute(): Catalog Retrieve");
        CatalogRetrieveResponse catalogRetrieveResponse = null;
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        CatalogBO catalogBO = getCatalogObject(catalogVO);
        catalogRetrieveResponse = (CatalogRetrieveResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_CATALOG_RESPONSE);
        try {
            catalogBusinessObjectBuilder.retrieve(catalogBO, request.getUser());
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogRetrieveRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        catalogRetrieveResponse.setCatalogBO(catalogBO);
        Logger
                .logInfo("CatalogBusinessService - Returning execute(): Catalog Retrieve");
        return catalogRetrieveResponse;
    }
    
    /**
     * Method to retrieve catalog
     * 
     * @param request
     * @return WPDResponse
     * @throws SevereException
     */
    public WPDResponse execute(SrdaCatalogRetrieveRequest request)
            throws ServiceException {
        Logger
                .logInfo("CatalogBusinessService - Entering execute(): Catalog Retrieve");
        SrdaCatalogRetrieveResponse srdaCatalogRetrieveResponse = null;
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        SrdaCatalogBO srdaCatalogBO = getSrdaCatalogObject(catalogVO);
         srdaCatalogRetrieveResponse = (SrdaCatalogRetrieveResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.RETRIEVE_SRDA_CATALOG_RESPONSE);
        try {
            catalogBusinessObjectBuilder.retrieveSrdaData(srdaCatalogBO, request.getUser());
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogRetrieveRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        srdaCatalogRetrieveResponse.setSrdaCatalogBO(srdaCatalogBO);
        Logger
                .logInfo("CatalogBusinessService - Returning execute(): Catalog Retrieve");
        return srdaCatalogRetrieveResponse;
    }

    /**
     * Method to edit catalog. Catalog size and datatype are allowed to be
     * editted if and only if no items are associated with the catalog.
     * 
     * @param request
     * @return WPDResponse
     * @throws SevereException
     */
    public WPDResponse execute(EditCatalogRequest request)
            throws ServiceException {
        Logger
                .logInfo("CatalogBusinessService - Entering execute(): Catalog Edit");
        EditCatalogResponse editCatalogResponse = null;
        CatalogBusinessObjectBuilder catalogBusinessObjectBuilder = new CatalogBusinessObjectBuilder();
        CatalogBO catalogBO = null;
        int action = 0;
        CatalogVO catalogVO = request.getCatalogVO();
        editCatalogResponse = (EditCatalogResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.EDIT_CATALOG_RESPONSE);
        try {
            catalogBO = getCatalogObject(request, true);
            catalogBO = catalogBusinessObjectBuilder.retrieve(catalogBO,
                    request.getUser());
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing CatalogRetrieve";
            throw new ServiceException(logMessage, logParameters, e);
        }

        if (!(catalogVO.getCatalogDatatype().equals(catalogBO
                .getCatalogDatatype()))
                || ((catalogVO.getCatalogSize()) != (catalogBO.getCatalogSize()))) {
            editCatalogResponse = (EditCatalogResponse) new ValidationServiceController()
                    .execute(request);
            List messageList = new ArrayList();
            if (!editCatalogResponse.isSuccess()) {

                //if item is associated and if old datatype and new datatype
                // are different
                if (!(catalogVO.getCatalogDatatype().equals(catalogBO
                        .getCatalogDatatype()))) {
                    messageList
                            .add(new ErrorMessage("catalog.datatype.update"));
                }
                //if item is associated and if old datatype and new datatype
                // are different
                if (catalogVO.getCatalogSize() != catalogBO.getCatalogSize()) {
                    messageList.add(new ErrorMessage("catalog.size.update"));
                }
                catalogBO = getCatalogObject(request, false);//for setting old
                                                             // datatype and
                                                             // size since item
                                                             // is associated
                editCatalogResponse.setMessages(messageList);
                //cannot update new datatype and size since item is associated
                action = 1;
            } else {
                //for setting new datatype and size since item is not
                // associated
                catalogBO = getCatalogObject(request, true);
                //item not associated,can update new datatype and size
                action = 2;
            }

        } else {
            //for setting new datatype and size since new and old datatype and
            // size are same
            catalogBO = getCatalogObject(request, true);
            //can update new datatype and size
            action = 2;
        }

        try {
            catalogBusinessObjectBuilder.persist(catalogBO, request.getUser(),
                    false);
            catalogBO = catalogBusinessObjectBuilder.retrieve(catalogBO,
                    request.getUser());
        } catch (SevereException e) {
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = "Failed while processing EditCatalogRequest";
            throw new ServiceException(logMessage, logParameters, e);
        }
        if (action == 2) {
            editCatalogResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_CATALOG_UPDATE_SUCCESS));
        }
        //for updating only description since item is associated
        else if (action == 1
                && !catalogVO.getDescription().equals(
                        catalogVO.getDescriptionOld())) {
            editCatalogResponse.addMessage(new InformationalMessage(
                    BusinessConstants.MSG_CATALOG_DESC_UPDATE_SUCCESS));
        }

        editCatalogResponse.setCatalogBO(catalogBO);
        Logger
                .logInfo("CatalogBusinessService - Returning execute(): Catalog Edit");
        return editCatalogResponse;
    }

    /**
     * Searches a catalog
     * 
     * @param searchCatalogRequest
     * @return WPDResponse
     * @throws ServiceException
     */
    public WPDResponse execute(SearchCatalogRequest searchCatalogRequest)
            throws ServiceException {
        Logger.logInfo("Entering execute method for searching catalog");
        List locateResultList = null;
        int locateResultCount = 0;
        List errorList = new ArrayList(1);
        SearchCatalogResponse response = null;
        CatalogLocateCriteriaVO catalogLocateCriteriaVO = (CatalogLocateCriteriaVO) searchCatalogRequest
                .getCatalogLocateCriteriaVO();
        CatalogLocateCriteria catalogLocateCriteria = (CatalogLocateCriteria) getCatalogLocateCriteriaBO(catalogLocateCriteriaVO);

        response = (SearchCatalogResponse) WPDResponseFactory
                .getResponse(WPDResponseFactory.SEARCH_CATALOG_RESPONSE);

        try {
            CatalogBusinessObjectBuilder builder = new CatalogBusinessObjectBuilder();
            LocateResults locateResults = builder.locate(catalogLocateCriteria,
                    searchCatalogRequest.getUser());
            if (null != locateResults) {
                locateResultList = locateResults.getLocateResults();
                locateResultCount = locateResultList.size();
                if (locateResultCount == 0) {
                    errorList.add(new InformationalMessage(
                            BusinessConstants.SEARCH_RESULT_NOT_FOUND));
                    response.setMessages(errorList);
                } else {
                    response.setCatalogList(locateResults.getLocateResults());
                }

            }
        } catch (SevereException ex) {
            List logParameters = new ArrayList();
            logParameters.add(searchCatalogRequest);
            String logMessage = "Failed while processing SearchCatalogRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }
        Logger.logInfo("Returning execute method for searching catalog");
        return response;

    }

    /**
     * Sets the values from Locate Criteria VO to BO
     * 
     * @param catalogLocateCriteriaVO
     * @return catalogLocateCriteria
     */
    private CatalogLocateCriteria getCatalogLocateCriteriaBO(
            CatalogLocateCriteriaVO catalogLocateCriteriaVO) {
        CatalogLocateCriteria catalogLocateCriteria = new CatalogLocateCriteria();
        catalogLocateCriteria.setCatalogName(catalogLocateCriteriaVO
                .getCatalogName());
        catalogLocateCriteria.setCatalogDescription(catalogLocateCriteriaVO
                .getCatalogDescription());
        catalogLocateCriteria.setSrdaFlag(catalogLocateCriteriaVO.getSrdaFlag());
        return catalogLocateCriteria;
    }

    /**
     * Checks whether the fields are empty
     * 
     * @param catalogLocateCriteriaVO
     * @return boolean
     */
    private boolean isAllFieldsBlank(
            CatalogLocateCriteriaVO catalogLocateCriteriaVO) {
        if ((null == catalogLocateCriteriaVO.getCatalogName() || ""
                .equals(catalogLocateCriteriaVO.getCatalogName()))
                && (null == catalogLocateCriteriaVO.getCatalogDescription() || ""
                        .equals(catalogLocateCriteriaVO.getCatalogDescription()))) {
            return true;
        }
        return false;
    }

    
    /**
     * Method to get CatalogBO for EditCatalogRequest
     * 
     * @param request
     * @param action
     * @return
     */
    private CatalogBO getCatalogObject(EditCatalogRequest request,
            boolean action) {
        CatalogBO catalogBO = new CatalogBO();
        CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
        catalogBO.setCatalogId(catalogVO.getCatalogId());
        catalogBO.setCatalogName(catalogVO.getCatalogName());
        //setting the old datatype and size to catalogBO since item is
        // associated
        if (action == false) {
            catalogBO.setCatalogDatatype(catalogVO.getCatalogDatatypeOld());
            catalogBO.setCatalogSize(catalogVO.getCatalogSizeOld());
        }
        //setting the new datatype and size to catalogBO
        else {
            catalogBO.setCatalogDatatype(catalogVO.getCatalogDatatype());
            catalogBO.setCatalogSize(catalogVO.getCatalogSize());
        }
        catalogBO.setDescription(catalogVO.getDescription());
        catalogBO.setCatalogParentID(null);
        return catalogBO;

    }

    /**
     * Method to get CatalogBO for request
     * 
     * @param catalogVO
     * @return CatalogBO
     */
    private CatalogBO getCatalogObject(CatalogVO catalogVO) {
        CatalogBO catalogBO = new CatalogBO();
        catalogBO.setCatalogId(catalogVO.getCatalogId());
        catalogBO.setCatalogName(catalogVO.getCatalogName());
        catalogBO.setCatalogSize(catalogVO.getCatalogSize());
        catalogBO.setCatalogDatatype(catalogVO.getCatalogDatatype());
        catalogBO.setDescription(catalogVO.getDescription());
        return catalogBO;

    }

    /**
     * Method to get SrdaCatalogBO for request
     * 
     * @param catalogVO
     * @return CatalogBO
     */
    private SrdaCatalogBO getSrdaCatalogObject(CatalogVO catalogVO) {
    	SrdaCatalogBO srdaCatalogBO = new SrdaCatalogBO();
    	srdaCatalogBO.setCatalogId(catalogVO.getCatalogId());
    	srdaCatalogBO.setCatalogName(catalogVO.getCatalogName());
    	srdaCatalogBO.setCatalogSize(catalogVO.getCatalogSize());
    	srdaCatalogBO.setCatalogDatatype(catalogVO.getCatalogDatatype());
    	srdaCatalogBO.setDescription(catalogVO.getDescription());
        return srdaCatalogBO;

    }
}