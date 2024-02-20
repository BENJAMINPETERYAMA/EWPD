/*
 * <AjaxAutocompleteController.java>
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.domain.entity.Catalog;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.ValidationUtility;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
/**
 * @author UST-GLOBAL This is a controller class for autocomplete of all the
 * hippasegments, rule id and sps id
 */
public class AjaxAutocompleteController implements Controller{
	
	private LocateFacade locateRuleIdFacade;
	private LocateFacade locateSpsIdFacade;
	/**
	 * Populating the autocomplete enabled text field with 
	 * hippasegment or rule id or sps id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		
		String searchText = request.getParameter("key");
		/* Reference Data Values -  START*/
		List items = null;
		/* Reference Data Values -  END*/
		String name = request.getParameter("name");

		Map map = new HashMap();		
		HippaSegment hippaSeg = new HippaSegment();
		ValidationUtility validationUtility = new ValidationUtility();
		
		Catalog catalog = new Catalog();
		
		 String[] spsFormats = request.getParameterValues("spsFormat");
	        List formats = new ArrayList();
	        if(null !=spsFormats && null != spsFormats[0] && !(spsFormats[0].trim().equals(""))){
	        	
	        	String format = spsFormats[0];	        
		        StringTokenizer tokens = new StringTokenizer(format,",");		       
		        while(tokens.hasMoreTokens()){		    	  
		        	formats.add(tokens.nextToken());
		        }
	        }	   
	    formats = validationUtility.removeDuplicate(formats);
		if(searchText != null && !"".equals(searchText.trim())){
		    searchText = searchText.trim().toUpperCase();
		}
		searchText = searchText + "%";
		if(name != null){
			// if its a Hippasegment
			if((!DomainConstants.RULE_ID.equals(name)) && (!DomainConstants.SPS_ID.equals(name)) ){				 
				catalog.setCatalogName(name.toUpperCase());
				boolean getItemsEB01 = false;
			    if(catalog.getCatalogName() != null && !(catalog.getCatalogName().equals(DomainConstants.EB01_NAME))){
			    	getItemsEB01 = false;				
			    }
			    else if(catalog.getCatalogName() != null && (catalog.getCatalogName().equals(DomainConstants.EB01_NAME)) &&
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
					hippaSeg.setName(name);
					hippaSeg.setHippaCodePossibleValues(items);
					/* Reference Data Values -  END*/
				}
			    if (hippaSeg != null && null !=hippaSeg.getHippaCodePossibleValues()) {
	                List hippaCodeList = new ArrayList();
	                	for (Iterator itr = hippaSeg.getHippaCodePossibleValues().iterator(); itr.hasNext();) {
	                        HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
	                        AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(hippaCodeValue.getDescription(), hippaCodeValue.getValue(), hippaCodeValue.getValue());
	                        hippaCodeList.add(autoPopulateVO1);
	                    }
	                    map.put(WebConstants.ROWS, hippaCodeList);
	                
	            }else {	            	
	                map.put(WebConstants.ROWS, new ArrayList());
	            }
			} 
			// if it's a Rule id
			if(DomainConstants.RULE_ID.equals(name)){
				
				List status = new ArrayList();
				status.add(DomainConstants.UNMAPPED_STATUS);
				status.add(DomainConstants.MAPPED_STATUS);
				Mapping mapping = new Mapping();
				Rule rule = new Rule();
				rule.setHeaderRuleId(searchText);
				mapping.setRule(rule);
				List mappings = locateRuleIdFacade.getRecords(mapping, status, null, 21, 21, null);
				
				 if (mappings != null) {
		                List variableIdList = new ArrayList();
		                for (Iterator itr = mappings.iterator(); itr.hasNext();) {
		                    Mapping mappingResult = (Mapping) itr.next();	
		                    mappingResult.getRule().setRuleDesc(StringEscapeUtils.unescapeHtml(mappingResult.getRule().getRuleDesc()));
		                    AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(StringEscapeUtils.unescapeHtml(mappingResult.getRule().getRuleDesc()),
		                    		mappingResult.getRule().getHeaderRuleId(),
		                    		mappingResult.getRule().getHeaderRuleId());
		                    variableIdList.add(autoPopulateVO1);
		                }
		                map.put(WebConstants.ROWS, variableIdList);
		            }
				 else {
		                map.put(WebConstants.ROWS, new ArrayList());
		            }
			}
//			 if it's a Sps id
			if(DomainConstants.SPS_ID.equals(name)){
				
				List status = new ArrayList();
				status.add(DomainConstants.UNMAPPED_STATUS);
				status.add(DomainConstants.MAPPED_STATUS);
				Mapping mapping = new Mapping();
				SPSId spsId = new SPSId();
				spsId.setSpsId(searchText);
				mapping.setSpsId(spsId);
				List mappings = locateSpsIdFacade.getRecords(mapping, status, null, 21, 21, null);
				
				 if (mappings != null) {
		                List spsIdList = new ArrayList();
		                for (Iterator itr = mappings.iterator(); itr.hasNext();) {
		                    Mapping mappingResult = (Mapping) itr.next();	
		                    mappingResult.getSpsId().setSpsDesc(StringEscapeUtils.unescapeHtml(mappingResult.getSpsId().getSpsDesc()));
		                    AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(mappingResult.getSpsId().getSpsDesc(),
		                    		mappingResult.getSpsId().getSpsId(),
		                    		mappingResult.getSpsId().getSpsId());
		                    spsIdList.add(autoPopulateVO1);
		                }
		                map.put(WebConstants.ROWS, spsIdList);
		            }
				 else {
		                map.put(WebConstants.ROWS, new ArrayList());
		            }
			}			
		}
		else{
		    map.put(WebConstants.ROWS, new ArrayList());//needed in case of blank
		}
		return new ModelAndView(WebConstants.JSON_VIEW,map);
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

}
