/*
 * <CreateOrEditRuleMappingController.java>
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
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

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.mapping.application.MappingFacade;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

/**
 * @author UST-GLOBAL This is a controller class for create and editing a rule
 *         mapping
 */
public class CreateOrEditRuleMappingController extends MultiActionController {

	private LocateFacade locateRuleIdFacade;

	private MappingFacade mappingRuleIdFacade;

	private SessionMessageTray sessionMessageTray;
	
	private static Logger log = Logger.getLogger(CreateOrEditRuleMappingController.class);

	private List<EB03Association> existingEB03AssnList = new ArrayList<EB03Association>();
	//private MappingResult 
	/**
	 * create a new mapping
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		String pageName = request.getParameter("pageFrom");
		
		HttpSession session = request.getSession();
		List warningMessages = new ArrayList();
		List<EB03Association> eB03AssnList;
		Mapping mapping = createMappingObject(request);
		
		eB03AssnList = (null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				? mapping.getEb03().getEb03Association() : new ArrayList<EB03Association>());
		
		String changeComments = request.getParameter(
				WebConstants.CHANGE_COMMENTS).toUpperCase();

		if (TokenGenerator.getInstance().isTokenValid(request, true)) {

			MappingResult result = mappingRuleIdFacade.create(mapping,
					changeComments);

			if (null != result.getMapping().getSensitiveBenefitIndicator()
					&& result.getMapping().getSensitiveBenefitIndicator()
							.equals("Y")) {
				result.getMapping().setSensitiveBenefitIndicator(
						WebConstants.SENSITIVE_BNFT_INDICATOR_TRUE);
			} else {
				result.getMapping().setSensitiveBenefitIndicator(
						WebConstants.SENSITIVE_BNFT_INDICATOR_FALSE);
			}
			TokenGenerator.getInstance().saveToken(request);
			if(null != result.getMapping() && null != result.getMapping().getEb03() && null != result.getMapping().getEb03().getEb03Association()){
				eB03AssnList =  result.getMapping().getEb03().getEb03Association();
			}
			
			// mapping creation successful
			if (result.isStatus()) {
				
				List infoMessages = new ArrayList();
			
				if(null!= result.getWarningMsgsList()){
					
					warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
				}
				infoMessages.add(BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_SAVE_SUCCESS, null));
				result.getMapping().setPageFrom(pageName);
				return new ModelAndView(WebConstants.EDIT_RULE_MAPPING)
				.addObject(WebConstants.MAPPING, result
						.getMapping())
				.addObject(WebConstants.CHANGE_COMMENTS,
						changeComments)
						.addObject("eB03AssnList",
								eB03AssnList)
				.addObject(WebConstants.INFO_MESSAGES, infoMessages)
				.addObject(WebConstants.WARNING_MESSAGES, warningMessages)
				.addObject(WebConstants.TRANSACTION_TOKEN_KEY,
								(String) session
										.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));				
			} else {
				List errorMessages = new ArrayList();
				if(null!= result.getErrorMsgsList()){
					
					errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				}
				result.getMapping().setPageFrom(pageName);
				
				
				return new ModelAndView("createrulemapping")
				.addObject(WebConstants.MAPPING, result
						.getMapping())
				.addObject(WebConstants.CHANGE_COMMENTS,
						changeComments)
						.addObject("eB03AssnList",
								eB03AssnList)
				.addObject(WebConstants.ERROR_MESSAGES, errorMessages)					
				.addObject(WebConstants.TRANSACTION_TOKEN_KEY,
								(String) session
										.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
			}
		} else {
			if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
				return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
			}else{
				return WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
	}

	/**
	 * cancel edit and redirecting to landing page
	 * 
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
	 * Cancel the opertaion and control goes to advance search page.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView cancelToAdvanceSearchPage(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		deleteLockDuringCancel(request);
		return WebUtil.redirectToeWPDAdvanceSearchPage(request);
	}
	/**
	 * marking a mapping as not applicable
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView notApplicable(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		Mapping mapping = createMappingObject(request);	
		
		List<EB03Association> eB03AssnList;
		eB03AssnList = (null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				? mapping.getEb03().getEb03Association() : new ArrayList<EB03Association>());
		
		String pageName = request.getParameter("pageFrom");
		
		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		
		String changeComments = request.getParameter(
				WebConstants.CHANGE_COMMENTS).toUpperCase();
		
		MappingResult result = mappingRuleIdFacade.notApplicable(mapping,
				changeComments);
		
		if (result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_RULEID_NOT_APPLICABLE, null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		} else {
			result.getMapping().setPageFrom(pageName);
			
			if(null != result.getMapping() && null != result.getMapping().getEb03() && null != result.getMapping().getEb03().getEb03Association()){
				eB03AssnList =  result.getMapping().getEb03().getEb03Association();
			}
			
			return new ModelAndView("createrulemapping")
			.addObject(WebConstants.MAPPING, result.getMapping())			
			.addObject(WebConstants.CHANGE_COMMENTS, changeComments)
			.addObject("eB03AssnList", eB03AssnList)
			.addObject(WebConstants.ERROR_MESSAGES, result
					.getErrorMsgsList());		
		}
		
		
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	/**
	 * Copy mapping to another rule id
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView copyToMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		String pageName = request.getParameter("pageFrom");
		
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView("createrulemapping");
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		Mapping mapping = new Mapping();
		Rule newrule = new Rule();
		newrule.setHeaderRuleId(request.getParameter("selectedruleIdCopyTo"));
		mapping.setRule(newrule);

		Mapping mappingToBeCopied = new Mapping();
		Rule previousRuleId = new Rule();
		previousRuleId.setHeaderRuleId(request.getParameter("selectedruleId"));
		mappingToBeCopied.setRule(previousRuleId);

		List status = new ArrayList();
		status.add(DomainConstants.UNMAPPED_STATUS);

		List mappings = locateRuleIdFacade.getRecords(mappingToBeCopied, null,
				null, WebConstants.TOTAL_NO_OF_RECORDS, WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		List newMapping = locateRuleIdFacade.getRecords(mapping, status, null,
				WebConstants.TOTAL_NO_OF_RECORDS, WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

		if (null != newMapping && !newMapping.isEmpty()) {
			mapping = (Mapping) newMapping.get(0);
			newrule.setRuleDesc(mapping.getRule().getRuleDesc());
		}
		if (null != mappings && !mappings.isEmpty()) {
			mappingToBeCopied = (Mapping) mappings.get(0);
			mappingToBeCopied.setRule(newrule);
			mappingToBeCopied.setPageFrom(pageName);
			if (null != mappingToBeCopied.getSensitiveBenefitIndicator()
					&& mappingToBeCopied.getSensitiveBenefitIndicator().equals(
							"Y")) {
				mappingToBeCopied
						.setSensitiveBenefitIndicator(WebConstants.SENSITIVE_BNFT_INDICATOR_TRUE);
			} else {
				mappingToBeCopied
						.setSensitiveBenefitIndicator(WebConstants.SENSITIVE_BNFT_INDICATOR_FALSE);
			}
			if (null != mappingToBeCopied.getAuditTrails()
					&& (!mappingToBeCopied.getAuditTrails().isEmpty())) {
				AuditTrail auditTrail = (AuditTrail) mappingToBeCopied.getAuditTrails()
						.get(0);
				String changeComments = auditTrail.getUserComments();
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS,
						changeComments);
			}
			if(null != mappingToBeCopied && null != mappingToBeCopied.getEb03() && null != mappingToBeCopied.getEb03().getEb03Association()){
				eB03AssnList =  mappingToBeCopied.getEb03().getEb03Association();
			}
			
			modelAndView.addObject(WebConstants.MAPPING, mappingToBeCopied);
			modelAndView.addObject("eB03AssnList", eB03AssnList);

		}
		TokenGenerator.getInstance().saveToken(request);
		modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY,
				(String) session
						.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));

		return modelAndView;
	}

	/**
	 * View history of a mapping i.e audit trail
	 * 
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
	
		
		List mappings = locateRuleIdFacade.getRecords(mapping, null, null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		ModelAndView modelAndView = new ModelAndView("viewHistoryRule");
		if (null != mappings && !mappings.isEmpty()) {
			mapping = (Mapping) mappings.get(0);
			modelAndView.addObject(WebConstants.MAPPING, mapping);
			modelAndView.addObject("auditTrailList", mapping.getAuditTrails());
			if(mapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
				modelAndView.addObject("viewalllink", new Boolean(true));
			}
			
		}
		return modelAndView;
	}
	private MappingResult saveAndDone(HttpServletRequest request)throws EBXException, ParseException{		

		Mapping mapping = createMappingObject(request);
		
		existingEB03AssnList = (null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				? mapping.getEb03().getEb03Association() : new ArrayList<EB03Association>());
		
		String changeComments = request.getParameter(
				WebConstants.CHANGE_COMMENTS).toUpperCase();
		
		MappingResult result = mappingRuleIdFacade.update(mapping,
				changeComments);
		result.setMapping(mapping);
		if (null != result.getMapping().getSensitiveBenefitIndicator()
				&& result.getMapping().getSensitiveBenefitIndicator()
						.equals("Y")) {
			result.getMapping().setSensitiveBenefitIndicator(
					WebConstants.SENSITIVE_BNFT_INDICATOR_TRUE);
		} else {
			result.getMapping().setSensitiveBenefitIndicator(
					WebConstants.SENSITIVE_BNFT_INDICATOR_FALSE);
		}
		
		return result;
	}
	/**
	 * saving a mapping and remain in the edit screen
	 * 
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
		ModelAndView modelAndView = new ModelAndView(
				WebConstants.EDIT_RULE_MAPPING);
		List warningMessages = new ArrayList();
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		if (TokenGenerator.getInstance().isTokenValid(request, true)) {
			
			MappingResult result = saveAndDone(request);
			if(null != result.getMapping() && null != result.getMapping().getEb03() && null != result.getMapping().getEb03().getEb03Association()){
				eB03AssnList =  result.getMapping().getEb03().getEb03Association();
			}
		
			// mapping creation successful
			if (result.isStatus()) {
				List infoMessages = new ArrayList();
				if(null!= result.getWarningMsgsList()){					
					warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
				}

				
				infoMessages.add(BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_SAVE_SUCCESS, null));				
				modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
				modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
				
			} else {	
				List errorMessages = new ArrayList();
				eB03AssnList = existingEB03AssnList;
				if(null!= result.getErrorMsgsList()){
					
					errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				}
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);					
			}
			TokenGenerator.getInstance().saveToken(request);
			
			
			result.getMapping().setPageFrom(pageName);
			return modelAndView.addObject(WebConstants.MAPPING, result
					.getMapping())
					.addObject(WebConstants.CHANGE_COMMENTS,
							request.getParameter(
									WebConstants.CHANGE_COMMENTS).toUpperCase())
									.addObject("eB03AssnList", eB03AssnList)
									.addObject(
											WebConstants.TRANSACTION_TOKEN_KEY,
											(String) session
											.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));	
		}
		else {
			if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
				return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
			}else{
				return WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
	}

	/**
	 * saving and redirecting and landing page
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	public ModelAndView done(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		List infoMessages = new ArrayList();
		List warningMessages = new ArrayList();
		MappingResult result = saveAndDone(request);
		String pageName = request.getParameter("pageFrom");
		List<EB03Association> eB03AssnList =  new ArrayList<EB03Association>();
		if(null != result.getMapping() && null != result.getMapping().getEb03() && null != result.getMapping().getEb03().getEb03Association()){
			eB03AssnList =  result.getMapping().getEb03().getEb03Association();
		}
		
		// mapping creation successful
		if (result.isStatus()) {
			
			infoMessages.add(BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_SAVE_SUCCESS, null));
			sessionMessageTray.setInformationMessages(infoMessages);
			if(null!= result.getWarningMsgsList()){					
				warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
			}
			sessionMessageTray.setWarningMessages(warningMessages);
		} else {
			List errorMessages = new ArrayList();	
			eB03AssnList = existingEB03AssnList;
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			
			result.getMapping().setPageFrom(pageName);
			return new ModelAndView(WebConstants.EDIT_RULE_MAPPING)
			.addObject(WebConstants.MAPPING, result.getMapping())
			.addObject(WebConstants.CHANGE_COMMENTS, request.getParameter(
					WebConstants.CHANGE_COMMENTS).toUpperCase())
					.addObject(WebConstants.ERROR_MESSAGES, errorMessages)
					.addObject("eB03AssnList", eB03AssnList);
			
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	/**
	 * saving and scheduling to test a mapping
	 * 
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
		List warningMessages = new ArrayList();
		List<EB03Association> eB03AssnList;
		Mapping mapping = createMappingObject(request);
		
		eB03AssnList = (null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				? mapping.getEb03().getEb03Association() : new ArrayList<EB03Association>());
		
		String changeComments = request.getParameter(
				WebConstants.CHANGE_COMMENTS).toUpperCase();

		MappingResult result = mappingRuleIdFacade.updateAndSendToTest(mapping,
				changeComments);

		if (null != result.getMapping().getSensitiveBenefitIndicator()
				&& result.getMapping().getSensitiveBenefitIndicator().equals(
						"Y")) {
			result.getMapping().setSensitiveBenefitIndicator(
					WebConstants.SENSITIVE_BNFT_INDICATOR_TRUE);
		} else {
			result.getMapping().setSensitiveBenefitIndicator(
					WebConstants.SENSITIVE_BNFT_INDICATOR_FALSE);
		}
		//Added as part of SSCR 19537
		if(null != result.getMapping() && null != result.getMapping().getEb03() && null != result.getMapping().getEb03().getEb03Association()){
			eB03AssnList =  result.getMapping().getEb03().getEb03Association();
		}
		// mapping creation successful
		if (result.isStatus()) {
			infoMessages.add(BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_SEND_TO_TEST_SUCCESS, null));
			sessionMessageTray.setInformationMessages(infoMessages);
			if(null!= result.getWarningMsgsList()){					
				warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
			}
			sessionMessageTray.setWarningMessages(warningMessages);
		} else {
			List errorMessages = new ArrayList();
			
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			result.getMapping().setPageFrom(pageName);//This is for setting the page name in the jsp hidden field.
			return new ModelAndView(WebConstants.EDIT_RULE_MAPPING)
			.addObject(WebConstants.MAPPING, result.getMapping())
			.addObject("eB03AssnList", eB03AssnList)
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
	 * Saving and approving a mapping
	 * 
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
		List warningMessages = new ArrayList();
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		Mapping mapping = createMappingObject(request);
		
		eB03AssnList = (null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
				? mapping.getEb03().getEb03Association() : new ArrayList<EB03Association>());
		
		String changeComments = request.getParameter(
				WebConstants.CHANGE_COMMENTS).toUpperCase();
		
		MappingResult result = mappingRuleIdFacade.updateAndApprove(mapping,
				changeComments);
		
		if (null != result.getMapping().getSensitiveBenefitIndicator()
				&& result.getMapping().getSensitiveBenefitIndicator().equals(
				"Y")) {
			result.getMapping().setSensitiveBenefitIndicator(
					WebConstants.SENSITIVE_BNFT_INDICATOR_TRUE);
		} else {
			result.getMapping().setSensitiveBenefitIndicator(
					WebConstants.SENSITIVE_BNFT_INDICATOR_FALSE);
		}
		
		//Added as part of SSCR 19537
		if(null != result.getMapping() && null != result.getMapping().getEb03() && null != result.getMapping().getEb03().getEb03Association()){
			eB03AssnList =  result.getMapping().getEb03().getEb03Association();
		}
		
		// mapping creation successful
		if (result.isStatus()) {
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approve.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_APPROVE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
			if(null!= result.getWarningMsgsList()){					
				warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
			}
			sessionMessageTray.setWarningMessages(warningMessages);
		} else {
			List errorMessages = new ArrayList();
			
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			result.getMapping().setPageFrom(pageName);
			
			return new ModelAndView(WebConstants.EDIT_RULE_MAPPING)
			.addObject(WebConstants.MAPPING, result.getMapping())
			.addObject("eB03AssnList", eB03AssnList)
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
	 * Discard changes to a previously published mapping
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView discardChanges(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		
		String pageName = request.getParameter("pageFrom");
		
		Mapping mapping = new Mapping();
		Rule rule = new Rule();
		List infoMessages = new ArrayList();
		mapping.setRule(rule);
		mapping.getRule().setHeaderRuleId(
				request.getParameter("selectedruleId"));
		mapping.getRule().setRuleDesc(request.getParameter("ruleDesc"));
		
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		MappingResult result = mappingRuleIdFacade.discardChanges(mapping,
				request.getParameter(WebConstants.CHANGE_COMMENTS));
		
		if (result.isStatus()) {
			
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_DISCARD_CHANGES_SUCCESS, null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public ModelAndView editRuleMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		HttpSession session = request.getSession();
		String pageName = request.getParameter("pageName");
		
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("selectedruleForEdit"));
		mapping.setRule(rule);
		
		String userName = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userName);
		user.setCreatedUserName(userName);
		mapping.setUser(user);
		
		TokenGenerator.getInstance().saveToken(request);
		
		ModelAndView modelAndView = new ModelAndView(
				WebConstants.EDIT_RULE_MAPPING)		
				.addObject(WebConstants.TRANSACTION_TOKEN_KEY,
						(String) session
						.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
		
		//List mappings = locateRuleIdFacade.getRecords(mapping, null, null, WebConstants.TOTAL_NO_OF_RECORDS,
		//WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		MappingResult mappingResult = locateRuleIdFacade.getRecordsForUpdate(mapping, null, null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		
		//if (null != mappings && !mappings.isEmpty()) {
		//if (null != mappings.get(0)) {
		//	mapping = (Mapping) mappings.get(0);
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
			mapping.setPageFrom(pageName);
			
			String userId = request.getAttribute(
					SecurityConstants.USER_NAME).toString();
			user.setLastUpdatedUserName(userId);
			user.setCreatedUserName(userId);
			mapping.setUser(user);
			
			if (mapping.getVariableMappingStatus().equals(
					DomainConstants.STATUS_PUBLISHED)) {
				
				MappingResult result =  locateRuleIdFacade.getRecordsForUpdate(mapping);
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
			if (null != mapping.getSensitiveBenefitIndicator()
					&& mapping.getSensitiveBenefitIndicator().equals(
							WebConstants.SENSITIVE_BNFT_INDICATOR_Y)) {
				mapping
				.setSensitiveBenefitIndicator(WebConstants.SENSITIVE_BNFT_INDICATOR_TRUE);
			} else {
				mapping
				.setSensitiveBenefitIndicator(WebConstants.SENSITIVE_BNFT_INDICATOR_FALSE);
			}
			
			if(null != mapping && null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()){
				eB03AssnList =  mapping.getEb03().getEb03Association();
			/*	if(null == mapping.getIndvdlEb03AssnIndicator() || (null != mapping.getIndvdlEb03AssnIndicator() && 
						mapping.getIndvdlEb03AssnIndicator().equalsIgnoreCase("N"))){
					if(mapping.getEb03().getEb03Association().size() > 0){
						EB03Association eB03Association = (EB03Association)mapping.getEb03().getEb03Association().get(0);
						String iii02ToDisplay = eB03Association.getCommaSeparatedIII02StringWithDesc();
						HippaCodeValue iii02Val = (null != mapping.getIi02() && 
								 null != mapping.getIi02().getHippaCodeSelectedValues() && mapping.getIi02().getHippaCodeSelectedValues().size() > 0) ?
										  (HippaCodeValue) mapping.getIi02().getHippaCodeSelectedValues().get(0) : null;	 
										  iii02Val.setValue(iii02ToDisplay);
										  mapping.getIi02().getHippaCodeSelectedValues().add(0,iii02Val);
					
					}
				}*/
			}
			
			
			
			
			
			
			modelAndView.addObject(WebConstants.MAPPING, mapping);
			
			if (null != mapping.getAuditTrails()
					&& (!mapping.getAuditTrails().isEmpty())) {
				AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails().get(0);
				String changeComments = auditTrail.getUserComments();
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS,
						changeComments);
			}
		}
		//}
		//}

		modelAndView.addObject("eB03AssnList", eB03AssnList);
		return modelAndView;
	}
	public ModelAndView printRuleMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, ParseException {
		
		Mapping currentMapping = createMappingObject(request);
		List hippaCodeEB03List = new ArrayList();
		HippaCodeValue hippaCodeValue;
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		if (null != request.getParameter("eb03String")) {
			StringTokenizer st = new StringTokenizer(request.getParameter("eb03String"),"**");
			StringTokenizer st1 = new StringTokenizer(request.getParameter("eb03DescString"),"**");
		     while (st.hasMoreTokens()) {
		    	 String eb03Val =  st.nextToken();
		         hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(eb03Val);
					hippaCodeValue.setDescription(st1.nextToken());
					hippaCodeEB03List.add(hippaCodeValue);
		     }
		     hippaCodeEB03List = BxUtil.removeDuplicates(hippaCodeEB03List);
		     currentMapping.getEb03().setName(DomainConstants.EB03_NAME);
		     currentMapping.getEb03().setHippaCodeSelectedValues(hippaCodeEB03List);
		}
		if (null != request.getParameter("unruleString")) {
			List hippaCodeUMRuleList = new ArrayList();
			StringTokenizer st = new StringTokenizer(request.getParameter("unruleString"),"**");
			StringTokenizer st1 = new StringTokenizer(request.getParameter("umruleDescString"),"**");
		     while (st.hasMoreTokens()) {
		    	 String umruleVal =  st.nextToken();
		         hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(umruleVal);
					hippaCodeValue.setDescription(st1.nextToken());
					hippaCodeUMRuleList.add(hippaCodeValue);
		     }
		     hippaCodeUMRuleList = BxUtil.removeDuplicates(hippaCodeUMRuleList);
		     currentMapping.getUtilizationMgmntRule().setName(DomainConstants.UMRULE_NAME);
		     currentMapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(hippaCodeUMRuleList);
		}
		
		//Added as part of SSCR 19537
		Map<String,String> eb03ValDescMap = locateRuleIdFacade.fetchHippaSegmentDescription(DomainConstants.EB03_NAME);
		
		if (null != request.getParameter("individualMappingString")) {
			List<EB03Association> individualMappingList = new ArrayList<EB03Association>();
			StringTokenizer st = new StringTokenizer(request.getParameter("individualMappingString"),"**");
			//StringTokenizer st1 = new StringTokenizer(request.getParameter("umruleDescString"),"**");
		     while (st.hasMoreTokens()) {
		    	 HippaCodeValue hippaCodeVal = new HippaCodeValue();
		    	 String eb03ObjVal =  st.nextToken();
		    	 if(null != eb03ObjVal){
			    	 String[] eb03_III02Val = eb03ObjVal.split("_");
			         EB03Association eb03AssnObj = new EB03Association();
			         if(eb03_III02Val.length >= 1){
			        	 hippaCodeVal.setValue(null != eb03_III02Val[0] ? eb03_III02Val[0].trim() : "");
			        	 hippaCodeVal.setDescription(eb03ValDescMap.get(null != eb03_III02Val[0] ? eb03_III02Val[0].trim() : ""));
			        	 eb03AssnObj.setEb03(hippaCodeVal);
			         }
			         if(eb03_III02Val.length >= 2){
			        	 String commaSeparatedIII02StringWithDesc = (null != eb03_III02Val[1] ? eb03_III02Val[1].trim() : "" );
			        	 eb03AssnObj.setCommaSeparatedIII02StringWithDesc(commaSeparatedIII02StringWithDesc);
			         }
			         individualMappingList.add(eb03AssnObj);
		    	 }

		     }
		     if (null != request.getParameter("eb03String")){
		    	 currentMapping.getEb03().setEb03Association(individualMappingList);
		    	 eB03AssnList =  currentMapping.getEb03().getEb03Association();
		     }
		}
		
		
		String createPageFlag = request.getParameter("createPage");
		List mappings;
		if (null != createPageFlag && !createPageFlag.trim().equals("") && createPageFlag.equals("true")) {
			List statusUnmapped = new ArrayList();
			statusUnmapped.add(DomainConstants.UNMAPPED_STATUS);
			mappings = locateRuleIdFacade.getRecords(currentMapping, statusUnmapped, null, -1, 21, null);

		}
		else {
			mappings = locateRuleIdFacade.getRecords(currentMapping, null, null, -1, 21, null);

		}

		ModelAndView modelAndView = new ModelAndView("printRuleMapping");

		if (null != mappings && !mappings.isEmpty() && null != mappings.get(0)) {
			Mapping mapping = (Mapping) mappings.get(0);
			currentMapping.setRule(mapping.getRule());
			currentMapping.setAuditTrails(mapping.getAuditTrails());
			currentMapping.setProcedureExcludedInd(mapping.getProcedureExcludedInd());
			if (!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus())) {
				currentMapping.setVariableMappingStatus(mapping.getVariableMappingStatus());
			}
		}

		modelAndView.addObject("currentMapping", currentMapping);
		modelAndView.addObject("eB03AssnList", eB03AssnList);
		
		return modelAndView;
	}
	/**
	 * Method reads the sensitive benefits from errorvalidator.properties property file 
	 * and returns the sensitive benefits list
	 * BXNI June2012 Release
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView populateSensitiveBeenfitsList(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		List sensitiveBenefitsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SENSITIVE_EB03,
				DomainConstants.PROPERTY_FILE_NAME);
		map.put(DomainConstants.SENSITIVE, sensitiveBenefitsList);
		return new ModelAndView(DomainConstants.JSON_VIEW,map);
	}
	/**
	 * Set the mapping object by getting the values from the request
	 * 
	 * @param request
	 * @return
	 * @throws ParseException 
	 * @throws ParseException 
	 */
	private Mapping createMappingObject(HttpServletRequest request) throws ParseException{

		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("selectedruleId"));
		rule.setRuleDesc(request.getParameter("ruleDesc"));
		mapping.setRule(rule);
		mapping.setEb03(new HippaSegment());
		//Added as part of SSCR 19537
		if(null != request.getParameter("individualMappingForEB03ChkBox") 
				&& request.getParameter("individualMappingForEB03ChkBox").equalsIgnoreCase("on")){
			String jsonArray = request.getParameter("hdeb03AssnJSON1");
			List<EB03Association> eb03AssnList = createObjectFromJSON(jsonArray);
			mapping.setIndvdlEb03AssnIndicator("Y");
			//Adding EB03Assn object to the mapping object
			mapping.getEb03().setEb03Association(eb03AssnList);
			mapping.setIi02(null);
			
		}else{
			
			List<EB03Association> eb03AssnList = createEB03AssociationObject(request);
			mapping.setIndvdlEb03AssnIndicator("N");
			//Adding EB03Assn object to the mapping object
			mapping.getEb03().setEb03Association(eb03AssnList);
		}
		
		String createdDate = request.getParameter("createdDate");
		if(null != createdDate && !createdDate.isEmpty()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date convertedCurrentDate = sdf.parse(createdDate);
		    mapping.setCreatedTmStamp(convertedCurrentDate);
		}

		List hippaCodeEB03List = new ArrayList();
		HippaCodeValue hippaCodeValue;
		// set eb03 values
		if (null != request.getParameterValues(WebConstants.EB03_VAL)) {
			int sizeOfEB03 = request.getParameterValues(WebConstants.EB03_VAL).length;
			String[] eb03 = new String[sizeOfEB03];
			String[] eb03Desc = request.getParameterValues("EB03Desc");
			if (eb03Desc == null) {
				eb03Desc = new String[sizeOfEB03];
			}
			for (int i = 0; i < sizeOfEB03; i++) {

				if (!request.getParameterValues(WebConstants.EB03_VAL)[i]
						.trim().equals("")) {

					eb03[i] = request.getParameterValues(WebConstants.EB03_VAL)[i]
							.trim();
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(eb03[i]);
					hippaCodeValue.setDescription(eb03Desc[i]);
					hippaCodeEB03List.add(hippaCodeValue);
				}
			}
		}
		mapping.getEb03().setName(DomainConstants.EB03_NAME);
		mapping.getEb03().setHippaCodeSelectedValues(hippaCodeEB03List);

		mapping.setUtilizationMgmntRule(new HippaSegment());
		// HIPAA5010 CHANGES STARTS HERE
		List hippaCodeUMRuleList = new ArrayList();
	    if(null != request.getParameterValues("umRuleVal")){
              int sizeOfUmRule = request.getParameterValues("umRuleVal").length;
              String [] UMRule = new String[sizeOfUmRule];
              String [] UMRuleDesc = request.getParameterValues("UMRuleDesc");
              if (UMRuleDesc == null){
                    UMRuleDesc = new String[sizeOfUmRule];
              }
              for (int i = 0; i < sizeOfUmRule; i++){
                    UMRule[i] = request.getParameterValues("umRuleVal")[i].trim();
                    hippaCodeValue = new HippaCodeValue();
                    hippaCodeValue.setValue(UMRule[i]);
                    hippaCodeValue.setDescription(UMRuleDesc[i]);
                    hippaCodeUMRuleList.add(hippaCodeValue);
              }
        }
      
        hippaCodeUMRuleList = BxUtil.removeBlankfromHippaCodeValueList(hippaCodeUMRuleList);
        mapping.getUtilizationMgmntRule().setName(DomainConstants.UMRULE_NAME);
        mapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(hippaCodeUMRuleList);
      //HIPAA5010 CHANGES ENDS HERE
		
        
		// setting sensitive benefit
		if (null != request.getParameter("sensitiveBenefitChkBox")) {
			mapping
					.setSensitiveBenefitIndicator(WebConstants.SENSITIVE_BNFT_INDICATOR_Y);
		} else {
			mapping
					.setSensitiveBenefitIndicator(WebConstants.SENSITIVE_BNFT_INDICATOR_N);
		}
		// setting user
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setCreatedUserName(userId);
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);
		
		// BXNI CR29
		if (null != request.getParameter("excludeProceduresChkBox") && request.getParameter("excludeProceduresChkBox").equalsIgnoreCase("checked")) {
			mapping.setProcedureExcludedInd(DomainConstants.Y);
		} else {
			mapping.setProcedureExcludedInd(DomainConstants.N);
		}
		//Added as part of SCSR 19537
		if(null != request.getParameter("ii02Val")){
			String iii02Val = request.getParameter("ii02Val");
			String iii02Desc = request.getParameter("III02IdLabel");
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(iii02Val);
			hippaCodeValue.setDescription(iii02Desc);
			HippaSegment hippaSegment = new HippaSegment();
			List selectedIII02Values =  new ArrayList();
			selectedIII02Values.add(hippaCodeValue);
			hippaSegment.setName(DomainConstants.III02_NAME);
			hippaSegment.setHippaCodeSelectedValues(selectedIII02Values);
			mapping.setIi02(hippaSegment);
		}
		return mapping;
	}
	private void deleteLockDuringCancel(HttpServletRequest request) {
		String ruleId = request.getParameter("selectedruleId");
		Mapping mapping = new Mapping();
		List mappingList = new ArrayList();
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		if (null != ruleId && !DomainConstants.EMPTY.equals(ruleId.trim())) {
			Rule rule = new Rule();
			rule.setHeaderRuleId(ruleId);
			mapping.setRule(rule);
			mappingList = locateRuleIdFacade.getRecords(mapping, null, null, -1, 21, null);
		}
		if (null != mappingList && !mappingList.isEmpty()) {
			Mapping existingMapping = (Mapping)mappingList.get(0);
			if (null != existingMapping) {
				// Case 1 :  Delete the Lock when mapping status is "scheduled to test"
				//or "sheduled to production" or "object transferred". 
				if (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(existingMapping.getVariableMappingStatus()) 
						|| DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_NOT_APPLICABLE.equals(existingMapping.getVariableMappingStatus())) {
					mappingRuleIdFacade.unlock(mapping);
				}
				// Case 2 :  Delete the Lock when mapping status is "Building"
				//or "Being Modified" and the loggedin user is not equal to last updated user. 
				else if ((DomainConstants.STATUS_BEING_MODIFIED.equals(existingMapping.getVariableMappingStatus()) 
						|| DomainConstants.STATUS_BUILDING.equals(existingMapping.getVariableMappingStatus())) &&
						(!loggedInUser.equalsIgnoreCase(existingMapping.getUser().getLastUpdatedUserName()))) {
					mappingRuleIdFacade.unlock(mapping);
				}

			}
		}
		
	}
	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}

	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}

	public MappingFacade getMappingRuleIdFacade() {
		return mappingRuleIdFacade;
	}

	public void setMappingRuleIdFacade(MappingFacade mappingRuleIdFacade) {
		this.mappingRuleIdFacade = mappingRuleIdFacade;
	}

	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}

	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
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
			  		String iii02ValForDisplay = "";
		            for (int i = 0; i < hdeb03AssnJSONPanelArray.size(); i++)
		            {
			  			eb03AssnObject= new EB03Association();
		               //get the json object
		               final JSONObject jsonObject = hdeb03AssnJSONPanelArray.getJSONObject(i);
		               //get the first value from the json object in the order of which it is pushed
		               //remember – EB03, III02, III02Desc and entityPrivilege is the common name given while creating the
		               //  json object
		               final String eb03Val = jsonObject.getString("EB03");
		               List<HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>();
		               
		               if(null != eb03Val && !eb03Val.isEmpty()){
			               eb03AssnHippaCode = new HippaCodeValue();
			               eb03AssnHippaCode.setValue(eb03Val.trim());
			               eb03AssnObject.setEb03(eb03AssnHippaCode);
			               iii02ValForDisplay = "";
			               
			               //get the III02 value from the json object in the order of which it is pushed
			               final String iii02Val = jsonObject.getString("III02");
			               if(!"".equalsIgnoreCase(iii02Val)){
			            	   String[] iii02Array = iii02Val.split(",");
			            	   for(int j = 0; j < iii02Array.length ; j++){
			            		   String iii02ValwithDesc = iii02Array[j];
			            		   int indexOfBrackets = (null != iii02ValwithDesc ?  iii02ValwithDesc.indexOf("(") : -1);
			            		   if(indexOfBrackets != -1){
			            			   String updatedIii02Val = iii02ValwithDesc.substring(0, indexOfBrackets);
			            			   String iii02Desc = iii02ValwithDesc.substring(indexOfBrackets, iii02ValwithDesc.length());
			            			   eb03AssnHippaCode = new HippaCodeValue();
			            			   eb03AssnHippaCode.setValue(null != updatedIii02Val ? updatedIii02Val.trim() : "");
						               eb03AssnHippaCode.setDescription(null != iii02Desc ? iii02Desc.trim() : "");
						               iii02List.add(eb03AssnHippaCode);
						               iii02ValForDisplay = ("".equalsIgnoreCase(iii02ValForDisplay) ? updatedIii02Val : 
						            	   iii02ValForDisplay +","+updatedIii02Val);
			            		   }
			            	   }
			            	   eb03AssnObject.setCommaSeparatedIII02StringWithDesc(iii02Val);  
			               }
			               eb03AssnObject.setIii02List(iii02List);
			               eb03AssnObject.setCommaSeparatedIII02String(iii02ValForDisplay);
			               eb03AssnList.add(eb03AssnObject);
			               
		            }

		            }
		      }


		  } catch (final JSONException jsEx)
		      {
			  		log.error("Json Error:");
		            jsEx.printStackTrace();
		            
		      }
	return eb03AssnList;	  
	}

	
	
	private List<EB03Association> createEB03AssociationObject(
			HttpServletRequest request) {

		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		try {
			String iii02Val = request.getParameter("ii02Val");
			EB03Association eb03AssnObject;
			HippaCodeValue eb03AssnHippaCode;
			Set<String> individualMappingSet = new HashSet<String>();

			// check the length of the json array
			// if (null != iii02Val && !iii02Val.equalsIgnoreCase("")) {
			eb03AssnObject = new EB03Association();

			if (null != request.getParameterValues(WebConstants.EB03_VAL)) {
				int sizeOfEB03 = request
						.getParameterValues(WebConstants.EB03_VAL).length;
				String[] eb03 = new String[sizeOfEB03];
				String[] eb03Desc = request.getParameterValues("EB03Desc");
				if (eb03Desc == null) {
					eb03Desc = new String[sizeOfEB03];
				}
				for (int i = 0; i < sizeOfEB03; i++) {

					if (!request.getParameterValues(WebConstants.EB03_VAL)[i]
							.trim().equals("")) {

						eb03[i] = request
								.getParameterValues(WebConstants.EB03_VAL)[i]
								.trim();
						HippaCodeValue hippaCodeValue = new HippaCodeValue();
						hippaCodeValue.setValue(eb03[i]);
						hippaCodeValue.setDescription(eb03Desc[i]);
						eb03AssnObject = new EB03Association();
						eb03AssnObject.setEb03(hippaCodeValue);

						String iii02ValForDisplay = "";
						if (null != iii02Val && !iii02Val.equalsIgnoreCase("")) {
							hippaCodeValue = new HippaCodeValue();
							hippaCodeValue.setValue(iii02Val.trim());
							hippaCodeValue.setDescription(request
									.getParameter("III02IdLabel"));
							List<HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>();
							iii02List.add(hippaCodeValue);
							eb03AssnObject.setIii02List(iii02List);

							String[] iii02Array = iii02Val.split(",");
							for (int j = 0; j < iii02Array.length; j++) {
								String iii02ValwithDesc = iii02Array[j];
								int indexOfBrackets = (null != iii02ValwithDesc ? iii02ValwithDesc
										.indexOf("(")
										: -1);
								if (indexOfBrackets != -1) {
									String updatedIii02Val = iii02ValwithDesc
											.substring(0, indexOfBrackets);
									String iii02Desc = iii02ValwithDesc
											.substring(indexOfBrackets,
													iii02ValwithDesc.length());
									iii02ValForDisplay = (""
											.equalsIgnoreCase(iii02ValForDisplay) ? updatedIii02Val.trim()
											: iii02ValForDisplay.trim() + ","
													+ updatedIii02Val.trim());
								}
							}
						}
						eb03AssnObject
								.setCommaSeparatedIII02StringWithDesc(iii02Val);
						eb03AssnObject
								.setCommaSeparatedIII02String(iii02ValForDisplay);

						eb03AssnList.add(eb03AssnObject);
					}
				}
			}

			// }

		} catch (final JSONException jsEx) {
			log.error("Json Error:");
			jsEx.printStackTrace();

		}
		return eb03AssnList;
	}
	
	public ModelAndView populateEb03ValuesToEB03AssnList(HttpServletRequest request,
			HttpServletResponse response){
		String associatedEB03Mapping = request.getParameter("associatedEB03Mapping");
		String newEb03ListFromPage = request.getParameter("newEB03List");
		String[] newEb03Array = (newEb03ListFromPage.split("`"));
		Set<String> newEB03List = new HashSet<String>();
		Collections.addAll(newEB03List, newEb03Array);
		//newEB03List.remove("");
		
		
		List<EB03Association> elementsToBeRemovedFromExistingList = new ArrayList<EB03Association>();
		List<String> elementsToBeRemovedFromNewList = new ArrayList<String>();
		List<EB03Association> eb03AssnList = createObjectFromJSON(associatedEB03Mapping);
		Iterator eb03AssnListIterator = eb03AssnList.iterator();
		while(eb03AssnListIterator.hasNext()){
			EB03Association eb03AssnObj = (EB03Association) eb03AssnListIterator.next();
			if(null != eb03AssnObj && null != eb03AssnObj.getEb03() &&
					null != eb03AssnObj.getEb03().getValue() ){
				String eb03Val = eb03AssnObj.getEb03().getValue().trim();
				if(null != newEB03List && newEB03List.size() > 0){
					if(!newEB03List.contains(null != eb03Val ? eb03Val.trim() : "")){
						elementsToBeRemovedFromExistingList.add(eb03AssnObj);
					}else{
						elementsToBeRemovedFromNewList.add(eb03Val.trim());
					}
				}else if(newEB03List.size() == 0){
					eb03AssnList.clear();
				}
			}
		}
		
		for(EB03Association eb03Assn : elementsToBeRemovedFromExistingList){
			if(null != eb03Assn){
				eb03AssnList.remove(eb03Assn);
			}
		}
		
		for(String eb03Val : elementsToBeRemovedFromNewList){
			if(null != eb03Val){
				newEB03List.remove(eb03Val);
			}
		}
		
		for(String eb03Val: newEB03List){
			if(null != eb03Val && !eb03Val.trim().equalsIgnoreCase("")){
				HippaCodeValue hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(eb03Val.trim());
				EB03Association newEb03AssnObj = new EB03Association();
				newEb03AssnObj.setEb03(hippaCodeValue);
				newEb03AssnObj.setCommaSeparatedIII02StringWithDesc("");
				newEb03AssnObj.setCommaSeparatedIII02String("");
				eb03AssnList.add(newEb03AssnObj);
			}
		}
		//Map<String,List<EB03Association>> map = new HashMap<String,List<EB03Association>>();
		JSONArray eb03AssnObjAsJsonArray = JSONArray.fromObject(eb03AssnList);
		return new ModelAndView("jsonView").addObject("eb03AssnObjAsJsonArray", eb03AssnObjAsJsonArray);
	}

}
