/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethodmapping.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.viewall.bo.AdminMethodViewAllFilterBO;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethodmapping.bo.AdminMethodMappingBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.AdminMethodMappingEntriesBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerGroupBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingAdapterManager {

	/**
	 * @param adminMethodMaintainBO
	 * @return
	 * @throws SevereException
	 */

	HashMap termSet;

	HashMap itemSet;

	/**
	 * Method to create Admin Method Mapping
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @return
	 * @throws AdapterException
	 */

	public boolean createAdminMethodMapping(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO)
			throws AdapterException {

		Logger
				.logInfo("Entering the method for Creating Admin Method Mapping ");

		boolean suceess = false;
		try {

			AdapterUtil.performInsert(adminMethodMappingEntriesBO,
					adminMethodMappingEntriesBO.getCreatedUser());
			suceess = true;
			Logger
					.logInfo("Returning from the method for Creating Admin Method Mapping ");
		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in createAdminMethodMapping method in AdminMethodMappingAdapterManager",
					se);
		}

		return suceess;
	}
	
		/**
	 * Method to Update IS_ADMN_MTHD_MAPPED Flag
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */

	public boolean updateIsAdminMethdMappedFlag(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO)
			throws AdapterException {
		Logger
				.logInfo("Entering the method for Creating Admin Method Mapping :Update IS_ADMN_MTHD_MAPPED Flag");
		boolean suceess = false;
		try {

			AdapterUtil.performUpdate(adminMethodMappingEntriesBO,
					adminMethodMappingEntriesBO.getCreatedUser());

			suceess = true;
			Logger
					.logInfo("Returning from the method for Creating Admin Method Mapping :Update IS_ADMN_MTHD_MAPPED Flag");

		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in insertAdqmQusnMstr method in AdminMethodMappingAdapterManager",
					se);
		}

		return suceess;
	}
	
		public boolean insertPossibleAnswer(
			AdminMethodMappingBO adminmethodMappingBO)
			throws SevereException, AdapterException {

		boolean suceess = false;
		try {
			
				
					AdapterUtil.performInsert(adminmethodMappingBO,adminmethodMappingBO.getCreatedUser());
					
				
				
				suceess = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}
	
	/**
	 * @param Method
	 *            for search Admin Method Mapping
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public List searchAdminMethodMapping(
			AdminMethodMappingBO adminmethodMappingBO) throws AdapterException {
		Logger
				.logInfo("Entering the method for getting valid search result for Admin Method Mapping ");

		HashMap hashMap = new HashMap();
		SearchResults searchResultsAdminMethod = null;
		List locateResultsList = new ArrayList();

		if (adminmethodMappingBO.getAdminMethodNo() == null
				|| "".equals(adminmethodMappingBO.getAdminMethodNo())) {
			hashMap.put("adminMethodNo", null);
		} else {
			hashMap.put("adminMethodNo", new Integer(adminmethodMappingBO
					.getAdminMethodNo()));
		}
		if (adminmethodMappingBO.getAdminMethodSysId() == null
				|| "".equals(adminmethodMappingBO.getAdminMethodSysId())) {
			hashMap.put("adminMethodSysId", null);
		} else {
			hashMap.put("adminMethodSysId", new Integer(adminmethodMappingBO
					.getAdminMethodSysId()));
		}
		if (adminmethodMappingBO.getDescription() == null
				|| "".equals(adminmethodMappingBO.getDescription())) {
			hashMap.put("adminMethodDesc", null);
		} else {
			hashMap.put("adminMethodDesc","%" + adminmethodMappingBO.getDescription().toUpperCase()+"%");
		}
		if (adminmethodMappingBO.getProcessMethodList() == null
				|| "".equals(adminmethodMappingBO.getProcessMethodList())) {
			hashMap.put("processMethodList", null);
		} else {
			hashMap.put("processMethodList", adminmethodMappingBO
					.getProcessMethodList());
		}

		if (adminmethodMappingBO.getQualifierList() == null
				|| "".equals(adminmethodMappingBO.getQualifierList())) {
			hashMap.put("qualifierList", null);
		} else {
			hashMap.put("qualifierList", adminmethodMappingBO
					.getQualifierList());
		}
		if (adminmethodMappingBO.getTermList() == null
				|| "".equals(adminmethodMappingBO.getTermList())) {
			hashMap.put("termList", null);
		} else {
			hashMap.put("termList", adminmethodMappingBO.getTermList());
		}
		if (adminmethodMappingBO.getPvaList() == null
				|| "".equals(adminmethodMappingBO.getPvaList())) {
			hashMap.put("pvaList", null);
		} else {
			hashMap.put("pvaList", adminmethodMappingBO.getPvaList());
		}

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodMappingBO.class.getName(),
				"locateAdminMethodMapping", hashMap);

		try {
			searchResultsAdminMethod = AdapterUtil
					.performSearch(searchCriteria);
			locateResultsList = searchResultsAdminMethod.getSearchResults();

			Logger
					.logInfo("Returning the method for getting valid search result for Admin Method Mapping");

			populateTerm(locateResultsList);
			Logger.logDebug(""
					+ searchResultsAdminMethod.getSearchResults().size());
			if (null != searchResultsAdminMethod) {
				return searchResultsAdminMethod.getSearchResults();
			} else
				return null;
		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in searchAdminMethodMapping method in AdminMethodMappingAdapterManager",
					se);
		}
	}

	/**
	 * @param locateResultsList
	 */
	private void populateTerm(List locateResultsList) {
		AdminMethodMappingBO bo = new AdminMethodMappingBO();

		for (Iterator itr = locateResultsList.iterator(); itr.hasNext();) {

			bo = (AdminMethodMappingBO) itr.next();

			if ((null != bo.getTerm()) && (bo.getTerm() != "")
					&& (bo.getTerm().length() > 0)) {
				if (termSet == null) {
					termSet = getItemMap();
				}
				populateDscrTerm(bo);

			}

		}
	}

	/**
	 * @Method to retrieving PPO and HMO adminMethodsysId and spsId
	 */
	public List getAdminMethodSysId(AdminMethodMappingBO adminmethodMappingBO)
			throws AdapterException {

		Logger
				.logInfo("Entering the method for getting PPO and HMO adminMethodsysId and spsId");

		HashMap hashMap = new HashMap();
		SearchResults searchResultsAdminMethod = null;
		List locateResultsList = new ArrayList();

		if (adminmethodMappingBO.getAdminMethodSysId() == null
				|| "".equals(adminmethodMappingBO.getAdminMethodSysId())) {
			hashMap.put("adminMethodSysId", null);
		} else {
			hashMap.put("adminMethodSysId", new Integer(adminmethodMappingBO
					.getAdminMethodSysId()));
		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodMappingBO.class.getName(), "locateAdminMethodSysId",
				hashMap);

		try {
			searchResultsAdminMethod = AdapterUtil
					.performSearch(searchCriteria);
			locateResultsList = searchResultsAdminMethod.getSearchResults();

			Logger
					.logInfo("Returning the method for getting PPO and HMO adminMethodsysId and spsId");
			if (null != searchResultsAdminMethod) {
				return searchResultsAdminMethod.getSearchResults();
			} else
				return null;
		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in getAdminMethodSysId method in AdminMethodMappingAdapterManager",
					se);
		}
	}
	
	/**Method to Delete Admin Method Mapping
	 * @throws AdapterException
	 * 
	 */
	public void deleteAdminMethodMapping(
			AdminMethodMappingBO adminmethodMappingBO, User user)
			throws AdapterException {
		Logger
				.logInfo("AdminMethodMappingAdapterManager: Entering deleteAdminMethodMapping ");
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("adminMethodSysId", new Integer(adminmethodMappingBO
				.getAdminMethodSysId()));
		try {
			SearchCriteria searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(AdminMethodMappingBO.class
							.getName(), "deleteAdminMethodMapping", refValSet);
			searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger
					.logInfo("AdminMethodMappingAdapterManager: Return From deleteAdminMethodMapping ");
		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in locate deleteAdminMethodMapping method in AdminMethodMaintainAdapterManager",
					ad);
		}

	}
	
	/**
	 * @param searchCriteria
	 * @return
	 */
	public List retrieveQuesAnswer(String searchCriteriaString)
			throws AdapterException {
		// TODO Auto-generated method stub

		Logger
				.logInfo("AdminMethodMaintainAdapterManager - Retrieving Admin Method");
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("searchString", searchCriteriaString);

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				QuestionAnswerGroupBO.class.getName(),
				BusinessConstants.GET_QSTN_ANSWERS, refValSet);
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException ad) {

			throw new AdapterException(
					"Exception occured in locate retrieveAdminMethod method in AdminMethodMaintainAdapterManager",
					ad);
		}

		Logger.logInfo("Returning the method for locate Admin Method");
		List searchResultList = searchResults.getSearchResults();
		return searchResultList;

	}

	/**
	 * Method used to retreive all the possible answers for an admin method. It
	 * will be populated in a list of AdminMethodMappingBO.
	 * 
	 * @param adminMethodSysId
	 * @return
	 * @throws AdapterException
	 */
	public List getAnswerList(int adminMethodSysId) throws AdapterException {

		HashMap hashMap = new HashMap();

		hashMap.put("adminMethodSysId", new Integer(adminMethodSysId));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodMappingBO.class.getName(), "locatePossibleAnswers",
				hashMap);

		try {
			SearchResults searchResults = AdapterUtil
					.performSearch(searchCriteria);

			return searchResults.getSearchResults();

		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in locate getAnswerList method in AdminMethodViewAllAdapterManager",
					se);
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
		if (comaSeperatedAnswerList != null) {
			hashMap.put("possibleAnswer", comaSeperatedAnswerList);
		}
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(QuestionAnswerBO.class.getName(),
						"getQuestionAnswers", hashMap);

		try {
			SearchResults searchResults = AdapterUtil
					.performSearch(searchCriteria);

			return searchResults.getSearchResults();
		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in locate getQuestionAnswerList method in AdminMethodViewAllAdapterManager",
					se);
		}

	}

	/**
	 * @param adminMethodMappingBO
	 * @return
	 * @throws AdapterException
	 */
	public List viewAdminMethodMapping(AdminMethodMappingBO adminMethodMappingBO)
			throws AdapterException {
		Logger.logInfo("Entering the method for View Admin Method Mapping ");

		HashMap hashMap = new HashMap();
		SearchResults searchResults = null;
		List locateResultsList = null;

		if (adminMethodMappingBO.getAdminMethodSysId() == null
				|| "".equals(adminMethodMappingBO.getAdminMethodSysId())) {
			hashMap.put("adminMethodSysId", null);
		} else {
			hashMap.put("adminMethodSysId", new Integer(adminMethodMappingBO
					.getAdminMethodSysId()));
		}
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodMappingBO.class.getName(), "ViewAdminMethodMapping",
				hashMap);

		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);

			Logger
					.logInfo("Returning the method for View Admin Method Mapping");
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;
		} catch (SevereException se) {
			throw new AdapterException(
					"Exception occured in viewAdminMethodMapping method in AdminMethodMappingAdapterManager",
					se);
		}
	}

	private HashMap getItemMap() {
		// TODO Auto-generated method stub

		itemSet = new HashMap();

		HashMap criteria = new HashMap();

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodMappingBO.class.getName(), "getItemCodes", criteria,
				BusinessConstants.RESULT_SET_SIZE);
		SearchResults searchResults = null;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {

			e.printStackTrace();
		}

		List locateList = searchResults.getSearchResults();

		AdminMethodMappingBO bo;

		for (Iterator itr = locateList.iterator(); itr.hasNext();) {

			bo = (AdminMethodMappingBO) itr.next();
			itemSet.put(bo.getTermid(), bo.getTerm());

		}

		return itemSet;
	}

	private void populateDscrTerm(AdminMethodMappingBO bo) {

		String[] termid = bo.getTerm().split(",");
		StringBuffer termDesc = new StringBuffer();
		StringBuffer term = new StringBuffer();

		for (int i = 0; i < termid.length; i++) {
		//	term.append(termid[i]).append(termDesc.append(termSet.get(termid[i])).append('~'));
			
			term.append(termid[i]).append('~').append(termSet.get(termid[i])).append('~');

			

		}
		term.deleteCharAt(term.length() - 1);

		bo.setTerm(term.toString());
	}

	/**
	 * @param adminMethodSysId
	 * @return
	 */
	public List searchAdminMethodQuestionList(String adminMethodSysId) {
	
		HashMap criteria = new HashMap();

		criteria.put("adminMethodSysId",new Integer(Integer.parseInt(adminMethodSysId)));
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				AdminMethodViewAllFilterBO.class.getName(), "locatePossibleAnswers", criteria);
		SearchResults searchResults = null;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {

			e.printStackTrace();
		}
		
		
		return searchResults.getSearchResults();
	}

}