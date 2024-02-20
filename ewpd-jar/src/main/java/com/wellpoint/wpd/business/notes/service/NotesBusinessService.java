/*
 * Created on May 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.notes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.notes.builder.NotesBusinessObjectBuilder;
import com.wellpoint.wpd.business.notes.locatecriteria.NotesLocateCriteria;
import com.wellpoint.wpd.business.notes.locatecriteria.NotesLocateCriteriaForView;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.SecurityException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainDeleteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainResult;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.CreateNotesDataDomainRequest;
import com.wellpoint.wpd.common.notes.request.CreateNotesRequest;
import com.wellpoint.wpd.common.notes.request.DeleteNotesRequest;
import com.wellpoint.wpd.common.notes.request.LocateTargetSystemsForNotesRequest;
import com.wellpoint.wpd.common.notes.request.NoteDomainRequest;
import com.wellpoint.wpd.common.notes.request.NotesCheckInRequest;
import com.wellpoint.wpd.common.notes.request.NotesCheckOutRequest;
import com.wellpoint.wpd.common.notes.request.NotesCopyRequest;
import com.wellpoint.wpd.common.notes.request.NotesLifeCycleRequest;
import com.wellpoint.wpd.common.notes.request.NotesSearchRequest;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.request.RetrieveNotesRequest;
import com.wellpoint.wpd.common.notes.request.ViewAllVersionsNotesRequest;
import com.wellpoint.wpd.common.notes.response.CreateNotesDataDomainResponse;
import com.wellpoint.wpd.common.notes.response.CreateNotesResponse;
import com.wellpoint.wpd.common.notes.response.DeleteNotesResponse;
import com.wellpoint.wpd.common.notes.response.LocateTargetSystemsForNotesResponse;
import com.wellpoint.wpd.common.notes.response.NoteDomainResponse;
import com.wellpoint.wpd.common.notes.response.NotesCheckInResponse;
import com.wellpoint.wpd.common.notes.response.NotesCheckOutResponse;
import com.wellpoint.wpd.common.notes.response.NotesCopyResponse;
import com.wellpoint.wpd.common.notes.response.NotesSearchResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.response.RetrieveNotesResponse;
import com.wellpoint.wpd.common.notes.response.ViewAllVersionsNotesResponse;
import com.wellpoint.wpd.common.notes.vo.NoteDomainVO;
import com.wellpoint.wpd.common.notes.vo.NotesVO;

/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesBusinessService extends WPDBusinessService{
	
	public NotesBusinessService(){
		
	}

	/**
	 * @param createNotesRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(CreateNotesRequest createNotesRequest)
	throws ServiceException {
		
		Logger.logInfo("Entering execute method for creating Notes");
		//Creating CreateNotesResponse object
		CreateNotesResponse createNotesResponse = (CreateNotesResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.CREATE_NOTES_RESPONSE);
		try {
			//Creating NoteBO object by coping the values from the createNotesRequest
			NoteBO noteBO = copyBusinessObjectFromRequestObject(createNotesRequest);
			NoteBO oldnoteBO = new NoteBO();
			if(null != createNotesRequest.getOldNotesVO()){
			    oldnoteBO = getNoteBO(createNotesRequest);				
			}
			//Creating BusinessObjectManagerFactory object
			BusinessObjectManagerFactory businessObjectManagerFactory = (BusinessObjectManagerFactory) ObjectFactory
			.getFactory(ObjectFactory.BOM);
			
			//Creating BusinessObjectManager object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			if (createNotesRequest.isCreateFlag()) {
				createNotesResponse = (CreateNotesResponse) new ValidationServiceController()
				.execute(createNotesRequest);
				if (createNotesResponse.isErrorFlag()) {
					copyValuesFromBOToResponse(createNotesResponse, noteBO);
					return createNotesResponse;
				}
				businessObjectManager.persist(noteBO, createNotesRequest
						.getUser(), true);
				
				createNotesResponse.addMessage(new InformationalMessage(
				"notes.save.success.info"));
			} else {
				if(!(noteBO.getNoteName().equalsIgnoreCase(oldnoteBO.getNoteName()))){
					
					createNotesResponse = (CreateNotesResponse) new ValidationServiceController()
					.execute(createNotesRequest);
					if (createNotesResponse.isErrorFlag()) {
						copyValuesFromBOToResponse(createNotesResponse, noteBO);
						return createNotesResponse;
					}

					if(createNotesResponse.isKeyValueChanged()){

					    businessObjectManager.changeIdentity(oldnoteBO,noteBO,createNotesRequest.getUser());
						copyValuesFromBOToResponse(createNotesResponse, noteBO);
						createNotesResponse.addMessage(new InformationalMessage(
						"notes.update.success.info"));
						return createNotesResponse;
					}			
				}
				businessObjectManager.persist(noteBO, createNotesRequest
						.getUser(), false);
				createNotesResponse.addMessage(new InformationalMessage(
				"notes.update.success.info"));
			}
			copyValuesFromBOToResponse(createNotesResponse, noteBO);
			
		} catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            createNotesResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(createNotesRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList(2);
			logParameters.add(createNotesRequest);
			String logMessage = "Failed while processing CreateNotesRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		 Logger
          .logInfo("Returning execute method for creating Notes");
		return createNotesResponse;
		
	}
	
	
	/**
	 * This method copies the values to NoteBO from the CreateNotesRequest.
	 * @param createNotesRequest
	 * @return NoteBO noteBO
	 */
	private NoteBO getNoteBO(CreateNotesRequest createNotesRequest) {
		NoteBO oldNoteBO = new NoteBO();
		oldNoteBO.setNoteText(createNotesRequest.getOldNotesVO().getText());
		oldNoteBO.setNoteType(createNotesRequest.getOldNotesVO().getNoteType());
		oldNoteBO.setSystemDomain(createNotesRequest.getOldNotesVO().getSystemDomainList());
		if(null!=createNotesRequest.getOldNotesVO().getText())
			oldNoteBO.setNoteText(createNotesRequest.getOldNotesVO().getText().toUpperCase());
		if(null!=createNotesRequest.getOldNotesVO().getNoteName())
			oldNoteBO.setNoteName(createNotesRequest.getOldNotesVO().getNoteName().toUpperCase());
		oldNoteBO.setStatus(createNotesRequest.getStatus());		
		return oldNoteBO;
	}
	
	/**
	 * @param createNotesDataDomainRequest
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(CreateNotesDataDomainRequest createNotesDataDomainRequest)
	throws WPDException 
	{
	    //	Creating CreateNotesDataDomainResponse object
		CreateNotesDataDomainResponse createNotesDataDomainResponse = (CreateNotesDataDomainResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.CREATE_NOTES_DATA_DOMAIN_RESPONSE);
		
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteId(createNotesDataDomainRequest.getNoteId());
		noteBO.setVersion(createNotesDataDomainRequest.getVersion());
		noteBO.setNoteName(createNotesDataDomainRequest.getNoteName());
		//	Creating NoteBO object by coping the values from the createNotesRequest
		NoteDomainBO noteDomainBO = copyBusinessObjectFromRequestObject(createNotesDataDomainRequest);
		noteDomainBO.setSystemDomainId("0");
		//	Creating BusinessObjectManagerFactory object
		BusinessObjectManagerFactory businessObjectManagerFactory = (BusinessObjectManagerFactory) ObjectFactory
		.getFactory(ObjectFactory.BOM);
		
		//Creating BusinessObjectManager object
		BusinessObjectManager businessObjectManager = this
		.getBusinessObjectManager();
		if (createNotesDataDomainRequest.isCreateFlag()) {
		    noteBO.setAction(true);
			businessObjectManager.persist(noteDomainBO, noteBO,createNotesDataDomainRequest
					.getUser(), true);
			createNotesDataDomainResponse.setState(createNotesDataDomainRequest.getState());
			createNotesDataDomainResponse.addMessage(new InformationalMessage(
			"notes.datadomain.save.success.info"));
		} else {
			businessObjectManager.persist(noteDomainBO, createNotesDataDomainRequest
					.getUser(), false);
			createNotesDataDomainResponse.addMessage(new InformationalMessage(
			"notes.update.success.info"));
		}
		return createNotesDataDomainResponse;
		
	}
	
	/**
	 * @param createNotesDataDomainRequest
	 * @return
	 */
	private NoteDomainBO copyBusinessObjectFromRequestObject(CreateNotesDataDomainRequest createNotesDataDomainRequest) {
		NoteDomainBO noteDomainBO = new NoteDomainBO();
		noteDomainBO.setNoteId(createNotesDataDomainRequest.getNoteId());
		noteDomainBO.setVersion(createNotesDataDomainRequest.getVersion());
		HashMap dataDomainListMap =  new HashMap();
		dataDomainListMap.put("term", createNotesDataDomainRequest.getTermList());
		dataDomainListMap.put("qualifier", createNotesDataDomainRequest.getQualifierList());
		dataDomainListMap.put("product", createNotesDataDomainRequest.getProductList());
		dataDomainListMap.put("benefit", createNotesDataDomainRequest.getBenefitList());
		dataDomainListMap.put("benefitcomponent", createNotesDataDomainRequest.getBenefitComponentList());
		dataDomainListMap.put("question", createNotesDataDomainRequest.getQuestionList());
		noteDomainBO.setNotesDataDomainLists(dataDomainListMap);
		return noteDomainBO;
	}

	/**


	/**
	 * This is the execute method for retrieving notes.
	 * @param retrieveNotesRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(RetrieveNotesRequest retrieveNotesRequest)throws ServiceException {
		Logger.logInfo("Entering execute method for retrieving Notes");
		//Creating RetrieveNotesResponse object
		RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.RETRIEVE_NOTES_RESPONSE);
		try {
			if(null != retrieveNotesRequest.getAction() 
					&& retrieveNotesRequest.getAction().equals("targetSystems")){
				NotesBusinessObjectBuilder builder = new NotesBusinessObjectBuilder();
				retrieveNotesResponse.setNotesInActivelyUsedStatus(
										builder.isNotesInActivelyUsedStatus(retrieveNotesRequest.getNoteId()));				
			}else{
				//Creating NoteBO object by coping the values from the request
				NoteBO noteBO = copyBusinessObjectFromRequest(retrieveNotesRequest);
				
				// Creating BusinessObjectManagerFactory object
				BusinessObjectManager businessObjectManager = this
				.getBusinessObjectManager();
				if(retrieveNotesRequest.isEdit()){
					boolean isLocked = businessObjectManager.lock(noteBO,retrieveNotesRequest.getUser());
				}
						noteBO = (NoteBO) businessObjectManager.retrieve(noteBO,
								retrieveNotesRequest.getUser());
						//Setting the new NoteBO object to the RetrieveNotesResponse
						retrieveNotesResponse.setNoteBO(noteBO);
				//Retrieving the Notes from thr database.
			}		
		}catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            retrieveNotesResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(retrieveNotesRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		} catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList(2);
			logParameters.add(retrieveNotesRequest);
			String logMessage = "Failed while processing RetrieveNotesRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		 Logger
          .logInfo("Returning  execute method for retrieving Notes");
		return retrieveNotesResponse;
		
	}
	/**
	 * Method to create a NotesLocateCriteria object and to copy the search
	 * criteria values from the request to this object.
	 * 
	 * @param notesSearchRequest NotesSearchRequest
	 * @return notesLocateCriteria NotesLocateCriteria
	 */
	private NotesLocateCriteria getNotesSearchObject(
			NotesSearchRequest notesSearchRequest) {
		
		NotesLocateCriteria notesLocateCriteria = new NotesLocateCriteria();
		
		//Coping the values from the request to notesLocateCriteria	
		notesLocateCriteria.setNoteId(notesSearchRequest.getNotesVO().getNoteId());
		notesLocateCriteria.setNoteName(notesSearchRequest.getNotesVO().getNoteName());
		notesLocateCriteria.setNoteTypeList(notesSearchRequest.getNotesVO().getNoteTypeList());
		notesLocateCriteria.setNoteSystemDomain(notesSearchRequest.getNotesVO().getSystemDomainList());	
		notesLocateCriteria.setMaximumResultSize(notesSearchRequest.getMaxSize());
		
		return notesLocateCriteria;
	}
	/**
	 * This method copies the values to NoteBO from the RetrieveNotesRequest.
	 * @param retrieveNotesRequest
	 * @return
	 */
	private NoteBO copyBusinessObjectFromRequest(RetrieveNotesRequest retrieveNotesRequest) {
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteId(retrieveNotesRequest.getNoteId());
		noteBO.setNoteName(retrieveNotesRequest.getNoteName());
		noteBO.setVersion(retrieveNotesRequest.getVersion());
		if(null != retrieveNotesRequest.getAction()){
			if(retrieveNotesRequest.getAction().equalsIgnoreCase("compare")){
				noteBO.setCompareAction("compare");
			}			
		}
		return noteBO;
	}
	
	/**
	 * @param createNotesResponse
	 * @param noteBO
	 */
	private void copyValuesFromBOToResponse(CreateNotesResponse createNotesResponse, NoteBO noteBO) {
		createNotesResponse.setNoteId(noteBO.getNoteId());
		createNotesResponse.setNoteName(noteBO.getNoteName());
		createNotesResponse.setNoteText(noteBO.getNoteText());
		createNotesResponse.setNoteType(noteBO.getNoteType());
		createNotesResponse.setNoteSystemDomain(noteBO.getNoteSystemDomain());
		createNotesResponse.setVersion(noteBO.getVersion());
		createNotesResponse.setStatus(noteBO.getStatus());
		createNotesResponse.setStateObject(noteBO.getState());
	}

	/**
	 * Copy values from request to NoteBO.
	 * @param createNotesRequest
	 * @return
	 */
	private NoteBO copyBusinessObjectFromRequestObject(CreateNotesRequest createNotesRequest) {
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteId(createNotesRequest.getNoteId());
		noteBO.setNoteType(createNotesRequest.getType());
		noteBO.setSystemDomain(createNotesRequest.getSystemDomain());
		if(null!=createNotesRequest.getText())
			noteBO.setNoteText(createNotesRequest.getText());
		if(null!=createNotesRequest.getName())
			noteBO.setNoteName(createNotesRequest.getName().toUpperCase());
		noteBO.setVersion(createNotesRequest.getVersion());
		noteBO.setStatus(createNotesRequest.getStatus());
		return noteBO;
	}	
	
	/**
	 * Method to get Business Object Manager
	 * 
	 * @return bom BusinessObjectManager
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
		.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}
	
	/**
	 * @param noteDomainRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(NoteDomainRequest noteDomainRequest)
	throws ServiceException {
	
		Logger.logInfo("Entering execute method for fetching note domains");
		//Creating NoteDomainResponse object
		LocateResults locateResults = null;
		NoteDomainResponse noteDomainResponse = (NoteDomainResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.NOTE_DOMAIN_RESPONSE);
		try {
			//Creating NoteBO object by coping the values from the createNotesRequest
			NotesLocateCriteria notesLocateCriteria = new NotesLocateCriteria();
			notesLocateCriteria.setAction(noteDomainRequest.getAction());
			notesLocateCriteria.setNoteId(noteDomainRequest.getNoteId());
			notesLocateCriteria.setNoteName(noteDomainRequest.getNoteName());
			notesLocateCriteria.setMaximumResultSize(noteDomainRequest.getMaximumSize());
			notesLocateCriteria.setVersion(noteDomainRequest.getVersion());
			
			/* For question domaining, the businessEntityList,businessGroupList
			 * and lobList need not be considered. The below if condition is checking that.*/
			if(!"question".equals(noteDomainRequest.getAction())){
				List lobList = noteDomainRequest.getLobList();
				List businessEntityList = noteDomainRequest.getBusinessEntityList();
				List businessGroupList = noteDomainRequest.getBusinessGroupList();
				List marketBusinessUnitList = noteDomainRequest.getMarketBusinessUnitList();
				List businessDomains = BusinessUtil.convertToDomains
				(lobList,businessEntityList,businessGroupList, marketBusinessUnitList, true);
				
				lobList = new ArrayList(businessDomains.size());
				businessEntityList = new ArrayList(businessDomains.size());
				businessGroupList = new ArrayList(businessDomains.size());
				marketBusinessUnitList = new ArrayList(businessDomains.size());
				
		        Domain domain = null;
		        for (Iterator iter = businessDomains.iterator(); iter.hasNext();) {
		            domain = (Domain) iter.next();
		            lobList.add(domain.getLineOfBusiness());
		            businessEntityList.add(domain.getBusinessEntity());
		            businessGroupList.add(domain.getBusinessGroup());
		            marketBusinessUnitList.add(domain.getMarketBusinessUnit());
		        }
				notesLocateCriteria.setLobList(lobList);
				notesLocateCriteria.setBusinessEntityList(businessEntityList);
				notesLocateCriteria.setBusinessGroupList(businessGroupList);
				notesLocateCriteria.setMarketBusinessUnitList(marketBusinessUnitList);
			}		
			//Creating BusinessObjectManagerFactory object
			BusinessObjectManagerFactory businessObjectManagerFactory = 
			    (BusinessObjectManagerFactory) ObjectFactory
			.getFactory(ObjectFactory.BOM);
			
			//Creating BusinessObjectManager object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			locateResults = businessObjectManager.locate(notesLocateCriteria, noteDomainRequest
						.getUser());
			List errorList = new ArrayList(3);
			List locateList = locateResults.getLocateResults();
			noteDomainResponse.setResults(locateList);
			NotesBusinessObjectBuilder builder = new NotesBusinessObjectBuilder();
			noteDomainResponse.setNotesInActivelyUsedStatus(builder
						.isNotesInActivelyUsedStatus(noteDomainRequest.getNoteId()));
			
			if(!("dataDomains").equals(noteDomainRequest.getAction()) && 
			        ("false".equals(noteDomainRequest.getCheck()))){
				if(null != locateList && !locateList.isEmpty()){
					List locateResultsList = new ArrayList(locateList.size());				
					if(locateList.size() > notesLocateCriteria.getMaximumResultSize()){
						Iterator ite = locateList.iterator();
						int count = 0;
						while(ite.hasNext()){
							NoteDomainResult noteDomainResult = new NoteDomainResult();
							noteDomainResult = (NoteDomainResult)ite.next();
							count++;
							locateResultsList.add(noteDomainResult);
							if(count >= notesLocateCriteria.getMaximumResultSize()){
								break;
							}
						}
						noteDomainResponse.setResults(locateResultsList);
						errorList.add(new InformationalMessage(
								BusinessConstants.MSG_FILTERED_RESULT_EXCEEDS));
						noteDomainResponse.setMessages(errorList);
					}
				}
			}	
		} catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            noteDomainResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(noteDomainRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		}catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList(2);
			logParameters.add(noteDomainRequest);
			String logMessage = "Failed while processing CreateNotesRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		 Logger
	      .logInfo("Returning execute method for creating Notes");
		return noteDomainResponse;
	
}
	/**
	 * Method to search Notes.
	 * 
	 * @param notesSearchRequest NotesSearchRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(NotesSearchRequest notesSearchRequest)
	throws ServiceException {
		Logger.logInfo("Entering execute method for locating Notes");
		int searchResultCount = 0;
		List notesSearchResultList = null;
		List errorList = null;
		LocateResults locateResults = null;
		
		NotesSearchResponse notesSearchResponse = (NotesSearchResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.NOTES_SEARCH_RESPONSE);
		
		try {
			
			//Creating a NotesLocateCriteria object
			NotesLocateCriteria notesLocateCriteria = getNotesSearchObject(notesSearchRequest);
			
			//Creating BusinessObjectManager instance.
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			//Get the locateResults from the businessObjectManager
			locateResults = businessObjectManager.locate(notesLocateCriteria,
					notesSearchRequest.getUser());
			notesSearchResultList = locateResults.getLocateResults();
			searchResultCount = notesSearchResultList.size();
			errorList = new ArrayList(5);
			
			if (notesSearchResultList.size() > 0) {
				
				//Setting the search results to the response
				notesSearchResponse
				.setNotesSearchResultList(notesSearchResultList);
				
			} else if (notesSearchResultList.size() == 0
					&& searchResultCount == 0) {
				
				
				//Setting thr error message to the response
				errorList.add(new InformationalMessage(
				"search.result.not.found"));
				notesSearchResponse.setMessages(errorList);
			} 
			if(searchResultCount>notesLocateCriteria.getMaximumResultSize()){
				notesSearchResultList.remove(notesLocateCriteria.getMaximumResultSize());
				notesSearchResponse.setNotesSearchResultList(notesSearchResultList);
				errorList.add(new InformationalMessage(
						BusinessConstants.SEARCH_RESULT_EXCEEDS));
				notesSearchResponse.setMessages(errorList);
			}
			
		} catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            notesSearchResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(notesSearchRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		}catch (Exception ex) {
			Logger.logInfo(ex);
			List logParameters = new ArrayList(2);
			logParameters.add(notesSearchRequest);
			String logMessage = "Failed while processing NotesSearchRequest";
			throw new ServiceException(logMessage, logParameters, ex);
		}
		Logger
        .logInfo("Returning execute method for locating Notes");
		return notesSearchResponse;
	}
	
	/**
	 * The method for Life Cycle Events for Note.
	 * 
	 * @param notesLifeCycleRequest NotesLifeCycleRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(NotesLifeCycleRequest notesLifeCycleRequest)throws ServiceException {
		Logger.logInfo("Entering execute method for Notes Life Cycle Events");
		RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.RETRIEVE_NOTES_RESPONSE);
		NoteBO noteBO = copyBusinessObjectFromValueObject(notesLifeCycleRequest.getNotesVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		int actionFlag = 0;
		List  messageList = new ArrayList(5);
		try {		    
		    boolean status;
		    if("SendForTest".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		        actionFlag =1;
		        status = bom.scheduleForTest(noteBO,notesLifeCycleRequest.getUser());
				retrieveNotesResponse.addMessage(new InformationalMessage("sendForTest.note.success.info"));		        
		    }else if ("Publish".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		        actionFlag =2;
		        status = bom.publish(noteBO,notesLifeCycleRequest.getUser());
		        InformationalMessage message = new InformationalMessage("publish.note.success.info");
		        message.setParameters(new String[] {notesLifeCycleRequest.getNotesVO().getNoteId()});
		        messageList.add(message);	
		        retrieveNotesResponse.setMessages(messageList);
				//retrieveNotesResponse.addMessage(new InformationalMessage("publish.note.success.info"));		        
		    }else if ("TestPass".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		        actionFlag =3;
		        status = bom.testPass(noteBO,notesLifeCycleRequest.getUser());
				retrieveNotesResponse.addMessage(new InformationalMessage("testpass.note.success.info"));
		    }else if ("TestFail".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		        actionFlag =4;
		        status = bom.testFail(noteBO,notesLifeCycleRequest.getUser());
				retrieveNotesResponse.addMessage(new InformationalMessage("testfail.note.success.info"));        
		    } else if ("Approve".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		        actionFlag =9;
		        status = bom.approve(noteBO,notesLifeCycleRequest.getUser());
		        status = bom.publish(noteBO,notesLifeCycleRequest.getUser());
		        InformationalMessage message = new InformationalMessage("approve.note.success.info");
		        message.setParameters(new String[] {notesLifeCycleRequest.getNotesVO().getNoteId()});
		        messageList.add(message);	
		        retrieveNotesResponse.setMessages(messageList);	
				//retrieveNotesResponse.addMessage(new InformationalMessage("approve.note.success.info"));        
		    }else if ("Reject".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		        actionFlag =10;
		        status = bom.reject(noteBO,notesLifeCycleRequest.getUser());
		        InformationalMessage message = new InformationalMessage("reject.note.success.info");
		        message.setParameters(new String[] {notesLifeCycleRequest.getNotesVO().getNoteId()});
		        messageList.add(message);	
		        retrieveNotesResponse.setMessages(messageList);	
				//retrieveNotesResponse.addMessage(new InformationalMessage("reject.note.success.info"));        
		    }else if ("unlock".equalsIgnoreCase(notesLifeCycleRequest.getAction())){
		    	actionFlag =11;
		    	 status = bom.unlock(noteBO,notesLifeCycleRequest.getUser());
			     InformationalMessage message = new InformationalMessage("note.lock.acquired");
			     message.setParameters(new String[] {notesLifeCycleRequest.getNotesVO().getNoteId()});
			     messageList.add(message);	
			     retrieveNotesResponse.setMessages(messageList);	
		    }
			
		} catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            retrieveNotesResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(notesLifeCycleRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		}
		catch (WPDException e) {
			Logger.logInfo(e);
			switch (actionFlag){
				case 1:
				    retrieveNotesResponse.addMessage(new ErrorMessage("sendForTest.note.faliure.info"));
				case 2:
				    retrieveNotesResponse.addMessage(new ErrorMessage("publish.note.faliure.info"));
				case 3:
				    retrieveNotesResponse.addMessage(new ErrorMessage("testpass.note.faliure.info"));
				case 4:
				    retrieveNotesResponse.addMessage(new ErrorMessage("testfail.note.faliure.info"));			    
				case 5:
				    retrieveNotesResponse.addMessage(new ErrorMessage("checkout.note.faliure.info"));
				case 9:
				    retrieveNotesResponse.addMessage(new ErrorMessage("approve.note.faliure.info"));			    
				case 10:
				    retrieveNotesResponse.addMessage(new ErrorMessage("reject.note.faliure.info"));		
				case 11:
				    retrieveNotesResponse.addMessage(new ErrorMessage("locked.by.another.user"));		
				    
			}			
			List logParameters = new ArrayList(2);
			logParameters.add(notesLifeCycleRequest);
			String logMessage = "Failed while processing NotesLifeCycleRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for Notes Life Cycle Events");
		return retrieveNotesResponse;
	}
	/**
	 * The method for Checkout Events for Note.
	 * 
	 * @param notesCheckOutRequest NotesCheckOutRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 */
	public WPDResponse execute(NotesCheckOutRequest notesCheckOutRequest)throws ServiceException {
		Logger.logInfo("Entering execute method for Notes Checkout Events");
		NotesCheckOutResponse notesCheckOutResponse = (NotesCheckOutResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.NOTES_CHECKOUT_RESPONSE);
		NoteBO noteBO = copyBusinessObjectFromValueObject(notesCheckOutRequest.getNotesVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		List messageList = new ArrayList(3);
		try {		    
		        noteBO.setAction(true);
		        noteBO = (NoteBO)bom.checkOut(noteBO,notesCheckOutRequest.getUser());
		        retrieveSystemDomain(noteBO);
		        InformationalMessage message = new InformationalMessage("checkout.note.success.info");
		        message.setParameters(new String[] {notesCheckOutRequest.getNotesVO().getNoteId()});
		        messageList.add(message);	
		        notesCheckOutResponse.setMessages(messageList);	
				//notesCheckOutResponse.addMessage(new InformationalMessage("checkout.note.success.info"));   
				notesCheckOutResponse.setNoteBO(noteBO);
			
		} catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            notesCheckOutResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(notesCheckOutRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		}catch (WPDException e) {
			Logger.logInfo(e);
			notesCheckOutResponse.addMessage(new ErrorMessage("checkout.note.faliure.info"));			    
			List logParameters = new ArrayList(2);
			logParameters.add(notesCheckOutRequest);
			String logMessage = "Failed while processing NotesCheckoutRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		Logger.logInfo("Returning execute method for Checkout Events");
		return notesCheckOutResponse;
	}
	
	public NoteBO retrieveSystemDomain(NoteBO noteBO) throws SevereException,WPDException{
		NotesBusinessObjectBuilder builder = new NotesBusinessObjectBuilder();
		noteBO = (NoteBO)builder.retrieve(noteBO);
		
		return noteBO;
	}
	/**
	 * This method copies the values to NoteBO from the value object of NotesLifeCycleRequest.
	 * @param retrieveNotesRequest
	 * @return
	 */
	private NoteBO copyBusinessObjectFromValueObject(NotesVO notesVO) {
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteId(notesVO.getNoteId());
		noteBO.setNoteName(notesVO.getNoteName());
		noteBO.setNoteType(notesVO.getNoteType());
		noteBO.setNoteText(notesVO.getText());
		noteBO.setSystemDomain(notesVO.getSystemDomainList());
		noteBO.setStatus(notesVO.getStatus());
		noteBO.setVersion(notesVO.getVersionNo());
		return noteBO;
	}
	
	/**
	 * @param notesCheckInRequest
	 * @return
	 */
	public WPDResponse execute(NotesCheckInRequest notesCheckInRequest){
	    //	Creating CreateNotesResponse object
		NotesCheckInResponse notesCheckInResponse = (NotesCheckInResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.NOTES_CHECKIN_RESPONSE);
		List messageList = new ArrayList(3);
         
		try {
			//Creating NoteBO object by coping the values from the createNotesRequest
			NoteBO noteBO = copyBusinessObjectFromRequestObject(notesCheckInRequest);
			
			//Creating BusinessObjectManager object
			BusinessObjectManager businessObjectManager = this
			.getBusinessObjectManager();
			
			boolean action = businessObjectManager.checkIn(noteBO, notesCheckInRequest.getUser());
			if(action){
				notesCheckInResponse.setAction(true);
				InformationalMessage message = new InformationalMessage("note.checkin.success.info");
		        message.setParameters(new String[] {notesCheckInRequest.getNoteId()});
		        messageList.add(message);	
		        notesCheckInResponse.setMessages(messageList);	
				//messageList.add(new InformationalMessage(
				//"note.checkin.success.info"));
				notesCheckInResponse.setMessages(messageList);
			}else {
				notesCheckInResponse.setAction(false);
				messageList.add(new ErrorMessage(
					"note.checkin.failed.info"));
				notesCheckInResponse.setMessages(messageList);
			}
		} catch (Exception e) {
			Logger.logInfo(e);
			notesCheckInResponse.setAction(false);
		}
		
		Logger.logInfo("Returning execute method for checking in Mandate");
		return notesCheckInResponse;
		
	}
	
	/**
	 * @param notesCheckInRequest
	 * @return
	 */
	private NoteBO copyBusinessObjectFromRequestObject(NotesCheckInRequest notesCheckInRequest){
		NoteBO noteBO = new NoteBO();
		noteBO.setNoteId(notesCheckInRequest.getNoteId());
		noteBO.setNoteName(notesCheckInRequest.getName());
		noteBO.setNoteText(notesCheckInRequest.getText());
		noteBO.setNoteType(notesCheckInRequest.getType());
		noteBO.setStatus(notesCheckInRequest.getStatus());
		noteBO.setVersion(notesCheckInRequest.getVersion());
		noteBO.setSystemDomain(notesCheckInRequest.getSystemDomain());
		noteBO.setAction(notesCheckInRequest.isCheckInFlag());
		return noteBO;
		
	}

	
	/**
	 * @param viewAllVersionsNotesRequest
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(ViewAllVersionsNotesRequest viewAllVersionsNotesRequest)
	throws ServiceException {
		ViewAllVersionsNotesResponse viewAllVersionsNotesResponse = (ViewAllVersionsNotesResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.NOTES_ALLVERSIONS_RESPONSE);
		NotesLocateCriteria notesLocateCriteria = new NotesLocateCriteria();
		notesLocateCriteria.setAction("viewAllVersions");
		notesLocateCriteria.setNoteId(viewAllVersionsNotesRequest.getNoteId());
		notesLocateCriteria.setNoteName(viewAllVersionsNotesRequest.getNoteName());
		BusinessObjectManager businessObjectManager = this
		.getBusinessObjectManager();
		try {
			LocateResults locateResults = businessObjectManager.locate(notesLocateCriteria,
					viewAllVersionsNotesRequest.getUser());
			List allVersionList = locateResults.getLocateResults();
			viewAllVersionsNotesResponse.setAllVersionList(allVersionList);
		} catch(SecurityException exception){
            ErrorMessage em = new ErrorMessage(BusinessConstants.MSG_SECURITY_INVALID);
            em.setParameters(new String[]{(String)exception.getLogParameters().get(1)});
            viewAllVersionsNotesResponse.addMessage(em);
            List logParameters = new ArrayList(2);
            logParameters.add(viewAllVersionsNotesRequest);
            ServiceException se = new ServiceException(
                    "Security Exception while processing service",
                    logParameters, exception);
            Logger.logException(se);
		}catch (WPDException e) {
			List logParameters = new ArrayList(2);			
			String logMessage = "Failed while processing NotesLifeCycleRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		return viewAllVersionsNotesResponse;
	}

	
	/**
	 * This method is used to get the target system list for the notes from the table 
	 * @param request
	 * @return
	 * @throws WPDException
	 */
	public WPDResponse execute(LocateTargetSystemsForNotesRequest request) throws WPDException{
	    // create the response reference
	    LocateTargetSystemsForNotesResponse notesResponse = null;
	    // get the the business object manager object
	    BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
						.getFactory(ObjectFactory.BOM);
	    BusinessObjectManager bom = bomf.getBusinessObjectManager();
	    // create the locate criteria object
	    NotesLocateCriteriaForView locateCriteriaForView = new NotesLocateCriteriaForView();
	    locateCriteriaForView.setNotesId(request.getNotesId());
	    
	    // create the response object
	    notesResponse = (LocateTargetSystemsForNotesResponse)WPDResponseFactory.
							getResponse(WPDResponseFactory.LOCATE_TARGET_SYSTEM_FOR_NOTES_RESPONSE);
	    // call the locateMandateList method to get the mandateList
	    LocateResults locateResults =  bom.locate(locateCriteriaForView, request.getUser());
	    // set the mandateList in the response
	    notesResponse.setTargetSystemsList(locateResults.getLocateResults());	 
	    // return the response
	    return notesResponse;
	}
	
	/**
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(DeleteNotesRequest request)
			throws WPDException {
	    // create the response reference
		DeleteNotesResponse deleteNotesResponse = null;
		// get the business object manager
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory)
								ObjectFactory.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		// create the response object
		deleteNotesResponse = (DeleteNotesResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_NOTES_RESPONSE);		
		
		if(request.isDataDomainDelete()){
			NoteBO noteBO = new NoteBO();
			NoteDomainDeleteBO noteDomainDeleteBO = new NoteDomainDeleteBO();
			NoteDomainDeleteBO noteDomainDeleteBO2 = null;
			NoteDomainVO noteDomainVO = null;
			List noteDomains = request.getNoteDomains();
			Iterator iterator = noteDomains.iterator();
			List noteDomains2 = new ArrayList();
			while(iterator.hasNext()){
				noteDomainVO = (NoteDomainVO)iterator.next();
				noteDomainDeleteBO2 = new NoteDomainDeleteBO();
				noteDomainDeleteBO2.setNoteId(noteDomainVO.getNotesId());
				noteDomainDeleteBO2.setEntityId(noteDomainVO.getDomainId());
				noteDomainDeleteBO2.setEntityType(noteDomainVO.getDomainType());
				noteDomainDeleteBO2.setVersion(request.getVersionNumber());
				noteDomains2.add(noteDomainDeleteBO2);
			}
			noteDomainDeleteBO.setNoteDomains(noteDomains2);
			noteBO.setNoteId(request.getNotesId());
			noteBO.setVersion(request.getVersionNumber());
			noteBO.setNoteName(request.getNotesName());
			noteBO.setAction(true);
			bom.delete(noteDomainDeleteBO, noteBO,request.getUser());
			deleteNotesResponse = (DeleteNotesResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.DELETE_NOTES_RESPONSE);
			deleteNotesResponse.setState(request.getState());
			deleteNotesResponse.addMessage(new InformationalMessage(
				"notes.datadomain.delete.success.info"));
			
		}else{
			// create the main obect
			NoteBO noteBO = new NoteBO();
			// set the required things to the main object 
			noteBO.setNoteName(request.getNotesName());
			noteBO.setNoteId(request.getNotesId());
			noteBO.setVersion(request.getVersionNumber());
			// call the delete method in the bom
			bom.delete(noteBO, request.getUser());
			// set the message in teh response
			InformationalMessage informationalMessage = new InformationalMessage("notes.delete");
			informationalMessage.setParameters(new String[]{noteBO.getNoteId()});
			deleteNotesResponse.addMessage(informationalMessage);
		}
		// return the response
		return deleteNotesResponse;
	}
	
	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public WPDResponse execute(NotesCopyRequest request) throws ServiceException{
		Logger.logInfo("Entering execute method for Notes Copy");
		NotesCopyResponse notesCopyResponse = (NotesCopyResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.NOTES_COPY_RESPONSE);
		NoteBO sourceNoteBO = copyBusinessObjectFromValueObject(request.getNotesVO());
		NoteBO targetNoteBO = copyBusinessObjectFromValueObject(request.getNotesVO());
		BusinessObjectManager bom = this.getBusinessObjectManager();
		List messageList = new ArrayList(3);
		try{
			notesCopyResponse = (NotesCopyResponse) new ValidationServiceController()
			.execute(request);
			if(!(notesCopyResponse.isSuccess())){
				bom.persist(targetNoteBO,request.getUser(),true);
				bom.copy(sourceNoteBO,targetNoteBO,request.getUser());
		        retrieveSystemDomain(targetNoteBO);
		        InformationalMessage message = new InformationalMessage("copy.note.success.info");
		        message.setParameters(new String[] {sourceNoteBO.getNoteId()});
		        messageList.add(message);	
		        notesCopyResponse.setMessages(messageList);	
				//notesCopyResponse.addMessage(new InformationalMessage("copy.note.success.info"));   
			}
			notesCopyResponse.setNoteBO(targetNoteBO);
			
		}catch (WPDException e) {
			Logger.logInfo(e);
			notesCopyResponse.addMessage(new ErrorMessage("copy.note.faliure.info"));			    
			List logParameters = new ArrayList(2);
			logParameters.add(notesCopyResponse);
			String logMessage = "Failed while processing NotesCopyRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		return notesCopyResponse;
	}
	/**
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(QuestionNotesPopUpRequest request) throws SevereException{
		List messageList = null;
		
		QuestionNotesPopUpResponse response =(QuestionNotesPopUpResponse)WPDResponseFactory.
		getResponse(WPDResponseFactory.QUESTION_NOTES_POPUP_RESPONSE);
		NotesAttachmentOverrideBO overrideBO = new NotesAttachmentOverrideBO();
		overrideBO.setSecondaryEntityType(request.getSecondaryEntityType());
		overrideBO.setPrimaryEntityId(new Integer(request.getPrimaryEntityID()).intValue());
		overrideBO.setPrimaryEntityType(request.getPrimaryType());
		if(null != request.getSecondaryId()){
		    overrideBO.setSecondaryEntityId(new Integer(request.getSecondaryId()).intValue());
		}		
		overrideBO.setBnftDefnIdString(request.getBenefitDenId());
		overrideBO.setBenefitComponentId(request.getBenefitComponentId());
		overrideBO.setQuestionNumber(request.getQuestionId());
		if(request.getSearchAction()==2){
			overrideBO.setSearchString(request.getSearchString());	
			overrideBO.setSearchAction(2);
		}
		NotesBusinessObjectBuilder builder = new NotesBusinessObjectBuilder();
		List noteList = builder.retrieveQuestionNotes(overrideBO);
		List newNoteList = new ArrayList();
		if(null==noteList ||noteList.size()==0){
		    response.setNotesList(null);
		    response.setRecordsGrtThanMaxSize(false);
    	  	messageList = new ArrayList(2);
    	  	if(request.getSearchAction()==1){
    	  		messageList.add(new InformationalMessage(
        				BusinessConstants.RECORDS_NOT_FOUND));
    	  	}else {
    	  		messageList.add(new InformationalMessage(
        				BusinessConstants.NO_RESULT_FOR_FILTER_POPUP));
    	  	}
    	  }
		else if(null != noteList && noteList.size()!=0){
		    messageList = new ArrayList(2);
		    response.setNotesList(noteList);
		    if(noteList.size()> BusinessConstants.MAX_SEARCH_RESULT_SIZE){ 
		        response.setRecordsGrtThanMaxSize(true);//for search result exceed msg in question notes popup
				Iterator iterator = noteList.iterator();
				int count = 0;
				while(iterator.hasNext()){						    
				    NotesAttachmentOverrideBO  noteAttachBO = (NotesAttachmentOverrideBO)iterator.next();
					count++;
					newNoteList.add(noteAttachBO);
					if(count >= BusinessConstants.MAX_SEARCH_RESULT_SIZE){
						break; 
					}
				}				 
				response.setNotesList(newNoteList);
				messageList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));	
		    }
		}
		addMessagesToResponse(response, messageList);
		//if(null!=noteList)
			//response.setNotesList(noteList);
		return response;
		
	}
	 /**
     * Method to set the list of messages to response.
     * 
     * @param response
     *            WPDResponse
     * @param messages
     *            List.
     */
    private void addMessagesToResponse(WPDResponse response, List messages)
    {
      if (null == messages || messages.size() == 0)
        return;
      if (null == response.getMessages())
        response.setMessages(messages);
      else
        response.getMessages().addAll(messages);
    }
}
