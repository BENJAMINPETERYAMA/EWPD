/*
 * ContractBenefitComponentNotesBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.contract.request.ContractBenefitComponentAttachNotesRequest;
import com.wellpoint.wpd.common.contract.response.LoadContractComponentNoteResponse;
import com.wellpoint.wpd.common.contract.request.LoadContractComponentNoteRequest;
import com.wellpoint.wpd.common.contract.response.ContractBenefitComponentAttachNotesResponse;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitComponentNotesBackingBean extends ContractBackingBean {

    ArrayList validationMessages = null;
    private String noteId;
	private String noteName = "" ;
	boolean validationStatus = false;
	private String benefitComponentNoteId;
	List noteList = null;
	private String  versionToDelete ;
	private boolean requiredNoteName = false;
	private boolean securityAccess;
	private String  loadForPrint;
	 
	 private String hiddenNoteList;
	
    public ContractBenefitComponentNotesBackingBean(){
        super();
        this.setBreadCrumbText();
    }
    public String loadNotes(){
    	validationMessages = new ArrayList();
		
		if(getContractSession().getContractKeyObject().getContractType().equalsIgnoreCase(WebConstants.MANDATE) && !super.isViewMode()){
			validationMessages.add(new InformationalMessage(
	                   WebConstants.BENEFIT_COMPONENT_MANDATE_NOTE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		LoadContractComponentNoteRequest loadContractComponentNoteRequest =(LoadContractComponentNoteRequest) this
		.getServiceRequest(ServiceManager.LOAD_CONTRACT_COMPONENT_NOTE_REQUEST);
		
		loadContractComponentNoteRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
		
		LoadContractComponentNoteResponse loadContractComponentNoteResponse =(LoadContractComponentNoteResponse) executeService(loadContractComponentNoteRequest);
		if(null != loadContractComponentNoteResponse && null != loadContractComponentNoteResponse.getProductNotetList() ){
		    noteList=loadContractComponentNoteResponse.getProductNotetList();
		}
		if(this.isViewMode())
		{
		    return "contractBenefitComponentNoteView";
		}else{
		    this.validateNotesAttached();
		    return "contractBenefitComponentNote";
		}
		
	}
   
    
            
            
    // 	Attach notes to benefitComponentOverride module
    public String attachNotesForBenefitComponent(){
		// check for empty fields
		 validationStatus = isValidateRequiredFields();
			if(!validationStatus){				
				addAllMessagesToRequest(validationMessages);
				return "";
			}else{
			    List noteKeyList = WPDStringUtil.getListFromTildaString(this.getNoteName(),3,
		                2, 2);
			    List versionList = WPDStringUtil.getListFromTildaString(this.getNoteName(), 3,
		                3, 2);   
			    ContractBenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest = (ContractBenefitComponentAttachNotesRequest)this.getServiceRequest(ServiceManager.ATTACH_CONTRACT_BENEFIT_COMPONENT_NOTES_REQUEST);
			    benefitComponentAttachNotesRequest.setAction(ContractBenefitComponentAttachNotesRequest.SAVE_NOTE);
			    benefitComponentAttachNotesRequest.setNotes(noteKeyList);
			    benefitComponentAttachNotesRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
			    benefitComponentAttachNotesRequest.setVersionList(versionList);
			    benefitComponentAttachNotesRequest.setOverrideStatus("F");
				ContractBenefitComponentAttachNotesResponse benefitComponentAttachNotesResponse =(ContractBenefitComponentAttachNotesResponse) executeService(benefitComponentAttachNotesRequest) ;
				if(null != benefitComponentAttachNotesResponse && null != benefitComponentAttachNotesResponse.getContractBenefitComponentNotesList()){
				    noteList = benefitComponentAttachNotesResponse.getContractBenefitComponentNotesList();
				}
				 this.validateNotesAttached();		
				 this.noteName = "";
		}
		getRequest().setAttribute("RETAIN_Value", "");	
		return "";
	}


	 private boolean isValidateRequiredFields() {
       validationMessages = new ArrayList();
       boolean requiredField = false;
       this.requiredNoteName = false;        
       
       if (this.noteName == null || "".equals(this.noteName)) {
       	requiredNoteName = true;
           requiredField = true;
       }             
       if (requiredField) {
           this.validationMessages.add(new ErrorMessage(
                   WebConstants.MANDATORY_FIELDS_NOTES_REQUIRED));
           return false;
       }   
       return true;
   }
//	private ContractBenefitComponentAttachNotesRequest getBenefitComponentAttachNotesRequest(){
//	    
//		List notesIdList = null;
//		String selectedNotes = this.getNoteName();
//		ContractBenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest = (ContractBenefitComponentAttachNotesRequest)this.getServiceRequest(ServiceManager.ATTACH_CONTRACT_BENEFIT_COMPONENT_NOTES_REQUEST);
//		if(null != selectedNotes){
//			notesIdList = WPDStringUtil.getListFromTildaString(selectedNotes,2,2,2);			
//		}
//		return benefitComponentAttachNotesRequest;
//	}		

	/*
	 * methode for unattaching a ProductBenefitComponentNote Page
	 */
	 public String deleteBenefitComponentNotes(){
	     	List noteKeyList = new ArrayList();
		 	List versionList = new ArrayList();
		 	noteKeyList.add(this.benefitComponentNoteId);
		 	versionList.add(new Integer(this.versionToDelete).toString());
		 	ContractBenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest = (ContractBenefitComponentAttachNotesRequest)this.getServiceRequest(ServiceManager.ATTACH_CONTRACT_BENEFIT_COMPONENT_NOTES_REQUEST);
		    benefitComponentAttachNotesRequest.setAction(ContractBenefitComponentAttachNotesRequest.OVERRIDE_NOTE);
		    benefitComponentAttachNotesRequest.setNotes(noteKeyList);
		    benefitComponentAttachNotesRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
		    benefitComponentAttachNotesRequest.setVersionList(versionList);
		    benefitComponentAttachNotesRequest.setOverrideStatus("T");
			ContractBenefitComponentAttachNotesResponse benefitComponentAttachNotesResponse =(ContractBenefitComponentAttachNotesResponse) executeService(benefitComponentAttachNotesRequest) ;
			if(null != benefitComponentAttachNotesResponse && null != benefitComponentAttachNotesResponse.getContractBenefitComponentNotesList()){
			    noteList = benefitComponentAttachNotesResponse.getContractBenefitComponentNotesList();
			}
			 getRequest().setAttribute("RETAIN_Value", "");	
			return "productBenefitComponentNote";
	 }

		 private boolean validateNotesAttached(){
			 	if (this.noteList.size()==1){        	
		        	this.validationMessages.add(new InformationalMessage(
		                    WebConstants.NOTE_ATTACHED_ALREADY_BC));
		        	addAllMessagesToRequest(validationMessages);

			 	}
		        return true;	 	
			 }

		 /**
		     * @return securityAccess
		     * 
		     * Returns the securityAccess.
		     */
		    public boolean isSecurityAccess() {
		        try{

		            securityAccess = getUser().isAuthorized(WebConstants.NOTES_MODULE,
		            StateFactory.VIEW_TASK);

		        } catch (SevereException e) {
		        	Logger.logError(e);
		        }
		        return securityAccess;
		    }
		    /**
		     * @param securityAccess
		     * 
		     * Sets the securityAccess.
		     */
		    public void setSecurityAccess(boolean securityAccess) {
		        this.securityAccess = securityAccess;
		    }
    /**
     * @return hiddenNoteList
     * 
     * Returns the hiddenNoteList.
     */
    public String getHiddenNoteList() {
        this.loadNotes();
        return hiddenNoteList;
    }
    /**
     * @param hiddenNoteList
     * 
     * Sets the hiddenNoteList.
     */
    public void setHiddenNoteList(String hiddenNoteList) {
        this.hiddenNoteList = hiddenNoteList;
    }
    /**
     * Returns the loadForPrint
     * @return String loadForPrint.
     */
    public String getLoadForPrint() {
        this.loadNotes();
        return loadForPrint;
    }
    /**
     * Sets the loadForPrint
     * @param loadForPrint.
     */
    public void setLoadForPrint(String loadForPrint) {
        this.loadForPrint = loadForPrint;
    }
    /**
     * Returns the versionToDelete
     * @return String versionToDelete.
     */
    public String getVersionToDelete() {
        return versionToDelete;
    }
    /**
     * Sets the versionToDelete
     * @param versionToDelete.
     */
    public void setVersionToDelete(String versionToDelete) {
        this.versionToDelete = versionToDelete;
    }
    /**
     * Returns the benefitComponentNoteId
     * @return String benefitComponentNoteId.
     */
    public String getBenefitComponentNoteId() {
        return benefitComponentNoteId;
    }
    /**
     * Sets the benefitComponentNoteId
     * @param benefitComponentNoteId.
     */
    public void setBenefitComponentNoteId(String benefitComponentNoteId) {
        this.benefitComponentNoteId = benefitComponentNoteId;
    }
    /**
     * Returns the noteList
     * @return List noteList.
     */
    public List getNoteList() {
        return noteList;
    }
    /**
     * Sets the noteList
     * @param noteList.
     */
    public void setNoteList(List noteList) {
        this.noteList = noteList;
    }
    /**
     * Returns the requiredNoteName
     * @return boolean requiredNoteName.
     */
    public boolean isRequiredNoteName() {
        return requiredNoteName;
    }
    /**
     * Sets the requiredNoteName
     * @param requiredNoteName.
     */
    public void setRequiredNoteName(boolean requiredNoteName) {
        this.requiredNoteName = requiredNoteName;
    }
    /**
     * Returns the noteId
     * @return String noteId.
     */
    public String getNoteId() {
        return noteId;
    }
    /**
     * Sets the noteId
     * @param noteId.
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
    /**
     * Returns the noteName
     * @return String noteName.
     */
    public String getNoteName() {
        return noteName;
    }
    /**
     * Sets the noteName
     * @param noteName.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    /**
     * Returns the validationMessages
     * @return ArrayList validationMessages.
     */
    public ArrayList getValidationMessages() {
        return validationMessages;
    }
    /**
     * Sets the validationMessages
     * @param validationMessages.
     */
    public void setValidationMessages(ArrayList validationMessages) {
        this.validationMessages = validationMessages;
    }
   
}