package com.wellpoint.ets.bx.mapping.web.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.vo.Eb03CodeValueVO;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;
import com.wellpoint.ets.bx.mapping.web.Eb03AutoPopulateVO;

public class AjaxEB03DefaultAutocompleteListForEBXController implements Controller {
	

    private UMRuleMappingFacade umRuleMappingFacade;
	
	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String searchString = request.getParameter("key");		
		searchString = searchString.replaceAll("'", "''");
		String hippaSegmentName = request.getParameter("name");
		String searchType = "";
		if(request.getParameter("searchType") != null){
		 searchType = request.getParameter("searchType");	
		}
				
		Map map = new HashMap();
		Variable variable = new Variable();
	
			int noOfRecords = 20;
		//This code is added for mass update EB01 auto populate to get all EB01 values , 
		//  since there are multiple formats.
			System.out.println("Default Mapping" + searchString);
			List eb03List = umRuleMappingFacade.getEb03DefaultForAutocomplete(searchString,searchType);
		if(!eb03List.isEmpty()){
                List variableIdList = new ArrayList();
                	for (Iterator itr = eb03List.iterator(); itr.hasNext();) {
                        Eb03CodeValueVO hippaCodeValue = (Eb03CodeValueVO) itr.next();
                        /*if(searchType.equalsIgnoreCase("locate"))
                        {
                        	 AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(hippaCodeValue.getDescription(),hippaCodeValue.getValue(),hippaCodeValue.getValue());
                        	 variableIdList.add(autoPopulateVO1);
                        }
                        else{*/
                        AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(hippaCodeValue.getDescription(), hippaCodeValue.getValue(), hippaCodeValue.getValue());
                        
                   //Eb03AutoPopulateVO eb03AutoPopulateVO1 = new Eb03AutoPopulateVO(hippaCodeValue.getDescription(), hippaCodeValue.getValue(), hippaCodeValue.getValue(),hippaCodeValue.getEb03Default());
                        
                        variableIdList.add(autoPopulateVO1);
                       // }
                    }
                    map.put("rows", variableIdList);
                
            }else {
            	//log.debug("The Size of hippacode possible values list is zero");
                map.put("rows", new ArrayList());
            }
		return new ModelAndView("jsonView",map);
	}



}
