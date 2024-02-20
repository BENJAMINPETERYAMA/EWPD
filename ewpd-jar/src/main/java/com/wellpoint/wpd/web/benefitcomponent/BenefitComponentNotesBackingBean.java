/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.benefitcomponent;


import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentAttachNotesRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.DeleteBenefitComponentNotesRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.LocateBenefitComponentNotesRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.DeleteBenefitComponentNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateBenefitComponentNotesResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for Benefit Hierarchy.
 * 
 * This bean will bind with the jsp pages.
 * BenefitComponentNotesBackingBean contains the getters and setters of the 
 * variables and respective functions
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitComponentNotesBackingBean extends WPDBackingBean{
	
	private String noteId;
	private String noteString = "" ;
	private boolean requiredNoteName = false;
	private String state = WebConstants.STATE;	
	private String status = WebConstants.STATUS_BUILDING;
	private int version = WebConstants. VERSION;
	private List associatedNotesList = null;
	private List standardBenefitOverrideNotesList = null;
	ArrayList validationMessages = null;
	private boolean notesValdn = true;
	boolean validationStatus = false;
	private int noteVersion;
	
	private boolean securityAccess;
	private boolean isDataPresent = false;
	
	private String deleteNoteIds;
		
	// for delete
	private String benefitComponentNoteId;
	
	public BenefitComponentNotesBackingBean(){	
	    setBreadCrumb();
		}
	
	
	// Attach notes to benefitComponent module
	public String AttachNotesForBenefitComponent(){
		// check for empty fields
		 validationStatus = validateRequiredFields();
			if(!validationStatus){				
				addAllMessagesToRequest(validationMessages);
				return "";
				// proceed with attach if fields are non empty
			}else{
				// call the getBenefitComponentAttachNotesRequest method to create request
				BenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest = getBenefitComponentAttachNotesRequest();
				// For benefitComponentNotesAttach set action = 1
				benefitComponentAttachNotesRequest.setAction(1);
				// Create a response object
//				BenefitComponentAttachNotesResponse benefitComponentAttachNotesResponse =(BenefitComponentAttachNotesResponse) 
				executeService(benefitComponentAttachNotesRequest) ;
				
		}		
			this.noteString ="";
			getRequest().setAttribute("RETAIN_Value", "");
			this.isDataPresent = false;
		return "";
	}
	
	private BenefitComponentAttachNotesRequest getBenefitComponentAttachNotesRequest(){
	    
		// List that has the values of all the notes selected ( with all tildas removed )
		List notesIdList = null;
		List versionList = null;
		// Stores the value of the string 
		String selectedNotes = this.getNoteString();
		
		// Create a request 
		BenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest = (BenefitComponentAttachNotesRequest)this.getServiceRequest(ServiceManager.ATTACH_BENEFIT_COMPONENT_NOTES_REQUEST);
		// create the object for BenefitComponentVO
		BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
		
		if(null != selectedNotes){
		    // Removes the tildas and stores the selected noteIds in a list
			
				notesIdList = WPDStringUtil.getListFromTildaString(selectedNotes,3,2,2);			
				versionList = WPDStringUtil.getListFromTildaString(selectedNotes,3,3,2);
		}
		// Set the selected noteIds to the VO
		benefitComponentVO.setSelectednotesIdList(notesIdList);
		// Set the list of versions to the VO
		benefitComponentVO.setVersionList(versionList);
		// get the various parameters from the session and set to VO
		benefitComponentVO.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());	
		benefitComponentVO.setStatus(getBenefitComponentSessionObject().getStatus());
		benefitComponentVO.setVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
		benefitComponentVO.setBenefitComponentName(getBenefitComponentSessionObject().getBenefitComponentName());
		benefitComponentVO.setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());		
		
		// Set the VO to the request		
		benefitComponentAttachNotesRequest.setBenefitComponentVO(benefitComponentVO);
		// return request
		return benefitComponentAttachNotesRequest;
		}
	
	
	//Delete Notes attached to benefitComponent
	public String deleteBenefitComponentNotes(){
	    
	    // create request
		DeleteBenefitComponentNotesRequest deleteBenefitComponentNotesRequest=(DeleteBenefitComponentNotesRequest)
			this.getServiceRequest(ServiceManager.DELETE_BENEFIT_COMPONENT_NOTES_REQUEST);
		
		// Set the entry to be deleted to the request
		//deleteBenefitComponentNotesRequest.setBenefitComponentNoteId(this.benefitComponentNoteId);
		deleteBenefitComponentNotesRequest.setBenefitComponentNotesIdsList
		(WPDStringUtil.getListFromTildaString(this.benefitComponentNoteId,1,1,2));
		
		// Set the various parameters from session to the request
		deleteBenefitComponentNotesRequest.setEntityId(getBenefitComponentSessionObject().getBenefitComponentId());
		deleteBenefitComponentNotesRequest.setVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
		deleteBenefitComponentNotesRequest.setStatus(getBenefitComponentSessionObject().getStatus());
		deleteBenefitComponentNotesRequest.setBenefitComponentName(getBenefitComponentSessionObject().getBenefitComponentName());
		deleteBenefitComponentNotesRequest.setBusinessDomains(getBenefitComponentSessionObject().getBusinessDomainList());
		deleteBenefitComponentNotesRequest.setAction(1);
		//call the  executeservice method of the service class 
		
//		DeleteBenefitComponentNotesResponse response =
//			(DeleteBenefitComponentNotesResponse)
		executeService(deleteBenefitComponentNotesRequest);	
		this.setNoteString("");		
		getRequest().setAttribute("RETAIN_Value", "");
		this.isDataPresent = false;
		return "";	
	}
	
		//	Delete standardBenefitOverrideNotes
/*		public String deleteStandardBenefitOverrideNotes(){
		    
		    // create request
			DeleteBenefitComponentNotesRequest request=(DeleteBenefitComponentNotesRequest)
				this.getServiceRequest(ServiceManager.DELETE_BENEFIT_COMPONENT_NOTES_REQUEST);
			
			 //Get the standard benefit id
			int standardBenefitId = Integer.parseInt(getSession().getAttribute(
				"SESSION_BNFT_ID").toString());
			
			// Set the entry to be deleted to the request
			request.setBenefitComponentNoteId(this.benefitComponentNoteId);
			
			// Set the various parameters from session to the request
			request.setEntityId(getBenefitComponentSessionObject().getBenefitComponentId());
			request.setNoteVersion(this.noteVersion);			
			request.setVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
			request.setStatus(getBenefitComponentSessionObject().getStatus());
			request.setBenefitComponentName(getBenefitComponentSessionObject().getBenefitComponentName());
			request.setBusinessDomains(getBenefitComponentSessionObject().getBusinessDomainList());
			request.setSecEntityId(standardBenefitId);			
			request.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
			request.setAction(2);
			
			//call the  executeservice method of the service class 
			DeleteBenefitComponentNotesResponse response =
				(DeleteBenefitComponentNotesResponse)executeService(request);	
			this.setNoteString("");	
			getRequest().setAttribute("RETAIN_Value", "");
			this.isDataPresent = false;
			return "";	
		}
	
	*/	
		
	public String deleteStandardBenefitOverrideNotes(){
	    
		String [] selectedIds = deleteNoteIds.split("~");
			if(selectedIds != null && selectedIds.length >0){
				List noteIds = new ArrayList();
	    		for(int i=0; i<selectedIds.length; i++) {
	    			noteIds.add(selectedIds[i]);
	    		}
			
			
	    	// create request
			DeleteBenefitComponentNotesRequest request=(DeleteBenefitComponentNotesRequest)
				this.getServiceRequest(ServiceManager.DELETE_BENEFIT_COMPONENT_NOTES_REQUEST);
			
			 //Get the standard benefit id
			int standardBenefitId = Integer.parseInt(getSession().getAttribute(
				"SESSION_BNFT_ID").toString());
			
			
			// Set the entry to be deleted to the request
			request.setBenefitComponentNotesIdsList(noteIds); //BC benefit level notes list
			request.setBenefitComponentNoteId(this.benefitComponentNoteId);
			request.setNotesList(this.standardBenefitOverrideNotesList); 
			// Set the various parameters from session to the request
			request.setEntityId(getBenefitComponentSessionObject().getBenefitComponentId());
			//request.setNoteVersion(this.noteVersion);			
			request.setVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
			request.setStatus(getBenefitComponentSessionObject().getStatus());
			request.setBenefitComponentName(getBenefitComponentSessionObject().getBenefitComponentName());
			request.setBusinessDomains(getBenefitComponentSessionObject().getBusinessDomainList());
			request.setSecEntityId(standardBenefitId);			
			request.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
			request.setAction(2);
			
			//call the  executeservice method of the service class 
			DeleteBenefitComponentNotesResponse response =
				(DeleteBenefitComponentNotesResponse)executeService(request);	
			this.setNoteString("");	
			getRequest().setAttribute("RETAIN_Value", "");
			this.isDataPresent = false;
			return "";	
		}
		getRequest().setAttribute("RETAIN_Value", "");	
	    return "";
	}
	
	//Attach notes to standardBenefitOverride module
	public String AttachNotesForStandardBenefitOverride(){
		// check for empty fields
		 validationStatus = validateRequiredFields();
			if(!validationStatus){				
				addAllMessagesToRequest(validationMessages);
				return "";
				// proceed with attach if fields are non empty
			}else{
			    
				// call the getBenefitComponentAttachNotesRequest method to create request
				BenefitComponentAttachNotesRequest benefitComponentAttachNotesRequest = getBenefitComponentAttachNotesRequest();
				// For stdBenOverrideNotesAttach - set action = 2
				benefitComponentAttachNotesRequest.setAction(2);
				// Get BenefitComponentVO object
				BenefitComponentVO benefitComponentVO = benefitComponentAttachNotesRequest.getBenefitComponentVO();
				// Get the standard benefit id
				int standardBenefitId = Integer.parseInt(getSession().getAttribute(
				"SESSION_BNFT_ID").toString());
				// set the stdBenefitId to the VO
				benefitComponentVO.setStandardBenefitKey(standardBenefitId);
				// Create a response object
//				BenefitComponentAttachNotesResponse benefitComponentAttachNotesResponse =(BenefitComponentAttachNotesResponse) 
				executeService(benefitComponentAttachNotesRequest) ;
				
		}		
			this.noteString ="";
			getRequest().setAttribute("RETAIN_Value", "");
			this.isDataPresent = false;
		return "";
	}
	
	
	// Check if the fields are valid
	 private boolean validateRequiredFields() {
        validationMessages = new ArrayList(10);
        boolean requiredField = false;
        this.requiredNoteName = false;        
        
        if (this.noteString == null || "".equals(this.noteString)) {
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
					
	
	// BenefitComponent Session Object 
	protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
    	BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)
		getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		if(benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY,benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
	}		
	   
	// Method to load the page benefitcomponentNotes after navigation
	public String loadBenefitComponentNotes() {
        return "LoadBenefitComponentForNotes";
    }
	
//	 Method to load the page standardBenefitOverride after navigation
	public String loadStandardBenefitOverrideNotes() {
        return "LoadStandardBenefitOverrideForNotes";
    }
	
	
	/**
	 * LOCATE : For BenefitComponentNotes
	 */
	public List getAssociatedNotesList() {		
		if(!isDataPresent){
			 // create request
			 LocateBenefitComponentNotesRequest locateBenefitComponentNotesRequest = (LocateBenefitComponentNotesRequest)
				this.getServiceRequest(ServiceManager.LOCATE_BENEFIT_COMPONENT_NOTES_REQUEST);
			 // Set the various parameters to the request
			 locateBenefitComponentNotesRequest.setEntityId(getBenefitComponentSessionObject().getBenefitComponentId());
			 locateBenefitComponentNotesRequest.setEntityType("ATTACHCOMP");
			 locateBenefitComponentNotesRequest.setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
			 locateBenefitComponentNotesRequest.setAction(1);
			 // create response
			LocateBenefitComponentNotesResponse locateBenefitComponentNotesResponse = 
	        	(LocateBenefitComponentNotesResponse)executeService(locateBenefitComponentNotesRequest);
			this.setStatus(this.getBenefitComponentSessionObject().getStatus());
//	 Added for bug fix -- State and Status was not being shown in the notes tab print page
			this.setVersion(this.getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
//	 End for bug fix. 
			this.setState(this.getBenefitComponentSessionObject().getComponentState());
			 // return the list 
			//if(null != locateBenefitComponentNotesResponse)
			this.setAssociatedNotesList(locateBenefitComponentNotesResponse.getBenefitComponentNotesList());
			this.isDataPresent = true;
		}
			return associatedNotesList;
		//else
			//return null;
	}
	
	 /**
     * @return standardBenefitOverrideNotesList
     * 
     * Returns the standardBenefitOverrideNotesList.
     */
    public List getStandardBenefitOverrideNotesList() {
        // create request
		/* LocateStandardBenefitOverrideNotesRequest request = (LocateStandardBenefitOverrideNotesRequest)
			this.getServiceRequest(ServiceManager.LOCATE_STD_BEN_OVERRIDE_NOTES_REQUEST);*/
    	if(!isDataPresent){
            LocateBenefitComponentNotesRequest request = (LocateBenefitComponentNotesRequest)
			this.getServiceRequest(ServiceManager.LOCATE_BENEFIT_COMPONENT_NOTES_REQUEST);
			 //Get the standard benefit id
			int standardBenefitId = Integer.parseInt(getSession().getAttribute(
				"SESSION_BNFT_ID").toString());
			 // Set the various parameters to the request
			
			request.setEntityId(getBenefitComponentSessionObject().getBenefitComponentId());
			request.setEntityType("ATTACHCOMP");
			request.setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
			request.setSecEntityId(standardBenefitId);
			request.setSecEntityType("ATTACHBENEFIT");
			request.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
			request.setAction(2);
			 // create response
			 com.wellpoint.wpd.common.benefitcomponent.response.LocateBenefitComponentNotesResponse locateBenefitComponentNotesResponse = 
	       	(com.wellpoint.wpd.common.benefitcomponent.response.LocateBenefitComponentNotesResponse)executeService(request);
			 this.setStandardBenefitOverrideNotesList(locateBenefitComponentNotesResponse.getBenefitComponentNotesList());
			 this.isDataPresent = true;
    	}
		 // return the list 
		 //if(null != locateBenefitComponentNotesResponse)
		 	return standardBenefitOverrideNotesList;
		 //else
			//return null;
    }
	
	// Method to set breadcrumb
	protected void setBreadCrumb() {
	    String mode = getBenefitComponentSessionObject().getBenefitComponentMode();
		if (this.getBenefitComponentSessionObject().getBenefitComponentName() == null)
			this
					.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB);
		
		else if(mode.equals("View"))
		    this.setBreadCrumbText("Product Configuration >> Benefit Component "
					+ "("
					+ this.getBenefitComponentSessionObject()
							.getBenefitComponentName() + ") >> View");
		else
			this.setBreadCrumbText("Product Configuration >> Benefit Component "
					+ "("
					+ this.getBenefitComponentSessionObject()
							.getBenefitComponentName() + ") >> Edit");
			
	}
	
	
	// For benefitComponentNotesView
	public String loadBenefitComponentNotesForView(){
		// Call method to retrieve the values
		// getAssociatedNotesList();		
	return "benefitComponentNotesView" ;
	}
	
	// For standardBenefitnotesView
	public String loadStandardBenefitNotesView(){
		return "componentBenefitNotesView";	
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
	 * @return Returns the requiredNoteName.
	 */
	public boolean isRequiredNoteName() {
		return requiredNoteName;
	}
	/**
	 * @param requiredNoteName The requiredNoteName to set.
	 */
	public void setRequiredNoteName(boolean requiredNoteName) {
		this.requiredNoteName = requiredNoteName;
	}
	
	/**
	 * @param associatedNotesList The associatedNotesList to set.
	 */
	public void setAssociatedNotesList(List associatedNotesList) {
		this.associatedNotesList = associatedNotesList;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return Returns notesValdn
	 */
	public boolean isNotesValdn() {
		return notesValdn;
	}
	/**
	 * @param requiredField The requiredField to set.
	 */
	public void setNotesValdn(boolean notesValdn) {
		this.notesValdn = notesValdn;
	}
	/**
	 * @return Returns the validationMessages.
	 */
	public ArrayList getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(ArrayList validationMessages) {
		this.validationMessages = validationMessages;
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
     * @param standardBenefitOverrideNotesList
     * 
     * Sets the standardBenefitOverrideNotesList.
     */
    public void setStandardBenefitOverrideNotesList(
            List standardBenefitOverrideNotesList) {
        this.standardBenefitOverrideNotesList = standardBenefitOverrideNotesList;
    }
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return Returns the noteVersion.
	 */
	public int getNoteVersion() {
		return noteVersion;
	}
	/**
	 * @param noteVersion The noteVersion to set.
	 */
	public void setNoteVersion(int noteVersion) {
		this.noteVersion = noteVersion;
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
	 * @return Returns the isDataPresent.
	 */
	public boolean isDataPresent() {
		return isDataPresent;
	}
	/**
	 * @param isDataPresent The isDataPresent to set.
	 */
	public void setDataPresent(boolean isDataPresent) {
		this.isDataPresent = isDataPresent;
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
