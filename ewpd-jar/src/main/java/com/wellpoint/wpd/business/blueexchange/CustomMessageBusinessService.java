/*
 * CustomMessageBusinessService.java
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

import com.wellpoint.wpd.business.blueexchange.builder.CustomMessageBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.CustomMessageTextBO;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageRetrieveRequest;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageTextCreateRequest;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageTextUpdateRequest;
import com.wellpoint.wpd.common.blueexchange.request.DeleteCustomMessageRequest;
import com.wellpoint.wpd.common.blueexchange.request.SearchCustomMessageRequest;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageRetrieveResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextUpdateResponse;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextCreateResponse;
import com.wellpoint.wpd.common.blueexchange.response.DeleteCustomMessageResponse;
import com.wellpoint.wpd.common.blueexchange.response.SearchCustomMessageResponse;
import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageBusinessService extends WPDBusinessService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		return super.execute(request);
	}

	/**
	 * Method for creating Custom Message Text @ input
	 * CustomMessageTextCreateRequest contains CustomMessageTextVO This call
	 * CustomMessageBusinessObjectBuilder object and pass customMessageTextBO as
	 * input. @ returns CustomMessageTextCreateResponse
	 *  
	 */
	public WPDResponse execute(
			CustomMessageTextCreateRequest customMessageTextCreateRequest)
			throws ServiceException {

		CustomMessageTextBO customMessageTextBO = getBOValuesFromVO(customMessageTextCreateRequest
				.getCustomMessageTextVO());
		CustomMessageBusinessObjectBuilder businessObjectBuilder = new CustomMessageBusinessObjectBuilder();
		CustomMessageTextCreateResponse customMessageTextCreateResponse = (CustomMessageTextCreateResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CUSTOM_MESSAGE_CREATE_RESPONSE);
		customMessageTextCreateResponse = (CustomMessageTextCreateResponse) new ValidationServiceController()
				.execute(customMessageTextCreateRequest);
		if (customMessageTextCreateResponse.isValidationSuccessFlag()) {
			try {
				if (businessObjectBuilder.create(customMessageTextBO,
						customMessageTextCreateRequest.getUser())) {
					List message = new ArrayList();
					message.add(new InformationalMessage(
							BusinessConstants.CUSTOM_MESSAGE_CREATE_SUCCESS));
					customMessageTextCreateResponse.setMessages(message);
					customMessageTextBO = businessObjectBuilder
							.retrieve(customMessageTextBO);
					if (null != customMessageTextBO)
						customMessageTextCreateResponse
								.setCustomMessageTextVO(getCustomMessageMappingVO(customMessageTextBO));
				}
			} catch (SevereException e) {
				e.printStackTrace();
				ServiceException serviceException = new ServiceException(null,
						null, e);
				throw serviceException;
			}
		}
		return customMessageTextCreateResponse;

	}

	/**
	 * Method for updating Custom Message Text @ input
	 * CustomMessageTextUpdateRequest contains CustomMessageTextVO This call
	 * CustomMessageBusinessObjectBuilder object and pass customMessageTextBO as
	 * input. @ returns CustomMessageTextUpdateResponse
	 *  
	 */
	public WPDResponse execute(
			CustomMessageTextUpdateRequest customMessageTextUpdateRequest)
			throws ServiceException {

		CustomMessageTextBO customMessageTextBO = getBOValuesFromVO(customMessageTextUpdateRequest
				.getCustomMessageTextVO());
		CustomMessageBusinessObjectBuilder businessObjectBuilder = new CustomMessageBusinessObjectBuilder();
		CustomMessageTextUpdateResponse customMessageTextUpdateResponse = (CustomMessageTextUpdateResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CUSTOM_MESSAGE_UPDATE_RESPONSE);

		try {
			if (businessObjectBuilder.update(customMessageTextBO,
					customMessageTextUpdateRequest.getUser())) {
				List message = new ArrayList();
				message.add(new InformationalMessage(
						BusinessConstants.CUSTOM_MESSAGE_UPDATE_SUCCESS));
				customMessageTextUpdateResponse.setMessages(message);
				customMessageTextBO = businessObjectBuilder
						.retrieve(customMessageTextBO);
				if (null != customMessageTextBO)
					customMessageTextUpdateResponse
							.setCustomMessageTextVO(getCustomMessageMappingVO(customMessageTextBO));
			}
		} catch (SevereException e) {
			e.printStackTrace();
			ServiceException serviceException = new ServiceException(null,
					null, e);
			throw serviceException;
		}

		return customMessageTextUpdateResponse;

	}

	/**
	 * Method for locate all the result. 
	 * SearchCustomMessageRequest contains CustomMessageTextVO that
	 * holds(headerRuleList,SPS Parameter list,Message text and message req
	 * indicator) This call CustomMessageBusinessObjectBuilder object and pass
	 * customMessageTextBO as input. @ returns SearchCustomMessageResponse
	 * contains search result list
	 *  
	 * @param SearchCustomMessageRequest
	 * @return SearchCustomMessageResponse
	 * @throws ServiceException
	 *
	 */
	public WPDResponse execute(
			SearchCustomMessageRequest searchCustomMessageRequest)
			throws ServiceException {

		Logger.logInfo("Entering execute method, request = "
				+ searchCustomMessageRequest.getClass().getName());

		CustomMessageBusinessObjectBuilder customMessageBusinessObjectBuilder = new CustomMessageBusinessObjectBuilder();
		SearchCustomMessageResponse searchCustomMessageResponse = (SearchCustomMessageResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CUSTOM_MESSAGE_SEARCH_RESPONSE);
		List searchResult;
		switch (searchCustomMessageRequest.getAction()) {
		
		case BusinessConstants.LOCATE_CUSTOM_MESSAGES:
			try {
				Logger.logInfo("Returning  from execute method for request="
						+ searchCustomMessageRequest.getClass().getName());
				CustomMessageTextBO customMessageTextBO = new CustomMessageTextBO();
				CustomMessageTextBO customMessageTeTextBO = getBOValuesFromVO(searchCustomMessageRequest
						.getCustomMessageTextVO());
				searchResult = customMessageBusinessObjectBuilder
						.locateCustomMessageText(customMessageTeTextBO);
				if (null != searchResult) {
					if (searchResult.size() > 50) {
						searchCustomMessageResponse
								.addMessage(new InformationalMessage(
										BusinessConstants.MSG_SEARCH_RESULT_EXCEEDS));
						searchResult.remove(searchResult.size() - 1);
					} else if (searchResult.size() <= 0) {
						searchCustomMessageResponse
								.addMessage(new InformationalMessage(
										BusinessConstants.SEARCH_RESULT_NOT_FOUND));
					}
				}
				searchCustomMessageResponse.setSearchResultList(searchResult);
			} catch (SevereException ex) {

				throw new ServiceException(
						"Exception occured while Builder call", null, ex);
			}
			break;
		case BusinessConstants.RETRIEVE_CUSTOM_MESSAGES:
			try {
				Logger.logInfo("Returning  from execute method for request="
						+ searchCustomMessageRequest.getClass().getName());
				searchResult = customMessageBusinessObjectBuilder
						.retrieveCustomMessagesDF(searchCustomMessageRequest.getStatusCd());
				searchCustomMessageResponse.setSearchResultList(searchResult);

			} catch (SevereException ex) {
				throw new ServiceException(
						"Exception occured while Builder call", null, ex);
			}
			break;
			/**
			 * MTM CODE
			 */
		case BusinessConstants.RETRIEVE_DELETED_CUSTOM_MESSAGES_SEND_TO_TEST:
			try {
				Logger.logInfo("Returning  from execute method for request="
						+ searchCustomMessageRequest.getClass().getName());
				searchResult = customMessageBusinessObjectBuilder
						.retrieveCustomMessagesDFSendToTest();
				searchCustomMessageResponse.setSearchResultList(searchResult);

			} catch (SevereException ex) {
				throw new ServiceException(
						"Exception occured while Builder call", null, ex);
			}
			break;	
		case BusinessConstants.RETRIEVE_DELETED_CUSTOM_MESSAGES_SEND_TO_PRODUCTION:
			try {
				Logger.logInfo("Returning  from execute method for request="
						+ searchCustomMessageRequest.getClass().getName());
				searchResult = customMessageBusinessObjectBuilder
						.retrieveCustomMessagesDFSendToProduction();
				searchCustomMessageResponse.setSearchResultList(searchResult);

			} catch (SevereException ex) {
				throw new ServiceException(
						"Exception occured while Builder call", null, ex);
			}
			break;
		}
		Logger.logInfo("Exiting execute method, request = "
				+ searchCustomMessageRequest.getClass().getName());
		return searchCustomMessageResponse;
	}

	/**
	 * This method gets the request parameters(headerRuleId,spsParameterId as VO
	 * Object) from backingBean and converts VO Object to businessObject and
	 * send this businessObject to CustomMessageBusinessObjectBuilder.
	 * CustomMessageBusinessObjectBuilder returns businessObject as search
	 * results and it is converted to valueObject and sent back to backingBean
	 * 
	 * @param retrieveRequest
	 * @return messageRetrieveResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(CustomMessageRetrieveRequest retrieveRequest)
			throws ServiceException {
		Logger.logInfo("Returning  from execute method for request="
				+ retrieveRequest.getClass().getName());
		CustomMessageBusinessObjectBuilder messageBusinessObjectBuilder = new CustomMessageBusinessObjectBuilder();
		CustomMessageTextBO messageTextBO = getBOValuesFromVO(retrieveRequest
				.getMessageTextVO());
		CustomMessageRetrieveResponse messageRetrieveResponse = (CustomMessageRetrieveResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CUSTOM_MESSAGE_RETRIEVE_RESPONSE);
		CustomMessageTextVO messageTextVO = new CustomMessageTextVO();

		try {
			messageTextBO = messageBusinessObjectBuilder
					.retrieve(messageTextBO);
			if (null != messageTextBO) {
				messageTextVO = getCustomMessageMappingVO(messageTextBO);
			}
		} catch (SevereException e) {
			List logParameters = new ArrayList();
			logParameters.add(retrieveRequest);
			String logMessage = "Failed while processing CustomMessageRetrieveRequest";
			throw new ServiceException(logMessage, logParameters, e);

		}
		Logger.logInfo("Exiting execute method, request = "
				+ retrieveRequest.getClass().getName());
		messageRetrieveResponse.setMessageTextVO(messageTextVO);
		return messageRetrieveResponse;
	}

	/**
	 * This method builds the businessObject by getting the VO values and return
	 * this businessObject to the method called
	 * 
	 * @param messageTextVO
	 * @return messageTextBO
	 */
	private CustomMessageTextBO getBOValuesFromVO(
			CustomMessageTextVO messageTextVO) {
		CustomMessageTextBO messageTextBO = new CustomMessageTextBO();
		messageTextBO.setHeaderRuleId(messageTextVO.getHeaderRuleId());
		messageTextBO.setSpsParameterId(messageTextVO.getSpsParameterId());
		messageTextBO.setHeaderRuleList(messageTextVO.getHeaderRuleList());
		messageTextBO.setSpsParameterList(messageTextVO.getSpsParameterList());
		messageTextBO.setMessagetext(messageTextVO.getMessagetext());
		messageTextBO.setMessageReqIndicator(messageTextVO
				.getMessageReqIndicator());
		messageTextBO.setNoteTypeCode(messageTextVO.getNoteTypeCode());
		return messageTextBO;

	}

	/**
	 * This method gets all parameters from businessObject returned back from
	 * builder and build VO Object and return this valueObject
	 * 
	 * @param mappingBO
	 * @return mappingVO
	 */
	private CustomMessageTextVO getCustomMessageMappingVO(
			CustomMessageTextBO messageTextBO) {

		CustomMessageTextVO messageTextVO = new CustomMessageTextVO();
		messageTextVO.setSpsParameterId(messageTextBO.getSpsParameterId());
		messageTextVO.setSpsParameterDesc(messageTextBO.getSpsParameterDesc());
		messageTextVO.setHeaderRuleId(messageTextBO.getHeaderRuleId());
		messageTextVO.setHeaderRuleDesc(messageTextBO.getHeaderRuleDesc());
		messageTextVO.setMessagetext(messageTextBO.getMessagetext());
		messageTextVO.setMessageReqIndicator(messageTextBO
				.getMessageReqIndicator());
		messageTextVO.setCreatedUser(messageTextBO.getCreatedUser());
		messageTextVO.setCreatedTimestamp(messageTextBO.getCreatedTimestamp());
		messageTextVO.setLastUpdatedUser(messageTextBO.getLastUpdatedUser());
		messageTextVO.setLastUpdatedTimestamp(messageTextBO
				.getLastUpdatedTimestamp());
		messageTextVO.setNoteTypeDesc(messageTextBO.getNoteTypeDesc());
		messageTextVO.setNoteTypeCode(messageTextBO.getNoteTypeCode());
		return messageTextVO;
	}
	

	/**
	 * Method to delete the custom message text
	 * 
	 * @param DeleteCustomMessageRequest
	 * @return DeleteCustomMessageResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(
			DeleteCustomMessageRequest deleteCustomMessageRequest)
			throws ServiceException {

		Logger.logInfo("Entering execute method, request = "
				+ deleteCustomMessageRequest.getClass().getName());
		CustomMessageBusinessObjectBuilder customMessageBusinessObjectBuilder = new CustomMessageBusinessObjectBuilder();
		DeleteCustomMessageResponse deleteCustomMessageResponse = (DeleteCustomMessageResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CUSTOM_MESSAGE_DELETE_RESPONSE);
		CustomMessageTextBO customMessageTextBO = getBOValuesFromVO(deleteCustomMessageRequest
				.getCustomMessageTextVO());
		List searchResult;
		try {
			customMessageBusinessObjectBuilder.deleteCustomMessage(
					customMessageTextBO, deleteCustomMessageRequest.getUser(),
					deleteCustomMessageResponse);
			searchResult = customMessageBusinessObjectBuilder
					.locateCustomMessageText(customMessageTextBO);
			if (null != searchResult && searchResult.size() > 0){
				deleteCustomMessageResponse.setSearchResults(searchResult);
			}
		} catch (SevereException ex) {
			List logParameters = new ArrayList();
			logParameters.add(deleteCustomMessageRequest);
			String logMessage = BusinessConstants.MSG_ERROR;
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger.logInfo("Exiting execute method, request = "
				+ deleteCustomMessageRequest.getClass().getName());
		return deleteCustomMessageResponse;
	}
}