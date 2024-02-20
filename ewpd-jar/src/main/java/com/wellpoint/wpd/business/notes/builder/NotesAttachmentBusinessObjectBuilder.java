/*
 * Created on May 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.notes.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.lookup.locateCriteria.NotesLookUpLocateCriteria;
import com.wellpoint.wpd.business.lookup.locateCriteria.NotesOverrideLocateCriteria;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;

/**
 * @author US Technology
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NotesAttachmentBusinessObjectBuilder {

	/**
	 * Method to locate the notes that can be attached to the various entities
	 * 
	 * @param locateCriteria
	 * @return List
	 * @throws SevereException
	 */
	public List locate(NotesLookUpLocateCriteria locateCriteria) throws SevereException{
		
	    //	  Create an instance of the Adapter Manager
		NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();
		// List to store values from notesOverride table
		
			// call the method in the adapter to get values from the override table
			List overrideList = adapterManager.locateNotesToAttach(locateCriteria);
		

		return overrideList;	
	}
	
	/**
 	 * Method to delete the notes attached to a benefit level of the associated standard 
 	 * benefit for overriding
 	 * @param overrideBO
 	 * @param componentBO
 	 * @param audit
 	 * @return
 	 * @throws SevereException
 	 */
 	public void persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit,boolean insertflag) throws SevereException{
 	 
 	    // Create an instance of the adapter manager
 	   AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
 	   long transactionId = AdapterUtil.getTransactionId();
 	    List persistNotesList = (List) overrideBO.getNotesList();
 	    try{
 	    	AdapterUtil.beginTransaction(adapterServicesAccess);
 	    	//For logging adapter information
 	    	
 	    	AdapterUtil.logBeginTranstn(transactionId,this,"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit,boolean insertflag)");
		 	    if(null != persistNotesList && !persistNotesList.isEmpty()){
		 	    	NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();
		 	        for(int i = 0 ; i < persistNotesList.size() ; i++){
		 	            NotesAttachmentOverrideBO overrideNotesBO = (NotesAttachmentOverrideBO)
							persistNotesList.get(i);
		 	            
		 	            	overrideNotesBO.setPrimaryEntityId(overrideBO.getPrimaryEntityId());
		 	            	overrideNotesBO.setPrimaryEntityType(overrideBO.getPrimaryEntityType());
		 	            	overrideNotesBO.setSecondaryEntityId(overrideBO.getSecondaryEntityId());
		 	            	overrideNotesBO.setSecondaryEntityType(overrideBO.getSecondaryEntityType());
		 	            	overrideNotesBO.setBenefitComponentId(overrideBO.getBenefitComponentId());
		 	            	adapterManager.attachNotesForOverrideEntity(overrideNotesBO,audit,true, adapterServicesAccess);
		 	            }
		 	    }
        AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId,this,"persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit,boolean insertflag)");
 	    }catch(SevereException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this, "persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit,boolean insertflag)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the NotesAttachmentOverrideBO in NotesAttachmentBusinessObjectBuilder",
                    errorParams, ex);	
 	    } catch(AdapterException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this, "persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit,boolean insertflag)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the NotesAttachmentOverrideBO in NotesAttachmentBusinessObjectBuilder",
                    errorParams, ex);	
 	    }catch (Exception e){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId,this, "persist(NotesAttachmentOverrideBO overrideBO,Contract contract, Audit audit,boolean insertflag)");
            throw new SevereException("Unhandled exception is caught",null,e);

        }
 	    
 	}
 	/**
	 * Method to locate the notes that can be attached to the various entities
	 * 
	 * @param locateCriteria
	 * @return List
	 * @throws SevereException
	 */
 	public LocateResults locate(NotesOverrideLocateCriteria notesOverrideLocateCriteria,User user) throws SevereException{
		
	    //	  Create an instance of the Adapter Manager
		NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();

        int primaryEntityId = notesOverrideLocateCriteria
                .getPrimaryEntityId();
        String primaryEntityType = notesOverrideLocateCriteria
                .getPrimaryEntityType();
        int secondaryEntityId = notesOverrideLocateCriteria
                .getSecondaryEntityId();
        String secondaryEntityType = notesOverrideLocateCriteria
                .getSecondaryEntityType();
        int benefitComponentId = notesOverrideLocateCriteria
                .getBenefitComponentId();
       LocateResults locateResults = new LocateResults();
       try{
       locateResults=notesAttachmentAdapterManager.locateAttachedNotesForOverride(
            primaryEntityId, primaryEntityType, secondaryEntityId,
            secondaryEntityType, benefitComponentId);
       }catch (SevereException se) {			
		List errorParams = new ArrayList(3);
		String obj = se.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in locate NotesOverrideLocateCriteria method in NotesAttachmentBusinessObjectBuilder",
				errorParams, se);
	} catch (AdapterException ae) {
		List errorParams = new ArrayList(3);
		String obj = ae.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in locate NotesOverrideLocateCriteria method in NotesAttachmentBusinessObjectBuilder",
				errorParams, ae);
	} catch (Exception ex) {
		List errorParams = new ArrayList(3);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in locate NotesOverrideLocateCriteria method in NotesAttachmentBusinessObjectBuilder",
				null, ex);
	}
       return locateResults;
	}
	
}
