package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;

public class ViewLandingEwpdBxController implements Controller {

	private LocateFacade locateRuleIdFacade;
	private LocateFacade locateSpsIdFacade;
	private LocateFacade locateCustomMessageFacade;
	private SessionMessageTray sessionMessageTray;	
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		List statusUnmapped = new ArrayList();
		statusUnmapped.add(DomainConstants.UNMAPPED_STATUS);
		
		List statusMapped = new ArrayList();
		statusMapped.add(DomainConstants.MAPPED_STATUS);
		
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		
		List unmappedRule = locateRuleIdFacade.getRecords(null, statusUnmapped, null, 26, 21, null);
		//unmappedRule = BxUtil.concatBreak(unmappedRule);
		if(null != unmappedRule && ! unmappedRule.isEmpty()){
			Collections.sort(unmappedRule);
		}
		
		List unmappedSps = locateSpsIdFacade.getRecords(null, statusUnmapped, null, 26, 21, null);
		//unmappedSps = BxUtil.concatBreak(unmappedSps);
		if(null != unmappedSps && !unmappedSps.isEmpty()){
			Collections.sort(unmappedSps);
		}
		List statusInProgress = new ArrayList();
		statusInProgress.add("IN-PROGRESS");
		List inprogressRule = locateRuleIdFacade.getRecords(null, statusInProgress, loggedInUser, 26, 21, null);
		//inprogressRule = BxUtil.concatBreak(inprogressRule);
		if(null != inprogressRule && !inprogressRule.isEmpty()){
			Collections.sort(inprogressRule);
		}	
		
		List inprogressSps = locateSpsIdFacade.getRecords(null, statusInProgress, loggedInUser, 26, 21, null);
		//inprogressSps = BxUtil.concatBreak(inprogressSps);
		if(null != inprogressSps && !inprogressSps.isEmpty()){
			Collections.sort(inprogressSps);
		}
				
		List inprogressCustomMsg = locateCustomMessageFacade.getRecords(null, statusInProgress, loggedInUser, 50, 21, null);
		//inprogressCustomMsg = BxUtil.concatBreak(inprogressCustomMsg);
		if(null != inprogressCustomMsg && !inprogressCustomMsg.isEmpty()){
			Collections.sort(inprogressCustomMsg);
		}
				
		ModelAndView modelAndView = new ModelAndView("viewlandingewpdbx");
		modelAndView.addObject("unmappedRule", unmappedRule)
		.addObject("unmappedSps", unmappedSps)	
		.addObject("inprogressRule", inprogressRule)
		.addObject("inprogressSps", inprogressSps)
		.addObject("inprogressCustomMsg", inprogressCustomMsg)
		.addObject(WebConstants.INFO_MESSAGES, sessionMessageTray.getAndClearInformationMessages())
		.addObject(WebConstants.ERROR_MESSAGES, sessionMessageTray.getAndClearErrorMessages())
		.addObject(WebConstants.WARNING_MESSAGES, sessionMessageTray.getAndClearWarningMessages());
		
		return modelAndView;
	}
	
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}

	public LocateFacade getLocateCustomMessageFacade() {
		return locateCustomMessageFacade;
	}

	public void setLocateCustomMessageFacade(LocateFacade locateCustomMessageFacade) {
		this.locateCustomMessageFacade = locateCustomMessageFacade;
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
    
    
}
