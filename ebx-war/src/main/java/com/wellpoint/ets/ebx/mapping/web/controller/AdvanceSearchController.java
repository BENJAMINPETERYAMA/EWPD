package com.wellpoint.ets.ebx.mapping.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.application.VariableMappingFacade;
import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Page;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.bx.mapping.web.view.ExcelSearchView;
import com.wellpoint.ets.ebx.mapping.application.AdvanceSearchFacade;

public class AdvanceSearchController extends MultiActionController {

	private AdvanceSearchFacade advancedSearchFacade;
	private VariableMappingFacade variableMappingFacade;
	private Integer auditInfoLimit;

	private SessionMessageTray sessionMessageTray;

	private static Logger log = Logger.getLogger(AdvanceSearchController.class);

	private MassUpdateTracker massUpdateTracker;
	
	ResourceBundle rb = ResourceBundle.getBundle("blueexchange", Locale.getDefault());	
	String mutExportThresholdLimit = rb.getString("MUT_EXPORT_THRESHOLD_LIMIT");	
	int maxLimit = Integer.parseInt(mutExportThresholdLimit);	
	
	/**
	 * method is called when the Advance Search Link is clicked from the landing page
	 * 
	 * @param HttpServletRequest,HttpServletResponse
	 * @return modelAndView(advancesearchresultbx.jsp)
	 */
	public ModelAndView viewSearch(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		HttpSession session = arg0.getSession();
		clearSessionValues(session);
		Map map = new HashMap();		
		map.put("fromHistory","false");

		ModelAndView modelAndView = new ModelAndView("advancesearch",map);

		modelAndView.addObject(WebConstants.INFO_MESSAGES,
				sessionMessageTray.getAndClearInformationMessages()).addObject(
				WebConstants.ERROR_MESSAGES,
				sessionMessageTray.getAndClearErrorMessages()).addObject(
				WebConstants.WARNING_MESSAGES,
				sessionMessageTray.getAndClearWarningMessages());
		return modelAndView;
	}
	
	/**
	 * method is called when the search criteria is entered and search button is clicked
	 * 
	 * @param HttpServletRequest,HttpServletResponse
	 * @return modelAndView(advancesearchresultbx.jsp)
	 */
	public ModelAndView advanceSearch(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		 long startingTime = System.currentTimeMillis();
		HttpSession session = request.getSession();
		String userName = request.getAttribute(SecurityConstants.USER_NAME).toString();
		clearSessionValues(session);
		request.setAttribute("fromHistory", "false");
		
		String variableId = (request.getParameter("varIdToLocate")).trim();
		String user = request.getParameter("user");
		BxUtil.validationVariable(user);
		String comaSeperatedUser = null;

		boolean isUnMapped = false;
		boolean isMapped = false;
		boolean isNotApplicable = false;
		boolean isInProgress = false;
		boolean isNotFinalized = false;

		if(StringUtils.isBlank(variableId)){
			variableId = null;
		}
		if(StringUtils.isBlank(user)){
			user = null;
		}else{
			comaSeperatedUser = BxUtil.splitCommaSeperatedUser(user);
		}
		if (null != request.getParameter("isUnMapped")
				&& request.getParameter("isUnMapped").equals("true"))
			isUnMapped = true;
		if (null != request.getParameter("isMapped")
				&& request.getParameter("isMapped").equals("true"))
			isMapped = true;
		if (null != request.getParameter("isNotApplicable")
				&& request.getParameter("isNotApplicable").equals("true"))
			isNotApplicable = true;
		if (null != request.getParameter("isInProgress")
				&& request.getParameter("isInProgress").equals("true"))
			isInProgress = true;
		if (null != request.getParameter("isNotFinalized")
				&& request.getParameter("isNotFinalized").equals("true"))
			isNotFinalized = true;

		String currentPage = request.getParameter("currentPage"); // "1";
		String section = request.getParameter("section"); // "Next";
		String authorizedToApprove  = ESAPI.encoder().encodeForHTML(request.getParameter("authorizedToApprove"));	
		if(BxUtil.regExPatterValidation(authorizedToApprove)){
			authorizedToApprove = authorizedToApprove;
		}else{
			authorizedToApprove=null;	
		}
		String variableDescription = request.getParameter("varDescription").trim();
		BxUtil.validationVariable(variableDescription);
		String majorHeading = request.getParameter("majorHeading").trim();
		BxUtil.validationVariable(majorHeading);
		String minorHeading = request.getParameter("minorHeading").trim();
		BxUtil.validationVariable(minorHeading);
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setVariableId(variableId);
		String eb01= request.getParameter("eb01").trim();
		BxUtil.validationVariable(eb01);
		searchCriteria.setEB01(eb01);
		String III02= request.getParameter("III02").trim();
		BxUtil.validationVariable(III02);
		searchCriteria.setIII02(III02);
		String msgtxt= request.getParameter("msgtxt").trim();
		BxUtil.validationVariable(msgtxt);
		searchCriteria.setMessageText(msgtxt);
		if (null != request.getParameter("eb03")) {
			String eb03ForCrieteria = "";
			String ebo3FromRequest = request.getParameter("eb03");
			String[] eb03Array = ebo3FromRequest.split(",");
			for (int itr = 0; itr < eb03Array.length; itr++) {
				String eb03Value = eb03Array[itr];
				if (null != eb03Value) {
					eb03Value = eb03Value.trim().toUpperCase();
					if (!"'".equals(eb03Value) && !"".equals(eb03Value)) {
						eb03ForCrieteria = eb03ForCrieteria + eb03Value + ",";
					}
				}

			}
			if (eb03ForCrieteria.length() > 0) {
				eb03ForCrieteria = eb03ForCrieteria.substring(0,
						eb03ForCrieteria.length() - 1);
			} else {
				eb03ForCrieteria = "";
			}
			searchCriteria.setEB03(eb03ForCrieteria.trim());
		}
		
		String noteType= request.getParameter("noteType").trim();
		BxUtil.validationVariable(noteType);
		searchCriteria.setNoteType(noteType);
		searchCriteria.setUser(user);
		
		searchCriteria.setVariableDescription(variableDescription);
		searchCriteria.setMajorHeading(majorHeading);
		searchCriteria.setMinorHeading(minorHeading);
		
		searchCriteria.setCommaSeperatedUser(comaSeperatedUser);
		//Mapped and Unmapped checked with the value of eb03 or eb01 or notetype then return only mapped
		if(isUnMapped && isMapped ){
			if(null != searchCriteria.getEB01() && !searchCriteria.getEB01().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getEB03() && !searchCriteria.getEB03().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getIII02() && !searchCriteria.getIII02().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getNoteType() && !searchCriteria.getNoteType().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getMessageText() && !searchCriteria.getMessageText().equals(""))
				isUnMapped = false;
			/*if(null != user && !user.equals(""))
				isUnMapped = false;*/
		}
		//NotApplicable and Unmapped checked with the value of eb03 or eb01 or notetype then return only NotApplicable
		if(isUnMapped && isNotApplicable ){
			if(null != searchCriteria.getEB01() && !searchCriteria.getEB01().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getEB03() && !searchCriteria.getEB03().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getIII02() && !searchCriteria.getIII02().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getNoteType() && !searchCriteria.getNoteType().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getMessageText() && !searchCriteria.getMessageText().equals(""))
				isUnMapped = false;
			/*if(null != user && !user.equals(""))
				isUnMapped = false;*/
		}
		//NotFinalized and Unmapped checked with the value of eb03 or eb01 or notetype then return only NotFinalized
		if(isUnMapped && isNotFinalized ){
			if(null != searchCriteria.getEB01() && !searchCriteria.getEB01().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getEB03() && !searchCriteria.getEB03().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getIII02() && !searchCriteria.getIII02().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getNoteType() && !searchCriteria.getNoteType().equals(""))
				isUnMapped = false;
			if(null != searchCriteria.getMessageText() && !searchCriteria.getMessageText().equals(""))
				isUnMapped = false;
			/*if(null != user && !user.equals(""))
				isUnMapped = false;*/
		}
		searchCriteria.setUnMapped(isUnMapped);
		searchCriteria.setMapped(isMapped);
		searchCriteria.setNotApplicable(isNotApplicable);
		searchCriteria.setInProgress(isInProgress);
		searchCriteria.setNotFinalized(isNotFinalized);
		searchCriteria.setCheckWPD(true);
		
		searchCriteria.setLoggedUser(userName);
		searchCriteria.setAuthorizedToApproveFlag(authorizedToApprove);
		
		/********January Release********/
		String contractId=ESAPI.encoder().encodeForHTML(request.getParameter("contractId"));
		if(BxUtil.regExPatterValidation(contractId)){
			searchCriteria.setContractId(contractId);
		}else{
			contractId=null;
		}
		String systemNameForContract=ESAPI.encoder().encodeForHTML(request.getParameter("systemNameForContract"));
		if(BxUtil.regExPatterValidation(systemNameForContract)){
		searchCriteria.setContractSystem(systemNameForContract);
		}else{
			systemNameForContract=null;
		}
		String startDateForContract= StringEscapeUtils.escapeHtml(request.getParameter("startDateForContract"));
		BxUtil.validationVariable(startDateForContract);
		searchCriteria.setContractStartDate(startDateForContract);
		String revisionDateForISGContract = StringEscapeUtils.escapeHtml(request.getParameter("revisionDateForISGContract"));
		BxUtil.validationVariable(revisionDateForISGContract);
		if(("null").equalsIgnoreCase(revisionDateForISGContract)){
			revisionDateForISGContract = null;
		}
		searchCriteria.setISGContractRevisionDate(revisionDateForISGContract);
		
		/********January Release********/
		
		
		int totalNoOfRecords = advancedSearchFacade
				.getAdvanceSearchCount(searchCriteria); // 10;
		massUpdateTracker.setLastSearchedType(MassUpdateTracker.VARIABLE_TYPE);
		Page page = getPage(currentPage, section, totalNoOfRecords);

		session.setAttribute("searchCriteria", searchCriteria);
		session.setAttribute("page", page);
		long endTime = System.currentTimeMillis(); 

		ModelAndView view =  getAdvanceSearchResultView(page, advancedSearchFacade
				.getAdvanceSearchRecords(searchCriteria, 50, page),
				searchCriteria, "false");
		
		log.debug("Total Time Taken :"+ (endTime-startingTime));
		return view;
	}
	/**
	 * method to load the result page
	 * page
	 * @param Page,List,SearchCriteria,String
	 * @return modelAndView(advancesearchresultbx.jsp)
	 */
	private ModelAndView getAdvanceSearchResultView(Page page, List list,
			SearchCriteria searchCriteria, String fromHistory) {
		massUpdateTracker.markSelected(list,
				Integer.valueOf(page.getCurrentPage()), "VARIABLE");
		BxUtil.encodeAdvanceSearchResult(list);
		Map map = new HashMap();
		map.put("searchResults", list);
		map.put("page", page);
		map.put("selectedPageInfo", massUpdateTracker.getSelectedPagesString());
		map.put("fromHistory", fromHistory);
		map.put("searchCriteria", searchCriteria);
		ModelAndView modelAndView = new ModelAndView("advancesearchresultbx",
				map);
		return modelAndView;
	}

	/**
	 * method to load the popup while clicking on the variable ID in the result page
	 * @param HttpServletRequest,HttpServletResponse
	 * @return modelAndView(viewPopUpVariable.jsp)
	 */

	/*public ModelAndView viewPopUpVariable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Setting the values to teh search result which was queried earlier in
		// advancesearchresult page
		SearchResult searchresult = new SearchResult();
		searchresult.setVariableId(request.getParameter("variableId"));
		searchresult.setDescription(request.getParameter("desc"));
		searchresult.setMajorHeading(request.getParameter("maj"));
		searchresult.setMinorHeading(request.getParameter("min"));
		searchresult.setPva(request.getParameter("pva"));
		searchresult.setEB03(request.getParameter("EB03"));
		searchresult.setEB02(request.getParameter("EB02"));
		searchresult.setEB06(request.getParameter("EB06"));
		searchresult.setEB09(request.getParameter("EB09"));
		searchresult.setHsd01(request.getParameter("hsd01"));
		searchresult.setHsd02(request.getParameter("hsd02"));
		searchresult.setHsd03(request.getParameter("hsd03"));
		searchresult.setHsd04(request.getParameter("hsd04"));
		searchresult.setHsd05(request.getParameter("hsd05"));
		searchresult.setHsd06(request.getParameter("hsd06"));
		searchresult.setHsd07(request.getParameter("hsd07"));
		searchresult.setHsd08(request.getParameter("hsd08"));
		searchresult.setStatus(request.getParameter("status"));
		searchresult.setNoteTypeCode(request.getParameter("noteTypeCode"));
		searchresult.setIII02(request.getParameter("III02"));
		searchresult
				.setNotCompleteFlag(request.getParameter("notCompleteFlag"));
		searchresult.setAccumNotReqrdIndctr(request
				.getParameter("accumNotReqrdIndctr"));
		searchresult.setAccumulator(request.getParameter("accumulator"));
		searchresult.setMsgReqrdIndctr(request.getParameter("msgReqrdIndctr"));
		searchresult.setMessageText(request.getParameter("messageText"));
		searchresult.setDataType(request.getParameter("dataType"));

		List searchResultList = new ArrayList();
		searchResultList.add(searchresult);
		ModelAndView modelAndView = new ModelAndView("viewPopUpVariable");
		modelAndView.addObject("searchResults", searchResultList);

		return modelAndView;
	}*/
	/**
	 * Method for viewing pop up for unmapped variable
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewPopUpForUnmappedVariable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer majorSb = new StringBuffer();
		StringBuffer minorSb = new StringBuffer();
		
		String variableId = request.getParameter("variableId");
		
		Variable variable = new Variable();
		variable.setVariableId(variableId);
		List currentViewVariable  = variableMappingFacade.viewVariableDetails(variable);
		SearchResult searchresult = new SearchResult();
		if(null != currentViewVariable && !currentViewVariable.isEmpty()){
			Variable variable2 = (Variable) currentViewVariable.get(0);
			searchresult.setVariableId(variable2.getVariableId());
			searchresult.setDescription(variable2.getDescription());
			searchresult.setMajorHeading(variable2.getMajorHeadings());
			searchresult.setMinorHeading(variable2.getMinorHeadings());
			searchresult.setPva(variable2.getPva());		
			searchresult.setDataType(variable2.getDataType());
			searchresult.setWpdAccumulator(variable2.getWpdAccumulator());
			
			
			searchresult.setStatus("UNMAPPED");
			if(null != variable2.getLgCatagory()){
				searchresult.setCategoryCode(variable2.getLgCatagory());
			}else{
				searchresult.setCategoryCode(variable2.getIsgCatagory());	
			}
			if(currentViewVariable.size()>1){
				Variable variable3=null;
				
				for(int i=0;i<currentViewVariable.size();i++){
					variable3 = (Variable) currentViewVariable.get(i);
					if(null!= variable3.getMajorHeadings() && !variable3.getMajorHeadings().equals("")){
						majorSb.append(variable3.getMajorHeadings());
						if(i != currentViewVariable.size()-1)
							majorSb.append(",");
						
					}
					if(null!= variable3.getMinorHeadings() && !variable3.getMinorHeadings().equals("")){
						minorSb.append(variable3.getMinorHeadings());
						if(i != currentViewVariable.size()-1)
							minorSb.append(",");
					}
				}	
				searchresult.setMajorHeading(majorSb.toString());
				searchresult.setMinorHeading(minorSb.toString());
			}
		}
		List searchResultList = new ArrayList();
		searchResultList.add(searchresult);
		
		ModelAndView modelAndView = new ModelAndView("viewPopUpVariable");
		modelAndView.addObject("searchResults",searchResultList);
		return modelAndView;
	}	
	/**
	 * Method for viewing pop up for mapped variable
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewPopUpForMapped(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String variableId = request.getParameter("variableId");
		
		StringBuffer majorSb = new StringBuffer();
		StringBuffer minorSb = new StringBuffer();
		
		Mapping currentMapping= variableMappingFacade.viewMapping(variableId, auditInfoLimit);
		if(null != currentMapping && currentMapping.getMessage() != null){
			String message = BxUtil.appendBreak(currentMapping.getMessage(),10);
			currentMapping.setMessage(message);
		}
		List currentViewVariable = null;
		if(null != currentMapping){
			currentViewVariable = currentMapping.getVariableList();
		}
		
		ModelAndView modelAndView = new ModelAndView("viewPopUpVariable");
		
		if(null != currentMapping){
			/**
			 * MTM CODE CHANGE
			 */boolean checkNotFinalized=currentMapping.isFinalized();
			 if(checkNotFinalized){
			 	currentMapping.setMappingComplete("Y");
			 }
			 else{
			 	currentMapping.setMappingComplete("N");
			 }
		}
		 /**
		  * END
		  */
		 
		 SearchResult searchresult = new SearchResult();
		 if(null != currentViewVariable && !currentViewVariable.isEmpty()){
		 	Variable variable2 = (Variable) currentViewVariable.get(0);
		 	searchresult.setVariableId(variable2.getVariableId());
		 	searchresult.setDescription(variable2.getDescription());
		 	searchresult.setMajorHeading(variable2.getMajorHeadings());
		 	searchresult.setMinorHeading(variable2.getMinorHeadings());
		 	searchresult.setPva(variable2.getPva());		
		 	searchresult.setDataType(variable2.getDataType());
		 	searchresult.setWpdAccumulator(variable2.getWpdAccumulator());
		 	if(null != variable2.getLgCatagory()){
				searchresult.setCategoryCode(variable2.getLgCatagory());
			}else{
				searchresult.setCategoryCode(variable2.getIsgCatagory());	
			}
		 	if(currentViewVariable.size()>1){
		 		Variable variable3=null;
		 		
		 		for(int i=0;i<currentViewVariable.size();i++){
		 			variable3 = (Variable) currentViewVariable.get(i);
		 			if(null!= variable3.getMajorHeadings() && !variable3.getMajorHeadings().equals("")){
		 				majorSb.append(variable3.getMajorHeadings());
		 				if(i != currentViewVariable.size()-1)
		 					majorSb.append(",");
		 				
		 			}
		 			if(null!= variable3.getMinorHeadings() && !variable3.getMinorHeadings().equals("")){
		 				minorSb.append(variable3.getMinorHeadings());
		 				if(i != currentViewVariable.size()-1)
		 					minorSb.append(",");
		 			}
		 		}	
		 		searchresult.setMajorHeading(majorSb.toString());
		 		searchresult.setMinorHeading(minorSb.toString());
		 	}
		 }
		 if(null != currentMapping){
		 	if(null != currentMapping.getEb03() && null!= currentMapping.getEb03().getHippaCodeSelectedValues())
		 		searchresult.setEB03(((HippaCodeValue)currentMapping.getEb03().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getEb03() && null!= currentMapping.getEb03().getEb03Association())
		 		searchresult.setEb03Association(currentMapping.getEb03().getEb03Association());
		 	if(null != currentMapping.getEb02() && null!= currentMapping.getEb02().getHippaCodeSelectedValues())
		 		searchresult.setEB02(((HippaCodeValue)currentMapping.getEb02().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getEb06() && null!= currentMapping.getEb06().getHippaCodeSelectedValues())
		 		searchresult.setEB06(((HippaCodeValue)currentMapping.getEb06().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getEb09() && null!= currentMapping.getEb09().getHippaCodeSelectedValues())
		 		searchresult.setEB09(((HippaCodeValue)currentMapping.getEb09().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd01() && null!= currentMapping.getHsd01().getHippaCodeSelectedValues())
		 		searchresult.setHsd01(((HippaCodeValue)currentMapping.getHsd01().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd02() && null!= currentMapping.getHsd02().getHippaCodeSelectedValues())
		 		searchresult.setHsd02(((HippaCodeValue)currentMapping.getHsd02().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd03() && null!= currentMapping.getHsd03().getHippaCodeSelectedValues())
		 		searchresult.setHsd03(((HippaCodeValue)currentMapping.getHsd03().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd04() && null!= currentMapping.getHsd04().getHippaCodeSelectedValues())
		 		searchresult.setHsd04(((HippaCodeValue)currentMapping.getHsd04().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd05() && null!= currentMapping.getHsd05().getHippaCodeSelectedValues())
		 		searchresult.setHsd05(((HippaCodeValue)currentMapping.getHsd05().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd06() && null!= currentMapping.getHsd06().getHippaCodeSelectedValues())
		 		searchresult.setHsd06(((HippaCodeValue)currentMapping.getHsd06().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd07() && null!= currentMapping.getHsd07().getHippaCodeSelectedValues())
		 		searchresult.setHsd07(((HippaCodeValue)currentMapping.getHsd07().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getHsd08() && null!= currentMapping.getHsd08().getHippaCodeSelectedValues())
		 		searchresult.setHsd08(((HippaCodeValue)currentMapping.getHsd08().getHippaCodeSelectedValues().get(0)).getValue());
		 	searchresult.setProcedureExcludedInd(currentMapping.getProcedureExcludedInd());
		 	searchresult.setStatus(currentMapping.getVariableMappingStatus());
		 	
		 	if(null != currentMapping.getNoteTypeCode() && null!= currentMapping.getNoteTypeCode().getHippaCodeSelectedValues())
		 		searchresult.setNoteTypeCode(((HippaCodeValue)currentMapping.getNoteTypeCode().getHippaCodeSelectedValues().get(0)).getValue());
		 	if(null != currentMapping.getIi02() && null!= currentMapping.getIi02().getHippaCodeSelectedValues())
		 		searchresult.setIII02(((HippaCodeValue)currentMapping.getIi02().getHippaCodeSelectedValues().get(0)).getValue());
		 	
		 	searchresult.setNotCompleteFlag(currentMapping.getMappingComplete());
		 	searchresult.setAccumNotReqrdIndctr(currentMapping.getSensitiveBenefitIndicator());
		 	
		 	if(null != currentMapping.getAccum() && null!= currentMapping.getAccum().getHippaCodeSelectedValues())
		 		searchresult.setAccumulator(((HippaCodeValue)currentMapping.getAccum().getHippaCodeSelectedValues().get(0)).getValue());
		 	searchresult.setMsgReqrdIndctr(currentMapping.getMsg_type_required());
		 	searchresult.setMessageText(currentMapping.getMessage());
		 	searchresult.setAuditLock(currentMapping.getAuditLock());
		 	//Show the start age in hover --BXNI
		 	String comaSeperatedStartAge = "";
			if(null != currentMapping.getStartAge() && null!= currentMapping.getStartAge().getHippaCodeSelectedValues())
		 		comaSeperatedStartAge  = ((HippaCodeValue) currentMapping.getStartAge().getHippaCodeSelectedValues().get(0)).getValue();
				comaSeperatedStartAge = comaSeperatedStartAge.replaceAll(",", ", ");
		 		searchresult.setStartAge(comaSeperatedStartAge);
		 	//Show the start age in hover --BXNI
		 	String comaSeperatedEndAge = "";
		 	if(null != currentMapping.getEndAge() && null!= currentMapping.getEndAge().getHippaCodeSelectedValues())
		 		comaSeperatedEndAge  = ((HippaCodeValue) currentMapping.getEndAge().getHippaCodeSelectedValues().get(0)).getValue();
		 		comaSeperatedEndAge = comaSeperatedEndAge.replaceAll(",", ", ");
		 		searchresult.setEndAge(comaSeperatedEndAge);

		 }
		 List searchResultList = new ArrayList();
		 searchResultList.add(searchresult);
		 
		 modelAndView.addObject("searchResults",searchResultList);
		 modelAndView.addObject("eb03Associations",searchresult.getEb03Association());
		 
		 return modelAndView;
	}
	
	/**
	 * method clears teh session values, called while advance search button is clicked
	 */
	public void clearSessionValues(HttpSession session) {
		session.removeAttribute("searchCriteria");
		session.removeAttribute("page");
	}
	/**
	 * method to load teh previous results screen after coming back from edit,create etc
	 * @param HttpServletRequest,HttpServletResponse
	 * @return modelAndView(viewPopUpVariable.jsp)
	 */
	
	public ModelAndView viewHistorySearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("fromHistory", "true");
		ModelAndView modelAndView = new ModelAndView("advancesearch");
		modelAndView.addObject(WebConstants.INFO_MESSAGES,
				sessionMessageTray.getAndClearInformationMessages()).addObject(
				WebConstants.ERROR_MESSAGES,
				sessionMessageTray.getAndClearErrorMessages()).addObject(
				WebConstants.WARNING_MESSAGES,
				sessionMessageTray.getAndClearWarningMessages());
		return modelAndView;
	}
	
	/*
	 * public ModelAndView viewSearchAfterPrint(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception {
	 * request.setAttribute("fromHistory", "false"); ModelAndView modelAndView = new
	 * ModelAndView("advancesearch");
	 * modelAndView.addObject(WebConstants.INFO_MESSAGES,
	 * sessionMessageTray.getAndClearInformationMessages()).addObject(
	 * WebConstants.ERROR_MESSAGES,
	 * sessionMessageTray.getAndClearErrorMessages()).addObject(
	 * WebConstants.WARNING_MESSAGES,
	 * sessionMessageTray.getAndClearWarningMessages()); return modelAndView; }
	 */
	
	public ModelAndView viewSearchAfterPrint(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setAttribute("fromPrint", "true");
		ModelAndView modelAndView = new ModelAndView("advancesearch");
		modelAndView.addObject(WebConstants.INFO_MESSAGES,
				sessionMessageTray.getAndClearInformationMessages()).addObject(
				WebConstants.ERROR_MESSAGES,
				sessionMessageTray.getAndClearErrorMessages()).addObject(
				WebConstants.WARNING_MESSAGES,
				sessionMessageTray.getAndClearWarningMessages());
		return modelAndView;
		
		
		
		
		
		
		
		/*
		 * request.setAttribute("fromPrint", "true"); HttpSession session =
		 * request.getSession();
		 * 
		 * SearchCriteria searchCriteria = (SearchCriteria) session
		 * .getAttribute("searchCriteria");
		 * log.info(">>>>>>>>>> Search criteria inside viewSearchAfterPrint is "+
		 * searchCriteria); Page page = (Page) session.getAttribute("page");
		 * 
		 * session.setAttribute("searchCriteria", searchCriteria);
		 * session.setAttribute("page", page); Map map = new HashMap(); map.put("page",
		 * page); map.put("fromHistory", "false"); map.put("searchCriteria",
		 * searchCriteria); ModelAndView modelAndView = new
		 * ModelAndView("advancesearch",map);
		 * modelAndView.addObject(WebConstants.INFO_MESSAGES,
		 * sessionMessageTray.getAndClearInformationMessages()).addObject(
		 * WebConstants.ERROR_MESSAGES,
		 * sessionMessageTray.getAndClearErrorMessages()).addObject(
		 * WebConstants.WARNING_MESSAGES,
		 * sessionMessageTray.getAndClearWarningMessages()); return modelAndView;
		 */
	}

	
	/**
	 * method to load the previous results screen after coming back from edit,create etc
	 * @param HttpServletRequest,HttpServletResponse
	 * @return modelAndView(advancesearchresultbx.jsp)
	 */
	public ModelAndView advanceSearchHistory(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		HttpSession session = request.getSession();

		SearchCriteria searchCriteria = (SearchCriteria) session
				.getAttribute("searchCriteria");

		Page page = (Page) session.getAttribute("page");

		session.setAttribute("searchCriteria", searchCriteria);
		session.setAttribute("page", page);

		return getAdvanceSearchResultView(page, advancedSearchFacade
				.getAdvanceSearchRecords(searchCriteria, 50, page),
				searchCriteria, "true");

	}
	
	/**
	 * method to calculate and load the pagination
	 */
	private Page getPage(String currentPage, String section,
			int totalNoOfRecords) {
		if (totalNoOfRecords == 0) {
			totalNoOfRecords = 1;
		}
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		page.setTotalNoOfRecords(totalNoOfRecords);

		if (section.equalsIgnoreCase("Init")) {
			massUpdateTracker.clearSelectedRecords();
			section = "First";
		}

		if (section.equalsIgnoreCase("Refresh")) {
			massUpdateTracker.clearSelectedRecords();
			return page;
		}

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
	 * method to generate excel report for advance search 
	 * @param response
	 * @param request
	 * @return modelAndView
	 */
	public ModelAndView generateExcelReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> infoMessages = new ArrayList();
		long starttime = System.currentTimeMillis();
		List variableList = null;
		boolean isUnMappedForExcel =  false;
		HttpSession session = request.getSession();
		ModelAndView modelAndView = null;
		SearchCriteria searchCriteria = (SearchCriteria) session
				.getAttribute("searchCriteria");
		log.info(">>>>>>>>>> Search criteria inside generate Excel Report is "+ searchCriteria);
		if (null != searchCriteria) {
			variableList = advancedSearchFacade
					.getRecordsForReport(searchCriteria);
		}
		if (variableList == null) {
			session.setAttribute("searchCriteria", searchCriteria);
			//Page page = (Page) session.getAttribute("page");
			//modelAndView = viewSearchAfterPrint(request, response);
			
			
			/*
			 * session.setAttribute("searchCriteria", searchCriteria);
			 * //session.setAttribute("page", page);
			 * 
			 * Map map = new HashMap(); map.put("fromHistory","false");
			 * map.put("searchCriteria", searchCriteria);
			 * 
			 * modelAndView = new ModelAndView("advancesearch",map);
			 * infoMessages.add("Dear User,\n You have requested to export  more than " +
			 * maxLimit +
			 * " records. \n This will be extracted in a batch program and will be sent within 30 minutes through email."
			 * + "\nRequests below " + maxLimit +
			 * " records can be processed and exported instantly.");
			 * 
			 * sessionMessageTray.setInformationMessages(infoMessages);
			 * modelAndView.addObject(WebConstants.INFO_MESSAGES,
			 * sessionMessageTray.getAndClearInformationMessages()).addObject(
			 * WebConstants.ERROR_MESSAGES,
			 * sessionMessageTray.getAndClearErrorMessages()).addObject(
			 * WebConstants.WARNING_MESSAGES,
			 * sessionMessageTray.getAndClearWarningMessages());
			 */
			
			
			//return modelAndView;
			
			
			  infoMessages.add("Dear User,\n You have requested to export  more than " +
			  maxLimit +
			  " records. \n This will be extracted in a batch program and will be sent within 30 minutes through email."
			  + "\nRequests below " + maxLimit +
			  " records can be processed and exported instantly.");
			  
			  sessionMessageTray.setInformationMessages(infoMessages); 
			  modelAndView = WebUtil.redirectToWPDAdvanceSearchPageFromResult(request);
			  
			  return modelAndView;
			 

		} else {
			if (null != searchCriteria) {
				if (searchCriteria.isUnMapped() || (!searchCriteria.isUnMapped() && !searchCriteria.isMapped()
						&& !searchCriteria.isNotApplicable() && !searchCriteria.isNotFinalized())) {
					isUnMappedForExcel = true;
				}
			}

			long startTime = System.currentTimeMillis();
			ExcelSearchView excelview = new ExcelSearchView(variableList, isUnMappedForExcel);
			
			modelAndView = new ModelAndView(excelview);
			long endTime = System.currentTimeMillis();
			log.debug("Time after generating Excel" + (endTime - startTime));

			long endtime = System.currentTimeMillis();
			log.info(">>>>>>>>>> Time taken for advance search variable controller processing is "
					+ (endtime - starttime));
			return modelAndView;
		}
	}
	
	
	/***************************January Release*************************************/	
	/**
	 * The method populates the System and Start Date for a Contract to load the System 
	 * and Start date in the advance search page while a contract id is entered.
	 * @param response
	 * @param request
	 * @return modelAndView
	 */
	
	public ModelAndView populateSystemAndStartDateForContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		String contractId = request.getParameter("contractId").toUpperCase().trim();
		String systemNameForContract = request.getParameter("systemNameForContract");
		String startDateForContract = request.getParameter("startDateForContract");
		List contractStartDateList =  null;
		List systemList =  null;
		List contractSystemList = null;
		List startDateList =  new ArrayList();
		String contractSystem="";
		Map revisonDateMap = new HashMap();
		List revisionDatesList = null;
		if(systemNameForContract == null){	
		contractSystemList = advancedSearchFacade.populateSystemAndStartDateForContract(contractId,contractSystem, false,null);
		if(null != contractSystemList){
			for(int i=0;i<contractSystemList.size();i++){
				SearchResult searchResult=(SearchResult) contractSystemList.get(i);
				contractSystem = searchResult.getContractSystem();
			}
		}
			if(DomainConstants.CONTRACT_SYSTEM_LG.equals(contractSystem)){
				systemList = new ArrayList();
				systemList.add(DomainConstants.CONTRACT_SYSTEM_LG);
				contractStartDateList = advancedSearchFacade.populateSystemAndStartDateForContract(contractId, contractSystem, true,null);
				if(null !=contractStartDateList){
					for(int i=0;i<contractStartDateList.size();i++){
						SearchResult searchResult=(SearchResult) contractStartDateList.get(i);
						if (!startDateList.contains(searchResult.getContractStartDate())) {
						startDateList.add(searchResult.getContractStartDate());
						}
					}
				}
				map.put("rows", getListOfStartDates(startDateList));
				map.put("system", getListOfSystem(systemList));
				map.put("rowsRev","");
				map.put("systemForContract", DomainConstants.CONTRACT_SYSTEM_LG);

			}else if(DomainConstants.CONTRACT_SYSTEM_ISG.equals(contractSystem)){
				systemList = new ArrayList();
				systemList.add(DomainConstants.CONTRACT_SYSTEM_ISG);
				contractStartDateList = advancedSearchFacade.populateSystemAndStartDateForContract(contractId, contractSystem, true,null);
				if(null !=contractStartDateList){
					for(int i=0;i<contractStartDateList.size();i++){
						SearchResult searchResult=(SearchResult) contractStartDateList.get(i);
						if (!startDateList.contains(searchResult.getContractStartDate())) {
							startDateList.add(searchResult.getContractStartDate());
						}
						//populates revision date for ISG contract --Jan Release CR
						if (revisonDateMap.get(searchResult.getContractStartDate()) == null) {
							revisionDatesList = new ArrayList();
							revisionDatesList.add(searchResult.getISGContractRevisionDate());
                              revisonDateMap.put(searchResult.getContractStartDate(), revisionDatesList);
                        }
                        else {
                        	revisionDatesList = (List)revisonDateMap.get(searchResult.getContractStartDate());
                        	revisionDatesList.add(searchResult.getISGContractRevisionDate());
                        }
					}
				}
				map.put("rows", getListOfStartDates(startDateList));
				map.put("system", getListOfSystem(systemList));
				map.put("systemForContract", DomainConstants.CONTRACT_SYSTEM_ISG);
				if(null != startDateList){
					List revList = (List)revisonDateMap.get(startDateList.get(0));
					if(revList.size() > 1){
						map.put("rowsRev", getListOfStartDates(revList));
					}
					else{
						map.put("rowsRev","");
					}
				}
			}else if(DomainConstants.CONTRACT_SYSTEM_LG_ISG.equals(contractSystem)){
				systemList = new ArrayList();
				systemList.add(DomainConstants.CONTRACT_SYSTEM_ISG);
				systemList.add(DomainConstants.CONTRACT_SYSTEM_LG);
				contractStartDateList = advancedSearchFacade.populateSystemAndStartDateForContract(contractId, DomainConstants.CONTRACT_SYSTEM_ISG, true,startDateForContract);
				if(null !=contractStartDateList){
					for(int i=0;i<contractStartDateList.size();i++){
						SearchResult searchResult=(SearchResult) contractStartDateList.get(i);
						if (!startDateList.contains(searchResult.getContractStartDate())) {
						startDateList.add(searchResult.getContractStartDate());
						}
						//populates revision date for ISG contract --Jan Release CR
						if (revisonDateMap.get(searchResult.getContractStartDate()) == null) {
							revisionDatesList = new ArrayList();
							revisionDatesList.add(searchResult.getISGContractRevisionDate());
                              revisonDateMap.put(searchResult.getContractStartDate(), revisionDatesList);
                        }
                        else {
                        	revisionDatesList = (List)revisonDateMap.get(searchResult.getContractStartDate());
                        	revisionDatesList.add(searchResult.getISGContractRevisionDate());
                        }
					}
				}
				map.put("rows", getListOfStartDates(startDateList));
				map.put("system", getListOfSystemForBoth(systemList));
				map.put("systemForContract", DomainConstants.CONTRACT_SYSTEM_LG_ISG);
				if(null != startDateList){
					List revList = (List)revisonDateMap.get(startDateList.get(0));
					if(revList.size() > 1){
						map.put("rowsRev", getListOfStartDates(revList));
					}
					else{
						map.put("rowsRev","");
					}
				}
			}else{
				map.put("system", "0");
				map.put("rows", "0");
				map.put("rowsRev","");
			}
		}else{
			contractStartDateList = advancedSearchFacade.populateSystemAndStartDateForContract(contractId, systemNameForContract, true,startDateForContract);
			if(null !=contractStartDateList){
				for(int i=0;i<contractStartDateList.size();i++){
					SearchResult searchResult=(SearchResult) contractStartDateList.get(i);
					if (!startDateList.contains(searchResult.getContractStartDate())) {
					startDateList.add(searchResult.getContractStartDate());
					}
					//populates revision date for ISG contract  --Jan Release CR
					if (revisonDateMap.get(searchResult.getContractStartDate()) == null) {
						revisionDatesList = new ArrayList();
						revisionDatesList.add(searchResult.getISGContractRevisionDate());
                          revisonDateMap.put(searchResult.getContractStartDate(), revisionDatesList);
                    }
                    else {
                    	revisionDatesList = (List)revisonDateMap.get(searchResult.getContractStartDate());
                    	revisionDatesList.add(searchResult.getISGContractRevisionDate());
                    }
				}
			}
			map.put("rows", getListOfStartDates(startDateList));
			map.put("systemForContract", systemNameForContract);
			if(null != startDateList){
				List revList = (List)revisonDateMap.get(startDateList.get(0));
				if(revList.size() > 1){
					map.put("rowsRev", getListOfStartDates(revList));
				}
				else{
					map.put("rowsRev","");
				}
			}
		}
		return new ModelAndView("jsonView",map);
	}
	/***************************January Release*************************************/		
	private String getListOfStartDates(List list){
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<list.size(); i++){
			buffer.append("<option value = '").append(list.get(i)).append("'>").append(list.get(i)).append("</option>");	
		}
		return buffer.toString();
	}
	
	private String getListOfSystem(List list){
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<list.size(); i++){
			buffer.append("<option value = '").append(list.get(i)).append("'>").append(list.get(i)).append("</option>");	
		}	
		return buffer.toString();
	}
	private String getListOfSystemForBoth(List list){
		StringBuffer buffer = new StringBuffer();
		//buffer.append("<option value = ''>&#160</option>");
		for(int i=0; i<list.size(); i++){
			buffer.append("<option value = '").append(list.get(i)).append("'>").append(list.get(i)).append("</option>");	
		}	
		return buffer.toString();
	}
	/***************************January Release*************************************/	
	
	
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}

	/**
	 * @return Returns the advancedSearchFacade.
	 */
	public AdvanceSearchFacade getAdvancedSearchFacade() {
		return advancedSearchFacade;
	}

	/**
	 * @param advancedSearchFacade
	 *            The advancedSearchFacade to set.
	 */
	public void setAdvancedSearchFacade(AdvanceSearchFacade advancedSearchFacade) {
		this.advancedSearchFacade = advancedSearchFacade;
	}

	public MassUpdateTracker getMassUpdateTracker() {
		return massUpdateTracker;
	}

	public void setMassUpdateTracker(MassUpdateTracker massUpdateTracker) {
		this.massUpdateTracker = massUpdateTracker;
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
}