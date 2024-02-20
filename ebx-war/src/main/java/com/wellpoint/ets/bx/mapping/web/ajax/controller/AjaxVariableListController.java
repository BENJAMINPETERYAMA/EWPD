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

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;

public class AjaxVariableListController implements Controller {
	
	private LocateFacade locateFacade;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String variableId = request.getParameter("key");
		String mode = request.getParameter("name");
		Map map = new HashMap();
		
		if(variableId != null && !"".equals(variableId.trim())){
		    variableId = variableId.trim();
		    List allvariables = null;
		    if ("unmapped".equalsIgnoreCase(mode)) {
		    	allvariables = locateFacade.findUnmappedVariableStartingWith(variableId.toUpperCase());
		    } else {
		    	allvariables = locateFacade.findAllVariableStartingWith(variableId.toUpperCase());
		    }
		  
		    
		    if (allvariables != null) {
                List variableIdList = new ArrayList();
                for (Iterator itr = allvariables.iterator(); itr.hasNext();) {
                    Mapping mapping = (Mapping) itr.next();
                    AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(mapping.getVariable().getDescription(),
                            mapping.getVariable().getVariableId(),
                            mapping.getVariable().getVariableId());
                    variableIdList.add(autoPopulateVO1);
                }
                map.put("rows", variableIdList);
            }else {
                map.put("rows", new ArrayList());
            }
		}else{
		    map.put("rows", new ArrayList());//needed in case of blank
		}
		return new ModelAndView("jsonView",map);
	}

	public LocateFacade getLocateFacade() {
		return locateFacade;
	}

	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}
	
}
