/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethodmapping.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.ets.ewpd.am.cache.AdminMethodCache;
import com.wellpoint.ets.ewpd.am.context.ApplicationContext;
import com.wellpoint.ets.ewpd.am.domain.validation.AdminMethodValidationManager;
import com.wellpoint.wpd.business.adminmethodmapping.builder.AdminMethodMappingBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingCreateResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingDeleteResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingEditResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingSearchResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.AdminMethodMappingViewResponse;
import com.wellpoint.wpd.common.adminmethodmapping.Response.QuestionAnswerLookupResponse;
import com.wellpoint.wpd.common.adminmethodmapping.bo.AdminMethodMappingBO;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingCreateRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingDeleteRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingEditRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingSearchRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.AdminMethodMappingViewRequest;
import com.wellpoint.wpd.common.adminmethodmapping.request.QuestionAnswerLookupRequest;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.refdata.response.ReferenceDataLookupResponse;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingBusinessService extends WPDBusinessService {
	AdminMethodMappingBusinessObjectBuilder adminMethodMappingBusinessObjectBuilder;

	/**
	 * Method to create Admin Method
	 * 
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodMappingCreateRequest request)
			throws ServiceException {
		Logger
				.logInfo("AdminMethodMappingBusinessService - Create Admin Method");

		adminMethodMappingBusinessObjectBuilder = new AdminMethodMappingBusinessObjectBuilder();
		AdminMethodMappingCreateResponse adminMethodMappingCreateResponse = (AdminMethodMappingCreateResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CREATE_ADMINMETHODMAPPING_RESPONSE);

		AdminMethodMappingBO adminMethodMappingBO = new AdminMethodMappingBO();

		populateBO(request, adminMethodMappingBO);
		List adminMethodSysIdList = new ArrayList();
		List processMethodList = new ArrayList();

		boolean success;
		try {

			success = adminMethodMappingBusinessObjectBuilder
					.createAdminMethodMapping(adminMethodMappingBO);

			adminMethodMappingCreateResponse.setSuccess(true);
			Logger
					.logInfo("AdminMethodMappingBusinessService - Return from Create Admin Method");
			if (success)
				adminMethodMappingCreateResponse
						.addMessage(new InformationalMessage(
								WebConstants.ADMIN_MAPPING_CREATE_SUCCESS));
			
			reloadAdminMethodCache();			

		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingCreateRequest request) method in AdminMethodMappingBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingCreateRequest request) method in AdminMethodMappingBusinessService",
					null, ex);

		}

		return adminMethodMappingCreateResponse;
	}

	public WPDResponse execute(AdminMethodMappingEditRequest request)
			throws ServiceException {

		Logger
				.logInfo("AdminMethodMappingBusinessService - Edit Admin Method Mapping");

		adminMethodMappingBusinessObjectBuilder = new AdminMethodMappingBusinessObjectBuilder();
		AdminMethodMappingEditResponse adminMethodMappingEditResponse = (AdminMethodMappingEditResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.EDIT_ADMINMETHODMAPPING_RESPONSE);

		AdminMethodMappingBO adminMethodMappingBO = new AdminMethodMappingBO();

		populateBO(request, adminMethodMappingBO);
		List adminMethodSysIdList = new ArrayList();
		List processMethodList = new ArrayList();

		boolean deleteFlag = false;
		boolean updateFlag;

		try {
			//Method to Delete Admin Method Mapping
			adminMethodMappingBusinessObjectBuilder.deleteAdminMethodMapping(
					adminMethodMappingBO, request.getUser());
			updateFlag = adminMethodMappingBusinessObjectBuilder
					.createAdminMethodMapping(adminMethodMappingBO);
			Logger
					.logInfo("AdminMethodMappingBusinessService - Return From Edit Admin Method Mapping");
			if (updateFlag)
				adminMethodMappingEditResponse
						.addMessage(new InformationalMessage(
								WebConstants.ADMIN_METHOD_EDIT_SUCCESS));
			reloadAdminMethodCache();

		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingEditRequest request) method in AdminMethodMappingBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingEditRequest request) method in AdminMethodMappingBusinessService",
					null, ex);

		}

		return adminMethodMappingEditResponse;
	}

	/**
	 * @param Method
	 *            to populate BO for AdminMethodMappingCreateRequest
	 */
	private void populateBO(AdminMethodMappingCreateRequest request,
			AdminMethodMappingBO adminMethodMappingBO) {
		Logger.logDebug("-----" + request.getAdminMethodsysID());
		adminMethodMappingBO.setAdminMethodNo(request.getAdminMethodNo());

		adminMethodMappingBO.setAdminMethodSysId(request.getAdminMethodsysID());
		adminMethodMappingBO.setComments(request.getComments());
		adminMethodMappingBO.setTerm(request.getTerm());
		adminMethodMappingBO.setQualifierList(request.getQualifierList());
		adminMethodMappingBO.setDatatypeList(request.getDatatypeList());
		adminMethodMappingBO.setPvaList(request.getPvaList());
		adminMethodMappingBO.setCreatedUser(request.getUser().getUserId());
		adminMethodMappingBO.setLastUpdatedUser(request.getUser().getUserId());
		adminMethodMappingBO.setProcessMethodList(request
				.getProcessMethodList());
		adminMethodMappingBO
				.setQuestionAnswerList(request.getQuesntionIdList());
		adminMethodMappingBO.setAnswers(request.getPossibleAnswer());
		adminMethodMappingBO.setQuestionNbrList(request.getQuestionNbrList());
		adminMethodMappingBO.setCreatedTimestamp(request.getCreatedDate());
		adminMethodMappingBO.setLastUpdatedTimestamp(request
				.getLastUpdatedDate());
	}

	/**
	 * @param Method
	 *            to populate BO for AdminMethodMappingEditRequest
	 * @param adminMethodMappingBO
	 */
	private void populateBO(AdminMethodMappingEditRequest request,
			AdminMethodMappingBO adminMethodMappingBO) {
		Logger.logDebug("-----" + request.getAdminMethodsysID());
		adminMethodMappingBO.setAdminMethodNo(request.getAdminMethodNo());
		adminMethodMappingBO.setAdminMethodSysId(request.getAdminMethodsysID());
		adminMethodMappingBO.setComments(request.getComments());
		adminMethodMappingBO.setTerm(request.getTerm());
		adminMethodMappingBO.setQualifierList(request.getQualifierList());
		adminMethodMappingBO.setDatatypeList(request.getDatatypeList());
		adminMethodMappingBO.setPvaList(request.getPvaList());
		adminMethodMappingBO.setCreatedUser(request.getUser().getUserId());
		adminMethodMappingBO.setLastUpdatedUser(request.getUser().getUserId());
		adminMethodMappingBO.setProcessMethodDesc(request
				.getProcessMethodDesc());
		adminMethodMappingBO
				.setQuestionAnswerList(request.getQuesntionIdList());
		adminMethodMappingBO.setAnswers(request.getPossibleAnswer());
		adminMethodMappingBO.setQuestionNbrList(request.getQuestionNbrList());
		adminMethodMappingBO.setCreatedTimestamp(request.getCreatedDate());
		adminMethodMappingBO.setLastUpdatedTimestamp(request
				.getLastUpdatedDate());
	}

	/**
	 * @param Method to view Admin Method Mapping
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodMappingViewRequest request)
			throws ServiceException {

		Logger
				.logInfo("AdminMethodMappingBusinessService - View Admin Method Mapping");
		AdminMethodMappingBusinessObjectBuilder adminMethodMappingBusinessObjectBuilder = new AdminMethodMappingBusinessObjectBuilder();
		AdminMethodMappingViewResponse adminMethodMappingViewResponse = null;
		AdminMethodMappingBO adminMethodMappingBO = new AdminMethodMappingBO();
		List searchResultsList = new ArrayList();
		adminMethodMappingBO.setAdminMethodSysId(request.getAdminMethodSysId());
		adminMethodMappingBO.setFilterCriteriaSysId(0);
		adminMethodMappingViewResponse = (AdminMethodMappingViewResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.VIEW_ADMIN_METHOD_MAPPING_RESPONSE);

		try {

			searchResultsList = adminMethodMappingBusinessObjectBuilder
					.viewAdminMethodMapping(adminMethodMappingBO);

		}

		catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingViewRequest request) method in AdminMethodMappingBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingViewRequest request) method in AdminMethodMappingBusinessService",
					null, ex);

		}
		Logger
				.logInfo("AdminMethodMappingBusinessService - Returning execute(): View Admin Method Mapping");
		adminMethodMappingViewResponse.setSearchResultList(searchResultsList);
		return adminMethodMappingViewResponse;
	}

	/**
	 * Method to Delete Admin Method Mapping
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(AdminMethodMappingDeleteRequest request)
			throws ServiceException {
		Logger
				.logInfo("AdminMethodMappingBusinessService - Delete Admin Method");
		AdminMethodMappingDeleteResponse adminMethodMappingDeleteResponse = (AdminMethodMappingDeleteResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_ADMINMETHODMAPPING_RESPONSE);

		AdminMethodMappingBO adminMethodMappingBO = new AdminMethodMappingBO();

		adminMethodMappingBO.setAdminMethodSysId(request.getAdminMethodsysID());
		AdminMethodMappingBusinessObjectBuilder adminMethodMappingBusinessObjectBuilder = new AdminMethodMappingBusinessObjectBuilder();

		try {

			adminMethodMappingBusinessObjectBuilder.deleteAdminMethodMapping(
					adminMethodMappingBO, request.getUser());

			List messages = new ArrayList();
			adminMethodMappingDeleteResponse
					.addMessage(new InformationalMessage(
							WebConstants.ADMIN_METHOD_MAP_DELETE_SUCCESS));
			reloadAdminMethodCache();

		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingDeleteRequest request) method in AdminMethodMappingBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingDeleteRequest request) method in AdminMethodMappingBusinessService",
					null, ex);

		}
		Logger
				.logInfo("AdminMethodMappingBusinessService - Returning execute(): Delete Admin Method Mapping");
		return adminMethodMappingDeleteResponse;
	}

	/**
	 * @param Method  to search Admin Method Mapping
	 * @return
	 * @throws ServiceException
	 * @throws AdapterException
	 * @throws
	 */
	public WPDResponse execute(
			AdminMethodMappingSearchRequest adminMethodMappingSearchRequest)
			throws ServiceException {
		Logger
				.logInfo("Entering execute method of AdminMethodMappingBusinessService class, request = "
						+ adminMethodMappingSearchRequest.getClass().getName());
		List messageList = null;
		adminMethodMappingBusinessObjectBuilder = new AdminMethodMappingBusinessObjectBuilder();
		//Getting the response from the response factory
		AdminMethodMappingSearchResponse adminMethodMappingSearchResponse = (AdminMethodMappingSearchResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_ADMIN_METHOD_MAPPING_RESPONSE);

		List searchList = new ArrayList();
		List searchListFinal = new ArrayList();

		AdminMethodMappingBO adminMethodMappingBO = populateBOForSearch(adminMethodMappingSearchRequest);
		try {
			searchList = adminMethodMappingBusinessObjectBuilder
					.adminMethodMappingSearchList(adminMethodMappingBO);
			
			
			if(adminMethodMappingSearchRequest.isEditRetireve()){
				Map selectedQuestionAnswerMap=adminMethodMappingBusinessObjectBuilder
				.getQuestionList(adminMethodMappingBO.getAdminMethodSysId());
				
				adminMethodMappingSearchResponse.setSelectedQuestionAnswerMap(selectedQuestionAnswerMap);
			}
			if ((null == searchList) || searchList.size() <= 0
					&& adminMethodMappingSearchRequest.isDeleteFlag() == false) {
				adminMethodMappingSearchResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.SEARCH_RESULT_NOT_FOUND));
			} else if (searchList.size() > 50) {
				adminMethodMappingSearchResponse
						.addMessage(new InformationalMessage(
								BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));

				for (int i = 0; i < 50; i++) {
					searchListFinal.add(searchList.get(i));
				}
				adminMethodMappingSearchResponse
						.setSearchResultList(searchListFinal);
				return adminMethodMappingSearchResponse;
			}

		} catch (SevereException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingSearchRequest request) method in AdminMethodMappingBusinessService",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new ServiceException(
					"Exception occured in execute(AdminMethodMappingSearchRequest request) method in AdminMethodMappingBusinessService",
					null, ex);

		}

		Logger
				.logInfo("Returning  from execute method of AdminMethodMappingBusinessService class for request="
						+ adminMethodMappingSearchRequest.getClass().getName());
		adminMethodMappingSearchResponse.setSearchResultList(searchList);

		return adminMethodMappingSearchResponse;

	}

	/**
	 * @param Method to populate Admin Method Mapping BO for search
	 * @param adminMethodMappingBO
	 */
	private AdminMethodMappingBO populateBOForSearch(
			AdminMethodMappingSearchRequest adminMethodMappingSearchRequest) {
		AdminMethodMappingBO adminMethodMappingBO = new AdminMethodMappingBO();
	 adminMethodMappingBO
				.setAdminMethodSysId(adminMethodMappingSearchRequest
						.getAdminMethodSysId());
		adminMethodMappingBO.setAdminMethodNo(adminMethodMappingSearchRequest.getAdminMethodNo());
		adminMethodMappingBO.setDescription(adminMethodMappingSearchRequest.getDescriptionCriteria());
		adminMethodMappingBO
				.setProcessMethodList(adminMethodMappingSearchRequest
						.getProcessMethodList());
		adminMethodMappingBO.setQualifierList(adminMethodMappingSearchRequest
				.getQualifierList());
		adminMethodMappingBO.setTermList(adminMethodMappingSearchRequest
				.getTermList());
		adminMethodMappingBO.setPvaList(adminMethodMappingSearchRequest
				.getPvaList());

		adminMethodMappingBO.setEditFlag(adminMethodMappingSearchRequest
				.isEditFlag());
		adminMethodMappingBO
				.setSearchDuplicateFlag(adminMethodMappingSearchRequest
						.isSearchFlag());
		return adminMethodMappingBO;

	}

	/**
	 * Execute method for ReferenceDataLookupRequest
	 * 
	 * @param referenceDataLookupRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			QuestionAnswerLookupRequest questionAnswerLookupRequest)
			throws ServiceException {
		ReferenceDataLookupResponse referenceDataLookupResponse = null;
		QuestionAnswerLookupResponse questionAnswerLookupResponse = (QuestionAnswerLookupResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.QUES_ANSWER_LOOKUP_RESPONSE);

		List locateResults = new ArrayList();
		String searchCriteria = getLocateCriteria(questionAnswerLookupRequest);
		AdminMethodMappingBusinessObjectBuilder adminMethodMappingBusinessObjectBuilder = new AdminMethodMappingBusinessObjectBuilder();
		List messageList = null;

		try {
			locateResults = adminMethodMappingBusinessObjectBuilder
					.searchQuesAnswer(searchCriteria);

			if (null != locateResults && !locateResults.isEmpty()) {
				messageList = new ArrayList(2);

				if (locateResults.size() > BusinessConstants.MAX_SEARCH_RESULT_SIZE) {

					questionAnswerLookupResponse.setRecordsGrtThanMaxSize(true);
					questionAnswerLookupResponse
							.setQuesAnswerList(locateResults.subList(0,
									locateResults.size()));
					messageList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));
				} else {
					questionAnswerLookupResponse
							.setRecordsGrtThanMaxSize(false);
					questionAnswerLookupResponse
							.setQuesAnswerList(locateResults);
				}

			} else {

				messageList = new ArrayList(2);
				messageList.add(new InformationalMessage(
						BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
			}

		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(questionAnswerLookupRequest);
			String logMessage = "Failed while processing ReferenceDataLookupRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		 addMessagesToResponse(questionAnswerLookupResponse, messageList);
		return questionAnswerLookupResponse;
	}

	/**
	   * Method to set the list of messages to response.
	   * 
	   * @param response
	   *            WPDResponse
	   * @param messages
	   *            List.
	   */
	  private void addMessagesToResponse(WPDResponse response, List messages)
	  {
	    if (null == messages || messages.size() == 0)
	      return;
	    if (null == response.getMessages())
	      response.setMessages(messages);
	    else
	      response.getMessages().addAll(messages);
	  }
	
	  
	private String getLocateCriteria(QuestionAnswerLookupRequest request) {
		String searchValue = request.getSearchString();

		// Set the values from the VO to the locateCriteria
		if (searchValue.indexOf("%") >= 0) {	

			if (!request.isSearchCriteriaEntered()) {
				searchValue = searchValue.replaceAll("%", "`%");
			}									
			else if (searchValue.indexOf("_") >= 0) {
				searchValue = searchValue.replaceAll("_", "_");		
			}	
			else if (searchValue.indexOf("\\") >= 0) {
				searchValue = searchValue.replaceAll("\\\\", "\\\\\\\\");	
			}
		}
		return searchValue;
	}
	private void reloadAdminMethodCache(){
		
		ApplicationContext context = ApplicationContext.createApplicationContext();
		
		AdminMethodCache adminMethodCache   = (AdminMethodCache)context.getContext().getBean("adminMethodCache");
		
		adminMethodCache.markCacheAsDirty();
		
	}
	
}