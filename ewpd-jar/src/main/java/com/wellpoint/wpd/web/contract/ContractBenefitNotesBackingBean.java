/*
 * Created on July 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.contract.request.LoadContractBenefitNoteRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBenefitNoteRequest;
import com.wellpoint.wpd.common.contract.response.LoadContractBenefitNoteResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitNoteResponse;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u12573
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractBenefitNotesBackingBean extends ContractBackingBean {
	
	private String noteString;
	List validationMessages=null;
	List noteList=null;
	private int noteId;
	private int version;	
	private String benefitComponentNoteId;	
	boolean noteValdn = true;
	private String loadNotesForPrint;
	private boolean securityAccess;
	List messageList = new ArrayList();;
	//default constructor
	public  ContractBenefitNotesBackingBean(){
		setBreadCrumbText();
	}

	/*
	 * method for loading Contract Benefit Note Page
	 */
	public String loadNotes(){
		validationMessages = new ArrayList();
		
		if(getContractSession().getContractKeyObject().getContractType().equalsIgnoreCase(WebConstants.MANDATE) && !super.isViewMode()){
			validationMessages.add(new InformationalMessage(
	                   WebConstants.STANDARD_BENEFIT_MANDATE_NOTE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}
		LoadContractBenefitNoteRequest loadContractBenefitNoteRequest=(LoadContractBenefitNoteRequest) this
		.getServiceRequest(ServiceManager.LOAD_CONTRACT_BENEFIT_NOTE_REQUEST);
		loadContractBenefitNoteRequest.setBenefitComponentId(getBenefitComponentIdFromSession());	
		loadContractBenefitNoteRequest.setBenefitId(this.getContractSession().getBenefitId());
		LoadContractBenefitNoteResponse loadContractBenefitNoteResponse = (LoadContractBenefitNoteResponse) executeService(loadContractBenefitNoteRequest);
		loadContractBenefitNoteResponse.setMessages(messageList);
		addAllMessagesToRequest(loadContractBenefitNoteResponse.getMessages());
		noteList=loadContractBenefitNoteResponse.getContractNotetList();
		if (super.isViewMode())
			return "contractBenefitNoteView";
		else{
			
		    this.validateNotesAttached();
		    return "contractBenefitNote";
		}
			
		
	}
	/*
	 * method for attaching a Contract Benefit Note
	 */
	public String saveNote(){
		 
	        List noteKeyList = WPDStringUtil.getListFromTildaString(noteString, 3,
	                2, 2);
	        List versionList = WPDStringUtil.getListFromTildaString(noteString, 3,
	                3, 2);
	        noteString = "";
	        
	    SaveContractBenefitNoteRequest saveContractBenefitNoteRequest=(SaveContractBenefitNoteRequest) this
        .getServiceRequest(ServiceManager.SAVE_CONTRACT_BENEFIT_NOTE_REQUEST);
	    saveContractBenefitNoteRequest.setAction(SaveContractBenefitNoteRequest.SAVE_NOTE);
	    saveContractBenefitNoteRequest.setNoteList(noteKeyList);
	    saveContractBenefitNoteRequest.setVersionList(versionList);
	    saveContractBenefitNoteRequest.setOverrideStatus("F");
	    saveContractBenefitNoteRequest.setBenefitComponentId(getBenefitComponentIdFromSession());	    
	    saveContractBenefitNoteRequest.setBenefitId(this.getContractSession().getBenefitId());
		if (null == noteKeyList || noteKeyList.size() == 0) {
			noteValdn=false;
            validationMessages = new ArrayList();
            
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_NOTE_FIELDS_REQUIRED));
            messageList.add(new ErrorMessage(
                    WebConstants.MANDATORY_NOTE_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);       
            return "contractBenefitNote";
        }
		SaveContractBenefitNoteResponse saveContractBenefitNoteResponse=(SaveContractBenefitNoteResponse) executeService(saveContractBenefitNoteRequest);
		noteList=saveContractBenefitNoteResponse.getContractNotesList();
		this.validateNotesAttached();
		getRequest().setAttribute("RETAIN_Value", "");	
		return "contractBenefitNote";
	}
	/*
	 * method for unattaching a Contract Benefit Note
	 */
	 public String deleteNote(){
	 	List noteKeyList = new ArrayList();
	 	List versionList = new ArrayList();
	 	noteKeyList.add(this.benefitComponentNoteId);
	 	versionList.add(new Integer(this.version).toString());
	 	SaveContractBenefitNoteRequest saveContractBenefitNoteRequest=(SaveContractBenefitNoteRequest) this
	        .getServiceRequest(ServiceManager.SAVE_CONTRACT_BENEFIT_NOTE_REQUEST);
	 	saveContractBenefitNoteRequest.setAction(SaveContractBenefitNoteRequest.OVERRIDE_NOTE);
	 	saveContractBenefitNoteRequest.setNoteList(noteKeyList);
	 	saveContractBenefitNoteRequest.setVersionList(versionList);
	 	saveContractBenefitNoteRequest.setOverrideStatus("T");
	 	saveContractBenefitNoteRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
	 	saveContractBenefitNoteRequest.setBenefitId(this.getContractSession().getBenefitId());
		SaveContractBenefitNoteResponse saveContractBenefitNoteResponse=(SaveContractBenefitNoteResponse) executeService(saveContractBenefitNoteRequest);
		messageList.addAll(saveContractBenefitNoteResponse.getMessages());
		noteList=saveContractBenefitNoteResponse.getContractNotesList();
		 getRequest().setAttribute("RETAIN_Value", "");	
	 	return "contractBenefitNote";
	 }
	 private boolean validateNotesAttached(){
		 	if (this.noteList.size()==1){      
		 	   validationMessages = new ArrayList();
	        	this.validationMessages.add(new InformationalMessage(
	                    WebConstants.NOTE_ATTACHED_ALREADY_BENEFIT));
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
     * Returns the loadNotesForPrint
     * @return String loadNotesForPrint.
     */
    public String getLoadNotesForPrint() {
        this.loadNotes();
        
        return loadNotesForPrint;
    }
    /**
     * Sets the loadNotesForPrint
     * @param loadNotesForPrint.
     */
    public void setLoadNotesForPrint(String loadNotesForPrint) {
        this.loadNotesForPrint = loadNotesForPrint;
    }
	/**
	 * @return Returns the noteList.
	 */
	public List getNoteList() {
		
		return noteList;
	}
	/**
	 * @param noteList The noteList to set.
	 */
	public void setNoteList(List noteList) {
		this.noteList = noteList;
	}
	/**
	 * @return Returns the noteString.
	 */
	public String getNoteString() {
		return noteString;
	}
	/**
	 * @param noteString The noteString to set.
	 */
	public void setNoteString(String noteString) {
		this.noteString = noteString;
	}
	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	/**
	 * @return Returns the noteId.
	 */
	public int getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
	/**
	 * @return Returns the benefitComponentNoteId.
	 */
	public String getBenefitComponentNoteId() {
		return benefitComponentNoteId;
	}
	/**
	 * @param benefitComponentNoteId The benefitComponentNoteId to set.
	 */
	public void setBenefitComponentNoteId(String benefitComponentNoteId) {
		this.benefitComponentNoteId = benefitComponentNoteId;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return Returns the noteValdn.
	 */
	public boolean isNoteValdn() {
		return noteValdn;
	}
	/**
	 * @param noteValdn The noteValdn to set.
	 */
	public void setNoteValdn(boolean noteValdn) {
		this.noteValdn = noteValdn;
	}
	/**
	 * @return Returns the messageList.
	 */
	public List getMessageList() {
		return messageList;
	}
	/**
	 * @param messageList The messageList to set.
	 */
	public void setMessageList(List messageList) {
		this.messageList = messageList;
	}
}
