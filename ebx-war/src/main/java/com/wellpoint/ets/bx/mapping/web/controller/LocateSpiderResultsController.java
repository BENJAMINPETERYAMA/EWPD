package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.PageSpider;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.view.ExcelUnmappedRuleView;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class LocateSpiderResultsController extends MultiActionController {

	private static Logger log = Logger.getLogger(LocateSpiderResultsController.class);

	private UMRuleMappingFacade umRuleMappingFacade;

	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}

	public ModelAndView locateRequest(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("locatespiderresults");

		try {

			String section = "section";

			String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
			String umRuleId = request.getParameter("umRuleId").trim();
			String umRuleDescription= request.getParameter("umRuleDescription").trim();
			String eb03 = request.getParameter("eb03").trim();

			Date date = new Date();
			
			if(umRuleDescription != null && (!umRuleDescription.equals(""))){
				List<Object>   ruleDescriptionList =   umRuleMappingFacade.findAllRuleDescriptionStartingWith(umRuleDescription);	
			    SpiderUMRuleMapping spdrUmDescription = (SpiderUMRuleMapping) ruleDescriptionList.get(0);
			    if(umRuleId == null || umRuleId.equals("")){
			    	umRuleId = spdrUmDescription.getUmRuleId();
			    }
			}

			SpiderUMRuleMapping umRule = new SpiderUMRuleMapping(umRuleId, eb03, null, "Testing", "Building", date);

			PageSpider page = new PageSpider();
			page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			// page.setTotalNoOfRecords(locateFacade.findTotalNoOfRecordsForLocate(variable));
			List<Object> testResultsList = umRuleMappingFacade.findAllMappingByRuleId(umRuleId);
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
			/*System.out.println("testResultsList after :" + testResultsList.size());*/

			List<SpiderUMRuleMappingVO> locateResultsList = new ArrayList<SpiderUMRuleMappingVO>();

			
			

			if (testResultsList != null) {
				for (Iterator itr = testResultsList.iterator(); itr.hasNext();) {
					SpiderUMRuleMappingVO spiderUMRuleMapping = (SpiderUMRuleMappingVO) itr.next();

					locateResultsList.add(spiderUMRuleMapping);
				}
			}
			
			HttpSession session = request.getSession();
			List errorMessagesList = new ArrayList();
			List ruleIdWithInfoList = null;
			List ruleIdList = new ArrayList();
			ruleIdList.add(umRuleId);


			int statusCode = umRuleMappingFacade.isValidRuleIDStatus(umRuleId);
			if (statusCode == 1) {
				ruleIdWithInfoList = umRuleMappingFacade.viewRuleIdDetails(umRuleId);
				SpiderUMRuleMapping newRule;
				newRule = (SpiderUMRuleMapping) ruleIdWithInfoList.get(0);
				newRule.setUmStatus(locateResultsList.get(0).getStatus());
				//System.out.println("List Results:::::::" + ruleIdWithInfoList.size());
				//ruleIdWithInfoList.
				modelAndView.addObject("ruleIdWithInfoList", newRule);
			} else if (statusCode == 0) {
				errorMessagesList.add(BxResourceBundle.getResourceBundle(WebConstants.INVALID_RULE_ID, ruleIdList));

			}
			/*System.out.println("Locate List Results:::::::" + locateResultsList.size());*/
			modelAndView.addObject("locateResultsList", locateResultsList);
			modelAndView.addObject("page", page);
			modelAndView.addObject("userId", userId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;

	}
	
	public ModelAndView viewFromEditSpider(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		return handleRequestForEdit(request.getParameter("ruleIdHidden").trim(),request.getParameter("ruleDescHidden").trim(),request.getParameter("ruleStatusHidden").trim(),loggedInUser, request);
	}

	private ModelAndView handleRequestForEdit(String ruleId,String ruleDesc,String ruleStatus, String loggedInUser, HttpServletRequest request) throws Exception 
	{	
	      ModelAndView modelAndView=new ModelAndView();
	      SpiderUMRuleMapping mappedRules = new SpiderUMRuleMapping();
	      mappedRules.setUmRuleId(ruleId);
	      mappedRules.setUmRuleDesc(ruleDesc);
	      mappedRules.setUmStatus(ruleStatus);
     
	      List<SpiderUMRuleMappingVO> editResultsList = new ArrayList<SpiderUMRuleMappingVO>();
	      List<Object> testResultsList = umRuleMappingFacade.findAllMappingByRuleIdForEdit(ruleId);

			if (testResultsList != null) 
			{
				for (Iterator itr = testResultsList.iterator(); itr.hasNext();) 
				{
					SpiderUMRuleMappingVO spiderUMRuleMappingVO = (SpiderUMRuleMappingVO) itr.next();
					editResultsList.add(spiderUMRuleMappingVO);
				}
			}
		  mappedRules.setUmStatus(editResultsList.get(0).getStatus());
		  mappedRules.setComment(editResultsList.get(0).getComments());
	      modelAndView = new ModelAndView("editSpider");
	      modelAndView.addObject("mappedRules", mappedRules);
	      modelAndView.addObject("editResultsList", editResultsList);
	      modelAndView.addObject("mappedRules", mappedRules);
	      
	      return modelAndView;
	}
	
	public ModelAndView locateRequestForEb03(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("locatespiderresultsForEb03");

		try {

			String section = "section";

			String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
			String umRuleId = request.getParameter("umRuleId").trim();
			String eb03 = request.getParameter("eb03").trim();

			Date date = new Date();

			SpiderUMRuleMapping umRule = new SpiderUMRuleMapping(umRuleId, eb03, null, "Testing", "Building", date);
			
			List<Object> testResultsList = umRuleMappingFacade.findAllUmRuleMappingsByEb03(eb03);

			PageSpider page = new PageSpider();
			page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
			// page.setTotalNoOfRecords(locateFacade.findTotalNoOfRecordsForLocate(variable));
			//page.setTotalNoOfRecords(1);
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

			List<SpiderUMRuleMappingVO> locateResultsList = new ArrayList<SpiderUMRuleMappingVO>();

			int startIndex = testResultsList.size() < page.getNoOfRecordsPerPage() ? 0: page.getStartRowNum() - 1;
			int endIndex = testResultsList.size() < page.getNoOfRecordsPerPage() ? testResultsList.size()
					: page.getTotalNoOfPages() == page.getCurrentPage() ? testResultsList.size() : page.getEndRowNum();
			testResultsList = testResultsList.subList(startIndex, endIndex);			

			if (testResultsList != null) {
				for (Iterator itr = testResultsList.iterator(); itr.hasNext();) {
					SpiderUMRuleMappingVO spiderUMRuleMapping = (SpiderUMRuleMappingVO) itr.next();

					locateResultsList.add(spiderUMRuleMapping);
				}
			}
			
			SpiderUMRuleMappingVO ruleIdWithInfoList;
			ruleIdWithInfoList = locateResultsList.get(0);

			modelAndView.addObject("locateResultsList", locateResultsList);
			modelAndView.addObject("ruleIdWithInfoList", ruleIdWithInfoList);
			modelAndView.addObject("page", page);
			modelAndView.addObject("userId", userId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;

	}

	
    public ModelAndView generateUnmappedExcel(HttpServletRequest request, HttpServletResponse response){
		
		List unmappedRules = umRuleMappingFacade.findAllUnmappedRules();//List<Mapping>
		unmappedRules = BxUtil.concatBreakSpider(unmappedRules);		
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();	
		BxUtil.encodeSpiderMappingsList(unmappedRules);
		ExcelUnmappedRuleView unmappedRuleExcel = new ExcelUnmappedRuleView(unmappedRules);
		response.setHeader("Content-Disposition", "attachment;filename=generateUnmappedExcel.xls");
		ModelAndView modelAndView = new ModelAndView(unmappedRuleExcel);
		return modelAndView;
	}

    public ModelAndView viewHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView("viewHistorySpider");
	
		try {
	
			String section = "section";
	
			String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
			String umRuleId = request.getParameter("umRuleId").trim();
			String umRuleDesc = request.getParameter("umRuleDesc").trim();
			
	
			List<SpiderUMRuleMappingVO> locateResultsList = new ArrayList<SpiderUMRuleMappingVO>();
	
			List<Object> testResultsList = umRuleMappingFacade.findAuditDetailsByRuleId(umRuleId);
	
			if (testResultsList != null) {
				List ruleIdList = new ArrayList();
				for (Iterator itr = testResultsList.iterator(); itr.hasNext();) {
					SpiderUMRuleMappingVO spiderUMRuleMapping = (SpiderUMRuleMappingVO) itr.next();
	
					locateResultsList.add(spiderUMRuleMapping);
				}
			}
			
			SpiderUMRuleMappingVO ruleIdWithInfoList;
			ruleIdWithInfoList = locateResultsList.get(0);
			ruleIdWithInfoList.setUmRuleDescription(umRuleDesc);
			ruleIdWithInfoList.setUmRuleId(umRuleId);
			//System.out.println("Locate List Results:::::::" + locateResultsList.size());
			modelAndView.addObject("locateResultsList", locateResultsList);
			modelAndView.addObject("ruleIdWithInfoList", ruleIdWithInfoList);
			//modelAndView.addObject("page", page);
			modelAndView.addObject("userId", userId);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return modelAndView;

    }

}
