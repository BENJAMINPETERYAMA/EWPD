/*
 * <StateFlowController.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.LoginUser;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.application.security.UserUIPermissions;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.ExtendedMessageMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.bx.mapping.web.view.ExcelPrintView;

/**
 * @author UST-GLOBAL This is a controller class
 */
public class StateFlowController extends MultiActionController {

	private VariableMappingFacade variableMappingFacade;

	private LocateFacade locateFacade;

	private SessionMessageTray sessionMessageTray;
	
	private Integer auditInfoLimit;
	
	private static Logger logger = Logger.getLogger(StateFlowController.class);

	/**
	 * Based on the loggedinuser and the no of records the inprogress section
	 * is changed dynamically and json is set in the response
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView multipleViewInProgress(HttpServletRequest request,
			HttpServletResponse response) {
		long changeInProgressStartTime = System.currentTimeMillis();
		String appendUser = request.getParameter("appendUser");
		String loggedInUser = null;
		int noOfRecords = 0;
		if (appendUser.equals("true")) {
			loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
					.toString();
		} else {
			loggedInUser = null;
		}
		String numOfRecords = request.getParameter("getAll");

		if (numOfRecords.equals("true")) {

			noOfRecords = -1;
		} else {
			noOfRecords = 51;
		}
		List inProgressVariables = locateFacade.findAllInProgressVariables(
				loggedInUser, noOfRecords, false);
		BxUtil.encodeMappingsList(inProgressVariables);
		inProgressVariables = BxUtil.concatBreak(inProgressVariables);
		Iterator variableIterator = inProgressVariables.iterator();
		String contextPath = request.getContextPath() + "/images";
		ArrayList inProgressMappings = new ArrayList();
		int sentToTest = 0;
		int approve = 0;
		int lock = 1;
		String idTest = null;
		String idApprove = null;
		String userName = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		String idEdit = null;
		String idLock = null;
		while (variableIterator.hasNext()) {

			sentToTest = 0;
			approve = 0;
			lock = 1;

			idTest = null;
			idApprove = null;

			Mapping mapping = (Mapping) variableIterator.next();

			Object[] mappingDetails = new Object[6];
			mappingDetails[0] = mapping.getVariable().getVariableSystem();

			if (!mapping.isFinalized()) {
				mappingDetails[0] = "Finalized" + mappingDetails[0];
			}
			String indicator="";
			if(!StringUtils.isBlank(mapping.getAuditLock())){
				if("Y".equals(mapping.getAuditLock())){
					indicator="<img src='"
						+contextPath+"/AuditLockIndicatorNew.jpg' alt='Lock' title='Lock' id="
						+ "AuditLockImg_"+mapping.getVariable().getVariableId()+" style='height:9px;width:9px'  />";
				}else if(("N").equalsIgnoreCase(mapping.getAuditLock())){
					indicator="<div id="
						+ "AuditLockImg_"+mapping.getVariable().getVariableId()+"  ></div>";
				}	
			}
			
			mappingDetails[1] = mapping.getVariable().getVariableId()+""+indicator;
			mappingDetails[2] = mapping.getVariable().getDescription();

			String DATE_FORMAT = "MM/dd/yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			mappingDetails[3] = sdf.format(mapping.getVariable()
					.getCreatedDate());
			mappingDetails[4] = mapping.getUser().getLastUpdatedUserName();
			String icons = "<a href=# onclick='openViewMappingInProgressDialog(\""
					+ mapping.getVariable().getVariableId()
					+ "\");'><img src='"
					+ contextPath
					+ "/search_icon.gif' alt='View' title='View' style=height:15px; /></a>";
			icons += "&#160;&#160;&#160;";
			if (null == mapping.getLock()) {
				lock = 0;
			} else {
				if (mapping.getLock().getLockUserId().equals(userName)) {
					lock = 0;
				}
			}
			String authorizedEditLockVar = request.getParameter("auditLockEditStatus");
			if (lock == 0) {
				if (mapping.getAuditLock().equals("N") || (authorizedEditLockVar.equals("true") && mapping.getAuditLock().equals("Y"))) {
					idEdit = mapping.getVariable().getVariableId() + "_Edit";
					icons += "<a href=# id='"
							+ idEdit
							+ "' onclick='editMapping(\""
							+ mapping.getVariable().getVariableId()
							+ "\");'><img src='"
							+ contextPath
							+ "/edit_icon.gif' alt='Edit' title='Edit' style=height:15px; /></a>";
				}
				if (!authorizedEditLockVar.equals("true") && mapping.getAuditLock().equals("Y")) {
					icons += "<span id=\"" + mapping.getVariable().getVariableId() + "_Space\" style=\"display:none;\"></span>";
				}
			}
			String unlock = "unlockVariableFromLanding";
			if (lock == 1) {
				if(request.getParameter("authorizedTounlock").equals("true")){
				idLock = mapping.getVariable().getVariableId() + "_Lock";
				icons += "<a href=# id='"
						+ idLock
						+ "' onclick='unlockMappingFromLocate(\""
						+ mapping.getVariable().getVariableId()
						+ "\",\""
						+ unlock
						+ "\",\""
						+ mapping.getLock().getLockUserId()
						+ "\",\""
						+ mapping.getAuditLock()
						+ "\",\""
						+ mapping.getVariable().getVariableSystem()
						+ "\",\""
						+ mapping.getVariable().getDescription()
						+ "\");'><img src='"
						+ contextPath
						+ "/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px; /></a>";
				}
				else{

					icons += "<img src='"+contextPath+"/lock_icon.jpg' alt='Lock' title='Lock' style=height:15px; />";
				}
			}
			idTest = mapping.getVariable().getVariableId() + "_Test";
			idApprove = mapping.getVariable().getVariableId() + "_Approve";

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
				icons += "&#160;&#160;&#160;";
				icons += "<a href='#' id="
						+ idTest
						+ " onclick='openUserCommentsSend2TestDialog(\""
						+ mapping.getVariable().getVariableId()
						+ "\");'><img	src='"
						+ contextPath
						+ "/test_icon.gif' alt='Send to Test' title='Send to Test' style=height:15px; /></a>";
			}
			if (approve != 1 && lock != 1) {
				if (request.getParameter("authorizedToapprove")
						.equalsIgnoreCase("true")
						|| mapping.getVariableMappingStatus().equals(
								"OBJECT_TRANSFERRED")) {
					icons += "&#160;&#160;&#160;";
					icons += "<a href='#' id="
							+ idApprove
							+ " onclick='openUserCommentsApproveDialog(\""
							+ mapping.getVariable().getVariableId()
							+ "\");'><img src='"
							+ contextPath
							+ "/approve_icon.gif' alt='Approve' title='Approve' style=height:15px; /></a>";
				}
			}
			icons += "&#160;&#160;&#160;";
			if(!StringUtils.isBlank(mapping.getAuditLock())){
				if(request.getParameter("authorizedToAuditUnlock").equals("true")){
					if("Y".equalsIgnoreCase(mapping.getAuditLock()) && lock != 1){
						icons +="<a href='#' id="
							+ "AuditLock_"+mapping.getVariable().getVariableId()
							+" onclick = 'openUserCommentsLockDialog (\""+mapping.getVariable().getVariableId()
							+	"\",\"" + mapping.getVariable().getVariableSystem()+"\",\"" + userName + "\",\"" + mapping.getVariable().getDescription() + "\",\"Unlock\");' >" 
							+"<img src='"+contextPath+"/auditUnLockIndicator.GIF' alt='Unlock' title='Unlock' style='height:15px;width:12px;'/></a>";
							
					}
					
				}
				if(request.getParameter("authorizedToAuditLock").equals("true")){
					if("N".equalsIgnoreCase(mapping.getAuditLock()) && lock != 1){
						icons +="<a href='#' id="
							+ "AuditUnLock_"+mapping.getVariable().getVariableId()
							+" onclick = 'openUserCommentsLockDialog (\""+mapping.getVariable().getVariableId()
							+	"\",\""+mapping.getVariable().getVariableSystem()+"\",\""+userName + "\",\"" + mapping.getVariable().getDescription() + "\",\"Lock\");' >" 
							+"<img src='"+contextPath+"/AuditLockIndicatorNew.jpg' alt='Lock' title='Lock' style='height:15px;width:12px;'/></a>";
							
					}
				}
			}
			if(lock==1 && "Y".equalsIgnoreCase(mapping.getAuditLock()) && request.getParameter("authorizedToAuditUnlock").equals("true")){
				icons +="<div id='AuditLock_"+mapping.getVariable().getVariableId()+"'  ></div>";
			}
			if(lock==1 && "N".equalsIgnoreCase(mapping.getAuditLock()) && request.getParameter("authorizedToAuditLock").equals("true")){
				icons +="<div id='AuditUnLock_"+mapping.getVariable().getVariableId()+"'  ></div>";
			}
			if(!StringUtils.isBlank(mapping.getAuditLock())){
				icons +="<input type='hidden' name='auditLockStatus' id='auditLockStatus' value='"+mapping.getAuditLock()+"' />";
			}
			if(!StringUtils.isBlank(mapping.getAuditLock())){
				icons +="<input type='hidden' name='auditLockEditStatus' id='auditLockEditStatus' value='"+authorizedEditLockVar+"'/>";
			}
			mappingDetails[5] = icons;
			inProgressMappings.add(mappingDetails);	
			
		}
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);
		modelAndView.addObject("aaData", inProgressMappings.toArray());
		long changeInProgressEndTime = System.currentTimeMillis();
		logger.info("Time taken for changeInProgress = "+ (changeInProgressEndTime - changeInProgressStartTime));

		return modelAndView;

	}

	/**
	 * Unlock variable mapping - landing and locate page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView unlockVariableMapping(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		Variable variable = new Variable();

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);
		String variableDesc = request.getParameter("variableDesc");
		String system = request.getParameter("systemName");
		variable.setVariableId(request.getParameter("variableId"));
		mapping.setVariable(variable);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		variableMappingFacade.unlock(mapping);

		String unlockSuccess = BxResourceBundle.getResourceBundle(
				"mapping.unlockMapping.success", null);
		infoMessages.add(unlockSuccess);
		modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);

		modelAndView.addObject("variableId", mapping.getVariable()
				.getVariableId());
		modelAndView.addObject("mapping", mapping);

		modelAndView.addObject("authorizedToApprove", request
				.getParameter("authorizedToApprove"));
		modelAndView.addObject("authorizedToAuditUnlock", request
				.getParameter("authorizedToAuditUnlock"));
		modelAndView.addObject("authorizedToAuditLock", request
				.getParameter("authorizedToAuditLock"));
		String auditLockStatus=request.getParameter("auditLockStatus");
		modelAndView.addObject("auditLockStatus", auditLockStatus);
		modelAndView.addObject("variableDesc", variableDesc);
		modelAndView.addObject("systemName", system);
		
		modelAndView.addObject(WebConstants.WARNING_MESSAGES, sessionMessageTray.getAndClearWarningMessages());
		//sessionMessageTray.clearWarningMessages();
	
		return modelAndView;
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * auditLockVariable method is used to unlock a locked variable
	 * it only unlocks a locked variable if the variable is not locked earlier
	 */
	public ModelAndView auditLockVariable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//initializing variables
		boolean auditUnLock = false;
		boolean isMappingBeingModified=false;
		Mapping mapping = new Mapping();
		User user = new User();
		Variable variable = new Variable();
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);
		
		//Getting Objects from Request
		String variableId = request.getParameter("variableId");
		String variableDesc = request.getParameter("variableDesc");
		String system = request.getParameter("systemName");
		String userComment = request.getParameter("userComment");
		if (null != userComment) {
			userComment = userComment.toUpperCase();
		}
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		Mapping currentMapping = variableMappingFacade.retrieveMapping(variableId);
		if (null != currentMapping && null != currentMapping.getVariable() 
				&& null != currentMapping.getVariable().getDescription()) {
			variableDesc = currentMapping.getVariable().getDescription();
		}
		//setting variables
		variable.setVariableId(variableId);
		variable.setDescription(variableDesc);
		variable.setAuditLock(false);
		user.setLastUpdatedUserName(userId);
		mapping.setVariable(variable);
		mapping.setUser(user);
		mapping.setVariableMappingStatus(DomainConstants.UNLOCKED);
		//setting back to the response
		modelAndView.addObject("variableId", mapping.getVariable().getVariableId());
		
		modelAndView.addObject("variableDesc", mapping.getVariable()
				.getDescription());
		
		modelAndView.addObject("systemName", request.getParameter("systemName"));
		modelAndView.addObject("userName", request.getAttribute(SecurityConstants.USER_NAME).toString());
		
		
		/*checking whether the variable is already unlocked or not
		 * if No, then only the variable is sent for unlocking.
		 */
		try{
			variableMappingFacade.retrieveTransactionLock(Mapping.class.getName(), variableId,userId);
			isMappingBeingModified=variableMappingFacade.isMappingBeingModified(variableId);
			if(isMappingBeingModified){
				auditUnLock=variableMappingFacade.isAuditUnLockInTemp(variableId);
				if(auditUnLock){
					modelAndView.addObject("isLockedOrUnlocked", "unlocked");
				}else{
					variableMappingFacade.auditTempLock(mapping, system);
					variableMappingFacade.logMapping(mapping, userComment, null);
					List auditTrailList = variableMappingFacade.retrieveAuditTrail(variableId, auditInfoLimit);
					modelAndView.addObject("auditTrailList",getAuditTrail(auditTrailList,variableId));
				}
			}else{
				auditUnLock=variableMappingFacade.isAuditUnLock(variableId);
				if(auditUnLock){
					modelAndView.addObject("isLockedOrUnlocked", "unlocked");
				}else{
					variableMappingFacade.auditLock(mapping, system);
					variableMappingFacade.logMapping(mapping, userComment, null);
					List auditTrailList = variableMappingFacade.retrieveAuditTrail(variableId, auditInfoLimit);
					modelAndView.addObject("auditTrailList",getAuditTrail(auditTrailList,variableId));
				}
			}
		}catch(MappingLockedByAnotherUserException e){
			List list = new ArrayList(1);
			list.add(e.getMessage());
			modelAndView.addObject(list);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, list);
			return modelAndView;
		}
		
		return modelAndView;
		
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * auditUnLockVariable method is used to lock a unlocked variable
	 * it only locks a unlocked variable if the variable is not unlocked earlier
	 */
	public ModelAndView auditUnLockVariable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//initializing variables
		boolean auditLock=true;
		boolean isMappingBeingModified=false;
		Mapping mapping = new Mapping();
		User user = new User();
		Variable variable = new Variable();
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW);
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		
		//getting values from the session
		String variableId = request.getParameter("variableId");
		String variableDesc = request.getParameter("variableDesc");
		String system = request.getParameter("systemName");
		String userComment = request.getParameter("userComment");
		if (null != userComment) {
			userComment = userComment.toUpperCase();
		}
		Mapping currentMapping = variableMappingFacade.retrieveMapping(variableId);
		if (null != currentMapping && null != currentMapping.getVariable() 
				&& null != currentMapping.getVariable().getDescription()) {
			variableDesc = currentMapping.getVariable().getDescription();
		}
		variable.setVariableId(variableId);
		variable.setAuditLock(true);
		user.setLastUpdatedUserName(userId);
		variable.setDescription(variableDesc);
		mapping.setVariable(variable);
		mapping.setUser(user);
		mapping.setVariableMappingStatus(DomainConstants.LOCKED);
		
		//setting objects back to the response
		modelAndView.addObject("variableId", mapping.getVariable()
				.getVariableId());

		modelAndView.addObject("variableDesc", mapping.getVariable()
				.getDescription());
		
		modelAndView.addObject("systemName", request.getParameter("systemName"));
		modelAndView.addObject("userName", request.getAttribute(SecurityConstants.USER_NAME).toString());
		
		/*checking whether the variable is already locked or not
		 * if No, then only the variable is sent for locking.
		 */
		try {
			variableMappingFacade.retrieveTransactionLock(Mapping.class
					.getName(), variableId, userId);

			isMappingBeingModified = variableMappingFacade
					.isMappingBeingModified(variableId);
			if (isMappingBeingModified) {
				auditLock = variableMappingFacade.isAuditLockInTemp(variableId);
				if (auditLock) {
					modelAndView.addObject("isLockedOrUnlocked", "locked");
				} else {
					variableMappingFacade.auditTempUnLock(mapping, system);
					variableMappingFacade.logMapping(mapping, userComment,
							DomainConstants.LOCKED);
					variableMappingFacade.removeExistingAuditTrail(variableId,
							system);
					List auditTrailList = variableMappingFacade
							.retrieveAuditTrail(variableId, auditInfoLimit);
					modelAndView.addObject("auditTrailList", getAuditTrail(
							auditTrailList, variableId));
				}
			} else {
				auditLock = variableMappingFacade.isAuditLock(variableId);
				if (auditLock) {
					modelAndView.addObject("isLockedOrUnlocked", "locked");
				} else {
					variableMappingFacade.auditUnLock(mapping, system);
					variableMappingFacade.logMapping(mapping, userComment,
							DomainConstants.LOCKED);
					variableMappingFacade.removeExistingAuditTrail(variableId,
							system);
					List auditTrailList = variableMappingFacade
							.retrieveAuditTrail(variableId, auditInfoLimit);
					modelAndView.addObject("auditTrailList", getAuditTrail(
							auditTrailList, variableId));
				}
			}
		} catch (MappingLockedByAnotherUserException e) {
			List list = new ArrayList(1);
			list.add(e.getMessage());
			modelAndView.addObject(list);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, list);
			return modelAndView;
		}
		return modelAndView;
	}

	// from view page
	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Mapping mapping = new Mapping();
		User user = new User();
		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		CreateOrEditMappingResult result = null;
		ModelAndView modelAndView = new ModelAndView("jsonView");

		String variableId = request.getParameter("variableId");
		String userComments = request.getParameter("userComments")
				.toUpperCase();
		String variableDesc = request.getParameter("variableDesc");

		Variable variable = new Variable();
		variable.setVariableId(variableId);
		variable.setDescription(variableDesc);		
		mapping.setVariable(variable);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= 250) {
			result = variableMappingFacade.sendToTest(mapping, userComments);

		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		List warningMessages = new ArrayList();
		if (result != null && result.getStatus() == 1) {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.sendToTestFromViewOrLocate.success", null);
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject("variableId", result.getMapping()
					.getVariable().getVariableId());
			AuditTrail auditTrail = (AuditTrail) result.getMapping()
					.getAuditTrails().get(0);
			auditTrail
					.setSystemComments(DomainConstants.AUDIT_STATUS_SEND_TO_TEST);
			modelAndView.addObject("auditTrail", auditTrail);

			if (null != result.getWarningMsgsList()) {
				for (Iterator itrerator = result.getWarningMsgsList()
						.iterator(); itrerator.hasNext();) {
					List warningMsg = (List) itrerator.next();
					if (warningMsg != null) {
						for (Iterator itr = warningMsg.iterator(); itr
								.hasNext();) {
							String msg = (String) itr.next();
							warningMessages.add(msg);
						}
					}
				}
			}
			modelAndView.addObject(WebConstants.WARNING_MESSAGES,
					warningMessages);

		} else {
			String mappingFailure = BxResourceBundle.getResourceBundle(
					"mapping.sendToTest.failure", null);
			errorMessages.add(mappingFailure);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		return modelAndView;
	}

	public ModelAndView notApplicable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = createMappingObject(request);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		mapping.getUser().setLastUpdatedUserName(userId);
		mapping.getUser().setCreatedUserName(userId);
		String selectedVariable = mapping.getVariable().getVariableId();
		Object sessionObj = request.getSession().getAttribute(SecurityConstants.USER);
        if (sessionObj != null && (sessionObj instanceof LoginUser)) {
            LoginUser loginUser = (LoginUser) sessionObj;
            String pageNameTemp = pageName;
            if (null != pageName && pageName.trim().equals("advanceSearch")){
            	pageNameTemp = "advancesearch";
            }
            UserUIPermissions userUIPermissions = new UserUIPermissions(pageNameTemp, loginUser);
            if (!userUIPermissions.isAuthorizedEditLockVar()) {
        		boolean auditLock = false;
        		if (variableMappingFacade.isMappingBeingModified(selectedVariable)) {
        			auditLock=variableMappingFacade.isAuditLockInTemp(selectedVariable);
        		} else {
        			auditLock=variableMappingFacade.isAuditLock(selectedVariable);
        		}
        		if (auditLock) {
        			List errorMessages = new ArrayList();
        			errorMessages.add(WebConstants.LOCK_ERROR_MSG);
        			sessionMessageTray.setErrorMessages(errorMessages);
        			if (null != pageName && pageName.trim().equals("advanceSearch")){
        				return WebUtil.redirectToWPDAdvanceSearchPage(request);
        			} else {
        				return WebUtil.redirectToLandingPage(request);
        			}
        		}
            }
        }
		CreateOrEditMappingResult result = variableMappingFacade.notApplicable(
				mapping, request.getParameter("changeComments").toUpperCase());
		if (null != result) {
			result.setMapping(mapping);
		}
		List infoMessages = new ArrayList();
		
		if (result != null && result.getStatus() == 1) {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.notApplicable", null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		logger.debug("CALL FROM PAGE "+pageName);
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return  WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	}

	// from view page
	public ModelAndView approve(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Mapping mapping = new Mapping();
		User user = new User();
		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		CreateOrEditMappingResult result = null;
		ModelAndView modelAndView = new ModelAndView("jsonView");

		String variableId = request.getParameter("variableId");
		String userComments = request.getParameter("userComments")
				.toUpperCase();
		//String variableDesc = request.getParameter("variableDesc");

		Variable variable = new Variable();
		variable.setVariableId(variableId);
		//variable.setDescription(variableDesc);
		mapping.setVariable(variable);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= 250) {
			result = variableMappingFacade.approve(mapping, userComments);

		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}
		List warningMessages = new ArrayList();
		if (result != null && result.getStatus() == 1) {
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approveFromViewOrLocate.scheduledtoproduction.success", null);
			}
			else {
			mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.approveFromViewOrLocate.success", null);
			}
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject("variableId", result.getMapping()
					.getVariable().getVariableId());
			AuditTrail auditTrail = (AuditTrail) result.getMapping()
					.getAuditTrails().get(0);
			auditTrail.setSystemComments(DomainConstants.AUDIT_STATUS_APPROVE);
			modelAndView.addObject("auditTrail", auditTrail);

			if (null != result.getWarningMsgsList()) {
				for (Iterator itrerator = result.getWarningMsgsList()
						.iterator(); itrerator.hasNext();) {
					List warningMsg = (List) itrerator.next();
					if (warningMsg != null) {
						for (Iterator itr = warningMsg.iterator(); itr
								.hasNext();) {
							String msg = (String) itr.next();
							warningMessages.add(msg);
						}
					}
				}
			}
			modelAndView.addObject(WebConstants.WARNING_MESSAGES,
					warningMessages);
		} else {
			String mappingFailure = BxResourceBundle.getResourceBundle(
					"mapping.approve.failure", null);
			errorMessages.add(mappingFailure);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		return modelAndView;
	}

	public ModelAndView saveAndsendToTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		List errorMessagesList = new ArrayList();
		List errorMessagesList1 = new ArrayList();
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();

		String errMsg = null;
		Mapping mapping = createMappingObject(request);

		CreateOrEditMappingResult result = variableMappingFacade
				.saveAndsendToTest(mapping, request.getParameter(
						"changeComments").toUpperCase());
		// SSCR19537 April 04
		if (null != result && null != result.getMapping() && null != result.getMapping().getEb03()
				&& null != result.getMapping().getEb03().getEb03Association() 
				&& !result.getMapping().getEb03().getEb03Association().isEmpty()) {
			eb03AssnList = result.getMapping().getEb03().getEb03Association();
		}
		//			If any of the hippacode validation fails, it should show the validation messages in the Create Mapping page
		if (null != result
				&& result.getStatus() == DomainConstants.FAILURE_STATUS) {
			if (null != result.getErrorMsgsList()
					&& !result.getErrorMsgsList().isEmpty()
					&& result.getErrorMsgsList().size() > 0) {
				errorMessagesList1 = result.getErrorMsgsList();
			}
			ModelAndView modelAndView1 = new ModelAndView("editmapping");

			if (null != errorMessagesList1 && !errorMessagesList1.isEmpty()
					&& errorMessagesList1.size() > 0) {
				for (int i = 0; i < errorMessagesList1.size(); i++) {
					if (null != errorMessagesList1.get(i)
							&& !errorMessagesList1.isEmpty()
							&& errorMessagesList1.size() > 0) {
						List nwList = new ArrayList();
						nwList = (ArrayList) errorMessagesList1.get(i);
						if (null != nwList && !nwList.isEmpty()) {
							for (int j = 0; j < nwList.size(); j++) {
								errMsg = (String) nwList.get(j);
								errorMessagesList.add(errMsg);
							}

						}

					}
				}
			}

			modelAndView1.addObject("error_messages", errorMessagesList);

			mapping = result.getMapping();
			mapping.setPageFrom(pageName);
			/**
			 * mtm code change
			 */
			boolean finalized = mapping.isFinalized();
			if (finalized) {
				mapping.setFinalized(false);
			} else {
				mapping.setFinalized(true);
			}
			/**
			 * end
			 */
			if ((null != mapping.getMsg_type_required())
					&& mapping.getMsg_type_required().equals("Y")) {

				mapping.setMsg_type_required("true");
			} else {
				mapping.setMsg_type_required("false");
			}
			if ((null != mapping.getSensitiveBenefitIndicator())
					&& mapping.getSensitiveBenefitIndicator().equals("Y")) {

				mapping.setSensitiveBenefitIndicator("true");
			} else {
				mapping.setSensitiveBenefitIndicator("false");
			}
			if (null != result.getMapping().getVariableList() && result.getMapping().getVariableList().size() > 0) {
				BxUtil.encodeVariable((Variable) result.getMapping().getVariableList().get(0));
			}
			result.getMapping().setPageFrom(pageName);
			modelAndView1.addObject("mapping", result.getMapping());
			modelAndView1.addObject("variableList", result.getMapping()
					.getVariableList());
			modelAndView1.addObject("hpnMapgList", result.getMapping()
					.getHpnMapgList());
			modelAndView1.addObject("changeComments", result.getUserComments());
			modelAndView1.addObject("result", result);
			List warningMessages = new ArrayList();
			if (null != result.getWarningMsgsList()) {
				for (Iterator itrerator = result.getWarningMsgsList()
						.iterator(); itrerator.hasNext();) {
					List warningMsg = (List) itrerator.next();
					if (warningMsg != null) {
						for (Iterator itr = warningMsg.iterator(); itr
								.hasNext();) {
							String msg = (String) itr.next();
							warningMessages.add(msg);
						}
					}
				}
			}
			modelAndView1.addObject(WebConstants.WARNING_MESSAGES,
					warningMessages);
			// SSCR19537 April04
			modelAndView1.addObject("eB03AssnList",eb03AssnList);
			return modelAndView1;
		}

		List infoMessages = new ArrayList();
		List warningMessages = new ArrayList();
		if (result != null && result.getStatus() == 1) {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.sendToTest.success", null);
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);

			if (null != result.getWarningMsgsList()) {
				for (Iterator itrerator = result.getWarningMsgsList()
						.iterator(); itrerator.hasNext();) {
					List warningMsg = (List) itrerator.next();
					if (warningMsg != null) {
						for (Iterator itr = warningMsg.iterator(); itr
								.hasNext();) {
							String msg = (String) itr.next();
							warningMessages.add(msg);
						}
					}
				}
			}
			sessionMessageTray.setWarningMessages(warningMessages);

		}
		logger.debug("CALL FROM PAGE "+pageName);
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return  WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	}

	public ModelAndView redirectToEditMappingPage(ModelAndView modelAndView,
			CreateOrEditMappingResult result) {

		Mapping mapping = result.getMapping();
		List variableWithInfoList = result.getMapping().getVariableList();
		modelAndView.addObject("mapping", mapping);
		modelAndView.addObject("changeComments", result.getUserComments());
		if (null != mapping.getMsg_type_required()
				&& mapping.getMsg_type_required().equals("Y")) {

			mapping.setMsg_type_required("true");
		} else {
			mapping.setMsg_type_required("false");
		}
		if (null != mapping.getSensitiveBenefitIndicator()
				&& mapping.getSensitiveBenefitIndicator().equals("Y")) {

			mapping.setSensitiveBenefitIndicator("true");
		} else {
			mapping.setSensitiveBenefitIndicator("false");
		}
		/**
		 * MTM CODE CHANGES
		 */
		if (mapping.isFinalized() == false) {

			mapping.setFinalized(true);
		} else {
			mapping.setFinalized(false);
		}

		/**
		 * END
		 */
		modelAndView.addObject("variableWithInfoList", variableWithInfoList);

		return modelAndView;
	}

	public ModelAndView saveAndApprove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		List errorMessagesList = new ArrayList();
		List errorMessagesList1 = new ArrayList();
		List <EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		String errMsg = null;
		Mapping mapping = createMappingObject(request);
		
		CreateOrEditMappingResult result = variableMappingFacade
		.saveAndApprove(mapping, request.getParameter("changeComments")
				.toUpperCase());
		// SSCR19537 April 04
		if (null != result && null != result.getMapping() && null != result.getMapping().getEb03()
				&& null != result.getMapping().getEb03().getEb03Association() 
				&& !result.getMapping().getEb03().getEb03Association().isEmpty()) {
			eb03AssnList = result.getMapping().getEb03().getEb03Association();
		}
		if (null != result
				&& result.getStatus() == DomainConstants.FAILURE_STATUS) {
			if (null != result.getErrorMsgsList()
					&& !result.getErrorMsgsList().isEmpty()
					&& result.getErrorMsgsList().size() > 0) {
				errorMessagesList1 = result.getErrorMsgsList();
			}
			ModelAndView modelAndView1 = new ModelAndView("editmapping");
			
			if (null != errorMessagesList1 && !errorMessagesList1.isEmpty()
					&& errorMessagesList1.size() > 0) {
				for (int i = 0; i < errorMessagesList1.size(); i++) {
					if (null != errorMessagesList1.get(i)
							&& !errorMessagesList1.isEmpty()
							&& errorMessagesList1.size() > 0) {
						
						List nwList = (ArrayList) errorMessagesList1.get(i);
						if (null != nwList && !nwList.isEmpty()) {
							for (int j = 0; j < nwList.size(); j++) {
								errMsg = (String) nwList.get(j);
								errorMessagesList.add(errMsg);
							}
							
						}
						
					}
				}
			}
			
			modelAndView1.addObject("error_messages", errorMessagesList);
			
			mapping = result.getMapping();
			/**
			 * mtm code change
			 */
			boolean finalized = mapping.isFinalized();
			if (finalized) {
				mapping.setFinalized(false);
			} else {
				mapping.setFinalized(true);
			}
			/**
			 * end
			 */
			if ((null != mapping.getMsg_type_required())
					&& mapping.getMsg_type_required().equals("Y")) {
				
				mapping.setMsg_type_required("true");
			} else {
				mapping.setMsg_type_required("false");
			}
			if ((null != mapping.getSensitiveBenefitIndicator())
					&& mapping.getSensitiveBenefitIndicator().equals("Y")) {
				
				mapping.setSensitiveBenefitIndicator("true");
			} else {
				mapping.setSensitiveBenefitIndicator("false");
			}
			if (null != result.getMapping().getVariableList() && result.getMapping().getVariableList().size() > 0) {
				BxUtil.encodeVariable((Variable) result.getMapping().getVariableList().get(0));
			}
			result.getMapping().setPageFrom(pageName);
			modelAndView1.addObject("mapping", result.getMapping());
			modelAndView1.addObject("variableList", result.getMapping()
					.getVariableList());
			modelAndView1.addObject("hpnMapgList", result.getMapping()
					.getHpnMapgList());
			modelAndView1.addObject("changeComments", result.getUserComments());
			modelAndView1.addObject("result", result);
			// SSCR19537 April 04
			modelAndView1.addObject("eB03AssnList",eb03AssnList);
			return modelAndView1;
		}
		//else{
		
		List infoMessages = new ArrayList();
		List warningMessages = new ArrayList();
		if (result != null && result.getStatus() == 1) {
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus()) 
				&& null != result.getMapping() 
				&& DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(
						result.getMapping().getVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approve.scheduledtoproduction.success", null);
			}
			else {
			mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.approve.success", null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
			
			if (null != result.getWarningMsgsList()) {
				for (Iterator itrerator = result.getWarningMsgsList()
						.iterator(); itrerator.hasNext();) {
					List warningMsg = (List) itrerator.next();
					if (warningMsg != null) {
						for (Iterator itr = warningMsg.iterator(); itr
						.hasNext();) {
							String msg = (String) itr.next();
							warningMessages.add(msg);
						}
					}
				}
			}
			sessionMessageTray.setWarningMessages(warningMessages);
		}
		logger.debug("CALL FROM PAGE "+pageName);
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return  WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	}

	public ModelAndView sendToTestFromLocate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Mapping mapping = new Mapping();
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);
		CreateOrEditMappingResult result = variableMappingFacade.sendToTest(
				mapping, request.getParameter("userComments").toUpperCase());
		ModelAndView modelAndView = new ModelAndView("mappinglocateresult");
		modelAndView.addObject("mapping", result);
		return modelAndView;
	}

	public ModelAndView sendToTestFromLaunch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Mapping mapping = new Mapping();
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		mapping.getUser().setLastUpdatedUserName(userId);
		CreateOrEditMappingResult result = variableMappingFacade.sendToTest(
				mapping, request.getParameter("userComments").toUpperCase());
		ModelAndView modelAndView = new ModelAndView("viewlandingpage");
		modelAndView.addObject("mapping", result);
		return modelAndView;
	}

	public ModelAndView generateExcel(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, Exception {

		String appendUser = request.getParameter("appendUserPrint");
		String loggedInUser = null;
		int noOfRecords = 0;
		if (appendUser.equals("true")) {
			loggedInUser = request.getAttribute(SecurityConstants.USER_NAME)
					.toString();
		} else {
			loggedInUser = null;
		}
		String numOfRecords = request.getParameter("getAllPrint");

		if (numOfRecords.equals("true")) {

			noOfRecords = -1;
		} else {
			noOfRecords = 51;
		}
		List inProgressVariables = locateFacade.findAllInProgressVariables(
				loggedInUser, noOfRecords, true);
		inProgressVariables = BxUtil.concatBreak(inProgressVariables);

		Collections.sort(inProgressVariables);
		ModelAndView modelAndView = null;
		response.setHeader("content-disposition", "attachment; filename="
				+ "Inprogress Mappings.xls");
		ExcelPrintView wpdExcelView = new ExcelPrintView(inProgressVariables);
		modelAndView = new ModelAndView(wpdExcelView);

		long generateExcelEndTime = System.currentTimeMillis();
		logger.debug("generateExcelEndTime is "+generateExcelEndTime);

		return modelAndView;

	}

	private Mapping createMappingObject(HttpServletRequest request) {

		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		User user = new User();
		HippaCodeValue hippaCodeValue;
		mapping.setVariable(variable);
		mapping.setEb01(new HippaSegment());
		mapping.setEb02(new HippaSegment());
		mapping.setEb03(new HippaSegment());
		mapping.setEb06(new HippaSegment());
		mapping.setEb09(new HippaSegment());
		mapping.setIi02(new HippaSegment());
		mapping.setHsd01(new HippaSegment());
		mapping.setHsd02(new HippaSegment());
		mapping.setHsd03(new HippaSegment());
		mapping.setHsd04(new HippaSegment());
		mapping.setHsd05(new HippaSegment());
		mapping.setHsd06(new HippaSegment());
		mapping.setHsd07(new HippaSegment());
		mapping.setHsd08(new HippaSegment());
		mapping.setNoteTypeCode(new HippaSegment());
		mapping.setAccum(new HippaSegment());
		mapping.setUtilizationMgmntRule(new HippaSegment());
		//NM1 Message Segment
		mapping.setNm1MessageSegment(new HippaSegment());

		List hippaCodeEB01List = new ArrayList();
		List<ExtendedMessageMapping> extndMsgEB01List = new ArrayList<ExtendedMessageMapping>();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb01Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB01Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("eb01SysId")));
		hippaCodeEB01List.add(hippaCodeValue);
		mapping.getEb01().setName(DomainConstants.EB01_NAME);
		mapping.getEb01().setHippaCodeSelectedValues(hippaCodeEB01List);

		ExtendedMessageMapping extndMsg = new ExtendedMessageMapping();
		extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(request.getParameter("eb01ExtndMsgValSysId")));
		extndMsg.setValue(request.getParameter("eb01Val").trim());
		String eb01ExtndMsgs = request.getParameter("eb01ExtndMsgJsonObj");	
		if(!eb01ExtndMsgs.equals("")){
			JSONArray jsonArray = JSONArray.fromObject(eb01ExtndMsgs);
			JSONObject jsonObject = jsonArray.getJSONObject(0);		
			if(!jsonObject.getString("eb01ExtndMessage1").equals("") || !(jsonObject.getString("eb01ExtndMessage2").equals("")) || !(jsonObject.getString("eb01ExtndMessage3").equals(""))){
				extndMsg.setNetworkInd(jsonObject.getString("eb01NetworkInd"));
				if(jsonObject.get("changeInd") != null){
					extndMsg.setChangeInd(jsonObject.getString("changeInd"));
				} 
				extndMsg.setExtndMsg1(jsonObject.getString("eb01ExtndMessage1"));
				extndMsg.setExtndMsg2(jsonObject.getString("eb01ExtndMessage2"));
				extndMsg.setExtndMsg3(jsonObject.getString("eb01ExtndMessage3"));
			}
		} else if(extndMsg.getExtendedMsgValueSysId() != null){
			extndMsg.setChangeInd("D");
		}
		extndMsgEB01List.add(extndMsg);
		mapping.getEb01().setExtendedMsgsForSelectedValues(extndMsgEB01List);
		
		
		List hippaCodeEB02List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb02Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB02Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("eb02SysId")));
		hippaCodeEB02List.add(hippaCodeValue);
		mapping.getEb02().setName(DomainConstants.EB02_NAME);
		mapping.getEb02().setHippaCodeSelectedValues(hippaCodeEB02List);

		List hippaCodeEB03List = new ArrayList();
		String[] eb03Values = request.getParameterValues("eb03Val");
		String[] eb03SysId = request.getParameterValues("eb03SysId");
		String[] eb03Desc = request.getParameterValues("EB03Desc");
		//String[] eb03Seq = request.getParameterValues("eb03Seq");
		if (eb03Values != null) {
			if (eb03Desc == null) {
				eb03Desc = new String[eb03Values.length];
			}
			for (int i = 0; i < eb03Values.length; i++) {
				if (eb03Values != null && eb03Values[i] != "") {
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(eb03Values[i].trim());
					if (eb03SysId.length > i && !eb03SysId[i].equals("")) {

						hippaCodeValue
								.setHippaCodeValueSysId(setHippaCodeValueSysId(eb03SysId[i]));
					} else {

						hippaCodeValue.setHippaCodeValueSysId(null);
					}
					
					hippaCodeValue.setDescription(eb03Desc[i]);
					hippaCodeEB03List.add(hippaCodeValue);
				}
			}
		}
		hippaCodeEB03List = BxUtil.removeDuplicates(hippaCodeEB03List);
		mapping.getEb03().setName(DomainConstants.EB03_NAME);
		mapping.getEb03().setHippaCodeSelectedValues(hippaCodeEB03List);

		List hippaCodeEB06List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb06Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB06Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("eb06SysId")));
		hippaCodeEB06List.add(hippaCodeValue);
		mapping.getEb06().setName(DomainConstants.EB06_NAME);
		mapping.getEb06().setHippaCodeSelectedValues(hippaCodeEB06List);

		List hippaCodeEB09List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb09Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB09Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("eb09SysId")));
		hippaCodeEB09List.add(hippaCodeValue);
		mapping.getEb09().setName(DomainConstants.EB09_NAME);
		mapping.getEb09().setHippaCodeSelectedValues(hippaCodeEB09List);

		List hippaCodeHSD01List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd01").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD01Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd01SysId")));
		hippaCodeHSD01List.add(hippaCodeValue);
		mapping.getHsd01().setName(DomainConstants.HSD01_NAME);
		mapping.getHsd01().setHippaCodeSelectedValues(hippaCodeHSD01List);

		List hippaCodeHSD02List = new ArrayList();
		if(null != request.getParameterValues("hsd02")){
			int sizeOfHSD02 = request.getParameterValues("hsd02").length;
			String[] HSD02 = new String[sizeOfHSD02];
			String[] HSD02Desc = request.getParameterValues("HSD02Desc");
			if(HSD02Desc == null){
				HSD02Desc = new String[sizeOfHSD02];
			}
			for(int i=0;i<sizeOfHSD02;i++){
				HSD02[i] = request.getParameterValues("hsd02")[i].trim();
				hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(HSD02[i]);
				hippaCodeValue.setDescription(HSD02Desc[i]);
				hippaCodeHSD02List.add(hippaCodeValue);
			}
		}
		mapping.getHsd02().setName(DomainConstants.HSD02_NAME);
		mapping.getHsd02().setHippaCodeSelectedValues(hippaCodeHSD02List);

		List hippaCodeHSD03List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd03").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD03Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd03SysId")));
		hippaCodeHSD03List.add(hippaCodeValue);
		mapping.getHsd03().setName(DomainConstants.HSD03_NAME);
		mapping.getHsd03().setHippaCodeSelectedValues(hippaCodeHSD03List);

		List hippaCodeHSD04List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd04").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD04Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd04SysId")));
		hippaCodeHSD04List.add(hippaCodeValue);
		mapping.getHsd04().setName(DomainConstants.HSD04_NAME);
		mapping.getHsd04().setHippaCodeSelectedValues(hippaCodeHSD04List);

		List hippaCodeHSD05List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd05").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD05Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd05SysId")));
		hippaCodeHSD05List.add(hippaCodeValue);
		mapping.getHsd05().setName(DomainConstants.HSD05_NAME);
		mapping.getHsd05().setHippaCodeSelectedValues(hippaCodeHSD05List);

		List hippaCodeHSD06List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd06").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD06Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd06SysId")));
		hippaCodeHSD06List.add(hippaCodeValue);
		mapping.getHsd06().setName(DomainConstants.HSD06_NAME);
		mapping.getHsd06().setHippaCodeSelectedValues(hippaCodeHSD06List);

		List hippaCodeHSD07List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd07").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD07Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd07SysId")));
		hippaCodeHSD07List.add(hippaCodeValue);
		mapping.getHsd07().setName(DomainConstants.HSD07_NAME);
		mapping.getHsd07().setHippaCodeSelectedValues(hippaCodeHSD07List);

		List hippaCodeHSD08List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd08").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD08Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
				.getParameter("hsd08SysId")));
		hippaCodeHSD08List.add(hippaCodeValue);
		mapping.getHsd08().setName(DomainConstants.HSD08_NAME);
		mapping.getHsd08().setHippaCodeSelectedValues(hippaCodeHSD08List);
		
		//Adding NM1 Message Segment to mapping object - Oct 2014
		List hippaCodeNM1MessageSegmentList = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("nm1MessageSegmentId").trim());
		hippaCodeValue.setDescription(request.getParameter("nm1MessageSegmentIdLabel"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("nm1MessageSegmentSysId")));	
		hippaCodeNM1MessageSegmentList.add(hippaCodeValue);
		mapping.getNm1MessageSegment().setName(DomainConstants.NM1_MSG_SGMNT);
		mapping.getNm1MessageSegment().setHippaCodeSelectedValues(hippaCodeNM1MessageSegmentList);
		
		// Condition added as part of SSCR19537
		if(null == request.getParameter("indEB03AssnCheckBox")) {
			List hippaCodeIII02List = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("ii02Val").trim());
			hippaCodeValue.setDescription(request.getParameter("III02Desc"));
			hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
					.getParameter("ii02SysId")));
			hippaCodeIII02List.add(hippaCodeValue);
			mapping.getIi02().setHippaCodeSelectedValues(hippaCodeIII02List);
		}
		mapping.getIi02().setName(DomainConstants.III02_NAME);
		
		List hippaCodeAccumList = new ArrayList();
		String[] accumValues = request.getParameterValues("accumulator");
		String[] accumSysId = request.getParameterValues("accumulatorSysId");
		String[] accumDesc = request.getParameterValues("AccumDesc");
		if (accumValues != null) {
			if (accumDesc == null) {
				accumDesc = new String[accumValues.length];
			}
			for (int i = 0; i < accumValues.length; i++) {
				if (accumValues != null && accumValues[i] != "") {
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(accumValues[i].trim());
					if (accumSysId != null && accumSysId.length > i
							&& !accumSysId[i].equals("")) {

						hippaCodeValue
								.setHippaCodeValueSysId(setHippaCodeValueSysId(accumSysId[i]));
					} else {

						hippaCodeValue.setHippaCodeValueSysId(null);
					}
				
					hippaCodeValue.setDescription(accumDesc[i]);
					hippaCodeAccumList.add(hippaCodeValue);
				}
			}
		}
		hippaCodeAccumList = BxUtil.removeDuplicates(hippaCodeAccumList);
		mapping.getAccum().setName(DomainConstants.ACCUM_NAME);
		mapping.getAccum().setHippaCodeSelectedValues(hippaCodeAccumList);

		List hippaCodeUMRuleList = new ArrayList();
		String[] umRuleValues = request.getParameterValues("umRuleVal");
		String[] umRuleSysId = request.getParameterValues("umRuleSysId");
		String[] umRuleDesc = request.getParameterValues("UMRuleDesc");

		if (umRuleValues != null) {
			if (umRuleDesc == null) {
				umRuleDesc = new String[umRuleValues.length];
			}
			for (int i = 0; i < umRuleValues.length; i++) {
				if (umRuleValues != null && umRuleValues[i] != "") {
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(umRuleValues[i].trim());

					if (umRuleSysId != null && umRuleSysId.length > i
							&& !umRuleSysId[i].equals("")) {

						hippaCodeValue
								.setHippaCodeValueSysId(setHippaCodeValueSysId(umRuleSysId[i]));
					} else {

						hippaCodeValue.setHippaCodeValueSysId(null);
					}
					hippaCodeValue.setDescription(umRuleDesc[i]);
					hippaCodeUMRuleList.add(hippaCodeValue);
				}
			}
		}
		hippaCodeUMRuleList = BxUtil.removeDuplicates(hippaCodeUMRuleList);
		mapping.getUtilizationMgmntRule().setName(DomainConstants.UMRULE_NAME);
		mapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(
				hippaCodeUMRuleList);
		// Codition added as part of SSCR19537
		if(null == request.getParameter("indEB03AssnCheckBox")) {
			List noteTypeList = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("noteTypeCodeVal").trim());
			hippaCodeValue.setDescription(request.getParameter("NoteTypeDesc"));
			hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request
					.getParameter("noteTypeCodeSysId")));
			noteTypeList.add(hippaCodeValue);
			mapping.getNoteTypeCode().setHippaCodeSelectedValues(noteTypeList);

			mapping.setMessage(request.getParameter("messageValue").trim()
					.toUpperCase());
			
			if (null != request.getParameter("msgRqdChkBox")) {
				mapping.setMsg_type_required("Y");
			} else {
				mapping.setMsg_type_required("N");
			}

		}
		mapping.getNoteTypeCode().setName(DomainConstants.NOTE_TYPE_CODE);
		
				mapping.getVariable().setVariableId(
				request.getParameter("selectedvariableId"));
		//mapping.getVariable().setDescription(request.getParameter("selectedvariableDesc"));
		mapping.getVariable().setDescription(request.getParameter("variableDescHidden"));
		mapping.setVariableSystemId(Long.valueOf(request
				.getParameter("mappingSysId")));
		mapping.getVariable().setVariableFormat(
				request.getParameter("variableFormat"));
		// BXNI CR29
		mapping.getVariable().setPva(request.getParameter("providerArrangement"));
		mapping.getVariable().setLgCatagory(request.getParameter("lgCatagory").trim());
		mapping.getVariable().setIsgCatagory(request.getParameter("isgCatagory").trim());
		mapping.getVariable().setVariableSystem(request.getParameter("system").trim());
		mapping.getVariable().setMinorHeadings(request.getParameter("valuesOfMinorHeadings"));
		mapping.getVariable().setMajorHeadings(request.getParameter("valuesOfMajorHeadings"));

		if (null != request.getParameter("accumNtReqdChkBox")) {
			mapping.setSensitiveBenefitIndicator("Y");
		} else {
			mapping.setSensitiveBenefitIndicator("N");
		}
		/**
		 * Code change for MTM
		 */
		/**
		 * started
		 */
		if (null != request.getParameter("notFinalizedChkBox")
				&& request.getParameter("notFinalizedChkBox").equalsIgnoreCase(
						"checked")) {

			mapping.setFinalized(false);
		} else {
			mapping.setFinalized(true);
		}
		/**
		 * ended
		 */
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setCreatedUserName(userId);
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);
		
		// CR29
		if (null != request.getParameter("excludeProceduresChkBox")	&& request.getParameter("excludeProceduresChkBox").equalsIgnoreCase("checked")) {
			mapping.setProcedureExcludedInd(DomainConstants.Y);
		} else {
			mapping.setProcedureExcludedInd(DomainConstants.N);
		}
		// SSCR19537 April04
		createEb03AssociatedValues(request, mapping);
		return mapping;
	}
	


	private Long setHippaCodeValueSysId(String hippaCodeSysId) {

		if (hippaCodeSysId != null && !hippaCodeSysId.trim().equals("")) {

			return Long.valueOf(hippaCodeSysId);
		} else{
			return null;
		}
	}

	/**
	 * @return Returns the variableMappingFacade.
	 */
	public VariableMappingFacade getVariableMappingFacade() {
		return variableMappingFacade;
	}

	/**
	 * @param variableMappingFacade The variableMappingFacade to set.
	 */
	public void setVariableMappingFacade(
			VariableMappingFacade variableMappingFacade) {
		this.variableMappingFacade = variableMappingFacade;
	}

	/**
	 * @return Returns the locateFacade.
	 */
	public LocateFacade getLocateFacade() {
		return locateFacade;
	}

	/**
	 * @param locateFacade The locateFacade to set.
	 */
	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}

	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
	
	public Integer getAuditInfoLimit() {
		return auditInfoLimit;
	}

	public void setAuditInfoLimit(Integer auditInfoLimit) {
		this.auditInfoLimit = auditInfoLimit;
	}
	
	public String getAuditTrail(List auditTrailList,String variableId){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div id='viewAuditTrailId'>");
		buffer.append("<div class='mL10 link fL mR10'  title='View Audit Trail'>");
		if(auditTrailList.size()==20){
			buffer.append("<a href='#' style='color:blue;' onclick ='viewAllAuditTrail(\""+variableId+"\")';>View All</a>");
			
		}
		buffer.append("</div><div>");
				
		buffer.append("<div>&nbsp;</div> ");
		buffer.append("<table border='0' cellpadding='0' cellspacing='0' width='520px' class='Pd2 auditTrailTable' >");
		buffer.append("<tr class='createEditTableShade'>");
		buffer.append("<td width='90px' class='headText'>Date</td>");
		buffer.append("<td width='93px' class='headText'>User ID</td>");
		buffer.append("<td width='163px' class='headText'>System Comment</td>");
		buffer.append("<td width='250px' class='headText'>User Comment</td>");
		buffer.append("</tr>");
		if(auditTrailList.isEmpty()){
			buffer.append("No audit trail found");
		}else{
			for(int i=0;i<auditTrailList.size();i++){
				AuditTrail audit = (AuditTrail) auditTrailList.get(i);
				if(i%2 ==0){
					buffer.append("<tr class='white-bg'>");
				}else{
					buffer.append("<tr class='alternate'>");
				}
				buffer.append("<fmt:timeZone value='PST'>");
				buffer.append("<td width='90px'>").append(audit.getCreatedAuditDate()).append("</td>");
				buffer.append("<td width='93px'>").append(audit.getCreatedUser()).append("</td>");
				buffer.append("<td width='180px'>");
				if("PUBLISHED".equals(audit.getMappingStatus())){
					buffer.append(audit.getMappingStatus());
					buffer.append("<a href='#' style='color:blue;' onclick='retreiveAuditTrailInDetail();' >View&nbsp;Details</a>");
				}else{
					buffer.append(audit.getSystemComments());
				}
				buffer.append("</td>");
				buffer.append("<td width='250px'>");
				if(null !=audit.getUserComments()){
					buffer.append(audit.getUserComments());
				}else{
					buffer.append("");
				}
				buffer.append("</td>");
				buffer.append("</fmt:timeZone>");
				buffer.append("</tr>");
			}
			buffer.append("</table>");
			buffer.append("</div>");
			buffer.append("</div>");
			buffer.append("</div>");
		}
		return buffer.toString();
	}

	// SSCR19537 April 04 - Start
	/**
	 * Method to create the EB03 associated values from the page.
	 * @param request
	 * @param mapping
	 */
	private void createEb03AssociatedValues(HttpServletRequest request,
			Mapping mapping) {
		
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		
		if(null != request.getParameter("indEB03AssnCheckBox") 
				&& request.getParameter("indEB03AssnCheckBox").equalsIgnoreCase("on")){
			String jsonArray = request.getParameter("eb03AssnJsonObj");
			
			if (null != jsonArray && !DomainConstants.EMPTY.equals(jsonArray)) {
				eb03AssnList = createEB03AssociationObject(jsonArray);
				createEB03ExtendMsgAssociationObject(request, mapping);
			}
			mapping.setIndvdlEb03AssnIndicator(DomainConstants.Y);
			
		}else{
			
			eb03AssnList = createEB03AssociationObject(request);
			mapping.setIndvdlEb03AssnIndicator(DomainConstants.N);
		}
		//Adding EB03Assn object to the mapping object
		mapping.getEb03().setEb03Association(eb03AssnList);
	}
	
	private void createEB03ExtendMsgAssociationObject(
			HttpServletRequest request, Mapping mapping) {
		List<ExtendedMessageMapping> extndMsgEB03List = new ArrayList<ExtendedMessageMapping>();
		List<ExtendedMessageMapping> eb03ObjsList = new ArrayList<ExtendedMessageMapping>();
		String eb03ExtndMsgJsonObj = request.getParameter("eb03ExtndMsgJsonObj");
		if(null != request.getParameter("indEB03AssnCheckBox") 
				&& request.getParameter("indEB03AssnCheckBox").equalsIgnoreCase("on")){
			String jsonArray = request.getParameter("eb03AssnJsonObj");
			
			if (null != jsonArray && !DomainConstants.EMPTY.equals(jsonArray)) {
				if (null != eb03ExtndMsgJsonObj && !DomainConstants.EMPTY.equals(eb03ExtndMsgJsonObj)) {
					JSONArray eb03JsonArray = JSONArray.fromObject(eb03ExtndMsgJsonObj);
					if (eb03JsonArray.size() > 0) {					
						for (int i = 0; i <  eb03JsonArray.size(); i++) {
							JSONObject jsonObject = eb03JsonArray.getJSONObject(i);
							String eb03=jsonObject.getString("eb03Val").trim();
							JSONArray eb03AssnJsonPanelArray = JSONArray.fromObject(jsonArray);
							if (eb03AssnJsonPanelArray.size() > 0) {
								for (int j = 0; j <  eb03AssnJsonPanelArray.size(); j++) {
									JSONObject eb03AssnjsonObject = eb03AssnJsonPanelArray.getJSONObject(j);
									ExtendedMessageMapping extndMsg = new ExtendedMessageMapping();
									if(eb03.equals(eb03AssnjsonObject.getString(DomainConstants.EB03_NAME).trim())){
										if(jsonObject.get("changeInd") != null && jsonObject.getString("changeInd").equals("D")){
											extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(jsonObject.getString("eb03ExtndMsgSysId")));
											extndMsg.setValue(eb03);
											extndMsg.setChangeInd(jsonObject.getString("changeInd"));
											extndMsgEB03List.add(extndMsg);
											break;
										} else{
											if(jsonObject.get("eb03ExtndMsgSysId").equals(null)){
												extndMsg.setExtendedMsgValueSysId(null);
											} else{
												extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(jsonObject.getString("eb03ExtndMsgSysId")));
											}
											extndMsg.setNetworkInd(jsonObject.getString("eb03NetworkInd"));
											extndMsg.setValue(jsonObject.getString("eb03Val"));										
											extndMsg.setChangeInd(jsonObject.getString("changeInd"));										
											extndMsg.setExtndMsg1(jsonObject.getString("eb03ExtndMessage1"));
											extndMsg.setExtndMsg2(jsonObject.getString("eb03ExtndMessage2"));
											extndMsg.setExtndMsg3(jsonObject.getString("eb03ExtndMessage3"));
											eb03ObjsList.add(extndMsg);
											break;
										}
									}
								}
							}
						}
						extndMsgEB03List.addAll(eb03ObjsList);
						mapping.getEb03().setExtendedMsgsForSelectedValues(extndMsgEB03List);
					}
				}
			}
		}		
	}

	/**
	 * Method to create the EB03 Associated values at the EB03 level.
	 * @param jsonArray
	 * @return
	 */
	private List<EB03Association> createEB03AssociationObject(String jsonArray) {

		List<EB03Association> eB03AssociationList = new ArrayList<EB03Association>();
		
		if (null != jsonArray && !DomainConstants.EMPTY.equals(jsonArray)) {
			// Assign stringified JSON object from the JSP JSONArray. 
			JSONArray eb03AssnJsonPanelArray = JSONArray.fromObject(jsonArray);
			if (eb03AssnJsonPanelArray.size() > 0) {
				
				// Iterate the JSON Array and get the corresponding JSON objet
				for (int i = 0; i <  eb03AssnJsonPanelArray.size(); i++) {
					EB03Association eb03Association = new EB03Association();
					List<HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>(); 
					// Get the JSON object
					final JSONObject jsonObject = eb03AssnJsonPanelArray.getJSONObject(i);
					
					// Get EB03 and associated values after from the JSON Object
					final String eb03 = jsonObject.getString(DomainConstants.EB03_NAME);
					final String iii02 = jsonObject.getString(DomainConstants.III02_NAME);
					final String iii02Desc = jsonObject.getString(DomainConstants.III02_DESC);
					final String messageText =  BxUtil.removeEscapedCharacters(jsonObject.getString(DomainConstants.MESSAGE));
					final String msgRqdInd = jsonObject.getString(DomainConstants.MESG_REQD_IND);
					final String noteTypeCode = jsonObject.getString(DomainConstants.NOTE_TYPE_CODE);
					final String noteTypeDesc = BxUtil.removeEscapedCharacters(jsonObject.getString(DomainConstants.NOTE_TYPE_DESC));
					
					// Convert the String Value to HippaCode Value.
					HippaCodeValue eb03Value = null;
					HippaCodeValue iii02Value = null;
					HippaCodeValue noteTypeCodeValue = null;
					
					if (null != eb03) {
						eb03Value = BxUtil.covertToHippaCodeValue(eb03.trim());
					}
					if(null != iii02) {
						iii02Value = BxUtil.covertToHippaCodeValue(iii02.trim(), iii02Desc);
					}
					
					iii02List.add(iii02Value);
					if (null != noteTypeCode) {
						noteTypeCodeValue = BxUtil.covertToHippaCodeValue(noteTypeCode.trim(), noteTypeDesc);
					}
					
					// Create EB03 Association Object.
					eb03Association.setEb03(eb03Value);
					eb03Association.setIii02List(iii02List);
					eb03Association.setMessage(messageText);
					eb03Association.setMsgReqdInd(msgRqdInd);
					eb03Association.setNoteType(noteTypeCodeValue);
					eB03AssociationList.add(eb03Association);
				}
			}
		}
		return eB03AssociationList;
	}
	
	/**
	 * Method to create the EB03 Associated values at the variable level.
	 * @param request
	 * @return
	 */
	private List<EB03Association> createEB03AssociationObject(
			HttpServletRequest request) {
		
		List<EB03Association> eb03AssociationList =  new ArrayList<EB03Association>();
		List<HippaCodeValue> hippaCodeEB03List = new ArrayList<HippaCodeValue>();
		List <HippaCodeValue>hippaCodeIII02List = new ArrayList<HippaCodeValue>();
		
		HippaCodeValue iii02HippaCodeValue = new HippaCodeValue();
		HippaCodeValue noteTypeHippaCodeValue = new HippaCodeValue();
		
		// Get EB03
		if(null != request.getParameterValues("eb03Val")){
			int sizeOfEB03 = request.getParameterValues("eb03Val").length;
			String[] EB03 = new String[sizeOfEB03];
			String[] EB03Desc = request.getParameterValues("EB03Desc");
			if(EB03Desc == null){
			    EB03Desc = new String[sizeOfEB03];
			}
			for(int i = 0; i<sizeOfEB03; i++){
				HippaCodeValue eb03HippaCodeValue = new HippaCodeValue();
				EB03[i] = request.getParameterValues("eb03Val")[i].trim();
				eb03HippaCodeValue.setValue(EB03[i]);
				eb03HippaCodeValue.setDescription(EB03Desc[i].trim());
				hippaCodeEB03List.add(eb03HippaCodeValue);
			}
		}	
		hippaCodeEB03List = BxUtil.removeDuplicates(hippaCodeEB03List);
		
		// Get III02
		String iii02 = "";
		if (null != request.getParameter("ii02Val")) {
			iii02 = request.getParameter("ii02Val").trim();
		}
		String iii02Desc = "";
		if (null != request.getParameter("III02Desc")) {
			iii02Desc = request.getParameter("III02Desc").trim();
		}
		iii02HippaCodeValue.setValue(iii02);
		iii02HippaCodeValue.setDescription(iii02Desc);
		hippaCodeIII02List.add(iii02HippaCodeValue);
		
		// Get Message
		String messageText = request.getParameter("messageValue").trim();
		
		// Get message required indicator.
		String msgRqdInd = DomainConstants.N;
		if(null != request.getParameter("msgRqdChkBox")){
			msgRqdInd = DomainConstants.Y;
		}
		
		// Get Note Type.
		// Get Note Type.
		String noteType = "";
		if (null != request.getParameter("noteTypeCodeVal")) {
			noteType = request.getParameter("noteTypeCodeVal").trim();
		}
		String noteTypeDesc = "";
		if (null != request.getParameter("NoteTypeDesc")) {
			noteTypeDesc = request.getParameter("NoteTypeDesc").trim();
		}
		noteTypeHippaCodeValue.setValue(noteType);
		noteTypeHippaCodeValue.setDescription(noteTypeDesc);
		
		// Create EB03 Association object with same associated values for each EB03.
		for (HippaCodeValue eb03HippaCodeValue : hippaCodeEB03List) {
			String eb03Value = eb03HippaCodeValue.getValue();
			if (null != eb03Value && !DomainConstants.EMPTY.equals(eb03Value)) {
				EB03Association eb03Association = new EB03Association();
				eb03Association.setEb03(eb03HippaCodeValue);
				eb03Association.setIii02List(hippaCodeIII02List);
				eb03Association.setMessage(messageText);
				eb03Association.setMsgReqdInd(msgRqdInd);
				eb03Association.setNoteType(noteTypeHippaCodeValue);
				eb03AssociationList.add(eb03Association);
			}
		}
		return eb03AssociationList;
	}
	// SSCR19537 April 04 - Start
}