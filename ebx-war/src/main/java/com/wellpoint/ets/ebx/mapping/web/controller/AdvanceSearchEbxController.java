package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.AdvanceSearchFacade;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.mapping.web.view.ExcelSearchView;
/**
 * The controller class for advance serach ebx(ewpd)
 * @author U23914
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdvanceSearchEbxController extends MultiActionController {
	
	private SessionMessageTray sessionMessageTray;	
	private AdvanceSearchFacade advancedSearchFacade;
	private LocateFacade locateSpsIdFacade;
	private LocateFacade locateRuleIdFacade;
	private MassUpdateTracker massUpdateTracker;
	private LocateFacade locateCustomMessageFacade;
	
	private static Logger logger = Logger.getLogger(AdvanceSearchEbxController.class.getName());
	/**
	 * This method calls when the advance search link clicked from viewlanding
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewSearch(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		HttpSession session = arg0.getSession();
		clearSessionValues(session,true);
		Map map = new HashMap();		
		map.put("fromHistory","false");
		ModelAndView modelAndView = new ModelAndView("advancesearchebx",map);
		modelAndView.addObject(WebConstants.INFO_MESSAGES, sessionMessageTray.getAndClearInformationMessages())
		.addObject(WebConstants.ERROR_MESSAGES, sessionMessageTray.getAndClearErrorMessages())
		.addObject(WebConstants.WARNING_MESSAGES, sessionMessageTray.getAndClearWarningMessages())
		;
		return modelAndView;
	}
	/**
	 * Clear session values
	 * @param session
	 * @param remove
	 */
	public void clearSessionValues(HttpSession session,boolean remove){
		session.removeAttribute("searchCriteria");
		session.removeAttribute("currentPage");
		session.removeAttribute("section");
		session.removeAttribute("criteriaForSearch");		
		session.removeAttribute("searchCriteria");
		if(remove)
			session.removeAttribute("pageObject");
		
	}
	/**
	 * This method will call when redirect from edit page
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewHistorySearch(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {		
		arg0.setAttribute("fromHistory","true");
		ModelAndView modelAndView = new ModelAndView("advancesearchebx");
		modelAndView.addObject(WebConstants.INFO_MESSAGES, sessionMessageTray.getAndClearInformationMessages())
		.addObject(WebConstants.ERROR_MESSAGES, sessionMessageTray.getAndClearErrorMessages())
		.addObject(WebConstants.WARNING_MESSAGES, sessionMessageTray.getAndClearWarningMessages())
		;
		return modelAndView;
	}
	/**
	 * This method will call when redirect from edit page
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws EBXException
	 */
	public ModelAndView advanceSearchHistory(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		HttpSession session = request.getSession();
		
		String currentPage = null;
		String section = null;
		SearchCriteria searchCriteriaFromSession = null;
		searchCriteriaFromSession = (SearchCriteria) session.getAttribute("searchCriteria");
		logger.info(ESAPI.encoder().encodeForHTML("Sps Id,Rule id from search criteria session object:"+searchCriteriaFromSession.getSpsId()+","+searchCriteriaFromSession.getHeaderRuleId()));
		
		if(null != session.getAttribute("currentPage")){
			currentPage = session.getAttribute("currentPage").toString();
		}
		if(null != session.getAttribute("section")){
			section =session.getAttribute("section").toString();
		}
		
		int totalNoOfRecords = advancedSearchFacade.getAdvanceSearchCount(searchCriteriaFromSession);
		Page page = null;
		if(null != section){
			 page = getPage(currentPage, section, totalNoOfRecords);	
		}
		
		
		session.setAttribute("searchCriteria",searchCriteriaFromSession);
		session.setAttribute("pageObject",page);
		
		return getAdvanceSearchResultView(page, advancedSearchFacade.getAdvanceSearchRecords(searchCriteriaFromSession, 50, page),searchCriteriaFromSession,"true");
		
		
	}
	/**
	 * This method will invoke on search
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView advanceSearch(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		HttpSession session = request.getSession();
		String userName = request.getAttribute(SecurityConstants.USER_NAME).toString();
		clearSessionValues(session,false);
		
		request.setAttribute("fromHistory","false");
		int totalNoOfRecords = 0;		
		String spsId = null;
		String eb01 = null;
		
		String ruleId =null;
		String eb03 = null;	
		String iii02 = null;	
		
		String msgText = null;
		String noteType = null;
		
		boolean isUnMapped= false;
		boolean isMapped = false;
		boolean isNotApplicable = false;
		boolean isInProgress = false;
		boolean isNotFinalized = false;
		
		boolean spsIdCrteria = false;
		boolean ruleIdCriteria  = false;
		boolean msgCriteria = false;
		String criteriaForSearch = null;
		String viewType ="spsid";
		String user =null;
		
		String currentPage = ESAPI.encoder().encodeForHTML(request.getParameter("currentPage"));
		if(BxUtil.regExPatterValidation(currentPage)){
			currentPage = currentPage;
		}else{
			currentPage=null;
		}
		String section = ESAPI.encoder().encodeForHTML(request.getParameter("section"));	
		if(BxUtil.regExPatterValidation(section)){
			section = section;
		}else{
			section=null;
		}
		String authorizedToApprove  = ESAPI.encoder().encodeForHTML(request.getParameter("authorizedToApprove"));	
		if(BxUtil.regExPatterValidation(authorizedToApprove)){
			authorizedToApprove = authorizedToApprove;
		}else{
			authorizedToApprove=null;
		}
		criteriaForSearch = ESAPI.encoder().encodeForHTML(request.getParameter("valueForSearchCriteria"));
		if(BxUtil.regExPatterValidation(criteriaForSearch)){
			criteriaForSearch = request.getParameter("valueForSearchCriteria");
		}else{
			criteriaForSearch=null;
		}

		if(null!=criteriaForSearch && criteriaForSearch.trim().equals("spsid")){
			spsIdCrteria = true;
			viewType ="spsid";
		}
		if(null!=criteriaForSearch && criteriaForSearch.trim().equals("ruleid")){
			ruleIdCriteria = true;
			viewType ="ruleid";
		}
		if(null!=criteriaForSearch && criteriaForSearch.trim().equals("msgtxt")){
			msgCriteria = true;
			viewType ="msgtxt";
		}
		if(null!= request.getParameter("isUnMapped")&& request.getParameter("isUnMapped").equals("true")){
			isUnMapped = true;
		}
		if(null!=request.getParameter("isMapped") && request.getParameter("isMapped").equals("true")){
			isMapped = true;
		}
		if(null!=request.getParameter("isNotApplicable") && request.getParameter("isNotApplicable").equals("true")){
			isNotApplicable = true;
		}
		
		if(spsIdCrteria){
			if(null!=request.getParameter("isNotFinalized") && request.getParameter("isNotFinalized").equals("true")){
				isNotFinalized = true;
			}
			spsId = request.getParameter("spsIdToLocate");
			BxUtil.validationVariable(spsId);
			eb01 = request.getParameter("eb01");
			BxUtil.validationVariable(eb01);
			
		}
		
		if(ruleIdCriteria){
			ruleId = request.getParameter("ruleIdToLocate");
			BxUtil.validationVariable(ruleId);
			if(null != request.getParameter("eb03")){
				String eb03ForCrieteria = "";
				String ebo3FromRequest = request.getParameter("eb03");
				BxUtil.validationVariable(ebo3FromRequest);
				String[] eb03Array = ebo3FromRequest.split(",");
				for(int itr = 0; itr<eb03Array.length; itr++){
					String eb03Value = eb03Array[itr];
					if (null != eb03Value) {
						eb03Value = eb03Value.trim().toUpperCase();
						if(!"'".equals(eb03Value) && !"".equals(eb03Value)){
							eb03ForCrieteria = eb03ForCrieteria
							+ eb03Value + ",";
						}
					}
				}
				if(eb03ForCrieteria.length()>0){
				eb03 = eb03ForCrieteria.substring(0, eb03ForCrieteria.length()-1);
				}else{
					eb03 ="";
				}
			}
			
			if(null != request.getParameter("iii02")){
				String iii02ForCrieteria = "";
				String iii02FromRequest = request.getParameter("iii02");
				BxUtil.validationVariable(iii02FromRequest);
				String[] iii02Array = iii02FromRequest.split(",");
				for(int itr = 0; itr<iii02Array.length; itr++){
					String iii02Value = iii02Array[itr];
					if (null != iii02Value) {
						iii02Value = iii02Value.trim().toUpperCase();
						if(!"'".equals(iii02Value) && !"".equals(iii02Value)){
							iii02ForCrieteria = iii02ForCrieteria
							+ iii02Value + ",";
						}
					}
				}
				if(iii02ForCrieteria.length()>0){
					iii02 = iii02ForCrieteria.substring(0, iii02ForCrieteria.length()-1);
				}else{
					iii02 ="";
				}
				
			}
		}
		
		if(msgCriteria){
			ruleId = request.getParameter("ruleIdToLocate");
			BxUtil.validationVariable(ruleId);
			spsId = request.getParameter("spsIdToLocate");
			BxUtil.validationVariable(spsId);
			if(null != request.getParameter("msgText")){
				String msgTextForCrieteria = "";
				String msgTextFromRequest = request.getParameter("msgText");
				BxUtil.validationVariable(msgTextFromRequest);
				String[] msgTextArray = msgTextFromRequest.split(",");
				for(int itr = 0; itr<msgTextArray.length; itr++){
					String msgTextValue = msgTextArray[itr];
					if (null != msgTextValue) {
						msgTextValue = msgTextValue.trim().toUpperCase();
						if(!"'".equals(msgTextValue) && !"".equals(msgTextValue)){
							msgTextForCrieteria = msgTextForCrieteria
							+ msgTextValue + ",";
						}
					}
				}
				if(msgTextForCrieteria.length()>0){
					msgText = msgTextForCrieteria.substring(0, msgTextForCrieteria.length()-1);
				}else{
					msgText ="";
				}
			}
			
			if(null != request.getParameter("noteType")){
				String noteTypeForCrieteria = "";
				String noteTypeFromRequest = ESAPI.encoder().encodeForHTML(request.getParameter("noteType"));
				if(null!=noteTypeFromRequest  && noteTypeFromRequest.matches("[0-9a-zA-Z_]+")){
					noteTypeFromRequest = noteTypeFromRequest;
				}
				String[] noteTypeArray = noteTypeFromRequest.split(",");
				for(int itr = 0; itr<noteTypeArray.length; itr++){
					String noteTypeValue = noteTypeArray[itr];
					if (null != noteTypeValue) {
						noteTypeValue = noteTypeValue.trim().toUpperCase();
						if(!"'".equals(noteTypeValue) && !"".equals(noteTypeValue)){
							noteTypeForCrieteria = noteTypeForCrieteria
							+ noteTypeValue + ",";
						}
					}
				}
				if(noteTypeForCrieteria.length()>0){
					noteType = noteTypeForCrieteria.substring(0, noteTypeForCrieteria.length()-1);
				}else{
					noteType ="";
				}
			}
			
		}
		
		
		if(null != request.getParameter("users")){
			user = request.getParameter("users").toString();
		}
		String users = BxUtil.splitCommaSeperatedUser(request.getParameter("users")); 
		BxUtil.validationVariable(users);
		//Mapped and Unmapped checked with the value of eb01 or user then return only mapped
		if(spsIdCrteria){
			if(isUnMapped && isMapped && null!=eb01 && !eb01.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotApplicable && null!=eb01 && !eb01.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotFinalized && null!=eb01 && !eb01.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isMapped && null!=user && !user.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotApplicable && null!=user && !user.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotFinalized && null!=user && !user.equals("")){				
				isUnMapped=false;
			}
		}
		//Mapped and Unmapped checked with the value of eb03 or user or iii02  then return only mapped
		if(ruleIdCriteria){
			if(isUnMapped && isMapped && null!=eb03 && !eb03.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotApplicable && null!=eb03 && !eb03.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isMapped && null!=user && !user.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotApplicable && null!=user && !user.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isMapped && null!=iii02 && !iii02.equals("")){				
				isUnMapped=false;
			}
			if(isUnMapped && isNotApplicable && null!=iii02 && !iii02.equals("")){				
				isUnMapped=false;
			}
		}
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setSpsId(spsId);
		searchCriteria.setEB01(eb01);
		searchCriteria.setEB03(eb03);
		searchCriteria.setIII02(iii02);
		searchCriteria.setHeaderRuleId(ruleId);
		searchCriteria.setUnMapped(isUnMapped);
		searchCriteria.setMapped(isMapped);
		searchCriteria.setNotApplicable(isNotApplicable);
		searchCriteria.setInProgress(isInProgress);
		searchCriteria.setNotFinalized(isNotFinalized);
		searchCriteria.setSpsIdCriteria(spsIdCrteria);
		searchCriteria.setRuleIdCriteria(ruleIdCriteria);
		searchCriteria.setMsgCrteria(msgCriteria);
		searchCriteria.setCommaSeperatedUser(users);
		searchCriteria.setMessageText(msgText);
		searchCriteria.setNoteType(noteType);
		searchCriteria.setViewType(viewType);
		if(null!=request.getParameter("users")  && request.getParameter("users").matches("[0-9a-zA-Z_]+")){
			searchCriteria.setUser(ESAPI.encoder().encodeForHTML(request.getParameter("users")));
		}		
		searchCriteria.setLoggedUser(userName);
		searchCriteria.setAuthorizedToApproveFlag(authorizedToApprove);
		
		if(null != section && section.equals("Init")){
			logger.info("Calling the initial method");
			totalNoOfRecords = advancedSearchFacade.getAdvanceSearchCount(searchCriteria);
		}
		else{
			logger.info("Calling from the session object");
			Page page =(Page) session.getAttribute("pageObject");
			if(null != page){
				totalNoOfRecords = (int) page.getTotalNoOfRecords();
			}else{
				totalNoOfRecords = advancedSearchFacade.getAdvanceSearchCount(searchCriteria);
			}
		}
		Page page=null;
		if(null != section){
			page = getPage(currentPage, section, totalNoOfRecords);
		}
		
		session.setAttribute("currentPage",currentPage);
		session.setAttribute("section",section);
		session.setAttribute("criteriaForSearch",criteriaForSearch);
		
		session.setAttribute("searchCriteria",searchCriteria);
		session.setAttribute("pageObject",page);
		
		if(spsIdCrteria){
			massUpdateTracker.setLastSearchedType(MassUpdateTracker.SPS_TYPE);
		}else if(ruleIdCriteria){
			massUpdateTracker.setLastSearchedType(MassUpdateTracker.RULE_TYPE);
		}else if(msgCriteria){
			massUpdateTracker.setLastSearchedType(MassUpdateTracker.MSG_TYPE);
		}
		return getAdvanceSearchResultView(page, advancedSearchFacade.getAdvanceSearchRecords(searchCriteria, 50, page),searchCriteria,"false");
	}
	/**
	 * Method creates the model and view object for result page
	 * @param page
	 * @param list
	 * @param searchCriteria
	 * @param fromHistory
	 * @return
	 */
	private ModelAndView getAdvanceSearchResultView(Page page,List list,SearchCriteria searchCriteria,String fromHistory ){
		String view ="";
		if(searchCriteria.isSpsIdCriteria())
			view ="SPS";
		if(searchCriteria.isRuleIdCriteria())
			view="RULE";
		if(searchCriteria.isMsgCrteria())
			view="MSG";
		massUpdateTracker.markSelected(list, Integer.valueOf(page.getCurrentPage()),view);
		Map map = new HashMap();
		map.put("searchResults", list);
		map.put("page", page);
		map.put("selectedPageInfo", massUpdateTracker.getSelectedPagesString());
		map.put("viewtype",view);
		map.put("fromHistory",fromHistory);
		map.put("searchCriteria",searchCriteria);
		
		ModelAndView modelAndView = new ModelAndView("advancesearchresult",map);
		return modelAndView;
	}
	/**
	 * Method for message pop up 
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView viewCustomMessagePopUp(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		Mapping currentMapping = new Mapping();
		SPSId spsId = new SPSId();
		spsId.setSpsId(request.getParameter("spsId"));
		currentMapping.setSpsId(spsId);

		Rule rule = new Rule();
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		currentMapping.setRule(rule);

		List status = new ArrayList();
		List mappings = null;

		status.add(DomainConstants.VIEW_STATUS);
		status.add(DomainConstants.VIEW_STATUS_FOR_CUSTOM_MESSAGE);
		mappings = locateCustomMessageFacade.getRecords(currentMapping,
				status, null, WebConstants.TOTAL_NO_OF_RECORDS,
				WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		
		ModelAndView modelAndView = new ModelAndView("viewPopUpCustomMsg");

		currentMapping = (Mapping) mappings.get(0);	
		
		if(null != currentMapping && null != currentMapping.getCreatedTmStamp()){
			Date created  = currentMapping.getCreatedTmStamp();
			currentMapping.setFormattedStringDate(BxUtil.getFormattedDateWithOutTime(created));
			
		}
		List<EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		if(null != currentMapping && null != currentMapping.getEb03() && null != currentMapping.getEb03().getEb03Association()){
			eB03AssnList = currentMapping.getEb03().getEb03Association();
		}
		
		modelAndView.addObject("currentMapping", currentMapping);
		modelAndView.addObject("eB03AssnList", eB03AssnList);
		
		
		if(null != currentMapping && null != currentMapping.getAuditTrails() && 
				currentMapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
			modelAndView.addObject("viewalllink", new Boolean(true));
		}

		return modelAndView;
	}
	/**
	 * Metthod for Sps Pop up
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView viewSpsAndRuleIdPopUp(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		
		Mapping currentMapping = new Mapping();
		List status = new ArrayList();
		List mappings = new ArrayList();
		String viewtype = request.getParameter("viewType");
		String section = request.getParameter("section");
		if (null != section
				&& (section.equals(DomainConstants.UNMAPPED_STATUS))) {

			status.add(DomainConstants.UNMAPPED_STATUS);
		}
		if (viewtype.equals("SPS")) {
			SPSId spsId = new SPSId();
			spsId.setSpsId(request.getParameter("viewId"));
			currentMapping.setSpsId(spsId);
			mappings = locateSpsIdFacade.getRecords(currentMapping, status,
					null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
			
		} else if (viewtype.equals("RULE")) {
			Rule rule = new Rule();
			rule.setHeaderRuleId(request.getParameter("viewId"));
			currentMapping.setRule(rule);
			mappings = locateRuleIdFacade.getRecords(currentMapping, status,
					null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
		}

		ModelAndView modelAndView = new ModelAndView("viewPopUpSpsAndRuleId");
		if (null != mappings && !mappings.isEmpty()) {
			currentMapping = (Mapping) mappings.get(0);
			if(null != currentMapping && null != currentMapping.getCreatedTmStamp()){
				Date created  = currentMapping.getCreatedTmStamp();
				currentMapping.setFormattedStringDate(BxUtil.getFormattedDateWithOutTime(created));
				
			}
			modelAndView.addObject("currentMapping", currentMapping);
			modelAndView.addObject("viewtype", viewtype);

			if (null != currentMapping
					&& null != currentMapping.getAuditTrails()
					&& currentMapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY) {
				modelAndView.addObject("viewalllink", new Boolean(true));
			}
		}

		return modelAndView;
	}
	/**
	 * Method for pagination object
	 * @param currentPage
	 * @param section
	 * @param totalNoOfRecords
	 * @return Page
	 */
	
	private Page getPage(String currentPage,String section, int totalNoOfRecords){
		if(totalNoOfRecords == 0){
			totalNoOfRecords = 1;
		}
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalNoOfRecords(totalNoOfRecords);
		
		if (section.equalsIgnoreCase("Init")) {
			massUpdateTracker.clearSelectedRecords();
			section = "First";
		}
		
		if (section.equalsIgnoreCase("Refresh")) {
			massUpdateTracker.clearSelectedRecords();
			return page;
		}
		
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
	
	/**
	 * Method for getting search result and generating excel report.
	 * @param response
	 * @param request
	 * @return ModelAndView
	 */
	
	public ModelAndView generateExcelReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long start = System.currentTimeMillis();
		ModelAndView modelAndView = null;
		List ruleIDForReport = null;
		List spsIDForReport = null;
		List msgForReport = null;
		SearchCriteria searchCriteria = null;
		HttpSession session = request.getSession();
		searchCriteria = (SearchCriteria) session
				.getAttribute("searchCriteria");
		String criteriaForSearch = (String) session
				.getAttribute("criteriaForSearch");
		if (null != searchCriteria
				&& (null != criteriaForSearch && !"".equalsIgnoreCase(criteriaForSearch))) {
			// search of ruleID.
			if (criteriaForSearch.trim().equalsIgnoreCase("ruleid")) {
				ruleIDForReport = advancedSearchFacade
						.getRecordsForReport(searchCriteria);
			}// search of spsID.
			else if (criteriaForSearch.trim().equalsIgnoreCase("spsid")) {
				spsIDForReport = advancedSearchFacade
						.getRecordsForReport(searchCriteria);
			}// search of custom message.
			else if (criteriaForSearch.trim().equalsIgnoreCase("msgtxt")) {
				msgForReport = advancedSearchFacade
						.getRecordsForReport(searchCriteria);
			}
		}
		ExcelSearchView excelview = new ExcelSearchView(ruleIDForReport,
				spsIDForReport, msgForReport, searchCriteria);
		modelAndView = new ModelAndView(excelview);
		long end = System.currentTimeMillis();
		logger.info("Report Respone ::::::: " + (end - start));
		return modelAndView;
	}
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
	
	/**
	 * @return Returns the advancedSearchFacade.
	 */
	public AdvanceSearchFacade getAdvancedSearchFacade() {
		return advancedSearchFacade;
	}
	/**
	 * @param advancedSearchFacade The advancedSearchFacade to set.
	 */
	public void setAdvancedSearchFacade(AdvanceSearchFacade advancedSearchFacade) {
		this.advancedSearchFacade = advancedSearchFacade;
	}
	/**
	 * @return Returns the locateSpsIdFacade.
	 */
	public LocateFacade getLocateSpsIdFacade() {
		return locateSpsIdFacade;
	}
	/**
	 * @param locateSpsIdFacade The locateSpsIdFacade to set.
	 */
	public void setLocateSpsIdFacade(LocateFacade locateSpsIdFacade) {
		this.locateSpsIdFacade = locateSpsIdFacade;
	}
	
	public MassUpdateTracker getMassUpdateTracker() {
		return massUpdateTracker;
	}

	public void setMassUpdateTracker(MassUpdateTracker massUpdateTracker) {
		this.massUpdateTracker = massUpdateTracker;
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

	public void setLocateCustomMessageFacade(
			LocateFacade locateCustomMessageFacade) {
		this.locateCustomMessageFacade = locateCustomMessageFacade;
	}
}
