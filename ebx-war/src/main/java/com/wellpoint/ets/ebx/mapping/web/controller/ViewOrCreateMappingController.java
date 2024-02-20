/*
 * <ViewCreateRuleMappingPageController.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */

package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;

/**
 * @author UST-GLOBAL This is a controller class for rendering the create
 *         mapping page of sps id, rule id and custom message
 */
public class ViewOrCreateMappingController extends MultiActionController {

	private LocateFacade locateRuleIdFacade;

	private LocateFacade locateSpsIdFacade;

	private LocateFacade locateCustomMessageFacade;

	private ModelAndView ruleIdCreate(Mapping mapping,
			HttpServletRequest request) throws EBXException {
		
		String pageName = request.getParameter("pageName");

		ModelAndView viewLanding = new ModelAndView("viewlandingewpdbx");
		List errorMessages = new ArrayList();
		HttpSession session = request.getSession();
		List paramList = new ArrayList();
		Rule rule = mapping.getRule();

		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();

		List status = new ArrayList();
		status.add(DomainConstants.MAPPED_STATUS);
		status.add(DomainConstants.UNMAPPED_STATUS);
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();

		List mappings = locateRuleIdFacade.getRecords(mapping, status, null,
				WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

		// if rule id not in mapped and unmapped tables, invalid else get status
		// if status is not null, mapping exists else go to the create rule
		// mapping page
		if (null != mappings && !mappings.isEmpty()) {
			if (null != mappings.get(0)) {

				mapping = (Mapping) mappings.get(0);
				mapping.setPageFrom(pageName);

				if (null == mapping.getVariableMappingStatus()
						|| (mapping.getVariableMappingStatus()
								.equals(DomainConstants.UNMAPPED_STATUS))) {

					ModelAndView modelAndView = new ModelAndView(
							"createrulemapping");
					modelAndView.addObject("mapping", mapping);
					modelAndView.addObject("eB03AssnList", null != mapping.getEb03() && null !=  mapping.getEb03().getEb03Association() ?
							mapping.getEb03().getEb03Association() : eB03AssnList);
					TokenGenerator.getInstance().saveToken(request);
					modelAndView
							.addObject(
									WebConstants.TRANSACTION_TOKEN_KEY,
									(String) session
											.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
					return modelAndView;
				} else {

					paramList.add(rule.getHeaderRuleId());
					String ruleMappingExists = BxResourceBundle
							.getResourceBundle(
									WebConstants.MAPPING_RULEID_EXISTS,
									paramList);

					errorMessages.add(ruleMappingExists);
					viewLanding.addObject(WebConstants.ERROR_MESSAGES,
							errorMessages);
					viewLanding = redirectToLandingPage(viewLanding,
							loggedInUser);
				}
			}
		} else {
			viewLanding = redirectToLandingPage(viewLanding, loggedInUser);
			paramList.add(rule.getHeaderRuleId());
			String ruleInvalid = BxResourceBundle.getResourceBundle(
					WebConstants.INVALID_RULE_ID, paramList);
			errorMessages.add(ruleInvalid);
			viewLanding.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return viewLanding;
	}

	/**
	 * Loading the create rule page with the header rule selected from the
	 * autocomplete
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView ruleIdCreateFromUnmapped(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		Mapping mapping = new Mapping();
		Rule rule = new Rule();
		String ruleId = null;
		if(null != request.getParameter("ruleIdHidden") && 
				(!"".equals(request.getParameter("ruleIdHidden").trim()))){
			
			ruleId = request.getParameter("ruleIdHidden").trim();
		}
		rule.setHeaderRuleId(ruleId);
		mapping.setRule(rule);

		return ruleIdCreate(mapping, request);
	}

	public ModelAndView ruleIdCreateUsingAutocomplete(
			HttpServletRequest request, HttpServletResponse response)
			throws EBXException {

		Mapping mapping = new Mapping();
		Rule rule = new Rule();
		String ruleId = null;
		if(null != request.getParameter("selectedruleId") && 
				(!"".equals(request.getParameter("selectedruleId").trim()))){
			
			ruleId = request.getParameter("selectedruleId").trim();
		}
		rule.setHeaderRuleId(ruleId);
		mapping.setRule(rule);

		return ruleIdCreate(mapping, request);
	}

	private ModelAndView spsIdCreate(Mapping mapping, HttpServletRequest request)
			throws EBXException {
		
		String pageName = request.getParameter("pageName");

		ModelAndView viewLanding = new ModelAndView("viewlandingewpdbx");
		HttpSession session = request.getSession();
		List errorMessages = new ArrayList();
		List paramList = new ArrayList();
		List status = new ArrayList();

		SPSId spsId = mapping.getSpsId();

		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		status.add(DomainConstants.MAPPED_STATUS);
		status.add(DomainConstants.UNMAPPED_STATUS);
		List mappings = locateSpsIdFacade.getRecords(mapping, status, null,
				WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		// if rule id not in mapped and unmapped tables, invalid else get status
		// if status is not null, mapping exists else go to the create sps
		// mapping page
		if (null != mappings && !mappings.isEmpty()) {
			if (null != mappings.get(0)) {

				mapping = (Mapping) mappings.get(0);
				mapping.setPageFrom(pageName);

				if ((null == mapping.getVariableMappingStatus())
						|| (mapping.getVariableMappingStatus()
								.equals(DomainConstants.UNMAPPED_STATUS))) {
					ModelAndView modelAndView = new ModelAndView("createspsmapping");
					HippaSegment hippaSegment = new HippaSegment();
					hippaSegment.setName("EB09");
				    locateSpsIdFacade.autopopulateBySPSFormat(hippaSegment, mapping);
				    modelAndView.addObject("mapping", mapping);
				    TokenGenerator.getInstance().saveToken(request);
					modelAndView
							.addObject(
									WebConstants.TRANSACTION_TOKEN_KEY,
									(String) session
											.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
					return modelAndView;
				} else {
					paramList.add(spsId.getSpsId());
					String ruleMappingExists = BxResourceBundle
							.getResourceBundle(
									WebConstants.MAPPING_SPSID_EXISTS,
									paramList);

					errorMessages.add(ruleMappingExists);
					viewLanding.addObject(WebConstants.ERROR_MESSAGES,
							errorMessages);
					viewLanding = redirectToLandingPage(viewLanding,
							loggedInUser);
				}
			}
		} else {
			viewLanding = redirectToLandingPage(viewLanding, loggedInUser);
			paramList.add(spsId.getSpsId());
			String ruleInvalid = BxResourceBundle.getResourceBundle(
					WebConstants.INVALID_SPS_ID, paramList);
			errorMessages.add(ruleInvalid);
			viewLanding.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return viewLanding;
	}

	public ModelAndView spsIdCreateFromUnmapped(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		Mapping mapping = new Mapping();
		SPSId spsId = new SPSId();
		String sps = null;
		if(null != request.getParameter("spsIdHidden") && 
				(!"".equals(request.getParameter("spsIdHidden").trim()))){
			
			sps = request.getParameter("spsIdHidden").trim();
		}
		spsId.setSpsId(sps);
		mapping.setSpsId(spsId);

		return spsIdCreate(mapping, request);

	}

	public ModelAndView spsIdCreateUsingAutocomplete(
			HttpServletRequest request, HttpServletResponse response)
			throws EBXException {

		Mapping mapping = new Mapping();
		SPSId spsId = new SPSId();
		String sps = null;
		if(null != request.getParameter("selectedSpsId") && 
				(!"".equals(request.getParameter("selectedSpsId").trim()))){
			
			sps = request.getParameter("selectedSpsId").trim();
		}
		spsId.setSpsId(sps);
		mapping.setSpsId(spsId);

		return spsIdCreate(mapping, request);

	}

	public ModelAndView viewCustomMsgCreate(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		ModelAndView viewLanding = new ModelAndView("viewlandingewpdbx");
		HttpSession session = request.getSession();
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		SPSId spsId = new SPSId();
		String sps = null;
		if (null != request.getParameter("selectedSpsId")
				&& (!"".equals(request.getParameter("selectedSpsId").trim()))) {

			sps = request.getParameter("selectedSpsId").trim();
		}
		spsId.setSpsId(sps);

		List paramList = new ArrayList();
		Rule rule = new Rule();
		String ruleId = null;
		if (null != request.getParameter("selectedruleId")
				&& (!"".equals(request.getParameter("selectedruleId").trim()))) {

			ruleId = request.getParameter("selectedruleId").trim();
		}
		rule.setHeaderRuleId(ruleId);

		mapping.setSpsId(spsId);
		mapping.setRule(rule);
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();

		List status = new ArrayList();
		status.add(DomainConstants.MAPPED_STATUS);
		status.add(DomainConstants.UNMAPPED_STATUS);
		List ruleMappings = locateRuleIdFacade.getRecords(mapping, status,
				null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		// check whether rule entered is valid
		if (null == ruleMappings || ruleMappings.isEmpty()) {
			viewLanding = redirectToLandingPage(viewLanding, loggedInUser);
			paramList.add(ruleId);
			String ruleInvalid = BxResourceBundle.getResourceBundle(
					WebConstants.UNMAPPED_RULE, paramList);
			errorMessages.add(ruleInvalid);
			viewLanding.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			return viewLanding;
		}else {

			Mapping mappedRule = (Mapping) ruleMappings.get(0);
			mapping.getRule().setRuleDesc(mappedRule.getRule().getRuleDesc());
			mapping.setInTempTable(mappedRule.getInTempTable());
			mapping.setEb03(null != mappedRule.getEb03() ? mappedRule.getEb03() : null );
			
			if(null != mappedRule.getVariableMappingStatus() && 
					mappedRule.getVariableMappingStatus().equalsIgnoreCase(DomainConstants.UNMAPPED_STATUS)){
				viewLanding = redirectToLandingPage(viewLanding, loggedInUser);
				paramList.add(mappedRule.getRule().getHeaderRuleId());
				String spsInvalid = BxResourceBundle.getResourceBundle(
						WebConstants.UNMAPPED_RULE, paramList);
				errorMessages.add(spsInvalid);
				viewLanding.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
				return viewLanding;
			}
			
			
			
		}
		List spsMappings = locateSpsIdFacade.getRecords(mapping, status, null,
				WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		// check whether the sps id is valid
		if (null == spsMappings || spsMappings.isEmpty()) {
			viewLanding = redirectToLandingPage(viewLanding, loggedInUser);
			paramList.add(spsId.getSpsId());
			String spsInvalid = BxResourceBundle.getResourceBundle(
					WebConstants.INVALID_SPS_ID, paramList);
			errorMessages.add(spsInvalid);
			viewLanding.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			return viewLanding;
		}
		// set sps desc to mapping object
		else {

			Mapping mappedSps = (Mapping) spsMappings.get(0);
			mapping.getSpsId().setSpsDesc(mappedSps.getSpsId().getSpsDesc());
		}
		status.clear();
		status.add(DomainConstants.VIEW_STATUS);
		status.add(DomainConstants.UNMAPPED_STATUS);
		// locate custom message by
		List mappings = locateCustomMessageFacade.getRecords(mapping, status,
				null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);


		// Added as part of SSCR 19537
		
		status.clear();
		status.add(DomainConstants.CUSTOM_MESSAGE_CREATE);
		List<Mapping> mappingForCustomMessageList = locateCustomMessageFacade.getRecords(mapping, status,
				null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
			
		if(null == mappingForCustomMessageList){
			ModelAndView modelAndView = new ModelAndView("createcustommsgmapping");
			modelAndView.addObject("mapping", mapping);
			TokenGenerator.getInstance().saveToken(request);
			modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY,
					(String) session
							.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
			return modelAndView;
		} else {
			viewLanding = redirectToLandingPage(viewLanding, loggedInUser);
			paramList.add(spsId.getSpsId());
			paramList.add(rule.getHeaderRuleId());
			String msgInvalid = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_CUSTOM_MSG_EXISTS, paramList);
			errorMessages.add(msgInvalid);
			viewLanding.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			return viewLanding;
		}
	}
	

	public ModelAndView viewMappingSps(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		Mapping currentMapping = new Mapping();
		SPSId spsId = new SPSId();
		spsId.setSpsId(request.getParameter("spsId"));
		currentMapping.setSpsId(spsId);

		String section = request.getParameter("section");
		List status = new ArrayList();

		if (null != section
				&& (section.equals(DomainConstants.UNMAPPED_STATUS))) {

			status.add(DomainConstants.UNMAPPED_STATUS);
		}

		List mappings = locateSpsIdFacade.getRecords(currentMapping, status, null,
				WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

		ModelAndView modelAndView = new ModelAndView("mappingviewsps");
		if (null != mappings && !mappings.isEmpty()) {
			currentMapping = (Mapping) mappings.get(0);
	
			modelAndView.addObject("currentMapping", currentMapping);
			
			if(null != currentMapping && null != currentMapping.getAuditTrails() 
					&& currentMapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
				modelAndView.addObject("viewalllink", new Boolean(true));
			}
		}

		return modelAndView;
	}

	public ModelAndView viewMappingRule(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		Mapping currentMapping = new Mapping();
		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		currentMapping.setRule(rule);

		String section = request.getParameter("section");
		List status = new ArrayList();

		if (null != section
				&& (section.equals(DomainConstants.UNMAPPED_STATUS))) {
			status.add(DomainConstants.UNMAPPED_STATUS);
		} else {
			status.add(DomainConstants.MAPPED_STATUS);
			status.add(DomainConstants.STATUS_NOT_APPLICABLE);
		}

		List mappings = locateRuleIdFacade.getRecords(currentMapping, status,
				null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

		ModelAndView modelAndView = new ModelAndView("mappingviewrule");

		currentMapping = (Mapping) mappings.get(0);
		/*String displayIii02 = "";
		if(null != currentMapping && null != currentMapping.getEb03() && 
				null != currentMapping.getEb03().getEb03Association() && 
				!currentMapping.getEb03().getEb03Association().isEmpty() ){
			EB03Association eb03AssnObj =  currentMapping.getEb03().getEb03Association().get(0);
			List<HippaCodeValue> iii02List = eb03AssnObj.getIii02List();
			if(null != iii02List && !iii02List.isEmpty()){
				for(HippaCodeValue iii02Code : iii02List){
					String iii02Val = null != iii02Code && null != iii02Code.getValue() ? iii02Code.getValue() : "";
					displayIii02 = "".equalsIgnoreCase(displayIii02) ? iii02Val : ", "+iii02Val;
				}
				eb03AssnObj.setCommaSeparatedIII02String(displayIii02);
				currentMapping.getEb03().getEb03Association().add(0, eb03AssnObj);
			}
			
		}*/

		modelAndView.addObject("currentMapping", currentMapping);
		modelAndView.addObject("eb03Associations", currentMapping.getEb03().getEb03Association());
		
		if(null != currentMapping && null != currentMapping.getAuditTrails() && 
				currentMapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
			modelAndView.addObject("viewalllink", new Boolean(true));
		}

		return modelAndView;
	}

	public ModelAndView viewMappingCustomMsg(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		Mapping currentMapping = new Mapping();
		SPSId spsId = new SPSId();
		spsId.setSpsId(request.getParameter("spsId"));
		currentMapping.setSpsId(spsId);

		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		currentMapping.setRule(rule);

		String section = request.getParameter("section");
		List status = new ArrayList();
		List mappings = new ArrayList();

		if (null != section && (section.equals(DomainConstants.VIEW_STATUS))) {

			status.add(DomainConstants.VIEW_STATUS_FOR_CUSTOM_MESSAGE);
			mappings = locateCustomMessageFacade.getRecords(currentMapping,
					status, null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		}

		ModelAndView modelAndView = new ModelAndView("mappingviewcustommessage");

		currentMapping = (Mapping) mappings.get(0);

		modelAndView.addObject("currentMapping", currentMapping);
		
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		if(null != currentMapping && null != currentMapping.getEb03() && null != currentMapping.getEb03().getEb03Association()){
			eB03AssnList = currentMapping.getEb03().getEb03Association();
		}
		
		modelAndView.addObject("eB03AssnList", eB03AssnList);
		
		if(null != currentMapping && null != currentMapping.getAuditTrails() && 
				currentMapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
			modelAndView.addObject("viewalllink", new Boolean(true));
		}

		return modelAndView;
	}
	public ModelAndView printRuleMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		Mapping currentMapping = new Mapping();
		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		String section = request.getParameter("status");
		currentMapping.setRule(rule);

		List status = new ArrayList();

		if (null != section
				&& (section.equals(DomainConstants.UNMAPPED_STATUS))) {
			status.add(DomainConstants.UNMAPPED_STATUS);
		} else {
			status.add(DomainConstants.MAPPED_STATUS);
			status.add(DomainConstants.STATUS_NOT_APPLICABLE);
		}
		List mappings = locateRuleIdFacade.getRecords(currentMapping, status,
				null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

		ModelAndView modelAndView = new ModelAndView("printRuleMapping");

		currentMapping = (Mapping) mappings.get(0);

		modelAndView.addObject("currentMapping", currentMapping);
		modelAndView.addObject("eB03AssnList", currentMapping.getEb03().getEb03Association());
		return modelAndView;
	}
	public ModelAndView printCustomMessageMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		Mapping currentMapping = new Mapping();
		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		currentMapping.setRule(rule);
		SPSId sps=new SPSId();
		sps.setSpsId(request.getParameter("spsId"));
		currentMapping.setSpsId(sps);
		List status = new ArrayList();
		status.add(DomainConstants.VIEW_STATUS_FOR_CUSTOM_MESSAGE);
		List mappings = locateCustomMessageFacade.getRecords(currentMapping, status,
				null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

		ModelAndView modelAndView = new ModelAndView("printCustomMessageMapping");

		currentMapping = (Mapping) mappings.get(0);

		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		
		if(null != currentMapping && null != currentMapping.getEb03() && null != currentMapping.getEb03().getEb03Association()){
			eB03AssnList = currentMapping.getEb03().getEb03Association();
		}
		
		modelAndView.addObject("currentMapping", currentMapping);
		modelAndView.addObject("eB03AssnList", eB03AssnList);
		
		return modelAndView;
	}
	public ModelAndView printSPSMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		Mapping currentMapping = new Mapping();
		SPSId sps=new SPSId();
		sps.setSpsId(request.getParameter("spsId"));
		currentMapping.setSpsId(sps);
		List mappings;
		if (DomainConstants.UNMAPPED_STATUS.equals(request.getParameter("status"))) {
			List status = new ArrayList();
			status.add(DomainConstants.UNMAPPED_STATUS);
			mappings = locateSpsIdFacade.getRecords(currentMapping, status,
					null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		}
		else {
			mappings = locateSpsIdFacade.getRecords(currentMapping, null,
					null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		}

		ModelAndView modelAndView = new ModelAndView("printSPSMapping");

		currentMapping = (Mapping) mappings.get(0);

		modelAndView.addObject("currentMapping", currentMapping);
		
		return modelAndView;
	}
	public ModelAndView redirectToLandingPage(ModelAndView modelAndView,
			String loggedInUser) {
		List statusUnmapped = new ArrayList();
		statusUnmapped.add(DomainConstants.UNMAPPED_STATUS);

		List statusMapped = new ArrayList();
		statusMapped.add("IN-PROGRESS");

		List unmappedRule = locateRuleIdFacade.getRecords(null, statusUnmapped,
				null, WebConstants.LANDING_PAGE_TOTAL_COUNT,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		//unmappedRule = BxUtil.concatBreak(unmappedRule);
		if (null != unmappedRule && !unmappedRule.isEmpty()) {
			Collections.sort(unmappedRule);
		}

		List unmappedSps = locateSpsIdFacade.getRecords(null, statusUnmapped,
				null, WebConstants.LANDING_PAGE_TOTAL_COUNT,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		//unmappedSps = BxUtil.concatBreak(unmappedSps);
		if (null != unmappedSps && !unmappedSps.isEmpty()) {
			Collections.sort(unmappedSps);
		}

		List inprogressRule = locateRuleIdFacade.getRecords(null, statusMapped,
				loggedInUser, WebConstants.LANDING_PAGE_TOTAL_COUNT,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		//inprogressRule = BxUtil.concatBreak(inprogressRule);		

		List inprogressSps = locateSpsIdFacade.getRecords(null, statusMapped,
				loggedInUser, WebConstants.LANDING_PAGE_TOTAL_COUNT,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		//inprogressSps = BxUtil.concatBreak(inprogressSps);
		if (null != inprogressSps && !inprogressSps.isEmpty()) {
			Collections.sort(inprogressSps);
		}

		List inprogressCustomMsg = locateCustomMessageFacade.getRecords(null,
				statusMapped, loggedInUser, 50,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		//inprogressCustomMsg = BxUtil.concatBreak(inprogressCustomMsg);
		if (null != inprogressCustomMsg && !inprogressCustomMsg.isEmpty()) {
			Collections.sort(inprogressCustomMsg);
		}

		modelAndView.addObject("unmappedRule", unmappedRule).addObject(
				"unmappedSps", unmappedSps).addObject("inprogressRule",
				inprogressRule).addObject("inprogressSps", inprogressSps)
				.addObject("inprogressCustomMsg", inprogressCustomMsg);

		return modelAndView;
	}

	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}

	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}

	public LocateFacade getLocateSpsIdFacade() {
		return locateSpsIdFacade;
	}

	public void setLocateSpsIdFacade(LocateFacade locateSpsIdFacade) {
		this.locateSpsIdFacade = locateSpsIdFacade;
	}

	public LocateFacade getLocateCustomMessageFacade() {
		return locateCustomMessageFacade;
	}

	public void setLocateCustomMessageFacade(
			LocateFacade locateCustomMessageFacade) {
		this.locateCustomMessageFacade = locateCustomMessageFacade;
	}

}
