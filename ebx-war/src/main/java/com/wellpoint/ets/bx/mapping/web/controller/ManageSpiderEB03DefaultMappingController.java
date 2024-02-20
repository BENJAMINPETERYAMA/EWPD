package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03DefaultMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Exclusion;
import com.wellpoint.ets.bx.mapping.domain.entity.PageSpider;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.view.ExcelEB03DefaultMappingView;
import com.wellpoint.ets.bx.mapping.web.view.ExcelUnmappedRuleView;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class ManageSpiderEB03DefaultMappingController extends MultiActionController {

	private static Logger log = Logger.getLogger(ManageSpiderEB03DefaultMappingController.class);

	private UMRuleMappingFacade umRuleMappingFacade;

	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}

		
	 public ModelAndView createEB03DefaultMapping(HttpServletRequest request,
				HttpServletResponse response) {
		   
	//	 @RequestParam(name="eb03") String eb03,@RequestParam(name="eb03Description") String eb03Description
		
		 List<SpiderUMRuleMappingVO> resultList = new ArrayList<SpiderUMRuleMappingVO>();
		   String eb03 = request.getParameter("eb03Val");
		   String eb03Default = request.getParameter("eb03Defaultval");
		 
		   EB03DefaultMapping eb03DefaultMapping = new EB03DefaultMapping();
		   
		   eb03DefaultMapping.setEb03(eb03);
		   eb03DefaultMapping.setEb03Default(eb03Default);
		   eb03DefaultMapping.setCreatedBy(request.getAttribute(SecurityConstants.USER_NAME).toString());
		   /*System.out.println(eb03 + eb03Default);*/
		    int defaultCount = umRuleMappingFacade.findEb03DefaultMappingsPresentOrNot(eb03);
		    List  eb03MappingDataList =  umRuleMappingFacade.validateEb03DefaultMapping(eb03,eb03Default);
		   
		    /*System.out.println("Existing mapping list"+ eb03MappingDataList);*/
		    
		    if(!eb03MappingDataList.isEmpty()){
		    	
		    	String section = "section";
		    	
		    	PageSpider page = new PageSpider();
		    	page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
		    	
		    	page.setTotalNoOfRecords(eb03MappingDataList.size());
				if (request.getParameter(section).equalsIgnoreCase("Next")
						|| request.getParameter(section).equalsIgnoreCase("fromLanding")) {
					if (page.getCurrentPage() >= 0 && page.getCurrentPage() < page.getTotalNoOfPages()) {
						page.setCurrentPage(page.getCurrentPage() + 1);
					}
				}

				if (request.getParameter(section).equalsIgnoreCase("Previous")) {
					if (page.getCurrentPage() > 1) {
						page.setCurrentPage(page.getCurrentPage() - 1);
					}
				}
				if (request.getParameter(section).equalsIgnoreCase("Last")) {
					page.setCurrentPage(page.getTotalNoOfPages());
				}
				if (request.getParameter(section).equalsIgnoreCase("First")) {
					page.setCurrentPage((page.getTotalNoOfPages() / page.getTotalNoOfPages()));
				}
		    	
				/*System.out.println("testResultsList Before :" + eb03MappingDataList.size());*/
				int startIndex = eb03MappingDataList.size() < page.getNoOfRecordsPerPage() ? 0 : page.getStartRowNum() - 1;
				int endIndex = eb03MappingDataList.size() < page.getNoOfRecordsPerPage() ? eb03MappingDataList.size()
						: page.getTotalNoOfPages() == page.getCurrentPage() ? eb03MappingDataList.size() : page.getEndRowNum();
				eb03MappingDataList = eb03MappingDataList.subList(startIndex, endIndex);
				/*System.out.println("testResultsList after :" + eb03MappingDataList.size());*/
		    	
		    	for (Iterator itr = eb03MappingDataList.iterator(); itr.hasNext();) {
					SpiderUMRuleMappingVO spiderUMRuleMapping = (SpiderUMRuleMappingVO) itr.next();

					resultList.add(spiderUMRuleMapping);
				}
		    	
		    	
		    	ModelAndView modelAndView = new ModelAndView();
		    			
		    	SpiderUMRuleMappingVO ruleIdWithInfoList;
				ruleIdWithInfoList = resultList.get(0);
		    	
		    	modelAndView.setViewName("defaultmappingresultsForEb03");
		    	modelAndView.addObject("ruleIdWithInfoList", ruleIdWithInfoList);
		    	modelAndView.addObject("eb03Exclude", eb03Default);
				modelAndView.addObject("persisttype", "duplicate");
				modelAndView.addObject("locateResultsList", eb03MappingDataList);
				modelAndView.addObject("page", page);
				modelAndView.setStatus(HttpStatus.CREATED);
				return modelAndView;
		    	
		    }else if(defaultCount == 0)
		    {		    			   	    		
		    	umRuleMappingFacade.createEb03DefaultMapping(eb03DefaultMapping);
				
	    	    Map dummyMap = new HashMap(); 
	    	    dummyMap.put("dummyVal", true);
				
				request.setAttribute("actions", DomainConstants.EMPTY);
				return new ModelAndView("jsonView",dummyMap);
		    }
		    else
		    {
		    	Map dummyMap = new HashMap(); 
	    	    dummyMap.put("dummyVal", true);
				
				request.setAttribute("actions", DomainConstants.EMPTY);
				return new ModelAndView("jsonView",dummyMap);
		    }
	 }
	
	
	 
	 public ModelAndView deleteEb03DefaultMapping(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		 String eb03Value = request.getParameter("eb03Value");
		     /*
			int mappingCount = umRuleMappingFacade.findEb03PresentOrNot(eb03Value);
			if (mappingCount != 0) {*/
				Boolean result = umRuleMappingFacade.deleteEb03DefaultMapping(eb03Value);
			//}
			return null;
	}

    public ModelAndView showManageEB03DefaultMappingPage(HttpServletRequest request,
			HttpServletResponse response) {
	   
	    List<EB03DefaultMapping> eb03Exclusions = new ArrayList<EB03DefaultMapping>();
	
	    List<Object> testResultsList = umRuleMappingFacade.findEb03DefaultMappings();
	    
	    String section = "section";
    	
    	PageSpider page = new PageSpider();
    	page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
    	
    	page.setTotalNoOfRecords(testResultsList.size());
		if (request.getParameter(section).equalsIgnoreCase("Next")
				|| request.getParameter(section).equalsIgnoreCase("fromLanding")) {
			if (page.getCurrentPage() >= 0 && page.getCurrentPage() < page.getTotalNoOfPages()) {
				page.setCurrentPage(page.getCurrentPage() + 1);
			}
		}

		if (request.getParameter(section).equalsIgnoreCase("Previous")) {
			if (page.getCurrentPage() > 1) {
				page.setCurrentPage(page.getCurrentPage() - 1);
			}
		}
		if (request.getParameter(section).equalsIgnoreCase("Last")) {
			page.setCurrentPage(page.getTotalNoOfPages());
		}
		if (request.getParameter(section).equalsIgnoreCase("First")) {
			page.setCurrentPage((page.getTotalNoOfPages() / page.getTotalNoOfPages()));
		}
    	
		/*System.out.println("testResultsList Before :" + testResultsList.size());*/
		int startIndex = testResultsList.size() < page.getNoOfRecordsPerPage() ? 0 : page.getStartRowNum() - 1;
		int endIndex = testResultsList.size() < page.getNoOfRecordsPerPage() ? testResultsList.size()
				: page.getTotalNoOfPages() == page.getCurrentPage() ? testResultsList.size() : page.getEndRowNum();
		testResultsList = testResultsList.subList(startIndex, endIndex);
		/*System.out.println("testResultsList after :" + testResultsList.size());
		System.out.println(page.toString());*/

		if (testResultsList != null) {
			for (Iterator itr = testResultsList.iterator(); itr.hasNext();) {
				EB03DefaultMapping eb03ExclusiveValues = (EB03DefaultMapping) itr.next();

				eb03Exclusions.add(eb03ExclusiveValues);
			}
			/*System.out.println("List from DB" + testResultsList);*/
		}
		ModelAndView modelAndView = new ModelAndView("manageEB03Default");
		modelAndView.addObject("visibility", "none");
		modelAndView.addObject("locateEb03Default",eb03Exclusions);
		modelAndView.addObject("page",page);
		
		request.setAttribute("actions", DomainConstants.EMPTY);
		return modelAndView;
	}
   

   public ModelAndView defaultMappedEb03Excel(HttpServletRequest request,
			HttpServletResponse response) {
   
	   String eb03 = request.getParameter("eb03Val");
	   String eb03Default = request.getParameter("eb03Defaultval");
	 
	   EB03DefaultMapping eb03DefaultMapping = new EB03DefaultMapping();
	   
	   eb03DefaultMapping.setEb03(eb03);
	   eb03DefaultMapping.setEb03Default(eb03Default);
	   eb03DefaultMapping.setCreatedBy(request.getAttribute(SecurityConstants.USER_NAME).toString());
	   /*System.out.println(eb03 + eb03Default);*/
	   
	    List  eb03MappingDataList =  umRuleMappingFacade.validateEb03DefaultMapping(eb03,eb03Default);
	    
	    ExcelEB03DefaultMappingView eb03MappedRule = new ExcelEB03DefaultMappingView(eb03MappingDataList);
		ModelAndView modelAndView = new ModelAndView(eb03MappedRule);
		return modelAndView;
   }
	
	
}
