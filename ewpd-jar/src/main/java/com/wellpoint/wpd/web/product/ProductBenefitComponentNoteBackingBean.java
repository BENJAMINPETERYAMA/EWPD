/*
 * Created on May 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.product.request.LoadProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.response.LoadProductComponentNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentNoteResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u14768
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductBenefitComponentNoteBackingBean extends ProductBackingBean {

    private String noteString;

    private List validationMessages = null;

    private List noteList = null;

    private String noteId;

    private int version;

    private String printValue;

    private String benefitComponentNoteId;

    boolean noteValdn = true;
    
    private boolean securityAccess;
	
	private boolean securityAccessDelete;
	
	private boolean notesRetrieved = false;
	
	private String deleteNoteIds;

    public ProductBenefitComponentNoteBackingBean(){
    	super();
    	if(!super.isViewMode()){
        	this.setBreadCumbTextForEdit();
        }
        else{
        	this.setBreadCumbTextForView();
        }
    }
    /*
     * @methode for loading ProductBenefitComponentNote @ Page return list of
     * notes
     */
    public String loadNotes() {
        LoadProductComponentNoteRequest loadProductComponentNoteRequest = (LoadProductComponentNoteRequest) this
                .getServiceRequest(ServiceManager.LOAD_PRODUCT_COMPONENT_NOTE_REQUEST);
        loadProductComponentNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        LoadProductComponentNoteResponse loadProductComponentNoteResponse = (LoadProductComponentNoteResponse) executeService(loadProductComponentNoteRequest);
        noteList = loadProductComponentNoteResponse.getProductNotetList();
        this.notesRetrieved = true;
        return "productBenefitComponentNote";

    }


    /*
     * @methode for loading ProductBenefitComponentNote view Page
     */
    public String loadNotesView() {
        LoadProductComponentNoteRequest loadProductComponentNoteRequest = (LoadProductComponentNoteRequest) this
                .getServiceRequest(ServiceManager.LOAD_PRODUCT_COMPONENT_NOTE_REQUEST);
        loadProductComponentNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        LoadProductComponentNoteResponse loadProductComponentNoteResponse = (LoadProductComponentNoteResponse) executeService(loadProductComponentNoteRequest);
        noteList = loadProductComponentNoteResponse.getProductNotetList();
        this.notesRetrieved = true;
        return "productBenefitComponentNoteView";
    }


    /*
     * @methode for attaching a ProductBenefitComponentNote Page
     * 
     * @return list of notes attached
     *  
     */
    public String saveNote() {
    	getRequest().setAttribute("RETAIN_Value", "");
        List noteKeyList = WPDStringUtil.getListFromTildaString(noteString, 3,
                2, 2);
        List versionList = WPDStringUtil.getListFromTildaString(noteString, 3,
                3, 2);
        noteString = "";
        SaveProductComponentNoteRequest saveProductComponentNoteRequest = (SaveProductComponentNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_COMPONENT_NOTE_REQUEST);
        saveProductComponentNoteRequest
                .setAction(SaveProductComponentNoteRequest.SAVE_NOTE);
        saveProductComponentNoteRequest.setNoteList(noteKeyList);
        saveProductComponentNoteRequest.setVersionList(versionList);
        saveProductComponentNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        saveProductComponentNoteRequest.setOverrideStatus("F");
        if (null == noteKeyList || noteKeyList.size() == 0) {
            noteValdn = false;
            validationMessages = new ArrayList(1);
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_NOTE_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return "productBenefitComponentNote";
        }
        SaveProductComponentNoteResponse saveProductComponentNoteResponse = (SaveProductComponentNoteResponse) executeService(saveProductComponentNoteRequest);
        noteList = saveProductComponentNoteResponse.getProductNotetList();
        this.notesRetrieved = true;
        return "productBenefitComponentNote";
    }


    /*
     * methode for unattaching a ProductBenefitComponentNote Page
     */
/*    public String deleteNote() {
        List noteKeyList = new ArrayList(1);
        List versionList = new ArrayList(1);
        noteKeyList.add(this.benefitComponentNoteId);
        versionList.add(new Integer(this.version).toString());
        SaveProductComponentNoteRequest saveProductComponentNoteRequest = (SaveProductComponentNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_COMPONENT_NOTE_REQUEST);
        saveProductComponentNoteRequest
                .setAction(SaveProductComponentNoteRequest.OVERRIDE_NOTE);
        saveProductComponentNoteRequest.setNoteList(noteKeyList);
        saveProductComponentNoteRequest.setVersionList(versionList);
        saveProductComponentNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        saveProductComponentNoteRequest.setOverrideStatus(WebConstants.CONST_T);
        SaveProductComponentNoteResponse saveProductComponentNoteResponse = (SaveProductComponentNoteResponse) executeService(saveProductComponentNoteRequest);
        noteList = saveProductComponentNoteResponse.getProductNotetList();
        this.notesRetrieved = true;
        return "productBenefitComponentNote";
    }
*/
    public String deleteNote(){
	    
		String [] selectedIds = deleteNoteIds.split("~");
			if(selectedIds != null && selectedIds.length >0){
				List noteIds = new ArrayList();
	    		for(int i=0; i<selectedIds.length; i++) {
	    			noteIds.add(selectedIds[i]);
	    		}
			
			
	    	// create request
	    	SaveProductComponentNoteRequest saveProductComponentNoteRequest = (SaveProductComponentNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_COMPONENT_NOTE_REQUEST);
	        saveProductComponentNoteRequest
              .setAction(SaveProductComponentNoteRequest.OVERRIDE_NOTE);
	        saveProductComponentNoteRequest.setNoteIdList(noteIds);
	        saveProductComponentNoteRequest.setNoteList(this.noteList);
	        saveProductComponentNoteRequest
            	.setBenefitComponentId(getBenefitComponentIdFromSession());
	        saveProductComponentNoteRequest.setOverrideStatus(WebConstants.CONST_T);
	      //  saveProductComponentNoteRequest.setVersionList(versionList);
	        SaveProductComponentNoteResponse saveProductComponentNoteResponse = (SaveProductComponentNoteResponse) executeService(saveProductComponentNoteRequest);
	        noteList = saveProductComponentNoteResponse.getProductNotetList();
	        
	        this.setNoteString("");	
			getRequest().setAttribute("RETAIN_Value", "");
			//this.isDataPresent = false;
			return "productBenefitComponentNote";
			}
			getRequest().setAttribute("RETAIN_Value", "");	
		    return "productBenefitComponentNote";
    }
	     

    /**
     * @return Returns the noteList.
     */
    public List getNoteList() {
    	if( !this.notesRetrieved)
    		loadNotes();
        return noteList;
    }


    /**
     * @param noteList
     *            The noteList to set.
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
     * @param noteString
     *            The noteString to set.
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
     * @param validationMessages
     *            The validationMessages to set.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * @return Returns the noteId.
     */
    public String getNoteId() {
        return noteId;
    }


    /**
     * @param noteId
     *            The noteId to set.
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }


    /**
     * @return Returns the benefitComponentNoteId.
     */
    public String getBenefitComponentNoteId() {
        return benefitComponentNoteId;
    }


    /**
     * @param benefitComponentNoteId
     *            The benefitComponentNoteId to set.
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
     * @param version
     *            The version to set.
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
     * @param noteValdn
     *            The noteValdn to set.
     */
    public void setNoteValdn(boolean noteValdn) {
        this.noteValdn = noteValdn;
    }


    /**
     * @return Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest()
                .getParameter("printValueForNotes");
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
        }
        return printValue;
    }


    /**
     * @param printValue
     *            The printValue to set.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
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

            // TODO Auto-generated catch block

			Logger.logError(e);

        }

        return securityAccess;

    }
	/**
	 * @param securityAccess The securityAccess to set.
	 */
	public void setSecurityAccess(boolean securityAccess) {
		this.securityAccess = securityAccess;
	}
	/**
	 * @return Returns the securityAccessDelete.
	 */
	public boolean isSecurityAccessDelete() {
		 try{
		  	
		  	this.securityAccessDelete = getUser().isAuthorized(WebConstants.NOTES_MODULE,

                StateFactory.DELETE_TASK);
		  } catch (SevereException e) {

			Logger.logError(e);
		  }
		return securityAccessDelete;
	}
	/**
	 * @param securityAccessDelete The securityAccessDelete to set.
	 */
	public void setSecurityAccessDelete(boolean securityAccessDelete) {
		this.securityAccessDelete = securityAccessDelete;
	}
	
	/**
	 * @return Returns the deleteNoteIds.
	 */
	public String getDeleteNoteIds() {
		return deleteNoteIds;
	}
	/**
	 * @param deleteNoteIds The deleteNoteIds to set.
	 */
	public void setDeleteNoteIds(String deleteNoteIds) {
		this.deleteNoteIds = deleteNoteIds;
	}
}