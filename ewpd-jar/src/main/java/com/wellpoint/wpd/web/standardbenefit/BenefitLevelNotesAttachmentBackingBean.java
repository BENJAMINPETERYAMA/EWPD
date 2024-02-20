/*
 * BenefitLevelNotesAttachmentBackingBean.java
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
import java.util.StringTokenizer;

import com.wellpoint.wpd.common.benefitlevel.request.BenefitLineNotesAttachmentRequest;
import com.wellpoint.wpd.common.benefitlevel.request.NotesAttachmentRequestForBnftLine;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLineNotesAttachmentResponse;
import com.wellpoint.wpd.common.benefitlevel.response.NotesAttachmentResponseForBnftLine;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.notes.request.RetrieveNotesRequest;
import com.wellpoint.wpd.common.notes.response.RetrieveNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteStandardBenefitNotesRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateStandardBenefitNotesRequest;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateStandardBenefitNotesResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for Benefit level notes attachment.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelNotesAttachmentBackingBean extends WPDBackingBean {

    private boolean requiredNoteName;

    private List associatedNotesList = null;

    private String benefitLineNoteId;

    private String noteName;

    private List validationMessages = null;

    private int loadAssociatedNotes;

    private List availableNotes = null;

    private String bnftLineId;

    private String bnftQualifierCode;

    private String benefitTermCode;

    private int loadAssociatedNotesForView;

    private String noteType;

    private String noteText;
    
    private List notesList;
    private String noteIdsForDeletion;   
    

    /**
     * @return notesList
     * 
     * Returns the notesList.
     */
    public List getNotesList() {
        NotesAttachmentRequestForBnftLine notesAttachmentRequestForBnftLine = (NotesAttachmentRequestForBnftLine) this
		        .getServiceRequest(ServiceManager.NOTES_ATTACHMENT_REQUEST_FOR_BNFT_LINE);
		// add necessary values to the request
		/*
		 * this.setBnftLineId(getRequest().getParameter("bnftLineId")); String
		 * bnftQualCode = getRequest().getParameter("bnftQualifierCode");
		 * if("null".equals(bnftQualCode)){ bnftQualCode = null; }
		 * this.setBnftQualifierCode(bnftQualCode);
		 * this.setBenefitTermCode(getRequest().getParameter("bnftTermCode"));
		 */
		loadAssociatedNotesForBnftLine();
		//modified for getting defn id
		if (null != this.getSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)
                && !"".equals(this.getSession().getAttribute(
                        WebConstants.SESSION_BNFT_DEFN_ID))) {
		    notesAttachmentRequestForBnftLine.setBenefitDefinitionId(Integer.parseInt((String) (this.getSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID))));
        }
        //end
		notesAttachmentRequestForBnftLine
		        .setBenefitLineID(this.getBnftLineId());
		//changed on 19th Dec 2007 for getting the list of qualifiers 
		List benefitQualifiers = new ArrayList();
		String benefitQualifierCode = this.getBnftQualifierCode();
		if (null != benefitQualifierCode) {
		    StringTokenizer tokenizerForQualifierCode = new StringTokenizer(
		            benefitQualifierCode, ",");
		    while (tokenizerForQualifierCode.hasMoreTokens()) {
		        String qualifierCodeTildaString = tokenizerForQualifierCode.nextToken();
		        benefitQualifiers.add(qualifierCodeTildaString);
		    }
		}
		// change ends
		
		notesAttachmentRequestForBnftLine.setBenefitQualifier(benefitQualifiers);
		if(null!=getRequest().getParameter("noteSearchCriteria"))
		    notesAttachmentRequestForBnftLine.setNoteFilterString(getRequest().getParameter("noteSearchCriteria"));
		else
		    notesAttachmentRequestForBnftLine.setNoteFilterString("");
		List benefitTerms = new ArrayList();
		String benefitTermCode = this.getBenefitTermCode();
		if (null != benefitTermCode) {
		    StringTokenizer tokenizerForTermCode = new StringTokenizer(
		            benefitTermCode, ",");
		    while (tokenizerForTermCode.hasMoreTokens()) {
		        String termCodeTildaString = tokenizerForTermCode.nextToken();
		        benefitTerms.add(WPDStringUtil.getCodeFromTildaString(termCodeTildaString,2,1,2));
		    }
		}
		notesAttachmentRequestForBnftLine.setBenefitTerms(benefitTerms);
		
		// get the response
		NotesAttachmentResponseForBnftLine notesAttachmentResponseForBnftLine = (NotesAttachmentResponseForBnftLine) executeService(notesAttachmentRequestForBnftLine);
		
		// Check whether notesList is not null and empty
		if (null != notesAttachmentResponseForBnftLine.getSelectedNotesList()
		        && !notesAttachmentResponseForBnftLine.getSelectedNotesList()
		                .isEmpty()) {
		    this.setAvailableNotes(notesAttachmentResponseForBnftLine
		            .getSelectedNotesList());
		} else {
		    this.setAvailableNotes(null);
		}

        return notesList;
    }
    /**
     * @param notesList
     * 
     * Sets the notesList.
     */
    public void setNotesList(List notesList) {
        this.notesList = notesList;
    }
    /**
     * This method is to load the notes attachment page for the benefit line
     * when the root node in the tree is clicked.
     * 
     * @return
     */
    public String getMainPage() {
        return WebConstants.LOAD_BENEFIT_LINE_NOTES;
    }


    /**
     * This method is to load the notes detailed page for the benefit line when
     * the notes facet in the tree is clicked.
     * 
     * @return
     */
    public String getNotesDetailedPage() {
        String key = getSession().getAttribute("noteIdFromTree").toString();
        String name = getSession().getAttribute("noteNameFromTree").toString();
        String noteId = null;
        String version = null;
        if (null != key) {
            noteId = key.substring(0, key.indexOf("~"));
            version = key.substring(key.indexOf("~") + 1);
        }
        RetrieveNotesRequest retrieveNotesRequest = (RetrieveNotesRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_NOTES_REQUEST);
        retrieveNotesRequest.setNoteId(noteId);
        if (null != version && !version.equals(""))
            retrieveNotesRequest.setVersion(Integer.parseInt(version));
        retrieveNotesRequest.setNoteName(name);
        RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse) executeService(retrieveNotesRequest);
        this.setNoteName(retrieveNotesResponse.getNoteBO().getNoteName());
        this.setNoteText(retrieveNotesResponse.getNoteBO().getNoteText());
        this.setNoteType(retrieveNotesResponse.getNoteBO().getNoteTypeDesc());
        return WebConstants.LOAD_BENEFIT_NOTES_DETAILS;
    }


    /**
     * This method is to locate the associated notes for the corresponding
     * benifitline.
     * 
     * @return
     */
    public String loadAssociatedNotesForBnftLine() {
        if (null != getRequest().getParameter(WebConstants.BENEFIT_LINE_ID)
                && !getRequest().getParameter(WebConstants.BENEFIT_LINE_ID)
                        .equals("")) {
        	if(null!=getRequest().getParameter(WebConstants.BENEFIT_LINE_ID) && getRequest().getParameter(WebConstants.BENEFIT_LINE_ID).matches("^[0-9a-zA-Z_]+$")){
            String bnftLineId = getRequest().getParameter(
                    WebConstants.BENEFIT_LINE_ID);
        	}
            if ((WebConstants.NULL).equals(bnftLineId)) {
                bnftLineId = null;
            }
            this.setBnftLineId(bnftLineId);
            getSession().setAttribute("benefitLineIdForTree", bnftLineId);
        }
        if (null != getRequest().getParameter("bnftQualifierCode")
                && !getRequest().getParameter("bnftQualifierCode").equals("")) {
            String bnftQualCode = getRequest()
                    .getParameter("bnftQualifierCode");
            if ((WebConstants.NULL).equals(bnftQualCode)) {
                bnftQualCode = null;
            }
            this.setBnftQualifierCode(bnftQualCode);
        }
        if (null != getRequest().getParameter("bnftTermCode")
                && !getRequest().getParameter("bnftTermCode").equals("")) {
            String bnftTermCode = getRequest().getParameter("bnftTermCode");
            if ((WebConstants.NULL).equals(bnftTermCode)) {
                bnftTermCode = null;
            }
            this.setBenefitTermCode(bnftTermCode);
        }
        return "";
    }


    /**
     * This method for attaching the Notes to the corresponding benefit line.
     * 
     * @return
     */
    public String attachNotesForBenefitLine() {
        boolean validationStatus = validateRequiredFields();
        if (!validationStatus) {
            addAllMessagesToRequest(validationMessages);
            return "";
        } else {
            // Create a request object
            BenefitLineNotesAttachmentRequest benefitLineNotesAttachmentRequest = getBenefitLineNotesAttachmentRequest();
            // Create a response object
            BenefitLineNotesAttachmentResponse benefitLineNotesAttachmentResponse = (BenefitLineNotesAttachmentResponse) executeService(benefitLineNotesAttachmentRequest);
        }
        this.setNoteName("");
        return "";
    }


    /**
     * to validate the fields that are entered through the jsp page
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList(1);
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


    /**
     * @return Returns standardBenefitNoteAttachmentRequest Gets the standard
     *         benefit and the note to be attached to benefit
     */
    private BenefitLineNotesAttachmentRequest getBenefitLineNotesAttachmentRequest() {
        List notesIdList = null;
        List versionList = null;
        //      Create a request object
        BenefitLineNotesAttachmentRequest benefitLineNotesAttachmentRequest = (BenefitLineNotesAttachmentRequest) this
                .getServiceRequest(ServiceManager.ATTACH_BENEFIT_LINE_NOTES_REQUEST);
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
        // changed 28thNov
        if(null == getStandardBenefitSessionObject().getBenefitDefinitionKey()){
        	standardBenefitVO.setBenefitDefinitionKey(getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString());
            
        }else{
        	standardBenefitVO.setBenefitDefinitionKey(getStandardBenefitSessionObject().getBenefitDefinitionKey());
        }
        //change ends
        benefitLineNotesAttachmentRequest
                .setStandardBenefitVO(standardBenefitVO);
        benefitLineNotesAttachmentRequest
                .setBenefitLineId(this.getBnftLineId());
        return benefitLineNotesAttachmentRequest;
    }


    /**
     * This method is to get the Standard benefit session object
     * 
     * @return
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


    /**
     * This method is to remove the attached notes from the corresponding
     * benefit line.
     * 
     * @return
     */
    public String deleteBenefitLineNotes() {
    	String [] selectedIds = noteIdsForDeletion.split("~");
    	if(selectedIds != null && selectedIds.length > 0) {
    		List noteIds = new ArrayList();
    		for(int i=0; i<selectedIds.length; i++) {
    			noteIds.add(selectedIds[i]);
    		}
    		
	        DeleteStandardBenefitNotesRequest request = (DeleteStandardBenefitNotesRequest) this
	                .getServiceRequest(ServiceManager.DELETE_STANDARD_BENEFIT_NOTES_REQUEST);
	        // the entry to be deleted
	        request.setNotedIds(noteIds);
	        //set the entity type
	        request
	                .setEntityType(WebConstants.ATTACH_BENEFIT_LINE);
	        // benefitlineid
	        if (null != this.bnftLineId && !this.bnftLineId.equals("")) {
	            request.setEntityId(Integer
	                    .parseInt(this.bnftLineId));
	        }
	
	        request
	                .setVersion(getStandardBenefitSessionObject()
	                        .getStandardBenefitVersionNumber());
	
	        request
	                .setStatus(getStandardBenefitSessionObject()
	                        .getStandardBenefitStatus());
	        request
	                .setStandardBenefitName(getStandardBenefitSessionObject()
	                        .getStandardBenefitName());
	        request
	                .setBusinessDomains(getStandardBenefitSessionObject()
	                        .getBusinessDomains());
	        //changed 28thNov
	        if(null == getStandardBenefitSessionObject().getBenefitDefinitionKey()){
	        	request.setBenefitDefinitionKey(getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString());
	            
	        }else{
	        	request.setBenefitDefinitionKey(getStandardBenefitSessionObject().getBenefitDefinitionKey());
	        }
	        //change ends
	        //call the executeservice method of the service class
	        DeleteStandardBenefitNotesResponse deleteStandardBenefitNotesResponse = (DeleteStandardBenefitNotesResponse) executeService(request);
	        return "";
    	}
    	return "";
    }


    /**
     * Returns the associatedNotesList
     * 
     * @return List associatedNotesList.
     */

    public List getAssociatedNotesList() {
    	this.setNoteName("");
        LocateStandardBenefitNotesRequest locateStandardBenefitNotesRequest = (LocateStandardBenefitNotesRequest) this
                .getServiceRequest(ServiceManager.LOCATE_STANDARD_BENEFIT_NOTES_REQUEST);
        if (null != this.bnftLineId && !this.bnftLineId.equals("")) {
            locateStandardBenefitNotesRequest.setEntityId(Integer
                    .parseInt(this.bnftLineId));
        }
        locateStandardBenefitNotesRequest
                .setEntityType(WebConstants.ATTACH_BENEFIT_LINE);
        locateStandardBenefitNotesRequest
                .setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
       //changed 28thNov
        if(null != getSession().getAttribute("SESSION_BNFT_DEFN_ID"))
        	locateStandardBenefitNotesRequest.setBenefitDefinitionKey(getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString());
        //change ends
        LocateStandardBenefitNotesResponse locateStandardBenefitNotesResponse = (LocateStandardBenefitNotesResponse) executeService(locateStandardBenefitNotesRequest);
        if (null != locateStandardBenefitNotesResponse
                && null != locateStandardBenefitNotesResponse
                        .getBenefitNotesList()
                && locateStandardBenefitNotesResponse.getBenefitNotesList()
                        .size() > 0)
            associatedNotesList = locateStandardBenefitNotesResponse
                    .getBenefitNotesList();
        else
            associatedNotesList = null;
        return associatedNotesList;
    }


    /**
     * Sets the associatedNotesList
     * 
     * @param associatedNotesList.
     */

    public void setAssociatedNotesList(List associatedNotesList) {
        this.associatedNotesList = associatedNotesList;
    }


    /**
     * Returns the benefitLineNoteId
     * 
     * @return String benefitLineNoteId.
     */

    public String getBenefitLineNoteId() {
        return benefitLineNoteId;
    }


    /**
     * Sets the benefitLineNoteId
     * 
     * @param benefitLineNoteId.
     */

    public void setBenefitLineNoteId(String benefitLineNoteId) {
        this.benefitLineNoteId = benefitLineNoteId;
    }


    /**
     * Returns the noteName
     * 
     * @return String noteName.
     */

    public String getNoteName() {
        return noteName;
    }


    /**
     * Sets the noteName
     * 
     * @param noteName.
     */

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }


    /**
     * Returns the requiredNoteName
     * 
     * @return boolean requiredNoteName.
     */

    public boolean isRequiredNoteName() {
        return requiredNoteName;
    }


    /**
     * Sets the requiredNoteName
     * 
     * @param requiredNoteName.
     */

    public void setRequiredNoteName(boolean requiredNoteName) {
        this.requiredNoteName = requiredNoteName;
    }


    /**
     * Returns the validationMessages
     * 
     * @return List validationMessages.
     */

    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * Sets the validationMessages
     * 
     * @param validationMessages.
     */

    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the loadAssociatedNotes
     * 
     * @return int loadAssociatedNotes.
     */

    public int getLoadAssociatedNotes() {
        // get the associated notes for the benefit line
        loadAssociatedNotesForBnftLine();
        
        this.setBreadCrumbText(WebConstants.STANDARD_BENEFIT
                + getStandardBenefitSessionObject().getStandardBenefitName()
                + ") >> Notes Attachment >> Edit");
        return loadAssociatedNotes;
    }


    /**
     * Sets the loadAssociatedNotes
     * 
     * @param loadAssociatedNotes.
     */

    public void setLoadAssociatedNotes(int loadAssociatedNotes) {
        this.loadAssociatedNotes = loadAssociatedNotes;
    }


    /**
     * This method gets the avilable notes for the selected benefit line.
     * Returns the availableNotes
     * 
     * @return List availableNotes.
     */

    public List getAvailableNotes() {
        return availableNotes;
    }


    /**
     * Sets the availableNotes
     * 
     * @param availableNotes.
     */

    public void setAvailableNotes(List availableNotes) {
        this.availableNotes = availableNotes;
    }


    /**
     * Returns the benefitTermCode
     * 
     * @return String benefitTermCode.
     */

    public String getBenefitTermCode() {
        return benefitTermCode;
    }


    /**
     * Sets the benefitTermCode
     * 
     * @param benefitTermCode.
     */

    public void setBenefitTermCode(String benefitTermCode) {
        this.benefitTermCode = benefitTermCode;
    }


    /**
     * Returns the bnftLineId
     * 
     * @return String bnftLineId.
     */

    public String getBnftLineId() {
        return bnftLineId;
    }


    /**
     * Sets the bnftLineId
     * 
     * @param bnftLineId.
     */

    public void setBnftLineId(String bnftLineId) {
        this.bnftLineId = bnftLineId;
    }


    /**
     * Returns the bnftQualifierCode
     * 
     * @return String bnftQualifierCode.
     */

    public String getBnftQualifierCode() {
        return bnftQualifierCode;
    }


    /**
     * Sets the bnftQualifierCode
     * 
     * @param bnftQualifierCode.
     */

    public void setBnftQualifierCode(String bnftQualifierCode) {
        this.bnftQualifierCode = bnftQualifierCode;
    }


    /**
     * Returns the loadAssociatedNotesForView
     * 
     * @return int loadAssociatedNotesForView.
     */

    public int getLoadAssociatedNotesForView() {
        if (null != getRequest().getParameter(WebConstants.BENEFIT_LINE_ID)
                && !getRequest().getParameter(WebConstants.BENEFIT_LINE_ID)
                        .equals("")) {
            this.setBnftLineId(getRequest().getParameter(
                    WebConstants.BENEFIT_LINE_ID));
        }
        return loadAssociatedNotesForView;
    }


    /**
     * Sets the loadAssociatedNotesForView
     * 
     * @param loadAssociatedNotesForView.
     */

    public void setLoadAssociatedNotesForView(int loadAssociatedNotesForView) {
        this.loadAssociatedNotesForView = loadAssociatedNotesForView;
    }


    /**
     * Returns the noteText
     * 
     * @return String noteText.
     */

    public String getNoteText() {
        return noteText;
    }


    /**
     * Sets the noteText
     * 
     * @param noteText.
     */

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }


    /**
     * Returns the noteType
     * 
     * @return String noteType.
     */

    public String getNoteType() {
        return noteType;
    }


    /**
     * Sets the noteType
     * 
     * @param noteType.
     */

    public void setNoteType(String noteType) {
        this.noteType = noteType;
    }
    
	/**
	 * @return Returns the noteIdsForDeletion.
	 */
	public String getNoteIdsForDeletion() {
		return noteIdsForDeletion;
	}
	/**
	 * @param noteIdsForDeletion The noteIdsForDeletion to set.
	 */
	public void setNoteIdsForDeletion(String noteIdsForDeletion) {
		this.noteIdsForDeletion = noteIdsForDeletion;
	}
}