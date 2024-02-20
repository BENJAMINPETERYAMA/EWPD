/*
 * MandateAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.mandate.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.mandate.locateCriteria.MandateLocateCriteria;
import com.wellpoint.wpd.business.mandate.locateResult.MandateLocateResult;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;


/**
 * Adapter class for Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateAdapterManager {
	
	/**
	 * Default Constructor
	 *  
	 */
	public MandateAdapterManager() {
	}
	
	/**
	 * Method to insert a newly created Mandate to the database.
	 * 
	 * @param mandateBO MandateBO
	 * @param audit Audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean persistMandate(MandateBO mandateBO, Audit audit)
		throws SevereException {
		SequenceAdapterManager sequenceAdapterManager = null;
		sequenceAdapterManager = new SequenceAdapterManager();
		Logger.logInfo("Getting the next Mandate Sequence Number.");
		mandateBO.setMandateId(sequenceAdapterManager.getNextMandateSequence());
		mandateBO.setCreatedUser(audit.getUser());
		mandateBO.setCreatedTimestamp(audit.getTime());
		mandateBO.setLastUpdatedUser(audit.getUser());
		mandateBO.setLastUpdatedTimestamp(audit.getTime());
		//Method call to insert a mandate.
		AdapterUtil.performInsert(mandateBO, audit.getUser());
		return true;
	}
	
	/**
	 * Method to update the Mandate details in the database.
	 * 
	 * @param mandateBO MandateBO
	 * @param audit Audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean updateMandate(MandateBO mandateBO, Audit audit)
		throws SevereException {
		mandateBO.setLastUpdatedUser(audit.getUser());
		mandateBO.setLastUpdatedTimestamp(audit.getTime());
		Logger.logInfo("Updating mandate "+ mandateBO.getMandateName()+" to database.");
		AdapterUtil.performUpdate(mandateBO, audit.getUser());
		return true;
	}
	
	/**
	 * This method for retrieving the Mandate Details for the corresponding
	 * mandateId
	 * 
	 * @param mandateBO MandateBO
	 * @return mandateBO MandateBO
	 * @throws WPDException
	 */
	public Object retrieve(MandateBO mandateBO) throws WPDException {
		Logger.logInfo("Retrieveing mandate.");
		mandateBO = (MandateBO) AdapterUtil.performRetrieve(mandateBO);
		return mandateBO;
	}
	
	/**
	 * Method to retrieve the Mandate details from the database, using Mandate
	 * Name.
	 * 
	 * @param mandateBO MandateBO
	 * @return mandateBO MandateBO
	 * @throws SevereException
	 */
	public MandateBO retrieveByMandateName(MandateBO mandateBO)
		throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("mandateName", mandateBO.getMandateName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				mandateBO.getClass().getName(), "retrieveByMandateName",
				criteriaMap);
		Logger.logInfo("Searching for mandates.");
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults.getSearchResultCount() > 0) {
			return (MandateBO) searchResults.getSearchResults().get(0);
		}
		return null;
	}
	
	/**
	 * Retrieves the latest version by mandate name.
	 * 
	 * @param mandateBO MandateBO
	 * @return mandateBO MandateBO
	 * @throws SevereException
	 */
	public MandateBO retrieveLatestVersionByMandateName(MandateBO mandateBO)
		throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("mandateName", mandateBO.getMandateName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				mandateBO.getClass().getName(),
				"retrieveLatestVersionByMandateName", criteriaMap);
		Logger.logInfo("Retrieving the latest version of mandate specified.");
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults.getSearchResultCount() > 0) {
			return (MandateBO) searchResults.getSearchResults().get(0);
		}
		return null;
	}
	
	/**
	 * Method to check the mandate is in use
	 * 
	 * @param mandateBO MandateBO
	 * @param user User
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isMandateInUse(MandateBO mandateBO, User user)
		throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("mandateId", new Integer(mandateBO.getMandateId()));
		SearchCriteria searchCriteria = AdapterUtil
		.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO.class
				.getName(), "isMandateInUse", criteriaMap);
		Logger.logInfo("Searching whether the specified mandate is in use.");
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults.getSearchResultCount() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method to search the Mandate from the database, using the given criteria.
	 * 
	 * @param mandateLocateCriteria MandateLocateCriteria
	 * @param user User
	 * @return locateResults LocateResults
	 * @throws WPDException
	 */
	public LocateResults locateMandate(MandateLocateCriteria mandateLocateCriteria, User user)
		throws WPDException {
		
		HashMap criteriaMap = new HashMap();
		List locateResultsList = new ArrayList();
		LocateResults locateResults = new LocateResults();
		
		//Setting the values for each search criteria
		criteriaMap.put("citationNumber", mandateLocateCriteria
				.getCitationNumberList());
		criteriaMap.put("effectiveDate", WPDStringUtil
				.getStringDate(mandateLocateCriteria.getEffectiveDate()));
		criteriaMap.put("expiryDate", WPDStringUtil
				.getStringDate(mandateLocateCriteria.getExpiryDate()));
		if ("".equals(mandateLocateCriteria.getMandateTypeId())) {
			criteriaMap.put("mandateType", null);
		} else {
			criteriaMap.put("mandateType", mandateLocateCriteria
					.getMandateTypeId());
		}
		
		criteriaMap.put("jurisdiction", mandateLocateCriteria
				.getJurisdictionList());
		
		if ("".equals(mandateLocateCriteria.getGroupSizeId())) {
			criteriaMap.put("groupSize", null);
		} else {
			criteriaMap
			.put("groupSize", mandateLocateCriteria.getGroupSizeId());
		}
		
		if ("".equals(mandateLocateCriteria.getFundingArrangementId())) {
			criteriaMap.put("fundingArrangement", null);
		} else {
			criteriaMap.put("fundingArrangement", mandateLocateCriteria
					.getFundingArrangementId());
		}
		//Creating the SearchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.mandate.bo.MandateBO.class.getName(),
				"searchMandate", criteriaMap);
		searchCriteria.setMaxSearchResultSize(mandateLocateCriteria.getMaximumResultSize()+1);
		
		Logger.logInfo("Searching for mandate.");
		//Getting the search results
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		
		if (null != searchResults) {
			Iterator searchResultIterator = searchResults.getSearchResults()
			.iterator();
			while (searchResultIterator.hasNext()) {
				MandateBO mandateBO = (MandateBO) searchResultIterator.next();
				
				locateResultsList.add(mandateBO);
			}
			locateResults.setLocateResults(locateResultsList);
		}
		
		return locateResults;
	}
	
	/**
	 * Method to delete the Mandate from the database.
	 * 
	 * @param mandateBO MandateBO
	 * @param audit Audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean deleteMandate(MandateBO mandateBO, Audit audit)
		throws SevereException {
		Logger.logInfo("Deleting mandate "+mandateBO.getMandateId()+"");
		AdapterUtil.performRemove(mandateBO, audit.getUser());
		return true;
	}
	
	/**
	 * Method to locate mandate for copying
	 * 
	 * @param mandateLocateCriteria MandateLocateCriteria
	 * @param user User
	 * @return locateResults LocateResults
	 * @throws WPDException
	 */
	public LocateResults locateForCopy(MandateLocateCriteria mandateLocateCriteria, User user)
		throws WPDException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("mandateId", new Integer(mandateLocateCriteria
				.getMandateId()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.mandate.bo.MandateBO.class.getName(),
				"locateForCopy", criteriaMap);
		Logger.logInfo("Searching mandate for Copy");
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		List locateResultsList = new ArrayList();
		LocateResults locateResults = new LocateResults();
		if (null != searchResults) {
			Iterator searchResultIterator = searchResults.getSearchResults().iterator();
			while (searchResultIterator.hasNext()) {
				MandateBO mandateBO = (MandateBO) searchResultIterator.next();
				MandateLocateResult mandateLocateResult = new MandateLocateResult();
				
				mandateLocateResult.setMandateId(mandateBO.getMandateId());
				mandateLocateResult.setVersionNo(mandateBO.getVersion());
				mandateLocateResult.setCitationNumber(mandateBO
						.getCitationNumber());
				
				mandateLocateResult.setEffectiveDate(mandateBO
						.getEffectiveDate());
				mandateLocateResult.setExpiryDate(mandateBO.getExpiryDate());
				mandateLocateResult.setMandateTypeId(mandateBO
						.getMandateTypeId());
				mandateLocateResult.setMandateTypeDesc(mandateBO
						.getMandateTypeDesc());
				mandateLocateResult.setGroupSizeId(mandateBO.getGroupSizeId());
				mandateLocateResult.setGroupSizeDesc(mandateBO
						.getGroupSizeDesc());
				mandateLocateResult.setJurisdictionId(mandateBO
						.getJurisdictionId());
				mandateLocateResult.setJurisdictionDesc(mandateBO
						.getJurisdictionDesc());
				mandateLocateResult.setFundingArrangementId(mandateBO
						.getFundingArrangementId());
				mandateLocateResult.setFundingArrangementDesc(mandateBO
						.getFundingArrangementDesc());
				mandateLocateResult.setDescription(mandateBO.getDescription());
				mandateLocateResult.setMandateName(mandateBO.getMandateName());
				mandateLocateResult.setStatus(mandateBO.getStatus());
				locateResultsList.add(mandateLocateResult);
			}
			locateResults.setLocateResults(locateResultsList);
		}
		
		return locateResults;
	}
	
	/**
	 * This method locates a mandate based on the search criteria.
	 * @param mandateLocateCriteria MandateLocateCriteria
	 * @param user User
	 * @return locateResults LocateResults
	 * @throws SevereException
	 */
	public LocateResults viewAllMandate(
			MandateLocateCriteria mandateLocateCriteria, User user)
	throws SevereException {
		
		List locateResultsList = new ArrayList();
		LocateResults locateResults = new LocateResults();
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("mandateName", mandateLocateCriteria.getMandateName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.mandate.bo.MandateBO.class.getName(),
				"ViewAllVersions", criteriaMap);
		Logger.logInfo("Searching for all the versions of mandate "+mandateLocateCriteria.getMandateName());
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (null != searchResults) {
			Iterator searchResultIterator = searchResults.getSearchResults().iterator();
			while (searchResultIterator.hasNext()) {
				MandateBO mandateBO = (MandateBO) searchResultIterator.next();
				locateResultsList.add(mandateBO);
			}
			locateResults.setLocateResults(locateResultsList);
		}
		
		return locateResults;
	}
	
	/**
	 * Retrieves the latest version number corresponding to the
	 * name of the Mandate.
	 * 
	 * @param mandateBO MandateBO object.
	 * @return int latest version number
	 * @throws SevereException
	 */
	public int retrieveLatestVersionNumber(MandateBO mandateBO) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("mandateName", mandateBO.getMandateName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(mandateBO.getClass().getName(),"retrieveLatestVersionNumber", criteriaMap);
		Logger.logInfo("Retrieving the latest version of mandate ");
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults.getSearchResultCount() > 0) {
			mandateBO =  (MandateBO) searchResults.getSearchResults().get(0);
			return mandateBO.getVersion();
		}
		return -1;
	}
	
}