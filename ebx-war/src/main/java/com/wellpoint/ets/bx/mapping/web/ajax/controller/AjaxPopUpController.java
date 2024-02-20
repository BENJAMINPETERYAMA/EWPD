/*
 * Created on May 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.owasp.esapi.ESAPI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AjaxPopUpController implements Controller{
    
    private LocateFacade locateFacade;
    private static Logger log = Logger.getLogger(AjaxPopUpController.class);
    
    public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String hippaSegmentName = request.getParameter("hippaSegmentName");
        String variableFormat = request.getParameter("variableFormat");
        String searchText = request.getParameter("searchText");
        String pva = request.getParameter("providerArrangement");
        String page = request.getParameter("pageName");
        log.debug("From Page is: "+ESAPI.encoder().encodeForHTML(page));
        
        if(null != searchText){
        	searchText = searchText.replaceAll("'", "''");
        }
        log.trace("@@@@@@in ajax controller : "+hippaSegmentName+", "+variableFormat+" ,"+searchText);
		Variable variable = new Variable();
		variable.setVariableFormat(variableFormat);
		variable.setPva(pva);
		HippaSegment hippaSegment = null;
		
		if(null != searchText && !"".equals(searchText.trim())){
		    searchText = searchText.trim().toUpperCase();
		    if (null != hippaSegmentName) {
			    if(hippaSegmentName.trim().equals(DomainConstants.EB01_NAME)){
			        hippaSegment = locateFacade.findMatchingHippaCodeValuesForEB01(hippaSegmentName.toUpperCase(), searchText, variable);
				}else{
				    hippaSegment = locateFacade.findMatchingHippaCodeValues(hippaSegmentName.toUpperCase(), searchText);
				}
		    }
		    
		}else{
			if (null != hippaSegmentName) {
			    if(hippaSegmentName.trim().equals(DomainConstants.EB01_NAME)){
			        hippaSegment = locateFacade.getAvailableHippaSegmentValuesForEB01(hippaSegmentName.toUpperCase(), variable);
				}else if(hippaSegmentName.equalsIgnoreCase(DomainConstants.UMRULE_NAME)){
					hippaSegment = locateFacade.getAvailableUMRule(hippaSegmentName.toUpperCase());
				}else{
				    hippaSegment = locateFacade.getAvailableHippaSegmentValues(hippaSegmentName.toUpperCase());
				}
			}
		}
		List infoList = new ArrayList();
		if(hippaSegment == null){
		    log.trace("Creating an empty object...");
		    hippaSegment = new HippaSegment();
		    infoList.add("No results to display");
		}else if(hippaSegment.getHippaCodePossibleValues().size() <= 0){
		    infoList.add("No results to display");
		}
		log.trace("in Ajax contro "+hippaSegment);
		Map map = new HashMap();
		map.put("hippaSegment", hippaSegment);
		map.put("searchText", searchText);
		map.put(WebConstants.INFO_MESSAGES, infoList);
		map.put("pageName", page);
		return new ModelAndView("popup",map);
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
