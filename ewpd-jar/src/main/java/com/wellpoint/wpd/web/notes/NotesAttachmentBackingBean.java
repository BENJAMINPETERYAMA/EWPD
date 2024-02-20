/*
 * Created on May 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.notes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.BenefitLineNotesRequest;
import com.wellpoint.wpd.common.notes.request.NotesAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.response.BenefitLineNotesResponse;
import com.wellpoint.wpd.common.notes.response.NotesAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentVO;
import com.wellpoint.wpd.common.standardbenefit.request.BenefitQuestionNoteProcessRequest;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitQuestionNoteResponse;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.standardbenefit.StandardBenefitSessionObject;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NotesAttachmentBackingBean extends WPDBackingBean {

    private String noteId;

    private String noteName;
    
    private String noteDesc;

    private List notes;

    private String viewNoteName;
    
    private String version;
    
    private List associatedNotesList;
    
    private boolean renderNoteList=false;
    
    private String retrieveAllNotes;
    
    private List ewpdNotesList;
	private List legacyNotesList;
	
	private List associatedNotesListObj;
	
	private List notesListAssociatedtoQuestion;
	
	private String searchString;
	
	private List questionNotes;
	
	private String questionId ;
	
	private String primaryEntityID;
	
	private String primaryType ;
	
	private String benefitComponentId ;
	
	private String benefitDefnId ;
	
	private String adminLvlOptionAssnSysId;
	
	int i=0;
	
	private String prevNoteIdSelected;
	
	private boolean postBack;
	
	private String records;
	
	private boolean isRestored;
	
	private int previousNoteVersion;
	
	
	
	//private String noteId;
	
	private String newNoteIdSelected;
	
	//private int questionId;
	
	private String noteAction;
	
	private int noteVersion;
	
	private String noteAttached;
	
	private boolean recordsGreaterThanMaxSize;

	/**
	 * @return Returns the associatedNotesListObj.
	 */
	public List getAssociatedNotesListObj() {
		return associatedNotesList;
	}
	/**
	 * @param associatedNotesListObj The associatedNotesListObj to set.
	 */
	public void setAssociatedNotesListObj(List associatedNotesListObj) {
		this.associatedNotesList = associatedNotesListObj;
	}
	/**
	 * @return Returns the ewpdNotesList.
	 */
	public List getEwpdNotesList() {
		return ewpdNotesList;
	}
	/**
	 * @param ewpdNotesList The ewpdNotesList to set.
	 */
	public void setEwpdNotesList(List ewpdNotesList) {
		this.ewpdNotesList = ewpdNotesList;
	}
	/**
	 * @return Returns the legacyNotesList.
	 */
	public List getLegacyNotesList() {
		return legacyNotesList;
	}
	/**
	 * @param legacyNotesList The legacyNotesList to set.
	 */
	public void setLegacyNotesList(List legacyNotesList) {
		this.legacyNotesList = legacyNotesList;
	}
	/**
	 * @return Returns the retrieveAllNotes.
	 */
	public String getRetrieveAllNotes() {
		List messages = new ArrayList();
		  // Get the request object with the values set in it
        NotesAttachmentRequest notesLookUpRequest = getNotesLookUpRequest();
        
        if(null!=getRequest().getParameter("noteSearchCriteria"))
            notesLookUpRequest.setNoteName(getRequest().getParameter("noteSearchCriteria"));
        else
            notesLookUpRequest.setNoteName("");

        // Call the execute service() to get the response
        NotesAttachmentResponse notesLookUpResponse = (NotesAttachmentResponse) this
                .executeService(notesLookUpRequest);

        // Check whether notesList is not null and empty
        if (null != notesLookUpResponse.getNotesList()
                && !notesLookUpResponse.getNotesList().isEmpty()) {
            this.setNotes(notesLookUpResponse.getNotesList());
        } else {
            this.setNotes(null);
        }
        // For Legacy Notes
		if(notes != null){
			Iterator it = notes.iterator();
			while(it.hasNext()){
				Object noteObject = it.next();
				if(noteObject instanceof NotesAttachmentOverrideBO){
					NotesAttachmentOverrideBO note = (NotesAttachmentOverrideBO)noteObject;
					if(note.getLegacyIndicator().equals("Y")){
						if(legacyNotesList == null)legacyNotesList = new ArrayList();
						legacyNotesList.add(note);
					}else{
						if(ewpdNotesList == null)ewpdNotesList = new ArrayList();
							ewpdNotesList.add(note);
					}
				}
			}
		}
        
        messages = notesLookUpResponse.getMessages();
        addAllMessagesToRequest(messages);
		return retrieveAllNotes;
	}
	/**
	 * @param retrieveAllNotes The retrieveAllNotes to set.
	 */
	public void setRetrieveAllNotes(String retrieveAllNotes) {
		this.retrieveAllNotes = retrieveAllNotes;
	}
    /**
     * @return viewNoteName
     * 
     * Returns the viewNoteName.
     */
    public String getViewNoteName() {

        String id = getRequest().getParameter("noteId");

        String name = getRequest().getParameter("noteName");
        
        String version = getRequest().getParameter("version");
        // Get the request object
        NotesAttachmentRequest notesAttachmentRequest = (NotesAttachmentRequest) this
                .getServiceRequest(ServiceManager.NOTES_ATTACHMENT_REQUEST);

        // Create an instance of NotesAttachmentVO
        NotesAttachmentVO notesAttachmentVO = new NotesAttachmentVO();

        // Set the values in the VO
        notesAttachmentVO.setNoteId(id);
        notesAttachmentVO.setNoteName(name);
        notesAttachmentVO.setVersion(Integer.parseInt(version));

        // Set the VO to the request
        notesAttachmentRequest.setAttachmentVO(notesAttachmentVO);

        // Set the action in the request to View Note Details Action
        notesAttachmentRequest
                .setAction(NotesAttachmentRequest.VIEW_NOTE_DESCRIPTION);

        // Call the executeService() to get the response
        NotesAttachmentResponse notesAttachmentResponse = (NotesAttachmentResponse) this
                .executeService(notesAttachmentRequest);

        // Get the BO from the response
        NoteBO noteBO = notesAttachmentResponse.getNoteBO();

        // Check whether the values in the BO are null
        if (null != noteBO.getNoteName() && null != noteBO.getNoteText() && null!= noteBO.getNoteId()) {
            // Set the values in the backing bean properties
        	
        	if("Y".equals(noteBO.getLegacyIndicator())){
        		this.setNoteId("LEGACY");
        	}else{
        		this.setNoteId(noteBO.getNoteId());
        	}
            this.setViewNoteName(noteBO.getNoteName());
            this.setNoteDesc(noteBO.getNoteText());     
            this.setVersion(""+noteBO.getVersion());
        }
        return this.viewNoteName;
    }


    /**
     * @param viewNoteName
     * 
     * Sets the viewNoteName.
     */
    public void setViewNoteName(String viewNoteName) {
        this.viewNoteName = viewNoteName;
    }


    /**
     * @return Returns the noteDesc.
     */
    public String getNoteDesc() {
        return noteDesc;
    }


    /**
     * @param noteDesc
     *            The noteDesc to set.
     */
    public void setNoteDesc(String noteDesc) {
        this.noteDesc = WPDStringUtil.processNoteText(noteDesc);
    }


    /**
     * @return Returns the noteName.
     */
    public String getNoteName() {
        return this.noteName;
    }


    /**
     * @param noteName
     *            The noteName to set.
     */
    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    /**
     * @return Returns the notes.
     */
    public List getNotes() {
        return notes;
    }


    /**
     * This method is used to set all the values in the NotesLookUpequest object
     * 
     * @return NotesLookUpRequest
     */
    private NotesAttachmentRequest getNotesLookUpRequest() {
        // Get the entity type from the request
        String entityType = getRequest().getParameter("entityType");

        // Create an instance of LookUpVO
        NotesAttachmentVO lookUpVO = new NotesAttachmentVO();

        // Set the key values in the VO
        lookUpVO.setEntityType(entityType);

        // Get the action from the request
        int action = Integer
                .parseInt(getRequest().getParameter("lookUpAction"));

        // Get the request
        NotesAttachmentRequest notesLookUpRequest = (NotesAttachmentRequest) this
                .getServiceRequest(ServiceManager.NOTES_ATTACHMENT_REQUEST);

        // Get the entity id from session
        getValuesFromSession(entityType, lookUpVO, action);

        // Set the VO in the request
        notesLookUpRequest.setAttachmentVO(lookUpVO);

        // Set the action value in the request
        notesLookUpRequest.setAction(action);

        // Return the request
        return notesLookUpRequest;
    }


    /**
     * Method to set the values to the VO from the session based on the action to
     * be performed
     * 
     * action = 2 for normal look up
     * action = 3 for override look up 
     * 
     * @param entityType
     * @param httpSession
     * @param entityId
     * @return
     */
    private void getValuesFromSession(String entityType,
            NotesAttachmentVO attachmentVO, int action) {

        // Create the HttpSession object
        HttpSession httpSession = getSession();

        // Check which action to be performed and call the corresponding method
        if (action == 2) {
            getNotesAttachmentVOForLookUpAction(entityType, attachmentVO,
                    action, httpSession);
        } else if (action == 3) {
            getNotesAttachmentVOForOverrideLookUpAction(attachmentVO,
                    httpSession, entityType);
        }

    }


    /**
     * Method to set the values in the VO for getting the look up
     * for attaching notes to a component
     * 
     * @param entityType
     * @param attachmentVO
     * @param action
     * @param httpSession
     */
    private void getNotesAttachmentVOForLookUpAction(String entityType,
            NotesAttachmentVO attachmentVO, int action, HttpSession httpSession) {
        // Create an int variable
        int entityId = 0;

        // Create an int variable for parent key
        int parentId = 0;

        // Get the session object from the session based on the entity type
        if (null != entityType) {
            if (entityType.equals("ATTACHBENEFIT")
                    || entityType.equals("ATTACHLEVEL")) {

                // Get the session object
                StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) httpSession
                        .getAttribute("standard");

                if (null != standardBenefitSessionObject) {
                    // Get the entity id
                    entityId = standardBenefitSessionObject
                            .getStandardBenefitKey();

                    if (action == 2) {
                        parentId = standardBenefitSessionObject
                                .getStandardBenefitParentKey();

                        attachmentVO.setAvailableEntityType("AVAILBENEFIT");
                    }
                }
            } else if (entityType.equals("ATTACHCOMP")) {
                // Get the session object
                BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) httpSession
                        .getAttribute("benefitComponent");

                if (null != benefitComponentSessionObject) {
                    // Get the entity id
                    entityId = benefitComponentSessionObject
                            .getBenefitComponentId();

                    if (action == 2) {
                        parentId = benefitComponentSessionObject
                                .getBenefitComponentParentId();

                        attachmentVO.setAvailableEntityType("AVAILCOMP");
                    }
                }
            } else if (entityType.equals("ATTACHPRODUCT")) {
                // Get the session object
                ProductSessionObject productSessionObject = (ProductSessionObject) httpSession
                        .getAttribute("product");

                if (null != productSessionObject) {
                    // Get the entity id
                    entityId = productSessionObject.getProductKeyObject()
                            .getProductId();

                    if (action == 2) {
                        parentId = productSessionObject.getProductKeyObject()
                                .getParentId();

                        attachmentVO.setAvailableEntityType("AVAILPRODUCT");
                    }
                }
            }
        }

        // Set the values in the VO
        attachmentVO.setEntityId(entityId);
        attachmentVO.setAvailableEntityId(parentId);
    }


    /**
     * Method to set the values into the VO for getting the look up when 
     * overriding the notes attachment at higher levels
     * 
     * @param attachmentVO
     */
    private void getNotesAttachmentVOForOverrideLookUpAction(
            NotesAttachmentVO attachmentVO, HttpSession httpSession,
            String entityType) {
        // Get the primary entity type from request
        String primaryEntityType = getRequest().getParameter(
                "primaryEntityType");

        int entityId = 0, parentId = 0,benefitComponentId = 0;

        String enttyType = "";

        // Get the primary entity id from session
        if (null != primaryEntityType) {
            if (primaryEntityType.equals("benefitLevel")
                    || primaryEntityType.equals("benefit")) {

                // Get the session object
                StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) httpSession
                        .getAttribute("standard");

                if (null != standardBenefitSessionObject) {
                    // Get the entity id
                    entityId = standardBenefitSessionObject
                            .getStandardBenefitKey();
                    enttyType = "ATTACHBENEFIT";

                }
            } else if (primaryEntityType.equals("benftComp")) {
                // Get the session object
                BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) httpSession
                        .getAttribute("benefitComponent");

                if (null != benefitComponentSessionObject) {
                    // Get the entity id
                    entityId = benefitComponentSessionObject
                            .getBenefitComponentId();
                    
                    
                    int standardBenefitId = Integer.parseInt(getSession()
                            .getAttribute("SESSION_BNFT_ID").toString());
                    enttyType = "ATTACHCOMP";
                    attachmentVO.setEntityId(standardBenefitId);
                    attachmentVO.setBenefitComponentId(entityId);

                }
            } /*else if (primaryEntityType.equals("productStructure")) {
                // Get the session object
                ProductStructureSessionObject productStructureSessionObject = (ProductStructureSessionObject) httpSession
                        .getAttribute("productStructure");

                if (null != productStructureSessionObject) {
                    // Get the entity id
                    entityId = productStructureSessionObject.getId();
                    
                }
            }*/ else if (primaryEntityType.equals("product")) {
                // Get the session object
                ProductSessionObject productSessionObject = (ProductSessionObject) httpSession
                        .getAttribute("product");

                if (null != productSessionObject) {
                    // Get the entity id
                    entityId = productSessionObject.getProductKeyObject()
                            .getProductId();
                    
                    enttyType = "ATTACHPRODUCT";
                    
                    benefitComponentId = productSessionObject.getBenefitComponentId();

                    int subComponentId = 0;

                    // Check which sub component notes attachment to be overrided
                    // and get its id from the session object
                    if (entityType.equals("ATTACHCOMP")) {
                        subComponentId = productSessionObject
                                .getBenefitComponentId();

                    } else if (entityType.equals("ATTACHBENEFIT")) {
                        subComponentId = productSessionObject.getBenefitId();
                    }

                    // Set the subComponent id to the VO
                    attachmentVO.setEntityId(subComponentId);
                    attachmentVO.setBenefitComponentId(benefitComponentId);
                }
            }
            else if (primaryEntityType.equals("contract")) {
                // Get the session object
                ContractSession contractSessionObject = (ContractSession) httpSession
                        .getAttribute("contract");

                if (null != contractSessionObject) {
                    // Get the entity id
                    entityId = contractSessionObject.getContractKeyObject().getDateSegmentId();
                            
                    
                    enttyType = "ATTACHCONTRACT";
                    
                    benefitComponentId = contractSessionObject.getBenefitComponentId();

                    int subComponentId = 0;

                    // Check which sub component notes attachment to be overrided
                    // and get its id from the session object
                    if (entityType.equals("ATTACHCOMP")) {
                        subComponentId = contractSessionObject
                                .getBenefitComponentId();

                    } else if (entityType.equals("ATTACHBENEFIT")) {
                        subComponentId = contractSessionObject.getBenefitId();
                    }

                    // Set the subComponent id to the VO
                    attachmentVO.setEntityId(subComponentId);
                    attachmentVO.setBenefitComponentId(benefitComponentId);
                }
            }
        }

        // Set the values to the VO
        attachmentVO.setAvailableEntityId(entityId);
        attachmentVO.setAvailableEntityType(enttyType);
    }


    /**
     * @param notes
     *            The notes to set.
     */
    public void setNotes(List notes) {
        this.notes = notes;
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
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @return Returns the associatedNotesList.
	 */
	public List getAssociatedNotesList() {
		String primaryId = getRequest().getParameter("primaryId");
		String primaryType = getRequest().getParameter("primaryType");
		String secondaryID = getRequest().getParameter("bnftLineId");
		String secondaryType = getRequest().getParameter("secType");
		String benefitComponentId = getRequest().getParameter("benefitComponentId");
		  // Get the request
		String tierSysId = getRequest().getParameter("tierSysId");
		BenefitLineNotesRequest benefitLineNotesRequest = 
			(BenefitLineNotesRequest)this.getServiceRequest(ServiceManager.BENEFIT_LINE_NOTES_OVERRIDE_VIEW_REQUEST);
		benefitLineNotesRequest.setPrimaryId(primaryId);
		benefitLineNotesRequest.setPrimaryType(primaryType);
		benefitLineNotesRequest.setSecondaryID(secondaryID);
		benefitLineNotesRequest.setSecondaryType(secondaryType);
		benefitLineNotesRequest.setBenefitComponentId(benefitComponentId);
		if(null!=tierSysId){
			benefitLineNotesRequest.setTierSysId(Integer.parseInt(tierSysId));
		}
		BenefitLineNotesResponse benefitLineNotesResponse = (BenefitLineNotesResponse) this
        .executeService(benefitLineNotesRequest);
		this.associatedNotesList=benefitLineNotesResponse.getBenefitLineNotesList();
		if(associatedNotesList.size()>0){
			this.setRenderNoteList(true);
		}else{
			this.setRenderNoteList(false);
		}
		return associatedNotesList;
	}
	/**
	 * @param associatedNotesList The associatedNotesList to set.
	 */
	public void setAssociatedNotesList(List associatedNotesList) {
		this.associatedNotesList = associatedNotesList;
	}
    
	/**
	 * @return Returns the resnderNoteList.
	 */
	public boolean isRenderNoteList() {
		return renderNoteList;
	}
	/**
	 * @param resnderNoteList The resnderNoteList to set.
	 */
	public void setRenderNoteList(boolean renderNoteList) {
		this.renderNoteList = renderNoteList;
	}
	public String getLegacyNoteText(){
		 String id = getRequest().getParameter("noteId");
		 String name = getRequest().getParameter("noteName");
	     String version = getRequest().getParameter("version");
	     NotesAttachmentRequest notesAttachmentRequest = (NotesAttachmentRequest) this
	                .getServiceRequest(ServiceManager.NOTES_ATTACHMENT_REQUEST);
	     NotesAttachmentVO notesAttachmentVO = new NotesAttachmentVO();
	     notesAttachmentVO.setNoteId(id);
	     notesAttachmentVO.setNoteName(name);
	     notesAttachmentVO.setVersion(Integer.parseInt(version));
	     notesAttachmentRequest.setAttachmentVO(notesAttachmentVO);
	     notesAttachmentRequest
               .setAction(NotesAttachmentRequest.VIEW_NOTE_DESCRIPTION);

	     NotesAttachmentResponse notesAttachmentResponse = (NotesAttachmentResponse) this
               .executeService(notesAttachmentRequest);

	     NoteBO noteBO = notesAttachmentResponse.getNoteBO();
	     if(noteBO != null){
	     	return noteBO.getNoteText();
	     }
	     return "";
		
	}
	public String getRecords() {
			
		if(null!=this.questionNotes) return records;
			QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
				}
			return new String();
	}
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	/**
	 * @return Returns the questionNotes.
	 */
	public List getQuestionNotes() {
		i++;
		if(i==1){
			String searchString  = this.getRequest().getParameter("searchString");		
			if(null!=searchString){
				this.searchString =searchString;
				QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
					if(response.isRecordsGrtThanMaxSize()){
					    setRecordsGreaterThanMaxSize(true);
					}
					else{
					    setRecordsGreaterThanMaxSize(false);
					}
					addAllMessagesToRequest(response.getMessages());
				}
			}
		}		
		return questionNotes;
	}
	/**
	 * @param questionNotes The questionNotes to set.
	 */
	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}
	
	private QuestionNotesPopUpRequest getQuestionNotesPopUpRequest(){	    
		QuestionNotesPopUpRequest request = (QuestionNotesPopUpRequest) this
		.getServiceRequest(ServiceManager.QUESTION_NOTES_POPUP_REQUEST);
		if(null!=getRequest().getParameter("questionId")&& !("").equals(getRequest().getParameter("questionId"))){
		if(null!=getRequest().getParameter("questionId")  && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
			this.questionId = getRequest().getParameter("questionId");
			this.getSession().setAttribute("questionId",questionId);
			}
		if(null!=getRequest().getParameter("primaryentityId") && getRequest().getParameter("primaryentityId").matches("^[0-9a-zA-Z_]+$")){
			this.primaryEntityID = getRequest().getParameter("primaryentityId");
			this.getSession().setAttribute("primaryEntityID",primaryEntityID);
			}
		if(null!=getRequest().getParameter("primaryEntytyType") && getRequest().getParameter("primaryEntytyType").matches("^[0-9a-zA-Z_]+$")){
			this.primaryType =  getRequest().getParameter("primaryEntytyType");
			this.getSession().setAttribute("primaryType",primaryType);
			}
		if(null!=getRequest().getParameter("bcId") && getRequest().getParameter("bcId").matches("^[0-9a-zA-Z_]+$")){
			this.benefitComponentId =  getRequest().getParameter("bcId");
			this.getSession().setAttribute("benefitComponentId",benefitComponentId);
			}
		if(null!=getRequest().getParameter("benefitDefnId") && getRequest().getParameter("benefitDefnId").matches("^[0-9a-zA-Z_]+$")){
			this.benefitDefnId = getRequest().getParameter("benefitDefnId");
			this.getSession().setAttribute("benefitDefnId",benefitDefnId);
			}
		if(null!=getRequest().getParameter("adminLvlOptionId") && getRequest().getParameter("adminLvlOptionId").matches("^[0-9a-zA-Z_]+$")){
			this.adminLvlOptionAssnSysId =getRequest().getParameter("adminLvlOptionId");
			this.getSession().setAttribute("adminLvlOptionAssnSysId",adminLvlOptionAssnSysId);
			}
		}
	//	this.noteAttached = getRequest().getParameter("notepresent");
		Logger.logDebug("this.noteAttached"+this.noteAttached);
		request.setPrimaryEntityID((this.getSession().getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType").toString());
		
		if(null!=this.getSession().getAttribute("adminLvlOptionAssnSysId") 
		        && !("").equals(this.getSession().getAttribute("adminLvlOptionAssnSysId").toString())){
		    request.setSecondaryId(this.getSession().getAttribute("adminLvlOptionAssnSysId").toString());
		}
		
		if(null!=this.getSession().getAttribute("benefitDefnId") 
		        && !("").equals(this.getSession().getAttribute("benefitDefnId").toString())
		        && !("null").equals(this.getSession().getAttribute("benefitDefnId").toString())){
		request.setBenefitDenId(this.getSession().getAttribute("benefitDefnId").toString());
		}
		if(null!=this.getSession().getAttribute("benefitComponentId") 
		        && !("").equals(this.getSession().getAttribute("benefitComponentId").toString())
		        && !("null").equals(this.getSession().getAttribute("benefitComponentId").toString())){
		request.setBenefitComponentId(Integer.parseInt(getRequest().getSession().getAttribute("benefitComponentId").toString()));
		}
		if(null!=this.getSession().getAttribute("questionId") 
		        && !("").equals(this.getSession().getAttribute("questionId").toString())
		        && !("null").equals(this.getSession().getAttribute("questionId").toString())){
		request.setQuestionId(Integer.parseInt(this.getSession().getAttribute("questionId").toString()));
		}
	
		if(null!=searchString && !("").equals(searchString)){
			String 	newSearchString = WPDStringUtil.escapeString(searchString);
			request.setSearchString("%"+newSearchString.trim().toUpperCase()+"%");
			request.setSearchAction(2);
		}else{
			request.setSearchAction(1);
		}
		request.setSecondaryEntityType("ATTACHQUESTION");
		return request;
	}
	
	private void setValuesToBackinBean(QuestionNotesPopUpResponse response){
		
		this.setQuestionNotes(response.getNotesList());
		if(null!= questionNotes && questionNotes.size()>0){
			for(int i=0;i<questionNotes.size();i++){	
				NotesAttachmentOverrideBO overridebo= (NotesAttachmentOverrideBO)questionNotes.get(i);
				if(overridebo.getOverrideStatus().equals("Y")){
					this.noteAttached="Y";
					break;
				}else{
					this.noteAttached="N";	
				}
			}
		}
	}
	/**
	 * @return Returns the adminLvlOptionAssnSysId.
	 */
	public String getAdminLvlOptionAssnSysId() {
		return adminLvlOptionAssnSysId;
	}
	/**
	 * @param adminLvlOptionAssnSysId The adminLvlOptionAssnSysId to set.
	 */
	public void setAdminLvlOptionAssnSysId(String adminLvlOptionAssnSysId) {
		this.adminLvlOptionAssnSysId = adminLvlOptionAssnSysId;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the benefitDefnId.
	 */
	public String getBenefitDefnId() {
		return benefitDefnId;
	}
	/**
	 * @param benefitDefnId The benefitDefnId to set.
	 */
	public void setBenefitDefnId(String benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
	/**
	 * @return Returns the primaryEntityID.
	 */
	public String getPrimaryEntityID() {
		return primaryEntityID;
	}
	/**
	 * @param primaryEntityID The primaryEntityID to set.
	 */
	public void setPrimaryEntityID(String primaryEntityID) {
		this.primaryEntityID = primaryEntityID;
	}
	/**
	 * @return Returns the primaryType.
	 */
	public String getPrimaryType() {
		return primaryType;
	}
	/**
	 * @param primaryType The primaryType to set.
	 */
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	/**
	 * @return Returns the questionId.
	 */
	public String getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId The questionId to set.
	 */
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return Returns the prevNoteIdSelected.
	 */
	public String getPrevNoteIdSelected() {
		return prevNoteIdSelected;
	}
	/**
	 * @param prevNoteIdSelected The prevNoteIdSelected to set.
	 */
	public void setPrevNoteIdSelected(String prevNoteIdSelected) {
		this.prevNoteIdSelected = prevNoteIdSelected;
	}
	/**
	 * This method perfotm insert,update and delete for question note in Benefit level
	 * 
	 * 
	 */
	public String saveAction(){
		//Create request
		BenefitQuestionNoteProcessRequest request = (BenefitQuestionNoteProcessRequest) this
		.getServiceRequest(ServiceManager.BENEFIR_QUESTION_NOTES_PROCESS_REQUEST);
		StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject) getSession()
		.getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
		int adminOptionLevelSysId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ASSN).toString()).intValue();
		request.setBenefitId(sessionObject
				.getStandardBenefitKey());
		request.setMainObjectIdentifier(sessionObject
				.getStandardBenefitName());
		request.setVersionNumber(sessionObject
				.getStandardBenefitVersionNumber());
		request.setDomainList(sessionObject
				.getBusinessDomains());
		request.setStatus(sessionObject
				.getStandardBenefitStatus());
		request.setParentSystemId(sessionObject
				.getStandardBenefitParentKey());
		
		request.setPrimaryEntityId(sessionObject
				.getStandardBenefitKey());
		request.setPrimaryType(WebConstants.ATTACH_BENEFIT);
		
		request.setSecondaryEntityId(adminOptionLevelSysId);
		
		request.setNoteVersion(this.noteVersion);
		if(null!= this.questionId && !("").equals(this.questionId)){
		request.setQuestionId(Integer.parseInt(this.questionId));
		}
		
		request.setNoteId(this.newNoteIdSelected);
	
		if(this.noteAction.equals(WebConstants.INSERT)){
			request.setNotesAction(WebConstants.INSERT_NOTE);
		}
		else if(this.noteAction.equals(WebConstants.UPDATE)){
			
			request.setNotesAction(WebConstants.UPDATE_NOTE);
		}else if(this.noteAction.equals(WebConstants.DELETE)){
			request.setNotesAction(WebConstants.DELETE_NOTE);
			this.prevNoteIdSelected=null;
		}
			
		BenefitQuestionNoteResponse response = (BenefitQuestionNoteResponse) this
		.executeService(request);
		List message = response.getMessages();
		refreshQuestionNote(message);
		
		
		
		return null;
	
	}
	/**
	 * @return Returns the postBack.
	 */
	public boolean isPostBack() {
		return postBack;
	}
	/**
	 * @param postBack The postBack to set.
	 */
	public void setPostBack(boolean postBack) {
		this.postBack = postBack;
	}
	public void setRecords(String records) {
		this.records = records;
	}
	/**
	 * @return Returns the previousNoteVersion.
	 */
	public int getPreviousNoteVersion() {
		return previousNoteVersion;
	}
	/**
	 * @param previousNoteVersion The previousNoteVersion to set.
	 */
	public void setPreviousNoteVersion(int previousNoteVersion) {
		this.previousNoteVersion = previousNoteVersion;
	}
	/**
	 * @return Returns the newNoteIdSelected.
	 */
	public String getNewNoteIdSelected() {
		return newNoteIdSelected;
	}
	/**
	 * @param newNoteIdSelected The newNoteIdSelected to set.
	 */
	public void setNewNoteIdSelected(String newNoteIdSelected) {
		this.newNoteIdSelected = newNoteIdSelected;
	}
	/**
	 * @return Returns the noteAction.
	 */
	public String getNoteAction() {
		return noteAction;
	} 
	/**
	 * @param noteAction The noteAction to set.
	 */
	public void setNoteAction(String noteAction) {
		this.noteAction = noteAction;
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
	
	private void refreshQuestionNote(List message ){
		
		QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
		//if(null!=request.getPrimaryEntityID()&& !("").equals(request.getPrimaryEntityID())){
			QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
			.executeService(request);
			if(null!=response){
				setValuesToBackinBean(response);
			}
			addAllMessagesToRequest(message);
		
	}
	/**
	 * @return Returns the noteAttached.
	 */
	public String getNoteAttached() {
		return noteAttached;
	}
	/**
	 * @param noteAttached The noteAttached to set.
	 */
	public void setNoteAttached(String noteAttached) {
		this.noteAttached = noteAttached;
	}
    /**
     * @return Returns the recordsGreaterThanMaxSize.
     */
    public boolean isRecordsGreaterThanMaxSize() {
        return recordsGreaterThanMaxSize;
    }
    /**
     * @param recordsGreaterThanMaxSize The recordsGreaterThanMaxSize to set.
     */
    public void setRecordsGreaterThanMaxSize(boolean recordsGreaterThanMaxSize) {
        this.recordsGreaterThanMaxSize = recordsGreaterThanMaxSize;
    }
}