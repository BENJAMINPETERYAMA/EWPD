/*
 * NotesAttachmentAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.notes.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.lookup.locateCriteria.NotesLookUpLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.NotesAttachmentForBenefitLineLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitlevel.bo.NotesBOForBnftLine;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainAssnBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentDomainOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesBOForDefId;
import com.wellpoint.wpd.common.override.benefit.bo.TierNoteOverRide;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesAttachmentAdapterManager {

	/**
	 * Method to get all the notes available for attaching to the various
	 * entitites
	 * 
	 * @param locateCriteria
	 * @return List
	 * @throws SevereException
	 */
	public List locateNotesToAttach(NotesLookUpLocateCriteria locateCriteria)
			throws SevereException {

		HashMap criteriaMap = new HashMap();
		SearchCriteria searchCriteria = null;
		if (locateCriteria.getAction() == 6) {
			populateMapForBenefitLevelOverrideLookUp(locateCriteria,
					criteriaMap);
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					NotesAttachmentOverrideBO.class.getName(),
					BusinessConstants.getAssociatedEntityListForOverride,
					criteriaMap);
		} else if (locateCriteria.getAction() == 200) {
			criteriaMap.put("primaryEntityId", new Integer(locateCriteria
					.getAvailableEntityId()));
			criteriaMap.put("primaryEntityType", locateCriteria
					.getAvailableEntityType());
			criteriaMap.put("EntityIdsList", locateCriteria.getEntityIdList());
			criteriaMap.put("secondaryEntityType", locateCriteria
					.getEntityType());
			criteriaMap.put("benefitComponentId", new Integer(locateCriteria
					.getBenefitComponentId()));
			criteriaMap.put("benefitId", new Integer(locateCriteria
					.getBenefitId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					NotesAttachmentOverrideBO.class.getName(),
					BusinessConstants.GET_NOTES_FOR_DATAFEED, criteriaMap);
		} else if (locateCriteria.getAction() == 2) {
			populateMapForLookUp(locateCriteria, criteriaMap);
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(NoteBO.class
					.getName(), "lookUpQuery", criteriaMap);
		} else if (locateCriteria.getAction() == 3) {
			populateMapForOverrideLookUp(locateCriteria, criteriaMap);
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					NotesAttachmentOverrideBO.class.getName(),
					"overrideLookUpQuery", criteriaMap);
		} else if (locateCriteria.getAction() == 4) {
			populateMapForBenefitLevelOverrideLookUp(locateCriteria,
					criteriaMap);
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					NotesAttachmentOverrideBO.class.getName(),
					"overrideBenefitLevelLookUpQuery", criteriaMap);
		} else if (locateCriteria.getAction() == 5) {
			// use only entity id and entity type
			populateMapForLookUp(locateCriteria, criteriaMap);
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					AttachedNotesBO.class.getName(),
					"overrideBenefitLevelLookUpQueryForDomainAssociation",
					criteriaMap);
		} else if (locateCriteria.getAction() == 555) {
			criteriaMap.put("primaryEntityId", new Integer(locateCriteria
					.getAvailableEntityId()));
			criteriaMap.put("primaryEntityType", "ATTACHCONTRACT");
			criteriaMap.put("EntityIdsList", locateCriteria.getEntityIdList());
			criteriaMap.put("secondaryEntityType", "ATTACHADMNQUEST");
			criteriaMap.put("entityType", "CONTRACT");
			searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(
							NotesAttachmentOverrideBO.class.getName(),
							BusinessConstants.getAssociatedContractAdminOptionNotesDatafeed,
							criteriaMap);
		} else if (locateCriteria.getAction() == 666) {
			criteriaMap.put("primaryEntityId", new Integer(locateCriteria
					.getAvailableEntityId()));
			criteriaMap.put("primaryEntityType", "ATTACHCONTRACT");
			criteriaMap.put("secondaryEntityList", locateCriteria
					.getSecondaryEntityIdList());
			criteriaMap.put("secondaryEntityType", "ATTACHQUESTION");
			criteriaMap.put("benefitComponentId", new Integer(locateCriteria
					.getBenefitComponentId()));
			criteriaMap.put("EntityIdsList", locateCriteria.getEntityIdList());
			criteriaMap.put("benefitId", new Integer(locateCriteria
					.getBenefitId()));
			searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(
							NotesAttachmentOverrideBO.class.getName(),
							BusinessConstants.getAssociatedBenefitAdminOptionNotesDatafeed,
							criteriaMap);
		} else if (locateCriteria.getAction() == 101) {
			populateMapForBenefitLevelOverrideLookUp(locateCriteria,
					criteriaMap);
			criteriaMap.put("tierSysId", new Integer(locateCriteria
					.getTierSysId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					NotesAttachmentOverrideBO.class.getName(),
					"benefitLevelTierNotes", criteriaMap);
		} else if (locateCriteria.getAction() == 102) {
			populateMapForBenefitLevelOverrideLookUp(locateCriteria,
					criteriaMap);
			criteriaMap.put("tierSysId", new Integer(locateCriteria
					.getTierSysId()));
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					NotesAttachmentOverrideBO.class.getName(),
					"benefitLevelTierNotesForView", criteriaMap);
		}

		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		List locateResults = (List) searchResults.getSearchResults();
		return locateResults;
	}

	public List lookupContractNotes(int dateSegmentSysId, String noteCriteria)
			throws SevereException {
		HashMap criteriaMap = new HashMap();
		String noteCriteriaSearch = "%" + noteCriteria.trim() + "%";
		criteriaMap.put("entityId", new Integer(dateSegmentSysId));
		criteriaMap.put("noteName", noteCriteriaSearch);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NoteBO.class.getName(), "lookUpQueryForContract", criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		if (searchResults == null || searchResults.getSearchResultCount() == 0) {
			criteriaMap.put("noteName", "%%");
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(NoteBO.class
					.getName(), "lookUpQueryForContract", criteriaMap);
			searchResults = AdapterUtil.performSearch(searchCriteria);
		}
		return searchResults.getSearchResults();
	}

	/**
	 * @param locateCriteria
	 * @param criteriaMap
	 */
	private void populateMapForOverrideLookUp(
			NotesLookUpLocateCriteria locateCriteria, HashMap criteriaMap) {
		criteriaMap.put("primaryEntityId", new Integer(locateCriteria
				.getAvailableEntityId()));
		criteriaMap.put("primaryEntityType", locateCriteria
				.getAvailableEntityType());
		criteriaMap.put("secondaryEntityId", new Integer(locateCriteria
				.getEntityId()));
		criteriaMap.put("secondaryEntityType", locateCriteria.getEntityType());
		criteriaMap.put("entityId", new Integer(locateCriteria.getEntityId()));
		criteriaMap.put("entityType", locateCriteria.getEntityType());
		criteriaMap.put("benefitComponentId", new Integer(locateCriteria
				.getBenefitComponentId()));
	}

	/**
	 * @param locateCriteria
	 * @param criteriaMap
	 */
	private void populateMapForBenefitLevelOverrideLookUp(
			NotesLookUpLocateCriteria locateCriteria, HashMap criteriaMap) {
		criteriaMap.put("primaryEntityId", new Integer(locateCriteria
				.getAvailableEntityId()));
		criteriaMap.put("primaryEntityType", locateCriteria
				.getAvailableEntityType());
		criteriaMap.put("secondaryEntityId", new Integer(locateCriteria
				.getEntityId()));
		criteriaMap.put("secondaryEntityType", locateCriteria.getEntityType());
		criteriaMap.put("benefitComponentId", new Integer(locateCriteria
				.getBenefitComponentId()));
	}

	public List locateUndomainedNotesForContract(AttachedNotesBO attachedNotesBO)
			throws SevereException {
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		SearchResults searchResults = null;
		searchCriteria.setBusinessObjectName(BusinessConstants.ENTITY_BO);
		searchCriteria.setMaxSearchResultSize(50);
		searchCriteria.setSearchQueryName("getUndomainedNotes");
		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		// Create the referenceValueSet hashmap
		HashMap refValSet = new HashMap();
		//Get the values entityId and EntityType and Set them to the refValSet
		refValSet.put("noteId", attachedNotesBO.getNoteId());
		refValSet.put("version", new Integer(attachedNotesBO.getVersion()));
		// Set the refValSet to the searchCriteria
		searchCriteria.setReferenceValueSet(refValSet);

		try {
			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
			logAdapterExceptionForSearch(
					searchCriteria.getBusinessObjectName(), searchCriteria
							.getSearchQueryName(), adapterException);
		}
		//Set the value of locateResultList to the locateResult
		return searchResults.getSearchResults();
	}

	/**
	 * @param locateCriteria
	 * @param criteriaMap
	 */
	private void populateMapForLookUp(NotesLookUpLocateCriteria locateCriteria,
			HashMap criteriaMap) {
		criteriaMap.put("entityId", new Integer(locateCriteria.getEntityId()));
		criteriaMap.put("availEntityType", locateCriteria
				.getAvailableEntityType());
		criteriaMap.put("systemDomain", new Integer(locateCriteria
				.getAvailableEntityId()));
		criteriaMap.put("entityType", locateCriteria.getEntityType());
		if (null != locateCriteria.getNoteName())
			criteriaMap.put("noteName", "%" + locateCriteria.getNoteName()
					+ "%");
		else
			criteriaMap.put("noteName", "%");
	}

	/**
	 * To attach note for an entity.
	 * 
	 * @param attachedNotesBO
	 * @param audit
	 * @param insertFlag
	 * @param serviceAccess
	 * @return
	 * @throws SevereException
	 */
	//FIXME since the method returning only same value so make the return type
	// as void
	public boolean attachNotesForEntity(AttachedNotesBO attachedNotesBO,
			Audit audit, boolean insertFlag, AdapterServicesAccess serviceAccess)
			throws AdapterException {
		// get the btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		// set type as create
		btc.setBusinessTransactionType(WebConstants.CREATE_STRUCTURE);
		btc.setBusinessTransactionUser(audit.getUser());
		// Set values to the BO
		attachedNotesBO.setCreatedUser(audit.getUser());
		attachedNotesBO.setLastUpdatedUser(audit.getUser());
		attachedNotesBO.setLastUpdatedTimestamp(audit.getTime());
		attachedNotesBO.setCreatedTimestamp(audit.getTime());
		try {
			// Call the insert method of the adapter
			AdapterUtil.performInsert(attachedNotesBO, audit.getUser(),
					serviceAccess);
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again. do not throw AdapterException
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in attachNotesForEntity method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}

	/**
	 * To attachNotes for OverrrideEntity.
	 * 
	 * @param attachedNotesBO
	 * @param audit
	 * @param insertFlag
	 * @param serviceAccess
	 * @return
	 * @throws SevereException
	 */
	//FIXME since the method returning only same value so make the return type
	// as void
	public boolean attachNotesForOverrideEntity(
			NotesAttachmentOverrideBO overridebo, Audit audit,
			boolean insertFlag, AdapterServicesAccess serviceAccess)
			throws AdapterException {
		// get the btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		// set type as create
		btc.setBusinessTransactionType(WebConstants.UPDATE_STRUCTURE);
		btc.setBusinessTransactionUser(audit.getUser());
		// Set values to the BO

		overridebo.setLastUpdatedUser(audit.getUser());
		overridebo.setLastUpdatedTimestamp(audit.getTime());
		try {
			// Call the insert method of the adapter
			if (insertFlag) {
				overridebo.setCreatedUser(audit.getUser());
				overridebo.setCreatedTimestamp(audit.getTime());
				AdapterUtil.performInsert(overridebo, audit.getUser(),
						serviceAccess);
			} else {
				AdapterUtil.performUpdate(overridebo, audit.getUser(),
						serviceAccess);
			}
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in attachNotesForOverrideEntity method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}
	
	public boolean attachNotesForOverrideEntityForTier(
			TierNoteOverRide tierNoteOverRide, Audit audit,
			boolean insertFlag, AdapterServicesAccess serviceAccess)
			throws AdapterException {
		// get the btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		// set type as create
		btc.setBusinessTransactionType(WebConstants.UPDATE_STRUCTURE);
		btc.setBusinessTransactionUser(audit.getUser());
		// Set values to the BO

		tierNoteOverRide.setLastUpdatedUser(audit.getUser());
		tierNoteOverRide.setLastUpdatedTimestamp(audit.getTime());
		try {
			// Call the insert method of the adapter
			if (insertFlag) {
				tierNoteOverRide.setCreatedUser(audit.getUser());
				tierNoteOverRide.setCreatedTimestamp(audit.getTime());
				AdapterUtil.performInsert(tierNoteOverRide, audit.getUser(),
						serviceAccess);
			} else {
				AdapterUtil.performUpdate(tierNoteOverRide, audit.getUser(),
						serviceAccess);
			}
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in attachNotesForOverrideEntity method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}

	/**
	 * To retrieve the attached notes of an entity.
	 * 
	 * @param entityId
	 * @param entityType
	 * @return
	 * @throws ServiceException
	 */
	public LocateResults locateAttachedNotes(int entityId, String entityType,
			String benefitDefinitionKey) throws ServiceException,
			AdapterException {
		try {
			return locateAttachedNotes(entityId, entityType, Integer.MAX_VALUE,
					benefitDefinitionKey);
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locateAttachedNotes entityId method in NotesAttachmentAdapterManager",
					ex);
		}
	}

	/**
	 * To retrieve the attached notes of an entity.
	 * 
	 * @param entityId
	 * @param entityType
	 * @param maxResultSize
	 * @return
	 * @throws ServiceException
	 */
	public LocateResults locateAttachedNotes(int entityId, String entityType,
			int maxResultSize, String benefitDefinitionKey)
			throws ServiceException {

		// create list
		//        List locateResultsList = new ArrayList();
		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		// Set parameters to the BO
		searchCriteria.setBusinessObjectName(BusinessConstants.ENTITY_BO);
		searchCriteria.setMaxSearchResultSize(maxResultSize);
		if (null != benefitDefinitionKey)
			searchCriteria
					.setSearchQueryName(BusinessConstants.getAssociatedEntityList);
		else
			searchCriteria
					.setSearchQueryName(BusinessConstants.getAssociatedEntityListNull);

		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		// Create the referenceValueSet hashmap
		HashMap refValSet = new HashMap();
		//Get the values entityId and EntityType and Set them to the refValSet
		refValSet.put(BusinessConstants.ENTITY_ID, new Integer(entityId));
		refValSet.put(BusinessConstants.ENTITY_TYPE, entityType);
		refValSet.put(BusinessConstants.BNFT_DEFN_KEY, benefitDefinitionKey);
		//change ends
		// Set the refValSet to the searchCriteria
		searchCriteria.setReferenceValueSet(refValSet);

		try {
			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
			logAdapterExceptionForSearch(
					searchCriteria.getBusinessObjectName(), searchCriteria
							.getSearchQueryName(), adapterException);
		}
		//Set the value of locateResultList to the locateResult
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults
				.setTotalResultsCount(searchResults.getSearchResultCount());
		return locateResults;
	}

	/**
	 * To retrieve the attached notes,benefit id for overidding while creating a
	 * product.
	 * 
	 * @param primary
	 *            entityid
	 * @param primary
	 *            entityType
	 * @param benefitcomponentid
	 * @param maxResultSize
	 * @return
	 * @throws ServiceException
	 */
	public LocateResults locateAttachedNotesForOverride(int entityId,
			String entityType,String secEntityType, int bcId)
			throws ServiceException {

		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		// Set parameters to the BO
		searchCriteria
				.setBusinessObjectName(BusinessConstants.ENTITY_OVERRIDE_BO);
		searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
		searchCriteria.setSearchQueryName(BusinessConstants.getAssociatedBenefitNoteListForOverride);
		
		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		// Create the referenceValueSet hashmap
		HashMap refValSet = new HashMap();
		//Get the values entityId and EntityType and Set them to the refValSet
		refValSet.put(BusinessConstants.PRI_ENTITY_ID, new Integer(entityId));
		refValSet.put(BusinessConstants.PRI_ENTITY_TYPE, entityType);
		refValSet.put(BusinessConstants.SEC_ENTITY_TYPE, secEntityType);
		refValSet.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(bcId));
		// Set the refValSet to the searchCriteria
		searchCriteria.setReferenceValueSet(refValSet);

		try {
			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
			logAdapterExceptionForSearch(
					searchCriteria.getBusinessObjectName(), searchCriteria
							.getSearchQueryName(), adapterException);
		}
		//Set the value of locateResultList to the locateResult
		locateResults.setLocateResults(searchResults.getSearchResults());//FIXME
		// db
		// call
		// use
		// local
		// list
		locateResults
				.setTotalResultsCount(searchResults.getSearchResultCount());//FIXME
		// db
		// call
		// use
		// local
		// list
		// return locateResults
		return locateResults;
	}

	/**
	 * To retrieve the attached notes of an entity.
	 * 
	 * @param entityId
	 * @param entityType
	 * @param maxResultSize
	 * @return
	 * @throws ServiceException
	 */
	public LocateResults locateAttachedNotesForOverride(int entityId,
			String entityType, int secEntityId, String secEntityType, int bcId)
			throws AdapterException, SevereException {

		// create list
		//        List locateResultsList = new ArrayList();
		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		try {
			// Set parameters to the BO
			searchCriteria
					.setBusinessObjectName(BusinessConstants.ENTITY_OVERRIDE_BO);
			searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
			
			searchCriteria.setSearchQueryName(BusinessConstants.getAssociatedBenefitNoteListForOverride);
			
			searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
			// Create the referenceValueSet hashmap
			HashMap refValSet = new HashMap();
			//Get the values entityId and EntityType and Set them to the
			// refValSet
			refValSet.put(BusinessConstants.PRI_ENTITY_ID,
					new Integer(entityId));
			refValSet.put(BusinessConstants.PRI_ENTITY_TYPE, entityType);
			refValSet.put(BusinessConstants.SEC_ENTITY_ID, new Integer(
					secEntityId));
			refValSet.put(BusinessConstants.SEC_ENTITY_TYPE, secEntityType);
			refValSet
					.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(bcId));
			// Set the refValSet to the searchCriteria
			searchCriteria.setReferenceValueSet(refValSet);

			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);

			//Set the value of locateResultList to the locateResult
			locateResults.setLocateResults(searchResults.getSearchResults());
			locateResults.setTotalResultsCount(searchResults
					.getSearchResultCount());
			// return locateResults
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locate BenefitComponentLocateCriteria method in NotesAttachmentAdapterManager",
					ex);
		}
		return locateResults;
	}

	/**
	 * To retrieve the attached notes of an entity.
	 * 
	 * @param entityId
	 * @param entityType
	 * @param maxResultSize
	 * @return
	 * @throws ServiceException
	 */
	public LocateResults locateAttachedNotesForOverride(int entityId,
			String entityType, int secEntityId, String secEntityType, int bcId, int tierSysId)
			throws AdapterException, SevereException {

		// create list
		//        List locateResultsList = new ArrayList();
		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		try {
			// Set parameters to the BO
			searchCriteria
					.setBusinessObjectName(BusinessConstants.ENTITY_OVERRIDE_BO);
			searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
			if(0!=tierSysId){
				searchCriteria.setSearchQueryName("getAssociatedEntityListForTier");
			}else{
				searchCriteria.setSearchQueryName(BusinessConstants.getAssociatedBenefitNoteListForOverride);
			}
			searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
			// Create the referenceValueSet hashmap
			HashMap refValSet = new HashMap();
			//Get the values entityId and EntityType and Set them to the
			// refValSet
			refValSet.put(BusinessConstants.PRI_ENTITY_ID,
					new Integer(entityId));
			refValSet.put(BusinessConstants.PRI_ENTITY_TYPE, entityType);
			refValSet.put(BusinessConstants.SEC_ENTITY_ID, new Integer(
					secEntityId));
			refValSet.put(BusinessConstants.SEC_ENTITY_TYPE, secEntityType);
			refValSet
					.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(bcId));
			if(0!=tierSysId){
				refValSet.put("tierSysId", new Integer(tierSysId));
			}
			// Set the refValSet to the searchCriteria
			searchCriteria.setReferenceValueSet(refValSet);

			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);

			//Set the value of locateResultList to the locateResult
			locateResults.setLocateResults(searchResults.getSearchResults());
			locateResults.setTotalResultsCount(searchResults
					.getSearchResultCount());
			// return locateResults
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locate BenefitComponentLocateCriteria method in NotesAttachmentAdapterManager",
					ex);
		}
		return locateResults;
	}
	/**
	 * To detach the already attched note from an entity.
	 * 
	 * @param subObject
	 * @param audit
	 * @return
	 * @throws ServiceException
	 */
	public boolean deleteNotesForEntity(AttachedNotesBO subObject, Audit audit)
			throws ServiceException, AdapterException {
		// Create a btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		// Set type as delete
		btc.setBusinessTransactionType("delete");
		btc.setBusinessTransactionUser(audit.getUser());
		try {
			//if BenefitDefinitionKey is null
			if (null == subObject.getBenefitDefinitionKey()) {
				NotesBOForDefId notesBOForDefId = new NotesBOForDefId();
				notesBOForDefId.setNoteId(subObject.getNoteId());
				notesBOForDefId.setEntityType(subObject.getEntityType());
				notesBOForDefId.setEntityId(subObject.getEntityId());
				notesBOForDefId.setBenefitDefinitionKey(subObject
						.getBenefitDefinitionKey());
				AdapterUtil.getAdapterService().removeObject(notesBOForDefId,
						notesBOForDefId.getClass().getName(), btc);
			} else {
				//  	 connect to the adapter and call the delete method
				AdapterUtil.getAdapterService().removeObject(subObject,
						subObject.getClass().getName(), btc);
			}

		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in deleteNotesForEntity method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}

	public boolean deleteNotesForEntity(NoteBO subObject, Audit audit)
			throws ServiceException {
		// Create a btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		// Set type as delete
		btc.setBusinessTransactionType("delete");
		btc.setBusinessTransactionUser(audit.getUser());
		try {
			// connect to the adapter and call the delete method
			this.getAdapterService().removeObject(subObject,
					subObject.getClass().getName(), btc);
		} catch (AdapterException adapterException) {
			logAdapterException(subObject, adapterException);
		}
		return true;
	}

	/**
	 * Method to delete the notes list for overriding benefit level notes list
	 * 
	 * @param overrideBO
	 * @param audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean delete(NotesAttachmentOverrideBO overrideBO,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		try {

			AdapterUtil.performRemove(overrideBO, BusinessConstants.TESTUSER,
					adapterServicesAccess);

		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in delete method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}

	/**
	 * To detach the notes from an already overridden entity.
	 * 
	 * @param subObject
	 * @param audit
	 * @param serviceAccess
	 * @return
	 * @throws ServiceException
	 */
	public boolean deleteNotesForOverriddenEntity(
			NotesAttachmentOverrideBO subObject, Audit audit)
			throws ServiceException, AdapterException {
		// Create a btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		try {
			// Set type as delete
			btc.setBusinessTransactionType("delete");
			btc.setBusinessTransactionUser(audit.getUser());

			// connect to the adapter and call the delete method
			this.getAdapterService().removeObject(subObject,
					subObject.getClass().getName(), btc);
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in deleteNotesForOverriddenEntity method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}

	/**
	 * To delte the notes associated to a benefitcomponent and benefit while
	 * deleting a benefitcomponent associated to a product.
	 * 
	 * @param subObject
	 * @param audit
	 * @return
	 * @throws ServiceException
	 */
	public boolean deleteComponentNotesOverridden(
			NotesAttachmentDomainOverrideBO subObject, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws ServiceException, AdapterException {
		// Create a btc object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		// Set type as delete
		btc.setBusinessTransactionType("delete");
		btc.setBusinessTransactionUser(audit.getUser());
		try {
			// connect to the adapter and call the delete method
			adapterServicesAccess.removeObject(subObject, subObject.getClass()
					.getName(), btc);
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in deleteComponentNotesOverridden method in NotesAttachmentAdapterManager",
					ex);
		}

		return true;
	}

	/**
	 * @param businessObjectName
	 * @param queryName
	 * @param adapterException
	 * @throws ServiceException
	 */
	private void logAdapterExceptionForSearch(String businessObjectName,
			String queryName, AdapterException adapterException)
			throws ServiceException {
		List errorParams = new ArrayList(3);
		errorParams.add(businessObjectName);
		errorParams.add(queryName);
		throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
				errorParams, adapterException);
	}

	/**
	 * @param operation
	 * @param userId
	 * @return
	 */
	private BusinessTransactionContext getTransactionContext(String operation,
			String userId) {
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		btc.setBusinessTransactionType(operation);
		btc.setBusinessTransactionUser("testUser");
		return btc;
	}

	/**
	 * @param obj
	 * @param adapterException
	 * @throws ServiceException
	 */
	private void logAdapterException(Object obj,
			AdapterException adapterException) throws ServiceException {
		List errorParams = new ArrayList(3);
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION,
				errorParams, adapterException);

	}

	/**
	 * @return
	 */
	private AdapterServicesAccess getAdapterService() {
		AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
				.getAdapterServicesAccess("ewpd");
		return adapterServicesAccess;
	}


	/**
	 * @param locateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateNotesListForBnftLine(
			LocateCriteria locateCriteria, User user) throws SevereException {
		// create the object for the locate results
		LocateResults locateResults = new LocateResults();
		NotesAttachmentForBenefitLineLocateCriteria attachmentForBenefitLineLocateCriteria = (NotesAttachmentForBenefitLineLocateCriteria) locateCriteria;
		// create the reference of the SearchResults
		SearchResults searchResults = null;
		// create the object for the search criteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// set the required things in the search criteria
		searchCriteria
				.setBusinessObjectName(NotesBOForBnftLine.class.getName());
		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		// create the referenceValueSet Hashmap
		HashMap refValSet = new HashMap();
		if (null != attachmentForBenefitLineLocateCriteria
				.getNoteFilterString())
			refValSet.put("noteFilterString", "%"
					+ attachmentForBenefitLineLocateCriteria
							.getNoteFilterString().toUpperCase() + "%");
		else
			refValSet.put("noteFilterString", "%");
		// get the required values from the locateCriterial and set it in the
		// hash map
		if (null != attachmentForBenefitLineLocateCriteria.getBenefitLineId()
				&& !attachmentForBenefitLineLocateCriteria.getBenefitLineId()
						.equals("")) {
			refValSet.put("bnftLineId", attachmentForBenefitLineLocateCriteria
					.getBenefitLineId()
					+ "");
		} else {
			refValSet.put("bnftLineId", null);
		}
		refValSet.put("bnftDefnId",
				new Integer(attachmentForBenefitLineLocateCriteria
						.getBenefitDefinitionId()));

		if (null != attachmentForBenefitLineLocateCriteria
				.getBenefitQualifierCode()
				&& !attachmentForBenefitLineLocateCriteria
						.getBenefitQualifierCode().isEmpty()) {
			refValSet.put("bnftQualifierCode",
					attachmentForBenefitLineLocateCriteria
							.getBenefitQualifierCode());
			searchCriteria
					.setSearchQueryName("searchNotesListForBnftLineWithQualifier");
		} else {
			refValSet.put("bnftQualifierCode", null);
			searchCriteria.setSearchQueryName("searchNotesListForBnftLine");
		}
		if (null != attachmentForBenefitLineLocateCriteria.getBenefitTermCode()
				&& !attachmentForBenefitLineLocateCriteria.getBenefitTermCode()
						.isEmpty()) {
			refValSet
					.put("bnftTermCode", attachmentForBenefitLineLocateCriteria
							.getBenefitTermCode());
			refValSet.put("noOfTerms", new Integer(
					attachmentForBenefitLineLocateCriteria.getBenefitTermCode()
							.size()));
		} else {
			refValSet.put("bnftTermCode", null);
		}
		searchCriteria.setReferenceValueSet(refValSet);
		searchCriteria.setMaxSearchResultSize(10000);
		try {
			// connect to the adapter
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
			List errorParams = new ArrayList(3);
			errorParams.add(searchCriteria.getBusinessObjectName());
			errorParams.add(searchCriteria.getSearchQueryName());
			throw new ServiceException("Adapter Exception occured",
					errorParams, adapterException);
		}
		// set the locateResultList to the locateResults
		locateResults.setLocateResults(searchResults.getSearchResults());
		// return locate results
		return locateResults;
	}

	/*
	 * to delete the All Note Domain Assn ( NOTE_DOMN_ASSN )
	 *  
	 *///FIXME since the method returning only same value so make the return
	// type as void
	public boolean deleteAllNoteDomainAssnInfo(
			NoteDomainAssnBO noteDomainAssnBO, Audit audit,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		try {
			AdapterUtil.performRemove(noteDomainAssnBO, audit.getUser(),
					adapterServicesAccess);
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in delete method in NotesAttachmentAdapterManager",
					ex);
		}
		return true;
	}

	/**
	 * @param primaryEntityId
	 * @param primaryEntityType
	 * @param secondaryEntityId
	 * @param secondaryEntityType
	 * @param benefitComponentId
	 * @return
	 * @throws AdapterException
	 */
	public LocateResults locateAttachedNotesForOverrideForDatafeed(
			int primaryEntityId, String primaryEntityType,
			String secondaryEntityType, List entityIdsList,
			int benefitComponentId) throws AdapterException {
		//		 create list
		//        List locateResultsList = new ArrayList();
		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		try {
			// Set parameters to the BO
			searchCriteria
					.setBusinessObjectName(BusinessConstants.ENTITY_OVERRIDE_BO);
			searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
			searchCriteria
					.setSearchQueryName(BusinessConstants.GET_NOTES_FOR_DATAFEED);
			searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
			// Create the referenceValueSet hashmap
			HashMap refValSet = new HashMap();
			//Get the values entityId and EntityType and Set them to the
			// refValSet
			refValSet.put(BusinessConstants.PRI_ENTITY_ID, new Integer(
					primaryEntityId));
			refValSet.put(BusinessConstants.PRI_ENTITY_TYPE, primaryEntityType);
			refValSet.put(BusinessConstants.SEC_ENTITY_TYPE,
					secondaryEntityType);
			refValSet.put(BusinessConstants.ENTITY_ID_LIST, entityIdsList);
			refValSet.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(
					benefitComponentId));
			// Set the refValSet to the searchCriteria
			searchCriteria.setReferenceValueSet(refValSet);

			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);

			//Set the value of locateResultList to the locateResult
			locateResults.setLocateResults(searchResults.getSearchResults());
			locateResults.setTotalResultsCount(searchResults
					.getSearchResultCount());
			// return locateResults
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locate BenefitComponentLocateCriteria method in NotesAttachmentAdapterManager",
					ex);
		}
		return locateResults;
	}

	/**
	 * @param primaryEntityId
	 * @param primaryEntityType
	 * @param secondaryEntityId
	 * @param secondaryEntityType
	 * @param benefitComponentId
	 * @return
	 * @throws AdapterException
	 */
	public LocateResults locateAttachedBenefitNotesForOverrideForDatafeed(
			int primaryEntityId, String primaryEntityType,
			String secondaryEntityType, List entityIdsList,
			int benefitComponentId) throws AdapterException {
		//		 create list
		//        List locateResultsList = new ArrayList();
		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		try {
			// Set parameters to the BO
			searchCriteria
					.setBusinessObjectName(BusinessConstants.ENTITY_OVERRIDE_BO);
			searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
			searchCriteria
					.setSearchQueryName(BusinessConstants.GET_ASSOCIATED_BENEFIT_NOTES_DATAFEED);
			searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
			// Create the referenceValueSet hashmap
			HashMap refValSet = new HashMap();
			//Get the values entityId and EntityType and Set them to the
			// refValSet
			refValSet.put(BusinessConstants.PRI_ENTITY_ID, new Integer(
					primaryEntityId));
			refValSet.put(BusinessConstants.PRI_ENTITY_TYPE, primaryEntityType);
			refValSet.put(BusinessConstants.SEC_ENTITY_TYPE,
					secondaryEntityType);
			refValSet.put(BusinessConstants.ENTITY_ID_LIST, entityIdsList);
			refValSet.put(BusinessConstants.BEN_COMPONENT_ID, new Integer(
					benefitComponentId));
			// Set the refValSet to the searchCriteria
			searchCriteria.setReferenceValueSet(refValSet);

			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);

			//Set the value of locateResultList to the locateResult
			locateResults.setLocateResults(searchResults.getSearchResults());
			locateResults.setTotalResultsCount(searchResults
					.getSearchResultCount());
			// return locateResults
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locate BenefitComponentLocateCriteria method in NotesAttachmentAdapterManager",
					ex);
		}
		return locateResults;
	}

	/**
	 * @param primaryEntityId
	 * @param primaryEntityType
	 * @param secondaryEntityType
	 * @param entityIdsList
	 * @return
	 * @throws AdapterException
	 */
	public LocateResults locateAttachedBCNotesForOverrideForDatafeed(
			int primaryEntityId, String primaryEntityType,
			String secondaryEntityType, List entityIdsList)
			throws AdapterException {
		//		 create list
		//        List locateResultsList = new ArrayList();
		// create an object for searchCriteria
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		// create an object for locateResults
		LocateResults locateResults = new LocateResults();
		//Create a reference for searchResults
		SearchResults searchResults = null;
		try {
			// Set parameters to the BO
			searchCriteria
					.setBusinessObjectName(BusinessConstants.ENTITY_OVERRIDE_BO);
			searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
			searchCriteria
					.setSearchQueryName(BusinessConstants.GET_BC_NOTES_FOR_DATAFEED);
			searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
			// Create the referenceValueSet hashmap
			HashMap refValSet = new HashMap();
			//Get the values entityId and EntityType and Set them to the
			// refValSet
			refValSet.put(BusinessConstants.PRI_ENTITY_ID, new Integer(
					primaryEntityId));
			refValSet.put(BusinessConstants.PRI_ENTITY_TYPE, primaryEntityType);
			refValSet.put(BusinessConstants.SEC_ENTITY_TYPE,
					secondaryEntityType);
			refValSet.put(BusinessConstants.ENTITY_ID_LIST, entityIdsList);
			// Set the refValSet to the searchCriteria
			searchCriteria.setReferenceValueSet(refValSet);

			// Connect to the adapter and call the search method
			searchResults = getAdapterService().searchObject(searchCriteria);

			//Set the value of locateResultList to the locateResult
			locateResults.setLocateResults(searchResults.getSearchResults());
			locateResults.setTotalResultsCount(searchResults
					.getSearchResultCount());
			// return locateResults
		} catch (Exception ex) {//FIXME catch SevereException and thows it
			// again.
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new AdapterException(
					"Exception occured in locate BenefitComponentLocateCriteria method in NotesAttachmentAdapterManager",
					ex);
		}
		return locateResults;
	}

}