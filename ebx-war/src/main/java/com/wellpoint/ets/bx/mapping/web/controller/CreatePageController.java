/*
 * <CreatePageController.java>
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

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.AdvanceSearchFacade;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;
import com.wellpoint.ets.bx.mapping.domain.entity.ExtendedMessageMapping;
import com.wellpoint.ets.ebx.ooamessage.util.OOAMessageConstants;

/**
 * @author UST-GLOBAL This is a controller class for create mapping for the variables
 */
public class CreatePageController extends MultiActionController {
	private static Logger logger = Logger.getLogger(CreatePageController.class);
	
	private VariableMappingFacade variableMappingFacade;
	private LocateFacade locateFacade;
	private int noOfRecords;
	private String erorrMsgs;
	private String userComments = "";
	private SessionMessageTray sessionMessageTray;
	private AdvanceSearchFacade messageTxtSearchFacade;
	
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = new Mapping();
		Variable variable = new Variable();
		List <EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		
		HippaCodeValue hippaCodeValue;
		List errorMessagesList = new ArrayList();
		List mstrErrMsgsList = new ArrayList();
		User user = new User();
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
		mapping.getEb01().setName(DomainConstants.EB01_NAME);
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb01Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB01Desc"));
		hippaCodeEB01List.add(hippaCodeValue);
		mapping.getEb01().setHippaCodeSelectedValues(hippaCodeEB01List);
		
		ExtendedMessageMapping extndMsg = new ExtendedMessageMapping();
		//extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(request.getParameter("eb01ExtndMsgValSysId")));
		extndMsg.setValue(request.getParameter("eb01Val").trim());
		String eb01ExtndMsgs = request.getParameter("eb01ExtndMsgJsonObj");	
		
		if(!eb01ExtndMsgs.equals("")){
			JSONArray jsonArray = JSONArray.fromObject(eb01ExtndMsgs);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			if(!jsonObject.getString("eb01ExtndMessage1").equals("") || !(jsonObject.getString("eb01ExtndMessage2").equals("")) || !(jsonObject.getString("eb01ExtndMessage3").equals(""))){
				extndMsg.setNetworkInd(jsonObject.getString("eb01NetworkInd"));
				extndMsg.setExtndMsg1(jsonObject.getString("eb01ExtndMessage1"));
				extndMsg.setExtndMsg2(jsonObject.getString("eb01ExtndMessage2"));
				extndMsg.setExtndMsg3(jsonObject.getString("eb01ExtndMessage3"));
			}
		}
		extndMsgEB01List.add(extndMsg);
		mapping.getEb01().setExtendedMsgsForSelectedValues(extndMsgEB01List);
			
		List hippaCodeEB02List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb02Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB02Desc"));
		hippaCodeEB02List.add(hippaCodeValue);
		mapping.getEb02().setName(DomainConstants.EB02_NAME);
		mapping.getEb02().setHippaCodeSelectedValues(hippaCodeEB02List);
		
		List hippaCodeEB03List = new ArrayList();
		
		if(null != request.getParameterValues("eb03Val")){
			int sizeOfEB03 = request.getParameterValues("eb03Val").length;
			String[] EB03 = new String[sizeOfEB03];
			String[] EB03Desc = request.getParameterValues("EB03Desc");
			if(EB03Desc == null){
			    EB03Desc = new String[sizeOfEB03];
			}
			for(int i=0;i<sizeOfEB03;i++){
				EB03[i] = request.getParameterValues("eb03Val")[i].trim();
				hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(EB03[i]);
				hippaCodeValue.setDescription(EB03Desc[i]);
				hippaCodeEB03List.add(hippaCodeValue);
			}
		}	
		hippaCodeEB03List = BxUtil.removeDuplicates(hippaCodeEB03List);
		mapping.getEb03().setName(DomainConstants.EB03_NAME);
		mapping.getEb03().setHippaCodeSelectedValues(hippaCodeEB03List);
		
		List hippaCodeEB06List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb06Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB06Desc"));
		hippaCodeEB06List.add(hippaCodeValue);
		mapping.getEb06().setName(DomainConstants.EB06_NAME);
		mapping.getEb06().setHippaCodeSelectedValues(hippaCodeEB06List);
		
		List hippaCodeEB09List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("eb09Val").trim());
		hippaCodeValue.setDescription(request.getParameter("EB09Desc"));
		hippaCodeEB09List.add(hippaCodeValue);
		mapping.getEb09().setName(DomainConstants.EB09_NAME);
		mapping.getEb09().setHippaCodeSelectedValues(hippaCodeEB09List);
		
		List hippaCodeHSD01List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd01").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD01Desc"));
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
		hippaCodeHSD02List = BxUtil.removeDuplicates(hippaCodeHSD02List);
		mapping.getHsd02().setName(DomainConstants.HSD02_NAME);
		mapping.getHsd02().setHippaCodeSelectedValues(hippaCodeHSD02List);
		
		List hippaCodeHSD03List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd03").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD03Desc"));
		hippaCodeHSD03List.add(hippaCodeValue);
		mapping.getHsd03().setName(DomainConstants.HSD03_NAME);
		mapping.getHsd03().setHippaCodeSelectedValues(hippaCodeHSD03List);
		
		List hippaCodeHSD04List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd04").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD04Desc"));
		hippaCodeHSD04List.add(hippaCodeValue);
		mapping.getHsd04().setName(DomainConstants.HSD04_NAME);
		mapping.getHsd04().setHippaCodeSelectedValues(hippaCodeHSD04List);
		
		List hippaCodeHSD05List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd05").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD05Desc"));
		hippaCodeHSD05List.add(hippaCodeValue);
		mapping.getHsd05().setName(DomainConstants.HSD05_NAME);
		mapping.getHsd05().setHippaCodeSelectedValues(hippaCodeHSD05List);
		
		List hippaCodeHSD06List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd06").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD06Desc"));
		hippaCodeHSD06List.add(hippaCodeValue);
		mapping.getHsd06().setName(DomainConstants.HSD06_NAME);
		mapping.getHsd06().setHippaCodeSelectedValues(hippaCodeHSD06List);
		
		List hippaCodeHSD07List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd07").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD07Desc"));
		hippaCodeHSD07List.add(hippaCodeValue);
		mapping.getHsd07().setName(DomainConstants.HSD07_NAME);
		mapping.getHsd07().setHippaCodeSelectedValues(hippaCodeHSD07List);
		
		List hippaCodeHSD08List = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("hsd08").trim());
		hippaCodeValue.setDescription(request.getParameter("HSD08Desc"));
		hippaCodeHSD08List.add(hippaCodeValue);
		mapping.getHsd08().setName(DomainConstants.HSD08_NAME);
		mapping.getHsd08().setHippaCodeSelectedValues(hippaCodeHSD08List);

		//Adding NM1 Message Segment to mapping object-- Oct 2014 Rel
		List hippaCodeNM1MessageSegmentList = new ArrayList();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("nm1MessageSegmentId").trim());
		hippaCodeValue.setDescription(request.getParameter("nm1MessageSegmentDesc"));
		hippaCodeNM1MessageSegmentList.add(hippaCodeValue);
		mapping.getNm1MessageSegment().setName(DomainConstants.NM1_MSG_SGMNT);
		mapping.getNm1MessageSegment().setHippaCodeSelectedValues(hippaCodeNM1MessageSegmentList);
		
		 // Indicator check has added as part of SSCR19537 April04
		if(null == request.getParameter("indEB03AssnCheckBox")) {
			List hippaCodeIII02List = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("ii02Val").trim());
			hippaCodeValue.setDescription(request.getParameter("III02Desc"));
			hippaCodeIII02List.add(hippaCodeValue);
			mapping.getIi02().setHippaCodeSelectedValues(hippaCodeIII02List);
		}
		mapping.getIi02().setName(DomainConstants.III02_NAME);
		
		
		List hippaCodeAccumList = new ArrayList();
		
		if(null != request.getParameterValues("accumulator")){
			int sizeOfAccum = request.getParameterValues("accumulator").length;
			String[] Accums = new String[sizeOfAccum];
			String[] AccumDesc = request.getParameterValues("AccumDesc");
			if(AccumDesc == null){
			    AccumDesc = new String[sizeOfAccum];
			}
			for(int i=0;i<sizeOfAccum;i++){
				Accums[i] = request.getParameterValues("accumulator")[i].trim();
				hippaCodeValue = new HippaCodeValue();
				hippaCodeValue.setValue(Accums[i]);
				hippaCodeValue.setDescription(AccumDesc[i]);
				hippaCodeAccumList.add(hippaCodeValue);
			}
		}	
		hippaCodeAccumList = BxUtil.removeDuplicates(hippaCodeAccumList);
		mapping.getAccum().setName(DomainConstants.ACCUM_NAME);
		mapping.getAccum().setHippaCodeSelectedValues(hippaCodeAccumList);

		List hippaCodeUMRuleList = new ArrayList();
        if(null != request.getParameterValues("umRuleVal")){
              int sizeOfUmRule = request.getParameterValues("umRuleVal").length;
              String [] UMRule = new String[sizeOfUmRule];
              String [] UMRuleDesc = request.getParameterValues("UMRuleDesc");
              if (UMRuleDesc == null){
                    UMRuleDesc = new String[sizeOfUmRule];
              }
              for (int i = 0; i < sizeOfUmRule; i++){
                    UMRule[i] = request.getParameterValues("umRuleVal")[i].trim();
                    hippaCodeValue = new HippaCodeValue();
                    hippaCodeValue.setValue(UMRule[i]);
                    hippaCodeValue.setDescription(UMRuleDesc[i]);
                    hippaCodeUMRuleList.add(hippaCodeValue);
              }
        }
        hippaCodeUMRuleList = BxUtil.removeDuplicates(hippaCodeUMRuleList);
        mapping.getUtilizationMgmntRule().setName(DomainConstants.UMRULE_NAME);
        mapping.getUtilizationMgmntRule().setHippaCodeSelectedValues(hippaCodeUMRuleList);
		
        // Indicator check has added as part of SSCR19537 April04
        if(null == request.getParameter("indEB03AssnCheckBox")) {
        	List noteTypeList = new ArrayList();
    		hippaCodeValue = new HippaCodeValue();
    		hippaCodeValue.setValue(request.getParameter("noteType").trim());
    		hippaCodeValue.setDescription(request.getParameter("NoteTypeDesc"));
    		noteTypeList.add(hippaCodeValue);
    		mapping.getNoteTypeCode().setHippaCodeSelectedValues(noteTypeList);
    		
    		if(null!=request.getParameter("msgRqdChkBox") && request.getParameter("msgRqdChkBox").equals("true")){
    			mapping.setMsg_type_required("Y");
    		}else{
    			mapping.setMsg_type_required("N");
    		}
    		mapping.setMessage(request.getParameter("messageValue").trim());
        }
        mapping.getNoteTypeCode().setName(DomainConstants.NOTE_TYPE_CODE);
		if(null!=request.getParameter("accumNtReqdChkBox")){
			mapping.setSensitiveBenefitIndicator("Y");
		}else{
			mapping.setSensitiveBenefitIndicator("N");
		}
		mapping.setIsMapgRequired("Y");
		mapping.getVariable().setVariableId(request.getParameter("variableIdHidden"));
		mapping.getVariable().setDescription(request.getParameter("variableDescHidden"));
		mapping.getVariable().setVariableFormat(request.getParameter("variableFormat"));
		//BXNI CR29
		mapping.getVariable().setPva(request.getParameter("providerArrangement"));
		
		// setting variable category code
		mapping.getVariable().setLgCatagory(request.getParameter("lgCatagory").trim());
		mapping.getVariable().setIsgCatagory(request.getParameter("isgCatagory").trim());
		mapping.getVariable().setVariableSystem(request.getParameter("system").trim());
		mapping.getVariable().setMinorHeadings(request.getParameter("valuesOfMinorHeadings"));
		mapping.getVariable().setMajorHeadings(request.getParameter("valuesOfMajorHeadings"));
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		userComments =  request.getParameter("userComments").toUpperCase();
		/**
		 * Code change for MTM
		 */
		/**
		 * started
		 */
		if(null!=request.getParameter("notFinalizedChkBox")&& request.getParameter("notFinalizedChkBox").equalsIgnoreCase("checked")){

			mapping.setFinalized(false);
		}else  {
			mapping.setFinalized(true);
		}
		/**
		 * ended
		 */
		// Start Age Mapping for a variable -- BXNI June 2012 Release.
		List<HippaCodeValue> hippaCodeStartAgeList = new ArrayList<HippaCodeValue>();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("startAge").trim());
		hippaCodeStartAgeList.add(hippaCodeValue);
		mapping.getStartAge().setName(DomainConstants.START_AGE_NAME);
		mapping.getStartAge().setHippaCodeSelectedValues(hippaCodeStartAgeList);
		
		// End Age Mapping for a variable -- BXNI June 2012 Release.
		List<HippaCodeValue> hippaCodeEndAgeList = new ArrayList<HippaCodeValue>();
		hippaCodeValue = new HippaCodeValue();
		hippaCodeValue.setValue(request.getParameter("endAge").trim());
		hippaCodeEndAgeList.add(hippaCodeValue);
		mapping.getEndAge().setName(DomainConstants.END_AGE_NAME);
		mapping.getEndAge().setHippaCodeSelectedValues(hippaCodeEndAgeList);
		
		// CR29
		if (null != request.getParameter("excludeProceduresChkBox")	&& request.getParameter("excludeProceduresChkBox").equalsIgnoreCase("checked")) {
			mapping.setProcedureExcludedInd(DomainConstants.Y);
		} else {
			mapping.setProcedureExcludedInd(DomainConstants.N);
		}
		
		HttpSession session = request.getSession();
		// Creating a variable mapping
		List infoMessages = new ArrayList(); 
		List warningMessages = new ArrayList();
		
		// SSCR19537 April04
		createEb03AssociatedValues(request, mapping);
		if(TokenGenerator.getInstance().isTokenValid(request,true)){
		CreateOrEditMappingResult result = variableMappingFacade.createOrEditVariableMapping(mapping, userComments);//List<Mapping>
		
		if (null != result && null != result.getMapping() && null != result.getMapping().getEb03()
				&& null != result.getMapping().getEb03().getEb03Association() 
				&& !result.getMapping().getEb03().getEb03Association().isEmpty()) {
			eb03AssnList = result.getMapping().getEb03().getEb03Association();
		}
		
		//If any of the hippacode validation fails, it should show the validation messages in the Create Mapping page
		if(result.getStatus() == DomainConstants.FAILURE_STATUS){
			if(result.getStatusCodes()!=null){
				for(Iterator itr = result.getStatusCodes().iterator();itr.hasNext(); ){
					String obj = (String)itr.next();
					if(obj!=null && obj.equals(WebConstants.MAPPING_EXISTS)){
						infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_EXISTS, null));
						sessionMessageTray.setInformationMessages(infoMessages);
						if(null != pageName && pageName.trim().equals("advanceSearch")){
							return  WebUtil.redirectToWPDAdvanceSearchPage(request);
						}else{
							return WebUtil.redirectToLandingPage(request);
						}
					}
				}
			}
			if(null!= result.getErrorMsgsList() && !result.getErrorMsgsList().isEmpty() && result.getErrorMsgsList().size()>0){
				mstrErrMsgsList = result.getErrorMsgsList();
			}
			ModelAndView modelAndView = new ModelAndView("createmapping");
			result.getMapping().setPageFrom(pageName);
			modelAndView = redirectToCreateMappingPage(modelAndView, result);
			if(null != mstrErrMsgsList && !mstrErrMsgsList.isEmpty() && mstrErrMsgsList.size()>0){
				for(int i=0;i<mstrErrMsgsList.size();i++){
					if(null !=mstrErrMsgsList.get(i) && !mstrErrMsgsList.isEmpty() && mstrErrMsgsList.size()>0){
						List errMsgsList = new ArrayList();
						errMsgsList = (ArrayList)mstrErrMsgsList.get(i);
						if(null != errMsgsList && !errMsgsList.isEmpty()){
							for(int j=0;j<errMsgsList.size();j++){
								erorrMsgs = (String)errMsgsList.get(j);
								errorMessagesList.add(erorrMsgs);
							}
							
						}
						
							
					}
				}
			}
			modelAndView.addObject("error_messages", errorMessagesList);
			TokenGenerator.getInstance().saveToken(request);		
			modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
			modelAndView.addObject("eB03AssnList",eb03AssnList);
			return modelAndView;
		}
		
		
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
		infoMessages.add(BxResourceBundle.getResourceBundle("mapping.save.success", null));
		ModelAndView modelAndView = new ModelAndView("editmapping");
		result.getMapping().setPageFrom(pageName);
		modelAndView.addObject("mapping",result.getMapping());
		modelAndView.addObject("changeComments", result.getUserComments());
		modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
		modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
		String lockMsgs = "";
		if((null != result.getStatusCodes()) && !(result.getStatusCodes().isEmpty())){
			lockMsgs = (String)result.getStatusCodes().get(0);
			if(null != lockMsgs && !lockMsgs.equals("")){
				List errorMessages = new ArrayList();
				errorMessages.add(lockMsgs);				
				modelAndView = new ModelAndView("createmapping");
				result.getMapping().setPageFrom(pageName);
				modelAndView.addObject("mapping",result.getMapping());
				modelAndView.addObject("variableWithInfoList",result.getMapping().getVariableList());
				modelAndView.addObject("changeComments", result.getUserComments());
				modelAndView.addObject(WebConstants.ERROR_MESSAGES,errorMessages);
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
				modelAndView.addObject("eB03AssnList",eb03AssnList);
				return modelAndView;
			}
			
		}		

		else {	
		result.getMapping().setPageFrom(pageName);
		mapping = result.getMapping();
		List variableList = result.getMapping().getVariableList();	
		if (null != variableList && variableList.size() > 0) {
			BxUtil.encodeVariable((Variable) variableList.get(0));
		}
		if(null != mapping.getMsg_type_required() && mapping.getMsg_type_required().equals("Y")){
			
			mapping.setMsg_type_required("true");
		}
		else {
			mapping.setMsg_type_required("false");
		}
		if(null != mapping.getSensitiveBenefitIndicator() && mapping.getSensitiveBenefitIndicator().equals("Y")){
			
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
		modelAndView.addObject("variableList",variableList);
		
		}
		TokenGenerator.getInstance().saveToken(request);		
		modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
		modelAndView.addObject("eB03AssnList",eb03AssnList);
		return modelAndView;
		}
		else{
			int statusCode = variableMappingFacade.isValidVariableIDStatus(request.getParameter("variableIdHidden"));
			if(statusCode == 1){
				
				ModelAndView modelAndView = new ModelAndView("createmapping");
				List variableDetails = variableMappingFacade.viewVariableDetails(mapping.getVariable());
				modelAndView.addObject("changeComments", request.getParameter("userComments"));
				if(null != mapping.getMsg_type_required() && mapping.getMsg_type_required().equals("Y")){
					
					mapping.setMsg_type_required("true");
				}
				else if((mapping.getMsg_type_required() == null) || (mapping.getMsg_type_required().equals("N"))){
					
					mapping.setMsg_type_required("false");
				}
				if(mapping.getSensitiveBenefitIndicator().equals("Y")){
					
					mapping.setSensitiveBenefitIndicator("true");
				}
				else if((mapping.getSensitiveBenefitIndicator() == null) || mapping.getSensitiveBenefitIndicator().equals("N")){
					
					mapping.setSensitiveBenefitIndicator("false");
				}
			
				 // MTM CODE CHANGES
				 
				if(mapping.isFinalized()==false){

					mapping.setFinalized(true);
				}
				else{
					mapping.setFinalized(false);
				}
				
				//* END
				 
				mapping.setPageFrom(pageName);
				modelAndView.addObject("mapping",mapping);
				String accum = mapping.getVariable().getWpdAccumulator();
				List<String>  accumList = new ArrayList<String>();
				if (null != accum && !DomainConstants.EMPTY.equals(accum)) {
					accumList= Arrays.asList(accum.split(","));
				}
				modelAndView.addObject("variableWithInfoList",variableDetails);
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject("TRANSACTION_TOKEN_KEY", (String) session.getAttribute("TRANSACTION_TOKEN_KEY"));
				modelAndView.addObject("eB03AssnList",eb03AssnList);
				
				return modelAndView;
			}
			else{
				if(statusCode == -1){
					infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_EXISTS, null));
					sessionMessageTray.setInformationMessages(infoMessages);
					if(null != pageName && pageName.trim().equals("advanceSearch")){
						return  WebUtil.redirectToWPDAdvanceSearchPage(request);
					}else{
						return WebUtil.redirectToLandingPage(request);
					}
				}
				if(null != pageName && pageName.trim().equals("advanceSearch")){
					return WebUtil.redirectToEditPageFromAdvanceSearch(request, request.getParameter("variableIdHidden"),pageName);
				}else{
					return WebUtil.redirectToEditPage(request, request.getParameter("variableIdHidden"));
				}
			}	
		}
		
	}
	
	public ModelAndView redirectToCreateMappingPage(ModelAndView modelAndView, CreateOrEditMappingResult result){
		
		Mapping mapping = result.getMapping();
		List variableWithInfoList = result.getMapping().getVariableList();
		modelAndView.addObject("changeComments", result.getUserComments());
		if(null != mapping.getMsg_type_required() && mapping.getMsg_type_required().equals("Y")){
			
			mapping.setMsg_type_required("true");
		}
		else{
			mapping.setMsg_type_required("false");
		}
		if(mapping.getSensitiveBenefitIndicator().equals("Y")){
			
			mapping.setSensitiveBenefitIndicator("true");
		}
		else {
			mapping.setSensitiveBenefitIndicator("false");
		}
		/**
		 * mtm code
		 */
		if(mapping.isFinalized()==false){

			mapping.setFinalized(true);
		}
		else{
			mapping.setFinalized(false);
		}
		/**
		 * end
		 */
		modelAndView.addObject("mapping",mapping);
		modelAndView.addObject("variableWithInfoList",variableWithInfoList);
		   
		   return modelAndView;
		  }
	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return  WebUtil.redirectToLandingPage(request);	

	}
	public ModelAndView cancelToAdvanceSearchBxResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return  WebUtil.redirectToWPDAdvanceSearchPage(request);	

	}
	
	public ModelAndView markVariableAsNotApplicable(HttpServletRequest request, HttpServletResponse response){
		
		String pageName = request.getParameter("pageFrom");
	    String variableId = request.getParameter("variableIdHidden");
	    Variable variable = new Variable();
	    variable.setVariableId(variableId);
	    Mapping mapping = new Mapping();
	    mapping.setVariable(variable);
		
		if(null!=request.getParameter("msgRqdChkBox") && request.getParameter("msgRqdChkBox").equals("true")){
			mapping.setMsg_type_required("Y");
		}else{
			mapping.setMsg_type_required("N");
		}
		
		if(null!=request.getParameter("accumNtReqdChkBox")){
			mapping.setSensitiveBenefitIndicator("Y");
		}else{
			mapping.setSensitiveBenefitIndicator("N");
		}
		/**
		 * Code change for MTM
		 */
		if(null!=request.getParameter("notFinalizedChkBox")&& request.getParameter("notFinalizedChkBox").equalsIgnoreCase("checked")){

			mapping.setFinalized(false);
		}else  {
			mapping.setFinalized(true);
		}
		/**
		 * ended
		 */
		mapping.setIsMapgRequired("N");
		mapping.setMessage(request.getParameter("messageValue"));
		//mapping.getVariable().setVariableId(request.getParameter("variableIdHidden"));
		mapping.getVariable().setDescription(request.getParameter("variableDescHidden"));
		mapping.getVariable().setVariableFormat(request.getParameter("variableFormat"));
		userComments =  request.getParameter("userComments").toUpperCase();
		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		mapping.setInTempTable("N");
		mapping.setPageFrom(pageName);
	    CreateOrEditMappingResult result = variableMappingFacade.markVariableAsNotApplicable(mapping, userComments);
	    
	    List infoMessages = new ArrayList();	   
	    String lockMsgs = "";
		if((null != result.getStatusCodes()) && !(result.getStatusCodes().isEmpty())){
			lockMsgs = (String)result.getStatusCodes().get(0);
			if(result.getStatus() == 0 && 
					(lockMsgs.equals("MAPPING_LOCKED_ANOTHER_USER") 
							|| lockMsgs.equals("MAPPING_ALREADY_NOT_APPLiCABLE"))
				) {
				String lockFailure = BxResourceBundle.getResourceBundle(lockMsgs, null);		
				infoMessages.add(lockFailure);
				sessionMessageTray.setInformationMessages(infoMessages);
				if(null != pageName && pageName.trim().equals("advanceSearch")){
					return  WebUtil.redirectToWPDAdvanceSearchPage(request);
				}else{
					return WebUtil.redirectToLandingPage(request);
				}
			}		
		}else if(result.getStatus() == 1){
			String info = BxResourceBundle.getResourceBundle("mapping.notApplicable", null);
			infoMessages.add(info);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if(null != pageName && pageName.trim().equals("advanceSearch")){
			return  WebUtil.redirectToWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToLandingPage(request);
		}
	
	}
	
	public ModelAndView copyTo(HttpServletRequest request,
			HttpServletResponse response) throws Exception{		
		
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		String pageName = request.getParameter("pageFrom");
		
		String selectedVariableIdToCopy = request.getParameter("selectedvariableIdCopyTo");
		String sourceVariableId = request.getParameter("selectedvariableId");
		ModelAndView modelAndView = new ModelAndView("createmapping");
		Variable variableToBeCopied = new Variable();
		
		variableToBeCopied.setVariableId(selectedVariableIdToCopy);
				
		List variableWithInfoList = variableMappingFacade.viewVariableDetails(variableToBeCopied);
		
		Mapping retrievedMapping = variableMappingFacade.retrieveMapping(sourceVariableId);
		retrievedMapping.setVariableList(variableWithInfoList);
		if (null != retrievedMapping && null != retrievedMapping.getEb03()
				&& null != retrievedMapping.getEb03().getEb03Association() 
				&& !retrievedMapping.getEb03().getEb03Association().isEmpty()) {
			eb03AssnList = retrievedMapping.getEb03().getEb03Association();
		}
		if((null != retrievedMapping.getMsg_type_required()) && retrievedMapping.getMsg_type_required().equals("Y")){
			
			retrievedMapping.setMsg_type_required("true");
		}
		else {
			retrievedMapping.setMsg_type_required("false");
		}
		if((null != retrievedMapping.getSensitiveBenefitIndicator()) && retrievedMapping.getSensitiveBenefitIndicator().equals("Y")){
			
			retrievedMapping.setSensitiveBenefitIndicator("true");
		}
		else {
			retrievedMapping.setSensitiveBenefitIndicator("false");
		}
		/**
		 * mtm code change
		 */
		if(retrievedMapping.isFinalized()==false){

			retrievedMapping.setFinalized(true);
		}
		else{
			retrievedMapping.setFinalized(false);
		}
		/**
		 * end
		 */
		retrievedMapping.setPageFrom(pageName);
		modelAndView.addObject("mapping",retrievedMapping);
		modelAndView.addObject("variableWithInfoList",retrievedMapping.getVariableList());
		modelAndView.addObject("eB03AssnList",eb03AssnList);
		
		return modelAndView;
		}
	
	
	/**
	 * Controller menthod for listing existing message texts
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView existingMsgTexts(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		String message = request.getParameter("message");
		String eb03 = request.getParameter("eb03");
		String variableId = request.getParameter("variableId");
		String currentPage = request.getParameter("currentPage");
		String section = request.getParameter("section");
		
		logger.debug("message " + message);
		logger.debug("variableId " + variableId);
		logger.debug("eb03 " + eb03);
		logger.debug("currentPage " + currentPage);
		logger.debug("section " + section);
		
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setEB03(eb03);
		searchCriteria.setVariableId(variableId);
		searchCriteria.setMessageText(message);
		//BXNI CHANGE
		if("FALSE".equalsIgnoreCase(request.getParameter("showOnlyStandardMessages"))){

			searchCriteria.setShowOnlyStandardMessages(false);
		}else {
			searchCriteria.setShowOnlyStandardMessages(true);
		}
		
		if(!BxUtil.hasText(eb03) && !BxUtil.hasText(variableId) && !BxUtil.hasText(message) && !searchCriteria.getShowOnlyStandardMesages()){
			return getSearchMessageResultView(getPage(currentPage, section, 1), new ArrayList());
		}
		
		
		int totalNoOfRecords = messageTxtSearchFacade.getSearchRecordCount(searchCriteria);
		
		Page page = getPage(currentPage, section, totalNoOfRecords);
		return getSearchMessageResultView(page, messageTxtSearchFacade.getSearchRecords(searchCriteria, 50, page));
	}
	
	/**
	 * Method reads the sensitive benefits from errorvalidator.properties property file 
	 * and returns the sensitive benefits list
	 * BXNI June2012 Release
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView populateSensitiveBeenfitsList(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		List sensitiveBenefitsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SENSITIVE_EB03,
				DomainConstants.PROPERTY_FILE_NAME);
		map.put(DomainConstants.SENSITIVE, sensitiveBenefitsList);
		return new ModelAndView(DomainConstants.JSON_VIEW,map);
	}
	
	/**
	 * Method returns view for message text search result.
	 * @param page
	 * @param list
	 * @return
	 */
	private ModelAndView getSearchMessageResultView(Page page,List list){
		Map map = new HashMap();
		map.put("searchResults", list);
		map.put("page", page);
		ModelAndView modelAndView = new ModelAndView("viewMessageSearch",map);
		return modelAndView;
	}
	
	/**
	 * Method returs page based on the selection in pagination.
	 * @param currentPage
	 * @param section
	 * @param totalNoOfRecords
	 * @return
	 */
	private Page getPage(String currentPage,String section, int totalNoOfRecords){
		if(totalNoOfRecords == 0){
			totalNoOfRecords = 1;
		}
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalNoOfRecords(totalNoOfRecords);
		
		if (section.equalsIgnoreCase("Next")) {
			if (page.getCurrentPage() >= 0
					&& page.getCurrentPage() < page.getTotalNoOfPages()) {
				page.setCurrentPage(page.getCurrentPage() + 1);
			}
		}

		if (section.equalsIgnoreCase("Previous")) {
			if (page.getCurrentPage() > 1) {
				page.setCurrentPage(page.getCurrentPage() - 1);
			}
		}
		if (section.equalsIgnoreCase("Last")) {
			page.setCurrentPage(page.getTotalNoOfPages());
		}
		if (section.equalsIgnoreCase("First")) {
			page.setCurrentPage((page.getTotalNoOfPages() / page
					.getTotalNoOfPages()));
		}
		
		return page;
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
	
	public AdvanceSearchFacade getMessageTxtSearchFacade() {
		return messageTxtSearchFacade;
	}
	
	public void setMessageTxtSearchFacade(AdvanceSearchFacade messageTxtSearchFacade) {
		this.messageTxtSearchFacade = messageTxtSearchFacade;
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
	 * Method to create the EB03 associated values from the page.
	 * @param request
	 * @param mapping
	 */
	private void createEb03AssociatedValues(HttpServletRequest request,
			Mapping mapping) {
		
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		List<ExtendedMessageMapping> extndMsgEB03List = new ArrayList<ExtendedMessageMapping>();
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
								if(eb03.equals(eb03AssnjsonObject.getString(DomainConstants.EB03_NAME).trim())){
									ExtendedMessageMapping extndMsg = new ExtendedMessageMapping();
									//extndMsg.setExtendedMsgValueSysId(setHippaCodeValueSysId(jsonObject.getString("eb03ExtndMsgSysId")));
									extndMsg.setNetworkInd(jsonObject.getString("eb03NetworkInd"));
									extndMsg.setValue(jsonObject.getString("eb03Val"));										
									extndMsg.setExtndMsg1(jsonObject.getString("eb03ExtndMessage1"));
									extndMsg.setExtndMsg2(jsonObject.getString("eb03ExtndMessage2"));
									extndMsg.setExtndMsg3(jsonObject.getString("eb03ExtndMessage3"));
									extndMsgEB03List.add(extndMsg);
									break;
								}
							}
						}
					}
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
	 * Returns the Extended Messages Screen 
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView object
	 * @throws Exception
	 */
	public ModelAndView showExtndMessagesScreen(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("createExtndMessagesScreen");		
		modelAndView.addObject("variableId", request.getParameter("variableId"));		
		return modelAndView;
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
		String messageText = "";
		if (null != request.getParameter("messageValue")) {
			messageText = request.getParameter("messageValue").trim();
		}
		
		// Get message required indicator.
		String msgRqdInd = DomainConstants.N;
		if(null != request.getParameter("msgRqdChkBox") && request.getParameter("msgRqdChkBox").equals("true")){
			msgRqdInd = DomainConstants.Y;
		}
		
		// Get Note Type.
		String noteType = "";
		if (null != request.getParameter("noteType")) {
			noteType = request.getParameter("noteType").trim();
		}
		String noteTypeDesc = "";
		if (null != request.getParameter("NoteTypeDesc")) {
			noteTypeDesc = request.getParameter("NoteTypeDesc").trim();
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
					final String messageText =  BxUtil.removeEscapedCharacters(jsonObject.getString(DomainConstants.MESSAGE));
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
	
	// SSCR 19537 April04 - End
}
