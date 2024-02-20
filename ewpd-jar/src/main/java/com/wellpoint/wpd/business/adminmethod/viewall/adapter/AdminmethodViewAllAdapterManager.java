/*
 * Created on Oct 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.viewall.adapter;

import java.util.HashMap;
import java.util.List;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.viewall.bo.AdminMethodViewAllFilterBO;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerBO;
import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminmethodViewAllAdapterManager {

	/**
	 * Method used to retreive the admin method mapping details from the filter
	 * criteria table
	 * 
	 * @param adminMethodViewAllFilterBO
	 * @return
	 * @throws AdapterException
	 */
	public List getSearchResults(
			AdminMethodViewAllFilterBO adminMethodViewAllFilterBO)
			throws AdapterException {

		Logger
				.logInfo("Entering the method for getting valid search result for view all popup ");

		HashMap hashMap = new HashMap();

		// Checks whether admin method number is entered or not, if not entered,
		// replace it with null
		if (adminMethodViewAllFilterBO.getAdminMethodNo() == null
				|| "".equals(adminMethodViewAllFilterBO.getAdminMethodNo())) {

			hashMap.put("adminMethodNo", null);
		} else {
			hashMap.put("adminMethodNo", adminMethodViewAllFilterBO
					.getAdminMethodNo());
		}

		hashMap.put("adminMethodDescription", "%"
				+ adminMethodViewAllFilterBO.getAdminMethodDescription().toUpperCase() + "%");

		//Checks whether processing method is entered or not, if not entered,
		// replace it with null
		if (adminMethodViewAllFilterBO.getProcessingMethod() == null
				|| "".equals(adminMethodViewAllFilterBO.getProcessingMethod()))
			hashMap.put("processMethod", null);
		else {
			String [] str= adminMethodViewAllFilterBO.getProcessingMethod().split("~");
			
			hashMap.put("processMethod", adminMethodViewAllFilterBO.getProcessingMethod());
		}

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodViewAllFilterBO.class.getName(),
				"locateAdminMethod", hashMap);

		SearchResults searchResults;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);

			Logger
					.logInfo("Returning the method for getting valid search result for forpopup");
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;
		} catch (Exception ex) {
			throw new AdapterException(
					"Exception occured in locate getSearchResults method in AdminMethodViewAllAdapterManager",
					ex);
		} 
	}

	/**
	 * Method used to retreive all the possible answers for an admin method. It
	 * will be populated in a list of AdminMethodViewAllFilterBO.
	 * 
	 * @param adminMethodSysId
	 * @return
	 * @throws AdapterException
	 */
	public List getAnswerList(int adminMethodSysId) throws AdapterException {

		HashMap hashMap = new HashMap();

		hashMap.put("adminMethodSysId", new Integer(adminMethodSysId));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodViewAllFilterBO.class.getName(),
				"locatePossibleAnswers", hashMap);

		try {
			SearchResults searchResults = AdapterUtil
					.performSearch(searchCriteria);

			return searchResults.getSearchResults();

		} catch (Exception ex) {
			throw new AdapterException(
					"Exception occured in locate getAnswerList method in AdminMethodViewAllAdapterManager",
					ex);
		}

	}

	/**
	 * Method used to get the question id, description, and the possible answer
	 * details for input as possible answer id.
	 * 
	 * @param comaSeperatedAnswerList
	 * @throws AdapterException
	 */
	public List getQuestionAnswerList(List comaSeperatedAnswerList)
			throws AdapterException {

		HashMap hashMap = new HashMap();

		hashMap.put("possibleAnswer", comaSeperatedAnswerList);

		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(QuestionAnswerBO.class.getName(),
						"getQuestionAnswers", hashMap);

		try {
			SearchResults searchResults = AdapterUtil
					.performSearch(searchCriteria);

			return searchResults.getSearchResults();
		} catch (Exception ex) {
			throw new AdapterException(
					"Exception occured in locate getQuestionAnswerList method in AdminMethodViewAllAdapterManager",
					ex);
		}

	}

}
