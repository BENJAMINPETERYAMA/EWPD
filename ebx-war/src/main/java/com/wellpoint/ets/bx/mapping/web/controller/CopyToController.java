/*
 * Created on Jun 11, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.WebConstants;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CopyToController extends MultiActionController {
	
	private VariableMappingFacade variableMappingFacade;
	
	//for invalid variable
	public ModelAndView invalidVariable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = new Mapping();
		Variable variableNew = new Variable();
		Variable variableOld = new Variable();
		
		String targetVariableId = request.getParameter("variableIdForCopyTo");
		/*if(targetVariableId!=null){
		    targetVariableId = targetVariableId.trim();
		}*/
		List variableList = new ArrayList();
		variableList.add(targetVariableId);
		variableNew.setVariableId(targetVariableId); 
		variableOld.setVariableId(request.getParameter("oldVarID"));
		variableOld.setDescription(request.getParameter("oldVarDesc"));
		variableOld.setVariableFormat(request.getParameter("oldVarFormate"));
		mapping.setVariable(variableOld);
		mapping.setPageFrom(pageName);
		List errorMessagesList = new ArrayList();
		ModelAndView modelAndView = new ModelAndView("jsonView");
		if(null == variableNew.getVariableId() || variableNew.getVariableId().equals("") ){
			errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.VARIABLE_MANDATORY, null));
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessagesList);
			return modelAndView;
		}
		if(null != variableNew.getVariableId() && !variableNew.getVariableId().equals("")){
			int statusCode = variableMappingFacade.isValidVariableIDStatus(variableNew.getVariableId());
			
			if(statusCode == 1){
				modelAndView.addObject("mapping",mapping);
				return modelAndView;
			}
			else if(statusCode == 0){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.COPY_INVALID_VARIABLE_LEGACY, variableList));
			}
			else if(statusCode == -1){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.COPY_INVALID_VARIABLE_MAPPING_EXIST, variableList));
			}
			else if(statusCode == -2){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.COPY_INVALID_VARIABLE_NOT_APPLICABLE, variableList));
			}
			else if(statusCode == -3){
				
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.COPY_INVALID_VARIABLE_FORMAT, variableList));
			}
			/*if(!(variableMappingFacade.isValidVariableIDStatus(variableNew.getVariableId()))){
				errorMessagesList.add(BxResourceBundle.getReourceBundle(WebConstants.COPY_INVALID_VARIABLE, null));
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessagesList);
				return modelAndView;
			}*/
		}
		
		modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessagesList);
		return modelAndView;
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
