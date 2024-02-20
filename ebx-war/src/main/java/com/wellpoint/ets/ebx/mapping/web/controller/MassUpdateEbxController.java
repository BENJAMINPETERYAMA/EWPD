package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.LoginUser;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.application.security.UserUIPermissions;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.ebx.mapping.application.MappingFacade;
import com.wellpoint.ets.ebx.mapping.web.view.MassUpdateReportView;

/**
 * @author UST-GLOBAL
 * This is Controller class for mass update.
 * 
 */
public class MassUpdateEbxController extends MultiActionController{
	private static Logger logger = Logger.getLogger(MassUpdateEbxController.class);
	private MassUpdateTracker massUpdateTracker;
	private MappingFacade mappingSpsIdFacade;	
	private MappingFacade mappingCustomMessageFacade;
	private MappingFacade mappingRuleIdFacade; 
	private VariableMappingFacade variableMappingFacade;
	
	
	public VariableMappingFacade getVariableMappingFacade() {
		return variableMappingFacade;
	}

	public void setVariableMappingFacade(VariableMappingFacade variableMappingFacade) {
		this.variableMappingFacade = variableMappingFacade;
	}

	public MassUpdateTracker getMassUpdateTracker() {
		return massUpdateTracker;
	}

	public void setMassUpdateTracker(MassUpdateTracker massUpdateTracker) {
		this.massUpdateTracker = massUpdateTracker;
	}
	
	public MappingFacade getMappingSpsIdFacade() {
		return mappingSpsIdFacade;
	}

	public void setMappingSpsIdFacade(MappingFacade mappingSpsIdFacade) {
		this.mappingSpsIdFacade = mappingSpsIdFacade;
	}

	public MappingFacade getMappingCustomMessageFacade() {
		return mappingCustomMessageFacade;
	}

	public void setMappingCustomMessageFacade(
			MappingFacade mappingCustomMessageFacade) {
		this.mappingCustomMessageFacade = mappingCustomMessageFacade;
	}
	
	public MappingFacade getMappingRuleIdFacade() {
		return mappingRuleIdFacade;
	}

	public void setMappingRuleIdFacade(MappingFacade mappingRuleIdFacade) {
		this.mappingRuleIdFacade = mappingRuleIdFacade;
	}
	
	/**
	 * splits the string of comma seperated.
	 * @param s
	 * @return
	 */
	private String[] getStringArray(String s){
		if(!BxUtil.hasText(s)){
			return new String[0];
		}
		List list = new ArrayList();
		String[] array = s.trim().split(",");
		for(int i=0; i<array.length; i++){
			if(BxUtil.hasText(array[i])){
				list.add(array[i].trim());
			}
		}
		return (String[])list.toArray(new String[list.size()]);
	}
	
	/**
	 * gets trim value or empty if null or empty
	 * @param s
	 * @return
	 */
	private String getString(String s){
		if(BxUtil.hasText(s)){
			return s.trim();
		}
		return "";
	}
	/**
	 * fills mass update criteria from request.
	 * @param request
	 * @param response
	 * @return
	 */
	private MassUpdateCriteria  getMassUpdateCriteria(HttpServletRequest request,
			HttpServletResponse response){
		MassUpdateCriteria muc = new MassUpdateCriteria();
		if (BxUtil.hasText(request.getParameter("isUdtEB01"))) {
			muc.setCfEb01(getStringArray(request.getParameter("ueb01Val")));
			String nveb01Val=request.getParameter("nveb01Val");
			BxUtil.validationVariable(nveb01Val);
			muc.setNvEb01(getString(nveb01Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtEB02"))) {
			muc.setCfEb02(getStringArray(request.getParameter("ueb02Val")));
			String nveb02Val=request.getParameter("nveb02Val");
			BxUtil.validationVariable(nveb02Val);
			muc.setNvEb02(getString(nveb02Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtEB03"))) {
			muc.setCfEb03(getStringArray(request.getParameter("ueb03Val")));
			muc.setNvEb03(getStringArray(request.getParameter("nveb03Val")));
		}
		if (BxUtil.hasText(request.getParameter("isUdtEB06"))) {
			muc.setCfEb06(getStringArray(request.getParameter("ueb06Val")));
			String nveb06Val=request.getParameter("nveb06Val");
			BxUtil.validationVariable(nveb06Val);
			muc.setNvEb06(getString(nveb06Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtEB09"))) {
			muc.setCfEb09(getStringArray(request.getParameter("ueb09Val")));
			String nveb09Val=request.getParameter("nveb09Val");
			BxUtil.validationVariable(nveb09Val);
			muc.setNvEb09(getString(nveb09Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD01"))) {
			muc.setCfHsd01(getStringArray(request.getParameter("uhsd01Val")));
			String nvhsd01Val=request.getParameter("nvhsd01Val");
			BxUtil.validationVariable(nvhsd01Val);
			muc.setNvHsd01(getString(nvhsd01Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD02"))) {
			muc.setCfHsd02(getStringArray(request.getParameter("uhsd02Val")));
			String nvhsd02Val=request.getParameter("nvhsd02Val");
			BxUtil.validationVariable(nvhsd02Val);
			muc.setNvHsd02(getString(nvhsd02Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD03"))) {
			muc.setCfHsd03(getStringArray(request.getParameter("uhsd03Val")));
			String nvhsd03Val=request.getParameter("nvhsd03Val");
			BxUtil.validationVariable(nvhsd03Val);
			muc.setNvHsd03(getString(nvhsd03Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD04"))) {
			muc.setCfHsd04(getStringArray(request.getParameter("uhsd04Val")));
			String nvhsd04Val=request.getParameter("nvhsd04Val");
			BxUtil.validationVariable(nvhsd04Val);
			muc.setNvHsd04(getString(nvhsd04Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD05"))) {
			muc.setCfHsd05(getStringArray(request.getParameter("uhsd05Val")));
			String nvhsd05Val=request.getParameter("nvhsd05Val");
			BxUtil.validationVariable(nvhsd05Val);
			muc.setNvHsd05(getString(nvhsd05Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD06"))) {
			muc.setCfHsd06(getStringArray(request.getParameter("uhsd06Val")));
			String nvhsd06Val=request.getParameter("nvhsd06Val");
			BxUtil.validationVariable(nvhsd06Val);
			muc.setNvHsd06(getString(nvhsd06Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD07"))) {
			muc.setCfHsd07(getStringArray(request.getParameter("uhsd07Val")));
			String nvhsd07Val=request.getParameter("nvhsd07Val");
			BxUtil.validationVariable(nvhsd07Val);
			muc.setNvHsd07(getString(nvhsd07Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtHSD08"))) {
			muc.setCfHsd08(getStringArray(request.getParameter("uhsd08Val")));
			String nvhsd08Val=request.getParameter("nvhsd08Val");
			BxUtil.validationVariable(nvhsd08Val);
			muc.setNvHsd08(getString(nvhsd08Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtiii02"))) {
			muc.setCfIII02(getStringArray(request.getParameter("uiii02Val")));
			String nviii02Val=request.getParameter("nviii02Val");
			BxUtil.validationVariable(nviii02Val);
			muc.setNvIII02(getString(nviii02Val));
		}
		if (BxUtil.hasText(request.getParameter("isUdtnoteType"))) {
			muc.setCfNoteType(getStringArray(request.getParameter("unoteTypeVal")));
			String nvnoteTypeVal=request.getParameter("nvnoteTypeVal");
			BxUtil.validationVariable(nvnoteTypeVal);
			muc.setNvNoteType(getString(nvnoteTypeVal));		
		}
		if (BxUtil.hasText(request.getParameter("isUdtnoteTypeCode"))) {
			muc.setCfNoteType(getStringArray(request.getParameter("unoteTypeCodeVal")));
			String nvnoteTypeCodeVal=request.getParameter("nvnoteTypeCodeVal");
			BxUtil.validationVariable(nvnoteTypeCodeVal);
			muc.setNvNoteType(getString(nvnoteTypeCodeVal));		
		}
		if (BxUtil.hasText(request.getParameter("isUdtMsgTxt"))) {
			muc.setCfMessage(getStringArray(request.getParameter("uMsgTxtVal")));
			String nvMsgTxtVal=request.getParameter("nvMsgTxtVal");
			BxUtil.validationVariable(nvMsgTxtVal);
			muc.setNvMessage(getString(nvMsgTxtVal));		
		}
		muc.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
		return muc;
	}
	/**
	 * gets mass update comment
	 * @param request
	 * @return
	 */
	private String getMassUpdateComment(HttpServletRequest request){
		String updateComments = ESAPI.encoder().encodeForHTML(request.getParameter("hdMassUpdateComment"));
		if(BxUtil.hasText(updateComments)){
			return updateComments.trim();
		}else{
			return "";
		}
	}
	
	/**
	 * sets the keys
	 * @param muc
	 * @param keyMuc
	 */
	private void mergeMassUpdateCriteriaKey(MassUpdateCriteria muc, MassUpdateCriteria keyMuc){
		muc.setSpsId(keyMuc.getSpsId());
		muc.setRuleId(keyMuc.getRuleId());
		muc.setVariableId(keyMuc.getVariableId());
	}
	
	/**
	 * update controller function
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MassUpdateCriteria nfMuc = getMassUpdateCriteria(request, response);
		String comments = getMassUpdateComment(request);
		List massUpdateList = new ArrayList();
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria criteria = (MassUpdateCriteria)SerializationUtils.clone(nfMuc);
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				mergeMassUpdateCriteriaKey(criteria, keyMuc);
				massUpdateList.add(criteria);
			}
		}
		List resultList = new ArrayList();
		long startTime = new Date().getTime();
		if(MassUpdateTracker.SPS_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingSpsIdFacade.update(massUpdateList, comments ,massUpdateTracker);
		}else if(MassUpdateTracker.RULE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingRuleIdFacade.update(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.MSG_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingCustomMessageFacade.update(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.VARIABLE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			boolean authorizedEditLockVar = this.isAuthorizeToEditAuditLockedVar(request); 
			resultList = variableMappingFacade.save(massUpdateList, comments, massUpdateTracker, authorizedEditLockVar);
		}
		
		long executedTime = new Date().getTime() - startTime;

		Date executedTimedelay = new Date(executedTime);
		logger.debug("executedTimedelay is "+executedTimedelay);
		return getMappingResultXL(massUpdateList, resultList);
	}
	
	/**
	 * Returns result xl
	 * @param massUpdateList
	 * @param resultList
	 * @return
	 */
	private ModelAndView getMappingResultXL(List massUpdateList, List resultList){
		massUpdateTracker.setLastMassUpdate(massUpdateList);
		massUpdateTracker.setLastMassupdateResult(resultList);
		return new ModelAndView(new MassUpdateReportView(massUpdateTracker));
	}
	
	/**
	 * controller function not applicable
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView markNotApplicable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String comments = getMassUpdateComment(request);
		List massUpdateList = new ArrayList();
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria criteria = new MassUpdateCriteria();
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				mergeMassUpdateCriteriaKey(criteria, keyMuc);
				criteria.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
				massUpdateList.add(criteria);
			}
		}
		List resultList = new ArrayList();
		long startTime = new Date().getTime();
		if(MassUpdateTracker.SPS_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingSpsIdFacade.notApplicable(massUpdateList, comments,massUpdateTracker);
		}else if(MassUpdateTracker.RULE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingRuleIdFacade.notApplicable(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.MSG_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingCustomMessageFacade.notApplicable(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.VARIABLE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			boolean authorizedEditLockVar = this.isAuthorizeToEditAuditLockedVar(request);
			resultList = variableMappingFacade.notApplicable(massUpdateList, comments, massUpdateTracker, authorizedEditLockVar);
		}
		
		long executedTime = new Date().getTime() - startTime;

		Date executedTimedelay = new Date(executedTime);
		logger.debug("executedTimedelay is "+executedTimedelay);
		return getMappingResultXL(massUpdateList, resultList);
	}
	
	/**
	 * controller function approve
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView approve(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String comments = getMassUpdateComment(request);
		List massUpdateList = new ArrayList();
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria criteria = new MassUpdateCriteria();
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				mergeMassUpdateCriteriaKey(criteria, keyMuc);
				criteria.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
				massUpdateList.add(criteria);
			}
		}
		List resultList = new ArrayList();
		long startTime = new Date().getTime();
		if(MassUpdateTracker.SPS_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingSpsIdFacade.approve(massUpdateList, comments,massUpdateTracker);
		}else if(MassUpdateTracker.RULE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingRuleIdFacade.approve(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.MSG_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingCustomMessageFacade.approve(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.VARIABLE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			boolean authorizedEditLockVar = this.isAuthorizeToEditAuditLockedVar(request); 
			resultList = variableMappingFacade.approve(massUpdateList, comments, massUpdateTracker, authorizedEditLockVar);
		}
		
		long executedTime = new Date().getTime() - startTime;

		Date executedTimedelay = new Date(executedTime);
		logger.debug("executedTimedelay is "+executedTimedelay);
		return getMappingResultXL(massUpdateList, resultList);
	}
	
	/**
	 * controller functionf sent to test
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String comments = getMassUpdateComment(request);
		List massUpdateList = new ArrayList();
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria criteria = new MassUpdateCriteria();
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				mergeMassUpdateCriteriaKey(criteria, keyMuc);
				criteria.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
				massUpdateList.add(criteria);
			}
		}
		List resultList = new ArrayList();
		long startTime = new Date().getTime();
		if(MassUpdateTracker.SPS_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingSpsIdFacade.sendToTest(massUpdateList, comments,massUpdateTracker);
		}else if(MassUpdateTracker.RULE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingRuleIdFacade.sendToTest(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.MSG_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			resultList = mappingCustomMessageFacade.sendToTest(massUpdateList, comments, massUpdateTracker);
		}else if(MassUpdateTracker.VARIABLE_TYPE.equals(massUpdateTracker.getLastSearchedType())){
			boolean authorizedEditLockVar = this.isAuthorizeToEditAuditLockedVar(request);
			resultList = variableMappingFacade.sendToTest(massUpdateList, comments, massUpdateTracker, authorizedEditLockVar);
		}
		
		long executedTime = new Date().getTime() - startTime;

		Date executedTimedelay = new Date(executedTime);
		logger.debug("executedTimedelay is "+executedTimedelay);
		return getMappingResultXL(massUpdateList, resultList);
	}
	
	public ModelAndView massUpdateResult(HttpServletRequest arg0,
	HttpServletResponse arg1) throws Exception {
		return new ModelAndView(new MassUpdateReportView(null));
	}
	
	/**
	 * controller function to get progress percentage to show progress information
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void progressPercentage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("completed percentage "+massUpdateTracker.getCompletedPercentage()+ " "+massUpdateTracker.getTotalCount()+ " "+ massUpdateTracker.getCompletedCount());
		response.setHeader("Strict-Transport-Security","max-age=31536000; includeSubDomains; preload");
		response.getWriter().write(""+(massUpdateTracker.getCompletedPercentage()));
		
	}

	/**
	 * on check box clicks this stores or removes the corresponding ids
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void storeSelected(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String spsid = request.getParameter("spsid").toString().trim();
		String ruleid = request.getParameter("ruleid").toString().trim();
		String variableid = ESAPI.encoder().encodeForHTML(request.getParameter("variableid").toString()).trim();
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo").trim());
		spsid = BxUtil.hasText(spsid) ? spsid.trim(): null;
		ruleid = BxUtil.hasText(ruleid) ? ruleid.trim(): null;
		variableid = BxUtil.hasText(variableid) ? variableid.trim(): null;
		massUpdateTracker.addSelectedRecord(pageNo, spsid, ruleid, variableid,  request.getParameter("checked").trim().equals("true"));
		response.setHeader("Strict-Transport-Security","max-age=31536000; includeSubDomains; preload");
		response.getWriter().write(massUpdateTracker.getSelectedPagesString());
				
	}
	
	public ModelAndView checkLockStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean locked  = false;
		boolean checktemptable = false;
		boolean authorizedEditLockVar = this.isAuthorizeToEditAuditLockedVar(request);
		boolean ignoreMessage = false;
		String trigger = request.getParameter("trigger").toString().trim();
		if(null != trigger && trigger.length() > 0){
			if(trigger.equalsIgnoreCase("unlock")){
				checktemptable = true;
				locked = false;
				authorizedEditLockVar = false;
			} else if(trigger.equalsIgnoreCase("lock")){
				locked = true;
				checktemptable = false;
				authorizedEditLockVar = false;
			} else if(trigger.equalsIgnoreCase("notApplicable")){
				locked = true;
				checktemptable = true;
			} else if(trigger.equalsIgnoreCase("sendToTest")){
				locked = true;
				checktemptable = true;
				authorizedEditLockVar = true;
				ignoreMessage = true;
			}else if(trigger.equalsIgnoreCase("approve")){
				locked = true;
				checktemptable = true;
				authorizedEditLockVar = true;
				ignoreMessage = true;
			}else if(trigger.equalsIgnoreCase("update")){
				locked = true;
				checktemptable = true;
			}
			
		}
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		List variableList = new ArrayList();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				variableList.add(keyMuc.getVariableId());
			}
		}
		List variableListofGivenStatus = variableMappingFacade.getAuditLockStatusOfVariable(variableList, locked, checktemptable);

		Map map = new HashMap();
		if(locked) {
			map.put("confirmmessage", "Below variables are already locked");
		} else {
			map.put("confirmmessage", "Below variables are not locked");
		} 
		if(!ignoreMessage) {
			if(authorizedEditLockVar){
				map.put("messagefoter", "These variables will be updated");
			} else {
				map.put("messagefoter", "These variables will be Ignored");
			}
		} else {
			map.put("messagefoter", "");
		}
		map.put("variableList", variableListofGivenStatus);
		map.put("trigger", trigger);
		map.put("listsize", Integer.valueOf(variableListofGivenStatus.size()));
		if(variableListofGivenStatus.size() > 0){
			map.put("varexist", "Exist multiple");
		} else {
			map.put("varexist", "No Variable found");
		}
		return new ModelAndView("variablelockstatuspopup",map);
		
		
	}
	
	/**
	 * on check box clicks this stores or removes the corresponding all ids for the page
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void storeAllSelected(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo").trim());
		massUpdateTracker.addAllSelectedRecord(pageNo, request.getParameter("checked").trim().equals("true"));
		response.setHeader("Strict-Transport-Security","max-age=31536000; includeSubDomains; preload");
		response.getWriter().write(massUpdateTracker.getSelectedPagesString());
				
	}
	
	/**
	 * controller function to lock all the selected variables
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView lock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String comments = getMassUpdateComment(request);
		List massUpdateList = new ArrayList();
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria criteria = new MassUpdateCriteria();
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				mergeMassUpdateCriteriaKey(criteria, keyMuc);
				criteria.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
				massUpdateList.add(criteria);
			}
		}
		List resultList = new ArrayList();
		resultList = variableMappingFacade.lock(massUpdateList, comments, massUpdateTracker);
		
		
		return getMappingResultXL(massUpdateList, resultList);
	}
	
	/**
	 * controller function to mass unlock the variables.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView unlock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String comments = getMassUpdateComment(request);
		List massUpdateList = new ArrayList();
		Iterator pages = massUpdateTracker.getSelectedPages().iterator();
		while(pages.hasNext()){
			Integer page = (Integer) pages.next();
			List records = massUpdateTracker.getSelectedRecords(page);
			Iterator itr = records.iterator();
			while(itr.hasNext()){
				MassUpdateCriteria criteria = new MassUpdateCriteria();
				MassUpdateCriteria keyMuc = (MassUpdateCriteria)itr.next();
				mergeMassUpdateCriteriaKey(criteria, keyMuc);
				criteria.setUserId(request.getAttribute(SecurityConstants.USER_NAME).toString());
				massUpdateList.add(criteria);
			}
		}
		List resultList = new ArrayList();
		resultList = variableMappingFacade.unlock(massUpdateList, comments, massUpdateTracker);
		
		
		return getMappingResultXL(massUpdateList, resultList);
	}
	/**
	 * method to check whther user is authorize to edit
	 * audit locked variable
	 * @param request
	 * @return
	 */
	private boolean isAuthorizeToEditAuditLockedVar(HttpServletRequest request){
		Object sessionObj = request.getSession().getAttribute(
				SecurityConstants.USER);
		LoginUser loginUser = (LoginUser) sessionObj;
		UserUIPermissions userUIPermissions = new UserUIPermissions(
				"advancesearch", loginUser);
		return userUIPermissions.isAuthorizedEditLockVar();
	
	}

}