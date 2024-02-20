/*
 * Created on Aug 24, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.contract;

import java.util.List;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.ContractTeiredNotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.response.ContractTeiredNotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.product.request.ProductQuestionTierNotesPopUpRequest;
import com.wellpoint.wpd.common.product.response.ProductQuestionNotesPopUpResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractTeiredBenefitAdministrationBackingBean extends
		ContractBackingBean {
	
	private String records;
	
	int i = 0;

	private String searchString;

	private String questionId;

	private String primaryEntityID;

	private String primaryType;

	private String benefitComponentId;

	private String benefitDefnId;

	private String adminLvlOptionAssnSystemId;

	private String prevNoteIdSelected;

	private String previousNoteVersion;

	private String noteStatus;

	private String noteId;

	private int noteVersion;

	private String noteName;

	private String version;
	
	private List questionNotes;
	
	private String noteAttached;
	
	private String tierSysId;
	
	private boolean postBack;
	
	private boolean isRestored;
		
	private String newNoteIdSelected;
	
	private String noteAction;
	
	private List notesListAssociatedtoQuestion;
	
	private String overrideStatus;
	

	public void setRecords(String records) {
		this.records = records;
	}
	
	public String getRecords() {
		
		if(null!=this.questionNotes) return records;
		ProductQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
			ProductQuestionNotesPopUpResponse response = (ProductQuestionNotesPopUpResponse) this
					.executeService(request);
					if(null!=response){
						setValuesToBackinBean(response);
					}
				return new String();		
		
	}
	private void setValuesToBackinBean(ProductQuestionNotesPopUpResponse response){
		
		Logger.logDebug("-----------------------------"+response.getNotesList().size());
		
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
	
	private ProductQuestionTierNotesPopUpRequest getQuestionNotesPopUpRequest() {
		ProductQuestionTierNotesPopUpRequest request = (ProductQuestionTierNotesPopUpRequest) this
		.getServiceRequest(ServiceManager.PRODUCT_QUESTION_TIER_NOTES_POPUP_REQUEST);
		if(null!=getRequest().getParameter("questionId")&& !("").equals(getRequest().getParameter("questionId"))){
		if(null!=getRequest().getParameter("questionId")  && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
			this.questionId = getRequest().getParameter("questionId");
			this.getSession().setAttribute("questionId",questionId);
			}
		}
		if(null!=getRequest().getParameter("primaryentityId")&& !("").equals(getRequest().getParameter("primaryentityId"))){
		if(null!=getRequest().getParameter("primaryentityId") && getRequest().getParameter("primaryentityId").matches("^[0-9a-zA-Z_]+$")){
			this.primaryEntityID = getRequest().getParameter("primaryentityId");
			this.getSession().setAttribute("primaryEntityID",primaryEntityID);
			}
		}
		if(null!=getRequest().getParameter("primaryEntytyType")&& !("").equals(getRequest().getParameter("primaryEntytyType"))){
		if(null!=getRequest().getParameter("primaryEntytyType") && getRequest().getParameter("primaryEntytyType").matches("^[0-9a-zA-Z_]+$")){
			this.primaryType =  getRequest().getParameter("primaryEntytyType");
			this.getSession().setAttribute("primaryType",primaryType);
			}
		}
		if(null!=getRequest().getParameter("bcId")&& !("").equals(getRequest().getParameter("bcId"))){
		if(null!=getRequest().getParameter("bcId") && getRequest().getParameter("bcId").matches("^[0-9a-zA-Z_]+$")){
			this.benefitComponentId =  getRequest().getParameter("bcId");
			this.getSession().setAttribute("benefitComponentId",benefitComponentId);
			}
		}
		if(null!=getRequest().getParameter("benefitDefnId")&& !("").equals(getRequest().getParameter("benefitDefnId"))){
		if(null!=getRequest().getParameter("benefitDefnId") && getRequest().getParameter("benefitDefnId").matches("^[0-9a-zA-Z_]+$")){
			this.benefitDefnId = getRequest().getParameter("benefitDefnId");
			this.getSession().setAttribute("benefitDefnId",benefitDefnId);
			}
		}
		if(null!=getRequest().getParameter("adminLvlOptionId")&& !("").equals(getRequest().getParameter("adminLvlOptionId"))){
		if(null!=getRequest().getParameter("adminLvlOptionId") && getRequest().getParameter("adminLvlOptionId").matches("^[0-9a-zA-Z_]+$")){
			this.adminLvlOptionAssnSystemId =getRequest().getParameter("adminLvlOptionId");
			this.getSession().setAttribute("adminLvlOptionAssnSystemId",adminLvlOptionAssnSystemId);
			}
		}
		if(null!=getRequest().getParameter("tierSysId")&& !("").equals(getRequest().getParameter("tierSysId"))){
		if(null!=getRequest().getParameter("tierSysId") && getRequest().getParameter("tierSysId").matches("^[0-9a-zA-Z_]+$")){
			this.tierSysId = getRequest().getParameter("tierSysId");
			this.getSession().setAttribute("TIERSYSID",tierSysId);
			}
		}
		
		request.setPrimaryEntityID((this.getSession().getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType").toString());
		
		if(null!=this.getSession().getAttribute("adminLvlOptionAssnSystemId") 
		        && !("").equals(this.getSession().getAttribute("adminLvlOptionAssnSystemId").toString())){
			this.adminLvlOptionAssnSystemId=(String)this.getSession().getAttribute("adminLvlOptionAssnSystemId").toString();
		    request.setSecondaryId(this.getSession().getAttribute("adminLvlOptionAssnSystemId").toString());
		}
		
		if(null!=this.getSession().getAttribute("benefitDefnId")
		        && !("").equals(this.getSession().getAttribute("benefitDefnId").toString())
		        && !("null").equals(this.getSession().getAttribute("benefitDefnId").toString())){
		this.setBenefitDefnId(this.getSession().getAttribute("benefitDefnId").toString())	;
		request.setBenefitDenId(this.getSession().getAttribute("benefitDefnId").toString());
		}
		if(null!=this.getSession().getAttribute("benefitComponentId")
		        && !("").equals(this.getSession().getAttribute("benefitComponentId").toString())
		        && !("null").equals(this.getSession().getAttribute("benefitComponentId").toString())){
			this.setBenefitComponentId(getRequest().getSession().getAttribute("benefitComponentId").toString());
		request.setBenefitComponentId(Integer.parseInt(getRequest().getSession().getAttribute("benefitComponentId").toString()));
		}
		if(null!=this.getSession().getAttribute("questionId") 
		        && !("").equals(this.getSession().getAttribute("questionId").toString())
		        && !("null").equals(this.getSession().getAttribute("questionId").toString())){
		this.setQuestionId(this.getSession().getAttribute("questionId").toString());
		request.setQuestionId(Integer.parseInt(this.getSession().getAttribute("questionId").toString()));
		}
		if(null!=this.getSession().getAttribute("TIERSYSID") 
		        && !("").equals(this.getSession().getAttribute("TIERSYSID").toString())
		        && !("null").equals(this.getSession().getAttribute("TIERSYSID").toString())){
		this.setTierSysId(this.getSession().getAttribute("TIERSYSID").toString());
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
	
	
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	public List getQuestionNotes() {

		i++;
		if(i==1){
			String searchString  = this.getRequest().getParameter("searchString");
			if(null!=searchString){
				this.searchString =searchString;
				ProductQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				ProductQuestionNotesPopUpResponse response = (ProductQuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
				}
			}
		}
		
		return questionNotes;
	}
	
	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}
	
	public String attachTeiredNotesToQuestion(){
		
		ContractTeiredNotesToQuestionAttachmentRequest contractTeiredNotesToQuestionAttachmentRequest =setValuesToRequest();
		
		ContractTeiredNotesToQuestionAttachmentResponse contractTeiredNotesToQuestionAttachmentResponse = (ContractTeiredNotesToQuestionAttachmentResponse) this
		.executeService(contractTeiredNotesToQuestionAttachmentRequest);

		List messageList = contractTeiredNotesToQuestionAttachmentResponse
		.getMessages();
		
		refreshQuestionNote(messageList);
		//ContractTeiredNotesToQuestionAttachmentResponse;
		
		return null;
	}
	
	private void refreshQuestionNote(List message) {

		ProductQuestionTierNotesPopUpRequest request = getQuestionNotesPopUpRequest();
		//if(null!=request.getPrimaryEntityID()&& !("").equals(request.getPrimaryEntityID())){
		ProductQuestionNotesPopUpResponse response = (ProductQuestionNotesPopUpResponse) this
			.executeService(request);
			if(null!=response){
				setValuesToBackinBean(response);
			}
			addAllMessagesToRequest(message);

	}
	
	private ContractTeiredNotesToQuestionAttachmentRequest setValuesToRequest(){
		
		ContractTeiredNotesToQuestionAttachmentRequest contractTeiredNotesToQuestionAttachmentRequest = (ContractTeiredNotesToQuestionAttachmentRequest) this
		.getServiceRequest(ServiceManager.CNTRCT_TEIRED_NOTES_TO_QUESTION_ATTACHMENT_REQUEST);
		
		if(null!=this.getQuestionId()&& !"".equals(this.getQuestionId()))
		contractTeiredNotesToQuestionAttachmentRequest.setQuestionId(Integer.parseInt(this
				.getQuestionId()));
		contractTeiredNotesToQuestionAttachmentRequest.setNoteId(this.getNoteId());

		if(null!=this.getPrimaryEntityID()&& !"".equals(this.getPrimaryEntityID()))
		contractTeiredNotesToQuestionAttachmentRequest.setPrimaryId(Integer.parseInt(this
				.getPrimaryEntityID()));
		contractTeiredNotesToQuestionAttachmentRequest.setPrimaryEntityType("ATTACHCONTRACT");
		
		if(null!=this.getAdminLvlOptionAssnSystemId()&& !"".equals(this.getAdminLvlOptionAssnSystemId()))
		contractTeiredNotesToQuestionAttachmentRequest.setSecondaryId(Integer.parseInt(this
				.getAdminLvlOptionAssnSystemId()));
		
		contractTeiredNotesToQuestionAttachmentRequest.setBenefitCompId(Integer.parseInt(this
				.getBenefitComponentId()));
		contractTeiredNotesToQuestionAttachmentRequest.setBnftDefId(Integer.parseInt(this
				.getBenefitDefnId()));
		contractTeiredNotesToQuestionAttachmentRequest.setNoteOverrideStatus("F");

		contractTeiredNotesToQuestionAttachmentRequest.setNoteVersionNumber(this.noteVersion);

		contractTeiredNotesToQuestionAttachmentRequest.setSecondaryEntityType("ATTACHQUESTION");
		
		contractTeiredNotesToQuestionAttachmentRequest.setTierSysId(Integer.parseInt(this.tierSysId));

		if (this.noteStatus.equals("update"))
			contractTeiredNotesToQuestionAttachmentRequest.setUpdateRequest(true);
		if (this.noteStatus.equals("delete"))
			contractTeiredNotesToQuestionAttachmentRequest.setDeleteRequest(true);
		if (this.noteStatus.equals("insert"))
			contractTeiredNotesToQuestionAttachmentRequest.setInsertRequest(true);
		
		
		return contractTeiredNotesToQuestionAttachmentRequest;
	}
	
	
	
	
	

	public String getAdminLvlOptionAssnSystemId() {
		return adminLvlOptionAssnSystemId;
	}
	public void setAdminLvlOptionAssnSystemId(String adminLvlOptionAssnSysId) {
		this.adminLvlOptionAssnSystemId = adminLvlOptionAssnSysId;
	}
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	public String getBenefitDefnId() {
		return benefitDefnId;
	}
	public void setBenefitDefnId(String benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getNoteStatus() {
		return noteStatus;
	}
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	public String getPreviousNoteVersion() {
		return previousNoteVersion;
	}
	public void setPreviousNoteVersion(String previousNoteVersion) {
		this.previousNoteVersion = previousNoteVersion;
	}
	public String getPrevNoteIdSelected() {
		return prevNoteIdSelected;
	}
	public void setPrevNoteIdSelected(String prevNoteIdSelected) {
		this.prevNoteIdSelected = prevNoteIdSelected;
	}
	public String getPrimaryEntityID() {
		return primaryEntityID;
	}
	public void setPrimaryEntityID(String primaryEntityID) {
		this.primaryEntityID = primaryEntityID;
	}
	public String getPrimaryType() {
		return primaryType;
	}
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getTierSysId() {
		return tierSysId;
	}
	public void setTierSysId(String tierSysId) {
		this.tierSysId = tierSysId;
	}
	public boolean isRestored() {
		return isRestored;
	}
	public void setRestored(boolean isRestored) {
		this.isRestored = isRestored;
	}
	public String getNewNoteIdSelected() {
		return newNoteIdSelected;
	}
	public void setNewNoteIdSelected(String newNoteIdSelected) {
		this.newNoteIdSelected = newNoteIdSelected;
	}
	public String getNoteAction() {
		return noteAction;
	}
	public void setNoteAction(String noteAction) {
		this.noteAction = noteAction;
	}
	public String getNoteAttached() {
		return noteAttached;
	}
	public void setNoteAttached(String noteAttached) {
		this.noteAttached = noteAttached;
	}
	public List getNotesListAssociatedtoQuestion() {
		return notesListAssociatedtoQuestion;
	}
	public void setNotesListAssociatedtoQuestion(
			List notesListAssociatedtoQuestion) {
		this.notesListAssociatedtoQuestion = notesListAssociatedtoQuestion;
	}
	public String getOverrideStatus() {
		return overrideStatus;
	}
	public void setOverrideStatus(String overrideStatus) {
		this.overrideStatus = overrideStatus;
	}
	public boolean isPostBack() {
		return postBack;
	}
	public void setPostBack(boolean postBack) {
		this.postBack = postBack;
	}
	public void setNoteVersion(int noteVersion) {
		this.noteVersion = noteVersion;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getNoteVersion() {
		return noteVersion;
	}
	public String getVersion() {
		return version;
	}
}
