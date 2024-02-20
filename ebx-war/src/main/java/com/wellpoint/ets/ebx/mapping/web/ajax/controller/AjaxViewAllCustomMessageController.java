/*
 * <AjaxViewAllRuleIdController.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */

package com.wellpoint.ets.ebx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
/**
 * @author UST-GLOBAL This is a controller class for rendering the 
 * view history with all the audit trail records of a  rule id
 */

public class AjaxViewAllCustomMessageController implements Controller {
	private LocateFacade locateCustomMessageFacade;
	/**
	 * Get all the audit trail records of a particular
	 * rule id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
	 	
		Map map = new HashMap();
		Mapping mapping = new Mapping();
		Rule rule = new Rule();	
		rule.setHeaderRuleId(request.getParameter("ruleId"));
		mapping.setRule(rule);	
		SPSId spsId = new SPSId();
		spsId.setSpsId(request.getParameter("spsId"));
		mapping.setSpsId(spsId);
		
		String fromView = request.getParameter("fromMappingView");
		List mappings = locateCustomMessageFacade.getRecords(mapping, null, null, -1, -1, null);
		
		 if((null != mappings) && (!mappings.isEmpty())){
	        	
	       	mapping = (Mapping) mappings.get(0);
			map.put("auditTrailList", mapping.getAuditTrails());
			if(null != fromView && (fromView.equals("true"))){
				
				ModelAndView modelAndView = new ModelAndView("viewallaudit",map);
				return modelAndView;
			}
			else{
				ModelAndView modelAndView = new ModelAndView("mappingviewhistory",map);
				return modelAndView;
			}
		 }
		 else{			 
				List infoList = new ArrayList();
	            infoList.add("No results to display");
	            map.put(WebConstants.INFO_MESSAGES, infoList);
	            ModelAndView modelAndView = new ModelAndView("mappingviewhistory",map);
				return modelAndView;
		 }
		
	 }
	public LocateFacade getLocateCustomMessageFacade() {
		return locateCustomMessageFacade;
	}
	public void setLocateCustomMessageFacade(LocateFacade locateCustomMessageFacade) {
		this.locateCustomMessageFacade = locateCustomMessageFacade;
	}



}
