/*
 * ServiceTypeMappingBusinessService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.blueexchange.builder.ServiceTypeMappingBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;

import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.RuleServiceTypeAssociation;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeLocateCriteriaBO;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.blueexchange.request.DeleteServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.RetrieveServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.SaveServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.ServiceTypeMappingDeleteRequest;
import com.wellpoint.wpd.common.blueexchange.request.ServiceTypeMappingSearchRequest;
import com.wellpoint.wpd.common.blueexchange.request.ViewServiceTypeCodeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.RetrieveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.SaveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.ServiceTypeMappingDeleteResponse;
import com.wellpoint.wpd.common.blueexchange.response.ServiceTypeMappingSearchResponse;
import com.wellpoint.wpd.common.blueexchange.response.ViewServiceTypeCodeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.vo.ServiceTypeLocateCriteriaVO;
import com.wellpoint.wpd.common.blueexchange.vo.ServiceTypeMappingVO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.SecurityException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingBusinessService extends WPDBusinessService {

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ServiceTypeMappingDeleteRequest request)
			throws ServiceException {
		Logger
				.logInfo("ServiceTypeMappingBusinessService - Entering execute(): ServiceTypeMapping Delete");
		ServiceTypeMappingDeleteResponse response = null;
		try {

			List messageList = new ArrayList();
			response = (ServiceTypeMappingDeleteResponse) WPDResponseFactory
					.getResponse(WPDResponseFactory.DELETE_SERVICETYPE_RESPONSE);

			ServiceTypeMapping serviceTypeMapping = new ServiceTypeMapping();
			serviceTypeMapping.setMappingSysId(request.getMappingSysId());
			ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
			Audit audit = getAudit(request.getUser());
			serviceTypeMapping = builder.retrieve(serviceTypeMapping);
			String serviceTypeCode = serviceTypeMapping.getEb03Identifier() + " - " + serviceTypeMapping.getEb03IdentifierDes();
			builder.delete(serviceTypeMapping, audit);
			InformationalMessage informationalMessage = new InformationalMessage(
					BusinessConstants.MSG_SERVICETYPE_DELETE_SUCCESS);
			informationalMessage.setParameters(new String[]{serviceTypeCode});
			messageList.add(informationalMessage);

			addMessagesToResponse(response, messageList);
			response.setSuccess(true);
			response.setMessages(messageList);

			Logger
					.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Delete");

		} catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			response.addMessage(em);
			List logParameters = new ArrayList();
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (SevereException ex) {
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing ServiceTypeMappingDeleteRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;
	}

	/**
	 * Method to set the list of messages to response.
	 * 
	 * @param response WPDResponse
	 * @param messages List.
	 */
	private void addMessagesToResponse(WPDResponse response, List messages) {
		if (null == messages || messages.size() == 0)
			return;
		if (null == response.getMessages())
			response.setMessages(messages);
		else
			response.getMessages().addAll(messages);
	}

	/**
	 * Service method for Create/Update Service type mapping.
	 * @param request
	 * @return
	 * @throws SevereException
	 */

	public WPDResponse execute(RetrieveServiceTypeMappingRequest request)
			throws ServiceException {
		RetrieveServiceTypeMappingResponse response = (RetrieveServiceTypeMappingResponse) WPDResponseFactory
								.getResponse(WPDResponseFactory.RETRIEVE_SERVICE_MAPPING_RESPONSE);
		Logger.logInfo("ServiceTypeMappingBusinessService - Entering execute(): ServiceTypeMapping Retrieve");
		try{		
		ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
		RuleMapping ruleMappingBO = new RuleMapping();
		ruleMappingBO = builder.retrieveRuleMapping(request.getRuleId());
		response.setRuleMappingBO(ruleMappingBO);
		Logger.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Retrieve");
		}catch(SevereException ex){
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing RetrieveServiceTypeMappingRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;

	}
	/*
	 * deleting sevice type mapping 
	 * @ param DeleteServiceTypeMappingRequest
	 */
	
	public WPDResponse execute(DeleteServiceTypeMappingRequest request)
								throws ServiceException {
		
	RetrieveServiceTypeMappingResponse response = (RetrieveServiceTypeMappingResponse) WPDResponseFactory
			.getResponse(WPDResponseFactory.RETRIEVE_SERVICE_MAPPING_RESPONSE);
	Logger
	.logInfo("ServiceTypeMappingBusinessService - Entering execute(): ServiceTypeMapping Delete");
	RuleServiceTypeAssociation  serviceTypeAssociation  =new RuleServiceTypeAssociation ();
	serviceTypeAssociation.setRuleId(request.getRuleId());
	RuleMapping ruleMapping;
	List messages =new ArrayList();
	List codeList =request.getCodeList();
	boolean isValid =true;
	try{
	ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
	ruleMapping = builder.retrieveRuleMapping(request.getRuleId());
	List totalList = ruleMapping.getServiceTypeCodes();
	ServiceTypeMappingBusinessValidationService validationSrervice = new ServiceTypeMappingBusinessValidationService();
	isValid = validationSrervice.isValidForDelete(codeList,totalList);
	if(null!=codeList){
		if(isValid){
			if(null!=codeList && codeList.size()>0){
				for(int i=0;i<codeList.size();i++){
				serviceTypeAssociation.setServiceTypeCode((codeList.get(i)).toString());
				builder.deleteServiceTypeAssociation(serviceTypeAssociation, getAudit(request.getUser()));
				}
				builder.updateRuleMappingTimeStamp(request.getRuleId(),getAudit(request.getUser()));
				messages.add(new InformationalMessage("srv.type.mapping.deleted"));
				}
		}else{
				messages.add(new ErrorMessage("srv.type.recquierd.code.alert"));
		}
	}
	ruleMapping = builder.retrieveRuleMapping(request.getRuleId());
	response.setRuleMappingBO(ruleMapping);
	
	addmessages(response, messages);
	
	Logger
	.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Retrieve");
	}catch(SevereException ex){
		List logParameters = new ArrayList();
		logParameters.add(request);
		String logMessage = "Failed while processing RetrieveServiceTypeMappingRequest";
		throw new ServiceException(logMessage, logParameters, ex);
	}
	return response;

}
	/**
	 * Service method for view Service type code mapping.
	 * This method gets the request from the backing bean and converts VO object to BO object and calls the builder
	 * and get back the search results.
	 * Same request is used for both retrieve as well view flow.
	 *  
	 * @param request
	 * @return
	 * @throws SevereException
	 */

	public WPDResponse execute(ViewServiceTypeCodeMappingRequest request)
			throws ServiceException {
		ViewServiceTypeCodeMappingResponse response = null;
		Logger
		.logInfo("ServiceTypeMappingBusinessService - Entering execute(): ServiceTypeMapping Search");		
		ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
		ServiceTypeMappingBusinessValidationService validationService = new ServiceTypeMappingBusinessValidationService();
		  List messages = new ArrayList();
		  
		try{
			
			response = (ViewServiceTypeCodeMappingResponse) new ValidationServiceController().execute(request); 
			if (request.getAction() == ViewServiceTypeCodeMappingRequest.RETRIEVE_UNMAPPED_RULES) {
				if(!response.isDataFeedRunning()) {
					List unMappedRules = builder.retrieveUnMappedRules();
					response.setUnMappedRules(unMappedRules);
					if(unMappedRules.size() == 0) {
						messages.add(new InformationalMessage("serviceType.no.unmappedrules"));
					}
				}
			}
			
			addMessagesToResponse(response, messages);
			response.setMessages(messages);
		    Logger.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Search");
		}catch(SevereException ex){
			List logParameters = new ArrayList();
			logParameters.add(request);
			String logMessage = "Failed while processing ViewServiceTypeCodeMappingRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		return response;

	}

	
	/**
	 * Service method for Create/Update Service type mapping.
	 * @param request
	 * @return
	 * @throws SevereException
	 */

	public WPDResponse execute(SaveServiceTypeMappingRequest request)
			throws SevereException {
		SaveServiceTypeMappingResponse response;

		// Calling validation service
		response = (SaveServiceTypeMappingResponse) new ValidationServiceController().execute(request);
		Logger.logInfo("ServiceTypeMappingBusinessService - Entering execute(): ServiceTypeMapping Save");
		// Returns if not valid.
		if (!response.isValid()) {
			response.setSuccess(false);
			Logger.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Save");
			return response;
		}

		ServiceTypeMapping serviceTypeMapping = new ServiceTypeMapping();
		ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
		//copyValues(request, serviceTypeMapping);
		List messages = new ArrayList();
		RuleMapping ruleMapping = new RuleMapping();
		RuleServiceTypeAssociation serviceTypeAssociation = new RuleServiceTypeAssociation();
		List eb03CodeList;
		switch (request.getAction()) {
		case SaveServiceTypeMappingRequest.CREATE_ACTION:
			// Creates the mapping.
			ruleMapping.setRuleId(request.getServiceTypeMappingVO().getHeaderRuleId());
			ruleMapping.setServiceTypeCodes(request.getServiceTypeMappingVO().getEb03codeList());
			ruleMapping.setApplicableToBX(request.getServiceTypeMappingVO().getBlueExcahngeApplicable());
			builder.createRuleMapping(ruleMapping, getAudit(request.getUser()));
			eb03CodeList = request.getServiceTypeMappingVO().getEb03codeList();
			if (null != eb03CodeList && eb03CodeList.size() > 0) {
				for (int i = 0; i < eb03CodeList.size(); i++) {
					serviceTypeAssociation.setRuleId(request.getServiceTypeMappingVO().getHeaderRuleId());
					serviceTypeAssociation.setServiceTypeCode(eb03CodeList.get(i).toString());
					serviceTypeAssociation.setSendDynamicInfo(request.getServiceTypeMappingVO().getSendDynamicInfo());
					builder.createRuleServiceTypeAssociation(serviceTypeAssociation, getAudit(request.getUser()));
				}
			}
			messages.add(new InformationalMessage("srv.type.mapping.created"));

			break;

		case SaveServiceTypeMappingRequest.UPDATE_ACTION:
			// Updates the mapping.
			List sendInfoCodeList = null;
			List sendInfoCodeValue = null;
			ruleMapping.setRuleId(request.getServiceTypeMappingVO().getHeaderRuleId());
			ruleMapping.setServiceTypeCodes(request.getServiceTypeMappingVO().getEb03codeList());
			ruleMapping.setApplicableToBX(request.getServiceTypeMappingVO().getBlueExcahngeApplicable());
			eb03CodeList = request.getServiceTypeMappingVO().getEb03codeList();
			if (null != eb03CodeList && eb03CodeList.size() > 0) {
				for (int i = 0; i < eb03CodeList.size(); i++) {
					serviceTypeAssociation.setRuleId(request.getServiceTypeMappingVO().getHeaderRuleId());
					serviceTypeAssociation.setServiceTypeCode(eb03CodeList.get(i).toString());
					serviceTypeAssociation.setSendDynamicInfo(request.getServiceTypeMappingVO().getBlueExcahngeApplicable());
					builder.createRuleServiceTypeAssociation(serviceTypeAssociation, getAudit(request.getUser()));
				}
			}
			builder.updateRuleMapping(ruleMapping, getAudit(request.getUser()));
			if (null != request.getSendInfoCodeList() ) {
				sendInfoCodeList = request.getSendInfoCodeList();
				sendInfoCodeValue = request.getSendInfoCodeValue();
				for (int i = 0; i < sendInfoCodeList.size(); i++) {
					serviceTypeAssociation.setRuleId(request.getServiceTypeMappingVO().getHeaderRuleId());
					serviceTypeAssociation.setServiceTypeCode(sendInfoCodeList.get(i).toString());
					serviceTypeAssociation.setSendDynamicInfo(sendInfoCodeValue.get(i).toString());
					builder.updateServiceTypeAssociation(serviceTypeAssociation,getAudit(request.getUser()));
				}
			}
			if ("N".equals(request.getServiceTypeMappingVO().getBlueExcahngeApplicable())) {
				deleteAllServiceTypeCode(request.getServiceTypeMappingVO().getHeaderRuleId(), getAudit(request.getUser()));
			}
			messages.add(new InformationalMessage("srv.type.mapping.updated"));
			break;
		case SaveServiceTypeMappingRequest.VIEW_ACTION:
			serviceTypeMapping = builder.retrieve(serviceTypeMapping);
			response.setServiceTypeMapping(serviceTypeMapping);
			response.setSuccess(true);
			Logger.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Save");
			return response;

		}

		ruleMapping = builder.retrieveRuleMapping(request.getServiceTypeMappingVO().getHeaderRuleId());
		response.setRuleMapping(ruleMapping);
		response.setSuccess(true);
		addmessages(response, messages);
		Logger.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Save");
		return response;
	}
	/*
	 * @ruleId,Audit
	 *This methode for deleting all mapping of rule id to eb03 code while chenging form bxvalue yes to no
	*/
		public void deleteAllServiceTypeCode(String ruleId,Audit audit) throws SevereException{
			RuleMapping ruleMapping =new RuleMapping();
			RuleServiceTypeAssociation serviceTypeAssociation = new RuleServiceTypeAssociation(); 
			ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
			ruleMapping = builder.retrieveRuleMapping(ruleId);
			List codeList = ruleMapping.getServiceTypeCodes();
			serviceTypeAssociation.setRuleId(ruleId);
			if(null!=codeList && codeList.size()>0){
				for(int i=0;i<codeList.size();i++){
					RuleServiceTypeAssociation serviceAssociation =(RuleServiceTypeAssociation)codeList.get(i);
					serviceTypeAssociation.setServiceTypeCode(serviceAssociation.getServiceTypeCode());
					builder.deleteServiceTypeAssociation(serviceTypeAssociation, audit);
				}
			}
			
			}
	private void addmessages(WPDResponse response, List messages) {
		if (response.getMessages() == null) {
			response.setMessages(messages);
		} else {
			response.getMessages().addAll(messages);
		}
	}

	public WPDResponse execute(ServiceTypeMappingSearchRequest request)
			throws ServiceException {
		ServiceTypeMappingSearchResponse response = (ServiceTypeMappingSearchResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SEARCH_SERVICE_TYPE_MAPPING_RESPONSE);
		ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
		Logger
		.logInfo("ServiceTypeMappingBusinessService - Entering execute(): ServiceTypeMapping Search");	
		switch (request.getAction()) {
		// method for locating values - Web Application
		case BusinessConstants.LOCATE_SERVICE_MAPPING:
			
			ServiceTypeLocateCriteriaBO businessObject = new ServiceTypeLocateCriteriaBO();
			businessObject.setEb03Identifier(request.getEB03List());
			if(null != request.getIsApplicable() && (!("".equals(request.getIsApplicable()))))
				businessObject.setApplicableToBX((request.getIsApplicable().equals(BusinessConstants.APPLICABLE_TO_BX))? BusinessConstants.APPLICABLE_TO_BX:BusinessConstants.NOT_APPLICABLE_TO_BX);
			businessObject.setHeaderRule(request.getHeaderRuleList());
			try {
				SearchResults searchResults = builder
						.searchServiceTypeMapping(businessObject);
				response.setSearchResultList(searchResults.getSearchResults());
				List messages = new ArrayList();
				if (null != searchResults) {
					if (searchResults.getSearchResultCount() >= 50) {
	
						messages.add(new InformationalMessage(
								BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
					} else if (searchResults.getSearchResultCount() <= 0) {
	
						messages.add(new InformationalMessage(
								BusinessConstants.SEARCH_RESULT_NOT_FOUND));
					}
				}
				addMessagesToResponse(response, messages);
				response.setMessages(messages);
				
			} catch (SevereException ex) {
				List logParameters = new ArrayList();
				logParameters.add(request);
				String logMessage = "Failed while processing ServiceTypeMappingSearchRequest";
				throw new ServiceException(logMessage, logParameters, ex);
			}
			break;
		
		// method for locating values - Datafeed			
		case BusinessConstants.RETRIEVE_SERVICE_MAPPING_DF:
	  		try{
	  			
	  			Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
	  			List searchResult = builder.retriveServiceTypeMappingDF(request.getStatus());
	  			response.setSearchResultList(searchResult);
	  			
	  		}catch(SevereException exp){
	  			
	  			throw new ServiceException("Exception occured while Builder call", null, exp);	  			
	  		}		
			break;
			// method for locating values - Datafeed			
		 case BusinessConstants.RETRIEVE_NA_SERVICE_TYPE_MAPPING_DF:
	  		try{
	  			
	  			Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
	  			List searchResult = builder.retriveNAServiceTypeMappingDF();
	  			response.setSearchResultList(searchResult);
	  			
	  		}catch(SevereException exp){
	  			
	  			throw new ServiceException("Exception occured while Builder call", null, exp);	  			
	  		}		
			break;
		}
		Logger
		.logInfo("ServiceTypeMappingBusinessService - Returning execute(): ServiceTypeMapping Search");
		return response;
	}

	private Audit getAudit(User user) {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		return audit;
	}

	/**
	 * @param request
	 * @param serviceTypeMapping
	 */
	public static void copyValues(SaveServiceTypeMappingRequest request,
			ServiceTypeMapping serviceTypeMapping) {
		ServiceTypeMappingVO serviceTypeMappingVO = request
				.getServiceTypeMappingVO();
		
		serviceTypeMapping.setEb03Identifier(serviceTypeMappingVO
				.getEb03Identifier());
		

	}

	/*
	 * @ param ServiceTypeLocateCriteriaVO
	 * this method is useed for setting value from ServiceTypeLocateCriteriaVO to ServiceTypeLocateCriteriaVO
	 * @ return ServiceTypeMapping
	 * 
	 */
	private ServiceTypeMapping setVOValuesToBO(ServiceTypeLocateCriteriaVO locateCriteriaVO){
		ServiceTypeMapping serviceTypeMapping = new ServiceTypeMapping();
		serviceTypeMapping.setHeaderRuleId(locateCriteriaVO.getHeaderRuleId());
		serviceTypeMapping.setEb03Identifier(locateCriteriaVO.getEb03IdentifierSTC());
		serviceTypeMapping.setPlaceOfService(locateCriteriaVO.getPlaceOfServicePOS());		
		return serviceTypeMapping;
		
	}
		
}