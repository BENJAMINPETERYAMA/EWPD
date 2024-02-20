/*
 * Created on May 25, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;

/**
 * @author u19278
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewCreateMappingPageController extends MultiActionController{
	
	private VariableMappingFacade variableMappingFacade;
	private LocateFacade locateFacade;
	
	private ModelAndView handleRequest(String variableId, String loggedInUser, HttpServletRequest request) throws Exception {
	    String pageName =null;
		Variable variable = new Variable();
		 HttpSession session = request.getSession();
		variable.setVariableId(variableId.trim());
		List variableWithInfoList = null;
		pageName = request.getParameter("pageName");
		List variableList = new ArrayList();
		variableList.add(variableId);
		List errorMessagesList = new ArrayList();
		String hippaSegmentToPopulate = "";
		if(null == variable.getVariableId() || variable.getVariableId().equals("") ){
			errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.VARIABLE_MANDATORY, null));
			ModelAndView modelAndView=new ModelAndView();
			if(null != pageName && pageName.trim().equals("advanceSearch")){
				modelAndView =WebUtil.redirectToWPDAdvanceSearchPage(request);
			}else{
				modelAndView = new ModelAndView("viewlandingpage");
			}
			modelAndView = redirectToLandingPage(modelAndView,loggedInUser);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessagesList);
			return modelAndView;
		}
		if(null != variable.getVariableId() && !variable.getVariableId().equals("")){
			int statusCode = variableMappingFacade.isValidVariableIDStatus(variable.getVariableId());
			if(statusCode == 1){
				Mapping mapping = new Mapping();
				variableWithInfoList = variableMappingFacade.viewVariableDetails(variable);
				// START-Code change as part of BXNI CR35
				List<String>  accumList = null;
				Variable newVariable;
				newVariable=(Variable) variableWithInfoList.get(0);
				String accum=newVariable.getWpdAccumulator();
				if(accum !=null  && accum !=""){
					 
					  accumList= Arrays.asList(accum.split(","));
				}
				// END-Code change as part of BXNI CR35
				ModelAndView modelAndView = new ModelAndView("createmapping");		
				modelAndView.addObject("variableWithInfoList", variableWithInfoList);
				modelAndView.addObject("accumList",accumList);
				//BXNI Change
				hippaSegmentToPopulate = DomainConstants.EB09_NAME;
				mapping = variableMappingFacade.autoPopulateByFormat((Variable)variableWithInfoList.get(0), mapping, hippaSegmentToPopulate,null);//Ends
				HippaSegment hs = new HippaSegment();
				if(null == mapping.getEb03()){
					mapping.setEb03(hs);
				}
				if(null == mapping.getEb01()){
					mapping.setEb01(hs);
				} 
				pageName = request.getParameter("pageName");
				if(null!=pageName)
					mapping.setPageFrom(pageName);  //karthik
				modelAndView.addObject("mapping", mapping);
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
				return modelAndView;
			}
			else if(statusCode == 0){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.INVALID_VARIABLE_LEGACY, variableList));
			}
			else if(statusCode == -1){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.INVALID_VARIABLE_MAPPING_EXIST, variableList));
			}
			else if(statusCode == -2){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.INVALID_VARIABLE_NOT_APPLICABLE, variableList));
			}
			else if(statusCode == -3){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.INVALID_VARIABLE_FORMAT, variableList));
			}
	}
	ModelAndView modelAndView=new ModelAndView();
	if(null != pageName && pageName.trim().equals("advanceSearch")){
		modelAndView =WebUtil.redirectToWPDAdvanceSearchPage(request);
	}else{
		modelAndView = new ModelAndView("viewlandingpage");
	}
	modelAndView = redirectToLandingPage(modelAndView,loggedInUser);
	modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessagesList);
	return modelAndView;
	}
	
	public ModelAndView redirectToLandingPage(ModelAndView modelAndView,String loggedInUser){
		   List unmappedVariables = locateFacade.findAllUnmappedVariables();//List<Mapping>
		   List inProgressVariables = locateFacade.findAllInProgressVariables(loggedInUser,51, false);//List<Mapping>   
		   BxUtil.encodeMappingsList(inProgressVariables,unmappedVariables);
		   modelAndView.addObject("unmappedVariables", unmappedVariables)
		    .addObject("inProgressVariables", inProgressVariables)
		    .addObject("variable", new Variable()); 
		   
		   return modelAndView;
		  }
	
	public ModelAndView viewFromCreate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		return handleRequest(request.getParameter("selectedVariableId").trim(),loggedInUser, request);
	}
	
	public ModelAndView viewFromUnMapped(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		return handleRequest(request.getParameter("variableIdHidden").trim(),loggedInUser, request);
	}
	
	public ModelAndView viewFromLocate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		return handleRequest(request.getParameter("variableId").trim(),loggedInUser, request);
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


