/*
 * Created on Jun 7, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AjaxHippaSegmentToolTipController implements Controller {
    
    private LocateFacade locateFacade;
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String hippaSegmentName = request.getParameter("hippaSegmentName");
        HippaSegment hippaSegment = null;
        hippaSegment = locateFacade.getHippaSegmentDescription(hippaSegmentName);
        if(hippaSegment == null  ) {
        	hippaSegment = new HippaSegment();
        	hippaSegment.setHippaSegmentName(hippaSegmentName);
        	hippaSegment.setDescription("No Details Available");
        	hippaSegment.setHippaSegmentDefinition(" ");
        }
        Map map = new HashMap();
        map.put("hippaSegmentName" ,hippaSegment.getHippaSegmentName());
        map.put("hippaSegmentDesc" ,hippaSegment.getDescription());
        map.put("hippaSegmentDef" ,hippaSegment.getHippaSegmentDefinition());
        return new ModelAndView("jsonView", map);
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
