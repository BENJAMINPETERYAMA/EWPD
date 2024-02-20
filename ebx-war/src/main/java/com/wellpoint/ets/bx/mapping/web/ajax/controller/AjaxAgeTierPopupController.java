package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/*
 * Created on Apr 1, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 *Ajax Controller class for the Population of Age Tier variables in the Start Age/End Age text boxes.
 *BXNI June 2012 Release
 */
public class AjaxAgeTierPopupController implements Controller {
	private LocateFacade locateFacade;
	private static Logger log = Logger.getLogger(AjaxPopUpController.class);
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
		String ageTierName = request.getParameter("ageTierName");
		String searchText = request.getParameter("searchText");
        String variableId = request.getParameter("variableIdHidden");
        
        if(null != searchText){
        	searchText = searchText.replaceAll("'", "''");
        }
        
        Variable variable = new Variable();
		variable.setVariableId(variableId);
		
		List<Variable> ageTierVariablelist = new ArrayList<Variable>();
		if(null != searchText && !"".equals(searchText.trim())){
		    searchText = searchText.trim().toUpperCase();
		}
		if (null != ageTierName){
			if(ageTierName.trim().equals(DomainConstants.START_AGE_FROM_PAGE) 
					|| ageTierName.trim().equals(DomainConstants.END_AGE_FROM_PAGE)){
				ageTierVariablelist = locateFacade.getAvailableAgeTierVariables(ageTierName.toUpperCase(),searchText,variable);
			}
		}
		Map map = new HashMap();
		List infoList = new ArrayList();
		if(ageTierVariablelist == null){
		    log.trace("Creating an empty object...");
		    ageTierVariablelist = new ArrayList();
		    infoList.add("No results to display");
		}else if(ageTierVariablelist.size() <= 0){
		    infoList.add("No results to display");
		}
		
		log.trace("in Ajax control "+ageTierVariablelist);
		map.put("tierVariables", ageTierVariablelist);
		map.put("ageTierName", ageTierName);
		map.put("variableIdHidden", variableId);
		map.put("searchText", searchText);
		
		map.put(WebConstants.INFO_MESSAGES, infoList);
		return new ModelAndView("agetierpopup",map);
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
