package com.wellpoint.ets.bx.mapping.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.ebx.mapping.web.view.MassUpdateReportView;

public class MassUpdateWpdController extends MultiActionController{
	
	private VariableMappingFacade variableMappingFacade;
	private MassUpdateTracker massUpdateTracker;
	
	public MassUpdateTracker getMassUpdateTracker() {
		return massUpdateTracker;
	}

	public void setMassUpdateTracker(MassUpdateTracker massUpdateTracker) {
		this.massUpdateTracker = massUpdateTracker;
	}
	
	public VariableMappingFacade getVariableMappingFacade() {
		return variableMappingFacade;
	}

	public void setVariableMappingFacade(VariableMappingFacade variableMappingFacade) {
		this.variableMappingFacade = variableMappingFacade;
	}

	public ModelAndView update(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		return null;
	}
	
	public ModelAndView markNotApplicable(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		return null;
	}
	
	public ModelAndView approve(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		return null;
	}
	
	public ModelAndView sendToTest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		return null;
	}
	
	public ModelAndView massUpdateResult(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return new ModelAndView(new MassUpdateReportView(null));
	}

}
