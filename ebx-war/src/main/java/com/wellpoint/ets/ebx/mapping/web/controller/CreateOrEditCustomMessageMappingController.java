/*
 * <CreateOrEditCustomMessageMappingController.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.AdvanceSearchFacade;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.mapping.application.MappingFacade;
/**
 * @author UST-GLOBAL This is a controller class for create and editing
 *  a custom message mapping
 */
public class CreateOrEditCustomMessageMappingController extends MultiActionController{
	
	private LocateFacade locateCustomMessageFacade;
	private MappingFacade mappingCustomMessageFacade;
	private SessionMessageTray sessionMessageTray;
	private LocateFacade locateRuleIdFacade;
	private LocateFacade locateSpsIdFacade;
	private AdvanceSearchFacade messageTxtSearchFacade;
	
	
	/**
	 * create a new mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		String errorMessage = null;
		List infoMessages = new ArrayList();  
		List errorMessagesList = new ArrayList();
		HttpSession session = request.getSession();
		Mapping mapping = createMappingObject(request);
		String changeComments = request.getParameter(WebConstants.CHANGE_COMMENTS).toUpperCase();
		
		if(TokenGenerator.getInstance().isTokenValid(request,true)){
			List status = new ArrayList();
			//if the mapping already exist the status is set as view
			status.add(DomainConstants.VIEW_STATUS);

			List mappingAlreadyExist = locateCustomMessageFacade.getRecords(mapping, status, null, WebConstants.TOTAL_NO_OF_RECORDS,WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
			//Checks whether mapping already exist
			if(null!=mappingAlreadyExist && mappingAlreadyExist.size()>0){
				errorMessage = BxResourceBundle.getResourceBundle(ValidatorConstants.MAPPING_ALREADY_EXISTS,null);		
				errorMessagesList.add(errorMessage);		
				sessionMessageTray.setErrorMessages(errorMessagesList);
				return  WebUtil.redirectToeWPDBXLandingPage(request);	
			}
			else {
				MappingResult result =  mappingCustomMessageFacade.create(mapping, changeComments);
				if (null != result && null != result.getMapping()) {
					if(null != result.getMapping().getMsg_type_required() && result.getMapping().getMsg_type_required().equals("Y")){				
						result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_TRUE);
					}
					else {
						result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_FALSE);
					}
					//Added for SSCR 19537
					if (null != result.getMapping()
							.getIndvdlEb03AssnIndicator()
							&& result.getMapping().getIndvdlEb03AssnIndicator()
									.equalsIgnoreCase("N")) {
						result.getMapping().setMessage(
								(null != result.getMapping().getEb03()
										&& null != result.getMapping()
												.getEb03().getEb03Association()
										&& !result.getMapping().getEb03()
												.getEb03Association().isEmpty()
										&& null != result.getMapping()
												.getEb03().getEb03Association()
												.get(0) && null != result
										.getMapping().getEb03()
										.getEb03Association().get(0)
										.getMessage()) ? result.getMapping()
										.getEb03().getEb03Association().get(0)
										.getMessage() : "");
					}
				}
				TokenGenerator.getInstance().saveToken(request);
				// mapping creation successful
				if(null != result) {
					if(result.isStatus()){
						infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SAVE_SUCCESS, null));
						if((null!= result.getWarningMsgsList()) && !(result.getWarningMsgsList().isEmpty())){
							List warningMessages = new ArrayList();
							//warningMessages.add(result.getWarningMsgsList());
							warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
							return new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING)
							.addObject(WebConstants.MAPPING,result.getMapping())
							.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
							.addObject(WebConstants.INFO_MESSAGES, infoMessages)
							.addObject(WebConstants.WARNING_MESSAGES, warningMessages)
							.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
						} else {
							return new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING)
							.addObject(WebConstants.MAPPING,result.getMapping())
							.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
							.addObject(WebConstants.INFO_MESSAGES, infoMessages)
							.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
						}
					}
					else {
						List errorMessages = new ArrayList();						
						if(null!= result.getErrorMsgsList()){
							
							errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
						}
						return new ModelAndView(WebConstants.CREATE_CUSTOM_MESSAGE_MAPPING)
						.addObject(WebConstants.MAPPING,result.getMapping())
						.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
						.addObject(WebConstants.ERROR_MESSAGES, errorMessages)
						.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
					}	 
				}
				else {
					return new ModelAndView(WebConstants.CREATE_CUSTOM_MESSAGE_MAPPING);
				}
			}	

		}
		else{
			return  WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
		/**
	 * cancel edit and redirecting to landing page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		deleteLockDuringCancel(request);
		
		return WebUtil.redirectToeWPDBXLandingPage(request);
	}
	
	/**
	 * Cancel edit and redirecting to advance search page
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView cancelToAdvanceSearchPage(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		deleteLockDuringCancel(request);
		return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
	}
	
	/**
	 * deleting a mapping and redirecting to landing page
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response)throws EBXException, ParseException {
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = createMappingObject(request);
		List infoMessages = new ArrayList(); 
		String changeComments = request.getParameter(WebConstants.CHANGE_COMMENTS).toUpperCase();
		MappingResult result =  mappingCustomMessageFacade.delete(mapping, changeComments);
		
		if(result.isStatus()){
			
			String mappingSuccess = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_DELETE_SUCCESS, null);		
			infoMessages.add(mappingSuccess);		
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		else{	
			List errorMessages = new ArrayList();						
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			result.getMapping().setPageFrom(pageName);
			return new ModelAndView(WebConstants.CREATE_CUSTOM_MESSAGE_MAPPING)
			.addObject(WebConstants.MAPPING,result.getMapping())
			.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
			.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			
		}
		
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	/**
	 * Copy mapping to another rule id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView copyToMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		HttpSession session = request.getSession();
		
		List paramList = new ArrayList();
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		
		Rule newrule = new Rule();	
		newrule.setHeaderRuleId(request.getParameter("selectedruleIdCopyTo"));
		mapping.setRule(newrule);	
		String ruleDesc = null;
		
		SPSId newSpsId = new SPSId();
		newSpsId.setSpsId(request.getParameter("selectedspsIdCopyTo"));	
		mapping.setSpsId(newSpsId);
		String spsDesc = null;
		
			
		/**
		 * SPS
		 */
		
	//Copies the details of the existing mapping
		Mapping mappingToBeCopied = new Mapping();
		Rule previousRuleId = new Rule();
		previousRuleId.setHeaderRuleId(request.getParameter("selectedruleId"));
		mappingToBeCopied.setRule(previousRuleId);	
		/**
		 * SPS
		 */
		SPSId previousSpsId = new SPSId();
		previousSpsId.setSpsId(request.getParameter("selectedspsId"));
		mappingToBeCopied.setSpsId(previousSpsId);	
		List statusForRuleAndSps = new ArrayList();			
		statusForRuleAndSps.add(DomainConstants.MAPPED_STATUS);
		statusForRuleAndSps.add(DomainConstants.UNMAPPED_STATUS);
		
		//Fetching existing mappings for Custom message
		List status = new ArrayList();
		status.add(DomainConstants.VIEW_STATUS);
		String message = "";
		String messageReqd = "N";
		HippaSegment noteType = new HippaSegment();
		Mapping mappingForExistingCustomMessage = new Mapping();
		List existingMessageList = locateCustomMessageFacade.getRecords(mappingToBeCopied, status, null, -1, 21, null);
		if(null != existingMessageList && !existingMessageList.isEmpty()){
			mappingForExistingCustomMessage = (Mapping)existingMessageList.get(0);
			if(null == mappingForExistingCustomMessage.getIndvdlEb03AssnIndicator() || 
					mappingForExistingCustomMessage.getIndvdlEb03AssnIndicator().equalsIgnoreCase("N")){
				message = (null != mappingForExistingCustomMessage.getMessage() ? mappingForExistingCustomMessage.getMessage() : "");
				messageReqd = (null != mappingForExistingCustomMessage.getMsg_type_required() ? mappingForExistingCustomMessage.getMsg_type_required() : "N" );
				noteType = (null != mappingForExistingCustomMessage.getNoteTypeCode() ?  mappingForExistingCustomMessage.getNoteTypeCode()  : null );
			}
		}
		
		List ruleMappings = locateRuleIdFacade.getRecords(mapping, statusForRuleAndSps, null, -1, 21,null);
		
		// check whether rule entered is valid
			Mapping mappedRule = (Mapping)ruleMappings.get(0);
			ruleDesc = BxUtil.escapeSpecialCharacters(mappedRule.getRule().getRuleDesc());
			mapping.getRule().setRuleDesc(ruleDesc);
			mapping.setEb03(null != mappedRule.getEb03() ? mappedRule.getEb03() : null );
			
		List spsMappings = locateSpsIdFacade.getRecords(mapping, statusForRuleAndSps, null, -1, 21, null);	
	
			Mapping mappedSps = (Mapping)spsMappings.get(0);
			spsDesc = BxUtil.escapeSpecialCharacters(mappedSps.getSpsId().getSpsDesc());
			mapping.getSpsId().setSpsDesc(spsDesc);

		
	
		// Modified as part of SSCR 19537

		status.clear();
		status.add(DomainConstants.CUSTOM_MESSAGE_CREATE);
		List mappings = locateCustomMessageFacade.getRecords(mapping, status, null, -1, 21, null);
		
				
		if(null == mappings){
			ModelAndView modelAndView = new ModelAndView("createcustommsgmapping");
			mapping.setMessage(message);
			mapping.setMsg_type_required(messageReqd);
			mapping.setNoteTypeCode(noteType);
			
			modelAndView.addObject("mapping", mapping);
			TokenGenerator.getInstance().saveToken(request);
			modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY,
					(String) session
							.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
			return modelAndView;
		} 
		else{
			paramList.add(newSpsId.getSpsId());
			paramList.add(newrule.getHeaderRuleId());
			String msgInvalid = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_CUSTOM_MSG_EXISTS, paramList);		
			errorMessages.add(msgInvalid);
			sessionMessageTray.setErrorMessages(errorMessages);
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
		
	/**
	 * View history of a mapping i.e audit trail 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewHistory(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		Mapping mapping = new Mapping();
		Rule rule = new Rule();	
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		mapping.setRule(rule);	
		/**
		 * SPS
		 */
		SPSId spsId = new SPSId();	
		spsId.setSpsId(request.getParameter("spsId"));
		mapping.setSpsId(spsId);
		
		List mappings  = locateCustomMessageFacade.getRecords(mapping, null, null,
				WebConstants.TOTAL_NO_OF_RECORDS, WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		ModelAndView modelAndView = new ModelAndView("viewHistoryCustomMsg");
		if(null != mappings && !mappings.isEmpty()){
			mapping = (Mapping) mappings.get(0);
			modelAndView.addObject(WebConstants.MAPPING, mapping);
			modelAndView.addObject("auditTrailList",mapping.getAuditTrails());
			if(mapping.getAuditTrails().size()>=WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
				modelAndView.addObject("viewlink", new Boolean(true));
			}
			
		}	
		return modelAndView;
	}	
	/**
	 * saving a mapping and remain in the edit screen
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView saveMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		String pageName = request.getParameter("pageFrom");
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING);
		
		if(TokenGenerator.getInstance().isTokenValid(request,true)){
			MappingResult result = saveAndDone(request);
			
			//			 mapping creation successful
			if(result.isStatus()){
				List warningMessages = new ArrayList();
				if(null!= result.getWarningMsgsList()){
					warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
					modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
				}
				List infoMessages = new ArrayList(); 
				infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SAVE_SUCCESS, null));
				modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
				/*modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
				 return modelAndView;*/
			}
			else{
				List errorMessages = new ArrayList();						
				if(null!= result.getErrorMsgsList()){
					
					errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				}
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			}	
			TokenGenerator.getInstance().saveToken(request);
			result.getMapping().setPageFrom(pageName);
			return modelAndView.addObject(WebConstants.MAPPING,result.getMapping())
			.addObject(WebConstants.CHANGE_COMMENTS, request.getParameter(
					WebConstants.CHANGE_COMMENTS).toUpperCase())
					.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
			
		}
		else{
			if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
				return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
			}else{
				return  WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
		
	}
	/**
	 * saving and redirecting and landing page
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView done(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException{
		List infoMessages = new ArrayList(); 
		MappingResult result = saveAndDone(request);
		String pageName = request.getParameter("pageFrom");
		// mapping creation successful
		if(result.isStatus()){
			if(null!= result.getWarningMsgsList()){
				List warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
				sessionMessageTray.setWarningMessages(warningMessages);
			}
			infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SAVE_SUCCESS, null));
			sessionMessageTray.setInformationMessages(infoMessages);	
		}
		else{
			List errorMessages = new ArrayList();						
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			result.getMapping().setPageFrom(pageName);
			return new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING)
			.addObject(WebConstants.MAPPING,result.getMapping())
			.addObject(WebConstants.CHANGE_COMMENTS, request.getParameter(
					WebConstants.CHANGE_COMMENTS).toUpperCase())
					.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{			
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	/**
	 * private method for save and done 
	 * @param request
	 * @return
	 * @throws EBXException
	 * @throws ParseException 
	 */
	private MappingResult saveAndDone(HttpServletRequest request)throws EBXException, ParseException{		

		Mapping mapping = createMappingObject(request);
		
		String changeComments = request.getParameter(
				WebConstants.CHANGE_COMMENTS).toUpperCase();
		
		MappingResult result = mappingCustomMessageFacade.update(mapping,
				changeComments);
		
		if(null != result.getMapping().getMsg_type_required() && result.getMapping().getMsg_type_required().equals(WebConstants.MESSAGE_REQUIRED_INDICATOR_Y)){				
			result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_TRUE);
		}
		else {
			result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_FALSE);
		}
		if(null != result.getMapping() && (null == result.getMapping().getIndvdlEb03AssnIndicator() || result.getMapping().getIndvdlEb03AssnIndicator().equalsIgnoreCase("N"))){
			EB03Association eB03AssnObj =  null != result.getMapping().getEb03() && null !=  result.getMapping().getEb03().getEb03Association() 
			&&  !result.getMapping().getEb03().getEb03Association().isEmpty() ? 
				(EB03Association) result.getMapping().getEb03().getEb03Association().get(0) :  null;
			if(null != eB03AssnObj){
				String message = eB03AssnObj.getMessage();
				String msgReqdString = eB03AssnObj.getMsgReqdInd();
				HippaCodeValue noteType = eB03AssnObj.getNoteType();
				HippaSegment noteTypeHippaSegment = new HippaSegment();
				List<HippaCodeValue> selectedValue = new ArrayList<HippaCodeValue>();
				selectedValue.add(noteType);
				noteTypeHippaSegment.setHippaCodeSelectedValues(selectedValue);
				result.getMapping().setMessage(message);
				result.getMapping().setNoteTypeCode(noteTypeHippaSegment);
				result.getMapping().setMsg_type_required(null != msgReqdString && msgReqdString.equalsIgnoreCase("Y") ? WebConstants.MESSAGE_REQUIRED_INDICATOR_TRUE
						 : WebConstants.MESSAGE_REQUIRED_INDICATOR_FALSE);
			}
		}
		
		return result;
	}
	/**
	 * saving and scheduling to test a mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		String pageName = request.getParameter("pageFrom");
		List infoMessages = new ArrayList();  
		Mapping mapping = createMappingObject(request);
		String changeComments = request.getParameter(WebConstants.CHANGE_COMMENTS).toUpperCase();
		MappingResult result =  mappingCustomMessageFacade.updateAndSendToTest(mapping, changeComments);
		
		if(null != result.getMapping().getMsg_type_required() && result.getMapping().getMsg_type_required().equals(WebConstants.MESSAGE_REQUIRED_INDICATOR_Y)){				
			result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_TRUE);
		}
		else {
			result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_FALSE);
		}
		// mapping creation successful
		if(result.isStatus()){
			infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SEND_TO_TEST_SUCCESS, null));
			sessionMessageTray.setInformationMessages(infoMessages);	
		}
		else{
			List errorMessages = new ArrayList();						
			if(null!= result.getErrorMsgsList()){				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			result.getMapping().setPageFrom(pageName);
			return new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING)
			.addObject(WebConstants.MAPPING,result.getMapping())
			.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
			.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	
	/**
	 * Saving and approving a mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView approve(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		String pageName = request.getParameter("pageFrom");
		
		List infoMessages = new ArrayList();  
		Mapping mapping = createMappingObject(request);
		String changeComments = request.getParameter(WebConstants.CHANGE_COMMENTS).toUpperCase();
		MappingResult result =  mappingCustomMessageFacade.updateAndApprove(mapping, changeComments);
		if(null != result.getMapping().getMsg_type_required() && result.getMapping().getMsg_type_required().equals(WebConstants.MESSAGE_REQUIRED_INDICATOR_Y)){				
			result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_TRUE);
		}
		else {
			result.getMapping().setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_FALSE);
		}
		// mapping creation successful
		if(result.isStatus()){		
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approve.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_APPROVE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);		
		}
		else{
			List errorMessages = new ArrayList();						
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			result.getMapping().setPageFrom(pageName);
			return new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING)
			.addObject(WebConstants.MAPPING,result.getMapping())
			.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
			.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	
	/**
	 * Discard changes to a previously published mapping
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView discardChanges(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		String pageName = request.getParameter("pageFrom");
		
		Mapping mapping = new Mapping();
		Rule rule = new Rule();
		List infoMessages = new ArrayList(); 
		mapping.setRule(rule);		
		mapping.getRule().setHeaderRuleId(request.getParameter("selectedruleId"));
		mapping.getRule().setRuleDesc(request.getParameter("ruleDesc"));
		
		SPSId spsId = new SPSId();	
		mapping.setSpsId(spsId);
		mapping.getSpsId().setSpsId(request.getParameter("selectedspsId"));
		mapping.getSpsId().setSpsDesc(request.getParameter("spsDesc"));
		
		
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		MappingResult result = mappingCustomMessageFacade.discardChanges(mapping,request.getParameter(WebConstants.CHANGE_COMMENTS) );
		if(result.isStatus()){
			String mappingSuccess = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_DISCARD_CHANGES_SUCCESS, null);		
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	/**
	 * to edit a mapping from in progress section
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editCustomMessageMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		String pageName = request.getParameter("pageName");
		
		HttpSession session = request.getSession();
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("selectedRuleIdCusForEdit"));
		mapping.setRule(rule);
		
		SPSId Sps = new SPSId();
		Sps.setSpsId(request.getParameter("selectedSpsIdCusForEdit"));    
		mapping.setSpsId(Sps);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		TokenGenerator.getInstance().saveToken(request); 
		ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_CUSTOM_MESSAGE_MAPPING)
		.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
		
		List status = new ArrayList();
		status.add(DomainConstants.VIEW_STATUS);
		
		//List mappings = locateCustomMessageFacade.getRecords(mapping, status, null,
		//WebConstants.TOTAL_NO_OF_RECORDS, WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		MappingResult mappingResult=locateCustomMessageFacade.getRecordsForUpdate(mapping, status, null,
				WebConstants.TOTAL_NO_OF_RECORDS, WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		
		//if(null != mappings && !mappings.isEmpty()){                
		//if(null != mappings.get(0)){                    
		//mapping = (Mapping) mappings.get(0);  
		if (mappingResult != null) {
			if (!mappingResult.isStatus()) {
				if(null!= mappingResult.getErrorMsgsList()){        					
					List errorMessages = BxUtil.getMessages(mappingResult.getErrorMsgsList());
					sessionMessageTray.setErrorMessages(errorMessages);
					if(null != pageName && pageName.trim().equals("advanceSearchEbx"))
						return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
					else
						return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
			mapping = (Mapping) mappingResult.getMapping(); 
			
			String userName = request.getAttribute(SecurityConstants.USER_NAME).toString();
			user.setLastUpdatedUserName(userName);
			user.setCreatedUserName(userName);
			mapping.setUser(user);
			
			if(mapping.getVariableMappingStatus().equals(DomainConstants.STATUS_PUBLISHED)){
				
				MappingResult result=locateCustomMessageFacade.getRecordsForUpdate(mapping);
				
				if(!result.isStatus()){            			
					if(null!= result.getErrorMsgsList()){        					
						List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
						sessionMessageTray.setErrorMessages(errorMessages);
						if(null != pageName && pageName.trim().equals("advanceSearchEbx"))
							return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
						else
							return WebUtil.redirectToeWPDBXLandingPage(request);
					}
				}
			}
			
			if(null != mapping.getMsg_type_required() && mapping.getMsg_type_required().equals(WebConstants.MESSAGE_REQUIRED_INDICATOR_Y)){                      
				mapping.setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_TRUE);
			}
			else {
				mapping.setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_FALSE);
			}
			mapping.setPageFrom(pageName);
			modelAndView.addObject(WebConstants.MAPPING, mapping);
			
			if(null != mapping.getAuditTrails() && (!mapping.getAuditTrails().isEmpty())){
				AuditTrail   auditTrail  = (AuditTrail) mapping.getAuditTrails().get(0);                    
				String changeComments = auditTrail.getUserComments();
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS, changeComments);
			}
		}
		return  modelAndView;     
}
	public ModelAndView printCustomMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		Mapping currentMapping = createMappingObject(request);
		List mappings;
		
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();

		List status = new ArrayList();
		status.add(DomainConstants.VIEW_STATUS);
		      
		mappings = locateCustomMessageFacade.getRecords(currentMapping, status, null,
		    		  WebConstants.TOTAL_NO_OF_RECORDS, WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);



		ModelAndView modelAndView = new ModelAndView("printCustomMessageMapping");

		if (null != mappings && !mappings.isEmpty() && null != mappings.get(0)) {
			Mapping mapping = (Mapping) mappings.get(0);
			currentMapping.setRule(mapping.getRule());
			currentMapping.setSpsId(mapping.getSpsId());
			currentMapping.setAuditTrails(mapping.getAuditTrails());
			currentMapping.setVariableMappingStatus(mapping.getVariableMappingStatus());
			
		}
		
		//Added as part of SSCR 19537
		
		Map<String,String> eb03ValDescMap = locateCustomMessageFacade.fetchHippaSegmentDescription(DomainConstants.EB03_NAME);
		Map<String,String> noteTypeValDescMap = locateCustomMessageFacade.fetchHippaSegmentDescription(DomainConstants.NOTE_TYPE_CODE);
		
		if (null != request.getParameter("individualMappingString")) {
			List<EB03Association> individualMappingList = new ArrayList<EB03Association>();
			StringTokenizer st = new StringTokenizer(request.getParameter("individualMappingString"),"**");
		     while (st.hasMoreTokens()) {
		    	 HippaCodeValue hippaCodeVal = new HippaCodeValue();
		    	 String eb03ObjVal =  st.nextToken();
		    	 if(null != eb03ObjVal){
			    	 String[] eb03_MessageVal = eb03ObjVal.split("_");
			         EB03Association eb03AssnObj = new EB03Association();
			         if(eb03_MessageVal.length >= 1){
			        	 hippaCodeVal.setValue(null != eb03_MessageVal[0] ? eb03_MessageVal[0].trim() : "");
			        	 hippaCodeVal.setDescription(eb03ValDescMap.get(null != eb03_MessageVal[0] ? eb03_MessageVal[0].trim() : ""));
			        	 eb03AssnObj.setEb03(hippaCodeVal);
			         }
			         if(eb03_MessageVal.length >= 2){
			        	 String messageText = (null != eb03_MessageVal[1] ? eb03_MessageVal[1].trim() : "" );
			        	 eb03AssnObj.setMessage(messageText);
			         }
			         if(eb03_MessageVal.length >= 3){
			        	 String messageReqd = (null != eb03_MessageVal[2] ? eb03_MessageVal[2].trim() : "" );
			        	 eb03AssnObj.setMsgReqdInd(messageReqd);
			         }
			         
			         //Note Type
			         hippaCodeVal = new HippaCodeValue();
			         if(eb03_MessageVal.length >= 4){
			        	 hippaCodeVal.setValue(null != eb03_MessageVal[3] ? eb03_MessageVal[3].trim() : "");
			        	 hippaCodeVal.setDescription(noteTypeValDescMap.get(null != eb03_MessageVal[3] ? eb03_MessageVal[3].trim() : ""));
			         } 
			         eb03AssnObj.setNoteType(hippaCodeVal);
			         individualMappingList.add(eb03AssnObj);
		    	 }

		     }
		     if (null != request.getParameter("individualMappingString")){
		    	 currentMapping.getEb03().setEb03Association(individualMappingList);
		    	 eB03AssnList =  currentMapping.getEb03().getEb03Association();
		     }
		}

		modelAndView.addObject("currentMapping", currentMapping);
		modelAndView.addObject("eB03AssnList", eB03AssnList);
		
		return modelAndView;
	}
	
	/**
	 * Set the mapping object by getting the values from the request
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	private Mapping createMappingObject(HttpServletRequest request) throws ParseException{
    	
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		SPSId spsIdObj = new SPSId();
		rule.setHeaderRuleId(request.getParameter("selectedruleId"));
		rule.setRuleDesc(BxUtil.escapeSpecialCharacters(request.getParameter("ruleDesc")));
		spsIdObj.setSpsId(request.getParameter("selectedspsId"));
		spsIdObj.setSpsDesc(BxUtil.escapeSpecialCharacters(request.getParameter("spsDesc")));
		mapping.setRule(rule);
		mapping.setSpsId(spsIdObj);
		//String date = request.getParameter("createdTimeStamp");
		
		String createdDate = request.getParameter("createdDate");
		if(null != createdDate && !createdDate.isEmpty()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date convertedCurrentDate = sdf.parse(createdDate);
		    mapping.setCreatedTmStamp(convertedCurrentDate);
		}
		HippaSegment hippaSegment;
		// changed for reference data values
		hippaSegment = createHippaSegment(DomainConstants.NOTE_TYPE_CODE,request.getParameter(WebConstants.NOTE_TYPE_CODE),request.getParameter(WebConstants.NOTE_TYPE_CODE_DESC));
		mapping.setNoteTypeCode(hippaSegment);
		mapping.setMessage(request.getParameter("messageText"));
		
		// setting message required checkbox
		if(null!=request.getParameter("msgRqdChkBox")){
			mapping.setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_Y);
		}else{
			mapping.setMsg_type_required(WebConstants.MESSAGE_REQUIRED_INDICATOR_N);
		}
		// setting user
		String userComments = request.getParameter(WebConstants.CHANGE_COMMENTS);
		user.setCreatedUserName(userComments);
		user.setLastUpdatedUserName(userComments);
		mapping.setUser(user);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setCreatedUserName(userId);
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);
		mapping.setEb03(new HippaSegment());
		mapping.getEb03().setName(DomainConstants.EB03_NAME);
		mapping.getEb03().setDescription(DomainConstants.EB03_NAME);
		
		//Added as part of SSCR 19537
		if(null != request.getParameter("individualMappingForEB03ChkBox") 
				&& request.getParameter("individualMappingForEB03ChkBox").equalsIgnoreCase("on")){
			  String jsonArray = request.getParameter("hdeb03AssnJSON");
			List<EB03Association> eb03AssnList = createObjectFromJSON(jsonArray);
			mapping.setIndvdlEb03AssnIndicator("Y");
			//Adding EB03Assn object to the mapping object
			mapping.getEb03().setEb03Association(eb03AssnList);
			mapping.setNoteTypeCode(null);
			mapping.setMessage(DomainConstants.BLANK_SPACE);
			mapping.setMsg_type_required("N");
		}else{
			
			List<EB03Association> eb03AssnList = createEB03AssociationObject(request);
			mapping.setIndvdlEb03AssnIndicator("N");
			//Adding EB03Assn object to the mapping object
			mapping.getEb03().setEb03Association(eb03AssnList);
		}
		
		return mapping;
    }
    
    //to set the hippacode value and description
	private HippaSegment createHippaSegment(String hippaSegmentName, String value, String description) {
		HippaSegment hippaSegment=new HippaSegment(hippaSegmentName);
	
		List selectedHippaSegmentValues = new ArrayList();
		HippaCodeValue code = new HippaCodeValue();
		code.setValue(value);
		code.setDescription(description);
		selectedHippaSegmentValues.add(code);
		hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
		return hippaSegment;
	}
	
	/**
	 * Controller menthod for listing existing message texts
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView existingMsgTexts(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		String message = request.getParameter("message");
		String eb03 = request.getParameter("eb03");
		String headerRuleId = request.getParameter("headerRuleId");
		String currentPage = request.getParameter("currentPage");
		String section = request.getParameter("section");
		
				
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setEB03(eb03);
		searchCriteria.setHeaderRuleId(headerRuleId);
		searchCriteria.setMessageText(message);
		
		//Search Criteria For Standard Message -- BXNI CHANGE
		if("FALSE".equalsIgnoreCase(request.getParameter("showOnlyStandardMessages"))){

			searchCriteria.setShowOnlyStandardMessages(false);
		}else {
			searchCriteria.setShowOnlyStandardMessages(true);
		}
		
		if(!BxUtil.hasText(eb03) && !BxUtil.hasText(headerRuleId) && !BxUtil.hasText(message) && !searchCriteria.getShowOnlyStandardMesages()){
			return getSearchMessageResultView(getPage(currentPage, section, 1), new ArrayList());
		}
		
		int totalNoOfRecords = messageTxtSearchFacade.getSearchRecordCount(searchCriteria);
		
		Page page = getPage(currentPage, section, totalNoOfRecords);
		return getSearchMessageResultView(page, messageTxtSearchFacade.getSearchRecords(searchCriteria, 50, page));
	}
	
	/**
	 * Method returns view for message text search result.
	 * @param page
	 * @param list
	 * @return
	 */
	private ModelAndView getSearchMessageResultView(Page page,List list){
		Map map = new HashMap();
		map.put("searchResults", list);
		map.put("page", page);
		ModelAndView modelAndView = new ModelAndView("viewMessageSearch",map);
		return modelAndView;
	}
	
	/**
	 * Method returs page based on the selection in pagination.
	 * @param currentPage
	 * @param section
	 * @param totalNoOfRecords
	 * @return
	 */
	private Page getPage(String currentPage,String section, int totalNoOfRecords){
		if(totalNoOfRecords == 0){
			totalNoOfRecords = 1;
		}
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalNoOfRecords(totalNoOfRecords);
		
		if (section.equalsIgnoreCase("Next")) {
			if (page.getCurrentPage() >= 0
					&& page.getCurrentPage() < page.getTotalNoOfPages()) {
				page.setCurrentPage(page.getCurrentPage() + 1);
			}
		}

		if (section.equalsIgnoreCase("Previous")) {
			if (page.getCurrentPage() > 1) {
				page.setCurrentPage(page.getCurrentPage() - 1);
			}
		}
		if (section.equalsIgnoreCase("Last")) {
			page.setCurrentPage(page.getTotalNoOfPages());
		}
		if (section.equalsIgnoreCase("First")) {
			page.setCurrentPage((page.getTotalNoOfPages() / page
					.getTotalNoOfPages()));
		}
		
		return page;
	}
	
	private void deleteLockDuringCancel(HttpServletRequest request) {
		List mappingList = new ArrayList();
		String ruleId = request.getParameter("selectedruleId");
		String spsId = request.getParameter("selectedspsId");
		Mapping mapping = new Mapping();
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		if (null != spsId
				&& null != ruleId
				&& !(DomainConstants.EMPTY.equals(ruleId.trim()) && DomainConstants.EMPTY
						.equals(spsId.trim()))) {
			Rule rule = new Rule();
			rule.setHeaderRuleId(ruleId);

			SPSId sps = new SPSId();
			sps.setSpsId(spsId);

			mapping.setRule(rule);
			mapping.setSpsId(sps);
			List status = new ArrayList();
			status.add(DomainConstants.VIEW_STATUS);

			mappingList = locateCustomMessageFacade.getRecords(mapping, status,
					null, -1, 21, null);
		}
		if (null != mappingList && !mappingList.isEmpty()) {
			Mapping existingMapping = (Mapping) mappingList.get(0);
			if (null != existingMapping) {
				// Case 1 : Delete the Lock when mapping status is "scheduled to
				// test"
				// or "sheduled to production" or "object transferred".
				if (DomainConstants.STATUS_SCHEDULED_TO_TEST
						.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION
								.equals(existingMapping
										.getVariableMappingStatus())
						|| DomainConstants.STATUS_OBJECT_TRANSFERRED
								.equals(existingMapping
										.getVariableMappingStatus())
						|| DomainConstants.STATUS_NOT_APPLICABLE
								.equals(existingMapping
										.getVariableMappingStatus())) {
					mappingCustomMessageFacade.unlock(mapping);
				}
				// Case 2 : Delete the Lock when mapping status is "Building"
				// or "Being Modified" and the loggedin user is not equal to
				// last updated user.
				else if ((DomainConstants.STATUS_BEING_MODIFIED
						.equals(existingMapping.getVariableMappingStatus()) || DomainConstants.STATUS_BUILDING
						.equals(existingMapping.getVariableMappingStatus()))
						&& (!loggedInUser.equalsIgnoreCase(existingMapping.getUser()
								.getLastUpdatedUserName()))) {
					mappingCustomMessageFacade.unlock(mapping);
				}

			}
		}
			
		}
	/**
	 * 
	 * @return locateCustomMessageFacade
	 */
	public LocateFacade getLocateCustomMessageFacade() {
		return locateCustomMessageFacade;
	}
	/**
	 * 
	 * @param locateCustomMessageFacade
	 */
	public void setLocateCustomMessageFacade(LocateFacade locateCustomMessageFacade) {
		this.locateCustomMessageFacade = locateCustomMessageFacade;
	}
	/**
	 * 
	 * @return mappingCustomMessageFacade
	 */
	public MappingFacade getMappingCustomMessageFacade() {
		return mappingCustomMessageFacade;
	}
	/**
	 * 
	 * @param mappingCustomMessageFacade
	 */
	public void setMappingCustomMessageFacade(
			MappingFacade mappingCustomMessageFacade) {
		this.mappingCustomMessageFacade = mappingCustomMessageFacade;
	}
	/**
	 * 
	 * @return sessionMessageTray
	 */
	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}
	/**
	 * 
	 * @param sessionMessageTray
	 */
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
/**
 * 
 * @return locateRuleIdFacade
 */
	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}
/**
 * 
 * @param locateRuleIdFacade
 */
	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}
/**
 * 
 * @return locateSpsIdFacade
 */
	public LocateFacade getLocateSpsIdFacade() {
		return locateSpsIdFacade;
	}
/**
 * 
 * @param locateSpsIdFacade
 */
	public void setLocateSpsIdFacade(LocateFacade locateSpsIdFacade) {
		this.locateSpsIdFacade = locateSpsIdFacade;
	}
	
	public AdvanceSearchFacade getMessageTxtSearchFacade() {
		return messageTxtSearchFacade;
	}
	public void setMessageTxtSearchFacade(AdvanceSearchFacade messageTxtSearchFacade) {
		this.messageTxtSearchFacade = messageTxtSearchFacade;
	}
	
	// Added as part of SSCR 19537
	/*
	 * The method will create EB03Association object from the JSON object obtained from the JSP
	 */

	private List<EB03Association> createObjectFromJSON(String jsonArray){
		  
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		try{
		   //declare a JSONArray and assign the corresponding stringified json object coming from jsp   
		   // jsonEntityPanel is the hidden parameter name
			  JSONArray hdeb03AssnJSONPanelArray = JSONArray.fromObject(jsonArray);
			  EB03Association eb03AssnObject;
			  HippaCodeValue eb03AssnHippaCode;

		   //check the length of the json array
		  if (hdeb03AssnJSONPanelArray.size() > 0)
		      {
		        //iterate the json array and get the corresponding JSONObject
		            for (int i = 0; i < hdeb03AssnJSONPanelArray.size(); i++)
		            {
			  			eb03AssnObject= new EB03Association();
		               //get the json object
		               final JSONObject jsonObject = hdeb03AssnJSONPanelArray.getJSONObject(i);
		               //get the first value from the json object in the order of which it is pushed
		               //remember – EB03, III02, III02Desc and entityPrivilege is the common name given while creating the
		               //  json object
		               final String eb03Val = jsonObject.getString("EB03");
		               if(null != eb03Val && !eb03Val.isEmpty()){
			               eb03AssnHippaCode = new HippaCodeValue();
			               eb03AssnHippaCode.setValue(eb03Val.trim());
			               eb03AssnObject.setEb03(eb03AssnHippaCode);
			               //get the III02 value from the json object in the order of which it is pushed
			               final String msgVal = jsonObject.getString("MSG");
			               final String msgReqd = jsonObject.getString("MSG_REQD");
			               final String noteType = jsonObject.getString("NOTE_TYPE");
			               final String noteTypeDesc = jsonObject.getString("NOTE_TYPE_DESC");
			               eb03AssnHippaCode = new HippaCodeValue();
			               eb03AssnHippaCode.setValue(null != noteType ? noteType.trim() : "");
			               eb03AssnHippaCode.setDescription(null != noteTypeDesc ? noteTypeDesc.trim() : "");
			               eb03AssnObject.setNoteType(eb03AssnHippaCode);
			               eb03AssnObject.setMessage(null != msgVal ? msgVal.trim() : "");
			               eb03AssnObject.setMsgReqdInd(msgReqd);
			               eb03AssnList.add(eb03AssnObject);
		            }

		            }
		      }


		  } catch (final JSONException jsEx)
		      {
		            jsEx.printStackTrace();
		            
		      }
	return eb03AssnList;	  
	}
	
	private List<EB03Association> createEB03AssociationObject(
			HttpServletRequest request) {

		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		try {
			String msgVal = request.getParameter("messageText");
			String msgReqd = request.getParameter("msgRqdChkBox");
			String noteType = request.getParameter("noteType");
			String noteTypeDesc1 = request.getParameter("NoteTypeDesc");
			EB03Association eb03AssnObject = null;
			HippaCodeValue eb03AssnHippaCode;
			Set<String> individualMappingSet = new HashSet<String>();

			//int eb03Count = Integer.parseInt(request.getParameter("eb03Count"));
			String jsonArray = request.getParameter("hdeb03AssnJSON");

			if(null != jsonArray && !jsonArray.equalsIgnoreCase("")){
				JSONArray hdeb03AssnJSONPanelArray = JSONArray
						.fromObject(jsonArray);
	
				// iterate the json array and get the corresponding JSONObject
				if(null != hdeb03AssnJSONPanelArray){
					for (int i = 0; i < hdeb03AssnJSONPanelArray.size(); i++) {
						eb03AssnObject = new EB03Association();
						final JSONObject jsonObject = hdeb03AssnJSONPanelArray
								.getJSONObject(i);
						final String eb03Val = jsonObject.getString("EB03");
						if (null != eb03Val && !eb03Val.isEmpty()) {
							eb03AssnHippaCode = new HippaCodeValue();
							eb03AssnHippaCode.setValue(eb03Val);
							eb03AssnObject.setEb03(eb03AssnHippaCode);
		
							eb03AssnObject.setEb03(eb03AssnHippaCode);
							eb03AssnHippaCode = new HippaCodeValue();
							eb03AssnHippaCode.setValue(noteType);
							eb03AssnHippaCode.setDescription(noteTypeDesc1);
							eb03AssnObject.setNoteType(eb03AssnHippaCode);
							eb03AssnObject.setMessage(msgVal);
							eb03AssnObject.setMsgReqdInd(null != msgReqd && msgReqd.equalsIgnoreCase("true") ?  "Y" : "N");
							eb03AssnList.add(eb03AssnObject);
		
						}
					}
			}
			}
		} catch (final JSONException jsEx) {
			jsEx.printStackTrace();

		}
		return eb03AssnList;
	}

}
