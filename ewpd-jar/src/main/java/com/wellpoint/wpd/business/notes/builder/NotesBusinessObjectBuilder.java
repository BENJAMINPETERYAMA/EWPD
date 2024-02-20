/*
 * Created on May 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.notes.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.notes.adapter.NotesAdapterManager;
import com.wellpoint.wpd.business.notes.locatecriteria.NotesLocateCriteria;
import com.wellpoint.wpd.business.notes.locatecriteria.NotesLocateCriteriaForView;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainDeleteBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;


/**
 * @author u11543
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesBusinessObjectBuilder {
	
	NotesAdapterManager notesAdapterManager;
	
	public NotesBusinessObjectBuilder(){
		notesAdapterManager = new NotesAdapterManager();
	}
	

    /**

	
	 * Method to persist notes.
	 * @param noteBO
	 * @param audit
	 * @param insertFlag
	 * @return
	 * @throws SevereException
	
	 */
	public void persist(NoteBO noteBO, Audit audit, boolean insertFlag)
	        throws SevereException  {
	    if (insertFlag) {
	        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
			Logger.logInfo("Getting the next Notes Sequence Number.");
			
			// get the sequence note id from the sequence adapter manager
			if(noteBO.getVersion() < 1){
				int seqNoteId = sequenceAdapterManager.getNextNotesSequence();
				// convert to string and concat with "N" in the front
				String noteId = "N" + lpad((new Integer(seqNoteId)).toString(), 5);
				noteBO.setNoteId(noteId);
			}
			if(!BusinessConstants.CHECKED_OUT.equals(noteBO.getStatus())) {
				noteBO.setNoteParentId(noteBO.getNoteId());
			}
			noteBO.setCreatedUser(audit.getUser());
			noteBO.setCreatedTimestamp(audit.getTime());
			noteBO.setLastUpdatedUser(audit.getUser());
			noteBO.setLastUpdatedTimestamp(audit.getTime());
			noteBO.setLegacyIndicator("N");
	        this.notesAdapterManager.persistNotes(noteBO, audit);
	    } else if (!insertFlag) {
	    	if(noteBO.getVersion() == 1 && noteBO.getStatus().equals(BusinessConstants.MSG_CHECKED_IN)){
	    		NoteBO noteSourceBo = new NoteBO();
	    		noteSourceBo.setNoteId(noteBO.getNoteId());
	    		noteSourceBo.setVersion(0);
				if(!BusinessConstants.CHECKED_OUT.equals(noteBO.getStatus())) {
					noteBO.setNoteParentId(noteBO.getNoteId());
				}
				
				noteBO.setLastUpdatedUser(audit.getUser());
				noteBO.setLastUpdatedTimestamp(audit.getTime());
				noteBO.setLegacyIndicator("N");
		        this.notesAdapterManager.persistNotes(noteBO, audit);
		        this.notesAdapterManager.copyNotesAssociations(noteSourceBo,noteBO,audit);
		        this.notesAdapterManager.deleteNotes(noteSourceBo,audit);
			}else{
		        // update logic
		        this.updateNotes(noteBO, audit);
	    	}
	    }
	}
	/**
	 * 
	 * @param noteID
	 * @return
	 * @throws SevereException
	 */
	public boolean isNotesInActivelyUsedStatus(String noteID)throws SevereException {
		return notesAdapterManager.isNotesInActivelyUsedStatus(noteID);
	}
	
	private void updateNotes(NoteBO noteBO, Audit audit) throws SevereException {
		String user = audit.getUser();
		java.util.Date currentTime = audit.getTime();		
		noteBO.setLastUpdatedUser(user);
		noteBO.setLastUpdatedTimestamp(currentTime);
		//FIXME remove all hardcoded values
		if(BusinessConstants.MSG_SCHEDULED_FOR_TEST.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_TEST_SUCCESSFUL.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_TEST_FAILED.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_PUBLISHED.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_MARKED_FOR_DELETION.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_CHECKED_IN.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_APPROVED.equals(noteBO.getStatus()) 
				|| BusinessConstants.MSG_REJECTED.equals(noteBO.getStatus()) ){
			this.notesAdapterManager.updateNotes(noteBO, user);
		}
		else{
			AdapterServicesAccess serviceAccessEWPDB = AdapterUtil.getAdapterService();
			long transactionId = AdapterUtil.getTransactionId();
			String methodName = "updateNotes(NoteBO noteBO, Audit audit)";
			try{
			    AdapterUtil.beginTransaction(serviceAccessEWPDB);
			    AdapterUtil.logBeginTranstn(transactionId , this ,methodName);
				String domain = BusinessConstants.SYSTEM_DOMAIN;
				
				this.notesAdapterManager.updateNotes(noteBO, user, serviceAccessEWPDB);
				this.deleteSystemDomainsForNotes(noteBO, domain, user, serviceAccessEWPDB);
				this.persistDomainValuesForNotes(this.copyValues(noteBO), domain, audit, serviceAccessEWPDB);
				
				AdapterUtil.endTransaction(serviceAccessEWPDB);
				AdapterUtil.logTheEndTransaction(transactionId , this ,methodName);
		    }catch(AdapterException ex){
		        AdapterUtil.abortTransaction(serviceAccessEWPDB);
		        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
		        List errorParams = new ArrayList(3);
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                "Exception occured in update method , for updating the BusinessObject NoteBO, in NotesBusinessObjectBuilder",
		                errorParams, ex);
			}catch (SevereException ex) {
			    AdapterUtil.abortTransaction(serviceAccessEWPDB);
			    AdapterUtil.logAbortTxn(transactionId , this ,methodName);
		        List errorParams = new ArrayList(3);
		        String obj = ex.getClass().getName();
		        errorParams.add(obj);
		        errorParams.add(obj.getClass().getName());
		        throw new SevereException(
		                "Exception occured in update method , for updating the BusinessObject NoteBO, in NotesBusinessObjectBuilder",
		                errorParams, ex);
		    } catch (Exception e){
		        AdapterUtil.abortTransaction(serviceAccessEWPDB);
		        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
		        throw new SevereException("Unhandled exception is caught",null,e);
		    }			
		}
	}
	
	private void deleteSystemDomainsForNotes(NoteBO noteBO, String domainName, String user, AdapterServicesAccess adapterServicesAccess) throws SevereException{
		NoteDomainBO noteDomainBO = new NoteDomainBO();
		noteDomainBO.setNoteId(noteBO.getNoteId());
		noteDomainBO.setEntityType(domainName);
		noteDomainBO.setSystemDomainIds((List) noteBO.getSystemDomain());
		noteDomainBO.setVersion(noteBO.getVersion());
		noteDomainBO.setSystemDomainId("0");
		this.notesAdapterManager.deleteSystemDomainsForNotes(noteDomainBO, user, adapterServicesAccess );
	}
	
	private void persistDomainValuesForNotes(NoteDomainBO noteDomainBO, String domainName, Audit audit, AdapterServicesAccess adapterServicesAccess) throws SevereException, AdapterException {
		String user = audit.getUser();
		java.util.Date currentTime = audit.getTime();
		noteDomainBO.setEntityType(domainName);
		noteDomainBO.setCreatedUser(user);
		noteDomainBO.setCreatedTimestamp(currentTime);
		noteDomainBO.setLastUpdatedUser(user);
		noteDomainBO.setLastUpdatedTimestamp(currentTime);
		noteDomainBO.setSystemDomainId("0");
		//this.notesAdapterManager.persistDomainValuesForNotes(noteDomainBO, user, adapterServicesAccess);
		this.notesAdapterManager.persistDomainValuesForNotes(noteDomainBO,domainName,audit,adapterServicesAccess);
	}
	
	private NoteDomainBO copyValues(NoteBO noteBO) {
		NoteDomainBO noteDomainBO = new NoteDomainBO();
		noteDomainBO.setNoteId(noteBO.getNoteId());
		noteDomainBO.setNoteName(noteBO.getNoteName());
		noteDomainBO.setNoteType(noteBO.getNoteType());
		noteDomainBO.setNoteText(noteBO.getNoteText());
		noteDomainBO.setSystemDomainIds((List) noteBO.getSystemDomain());
		noteDomainBO.setVersion(noteBO.getVersion());
		return noteDomainBO;
	}
	
	/**
	 * Function to Padding 0 in the left 
	 * @param seqNo
	 * @return
	 */
	private String lpad(String seqNo, int maxLength){
		for(int i = seqNo.length(); i < maxLength; i++){
			seqNo = "0" + seqNo;
		}
		return seqNo;
	}
    
    /**
     *  Method to persist Notes Data Domain.
     * 
     * @param noteDomainBO
     * @param audit
     * @param insertFlag
     * @return
     * @throws SevereException
     */
    public void persist(NoteDomainBO noteDomainBO, NoteBO noteBO, Audit audit, boolean insertFlag)
            throws SevereException  {
        if (insertFlag) {
            // insert logic
            this.persistNotesDataDomain(noteDomainBO, audit);
        }
    }
    
    
    private void persistNotesDataDomain(NoteDomainBO noteDomainBO, Audit audit) throws SevereException {
		//deleteDataDomainsForNotes(noteDomainBO,audit);
		List termList = (List) noteDomainBO.getNotesDataDomainLists().get("term");
		List qualifierList = (List) noteDomainBO.getNotesDataDomainLists().get("qualifier");
		List productList = (List) noteDomainBO.getNotesDataDomainLists().get("product");
		List benefitList = (List) noteDomainBO.getNotesDataDomainLists().get("benefit");
		List benefitComponentList = (List) noteDomainBO.getNotesDataDomainLists().get("benefitcomponent");
		List questionList = (List) noteDomainBO.getNotesDataDomainLists().get("question");
	
		if( null != termList && 0 != termList.size()){
			String term = "AVAILTERM";
			String queryName="checkForDuplicateTerm";
			this.getResultsFromLists(termList,noteDomainBO,audit,term,queryName);
		}
		if( null != benefitComponentList && 0 != benefitComponentList.size()){
			String benefitComponent = "AVAILCOMP";
			String queryName="checkForDuplicateComp";
			this.getResultsFromLists(benefitComponentList,noteDomainBO,audit,benefitComponent,queryName);		
		}
		if( null != qualifierList && 0 != qualifierList.size()){
			String qualifier = "AVAILQUALIFIER";
			String queryName="checkForDuplicateQualifier";
			this.getResultsFromLists(qualifierList,noteDomainBO,audit,qualifier,queryName);		
		}
		if( null != productList && 0 != productList.size()){
			String product = "AVAILPRODUCT";
			String queryName="checkForDuplicateProduct";
			this.getResultsFromLists(productList,noteDomainBO,audit,product,queryName);	
		}
		if( null != benefitList && 0 != benefitList.size()){
			String benefit = "AVAILBENEFIT";
			String queryName="checkForDuplicateBenefit";
			this.getResultsFromLists(benefitList,noteDomainBO,audit,benefit,queryName);
		}
		if( null != questionList && 0 != questionList.size()){
			String question = "AVAILQUESTION";
			String queryName="checkForDuplicateQuestion";
			this.getResultsFromLists(questionList,noteDomainBO,audit,question,queryName);
		}
	}
	
	private void getResultsFromLists(List list,NoteDomainBO noteDomainBO, Audit audit,String entityType,String queryName)throws SevereException {
		HashMap criteriaMap = new HashMap();
		SearchCriteria searchCriteria = null;//
		SearchResults searchResults = null;

		Iterator iterator = list.iterator();

		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		String methodName = "persistDomainValuesForNotes(noteDomainBO,entityType,audit)";
		try{
		    AdapterUtil.beginTransaction(serviceAccessEWPDB);
		    AdapterUtil.logBeginTranstn(transactionId , this ,methodName);
			
			while(iterator.hasNext()){
				String value = (String)iterator.next();
				criteriaMap.put("entityId", value);
				criteriaMap.put("noteId",noteDomainBO.getNoteId());
				criteriaMap.put("version",new Integer(noteDomainBO.getVersion()));
				criteriaMap.put("entityType", entityType);			
				List resultList= new ArrayList(3);
				if(!this.notesAdapterManager.checkNoteDomain(queryName, criteriaMap, serviceAccessEWPDB)){				
					resultList.add(value);
					noteDomainBO.setSystemDomainIds(resultList);
					this.notesAdapterManager.persistDomainValuesForNotes(noteDomainBO, entityType, audit, serviceAccessEWPDB);	
					resultList.clear();
				}
			}
		
			AdapterUtil.endTransaction(serviceAccessEWPDB);
			AdapterUtil.logTheEndTransaction(transactionId , this ,methodName);
		}catch (SevereException ex) {
		    AdapterUtil.abortTransaction(serviceAccessEWPDB);
		    AdapterUtil.logAbortTxn(transactionId , this ,methodName);
	        List errorParams = new ArrayList(3);
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                "Exception occured in persist method , for persisting the BusinessObject NoteDomainBO, in NotesBusinessObjectBuilder",
	                errorParams, ex);
	    }catch(AdapterException ex){
	        AdapterUtil.abortTransaction(serviceAccessEWPDB);
	        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
	        List errorParams = new ArrayList(3);
	        String obj = ex.getClass().getName();
	        errorParams.add(obj);
	        errorParams.add(obj.getClass().getName());
	        throw new SevereException(
	                "Exception occured in persist method , for persisting the BusinessObject NoteDomainBO, in NotesBusinessObjectBuilder",
	                errorParams, ex);
	    } catch (Exception e){
	        AdapterUtil.abortTransaction(serviceAccessEWPDB);
	        AdapterUtil.logAbortTxn(transactionId , this ,methodName);
	        throw new SevereException("Unhandled exception is caught",null,e);
	    }
	}
	
    /**
     * Method to retrieve the Notes.
     * 
     * @param noteBO
     * @return
     * @throws WPDException
     * @throws SevereException
     */
    public NoteBO retrieve(NoteBO noteBO) throws WPDException,SevereException  {
    	//FIXME it's ok but use like: 
    	if(null != noteBO.getCompareAction() 
    			&& noteBO.getCompareAction().equalsIgnoreCase(BusinessConstants.COMPARE)){
	    		noteBO = this.notesAdapterManager.retrieveByNoteNameForCompare(noteBO);	    	
    	}else{
    		noteBO =  (NoteBO) this.notesAdapterManager.retrieve(noteBO);
    	}
    	return noteBO;
    }
    

    /**
     * Method to locate the result
     * 
     * @param notesLocateCriteria
     * @param user
     * @return
     * @throws WPDException
     * @throws SevereException
     */ 
    public LocateResults locate(NotesLocateCriteria notesLocateCriteria, User user) throws  SevereException  {
        LocateResults locateResults = null;
        if(BusinessConstants.PRODUCT_LOWER.equals(notesLocateCriteria.getAction())){		
        	locateResults = notesAdapterManager.locateProdcuts(notesLocateCriteria, user);
        	if(null==locateResults && notesLocateCriteria.getNoteName().length() > 0){
        	     notesLocateCriteria.setNoteName("%");
        	     locateResults = notesAdapterManager.locateProdcuts(notesLocateCriteria, user); 
             }
        }else if(BusinessConstants.BENEFIT.equals(notesLocateCriteria.getAction())){
        	locateResults = notesAdapterManager.locateBenefits(notesLocateCriteria, user);
        	if(null==locateResults && notesLocateCriteria.getNoteName().length() > 0){
        	     notesLocateCriteria.setNoteName("%");
        	     locateResults = notesAdapterManager.locateBenefits(notesLocateCriteria, user); 
             }
        }else if(BusinessConstants.COMPONENT.equals(notesLocateCriteria.getAction())){
        	locateResults = notesAdapterManager.locateComponents(notesLocateCriteria, user);
        	if(null==locateResults && notesLocateCriteria.getNoteName().length() > 0){
        	     notesLocateCriteria.setNoteName("%");
        	     locateResults = notesAdapterManager.locateComponents(notesLocateCriteria, user); 
             }
        }else if(BusinessConstants.QUESTION.equals(notesLocateCriteria.getAction())){
        	locateResults = notesAdapterManager.locateQuestions(notesLocateCriteria, user);
        	if(null==locateResults && notesLocateCriteria.getNoteName().length() > 0){
        	     notesLocateCriteria.setNoteName("%");
        	     locateResults = notesAdapterManager.locateQuestions(notesLocateCriteria, user); 
             }        
        }else if(BusinessConstants.DATA_DOMAINS.equals(notesLocateCriteria.getAction())){
        	locateResults = notesAdapterManager.locateNotesDataDomain(notesLocateCriteria, user);
        }else if(BusinessConstants.VIEW_ALL_VERSIONS.equals(notesLocateCriteria.getAction())){
        	locateResults = notesAdapterManager.getAllVersions(notesLocateCriteria, user);
        }else{
        	locateResults = notesAdapterManager.locateNotes(notesLocateCriteria, user);
        	if(null!=locateResults)
        		locateResults.setLatestVersion(true);
        }
        //@info framework not allows as to return null locate result 
        if(null == locateResults){
        	locateResults = new LocateResults();
        	locateResults.setLocateResults(new ArrayList());
        }
        return locateResults;
    }
    
    /**
     * Method to retrieve Latest Version
     * 
     * @param noteBO
     * @return
     * @throws SevereException
     */
    
    public BusinessObject retrieveLatestVersion(NoteBO noteBO)
            throws SevereException  {
    	if(noteBO.isAction()){
    		return (BusinessObject) this.notesAdapterManager.retrieveLatestVersionForCheckIn(noteBO);
    	}
        return (BusinessObject) this.notesAdapterManager.retrieveLatestVersionByNoteName(noteBO);
    }
    
    /**
     * Method to retrieve Latest Version
     * 
     * @param noteBO
     * @return
     * @throws SevereException
     */
    public int retrieveLatestVersionNumber(NoteBO noteBO) throws SevereException  {
    	noteBO = (NoteBO)this.notesAdapterManager.retrieveLatestVersionNumber(noteBO);
    	return noteBO.getVersion();
    }

    /**
     * Method to retrieve the Note details using noteName.
     * 
     * @param noteBO NoteBO
     * @param user User
     * @return noteBO NoteBO
     */
    public NoteBO retrieveByNoteName(NoteBO noteBO, User user)throws SevereException  {
        return this.notesAdapterManager.retrieveByNoteName(noteBO);
    }
        
    /**
     * @param noteBO1
     * @param noteBO
     * @param audit
     * @return
     * @throws SevereException
     */
    public void copy(NoteBO noteBO1, NoteBO noteBO, Audit audit) throws SevereException{
    	this.notesAdapterManager.copyNotesAssociations(noteBO1, noteBO, audit);
    }
    
    
	
    /**
     * @param noteBO1
     * @param noteBO
     * @param audit
     * @return
     * @throws SevereException
     */
    public void delete(NoteBO noteBO, Audit audit) throws SevereException{
		this.notesAdapterManager.deleteNotesAssociation(noteBO, audit);
    }
    /**
     * 
     * @param sourceNoteBO
     * @param targetNoteBO
     * @param audit
     * @throws SevereException
     */
	public void copyForCheckOut(NoteBO sourceNoteBO, NoteBO targetNoteBO, Audit audit)throws SevereException{
		this.notesAdapterManager.copyNotesAssociations(sourceNoteBO,targetNoteBO,audit);
	}
	
	/**
	 * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#locate(com.wellpoint.wpd.common.bo.LocateCriteria)
	 * @param locateCriteria
	 * @return
	 * @throws WPDException
	 */
	public LocateResults locate(NotesLocateCriteriaForView locateCriteria,User user) throws SevereException {
		NotesLocateCriteriaForView notesLocateCriteriaForView = (NotesLocateCriteriaForView)locateCriteria;
		return this.notesAdapterManager.locateTargetSystemsForNotes(notesLocateCriteriaForView.getNotesId());
	}
	
	/**
	  * 
	  * @param subObject
	  * @param mainObject
	  * @param audit
	  * @return
	  * @throws WPDException
	  */
	 public void deleteLatestVersion(NoteBO mainObject, Audit audit) throws WPDException {
       // call the hideQuestion method in the adaptermanager 
	   this.notesAdapterManager.deleteNotes(mainObject, audit);
	}
	 /**
	  * 
	  * @param noteDomainDeleteBO
	  * @param mainObject
	  * @param audit
	  * @throws SevereException
	  */
	 public void delete(NoteDomainDeleteBO noteDomainDeleteBO, NoteBO mainObject, Audit audit) throws SevereException{
	 	this.notesAdapterManager.deleteNoteDataDomain(noteDomainDeleteBO, audit);
	 }
	 
	    /**
	     * Method to retrieve the Note details using noteName.
	     * 
	     * @param noteBO NoteBO
	     * @param user User
	     * @return noteBO NoteBO
	     */
	    public NoteBO retrieveByNoteNameForEdit(NoteBO noteBO, User user)throws SevereException  {
	        return this.notesAdapterManager.retrieveByNoteNameForEdit(noteBO);
	    }

	    
	    /**
	     * Method to handle the identity change of product.
	     * 
	     * @param oldProduct
	     * @param product
	     * @param audit
	     * @return
	     * @throws SevereException
	     */
	    public void changeIdentity(NoteBO oldNoteBO, NoteBO noteBO, Audit audit) throws SevereException {
	        this.updateNotes(noteBO, audit);
	    }

	    /**
	     * Method to update time stamp
	     * 
	     * @return boolean
	     */
		 public boolean persistTimeStamp(NoteBO noteBO, Audit audit) throws SevereException {
		 	NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
			try {
				
				noteBO.setLastUpdatedUser(audit.getUser());
				noteBO.setLastUpdatedTimestamp(audit.getTime());				
				
				notesAdapterManager.updateNotes(
						noteBO,audit.getUser());
		
			} catch (Exception ex) {
				throw new SevereException("Unhandled exception occured", null, ex);
			}

			return true;
		}
		 public List retrieveQuestionNotes(NotesAttachmentOverrideBO overrideBO)throws SevereException{
		 	List noteList = null;
		 	NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		 	noteList=notesAdapterManager.getQuestionNote(overrideBO);
		 	return noteList;
		 	
		 }
}
