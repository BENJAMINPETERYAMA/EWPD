package com.wellpoint.ets.ebx.mapping.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.mapping.application.MappingFacade;
import com.wellpoint.ets.ebx.mapping.web.view.ExcelPrintView;

public class StateFlowEwpdBxController extends MultiActionController {

	private MappingFacade mappingRuleIdFacade;

	private MappingFacade mappingSpsIdFacade;

	private MappingFacade mappingCustomMessageFacade;

	private SessionMessageTray sessionMessageTray;

	private LocateFacade locateRuleIdFacade;

	private LocateFacade locateSpsIdFacade;

	private LocateFacade locateCustomMessageFacade;

	private static Logger logger = Logger.getLogger(StateFlowEwpdBxController.class);
	public ModelAndView changeInProgress(HttpServletRequest request,
			HttpServletResponse response) {
		long changeInProgressStartTime = System.currentTimeMillis();
		int noOfRecords = 0;
		int noOfCustomMessage = 0;
		List statusUnmapped = new ArrayList();
		statusUnmapped.add(DomainConstants.UNMAPPED_STATUS);

		List statusMapped = new ArrayList();
		statusMapped.add(DomainConstants.MAPPED_STATUS);

		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();

		String numOfRecords = request.getParameter("getAll");
		if(numOfRecords.equals("true")){
			
			noOfRecords = -1;
			noOfCustomMessage =-1;
		}
		else{
			noOfRecords = 26;
			noOfCustomMessage = 50;
		}
		
		String appendUser = request.getParameter("appendUser");
		if(appendUser.equals("true")){
			loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		}	
		else{			
			loggedInUser = null;
		}
		List statusInProgress = new ArrayList();
		statusInProgress.add("IN-PROGRESS");
		List inprogressRule = locateRuleIdFacade.getRecords(null,
				statusInProgress, loggedInUser, noOfRecords, 21, null);
		 inprogressRule = BxUtil.concatBreak(inprogressRule);
		if(null != inprogressRule && !inprogressRule.isEmpty()){
				Collections.sort(inprogressRule);
		}	
		List inprogressSps = locateSpsIdFacade.getRecords(null, statusInProgress,
				loggedInUser, noOfRecords, 21, null);
		 inprogressSps = BxUtil.concatBreak(inprogressSps);
		if (null != inprogressSps && !inprogressSps.isEmpty()) {
			Collections.sort(inprogressSps);
		}

		List inprogressCustomMsg = locateCustomMessageFacade.getRecords(null,
				statusInProgress, loggedInUser, noOfCustomMessage , 21, null);
		 inprogressCustomMsg = BxUtil.concatBreak(inprogressCustomMsg);
		if (null != inprogressCustomMsg && !inprogressCustomMsg.isEmpty()) {
			Collections.sort(inprogressCustomMsg);
		}

		Iterator spsIterator = inprogressSps.iterator();
		String contextPath = request.getContextPath() + "/images";
		ArrayList inProgressSpsAndRuleMappings = new ArrayList();
		int sentToTest = 0;
		int approve = 0;
		String idTest = null;
		String idApprove = null;
		int lock = 1;
		String idLock = null;
		String idEdit = null;
		String userName = request.getAttribute(SecurityConstants.USER_NAME).toString();
		while (spsIterator.hasNext()) {

			sentToTest = 0;
			approve = 0;

			idTest = null;
			idApprove = null;
			lock = 1;
			
			Mapping mapping = (Mapping) spsIterator.next();

			Object[] mappingDetails = new Object[5];
			if (!mapping.isFinalized()) {
				mappingDetails[0] = "FINALIZED" + mapping.getSpsId().getSpsId();
			} else {
				mappingDetails[0] = mapping.getSpsId().getSpsId();
			}
			mappingDetails[1] = mapping.getSpsId().getSpsDesc();

			String DATE_FORMAT = "MM/dd/yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			mappingDetails[2] = sdf.format(mapping.getLastChangedTmStamp());
			mappingDetails[3] = mapping.getUser().getLastUpdatedUserName();
			String icon = "<a href=# onclick='openViewMappingInProgressDialogForSps(\""
					+ mapping.getSpsId().getSpsId()
					+ "\");'><img src='"
					+ contextPath + "/search_icon.gif' alt='View' title='View' /></a>";
			icon += "&#160;&#160";
			
			if(null == mapping.getLock()){				
				lock = 0;
			}
			else{
				if(mapping.getLock().getLockUserId().equals(userName)){					
					lock =0;
				}
			}
			if(lock == 0){
				idEdit = mapping.getSpsId().getSpsId()+"_Edit";
				icon += "<a	href=# id='"+idEdit+"' onclick='editMappingForSps(\""
						+ mapping.getSpsId().getSpsId() + "\");' ><img src='"
						+ contextPath + "/edit_icon.gif' alt='Edit' title='Edit' /></a>";
			}
			String unlock = "unlockSpsFromLanding";
			if(lock == 1)	{
				if(request.getParameter("authorizedTounlock").equals("true")){
					idLock = mapping.getSpsId().getSpsId()+"_Lock";
					icon += "<a	href=#  id='"+idLock+"' " +
					"onclick='unlockMappingFromLocate(\"\",\""
					+mapping.getSpsId().getSpsId()+"\",\""+unlock+"\",\""+mapping.getLock().getLockUserId()+"\");'>" +
					"<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px;/></a>";
				}
				else{
					
					icon += "<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px; />";
				}
			}
			idTest = mapping.getSpsId().getSpsId() + "_Test";
			idApprove = mapping.getSpsId().getSpsId() + "_Approve";

			if (mapping.getVariableMappingStatus().equals("SCHEDULED_TO_TEST")) {
				sentToTest = 1;
				approve = 1;
			}
			if (mapping.getVariableMappingStatus().equals("OBJECT_TRANSFERRED")) {
				sentToTest = 1;
			}
			if (mapping.getVariableMappingStatus().equals(
					"SCHEDULED_TO_PRODUCTION")
					|| mapping.getVariableMappingStatus().equals("PUBLISHED")) {
				sentToTest = 1;
				approve = 1;
			}

			if (sentToTest != 1 && lock != 1) {
				icon += "&#160;&#160";
				icon += "<a href='#' id=" + idTest
						+ " onclick='openUserCommentsSend2TestDialogForSps(\""
						+ mapping.getSpsId().getSpsId() + "\");'><img	src='"
						+ contextPath
						+ "/test_icon.gif' alt='Send to Test' title='Send to Test' /></a>";
			}

			if (approve != 1 && lock != 1) {
				if (request.getParameter("authorizedToapprove")
						.equalsIgnoreCase("true")
						|| mapping.getVariableMappingStatus().equals(
								"OBJECT_TRANSFERRED")) {
					icon += "&#160;&#160";
					icon += "<a href='#' id="
							+ idApprove
							+ " onclick='openUserCommentsApproveDialogForSps(\""
							+ mapping.getSpsId().getSpsId()
							+ "\");'><img src='" + contextPath
							+ "/approve_icon.gif' alt='Approve' title='Approve' /></a>";
				}
			}

			mappingDetails[4] = icon;

			inProgressSpsAndRuleMappings.add(mappingDetails);

		}
		Iterator ruleIterator = inprogressRule.iterator();
		while (ruleIterator.hasNext()) {

			sentToTest = 0;
			approve = 0;
			lock = 1;
			
			idEdit = null;
			idLock = null;
			idTest = null;
			idApprove = null;

			Mapping mapping = (Mapping) ruleIterator.next();

			Object[] mappingDetails = new Object[5];
			mappingDetails[0] = mapping.getRule().getHeaderRuleId();
			mappingDetails[1] = mapping.getRule().getRuleDesc();

			String DATE_FORMAT = "MM/dd/yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			mappingDetails[2] = sdf.format(mapping.getLastChangedTmStamp());
			mappingDetails[3] = mapping.getUser().getLastUpdatedUserName();
			/** ************************************************************************ */
			String icon = "<a href='#' onclick='openViewMappingInProgressDialogForRule(\""
					+ mapping.getRule().getHeaderRuleId()
					+ "\");'><img src='"
					+ contextPath + "/search_icon.gif' alt='View' title='View' /></a>";
			icon += "&#160;&#160;";
			if(null == mapping.getLock()){				
				lock = 0;
			}
			else{
				if(mapping.getLock().getLockUserId().equals(userName)){					
					lock =0;
				}
			}
			if(lock == 0){
				idEdit = mapping.getRule().getHeaderRuleId()+"_Edit";
			
				icon += "<a	href='#' id='"+idEdit+"' onclick='editMappingForRule(\""
						+ mapping.getRule().getHeaderRuleId() + "\");'><img src='"
						+ contextPath + "/edit_icon.gif' alt='Edit' title='Edit'/></a>";
				
			}		
			String unlock = "unlockRuleFromLanding";
			if(lock == 1)	{
				if(request.getParameter("authorizedTounlock").equals("true")){
					idLock = mapping.getRule().getHeaderRuleId()+"_Lock";
					icon += "<a	href=#  id='"+idLock+"' " +
					"onclick='unlockMappingFromLocate(\""
					+mapping.getRule().getHeaderRuleId()+"\",\"\",\""+unlock+"\",\""+mapping.getLock().getLockUserId()+"\");'>" +
					"<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px;/></a>";
				}
				else{
				
					icon += "<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px; />";
				}
			}
			icon += "&#160;&#160;";
			idTest = mapping.getRule().getHeaderRuleId() + "_Test";
			idApprove = mapping.getRule().getHeaderRuleId() + "_Approve";

			if (mapping.getVariableMappingStatus().equals("SCHEDULED_TO_TEST")) {
				sentToTest = 1;
				approve = 1;
			}
			if (mapping.getVariableMappingStatus().equals("OBJECT_TRANSFERRED")) {
				sentToTest = 1;
			}
			if (mapping.getVariableMappingStatus().equals(
					"SCHEDULED_TO_PRODUCTION")
					|| mapping.getVariableMappingStatus().equals("PUBLISHED")) {
				sentToTest = 1;
				approve = 1;
			}
			if (sentToTest != 1 && lock != 1) {
				icon += "&#160;&#160";
				icon += "<a href='#' id=" + idTest
						+ " onclick='openUserCommentsSend2TestDialogForRule(\""
						+ mapping.getRule().getHeaderRuleId()
						+ "\");'><img src='" + contextPath
						+ "/test_icon.gif' alt='Send to Test' title='Send to Test' /></a>";
			}

			if (approve != 1 && lock != 1) {
				if (request.getParameter("authorizedToapprove")
						.equalsIgnoreCase("true")
						|| mapping.getVariableMappingStatus().equals(
								"OBJECT_TRANSFERRED")) {
					icon += "&#160;&#160";
					icon += "<a href='#' id="
							+ idApprove
							+ " onclick='openUserCommentsApproveDialogForRule(\""
							+ mapping.getRule().getHeaderRuleId()
							+ "\");'><img src='" + contextPath
							+ "/approve_icon.gif' alt='Approve' title='Approve'/></a>";
				}

			}
			mappingDetails[4] = icon;

			inProgressSpsAndRuleMappings.add(mappingDetails);
		}
		/******CUSTOM MESSAGE*************************/
		ArrayList inProgressCustMsgMappings = new ArrayList();
		Iterator custIterator = inprogressCustomMsg.iterator();
		while (custIterator.hasNext()) {

			sentToTest = 0;
			approve = 0;
			lock = 1;
			
			idEdit = null;
			idLock = null;
			idTest = null;
			idApprove = null;

			Mapping mapping = (Mapping) custIterator.next();

			Object[] mappingDetails = new Object[6];

			mappingDetails[0] = mapping.getRule().getHeaderRuleId();

			mappingDetails[1] = mapping.getSpsId().getSpsId();
			mappingDetails[2] = mapping.getMessage();
			
			
			String DATE_FORMAT = "MM/dd/yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			mappingDetails[3] = sdf.format(mapping.getLastChangedTmStamp());
			mappingDetails[4] = mapping.getUser().getLastUpdatedUserName();

			/** ************************************************************** */
			String icon = "<a href='#' onclick ='openViewMappingInProgressDialogForCustomMsg(\""
					+ mapping.getRule().getHeaderRuleId()
					+ "\",\""
					+ mapping.getSpsId().getSpsId()
					+ "\");'><img src='"
					+ contextPath + "/search_icon.gif' alt='View' title='View' /></a>";
			icon += "&#160;&#160;";
			if(null == mapping.getLock()){				
				lock = 0;
			}
			else{
				if(mapping.getLock().getLockUserId().equals(userName)){					
					lock =0;
				}
			}
			if(lock == 0){
				idEdit = mapping.getRule().getHeaderRuleId()+mapping.getSpsId().getSpsId()+"_Edit";
				icon += "<a href='#' id='"+idEdit+"' onclick='editMappingForCustomMsg(\""
						+ mapping.getRule().getHeaderRuleId() + "\",\""
						+ mapping.getSpsId().getSpsId() + "\");'><img src='"
						+ contextPath + "/edit_icon.gif' alt='Edit' title='Edit'/></a>";
				
			}
			String unlock = "unlockCustomMsgFromLanding";
			if(lock == 1)	{
				if(request.getParameter("authorizedTounlock").equals("true")){
					idLock = mapping.getRule().getHeaderRuleId()+mapping.getSpsId().getSpsId()+"_Lock";
					icon += "<a	href=#  id='"+idLock+"' " +
					"onclick='unlockMappingFromLocate(\""
					+mapping.getRule().getHeaderRuleId()+"\",\""
					+mapping.getSpsId().getSpsId()+"\",\""+unlock+"\",\""+mapping.getLock().getLockUserId()+"\");'>" +
					"<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px;/></a>";
				}
				else{
					
					icon += "<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px; />";
				}
			}
			icon += "&#160;&#160;";
			idTest = mapping.getRule().getHeaderRuleId()
					+ mapping.getSpsId().getSpsId() + "_Test";
			idApprove = mapping.getRule().getHeaderRuleId()
					+ mapping.getSpsId().getSpsId() + "_Approve";

			if (mapping.getVariableMappingStatus().equals("SCHEDULED_TO_TEST")) {
				sentToTest = 1;
				approve = 1;
			}
			if (mapping.getVariableMappingStatus().equals("OBJECT_TRANSFERRED")) {
				sentToTest = 1;
			}
			if (mapping.getVariableMappingStatus().equals(
					"SCHEDULED_TO_PRODUCTION")
					|| mapping.getVariableMappingStatus().equals("PUBLISHED")) {
				sentToTest = 1;
				approve = 1;
			}
			if (sentToTest != 1 && lock != 1) {
				icon += "<a href='#' id ="
						+ idTest
						+ " onclick='openUserCommentsSend2TestDialogForCustomMsg(\""
						+ mapping.getRule().getHeaderRuleId() + "\",\""
						+ mapping.getSpsId().getSpsId() + "\");'><img src='"
						+ contextPath
						+ "/test_icon.gif' alt='Send to Test' title='Send to Test' /></a>";
				icon += "&#160;&#160;";
			}
			if (approve != 1 && lock != 1) {
				if (request.getParameter("authorizedToapprove")
						.equalsIgnoreCase("true")
						|| mapping.getVariableMappingStatus().equals(
								"OBJECT_TRANSFERRED")) {
					icon += "<a href='#' id ="
							+ idApprove
							+ " onclick='openUserCommentsApproveDialogForCustomMsg(\""
							+ mapping.getRule().getHeaderRuleId() + "\",\""
							+ mapping.getSpsId().getSpsId()
							+ "\");'><img src='" + contextPath
							+ "/approve_icon.gif' alt='Approve' title='Approve' /></a>";
				}
			}

			/********************************************************************/

			mappingDetails[5] = icon;

			inProgressCustMsgMappings.add(mappingDetails);

		}
		/********************************/
		ModelAndView modelAndView1 = new ModelAndView(WebConstants.JSON_VIEW);
		modelAndView1.addObject("aaData", inProgressSpsAndRuleMappings.toArray());
		modelAndView1.addObject("message", inProgressCustMsgMappings.toArray()); 
		long changeInProgressEndTime = System.currentTimeMillis();
		logger.info("Time taken for changeInProgress = "+ (changeInProgressEndTime - changeInProgressStartTime));

		return modelAndView1;

	}
public ModelAndView generateExcel(HttpServletRequest request, HttpServletResponse response) throws EBXException,Exception {
		
		long generateExcelStartTime = System.currentTimeMillis();	
		int noOfRecords = 0;
		int noOfCustomMessage = 0;
		List statusUnmapped = new ArrayList();
		statusUnmapped.add(DomainConstants.UNMAPPED_STATUS);

		List statusMapped = new ArrayList();
		statusMapped.add(DomainConstants.MAPPED_STATUS);

		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();

		String numOfRecords = request.getParameter("getAllPrint");
		if(numOfRecords.equals("true")){
			
			noOfRecords = -1;
			noOfCustomMessage = -1;
		}
		else{
			noOfRecords = 26;
			noOfCustomMessage = 50;
		}
		
		String appendUser = request.getParameter("appendUserPrint");
		if(appendUser.equals("true")){
			loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		}	
		else{			
			loggedInUser = null;
		}
		List statusInProgress = new ArrayList();
		statusInProgress.add("IN-PROGRESS");
		statusInProgress.add("PRINT");
		List inprogressRule = locateRuleIdFacade.getRecords(null,
				statusInProgress, loggedInUser, noOfRecords, 21, null);

		if(null != inprogressRule && ! inprogressRule.isEmpty()){
			Collections.sort(inprogressRule);
		}
		List inprogressSps = locateSpsIdFacade.getRecords(null, statusInProgress,
				loggedInUser, noOfRecords, 21, null);

		
		List inprogressCustomMsg = locateCustomMessageFacade.getRecords(null,
				statusInProgress, loggedInUser, noOfCustomMessage , 21, null);

		ModelAndView modelAndView = null;

				
		response.setHeader("content-disposition", "attachment; filename=" + "Inprogress Mappings.xls");
		ExcelPrintView wpdExcelView = new ExcelPrintView(inprogressSps, inprogressRule, inprogressCustomMsg);
		modelAndView = new ModelAndView(wpdExcelView);
				

		long generateExcelEndTime = System.currentTimeMillis();
		logger.info("Time taken for generateExcel method = "+ (generateExcelEndTime - generateExcelStartTime));
		return modelAndView;
		
	}
	public ModelAndView sendToTestRule(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		String pageName = request.getParameter("pageName");
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		MappingResult result = null;
		String userComments = request
		.getParameter("send2TestMappingRuleComments");
		rule.setHeaderRuleId(request.getParameter("send2teststateflowruleId"));
		mapping.setRule(rule);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingRuleIdFacade.sendToTest(mapping, userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if (null != result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);
				if (null != pageName && pageName.equals("advanceSearchEbx")){
					return WebUtil.redirectToeWPDAdvanceSearchPage(request);
				}else{ 
					return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
		}
		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle
			.getResourceBundle(
					WebConstants.MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS,
					null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null != pageName && pageName.equals("advanceSearchEbx")){
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{ 
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
		
	}

	public ModelAndView sendToTestSps(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		String pageName = request.getParameter("pageName");
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();
		
		String userComments = request
		.getParameter("send2TestMappingSpsComments");
		spsId.setSpsId(request.getParameter("send2teststateflowspsId"));
		mapping.setSpsId(spsId);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingSpsIdFacade.sendToTest(mapping, userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if (null != result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);
				if (null != pageName && pageName.equals("advanceSearchEbx")){
					return WebUtil.redirectToeWPDAdvanceSearchPage(request);
				}else{ 
					return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
		}
		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle
			.getResourceBundle(
					WebConstants.MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS,
					null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null != pageName && pageName.equals("advanceSearchEbx")){
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{ 
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public ModelAndView sendToTestCustomMsg(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		String pageName = request.getParameter("pageName");
		
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();
		Rule rule = new Rule();
		
		String userComments = request
		.getParameter("send2TestMappingCustomMsgComments");
		spsId.setSpsId(request.getParameter("send2teststateflowspsIdCus"));
		mapping.setSpsId(spsId);
		rule.setHeaderRuleId(request
				.getParameter("send2teststateflowruleIdCus"));
		mapping.setRule(rule);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingCustomMessageFacade.sendToTest(mapping,
					userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if (null != result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);
				if (null != pageName && pageName.equals("advanceSearchEbx")) {
					return WebUtil.redirectToeWPDAdvanceSearchPage(request);
				}else{ 
					return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
		}
		if (null != result  && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle
			.getResourceBundle(
					WebConstants.MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS,
					null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null != pageName && pageName.equals("advanceSearchEbx")){
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{ 
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public ModelAndView approveRule(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		String pageName = request.getParameter("pageName");
		
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		MappingResult result = new MappingResult();
		
		String userComments = request
		.getParameter("approvedMappingRuleComments");
		rule.setHeaderRuleId(request.getParameter("approvestateflowruleId"));
		mapping.setRule(rule);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingRuleIdFacade.approve(mapping, userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if (null !=result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);
				if (null != pageName && pageName.equals("advanceSearchEbx")) {
					return WebUtil.redirectToeWPDAdvanceSearchPage(request);
				}else{ 
					return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
		}
		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
			 mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_APPROVE_VIEW_LOCATE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null != pageName && pageName.equals("advanceSearchEbx")){
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{ 
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
		
	}

	public ModelAndView approveSps(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		String pageName = request.getParameter("pageName");
		
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();
		
		String userComments = request
		.getParameter("approvedMappingSpsComments");
		spsId.setSpsId(request.getParameter("approvestateflowspsId"));
		mapping.setSpsId(spsId);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingSpsIdFacade.approve(mapping, userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if (null !=result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);
				
				if (null != pageName && pageName.equals("advanceSearchEbx")) {
					return WebUtil.redirectToeWPDAdvanceSearchPage(request);
				}else{
					return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
		}
		if (null != result && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_APPROVE_VIEW_LOCATE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		
		if (null != pageName && pageName.equals("advanceSearchEbx")){
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public ModelAndView approveCustomMsg(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		String pageName = request.getParameter("pageName");
		
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		Rule rule = new Rule();
		MappingResult result = new MappingResult();
		
		String userComments = request
		.getParameter("approvedMappingCustomMsgComments");
		spsId.setSpsId(request.getParameter("approvestateflowspsIdCus"));
		mapping.setSpsId(spsId);
		rule.setHeaderRuleId(request.getParameter("approvestateflowruleIdCus"));
		mapping.setRule(rule);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingCustomMessageFacade.approve(mapping, userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if (null !=result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);
				if (null != pageName && pageName.equals("advanceSearchEbx")) {
					return WebUtil.redirectToeWPDAdvanceSearchPage(request);
				}else{ 
					return WebUtil.redirectToeWPDBXLandingPage(request);
				}
			}
		}
		if (null != result && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_APPROVE_VIEW_LOCATE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null != pageName && pageName.equals("advanceSearchEbx")){
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{ 
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public ModelAndView sendToTestRuleAjax(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {

		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		MappingResult result = new MappingResult();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		String userComments = request.getParameter(WebConstants.USER_COMNTS)
				.toUpperCase();
		rule.setHeaderRuleId(request.getParameter(WebConstants.RULE_ID));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingRuleIdFacade.sendToTest(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle
					.getResourceBundle(
							WebConstants.MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS,
							null);
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject(WebConstants.RULE_ID, result.getMapping()
					.getRule().getHeaderRuleId());

			List mappings = locateRuleIdFacade.getRecords(mapping, null, null,
					WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

			mapping = (Mapping) mappings.get(0);

			AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails()
					.get(0);
			auditTrail
					.setSystemComments(DomainConstants.AUDIT_STATUS_SEND_TO_TEST);
			modelAndView.addObject(WebConstants.AUDIT_TRAIL, auditTrail);
		} else {
			if(null !=result &&  null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			else {
				String mappingFailure = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_SEND_TO_TEST_FAILURE, null);
				errorMessages.add(mappingFailure);
			}
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return modelAndView;

	}

	public ModelAndView sendToTestSpsAjax(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		String userComments = request.getParameter(WebConstants.USER_COMNTS)
				.toUpperCase();
		spsId.setSpsId(request.getParameter(WebConstants.SPS_ID));
		mapping.setSpsId(spsId);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingSpsIdFacade.sendToTest(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle
					.getResourceBundle(
							WebConstants.MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS,
							null);
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject("spsId", result.getMapping().getSpsId()
					.getSpsId());

			List mappings = locateSpsIdFacade.getRecords(mapping, null, null,
					WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

			mapping = (Mapping) mappings.get(0);

			AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails()
					.get(0);
			auditTrail
					.setSystemComments(DomainConstants.AUDIT_STATUS_SEND_TO_TEST);
			modelAndView.addObject(WebConstants.AUDIT_TRAIL, auditTrail);
		} else {
			if(null !=result && null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			else {
				String mappingFailure = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_SEND_TO_TEST_FAILURE, null);
				errorMessages.add(mappingFailure);
			}
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return modelAndView;
	}

	public ModelAndView sendToTestCustomMsgAjax(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();
		Rule rule = new Rule();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		String userComments = request.getParameter(WebConstants.USER_COMNTS)
				.toUpperCase();
		spsId.setSpsId(request.getParameter(WebConstants.SPS_ID));
		mapping.setSpsId(spsId);

		rule.setHeaderRuleId(request.getParameter(WebConstants.RULE_ID));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingCustomMessageFacade.sendToTest(mapping,
					userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle
					.getResourceBundle(
							WebConstants.MAPPING_SEND_TO_TEST_VIEW_LOCATE_SUCCESS,
							null);
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject(WebConstants.SPS_ID, result.getMapping()
					.getSpsId().getSpsId());
			modelAndView.addObject(WebConstants.RULE_ID, result.getMapping()
					.getRule().getHeaderRuleId());

			List mappings = locateCustomMessageFacade.getRecords(mapping, null,
					null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);

			mapping = (Mapping) mappings.get(0);

			AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails()
					.get(0);
			auditTrail
					.setSystemComments(DomainConstants.AUDIT_STATUS_SEND_TO_TEST);
			modelAndView.addObject(WebConstants.AUDIT_TRAIL, auditTrail);
		} else {
			if(null !=result && null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			else {
				String mappingFailure = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_SEND_TO_TEST_FAILURE, null);
				errorMessages.add(mappingFailure);
			}
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return modelAndView;
	}

	public ModelAndView approveRuleAjax(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
		MappingResult result = new MappingResult();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		String userComments = request.getParameter(WebConstants.USER_COMNTS)
				.toUpperCase();
		rule.setHeaderRuleId(request.getParameter(WebConstants.RULE_ID));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingRuleIdFacade.approve(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_APPROVE_VIEW_LOCATE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject(WebConstants.RULE_ID, result.getMapping()
					.getRule().getHeaderRuleId());

			if (null != result.getMapping() &&  null != result.getMapping().getVariableMappingStatus()) {
				modelAndView.addObject("variableMappingStatus", result.getMapping().getVariableMappingStatus());
			}
			
			List mappings = locateRuleIdFacade.getRecords(mapping, null, null,
					WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
			mapping = (Mapping) mappings.get(0);

			AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails()
					.get(0);
			auditTrail.setSystemComments(DomainConstants.AUDIT_STATUS_APPROVE);
			modelAndView.addObject(WebConstants.AUDIT_TRAIL, auditTrail);
		} else {
			if(null !=result && null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			else {
				String mappingFailure = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_APPROVE_FAILURE, null);
				errorMessages.add(mappingFailure);
			}
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return modelAndView;

	}

	public ModelAndView approveSpsAjax(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		String userComments = request.getParameter(WebConstants.USER_COMNTS)
				.toUpperCase();
		spsId.setSpsId(request.getParameter(WebConstants.SPS_ID));
		mapping.setSpsId(spsId);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingSpsIdFacade.approve(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_APPROVE_VIEW_LOCATE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject(WebConstants.SPS_ID, result.getMapping()
					.getSpsId().getSpsId());
			if (null != result.getMapping() &&  null != result.getMapping().getVariableMappingStatus()) {
				modelAndView.addObject("variableMappingStatus", result.getMapping().getVariableMappingStatus());
			}

			List mappings = locateSpsIdFacade.getRecords(mapping, null, null,
					WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
			mapping = (Mapping) mappings.get(0);
			AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails()
					.get(0);
			auditTrail.setSystemComments(DomainConstants.AUDIT_STATUS_APPROVE);
			modelAndView.addObject(WebConstants.AUDIT_TRAIL, auditTrail);
		} else {
			if(null !=result && null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			else {
				String mappingFailure = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_APPROVE_FAILURE, null);
				errorMessages.add(mappingFailure);
			}
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return modelAndView;
	}

	public ModelAndView approveCustomMsgAjax(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		MappingResult result = new MappingResult();
		Rule rule = new Rule();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		String userComments = request.getParameter(WebConstants.USER_COMNTS)
				.toUpperCase();
		spsId.setSpsId(request.getParameter(WebConstants.SPS_ID));
		mapping.setSpsId(spsId);
		rule.setHeaderRuleId(request.getParameter(WebConstants.RULE_ID));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingCustomMessageFacade.approve(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_APPROVE_VIEW_LOCATE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject(WebConstants.SPS_ID, result.getMapping()
					.getSpsId().getSpsId());
			modelAndView.addObject(WebConstants.RULE_ID, result.getMapping()
					.getRule().getHeaderRuleId());

			if (null != result.getMapping() &&  null != result.getMapping().getVariableMappingStatus()) {
				modelAndView.addObject("variableMappingStatus", result.getMapping().getVariableMappingStatus());
			}
			List mappings = locateCustomMessageFacade.getRecords(mapping, null,
					null, WebConstants.TOTAL_NO_OF_RECORDS,
					WebConstants.TOTAL_NO_OF_AUDIT_TRAIL, null);
			mapping = (Mapping) mappings.get(0);
			AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails()
					.get(0);
			auditTrail.setSystemComments(DomainConstants.AUDIT_STATUS_APPROVE);
			modelAndView.addObject(WebConstants.AUDIT_TRAIL, auditTrail);
		} else {
			if(null !=result && null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			else {
				String mappingFailure = BxResourceBundle.getResourceBundle(
						WebConstants.MAPPING_APPROVE_FAILURE, null);
				errorMessages.add(mappingFailure);
			}
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		return modelAndView;
	}

	public ModelAndView markRuleAsNotApplicable(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		String pageName = request.getParameter("pageName");
		List errorMessages = new ArrayList();
		MappingResult result;

		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();

		String userComments = request.getParameter(
				"notApplicableMappingRuleComments").toUpperCase();
		rule.setHeaderRuleId(request.getParameter("notApplicableruleId"));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingRuleIdFacade.notApplicable(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
			if (null != pageName && pageName.equals("advanceSearchEbx")) {
				return WebUtil.redirectToeWPDAdvanceSearchPage(request);
			} else {
				return WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
		if (null != result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);

				return WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
		if (null != result && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_RULEID_NOT_APPLICABLE, null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null != pageName && pageName.equals("advanceSearchEbx")) {
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		} else {
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public ModelAndView markSpsAsNotApplicable(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		String pageFrom = request.getParameter("pageName");
		MappingResult result;

		SPSId spsId = new SPSId();
		spsId.setSpsId(request.getParameter("notApplicablespsId"));
		Mapping mapping = new Mapping();
		mapping.setSpsId(spsId);
		User user = new User();

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		String userComments = request.getParameter(
				"notApplicableMappingSpsComments").toUpperCase();

		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);

		if (userComments.length() <= WebConstants.USER_COMMENTS_MAX_LENGTH) {
			result = mappingSpsIdFacade.notApplicable(mapping, userComments);
		} else {
			List errorMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_USER_CMNTS_FAILURE, null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
			if (null!= pageFrom && pageFrom.equals("advanceSearchEbx")) {
				return WebUtil.redirectToeWPDAdvanceSearchPage(request);
			} else {
				return WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
		if (null !=result && !result.isStatus()) {
			if(null!= result.getErrorMsgsList()){        					
				List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				sessionMessageTray.setErrorMessages(errorMessages);

				return WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
		if (null != result && result.isStatus()) {
			List infoMessages = new ArrayList();
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_SPSID_NOT_APPLICABLE, null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if (null!= pageFrom && pageFrom.equals("advanceSearchEbx")) {
			return WebUtil.redirectToeWPDAdvanceSearchPage(request);
		} else {
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	
	/**
	 * Unlock rule mapping - landing and locate page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView unlockRuleMapping(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		Rule rule = new Rule();
	
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);

		rule.setHeaderRuleId(request.getParameter(WebConstants.RULE_ID));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		mappingRuleIdFacade.unlock(mapping);
		
		String unlockSuccess = BxResourceBundle.getResourceBundle(
				"mapping.unlockMapping.success", null);
		infoMessages.add(unlockSuccess);			
		modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
		
		modelAndView.addObject(WebConstants.RULE_ID, mapping
				.getRule().getHeaderRuleId());
		modelAndView.addObject("mapping", mapping);
		
		modelAndView.addObject("authorizedToApprove", request.getParameter("authorizedToApprove"));
		return modelAndView;
	}
	/**
	 * Unlock sps mapping - landing and locate page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView unlockSpsMapping(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
			
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);
	
		spsId.setSpsId(request.getParameter(WebConstants.SPS_ID));
		mapping.setSpsId(spsId);
	
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
			
		mappingSpsIdFacade.unlock(mapping);
		
		String unlockSuccess = BxResourceBundle.getResourceBundle(
				"mapping.unlockMapping.success", null);
		infoMessages.add(unlockSuccess);			
		modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
		modelAndView.addObject(WebConstants.SPS_ID, mapping
				.getSpsId().getSpsId());
				
		modelAndView.addObject("mapping", mapping);
		
		modelAndView.addObject("authorizedToApprove", request.getParameter("authorizedToApprove"));
		return modelAndView;
	
	}
	/**
	 * Unlock custom message mapping - landing and locate page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView unlockCustomMsgMapping(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		Rule rule = new Rule();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);
		
		spsId.setSpsId(request.getParameter(WebConstants.SPS_ID));
		mapping.setSpsId(spsId);
		rule.setHeaderRuleId(request.getParameter(WebConstants.RULE_ID));
		mapping.setRule(rule);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		mappingCustomMessageFacade.unlock(mapping);
		
		String unlockSuccess = BxResourceBundle.getResourceBundle(
				"mapping.unlockMapping.success", null);
		infoMessages.add(unlockSuccess);			
		modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
		
		modelAndView.addObject(WebConstants.SPS_ID, mapping
				.getSpsId().getSpsId());
		modelAndView.addObject(WebConstants.RULE_ID, mapping
				.getRule().getHeaderRuleId());
		modelAndView.addObject("mapping", mapping);
		
		modelAndView.addObject("authorizedToApprove", request.getParameter("authorizedToApprove"));
		return modelAndView;
	}
	public MappingFacade getMappingRuleIdFacade() {
		return mappingRuleIdFacade;
	}

	public void setMappingRuleIdFacade(MappingFacade mappingRuleIdFacade) {
		this.mappingRuleIdFacade = mappingRuleIdFacade;
	}

	public MappingFacade getMappingSpsIdFacade() {
		return mappingSpsIdFacade;
	}

	public void setMappingSpsIdFacade(MappingFacade mappingSpsIdFacade) {
		this.mappingSpsIdFacade = mappingSpsIdFacade;
	}

	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}

	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
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

	public MappingFacade getMappingCustomMessageFacade() {
		return mappingCustomMessageFacade;
	}

	public void setMappingCustomMessageFacade(
			MappingFacade mappingCustomMessageFacade) {
		this.mappingCustomMessageFacade = mappingCustomMessageFacade;
	}

}
