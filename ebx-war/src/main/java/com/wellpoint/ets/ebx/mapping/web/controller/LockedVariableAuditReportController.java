package com.wellpoint.ets.ebx.mapping.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.owasp.esapi.ESAPI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LockedVariableAuditReportFacade;
import com.wellpoint.ets.ebx.mapping.web.view.ExcelLockedVariableAuditEbxView;
import com.wellpoint.ets.ebx.mapping.web.view.ExcelLockedVariableAuditWPDView;
import com.wellpoint.ets.ebx.ooamessage.application.OOAMessageFacade;
import com.wellpoint.ets.ebx.ooamessage.util.MaintainOOAMessageRequest;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

public class LockedVariableAuditReportController extends MultiActionController {

	private static Logger logger = Logger
			.getLogger(LockedVariableAuditReportController.class);

	private LockedVariableAuditReportFacade lockedVariableAuditReportFacade;

	private boolean eBxReportFlag = false;
	
	

	// OOAMessage controller related code..

	private OOAMessageFacade ooaMessageFacade;

	public OOAMessageFacade getOoaMessageFacade() {
		return ooaMessageFacade;
	}

	public void setOoaMessageFacade(OOAMessageFacade ooaMessageFacade) {
		this.ooaMessageFacade = ooaMessageFacade;
	}

	/**
	 * Returns the ooa message search criteria screen
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView ooaMessageView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("ooaMessageView");

	}

	/**
	 * Returns the ooa message search results screen
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView searchOOAMessage(HttpServletRequest request, // 30296
			HttpServletResponse response) throws Exception {
		String viewOrSearchFunction = null;
		String resultScreen = null;
		String search = null;
		String searchCriteria = null;
		List oOAMessageSearchDetailList = null;
		String exchangeInd = null;
		String messageId = null;
		
		HttpSession session=request.getSession();
		
		viewOrSearchFunction = request.getParameter("viewOrSearchFunction");
		// search variable describes the radio button in the screen, whether
		// network id or contract code
		search = request.getParameter("search");
		String isSearch=request.getParameter("isSearch");
		// value of Contract code or network id
		searchCriteria = ESAPI.encoder().encodeForHTML(request.getParameter("searchCriteria"));	
		if(BxUtil.regExPatterValidation(searchCriteria)){
			searchCriteria = searchCriteria;
		}else{
			searchCriteria=null;
		}
		if("searchFunction".equals(viewOrSearchFunction)){
		if(isSearch!=null&& ("Yes").equalsIgnoreCase(isSearch)){
			session.setAttribute("searchCriteria", searchCriteria);
		}else{
			searchCriteria=(String) session.getAttribute("searchCriteria");
		}
		}
		exchangeInd = request.getParameter("excInd");
		messageId = request.getParameter("messgId");
		if (searchCriteria != null) {
			searchCriteria = searchCriteria.toUpperCase().trim();
		}
		oOAMessageSearchDetailList = ooaMessageFacade.searchOOAMessageDetails(
				search, searchCriteria, viewOrSearchFunction, exchangeInd,
				messageId);
		Map map = new HashMap();
		map.put("oOAMessageSearchDetailList", oOAMessageSearchDetailList);
		
		if ("searchFunction".equals(viewOrSearchFunction)) {
			resultScreen = "ooaMessagesSearchScreen";

		} else if ("viewFunction".equals(viewOrSearchFunction)) {
			resultScreen = "ooaMessagesViewScreen";
			MaintainOOAMessageRequest ooaMessageRequest = (MaintainOOAMessageRequest) oOAMessageSearchDetailList.get(0);
			
			String comment=ooaMessageRequest.getComments();
			StringBuffer stringBuffer=new StringBuffer();
			if(null!=comment&&comment.contains("|")){
			StringTokenizer stk= new StringTokenizer(comment,"|");
			while(stk.hasMoreElements()){
				stringBuffer.append(stk.nextElement());
				stringBuffer.append(System.getProperty("line.separator"));
			}
			ooaMessageRequest.setComments(stringBuffer.toString());
			}
	     
		}

		ModelAndView modelAndView = new ModelAndView(resultScreen, map);

		return modelAndView;
	}

	/**
	 * Returns the ooaMessage create screen
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView oOAMessageCreateScreen(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("createOOAMessagesScreen");
		modelAndView.addObject("currentPageMode", OOAMessageConstants.CREATE);
		modelAndView.addObject("networkOrContractVar",
				request.getParameter("networkOrContractVar"));
		modelAndView.addObject("netWorkOrContractCode",
				request.getParameter("netWorkOrContractCode"));
		modelAndView.addObject("networkOrContractDetails", "");

		if (null != request.getParameter("networkOrContractVar")
				&& request.getParameter("networkOrContractVar").contains(OOAMessageConstants.network)) {
			modelAndView.addObject("exchangeOrExemptVariable",
					"Exchange Indicator");
			modelAndView.addObject("exchangeOrExemptValue",
					request.getParameter("exchangeIndiCator"));
			modelAndView.addObject("exchangeVal",
					request.getParameter("exchangeIndiCator"));
		} else {
			modelAndView
					.addObject("exchangeOrExemptVariable", "Message Exempt");
			String exemptCheckBox = "<input type='checkbox' id = 'exemptCheckBox' name = 'exemptFlag'  onclick='disableTextArea();' />";
			modelAndView.addObject("exchangeOrExemptValue", exemptCheckBox);
			modelAndView.addObject("exchangeVal", "");
		}
		modelAndView.addObject("messageId", "");
		return modelAndView;

	}

	/**
	 * This method is used to add date segment to the ooa messages
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView oOAMessageAddDateSegment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Entering ooaMessageAddDateSegment Method");
		ModelAndView modelAndView = null;

		try {
			String networkOrContractCode = request
					.getParameter("netWorkOrContractCode");
			String messageId = request.getParameter("messageId");
			String exchangeIndicator = request
					.getParameter("exchangeIndicator");
			String networkOrContract = request.getParameter("search");

			logger.info(ESAPI.encoder().encodeForHTML(" NetworkOrContractCode value:" + networkOrContractCode
					+ "and MessageId is:" + messageId
					+ "and NetworkOrContract is:" + networkOrContract));

			modelAndView = new ModelAndView("addDateSegmentForOOAMessage");
			modelAndView.addObject("netWorkOrContractCode",
					networkOrContractCode);
			modelAndView.addObject("MessageId", messageId);
			modelAndView.addObject("addSegmentEffectiveDate",
					request.getParameter("oOAMessageEffectiveDatePickerName"));
			modelAndView.addObject("addSegmentExpiryDate",
					request.getParameter("oOAMessageExpiryDatePickerName"));
			if (networkOrContract != null
					&& OOAMessageConstants.network.equalsIgnoreCase(networkOrContract)) {
				modelAndView.addObject("ExcahngeOrExpl", "ExchangeIndicator");
				modelAndView.addObject("ExcahngeOrExplVal", exchangeIndicator);
				modelAndView.addObject("ExcahngeVal", exchangeIndicator);
			} else {
				modelAndView.addObject("ExcahngeOrExpl", "Message Exempt");
				String exemptCheckBox = "<input type='checkbox' id = 'exemptCheckBox' name = 'exemptFlag'  onclick='disableTextArea();' />";
				modelAndView.addObject("ExcahngeOrExplVal", exemptCheckBox);
				modelAndView.addObject("ExcahngeVal", "");
			}

			modelAndView.addObject("netWorkOrContract", networkOrContract
					+ " ID");
			modelAndView.addObject("networkOrContract", networkOrContract);
			logger.info("Existing ooaMessageAddDateSegment");
			return modelAndView;
		} catch (Exception ex) {
			logger.error("Error occurred in oOOMessageAddDateSegment Method");
			logger.error(ex.getMessage());
			modelAndView = new ModelAndView("error");

		}
		return modelAndView;

	}

	/**
	 * This method saves the ooa message details to the database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView oOAMessageSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView =null;
		MaintainOOAMessageRequest maintainOOAMessageRequest = null;

		String message = request.getParameter("oOAMessageTextAreaName");
		/*
		 * 12/2016 - Fix for handling new line characters in message. Replacing
		 * new line with one space
		 */
		if (message.contains("\n")) {
			message = message.replaceAll("\n", " ").replaceAll("^ +| +$|( )+","$1");
		}
		boolean transactionStatus = true;
		boolean isValidTextLength = true;
		boolean duplicateDatesFound = false;
		String duplicateDatesMsg=""; 

		if (null != request.getParameter("currentPageMode")) {
			if (request.getParameter("currentPageMode").trim().equals(OOAMessageConstants.EDIT)) {
				String messageId = request.getParameter("oldMessageId");
				
				
				maintainOOAMessageRequest = this.fillDataFromRequest(request, OOAMessageConstants.EDIT);
				maintainOOAMessageRequest.setMessageTextOne(message);
				maintainOOAMessageRequest.setMessageId(messageId);
				List oOAMessageSearchDetailList = ooaMessageFacade.searchOOAMessageDetails(
						request.getParameter("netWorkOrContractName"),//replace actual id
						request.getParameter("netWorkOrContractCodeName"), "viewFunction",
						null, messageId);
				if(oOAMessageSearchDetailList.size()>0){
				MaintainOOAMessageRequest ooaMessageRequest = (MaintainOOAMessageRequest) oOAMessageSearchDetailList.get(0);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				StringBuffer auditcomments=new StringBuffer("[");
				
				String userComment=request.getParameter("auditComments");
				if(null==userComment|| userComment.isEmpty()){
					userComment="Message Edited";
					auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
							simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
				}else{
					auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
							simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
					
				}
				
				if(null == ooaMessageRequest.getComments()){
					maintainOOAMessageRequest.setComments(auditcomments.toString());
				}else{
					maintainOOAMessageRequest.setComments(auditcomments.toString()+"|"+ooaMessageRequest.getComments());
				} 
				if(OOAMessageConstants.BUILDING.equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BUILDING);
				}else if((OOAMessageConstants.OBJECT_TRANSFERRED).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus()) && ("Y").equalsIgnoreCase(ooaMessageRequest.getTestInd())){					
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BEING_MODIFIED);
				}
				else if((OOAMessageConstants.SCHEDULED_TO_TEST).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus()) && ("Y").equalsIgnoreCase(ooaMessageRequest.getTestInd())){					
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BEING_MODIFIED);
				}
				else if((OOAMessageConstants.BEING_MODIFIED).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus()) && ("Y").equalsIgnoreCase(ooaMessageRequest.getTestInd())){					
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BEING_MODIFIED);
				}
				else if((OOAMessageConstants.SCHEDULED_TO_PRODUCTION_NEW).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){					
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BEING_MODIFIED);
				}
				else if((OOAMessageConstants.PUBLISHED).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){					
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BEING_MODIFIED);
				}
				else{
					maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BUILDING);
				}
				this.smartSplitText(maintainOOAMessageRequest, 260);

				if (null != maintainOOAMessageRequest.getMessageTextThree()
						&& maintainOOAMessageRequest.getMessageTextThree()
								.length() > 260) {
					isValidTextLength = false;
					modelAndView = new ModelAndView("jsonView");
					modelAndView.addObject("invalidLength", isValidTextLength);
				} else {
					if(OOAMessageConstants.network.equals(maintainOOAMessageRequest.getSearch())){
					if(OOAMessageConstants.BUILDING.equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){
					maintainOOAMessageRequest
							.setHasExchOrExplChanged(new Boolean(request
									.getParameter("hasExchOrExplChangedVar")));
					}else{
						maintainOOAMessageRequest
						.setHasExchOrExplChanged(false);
					}
					}
					duplicateDatesMsg= ooaMessageFacade.duplicateDatesCheck(maintainOOAMessageRequest);
					if(duplicateDatesMsg=="") {
					transactionStatus = ooaMessageFacade.modifyOOAMessage(maintainOOAMessageRequest);
					} else {
						duplicateDatesFound=true;
						modelAndView = new ModelAndView("jsonView");
			        	modelAndView.addObject("duplicateDatesFound",duplicateDatesFound);
			        	modelAndView.addObject("duplicateDatesMsg",duplicateDatesMsg);
						
					}  
					
				}
				}else{
					duplicateDatesFound=true;
					modelAndView = new ModelAndView("jsonView");
		        	modelAndView.addObject("duplicateDatesFound",duplicateDatesFound);
		        	modelAndView.addObject("duplicateDatesMsg","Please Refresh Browser");
				}
				modelAndView = new ModelAndView("jsonView");
				modelAndView.addObject(maintainOOAMessageRequest);
			} else {
				maintainOOAMessageRequest = this.fillDataFromRequest(request, OOAMessageConstants.CREATE);
				maintainOOAMessageRequest.setMessageTextOne(message);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				StringBuffer auditcomments=new StringBuffer("[");
				String userComment=request.getParameter("auditComments");
				if(null==userComment||userComment.isEmpty()){
					userComment="New Message created";
					auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
							simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
				}else{
					auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
							simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
					
				}
		  		maintainOOAMessageRequest.setComments(auditcomments.toString());
		  		maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BUILDING);

				this.smartSplitText(maintainOOAMessageRequest, 260);

				if (null != maintainOOAMessageRequest.getMessageTextThree()
						&& maintainOOAMessageRequest.getMessageTextThree()
								.length() > 260) {
					isValidTextLength = false;
					modelAndView = new ModelAndView("jsonView");
					modelAndView.addObject("invalidLength", isValidTextLength);
				} else {
					String code=maintainOOAMessageRequest.getNetworkOrContractCode();
					if(!code.isEmpty() ){
					transactionStatus = ooaMessageFacade
							.createOOAMessage(maintainOOAMessageRequest);
					}else{
						transactionStatus=false;
					}
				}
				modelAndView = new ModelAndView("ooaMessageView");
			}

			if (!transactionStatus) {
				modelAndView = new ModelAndView("jsonView");
				modelAndView.addObject("duplicateNotFound", transactionStatus);
			}
		}

		return modelAndView;
	}

	/**
	 * This method save the date segment to the database
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView oOAMessageADDDateSegmentSave(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.info("Entering oOAMessageAddDateSegmentSave method");
		Boolean success = false;
		String nextScreen = null;
		MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();
		try {

			String message = request.getParameter("oOAMessageTextAreaId");
			logger.info("Message is:" +ESAPI.encoder().encodeForHTML(message));
			maintainOOAMessageRequest.setMessageTextOne(message);

			maintainOOAMessageRequest = this.fillDataFromRequest(request, "");
			List oOAMessageSearchDetailList = ooaMessageFacade.searchOOAMessageDetails(
					maintainOOAMessageRequest.getSearch(),//replace actual id
					maintainOOAMessageRequest.getNetworkOrContractCode(), "viewFunction",
					null, maintainOOAMessageRequest.getMessageId());
			MaintainOOAMessageRequest ooaMessageRequest = (MaintainOOAMessageRequest) oOAMessageSearchDetailList.get(0);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			StringBuffer auditcomments=new StringBuffer("[");
			String userComment=request.getParameter("userComment");
			if(null==userComment||userComment.isEmpty()){
				userComment="DateSegment Added";
				auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
						simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
			}else{
				auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
						simpleDateFormat.format(new Date())+"] "+userComment.toUpperCase());
				
			}
			if(OOAMessageConstants.BUILDING.equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BUILDING);
			}else if((OOAMessageConstants.OBJECT_TRANSFERRED).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus()) && ("Y").equalsIgnoreCase(ooaMessageRequest.getTestInd())){					
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BEING_MODIFIED);
			}
			else if((OOAMessageConstants.SCHEDULED_TO_TEST).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus()) && ("Y").equalsIgnoreCase(ooaMessageRequest.getTestInd())){					
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BEING_MODIFIED);
			}
			else if((OOAMessageConstants.BEING_MODIFIED).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus()) && ("Y").equalsIgnoreCase(ooaMessageRequest.getTestInd())){					
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BEING_MODIFIED);
			}
			else if((OOAMessageConstants.SCHEDULED_TO_PRODUCTION_NEW).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){					
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BEING_MODIFIED);
			}else if((OOAMessageConstants.PUBLISHED).equalsIgnoreCase(ooaMessageRequest.getWorkFlowAssosciationStatus())){					
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BEING_MODIFIED);
			}
			else{
				maintainOOAMessageRequest.setWorkFlowAssosciationStatus(OOAMessageConstants.BUILDING);
			}
	  		maintainOOAMessageRequest.setComments(auditcomments.toString());
	  		maintainOOAMessageRequest.setCurrentStatus(OOAMessageConstants.BUILDING);	
	  		
			success = ooaMessageFacade
					.addDateSegmentToMessage(maintainOOAMessageRequest);

			if (success) {
				nextScreen = "ooaMessageView";
			} else {
				nextScreen = "error";
			}
		} catch (Exception ex) {
			logger.error("Error occurred in oOAMessageADDDateSegmentSave Method");
			logger.error(ex.getMessage());
			// ex.printStackTrace();
			nextScreen = "error";
		}

		logger.info("Exiting oOAMessageAddDateSegmentSave method");
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject(maintainOOAMessageRequest);
		//ModelAndView modelAndView = new ModelAndView(nextScreen);
		return modelAndView;

	}

	/**
	 * This method schedule the ooa message to the wgs system
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView scheduleToWGS(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String nextScreen = "";
		ModelAndView modelAndView = new ModelAndView("jsonView");
		MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();
		try {
			String networkOrContractCode = request
					.getParameter("networkOrContractCode");
			String messageId = request.getParameter("messageId");
			String search = request.getParameter("search");
			String exchangeIndicator = request
					.getParameter("exchangeIndicator");
			String testOrProd = request
					.getParameter("testOrProd");
			maintainOOAMessageRequest
					.setNetworkOrContractCode(networkOrContractCode);
			maintainOOAMessageRequest.setMessageId(messageId);
			maintainOOAMessageRequest.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
			
			if(OOAMessageConstants.SCHEDULED_TO_PRODUCTION.equalsIgnoreCase(testOrProd)){
				maintainOOAMessageRequest
				.setWorkFlowAssosciationStatus(OOAMessageConstants.SCHEDULED_TO_PRODUCTION_NEW);
			}else{
				maintainOOAMessageRequest
				.setWorkFlowAssosciationStatus(OOAMessageConstants.SCHEDULED_TO_TEST);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			StringBuffer auditcomments=new StringBuffer("[");
			String userComments = request.getParameter("userComments");
			if(null==userComments||userComments.isEmpty()){
				userComments="SCHEDULED TO "+testOrProd;
				auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
						simpleDateFormat.format(new Date())+"] "+userComments.toUpperCase());
			}else{
				auditcomments=auditcomments.append(request.getAttribute(SecurityConstants.USER_NAME).toString()+","+
						simpleDateFormat.format(new Date())+"] "+userComments.toUpperCase()+"|");
				
			}
			maintainOOAMessageRequest.setComments(auditcomments.toString());
			maintainOOAMessageRequest.setSearch(search);
			maintainOOAMessageRequest.setExchangeIndicator(exchangeIndicator);
			ooaMessageFacade
					.updateNetworkOrContractStatus(maintainOOAMessageRequest);
			nextScreen = "ooaMessageView";
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.error("Error occurred in scheduleToWGS Method");
			logger.error(ex.getMessage());
			nextScreen = "error";
		}

//		ModelAndView modelAndView = new ModelAndView(nextScreen);
		modelAndView.addObject(maintainOOAMessageRequest);
		return modelAndView;

	}

	/**
	 * 
	 * This method is used to modify the ooa message details
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView oOAMessageEditScreen(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("createOOAMessagesScreen");

		MaintainOOAMessageRequest maintainOOAMessageRequest = this
				.fillDataFromRequest(request, "LOADEDIT");
		modelAndView.addObject("currentPageMode", OOAMessageConstants.EDIT);
		modelAndView.addObject("networkOrContractVar",
				request.getParameter("netWorkOrContractName"));
		modelAndView.addObject("netWorkOrContractCode",
				maintainOOAMessageRequest.getNetworkOrContractCode());

		modelAndView.addObject("ExcahngeOrExpl", "Message Exempt");
		maintainOOAMessageRequest.setWorkFlowAssosciationStatus(request.getParameter("workFlowAssosciationStatus"));
		String status=request.getParameter("workFlowAssosciationStatus");
		String exchangeDropDown=null;
		String exemptCheckBox=null;
		if((OOAMessageConstants.BUILDING).equals(status)){
		 exchangeDropDown = " <select id ='exchangeIndicatorDrpDwnID' > "
				+ "<option value='ON' >ON</option> "
				+ " <option value='OFF' >OFF</option> "
				+ " <option value='BOTH' >BOTH</option> " + " </select> ";
		}else{
			exchangeDropDown = request.getParameter("ExcahngeOrExplValIdName");
		}
		if (maintainOOAMessageRequest.getSearch().equalsIgnoreCase(OOAMessageConstants.network)) {
			modelAndView.addObject("exchangeOrExemptVariable",
					"ExchangeIndicator");

			modelAndView.addObject("exchangeOrExemptValue", exchangeDropDown);
			modelAndView.addObject("exchangeVal",
					request.getParameter("exchangeExchangeIndiCatorName"));
		} else {
			modelAndView
					.addObject("exchangeOrExemptVariable", "Message Exempt");
			if((OOAMessageConstants.BUILDING).equals(status)){
		    exemptCheckBox = "<input type='checkbox' id = 'exemptCheckBox' name = 'exemptFlag'  onclick='disableTextArea();' />";
			}else{
				exemptCheckBox=request.getParameter("ExcahngeOrExplValIdName");
			}
			modelAndView.addObject("exchangeOrExemptValue", exemptCheckBox);
			modelAndView.addObject("exchangeVal",
					maintainOOAMessageRequest.getExchangeIndicator());
		}

		if (null == maintainOOAMessageRequest.getExchangeIndicator()
				|| maintainOOAMessageRequest.getExchangeIndicator().trim()
						.isEmpty()) {
			maintainOOAMessageRequest.setExchangeIndicator("ON");
		}
		
		modelAndView.addObject("maintainMessageObject",
				maintainOOAMessageRequest);
		modelAndView.addObject("messageId",
				maintainOOAMessageRequest.getMessageId());

		return modelAndView;

	}

	/**
	 * 
	 * This method is used to extract the data from httpServlet request object
	 * 
	 * @param request
	 * @param pageMode
	 *            -
	 * @return MaintainOOAMessageRequest
	 */
	private MaintainOOAMessageRequest fillDataFromRequest(
			HttpServletRequest request, String pageMode) {
		MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();
		try {

			
			maintainOOAMessageRequest.setSearch(request
					.getParameter("netWorkOrContractName"));
			maintainOOAMessageRequest
					.setMessageExempted(request.getParameter(
							"netWorkOrContractName").equalsIgnoreCase(
							"Contract") ? request
							.getParameter("ExcahngeOrExplValIdName") : "N");
			maintainOOAMessageRequest 
					.setExchangeIndicator(deriveExchangeIndicatorData(
							request.getParameter("ExcahngeOrExplValIdName"),
							request.getParameter("netWorkOrContractName")));
			maintainOOAMessageRequest 
					.setMessageEffectiveDate(this.returnDateFromString(request
							.getParameter("oOAMessageEffectiveDatePickerName")));
			maintainOOAMessageRequest.setMessageExpiryDate(this
					.returnDateFromString(request
							.getParameter("oOAMessageExpiryDatePickerName")));
			maintainOOAMessageRequest.setMessageTextOne(request
					.getParameter("oOAMessageTextAreaName"));
			maintainOOAMessageRequest.setNetworkOrContractCode(request
					.getParameter("netWorkOrContractCodeName").toUpperCase());
			maintainOOAMessageRequest.setMessageId((request
					.getParameter("oldMessageId")));
			maintainOOAMessageRequest.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
			if (null == maintainOOAMessageRequest.getExchangeIndicator()
					|| maintainOOAMessageRequest.getExchangeIndicator().trim()
							.isEmpty()) {
				maintainOOAMessageRequest.setExchangeIndicator("ON");
			}
			// this.printData(maintainOOAMessageRequest);
		} catch (Exception ex) {
			logger.error("Error occurred in fillDataFromRequest Method");
			logger.error(ex.getMessage());
			// ex.printStackTrace();
		}
		return maintainOOAMessageRequest;
	}

	
	/**
	 * 
	 * This method is used to derive the exchange indicator for the network
	 * 
	 * @param parameter
	 *            - String representing the exchange indicator
	 * @param isNetwork
	 * @return String representing exchange indicator code
	 */
	private String deriveExchangeIndicatorData(String parameter,
			String isNetwork) {
		if (isNetwork.equalsIgnoreCase(OOAMessageConstants.network)) {
			if (null != parameter) {
				if (parameter.contains("Off")) {
					return "OF";
				} else if (parameter.contains("On")) {
					return "ON";
				} else if (parameter.contains("Both")) {
					return "BT";
				}
			}
		}
		return parameter;
	}

	/**
	 * 
	 * This method is used to convert the string to date
	 * 
	 * @param dateStr
	 * @return java.util.Date object
	 */
	private Date returnDateFromString(String dateStr) {

		Date convertedDateFormat = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			convertedDateFormat = (Date) sdf.parse(dateStr);
		} catch (ParseException parseExp) {
			logger.error("Error occurred in returnDateFromString Method");
			logger.error(parseExp.getMessage());
			// parseExp.printStackTrace();
		}
		return convertedDateFormat;
	}


	/**
	 * This method is used to Split the ooa message
	 * 
	 * @param maintainOOAMessageRequest
	 * @param splitLength
	 * @return
	 */
	private MaintainOOAMessageRequest smartSplitText(
			MaintainOOAMessageRequest maintainOOAMessageRequest, int splitLength) {

		String toBeSplitString = maintainOOAMessageRequest.getMessageTextOne();

		int startIndexVal = 0;
		int lastIndexVal = splitLength;

		if (toBeSplitString != null && toBeSplitString.length() >= lastIndexVal) {
			maintainOOAMessageRequest.setMessageTextOne(smartSplit(
					toBeSplitString, startIndexVal, lastIndexVal));

			startIndexVal = maintainOOAMessageRequest.getMessageTextOne()
					.length();

			maintainOOAMessageRequest.setMessageTextTwo(toBeSplitString
					.substring(startIndexVal, toBeSplitString.length()));

		}

		if (toBeSplitString != null
				&& toBeSplitString.length() >= (lastIndexVal = startIndexVal
						+ splitLength)) {
			maintainOOAMessageRequest.setMessageTextTwo(smartSplit(
					toBeSplitString, startIndexVal, lastIndexVal));
			startIndexVal = startIndexVal
					+ maintainOOAMessageRequest.getMessageTextTwo().length();
			maintainOOAMessageRequest.setMessageTextThree(toBeSplitString
					.substring(startIndexVal));
		}

		return maintainOOAMessageRequest;
	}

	/**
	 * This method is used to Split the ooa message
	 * 
	 * @param toBeSplitString
	 * @param startIndex
	 * @param lastIndex
	 * @return String representing the splitText
	 */
	private String smartSplit(String toBeSplitString, int startIndex,
			int lastIndex) {

		String splitText = toBeSplitString.substring(startIndex, lastIndex);

		int splitLength = 0;

		if (-1 != splitText.lastIndexOf(" ")) {
			splitLength = splitText.lastIndexOf(" ");
		}

		if (-1 != splitText.lastIndexOf(",")
				&& splitText.lastIndexOf(",") > splitLength) {
			splitLength = splitText.lastIndexOf(",");
		}
		if (-1 != splitText.lastIndexOf(";")
				&& splitText.lastIndexOf(";") > splitLength) {
			splitLength = splitText.lastIndexOf(";");
		}
		if (-1 != splitText.lastIndexOf(".")
				&& splitText.lastIndexOf(".") > splitLength) {
			splitLength = splitText.lastIndexOf(".");
		}
		if (-1 != splitText.lastIndexOf("-")
				&& splitText.lastIndexOf("-") > splitLength) {
			splitLength = splitText.lastIndexOf("-");
		}
		if (-1 != splitText.lastIndexOf("/")
				&& splitText.lastIndexOf("/") > splitLength) {
			splitLength = splitText.lastIndexOf("/");
		}
		if (-1 != splitText.lastIndexOf("\\")
				&& splitText.lastIndexOf("\\") > splitLength) {
			splitLength = splitText.lastIndexOf("\\");
		}

		if (splitLength == 0) {
			splitLength = splitText.length();
		} else if (splitLength < splitText.length()) {
			splitLength++;
		}

		splitText = splitText.substring(0, splitLength);

		return splitText;

	}

	/**
	 * This method is used to show the formated ooa message in the preview text
	 * area
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */

	public ModelAndView oOAMessagePreviewMethod(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String previewText = "";
		ModelAndView modelAndView = new ModelAndView("jsonView");
		MaintainOOAMessageRequest maintainOOAMessageRequest = new MaintainOOAMessageRequest();
		maintainOOAMessageRequest.setMessageTextOne(request
				.getParameter("completeMessage"));

		int splitValue = 10;

		if (null != request.getParameter("splitVal")) {
			try {
				splitValue = Integer.parseInt(request.getParameter("splitVal"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.smartSplitText(maintainOOAMessageRequest, splitValue);

		modelAndView.addObject(
				"previewCompleteText",
				maintainOOAMessageRequest.getMessageTextOne() + "\n\n"
						+ maintainOOAMessageRequest.getMessageTextTwo()
						+ "\n\n"
						+ maintainOOAMessageRequest.getMessageTextThree());

		modelAndView.addObject("previewTextOne",
				maintainOOAMessageRequest.getMessageTextOne());
		modelAndView.addObject("previewTextTwo",
				maintainOOAMessageRequest.getMessageTextTwo());
		modelAndView.addObject("previewTextThree",
				maintainOOAMessageRequest.getMessageTextThree());

		return modelAndView;

	}

	// /OOAMessage controller ends here

	public ModelAndView auditReportView(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("auditReportView");

	}

	public ModelAndView invalidReportInputs(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, Exception {

		long invalidReportInputsStartTime = System.currentTimeMillis();
		String systemType = request.getParameter("systemType").trim();
		String systemIndicator = request.getParameter("systemIndicator").trim();
		String startDate = request.getParameter("startDatePicker") != null ? request
				.getParameter("startDatePicker").trim() : request
				.getParameter("startDatePicker");
		String endDate = request.getParameter("endDatePicker") != null ? request
				.getParameter("endDatePicker").trim() : request
				.getParameter("endDatePicker");
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
			List auditList = null;
			if (DomainConstants.EBX.equalsIgnoreCase(systemType)) {
				auditList = lockedVariableAuditReportFacade
						.getEbxLockedVariableAudits(startDate, endDate);
			} else if (DomainConstants.LG.equalsIgnoreCase(systemType)) {
				auditList = lockedVariableAuditReportFacade
						.getLgLockedVariableAudits(systemIndicator, startDate,
								endDate);
			} else if (DomainConstants.ISG.equalsIgnoreCase(systemType)) {
				auditList = lockedVariableAuditReportFacade
						.getIsgLockedVariableAudits(systemIndicator, startDate,
								endDate);
			}
			HttpSession session = request.getSession();
			session.setAttribute("AUDIT_LIST", auditList);
			long invalidReportInputsEndTime = System.currentTimeMillis();
			logger.info("Time taken for invalidReportInputs method = "
					+ (invalidReportInputsEndTime - invalidReportInputsStartTime));
			return modelAndView;

		} catch (EBXException ebx) {
			List list = new ArrayList();
			list.add(ebx.getMessage());
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, list);
			return modelAndView;

		}

	}

	/**
	 * jsp calls this method for generating Locked variable audit report
	 * 
	 * @param
	 * @return
	 * @exception
	 * 
	 */
	public ModelAndView generateLockedVarialbAuditReport(
			HttpServletRequest request, HttpServletResponse response)
			throws EBXException, Exception {

		long generateExcelStartTime = System.currentTimeMillis();
		Variable variable = new Variable();
		String systemType = ESAPI.encoder().encodeForURL(request.getParameter("systemType").trim());
		BxUtil.validationVariable(systemType);
		String startDate = ESAPI.encoder().encodeForURL(request.getParameter("startDatePicker") != null ? request
				.getParameter("startDatePicker").trim() : request.getParameter("startDatePicker"));
		String endDate = ESAPI.encoder().encodeForURL(request.getParameter("endDatePicker") != null ? request
				.getParameter("endDatePicker").trim() : request.getParameter("endDatePicker"));
		ModelAndView modelAndView = null;
		variable.setVariableSystem(systemType.trim());
		HttpSession session = request.getSession();
		List auditList = (List) session.getAttribute("AUDIT_LIST");
		session.removeAttribute("AUDIT_LIST");
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");

		StringBuffer fileName = new StringBuffer();
		fileName = fileName.append(systemType + "-Audit Trial");
		if (null != startDate && !DomainConstants.EMPTY.equals(startDate)) {
			fileName = fileName.append(" from " + startDate);
		}
		if (null != endDate && !DomainConstants.EMPTY.equals(endDate)) {
			fileName = fileName.append(" to " + endDate);
		}
		if ((null == startDate || DomainConstants.EMPTY.equals(startDate))
				&& (null == endDate || DomainConstants.EMPTY.equals(endDate))) {
			fileName = fileName.append(" as on " + dt1.format(new Date()));
		}
		fileName = fileName.append(".xls");

		response.setHeader("content-disposition", "attachment; filename="
				+ fileName.toString());

		if (DomainConstants.EBX.equalsIgnoreCase(systemType)) {
			ExcelLockedVariableAuditEbxView auditExcelView = new ExcelLockedVariableAuditEbxView(
					auditList);
			modelAndView = new ModelAndView(auditExcelView);
		} else {
			ExcelLockedVariableAuditWPDView auditExcelView = new ExcelLockedVariableAuditWPDView(
					auditList, systemType);
			modelAndView = new ModelAndView(auditExcelView);
		}

		long generateExcelEndTime = System.currentTimeMillis();
		logger.info("Time taken for generateExcel method = "
				+ (generateExcelEndTime - generateExcelStartTime));
		return modelAndView;

	}

	public LockedVariableAuditReportFacade getLockedVariableAuditReportFacade() {
		return lockedVariableAuditReportFacade;
	}

	public void setLockedVariableAuditReportFacade(
			LockedVariableAuditReportFacade lockedVariableAuditReportFacade) {
		this.lockedVariableAuditReportFacade = lockedVariableAuditReportFacade;
	}

	public boolean isEBxReportFlag() {
		return eBxReportFlag;
	}

	public void setEBxReportFlag(boolean bxReportFlag) {
		eBxReportFlag = bxReportFlag;
	}

}
