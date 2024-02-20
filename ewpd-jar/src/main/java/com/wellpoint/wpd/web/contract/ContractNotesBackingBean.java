/*
 * ContractNotesBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.ContractNoteAttachmentRequest;
import com.wellpoint.wpd.common.contract.request.ContractNotesDeleteRequest;
import com.wellpoint.wpd.common.contract.request.ContractWebServiceRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractNoteRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.response.ContractNoteAttachmentResponse;
import com.wellpoint.wpd.common.contract.response.ContractNotesDeleteResponse;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractNoteResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.notes.bo.NoteBO;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractNotesBackingBean extends ContractBackingBean{

    private boolean noteNameValdn = true;   
    private List attachNotesList = null;    
    private String selectedNotesId = null;   
    private List noteListForPopup = null;
    List validationMessages = null;    
    boolean validationStatus = false;    
    private boolean requiredNoteName = false;    	
	private String noteId = "";	
	private String noteName = "" ;
	private String state;	
	private String status;
	private int version;
	private String verifyString = null;
	private boolean checkin = false;
	private String printValue;
	private String loadNote;
	private String retrieveContractNotesRecords;
	private boolean securityAccess;
	private boolean securityAccessForDelete;
	List notesMessageList = new ArrayList();
	private boolean hasValidationErrors;
	
	private List ewpdNotesList;
	private List legacyNotesList;
	
	// eBX error validation
	
	private String invokeWebService;
	
	// SSCR 16332  -START
	private List<EbxWebSerDisplayVO> ebxWebSerDisplayList = null;
	private int initEbxWs;
	private String webServiceFlag ="false";
	private int initEbxRuleId;
	private int initEbxSPSId;
	private List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst = null;
	private List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst = null;
	private String ruleId;
	private String spsId;
	EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO;
	private String ebxProcessInterruptMsg = null;
	
	public List<EbxWebSerDisplayVO> getEbxWebSerDisplayList() {
		return ebxWebSerDisplayList;
	}

	public void setEbxWebSerDisplayList(
			List<EbxWebSerDisplayVO> ebxWebSerDisplayList) {
		this.ebxWebSerDisplayList = ebxWebSerDisplayList;
	}

	public int getInitEbxWs() {
		this.ebxWebSerDisplayList = (List<EbxWebSerDisplayVO>) getSession().getAttribute("wSErrorDisplayList");
		this.ebxWebSerPopupDisplayVO =(EbxWebSerPopupDisplayVO)getSession().getAttribute("ebxWebSerPopupDisplayVO");
		this.ebxProcessInterruptMsg = (String)getSession().getAttribute("ebxProcessInterruptMsg");
		return initEbxWs;
	}
	
	public void setInitEbxWs(int initEbxWs) {
		this.initEbxWs = initEbxWs;
	}
	
	public String getWebServiceFlag() {
		return webServiceFlag;
	}

	public void setWebServiceFlag(String webServiceFlag) {
		this.webServiceFlag = webServiceFlag;
	}

	
	public int getInitEbxRuleId() {
		HttpServletRequest request = getRequest();
		String ruleId = null;
		if(null!=request.getParameter("ruleId")){
			ruleId=request.getParameter("ruleId");
		}
		if(ruleId != null){	
			this.setRuleId(ruleId);
			List<ContractWebServiceVO> contractWSErrorList =(List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
			this.setEbxWebSerRuleIdDisplayLst(DomainUtil.ruleIdPopupDisplay(contractWSErrorList,ruleId));
		}
		
		return initEbxRuleId;
	}

	public void setInitEbxRuleId(int initEbxRuleId) {
		this.initEbxRuleId = initEbxRuleId;
	}

	public int getInitEbxSPSId() {
		HttpServletRequest request = getRequest();
		String spsId = null;
		if(null!=request.getParameter("spsId")){
			spsId=request.getParameter("spsId");
		}
		if(spsId != null){	
			this.setSpsId(spsId);
			List<ContractWebServiceVO> contractWSErrorList =(List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
			this.setEbxWebSerSPSIdDisplayLst(DomainUtil.spsIdPopupDisplay(contractWSErrorList,spsId));
		}
		return initEbxSPSId;
	}

	public void setInitEbxSPSId(int initEbxSPSId) {
		this.initEbxSPSId = initEbxSPSId;
	}

	public List<EbxWebSerRuleIdDisplayVO> getEbxWebSerRuleIdDisplayLst() {
		return ebxWebSerRuleIdDisplayLst;
	}

	public void setEbxWebSerRuleIdDisplayLst(
			List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst) {
		this.ebxWebSerRuleIdDisplayLst = ebxWebSerRuleIdDisplayLst;
	}
	
	public List<EbxWebSerSPSIdDisplayVO> getEbxWebSerSPSIdDisplayLst() {
		return ebxWebSerSPSIdDisplayLst;
	}

	public void setEbxWebSerSPSIdDisplayLst(
			List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst) {
		this.ebxWebSerSPSIdDisplayLst = ebxWebSerSPSIdDisplayLst;
	}
	
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getSpsId() {
		return spsId;
	}

	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	
	public EbxWebSerPopupDisplayVO getEbxWebSerPopupDisplayVO() {
		return ebxWebSerPopupDisplayVO;
	}

	public void setEbxWebSerPopupDisplayVO(
			EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO) {
		this.ebxWebSerPopupDisplayVO = ebxWebSerPopupDisplayVO;
	}
	
	public String getEbxProcessInterruptMsg() {
		return ebxProcessInterruptMsg;
	}

	public void setEbxProcessInterruptMsg(String ebxProcessInterruptMsg) {
		this.ebxProcessInterruptMsg = ebxProcessInterruptMsg;
	}

	// SSCR 16332 - END

	

	/**
	 * @return the invokeWebService
	 */
	public String getInvokeWebService() {
		return invokeWebService;
	}

	/**
	 * @param invokeWebService the invokeWebService to set
	 */
	public void setInvokeWebService(String invokeWebService) {
		this.invokeWebService = invokeWebService;
	}
	
	//sscr 17571
	private String vendorFlag = "false";
	
	private String closePopup = "false";
		  
	
	public String getClosePopup() {
		return closePopup;
	}
	public void setClosePopup(String closePopup) {
		this.closePopup = closePopup;
	}
	
	public String getVendorFlag() {
		return vendorFlag;
	}
	public void setVendorFlag(String vendorFlag) {
		this.vendorFlag = vendorFlag;
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
	 * @return Returns the retrieveContractNotesRecords.
	 */
	public String getRetrieveContractNotesRecords() {
		
		ContractNoteAttachmentRequest contractNoteAttachmentRequest = (ContractNoteAttachmentRequest)this.getServiceRequest(ServiceManager.ATTACH_CONTRACT_NOTES_REQUEST);
		if(null!=getRequest().getParameter("noteSearchCriteria"))
			contractNoteAttachmentRequest.setNoteName(getRequest().getParameter("noteSearchCriteria"));
        else
        	contractNoteAttachmentRequest.setNoteName("");
		contractNoteAttachmentRequest.setAction(2);
		ContractNoteAttachmentResponse contractAttachNotesResponse =(ContractNoteAttachmentResponse) executeService(contractNoteAttachmentRequest) ;
		
		if(null!=contractAttachNotesResponse){
			noteListForPopup=contractAttachNotesResponse.getNoteList();
		}
		else
			noteListForPopup = null;
		
		this.setNoteListForPopup(noteListForPopup);
		if(noteListForPopup != null){
			Iterator it = noteListForPopup.iterator();
			while(it.hasNext()){
				NoteBO note = (NoteBO)it.next();
				if(note.getLegacyIndicator().equals("Y")){
					if(legacyNotesList == null)legacyNotesList = new ArrayList();
					legacyNotesList.add(note);
				}else{
					if(ewpdNotesList == null)ewpdNotesList = new ArrayList();
						ewpdNotesList.add(note);
				}
			}
		}
		return retrieveContractNotesRecords;
	}
	/**
	 * @param retrieveContractNotesRecords The retrieveContractNotesRecords to set.
	 */
	public void setRetrieveContractNotesRecords(
			String retrieveContractNotesRecords) {
		this.retrieveContractNotesRecords = retrieveContractNotesRecords;
	}
    /**
     * Default Constructor sets the Breadcrumb and values to backing bean.
     * @param 
     * @param 
     * @return void
     */
    public ContractNotesBackingBean(){
    	
    	setBreadCrumb();
    	setValuesToBackingBean();
    }
    
    /**
     * Attach a Contract Note. Only one Note can be attached to a Contract.
     * @param 
     * @param 
     * @return void
     */
    public String attachNotesForContract(){
		// check for empty fields
		 validationStatus = validateRequiredFields();
		if(!validationStatus){				
			addAllMessagesToRequest(validationMessages);
			getRequest().setAttribute("RETAIN_Value", "");
			return "";
			// proceed with attach if fields are non empty
		}else{			
			// call the getContractAttachNotesRequest method to create request
			ContractNoteAttachmentRequest contractNoteAttachmentRequest = getContractAttachNotesRequest();		
			// Create a response object
			ContractNoteAttachmentResponse contractAttachNotesResponse =(ContractNoteAttachmentResponse) executeService(contractNoteAttachmentRequest) ;
			this.attachNotesList = contractAttachNotesResponse.getNoteList();
			getSession().setAttribute("notesList",this.attachNotesList);
			getContractSessionObject().setAttachNotesList(this.attachNotesList);
			// validate if note already exists.
			validateNotesAttached();	
			this.notesMessageList = this.validationMessages;
		}	
	this.noteName = "";	
	getRequest().setAttribute("RETAIN_Value", "");
	return "";
	}
    
    /**
     * Validates the fields in the form.
     * @param 
     * @param 
     * @return void
     */
	 private boolean validateRequiredFields() {
       validationMessages = new ArrayList();
       boolean requiredField = false;
       this.requiredNoteName = false;        
       
       if (this.noteName == null || "".equals(this.noteName)) {
       	requiredNoteName = true;
           requiredField = true;
       }             
       if (requiredField) {
       	noteNameValdn = false;
           this.validationMessages.add(new ErrorMessage(
                   WebConstants.MANDATORY_FIELDS_NOTES_REQUIRED));
           return false;
       }   
       return true;
	 }
	 
	 /**
	  * Validates if a note is already attached to a Contract.
	  * @param 
	  * @param 
	  * @return void
	  */
	 private boolean validateNotesAttached(){
	 	if (this.attachNotesList.size()==1){ 
	 		if(this.notesMessageList.size() == 0){
	 			if(validationMessages == null)
	 				validationMessages = new ArrayList();
	        	this.validationMessages.add(new InformationalMessage(
	                    WebConstants.NOTE_ATTACHED_ALREADY));
	 		}
	 		this.verifyString = "XXX";
	 	}
	 	else if (this.attachNotesList.size()<1){
	 		this.verifyString = null;
	 	}
        return true;	 	
	 }
	 
	 /**
	  * Sets the Bread Crumb.
	  * @param 
	  * @param 
	  * @return void
	  */
	 private void setBreadCrumb() {
	 	
		if (getContractSession().getContractKeyObject().getContractId() == null)
			super.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB);
		else if(super.isEditMode()){
			super.setBreadCrumbText("Contract Configuration >> Notes "
					+ "("
					+ getContractSession()
							.getContractKeyObject().getContractId() + ") >> Edit");
		}else if(super.isViewMode()){
		    super.setBreadCrumbText("Contract Configuration >> Notes "
					+ "("
					+ getContractSession()
							.getContractKeyObject().getContractId() + ") >> View");
		}
	}
	 
	/**
	 * Returns the ContractSession object
	 * @param 
	 * @param 
	 * @return ContractSession
	 */
	protected ContractSession getContractSessionObject(){
    	ContractSession contractSessionObject = (ContractSession)
		getSession().getAttribute(WebConstants.CONTRACT_SESSION_KEY);
		if(contractSessionObject == null) {
			contractSessionObject = new ContractSession();
			getSession().setAttribute(WebConstants.CONTRACT_SESSION_KEY,contractSessionObject);
		}
		return contractSessionObject;
	}	
	
	/**
	 * Sets the values to Backing bean
	 * @param 
	 * @param 
	 * @return ContractSession
	 */
	private void setValuesToBackingBean(){
		if(null!=getContractSession().getContractKeyObject().getState())
			this.state=getContractSession().getContractKeyObject().getState();
		this.version=getContractSession().getContractKeyObject().getVersion();
		if(null!=getContractSession().getContractKeyObject().getStatus())
			this.status=getContractSession().getContractKeyObject().getStatus();
	}
	
	/**
	 * Returns the Contract Note Attachment request
	 * @param 
	 * @param 
	 * @return ContractNoteAttachmentRequest
	 */
	private ContractNoteAttachmentRequest getContractAttachNotesRequest(){
	    
		// List that has the values of all the notes selected ( with all tildas removed )
		List notesIdList = new ArrayList();		
		List versionList = new ArrayList();
		// Stores the value of the string 
		String selectedNotes = this.getNoteName();		
		// Create a request 
		ContractNoteAttachmentRequest contractNoteAttachmentRequest = (ContractNoteAttachmentRequest)this.getServiceRequest(ServiceManager.ATTACH_CONTRACT_NOTES_REQUEST);
			
		if(null != selectedNotes){
		    // Removes the tildas and stores the selected noteIds in a list
			notesIdList = WPDStringUtil.getListFromTildaString(selectedNotes,3,2,2);
			versionList= WPDStringUtil.getListFromTildaString(selectedNotes,3,3,1);
		}
		// Set the selected noteIds to Request
		contractNoteAttachmentRequest.setNotesIdList(notesIdList);		
		contractNoteAttachmentRequest.setVersionList(versionList);
		contractNoteAttachmentRequest.setAction(1);		
		contractNoteAttachmentRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
		contractNoteAttachmentRequest.setEntityType("ATTACHCONTRACT");
		return contractNoteAttachmentRequest;
		}
	
	/**
	 * Loads the Contract Notes page each time user clicks on the
	 * Notes tab. 
	 * @param 
	 * @param 
	 * @return String loadnotes
	 */
	public String load(){
		validationMessages = new ArrayList();
		if(getContractSession().getContractKeyObject().getContractType().equalsIgnoreCase(WebConstants.MANDATE) && !super.isViewMode()){
			validationMessages.add(new InformationalMessage(
	                   WebConstants.CANNOT_ATTACH_NOTE_TO_MANDATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}			
        RetrieveContractNoteRequest retrieveContractNoteRequest=(RetrieveContractNoteRequest) this
        .getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_NOTES);
        retrieveContractNoteRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
        retrieveContractNoteRequest.setEntityType("ATTACHCONTRACT");
        RetrieveContractNoteResponse retrieveContractNoteResponse = (RetrieveContractNoteResponse) executeService(retrieveContractNoteRequest);
        this.attachNotesList = retrieveContractNoteResponse.getNoteList();
        if(null != this.attachNotesList && this.attachNotesList.size()>0){
	        getSession().setAttribute("notesList",this.attachNotesList);
	        this.verifyString = "XXX";
        }
        if (super.isViewMode()){			
			return "displayContractNotesViewTab";
		}
		else{ //validate if note exist
			if(validateNotesAttached()){
				addAllMessagesToRequest(this.validationMessages);
				this.notesMessageList = this.validationMessages;
			}
		 	return "loadNotes";
		}
	}
	
	public void setInitView(String temp) {
		
	}
	
	public String getInitViewForPrint(){
		RetrieveContractNoteRequest retrieveContractNoteRequest=(RetrieveContractNoteRequest) this
        .getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_NOTES);
        retrieveContractNoteRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
        retrieveContractNoteRequest.setEntityType("ATTACHCONTRACT");
        RetrieveContractNoteResponse retrieveContractNoteResponse = (RetrieveContractNoteResponse) executeService(retrieveContractNoteRequest);
        this.attachNotesList = retrieveContractNoteResponse.getNoteList();
        return "";
	}
	
	/**
	 * For checking in a Note
	 * @param 
	 * @param 
	 * @return String 
	 */
	public String done(){
		
		validationStatus = validateRequiredFields();
		this.validationMessages = new ArrayList();
		this.noteNameValdn=true;
		List msgListOne = new ArrayList();
		List msgListTwo = new ArrayList();
		boolean bool= false;
		if(!validationStatus){				
			requiredNoteName = false;	
			bool = true;
			// proceed with attach if fields are non empty
		}else if(this.verifyString.equals("XXX")){
			//ContractSession contractSessionObject = (ContractSession)getSession().getAttribute(WebConstants.CONTRACT_SESSION_KEY);
			List notesList =  (List)getSession().getAttribute("notesList");
			if(null != notesList && notesList.size()>0){
				this.attachNotesList = (List)getSession().getAttribute("notesList");	
			}
        	validateNotesAttached();
        	if(bool == false ){
        		this.notesMessageList =this.validationMessages;
        	}
			
		}
		else{			
			// call the getContractAttachNotesRequest method to create request
			ContractNoteAttachmentRequest contractNoteAttachmentRequest = getContractAttachNotesRequest();		
			// Create a response object
			ContractNoteAttachmentResponse contractAttachNotesResponse =(ContractNoteAttachmentResponse) executeService(contractNoteAttachmentRequest) ;
			this.attachNotesList = contractAttachNotesResponse.getNoteList();
			getContractSessionObject().setAttachNotesList(this.attachNotesList);
			 getSession().setAttribute("notesList",this.attachNotesList);
			// validate if note already exists.
			validateNotesAttached();	
			this.notesMessageList = this.validationMessages;
		}
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
			.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		ContractVO contractVO = new ContractVO();
		contractVO.setContractSysId(this.getContractSession().getContractKeyObject().getContractSysId());
		contractVO.setContractId(this.getContractSession().getContractKeyObject().getContractId());
		contractVO.setVersion(this.getContractSession().getContractKeyObject().getVersion());
		contractVO.setStatus(this.getContractSession().getContractKeyObject().getStatus());
		saveContractBasicInfoRequest.setContractVO(contractVO);
		saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
		saveContractBasicInfoRequest.setChechIn(this.checkin);
		// SSCR 16332 -Start
		if (this.checkin && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
			saveContractBasicInfoRequest.setEBXWS(true);
			saveContractBasicInfoRequest.setCarvConfirm(true);
		}
		if (null != getSession().getAttribute("contractWSErrorList")){
			getSession().removeAttribute("contractWSErrorList");
		}
		if (null != getSession().getAttribute("wSErrorDisplayList")){
			getSession().removeAttribute("wSErrorDisplayList"); 
		}
		if(null != getSession().getAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST)){
			getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
		}
		saveContractBasicInfoRequest.setEbxAndCarvErrorsByPassCmts(this.getInvokeWebService());
		// SSCR 16332 -End
		
		if (null != getSession().getAttribute("AM_BENEFIT"))
            getSession().removeAttribute("AM_BENEFIT");   // clearing for adminmethod contract validation popup
    	if (null != getSession().getAttribute("AM_BC_KEY"))
            getSession().removeAttribute("AM_BC_KEY");   // clearing for adminmethod contract validation popup
    	if (null != getSession().getAttribute("DIRECT_CLICK"))
            getSession().removeAttribute("DIRECT_CLICK");   // clearing for adminmethod contract validation popup
    	
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
			
		if (null != saveContractBasicInfoResponse) {
			if (null != saveContractBasicInfoResponse.getMessages()) {
				// Rule Validation
				getSession().setAttribute( 
						WebConstants.SESSION_DELETED_RULES_LIST, 
						saveContractBasicInfoResponse.getDeletedRulesList());
				getSession().setAttribute(
						WebConstants.SESSION_UNCODED_RULES_LIST, 
						saveContractBasicInfoResponse.getUnCodedRulesList());
				msgListTwo = saveContractBasicInfoResponse.getMessages();
				msgListOne.addAll(msgListTwo);
				this.validationMessages.addAll(msgListOne) ;
				//addAllMessagesToRequest(msgListOne);
			if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAdminMethodValidation();
				getRequest().setAttribute("RETAIN_Value", "");
				return "";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.SPS_VALIDATION_ERROR){
		 		getSession().setAttribute("AM_CONTRACT_ID",getContractSession().getContractKeyObject().getContractSysId() + "");
				if (null != getSession().getAttribute("adminmethodContractCodedSPSTreeBackingBean"))
					getSession().removeAttribute("adminmethodContractCodedSPSTreeBackingBean");
				setValuesForAdminMethodValidation();
		 		return "";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"ContractNotesBackingBean");
				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
						this);
				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getDateSegmentId()));
				getSession().setAttribute(WebConstants.CONTRACT_ID_FOR_CHECKIN,
						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getContractSysId()));
				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
						WebConstants.ENTITY_TYPE_CONTRACT);
				getSession().setAttribute("AM_ENTITY_NAME",getContractSession().getContractKeyObject().getContractId());
				return "validationWait";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS || saveContractBasicInfoResponse.getCarvedOutCondition() == SaveProductResponse.DO_CARVEDOUT_PROCESS ){
				//SSCR 17571 - Tab Impl
				if(null !=saveContractBasicInfoResponse.getCarvedoutMap() && !saveContractBasicInfoResponse.getCarvedoutMap().isEmpty()){
					getSession().setAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST,saveContractBasicInfoResponse.getCarvedoutMap());
					this.setVendorFlag("true");	
				}//SSCR 17571 - Tab Impl
				if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS ){
					  List<ContractWebServiceVO> contractWSErrorList = null;
					  List<EbxWebSerDisplayVO> wSErrorDisplayList =null;
					
					if(saveContractBasicInfoResponse.getContractWSErrorList() != null && !saveContractBasicInfoResponse.getContractWSErrorList().isEmpty()){
						contractWSErrorList =saveContractBasicInfoResponse.getContractWSErrorList();	
					}
					if(saveContractBasicInfoResponse.getWSErrorDisplayList() != null  && !saveContractBasicInfoResponse.getWSErrorDisplayList().isEmpty()){
						wSErrorDisplayList = saveContractBasicInfoResponse.getWSErrorDisplayList();
					}
					if(contractWSErrorList != null && wSErrorDisplayList !=null &&!contractWSErrorList.isEmpty() && !wSErrorDisplayList.isEmpty()){
						this.setWebServiceFlag("true");
						getSession().setAttribute("contractWSErrorList",contractWSErrorList);
						getSession().setAttribute("wSErrorDisplayList",wSErrorDisplayList);
						ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
						this.ebxWebSerPopupDisplayVO = DomainUtil.ebxPopupDisplayValues(contractKeyObject);
						getSession().setAttribute("ebxWebSerPopupDisplayVO",ebxWebSerPopupDisplayVO);
						this.setEbxWebSerDisplayList(wSErrorDisplayList);
						
					}
					
				}else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS_FAILURE){
					this.setWebServiceFlag("true");
					this.ebxProcessInterruptMsg = saveContractBasicInfoResponse.getWsProcessError();
					getSession().setAttribute("ebxProcessInterruptMsg",ebxProcessInterruptMsg);
					ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
					this.ebxWebSerPopupDisplayVO = DomainUtil.ebxPopupDisplayValues(contractKeyObject);
					getSession().setAttribute("ebxWebSerPopupDisplayVO",ebxWebSerPopupDisplayVO);
					
				}
				this.getSession().setAttribute("VendorFlag",vendorFlag);
				this.getSession().setAttribute("WebServiceFlag",webServiceFlag);
				return "loadNotes";
			}else{
				if (saveContractBasicInfoResponse.isSuccess()) {
					this.noteName = "";
					super.clearSession();
					return "CONTRACTCREATE";
				}else if(saveContractBasicInfoResponse.isIfUncodedLineNotesExist()){
					this.setIfUncodedLineNotesExist("Yes");
				}
			 }
		  }
		}
		this.requiredNoteName = false;
		this.checkin = false;
		getRequest().setAttribute("RETAIN_Value", "");
		return WebConstants.EMPTY_STRING;
		//return "loadNotes";
	}
	public String doContractCancelAction(){ 
		this.setWebServiceFlag("false");
		this.setVendorFlag("false");
    	getSession().removeAttribute("contractWSErrorList");
    	getSession().removeAttribute("wSErrorDisplayList");
    	getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
    	getSession().removeAttribute("VendorFlag");
    	getSession().removeAttribute("WebServiceFlag");
    	getSession().removeAttribute("ebxProcessInterruptMsg");
    	return "";
    }
	
	
	/**
	 * For deleting a Note
	 * @param 
	 * @param 
	 * @return String 
	 */
	public String deleteNotes(){
		//create requets object
		ContractNotesDeleteRequest contractNotesDeleteRequest = (ContractNotesDeleteRequest)
		this.getServiceRequest(ServiceManager.DELETE_CONTRACT_NOTES_REQUEST);
		//set values to request object from session
		contractNotesDeleteRequest.setNoteId(this.selectedNotesId);
		contractNotesDeleteRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
		contractNotesDeleteRequest.setEntityType("ATTACHCONTRACT");
		//execute service and acquire response
		ContractNotesDeleteResponse contractDeleteNotesResponse =
			(ContractNotesDeleteResponse)executeService(contractNotesDeleteRequest);	
		this.attachNotesList =null;
		this.verifyString = null;
		getRequest().setAttribute("RETAIN_Value", "");
	    return "";
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
     * @return securityAccessForDelete
     * 
     * Returns the securityAccessForDelete.
     */
    public boolean isSecurityAccessForDelete() {
        try{

            securityAccessForDelete = getUser().isAuthorized(WebConstants.NOTES_MODULE,
            StateFactory.DELETE_TASK);

        } catch (SevereException e) {
        	Logger.logError(e);
        }
        return securityAccessForDelete;
    }
    /**
     * @param securityAccessForDelete
     * 
     * Sets the securityAccessForDelete.
     */
    public void setSecurityAccessForDelete(boolean securityAccessForDelete) {
        this.securityAccessForDelete = securityAccessForDelete;
    }
    /**
     * Returns the attachNotesList
     * @return List attachNotesList.
     */
    public List getAttachNotesList() {
        return attachNotesList;
    }
    /**
     * Sets the attachNotesList
     * @param attachNotesList.
     */

    public void setAttachNotesList(List attachNotesList) {
        this.attachNotesList = attachNotesList;
    }
    /**
     * Returns the noteNameValdn
     * @return boolean noteNameValdn.
     */

    public boolean isNoteNameValdn() {
        return noteNameValdn;
    }
    /**
     * Sets the noteNameValdn
     * @param noteNameValdn.
     */

    public void setNoteNameValdn(boolean noteNameValdn) {
        this.noteNameValdn = noteNameValdn;
    }
    /**
     * Returns the selectedNotesId
     * @return String selectedNotesId.
     */

    public String getSelectedNotesId() {
        return selectedNotesId;
    }
    /**
     * Sets the selectedNotesId
     * @param selectedNotesId.
     */

    public void setSelectedNotesId(String selectedNotesId) {
        this.selectedNotesId = selectedNotesId;
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
	 * @return Returns the noteListForPopup.
	 */
	public List getNoteListForPopup() {
		
		return noteListForPopup;
	}
	/**
	 * @param noteListForPopup The noteListForPopup to set.
	 */
	public void setNoteListForPopup(List noteListForPopup) {
		this.noteListForPopup = noteListForPopup;
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
	 * @return Returns the validationStatus.
	 */
	public boolean isValidationStatus() {
		return validationStatus;
	}
	/**
	 * @param validationStatus The validationStatus to set.
	 */
	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
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
	 * Returns the verifyString
	 * @return String verifyString.
	 */
	public String getVerifyString() {
		return verifyString;
	}
	/**
	 * Sets the verifyString
	 * @param verifyString.
	 */
	public void setVerifyString(String verifyString) {
		this.verifyString = verifyString;
	}
	/**
	 * Returns the checkin
	 * @return boolean checkin.
	 */
	public boolean isCheckin() {
		return checkin;
	}
	/**
	 * Sets the checkin
	 * @param checkin.
	 */
	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}
	/**
	 * Returns the printValue
	 * @return String printValue.
	 */
	public String getPrintValue() {
		  String requestForPrint = getRequest().getParameter("printContractNotes");
	        if(null != requestForPrint && !requestForPrint.equals("")){
	            printValue = requestForPrint;
	        }else{
	            printValue = "";
	        }
	        return printValue;
	}
	/**
	 * Sets the printValue
	 * @param printValue.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}
	/**
	 * @return Returns the loadNote.
	 */
	public String getLoadNote() {
		
		if(getContractSession().getContractKeyObject().getContractType().equalsIgnoreCase(WebConstants.MANDATE) && !super.isViewMode()){
			validationMessages.add(new InformationalMessage(
	                   WebConstants.CANNOT_ATTACH_NOTE_TO_MANDATE));
			addAllMessagesToRequest(validationMessages);
			return WebConstants.EMPTY_STRING;
		}			
        RetrieveContractNoteRequest retrieveContractNoteRequest=(RetrieveContractNoteRequest) this
        .getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_NOTES);
        retrieveContractNoteRequest.setDateSegmentId(getContractSession().getContractKeyObject().getDateSegmentId());
        retrieveContractNoteRequest.setEntityType("ATTACHCONTRACT");
        if(attachNotesList == null || (attachNotesList.isEmpty())){
	        RetrieveContractNoteResponse retrieveContractNoteResponse = (RetrieveContractNoteResponse) executeService(retrieveContractNoteRequest);
	        this.attachNotesList = retrieveContractNoteResponse.getNoteList();
        }
        if(null != this.attachNotesList && this.attachNotesList.size()>0){
	        getSession().setAttribute("notesList",this.attachNotesList);
	        this.verifyString = "XXX";
        }
        if (super.isViewMode()){			
			return "displayContractNotesViewTab";
		}
		else{ //validate if note exist
			if(validateNotesAttached())
				addAllMessagesToRequest(this.validationMessages);			 
		 	return "loadNotes";
		}
	
	}
	/**
	 * @param loadNote The loadNote to set.
	 */
	public void setLoadNote(String loadNote) {
		this.loadNote = loadNote;
	}
	/**
	 * @return Returns the notesMessageList.
	 */
	public List getNotesMessageList() {
		return notesMessageList;
	}
	/**
	 * @param notesMessageList The notesMessageList to set.
	 */
	public void setNotesMessageList(List notesMessageList) {
		this.notesMessageList = notesMessageList;
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
	 * @return Returns the hasValidationErrors.
	 */
	public boolean isHasValidationErrors() {
		return hasValidationErrors;
	}
	/**
	 * @param hasValidationErrors The hasValidationErrors to set.
	 */
	public void setHasValidationErrors(boolean hasValidationErrors) {
		this.hasValidationErrors = hasValidationErrors;
	}
}
