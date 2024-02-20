package com.wellpoint.ets.bx.mapping.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.simulation.application.SimulationFacade;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

public class WebUtil {
	private static SimulationFacade simulationFacade;
	/**
	 * @param modelAndView
	 * @param result
	 * @return
	 */
	public static ModelAndView redirectToLandingPage(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_LANDING_PAGE_NAME);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;			
	}
	
	public static ModelAndView redirectToEditPage(HttpServletRequest request, String variableId) {
		String contextPath = request.getContextPath();		
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_EDIT_PAGE_NAME);		
		ModelAndView modelAndView = new ModelAndView(redirectView);
		modelAndView.addObject("selectedvariableForEditId", variableId);
		return modelAndView;			
	}
	/**
	 * Method redirects to the previous search Results page from the Edit page
	 * @return modelAndView
	 */
	public static ModelAndView redirectToEditPageFromAdvanceSearch(HttpServletRequest request, String variableId,String pageName) {
		String contextPath = request.getContextPath();		
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_EDIT_PAGE_NAME);		
		ModelAndView modelAndView = new ModelAndView(redirectView);
		modelAndView.addObject("selectedvariableForEditId", variableId);
		modelAndView.addObject("pageName", pageName);
		return modelAndView;			
	}
	public static ModelAndView redirectToeWPDBXLandingPage(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_LANDING_EWPD_BX_PAGE_NAME);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;			
	}
	/**
	 * Method redirects to the previous search Results page from the Edit page
	 * @return modelAndView
	 */
	public static ModelAndView redirectToeWPDAdvanceSearchPage(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_ADVANCE_SEARCH_PAGE);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;			
	}
	/**
	 * Method redirects to the previous search Results page from the Edit page
	 * @return modelAndView
	 */
	public static ModelAndView redirectToWPDAdvanceSearchPage(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_ADVANCE_SEARCH_PAGE_WPD);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;			
	}
	/**
	 * @param request
	 * @return
	 * Redirecting to simulation page.
	 * @throws Exception 
	 * @throws EBXException 
	 */
	public static ModelAndView redirectToSimulationPage(HttpServletRequest request) throws EBXException, Exception {
		String contextPath = request.getContextPath();
		String is4010Exists="false";
		if(simulationFacade.is4010Exists()){
			is4010Exists="true";
		}
		request.setAttribute("is4010Exists", is4010Exists);
		request.setAttribute("systemForErrorValidation", getSystemListForErrorValidation());
		request.setAttribute("systemForContractInformation", getSystemListForContractInformation());
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_SIMULATION_PAGE);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;			
	}
	
	/**
	 * @return Returns the simulationFacade.
	 */
	public SimulationFacade getSimulationFacade() {
		return simulationFacade;
	}

	/**
	 * @param simulationFacade
	 *            The simulationFacade to set.
	 */
	public void setSimulationFacade(SimulationFacade simulationFacade) {
		this.simulationFacade = simulationFacade;
	}
	
	public static List getSystemListForErrorValidation(){
		List list = SimulationResourceBundle.getResourceBundle("systemsForErrorValidation",DomainConstants.WEB_CONSTANT);
		Collections.sort(list);
    	return list;
	}
	public static List getSystemListForContractInformation(){
		List list = SimulationResourceBundle.getResourceBundle("systemsForContractInformation",DomainConstants.WEB_CONSTANT);
		Collections.sort(list);
    	return list;
	}

	public static ModelAndView redirectToWPDAdvanceSearchPageFromResult(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_ADVANCE_SEARCH_PAGE_WPD_ADV);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;
	}
	
	
	public static ModelAndView redirectToEbxSpiderLandingPage(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath+WebConstants.REDIRECT_EBX_SPIDER_LANDING_PAGE_NAME);
		//redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;			
	}
}
