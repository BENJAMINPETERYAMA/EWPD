/*
 * <LocateResultsController.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.view.ExcelLocateView;

/**
 * @author UST-GLOBAL
 * This is an implementation class for locating or searching a variable
 */
public class LocateResultsController extends MultiActionController {

	private LocateFacade locateFacade;

	private static Logger log = Logger.getLogger(LocateResultsController.class);

	public ModelAndView locateRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String section = "section";
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString(); 
		
		Variable variable = new Variable();
		if (null != request.getParameter("variableId")) {
			String variableId = request.getParameter("variableId").trim();
			variable.setVariableId(variableId);
		}
		if (null != request.getParameter("variableDesc")) {
			variable.setDescription(request.getParameter("variableDesc").trim());
		}
		if (request.getParameter("mappedStatus").equalsIgnoreCase("isMapped")) {
			variable.setMappedVariable(true);
		} else {
			variable.setMappedVariable(false);
		}
		if (request.getParameter("unMappedStatus").equalsIgnoreCase("unMapped")) {
			variable.setUnmappedVariable(true);
		} else {
			variable.setUnmappedVariable(false);
		}
		if (request.getParameter("notAppStatus").equalsIgnoreCase(
				"isNotApplicable")) {
			variable.setNotApplicable(true);
		} else {
			variable.setNotApplicable(false);
		}
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(request
				.getParameter("currentPage")));
		page.setTotalNoOfRecords(locateFacade
				.findTotalNoOfRecordsForLocate(variable));
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

		List locateResultsList = locateFacade
				.findLocateResultsMatchingVariables(variable, page);
		BxUtil.encodeMappingsList(locateResultsList);
		log.debug("##########" + locateResultsList.size());
		ModelAndView modelAndView = new ModelAndView("locateresults");
		modelAndView.addObject("locateResultsList", locateResultsList);
		modelAndView.addObject("page", page);
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
		Variable variable = new Variable();
		List locateResultsList = null;
		if (null != request.getParameter("variableId").trim()) {
			variable.setVariableId(request.getParameter("variableId").trim());
		}
		if (null != request.getParameter("variableDesc")) {
			variable.setDescription(request.getParameter("variableDesc").trim());
		}
		if (request.getParameter("mappedStatus").equalsIgnoreCase("isMapped")) {
			variable.setMappedVariable(true);
		} else {
			variable.setMappedVariable(false);
		}
		if (request.getParameter("unMappedStatus").equalsIgnoreCase("unMapped")) {
			variable.setUnmappedVariable(true);
		} else {
			variable.setUnmappedVariable(false);
		}
		if (request.getParameter("notAppStatus").equalsIgnoreCase(
				"isNotApplicable")) {
			variable.setNotApplicable(true);
		} else {
			variable.setNotApplicable(false);
		}
		if(null!= variable){
		locateResultsList = locateFacade.getRecordsForReport(variable);
		}
		log.debug("##########" + locateResultsList.size());
		ExcelLocateView excelLocate = new ExcelLocateView(locateResultsList);
		ModelAndView modelAndView = new ModelAndView(excelLocate);
		return modelAndView;
	
	}

	/**
	 * @return Returns the locateFacade.
	 */
	public LocateFacade getLocateFacade() {
		return locateFacade;
	}

	/**
	 * @param locateFacade
	 *            The locateFacade to set.
	 */
	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}
}