/*
 * <LocateEbxResultsController.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.mapping.web.view.ExcelLocateView;

/**
 * @author UST-GLOBAL
 * This is a controller class for locating a rule and SPS.
 */
public class LocateEbxResultsController extends MultiActionController {

	private LocateFacade locateSpsIdFacade;
	private LocateFacade locateRuleIdFacade;
	private LocateFacade locateCustomMessageFacade;

	private static Logger log = Logger.getLogger(LocateEbxResultsController.class);

	
	public ModelAndView locateRequest(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = null;
		boolean isSpsIdLocate = false;
		boolean isRuleIdLocate = false;
		boolean isCustomMessageLocate = false;
		String selectedSpsId = "selectedSpsId";
		String selectedRuleId = "selectedRuleId";
		String section = "section";

		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		
		if (request.getParameter("isCustomMessage").equalsIgnoreCase("isCustomMessage")) {
			isCustomMessageLocate = true;
		} 
		else {
			if (request.getParameter(selectedSpsId) !=null 
					&& !request.getParameter(selectedSpsId).trim().equals("")) {
				isSpsIdLocate = true;
			}
			if (request.getParameter(selectedRuleId) !=null 
					&& !request.getParameter(selectedRuleId).trim().equals("")) {
				isRuleIdLocate = true;
			}
		}
		
		Mapping mapping = new Mapping();
		
		mapping.setSpsId(new SPSId());
		mapping.setRule(new Rule());
		
		
		List status = new ArrayList();

		Variable variable = new Variable();
		variable.setVariableId(request.getParameter("variableId"));
		variable.setDescription(request.getParameter("variableDesc"));
		
		if (request.getParameter("mappedStatus").equalsIgnoreCase("isMapped")) {
			variable.setMappedVariable(true);
			status.add(DomainConstants.MAPPED_STATUS);
		} 
		if (request.getParameter("unMappedStatus").equalsIgnoreCase("unMapped")) {
			variable.setUnmappedVariable(true);
			status.add(DomainConstants.UNMAPPED_STATUS);
		} 
		if (request.getParameter("notAppStatus").equalsIgnoreCase(
				"isNotApplicable")) {
			status.add(DomainConstants.STATUS_NOT_APPLICABLE);
		} 
		if (status.isEmpty()) {
			status.add(DomainConstants.MAPPED_STATUS);
			status.add(DomainConstants.UNMAPPED_STATUS);
			status.add(DomainConstants.STATUS_NOT_APPLICABLE);
		}
		
		Page page = new Page();
		
		page.setCurrentPage(Integer.parseInt(request
				.getParameter("currentPage")));
		
		if (isSpsIdLocate) {
			mapping.getSpsId().setSpsId(request.getParameter(selectedSpsId).trim() + "%");
			page.setTotalNoOfRecords(locateSpsIdFacade
					.getRecordCount(mapping,status));		
	
		}
		if (isRuleIdLocate) {
			mapping.getRule().setHeaderRuleId(request.getParameter(selectedRuleId).trim() + "%");
			page.setTotalNoOfRecords(locateRuleIdFacade
					.getRecordCount(mapping,status));		
		}
		if (isCustomMessageLocate) {
			if (null != request.getParameter(selectedSpsId)) {
				mapping.getSpsId().setSpsId(request.getParameter(selectedSpsId).trim());
			}
			if (null != request.getParameter(selectedRuleId)) {
				mapping.getRule().setHeaderRuleId(request.getParameter(selectedRuleId).trim());
			}
			page.setTotalNoOfRecords(locateCustomMessageFacade
					.getRecordCount(mapping,status)); 
		}

		if (request.getParameter(section).equalsIgnoreCase("Next")
				|| request.getParameter(section).equalsIgnoreCase(
						"fromLanding")) {
			if (page.getCurrentPage() >= 0
					&& page.getCurrentPage() < page.getTotalNoOfPages()) {
				page.setCurrentPage(page.getCurrentPage() + 1);
			}
		}

		if (request.getParameter(section).equalsIgnoreCase("Previous")) {
			if (page.getCurrentPage() > 1) {
				page.setCurrentPage(page.getCurrentPage() - 1);
			}
		}
		if (request.getParameter(section).equalsIgnoreCase("Last")) {
			page.setCurrentPage(page.getTotalNoOfPages()); 
		}
		if (request.getParameter(section).equalsIgnoreCase("First")) {
			page.setCurrentPage((page.getTotalNoOfPages() / page
					.getTotalNoOfPages()));
		}

		List locateResultsList = new ArrayList();
		if (isSpsIdLocate) {
			locateResultsList = locateSpsIdFacade
				.getRecords(mapping, status, null, -1, -1, page);
			modelAndView = new ModelAndView("locatespsresults");
		}
		if (isRuleIdLocate) {
			locateResultsList = locateRuleIdFacade
				.getRecords(mapping, status, null, -1, -1, page);
			modelAndView = new ModelAndView("locateruleresults");
		}
		if (isCustomMessageLocate) {
			locateResultsList = locateCustomMessageFacade
				.getRecords(mapping, status, null, -1, -1, page);
			modelAndView = new ModelAndView("locatecustommsgresults");
		}
		
		log.debug("##########" + locateResultsList.size());
		locateResultsList = BxUtil.concatBreak(locateResultsList);
		modelAndView.addObject("locateResultsList", locateResultsList);
		modelAndView.addObject("page", page);
		// Lock - May release setting userId
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}
	/**
	 * method to generate excel report for advance search 
	 * @param response
	 * @param request
	 * @return modelAndView
	 */
	public ModelAndView generateExcelReport(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = null;
		boolean isSpsIdLocate = false;
		boolean isRuleIdLocate = false;
		boolean isCustomMessageLocate = false;
		String selectedSpsId = "selectedSpsId";
		String selectedRuleId = "selectedRuleId";
		List ruleIDList = null;
		List spsIDList = null;
		List customMessageList = null;
		
		if ("isCustomMessage".equalsIgnoreCase(request.getParameter("customMessageLocateChkBox"))) {
			isCustomMessageLocate = true;
		} else {
			if (request.getParameter(selectedSpsId) != null 
					&& !request.getParameter(selectedSpsId).trim().equals("")) {
				isSpsIdLocate = true;
			}
			if (request.getParameter(selectedRuleId) !=null 
					&& !request.getParameter(selectedRuleId).trim().equals("")) {
				isRuleIdLocate = true;
			}
		}
		
		Mapping mapping = new Mapping();
		
		mapping.setSpsId(new SPSId());
		mapping.setRule(new Rule());
		
		
		List status = new ArrayList();

		Variable variable = new Variable();
		variable.setVariableId(request.getParameter("variableId"));
		variable.setDescription(request.getParameter("variableDesc"));
		
		if ("isMapped".equalsIgnoreCase(request.getParameter("mappedStatus"))) {
			variable.setMappedVariable(true);
			status.add(DomainConstants.MAPPED_STATUS);
		} 
		if ("unMapped".equalsIgnoreCase(request.getParameter("unMappedStatus"))) {
			variable.setUnmappedVariable(true);
			status.add(DomainConstants.UNMAPPED_STATUS);
		} 
		if ("isNotApplicable".equalsIgnoreCase(request.getParameter("notAppStatus"))) {
			status.add(DomainConstants.STATUS_NOT_APPLICABLE);
		} 
		if (status.isEmpty()) {
			status.add(DomainConstants.MAPPED_STATUS);
			status.add(DomainConstants.UNMAPPED_STATUS);
			status.add(DomainConstants.STATUS_NOT_APPLICABLE);
		}
		
		//flow for spsId locate
		if (isSpsIdLocate) {
			mapping.getSpsId().setSpsId(request.getParameter(selectedSpsId).trim() + "%");
			spsIDList = locateSpsIdFacade.getRecordsForReport(mapping,status);		
	
		}
		//flow for ruleId locate
		else if (isRuleIdLocate) {
			mapping.getRule().setHeaderRuleId(request.getParameter(selectedRuleId).trim() + "%");	
			ruleIDList = locateRuleIdFacade.getRecordsForReport(mapping,status);		
				
		}
		//flow for message text locate
		else if (isCustomMessageLocate) {
			if (null != request.getParameter(selectedSpsId)) {
				mapping.getSpsId().setSpsId(request.getParameter(selectedSpsId).trim());
			}
			if (null != request.getParameter(selectedRuleId)) {
				mapping.getRule().setHeaderRuleId(request.getParameter(selectedRuleId).trim());	
			}
			customMessageList = locateCustomMessageFacade.getRecordsForReport(mapping,status);				
		}
		ExcelLocateView excelLocate = new ExcelLocateView(ruleIDList, spsIDList, customMessageList);
		modelAndView = new ModelAndView(excelLocate);
		return modelAndView;
	}

	public LocateFacade getLocateSpsIdFacade() {
		return locateSpsIdFacade;
	}

	public void setLocateSpsIdFacade(LocateFacade locateSpsIdFacade) {
		this.locateSpsIdFacade = locateSpsIdFacade;
	}

	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}

	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}

	public LocateFacade getLocateCustomMessageFacade() { 
		return locateCustomMessageFacade;
	}

	public void setLocateCustomMessageFacade(LocateFacade locateCustomMessageFacade) {
		this.locateCustomMessageFacade = locateCustomMessageFacade;
	}

	
}