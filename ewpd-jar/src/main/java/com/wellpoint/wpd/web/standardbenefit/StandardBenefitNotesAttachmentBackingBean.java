/*
 * StandardBenefitNotesAttachmentBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteStandardBenefitNotesRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateStandardBenefitNotesRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitNoteAttachmentRequest;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitNoteAttachmentResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for standard benefit notes attachment
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitNotesAttachmentBackingBean extends WPDBackingBean {
    private String noteName = null;

    private boolean requiredNoteName = false;

    private String state = WebConstants.STATE;

    private String status = WebConstants.STATUS_BUILDING;

    private int version = WebConstants.VERSION;

    private String selectedNotes;

    private List associatedNotesList;

    ArrayList validationMessages = null;

    private boolean notesValdn = true;

    boolean validationStatus = false;

    private int BenefitNotesMasterKey;

    /* For deleting notes attached to benefit */
    private String standardBenefitNoteId;
    
    private boolean securityAccess;
	
    private String deleteNoteIds;
    
    private boolean notesPresent = false;
    
    private boolean doneFlag = false;

    public StandardBenefitNotesAttachmentBackingBean() {

    }


    /**
     * @return selectedNotes
     * 
     * Returns the selectedNotes.
     */
    public String getSelectedNotes() {
        return selectedNotes;
    }


    /**
     * @param selectedNotes
     * 
     * Sets the selectedNotes.
     */
    public void setSelectedNotes(String selectedNotes) {
        this.selectedNotes = selectedNotes;
    }


    public String attachNotesForStandardBenefit() {

        validationStatus = validateRequiredFields();
        if (!validationStatus) {
            addAllMessagesToRequest(validationMessages);
            getRequest().setAttribute("RETAIN_Value", "");	
            return "";
        } else {
            // Create a request object
            StandardBenefitNoteAttachmentRequest standardBenefitNoteAttachmentRequest = getStandardBenefitNoteAttachmentRequest();
            // Create a response object
            StandardBenefitNoteAttachmentResponse standardBenefitNoteAttachmentResponse = (StandardBenefitNoteAttachmentResponse) executeService(standardBenefitNoteAttachmentRequest);            

        }
        this.setNoteName("");
        this.notesPresent = false;
        getRequest().setAttribute("RETAIN_Value", "");	
        return WebConstants.LOAD_SB_FOR_NOTES;
    }


    /**
     * @return Returns standardBenefitNoteAttachmentRequest Gets the standard
     *         benefit and the note to be attached to benefit
     */
    private StandardBenefitNoteAttachmentRequest getStandardBenefitNoteAttachmentRequest() {
        List notesIdList = null;
        List versionList = null;
        //      Create a request object
        StandardBenefitNoteAttachmentRequest standardBenefitNoteAttachmentRequest = (StandardBenefitNoteAttachmentRequest) this
                .getServiceRequest(ServiceManager.ATTACH_STANDARD_BENEFIT_NOTES_REQUEST);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        String selectedNotes = this.getNoteName();

        if (null != selectedNotes) {
            notesIdList = WPDStringUtil.getListFromTildaString(selectedNotes,
                    3, 2, 2);

            //get version in list and set it to VO
            versionList = WPDStringUtil.getListFromTildaString(selectedNotes,
                    3, 3, 2);
        }
        standardBenefitVO.setAttachedNotes(notesIdList);
        standardBenefitVO.setVersionList(versionList);
        standardBenefitVO
                .setBenefitIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        standardBenefitVO
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        standardBenefitVO.setStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        standardBenefitVO.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        standardBenefitVO.setBusinessDomains(getStandardBenefitSessionObject()
                .getBusinessDomains());
        standardBenefitNoteAttachmentRequest
                .setStandardBenefitVO(standardBenefitVO);
        return standardBenefitNoteAttachmentRequest;
    }


    /**
     * to validate the fields
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList(1);
        boolean requiredField = false;
        this.requiredNoteName = false;

        if (this.noteName == null || "".equals(this.noteName)) {
        	if(!doneFlag){
        		requiredNoteName = true;
        	}
            requiredField = true;
        }
        if (requiredField) {
        	if(!doneFlag){
	            this.validationMessages.add(new ErrorMessage(
	                    WebConstants.MANDATORY_FIELDS_NOTES_REQUIRED));
        	}
            return false;
        }
        return true;
    }


    public String loadStandardBenefitNotes() {
        return WebConstants.LOAD_SB_FOR_NOTES;
    }


    /**
     * to get the notes assiciated
     * 
     * @return Returns the associatedNotesList.
     */

    public List getAssociatedNotesList() {

    	if(!notesPresent){
            //List sysDomainIdList = new ArrayList();
            associatedNotesList = new ArrayList();
            LocateStandardBenefitNotesRequest locateStandardBenefitNotesRequest = (LocateStandardBenefitNotesRequest) this
                    .getServiceRequest(ServiceManager.LOCATE_STANDARD_BENEFIT_NOTES_REQUEST);
            locateStandardBenefitNotesRequest
                    .setEntityId(getStandardBenefitSessionObject()
                            .getStandardBenefitKey());
            locateStandardBenefitNotesRequest
                    .setEntityType(WebConstants.ATTACH_BENEFIT);
            locateStandardBenefitNotesRequest
                    .setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
            LocateStandardBenefitNotesResponse locateStandardBenefitNotesResponse = (LocateStandardBenefitNotesResponse) executeService(locateStandardBenefitNotesRequest);
            this.setVersion(getStandardBenefitSessionObject()
                    .getStandardBenefitVersionNumber());
            this.setState(getStandardBenefitSessionObject()
                    .getStandardBenefitState());
            this.setStatus(getStandardBenefitSessionObject()
                    .getStandardBenefitStatus());
            this.notesPresent = true;
            if(null != locateStandardBenefitNotesResponse.getBenefitNotesList()&& locateStandardBenefitNotesResponse.getBenefitNotesList().size()>0){
            	this.setAssociatedNotesList(locateStandardBenefitNotesResponse.getBenefitNotesList());
            }else{
            	this.setAssociatedNotesList(null);
            }
    	}
    	return this.associatedNotesList;
    }


    /*
     * gets the getStandardBenefitSessionObject
     */
    public StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(
                    WebConstants.STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }


    /*
     * this method deletes the notes attached
     */
    public String deleteStandardBenefitNotes() {
    	
    	String [] selectedIds = deleteNoteIds.split("~");
    	if(selectedIds != null && selectedIds.length > 0) {
    		List noteIds = new ArrayList();
    		for(int i=0; i<selectedIds.length; i++) {
    			noteIds.add(selectedIds[i]);
    		}
            DeleteStandardBenefitNotesRequest request = (DeleteStandardBenefitNotesRequest) this
                    .getServiceRequest(ServiceManager.DELETE_STANDARD_BENEFIT_NOTES_REQUEST);
            // the entry to be deleted
//            deleteStandardBenefitNotesRequest
//                    .setStandardBenefitNoteId(this.standardBenefitNoteId);
            request.setNotedIds(noteIds);
            // **Change**July16th
            //set the entity type
            request.setEntityType(WebConstants.ATTACH_BENEFIT);
            // **End**July16th
            // benefitid
            request.setEntityId(getStandardBenefitSessionObject()
                            .getStandardBenefitKey());

            request.setVersion(getStandardBenefitSessionObject()
                            .getStandardBenefitVersionNumber());

            request.setStatus(getStandardBenefitSessionObject()
                            .getStandardBenefitStatus());
            request.setStandardBenefitName(getStandardBenefitSessionObject()
                            .getStandardBenefitName());
            request.setBusinessDomains(getStandardBenefitSessionObject()
                            .getBusinessDomains());
            //call the executeservice method of the service class
            DeleteStandardBenefitNotesResponse response = (DeleteStandardBenefitNotesResponse) executeService(request);
            this.setNoteName("");
            this.notesPresent = false;
            getRequest().setAttribute("RETAIN_Value", "");	
            return "";
    	}
    	getRequest().setAttribute("RETAIN_Value", "");	
    	return "";
    }


    /*
     * this method loads the notes associated for view
     *  
     */
    public String loadBenefitNotesView() {
        this.setBenefitNotesMasterKey(getStandardBenefitSessionObject()
                .getStandardBenefitKey());
        //    	Setting the header
        this.setBreadCrumbText(WebConstants.HEADER
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> View");
        return WebConstants.LOAD_BENEFIT_NOTES_VIEW;
    }


    /**
     * @param associatedNotesList
     *            The associatedNotesList to set.
     */
    public void setAssociatedNotesList(List associatedNotesList) {
        this.associatedNotesList = associatedNotesList;
    }


    /**
     * @return Returns the noteName.
     */
    public String getNoteName() {
        return noteName;
    }


    /**
     * @param noteName
     *            The noteName to set.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }


    /**
     * @return Returns the notesValdn.
     */
    public boolean isNotesValdn() {
        return notesValdn;
    }


    /**
     * @param notesValdn
     *            The notesValdn to set.
     */
    public void setNotesValdn(boolean notesValdn) {
        this.notesValdn = notesValdn;
    }


    /**
     * @return Returns the requiredNoteName.
     */
    public boolean isRequiredNoteName() {
        return requiredNoteName;
    }


    /**
     * @param requiredNoteName
     *            The requiredNoteName to set.
     */
    public void setRequiredNoteName(boolean requiredNoteName) {
        this.requiredNoteName = requiredNoteName;
    }


    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }


    /**
     * @param state
     *            The state to set.
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
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return Returns the validationMessages.
     */
    public ArrayList getValidationMessages() {
        return validationMessages;
    }


    /**
     * @param validationMessages
     *            The validationMessages to set.
     */
    public void setValidationMessages(ArrayList validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * @return Returns the validationStatus.
     */
    public boolean isValidationStatus() {
        return validationStatus;
    }


    /**
     * @param validationStatus
     *            The validationStatus to set.
     */
    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }


    /**
     * @return standardBenefitNoteId
     * 
     * Returns the standardBenefitNoteId.
     */
    public String getStandardBenefitNoteId() {
        return standardBenefitNoteId;
    }


    /**
     * @param standardBenefitNoteId
     * 
     * Sets the standardBenefitNoteId.
     */
    public void setStandardBenefitNoteId(String standardBenefitNoteId) {
        this.standardBenefitNoteId = standardBenefitNoteId;
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
     * @return Returns the benefitNotesMasterKey.
     */
    public int getBenefitNotesMasterKey() {
        return BenefitNotesMasterKey;
    }


    /**
     * @param benefitNotesMasterKey
     *            The benefitNotesMasterKey to set.
     */
    public void setBenefitNotesMasterKey(int benefitNotesMasterKey) {
        BenefitNotesMasterKey = benefitNotesMasterKey;
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
	 * @return Returns the notesPresent.
	 */
	public boolean isNotesPresent() {
		return notesPresent;
	}
	/**
	 * @param notesPresent The notesPresent to set.
	 */
	public void setNotesPresent(boolean notesPresent) {
		this.notesPresent = notesPresent;
	}
	/**
	 * @return Returns the doneFlag.
	 */
	public boolean isDoneFlag() {
		return doneFlag;
	}
	/**
	 * @param doneFlag The doneFlag to set.
	 */
	public void setDoneFlag(boolean doneFlag) {
		this.doneFlag = doneFlag;
	}
}