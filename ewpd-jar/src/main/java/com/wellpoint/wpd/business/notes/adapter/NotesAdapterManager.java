/*
 * Created on May 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.notes.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.wellpoint.wpd.business.notes.locatecriteria.NotesLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.ContractProductAONotesAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainDeleteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainResult;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesBOForView;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesAdapterManager {
	

	/**
	 * Method to insert notes to the database.
	 * 
	 * @param noteBO
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void persistNotes(NoteBO noteBO, Audit audit) throws SevereException {
		
		//Method call to insert a note.
		AdapterUtil.performInsert(noteBO, audit.getUser());
	}
	
	/**
	 * 
	 * @param noteBO
	 * @param user
	 * @throws SevereException
	 */
	public void updateNotes(NoteBO noteBO, String user)throws SevereException {
		AdapterUtil.performUpdate(noteBO, user);
	}
	/**
	 * 
	 * @param noteBO
	 * @param user
	 * @param adapterServicesAccess
	 * @throws SevereException
	 * @throws AdapterException
	 */
	public void updateNotes(NoteBO noteBO, String user, AdapterServicesAccess adapterServicesAccess)throws SevereException, AdapterException {
		AdapterUtil.performUpdate(noteBO, user, adapterServicesAccess);
	}
	
	/**
	 * 
	 * @param noteDomainBO
	 * @param user
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void persistDomainValuesForNotes(NoteDomainBO noteDomainBO, String user, AdapterServicesAccess adapterServicesAccess) throws SevereException {		
		AdapterUtil.performInsert(noteDomainBO, user, adapterServicesAccess);
	}
	
	/**
	 * 
	 * @param noteDomainBO
	 * @param user
	 * @param adapterServicesAccess
	 * @throws SevereException
	 */
	public void deleteSystemDomainsForNotes(NoteDomainBO noteDomainBO , String user, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		AdapterUtil.performRemove(noteDomainBO, user, adapterServicesAccess);
	}
	
	/**
	 * This method for retrieving the Notes for the corresponding note id
	 * 
	 * @param noteBO
	 * @return
	 * @throws WPDException
	 */
	public Object retrieve(NoteBO noteBO) throws SevereException {
		Logger.logInfo("Retrieveing notes.");
		noteBO = (NoteBO) AdapterUtil.performRetrieve(noteBO);
		return this.retrieveNotesSystemDomain(noteBO);
		
	}
	/**
	 * 
	 * @param noteID
	 * @return
	 * @throws SevereException
	 */
	public boolean isNotesInActivelyUsedStatus(String noteID)throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("noteName", noteID);//FIXME remove all hardcoded value
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NoteBO.class.getName(), 
				"forActivelyUsedStatus",
				criteriaMap);
		
		if(0 != AdapterUtil.performSearch(searchCriteria).getSearchResultCount())
			return true;
				
		return false;
	}
	
	
	/**
	 * @param noteBO
	 * @throws SevereException
	 */
	private NoteBO retrieveNotesSystemDomain(NoteBO noteBO) throws SevereException {
		NoteDomainBO noteSystemDomainBO = new NoteDomainBO();
		noteSystemDomainBO.setNoteId(noteBO.getNoteId());
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("noteId", noteBO.getNoteId());//FIXME remove all hardcoded value
		criteriaMap.put("version", new Integer(noteBO.getVersion()));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				noteSystemDomainBO.getClass().getName(), BusinessConstants.RETRIEVE_BY_NOTEID,
				criteriaMap);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		noteBO.setSystemDomain(searchResults.getSearchResults());
		return noteBO;
		
	}
	
	/**
	 * @param noteBO
	 * @throws SevereException
	 */
	public NoteBO retrieveByNoteNameForCompare(NoteBO noteBO)throws SevereException {
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("noteName", noteBO.getNoteName());
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					noteBO.getClass().getName(), BusinessConstants.RETRIEVE_LATEST_VERSION_COMPARE,
					criteriaMap);
			List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
			if (null != searchResultList && !searchResultList.isEmpty()) {
				 return this.retrieveNotesSystemDomain((NoteBO)searchResultList.get(0));
			}
		return null;
	}


	/**
	 * This method for retrieving the product Details 
	 * 
	 * 
	 * @param notesLocateCriteria NotesLocateCriteria
	 * @return locateResults LocateResults
	 * @throws SevereException
	 */
	// ************* modified for CAR ****************
	public LocateResults locateProdcuts(NotesLocateCriteria notesLocateCriteria, User user) throws SevereException {
		HashMap criteriaMap = new HashMap();
		String productDesc=notesLocateCriteria.getNoteName();
		if(null != productDesc){
		    productDesc = productDesc.replaceAll("_", "`_");
		}
		else{
		    productDesc = "%";
		}
		criteriaMap.put("productName",productDesc);
		criteriaMap.put("lob",notesLocateCriteria.getLobList());
		criteriaMap.put("be",notesLocateCriteria.getBusinessEntityList());
		criteriaMap.put("bg",notesLocateCriteria.getBusinessGroupList());
		criteriaMap.put("mbu", notesLocateCriteria.getMarketBusinessUnitList());
		criteriaMap.put("noteId",notesLocateCriteria.getNoteId());
				
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.product.bo.ProductBO.class.getName(),
				"locateProducts", criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		LocateResults locateResults = null;
		if (null != searchResultList && !searchResultList.isEmpty()) {
			List locateResultsList = new ArrayList(searchResultList.size()); 
			locateResults = new LocateResults();
			Iterator searchResultIterator = searchResultList.iterator();
			while (searchResultIterator.hasNext()) {
				ProductBO productBO = (ProductBO) searchResultIterator.next();
				NoteDomainResult result = new NoteDomainResult();
				result.setParentId(productBO.getParentProductKey());
				result.setName(productBO.getProductName());
				locateResultsList.add(result);
			}
			locateResults.setLocateResults(locateResultsList);
		}
		return locateResults;
	}

	/**
	 * @param notesLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	// ************* modified for CAR ****************
	public LocateResults locateBenefits(NotesLocateCriteria notesLocateCriteria, User user) throws SevereException {
		HashMap criteriaMap = new HashMap();
		String benefitDesc=notesLocateCriteria.getNoteName();
		if(null != benefitDesc){
		    benefitDesc = benefitDesc.replaceAll("_", "`_");
		}
		else{
		    benefitDesc = "%";
		}
		criteriaMap.put("benefitIdentifier",benefitDesc);
		criteriaMap.put("lobCodeList",notesLocateCriteria.getLobList());
		criteriaMap.put("businessEntityCodeList",notesLocateCriteria.getBusinessEntityList());
		criteriaMap.put("businessGroupCodeList",notesLocateCriteria.getBusinessGroupList());
		criteriaMap.put("marketBusinessUnitList",notesLocateCriteria.getMarketBusinessUnitList());
		criteriaMap.put("noteId",notesLocateCriteria.getNoteId());
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO.class.getName(),
				BusinessConstants.LOCATE_BENEFITS, criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		LocateResults locateResults = null;
		if (null != searchResultList && !searchResultList.isEmpty()) {
			List locateResultsList = new ArrayList(searchResultList.size()); 
			locateResults = new LocateResults();
			Iterator searchResultIterator = searchResultList.iterator();
			while (searchResultIterator.hasNext()) {
				StandardBenefitBO standardBenefitBO = (StandardBenefitBO) searchResultIterator.next();
				NoteDomainResult result = new NoteDomainResult();
				result.setParentId(standardBenefitBO.getStandardBenefitKey());
				result.setName(standardBenefitBO.getBenefitIdentifier());
				locateResultsList.add(result);
			}
			locateResults.setLocateResults(locateResultsList);
		}		
		return locateResults;
	}

	/**
	 * @param notesLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	// ************* modified for CAR ****************
	public LocateResults locateComponents(NotesLocateCriteria notesLocateCriteria, User user) throws SevereException {
		HashMap criteriaMap = new HashMap();
		String componentDesc=notesLocateCriteria.getNoteName();
		if(null != componentDesc){
		    componentDesc = componentDesc.replaceAll("_", "`_");
		}
		else{
		    componentDesc = "%";
		}
		criteriaMap.put("benefitComponentName",componentDesc);
		criteriaMap.put("lobCodeList",notesLocateCriteria.getLobList());
		criteriaMap.put("businessEntityCodeList",notesLocateCriteria.getBusinessEntityList());
		criteriaMap.put("businessGroupCodeList",notesLocateCriteria.getBusinessGroupList());
		criteriaMap.put("marketBusinessUnitList",notesLocateCriteria.getMarketBusinessUnitList());
		criteriaMap.put("noteId",notesLocateCriteria.getNoteId());
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO.class.getName(),
				BusinessConstants.LOCATE_COMPONENTS, criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		LocateResults locateResults = null;
		if (null != searchResultList && !searchResultList.isEmpty()) {
			List locateResultsList = new ArrayList(searchResultList.size()); 
			locateResults = new LocateResults();
			Iterator searchResultIterator = searchResultList.iterator();
			while (searchResultIterator.hasNext()) {
				BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultIterator.next();
				NoteDomainResult result = new NoteDomainResult();
				result.setParentId(benefitComponentBO.getBenefitComponentId());
				result.setName(benefitComponentBO.getName());
				locateResultsList.add(result);
			}
			locateResults.setLocateResults(locateResultsList);
		}		
		return locateResults;
	}
	
	/**
	 * The method will fetch all the Questions from the Question Master table
	 * @param notesLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateQuestions(NotesLocateCriteria notesLocateCriteria, User user) throws SevereException {
		HashMap criteriaMap = new HashMap();
		String questionDesc=notesLocateCriteria.getNoteName();
		if(null != questionDesc){
		    questionDesc = questionDesc.replaceAll("_", "`_");
		}
		else{
		    questionDesc = "%";
		}
		criteriaMap.put("questionDesc",questionDesc.toUpperCase());		
		criteriaMap.put("noteId",notesLocateCriteria.getNoteId());
		
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.question.bo.QuestionBO.class.getName(),
				BusinessConstants.LOCATE_QUESTIONS, criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		LocateResults locateResults = null;
		if (null != searchResultList && !searchResultList.isEmpty()) {
			List locateResultsList = new ArrayList(searchResultList.size()); 
			locateResults = new LocateResults();
			Iterator searchResultIterator = searchResultList.iterator();
			while (searchResultIterator.hasNext()) {
			    QuestionBO questionBO = (QuestionBO) searchResultIterator.next();
				NoteDomainResult result = new NoteDomainResult();
				result.setId(questionBO.getQuestionNumber());
				result.setName(questionBO.getQuestionDesc());
				locateResultsList.add(result);
			}
			locateResults.setLocateResults(locateResultsList);
		}			
		return locateResults;
	}

	/**
	 * This method retrieves the latest version by NoteName.
	 * 
	 * @param noteBO
	 * @return
	 * @throws SevereException
	 */
	
	public BusinessObject retrieveLatestVersionByNoteName(NoteBO noteBO) throws SevereException {
		
		if(noteBO.getNotesAction().equals(BusinessConstants.NOTES_ATTACHMENT)){
			noteBO = (NoteBO)this.retrieve(noteBO);
			return noteBO;
		}else{
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("noteName", noteBO.getNoteName());
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					noteBO.getClass().getName(), BusinessConstants.RETRIEVE_LATEST_VERSION,
					criteriaMap);
			List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults(); 
			if (null != searchResultList && !searchResultList.isEmpty()) {
				return (NoteBO) searchResultList.get(0);
			}
		}
		return null;
	}
	
	/**
	 * This method retrieves the latest version by NoteName.
	 * 
	 * @param noteBO
	 * @return
	 * @throws SevereException
	 */
	public BusinessObject retrieveLatestVersionNumber(NoteBO noteBO) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("noteName", noteBO.getNoteName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				noteBO.getClass().getName(), BusinessConstants.RETRIEVE_BY_NUMBER,
				criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults(); 
		if (null != searchResultList && !searchResultList.isEmpty()) {
			return (NoteBO) searchResultList.get(0);
		}
		return null;
	}
	
	/**
	 * Method to retrieve the Note details from the database, using Note
	 * Name.
	 * 
	 * @param noteBO NoteBO
	 * @return noteBO NoteBO
	 * @throws SevereException
	 */
	public NoteBO retrieveByNoteName(NoteBO noteBO)
		throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("noteName", noteBO.getNoteName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				noteBO.getClass().getName(), BusinessConstants.RETRIEVE_BY_NOTE_NAME,
				criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults(); 
		if (null != searchResultList && !searchResultList.isEmpty()) {
			return (NoteBO) searchResultList.get(0);
		}
		return null;
	}

	
	/**
	 * Method to retrieve the Note details from the database, using Note
	 * Name.
	 * 
	 * @param noteBO NoteBO
	 * @return noteBO NoteBO
	 * @throws SevereException
	 */
	public NoteBO retrieveByNoteNameForEdit(NoteBO noteBO)
		throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("noteName", noteBO.getNoteName());
		criteriaMap.put("noteParentId", noteBO.getNoteParentId());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				noteBO.getClass().getName(), BusinessConstants.RETRIEVE_BY_NOTE_EDIT,
				criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults(); 
		if (null != searchResultList && !searchResultList.isEmpty()) {
			return (NoteBO) searchResultList.get(0);
		}
		return null;
	}

	
	/**
	 * Method to search the Notes from the database, using the given criteria.
	 * 
	 * @param notesLocateCriteria NotesLocateCriteria
	 * @param user User
	 * @return locateResults LocateResults
	 * @throws SevereException
	 */
	public LocateResults locateNotes(NotesLocateCriteria notesLocateCriteria, User user)throws SevereException {
		
		HashMap criteriaMap = new HashMap();
		
		//Setting the values for each search criteria
		criteriaMap.put("newNoteID", (null == notesLocateCriteria.getNoteId() || notesLocateCriteria.getNoteId().equals(""))? 
				null : notesLocateCriteria.getNoteId().toString());
		criteriaMap.put("noteName", "".equals(notesLocateCriteria.getNoteName())? 
				null : notesLocateCriteria.getNoteName());
		criteriaMap.put("noteType", notesLocateCriteria.getNoteTypeList());
		criteriaMap.put("systemDomain",notesLocateCriteria.getNoteSystemDomain());
		
		//Creating the SearchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NoteBO.class.getName(),	BusinessConstants.SEARCH_NOTES, criteriaMap);
		searchCriteria.setMaxSearchResultSize(notesLocateCriteria.getMaximumResultSize()+1);
		
		LocateResults locateResults = null;
		//Getting the search results
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults(); 
		if (null != searchResultList && !searchResultList.isEmpty()) {
			List locateResultsList = new ArrayList(searchResultList.size());
			locateResults = new LocateResults();
			Iterator searchResultIterator = searchResultList.iterator();
			while (searchResultIterator.hasNext()) {
				NoteBO noteBO = (NoteBO) searchResultIterator.next();
				
				locateResultsList.add(noteBO);
			}
			locateResults.setLocateResults(locateResultsList);
		}
		
		return locateResults;
	}
	
	/**
	 * This method persists the domain values for Notes.
	 * @param noteBO
	 * @param audit
	 * @throws SevereException
	 */
	public void persistDomainValuesForNotes(NoteDomainBO noteDomainBO, String domainName, Audit audit, AdapterServicesAccess serviceAccessEWPDB) throws SevereException, AdapterException {
		noteDomainBO.setEntityType(domainName);
		noteDomainBO.setCreatedUser(audit.getUser());
		noteDomainBO.setCreatedTimestamp(audit.getTime());
		noteDomainBO.setLastUpdatedUser(audit.getUser());
		noteDomainBO.setLastUpdatedTimestamp(audit.getTime());
		noteDomainBO.setSystemDomainId("0");
		AdapterUtil.performInsert(noteDomainBO, audit.getUser(), serviceAccessEWPDB);
	}
	
	public boolean checkNoteDomain(String queryName, HashMap criteriaMap, AdapterServicesAccess serviceAccessEWPDB) throws SevereException, AdapterException {
		SearchCriteria searchCriteria  = AdapterUtil.getAdapterSearchCriteria(
														NoteDomainBO.class.getName(),
														queryName, 
														criteriaMap);
		//Getting the search results
		List searchResultList = AdapterUtil.performSearch(searchCriteria, serviceAccessEWPDB).getSearchResults();
		if(null != searchResultList && !searchResultList.isEmpty()){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 * @throws SevereException
	 */
	public SearchResults copyNotesForCheckOut(NotesLocateCriteria criteria)throws SevereException {
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("sourceNoteId",criteria.getNoteId());
		refValSet.put("destinationNoteId",criteria.getNoteId());
		//Creating the SearchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NoteBO.class.getName(),	BusinessConstants.COPY_FOR_CHECKOUT, refValSet);
		searchCriteria.setMaxSearchResultSize(criteria.getMaximumResultSize()+1);
		
		//Getting the search results
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults;
	}


	
	/**
	 * @param noteBO1
	 * @param noteBO
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void copyNotesAssociations(NoteBO noteBO1_new, NoteBO noteBO, Audit audit) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("newNoteID", (null == noteBO.getNoteId() || noteBO.getNoteId().equals(""))? 
				null : noteBO.getNoteId());
		criteriaMap.put("oldNoteID",noteBO1_new.getNoteId());//old
		criteriaMap.put("newNoteVersion", new Integer(noteBO.getVersion()));				
		criteriaMap.put("oldNoteVersion",new Integer(noteBO1_new.getVersion()));//old
		
		//		Creating the SearchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NoteBO.class.getName(),	"copyNoteAssociations", criteriaMap);
		AdapterUtil.performSearch(searchCriteria);
	}

	/**
	 * @return
	 * @throws SevereException
	 */
	public void deleteNotesAssociation(NoteBO noteBO, Audit audit) throws SevereException {
		AdapterUtil.performRemove(noteBO,audit.getUser());
	}

	/**
	 * @param noteBO
	 * @return
	 * @throws SevereException
	 */
	
	public BusinessObject retrieveLatestVersionForCheckIn(NoteBO noteBO) throws SevereException {
		NoteBO notesBO = (NoteBO) retrieveLatestVersionByNoteName(noteBO);
		notesBO = retrieveNotesSystemDomain(notesBO);
		List noteDomainBOList = (List) notesBO.getSystemDomain();
		List systemDomain = null;
		if(null!=noteDomainBOList){
			 systemDomain = new ArrayList(noteDomainBOList.size());
		}
		NoteDomainBO noteDomainBO = null;
		for(int i=0;i<noteDomainBOList.size();i++){
			noteDomainBO = (NoteDomainBO) noteDomainBOList.get(i);
			systemDomain.add(noteDomainBO.getSystemDomainIds().get(0));
		}
		notesBO.setSystemDomain(systemDomain);
		return notesBO;
	}
	
	/**
	 * 
	 * @param notesLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults getAllVersions(NotesLocateCriteria notesLocateCriteria, User user) throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("noteName",notesLocateCriteria.getNoteName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				NoteBO.class.getName(),
				BusinessConstants.RETRIEVE_BY_NOTE_NAME, criteriaMap);
		List searchResultList = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		LocateResults locateResults = null;
		if (null != searchResultList && !searchResultList.isEmpty()) {
			List locateResultsList = new ArrayList(searchResultList.size()); 
			locateResults = new LocateResults();
			Iterator searchResultIterator = searchResultList.iterator();
			while (searchResultIterator.hasNext()) {
				NoteBO noteBO = (NoteBO)searchResultIterator.next();
				locateResultsList.add(noteBO);
			}
			locateResults.setLocateResults(locateResultsList);
		}	
		return locateResults;
	}
	
	/**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
	 * @param locateCriteria
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateTargetSystemsForNotes(String noteID) throws ServiceException {		
    	SearchCriteria searchCriteria = new SearchCriteriaImpl();
		LocateResults locateResults = new LocateResults();
		SearchResults searchResults = null;
		// set the required datas to the search criteria
		searchCriteria.setBusinessObjectName(NotesBOForView.class.getName());
		searchCriteria.setSearchQueryName(BusinessConstants.LOCATE_TARGET_SYSTEMS);
		searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
		searchCriteria.setMaxSearchResultSize(99999);
		// set the search note id to the map
		HashMap refValSet = new HashMap();		
		refValSet.put("noteId", noteID);	
		searchCriteria.setReferenceValueSet(refValSet);
		try {			
			searchResults = getAdapterService().searchObject(searchCriteria);
		} catch (AdapterException adapterException) {
			logAdapterExceptionForSearch(searchCriteria.getBusinessObjectName(),searchCriteria.getSearchQueryName(),adapterException);
		}
		locateResults.setLocateResults(searchResults.getSearchResults());
		locateResults.setTotalResultsCount(searchResults.getSearchResultCount());
		return locateResults;
	}	
	/**
	 * get instance of AdapterServicesAccess  
	 */
	 private AdapterServicesAccess getAdapterService(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess(BusinessConstants.EWPD);
        return adapterServicesAccess;
    }
	 
	/**
	 * 
	 * @param businessObjectName
	 * @param queryName
	 * @param adapterException
	 * @throws ServiceException
	 */
	 private void logAdapterExceptionForSearch(String businessObjectName, String queryName, AdapterException adapterException) throws ServiceException{
        List errorParams = new ArrayList(3);
        errorParams.add(businessObjectName);
        errorParams.add(queryName);
        throw new ServiceException(BusinessConstants.ADAPTER_EXCEPTION, errorParams, adapterException);
    }
	 
	 /**
	 * 
	 * @param subObject
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	public void deleteNotes(NoteBO mainObject, Audit audit) throws SevereException {
	    // get the BusinessTransactionContext object
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
	    // delete from the table
		AdapterUtil.performRemove(mainObject, audit.getUser());
	}
	
	/**
	 * 
	 * @param notesLocateCriteria
	 * @param user
	 * @return
	 * @throws SevereException
	 */
	public LocateResults locateNotesDataDomain(NotesLocateCriteria notesLocateCriteria, User user) throws SevereException {
		HashMap dataDomainMap = new HashMap();
		HashMap criteriaMap = new HashMap();
		SearchResults searchResults = null;
		List locateResultsList = null;
		LocateResults locateResults = new LocateResults();
		List dataDomainList = new ArrayList(3);
		SearchCriteria searchCriteria = null;
		criteriaMap.put("noteId",notesLocateCriteria.getNoteId());		
		//criteriaMap.put("version",new Integer(notesLocateCriteria.getVersion()));
		
		
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.notes.bo.NoteDomainBO.class.getName(),
				BusinessConstants.RETRIEVE_PRODUCT, criteriaMap);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		dataDomainMap.put("product",locateResultsList);
		
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.notes.bo.NoteDomainBO.class.getName(),
				BusinessConstants.RETRIEVE_BENEFIT, criteriaMap);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		dataDomainMap.put("benefit",locateResultsList);
		
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.notes.bo.NoteDomainBO.class.getName(),
				BusinessConstants.RETRIEVE_BENEFIT_COMPONENT, criteriaMap);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		dataDomainMap.put("benefitcomp",locateResultsList);
		
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.notes.bo.NoteDomainBO.class.getName(),
				BusinessConstants.RETRIEVE_QUESTION, criteriaMap);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		dataDomainMap.put("question",locateResultsList);
		
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.notes.bo.NoteDomainBO.class.getName(),
				BusinessConstants.RETRIEVE_TERM, criteriaMap);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		dataDomainMap.put("term",locateResultsList);
		
		searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				com.wellpoint.wpd.common.notes.bo.NoteDomainBO.class.getName(),
				BusinessConstants.RETRIEVE_QUALIFIER, criteriaMap);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		dataDomainMap.put("qualifier",locateResultsList);
		
		dataDomainList.add(dataDomainMap);
		locateResults.setLocateResults(dataDomainList);
		return locateResults;
	}
	
	/**
	 * @param noteDomainDeleteBO
	 * @return
	 * @throws SevereException
	 */
	public void deleteNoteDataDomain(NoteDomainDeleteBO noteDomainDeleteBO, Audit audit) throws SevereException {
		List noteDomainList = noteDomainDeleteBO.getNoteDomains();
		NoteDomainDeleteBO deleteBO = null;
		Iterator iterator = noteDomainList.iterator();
		while(iterator.hasNext()){
			deleteBO = (NoteDomainDeleteBO)iterator.next();
			AdapterUtil.performRemove(deleteBO, audit.getUser());			
		}
	}
	/**
	 * 
	 * @param noteBO
	 * @return
	 * @throws SevereException
	 */ 
	 public List getLatestVersionNoteForContract(NoteBO noteBO) throws SevereException{
   	 SearchCriteria searchCriteria = new SearchCriteriaImpl();
   	 SearchResults searchResults = null;
   	 searchCriteria.setBusinessObjectName(com.wellpoint.wpd.common.notes.bo.NoteBO.class.getName());
        searchCriteria.setMaxSearchResultSize(50);
        searchCriteria.setSearchQueryName(BusinessConstants.LATEST_VERSION_NOTES_FOR_CONTRACT);
        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
        // Create the referenceValueSet hashmap	
        HashMap refValSet = new HashMap();
        //Get the values entityId and EntityType and  Set them to the refValSet
        refValSet.put("newNoteID", noteBO.getNoteId());
        refValSet.put("oldNoteVersion", new Integer(noteBO.getVersion()));
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
	 * @param overrideBO
	 * @return
	 * @throws ServiceException
	 */
	public List getQuestionNote(NotesAttachmentOverrideBO overrideBO) throws SevereException{	
	 	
	 	String isDeleteAction = "Y";
	 	String isInsertAction ="Y";
	 	String isUpdateAction ="Y";
	 	SearchResults searchResults = null;
	 	List noteList=null;
	   	
	   // Create the referenceValueSet hashmap	
	    HashMap refValSet = new HashMap();
        //Get the values entityId and EntityType and  Set them to the refValSet
        refValSet.put("primaryEntityId", new Integer(overrideBO.getPrimaryEntityId()));
        refValSet.put("primaryEntityType", overrideBO.getPrimaryEntityType());
        refValSet.put("secondaryEntityId", new Integer(overrideBO.getSecondaryEntityId()));
        refValSet.put("bnftDefnIdString", overrideBO.getBnftDefnIdString());
        refValSet.put("secondaryEntityType", overrideBO.getSecondaryEntityType());
        if(overrideBO.getPrimaryEntityType().equals("ATTACHBENEFIT")||overrideBO.getSecondaryEntityType().equals("ATTACHADMNQUEST")){
	        refValSet.put("benefitComponentId", null);
	    }else{
	        	   refValSet.put("benefitComponentId", new Integer(overrideBO.getBenefitComponentId()));
	    }
        refValSet.put("questionNumber", new Integer(overrideBO.getQuestionNumber()));
        
        refValSet.put("entityId", new Integer(overrideBO.getQuestionNumber()).toString());
        if(overrideBO.getSearchAction()==2){
        	 refValSet.put("searchString", overrideBO.getSearchString());
        }
        // Connect to the adapter and call the search method
    	int k=0;
    	boolean isPresnt = false;
    	
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
    	        com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO.class.getName(),
    	        "questionNotePopUpQuery", refValSet);    	
    	searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);           
    	searchResults = AdapterUtil.performSearch(searchCriteria);
        noteList =searchResults.getSearchResults();
        
       
        
        List overridedNoteList =getOverridedQuestionNote(overrideBO);
        if(overrideBO.getSearchAction()==2  ){            	
            	String [] action = new String[3];
            	action=isDeleteAction(overridedNoteList,overrideBO,isInsertAction,isUpdateAction,isDeleteAction);
            	isDeleteAction = action[0];
            	isUpdateAction = action[1];
            	isInsertAction = action[2];
	    }
	 	if(null!=overridedNoteList && overridedNoteList.size()>0 ){
	 		NotesAttachmentOverrideBO attachmentBO= (NotesAttachmentOverrideBO)overridedNoteList.get(0);	
	 		attachmentBO.setOverrideStatus("Y");
	 		if(null!=noteList && noteList.size()>0){
	 			for (int i=0;i<noteList.size();i++){
	 				if(	((NotesAttachmentOverrideBO)noteList.get(i)).getNoteId().equals(attachmentBO.getNoteId())){
	 					noteList.remove(i);
	 					k=i;
	 					isPresnt=true;
	 					break;
	 			}
	 		}
	 		}
	 		if(isPresnt){
	 		noteList.add(k,attachmentBO);
	 		}
	 		else{
	 			noteList.add(attachmentBO);
	 		}
		}	      
        //Set the value of locateResultList to the locateResult
        if(null!=noteList && noteList.size()>0){
	       	for(int i=0;i<noteList.size();i++){
		       	((NotesAttachmentOverrideBO)noteList.get(i)).setIsdelete(isDeleteAction);
		       	((NotesAttachmentOverrideBO)noteList.get(i)).setIsInsert(isInsertAction);
		       	((NotesAttachmentOverrideBO)noteList.get(i)).setIsUpdate(isUpdateAction);
	       	}
       }
        //Code change for Sonar violations
        if(null == noteList){
        	noteList = new ArrayList();
        }
	   return noteList;	 	
	 }
	
	private String [] isDeleteAction(List overrideList,NotesAttachmentOverrideBO overrideBO,String isInsertAction,String isUpdateAction,String isDeleteAction){
		
		boolean deletePermission =false;
		
		String [] action = new String[3];
 		
		if(null!=overrideList && overrideList.size()>0){
			isDeleteAction ="Y";
			isUpdateAction ="Y";
			isInsertAction="N";
		}else{
			try {
				overrideBO.setSearchString(null);
				List overridedNoteList =getOverridedQuestionNote(overrideBO);
				if(null!= overridedNoteList && overridedNoteList.size()>0){
					isDeleteAction ="N";
					isUpdateAction ="Y";
					isInsertAction="N";
				}else{
					isDeleteAction ="N";
					isUpdateAction ="N";
					isInsertAction="Y";
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		action[0]=isDeleteAction;
		action[1]=isUpdateAction;
		action[2]=isInsertAction;
	return action;
	}
		 /**
	  * Method used to insert the attached notes in an admin option question
	  * @param notesToQuestionAttachmentBO
	  * @param audit
	  * @throws AdapterException
	  */
	 public void insertNotesAttachedToQuestion(
			NotesToQuestionAttachmentBO notesToQuestionAttachmentBO, Audit audit)
			throws AdapterException {
		try {
			AdapterUtil.performInsert(notesToQuestionAttachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in insert", exception);
		}

	}
	 /**
	  * Method used to update the attached notes in an admin option question
	  * @param notesToQuestionAttachmentBO
	  * @param audit
	  * @throws AdapterException
	  */
	public void updateNotesAttachedToQuestion(
			NotesToQuestionAttachmentBO notesToQuestionAttachmentBO, Audit audit)
			throws AdapterException {

		try {
			AdapterUtil.performUpdate(notesToQuestionAttachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in update", exception);
		}

	}

	/**
	 * Method used to delete the attached notes in an admin option question
	 * @param notesToQuestionAttachmentBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void deleteNotesAttachedToQuestion(
			NotesToQuestionAttachmentBO notesToQuestionAttachmentBO, Audit audit)
			throws AdapterException {

		try {
			AdapterUtil.performRemove(notesToQuestionAttachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in delete", exception);
		}

	}
	/**
	 * Method used to insert the attached notes in an admin option question at productlevel
	 * @param contractProductAONotesAttachmentBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void insertNotesAttachedToQuestion(
			TierNotesAttachmentOverrideBO attachmentBO,
			Audit audit) throws AdapterException {
		try {
			AdapterUtil.performInsert(attachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in insert", exception);
		}

	}

	/**
	 *  Method used to update the attached notes in an admin option question at product level
	 * @param contractProductAONotesAttachmentBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void updateNotesAttachedToQuestion(
			TierNotesAttachmentOverrideBO attachmentBO,
			Audit audit) throws AdapterException {

		try {
			AdapterUtil.performUpdate(attachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in update", exception);
		}

	}

	/**
	 *  Method used to delete the attached notes in an admin option question at product/contract level
	 * @param contractProductAONotesAttachmentBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void deleteNotesAttachedToQuestion(
			ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO,
			Audit audit) throws AdapterException {

		try {
			AdapterUtil.performRemove(contractProductAONotesAttachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in delete", exception);
		}

	}
	
	 /**
	 * @param overrideBO
	 * @return
	 * @throws ServiceException
	 */
	public List getOverridedQuestionNote(NotesAttachmentOverrideBO overrideBO) throws ServiceException{
	 	
	 	SearchCriteria searchCriteria = new SearchCriteriaImpl();
	 	
	 	SearchResults searchResults = null;
	   	 searchCriteria.setBusinessObjectName(com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO.class.getName());
	        searchCriteria.setMaxSearchResultSize(20);
	        searchCriteria.setSearchQueryName("getOverridedNote");
	        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
	        // Create the referenceValueSet hashmap	
	        HashMap refValSet = new HashMap();
	        //Get the values entityId and EntityType and  Set them to the refValSet
	        refValSet.put("primaryEntityId", new Integer(overrideBO.getPrimaryEntityId()));
	        refValSet.put("primaryEntityType", overrideBO.getPrimaryEntityType());
	        refValSet.put("secondaryEntityId", new Integer(overrideBO.getSecondaryEntityId()));
	        refValSet.put("bnftDefnIdString", overrideBO.getBnftDefnIdString());
	        refValSet.put("secondaryEntityType", overrideBO.getSecondaryEntityType());
	        if(overrideBO.getPrimaryEntityType().equals("ATTACHBENEFIT")||overrideBO.getSecondaryEntityType().equals("ATTACHADMNQUEST")){
		        refValSet.put("benefitComponentId", null);
		    }else{
		        	   refValSet.put("benefitComponentId", new Integer(overrideBO.getBenefitComponentId()));
		    }
	        refValSet.put("questionNumber", new Integer(overrideBO.getQuestionNumber()));
	        if(overrideBO.getSearchAction()==2){
	        	 refValSet.put("searchString", overrideBO.getSearchString());
	        }
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
	 * @param overrideBO
	 * @return
	 * @throws ServiceException
	 */
	public List getQuestionTierNote(TierNotesAttachmentOverrideBO overrideBO) throws SevereException{	
	 	
	 	String isDeleteAction = "Y";
	 	String isInsertAction ="Y";
	 	String isUpdateAction ="Y";
	 	SearchResults searchResults = null;
	 	List noteList=null;	 	
	 
	   // Create the referenceValueSet hashmap	
	    HashMap refValSet = new HashMap();
        //Get the values entityId and EntityType and  Set them to the refValSet
        refValSet.put("questionNumber", new Integer(overrideBO.getQuestionNumber()));
        if(overrideBO.getSearchAction()==2){
        	 refValSet.put("searchString", overrideBO.getSearchString());
        }
        // Connect to the adapter and call the search method
    	int k=0;
    	boolean isPresnt = false;
    	
    	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
    	        com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO.class.getName(),
    	        "questionNotePopUpQuery", refValSet);    	
    	searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);           
    	searchResults = AdapterUtil.performSearch(searchCriteria);
        noteList =searchResults.getSearchResults();
                
        List overridedNoteList =getOverridedQuestionTierNote(overrideBO);
        if(overrideBO.getSearchAction()==2  ){            	
            	String [] action = new String[3];
            	action=isDeleteAction(overridedNoteList,overrideBO,isInsertAction,isUpdateAction,isDeleteAction);
            	isDeleteAction = action[0];
            	isUpdateAction = action[1];
            	isInsertAction = action[2];
	    }
	 	if(null!=overridedNoteList && overridedNoteList.size()>0 ){
	 		TierNotesAttachmentOverrideBO attachmentBO= (TierNotesAttachmentOverrideBO)overridedNoteList.get(0);	
	 		attachmentBO.setOverrideStatus("Y");
	 		if(null!=noteList && noteList.size()>0){
	 			for (int i=0;i<noteList.size();i++){
	 				if(	((TierNotesAttachmentOverrideBO)noteList.get(i)).getNoteId().equals(attachmentBO.getNoteId())){
	 					noteList.remove(i);
	 					k=i;
	 					isPresnt=true;
	 					break;
	 			}
	 		}
	 		}
	 		if(isPresnt){
	 		noteList.add(k,attachmentBO);
	 		}
	 		else{
	 			noteList.add(attachmentBO);
	 		}
		}	      
        //Set the value of locateResultList to the locateResult
        if(null!=noteList && noteList.size()>0){
	       	for(int i=0;i<noteList.size();i++){
		       	((NotesAttachmentOverrideBO)noteList.get(i)).setIsdelete(isDeleteAction);
		       	((NotesAttachmentOverrideBO)noteList.get(i)).setIsInsert(isInsertAction);
		       	((NotesAttachmentOverrideBO)noteList.get(i)).setIsUpdate(isUpdateAction);
	       	}
       }
        //Code change for Sonar violations
        if(null == noteList){
        	noteList = new ArrayList();
        }
	   return noteList;	 	
	 }
	
	/**
	 * @param overrideBO
	 * @return
	 * @throws ServiceException
	 */
	public List getOverridedQuestionTierNote(TierNotesAttachmentOverrideBO overrideBO) throws ServiceException{
	 	
	 	SearchCriteria searchCriteria = new SearchCriteriaImpl();
	 	
	 	SearchResults searchResults = null;
	   	 searchCriteria.setBusinessObjectName(com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO.class.getName());
	        searchCriteria.setMaxSearchResultSize(20);
	        searchCriteria.setSearchQueryName("getOverridedNote");
	        searchCriteria.setSearchDomain(BusinessConstants.SEARCH_DOMAIN);
	        // Create the referenceValueSet hashmap	
	        HashMap refValSet = new HashMap();
	        //Get the values entityId and EntityType and  Set them to the refValSet
	        refValSet.put("tierSysId", new Integer(overrideBO.getTierSysId()));
	        refValSet.put("primaryEntityId", new Integer(overrideBO.getPrimaryEntityId()));
	        refValSet.put("primaryEntityType", overrideBO.getPrimaryEntityType());
	        refValSet.put("secondaryEntityId", new Integer(overrideBO.getSecondaryEntityId()));
	        refValSet.put("bnftDefnIdString", overrideBO.getBnftDefnIdString());
	        refValSet.put("secondaryEntityType", overrideBO.getSecondaryEntityType());
	        refValSet.put("benefitComponentId", new Integer(overrideBO.getBenefitComponentId()));
		  
	        refValSet.put("questionNumber", new Integer(overrideBO.getQuestionNumber()));
	        if(overrideBO.getSearchAction()==2){
	        	 refValSet.put("searchString", overrideBO.getSearchString());
	        }
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
	 * Method used to delete the attached notes in an admin option question
	 * @param notesToQuestionAttachmentBO
	 * @param audit
	 * @throws AdapterException
	 */
	public void deleteNotesAttachedToQuestion(
			TierNotesAttachmentOverrideBO attachmentBO, Audit audit)
			throws AdapterException {

		try {
			AdapterUtil.performRemove(attachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in delete", exception);
		}

	}
	
	 /**
	  * Method used to insert the attached notes in an admin option question
	  * @param notesToQuestionAttachmentBO
	  * @param audit
	  * @throws AdapterException
	  */
	 public void insertNotesAttachedToQuestion(
	 		ContractProductAONotesAttachmentBO attachmentBO, Audit audit)
			throws AdapterException {
		try {
			AdapterUtil.performInsert(attachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in insert", exception);
		}

	}
	 /**
	  * Method used to update the attached notes in an admin option question
	  * @param notesToQuestionAttachmentBO
	  * @param audit
	  * @throws AdapterException
	  */
	public void updateNotesAttachedToQuestion(
			ContractProductAONotesAttachmentBO attachmentBO, Audit audit)
			throws AdapterException {

		try {
			AdapterUtil.performUpdate(attachmentBO, audit
					.getUser());

		} catch (SevereException exception) {
			throw new AdapterException(
					"Exception occured while adapter call in update", exception);
		}

	}
	public List getNoteInfo(TierNotesAttachmentOverrideBO attachmentBo)throws AdapterException{
		
		SearchCriteria searchCriteria = new SearchCriteriaImpl();
		List validQuestionnaires = null;
		SearchResults searchResults = null;
    	searchCriteria.setBusinessObjectName(TierNotesAttachmentOverrideBO.class.getName());
    	searchCriteria.setMaxSearchResultSize(Integer.MAX_VALUE);
    	searchCriteria.setSearchDomain("medical");
    	searchCriteria.setSearchQueryName("getNoteInfo");
    	HashMap refValSet = new HashMap();
    	searchCriteria.setReferenceValueSet(refValSet);
    	refValSet.put("primaryEntityId",new Integer(attachmentBo.getPrimaryEntityId()));
    	refValSet.put("secondaryEntityId",new Integer(attachmentBo.getSecondaryEntityId()));
    	refValSet.put("questionNumber",new Integer(attachmentBo.getQuestionNumber()));
    	refValSet.put("primaryEntityType",attachmentBo.getPrimaryEntityType());
    	refValSet.put("secondaryEntityType",attachmentBo.getSecondaryEntityType());
    	refValSet.put("tierSysId",new Integer(attachmentBo.getTierSysId()));
    	try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {
			throw new AdapterException("Adapter Exception occured",
					e);
		}
		
		List resultList = searchResults.getSearchResults();
		
		return resultList;
	}
}
