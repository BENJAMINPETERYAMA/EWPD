package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

public class ViewLandingPageController implements Controller {

	private LocateFacade locateFacade;
	private int noOfRecords;
	private SessionMessageTray sessionMessageTray;
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List unmappedVariables = locateFacade.findAllUnmappedVariables();//List<Mapping>
		unmappedVariables = BxUtil.concatBreak(unmappedVariables);
		
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		List inProgressVariables = locateFacade.findAllInProgressVariables(loggedInUser, noOfRecords, false);//List<Mapping>
		inProgressVariables = BxUtil.concatBreak(inProgressVariables);
		BxUtil.encodeMappingsList(inProgressVariables,unmappedVariables);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("unmappedVariables", unmappedVariables)
		.addObject("inProgressVariables", inProgressVariables)
		.addObject("variable", new Variable())
		.addObject(WebConstants.INFO_MESSAGES, sessionMessageTray.getAndClearInformationMessages())
		.addObject(WebConstants.ERROR_MESSAGES, sessionMessageTray.getAndClearErrorMessages())
		.addObject(WebConstants.WARNING_MESSAGES, sessionMessageTray.getAndClearWarningMessages());
		
		return modelAndView;
	}
	

	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}

	public LocateFacade getLocateFacade() {
		return locateFacade;
	}

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
    
    
}
