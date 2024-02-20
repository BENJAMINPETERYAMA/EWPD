/*
 * <EditMappingController.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.LoginUser;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.application.security.UserUIPermissions;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.ExtendedMessageMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;




/**
 * @author UST-GLOBAL This is a controller class for editing mapping for the variables
 */
public class EditMappingController extends MultiActionController {
	
	private VariableMappingFacade variableMappingFacade;
	private LocateFacade locateFacade;
	private int noOfRecords;
	private Integer auditInfoLimit;
	private static Logger log = Logger.getLogger(EditMappingController.class);
	private String ny;
	private SessionMessageTray sessionMessageTray;
	
	
	public ModelAndView redirectToCreateMappingPage(ModelAndView modelAndView, CreateOrEditMappingResult result){
		Mapping mapping = null;
		mapping = result.getMapping();
		List variableWithInfoList = result.getMapping().getVariableList();
		modelAndView.addObject("changeComments", result.getUserComments());
		if((null!=mapping.getMsg_type_required())&&(mapping.getMsg_type_required().equals("Y"))){
			
			mapping.setMsg_type_required("true");
		}
		else{
			mapping.setMsg_type_required("false");
		}
		if((null!=mapping.getSensitiveBenefitIndicator())&&mapping.getSensitiveBenefitIndicator().equals("Y")){
			
			mapping.setSensitiveBenefitIndicator("true");
		}
		else{
			mapping.setSensitiveBenefitIndicator("false");
		}
		/**
		 * MTM CODE CHANGES
		 */
		if(mapping.isFinalized()==false){

			mapping.setFinalized(true);
		}
		else{
			mapping.setFinalized(false);
		}

		/**
		 * END
		 */
		modelAndView.addObject("mapping",mapping);
		modelAndView.addObject("variableWithInfoList",variableWithInfoList);
		   
		   return modelAndView;
		  }
	public ModelAndView editMapping(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("inside editMapping");
		String pageName =null;
		pageName = request.getParameter("pageName");
		log.debug(" pageName :"+pageName);
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		// SSCR19537 April04
		List <EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		User user = new User();
		 HttpSession session = request.getSession();
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		mapping.setVariable(variable);
		String selectedVariable = request.getParameter("selectedvariableForEditId");
		mapping.getVariable().setVariableId(selectedVariable);
		Object sessionObj = request.getSession().getAttribute(SecurityConstants.USER);
        if (sessionObj != null && (sessionObj instanceof LoginUser)) {
            LoginUser loginUser = (LoginUser) sessionObj;
            String pageNameTemp = pageName;
            if (null != pageName && pageName.trim().equals("advanceSearch")){
            	pageNameTemp = "advancesearch";
            }
            UserUIPermissions userUIPermissions = new UserUIPermissions(pageNameTemp, loginUser);
            if (!userUIPermissions.isAuthorizedEditLockVar()) {
        		boolean auditLock = false;
        		if (variableMappingFacade.isMappingBeingModified(selectedVariable)) {
        			auditLock=variableMappingFacade.isAuditLockInTemp(selectedVariable);
        		} else {
        			auditLock=variableMappingFacade.isAuditLock(selectedVariable);
        		}
        		if (auditLock) {
        			List errorMessages = new ArrayList();
        			errorMessages.add(WebConstants.LOCK_ERROR_MSG);
        			sessionMessageTray.setErrorMessages(errorMessages);
        			if (null != pageName && pageName.trim().equals("advanceSearch")){
        				return WebUtil.redirectToWPDAdvanceSearchPage(request);
        			} else {
        				return WebUtil.redirectToLandingPage(request);
        			}
        		}
            }
        }
		CreateOrEditMappingResult result = variableMappingFacade.editMapping(mapping);
		// SSCR19537 April 04
		if (null != result && null != result.getMapping() && null != result.getMapping().getEb03()
				&& null != result.getMapping().getEb03().getEb03Association() 
				&& !result.getMapping().getEb03().getEb03Association().isEmpty()) {
			eb03AssnList = result.getMapping().getEb03().getEb03Association();
		}
		if(result.getStatus() == DomainConstants.LOCKED_STATUS){
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();					
					if(obj!=null){
						List errorMessages = new ArrayList();
						errorMessages.add(obj);
						sessionMessageTray.setErrorMessages(errorMessages);
						if(null != pageName && pageName.trim().equals("advanceSearch")){
							return WebUtil.redirectToWPDAdvanceSearchPage(request);
						}else{
							return WebUtil.redirectToLandingPage(request);
						}
					}
				}
			}			
		}
		
		mapping = (Mapping)result.getMapping();
		
		if(null!=pageName)
			mapping.setPageFrom(pageName);  
		/**
		 * mtm code change
		 */
		boolean finalized = mapping.isFinalized();
		if(finalized)
		{
			mapping.setFinalized(false);
		}
		else
		{
			mapping.setFinalized(true);
		}
		/**
		 * end
		 */
		List variableList = result.getMapping().getVariableList();
		List hpnMapgList = result.getMapping().getHpnMapgList();
		if (null != variableList && variableList.size() > 0) {
			BxUtil.encodeVariable((Variable) variableList.get(0));
		}
		ModelAndView modelAndView = new ModelAndView("editmapping");
	  
		TokenGenerator.getInstance().saveToken(request);		
		modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
		modelAndView.addObject("mapping",mapping);
		// SSCR19537 April 04
		modelAndView.addObject("eB03AssnList",eb03AssnList);
		List<String>  accumList = null;
		
		// START-Code change as part of BXNI CR35
		Variable newVariable;
		newVariable=(Variable) variableList.get(0);
		String accum=newVariable.getWpdAccumulator();
		if(accum !=null  && accum !=""){
		 
		  accumList= Arrays.asList(accum.split(","));
		}
		mapping.setWpdAccumList(accumList);
		modelAndView.addObject("accumList",accumList);
		// END-Code change as part of BXNI CR35
		modelAndView.addObject("variableList",variableList);
		modelAndView.addObject("hpnMapgList",hpnMapgList);
		modelAndView.addObject("changeComments", result.getUserComments());
		if((null != mapping.getMsg_type_required()) && (mapping.getMsg_type_required().equals("Y"))){
			
			mapping.setMsg_type_required("true");
		}
		else {
			mapping.setMsg_type_required("false");
		}
		if((null != mapping.getSensitiveBenefitIndicator()) && mapping.getSensitiveBenefitIndicator().equals("Y")){
			
			mapping.setSensitiveBenefitIndicator("true");
		}
		else{
			mapping.setSensitiveBenefitIndicator("false");
		}
		
		return modelAndView;
	}
	/**
	 * @param user
	 * @param modelAndView
	 * @return
	 */
	
	public ModelAndView discardChanges(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		List infoMessages = new ArrayList(); 
		mapping.setVariable(variable);		
		mapping.getVariable().setVariableId(request.getParameter("selectedvariableId"));
		mapping.setVariableSystemId(Long.valueOf(request.getParameter("mappingSysId")));
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		  
		CreateOrEditMappingResult result = variableMappingFacade.discardChanges(mapping,request.getParameter("changeComments") );
		
		if(result.getStatus() == 1){
			
			String mappingSuccess = BxResourceBundle.getResourceBundle("mapping.discardChanges.success", null);		
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	}
	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		deleteLockDuringCancel(request);
		
		return  WebUtil.redirectToLandingPage(request);		
	
	}
	
	
	// From Edit Mapping Page to the previous result session
	public ModelAndView cancelToAdvanceSearchBxResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		deleteLockDuringCancel(request);
		return  WebUtil.redirectToWPDAdvanceSearchPage(request);
	}
	public ModelAndView done(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Mapping mapping = createMappingObject(request);
		
		String pageName = request.getParameter("pageFrom");
		List errorMessagesList = new ArrayList();
		List errorMessagesList1 = new ArrayList();
		List warningMessages = new ArrayList();
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		// checking audit lock
		String selectedVariable = mapping.getVariable().getVariableId();
		Object sessionObj = request.getSession().getAttribute(SecurityConstants.USER);
        if (sessionObj != null && (sessionObj instanceof LoginUser)) {
            LoginUser loginUser = (LoginUser) sessionObj;
            String pageNameTemp = pageName;
            if (null != pageName && pageName.trim().equals("advanceSearch")){
            	pageNameTemp = "advancesearch";
            }
            UserUIPermissions userUIPermissions = new UserUIPermissions(pageNameTemp, loginUser);
            if (!userUIPermissions.isAuthorizedEditLockVar()) {
        		boolean auditLock = false;
        		if (variableMappingFacade.isMappingBeingModified(selectedVariable)) {
        			auditLock=variableMappingFacade.isAuditLockInTemp(selectedVariable);
        		} else {
        			auditLock=variableMappingFacade.isAuditLock(selectedVariable);
        		}
        		if (auditLock) {
        			List variableList = variableMappingFacade.viewVariableDetails(mapping.getVariable());
        			if (null != variableList && variableList.size() > 0) {
        				BxUtil.encodeVariable((Variable) variableList.get(0));
        			}
        			errorMessagesList.add(WebConstants.LOCK_ERROR_MSG);
        			ModelAndView modelAndView = new ModelAndView("editmapping");
        			modelAndView.addObject("error_messages", errorMessagesList);
					modelAndView.addObject("mapping",mapping);
					
					modelAndView.addObject("variableList",variableList);
					modelAndView.addObject("changeComments", request.getParameter("changeComments"));
					// SSCR19537
					if (null != mapping && null != mapping.getEb03() 
							&& null != mapping.getEb03().getEb03Association() && !mapping.getEb03().getEb03Association().isEmpty()) {
						modelAndView.addObject("eB03AssnList",mapping.getEb03().getEb03Association());	
					}
					return modelAndView;
        		}
            }
        }
		CreateOrEditMappingResult result = variableMappingFacade.save(mapping, request.getParameter("changeComments").toUpperCase());
		// SSCR19537 April 04
		if (null != result && null != result.getMapping() && null != result.getMapping().getEb03()
				&& null != result.getMapping().getEb03().getEb03Association() 
				&& !result.getMapping().getEb03().getEb03Association().isEmpty()) {
			eb03AssnList = result.getMapping().getEb03().getEb03Association();
		}
		
		if(null != result && result.getStatus() == DomainConstants.FAILURE_STATUS){			
			if(null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty() && result.getErrorMsgsList().size()>0){
				errorMessagesList1 = result.getErrorMsgsList();
			}
			ModelAndView modelAndView1 = new ModelAndView("editmapping");
			if(null != errorMessagesList1 && !errorMessagesList1.isEmpty() && errorMessagesList1.size()>0){
				for(int i=0;i<errorMessagesList1.size();i++){
					if(null !=errorMessagesList1.get(i) && !errorMessagesList1.isEmpty() && errorMessagesList1.size()>0){
						List nwList = null;
						nwList = (ArrayList)errorMessagesList1.get(i);
						if(null != nwList && !nwList.isEmpty()){
							for(int j=0;j<nwList.size();j++){
								ny = (String)nwList.get(j);
								errorMessagesList.add(ny);
							}									
						}	
					}
				}
			}
			
			modelAndView1.addObject("error_messages", errorMessagesList);
			mapping = result.getMapping();
			if((null != mapping.getMsg_type_required()) && mapping.getMsg_type_required().equals("Y")){
				mapping.setMsg_type_required("true");
			}
			else{
				mapping.setMsg_type_required("false");
			}
			if((null != mapping.getSensitiveBenefitIndicator()) && mapping.getSensitiveBenefitIndicator().equals("Y")){
				
				mapping.setSensitiveBenefitIndicator("true");
			}
			else{
				mapping.setSensitiveBenefitIndicator("false");
			}
			/**
			 * MTM CODE CHANGES
			 */
			if(mapping.isFinalized()==false){

				mapping.setFinalized(true);
			}
			else{
				mapping.setFinalized(false);
			}
			/**
			 * END
			 */
			result.getMapping().setPageFrom(pageName);

			if (null != result.getMapping().getVariableList() && result.getMapping().getVariableList().size() > 0) {
				BxUtil.encodeVariable((Variable) result.getMapping().getVariableList().get(0));
			}
			
			modelAndView1.addObject("mapping",result.getMapping());
			modelAndView1.addObject("variableList",result.getMapping().getVariableList());
			modelAndView1.addObject("hpnMapgList",result.getMapping().getHpnMapgList());
			modelAndView1.addObject("changeComments", result.getUserComments());
			modelAndView1.addObject("result",result);
			modelAndView1.addObject("eB03AssnList",eb03AssnList);
			
			return modelAndView1;
	}

		//List unmappedVariables = locateFacade.findAllUnmappedVariables();
		//List inProgressVariables = locateFacade.findAllInProgressVariables(mapping.getUser().getCreatedUserName());
		
		List infoMessages = new ArrayList(); 
		
		if(result!=null && result.getStatus() == 1){
		
			String mappingSuccess = BxResourceBundle.getResourceBundle("mapping.save.success", null);		
			infoMessages.add(mappingSuccess);		
			//modelAndView.addObject(WebConstants.INFO_MESSAGES,infoMessages);
			sessionMessageTray.setInformationMessages(infoMessages);
			
			if(null!= result.getWarningMsgsList()){
				for(Iterator itrerator = result.getWarningMsgsList().iterator();itrerator.hasNext();){
				    List warningMsg = (List)itrerator.next();
				    if(warningMsg!=null){
				        for(Iterator itr = warningMsg.iterator();itr.hasNext();){
				            String msg = (String)itr.next();
				            warningMessages.add(msg);
				        }
				    }
				}
			}
			//modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
			sessionMessageTray.setWarningMessages(warningMessages);
		}
		//modelAndView.addObject("unmappedVariables", unmappedVariables)
		//.addObject("inProgressVariables", inProgressVariables)
		//.addObject("variable", new Variable());		
		
		log.debug("CALL FROM PAGE "+pageName);
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return  WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	}
	
	public ModelAndView saveMapping(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		List infoMessages = new ArrayList(); 
		List errorMessages = new ArrayList();
		
		List errorMessagesList = new ArrayList();
		List errorMessagesList1 = new ArrayList();
		// SCR19537
		List <EB03Association> eB03AssnList = new ArrayList<EB03Association>();
		
		ModelAndView modelAndView = new ModelAndView("editmapping");
		 HttpSession session = request.getSession();
		 
		User user=new User();
		Mapping mapping = createMappingObject(request);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		if(TokenGenerator.getInstance().isTokenValid(request,true)){
			// checking audit lock
			String selectedVariable = mapping.getVariable().getVariableId();
			Object sessionObj = request.getSession().getAttribute(SecurityConstants.USER);
	        if (sessionObj != null && (sessionObj instanceof LoginUser)) {
	            LoginUser loginUser = (LoginUser) sessionObj;
	            String pageNameTemp = pageName;
	            if (null != pageName && pageName.trim().equals("advanceSearch")){
	            	pageNameTemp = "advancesearch";
	            }
	            UserUIPermissions userUIPermissions = new UserUIPermissions(pageNameTemp, loginUser);
	            if (!userUIPermissions.isAuthorizedEditLockVar()) {
	        		boolean auditLock = false;
	        		if (variableMappingFacade.isMappingBeingModified(selectedVariable)) {
	        			auditLock=variableMappingFacade.isAuditLockInTemp(selectedVariable);
	        		} else {
	        			auditLock=variableMappingFacade.isAuditLock(selectedVariable);
	        		}
	        		if (auditLock) {
	        			List variableList = variableMappingFacade.viewVariableDetails(mapping.getVariable());
	        			if (null != variableList && variableList.size() > 0) {
	        				BxUtil.encodeVariable((Variable) variableList.get(0));
	        			}
	        			errorMessages.add(WebConstants.LOCK_ERROR_MSG);
	        			modelAndView.addObject("error_messages", errorMessages);
						modelAndView.addObject("mapping",mapping);
						modelAndView.addObject("variableList",variableList);
						modelAndView.addObject("changeComments", request.getParameter("changeComments"));
						TokenGenerator.getInstance().saveToken(request);		
						modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
						// SSCR19537
						if (null != mapping && null != mapping.getEb03() 
								&& null != mapping.getEb03().getEb03Association() && !mapping.getEb03().getEb03Association().isEmpty()) {
							modelAndView.addObject("eB03AssnList",mapping.getEb03().getEb03Association());	
						}
						return modelAndView;
	        		}
	            }
	        }
		CreateOrEditMappingResult result = variableMappingFacade.save(mapping, request.getParameter("changeComments").toUpperCase());
		if (null != result && null != result.getMapping() && null != result.getMapping().getEb03()
				&& null != result.getMapping().getEb03().getEb03Association() 
				&& !result.getMapping().getEb03().getEb03Association().isEmpty()) {
			eB03AssnList = result.getMapping().getEb03().getEb03Association();
		}
		List warningMessages = new ArrayList();
		if(result != null && result.getStatus() == DomainConstants.LOCKED_STATUS){
			
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();
					if(obj!=null){
						errorMessages.add(obj);
						result.getMapping().setPageFrom(pageName);
						if (null != result.getMapping().getVariableList() && result.getMapping().getVariableList().size() > 0) {
							BxUtil.encodeVariable((Variable) result.getMapping().getVariableList().get(0));
						}
						modelAndView.addObject("error_messages", errorMessages);
						modelAndView.addObject("mapping",result.getMapping());
						modelAndView.addObject("variableList",result.getMapping().getVariableList());
						modelAndView.addObject("changeComments", result.getUserComments());
						modelAndView.addObject("result",result);
						TokenGenerator.getInstance().saveToken(request);		
						modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
						modelAndView.addObject("eB03AssnList",eB03AssnList);
						return modelAndView;
					}
				}
			}
		}
			if(result!=null && result.getStatus() == 1){
				String mappingSuccess = BxResourceBundle.getResourceBundle("mapping.save.success", null);		
				infoMessages.add(mappingSuccess);		
				modelAndView.addObject(WebConstants.INFO_MESSAGES,infoMessages);
				
				if(null!= result.getWarningMsgsList()){
					for(Iterator itrerator = result.getWarningMsgsList().iterator();itrerator.hasNext();){
					    List warningMsg = (List)itrerator.next();
					    if(warningMsg!=null){
					        for(Iterator itr = warningMsg.iterator();itr.hasNext();){
					            String msg = (String)itr.next();
					            warningMessages.add(msg);
					        }
					    }
					}
				}
				modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
				
			}else{
				String mappingFailure = BxResourceBundle.getResourceBundle("mapping.save.failure", null);		
				errorMessages.add(mappingFailure);		
				modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);	
				
				
				if(result !=null && result.getStatus() == DomainConstants.FAILURE_STATUS){			
					if(null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty() && result.getErrorMsgsList().size()>0){
						errorMessagesList1 = result.getErrorMsgsList();
					}
					
					if(null != errorMessagesList1 && !errorMessagesList1.isEmpty() && errorMessagesList1.size()>0){
						for(int i=0;i<errorMessagesList1.size();i++){
							if(null !=errorMessagesList1.get(i) && !errorMessagesList1.isEmpty() && errorMessagesList1.size()>0){
								List nwList = null;
								nwList = (ArrayList)errorMessagesList1.get(i);
								if(null != nwList && !nwList.isEmpty()){
									for(int j=0;j<nwList.size();j++){
										ny = (String)nwList.get(j);
										errorMessagesList.add(ny);
									}									
								}	
							}
						}
					}
					
					modelAndView.addObject("error_messages", errorMessagesList);
					
			}
				
		}
	
		if(null != result){
			mapping = result.getMapping();	
		}
		
		if((null != mapping.getMsg_type_required()) && mapping.getMsg_type_required().equals("Y")){
			
			mapping.setMsg_type_required("true");
		}
		else {
			mapping.setMsg_type_required("false");
		}
		if((null != mapping.getSensitiveBenefitIndicator()) && mapping.getSensitiveBenefitIndicator().equals("Y")){
			
			mapping.setSensitiveBenefitIndicator("true");
		}
		else {
			mapping.setSensitiveBenefitIndicator("false");
		}
		/**
		 * MTM CODE CHANGES
		 */
		if(mapping.isFinalized()==false){

			mapping.setFinalized(true);
		}
		else{
			mapping.setFinalized(false);
		}
		/**
		 * END
		 */
		
		/*if (null!= mapping.getProcedureExcludedInd() && mapping.getProcedureExcludedInd().equals(DomainConstants.Y)) {
			mapping.setProcedureExcludedInd("true");
		} else {
			mapping.setProcedureExcludedInd("false");
		}*/
		if(null != result){
			if (null != result.getMapping().getVariableList() && result.getMapping().getVariableList().size() > 0) {
				BxUtil.encodeVariable((Variable) result.getMapping().getVariableList().get(0));
			}
			result.getMapping().setPageFrom(pageName);
			modelAndView.addObject("mapping",result.getMapping());
			modelAndView.addObject("variableList",result.getMapping().getVariableList());
			modelAndView.addObject("hpnMapgList",result.getMapping().getHpnMapgList());
			modelAndView.addObject("changeComments", result.getUserComments());
			modelAndView.addObject("result",result);
			modelAndView.addObject("eB03AssnList",eB03AssnList);
		}
		
		TokenGenerator.getInstance().saveToken(request);		
		modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
		return modelAndView;
		}
		// If Token invalid, i.e a page refresh
		else{
			return WebUtil.redirectToEditPage(request, request.getParameter("selectedvariableId"));
		}
	}
	public ModelAndView viewHistory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String variableId = request.getParameter("variableId");		
		List auditTrail  = variableMappingFacade.viewHistory(variableId, auditInfoLimit);
		ModelAndView modelAndView = new ModelAndView("viewhistory");
		modelAndView.addObject("auditTrailList",auditTrail);
		Integer auditTrailCount = 0;
		if (null != auditTrail) {
			auditTrailCount = auditTrail.size();
		}
		modelAndView.addObject("sizeOfAuditTrail",auditTrailCount);
		return modelAndView;
	}
	private Long setHippaCodeValueSysId(String hippaCodeSysId){
		
		if(hippaCodeSysId != null && !hippaCodeSysId.trim().equals("")){
			
			return Long.valueOf(hippaCodeSysId);
		}
		else{ 
			return null;
		}
	}
	/*private Long setSequenceNumber(String seqNum){
		
		if(seqNum != null && !seqNum.trim().equals("")){
			
			return Long.valueOf(seqNum);
		}
		else return null;
	}*/
	private Mapping createMappingObject(HttpServletRequest request){
		
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		HippaCodeValue hippaCodeValue;
		User user=new User();
		mapping.setVariable(variable);
		mapping.setEb01(new HippaSegment());
		mapping.setEb02(new HippaSegment());
		mapping.setEb03(new HippaSegment());
		mapping.setEb06(new HippaSegment());
		mapping.setEb09(new HippaSegment());
		mapping.setIi02(new HippaSegment());
		mapping.setHsd01(new HippaSegment());
		mapping.setHsd02(new HippaSegment());
		mapping.setHsd03(new HippaSegment());
		mapping.setHsd04(new HippaSegment());
		mapping.setHsd05(new HippaSegment());
		mapping.setHsd06(new HippaSegment());
		mapping.setHsd07(new HippaSegment());
		mapping.setHsd08(new HippaSegment());
		mapping.setNoteTypeCode(new HippaSegment());
		mapping.setAccum(new HippaSegment());
		mapping.setUtilizationMgmntRule(new HippaSegment());
		mapping.setStartAge(new HippaSegment());
		mapping.setEndAge(new HippaSegment());
		//NM1 Message Segment
		mapping.setNm1MessageSegment(new HippaSegment());
				
		List hippaCodeEB01List = new ArrayList();
		List<ExtendedMessageMapping> extndMsgEB01List = new ArrayList<ExtendedMessageMapping>();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb01Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB01Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("eb01SysId")));
		//hippaCodeValue.setDescription(request.getParameter("eb01Desc"));
		hippaCodeEB01List.add(hippaCodeValue);
		mapping.getEb01().setName(DomainConstants.EB01_NAME);
		mapping.getEb01().setHippaCodeSelectedValues(hippaCodeEB01List);
		
		ExtendedMessageMapping extndMsg = new ExtendedMessageMapping();
		extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(request.getParameter("eb01ExtndMsgValSysId")));
		extndMsg.setValue(request.getParameter("eb01Val").trim());
		String eb01ExtndMsgs = request.getParameter("eb01ExtndMsgJsonObj");	
		if(!eb01ExtndMsgs.equals("")){
			JSONArray jsonArray = JSONArray.fromObject(eb01ExtndMsgs);
			JSONObject jsonObject = jsonArray.getJSONObject(0);		
			if(!jsonObject.getString("eb01ExtndMessage1").equals("") || !(jsonObject.getString("eb01ExtndMessage2").equals("")) || !(jsonObject.getString("eb01ExtndMessage3").equals(""))){
				extndMsg.setNetworkInd(jsonObject.getString("eb01NetworkInd"));
				if(jsonObject.get("changeInd") != null){
					extndMsg.setChangeInd(jsonObject.getString("changeInd"));
				} 
				extndMsg.setExtndMsg1(jsonObject.getString("eb01ExtndMessage1"));
				extndMsg.setExtndMsg2(jsonObject.getString("eb01ExtndMessage2"));
				extndMsg.setExtndMsg3(jsonObject.getString("eb01ExtndMessage3"));
			}
		} else if(extndMsg.getExtendedMsgValueSysId() != null){
			extndMsg.setChangeInd("D");
		}
		extndMsgEB01List.add(extndMsg);
		mapping.getEb01().setExtendedMsgsForSelectedValues(extndMsgEB01List);
		
		List hippaCodeEB02List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb02Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB02Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("eb02SysId")));	
		//hippaCodeValue.setDescription(request.getParameter("eb02Desc"));
		hippaCodeEB02List.add(hippaCodeValue);
		mapping.getEb02().setName(DomainConstants.EB02_NAME);
		mapping.getEb02().setHippaCodeSelectedValues(hippaCodeEB02List);
		
		List hippaCodeEB03List = new ArrayList();
		String[] eb03Values = request.getParameterValues("eb03Val");
		String[] eb03SysId = request.getParameterValues("eb03SysId");
		//String[] eb03Desc = request.getParameterValues("eb03Desc");
		String[] eb03Desc = request.getParameterValues("EB03Desc");
			if(eb03Values != null){
			    if(eb03Desc == null){
			        eb03Desc = new String[eb03Values.length];
			    }
				for(int i=0;i<eb03Values.length;i++){
					if(eb03Values != null && eb03Values[i] != ""){
						hippaCodeValue = new HippaCodeValue();
						hippaCodeValue.setValue(eb03Values[i].trim());
						if(eb03SysId.length > i && !eb03SysId[i].equals("")){
							
							hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(eb03SysId[i]));
						}
						else{
							
							hippaCodeValue.setHippaCodeValueSysId(null);
						}

						hippaCodeValue.setDescription(eb03Desc[i]);
						hippaCodeEB03List.add(hippaCodeValue);
					}
			}	
		}
		hippaCodeEB03List = BxUtil.removeDuplicates(hippaCodeEB03List);
		mapping.getEb03().setName(DomainConstants.EB03_NAME);
		mapping.getEb03().setHippaCodeSelectedValues(hippaCodeEB03List);
		
		List hippaCodeEB06List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb06Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB06Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("eb06SysId")));
		hippaCodeEB06List.add(hippaCodeValue);
		mapping.getEb06().setName(DomainConstants.EB06_NAME);
		mapping.getEb06().setHippaCodeSelectedValues(hippaCodeEB06List);
		
		List hippaCodeEB09List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb09Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB09Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("eb09SysId")));
		hippaCodeEB09List.add(hippaCodeValue);
		mapping.getEb09().setName(DomainConstants.EB09_NAME);
		mapping.getEb09().setHippaCodeSelectedValues(hippaCodeEB09List);
		
		List hippaCodeHSD01List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd01").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD01Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd01SysId")));
		hippaCodeHSD01List.add(hippaCodeValue);
		mapping.getHsd01().setName(DomainConstants.HSD01_NAME);
		mapping.getHsd01().setHippaCodeSelectedValues(hippaCodeHSD01List);
		
		List hippaCodeHSD02List = new ArrayList();
		if(null != request.getParameterValues("hsd02")){
			int sizeOfHSD02 = request.getParameterValues("hsd02").length;
			String[] HSD02 = new String[sizeOfHSD02];
			String[] HSD02Desc = request.getParameterValues("HSD02Desc");
			if(HSD02Desc == null){
				HSD02Desc = new String[sizeOfHSD02];
			}
			for(int i=0;i<sizeOfHSD02;i++){
				HSD02[i] = request.getParameterValues("hsd02")[i].trim();
				hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(HSD02[i]);
				hippaCodeValue.setDescription(HSD02Desc[i]);
				hippaCodeHSD02List.add(hippaCodeValue);
			}
		}
		mapping.getHsd02().setName(DomainConstants.HSD02_NAME);
		mapping.getHsd02().setHippaCodeSelectedValues(hippaCodeHSD02List);
		
		List hippaCodeHSD03List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd03").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD03Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd03SysId")));
		hippaCodeHSD03List.add(hippaCodeValue);
		mapping.getHsd03().setName(DomainConstants.HSD03_NAME);
		mapping.getHsd03().setHippaCodeSelectedValues(hippaCodeHSD03List);
		
		List hippaCodeHSD04List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd04").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD04Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd04SysId")));
		hippaCodeHSD04List.add(hippaCodeValue);
		mapping.getHsd04().setName(DomainConstants.HSD04_NAME);
		mapping.getHsd04().setHippaCodeSelectedValues(hippaCodeHSD04List);
		
		List hippaCodeHSD05List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd05").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD05Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd05SysId")));
		hippaCodeHSD05List.add(hippaCodeValue);
		mapping.getHsd05().setName(DomainConstants.HSD05_NAME);
		mapping.getHsd05().setHippaCodeSelectedValues(hippaCodeHSD05List);
		
		List hippaCodeHSD06List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd06").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD06Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd06SysId")));
		hippaCodeHSD06List.add(hippaCodeValue);
		mapping.getHsd06().setName(DomainConstants.HSD06_NAME);
		mapping.getHsd06().setHippaCodeSelectedValues(hippaCodeHSD06List);
		
		List hippaCodeHSD07List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd07").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD07Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd07SysId")));
		hippaCodeHSD07List.add(hippaCodeValue);
		mapping.getHsd07().setName(DomainConstants.HSD07_NAME);
		mapping.getHsd07().setHippaCodeSelectedValues(hippaCodeHSD07List);
		
		List hippaCodeHSD08List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd08").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD08Desc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("hsd08SysId")));
		hippaCodeHSD08List.add(hippaCodeValue);
		mapping.getHsd08().setName(DomainConstants.HSD08_NAME);
		mapping.getHsd08().setHippaCodeSelectedValues(hippaCodeHSD08List);
		
		// Condition added as part of SSCR19537
		if(null == request.getParameter("indEB03AssnCheckBox")) {
			List hippaCodeIII02List = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("ii02Val").trim());
			hippaCodeValue.setDescription(request.getParameter("III02Desc"));
			hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("ii02SysId")));
			hippaCodeIII02List.add(hippaCodeValue);
			mapping.getIi02().setHippaCodeSelectedValues(hippaCodeIII02List);
		}
		mapping.getIi02().setName(DomainConstants.III02_NAME);
		
		List hippaCodeAccumList = new ArrayList();
		String[] accumValues = request.getParameterValues("accumulator");
		String[] accumSysId = request.getParameterValues("accumulatorSysId");	
		String[] accumDesc = request.getParameterValues("AccumDesc");
		
		if(accumValues!=null){
		    if(accumDesc == null){
		        accumDesc = new String[accumValues.length];
		    }
			for(int i=0;i<accumValues.length;i++){
				if(accumValues != null && accumValues[i] != ""){
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(accumValues[i].trim());
					
					if(accumSysId != null && accumSysId.length > i &&  !accumSysId[i].equals("")){
						
						hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(accumSysId[i]));
					}
					else{
						
						hippaCodeValue.setHippaCodeValueSysId(null);
					}

					hippaCodeValue.setDescription(accumDesc[i]);
					hippaCodeAccumList.add(hippaCodeValue);
				}	
			}	
		}
		hippaCodeAccumList = BxUtil.removeDuplicates(hippaCodeAccumList);
		mapping.getAccum().setName(DomainConstants.ACCUM_NAME);
		mapping.getAccum().setHippaCodeSelectedValues(hippaCodeAccumList);
		
		
		List hippaCodeUMRuleList = new ArrayList();
		String[] umRuleValues = request.getParameterValues("umRuleVal");
		String[] umRuleSysId = request.getParameterValues("umRuleSysId");	
		String[] umRuleDesc = request.getParameterValues("UMRuleDesc");
		
		if(umRuleValues!=null){
		    if(umRuleDesc == null){
		    	umRuleDesc = new String[umRuleValues.length];
		    }
			for(int i=0;i<umRuleValues.length;i++){
				if(umRuleValues[i] != ""){
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(umRuleValues[i].trim());
					
					if(umRuleSysId != null && umRuleSysId.length > i &&  !umRuleSysId[i].equals("")){
						
						hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(umRuleSysId[i]));
					}
					else{
						
						hippaCodeValue.setHippaCodeValueSysId(null);
					}
					hippaCodeValue.setDescription(umRuleDesc[i]);
					hippaCodeUMRuleList.add(hippaCodeValue);
				}	
			}	
		}
		 hippaCodeUMRuleList = BxUtil.removeDuplicates(hippaCodeUMRuleList);
        mapping.getUtilizationMgmntRule().setName(DomainConstants.UMRULE_NAME);
        mapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(hippaCodeUMRuleList);
        
        // Condition added as part of SSCR19537
		if(null == request.getParameter("indEB03AssnCheckBox")) {
			List noteTypeList = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("noteTypeCodeVal").trim());
			hippaCodeValue.setDescription(request.getParameter("NoteTypeDesc"));
			hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("noteTypeCodeSysId")));
			noteTypeList.add(hippaCodeValue);
			mapping.getNoteTypeCode().setHippaCodeSelectedValues(noteTypeList);
			
			if(null != request.getParameter("messageValue").trim()){
				mapping.setMessage(request.getParameter("messageValue").toUpperCase());
			}
			else{
				mapping.setMessage(null);
			}
			if(null!=request.getParameter("msgRqdChkBox")){
				mapping.setMsg_type_required("Y");
			}else{
				mapping.setMsg_type_required("N");
			}
		}
		mapping.getNoteTypeCode().setName(DomainConstants.NOTE_TYPE_CODE);
		
		mapping.getVariable().setVariableId(request.getParameter("selectedvariableId"));
		mapping.setVariableSystemId(Long.valueOf(request.getParameter("mappingSysId")));
		mapping.getVariable().setVariableFormat(request.getParameter("variableFormat"));
		//BXNI CR29
		mapping.getVariable().setPva(request.getParameter("providerArrangement"));
		//setting variable category code
		mapping.getVariable().setLgCatagory(request.getParameter("lgCatagory").trim());
		mapping.getVariable().setIsgCatagory(request.getParameter("isgCatagory").trim());
		mapping.getVariable().setVariableSystem(request.getParameter("system").trim());
		mapping.getVariable().setDescription(request.getParameter("variableDescHidden"));
		mapping.getVariable().setMinorHeadings(request.getParameter("valuesOfMinorHeadings"));
		mapping.getVariable().setMajorHeadings(request.getParameter("valuesOfMajorHeadings"));
		
		
		if(null!=request.getParameter("accumNtReqdChkBox")){
			mapping.setSensitiveBenefitIndicator("Y");
		}else{
			mapping.setSensitiveBenefitIndicator("N");
		}
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		//Adding NM1 Message Segment to mapping object
		List hippaCodeNM1MessageSegmentList = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("nm1MessageSegmentId").trim());
		hippaCodeValue.setDescription(request.getParameter("nm1MessageSegmentDesc"));
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("nm1MessageSegmentSysId")));	
		hippaCodeNM1MessageSegmentList.add(hippaCodeValue);
		mapping.getNm1MessageSegment().setName(DomainConstants.NM1_MSG_SGMNT);
		mapping.getNm1MessageSegment().setHippaCodeSelectedValues(hippaCodeNM1MessageSegmentList);
		
		/**
		 * Code change for MTM
		 */
		String notFinalized=request.getParameter("notFinalizedChkBox");
		log.debug("check the value for not finalized"+notFinalized);
		if(null!=request.getParameter("notFinalizedChkBox")&& request.getParameter("notFinalizedChkBox").equalsIgnoreCase("checked")){

			mapping.setFinalized(false);
		}else  {
			mapping.setFinalized(true);
		}
		/**
		 * ended
		 */
		
		// Code change for October Release
	
		String auditStatus = (String) request.getParameter("auditStatus");
		if (null !=auditStatus && !"".equalsIgnoreCase(auditStatus)) {
			if("N".equalsIgnoreCase(auditStatus.trim())) {
				mapping.setVariableStatusForAuditTrail(0);
			} else if("Y".equalsIgnoreCase(auditStatus.trim())) {
				mapping.setVariableStatusForAuditTrail(1);
			}
			mapping.setAuditLock(auditStatus);
		}
		
		// Start Age Mapping for a variable -- BXNI June 2012 Release.
		List<HippaCodeValue> hippaCodeStartAgeList = new ArrayList<HippaCodeValue>();
		hippaCodeValue = new HippaCodeValue();
		String startAge = request.getParameter("startAge").trim();
		startAge = startAge.replaceAll("( )+", "");
		startAge = startAge.replaceAll("(,,)+", ",");
		startAge = StringUtils.removeStart(startAge, ",");
		startAge = StringUtils.removeEnd(startAge, ",");
		hippaCodeValue.setValue(startAge);
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("startAgeSysId")));
		hippaCodeStartAgeList.add(hippaCodeValue);
		mapping.getStartAge().setName(DomainConstants.START_AGE_NAME);
		mapping.getStartAge().setHippaCodeSelectedValues(hippaCodeStartAgeList);
		
		// End Age Mapping for a variable -- BXNI June 2012 Release.
		List<HippaCodeValue> hippaCodeEndAgeList = new ArrayList<HippaCodeValue>();
		hippaCodeValue = new HippaCodeValue();
		String endAge = request.getParameter("endAge").trim();
		endAge = endAge.replaceAll("( )+", "");
		endAge = endAge.replaceAll("(,,)+", ",");
		endAge = StringUtils.removeStart(endAge, ",");
		endAge = StringUtils.removeEnd(endAge, ",");
		hippaCodeValue.setValue(endAge);
		hippaCodeValue.setHippaCodeValueSysId(setHippaCodeValueSysId(request.getParameter("endAgeSysId")));
		hippaCodeEndAgeList.add(hippaCodeValue);
		mapping.getEndAge().setName(DomainConstants.END_AGE_NAME);
		mapping.getEndAge().setHippaCodeSelectedValues(hippaCodeEndAgeList);
		
		// CR29
		if (null != request.getParameter("excludeProceduresChkBox")	&& request.getParameter("excludeProceduresChkBox").equalsIgnoreCase("checked")) {
			mapping.setProcedureExcludedInd(DomainConstants.Y);
		} else {
			mapping.setProcedureExcludedInd(DomainConstants.N);
		}
		// SSCR19537 April04
		createEb03AssociatedValues(request, mapping);
		return mapping;
	}
	private void deleteLockDuringCancel(HttpServletRequest request) {
		String variableId = request.getParameter("selectedvariableId");
		Mapping existingMapping;
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		if (null != variableId && !DomainConstants.EMPTY.equals(variableId)) {
			existingMapping = variableMappingFacade.retrieveMapping(variableId);
			if (null != existingMapping) {
				// Case 1 :  Delete the Lock when mapping status is "scheduled to test"
				//or "sheduled to production" or "object transferred". 
				if (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(existingMapping.getVariableMappingStatus()) 
						|| DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_NOT_APPLICABLE.equals(existingMapping.getVariableMappingStatus())) {
					variableMappingFacade.unlock(existingMapping);
				}
				// Case 2 :  Delete the Lock when mapping status is "Building"
				//or "Being Modified" and the loggedin user is not equal to last updated user. 
				else if ((DomainConstants.STATUS_BEING_MODIFIED.equals(existingMapping.getVariableMappingStatus()) 
						|| DomainConstants.STATUS_BUILDING.equals(existingMapping.getVariableMappingStatus())) &&
						(!loggedInUser.equalsIgnoreCase(existingMapping.getUser().getLastUpdatedUserName()))) {
					variableMappingFacade.unlock(existingMapping);
				}

			}
		}
		
	}
	/**
	 * Method checks if an entered variable id is a valid one. If not throws error
	 * BXNI June 2012 Release
	 * @param request,response
	 * @return Returns the modelAndView jsonView containing the error list.
	 */
	public ModelAndView isAgeTierVariableValid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String variableId = request.getParameter("variableIdHidden");
		String startAge = request.getParameter("startAge");
		String endAge = request.getParameter("endAge");
		String[] startAgeArray = StringUtils.split(startAge, ",");
		String[] endAgeArray = StringUtils.split(endAge, ",");
		int sizeOfStartAgeList = 0;
		int sizeOfEndAgeList = 0;
		int startAgeValue = 0;
		int endAgeValue = 0;
		Variable variable = new Variable();
		variable.setVariableId(variableId);
		List<String> errorList = new ArrayList<String>();
		List<Variable> validAgeTierVariableList = new ArrayList<Variable>();
		
		sizeOfStartAgeList = startAgeArray.length;
		sizeOfEndAgeList = endAgeArray.length;
		
		Map map = new HashMap();
		
		
		if(sizeOfStartAgeList > 6 ){
			errorList.add("More than 6 Start Age values cannot be mapped.");
			map.put(WebConstants.ERROR_MESSAGES, errorList);
			return new ModelAndView("jsonView",map);
		}
	
		if(sizeOfEndAgeList > 6){
			errorList.add("More than 6 End Age values cannot be mapped.");
			map.put(WebConstants.ERROR_MESSAGES, errorList);
			return new ModelAndView("jsonView",map);
		}
		
		for(int i = 0; i < sizeOfStartAgeList; i++){
			startAgeArray[i] = StringUtils.trim(startAgeArray[i]);
		}
		for(int i = 0; i < sizeOfEndAgeList; i++){
			endAgeArray[i] = StringUtils.trim(endAgeArray[i]);
		}
		
	
		
		//flag to check if the start age is contained in the db list, if so set it to true
		boolean validStartAgeVariableFlag = false;
		//flag to check if the end age is contained in the db list, if so set it to true
		boolean validEndAgeVariableFlag = false ;
	
		
			int countForStartAge = 0;
			int countForValidStartAge = 0;
			int countForEndAge = 0;
			int countForValidEndAge = 0;
			int countForNumericStartAge = 0;
			int countForNumericEndAge = 0;
			boolean isStartAgeValid = false;
			boolean isEndAgeValid = false;
			boolean isStartAgeInteger = false;
			boolean isEndAgeInteger = false;
			validAgeTierVariableList = locateFacade.getAvailableAgeTierVariables("","",variable);
			
			
			for (int k = 0; k < sizeOfStartAgeList; k++) {
				if (!StringUtils.isEmpty(startAgeArray[k])) {
					countForStartAge++;
				}
				if(BxUtil.isInteger(startAgeArray[k])){
					isStartAgeInteger = true;
					countForNumericStartAge++;
					startAgeValue = Integer.parseInt(startAgeArray[k]);
				}
				if(null != validAgeTierVariableList){
				for (int i = 0; i < validAgeTierVariableList.size(); i++) {
					String validAgeVariable = validAgeTierVariableList.get(i)
					.getVariableId();

					if (validAgeVariable.equals(startAgeArray[k])) {
						countForValidStartAge++;
						validStartAgeVariableFlag = true;
						break;
					}
				}
			}
			}
		for (int k = 0; k < sizeOfEndAgeList; k++) {

			if (!StringUtils.isEmpty(endAgeArray[k])) {
				countForEndAge++;
			}
			if(BxUtil.isInteger(endAgeArray[k])){
				isEndAgeInteger = true;
				countForNumericEndAge++;
				endAgeValue = Integer.parseInt(endAgeArray[k]);
			}
			if(null != validAgeTierVariableList){
			for (int i = 0; i < validAgeTierVariableList.size(); i++) {
				String validAgeVariable = validAgeTierVariableList.get(i)
				.getVariableId();

				if (validAgeVariable.equals(endAgeArray[k])) {
					countForValidEndAge++;
					validEndAgeVariableFlag = true;
					break;
				}
			}
		}
		}
		
			if(countForStartAge > 1){
				if(isStartAgeInteger){
					errorList.add("Only Variables can be mapped for multiple Start Age.");
				if(countForStartAge > (countForValidStartAge +  countForNumericStartAge)){
					errorList.add("Invalid Start Age Variable ");
				}}else{
					if((countForStartAge != countForValidStartAge)){
						errorList.add("Invalid Start Age Variable ");
					}
					
				}
			}else if(countForStartAge == 1){
				if(!isStartAgeInteger && !validStartAgeVariableFlag){
					errorList.add("Invalid Start Age Variable ");
				}
			}
			
			
			
			if(countForEndAge > 1){
				if(isEndAgeInteger){
					errorList.add("Only Variables can be mapped for multiple End Age.");
				if(countForEndAge > (countForValidEndAge +  countForNumericEndAge)){
					errorList.add("Invalid End Age Variable ");
				}}else{
					if((countForEndAge != countForValidEndAge)){
						errorList.add("Invalid End Age Variable ");
					}
					
				}
			}else if(countForEndAge == 1){
				if(!isEndAgeInteger && !validEndAgeVariableFlag){
					errorList.add("Invalid End Age Variable ");
				}
			}
				if(null != errorList && !errorList.isEmpty() && !errorList.get(0).equals(DomainConstants.EMPTY)){
					map.put(WebConstants.ERROR_MESSAGES, errorList);
					return new ModelAndView("jsonView",map);
				}
				
				
			if(countForStartAge == 1 && isStartAgeInteger){
				if(!(startAgeValue >= 1 && startAgeValue <= 100)){
					errorList.add("Start Age must be an Integer between 1-100");
					map.put(WebConstants.ERROR_MESSAGES, errorList);
					return new ModelAndView("jsonView",map);
					}
			}
		  if(countForEndAge == 1 && isEndAgeInteger ){
				if(!(endAgeValue >= 1 && endAgeValue <= 100)){
					errorList.add("End Age must be an Integer between 1-100");
					map.put(WebConstants.ERROR_MESSAGES, errorList);
					return new ModelAndView("jsonView",map);
				}
		  }
		  if(countForStartAge == 1 && isStartAgeInteger && countForEndAge == 1 && isEndAgeInteger ){
				if(endAgeValue <= startAgeValue){
					errorList.add("End Age must be greater than Start Age");
					map.put(WebConstants.ERROR_MESSAGES, errorList);
					return new ModelAndView("jsonView",map);
				}
			}
			if(countForStartAge == 1 && isStartAgeInteger){
				if(startAgeValue%1 != 0){
					  errorList.add("Invalid Start Age");
					  map.put(WebConstants.ERROR_MESSAGES, errorList);
						return new ModelAndView("jsonView",map);
				}
			}
			if(countForEndAge == 1 && isEndAgeInteger){
				if(endAgeValue%1 != 0){
					  errorList.add("Invalid End Age");
					  map.put(WebConstants.ERROR_MESSAGES, errorList);
					  return new ModelAndView("jsonView",map);
				}
			}
			
	
		map.put(WebConstants.ERROR_MESSAGES, errorList);
		return new ModelAndView("jsonView",map);		
	}
		

	/**
	 * @return Returns the variableMappingFacade.
	 */
	public VariableMappingFacade getVariableMappingFacade() {
		return variableMappingFacade;
	}
	/**
	 * @param variableMappingFacade The variableMappingFacade to set.
	 */
	public void setVariableMappingFacade(
			VariableMappingFacade variableMappingFacade) {
		this.variableMappingFacade = variableMappingFacade;
	}	
	/**
	 * @return Returns the auditInfoLimit.
	 */
	public Integer getAuditInfoLimit() {
		return auditInfoLimit;
	}
	/**
	 * @param auditInfoLimit The auditInfoLimit to set.
	 */
	public void setAuditInfoLimit(Integer auditInfoLimit) {
		this.auditInfoLimit = auditInfoLimit;
	}
	/**
	 * @return Returns the locateFacade.
	 */
	public LocateFacade getLocateFacade() {
		return locateFacade;
	}
	/**
	 * @param locateFacade The locateFacade to set.
	 */
	public void setLocateFacade(LocateFacade locateFacade) {
		this.locateFacade = locateFacade;
	}
	/**
	 * @return Returns the noOfRecords.
	 */
	public int getNoOfRecords() {
		return noOfRecords;
	}
	/**
	 * @param noOfRecords The noOfRecords to set.
	 */
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView autopopulateHSD(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		String format = request.getParameter("format");
		String variableId = request.getParameter("variableId");
		String eb09 = request.getParameter("eb09");
		Variable variable = new Variable();
		HttpSession session = request.getSession();
		variable.setVariableId(variableId.trim());
		List variableWithInfoList = null;
		List variableList = new ArrayList();
		variableList.add(variableId);
		List errorMessagesList = new ArrayList();
		Map map = new HashMap();
		String hippaSegmentToPopulate = "";
		String hsdValue = "";
		String hsdDescription = "";
		String hsdVal = "HsdValue";
		String hsdDesc = "HsdDescription";
		HippaCodeValue hsdHippaCode = new HippaCodeValue();
		if (null != pageName && null != variable.getVariableId()
				&& !variable.getVariableId().equals("")) {
			
				Mapping mapping = new Mapping();
				variableWithInfoList = variableMappingFacade
						.viewVariableDetails(variable);
				ModelAndView modelAndView = new ModelAndView("jsonView");
				hippaSegmentToPopulate = DomainConstants.HSD01_NAME;
				mapping = variableMappingFacade.autoPopulateByFormat(
						(Variable) variableWithInfoList.get(0), mapping,
						hippaSegmentToPopulate, eb09);// Ends
				if (null != mapping && null != mapping.getHsd01Value()
						&& !mapping.getHsd01Value().equalsIgnoreCase("")) {
					hsdHippaCode = (HippaCodeValue)mapping.getHsd01().getHippaCodeSelectedValues().get(0);
					hsdValue = hsdHippaCode.getValue();
					hsdDescription = hsdHippaCode.getDescription();
					map.put(hsdVal, hsdValue);
					map.put(hsdDesc, hsdDescription);
					return new ModelAndView(WebConstants.JSON_VIEW, map);
				}
			}
			
	
		map.put(hsdVal, new String());
		map.put(hsdDesc, new String());

		return new ModelAndView(WebConstants.JSON_VIEW, map);
	}
	
// SSCR 19537 April04 - Start
	
	public ModelAndView populateEb03AssociatedValues(HttpServletRequest request,
			HttpServletResponse response) {
		
		List <EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		
		String newEb03Values = request.getParameter(DomainConstants.NEW_EB03_VALUES);
		String existingEb03Values = request.getParameter(DomainConstants.EXISTING_EB03_VALUES);
		String existingEb03Assn = request.getParameter(DomainConstants.EXISTING_EB03_ASSN);
		
		// Contains existing EB03 values
		Set <String> existingEb03List = new HashSet <String>();
		
		if (null != existingEb03Values && !DomainConstants.EMPTY.equals(existingEb03Values)) {
			String [] existingEb03Array = existingEb03Values.split("`");
			Collections.addAll(existingEb03List, existingEb03Array);
		}
		
		// Contains both existing and newly added EB03 values.
		Set <String> newEb03List = new HashSet <String>();
		if (null != newEb03Values && !DomainConstants.EMPTY.equals(newEb03Values)) {
			String [] newEb03Array = newEb03Values.split("`"); 
			Collections.addAll(newEb03List, newEb03Array);
		}
		
		if (!newEb03List.isEmpty() && existingEb03List.isEmpty()) {
			eb03AssnList = createEB03AssociationObject(newEb03List);
			
		} else if (!existingEb03List.isEmpty() && !newEb03List.isEmpty()) {
			List <EB03Association> existingEb03AssnList = createEB03AssociationObject(existingEb03Assn);
			if (!existingEb03AssnList.isEmpty()) {
				for (EB03Association eb03Assn: existingEb03AssnList) {
					String eb03Value = null;
					if (null != eb03Assn && null != eb03Assn.getEb03()) {
						eb03Value = eb03Assn.getEb03().getValue();
					}
					if (null != eb03Value) {
						// Add only if it it is present the new list (New list will have both existing and new Eb03 values)
						if (newEb03List.contains(eb03Value)) {
							eb03AssnList.add(eb03Assn);
						}
					}
				}
			}
			
			newEb03List.removeAll(existingEb03List);
			if (!newEb03List.isEmpty()) {
				List <EB03Association> newEb03AssnList = createEB03AssociationObject(newEb03List);
				eb03AssnList.addAll(newEb03AssnList);
			}
		}
			
		JSONArray eb03AssnObjAsJsonArray = JSONArray.fromObject(eb03AssnList);
		return new ModelAndView("jsonView").addObject("eb03AssnObjAsJsonArray", eb03AssnObjAsJsonArray);
	}
	
	/**
	 * Method to create the List of  EB03Association with eb03 coded and other values empty. 
	 * @param newEb03List
	 * @param validEb03List 
	 * @return
	 */
	private List<EB03Association> createEB03AssociationObject(Set<String> newEb03List) {
		
		List <EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		for (String eb03Val : newEb03List) {
			if (null != eb03Val && !DomainConstants.EMPTY.equals(eb03Val)) {
				List <HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>();
				EB03Association eb03Association = new EB03Association();
				HippaCodeValue eb03HippaCodeValue = new HippaCodeValue();
				HippaCodeValue iii02HippaCodeValue = new HippaCodeValue();
				HippaCodeValue noteTypeHippaCodeValue = new HippaCodeValue();
				
				eb03HippaCodeValue.setValue(eb03Val);
				iii02HippaCodeValue.setValue(DomainConstants.EMPTY);
				iii02List.add(iii02HippaCodeValue);
				noteTypeHippaCodeValue.setValue(DomainConstants.EMPTY);
				
				eb03Association.setIii02List(iii02List);
				eb03Association.setMessage(DomainConstants.EMPTY);
				eb03Association.setMsgReqdInd(DomainConstants.EMPTY);
				eb03Association.setEb03(eb03HippaCodeValue);
				eb03Association.setNoteType(noteTypeHippaCodeValue);
				eb03AssnList.add(eb03Association);
			}
		}
		return eb03AssnList;
	}
	
	/**
	 * Method to create the EB03 Associated values at the EB03 level.
	 * @param jsonArray
	 * @return
	 */
	private List<EB03Association> createEB03AssociationObject(String jsonArray) {

		List<EB03Association> eB03AssociationList = new ArrayList<EB03Association>();
		
		if (null != jsonArray && !DomainConstants.EMPTY.equals(jsonArray)) {
			// Assign stringified JSON object from the JSP JSONArray. 
			JSONArray eb03AssnJsonPanelArray = JSONArray.fromObject(jsonArray);
			if (eb03AssnJsonPanelArray.size() > 0) {
				
				// Iterate the JSON Array and get the corresponding JSON objet
				for (int i = 0; i <  eb03AssnJsonPanelArray.size(); i++) {
					EB03Association eb03Association = new EB03Association();
					List<HippaCodeValue> iii02List = new ArrayList<HippaCodeValue>(); 
					// Get the JSON object
					final JSONObject jsonObject = eb03AssnJsonPanelArray.getJSONObject(i);
					
					// Get EB03 and associated values after from the JSON Object
					final String eb03 = jsonObject.getString(DomainConstants.EB03_NAME);
					final String iii02 = jsonObject.getString(DomainConstants.III02_NAME);
					final String iii02Desc = jsonObject.getString(DomainConstants.III02_DESC);
					final String messageText = BxUtil.removeEscapedCharacters(jsonObject.getString(DomainConstants.MESSAGE));
					final String msgRqdInd = jsonObject.getString(DomainConstants.MESG_REQD_IND);
					final String noteTypeCode = jsonObject.getString(DomainConstants.NOTE_TYPE_CODE);
					final String noteTypeDesc = BxUtil.removeEscapedCharacters(jsonObject.getString(DomainConstants.NOTE_TYPE_DESC));
					
					// Convert the String Value to HippaCode Value.
					HippaCodeValue eb03Value = null;
					HippaCodeValue iii02Value = null;
					HippaCodeValue noteTypeCodeValue = null;
					
					if (null != eb03) {
						eb03Value = BxUtil.covertToHippaCodeValue(eb03.trim());
					}
					if(null != iii02) {
						iii02Value = BxUtil.covertToHippaCodeValue(iii02.trim(), iii02Desc);
					}
					
					iii02List.add(iii02Value);
					if (null != noteTypeCode) {
						noteTypeCodeValue = BxUtil.covertToHippaCodeValue(noteTypeCode.trim(), noteTypeDesc);
					}
					
					// Create EB03 Association Object.
					eb03Association.setEb03(eb03Value);
					eb03Association.setIii02List(iii02List);
					eb03Association.setMessage(messageText);
					eb03Association.setMsgReqdInd(msgRqdInd);
					eb03Association.setNoteType(noteTypeCodeValue);
					eB03AssociationList.add(eb03Association);
				}
			}
		}
		return eB03AssociationList;
	}
	
	/**
	 * Method to create the EB03 associated values from the page.
	 * @param request
	 * @param mapping
	 */
	private void createEb03AssociatedValues(HttpServletRequest request,
			Mapping mapping) {
		
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		List<ExtendedMessageMapping> extndMsgEB03List = new ArrayList<ExtendedMessageMapping>();
		List<ExtendedMessageMapping> eb03ObjsList = new ArrayList<ExtendedMessageMapping>();
		String eb03ExtndMsgJsonObj = request.getParameter("eb03ExtndMsgJsonObj");
		if(null != request.getParameter("indEB03AssnCheckBox") 
				&& request.getParameter("indEB03AssnCheckBox").equalsIgnoreCase("on")){
			String jsonArray = request.getParameter("eb03AssnJsonObj");
			
			if (null != jsonArray && !DomainConstants.EMPTY.equals(jsonArray)) {
				eb03AssnList = createEB03AssociationObject(jsonArray);
				if (null != eb03ExtndMsgJsonObj && !DomainConstants.EMPTY.equals(eb03ExtndMsgJsonObj)) {
					JSONArray eb03JsonArray = JSONArray.fromObject(eb03ExtndMsgJsonObj);
					if (eb03JsonArray.size() > 0) {					
						for (int i = 0; i <  eb03JsonArray.size(); i++) {
							JSONObject jsonObject = eb03JsonArray.getJSONObject(i);
							String eb03=jsonObject.getString("eb03Val").trim();
							JSONArray eb03AssnJsonPanelArray = JSONArray.fromObject(jsonArray);
							if (eb03AssnJsonPanelArray.size() > 0) {
								for (int j = 0; j <  eb03AssnJsonPanelArray.size(); j++) {
									JSONObject eb03AssnjsonObject = eb03AssnJsonPanelArray.getJSONObject(j);
									ExtendedMessageMapping extndMsg = new ExtendedMessageMapping();
									if(eb03.equals(eb03AssnjsonObject.getString(DomainConstants.EB03_NAME).trim())){
										if(jsonObject.get("changeInd") != null && jsonObject.getString("changeInd").equals("D")){
											extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(jsonObject.getString("eb03ExtndMsgSysId")));
											extndMsg.setValue(eb03);
											extndMsg.setChangeInd(jsonObject.getString("changeInd"));
											extndMsgEB03List.add(extndMsg);
											break;
										} else{
											if(jsonObject.get("eb03ExtndMsgSysId").equals(null)){
												extndMsg.setExtendedMsgValueSysId(null);
											} else{
												extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(jsonObject.getString("eb03ExtndMsgSysId")));
											}
											extndMsg.setNetworkInd(jsonObject.getString("eb03NetworkInd"));
											extndMsg.setValue(jsonObject.getString("eb03Val"));										
											extndMsg.setChangeInd(jsonObject.getString("changeInd"));										
											extndMsg.setExtndMsg1(jsonObject.getString("eb03ExtndMessage1"));
											extndMsg.setExtndMsg2(jsonObject.getString("eb03ExtndMessage2"));
											extndMsg.setExtndMsg3(jsonObject.getString("eb03ExtndMessage3"));
											eb03ObjsList.add(extndMsg);
											break;
										}
									}
								}
							}
						}
						extndMsgEB03List.addAll(eb03ObjsList);
						mapping.getEb03().setExtendedMsgsForSelectedValues(extndMsgEB03List);
					}
				}
			}
			mapping.setIndvdlEb03AssnIndicator(DomainConstants.Y);
			
		}else{
			
			eb03AssnList = createEB03AssociationObject(request);
			mapping.setIndvdlEb03AssnIndicator(DomainConstants.N);
		}
		//Adding EB03Assn object to the mapping object
		mapping.getEb03().setEb03Association(eb03AssnList);
	}
	
	/**
	 * Method to create the EB03 Associated values at the variable level.
	 * @param request
	 * @return
	 */
	private List<EB03Association> createEB03AssociationObject(
			HttpServletRequest request) {
		
		List<EB03Association> eb03AssociationList =  new ArrayList<EB03Association>();
		List<HippaCodeValue> hippaCodeEB03List = new ArrayList<HippaCodeValue>();
		List <HippaCodeValue>hippaCodeIII02List = new ArrayList<HippaCodeValue>();
		
		HippaCodeValue iii02HippaCodeValue = new HippaCodeValue();
		HippaCodeValue noteTypeHippaCodeValue = new HippaCodeValue();
		
		// Get EB03
		if(null != request.getParameterValues("eb03Val")){
			int sizeOfEB03 = request.getParameterValues("eb03Val").length;
			String[] EB03 = new String[sizeOfEB03];
			String[] EB03Desc = request.getParameterValues("EB03Desc");
			if(EB03Desc == null){
			    EB03Desc = new String[sizeOfEB03];
			}
			for(int i = 0; i<sizeOfEB03; i++){
				HippaCodeValue eb03HippaCodeValue = new HippaCodeValue();
				EB03[i] = request.getParameterValues("eb03Val")[i].trim();
				eb03HippaCodeValue.setValue(EB03[i]);
				eb03HippaCodeValue.setDescription(EB03Desc[i].trim());
				hippaCodeEB03List.add(eb03HippaCodeValue);
			}
		}	
		hippaCodeEB03List = BxUtil.removeDuplicates(hippaCodeEB03List);
		
		// Get III02
		String iii02 = "";
		if (null != request.getParameter("ii02Val")) {
			iii02 = request.getParameter("ii02Val").trim();
		}
		String iii02Desc = "";
		if (null != request.getParameter("III02Desc")) {
			iii02Desc = request.getParameter("III02Desc").trim();
		}
		iii02HippaCodeValue.setValue(iii02);
		iii02HippaCodeValue.setDescription(iii02Desc);
		hippaCodeIII02List.add(iii02HippaCodeValue);
		
		// Get Message
		String messageText = BxUtil.removeEscapedCharacters(request.getParameter("messageValue").trim());
		
		// Get message required indicator.
		String msgRqdInd = DomainConstants.N;
		
		if(null != request.getParameter("msgRqdChkBox")){
			msgRqdInd = DomainConstants.Y;
		}
		
		// Get Note Type.
		String noteType = "";
		if (null != request.getParameter("noteTypeCodeVal")) {
			noteType = request.getParameter("noteTypeCodeVal").trim();
		}
		String noteTypeDesc = "";
		if (null != request.getParameter("NoteTypeDesc")) {
			noteTypeDesc = BxUtil.removeEscapedCharacters(request.getParameter("NoteTypeDesc").trim());
		}
		noteTypeHippaCodeValue.setValue(noteType);
		noteTypeHippaCodeValue.setDescription(noteTypeDesc);
		
		// Create EB03 Association object with same associated values for each EB03.
		for (HippaCodeValue eb03HippaCodeValue : hippaCodeEB03List) {
			String eb03Value = eb03HippaCodeValue.getValue();
			if (null != eb03Value && !DomainConstants.EMPTY.equals(eb03Value)) {
				EB03Association eb03Association = new EB03Association();
				eb03Association.setEb03(eb03HippaCodeValue);
				eb03Association.setIii02List(hippaCodeIII02List);
				eb03Association.setMessage(messageText);
				eb03Association.setMsgReqdInd(msgRqdInd);
				eb03Association.setNoteType(noteTypeHippaCodeValue);
				eb03AssociationList.add(eb03Association);
			}
		}
		return eb03AssociationList;
	}
	
	// SSCR 19537 April04 - End
}