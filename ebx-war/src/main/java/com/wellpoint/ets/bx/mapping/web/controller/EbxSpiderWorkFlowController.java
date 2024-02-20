package com.wellpoint.ets.bx.mapping.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.UMRuleMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditUmRuleMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebUtil;


public class EbxSpiderWorkFlowController extends MultiActionController {

    private UMRuleMappingFacade umRuleMappingFacade;
	
	public UMRuleMappingFacade getUmRuleMappingFacade() {
		return umRuleMappingFacade;
	}

	public void setUmRuleMappingFacade(UMRuleMappingFacade umRuleMappingFacade) {
		this.umRuleMappingFacade = umRuleMappingFacade;
	}
	
	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}

	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}

	public Integer getAuditInfoLimit() {
		return auditInfoLimit;
	}

	public void setAuditInfoLimit(Integer auditInfoLimit) {
		this.auditInfoLimit = auditInfoLimit;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		EbxSpiderWorkFlowController.logger = logger;
	}

	private SessionMessageTray sessionMessageTray;
	
	private Integer auditInfoLimit;
	
	private static Logger logger = Logger.getLogger(EbxSpiderWorkFlowController.class);

	
	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SpiderUMRuleMapping spiderUMRuleMapping = new SpiderUMRuleMapping();
		User user = new User();
		CreateOrEditUmRuleMappingResult result = null;

		String ruleId = request.getParameter("umRuleId");
		//String comments = result.getParameter("comments");
		String ruleDesc = request.getParameter("umRuleDesc");
		spiderUMRuleMapping.setUmRuleId(ruleId);
		spiderUMRuleMapping.setUmRuleDesc(ruleDesc);
        SpiderUMRuleMapping spiderMappingDB = umRuleMappingFacade.findByRuleId(ruleId);
        
		spiderUMRuleMapping.setComment(DomainConstants.EBX_SPIDER_LOCATE);
		spiderUMRuleMapping.setDefaultVersion(spiderMappingDB.getDefaultVersion());
		spiderUMRuleMapping.setSystemStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
		spiderUMRuleMapping.setUmRuleIdSystemId(spiderMappingDB.getUmRuleIdSystemId());
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		spiderUMRuleMapping.setUser(user);

		result = umRuleMappingFacade.sendToTest(spiderUMRuleMapping);

		return WebUtil.redirectToEbxSpiderLandingPage(request);	
	}

	

	public ModelAndView saveAndApprove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = new User();
		SpiderUMRuleMapping spiderUMRuleMapping = new SpiderUMRuleMapping();
		String ruleId = request.getParameter("umRuleId");
		String ruleDesc = request.getParameter("umRuleDesc");
		spiderUMRuleMapping.setUmRuleId(ruleId);
		spiderUMRuleMapping.setUmRuleDesc(ruleDesc);
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		SpiderUMRuleMapping spiderMappingDB = umRuleMappingFacade.findByRuleId(ruleId);
        
		spiderUMRuleMapping.setComment(DomainConstants.EBX_SPIDER_LOCATE);
		spiderUMRuleMapping.setDefaultVersion(spiderMappingDB.getDefaultVersion());
		spiderUMRuleMapping.setSystemStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
		spiderUMRuleMapping.setUmRuleIdSystemId(spiderMappingDB.getUmRuleIdSystemId());
		spiderUMRuleMapping.setUser(user);
		CreateOrEditUmRuleMappingResult result = umRuleMappingFacade.saveAndApprove(spiderUMRuleMapping);
		
		return WebUtil.redirectToEbxSpiderLandingPage(request);	
	}

	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		return WebUtil.redirectToEbxSpiderLandingPage(request);
	}
	
	public ModelAndView back(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		return WebUtil.redirectToEbxSpiderLandingPage(request);
	}
	
	public ModelAndView markAsApplicable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		User user = new User();
		SpiderUMRuleMapping spiderUMRuleMapping = new SpiderUMRuleMapping();
		String ruleId = request.getParameter("umRuleId");

		spiderUMRuleMapping.setUmRuleId(ruleId);
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		SpiderUMRuleMapping spiderMappingDB = umRuleMappingFacade.findByRuleId(ruleId);
        
		spiderUMRuleMapping.setComment(DomainConstants.EBX_SPIDER_LOCATE);
		spiderUMRuleMapping.setDefaultVersion(spiderMappingDB.getDefaultVersion());
		spiderUMRuleMapping.setSystemStatus(DomainConstants.APPLICABLE_STATUS);
		spiderUMRuleMapping.setUmRuleIdSystemId(spiderMappingDB.getUmRuleIdSystemId());
		spiderUMRuleMapping.setUser(user);
		CreateOrEditUmRuleMappingResult result = umRuleMappingFacade.markAsApplicable(spiderUMRuleMapping);
		
		return WebUtil.redirectToEbxSpiderLandingPage(request);	
	}
	

}
