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
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocatePageStateFlowController extends MultiActionController {

	private VariableMappingFacade variableMappingFacade;

	private LocateFacade locateFacade;

	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		CreateOrEditMappingResult result = null;
		ModelAndView modelAndView = new ModelAndView("jsonView");

		String variableId = request.getParameter("variableId");
		String userComments = request.getParameter("userComments");
		String pageName = request.getParameter("pageFrom");
		//String variableDesc = request.getParameter("variableDesc");

		variable.setVariableId(variableId);
		//variable.setDescription(variableDesc);
		mapping.setMessage(userComments);
		mapping.setVariable(variable);

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		mapping.getUser().setLastUpdatedUserName(userId);
		mapping.setPageFrom(pageName);

		if (userComments.length() <= 250) {
			result = variableMappingFacade.sendToTest(mapping, userComments);			
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		}

		if (result != null && result.getStatus() == 1) {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.sendToTestFromViewOrLocate.success", null);
			infoMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject("variableId", variableId);
		}
		else if(null != result && result.getStatus() == DomainConstants.LOCKED_STATUS){
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();					
					if(obj!=null){
						errorMessages = new ArrayList();
						errorMessages.add(obj);								
					}
				}
				modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);
			}			
		}
		else {
			String mappingFailure = BxResourceBundle.getResourceBundle("mapping.sendToTest.failure", null);		
			errorMessages.add(mappingFailure);		
			modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);
		}
		return modelAndView;
	}

	public ModelAndView approve(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List infoMessages = new ArrayList();
		List errorMessages = new ArrayList();
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		CreateOrEditMappingResult result = null;
		ModelAndView modelAndView = new ModelAndView("jsonView");

		String variableId = request.getParameter("variableId");
		String userComments = request.getParameter("userComments");
		//String variableDesc = request.getParameter("variableDesc");

		variable.setVariableId(variableId);
		//variable.setDescription(variableDesc);
		mapping.setMessage(userComments);
		mapping.setVariable(variable);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		mapping.getUser().setLastUpdatedUserName(userId);

		if (userComments.length() <= 250) {
			result = variableMappingFacade.approve(mapping, userComments);			
		} else {
			String mappingSuccess = BxResourceBundle.getResourceBundle(
					"mapping.usercomments.failure", null);
			errorMessages.add(mappingSuccess);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
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
			modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
			modelAndView.addObject("variableId", variableId);
			if (null != result.getMapping() &&  null != result.getMapping().getVariableMappingStatus()) {
				modelAndView.addObject("variableMappingStatus", result.getMapping().getVariableMappingStatus());
			}
		}
		else if(null != result && result.getStatus() == DomainConstants.LOCKED_STATUS){
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();					
					if(obj!=null){
						errorMessages = new ArrayList();
						errorMessages.add(obj);								
					}
				}
				modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);
			}			
		}
		else {
			String mappingFailure = BxResourceBundle.getResourceBundle("mapping.approve.failure", null);		
			errorMessages.add(mappingFailure);		
			modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);
		}
		return modelAndView;
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

}