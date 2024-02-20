/*
 * Created on Jun 2, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HippaSegmentDescriptionController {
	
	private LocateFacade locateFacade;
		
	public ModelAndView hippaSegementDesc(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String hippaSegmentCode = request.getParameter("hippaSegmentCode");	
		HippaSegment hippaSegment = locateFacade.getHippaSegmentDescription(hippaSegmentCode);		
		ModelAndView modelAndView = new ModelAndView("createmapping");		
		modelAndView.addObject("hippaSegment", hippaSegment);
		return modelAndView;
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
