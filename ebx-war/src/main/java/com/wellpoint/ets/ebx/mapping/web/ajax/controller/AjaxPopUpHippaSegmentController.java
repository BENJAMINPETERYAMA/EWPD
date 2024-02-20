/*
 * <AjaxPopUpHippaSegmentController.java>
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
import java.util.StringTokenizer;
import org.owasp.esapi.ESAPI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.domain.entity.Catalog;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.ValidationUtility;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
/**
 * @author UST-GLOBAL This is a controller class for the popup
 * of hippasegments
 */
public class AjaxPopUpHippaSegmentController implements Controller{

	private static Logger logger = Logger.getLogger(AjaxPopUpHippaSegmentController.class);
	private LocateFacade locateRuleIdFacade;
	/**
	 * Populating the popup with the possible values of a hippasegment
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 public ModelAndView handleRequest(HttpServletRequest request,
				HttpServletResponse response) throws EBXException {
	        String hippaSegmentName = request.getParameter("hippaSegmentName");	

	        String[] spsFormats = request.getParameterValues("spsFormat");
	        
	        //Added as part of SSCR 19537
	        String pageName = request.getParameter("pageName");
	        //System.out.println(pageName);
	        logger.info(ESAPI.encoder().encodeForHTML(pageName));
	        //Ends here
	        
	        List formats = new ArrayList();
	        ValidationUtility validationUtility = new ValidationUtility();
	        if(null !=spsFormats && null != spsFormats[0] && !(spsFormats[0].trim().equals(""))){
	        	
	        	String format = spsFormats[0];	        
		        StringTokenizer tokens = new StringTokenizer(format,",");		       
		        while(tokens.hasMoreTokens()){		    	  
		        	formats.add(tokens.nextToken());
		        }
	        }
	        formats = validationUtility.removeDuplicate(formats);
	        String searchText = request.getParameter("searchText");	  
	        // SIT fix for EB02 textbox hiding in variable
	        String mappingItem = request.getParameter("mappingItem");
	        
			HippaSegment hippaSegment = null;
			List items = null;
			Catalog catalog = new Catalog();			
			
			if(searchText != null && !"".equals(searchText.trim())){
				searchText = searchText.trim().toUpperCase();					    
			} 
			if(null !=hippaSegmentName){
				catalog.setCatalogName(hippaSegmentName.toUpperCase());	
			}
			
			boolean getItemsEB01 = false;
		    if(hippaSegmentName != null && !(hippaSegmentName.equals(DomainConstants.EB01_NAME))){
		    	getItemsEB01 = false;				
		    }
		    else if(hippaSegmentName != null && (hippaSegmentName.equals(DomainConstants.EB01_NAME)) &&
		    		null != formats && !(formats.isEmpty())){
		    	
		    	getItemsEB01 = true;			
		    }
		    else{
		    	
		    	getItemsEB01 = false;
		    }
		    if(getItemsEB01){
		    	
		    	items = locateRuleIdFacade.getItems(catalog, searchText, false,formats);
		    }
		    else{
		    	
		    	items = locateRuleIdFacade.getItems(catalog, searchText, false,null);
		    }
			if(null != items){
				/* Reference Data Values -  START*/
				hippaSegment = new HippaSegment();
				hippaSegment.setName(hippaSegmentName);
				hippaSegment.setHippaCodePossibleValues(items);
				/* Reference Data Values -  END*/
			}
			List infoList = new ArrayList();
			if(hippaSegment == null){			    
			    hippaSegment = new HippaSegment();
			    infoList.add("No results to display");
			}else if(hippaSegment.getHippaCodePossibleValues().size() <= 0){
			    infoList.add("No results to display");
			}			
			Map map = new HashMap();
			map.put("hippaSegment", hippaSegment);
			map.put("searchText", searchText);
			map.put(WebConstants.INFO_MESSAGES, infoList);
			map.put("mappingItem", mappingItem);
			//For SSCR 19537
			map.put("pageName", pageName);
			return new ModelAndView("popup",map);
	    }
	 
	
	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}

	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}
}
