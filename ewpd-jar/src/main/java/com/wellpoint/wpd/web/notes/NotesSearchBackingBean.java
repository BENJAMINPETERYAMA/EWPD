/*
 * NotesSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.notes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.notes.request.DeleteNotesRequest;
import com.wellpoint.wpd.common.notes.request.NotesLifeCycleRequest;
import com.wellpoint.wpd.common.notes.request.NotesSearchRequest;
import com.wellpoint.wpd.common.notes.response.DeleteNotesResponse;
import com.wellpoint.wpd.common.notes.response.NotesSearchResponse;
import com.wellpoint.wpd.common.notes.response.RetrieveNotesResponse;
import com.wellpoint.wpd.common.notes.vo.NotesVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Backing bean for searching Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class NotesSearchBackingBean extends WPDBackingBean {
	
	private String noteId;
	
	private int versionNo;	
	
	private String noteName;
	
	private String noteType;	
	
	private String systemDomain;	
	
	private List systemDomainList;
	
	private List validationMessages ;
	
	private List notesSearchResultList;
	
	private static final int SIZE = 50;
	
	private String action = null;
	
	private List messages;
	
	private List allVersionList;
	
	private String noteNameForLifeCycles;
	
	private String benefits;
	
	private String benefitComponents;
	
	private String products;
	
	private String terms;
	
	private String qualifiers;
	
	private List availBenefits;
	
	private List availBenefitComponents;
	
	private List availProducts;
	
	private List availTerms;
	
	private List availQualifiers;
	
	private String pageLoad;
	
	private String noteNameForDelete;
	
	private List noteTypeList;
	
	private String noteIdForLocate;
	
	private boolean actionForViewAll;
	
	private String noteSearchPrint;
	
	private boolean printPageLoad = false;
	
	private String printBreadCrumbText;

	private String oldNoteName;
	
    /**
     * @return Returns the noteIdForLocate.
     */
    public String getNoteIdForLocate() {
        return noteIdForLocate;
    }
    /**
     * @param noteIdForLocate The noteIdForLocate to set.
     */
    public void setNoteIdForLocate(String noteIdForLocate) {
        this.noteIdForLocate = noteIdForLocate;
    }
    /**
     * @return Returns the noteNameForDelete.
     */
    public String getNoteNameForDelete() {
        return noteNameForDelete;
    }
    /**
     * @param noteNameForDelete The noteNameForDelete to set.
     */
    public void setNoteNameForDelete(String noteNameForDelete) {
        this.noteNameForDelete = noteNameForDelete;
    }
	/**
	 * @return Returns the availBenefitComponents.
	 */
	public List getAvailBenefitComponents() {
		return availBenefitComponents;
	}
	/**
	 * @param availBenefitComponents The availBenefitComponents to set.
	 */
	public void setAvailBenefitComponents(List availBenefitComponents) {
		this.availBenefitComponents = availBenefitComponents;
	}
	/**
	 * @return Returns the availBenefits.
	 */
	public List getAvailBenefits() {
		return availBenefits;
	}
	/**
	 * @param availBenefits The availBenefits to set.
	 */
	public void setAvailBenefits(List availBenefits) {
		this.availBenefits = availBenefits;
	}
	/**
	 * @return Returns the availProducts.
	 */
	public List getAvailProducts() {
		return availProducts;
	}
	/**
	 * @param availProducts The availProducts to set.
	 */
	public void setAvailProducts(List availProducts) {
		this.availProducts = availProducts;
	}
	/**
	 * @return Returns the availQualifiers.
	 */
	public List getAvailQualifiers() {
		return availQualifiers;
	}
	/**
	 * @param availQualifiers The availQualifiers to set.
	 */
	public void setAvailQualifiers(List availQualifiers) {
		this.availQualifiers = availQualifiers;
	}
	/**
	 * @return Returns the availTerms.
	 */
	public List getAvailTerms() {
		return availTerms;
	}
	/**
	 * @param availTerms The availTerms to set.
	 */
	public void setAvailTerms(List availTerms) {
		this.availTerms = availTerms;
	}
	/**
	 * @return Returns the benefitComponents.
	 */
	public String getBenefitComponents() {
		return benefitComponents;
	}
	/**
	 * @param benefitComponents The benefitComponents to set.
	 */
	public void setBenefitComponents(String benefitComponents) {
		this.benefitComponents = benefitComponents;
	}
	/**
	 * @return Returns the benefits.
	 */
	public String getBenefits() {
		return benefits;
	}
	/**
	 * @param benefits The benefits to set.
	 */
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	/**
	 * @return Returns the products.
	 */
	public String getProducts() {
		return products;
	}
	/**
	 * @param products The products to set.
	 */
	public void setProducts(String products) {
		this.products = products;
	}
	/**
	 * @return Returns the qualifiers.
	 */
	public String getQualifiers() {
		return qualifiers;
	}
	/**
	 * @param qualifiers The qualifiers to set.
	 */
	public void setQualifiers(String qualifiers) {
		this.qualifiers = qualifiers;
	}
	/**
	 * @return Returns the terms.
	 */
	public String getTerms() {
		return terms;
	}
	/**
	 * @param terms The terms to set.
	 */
	public void setTerms(String terms) {
		this.terms = terms;
	}
	/**
	 * No Argument constructor
	 *
	 */
	public NotesSearchBackingBean() {
		
		validationMessages = new ArrayList();
		setBreadCrump();
		
	}
	/**
	 * Sets the bread crump text.
	 */
	protected void setBreadCrump(){
		setBreadCrumbText("Notes Library >> Note >> Locate");
	}		
	/**
	 * @return Returns the SIZE.
	 */
	public static int getSIZE() {
		return SIZE;
	}
	
	/**
	 * @return Returns the systemDomainList.
	 */
	public List getSystemDomainList() {
		return systemDomainList;
	}
	/**
	 * @param systemDomainList The systemDomainList to set.
	 */
	public void setSystemDomainList(List systemDomainList) {
		this.systemDomainList = systemDomainList;
	}
	/**
	 * @return Returns the notesSearchResultList.
	 */
	public List getNotesSearchResultList() {
		return notesSearchResultList;
	}
	/**
	 * @param notesSearchResultList The notesSearchResultList to set.
	 */
	public void setNotesSearchResultList(List notesSearchResultList) {
		this.notesSearchResultList = notesSearchResultList;
	}
	
	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	/**
	 * @return Returns the noteType.
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType The noteType to set.
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	/**
	 * @return Returns the systemDomain.
	 */
	public String getSystemDomain() {
		return systemDomain;
	}
	/**
	 * @param systemDomain The systemDomain to set.
	 */
	public void setSystemDomain(String systemDomain) {
		this.systemDomain = systemDomain;
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
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
	/**
	 * Method to validate whether any of the search criteria is given
	 * 
	 * @return boolean
	 */

	private boolean validateRequiredFields() {
		if(this.printPageLoad == false){
			validationMessages = new ArrayList();
			boolean valid = false;
			if ((null == this.noteName || "".equals(this.noteName.trim()))
			    && (null == this.noteIdForLocate || "".equals(this.noteIdForLocate.trim()) || "0".equals(this.noteIdForLocate.trim()))	
					&& (null == this.noteType || "".equals(this.noteType.trim()))				
						&& (null == this.systemDomain || "".equals(this.systemDomain.trim()))
							&& (null == this.products || "".equals(this.products.trim()))
									&& (null == this.benefits || "".equals(this.benefits.trim()))
											&& (null == this.benefitComponents || "".equals(this.benefitComponents.trim()))
													&& (null == this.terms || "".equals(this.terms.trim()))
															&& (null == this.qualifiers || "".equals(this.qualifiers.trim())))			
				valid = true;
			
			if (valid) {
			    if("0".equals(this.noteIdForLocate.trim()))
			        validationMessages.add(new ErrorMessage("noteId.field.invalid"));
			    else
			        validationMessages.add(new ErrorMessage(WebConstants.ATLEAST_ONE_SEARCH));
				notesSearchResultList = null;
				return false;
			}
		}
			return true;

		}	
	
	/**
	 * Method to perform search action.
	 * 
	 * @return String
	 */
	public String locateNotes() {
		
		NotesSearchResponse notesSearchResponse = null;
			//Checking atleast one search criteria is given
				if (!validateRequiredFields()) {
					addAllMessagesToRequest(validationMessages);
					return WebConstants.EMPTY_STRING;
				}
				else {
				
				//Getting the notesSearchRequest object
					NotesSearchRequest 	notesSearchRequest = this.getNotesSearchRequest();
				
				//Getting the locateMandateResponse after performing Search
					notesSearchResponse = (NotesSearchResponse) this
					.executeService(notesSearchRequest);
				
					if (null != notesSearchResponse) {
					
						List list = notesSearchResponse.getNotesSearchResultList();
						if(null != list && !list.isEmpty()){
							Iterator ite = list.iterator();
							while(ite.hasNext()){
								NoteBO noteBO = (NoteBO)ite.next();
								noteBO.setOldNoteName(noteBO.getNoteName());
								String desc = noteBO.getNoteText();
								if(desc.length() > 25){
									desc = desc.substring(0,25).concat("...");
								}
								noteBO.setNoteText(desc);
							}

						}
						//Setting the search result from the response to the backing
						// bean
						this.setNotesSearchResultList(list);
					}
					this.setBreadCrumbText("Notes Library >> Note >> Locate");
				} 
		return "maintainNotesPage";
	}
	

	/**
	 * Method to get NotesSearchRequest.
	 * 
	 * @return NotesSearchRequest
	 */
	private NotesSearchRequest getNotesSearchRequest() {
		
		//Creating the request
		NotesSearchRequest notesSearchRequest = (NotesSearchRequest) this
		.getServiceRequest(ServiceManager.NOTES_SEARCH_REQUEST);
		if(null != getRequest().getSession().getAttribute("notesVO")){
			notesSearchRequest.setNotesVO((NotesVO)getRequest().getSession().getAttribute("notesVO"));
		}
		//Setting the search criteria values to the request
// **Start** change made for String Conversion		
		/*int noteIdForSearch = 0;
		if(null != this.noteIdForLocate && !noteIdForLocate.equals(""))
		    noteIdForSearch = new Integer(this.noteIdForLocate).intValue();
		notesSearchRequest.getNotesVO().setNoteId(noteIdForSearch);*/
		notesSearchRequest.setMaxSize(NotesSearchBackingBean.SIZE);
		
		if(this.printPageLoad == false){
			if(null != this.noteIdForLocate && !noteIdForLocate.equals("")){
				notesSearchRequest.getNotesVO().setNoteId(this.noteIdForLocate.trim());
			}else{
				notesSearchRequest.getNotesVO().setNoteId("");
			}				
	// **End** change made for String Conversion	
			if(null != this.getNoteName() && !this.getNoteName().equals("")){
				notesSearchRequest.getNotesVO().setNoteName(checkForEscapeCharacter());
			}else{
				notesSearchRequest.getNotesVO().setNoteName("");
			}
			notesSearchRequest.getNotesVO().setNoteTypeList(
					WPDStringUtil.getListFromTildaString(this.getNoteType(),2,2,2));
			notesSearchRequest.getNotesVO().setSystemDomainList(
					WPDStringUtil.getListFromTildaString(this.getSystemDomain(),2,2,2));
			if(null == getRequest().getSession().getAttribute("notesVO")){
				getRequest().getSession().setAttribute("notesVO",notesSearchRequest.getNotesVO());
			}
		}		
		return notesSearchRequest;
		
	}
	
	/**
	 * This method is to check the escape character.
	 *
	 */
	private String checkForEscapeCharacter(){
	    String escapeCharacter = this.getNoteName().trim();
	    if(null != escapeCharacter){
		    escapeCharacter = escapeCharacter.replaceAll("#","##");
		    escapeCharacter = escapeCharacter.replaceAll("_","#_");
		    escapeCharacter = escapeCharacter.replaceAll("%","#%");
	    }
	    return escapeCharacter;
	}
	
	/**
	 * Method to get NotesLifeCycleRequest.
	 * 
	 * @return NotesLifeCycleRequest
	 */
	private NotesLifeCycleRequest getNotesLifeCycleRequest() {
		
		//Creating the request
		NotesLifeCycleRequest notesLifeCycleRequest = (NotesLifeCycleRequest) this
		.getServiceRequest(ServiceManager.NOTES_LIFE_CYCLE_REQUEST);
		
		//Setting the search criteria values to the request
		notesLifeCycleRequest.getNotesVO().setVersionNo(this.getVersionNo());
		notesLifeCycleRequest.getNotesVO().setNoteName(this.getNoteNameForLifeCycles());
		notesLifeCycleRequest.getNotesVO().setNoteId(this.getNoteId());		
		
		return notesLifeCycleRequest;
		
	}

	/**
	 * Method for Note's LifeCycle Events (send For Test, Test Pass, Test Fail, Publish)
	 * 
	 * @return returnMessage String
	 */
	public String noteLifeCycleEvents() {
		String returnMessage = null;
		NotesLifeCycleRequest notesLifeCycleRequest = getNotesLifeCycleRequest();
		notesLifeCycleRequest.setAction(this.getAction());
		RetrieveNotesResponse retrieveNotesResponse = (RetrieveNotesResponse) executeService(notesLifeCycleRequest);
		if(null != retrieveNotesResponse){
			this.setMessages(retrieveNotesResponse.getMessages());	
			if(!(this.actionForViewAll)){
				returnMessage = this.locateNotes();
			}
				//Adding all the messages to the request
				addAllMessagesToRequest(messages);
				if((this.actionForViewAll)){
					this.getSession().setAttribute("lifecyclemessages",this.getMessages());
				}
// **Start** change made for String Conversion				
				//this.getSession().setAttribute("noteID",new Integer(this.getNoteId()));
				this.getSession().setAttribute("noteID",this.getNoteId());
// **End** change made for String Conversion				
				this.getSession().setAttribute("noteName",this.getNoteNameForLifeCycles());
				this.getSession().setAttribute("noteVersion",new Integer(this.getVersionNo()));
			}
		return returnMessage;
	}	
	
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the messages.
	 */
	public List getMessages() {
		return messages;
	}
	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}
	
	/**
	 * Returns the noteNameForLifeCycles
	 * @return String noteNameForLifeCycles.
	 */
	public String getNoteNameForLifeCycles() {
		return noteNameForLifeCycles;
	}
	/**
	 * Sets the noteNameForLifeCycles
	 * @param noteNameForLifeCycles.
	 */
	public void setNoteNameForLifeCycles(String noteNameForLifeCycles) {
		this.noteNameForLifeCycles = noteNameForLifeCycles;
	}
	/**
	 * @return Returns the allVersionList.
	 */
	public List getAllVersionList() {	
		return allVersionList;
	}
	/**
	 * @param allVersionList The allVersionList to set.
	 */
	public void setAllVersionList(List allVersionList) {
		this.allVersionList = allVersionList;
	}
	
	/**
	 * @return Returns the pageLoad.
	 */
	public String getPageLoad() {

		return pageLoad;
	}
	/**
	 * @param pageLoad The pageLoad to set.
	 */
	public void setPageLoad(String pageLoad) {
		this.pageLoad = pageLoad;
	}
	
	/**
	 * This method is to delete the selected notes details from the table.
	 * @return
	 */

	public String deleteNotes(){
		// create the request
		DeleteNotesRequest deleteNotesRequest = (DeleteNotesRequest)
				this.getServiceRequest(ServiceManager.DELETE_NOTES);
		// set the note id in the request
		deleteNotesRequest.setNotesId(this.noteId);
		//deleteNotesRequest.setNotesName(this.noteNameForDelete);
		deleteNotesRequest.setNotesName(this.oldNoteName);
		deleteNotesRequest.setVersionNumber(this.versionNo);
		deleteNotesRequest.setDataDomainDelete(false);
		// get the response
		DeleteNotesResponse deleteNotesResponse =  
		    	(DeleteNotesResponse)executeService(deleteNotesRequest);
		// get the message 
		messages = deleteNotesResponse.getMessages();
		//locate notes again
		this.locateNotes();
		// set the messages
		HttpServletRequest request = getRequest();
		request.setAttribute("messages", messages);
		if((this.actionForViewAll)){
			this.getSession().setAttribute("deletemessage",this.getMessages());
		}	
		this.getSession().setAttribute("noteID",this.getNoteId());
		this.getSession().setAttribute("noteName",this.oldNoteName);
		this.getSession().setAttribute("noteVersion",new Integer(this.getVersionNo()));
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * This method is to lock the selected notes details from the table.
	 * @return String;
	 */
	public String lockNote(){
		
		
		
		return "";
	}
	/**
	 * This method is to unlock the selected notes details from the table.
	 * @return String;
	 */
	public String unlockNote(){
		NotesLifeCycleRequest notesLifeCycleRequest = getNotesLifeCycleRequest();
		notesLifeCycleRequest.setAction(this.getAction());
		notesLifeCycleRequest.getNotesVO().setNoteId(this.getNoteId());
		notesLifeCycleRequest.getNotesVO().setVersionNo(this.getVersionNo());
		notesLifeCycleRequest.getNotesVO().setNoteName(this.getNoteName());				
		return "";
	}
	/**
	 * Returns the noteTypeList
	 * @return List noteTypeList.
	 */
	public List getNoteTypeList() {
		return noteTypeList;
	}
	/**
	 * Sets the noteTypeList
	 * @param noteTypeList.
	 */
	public void setNoteTypeList(List noteTypeList) {
		this.noteTypeList = noteTypeList;
	}
	
	/**
	 * Returns the actionForViewAll
	 * @return boolean actionForViewAll.
	 */
	public boolean isActionForViewAll() {
		return actionForViewAll;
	}
	/**
	 * Sets the actionForViewAll
	 * @param actionForViewAll.
	 */
	public void setActionForViewAll(boolean actionForViewAll) {
		this.actionForViewAll = actionForViewAll;
	}
	/**
	 * @return Returns the noteSearchPrint.
	 */
	public String getNoteSearchPrint() {
		this.printPageLoad = true;
		this.locateNotes();
		return noteSearchPrint;
	}
	/**
	 * @param noteSearchPrint The noteSearchPrint to set.
	 */
	public void setNoteSearchPrint(String noteSearchPrint) {
		this.noteSearchPrint = noteSearchPrint;
	}
	/**
	 * @return Returns the printPageLoad.
	 */
	public boolean isPrintPageLoad() {
		return printPageLoad;
	}
	/**
	 * @param printPageLoad The printPageLoad to set.
	 */
	public void setPrintPageLoad(boolean printPageLoad) {
		this.printPageLoad = printPageLoad;
	}
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Notes Library >> Note ("+getRequest().getSession().getAttribute("noteName")+") >> Print";
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
	public String getOldNoteName() {
		return oldNoteName;
	}
	public void setOldNoteName(String oldNoteName) {
		this.oldNoteName = oldNoteName;
	}
}