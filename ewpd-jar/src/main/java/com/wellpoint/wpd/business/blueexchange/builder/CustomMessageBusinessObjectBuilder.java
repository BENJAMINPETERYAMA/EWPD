/*
 * CustomMessageBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.blueexchange.adapter.CustomMessageAdapterManager;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.CustomMessageTextBO;
import com.wellpoint.wpd.common.blueexchange.response.DeleteCustomMessageResponse;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageBusinessObjectBuilder {

	/**
	 * Method to retrieve Custom Message for datafeed
	 * 
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveCustomMessagesDF(String status) throws SevereException {
		
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering retrieve method retrieveCustomMessagesDF");

		CustomMessageAdapterManager customMessageAdapterManager = new CustomMessageAdapterManager();

		List searchList = customMessageAdapterManager
				.retrieveCustomMessagesForDataFeed(status);
		Logger.logInfo("Exit CustomMessageBusinessObjectBuilder, method -retrieveCustomMessagesDF");
				
		return searchList;

	}
	/**
	 * Method to retrieve Custom Message for datafeed with status as send to test
	 * 
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveCustomMessagesDFSendToTest() throws SevereException {
		
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering retrieve method retrieveCustomMessagesDFForScheduledToTest");

		CustomMessageAdapterManager customMessageAdapterManager = new CustomMessageAdapterManager();

		List searchList = customMessageAdapterManager
				.retrieveCustomMessagesForDataFeedScheduledToTest();
		Logger.logInfo("Exit CustomMessageBusinessObjectBuilder, method -retrieveCustomMessagesDF");
				
		return searchList;

	}

	/**
	 * Method to retrieve Custom Message for datafeed with status as approve
	 * 
	 * @return List
	 * @throws SevereException
	 */
	public List retrieveCustomMessagesDFSendToProduction() throws SevereException {
		
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering retrieve method retrieveCustomMessagesDFForScheduledToProduction");

		CustomMessageAdapterManager customMessageAdapterManager = new CustomMessageAdapterManager();

		List searchList = customMessageAdapterManager
				.retrieveCustomMessagesForDataFeedScheduledToProduction();
		Logger.logInfo("Exit CustomMessageBusinessObjectBuilder, method -retrieveCustomMessagesDF");
				
		return searchList;

	}
	/**
	 * This method gets the audit object and calls the adapter to create Custom
	 * message text
	 * 
	 * @param CustomMessageTextBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean create(CustomMessageTextBO customMessageTextBO, User user)
			throws SevereException {
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering create method ");
		CustomMessageAdapterManager customMessageAdapterManager = new CustomMessageAdapterManager();
		Audit audit = getAudit(user);
		customMessageTextBO.setCreatedUser(audit.getUser());
		customMessageTextBO.setCreatedTimestamp(audit.getTime());
		customMessageTextBO.setLastUpdatedUser(audit.getUser());
		customMessageTextBO.setLastUpdatedTimestamp(audit.getTime());
		Logger.logInfo("Exit From CustomMessageBusinessObjectBuilder, method- create(CustomMessageTextBO customMessageTextBO, User user)");
		return customMessageAdapterManager
				.createCustomMessage(customMessageTextBO);

	}

	/**
	 * This method gets the audit object and calls the adapter to update the
	 * Business Object
	 * 
	 * @param CustomMessageTextBO
	 * @param user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean update(CustomMessageTextBO customMessageTextBO, User user)
			throws SevereException {
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering update method ");
		CustomMessageAdapterManager customMessageAdapterManager = new CustomMessageAdapterManager();
		//Audit Object is created to get the created time of the Business
		// Object
		Audit audit = getAudit(user);
		customMessageTextBO.setLastUpdatedUser(audit.getUser());
		customMessageTextBO.setLastUpdatedTimestamp(audit.getTime());
		//Adapter call to update the Business Object
		Logger.logInfo("Exit From CustomMessageBusinessObjectBuilder, method- update(CustomMessageTextBO customMessageTextBO, User user)");
		return customMessageAdapterManager.updateCustomMessage(
				customMessageTextBO, user.getUserId());

	}

	/**
	 * This method gets the businessObject as input from
	 * CustomMessageBusinessService and pass the businessObject to the
	 * CustomMessageAdapterManager. CustomMessageAdapterManager returns search
	 * results and this result are converted to businessObject and return this
	 * businessObject to the CustomMessageBusinessService
	 * 
	 * @param messageTextBO
	 * @return messageTextBO
	 * @throws SevereException
	 */
	public CustomMessageTextBO retrieve(CustomMessageTextBO messageTextBO)
			throws SevereException {
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering retrieve method ");
		CustomMessageAdapterManager messageAdapterManager = new CustomMessageAdapterManager();
		CustomMessageTextBO customMessageTextBO = new CustomMessageTextBO();
		SearchResults searchResults = null;

		searchResults = messageAdapterManager
				.retrieveCustomMessage(messageTextBO);
		if (null != searchResults && searchResults.getSearchResultCount() > 0) {
			customMessageTextBO = (CustomMessageTextBO) searchResults
					.getSearchResults().get(0);
		}
		Logger.logInfo("Exit From CustomMessageBusinessObjectBuilder, method- retrieve(CustomMessageTextBO messageTextBO)");
		return customMessageTextBO;
	}

	/**
	 * This method gets customMessageTextBO businessObject as input parameters
	 * from BusinessService and pass the businessObject to the
	 * CustomMessageAdapterManager. CustomMessageAdapterManager returns search
	 * results and this result are converted boolean and return it to the
	 * BusinessService
	 * 
	 * @param messageTextBO
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean locateDuplicate(CustomMessageTextBO messageTextBO)
			throws SevereException {
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering locateDuplicate method ");
		CustomMessageAdapterManager messageAdapterManager = new CustomMessageAdapterManager();
		SearchResults searchResults = null;
		boolean valid = true;

		searchResults = messageAdapterManager
				.retrieveCustomMessage(messageTextBO);
		if (null != searchResults && searchResults.getSearchResultCount() > 0) {
			valid = false;
		}
		Logger.logInfo("Exit From CustomMessageBusinessObjectBuilder, method name- locateDuplicate(CustomMessageTextBO messageTextBO)");
		return valid;
	}

	/**
	 * this method used to search CustomMessageText values and it returns list
	 * 
	 * @param CustomMessageTextBO
	 *            (inputs are headerRule List,spsParameterList,messageText and
	 *            messagereqindicator)
	 * @return list contains search result(searchList)
	 * @throws SevereException
	 */
	public List locateCustomMessageText(CustomMessageTextBO customMessageTextBO)
			throws SevereException {
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering locateCustomMessageText method ");
		List searchList = new ArrayList();
		CustomMessageAdapterManager messageAdapterManager = new CustomMessageAdapterManager();
		searchList = messageAdapterManager.locate(customMessageTextBO);
		if (null != searchList && searchList.size() > 0) {
			for (int i = 0; i < searchList.size(); i++) {
				CustomMessageTextBO customMessageBO = (CustomMessageTextBO) searchList
						.get(i);
				if (null != customMessageBO.getMessagetext()
						&& customMessageBO.getMessagetext().length() > 15) {
					customMessageBO.setMessagetext((customMessageBO
							.getMessagetext()).substring(0, 15)
							+ "...");
					searchList.set(i, customMessageBO);
				}
			}
		}
		Logger.logInfo("Exit From CustomMessageBusinessObjectBuilder, method name- locateCustomMessageText(CustomMessageTextBO customMessageTextBO)");
		return searchList;

	}

	/**
	 * method for deleting customMessageText @ input CustomMessageTextBO that
	 * holds(spsID and headerRuleId)
	 * @param deleteCustomMessageRequest
	 * @throws ServiceException @
	 */
	public void deleteCustomMessage(CustomMessageTextBO messageTextBO,
			User user, DeleteCustomMessageResponse deleteCustomMessageResponse)
			throws SevereException {
		Logger.logInfo("CustomMessageBusinessObjectBuilder - Entering deleteCustomMessage method ");
		CustomMessageAdapterManager messageAdapterManager = new CustomMessageAdapterManager();
		List messages = null;

		if (messageAdapterManager.deleteCustomMessage(messageTextBO, user)) {
			deleteCustomMessageResponse.setSearchResults(null);
			messages = new ArrayList(2);
			InformationalMessage informationalMessage = new InformationalMessage(
					BusinessConstants.CUSTOM_MESSAGE_DELETE_SUCCESS);
			informationalMessage.setParameters(new String[] {
					messageTextBO.getHeaderRuleId(),
					messageTextBO.getSpsParameterId() });
			messages.add(informationalMessage);
			deleteCustomMessageResponse.setMessages(messages);
		}
		Logger.logInfo("Exit From CustomMessageBusinessObjectBuilder, method name- deleteCustomMessage");
	}

	/**
	 * This method is used to build the audit object with user
	 * 
	 * @param user
	 * @return audit
	 */
	private Audit getAudit(User user) {
		AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
		Audit audit = auditFactory.getAudit(user);
		return audit;
	}

}