/*
 * SubcatalogBusinessService.java
 *  © 2006 WellPoint, Inc. All Rights
 * Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.subcatalog.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.subcatalog.builder.SubCatalogBusinessObjectBuilder;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.ReferenceDataLocateCriteria;
import com.wellpoint.wpd.business.subcatalog.locatecriteria.SubCatalogLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.catalog.bo.CatalogBO;
import com.wellpoint.wpd.common.catalog.vo.CatalogVO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.refdata.request.ReferenceDataLookupRequest;
import com.wellpoint.wpd.common.refdata.response.ReferenceDataLookupResponse;
import com.wellpoint.wpd.common.subcatalog.bo.SubCatalogBO;
import com.wellpoint.wpd.common.subcatalog.request.ItemAssociationDeleteRequest;
import com.wellpoint.wpd.common.subcatalog.request.ItemLookUpRequest;
import com.wellpoint.wpd.common.subcatalog.request.RetrieveSubCatalogRequest;
import com.wellpoint.wpd.common.subcatalog.request.SaveItemAssociationRequest;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogDeleteRequest;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogSaveRequest;
import com.wellpoint.wpd.common.subcatalog.request.SubCatalogSearchRequest;
import com.wellpoint.wpd.common.subcatalog.response.ItemAssociationDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.ItemLookUpResponse;
import com.wellpoint.wpd.common.subcatalog.response.RetrieveSubCatalogResponse;
import com.wellpoint.wpd.common.subcatalog.response.SaveItemAssociationResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogDeleteResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSaveResponse;
import com.wellpoint.wpd.common.subcatalog.response.SubCatalogSearchResponse;
import com.wellpoint.wpd.common.subcatalog.vo.ItemLocateCriteriaVO;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;

/**
 * Base class for subcatalog business service
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class SubCatalogBusinessService extends WPDService {

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
	 * Method to save subcatalog
	 * 
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(SubCatalogSaveRequest request)
			throws ServiceException {
		Logger
				.logInfo("Sub-CatalogBusinessService - Entering execute(): Sub-Catalog Create");
		SubCatalogSaveResponse subCatalogSaveResponse = null;
		SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = null;
		CatalogBO catalogBO = null;
		try {
			subCatalogSaveResponse = null;
			subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
			catalogBO = getSubCatalogObjectFromCatalogVO(request);
			subCatalogSaveResponse = (SubCatalogSaveResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.SAVE_SUB_CATALOG_RESPONSE);
			subCatalogSaveResponse = (SubCatalogSaveResponse) new ValidationServiceController()
					.execute(request);
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing SaveSubCatalogRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		if (subCatalogSaveResponse.isSuccess()) {
			try {

				if (request.getAction() == 1) {
					//if action=1,then insertion
					subCatalogBusinessObjectBuilder.persist(catalogBO, request
							.getUser(), true);
					subCatalogSaveResponse.addMessage(new InformationalMessage(
							BusinessConstants.MSG_SUB_CATALOG_SAVE_SUCCESS));
				} else {
					//if  action=2,then updation
					subCatalogBusinessObjectBuilder.persist(catalogBO, request
							.getUser(), false);
					subCatalogSaveResponse.addMessage(new InformationalMessage(
							BusinessConstants.MSG_SUB_CATALOG_UPDATE_SUCCESS));
				}

				catalogBO = subCatalogBusinessObjectBuilder.retrieve(catalogBO);

				// Set the BO in the response
				subCatalogSaveResponse.setCatalogBO(catalogBO);

			} catch (SevereException e) {
				List logParameters = new ArrayList();
				logParameters.add(request);
				String logMessage = "Failed while processing SaveSubCatalogRequest";
				throw new ServiceException(logMessage, logParameters, e);
			}

		} else {

			subCatalogSaveResponse.setSuccess(false);
			return subCatalogSaveResponse;
		}
		try {
			subCatalogSaveResponse.setDomainDetail(DomainUtil
					.retrieveDomainDetailInfo("SubCatalog", catalogBO
							.getCatalogId()));
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing SaveSubCatalogRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		subCatalogSaveResponse.setCatalogBO(catalogBO);
		Logger
				.logInfo("Sub-CatalogBusinessService - Returning execute(): Sub-Catalog Create");
		return subCatalogSaveResponse;
	}

	/**
	 * Execute method for SubCatalogDeleteRequest
	 * @param request
	 * @return
	 * @throws SevereException
	 */

	public WPDResponse execute(SubCatalogDeleteRequest request)
			throws ServiceException {
		Logger
				.logInfo("SubCatalogBusinessService - Entering execute(): Sub-Catalog Delete");
		SubCatalogDeleteResponse subCatalogDeleteResponse = null;
		List locateResultList = null;
		SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
		CatalogBO catalogBO = getSubCatalogObjectFromSubCatalogVO(request);
		subCatalogDeleteResponse = (SubCatalogDeleteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_SUB_CATALOG_RESPONSE);
		try {
			subCatalogDeleteResponse = (SubCatalogDeleteResponse) new ValidationServiceController()
					.execute(request);
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing Sub-CatalogDeleteRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		if (subCatalogDeleteResponse.isSuccess()) {
			try {
				subCatalogBusinessObjectBuilder.delete(catalogBO, request
						.getUser());

			} catch (SevereException e) {
				List logParameters = new ArrayList();
				logParameters.add(request);
				String logMessage = "Failed while processing Sub-CatalogDeleteRequest";
				throw new ServiceException(logMessage, logParameters, e);
			}
			subCatalogDeleteResponse.addMessage(new InformationalMessage(
					BusinessConstants.MSG_SUB_CATALOG_DELETE_SUCCESS));

		}
		List locateResults = null;
		SubCatalogVO subCatalogVO = request.getSubCatalogVO();
		//for getting the search results after deletion
		try {
			SubCatalogLocateCriteria subCatalogLocateCriteria = (SubCatalogLocateCriteria) getSubCatalogLocateCriteria(subCatalogVO);
			locateResults = subCatalogBusinessObjectBuilder
					.locate(subCatalogLocateCriteria);
			if (null != locateResults && !locateResults.isEmpty()) {
				subCatalogDeleteResponse.setSubCatalogList(locateResults);
			}

		} catch (SevereException e2) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing Sub-CatalogDeleteRequest";
			throw new ServiceException(logMessage, logParameters, e2);
		}

		Logger
				.logInfo("Sub-CatalogBusinessService - Returning execute(): Sub-Catalog Delete");
		return subCatalogDeleteResponse;
	}

	/**
	 * Method to search the sub catalogs based on the search criteria entered
	 * 
	 * @param request
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(SubCatalogSearchRequest request)
			throws ServiceException {

		// Create a list to store all the messages
		List messageList = new ArrayList();

		// Create the response object from the response factory
		SubCatalogSearchResponse subCatalogSearchResponse = (SubCatalogSearchResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_SUB_CATALOG_RESPONSE);

		SubCatalogVO subCatalogVO = request.getSubCatalogVO();
		// Set the values to the locate criteria
		SubCatalogLocateCriteria subCatalogLocateCriteria = getSubCatalogLocateCriteria(subCatalogVO);

		// Create an instance of the builder
		SubCatalogBusinessObjectBuilder builder = new SubCatalogBusinessObjectBuilder();

		// Call the builder locate() to get the result list
		List subCatalogList = null;
		try {
			subCatalogList = builder.locate(subCatalogLocateCriteria);
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing SearchSubCatalogRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		// Check whether the list is null or empty
		if (null != subCatalogList && !subCatalogList.isEmpty()) {

			// If no, set the list to the response
			subCatalogSearchResponse.setSubCatalogList(subCatalogList);
		} else {
			// If yes, set the message to the response
			messageList.add(new InformationalMessage(
					BusinessConstants.SEARCH_RESULT_NOT_FOUND));
			subCatalogSearchResponse.setMessages(messageList);
		}
		// Return the response
		return subCatalogSearchResponse;
	}

	/**
	 * Execute method for RetrieveSubCatalogRequest
	 * @param retrieveSubCatalogRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			RetrieveSubCatalogRequest retrieveSubCatalogRequest)
			throws ServiceException {
		List locateResultList;
		int locateResultCount = 0;
		List errorList = new ArrayList();
		SubCatalogLocateCriteria subCatalogLocateCriteria = null;

		// Create the response
		RetrieveSubCatalogResponse response = new RetrieveSubCatalogResponse();

		// Create an instance of the builder
		SubCatalogBusinessObjectBuilder builder = new SubCatalogBusinessObjectBuilder();

		try {
			if (retrieveSubCatalogRequest.getAction() == 1) {
				// Get the VO from the request
				CatalogVO catalogVO = retrieveSubCatalogRequest.getCatalogVO();

				// Set the values to the BO
				CatalogBO catalogBO = new CatalogBO();
				catalogBO.setCatalogId(catalogVO.getCatalogId());

				// Call the retrieve() of the builder to get the sub catalog details
				catalogBO = builder.retrieve(catalogBO);

				// Set the BO to the response
				response.setCatalogBO(catalogBO);

				// Call the domain detail retrieve()
				response.setDomainDetail(DomainUtil.retrieveDomainDetailInfo(
						"SubCatalog", catalogBO.getCatalogId()));
			} else if (retrieveSubCatalogRequest.getAction() == 2) {
				SubCatalogVO subCatalogVO = retrieveSubCatalogRequest
						.getSubCatalogVO();
				subCatalogLocateCriteria = getSubCatalogLocateCriteria(subCatalogVO);
				LocateResults locateResults = builder
						.locateItemAssociation(subCatalogLocateCriteria);

				if (null != locateResults) {
					locateResultList = locateResults.getLocateResults();
					locateResultCount = locateResultList.size();
					if (locateResultCount > 0) {
						response.setItemList(locateResults.getLocateResults());
					}
				}
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(retrieveSubCatalogRequest);
			String logMessage = "Failed while processing RetrieveSubCatalogRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}

		// Return the response
		return response;
	}

	/**
	 * Method for selecting the item not associated with the
	 * subcatalog
	 * @param itemLookUpRequest
	 * @return ItemLookUpResponse
	 * @throws ServiceException
	 */

	public WPDResponse execute(ItemLookUpRequest itemLookUpRequest)
			throws ServiceException {
		ItemLookUpResponse itemLookUpResponse = null;
		ItemLocateCriteriaVO itemLocateCriteriaVO = (ItemLocateCriteriaVO) itemLookUpRequest
				.getCriteriaVO();
		ItemLocateCriteria itemLocateCriteria = (ItemLocateCriteria) getItemLocateCriteria(itemLocateCriteriaVO);
		List locateResultList = null;
		int locateResultCount = 0;
		List errorList = new ArrayList();
		itemLookUpResponse = (ItemLookUpResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_ITEM_LOOKUP_RESPONSE);

		try {
			SubCatalogBusinessObjectBuilder builder = new SubCatalogBusinessObjectBuilder();
			LocateResults locateResults = builder.locate(itemLocateCriteria);
			if (null != locateResults) {
				locateResultList = locateResults.getLocateResults();
				locateResultCount = locateResultList.size();
				if (locateResultCount > 0) {
					itemLookUpResponse.setItemList(locateResults
							.getLocateResults());
				} else if (locateResultCount == 0) {
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_NOT_FOUND));
					itemLookUpResponse.setMessages(errorList);

				}
			}
		} catch (SevereException ex) {
			List logParameters = new ArrayList();
			logParameters.add(itemLookUpRequest);
			String logMessage = "Failed while processing ItemLookUpRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
				.logInfo("SubCatalogBusinessService - Returning execute(): Item Retrieve");
		return itemLookUpResponse;
	}

	/**
	 * Method to form item association with the subcatalog
	 * @param saveItemAssociationRequest
	 * @return SaveItemAssociationResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			SaveItemAssociationRequest saveItemAssociationRequest)
			throws ServiceException {
		SaveItemAssociationResponse saveItemAssociationResponse = null;
		SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
		List locateResultList;
		int locateResultCount = 0;
		List messageList = new ArrayList();
		SubCatalogBO subCatalogBO = getSubCatalogForItemAssociation(saveItemAssociationRequest);

		saveItemAssociationResponse = (SaveItemAssociationResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_ITEM_ASSOCIATION_RESPONSE);

		List item = saveItemAssociationRequest.getCatalogVO()
				.getAssociatedItems();
		SubCatalogLocateCriteria subCatalogLocateCriteria = new SubCatalogLocateCriteria();
		subCatalogLocateCriteria
				.setSubCatalogId(subCatalogBO.getSubCatalogId());

		try {
			if (null != item) {
				subCatalogBusinessObjectBuilder.persist(item, subCatalogBO,
						saveItemAssociationRequest.getUser());
				messageList.add(new InformationalMessage(
						"subCatalog.item.association.success"));
				saveItemAssociationResponse.setMessages(messageList);
			} else
				saveItemAssociationResponse.addMessage(new ErrorMessage(
						"subCatalog.item.association.failure"));

		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(saveItemAssociationRequest);
			String logMessage = "Failed while processing SaveItemAssociationRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}

		// Call the builder item retrieve method
		LocateResults locateResults = null;
		try {
			locateResults = subCatalogBusinessObjectBuilder
					.locateItemAssociation(subCatalogLocateCriteria);
		} catch (SevereException ex) {
			List logParameters = new ArrayList();
			logParameters.add(saveItemAssociationRequest);
			String logMessage = "Failed while processing SaveItemAssociationRequest Retrieve after Saving";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		if (null != locateResults) {
			locateResultList = locateResults.getLocateResults();
			locateResultCount = locateResultList.size();
		}
		if (locateResultCount > 0)
			saveItemAssociationResponse.setItemList(locateResults
					.getLocateResults());
		return saveItemAssociationResponse;
	}

	/**
	 * Execute method for ReferenceDataLookupRequest
	 * @param referenceDataLookupRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			ReferenceDataLookupRequest referenceDataLookupRequest)
			throws ServiceException {
		ReferenceDataLookupResponse referenceDataLookupResponse = null;
		SubCatalogBusinessObjectBuilder subCatalogBusinessObjectBuilder = new SubCatalogBusinessObjectBuilder();
		referenceDataLookupResponse = (ReferenceDataLookupResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.REFERENCE_DATA_LOOKUP_RESPONSE);
		List locateResults = null;
		int action = referenceDataLookupRequest.getSelectaction();
		List messageList = null;

		try {
			ReferenceDataLocateCriteria referenceDataLocateCriteria = (ReferenceDataLocateCriteria) getReferenceDataLocateCriteria(referenceDataLookupRequest);
			String sortOrder = getSortOrderForReference(referenceDataLocateCriteria);
			referenceDataLocateCriteria.setSortOrder(sortOrder);

			locateResults = subCatalogBusinessObjectBuilder
					.locate(referenceDataLocateCriteria);
			if (null != locateResults && !locateResults.isEmpty()) {
				messageList = new ArrayList(2);
				// DROP_DOWN_ACTION is added for WTT.It is required to show all the values in dropdown.
				if (locateResults.size() > BusinessConstants.MAX_SEARCH_RESULT_SIZE
						&& referenceDataLookupRequest.getAction() != BusinessConstants.DROP_DOWN_ACTION
						&& referenceDataLookupRequest.getAction() != 27	&& referenceDataLookupRequest.getAction() != 31 && referenceDataLookupRequest.getAction() != 8 ) {
					// referenceDataLookupRequest.getAction()!= 27  --> Show all the values for the blue exchange .Dont limit to 50 nos.		
					// referenceDataLookupRequest.getAction()!= 31  --> Show all the values for the blue exchange Service type Mapping .Dont limit to 50 nos.
					referenceDataLookupResponse.setRecordsGrtThanMaxSize(true);//for search result exceed msg in question notes popup
					referenceDataLookupResponse.setSubCatalogList(locateResults
							.subList(0,
									BusinessConstants.MAX_SEARCH_RESULT_SIZE));
					messageList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));
				} else {
					referenceDataLookupResponse.setRecordsGrtThanMaxSize(false);
					referenceDataLookupResponse
							.setSubCatalogList(locateResults);
				}
			} else {
				if (referenceDataLookupRequest.getAction() != 5
						&& referenceDataLookupRequest.getAction() != 4) {
					messageList = new ArrayList(2);
					messageList.add(new InformationalMessage(
							BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
				}
			}
			referenceDataLookupResponse.setSortOrder(sortOrder);
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(referenceDataLookupRequest);
			String logMessage = "Failed while processing ReferenceDataLookupRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		addMessagesToResponse(referenceDataLookupResponse, messageList);
		return referenceDataLookupResponse;
	}

	/**
	 * Execute method for itemAssociationDeleteRequest
	 * @param itemAssociationDeleteRequest
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 * @throws SevereException
	 */
	public WPDResponse execute(
			ItemAssociationDeleteRequest itemAssociationDeleteRequest)
			throws ServiceException {
		ItemAssociationDeleteResponse response = null;
		List locateResultList;
		int locateResultCount = 0;
		List messageList = new ArrayList();
		SubCatalogBusinessObjectBuilder builder = new SubCatalogBusinessObjectBuilder();
		SubCatalogBO subCatalogBO = getDeleteItemObject(itemAssociationDeleteRequest);

		response = (ItemAssociationDeleteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_ITEM_ASSOCIATION_RESPONSE);
		try {
			builder.deleteItemAssociation(subCatalogBO,
					itemAssociationDeleteRequest.getUser());
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(itemAssociationDeleteRequest);
			String logMessage = "Failed while processing SaveItemAssociationRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		messageList.add(new InformationalMessage(
				"subCatalog.item.deletion.success"));
		response.setMessages(messageList);
		//Calling buider for retrieve
		SubCatalogLocateCriteria subCatalogLocateCriteria = new SubCatalogLocateCriteria();
		subCatalogLocateCriteria
				.setSubCatalogId(subCatalogBO.getSubCatalogId());
		LocateResults locateResults = null;
		try {
			locateResults = builder
					.locateItemAssociation(subCatalogLocateCriteria);
		} catch (SevereException ex) {
			List logParameters = new ArrayList();
			logParameters.add(itemAssociationDeleteRequest);
			String logMessage = "Failed while processing SaveItemAssociationRequest Retrieve after Saving";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		if (null != locateResults) {
			locateResultList = locateResults.getLocateResults();
			locateResultCount = locateResultList.size();
		}
		if (locateResultCount > 0)
			response.setItemList(locateResults.getLocateResults());

		return response;
	}

	/**
	 * Method for getting the referencedata locate criteria
	 * @param request
	 * @return ReferenceDataLocateCriteria
	 */
	private ReferenceDataLocateCriteria getReferenceDataLocateCriteria(
			ReferenceDataLookupRequest request) {
		SubCatalogVO subCatalogVO = request.getSubCatalogVO();

		// Create an instance of the locate criteria
		ReferenceDataLocateCriteria locateCriteria = new ReferenceDataLocateCriteria();
		locateCriteria.setAction(request.getAction());
		if (null != request.getSearchString()) {
			locateCriteria.setSearchString(request.getSearchString());
		}

		// Set the values from the VO to the locateCriteria
		if (null != subCatalogVO.getParentCatalog()) {
			locateCriteria.setParentCatalog(subCatalogVO.getParentCatalog());
		}
		if (request.getAction() == 2) {
			locateCriteria.setLobList(subCatalogVO.getLobList());
			locateCriteria.setBeList(subCatalogVO.getBeList());
			locateCriteria.setBgList(subCatalogVO.getBgList());
		} else if (request.getAction() == 3) {
			locateCriteria.setEntityId(subCatalogVO.getEntityId());
			locateCriteria.setEntityType(subCatalogVO.getEntityType());
		} else if (request.getAction() == 4) {
			String searchValue = subCatalogVO.getSearchText();
			locateCriteria.setEntityId(subCatalogVO.getEntityId());
			locateCriteria.setEntityType(subCatalogVO.getEntityType());

			if (searchValue.indexOf("%") >= 0) {
				if (!subCatalogVO.isSearchCriteriaEntered()) {
					searchValue = searchValue.replaceAll("%", "`%");
				}
			} else if (searchValue.indexOf("_") >= 0) {
				searchValue = searchValue.replaceAll("_", "`_");
			}
			locateCriteria.setSearchValueForPopUp(searchValue);

		} else if (request.getAction() == 5) {
			locateCriteria.setEntityId(subCatalogVO.getEntityId());
			locateCriteria.setEntityType(subCatalogVO.getEntityType());
			locateCriteria.setTermValue(subCatalogVO.getBenefitLevelTerm());
			locateCriteria.setPvaValue(subCatalogVO.getBenefitLevelPVA());
		} else if (request.getAction() == 6) {
			String searchValue = subCatalogVO.getSearchText();
			if (searchValue.indexOf("%") >= 0) {
				if (!subCatalogVO.isSearchCriteriaEntered()) {
					searchValue = searchValue.replaceAll("%", "`%");
				}
			} else if (searchValue.indexOf("_") >= 0) {
				searchValue = searchValue.replaceAll("_", "`_");
			}
			locateCriteria.setSearchValueForPopUp(searchValue);
		} else if (request.getAction() == 10) {
			String headerRuleId = request.getHeaderRuleId();
			locateCriteria.setHeaderRuleId(headerRuleId);
		} else if (request.getAction() == 11) {
			String searchValue = subCatalogVO.getSearchText();
			/*locateCriteria.setEntityId(subCatalogVO.getEntityId());
			 locateCriteria.setEntityType(subCatalogVO.getEntityType()); */

			if (searchValue.indexOf("%") >= 0) {
				if (!subCatalogVO.isSearchCriteriaEntered()) {
					searchValue = searchValue.replaceAll("%", "`%");
				}
			} else if (searchValue.indexOf("_") >= 0) {
				searchValue = searchValue.replaceAll("_", "`_");
			}
			locateCriteria.setSearchValueForPopUp(searchValue);

		} else if (request.getAction() == 12) {

			String searchValue = subCatalogVO.getSearchText();
			if (searchValue.indexOf("%") >= 0) {
				if (!subCatalogVO.isSearchCriteriaEntered()) {
					searchValue = searchValue.replaceAll("%", "`%");
				}
			} else if (searchValue.indexOf("_") >= 0) {
				searchValue = searchValue.replaceAll("_", "`_");
			}
			locateCriteria.setLobList(subCatalogVO.getLobList());
			locateCriteria.setBeList(subCatalogVO.getBeList());
			locateCriteria.setBgList(subCatalogVO.getBgList());
			locateCriteria.setSearchValueForPopUp(searchValue);

		} else if (request.getAction() == 13) {

			String searchValue = subCatalogVO.getSearchText();
			if (searchValue.indexOf("%") >= 0) {
				if (!subCatalogVO.isSearchCriteriaEntered()) {
					searchValue = searchValue.replaceAll("%", "`%");
				}
			} else if (searchValue.indexOf("_") >= 0) {
				searchValue = searchValue.replaceAll("_", "`_");
			}
			locateCriteria.setLobList(subCatalogVO.getLobList());
			locateCriteria.setBeList(subCatalogVO.getBeList());
			locateCriteria.setBgList(subCatalogVO.getBgList());
			locateCriteria.setEntityId(subCatalogVO.getEntityId());
			locateCriteria.setEntityType(subCatalogVO.getEntityType());
			locateCriteria.setSearchValueForPopUp(searchValue);
		}

		if (request.getAction() == 17) {
			locateCriteria.setSearchString(request.getSubCatalogVO()
					.getSearchText());
		}
		return locateCriteria;

	}

	/**
	 * For getting the sort order of reference data
	 * @param referenceDataLocateCriteria
	 * @return
	 */
	private String getSortOrderForReference(
			ReferenceDataLocateCriteria referenceDataLocateCriteria) {
		//For setting the list of catalog names to be sorted in the
		//order of primary code from the regular sorting order of code description

		List catalogNameList = new ArrayList();
		//codesortedreference.properties contains the list of parent catalog names to be sorted in the
		//order of primary code
		ResourceBundle catNames = ResourceBundle.getBundle(
				"codesortedreference", Locale.getDefault());
		Enumeration keys = catNames.getKeys();

		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = catNames.getString(key).toUpperCase();
			catalogNameList.add(value.toUpperCase().trim());
		}
		if (catalogNameList != null
				&& !catalogNameList.isEmpty()
				&& referenceDataLocateCriteria.getParentCatalog() != null
				&& !"".equals(referenceDataLocateCriteria.getParentCatalog()
						.trim())) {
			if (catalogNameList.contains(referenceDataLocateCriteria
					.getParentCatalog().toUpperCase().trim()))
				return BusinessConstants.PRIMARY_CODE;
		}

		return BusinessConstants.DESCRIPTION;
	}

	/**
	 * Method for copying value object to business object
	 * @param request
	 * @return CatalogBO
	 */

	private CatalogBO getSubCatalogObjectFromCatalogVO(
			SubCatalogSaveRequest request) {
		CatalogBO catalogBO = new CatalogBO();
		CatalogVO catalogVO = (CatalogVO) request.getCatalogVO();
		//for setting parentid as null
		catalogBO
				.setCatalogParentID(new Integer(catalogVO.getCatalogParentid())
						.toString());
		catalogBO.setCatalogName(catalogVO.getCatalogName());
		catalogBO.setDescription(catalogVO.getDescription());
		if (request.getAction() == 2) {
			catalogBO.setCatalogId(request.getCatalogVO().getCatalogId());
		}
		catalogBO.setCatalogDatatype("String");
		catalogBO.setCatalogSize(5);
		List lineOfBusiness = catalogVO.getLobList();
		List businessEntity = catalogVO.getBusinessEntityList();
		List businessGroup = catalogVO.getBusinessGroupList();
        List marketBusinessUnit =  catalogVO.getMarketBusinessUnitList();
        catalogBO.setBusinessDomains(BusinessUtil.convertToDomains(
                lineOfBusiness, businessEntity, businessGroup, marketBusinessUnit));
        return catalogBO;
	}

	/**
	 * Method to get ItemLocateCriteria
	 * @param criteriaVO
	 * @return
	 */
	private ItemLocateCriteria getItemLocateCriteria(
			ItemLocateCriteriaVO criteriaVO) {
		ItemLocateCriteria criteria = new ItemLocateCriteria();

		criteria.setParentCatalogId(criteriaVO.getParentCatalogId());
		criteria.setSubCatalogId(criteriaVO.getSubCatalogId());

		return criteria;
	}

	/**
	 * Method to get SubCatalogBO for SaveItemAssociationRequest
	 * @param saveItemAssociationRequest
	 * @return
	 */
	private SubCatalogBO getSubCatalogForItemAssociation(
			SaveItemAssociationRequest saveItemAssociationRequest) {
		SubCatalogBO subCatalogBO = new SubCatalogBO();
		CatalogVO catalogVO = (CatalogVO) saveItemAssociationRequest
				.getCatalogVO();

		subCatalogBO.setParentCatalogId(catalogVO.getCatalogParentid());
		subCatalogBO.setSubCatalogId(catalogVO.getCatalogId());

		return subCatalogBO;
	}

	/**
	 * Method to get CatalogBO for SubCatalogDeleteRequest
	 * @param request
	 * @return
	 */
	private CatalogBO getSubCatalogObjectFromSubCatalogVO(
			SubCatalogDeleteRequest request) {
		CatalogBO catalogBO = new CatalogBO();
		SubCatalogVO subCatalogVO = (SubCatalogVO) request.getSubCatalogVO();
		catalogBO.setCatalogId(subCatalogVO.getSelectedSubCatalogId());
		return catalogBO;
	}

	/**
	 * Method to get SubCatalogBO for ItemAssociationDeleteRequest
	 * @param request
	 * @return
	 */
	private SubCatalogBO getDeleteItemObject(
			ItemAssociationDeleteRequest request) {
		SubCatalogVO subCatalogVO = (SubCatalogVO) request.getSubCatalogVO();
		SubCatalogBO subCatalogBO = new SubCatalogBO();

		//Setting list of parent catalog ids to CatalogBO
		subCatalogBO.setParentCatalogList(subCatalogVO.getParentCatalogList());
		//subCatalogBO.setSubCatalogSysId(subCatalogVO.getParentCatalogId());
		subCatalogBO.setPrimaryCode(subCatalogVO.getPrimaryCode());
		try {
			subCatalogBO.setSubCatalogId(new Integer(subCatalogVO
					.getSubCatalogId()).intValue());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return subCatalogBO;
	}

	/**
	 * Method to return the SubCatalogLocateCriteria
	 */
	private SubCatalogLocateCriteria getSubCatalogLocateCriteria(
			SubCatalogVO subCatalogVO) {
		//Create an instance of the locate criteria
		SubCatalogLocateCriteria locateCriteria = new SubCatalogLocateCriteria();
		if (null != subCatalogVO.getSubCatalogId()) {
			locateCriteria.setSubCatalogId(new Integer(subCatalogVO
					.getSubCatalogId()).intValue());
		}
		// Set the values from the VO to the locateCriteria
		if (null != subCatalogVO.getSubCatalogName()) {
			locateCriteria.setSubCatalogName(subCatalogVO.getSubCatalogName());
		}
		if (null != subCatalogVO.getCatalogIdList()) {
			locateCriteria.setCatalogList(subCatalogVO.getCatalogIdList());
		}
		return locateCriteria;
	}

	/**
	 * Method to set the list of messages to response.
	 * 
	 * @param response
	 *            WPDResponse
	 * @param messages
	 *            List.
	 */
	private void addMessagesToResponse(WPDResponse response, List messages) {
		if (null == messages || messages.size() == 0)
			return;
		if (null == response.getMessages())
			response.setMessages(messages);
		else
			response.getMessages().addAll(messages);
	}

}