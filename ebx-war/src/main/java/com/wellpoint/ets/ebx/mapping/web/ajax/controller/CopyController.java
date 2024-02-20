/*
 * <CopyController.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
/**
 * @author UST-GLOBAL This is a controller class for checking the 
 * validity of the ruleid or sps id selected for copy to
 */
public class CopyController extends MultiActionController{
	
	private LocateFacade locateRuleIdFacade;
	private LocateFacade locateSpsIdFacade;
	private LocateFacade LocateCustomMessageFacade;
	
	/**
	 * Validate the rule id selected using the autocomplete for
	 * copy to
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invalidRuleId(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		String pageName = request.getParameter("pageFrom");
		
		Mapping mapping = new Mapping();
		Rule rule = new Rule();		
		List errorMessages = new ArrayList();
		mapping.setRule(rule);		
		mapping.getRule().setHeaderRuleId(request.getParameter("ruleIdForCopyTo"));
		
		List status = new ArrayList();
		status.add(DomainConstants.UNMAPPED_STATUS);
		status.add(DomainConstants.MAPPED_STATUS);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		List paramList = new ArrayList();
		
		List mappings = locateRuleIdFacade.getRecords(mapping, status, null, -1, 21, null);
//		 if rule id not in mapped and unmapped tables, invalid else get status
		// if status is not null, mapping exists else go to the create rule mapping page
		if(null != mappings && !mappings.isEmpty()){			
			if(null != mappings.get(0)){
				
				Mapping retrievedMapping = (Mapping) mappings.get(0);
				
				if(null == retrievedMapping.getVariableMappingStatus() || 
						(retrievedMapping.getVariableMappingStatus().equals(DomainConstants.UNMAPPED_STATUS))){
					
					mapping.getRule().setHeaderRuleId(request.getParameter("oldRuleID"));	
					mapping.setPageFrom(pageName);
					modelAndView.addObject("mapping", mapping);	
				}
				else{
					
					paramList.add(rule.getHeaderRuleId());
					errorMessages.add(BxResourceBundle.getResourceBundle
							(WebConstants.MAPPING_RULEID_EXISTS, paramList));				
					modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);						
				}					
			}
		}		
		else{
			errorMessages.add(BxResourceBundle.getResourceBundle("copy.ruleid.invalid", null));			
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);					
		}
		return modelAndView;
	}
	/**
	 * Validate the rule id selected using the autocomplete for
	 * copy to
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invalidSPSId(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = new Mapping();
		SPSId spsId = new SPSId();	
		List errorMessages = new ArrayList();
		List paramList = new ArrayList();
			
		spsId.setSpsId(request.getParameter("spsIdForCopyTo"));
		mapping.setSpsId(spsId);	
		
		List status = new ArrayList();
		status.add(DomainConstants.UNMAPPED_STATUS);
		status.add(DomainConstants.MAPPED_STATUS);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List mappings = locateSpsIdFacade.getRecords(mapping, status, null, -1, 21, null);
		
		if(null == mappings || mappings.isEmpty()){
			
			errorMessages.add(BxResourceBundle.getResourceBundle("copy.spsid.invalid", null));			
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
			return modelAndView;
		}
		else {
			Mapping retrievedMapping = (Mapping) mappings.get(0);
			
			if(null == retrievedMapping.getVariableMappingStatus() || 
					(retrievedMapping.getVariableMappingStatus().equals(DomainConstants.UNMAPPED_STATUS))){
				
				mapping.getSpsId().setSpsId(request.getParameter("oldSPSID"));
				mapping.setPageFrom(pageName);
				modelAndView.addObject("mapping", mapping);	
			}
			else{
				
				paramList.add(spsId.getSpsId());
				errorMessages.add(BxResourceBundle.getResourceBundle
						(WebConstants.MAPPING_SPSID_EXISTS, paramList));				
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);						
			}		
		}
		return modelAndView;
	}
	
	/**
	 * Validate the rule id  and SPS id selected using the autocomplete for
	 * copy to
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView invalidCustomMessage(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		Mapping mapping = new Mapping();
		HttpSession session = request.getSession();
		SPSId spsId = new SPSId();	
		Rule ruleId=new Rule();
		List errorMessages = new ArrayList();
		ruleId.setHeaderRuleId(request.getParameter("ruleIdForCopyTo"));
		spsId.setSpsId(request.getParameter("spsIdForCopyTo"));
		mapping.setSpsId(spsId);
		mapping.setRule(ruleId);
		List paramList = new ArrayList();
		String ruleDesc = null;
		String spsDesc = null;
		List statusForRuleAndSps = new ArrayList();			
		statusForRuleAndSps.add(DomainConstants.MAPPED_STATUS);
		statusForRuleAndSps.add(DomainConstants.UNMAPPED_STATUS);
		List ruleMappings = locateRuleIdFacade.getRecords(mapping, statusForRuleAndSps, null, -1, 21,null);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		// check whether rule entered is valid
		if(null == ruleMappings || ruleMappings.isEmpty()){				
			paramList.add(ruleId.getHeaderRuleId());
			String ruleInvalid = BxResourceBundle.getResourceBundle(WebConstants.INVALID_RULE_ID, paramList);		
			errorMessages.add(ruleInvalid);		
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);		
			return modelAndView;
		}
	//set the rule desc to mapping object
		else{
			
			Mapping mappedRule = (Mapping)ruleMappings.get(0);
			ruleDesc = BxUtil.escapeSpecialCharacters(mappedRule.getRule().getRuleDesc());
			mapping.getRule().setRuleDesc(ruleDesc);
			
			if(null != mappedRule.getVariableMappingStatus() && 
					mappedRule.getVariableMappingStatus().equalsIgnoreCase(DomainConstants.UNMAPPED_STATUS)){
				paramList.add(mappedRule.getRule().getHeaderRuleId());
				String unmappedRule = BxResourceBundle.getResourceBundle(
						WebConstants.UNMAPPED_RULE, paramList);
				errorMessages.add(unmappedRule);
				
				
					modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);	
					
				
			}
		}
		
	
	
		List spsMappings = locateSpsIdFacade.getRecords(mapping, statusForRuleAndSps, null, -1, 21, null);	
		// check whether the sps id is valid
		if(null == spsMappings || spsMappings.isEmpty()){				
			paramList.add(spsId.getSpsId());
			String spsInvalid = BxResourceBundle.getResourceBundle(WebConstants.INVALID_SPS_ID, paramList);		
			errorMessages.add(spsInvalid);		
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);		
			return modelAndView;
		}
		
		
     else{
			Mapping mappedSps = (Mapping)spsMappings.get(0);
			spsDesc = BxUtil.escapeSpecialCharacters(mappedSps.getSpsId().getSpsDesc());
			mapping.getSpsId().setSpsDesc(spsDesc);
		}
		
		
		
		List status = new ArrayList();
		//status.add(DomainConstants.VIEW_STATUS);
		
		
		status.clear();
		status.add(DomainConstants.CUSTOM_MESSAGE_CREATE);
		
		List mappings = LocateCustomMessageFacade.getRecords(mapping, status, null, -1, 21, null);
		
		if(null == mappings || mappings.isEmpty()){
			mapping.getSpsId().setSpsId(request.getParameter("oldSPSID"));
			mapping.getSpsId().setSpsDesc(request.getParameter("oldSpsDesc"));
			mapping.getRule().setHeaderRuleId(request.getParameter("oldRuleID"));
			mapping.getRule().setRuleDesc(request.getParameter("oldRuleDesc"));
			modelAndView.addObject("mapping",mapping);
			return modelAndView;
		}
		else{
			paramList.add(spsId.getSpsId());
			paramList.add(ruleId.getHeaderRuleId());
			String msgInvalid = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_CUSTOM_MSG_EXISTS, paramList);		
			errorMessages.add(msgInvalid);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);
			TokenGenerator.getInstance().saveToken(request);		
			modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
			return modelAndView;

		}
		
	}
	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}

	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}
	public LocateFacade getLocateSpsIdFacade() {
		return locateSpsIdFacade;
	}
	public void setLocateSpsIdFacade(LocateFacade locateSpsIdFacade) {
		this.locateSpsIdFacade = locateSpsIdFacade;
	}
	public LocateFacade getLocateCustomMessageFacade() {
		return LocateCustomMessageFacade;
	}
	public void setLocateCustomMessageFacade(LocateFacade locateCustomMessageFacade) {
		LocateCustomMessageFacade = locateCustomMessageFacade;
	}
}
