/*
 * QuestionAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.question.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.question.locateCriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.question.bo.FunctionalDomainBO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.util.TimeHandler;

/**
 * Adapter Class for Questions.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionAdapterManager {

	/**
	 * Constructor
	 */
	public QuestionAdapterManager() {
	}

	/**
	 * Method to insert question
	 * 
	 * @param questionBO
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persistQuestion(QuestionBO questionBO, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {

		questionBO.setQuestionNumber(new SequenceAdapterManager()
				.getNextQuestionNumberSequence());
		if (questionBO.getVersion() <= 1) {
			questionBO.setQuestionNumberParentSysId(questionBO
					.getQuestionNumber());
		}
		questionBO.setCreatedUser(audit.getUser());
		questionBO.setCreatedTimestamp(audit.getTime());
		questionBO.setLastUpdatedUser(audit.getUser());
		questionBO.setLastUpdatedTimestamp(audit.getTime());
		try {

			//Inserting the question details to master table
			AdapterUtil.performInsert(questionBO, audit.getUser(),
					adapterServicesAccess);
			
			//Inserting the answers for the question to possible answer table
			List possibleAnswerList = questionBO.getAnswerList();			
			if (null != possibleAnswerList) {
				Iterator answerListIterator = possibleAnswerList.iterator();
				while (answerListIterator.hasNext()) {
					PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) answerListIterator
							.next();
					possibleAnswerBO.setQuestionNumber(questionBO
							.getQuestionNumber());
					this.persistPossibleAnswer(possibleAnswerBO, audit,
							adapterServicesAccess);
				}
			}
			
			//Insertin the functional domains to the question func domain assn table
			List functionalDomainList = questionBO.getFunctionalDomainList();
			if (null != functionalDomainList) {
				Iterator domainListIterator = functionalDomainList.iterator();

				while (domainListIterator.hasNext()) {
					FunctionalDomainBO domainBO = (FunctionalDomainBO) domainListIterator
							.next();
					domainBO.setQuestionNumber(questionBO.getQuestionNumber());
					this.persistFunctionalDomain(domainBO, audit,
							adapterServicesAccess);
				}
			}

		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		return true;
	}

	/**
	 * Method to update question
	 * 
	 * @param questionBO
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateQuestion(QuestionBO questionBO, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {

		questionBO.setLastUpdatedUser(audit.getUser());
		questionBO.setLastUpdatedTimestamp(audit.getTime());
		try {

			AdapterUtil.performUpdate(questionBO, audit.getUser(),
					adapterServicesAccess);
			List searchAnswerList = this.getPossibleAnswer(questionBO,
					adapterServicesAccess);
			List possibleAnswerList = questionBO.getAnswerList();
			Iterator possibleAnsListIterator, searchAnsListIterator;
			PossibleAnswerBO possibleAnswerBO1, possibleAnswerBO2;
			boolean existInDB;
			if (possibleAnswerList != null && possibleAnswerList.size() > 0) {
				for (possibleAnsListIterator = possibleAnswerList.iterator(); possibleAnsListIterator
						.hasNext();) {
					possibleAnswerBO1 = (PossibleAnswerBO) possibleAnsListIterator
							.next();
					existInDB = false;

					if (searchAnswerList != null && searchAnswerList.size() > 0) {
						for (searchAnsListIterator = searchAnswerList
								.iterator(); searchAnsListIterator.hasNext();) {
							possibleAnswerBO2 = (PossibleAnswerBO) searchAnsListIterator
									.next();
							if (possibleAnswerBO1.getPossibleAnswerDesc()
									.equals(
											possibleAnswerBO2
													.getPossibleAnswerDesc())) {
								existInDB = true;
								searchAnsListIterator.remove();
								break;
							}
						}
					}
					if (!existInDB) {
						possibleAnswerBO1.setQuestionNumber(questionBO
								.getQuestionNumber());
						this.persistPossibleAnswer(possibleAnswerBO1, audit,
								adapterServicesAccess);
					}
				}
			}
			if (searchAnswerList != null && searchAnswerList.size() > 0) {
				for (searchAnsListIterator = searchAnswerList.iterator(); searchAnsListIterator
						.hasNext();) {
					possibleAnswerBO2 = (PossibleAnswerBO) searchAnsListIterator
							.next();
					this.removePossibleAnswer(possibleAnswerBO2, audit
							.getUser(), adapterServicesAccess);
				}
			}
			
			// removing the function domain and inserting functional domain
			FunctionalDomainBO functionalDomainBO = new FunctionalDomainBO();
			functionalDomainBO
					.setQuestionNumber(questionBO.getQuestionNumber());
			//used a value dummy since the functional domain code being key attribute should not be null.
			functionalDomainBO.setFunctionalDomainCD("DUMMY");
			this.removeFunctionalDomain(functionalDomainBO, audit.getUser(),
					adapterServicesAccess);
			
			
			List domainList = questionBO.getFunctionalDomainList();
			if (null != domainList) {
				Iterator domainListIterator = domainList.iterator();

				while (domainListIterator.hasNext()) {
					FunctionalDomainBO domainBO = (FunctionalDomainBO) domainListIterator
							.next();
					domainBO.setQuestionNumber(questionBO.getQuestionNumber());
					this.persistFunctionalDomain(domainBO, audit,
							adapterServicesAccess);
				}
			}

		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		return true;
	}

	/**
	 * Method to get all the possible answers
	 * 
	 * @param questionBO
	 * @param adapterServicesAccess
	 * @return List
	 * @throws SevereException
	 */
	public List getPossibleAnswer(QuestionBO questionBO,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {
		
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("questionNumber", new Integer(questionBO
				.getQuestionNumber()));
		SearchResults searchResult = null;
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.question.bo.PossibleAnswerBO.class
						.getName(), "possibleAnswerSearch", referenceValueSet);
		searchResult = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess);
		if (null != searchResult) {		
		    List resultList = searchResult.getSearchResults();
		    if(null!=resultList && resultList.size()>0){
			Collections.sort(resultList);
		    }
		    return resultList;
		}
		return null;
	}
	
	/**
	 * Method to get all the possible answers
	 * 
	 * @param questionBO
	 * @param adapterServicesAccess
	 * @return List
	 * @throws SevereException
	 */
	public List getAllPossibleAnswer(int adminOptSysId,
			AdapterServicesAccess adapterServicesAccess) throws SevereException {
		
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("adminOptSysId", new Integer(adminOptSysId));
		SearchResults searchResult = null;
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.question.bo.PossibleAnswerBO.class
						.getName(), "allPossibleAnswerSearch", referenceValueSet);
		searchResult = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess);
		if (null != searchResult) {		
		    List resultList = searchResult.getSearchResults();
		    
		    return resultList;
		}
		return null;
	}

	/**
	 * method to get all the functioanl domain corresponding to question number
	 * 
	 * @param questionBO
	 * @return List
	 * @throws SevereException
	 */
	public List getFunctionalDomain(QuestionBO questionBO)
			throws SevereException {
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("questionNumber", new Integer(questionBO
				.getQuestionNumber()));
		SearchResults searchResult = null;
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.FunctionalDomainBO.class
								.getName(), "functionalDomainSearch",
						referenceValueSet);
		searchResult = AdapterUtil.performSearch(searchCriteria);
		if (null != searchResult) {
			return searchResult.getSearchResults();
		}
		return null;
	}

	/**
	 * Method to remove the possible answers
	 * 
	 * @param possibleAnswerBO
	 * @param user
	 * @param adapterServicesAccess
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean removePossibleAnswer(PossibleAnswerBO possibleAnswerBO,
			String user, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		AdapterUtil
				.performRemove(possibleAnswerBO, user, adapterServicesAccess);
		return true;
	}

	/**
	 * Method to remove the functioanl domain
	 * 
	 * @param domainBO
	 * @param user
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean removeFunctionalDomain(FunctionalDomainBO domainBO,
			String user, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		AdapterUtil.performRemove(domainBO, user, adapterServicesAccess);
		return true;
	}

	/**
	 * Method to insert the possible answers
	 * 
	 * @param possibleAnswerBO
	 * @param audit
	 * @param adapterServicesAccess
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persistPossibleAnswer(PossibleAnswerBO possibleAnswerBO,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		possibleAnswerBO.setPossibleAnswerId(new SequenceAdapterManager()
				.getNextAnswerIdSequence());
		possibleAnswerBO.setCreatedUser(audit.getUser());
		possibleAnswerBO.setCreatedTimestamp(audit.getTime());
		possibleAnswerBO.setLastUpdatedUser(audit.getUser());
		possibleAnswerBO.setLastUpdatedTimestamp(audit.getTime());
		AdapterUtil.performInsert(possibleAnswerBO, audit.getUser(),
				adapterServicesAccess);
		return true;
	}

	/**
	 * Method to insert functional domain
	 * 
	 * @param domainBO
	 * @param audit
	 * @param adapterServicesAccess
	 * @return
	 * @throws SevereException
	 */
	public boolean persistFunctionalDomain(FunctionalDomainBO domainBO,
			Audit audit, AdapterServicesAccess adapterServicesAccess)
			throws SevereException {
		domainBO.setCreatedUser(audit.getUser());
		domainBO.setCreatedTimestamp(audit.getTime());
		domainBO.setLastUpdatedUser(audit.getUser());
		domainBO.setLastUpdatedTimestamp(audit.getTime());
		AdapterUtil.performInsert(domainBO, audit.getUser(),
				adapterServicesAccess);
		return true;
	}

	/**
	 * Method to copy question associated information
	 * 
	 * @param srcBO
	 * @param trgtBO
	 * @param audit
	 * @return boolean
	 */
	public boolean copy(QuestionBO srcBO, QuestionBO trgtBO, Audit audit) {
		return true;
	}

	/**
	 * Method to get the possible answers
	 * 
	 * @param questionBO
	 * @param user
	 * @return List
	 * @throws SevereException
	 */
	public List locate(QuestionBO questionBO, User user) throws SevereException {
		//List searchResult = new ArrayList();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		List searchResult = this.getPossibleAnswer(questionBO,
				adapterServicesAccess);
		return searchResult;
	}

	/**
	 * Method to search the question
	 * 
	 * @param questionLocateCriteria
	 * @return LocateResults
	 * @throws SevereException
	 */
	public LocateResults searchQuestion(
			QuestionLocateCriteria questionLocateCriteria)
			throws SevereException {
		SearchResults questionSearchResults = null;
		LocateResults locateResults = new LocateResults();
		HashMap referenceValueSet = new HashMap();

		String questionDesc = questionLocateCriteria.getQuestionDesc();
		if (null != questionDesc) {
			questionDesc = "%" + questionDesc.toUpperCase() + "%";
		} else
			questionDesc = "%";

		referenceValueSet.put("dataTypeId", questionLocateCriteria
				.getDataTypeList());
		referenceValueSet.put("questionDesc", questionDesc);
		referenceValueSet.put("functionalDomain", questionLocateCriteria
				.getFunctionalDomainList());
		referenceValueSet.put("spsReference", questionLocateCriteria
				.getSpsReference());

		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "questionSearch",
						referenceValueSet);

		questionSearchResults = AdapterUtil.performSearch(searchCriteria);

		List questionList = new ArrayList();
		if (null != questionSearchResults) {

			Iterator iter = questionSearchResults.getSearchResults().iterator();
			// getting the functioanl domains correspondng to each question
			while (iter.hasNext()) {
				QuestionBO questionBO = (QuestionBO) iter.next();
				List funcDomnList = getFunctionalDomain(questionBO);
				if (funcDomnList != null && !funcDomnList.isEmpty()) {
					questionBO.setFunctionalDomainList(funcDomnList);
				}
				questionList.add(questionBO);
			}
			if (questionList != null) {    
				locateResults.setLocateResults(questionList);
				locateResults.setTotalResultsCount(questionList.size());
			}
		}
		
		return locateResults;
	}

	/**
	 * Method to retieve the question
	 * 
	 * @param questionBO
	 * @return QuestionBO
	 * @throws SevereException
	 */
	public QuestionBO retrieve(QuestionBO questionBO) throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		questionBO = (QuestionBO) AdapterUtil.performRetrieve(questionBO,
				adapterServicesAccess);
		if(null!=questionBO.getSpsReference()&& !"".equals(questionBO.getSpsReference()))
			questionBO.setSpsReference(questionBO.getSpsReference()+"~"+getSPSDesc(questionBO.getQuestionNumber()));
		
		questionBO.setTerm(this.getDesription(questionBO.getTerm(),"selectTerm"));
		questionBO.setQualifier(this.getDesription(questionBO.getQualifier(),"selectQualifier"));
		
		questionBO.setBenefitLineDataType(this.getDesription(questionBO.getBenefitLineDataType(), "selectDatatype"));
		
		
		List searchResult = this.getPossibleAnswer(questionBO,
				adapterServicesAccess);
		questionBO.setAnswerList(searchResult);
		List functioanlDomainList = this.getFunctionalDomain(questionBO);
		questionBO.setFunctionalDomainList(functioanlDomainList);
		return questionBO;
	}
	private String getSPSDesc(int questionNumber) {

		HashMap referenceValueSet = new HashMap();

		referenceValueSet.put("questionNumber", new Integer(questionNumber));
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "retrieveSPSDesc",
						referenceValueSet);

		SearchResults questionSearchResults = null;

		try {
			questionSearchResults = AdapterUtil.performSearch(searchCriteria);
			
			QuestionBO questionBO = (QuestionBO) questionSearchResults
					.getSearchResults().get(0);
			return questionBO.getDescription();
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method to retrieve the question details by question description.
	 * 
	 * @param questionBO
	 * @return QuestionBO
	 * @throws SevereException
	 */
	public QuestionBO retrieveByQuestionDesc(QuestionBO questionBO)
			throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		//List questionSearchResult = new ArrayList();
		//List possibleAnswerSearchResult = new ArrayList();
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("questionDesc", questionBO.getQuestionDesc());
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "retrieveQuestionDesc", criteriaMap);
		List questionSearchResult = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess).getSearchResults();
		if (null != questionSearchResult && questionSearchResult.size() > 0) {
			questionBO = (QuestionBO) questionSearchResult.get(0);
			List possibleAnswerSearchResult = this.getPossibleAnswer(
					questionBO, adapterServicesAccess);
			questionBO.setAnswerList(possibleAnswerSearchResult);
			List functionalDomainList = this.getFunctionalDomain(questionBO);
			questionBO.setFunctionalDomainList(functionalDomainList);
			return questionBO;
		}
		return null;
	}

	/**
	 * Method to retrieve the latest version.
	 * 
	 * @param questionBO
	 * @return QuestionBO
	 * @throws SevereException
	 */
	public QuestionBO retrieveLatestVersion(QuestionBO questionBO)
			throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		//List questionSearchResult = new ArrayList();
		// List possibleAnswerSearchResult = new ArrayList();
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("questionDesc", questionBO.getQuestionDesc());
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "latestVersion", criteriaMap);
		List questionSearchResult = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess).getSearchResults();
		if (null != questionSearchResult && questionSearchResult.size() > 0) {
			questionBO = (QuestionBO) questionSearchResult.get(0);
			
			List possibleAnswerSearchResult = this.getPossibleAnswer(
					questionBO, adapterServicesAccess);
			questionBO.setAnswerList(possibleAnswerSearchResult);
			
			List functionalDomainSearchResult = this
					.getFunctionalDomain(questionBO);
			questionBO.setFunctionalDomainList(functionalDomainSearchResult);
			
			return questionBO;
		}
		return null;
	}

	/**
	 * 
	 * Method to retrieve latset version number of Question
	 * 
	 * @param questionBO
	 * @return int
	 * @throws SevereException
	 */
	public int retrieveLatestVersionNumber(QuestionBO questionBO)
			throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		//List questionSearchResult = new ArrayList();
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("questionDesc", questionBO.getQuestionDesc());
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "latestVersionNumber", criteriaMap);
		List questionSearchResult = AdapterUtil.performSearch(searchCriteria,
				adapterServicesAccess).getSearchResults();
		if (null != questionSearchResult && questionSearchResult.size() > 0) {
			questionBO = (QuestionBO) questionSearchResult.get(0);
			return questionBO.getVersion();
		}
		return -1;

	}

	/**
	 * Method to check the question is used
	 * 
	 * @param questionBO
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isQuestionInUse(QuestionBO questionBO)
			throws SevereException {
		HashMap criteriaMap = new HashMap();
		//List questionSearchResult = new ArrayList();
		criteriaMap.put("questionNumber", new Integer(questionBO
				.getQuestionNumber()));
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "isQuestionInUse", criteriaMap);
		List questionSearchResult = AdapterUtil.performSearch(searchCriteria)
				.getSearchResults();
		if (null != questionSearchResult && questionSearchResult.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check the question is valid
	 * 
	 * @param questionBO
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isValidQuestion(QuestionBO questionBO)
			throws SevereException {
		//List searchResult = new ArrayList();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		List searchResult = this.getPossibleAnswer(questionBO,
				adapterServicesAccess);
		if (null != searchResult && searchResult.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method to delete the question
	 * 
	 * @param questionBO,user
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteQuestion(QuestionBO questionBO, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {

		try {

			boolean possibleAnswerFlag = true;
			boolean functionalDomainFlag = true;
			List possibleAnswerList = this.getPossibleAnswer(questionBO,
					adapterServicesAccess);
			List functionalDomainList = this.getFunctionalDomain(questionBO);
			if (null != possibleAnswerList && !possibleAnswerList.isEmpty()) {
				Iterator answerListIterator = possibleAnswerList.iterator();
				while (answerListIterator.hasNext()) {
					PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) answerListIterator
							.next();
					possibleAnswerFlag = this.removePossibleAnswer(
							possibleAnswerBO, audit.getUser(),
							adapterServicesAccess);
				}
			}
			
			// removing the function domain and inserting functional domain
			FunctionalDomainBO functionalDomainBO = new FunctionalDomainBO();
			functionalDomainBO
					.setQuestionNumber(questionBO.getQuestionNumber());
			//used a value dummy since the functional domain code being key attribute should not be null.
			functionalDomainBO.setFunctionalDomainCD("DUMMY");
			this.removeFunctionalDomain(functionalDomainBO, audit.getUser(),
					adapterServicesAccess);
			if (possibleAnswerFlag && functionalDomainFlag) {
				AdapterUtil.performRemove(questionBO, audit.getUser(),
						adapterServicesAccess);
			}
		} catch (Exception ex) {
			throw new AdapterException(null, ex);
		}
		return true;
	}

	/**
	 * * Method to retrieve a question
	 * 
	 * @param questionLocateCriteria
	 * @return LocateResults
	 */
	public LocateResults view(QuestionLocateCriteria questionLocateCriteria)
			throws SevereException {
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		SearchResults questionSearchResults = null;
		LocateResults locateResults = new LocateResults();
		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("questionNumber", new Integer(
				(questionLocateCriteria.getQuestionNumber())));
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "view", referenceValueSet);
		questionSearchResults = AdapterUtil.performSearch(searchCriteria);
		List locateResultsList = new ArrayList(questionSearchResults
				.getSearchResults().size());
		if (null != locateResultsList) {
			Iterator searchResultIterator = questionSearchResults
					.getSearchResults().iterator();
			while (searchResultIterator.hasNext()) {
				QuestionBO questionBO = (QuestionBO) searchResultIterator
						.next();
				
				questionBO.setTerm(getDesriptionForView(questionBO.getTerm(),"selectTerm"));
				questionBO.setQualifier(getDesriptionForView(questionBO.getQualifier(),"selectQualifier"));
				questionBO.setBenefitLineDataType(getDesriptionForView(questionBO.getBenefitLineDataType(), "selectDatatype"));
				
				List possibleAnswerSearchResult = this.getPossibleAnswer(
						questionBO, adapterServicesAccess);
				questionBO.setAnswerList(possibleAnswerSearchResult);
				List functionalDomainList = this
						.getFunctionalDomain(questionBO);
				questionBO.setFunctionalDomainList(functionalDomainList);
				locateResultsList.add(questionBO);
			}
		}
		locateResults.setLocateResults(locateResultsList);
		return locateResults;
	}
	
	private String getDesription(String catalogId, String queryName) {

		if(null==catalogId || "".equals(catalogId))
			return "";
		List catalogList = new ArrayList();
		String[] catalogid = catalogId.split(",");

		int i = 1;
		while (i <= catalogid.length) {

			catalogList.add(catalogid[i - 1]);
			i++;
		}
		HashMap referenceValueSet = new HashMap();

		referenceValueSet.put("catalogId", catalogList);
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), queryName, referenceValueSet);

		SearchResults questionSearchResults = null;
		try {
			questionSearchResults = AdapterUtil.performSearch(searchCriteria);

			i = 0;
			String catalog = "";
			for (Iterator iter = questionSearchResults.getSearchResults()
					.iterator(); iter.hasNext();) {
				QuestionBO questionBO = (QuestionBO) iter.next();
			
				if(i!=0)
					catalog=catalog+"~";
				if(queryName.equalsIgnoreCase("selectDatatype")){
					catalog = catalog + questionBO.getDescription() + "~"
					+ catalogid[i];
				}else{
					catalog = catalog + catalogid[i] + "~"
					+ questionBO.getDescription();
				}
					i++;
				
			}
			return catalog;
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private String getDesriptionForView(String catalogId, String queryName) {
		
		if(null==catalogId || "".equals(catalogId))
			return "";

		List catalogList = new ArrayList();
		String[] catalogid = catalogId.split(",");

		int i = 1;
		while (i <= catalogid.length) {

			catalogList.add(catalogid[i - 1]);
			i++;
		}
		HashMap referenceValueSet = new HashMap();

		referenceValueSet.put("catalogId", catalogList);
		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), queryName, referenceValueSet);

		SearchResults questionSearchResults = null;
		try {
			questionSearchResults = AdapterUtil.performSearch(searchCriteria);

			
			String catalog = "";
			for (Iterator iter = questionSearchResults.getSearchResults()
					.iterator(); iter.hasNext();) {
				QuestionBO questionBO = (QuestionBO) iter.next();
							
				catalog = catalog+questionBO.getDescription();
				if(iter.hasNext())
					catalog+=",";
			}
			
			return catalog;
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Method to retrieve all versions of the question
	 * 
	 * @param questionLocateCriteria
	 * @return LocateResults
	 * @throws SevereException
	 */
	public LocateResults viewAllVersions(
			QuestionLocateCriteria questionLocateCriteria)
			throws SevereException {

		SearchResults questionSearchResults = null;
		LocateResults locateResults = new LocateResults();

		HashMap referenceValueSet = new HashMap();
		referenceValueSet.put("questionDesc", (questionLocateCriteria
				.getQuestionDesc()));

		SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(
						com.wellpoint.wpd.common.question.bo.QuestionBO.class
								.getName(), "allVersions", referenceValueSet);

		questionSearchResults = AdapterUtil.performSearch(searchCriteria);
		List questionList = new ArrayList(questionSearchResults
				.getSearchResultCount());
		if (null != questionSearchResults) {

			Iterator iter = questionSearchResults.getSearchResults().iterator();
			// getting the functional domains corresponding to each question
			while (iter.hasNext()) {
				QuestionBO questionBO = (QuestionBO) iter.next();
				List funcDomnList = getFunctionalDomain(questionBO);
				if (funcDomnList != null && !funcDomnList.isEmpty()) {    
					questionBO.setFunctionalDomainList(funcDomnList);
				}
				questionList.add(questionBO);
			}
			if (questionList != null && !questionList.isEmpty()) {    
				locateResults.setLocateResults(questionList);
				locateResults.setTotalResultsCount(questionList.size());
			}
		}

		return locateResults;
	}
}