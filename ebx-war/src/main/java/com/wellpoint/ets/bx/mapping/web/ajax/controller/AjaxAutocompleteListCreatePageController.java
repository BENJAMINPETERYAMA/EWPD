/*
 * Created on May 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AjaxAutocompleteListCreatePageController implements Controller {
	private LocateFacade locateFacade;
	private static Logger log = Logger.getLogger(AjaxAutocompleteListCreatePageController.class);
	private int noOfRecords;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String searchString = request.getParameter("key");		
		searchString = searchString.replaceAll("'", "''");
		String hippaSegmentName = request.getParameter("name");
		Map map = new HashMap();
		Variable variable = new Variable();
		HippaSegment hippaSeg = null;
		if(hippaSegmentName.equalsIgnoreCase(DomainConstants.EB01_NAME)){
			variable.setVariableFormat(request.getParameter("varformat"));
		}		
		int noOfRecords = 20;
		//This code is added for mass update EB01 auto populate to get all EB01 values , 
		//  since there are multiple formats.
		if(BxUtil.hasText(hippaSegmentName) && hippaSegmentName.equalsIgnoreCase(DomainConstants.EB01_NAME) &&
				!BxUtil.hasText(variable.getVariableFormat())){
			hippaSeg = locateFacade.getAllEB01ForAutocomplete(searchString, noOfRecords);
		}if(BxUtil.hasText(hippaSegmentName) && hippaSegmentName.equalsIgnoreCase(DomainConstants.HSD02_NAME) &&
				!BxUtil.hasText(variable.getVariableFormat())){
			List allvariables = new ArrayList();
			allvariables = locateFacade.findAllVariableStartingWith(searchString.toUpperCase());
			if(allvariables != null && !allvariables.isEmpty()){
                List variableIdList = new ArrayList();
                for (Iterator itr = allvariables.iterator(); itr.hasNext();) {
                    Mapping mapping = (Mapping) itr.next();
                    AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(mapping.getVariable().getDescription(),
                            mapping.getVariable().getVariableId(),
                            mapping.getVariable().getVariableId());
                    variableIdList.add(autoPopulateVO1); 
                }
                map.put("rows", variableIdList);
            }else{
            	map.put("rows", new ArrayList());
            }
			return new ModelAndView("jsonView",map);
		}if(BxUtil.hasText(hippaSegmentName) && hippaSegmentName.equalsIgnoreCase(DomainConstants.VARIABLE_IDENTIFIER_ICON) &&
				!BxUtil.hasText(variable.getVariableFormat())){
			List allvariables = new ArrayList();
			//isEBSegmentPresent
			boolean isEBSegmentPresent = locateFacade.isEBSegmentPresentIcon(searchString.toUpperCase());
			map.put("isEBSegmentPresent", isEBSegmentPresent);
			map.put("variableId", searchString.toUpperCase());
			return new ModelAndView("jsonView",map);
			
		}if(BxUtil.hasText(hippaSegmentName) && hippaSegmentName.equalsIgnoreCase(DomainConstants.VARIABLE_ID_ICON) &&
				!BxUtil.hasText(variable.getVariableFormat())){
			String ebMappingAssocDetails = locateFacade.getEBMappingAssocDetailsIcon(searchString.toUpperCase());
			map.put("ebMappingAssocDetails",ebMappingAssocDetails);
			return new ModelAndView("jsonView",map);
		}
		if(BxUtil.hasText(hippaSegmentName) && hippaSegmentName.equalsIgnoreCase(DomainConstants.VARIABLE_IDENTIFIER) &&
				!BxUtil.hasText(variable.getVariableFormat())){
			List allvariables = new ArrayList();
			//isEBSegmentPresent
			boolean isEBSegmentPresent = locateFacade.isEBSegmentPresent(searchString.toUpperCase());
			map.put("isEBSegmentPresent", isEBSegmentPresent);
			map.put("variableId", searchString.toUpperCase());
			return new ModelAndView("jsonView",map);
			
		}if(BxUtil.hasText(hippaSegmentName) && hippaSegmentName.equalsIgnoreCase(DomainConstants.VARIABLE_ID) &&
				!BxUtil.hasText(variable.getVariableFormat())){
			String ebMappingAssocDetails = locateFacade.getEBMappingAssocDetails(searchString.toUpperCase());
			map.put("ebMappingAssocDetails",ebMappingAssocDetails);
			return new ModelAndView("jsonView",map);
		}else{
			hippaSeg = locateFacade.getHippaSegmentsForAutocomplete(hippaSegmentName, searchString, variable, noOfRecords);
		}
		    if (hippaSeg != null && null !=hippaSeg.getHippaCodePossibleValues()) {
                List variableIdList = new ArrayList();
                	for (Iterator itr = hippaSeg.getHippaCodePossibleValues().iterator(); itr.hasNext();) {
                        HippaCodeValue hippaCodeValue = (HippaCodeValue) itr.next();
                        AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(hippaCodeValue.getDescription(), hippaCodeValue.getValue(), hippaCodeValue.getValue());
                        variableIdList.add(autoPopulateVO1);
                    }
                    map.put("rows", variableIdList);
                
            }else {
            	log.debug("The Size of hippacode possible values list is zero");
                map.put("rows", new ArrayList());
            }
		return new ModelAndView("jsonView",map);
	}

	public LocateFacade getLocateFacade() {
		return locateFacade;
	}

	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}
	/**
	 * @param noOfRecords The noOfRecords to set.
	 */
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
}
