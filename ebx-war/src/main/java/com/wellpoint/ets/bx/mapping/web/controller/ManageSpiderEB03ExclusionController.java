package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class ManageSpiderEB03ExclusionController extends MultiActionController {

	private static Logger log = Logger.getLogger(ManageSpiderEB03ExclusionController.class);

	private UMRuleMappingFacade umRuleMappingFacade;

	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}

	
	
	
	 public ModelAndView createEB03Exclusion(HttpServletRequest request,
				HttpServletResponse response) {
		   
	//	 @RequestParam(name="eb03") String eb03,@RequestParam(name="eb03Description") String eb03Description
		
		   String eb03 = request.getParameter("eb03Val");
		   String eb03Description = request.getParameter("eb03Label");
		 
		  /* System.out.println("EB03========================"+eb03);
		   
		   System.out.println("EB03 Description========================"+eb03Description);*/
		   
		   EB03Exclusion eb03Exclusion = new EB03Exclusion();
		   
		   eb03Exclusion.setEb03(eb03);
		   eb03Exclusion.setEb03Description(eb03Description);
		   eb03Exclusion.setCreatedBy(request.getAttribute(SecurityConstants.USER_NAME).toString());
		   
		   //List  eb03List = umRuleMappingFacade.validateEb03Exclusion(eb03);
		    
		  //  if(eb03List.isEmpty()){
		    	 //umRuleMappingFacade.createEb03Exclusion(eb03Exclusion);  	
		   /* }else{
		    	Map<String,List> map = new HashMap<String,List>();
		    	map.put("eb03List",eb03List);		    	
		    	ModelAndView modelAndView = new ModelAndView("manageEB03Exclusion");
				modelAndView.addObject("result", map);		    	
		    }
		   */
		   
	       boolean duplicate = checkDuplicate(request,response);
		   if(!duplicate)
		   {
			   umRuleMappingFacade.createEb03Exclusion(eb03Exclusion);
		   }
				   
		   List<EB03Exclusion> eb03Exclusions = new ArrayList<EB03Exclusion>();
		   	   
			ModelAndView modelAndView = new ModelAndView("manageEB03Exclusion");
			modelAndView.addObject("visibility", "none");
			modelAndView.addObject("locateEb03Exclusions",eb03Exclusions);
			
			request.setAttribute("actions", DomainConstants.EMPTY);
			return modelAndView;
		}
	
	
	 
	 public ModelAndView deleteEb03ExclusionMapping(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		    String eb03Value = request.getParameter("eb03Value");
			int mappingCount = umRuleMappingFacade.findEb03PresentOrNot(eb03Value);
			if (mappingCount != 0) {
				Boolean result = umRuleMappingFacade.deleteEb03Exclusion(eb03Value);
			}
			return null;
		}

   public ModelAndView showManageEB03ExclusionPage(HttpServletRequest request,
			HttpServletResponse response) {
	   
	   
	   
	   List<EB03Exclusion> eb03Exclusions = new ArrayList<EB03Exclusion>();
	   
	  /* eb03Exclusions.add(new EB03Exclusion("AL","Test"));
	   eb03Exclusions.add(new EB03Exclusion("A2","Test"));
	   eb03Exclusions.add(new EB03Exclusion("A3","Test"));
	   eb03Exclusions.add(new EB03Exclusion("A4","Test"));
	   eb03Exclusions.add(new EB03Exclusion("A5","Test"));*/

	    List<Object> testResultsList = umRuleMappingFacade.findEb03Exclusions();

		if (testResultsList != null) {
			for (Iterator itr = testResultsList.iterator(); itr.hasNext();) {
				EB03Exclusion eb03ExclusiveValues = (EB03Exclusion) itr.next();

				eb03Exclusions.add(eb03ExclusiveValues);
			}
			/*System.out.println("List from DB" + testResultsList);*/
		}
		ModelAndView modelAndView = new ModelAndView("manageEB03Exclusion");
		modelAndView.addObject("visibility", "none");
		modelAndView.addObject("locateEb03Exclusions",eb03Exclusions);
		
		request.setAttribute("actions", DomainConstants.EMPTY);
		return modelAndView;
	}
   
   public boolean checkDuplicate(HttpServletRequest request,
			HttpServletResponse response) {
	 String eb03 = request.getParameter("eb03Val");
	 boolean duplicate =false;
	 List<Object> testResultsList = umRuleMappingFacade.findEb03Exclusions();
	 List<EB03Exclusion> eb03Exclusions = new ArrayList<EB03Exclusion>();
	 if (testResultsList != null) {
			for (Iterator itr = testResultsList.iterator(); itr.hasNext();) {
				EB03Exclusion eb03ExclusiveValues = (EB03Exclusion) itr.next();

				eb03Exclusions.add(eb03ExclusiveValues);
			}
			/*System.out.println("List from DB" + testResultsList);*/
	}
	for(EB03Exclusion excln : eb03Exclusions)
	{
		if(excln.getEb03().equalsIgnoreCase(eb03))
		{
			duplicate = true;
			break;
		}
	}
	return duplicate;
   }
	
	
}
