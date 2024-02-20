/*
 * contractCommentBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;




import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.request.ContractWebServiceRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveSelectedCommentRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveSelectedCommentResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.contract.bo.Comment;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractCommentBackingBean  extends ContractBackingBean  {
	
	private boolean checkin; 
	private String state;
	private String status;
	private int  version;
	private String commentsHistory;
	private String newComments;
	private List commentHistroyList;
	private String commentId;
	private String dateSegmentId;
	private String viewSelectedComment;
	private String viewcomment;
	private String viewuserId;
	private String loadComment;
	private List validationMessages;
	private boolean requiredMinorHeading = false;
	private String printValue;

	private String maxResult;
	private String valueToMaxResult;
	private Timestamp createdTimeStampForView;
	private boolean hasValidationErrors;
	private int tempVal;
	private String loadComments;
	private List msgListOne;
	
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

	
	public ContractCommentBackingBean(){
        super();
        this.setBreadCrumbText();
    }
	public String loadComment()
	{
		 ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
	   	    if(null != contractKeyObject){
	   	    	
	   	    	this.version = contractKeyObject.getVersion();
	   	    	this.status = contractKeyObject.getStatus();
	   	    	this.state = contractKeyObject.getState();
	   	    	
	   	    }
		
	
		if (super.isViewMode())
			return "displayContractCommentViewTab";
		else
		return "loadComments" ;
	}
	public String actionViewAll()
	{
		this.setMaxResult("NoLimit");
		if (super.isViewMode())
			return "displayContractCommentViewAllTab";
		else{
			getRequest().setAttribute("RETAIN_Value", "");
			return "loadViewAllComments" ;
		}
		
	}
	/**
	 * @return Returns the commentHistroyList.
	 */
	public List getCommentHistroyList() {
		
		return commentHistroyList;
	}
	public String getSelectedComment()
	{
		 String commentId = getRequest().getParameter("commentId");
		 String dateSegmentId = getRequest().getParameter("dateSegmentId");
		 this.dateSegmentId = dateSegmentId;
		 this.commentId = commentId;
		 RetrieveSelectedCommentRequest retrieveSelectedCommentRequest = null;
		 RetrieveSelectedCommentResponse retrieveSelectedCommentResponse = null;
		 DateSegment dateSegment = new DateSegment();
		 List retrieveCommentList = new ArrayList();
		 
		 retrieveSelectedCommentRequest = (RetrieveSelectedCommentRequest)this.getServiceRequest(ServiceManager.RETRIEVE__SELECTED_CONTRACT_COMMENT);
		 retrieveSelectedCommentRequest.setCommentId(Integer.parseInt(commentId));
		 retrieveSelectedCommentRequest.setDateSegmentId(Integer.parseInt(dateSegmentId));
		 
		 retrieveSelectedCommentResponse = (RetrieveSelectedCommentResponse) this.executeService(retrieveSelectedCommentRequest);
		 List dateSegmentList = retrieveSelectedCommentResponse.getContract().getDateSegmentList();
		 if(null != dateSegmentList && dateSegmentList.size() >0){
		 		dateSegment = (DateSegment)dateSegmentList.get(0);
		 		retrieveCommentList = dateSegment.getCommentsList();
	     }
		Comment comment = (Comment)retrieveCommentList.get(0);
		this.viewcomment = comment.getCommentText();
		this.viewuserId = comment.getCreatedUser();
		this.createdTimeStampForView = comment.getCreatedTimeStamp();
		return "";
	}	
	public String saveComments()
	{
		validationMessages = new ArrayList();
		SaveDateSegmentCommentRequest saveDateSegmentCommentRequest =null;
		SaveDateSegmentCommentResponse saveDateSegmentCommentResponse =null;
		
		  if (!isCommentFieldsValid()) {
            addAllMessagesToRequest(this.validationMessages);
            //this.newComments ="";
            return "";
        }
	    	//request is created
			saveDateSegmentCommentRequest = (SaveDateSegmentCommentRequest)this.getServiceRequest(ServiceManager.SAVE_DATE_SEGMENT_CONTRACT_COMMENT);
			saveDateSegmentCommentRequest.setNewComments(this.getNewComments().trim());
			saveDateSegmentCommentResponse = (SaveDateSegmentCommentResponse) this.executeService(saveDateSegmentCommentRequest);
           this.newComments ="";
           this.commentHistroyList = null;
        getRequest().setAttribute("RETAIN_Value", "");
		return "";
	}
	private boolean isCommentFieldsValid()
    {
		 if (this.newComments == null || "".equals(this.newComments.trim())) {
		 	this.requiredMinorHeading = true;
		 	this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
		 }
		 if (this.newComments.length() > 4000 ) {
			 	this.validationMessages.add(new ErrorMessage(
	                    WebConstants.CONTRACT_COMMENT_FIELDS_SIZE));
	            return false;
			 }
		return true;
    }
	
	public String done() {
		
		validationMessages = new ArrayList();
		SaveDateSegmentCommentRequest saveDateSegmentCommentRequest = null;
		SaveDateSegmentCommentResponse saveDateSegmentCommentResponse = null;
		msgListOne = new ArrayList();
		List msgListTwo= new ArrayList();

		if (isCommentFieldsValid()) {
			//request is created
			saveDateSegmentCommentRequest = (SaveDateSegmentCommentRequest) this
					.getServiceRequest(ServiceManager.SAVE_DATE_SEGMENT_CONTRACT_COMMENT);
			
			saveDateSegmentCommentRequest.setNewComments(this.getNewComments());
			saveDateSegmentCommentResponse = (SaveDateSegmentCommentResponse) this
					.executeService(saveDateSegmentCommentRequest);

			if (saveDateSegmentCommentResponse != null) {
				msgListOne = saveDateSegmentCommentResponse.getMessages();
				this.newComments = "";
			}
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
		saveContractBasicInfoRequest
				    .setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
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
			if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAdminMethodValidation();
				getRequest().setAttribute("RETAIN_Value", "");
				return "";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.SPS_VALIDATION_ERROR){
				if (null != getSession().getAttribute("adminmethodContractCodedSPSTreeBackingBean"))
					getSession().removeAttribute("adminmethodContractCodedSPSTreeBackingBean");
				if (saveContractBasicInfoResponse != null) {
					msgListOne.addAll(saveContractBasicInfoResponse.getMessages()) ;
					addAllMessagesToRequest(msgListOne);
				}
				setValuesForAdminMethodValidation();
		 		getSession().setAttribute("AM_CONTRACT_ID",getContractSession().getContractKeyObject().getContractSysId() + "");
		 		return "";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"contractCommentBackingBean");
				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
						this);
				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getDateSegmentId()));
				getSession().setAttribute(WebConstants.CONTRACT_ID_FOR_CHECKIN,
						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getContractSysId()));
				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
						WebConstants.ENTITY_TYPE_CONTRACT);
				getSession().setAttribute("AM_ENTITY_NAME",getContractSession().getContractKeyObject().getContractId());
				if(tempVal == 1){
					getSession().setAttribute(WebConstants.ACTION_FOR_CHECKIN,new Integer(2));
				}else{
					getSession().setAttribute(WebConstants.ACTION_FOR_CHECKIN,new Integer(1));
				}
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
				return "loadComments";
			}else{
				if (null != saveContractBasicInfoResponse.getMessages()) {
					// Rule Validation
					getSession().setAttribute(
							WebConstants.SESSION_DELETED_RULES_LIST, 
							saveContractBasicInfoResponse.getDeletedRulesList());
					getSession().setAttribute(
							WebConstants.SESSION_UNCODED_RULES_LIST, 
							saveContractBasicInfoResponse.getUnCodedRulesList());
					msgListTwo = saveContractBasicInfoResponse
							.getMessages();
					msgListOne.addAll(msgListTwo);
					addAllMessagesToRequest(msgListOne);
				}
				if (saveContractBasicInfoResponse.isSuccess()) {
					this.newComments = "";
					super.clearSession();
					return "CONTRACTCREATE";
				}else if(saveContractBasicInfoResponse.isIfUncodedLineNotesExist()){
					this.setIfUncodedLineNotesExist("Yes");
				}
			}
		}
			
		this.requiredMinorHeading = false;
		this.checkin = false;
		getRequest().setAttribute("RETAIN_Value", "");
		return WebConstants.EMPTY_STRING;

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
	 * @return Returns the maxResult.
	 */
	public String getMaxResult() {
		return maxResult;
	}
	/**
	 * @param maxResult The maxResult to set.
	 */
	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}
	/**
	 * @return Returns the viewSelectedComment.
	 */
	public String getViewSelectedComment() {
		this.getSelectedComment();
		return viewSelectedComment;
	}
	/**
	 * @param viewSelectedComment The viewSelectedComment to set.
	 */
	public void setViewSelectedComment(String viewSelectedComment) {
		this.viewSelectedComment = viewSelectedComment;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public String getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(String dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the requiredMinorHeading.
	 */
	public boolean isRequiredMinorHeading() {
		return requiredMinorHeading;
	}
	/**
	 * @param requiredMinorHeading The requiredMinorHeading to set.
	 */
	public void setRequiredMinorHeading(boolean requiredMinorHeading) {
		this.requiredMinorHeading = requiredMinorHeading;
	}
	/**
	 * @return Returns the loadComment.
	 */
	public String getLoadComment() {
		return loadComment;
	}
	/**
	 * @param loadComment The loadComment to set.
	 */
	public void setLoadComment(String loadComment) {
		this.loadComment = loadComment;
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
	 * @return Returns the viewcomment.
	 */
	public String getViewcomment() {
		return viewcomment;
	}
	/**
	 * @param viewcomment The viewcomment to set.
	 */
	public void setViewcomment(String viewcomment) {
		
		this.viewcomment = viewcomment;
	}
	/**
	 * @return Returns the viewuserId.
	 */
	public String getViewuserId() {
		return viewuserId;
	}
	/**
	 * @param viewuserId The viewuserId to set.
	 */
	public void setViewuserId(String viewuserId) {
		this.viewuserId = viewuserId;
	}
	
	/**
	 * @return Returns the commentId.
	 */
	public String getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId The commentId to set.
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	/**
	 * Returns the commentsHistory
	 * @return String commentsHistory.
	 */
	public String getCommentsHistory() {
		
		
    
		return commentsHistory;
	}
	/**
	 * Sets the commentsHistory
	 * @param commentsHistory.
	 */
	public void setCommentsHistory(String commentsHistory) {
		this.commentsHistory = commentsHistory;
	}
	/**
	 * Returns the newComments
	 * @return String newComments.
	 */
	public String getNewComments() {
		return newComments;
	}
	/**
	 * Sets the newComments
	 * @param newComments.
	 */
	public void setNewComments(String newComments) {
		this.newComments = newComments;
	}
	/**
	 * Returns the state
	 * @return String state.
	 */
	public String getState() {
		return getContractSession().getContractKeyObject().getState();
	}
	/**
	 * Sets the state
	 * @param state.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * Returns the status
	 * @return String status.
	 */
	public String getStatus() {
		return getContractSession().getContractKeyObject().getStatus();
	}
	/**
	 * Sets the status
	 * @param status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Returns the version
	 * @return String version.
	 */
	public int getVersion() {
		return getContractSession().getContractKeyObject().getVersion();
	}
	/**
	 * Sets the version
	 * @param version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @param commentHistroyList The commentHistroyList to set.
	 */
	public void setCommentHistroyList(List commentHistroyList) {
		this.commentHistroyList = commentHistroyList;
	}
	
	
   
    /**
     * Returns the createdTimeStampForView
     * @return Timestamp createdTimeStampForView.
     */
    public Timestamp getCreatedTimeStampForView() {
        return createdTimeStampForView;
    }
    /**
     * Sets the createdTimeStampForView
     * @param createdTimeStampForView.
     */
    public void setCreatedTimeStampForView(Timestamp createdTimeStampForView) {
        this.createdTimeStampForView = createdTimeStampForView;
    }
	
			
	
	/**
	 * @return Returns the valueToMaxResult.
	 */
	public String getValueToMaxResult() {
		return valueToMaxResult;
	}
	/**
	 * @param valueToMaxResult The valueToMaxResult to set.
	 */
	public void setValueToMaxResult(String valueToMaxResult) {
		this.setMaxResult("NoLimit");
		this.valueToMaxResult = valueToMaxResult;
	}
	/**
	 * @return Returns the checkin.
	 */
	public boolean isCheckin() {
		return checkin;
	}
	/**
	 * @param checkin The checkin to set.
	 */
	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}


    
	/**
	 * Returns the printValue
	 * @return String printValue.
	 */
	public String getPrintValue() {
		 String requestForPrint = getRequest().getParameter("printContractComment");
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
	/**
	 * @return Returns the tempVal.
	 */
	public int getTempVal() {
		return 1;
	}
	/**
	 * @param tempVal The tempVal to set.
	 */
	public void setTempVal(int tempVal) {
		this.tempVal = tempVal;
	}
	/**
	 * @return Returns the loadComments.
	 */
	public String getLoadComments() {
		DateSegment dateSegment = new DateSegment();
		List retrieveCommentList = new ArrayList();;
		RetrieveDateSegmentCommentRequest retrieveDateSegmentCommentRequest  = 	(RetrieveDateSegmentCommentRequest)this.getServiceRequest(ServiceManager.RETRIEVE__DATE_SEGMENT_CONTRACT_COMMENT);
		retrieveDateSegmentCommentRequest.setMaxValue(this.getMaxResult());
		RetrieveDateSegmentCommentResponse retrieveDateSegmentCommentResponse = null;
		if(null == commentHistroyList || commentHistroyList.isEmpty())
		retrieveDateSegmentCommentResponse = (RetrieveDateSegmentCommentResponse)executeService(retrieveDateSegmentCommentRequest);
		if(null !=retrieveDateSegmentCommentResponse && null != retrieveDateSegmentCommentResponse.getContract().getDateSegmentList()){
			List dateSegmentList =retrieveDateSegmentCommentResponse.getContract().getDateSegmentList();
			if(null != dateSegmentList && dateSegmentList.size() >0)
	     	{
				dateSegment = (DateSegment)dateSegmentList.get(0);
				retrieveCommentList = dateSegment.getCommentsList();
				if(null != retrieveCommentList && retrieveCommentList.size()>0)
				{
					commentHistroyList  = dateSegment.getCommentsList();
				}
	     	}
		}
		if(null!=msgListOne)
			addAllMessagesToRequest(msgListOne);
		else if(null != validationMessages)
			addAllMessagesToRequest(validationMessages);
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * @param loadComments The loadComments to set.
	 */
	public void setLoadComments(String loadComments) {
		this.loadComments = loadComments;
	}

	/**
	 * @return Returns the msgListOne.
	 */
	public List getMsgListOne() {
		return msgListOne;
	}
	/**
	 * @param msgListOne The msgListOne to set.
	 */
	public void setMsgListOne(List msgListOne) {
		this.msgListOne = msgListOne;
	}
}
