/*
 * ProductBenefitAdministrationBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.List;

import com.wellpoint.wpd.common.contract.request.ContractQuestionTierNoteProcessRequest;
import com.wellpoint.wpd.common.contract.request.ContractQuestionTierNotesPopUpRequest;
import com.wellpoint.wpd.common.contract.response.ContractQuestionNoteResponse;
import com.wellpoint.wpd.common.contract.response.ContractQuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractQuestionTierNotesAttachmentBackingBean extends ContractBackingBean {
	
	private String noteId;
	
	private String noteName;
	
	private String overrideStatus;
	
	private int version;
	
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
	
	private String newNoteIdSelected;
	
	//private int questionId;
	
	private String noteAction;
	
	private int noteVersion;
	
	private String noteAttached;
	
	private String tierSysId;
	
	private String viewRecords;

	public String getRecords() {
		
		if(null!=this.questionNotes) return records;
		ContractQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
		request.setAction(WebConstants.EDIT_ACTION);
		ContractQuestionNotesPopUpResponse response = (ContractQuestionNotesPopUpResponse) this
					.executeService(request);
					if(null!=response){
						setValuesToBackinBean(response);
					}
				return new String();
		}
	
	
	public String getViewRecords() {
		
		if(null!=this.questionNotes) return records;
		ContractQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
		request.setAction(WebConstants.VIEW_ACTION);
		ContractQuestionNotesPopUpResponse response = (ContractQuestionNotesPopUpResponse) this
					.executeService(request);
					if(null!=response){
						setValuesToBackinBean(response);
					}
				return new String();
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
	 * @return Returns the i.
	 */
	public int getI() {
		return i;
	}
	/**
	 * @param i The i to set.
	 */
	public void setI(int i) {
		this.i = i;
	}
	/**
	 * @return Returns the isRestored.
	 */
	public boolean isRestored() {
		return isRestored;
	}
	/**
	 * @param isRestored The isRestored to set.
	 */
	public void setRestored(boolean isRestored) {
		this.isRestored = isRestored;
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
	 * @return Returns the notesListAssociatedtoQuestion.
	 */
	public List getNotesListAssociatedtoQuestion() {
		return notesListAssociatedtoQuestion;
	}
	/**
	 * @param notesListAssociatedtoQuestion The notesListAssociatedtoQuestion to set.
	 */
	public void setNotesListAssociatedtoQuestion(
			List notesListAssociatedtoQuestion) {
		this.notesListAssociatedtoQuestion = notesListAssociatedtoQuestion;
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
	 * @return Returns the questionNotes.
	 */
	public List getQuestionNotes() {
		i++;
		if(i==1){
			String searchString  = this.getRequest().getParameter("searchString");
			if(null!=searchString){
				this.searchString =searchString;
				ContractQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				request.setAction(WebConstants.EDIT_ACTION);
				ContractQuestionNotesPopUpResponse response = (ContractQuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
				}
			}
		}
		
		return questionNotes;
	}
	
	private ContractQuestionTierNotesPopUpRequest getQuestionNotesPopUpRequest(){
		ContractQuestionTierNotesPopUpRequest request = (ContractQuestionTierNotesPopUpRequest) this
		.getServiceRequest(ServiceManager.CONTRACT_QUESTION_TIER_NOTES_POPUP_REQUEST);
		if(null!=getRequest().getParameter("questionId")&& !("").equals(getRequest().getParameter("questionId"))){
		if(null!=getRequest().getParameter("questionId") && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
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
			if(null!=getRequest().getParameter("tierSysId") && getRequest().getParameter("tierSysId").matches("^[0-9a-zA-Z_]+$")){
				this.tierSysId = getRequest().getParameter("tierSysId");
				this.getSession().setAttribute("TIERSYSID",tierSysId);
				}
		}
		
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
		if(null!=this.getSession().getAttribute("TIERSYSID") 
		        && !("").equals(this.getSession().getAttribute("TIERSYSID").toString())
		        && !("null").equals(this.getSession().getAttribute("TIERSYSID").toString())){
		request.setTierSysId(Integer.parseInt(this.getSession().getAttribute("TIERSYSID").toString()));
		}
		
		if(null!=searchString && !("").equals(searchString)){
			String 	newSearchString = WPDStringUtil.escapeString(searchString);
			request.setSearchString("%"+newSearchString.trim().toUpperCase()+"%");
			request.setSearchAction(2);
		}else{
			request.setSearchAction(1);
		}
		request.setSecondarEntityType("ATTACHQUESTION");
		return request;
	}
	private void setValuesToBackinBean(ContractQuestionNotesPopUpResponse response){
		
		this.setQuestionNotes(response.getNotesList());
		if(null!= questionNotes && questionNotes.size()>0){
			for(int i=0;i<questionNotes.size();i++){	
				TierNotesAttachmentOverrideBO overridebo= (TierNotesAttachmentOverrideBO)questionNotes.get(i);
				
				if(overridebo.getOverrideStatus().equals("Y")){
					this.noteAttached="Y";
					break;
				}else{
					this.noteAttached="N";	
				}
			}
			}
	}
	public String saveAction(){
		//Create request
		ContractQuestionTierNoteProcessRequest request = (ContractQuestionTierNoteProcessRequest) this
		.getServiceRequest(ServiceManager.CONTRACT_QUESTION_TIER_NOTES_PROCESS_REQUEST);
		
//		
//		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
//		.getAttribute(WebConstants.PROD_TYPE);
//		
//		request.setMainObjectIdentifier(productSessionObject
//				.getProductKeyObject().getProductName());
//		request.setDomainList(productSessionObject
//				.getProductKeyObject().getBusinessDomains());
//		request.setVersionNumber(productSessionObject
//				.getProductKeyObject().getVersion());
		
		if(null!= this.adminLvlOptionAssnSysId && !("").equals(this.adminLvlOptionAssnSysId)){
		int adminOptionLevelSysId = Integer.parseInt(this.adminLvlOptionAssnSysId);
		request.setSecondaryEntityId(adminOptionLevelSysId);
		}
		if(null!=this.primaryEntityID && !("").equals(this.primaryEntityID)){
		request.setPrimaryEntityId(Integer.parseInt(this.primaryEntityID));
		}
		request.setPrimaryType(WebConstants.ATTACH_CONTRACT);
		
		request.setNoteVersion(this.noteVersion);
		if(null!=this.questionId && !("").equals(this.questionId)){
		request.setQuestionId(Integer.parseInt(this.questionId));
		}
		if(null!=this.benefitDefnId && !("").equals(this.benefitDefnId)){
		request.setBenefitDefnId(Integer.parseInt(this.benefitDefnId));
		}
		if(null!=this.benefitComponentId && !("").equals(this.benefitComponentId)){
		request.setBenefitComponentId(Integer.parseInt(this.benefitComponentId));
		}
		if(null!=this.tierSysId && !("").equals(this.tierSysId)){
			request.setTierSysId(Integer.parseInt(this.tierSysId));
		}
		
		
		request.setNoteId(this.newNoteIdSelected);
		if(this.noteAction.equals("insert")){
			request.setNotesAction(WebConstants.INSERT_NOTE);
		}
		else if(this.noteAction.equals("update")){
			request.setNotesAction(WebConstants.UPDATE_NOTE);
		}else if(this.noteAction.equals("delete")){
			request.setNotesAction(WebConstants.DELETE_NOTE);
			this.prevNoteIdSelected=null;
		}
			
		ContractQuestionNoteResponse response = (ContractQuestionNoteResponse) this
		.executeService(request);
		List message = response.getMessages();
		refreshQuestionNote(message);
		
		return null;
	
	}
	private void refreshQuestionNote(List message ){
		
		ContractQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
		request.setAction(WebConstants.EDIT_ACTION);
		//if(null!=request.getPrimaryEntityID()&& !("").equals(request.getPrimaryEntityID())){
		ContractQuestionNotesPopUpResponse response = (ContractQuestionNotesPopUpResponse) this
			.executeService(request);
			if(null!=response){
				setValuesToBackinBean(response);
			}
			addAllMessagesToRequest(message);
		
	}
	/**
	 * @param questionNotes The questionNotes to set.
	 */
	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}
	/**
	 * @param records The records to set.
	 */
	public void setRecords(String records) {
		this.records = records;
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
	 * @return Returns the overrideStatus.
	 */
	public String getOverrideStatus() {
		return overrideStatus;
	}
	/**
	 * @param overrideStatus The overrideStatus to set.
	 */
	public void setOverrideStatus(String overrideStatus) {
		this.overrideStatus = overrideStatus;
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
	 * @return Returns the tierSysId.
	 */
	public String getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(String tierSysId) {
		this.tierSysId = tierSysId;
	}
}