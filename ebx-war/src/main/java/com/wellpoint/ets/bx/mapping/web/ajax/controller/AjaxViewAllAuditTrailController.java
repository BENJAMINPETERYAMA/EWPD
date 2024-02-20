/*
 * Created on May 29, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AjaxViewAllAuditTrailController implements Controller{
	
	private VariableMappingFacade variableMappingFacade;
	
	
	 public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 	
		Map map = new HashMap();
		String variableId = request.getParameter("variableId");	
		String fromView = request.getParameter("fromMappingView");
		List auditTrail = variableMappingFacade.viewAllAuditTrail(variableId);
		map.put("auditTrailList", auditTrail);
		if(null != fromView && (fromView.equals("true"))){
			
			ModelAndView modelAndView = new ModelAndView("viewallaudit",map);
			return modelAndView;
		}
		else{
			ModelAndView modelAndView = new ModelAndView("mappingviewhistory",map);
			return modelAndView;
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

}
