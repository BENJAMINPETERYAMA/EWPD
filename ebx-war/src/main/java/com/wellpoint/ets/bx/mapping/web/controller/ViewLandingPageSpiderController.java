package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

public class ViewLandingPageSpiderController implements Controller {
	
private UMRuleMappingFacade umRuleMappingFacade;
private int noOfRecords;
private SessionMessageTray sessionMessageTray;

	
	public int getNoOfRecords() {
	return noOfRecords;
	}
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}	
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}
	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Spider");
		List unmappedRules = umRuleMappingFacade.findAllUnmappedRules();//List<Mapping>
		unmappedRules = BxUtil.concatBreakSpider(unmappedRules);		
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();	
		BxUtil.encodeSpiderMappingsList(unmappedRules);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("unmappedRules", unmappedRules)
		.addObject(WebConstants.INFO_MESSAGES, sessionMessageTray.getAndClearInformationMessages())
		.addObject(WebConstants.ERROR_MESSAGES, sessionMessageTray.getAndClearErrorMessages())
		.addObject(WebConstants.WARNING_MESSAGES, sessionMessageTray.getAndClearWarningMessages());
		
		return modelAndView;
	}

}