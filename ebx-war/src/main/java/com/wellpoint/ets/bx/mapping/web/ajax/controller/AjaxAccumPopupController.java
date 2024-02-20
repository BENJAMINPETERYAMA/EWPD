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
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/*
 * Created on Apr 1, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author U23057
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AjaxAccumPopupController implements Controller {
	private LocateFacade locateFacade;
	private static Logger log = Logger.getLogger(AjaxPopUpController.class);
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String hippaSegmentName = request.getParameter("hippaSegmentName");
	    String accumName = request.getParameter("searchAccumName");
	    String accumDesc= request.getParameter("searchAccumDesc");
		List accumList = new ArrayList();
		if(accumName != null && accumDesc != null){
			accumList = locateFacade.findMatchingAccumulator(accumName, accumDesc);
		} else {
			accumList = locateFacade.getAvailableAccumulators();
		}
		Map map = new HashMap();
		List infoList = new ArrayList();
		if(accumList == null){
		    log.trace("Creating an empty object...");
		    accumList = new ArrayList();
		    infoList.add("No results to display");
		}else if(accumList.size() <= 0){
		    infoList.add("No results to display");
		}
		
		log.trace("in Ajax contro "+accumList);
		map.put("accumulators", accumList);
		map.put("searchAccumName", accumName);
		map.put("searchAccumDesc", accumDesc);
		map.put("hippaSegmentName", hippaSegmentName);
		//map.put("hippaSegmentName", hippaSegmentName);
		map.put(WebConstants.INFO_MESSAGES, infoList);
		return new ModelAndView("accumpopup",map);
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
