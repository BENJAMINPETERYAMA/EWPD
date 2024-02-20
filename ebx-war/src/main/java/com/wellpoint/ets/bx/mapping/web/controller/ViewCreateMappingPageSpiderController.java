package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingDataAudit;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditUmRuleMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.referencedata.dto.SpiderUMRuleMappingDTO;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class ViewCreateMappingPageSpiderController extends MultiActionController {

	private UMRuleMappingFacade umRuleMappingFacade;

	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}

	private ModelAndView handleRequest(String ruleId, String loggedInUser, HttpServletRequest request)
			throws Exception {

		SpiderUMRuleMapping sp = new SpiderUMRuleMapping();

		HttpSession session = request.getSession();
		List errorMessagesList = new ArrayList();
		List ruleIdWithInfoList = null;
		List ruleIdList = new ArrayList();
		ruleIdList.add(ruleId);
		ModelAndView modelAndView = new ModelAndView();
		int statusCode = umRuleMappingFacade.isValidRuleIDStatus(ruleId);
		if (statusCode == 1) {
			ruleIdWithInfoList = umRuleMappingFacade.viewRuleIdDetails(ruleId);
			SpiderUMRuleMapping newRule;
			newRule = (SpiderUMRuleMapping) ruleIdWithInfoList.get(0);
			modelAndView = new ModelAndView("createspidermapping");
			modelAndView.addObject("ruleIdWithInfoList", ruleIdWithInfoList);
		}
		modelAndView = redirectToSpiderLandingPage(modelAndView, loggedInUser);
		return modelAndView;
	}

	public ModelAndView viewFromCreateSpider(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		return handleRequest(request.getParameter("selectedRuleId").trim(), loggedInUser, request);
	}

	public ModelAndView create(HttpServletRequest request, HttpServletResponse response,
			@RequestBody SpiderUMRuleMappingDTO umRuleCreateData) throws Exception {
		String mappingListdata = request.getParameter("mappingValues");
		String eb03Value = request.getParameter("eb03Value");
		
		ObjectMapper mapper = new ObjectMapper();
		int count = 0;
		List<SpiderUMRuleMappingVO> umRuleSpdrMappingList = new ArrayList<SpiderUMRuleMappingVO>();
		umRuleSpdrMappingList = mapper.readValue(mappingListdata, new TypeReference<List<SpiderUMRuleMappingVO>>() {
		});

		List<SpiderUMRuleMappingData> spiderList = new ArrayList<SpiderUMRuleMappingData>();
		User user = new User();

		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setCreatedUserName(userId);
		user.setLastUpdatedUserName(userId);
		ModelAndView modelAndView = new ModelAndView("createspidermapping");
		SpiderUMRuleMapping spiderMaster = new SpiderUMRuleMapping();
		spiderMaster.setUser(user);
		spiderMaster.setUmRuleId(umRuleCreateData.getUmRuleId());
		spiderMaster.setUmRuleDesc(umRuleCreateData.getUmRuleDesc());
		spiderMaster.setComment(umRuleCreateData.getComment());
		spiderMaster.setDefaultVersion(1);
		spiderMaster.setSystemStatus(DomainConstants.EBX_SPIDER_CREATE); //for log
		
		spiderMaster.setUmStatus("BUILDING");

		if (!umRuleSpdrMappingList.isEmpty()) {
			umRuleSpdrMappingList.forEach(eb03Mapping -> {

				SpiderUMRuleMappingData spiderUmRuleAssociation = new SpiderUMRuleMappingData();
				spiderUmRuleAssociation.setUser(user);
				spiderUmRuleAssociation.setUmEB03(eb03Mapping.getEb03Value());
				spiderUmRuleAssociation.setUmEB03Default(eb03Mapping.getEb03DefaultValue());
				spiderUmRuleAssociation.setUmMessage(eb03Mapping.getMsgValue());
				spiderUmRuleAssociation.setVersion(1);

				spiderList.add(spiderUmRuleAssociation);
			});
		}
        if(!eb03Value.equalsIgnoreCase(""))
        {
			CreateOrEditUmRuleMappingResult resultInMasterTable = umRuleMappingFacade.createOrEditRuleIdMapping(spiderList,
					spiderMaster,WebConstants.PERSIST_TYPE_CREATE);// List<Mapping>
			
			modelAndView.addObject("spiderUmRuleMapping", resultInMasterTable.getSpiderUMRuleMapping());
        }
			
	    return WebUtil.redirectToEbxSpiderLandingPage(request);

	}

	public ModelAndView edit( HttpServletRequest request,
			HttpServletResponse response, @RequestBody SpiderUMRuleMappingDTO umRuleCreateData) throws Exception {
		
		//@RequestParam(name = "mappingvalue") String mappingList,@RequestParam(name = "comment") String comment

		String mappingListdata = request.getParameter("mappingValues");
		String status = request.getParameter("status");

		ObjectMapper mapper = new ObjectMapper();
		int count = 0;
		List<SpiderUMRuleMappingVO> umRuleSpdrMappingList = new ArrayList<SpiderUMRuleMappingVO>();
		umRuleSpdrMappingList = mapper.readValue(mappingListdata, new TypeReference<List<SpiderUMRuleMappingVO>>() {
		});

		
		System.out.println(umRuleSpdrMappingList);
		
		List<SpiderUMRuleMappingData> spiderList = new ArrayList<SpiderUMRuleMappingData>();

		List<SpiderUMRuleMappingDataAudit> spiderAuditList = new ArrayList<SpiderUMRuleMappingDataAudit>();

		User user = new User();

		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setCreatedUserName(userId);
		user.setLastUpdatedUserName(userId);
		ModelAndView modelAndView = new ModelAndView("editspider");

		SpiderUMRuleMapping spiderMappingDB = umRuleMappingFacade.findByRuleId(umRuleCreateData.getUmRuleId());

		spiderMappingDB.setUser(user);
		//spiderMappingDB.setComment(spiderMappingDB.getComment() + "," + umRuleCreateData.getComment());
		spiderMappingDB.setComment(umRuleCreateData.getComment());
		spiderMappingDB.setDefaultVersion(spiderMappingDB.getDefaultVersion().intValue() + 1);
		if(status.equalsIgnoreCase("BUILDING"))
		{	
		  spiderMappingDB.setSystemStatus(DomainConstants.EBX_SPIDER_EDIT);
		}
		else if(status.equalsIgnoreCase("SCHEDULED_TO_TEST"))
		{	
			  spiderMappingDB.setSystemStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
		}
		else if(status.equalsIgnoreCase("SCHEDULED_TO_PRODUCTION"))
		{	
			  spiderMappingDB.setSystemStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
		}
		else if(status.equalsIgnoreCase("NOT_APPLICABLE"))
		{	
			  spiderMappingDB.setSystemStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		}
		
		if(spiderMappingDB.getUmStatus().equalsIgnoreCase(DomainConstants.STATUS_PUBLISHED) && !status.equalsIgnoreCase("NOT_APPLICABLE"))
		{
			spiderMappingDB.setUmStatus(DomainConstants.STATUS_BEING_MODIFIED);
		}
		else
		{
			spiderMappingDB.setUmStatus(status);
		}

		 List<SpiderUMRuleMappingData>  spiderMappingDataList =    umRuleMappingFacade.findAllUmRuleMappingsDataByRuleId(spiderMappingDB.getUmRuleId());
		 
				//findAllMappingByRuleId(spiderMappingDB.getUmRuleId());  

		 Map<String,SpiderUMRuleMappingData> spiderMappingDataMap = new HashMap<String,SpiderUMRuleMappingData>();
		 
		 spiderMappingDataList.stream().forEach(umMappingData->{
			 SpiderUMRuleMappingDataAudit umMappingAuditData = new SpiderUMRuleMappingDataAudit();
			 BeanUtils.copyProperties(umMappingData, umMappingAuditData);
			 umMappingAuditData.setUmRuleSystemId(spiderMappingDB.getUmRuleIdSystemId());
			 spiderAuditList.add(umMappingAuditData);			 
			 spiderMappingDataMap.put(umMappingData.getUmEB03(), umMappingData);
		 });
		 
		umRuleMappingFacade.createRuleIdAuditMapping(spiderAuditList,umRuleCreateData.getUmRuleId());

		if (!umRuleSpdrMappingList.isEmpty())
		{
			umRuleSpdrMappingList.forEach(eb03Mapping -> {
			SpiderUMRuleMappingData spiderUmRuleAssociation;
			if(spiderMappingDataMap.get(eb03Mapping.getEb03Value()) != null ){
			spiderUmRuleAssociation = spiderMappingDataMap.get(eb03Mapping.getEb03Value());
			}else{
			spiderUmRuleAssociation = new SpiderUMRuleMappingData();
			}
			spiderUmRuleAssociation.setUser(user);
			spiderUmRuleAssociation.setUmEB03(eb03Mapping.getEb03Value());
			spiderUmRuleAssociation.setUmEB03Default(eb03Mapping.getEb03DefaultValue());
			spiderUmRuleAssociation.setUmMessage(eb03Mapping.getMsgValue());
			spiderUmRuleAssociation.setVersion( spiderMappingDB.getDefaultVersion().intValue() );
			spiderList.add(spiderUmRuleAssociation);
			});
		}
		
		CreateOrEditUmRuleMappingResult resultInMasterTable = umRuleMappingFacade.createOrEditRuleIdMapping(spiderList,
				spiderMappingDB,WebConstants.PERSIST_TYPE_EDIT);
		
		// // for saving records in association table
		modelAndView.addObject("spiderUmRuleMapping", resultInMasterTable.getSpiderUMRuleMapping());
		
		return WebUtil.redirectToEbxSpiderLandingPage(request);

	}

	/*public ModelAndView redirectToCreateMappingPage(ModelAndView modelAndView, CreateOrEditUmRuleMappingResult result) {
		modelAndView = new ModelAndView("createspidermapping");
		modelAndView.addObject("spiderUmRuleMapping", result.getSpiderUMRuleMapping());
		return modelAndView;
	}*/

	public ModelAndView viewFromUnMapped(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		return handleRequest(request.getParameter("ruleIdHidden").trim(), loggedInUser, request);
	}

	public ModelAndView redirectToSpiderLandingPage(ModelAndView modelAndView, String loggedInUser) {
		List unmappedrules = umRuleMappingFacade.findAllUnmappedRules();// List<Mapping>
		modelAndView.addObject("unmappedrules", unmappedrules);
		return modelAndView;
	}

	public ModelAndView viewUnmappedRule(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String umRuleId = request.getParameter("ruleId");
		SpiderUMRuleMapping spiderMap = new SpiderUMRuleMapping();
		spiderMap.setUmRuleId(umRuleId);
		List currentViewRule = umRuleMappingFacade.viewRuleDetails(spiderMap);

		SpiderUMRuleMapping newRule;
		newRule = (SpiderUMRuleMapping) currentViewRule.get(0);

		ModelAndView modelAndView = new ModelAndView("spidermappingview");

		modelAndView.addObject("currentViewRule", currentViewRule);
		return modelAndView;
	}

	public ModelAndView deleteSpiderMapping(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ruleId = request.getParameter("ruleId");
		String eb03Value = request.getParameter("eb03Value");
		int mappingCount = umRuleMappingFacade.findMappingPresentOrNot(ruleId, eb03Value);
		if (mappingCount != 0) {
			//if(mappingCount != 1)
			//{	
			  Boolean result = umRuleMappingFacade.deleteMapping(ruleId, eb03Value);
			//}
			if(mappingCount == 1)
			{
			  Boolean result1 = umRuleMappingFacade.deleteRuleMapping(ruleId);
			}
		}
		return null;
	}
	
	public ModelAndView createIgnoreUnmappedUMRule(HttpServletRequest request, HttpServletResponse response){
		String ruleId = request.getParameter("ruleId");
		String version = request.getParameter("ruleVersion");
		String status = request.getParameter("ruleStatus");
		Number ruleVersion =Integer.parseInt(version);
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setCreatedUserName(userId);
		user.setLastUpdatedUserName(userId);
		SpiderUMRuleMapping spiderMapping = new SpiderUMRuleMapping();
		spiderMapping.setUmRuleId(ruleId);
		spiderMapping.setDefaultVersion(ruleVersion);		
		spiderMapping.setUmStatus(status);
		spiderMapping.setUser(user);
		System.out.println(ruleVersion);
		umRuleMappingFacade.createIgnoreUnmappedUMRule(spiderMapping);
		
		return null;
	}
	
	public ModelAndView verifyUmRuleExistByRuleId(HttpServletRequest request, HttpServletResponse response){
		String ruleId = request.getParameter("ruleId");
		Map map = new HashMap();
		System.out.println(ruleId);
		String isRuleExist =  umRuleMappingFacade.verifyUmRuleExistByRuleId(ruleId);
		 map.put("isMapped", isRuleExist);
		
		
		return new ModelAndView("jsonView",map);
	}
}
