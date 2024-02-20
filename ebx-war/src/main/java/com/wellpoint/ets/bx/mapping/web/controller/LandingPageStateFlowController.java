/*
 * Created on May 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebUtil;

/**
 * @author u22093
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LandingPageStateFlowController extends MultiActionController {

	private VariableMappingFacade variableMappingFacade;

	private LocateFacade locateFacade;
	
	private SessionMessageTray sessionMessageTray;

	/*
	 * method used to update the status 'send to test'
	 *  
	 */
	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		User user = new User();
		Variable variable = new Variable();
		CreateOrEditMappingResult result = null;

		String variableId = request
				.getParameter("send2teststateflowvariableId");

		String userComments = request.getParameter("send2TestMappingComments");

		variable.setVariableId(variableId);
		mapping.setMessage(userComments);
		mapping.setVariable(variable);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= 250) {
			result = variableMappingFacade.sendToTest(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			//modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			sessionMessageTray.setErrorMessages(errorMessages);
		}

		if (result != null && result.getStatus() == 1) {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.sendToTestFromViewOrLocate.success", null);
			infoMessages.add(mappingSuccess);
			//modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);	
			sessionMessageTray.setInformationMessages(infoMessages);
			
		}
		if(null !=result && result.getStatus() == DomainConstants.LOCKED_STATUS){
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();					
					if(obj!=null){
						errorMessages = new ArrayList();
						errorMessages.add(obj);
						sessionMessageTray.setErrorMessages(errorMessages);
						return WebUtil.redirectToLandingPage(request);
					}
				}
			}			
		}
		return WebUtil.redirectToLandingPage(request);
	}

	/*
	 * method used to update the status 'approve'
	 *  
	 */
	public ModelAndView approve(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		User user = new User();
		CreateOrEditMappingResult result = null;

		String variableId = request.getParameter("approvestateflowvariableId");
		String userComments = request.getParameter("approvedMappingComments");
		variable.setVariableId(variableId);
		mapping.setMessage(userComments);
		mapping.setVariable(variable);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);

		if (userComments.length() <= 250) {
			result = variableMappingFacade.approve(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			//modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			sessionMessageTray.setErrorMessages(errorMessages);
		}
		if(null !=result && result.getStatus() == DomainConstants.LOCKED_STATUS){
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();					
					if(obj!=null){
						errorMessages = new ArrayList();
						errorMessages.add(obj);
						sessionMessageTray.setErrorMessages(errorMessages);
						return WebUtil.redirectToLandingPage(request);
					}
				}
			}			
		}
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
			//modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		return WebUtil.redirectToLandingPage(request);	
		/*modelAndView = redirectToLandingPage(modelAndView, mapping,
				userComments);

		return modelAndView;*/
	}
	
	public ModelAndView markVariableAsNotApplicable(HttpServletRequest request, HttpServletResponse response){
	    
		String pageName = request.getParameter("pageName");
		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		CreateOrEditMappingResult result = null;
	    String variableId = request.getParameter("notApplicablestateflowvariableId");
	    String variableDesc = request.getParameter("notApplicablestateflowvariableDesc");
	    Variable variable = new Variable();
	    variable.setVariableId(variableId);
	    Mapping mapping = new Mapping();
	    mapping.setVariable(variable);
	    
	    String userComments = "";
		
		if(null!=request.getParameter("msgRqdChkBox") && request.getParameter("msgRqdChkBox").equals("true")){
			mapping.setMsg_type_required("Y");
		}else{
			mapping.setMsg_type_required("N");
		}
		
		if(null!=request.getParameter("accumNtReqdChkBox")){
			mapping.setSensitiveBenefitIndicator("Y");
		}else{
			mapping.setSensitiveBenefitIndicator("N");
		}
		/**
		 * MTM code change
		 */

		if(null!=request.getParameter("notFinalizedChkBox")&& request.getParameter("notFinalizedChkBox").equalsIgnoreCase("checked")){

			mapping.setFinalized(false);
		}else  {
			mapping.setFinalized(true);
		}
		/**
		 * ended
		 */
		mapping.setIsMapgRequired("N");		
		mapping.getVariable().setDescription(variableDesc);
		
		userComments =  request.getParameter("notApplicableMappingComments");
		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		mapping.setInTempTable("N");
		if(null!=pageName)
			mapping.setPageFrom(pageName);  //karthik
	    if (userComments.length() <= 250) {
	    	result = variableMappingFacade.markVariableAsNotApplicable(mapping, userComments);
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			sessionMessageTray.setErrorMessages(errorMessages);
			if(null != pageName && pageName.trim().equals("advanceSearch")){
				return  WebUtil.redirectToWPDAdvanceSearchPage(request);
			}else{
				return WebUtil.redirectToLandingPage(request);
			}
		}
	    String lockMsgs = "";
		if((null != result.getStatusCodes()) && !(result.getStatusCodes().isEmpty())){
			lockMsgs = (String)result.getStatusCodes().get(0);
			if(result.getStatus() == 0 &&
					(lockMsgs.equals("MAPPING_LOCKED_ANOTHER_USER") ||
							lockMsgs.equals("MAPPING_ALREADY_NOT_APPLiCABLE"))
				) {
				String lockFailure = BxResourceBundle.getResourceBundle(lockMsgs, null);		
				infoMessages.add(lockFailure);
				sessionMessageTray.setInformationMessages(infoMessages);
				if(null != pageName && pageName.trim().equals("advanceSearch")){
					return  WebUtil.redirectToWPDAdvanceSearchPage(request);
				}else{
					return WebUtil.redirectToLandingPage(request);
				}
			}
		}		
		else if(result.getStatus() == 1){
		
			String info = BxResourceBundle.getResourceBundle("mapping.notApplicable", null);
			infoMessages.add(info);
			sessionMessageTray.setInformationMessages(infoMessages);
			
		}
		
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return  WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	}

	/**
	 * @return Returns the variableMappingFacade.
	 */
	public VariableMappingFacade getVariableMappingFacade() {
		return variableMappingFacade;
	}

	/**
	 * @param variableMappingFacade
	 *            The variableMappingFacade to set.
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
	 * @param locateFacade
	 *            The locateFacade to set.
	 */
	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}
	
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}	
}