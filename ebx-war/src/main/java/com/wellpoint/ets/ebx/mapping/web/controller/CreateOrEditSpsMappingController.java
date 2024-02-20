package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSDetail;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.TokenGenerator;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.mapping.application.MappingFacade;

public class CreateOrEditSpsMappingController extends MultiActionController{
	
	private MappingFacade mappingSpsIdFacade; 
	private SessionMessageTray sessionMessageTray;
	private LocateFacade locateSpsIdFacade;   
  
	
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		List infoMessages = new ArrayList();
		Mapping mapping = createMappingObject(request);
		String userComments = request.getParameter(WebConstants.USER_COMNTS);
		HttpSession session = request.getSession();
		
		List warningMessages = new ArrayList();
				
		if(TokenGenerator.getInstance().isTokenValid(request,true)){
			MappingResult result = mappingSpsIdFacade.create(mapping, userComments);

			if(result.isStatus()){		
				if(null!= result.getWarningMsgsList()){
					
					warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
				}
				infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SAVE_SUCCESS, null));
				ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
				result.getMapping().setPageFrom(pageName);
				modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS, userComments);
				modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
				modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
				return modelAndView;
			}
			else{
				List errorMessages = new ArrayList();
				ModelAndView modelAndView = new ModelAndView("createspsmapping");
				
				if(null!= result.getErrorMsgsList()){
					
					errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				}
				result.getMapping().setPageFrom(pageName);
				modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS, userComments);
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
				
				return modelAndView;
			}
		}
		else{
			if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
				return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
			}else{
				return  WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}

	}
	public ModelAndView saveMapping(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		List infoMessages = new ArrayList();  
		Mapping mapping = createMappingObject(request);
		String userComments = request.getParameter(WebConstants.USER_COMNTS);
		HttpSession session = request.getSession();
		List warningMessages = new ArrayList();
		
		if(TokenGenerator.getInstance().isTokenValid(request,true)){
			MappingResult result = mappingSpsIdFacade.update(mapping, userComments);
			
			if(result.isStatus()){		
				if(null!= result.getWarningMsgsList()){
					
					warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
				}
				infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SAVE_SUCCESS, null));
				ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
				result.getMapping().setPageFrom(pageName);
				modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS, userComments);
				modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessages);
				modelAndView.addObject(WebConstants.WARNING_MESSAGES, warningMessages);
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
				return modelAndView;
			}
			else{
				List errorMessages = new ArrayList();				
				
				if(null!= result.getErrorMsgsList()){
					
					errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
				}
				ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
				result.getMapping().setPageFrom(pageName);
				modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS, userComments);
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);				
				TokenGenerator.getInstance().saveToken(request);		
				modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
				return modelAndView;
			}
		}
		else{
			if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
				return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
			}else{
				return  WebUtil.redirectToeWPDBXLandingPage(request);
			}
		}
		
	}
	public ModelAndView done(HttpServletRequest request,
			HttpServletResponse response) {
		List infoMessages = new ArrayList();  
		Mapping mapping = createMappingObject(request);
		String userComments = request.getParameter(WebConstants.USER_COMNTS);
		String pageName = request.getParameter("pageFrom");
		
		HttpSession session = request.getSession();
		List warningMessages = new ArrayList();
		
		MappingResult result = mappingSpsIdFacade.update(mapping, userComments);
		
		if(result.isStatus()){		
			if(null!= result.getWarningMsgsList()){
				
				warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
			}
			infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SAVE_SUCCESS, null));
			
			sessionMessageTray.setInformationMessages(infoMessages);
			sessionMessageTray.setWarningMessages(warningMessages);
		}
		else{
			List errorMessages = new ArrayList();				
			
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			
			ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
			result.getMapping().setPageFrom(pageName);
			modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
			modelAndView.addObject(WebConstants.CHANGE_COMMENTS, userComments);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);			
			TokenGenerator.getInstance().saveToken(request);		
			modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
			return modelAndView;
			
		} 
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return  WebUtil.redirectToeWPDBXLandingPage(request);
		}
		
	}
	public ModelAndView copyToMapping(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		ModelAndView modelAndView = new ModelAndView("createspsmapping");
		HttpSession session = request.getSession();
		Mapping mapping = new Mapping();		
		SPSId newSpsId = new SPSId();
		newSpsId.setSpsId(request.getParameter("selectedSpsIdCopyTo"));		
		mapping.setSpsId(newSpsId);
		
		Mapping mappingToBeCopied = new Mapping();
		SPSId previousSpsId = new SPSId();
		previousSpsId.setSpsId(request.getParameter("selectedSpsId"));
		mappingToBeCopied.setSpsId(previousSpsId);	
		
		
		List status = new ArrayList();
		
		status.add("UNMAPPED");
		
		List mappings = locateSpsIdFacade.getRecords(mappingToBeCopied, null, null, -1, 21, null);
		List newMapping = locateSpsIdFacade.getRecords(mapping, status, null, -1, 21, null);
		
		if(null != newMapping && !newMapping.isEmpty()){	 		
			mapping = (Mapping) newMapping.get(0);
			newSpsId = mapping.getSpsId();
		}
		if(null != mappings && !mappings.isEmpty()){
			mappingToBeCopied = (Mapping) mappings.get(0);
			mappingToBeCopied.setSpsId(newSpsId);
			mappingToBeCopied.setPageFrom(pageName);
			if(null != mappingToBeCopied.getAuditTrails() && (!mappingToBeCopied.getAuditTrails().isEmpty())){
				AuditTrail  auditTrail = (AuditTrail) mappingToBeCopied.getAuditTrails().get(0);				
				String changeComments = auditTrail.getUserComments();
				modelAndView.addObject(WebConstants.CHANGE_COMMENTS, changeComments);
			}
			mappingToBeCopied.setPageFrom(pageName);
			modelAndView.addObject(WebConstants.MAPPING,mappingToBeCopied);

			TokenGenerator.getInstance().saveToken(request);		
			modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));

		}
		return modelAndView;
	}
	public ModelAndView editSPSMapping(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String pageName =null;
		
		Mapping mapping = new Mapping();
		User user = new User();
		SPSId spsId = new SPSId();
		spsId.setSpsId(request.getParameter("selectedSpsIdForEdit"));
		mapping.setSpsId(spsId);
		
		pageName = request.getParameter("pageName");
		if(null!=pageName)
			mapping.setPageFrom(pageName);
		
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		
		ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
		TokenGenerator.getInstance().saveToken(request);		
		modelAndView.addObject(WebConstants.TRANSACTION_TOKEN_KEY, (String) session.getAttribute(WebConstants.TRANSACTION_TOKEN_KEY));
		
		//List mappings = locateSpsIdFacade.getRecords(mapping, null, null, -1, 21, null);
		MappingResult mappingResult = locateSpsIdFacade.getRecordsForUpdate(mapping, null, null, -1, 21, null);

		//if(null != mappings && !mappings.isEmpty()){			
			//if(null != mappings.get(0)){	
		//mapping = (Mapping) mappings.get(0);
		if (mappingResult != null) {
			if (!mappingResult.isStatus()) {
				if(null!= mappingResult.getErrorMsgsList()){        					
					List errorMessages = BxUtil.getMessages(mappingResult.getErrorMsgsList());
					sessionMessageTray.setErrorMessages(errorMessages);
					if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
						return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
					}else{
						return WebUtil.redirectToeWPDBXLandingPage(request);
					}
				}
			}
				mapping = (Mapping) mappingResult.getMapping();	
				
				
				user.setLastUpdatedUserName(userId);
				user.setCreatedUserName(userId);
				mapping.setUser(user);
				if(null!=pageName)
					mapping.setPageFrom(pageName);
				
				if(mapping.getVariableMappingStatus().equals(DomainConstants.STATUS_PUBLISHED)){
					
					MappingResult result= locateSpsIdFacade.getRecordsForUpdate(mapping);
					if(!result.isStatus()){            			
						if(null!= result.getErrorMsgsList()){        					
							List errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
							sessionMessageTray.setErrorMessages(errorMessages);
							if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
								return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
							}else{
								return WebUtil.redirectToeWPDBXLandingPage(request);
							}
						}
            		}
				}
				
				modelAndView.addObject(WebConstants.MAPPING, mapping);
				
				if(null != mapping.getAuditTrails() && (!mapping.getAuditTrails().isEmpty())){
					AuditTrail auditTrail = (AuditTrail) mapping.getAuditTrails().get(0);				
					String changeComments = auditTrail.getUserComments();
					modelAndView.addObject(WebConstants.CHANGE_COMMENTS, changeComments);
				}
		}
			//}
		//}	
		return 	modelAndView;	
	}
	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response) {
		deleteLockDuringCancel(request);
		return  WebUtil.redirectToeWPDBXLandingPage(request);
	}
	public ModelAndView cancelToAdvanceSearchPage(HttpServletRequest request,
			HttpServletResponse response) {
		deleteLockDuringCancel(request);
		return  WebUtil.redirectToeWPDAdvanceSearchPage(request);		
	}
	
	public ModelAndView notApplicable(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		Mapping mapping = createMappingObject(request);
		List infoMessages = new ArrayList(); 
		mapping.setVariableMappingStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		String changeComments = request.getParameter(WebConstants.USER_COMNTS).toUpperCase();
		
		MappingResult result =  mappingSpsIdFacade.notApplicable(mapping, changeComments);
		
		if(result.isStatus()){
			
			String mappingSuccess = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SPSID_NOT_APPLICABLE, null);		
			infoMessages.add(mappingSuccess);		
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	
	public ModelAndView viewHistory(HttpServletRequest request,
			HttpServletResponse response) {
		Mapping mapping = new Mapping();
		SPSId spsId = new SPSId();	
		spsId.setSpsId(request.getParameter("spsId"));
		mapping.setSpsId(spsId);	
		
		List mappings  = locateSpsIdFacade.getRecords(mapping, null, null, -1, 21, null);
		ModelAndView modelAndView = new ModelAndView("viewHistorySPS");
		if(null != mappings && !mappings.isEmpty()){
			mapping = (Mapping) mappings.get(0);
			modelAndView.addObject(WebConstants.MAPPING, mapping);
			modelAndView.addObject("auditTrailList",mapping.getAuditTrails());
			if(mapping.getAuditTrails().size() >= WebConstants.AUDIT_TRAIL_COUNT_IN_VIEW_HISTORY){				
				modelAndView.addObject("viewalllink", new Boolean(true));
			}
		}	
		return modelAndView;
		}	
	
	
	public ModelAndView sendToTest(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		List infoMessages = new ArrayList();  
		List warningMessages = new ArrayList();  
		Mapping mapping = createMappingObject(request);
		String changeComments = request.getParameter(WebConstants.USER_COMNTS).toUpperCase();
		
		MappingResult result =  mappingSpsIdFacade.updateAndSendToTest(mapping, changeComments);
		
		
		// mapping creation successful
		if(result.isStatus()){
			if(null!= result.getWarningMsgsList()){
				
				warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
			}
			infoMessages.add(BxResourceBundle.getResourceBundle(WebConstants.MAPPING_SEND_TO_TEST_SUCCESS, null));
			sessionMessageTray.setInformationMessages(infoMessages);
			sessionMessageTray.setWarningMessages(warningMessages);
		}
		else{
			List errorMessages = new ArrayList();			
			
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
			result.getMapping().setPageFrom(pageName);
			modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
			modelAndView.addObject(WebConstants.CHANGE_COMMENTS, changeComments);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);			
			return modelAndView;
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	public ModelAndView approve(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		List infoMessages = new ArrayList();  
		List warningMessages = new ArrayList();  
		Mapping mapping = createMappingObject(request);
		String changeComments = request.getParameter(WebConstants.USER_COMNTS).toUpperCase();
		
		MappingResult result =  mappingSpsIdFacade.updateAndApprove(mapping, changeComments);
		
		// mapping creation successful
		if(result.isStatus()){		
			if(null!= result.getWarningMsgsList()){
				
				warningMessages = BxUtil.getMessages(result.getWarningMsgsList());
			}
			String mappingSuccess;
			if (DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(
					result.getPreviousVariableMappingStatus())) {
				mappingSuccess = BxResourceBundle.getResourceBundle(
						"mapping.approve.scheduledtoproduction.success", null);
			}
			else {
				mappingSuccess = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_APPROVE_SUCCESS, null);
			}
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);	
			sessionMessageTray.setWarningMessages(warningMessages);
		}
		else{
			List errorMessages = new ArrayList();			
			
			if(null!= result.getErrorMsgsList()){
				
				errorMessages = BxUtil.getMessages(result.getErrorMsgsList());
			}
			ModelAndView modelAndView = new ModelAndView(WebConstants.EDIT_SPS_MAPPING);
			result.getMapping().setPageFrom(pageName);
			modelAndView.addObject(WebConstants.MAPPING,result.getMapping());
			modelAndView.addObject(WebConstants.CHANGE_COMMENTS, changeComments);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);			
			return modelAndView;
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}
	public ModelAndView discardChanges(HttpServletRequest request,
			HttpServletResponse response) {
		String pageName = request.getParameter("pageFrom");
		
		Mapping mapping = new Mapping();
		List infoMessages = new ArrayList(); 
		
		SPSId spsId = new SPSId();	
		spsId.setSpsId(request.getParameter("selectedSpsId"));
		mapping.setSpsId(spsId);
		
		
		User user = new User();
		String userId = request.getAttribute(SecurityConstants.USER_NAME).toString();
		user.setLastUpdatedUserName(userId);
		user.setCreatedUserName(userId);
		mapping.setUser(user);
		
		  
		MappingResult result = mappingSpsIdFacade.discardChanges(mapping,request.getParameter(WebConstants.CHANGE_COMMENTS) );
				
		if(result.isStatus()){
			
			String mappingSuccess = BxResourceBundle.getResourceBundle(WebConstants.MAPPING_DISCARD_CHANGES_SUCCESS, null);		
			infoMessages.add(mappingSuccess);
			sessionMessageTray.setInformationMessages(infoMessages);
		}
		if(null != pageName && pageName.trim().equals("advanceSearchEbx")){
			return  WebUtil.redirectToeWPDAdvanceSearchPage(request);
		}else{
			return WebUtil.redirectToeWPDBXLandingPage(request);
		}
	}

	public MappingFacade getMappingSpsIdFacade() {
		return mappingSpsIdFacade;
	}

	public void setMappingSpsIdFacade(MappingFacade mappingSpsIdFacade) {
		this.mappingSpsIdFacade = mappingSpsIdFacade;
	}
	public ModelAndView printSPSMapping(HttpServletRequest request,
			HttpServletResponse response) throws EBXException{
		
		Mapping currentMapping = createMappingObject(request);
		String createPageFlag = request.getParameter("createPage");
		List mappings;
		if (null != createPageFlag && !createPageFlag.trim().equals("") && createPageFlag.equals("true")) {
			List statusUnmapped = new ArrayList();
			statusUnmapped.add(DomainConstants.UNMAPPED_STATUS);
			mappings = locateSpsIdFacade.getRecords(currentMapping, statusUnmapped, null, -1, 21, null);
		}
		else {
			mappings = locateSpsIdFacade.getRecords(currentMapping, null, null, -1, 21, null);
		}

		ModelAndView modelAndView = new ModelAndView("printSPSMapping");

		if (null != mappings && !mappings.isEmpty() && null != mappings.get(0)) {
			Mapping mapping = (Mapping) mappings.get(0);
			currentMapping.setSpsId(mapping.getSpsId());
			currentMapping.setAuditTrails(mapping.getAuditTrails());
			if (!DomainConstants.UNMAPPED_STATUS.equals(mapping.getVariableMappingStatus())) {
				currentMapping.setVariableMappingStatus(mapping.getVariableMappingStatus());
			}
		}

		modelAndView.addObject("currentMapping", currentMapping);
		
		return modelAndView;
	}

	private Mapping createMappingObject(HttpServletRequest request) {
		
	

		Mapping mapping = new Mapping();

		SPSId spsId = new SPSId();
		spsId.setSpsDetail(new ArrayList());

		spsId.setSpsDesc(request.getParameter("spsIdDesc"));
		spsId.setSpsId(request.getParameter("spsId"));
		String[] spsPVA = request.getParameterValues("spsPVA");
		String[] spsDataType = request.getParameterValues("spsDataType");
		String[] spsType = request.getParameterValues("spsType");
		int totalCount = 0;
		if (null != request.getParameter("totalCount")) {
		  totalCount = Integer.parseInt(request.getParameter("totalCount"));
		}
		
		for (int count = 0; count < totalCount; count++) {
			SPSDetail spsDetail = new SPSDetail();
			spsDetail.setSpsPVA(spsPVA[count]);
			spsDetail.setSpsDataType(spsDataType[count]);
			spsDetail.setSpsType(spsType[count]);
			spsId.getSpsDetail().add(spsDetail);
		}
		
		mapping.setSpsId(spsId);
		
		HippaSegment hippaSegment;
		hippaSegment = createHippaSegment(DomainConstants.EB01_NAME,request.getParameter("eb01Val"),request.getParameter("EB01Desc"));
		mapping.setEb01(hippaSegment);
		
		hippaSegment = createHippaSegment(DomainConstants.EB02_NAME,request.getParameter("eb02Val"),request.getParameter("EB02Desc"));
		mapping.setEb02(hippaSegment);

		hippaSegment = createHippaSegment(DomainConstants.EB06_NAME,request.getParameter("eb06Val"),request.getParameter("EB06Desc"));
		mapping.setEb06(hippaSegment);

		hippaSegment = createHippaSegment(DomainConstants.EB09_NAME,request.getParameter("eb09Val"),request.getParameter("EB09Desc"));
		mapping.setEb09(hippaSegment);

  
		hippaSegment = createHippaSegment(DomainConstants.ACCUM_REF_NAME,request.getParameter("accumulator"),request.getParameter("accumDesc"));
		mapping.setAccum(hippaSegment);
 
		hippaSegment = createHippaSegment(DomainConstants.HSD01_NAME,request.getParameter("hsd01"),request.getParameter("HSD01Desc"));
		mapping.setHsd01(hippaSegment);
		
		hippaSegment = createHippaSegment(DomainConstants.HSD02_NAME,request.getParameter("hsd02"),request.getParameter("HSD02Desc"));
		mapping.setHsd02(hippaSegment);

		hippaSegment = createHippaSegment(DomainConstants.HSD03_NAME,request.getParameter("hsd03"),request.getParameter("HSD03Desc"));
		mapping.setHsd03(hippaSegment);

		hippaSegment = createHippaSegment(DomainConstants.HSD04_NAME,request.getParameter("hsd04"),request.getParameter("HSD04Desc"));
		mapping.setHsd04(hippaSegment);
		
		hippaSegment = createHippaSegment(DomainConstants.HSD05_NAME,request.getParameter("hsd05"),request.getParameter("HSD05Desc"));
		mapping.setHsd05(hippaSegment);
		
		hippaSegment = createHippaSegment(DomainConstants.HSD06_NAME,request.getParameter("hsd06"),request.getParameter("HSD06Desc"));
		mapping.setHsd06(hippaSegment);	


		hippaSegment = createHippaSegment(DomainConstants.HSD07_NAME,request.getParameter("hsd07"),request.getParameter("HSD07Desc"));
		mapping.setHsd07(hippaSegment);	


		hippaSegment = createHippaSegment(DomainConstants.HSD08_NAME,request.getParameter("hsd08"),request.getParameter("HSD08Desc"));
		mapping.setHsd08(hippaSegment);	



		if ("checked".equals(request.getParameter("notFinalizedChkBox"))) {
			mapping.setFinalized(false);
		}
		else {
			mapping.setFinalized(true);
		}


		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		User user = new User();
		user.setCreatedUserName(userId); 
		user.setLastUpdatedUserName(userId);
		mapping.setUser(user);


		return mapping;
	}


	private HippaSegment createHippaSegment(String hippaSegmentname, String value, String description) {
		HippaSegment hippaSegment = new HippaSegment(hippaSegmentname);
		List selectedHippaSegmentValues = new ArrayList();
		HippaCodeValue code = new HippaCodeValue();
		code.setValue(value);
		code.setDescription(description);
		selectedHippaSegmentValues.add(code);
		hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
		return hippaSegment;
	}
	private void deleteLockDuringCancel(HttpServletRequest request) {
		String spsId = request.getParameter("spsId");
		Mapping mapping = new Mapping();
		List mappingList = new ArrayList();
		String loggedInUser = request.getAttribute(SecurityConstants.USER_NAME).toString();
		if (null != spsId && !DomainConstants.EMPTY.equals(spsId.trim())) {
			SPSId sps = new SPSId();
			sps.setSpsId(spsId);
			mapping.setSpsId(sps);
			mappingList = locateSpsIdFacade.getRecords(mapping, null, null, -1, 21, null);		
		}
		if (null != mappingList && !mappingList.isEmpty()) {
			Mapping existingMapping = (Mapping)mappingList.get(0);
			if (null != existingMapping) {
				// Case 1 :  Delete the Lock when mapping status is "scheduled to test"
				//or "sheduled to production" or "object transferred". 
				if (DomainConstants.STATUS_SCHEDULED_TO_TEST.equals(existingMapping.getVariableMappingStatus()) 
						|| DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_OBJECT_TRANSFERRED.equals(existingMapping.getVariableMappingStatus())
						|| DomainConstants.STATUS_NOT_APPLICABLE.equals(existingMapping.getVariableMappingStatus())) {
					mappingSpsIdFacade.unlock(mapping);
				}
				// Case 2 :  Delete the Lock when mapping status is "Building"
				//or "Being Modified" and the loggedin user is not equal to last updated user. 
				else if ((DomainConstants.STATUS_BEING_MODIFIED.equals(existingMapping.getVariableMappingStatus()) 
						|| DomainConstants.STATUS_BUILDING.equals(existingMapping.getVariableMappingStatus())) &&
						(!loggedInUser.equalsIgnoreCase(existingMapping.getUser().getLastUpdatedUserName()))) {
					mappingSpsIdFacade.unlock(mapping);
				}

			}
		}
	}
	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}
	public LocateFacade getLocateSpsIdFacade() {
		return locateSpsIdFacade;
	}
	public void setLocateSpsIdFacade(LocateFacade locateSpsIdFacade) {
		this.locateSpsIdFacade = locateSpsIdFacade;
	}


	
}
