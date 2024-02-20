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
import com.wellpoint.wpd.common.product.request.LoadProductBenefitNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductBenefitNoteRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentNoteRequest;
import com.wellpoint.wpd.common.product.response.LoadProductBenefitNoteResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitNoteResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u14768
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductBenefitNoteBackingBean extends ProductBackingBean {

    private String noteString;

    private List validationMessages = null;

    private List noteList = null;

    private int noteId;

    private int version;

    private String printValue;

    private String benefitComponentNoteId;

    boolean noteValdn = true;

    private String productType;
    
    private boolean securityAccess;
	
	private boolean securityAccessDelete;

	private boolean notesRetrieved;
	
	private String deleteNoteIds;
	
	private String deleteNoteVersions;
	
	public ProductBenefitNoteBackingBean(){
    	super();
    	if(!super.isViewMode()){
        	this.setBreadCumbTextForEdit();
        }
        else{
        	this.setBreadCumbTextForView();
        }
    }

    /*
     * methode for loading ProductBenefitComponentNote Page
     */
    public String loadNotes() {

    	if(!this.notesRetrieved) {
	        LoadProductBenefitNoteRequest loadProductBenefitNoteRequest = (LoadProductBenefitNoteRequest) this
	                .getServiceRequest(ServiceManager.LOAD_PRODUCT_BENEFIT_NOTE_REQUEST);
	        loadProductBenefitNoteRequest
	                .setBenefitComponentId(getBenefitComponentIdFromSession());
	        loadProductBenefitNoteRequest.setBenefitId(getProductSessionObject()
	                .getBenefitId());
	        LoadProductBenefitNoteResponse loadProductBenefitNoteResponse = (LoadProductBenefitNoteResponse) executeService(loadProductBenefitNoteRequest);
	        noteList = loadProductBenefitNoteResponse.getProductNotetList();
	        this.notesRetrieved = true;
    	}
        this.productType = super.getProductTypeFromSession();
        //return "productBenefitNote";
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            return "productBenefitNoteView";
        } else {
            return "productBenefitNote";
        }

    }


    /*
     * methode for attaching a ProductBenefitComponentNote Page
     */
    public String saveNote() {
    	getRequest().setAttribute("RETAIN_Value", "");

        List noteKeyList = WPDStringUtil.getListFromTildaString(noteString, 3,
                2, 2);
        List versionList = WPDStringUtil.getListFromTildaString(noteString, 3,
                3, 2);
        noteString = "";

        SaveProductBenefitNoteRequest saveProductBenefitNoteRequest = (SaveProductBenefitNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_BENEFIT_NOTE_REQUEST);
        saveProductBenefitNoteRequest
                .setAction(SaveProductComponentNoteRequest.SAVE_NOTE);
        saveProductBenefitNoteRequest.setNoteList(noteKeyList);
        saveProductBenefitNoteRequest.setVersionList(versionList);
        saveProductBenefitNoteRequest.setOverrideStatus("F");
        saveProductBenefitNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        saveProductBenefitNoteRequest.setBenefitId(getProductSessionObject()
                .getBenefitId());
        if (null == noteKeyList || noteKeyList.size() == 0) {
            noteValdn = false;
            validationMessages = new ArrayList(1);
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_NOTE_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return "productBenefitNote";
        }
        SaveProductBenefitNoteResponse saveProductBenefitNoteResponse = (SaveProductBenefitNoteResponse) executeService(saveProductBenefitNoteRequest);
        noteList = saveProductBenefitNoteResponse.getProductNotetList();
        this.notesRetrieved = true;
        return "productBenefitNote";
    }


    /*
     * methode for unattaching a ProductBenefitComponentNote Page
     */
    public String deleteNote() {
    	List noteKeyList = new ArrayList();
        List versionList = new ArrayList();
        String [] selectedIds = deleteNoteIds.split("~");
        String [] selectedNoteVersions = deleteNoteVersions.split("~");
    	if(selectedIds != null && selectedIds.length > 0) {
    		
    		for(int i=0; i<selectedIds.length; i++) {
    			noteKeyList.add(selectedIds[i]);
    		}
    	}
    	if(selectedNoteVersions != null && selectedNoteVersions.length > 0) {
    		
    		for(int i=0; i<selectedNoteVersions.length; i++) {
    			versionList.add(selectedNoteVersions[i]);
    		}
    	}	
        //versionList.add(new Integer(this.version).toString());
        SaveProductBenefitNoteRequest saveProductBenefitNoteRequest = (SaveProductBenefitNoteRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_BENEFIT_NOTE_REQUEST);
        saveProductBenefitNoteRequest
                .setAction(SaveProductBenefitNoteRequest.OVERRIDE_NOTE);
        saveProductBenefitNoteRequest.setNoteList(noteKeyList);
        saveProductBenefitNoteRequest.setVersionList(versionList);
        saveProductBenefitNoteRequest.setOverrideStatus(WebConstants.CONST_T);
        saveProductBenefitNoteRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        saveProductBenefitNoteRequest.setBenefitId(getProductSessionObject()
                .getBenefitId());
        SaveProductBenefitNoteResponse saveProductBenefitNoteResponse = (SaveProductBenefitNoteResponse) executeService(saveProductBenefitNoteRequest);
        if(null != saveProductBenefitNoteResponse.getProductNotetList())
            noteList = saveProductBenefitNoteResponse.getProductNotetList();
        this.notesRetrieved = true;
        return "productBenefitNote";
    }


    /**
     * @return Returns the noteList.
     */
    public List getNoteList() {
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
    public int getNoteId() {
        return noteId;
    }


    /**
     * @param noteId
     *            The noteId to set.
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
     * @return printValue
     * 
     * Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest().getAttribute("printValueForNotes").toString();
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
        }
        return printValue;
    }


    /**
     * @param printValue
     * 
     * Sets the printValue.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }


    /**
     * @return Returns the productType.
     */
    public String getProductType() {
        return productType;
    }


    /**
     * @param productType
     *            The productType to set.
     */
    public void setProductType(String productType) {
        this.productType = productType;
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
	/**
	 * @return Returns the deleteNoteVersions.
	 */
	public String getDeleteNoteVersions() {
		return deleteNoteVersions;
	}
	/**
	 * @param deleteNoteVersions The deleteNoteVersions to set.
	 */
	public void setDeleteNoteVersions(String deleteNoteVersions) {
		this.deleteNoteVersions = deleteNoteVersions;
	}
}