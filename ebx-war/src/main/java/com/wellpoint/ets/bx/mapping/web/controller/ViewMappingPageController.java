/*
 * Created on May 21, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.LocateFacade;
import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ViewMappingPageController extends MultiActionController{
	
		private VariableMappingFacade variableMappingFacade;
		private LocateFacade locateFacade;
		private Integer auditInfoLimit;
		
		public ModelAndView viewMapping(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			String variableId = request.getParameter("variableId");
			
			Mapping currentMapping= variableMappingFacade.viewMapping(variableId, auditInfoLimit);
			if(currentMapping.getMessage() != null){
				String message = BxUtil.appendBreak(currentMapping.getMessage(),10);
				currentMapping.setMessage(message);
			}
			BxUtil.encodeMapping(currentMapping);
			User user = new User();
			String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
			user.setLastUpdatedUserName(userId);
			user.setCreatedUserName(userId);
			currentMapping.setUser(user);
			List auditTrailList = currentMapping.getAuditTrails();
			List currentViewVariable = currentMapping.getVariableList();
			List hpnMapgList = currentMapping.getHpnMapgList();
			// START-Code change as part of BXNI CR35
			List<String>  accumList = null;
			Variable newVariable;
			newVariable=(Variable) currentViewVariable.get(0);
			String accum=newVariable.getWpdAccumulator();
			if(accum !=null  && accum !=""){
			 
			  accumList= Arrays.asList(accum.split(","));
			}
			// END-Code change as part of BXNI CR35
			//Start:- SSCR15937
			List<EB03Association> eb03Associations = null;
			if(null != currentMapping.getEb03() && null!= currentMapping.getEb03().getEb03Association()) {
				eb03Associations = currentMapping.getEb03().getEb03Association();
			}
			
			//End:- SSCR15937
			if (null != currentViewVariable && currentViewVariable.size() > 0) {
				BxUtil.encodeVariable((Variable) currentViewVariable.get(0));
			}
			ModelAndView modelAndView = new ModelAndView("mappingview");
		    modelAndView.addObject("accumList",accumList);
		    modelAndView.addObject("eb03Associations",eb03Associations);
			/**
			 * MTM CODE CHANGE
			 */boolean checkNotFinalized=currentMapping.isFinalized();
			 if(checkNotFinalized){
				 currentMapping.setMappingComplete("Y");
			 }
			 else{
				 currentMapping.setMappingComplete("N");
			 }
			 /**
			  * END
			  */
			 
			 String comaSeperatedStartAge = "";
			if(null != currentMapping.getStartAge() && null!= currentMapping.getStartAge().getHippaCodeSelectedValues()){
				
				HippaCodeValue hippaCodeValue = (HippaCodeValue) currentMapping.getStartAge().getHippaCodeSelectedValues().get(0);
				
				comaSeperatedStartAge  = hippaCodeValue.getValue();
				comaSeperatedStartAge = comaSeperatedStartAge.replaceAll(",", ", ");
				hippaCodeValue.setValue(comaSeperatedStartAge);

			}
			
			 String comaSeperatedEndAge = "";
				if(null != currentMapping.getEndAge() && null!= currentMapping.getEndAge().getHippaCodeSelectedValues()){
					
					HippaCodeValue hippaCodeValue = (HippaCodeValue) currentMapping.getEndAge().getHippaCodeSelectedValues().get(0);
					
					comaSeperatedEndAge  = hippaCodeValue.getValue();
					comaSeperatedEndAge = comaSeperatedEndAge.replaceAll(",", ", ");
					hippaCodeValue.setValue(comaSeperatedEndAge);

				}
			
			modelAndView.addObject("currentMapping", currentMapping);
			modelAndView.addObject("auditTrailList",auditTrailList);
			Integer auditTrailCount = 0;
			if (null != auditTrailList) {
				auditTrailCount = auditTrailList.size();
			}
			
			modelAndView.addObject("sizeOfAuditTrail",auditTrailCount);
			modelAndView.addObject("currentViewVariable",currentViewVariable);
			modelAndView.addObject("hpnMapgList",hpnMapgList);
			
			request.getParameterNames();
			return modelAndView;
		}
		
		public ModelAndView viewUnmappedVariable(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			String variableId = request.getParameter("variableId");
		
			Variable variable = new Variable();
			variable.setVariableId(variableId);
			List currentViewVariable  = variableMappingFacade.viewVariableDetails(variable);			
			// START-Code change as part of BXNI CR35
			List<String>  accumList = null;
			Variable newVariable;
			newVariable=(Variable) currentViewVariable.get(0);
			String accum=newVariable.getWpdAccumulator();
			if(accum !=null  && accum !=""){
			 
			  accumList= Arrays.asList(accum.split(","));
			}
			// END-Code change as part of BXNI CR35
			ModelAndView modelAndView = new ModelAndView("mappingview");
			modelAndView.addObject("accumList",accumList);
			modelAndView.addObject("currentViewVariable",currentViewVariable);
			return modelAndView;
		}	
		
		public ModelAndView printMapping(HttpServletRequest request,
				HttpServletResponse response) throws EBXException{
			Mapping currentMapping = null;
			String currentPage = request.getParameter("currentpage");
			String variableId = request.getParameter("variableId");
			String mappingstatus = request.getParameter("mappingstatus");
			List currentViewVariable = new ArrayList();
			List hpnMapgList = new ArrayList();

			if (null != currentPage && currentPage.equalsIgnoreCase("create")) {
				Variable variable = new Variable();
				variable.setVariableId(variableId);
				currentViewVariable  = variableMappingFacade.viewVariableDetails(variable);
				currentMapping = createMappingObject(request);
				currentMapping.setVariableList(currentViewVariable);
				
				
			}
			else if (null != currentPage && currentPage.equalsIgnoreCase("edit")) {
				currentMapping = createMappingObject(request);
				Mapping savedMapping = variableMappingFacade.viewMapping(variableId, auditInfoLimit);
				currentMapping.setAuditTrails(savedMapping.getAuditTrails());
				currentMapping.setVariableList(savedMapping.getVariableList());
				currentMapping.setProcedureExcludedInd(savedMapping.getProcedureExcludedInd());
				//added for extd message will show only saved records
				currentMapping.setEb01(savedMapping.getEb01());
				currentMapping.setEb03(savedMapping.getEb03());
				
			}
			else {
				if(null != mappingstatus) {
					currentMapping= variableMappingFacade.viewMapping(variableId, auditInfoLimit);
					hpnMapgList=currentMapping.getHpnMapgList();
				}
				else {					
					Variable variable = new Variable();
					variable.setVariableId(variableId);
					currentViewVariable  = variableMappingFacade.viewVariableDetails(variable);
					
					
				}
			}
			if(null != currentMapping && currentMapping.getMessage() != null){
				String message = BxUtil.appendBreak(currentMapping.getMessage(),10);
				currentMapping.setMessage(message);
			}	
			ModelAndView modelAndView = new ModelAndView("printVariableMapping");
			List auditTrailList = new ArrayList();
			if (null != currentMapping) {
				auditTrailList = currentMapping.getAuditTrails();
				currentViewVariable = currentMapping.getVariableList();
				boolean checkNotFinalized = currentMapping.isFinalized();
				if (checkNotFinalized) {
					currentMapping.setMappingComplete("Y");
				} else {
					currentMapping.setMappingComplete("N");
				}
			}
			 
			 String comaSeperatedStartAge = "";
			 if (null != currentMapping) {
				if(null != currentMapping.getStartAge() && null!= currentMapping.getStartAge().getHippaCodeSelectedValues()
						&& currentMapping.getStartAge().getHippaCodeSelectedValues().size()!=0){
					
					HippaCodeValue hippaCodeValue = (HippaCodeValue) currentMapping.getStartAge().getHippaCodeSelectedValues().get(0);
					
					comaSeperatedStartAge  = hippaCodeValue.getValue();
					comaSeperatedStartAge = comaSeperatedStartAge.replaceAll(",", ", ");
					hippaCodeValue.setValue(comaSeperatedStartAge);

				}
			 }
			 String comaSeperatedEndAge = "";
			 if (null != currentMapping) {
				if(null != currentMapping.getEndAge() && null!= currentMapping.getEndAge().getHippaCodeSelectedValues()
						&& currentMapping.getEndAge().getHippaCodeSelectedValues().size()!=0){
					
					HippaCodeValue hippaCodeValue = (HippaCodeValue) currentMapping.getEndAge().getHippaCodeSelectedValues().get(0);
					
					comaSeperatedEndAge  = hippaCodeValue.getValue();
					comaSeperatedEndAge = comaSeperatedEndAge.replaceAll(",", ", ");
					hippaCodeValue.setValue(comaSeperatedEndAge);

				}
			 }
			 // SSCR19537 Change
			 Map<String,String> eb03ValDescMap = locateFacade.fetchHippaSegmentDescription(DomainConstants.EB03_NAME);
			 List <EB03Association> eb03AssnList = new ArrayList<EB03Association>();
			 if(null != currentMapping && null != currentMapping.getEb03() && null != currentMapping.getEb03().getEb03Association()){
				 eb03AssnList = currentMapping.getEb03().getEb03Association();
			 }
			 setDescription(eb03AssnList, eb03ValDescMap);
			 modelAndView.addObject("eb03Associations", eb03AssnList);
			 
			 modelAndView.addObject("currentMapping", currentMapping);
			 modelAndView.addObject("auditTrailList",auditTrailList);
			 Integer auditTrailCount = 0;
			 if (null != auditTrailList) {
				auditTrailCount = auditTrailList.size();
			 }
			 modelAndView.addObject("sizeOfAuditTrail",auditTrailCount);
			 modelAndView.addObject("currentViewVariable",currentViewVariable);
			 // START-Code change as part of BXNI CR35
			 List<String>  accumList = null;
			 Variable newVariable;
			 newVariable=(Variable) currentViewVariable.get(0);
			 String accum=newVariable.getWpdAccumulator();
			 if(accum !=null  && accum !=""){
			 
				 accumList= Arrays.asList(accum.split(","));
			 }
			 modelAndView.addObject("accumList",accumList);
			 modelAndView.addObject("hpnMapgList",hpnMapgList);
			 // END-Code change as part of BXNI CR35
			 request.getParameterNames();
			
			 return modelAndView;
		}
		
		private void setDescription(List<EB03Association> eb03AssnList,
				Map<String, String> eb03ValDescMap) {
			
			for (EB03Association eb03Assn : eb03AssnList) {
				if (null != eb03Assn && null != eb03Assn.getEb03()) {
					String eb03Val = eb03Assn.getEb03().getValue();
					String eb03Desc = eb03ValDescMap.get(eb03Val);
					eb03Assn.getEb03().setDescription(eb03Desc);
				}
			}
			
		}

		private Mapping createMappingObject(HttpServletRequest request) {
			Mapping mapping = new Mapping();
			Variable variable = new Variable();
			
			HippaCodeValue hippaCodeValue;
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
			mapping.getEb01().setName(DomainConstants.EB01_NAME);
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("eb01Val").trim());
			hippaCodeValue.setDescription(request.getParameter("EB01Desc"));
			hippaCodeEB01List.add(hippaCodeValue);
			mapping.getEb01().setHippaCodeSelectedValues(hippaCodeEB01List);
			
			List hippaCodeEB02List = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("eb02Val").trim());
			hippaCodeValue.setDescription(request.getParameter("EB02Desc"));
			hippaCodeEB02List.add(hippaCodeValue);
			mapping.getEb02().setName(DomainConstants.EB02_NAME);
			mapping.getEb02().setHippaCodeSelectedValues(hippaCodeEB02List);
			
			List hippaCodeEB03List = new ArrayList();
			if (null != request.getParameter("eb03String")) {
			StringTokenizer st = new StringTokenizer(request.getParameter("eb03String"),"**");
			StringTokenizer st1 = new StringTokenizer(request.getParameter("eb03DescString"),"**");
		     while (st.hasMoreTokens()) {
		    	 String eb03Val =  st.nextToken();
		         hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(eb03Val);
					hippaCodeValue.setDescription(st1.nextToken());
					hippaCodeEB03List.add(hippaCodeValue);
		     }
			}
			
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
			if(null != request.getParameter("hsd02ValueString")){
				StringTokenizer st = new StringTokenizer(request.getParameter("hsd02ValueString"),"**");
				while (st.hasMoreTokens()){
					String hsd02Val = st.nextToken();
					hippaCodeValue = new HippaCodeValue();
					hippaCodeValue.setValue(hsd02Val);
					hippaCodeHSD02List.add(hippaCodeValue);
				}
			}
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
			
			//Adding NM1 Message Segment to mapping object - Oct 2014
			List hippaCodeNM1MessageSegmentList = new ArrayList();
			hippaCodeValue = new HippaCodeValue();
			hippaCodeValue.setValue(request.getParameter("nm1MessageSegment").trim());
			hippaCodeValue.setDescription(request.getParameter("nm1MessageSegmentDesc"));
			hippaCodeNM1MessageSegmentList.add(hippaCodeValue);
			mapping.getNm1MessageSegment().setName(DomainConstants.NM1_MSG_SGMNT);
			mapping.getNm1MessageSegment().setHippaCodeSelectedValues(hippaCodeNM1MessageSegmentList);
			
			
			// Condition added as part of SSCR19537
			if(null == request.getParameter("indEB03AssnCheckBox") 
					|| request.getParameter("indEB03AssnCheckBox").equals("")) {
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
			
	     // Condition added as part of SSCR19537
			if(null == request.getParameter("indEB03AssnCheckBox") 
					|| request.getParameter("indEB03AssnCheckBox").equals("")) {
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
			
			if(null!=request.getParameter("accumNtReqdChkBox") && request.getParameter("accumNtReqdChkBox").equals("true")){
				mapping.setSensitiveBenefitIndicator("Y");
			}else{
				mapping.setSensitiveBenefitIndicator("N");
			}
			mapping.setIsMapgRequired("Y");
			mapping.getVariable().setVariableId(request.getParameter("variableIdHidden"));
			mapping.getVariable().setDescription(request.getParameter("variableDescHidden"));
			mapping.getVariable().setVariableFormat(request.getParameter("variableFormat"));
			
			// setting variable category code
			mapping.getVariable().setLgCatagory(request.getParameter("lgCatagory").trim());
			mapping.getVariable().setIsgCatagory(request.getParameter("isgCatagory").trim());
			mapping.getVariable().setVariableSystem(request.getParameter("system").trim());
			
			String userId = request.getAttribute(SecurityConstants.USER_NAME)
			.toString();
			user.setCreatedUserName(userId);
			mapping.setUser(user);
			
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
			
			// Start Age Mapping for a variable -- BXNI June 2012 Release.
			List<HippaCodeValue> hippaCodeStartAgeList = new ArrayList<HippaCodeValue>();
			hippaCodeValue = new HippaCodeValue();
			if(null!=request.getParameter("startAge")){
			hippaCodeValue.setValue(request.getParameter("startAge").trim());
			hippaCodeStartAgeList.add(hippaCodeValue);
			mapping.getStartAge().setName(DomainConstants.START_AGE_NAME);
			mapping.getStartAge().setHippaCodeSelectedValues(hippaCodeStartAgeList);
			}
			// End Age Mapping for a variable -- BXNI June 2012 Release.
		
			List<HippaCodeValue> hippaCodeEndAgeList = new ArrayList<HippaCodeValue>();
			hippaCodeValue = new HippaCodeValue();
			if(null!=request.getParameter("endAge")){
			hippaCodeValue.setValue(request.getParameter("endAge").trim());
			hippaCodeEndAgeList.add(hippaCodeValue);
			mapping.getEndAge().setName(DomainConstants.END_AGE_NAME);
			mapping.getEndAge().setHippaCodeSelectedValues(hippaCodeEndAgeList);
			}
			// SSCR19537 April04
			createEb03AssociatedValues(request, mapping);
			
			return mapping;
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

		public Integer getAuditInfoLimit() {
			return auditInfoLimit;
		}

		public void setAuditInfoLimit(Integer auditInfoLimit) {
			this.auditInfoLimit = auditInfoLimit;
		}
		
		
		public LocateFacade getLocateFacade() {
			return locateFacade;
		}

		public void setLocateFacade(LocateFacade locateFacade) {
			this.locateFacade = locateFacade;
		}

		// SSCR19537 April04- Start
		/**
		 * Method to create the EB03 associated values from the page.
		 * @param request
		 * @param mapping
		 */
		private void createEb03AssociatedValues(HttpServletRequest request,
				Mapping mapping) {
			
			List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
			
			if(null != request.getParameter("indEB03AssnCheckBox") 
					&& request.getParameter("indEB03AssnCheckBox").equalsIgnoreCase("on")){
				
				Map<String, String> eb03ValDescMap = new HashMap<String, String>();
				String jsonArray = request.getParameter("eb03AssnJsonObj");
				String eb03String = null;
				String eb03DescString = null;
				if (null != request.getParameter("eb03String")) {
					eb03String = request.getParameter("eb03String").trim();
				}
				if (null != request.getParameter("eb03String")) {
					eb03DescString = request.getParameter("eb03DescString").trim();
				}
				
				eb03ValDescMap = createEb03ValDescPair(eb03String, eb03DescString);
				
				if (null != jsonArray && !DomainConstants.EMPTY.equals(jsonArray)) {
					eb03AssnList = createEB03AssociationObject(jsonArray, eb03ValDescMap);
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
			if (null != request.getParameter("eb03String")) {
				StringTokenizer st = new StringTokenizer(request.getParameter("eb03String"),"**");
				StringTokenizer st1 = new StringTokenizer(request.getParameter("eb03DescString"),"**");
				while (st.hasMoreTokens()) {
			    	String eb03Val = st.nextToken();
					HippaCodeValue eb03HippaCodeValue = new HippaCodeValue();
					eb03HippaCodeValue.setValue(eb03Val);
					eb03HippaCodeValue.setDescription(st1.nextToken());
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
			String messageText = request.getParameter("messageValue").trim();
			
			// Get message required indicator.
			String msgRqdInd = DomainConstants.N;
			if(null != request.getParameter("msgRqdChkBox") && request.getParameter("msgRqdChkBox").equals("on")){
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
		 * @param eb03ValDescMap 
		 * @return
		 */
		private List<EB03Association> createEB03AssociationObject(String jsonArray, Map<String, String> eb03ValDescMap) {

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
						final String noteTypeDesc =  BxUtil.removeEscapedCharacters(jsonObject.getString(DomainConstants.NOTE_TYPE_DESC));
						
						// Convert the String Value to HippaCode Value.
						HippaCodeValue eb03Value = null;
						HippaCodeValue iii02Value = null;
						HippaCodeValue noteTypeCodeValue = null;
						
						if (null != eb03) {
							String eb03Desc = eb03ValDescMap.get(eb03.trim());
							eb03Value = BxUtil.covertToHippaCodeValue(eb03.trim(), eb03Desc);
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
		

		private Map<String, String> createEb03ValDescPair(String eb03String, String eb03DescString) {
			Map<String, String> eb03ValDescMap = new java.util.HashMap<String, String>();
			if (null != eb03String && null != eb03DescString) {
				StringTokenizer value = new StringTokenizer(eb03String,"**");
				StringTokenizer decription = new StringTokenizer(eb03DescString,"**");
				while (value.hasMoreTokens()) {
			    	String eb03Val = value.nextToken();
			    	String eb03Desc = decription.nextToken();
			    	eb03ValDescMap.put(eb03Val, eb03Desc);
				}
			}
			return eb03ValDescMap;
		}
		
		// SSCR 19537 - April 04 - End
}

