/*
 * CustomMessageAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.CustomMessageTextBO;
import com.wellpoint.wpd.common.blueexchange.bo.DeletedCustomMessageTextBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageAdapterManager {

	/**
	 * This method is to create the Custom Message text
	 * 
	 * @param CustomMessageTextBO
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean createCustomMessage(CustomMessageTextBO customMessageTextBO)
			throws SevereException {
		//Adapter Method to Insert the Business Object
		AdapterUtil.performInsert(customMessageTextBO, customMessageTextBO
				.getCreatedUser());
		return true;
	}

	/**
	 * This method is to Update the Custom Message text
	 * 
	 * @param CustomMessageTextBO
	 * @param userId
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateCustomMessage(CustomMessageTextBO customMessageTextBO,
			String userId) throws SevereException {
		//Adapter Method to Update the Business Object
		AdapterUtil.performUpdate(customMessageTextBO, userId);
		return true;
	}

	/**
	 * Retrive the custom message for data feed.
	 *  @ return list of custom message
	 * @throws SevereException
	 */
	public List retrieveCustomMessagesForDataFeed(String status) throws SevereException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
		List locateResultsList = null;
		HashMap refValSet = new HashMap();
		refValSet.put("statusCd",status);
		refValSet.put("statusCd2",status);
	
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				CustomMessageTextBO.class.getName(),
				BusinessConstants.RETRIEVE_CUSTOM_MESSAGE_VALUES, refValSet);

		searchResults = AdapterUtil.performSearch(searchCriteria);

		locateResultsList = searchResults.getSearchResults();

		return locateResultsList;
	}
	/**
	 * Retrive the custom message for data feed for scheduled to test.
	 *  @ return list of custom message
	 * @throws SevereException
	 */
	public List retrieveCustomMessagesForDataFeedScheduledToTest() throws SevereException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
		List locateResultsList = null;
		HashMap refValSet = new HashMap();
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		DeletedCustomMessageTextBO.class.getName(),
		BusinessConstants.RETRIEVE_CUSTOM_MESSAGE_VALUES_FOR_SEND_TO_TEST, refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		return locateResultsList;
	}
	/**
	 * Retrive the custom message for data feed for scheduled to production.
	 *  @ return list of custom message
	 * @throws SevereException
	 */
	public List retrieveCustomMessagesForDataFeedScheduledToProduction() throws SevereException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
		List locateResultsList = null;
		HashMap refValSet = new HashMap();
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		DeletedCustomMessageTextBO.class.getName(),
		BusinessConstants.RETRIEVE_CUSTOM_MESSAGE_VALUES_FOR_SEND_TO_PRODUCTION, refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		return locateResultsList;
	}
	/**
	 * This method gets businessObject as input parameters from
	 * CustomMessageBusinessObjectBuilder. From the businessObject it takes the
	 * Search Criteria values(headerRuleId,spsParameterId) and perform Search.
	 * Search Results are sent back to the CustomMessageBusinessObjectBuilder.
	 * 
	 * @param CustomMessageTextBO
	 * @return searchResults
	 * @throws SevereException
	 */
	public SearchResults retrieveCustomMessage(CustomMessageTextBO messageTextBO)
			throws SevereException {
		SearchResults searchResults = null;
		Logger
				.logInfo("Entering the method for retrieving custom message text using spsid and headerRuleId");
		HashMap criteriaMap = getCriteriaForCustomMessage(messageTextBO);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				messageTextBO.getClass().getName(),
				BusinessConstants.CUSTOM_MESSAGE_TEXT_VIEW, criteriaMap);

		searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger
				.logInfo("Returning the method for retrieving custom message text using spsid and headerRuleId");
		return searchResults;
	}

	/**
	 * This method gets the Search Criteria Values(headerRuleId,spsParameterId)
	 * from the businessObject and return it to the method called.
	 * 
	 * @param CustomMessageTextBO
	 * @return hashmap
	 */
	private HashMap getCriteriaForCustomMessage(
			CustomMessageTextBO messageTextBO) {

		Logger
				.logInfo("Entering the method for getting criteria for custom message text BO");
		HashMap criteriaValues = new HashMap();
		criteriaValues.put(BusinessConstants.CUSTOM_MESSAGE_TEXT_SPS_ID,
				messageTextBO.getSpsParameterId());
		criteriaValues.put(BusinessConstants.CUSTOM_MESSAGE_TEXT_RULE_ID,
				messageTextBO.getHeaderRuleId());
		Logger
				.logInfo("Returning the method for getting criteria for custom message text BO");
		return criteriaValues;

	}

	/**
	 * This method performs a delete operation on the custom message selected.
	 * The input required are header rule id and sps paramter id.
	 * 
	 * @param CustomMessageTextBO
	 * @throws SevereException
	 */
	public boolean deleteCustomMessage(CustomMessageTextBO messageTextBO,
			User user) throws SevereException {

		AdapterUtil.performRemove(messageTextBO, user.getUserId());

		return true;

	}

	/**
	 * This method takes CustomMessageTextBO(inputs are headerRule
	 * List,spsParameterList,messageText and messagereqindicator) as input and
	 * map these values and perform search
	 * 
	 * @param CustomMessageTextBO
	 * @return List of search result(locateResultsList)
	 * @throws SevereException
	 */
	public List locate(CustomMessageTextBO customMessageTextBO)
			throws SevereException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
		List locateResultsList = null;
		HashMap refValSet = new HashMap();
		refValSet
				.put("headerRuleList", customMessageTextBO.getHeaderRuleList());
		refValSet.put("spsParameterList", customMessageTextBO
				.getSpsParameterList());
		if (null != customMessageTextBO.getMessagetext()
				&& !("").equals(customMessageTextBO.getMessagetext())){
			refValSet.put("messagetext", "%"
					+ customMessageTextBO.getMessagetext() + "%");
		}
		refValSet.put("messageReqIndicator", customMessageTextBO
				.getMessageReqIndicator());

		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				CustomMessageTextBO.class.getName(), "searchCustomMessages",
				refValSet, BusinessConstants.RESULT_SET_SIZE);

		searchResults = AdapterUtil.performSearch(searchCriteria);
		
		locateResultsList = searchResults.getSearchResults();
		
		return locateResultsList;
	}
}