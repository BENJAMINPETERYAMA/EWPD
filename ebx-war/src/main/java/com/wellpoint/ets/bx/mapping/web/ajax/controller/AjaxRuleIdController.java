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
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;

public class AjaxRuleIdController implements Controller
{
    private UMRuleMappingFacade umRuleMappingFacade;
	
	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String ruleId = request.getParameter("key");
		String mode = request.getParameter("name");
		Map map = new HashMap();
		
		if(ruleId != null && !"".equals(ruleId.trim())){
			ruleId = ruleId.trim();
		    List allvariables = null;
		   
		    allvariables = umRuleMappingFacade.findAllRuleStartingWith(ruleId.toUpperCase());
		  
		    
		    if (allvariables != null) {
                List ruleIdList = new ArrayList();
                for (Iterator itr = allvariables.iterator(); itr.hasNext();) {
                    SpiderUMRuleMapping spiderUMRuleMapping = (SpiderUMRuleMapping) itr.next();
                    AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(spiderUMRuleMapping.getUmRuleDesc(),
                    		spiderUMRuleMapping.getUmRuleId(),spiderUMRuleMapping.getUmRuleId());
                    ruleIdList.add(autoPopulateVO1);
                }
                map.put("rows", ruleIdList);
            }else {
                map.put("rows", new ArrayList());
            }
		}else{
		    map.put("rows", new ArrayList());//needed in case of blank
		}
		return new ModelAndView("jsonView",map);
	}
}
