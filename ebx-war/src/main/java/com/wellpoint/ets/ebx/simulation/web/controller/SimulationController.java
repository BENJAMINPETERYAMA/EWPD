/*
 * <SimulationController.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.jms.JmsException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.WebUtil;
import com.wellpoint.ets.ebx.simulation.application.SimulationFacade;
import com.wellpoint.ets.ebx.simulation.util.SimulationHelper;
import com.wellpoint.ets.ebx.simulation.util.x12parser.X12Parser;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Interchange;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2100;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.AAA;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.simulation.vo.MemberInfoVO;
import com.wellpoint.ets.ebx.simulation.web.view.DynamicExcelView;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

/**
 * @author UST-GLOBAL
 * 
 *         Controller class. This class gets the contract/member details from
 *         jsp for all the three services and create the input object. with the
 *         input details, Facade layer is invoked and the result is displayed to
 *         user.
 * 
 */
public class SimulationController extends MultiActionController {

	private static Logger logger = Logger.getLogger(SimulationController.class);

	private SimulationFacade simulationFacade;

	private boolean eBxReportFlag = false;
	
	public static final List errorValidationSystemList = WebUtil.getSystemListForErrorValidation();
	public static final List contractInformationSystemList = WebUtil.getSystemListForContractInformation();

	/**
	 * get27xHIPAABX creates the X12 Request and get the response.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws JMSException
	 */
	public ModelAndView get27xHIPAABX(HttpServletRequest request,
			HttpServletResponse response) throws JMSException {
		logger.info("In Controller - get27xHIPAABX()");
		SimulationHelper simulationHelper = new SimulationHelper();
		// Initializing Variables
		long time;
		time = System.currentTimeMillis();
		HIPAA270BXVO hipaa270bxvo = new HIPAA270BXVO();
		MemberInfoVO memberInfoVo = new MemberInfoVO();

		// Getting the values from the request
		String serviceTypeCode = request.getParameter("serviceTypecodeX12");
		String startDate = request.getParameter("startDateX12");
		String endDate = request.getParameter("endDateX12");
		String dependentInformation = request.getParameter("dependentInfo");
		String memberId = request.getParameter("memberIDX12");
		String alphaPrefix = request.getParameter("alphaPrefixX12");
		String firstName = request.getParameter("firstNameX12");
		String lastName = request.getParameter("lastNameX12");
		String dateOfBirth = request.getParameter("birthDateX12");
		// Set the environment for the MQ call
		String environment = request.getParameter("environment");
		String is4010Exists = ESAPI.encoder().encodeForHTML(request.getParameter("is4010Exists"));
		if(BxUtil.regExPatterValidation(is4010Exists)){
			is4010Exists = is4010Exists;
		}else{
			is4010Exists=null;
		}

		request.setAttribute("is4010Exists", is4010Exists);
		String responseFormat = request.getParameter("responseFormat");
		String senderID = request.getParameter("senderID");
		if(StringUtils.isBlank(responseFormat)){
			responseFormat="5010";
		}
		logger.info("Response Format is:" +ESAPI.encoder().encodeForHTML(responseFormat));
		// setting MemberInfoVo Object
		memberInfoVo.setAlphaPrefix(alphaPrefix);
		memberInfoVo.setMemberId(memberId);
		memberInfoVo.setFirstName(firstName);
		memberInfoVo.setLastName(lastName);
		memberInfoVo.setDateOfBirth(dateOfBirth);
		if (!StringUtils.isBlank(dependentInformation)
				&& dependentInformation.equals("true")) {
			memberInfoVo.setDependentInformation(true);
		} else {
			memberInfoVo.setDependentInformation(false);
		}

		// setting HIPAA270BVO Object
		hipaa270bxvo.setServiceTypeCode(serviceTypeCode);
		hipaa270bxvo.setStartDate(startDate);
		hipaa270bxvo.setEndDate(endDate);
		hipaa270bxvo.setMemberInfo(memberInfoVo);

		// Calling the Hipaa Service.
		String x12Response = "";
		try {
			x12Response = simulationFacade.get27xHIPAABX(hipaa270bxvo,
					environment, responseFormat, senderID);
		} catch (EBXException e) {
			logger.error("EBXException: " + e.getMessage());
			ModelAndView mv = new ModelAndView("simulationrequest");
			List list = new ArrayList();

			list.add(e.getMessage());
			mv.addObject("error_messages", list);
			return mv;
		}
		request.setAttribute("x12Response", x12Response);
		ModelAndView modelAndView = new ModelAndView("generate271");
		
		if (StringUtils.containsIgnoreCase(x12Response, "AAA*")) {
			X12Parser x12Parser = new X12Parser();
			String aaaCode = "";
			Interchange interchange = null;
			try {
				interchange = x12Parser.parse271Response(x12Response);
				
			} catch (Exception e) {
				logger.info("X12 Parsing failed");
				e.printStackTrace();				
			}
			if (null!= interchange) {				
				List<AAA> aaaList = simulationHelper.extractAllAAACodes(interchange);
				for (AAA aaa : aaaList) {
					if (null != aaa) {						
						aaaCode += aaa.getAaa03Desc()+ ", " + aaa.getAaa04Desc()+". ";
					}
				}
				request.setAttribute("x12AAACode", aaaCode);
			}
			
		}
		
		time = System.currentTimeMillis() - time;
		logger.info(" The turn around time for X12 Information is " + time
				+ " milliseconds.");

		return modelAndView;
	}

	/**
	 * generate271Text saves the o/p of X12 as text file from the pop up
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView generate271Text(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("In Controller - generate271Text()");
		// Sets the content-disposition
		response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		response.setHeader("Cache-Control", "no-store,no-cache");
		response.setHeader("content-disposition", "attachment; filename="
				+ "X12Response.txt");
		// Sets the content type
		response.setContentType("text/plain");

		// Generate the formatted output to be saved.
		String formattedResponse = StringEscapeUtils.escapeHtml(request.getParameter("x12Response"));
		// Get the output stream and write the value into it.
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			logger.debug("IOException caught with message: " + e.getMessage());
		}
		out.print(formattedResponse);

		return null;
	}

	// TODO This method needs to be modified, for generating excel for X12 from
	// the pop up
	public ModelAndView generate271Excel(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("In Controller - generate271Excel()");

		SimulationHelper helper=new SimulationHelper();
		String xmlOrX12 = "X12";
		String formattedResponse = request.getParameter("x12Response");
		/* *****************
		 * BXNI November - No need format the incoming X12.
		//formattedResponse = helper.format27xHIPAABXResponse5010(formattedResponse);
		******************/
		response.setHeader("content-disposition", "attachment; filename="
				+ "271_Response.xls");
		DynamicExcelView dynamicView = new DynamicExcelView(xmlOrX12,
				formattedResponse);

		ModelAndView modelAndView = new ModelAndView(dynamicView);
		return modelAndView;
	}

	/**
	 * Method for getting contract information and performing Error Validation
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView getContractMappingValidationInfo(
			HttpServletRequest request, HttpServletResponse response)
			throws EBXException, Exception {
		long time;
		time = System.currentTimeMillis();
		ContractVO contract = new ContractVO();
		DynamicExcelView dynamicView = null;
		List systemNames = new ArrayList();
		// List with system names
		systemNames.add(DomainConstants.SYSTEM_EWPD);
		systemNames.add(DomainConstants.SYSTEM_ISG);
		systemNames.add(DomainConstants.SYSTEM_LG);
		StringBuffer fileName=new StringBuffer("Error Report");		
		String system = request.getParameter("systemName");
		BxUtil.validationVariable(system);
		String contractId = ESAPI.encoder().encodeForURL(request.getParameter("contractIDMapping"));
		if(null!=contractId && contractId.matches("[0-9a-zA-Z_]+")){
			contractId = contractId;
		}
		String effectiveDate = request.getParameter("startDateMapping");
		String versionNum = request.getParameter("versionNumberMapping");
		/**Added for BXNI requirement T6.1.1.2.WGSSTAR.eBX.6**/
		String environment = request.getParameter("environmentError");
		BxUtil.validationVariable(environment);
		/**End BXNI requirement T6.1.1.2.WGSSTAR.eBX.6**/
		String is4010Exists = ESAPI.encoder().encodeForHTML(request.getParameter("is4010Exists"));
		if(BxUtil.regExPatterValidation(is4010Exists)){
			is4010Exists = is4010Exists;
		}else{
			is4010Exists=null;
		}
		request.setAttribute("is4010Exists", is4010Exists);
		request.setAttribute("systemForErrorValidation", errorValidationSystemList);
		request.setAttribute("contractInformationSystemList", contractInformationSystemList);
		List <String> startDates = simulationFacade.getStartDates(system, contractId, environment);
		request.setAttribute("startDates", getListOfStartDates(startDates));
		int version = 0;
		try {
			if(!StringUtils.isEmpty(contractId) && !StringUtils.isEmpty(effectiveDate)){
				String newDate=effectiveDate.replaceAll("/", "-");
				//System.out.println("Effective Date from Page is:" + effectiveDate);
				//System.out.println("Modified Effective Date from Page is:" + newDate);
				logger.info("Effective Date from Page is:" +ESAPI.encoder().encodeForHTML(effectiveDate));
				logger.info("Modified Effective Date from Page is:" +ESAPI.encoder().encodeForHTML(newDate));
				fileName.append("_").append(contractId).append("_").append(newDate).append(".xls");
			}else{
				throw new EBXException("Contract Id and Effective date is mandatory");
			}
			if (!StringUtils.isBlank(versionNum)) {
				version = Integer.parseInt(request.getParameter(
						"versionNumberMapping").trim());
			}
			/**
			 * Validation for System
			 */
			if (!systemNames.contains(system.toUpperCase())) {
				throw new EBXException(DomainConstants.SYSTEM_MISMATCH);
			}
			// Setting input values
			contract.setSystem(system.trim());
			contract.setContractId(contractId.trim().toUpperCase());
			contract.setEffectiveDate(effectiveDate.trim());
			contract.setVersion(version);
			
			
			// Following values are hardcoded. This values are to be received from request and that requirement
			// will be only on next release. Adding this presently to avoid impact of may 2012 release
			
			String functionality = "STATIC REPORT";
			String systemSelected = "WGS/STAR";
			List<SystemConfigurationVO> systemConfigurationVOList = simulationFacade.loadBackEndRegionBasedOnSystem(
					functionality, systemSelected, environment);
			SystemConfigurationVO systemConfigurationVO = null;
			try{
				systemConfigurationVO = systemConfigurationVOList.get(0);
			}catch (Exception e){
				systemConfigurationVO = null;
			}
			 
			
			// Facade Invokation
			//Modified for BXNI requirement T6.1.1.2.WGSSTAR.eBX.6
			List contractVOList = simulationFacade.getContractInfo(contract,environment,
					isEBxReportFlag(),systemConfigurationVO);
			// Error Report View
			response.setHeader("content-disposition", "attachment; filename="
					+ fileName.toString());
			dynamicView = new DynamicExcelView(contractVOList);

			
			if(systemConfigurationVO != null){
				dynamicView.setBackEndRegion(systemConfigurationVO.getBackEndRegion());
			}	
			logger.info("In Controller - getContractMappingValidationInfo()");

			return new ModelAndView(dynamicView);
		} catch (EBXException ebx) {
			ModelAndView mv = new ModelAndView("simulationrequest");
			List list = new ArrayList();
			list.add(ebx.getMessage());
			mv.addObject("error_messages", list);
			if(simulationFacade.isInactiveContract(system, contractId)){
				List warings = new ArrayList();
				warings.add(getContractValidationWarning(contractId));
				mv.addObject("warning_messages", warings);				
			}
			request.setAttribute("system_val", (system == null ? "" : system));
			request.setAttribute("contract_id", (contractId == null ? ""
					: contractId));
			request.setAttribute("eff_date", (effectiveDate == null ? ""
					: effectiveDate));
			request.setAttribute("ver_num", (versionNum == null ? ""
					: versionNum));
			return mv;

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			time = System.currentTimeMillis() - time;
			logger.info(" The turn around time for Contract Validation is "
					+ time + " milliseconds.");

		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView Method creates the request from the contract object,
	 *         From the response creates the Excel
	 * @throws JMSException
	 */
	public ModelAndView getContractBenefitInfo(HttpServletRequest request,
			HttpServletResponse response) throws EBXException, JMSException,
			Exception {
		logger.info("Entering In Controller - getContractBenefitInfo()");
		long time;
		time = System.currentTimeMillis();
		ContractVO contract = new ContractVO();
		
		String contractId = request.getParameter("contractIdInfo");
		String startDate = request.getParameter("startDateInfo");
		String endDate = request.getParameter("endDateInfo");
		String environment = request.getParameter("environmentInfo");
		String dateOfBirth = request.getParameter("dateOfBirthInfo");
		
		
		String senderID = ESAPI.encoder().encodeForHTML(request.getParameter("staticReportSenderId")).trim();
		if(BxUtil.regExPatterValidation(senderID)){
			senderID = senderID;
		}else{
			senderID=null;
		}
		String sourceEnvironment = ESAPI.encoder().encodeForHTML(request.getParameter("sourceEnvironmentStaticReport")).trim();
		if(BxUtil.regExPatterValidation(sourceEnvironment)){
			sourceEnvironment = sourceEnvironment;
		}else{
			sourceEnvironment=null;
		}
		String destinationEnvironment = ESAPI.encoder().encodeForHTML(request.getParameter("destinationEnvironmentForStaticReport")).trim();	
		if(BxUtil.regExPatterValidation(destinationEnvironment)){
			destinationEnvironment = destinationEnvironment;
		}else{
			destinationEnvironment=null;
		}
		String backEndRegionFromRequest = ESAPI.encoder().encodeForHTML(request.getParameter("backEndRegionSelectedInStaticReport"));
		if(BxUtil.regExPatterValidation(backEndRegionFromRequest)){
			backEndRegionFromRequest = backEndRegionFromRequest;
		}else{
			backEndRegionFromRequest=null;
		}
		String backEndRegionSelected = "";
		if(backEndRegionFromRequest != null){
			backEndRegionSelected = backEndRegionFromRequest.trim();
		}		 
		String selectedBackEndRegionDescription = ESAPI.encoder().encodeForHTML(request.getParameter("selectedBackEndRegionDescriptionInStaticReport")).trim(); 
		if(BxUtil.regExPatterValidation(selectedBackEndRegionDescription)){
			selectedBackEndRegionDescription = selectedBackEndRegionDescription;
		}else{
			selectedBackEndRegionDescription=null;
		}
		String numberOfConfigurationsLoadedInStaticReport = ESAPI.encoder().encodeForHTML(request.getParameter("numberOfConfigurationsLoadedInStaticReport")).trim();
		if(BxUtil.regExPatterValidation(numberOfConfigurationsLoadedInStaticReport)){
			numberOfConfigurationsLoadedInStaticReport = numberOfConfigurationsLoadedInStaticReport;
		}else{
			numberOfConfigurationsLoadedInStaticReport=null;
		}
		Object populatedBackEndRegionsFromRequest = StringEscapeUtils.escapeHtml(request.getParameter("populatedBackEndRegionsAsJson"));
		if(null!=populatedBackEndRegionsFromRequest  && populatedBackEndRegionsFromRequest.toString().matches("^[0-9a-zA-Z_]+$")){
			populatedBackEndRegionsFromRequest = populatedBackEndRegionsFromRequest;
		}else{
			populatedBackEndRegionsFromRequest=null;
		}
		if(populatedBackEndRegionsFromRequest != null && ((String) populatedBackEndRegionsFromRequest).matches("^[0-9a-zA-Z_]+$")){
			JSONArray populatedBackEndRegionsAsJson = JSONArray.fromObject(populatedBackEndRegionsFromRequest);	
			request.setAttribute("populatedBackEndRegionsAsJson", populatedBackEndRegionsAsJson);
		}
		SystemConfigurationVO systemConfigurationVO = new SystemConfigurationVO();
		systemConfigurationVO.setSenderID(senderID);
		systemConfigurationVO.setSourceEnvironment(sourceEnvironment);
		systemConfigurationVO.setDestinationEnvironment(destinationEnvironment);
		// Setting system and ServiceTypeCode for Change Request
		String system = ESAPI.encoder().encodeForURL(request.getParameter("systemInfo").trim());
		String serviceTypeCode = request.getParameter("serviceTypeCodeInfo");
		String is4010Exists = ESAPI.encoder().encodeForHTML(request.getParameter("is4010Exists"));
		if(BxUtil.regExPatterValidation(is4010Exists)){
			is4010Exists = is4010Exists;
		}else{
			is4010Exists=null;
		}
		request.setAttribute("is4010Exists", is4010Exists);
		request.setAttribute("systemForErrorValidation", errorValidationSystemList);
		request.setAttribute("contractInformationSystemList", contractInformationSystemList);
		request.setAttribute("startDates", getListOfStartDates(new ArrayList<String>()));
		String responseFormat = request.getParameter("responseFormatInfo");
		request.setAttribute("selectedBackEndRegionDescription", selectedBackEndRegionDescription);
		request.setAttribute("senderID", senderID);
		request.setAttribute("selectedBackEndRegionDescription", selectedBackEndRegionDescription);
		request.setAttribute("sourceEnvironment", sourceEnvironment);
		request.setAttribute("destinationEnvironment", destinationEnvironment);
		request.setAttribute("backEndRegionSelected", backEndRegionSelected);
		request.setAttribute("numberOfConfigurationsLoadedInStaticReport", numberOfConfigurationsLoadedInStaticReport);
		
		if(StringUtils.isBlank(responseFormat)){
			responseFormat="5010";
		}
		logger.info("Response Format is:" +ESAPI.encoder().encodeForHTML(responseFormat));
		contract.setContractId(contractId);
		//contract.setEffectiveDate(effectiveDate);
		contract.setStartDate(startDate);
		contract.setEndDate(endDate);
		contract.setSystem(system.toUpperCase());
		contract.setServiceTypeCode(serviceTypeCode);
		contract.setDateOfBirth(dateOfBirth);

		logger.debug("ContractId: " +ESAPI.encoder().encodeForHTML(contractId));
		logger.debug("startDate: " +ESAPI.encoder().encodeForHTML(startDate));
		logger.debug("endDate: " +ESAPI.encoder().encodeForHTML(endDate));
		logger.debug("environment: " +ESAPI.encoder().encodeForHTML(environment));
		logger.debug("system: " +ESAPI.encoder().encodeForHTML(system));
		logger.debug("serviceTypeCode: " +ESAPI.encoder().encodeForHTML(serviceTypeCode));
		logger.debug("DateOfBirth: " +ESAPI.encoder().encodeForHTML(dateOfBirth));

		try {
			String xmlOrX12 = "XML";

			List contractsListPagination = simulationFacade
					.get27xBenefitAccumInfo(contract, environment, responseFormat,systemConfigurationVO);

			if (null != contractsListPagination
					&& contractsListPagination.size() > 0) {
				for (int i = 0; i < contractsListPagination.size(); i++) {
					ContractVO contractVO = (ContractVO) contractsListPagination
							.get(i);
					if (null != contractVO) {
						if (null == contractVO.getContractId()
								|| "".equals(contractVO.getContractId())) {
							contractVO.setContractId(contractId);
						}
					}
				}
			}
			response.setHeader("content-disposition", "attachment; filename="
					+ contract.getSystem()+"_"+contract.getContractId()+"_BenefitInfo.xls");
			DynamicExcelView dynamicView = new DynamicExcelView(xmlOrX12,
					contractsListPagination);
			logger.info("Exiting from Controller - getContractBenefitInfo()");
			return new ModelAndView(dynamicView);
		} catch (JmsException jmsEx) {
			ModelAndView mv = new ModelAndView("simulationrequest");
			List list = new ArrayList();
			logger.error("JmsException: " + jmsEx.getMessage());
			list.add(jmsEx.getMessage());
			mv.addObject("error_messages", list);
			if(BxUtil.hasText(system) && "WGS".equals(system.trim())){
				if(simulationFacade.isInactiveContract("ALL", contractId)){
					List warings = new ArrayList();
					warings.add(getContractValidationWarning(contractId));
					mv.addObject("warning_messages", warings);				
				}
			}
			request.setAttribute("contract_Id_val", (contractId == null ? ""
					: contractId));
			request.setAttribute("start_Date_val", (startDate == null ? ""
					: startDate));
			
			request.setAttribute("end_Date_val", (endDate == null ? ""
					: endDate));
			
			request.setAttribute("birth_Date_val", (dateOfBirth == null ? ""
					: dateOfBirth));

			// setting back in the request
			request.setAttribute("system_info", (system == null ? "" : system));
			request.setAttribute("service_Type_Code_val",
					(serviceTypeCode == null ? "" : serviceTypeCode));
			request.setAttribute("environment_info", 
					(environment == null ? "" : environment));
			request.setAttribute("responseformat_info", 
					(responseFormat == null ? "" : responseFormat));
			
			logger.info("Exiting from Controller - getContractBenefitInfo()");
			return mv;
		} catch (EBXException e) {
			logger.error("EBXException: " + e.getMessage());
			ModelAndView mv = new ModelAndView("simulationrequest");
			List list = new ArrayList();

			list.add(e.getMessage());
			mv.addObject("error_messages", list);
			if(BxUtil.hasText(system) && "WGS".equals(system.trim())){
				if(simulationFacade.isInactiveContract("ALL", contractId)){
					List warings = new ArrayList();
					warings.add(getContractValidationWarning(contractId));
					mv.addObject("warning_messages", warings);				
				}
			}
			request.setAttribute("contract_Id_val", (contractId == null ? ""
					: contractId));
			request.setAttribute("start_Date_val", (startDate == null ? ""
					: startDate));
			
			request.setAttribute("end_Date_val", (endDate == null ? ""
					: endDate));
			request.setAttribute("birth_Date_val", (dateOfBirth == null ? ""
					: dateOfBirth));

			
			// setting back in the request
			request.setAttribute("system_info", (system == null ? "" : system));
			request.setAttribute("service_Type_Code_val",
					(serviceTypeCode == null ? "" : serviceTypeCode));
			request.setAttribute("environment_info", 
					(environment == null ? "" : environment));
			request.setAttribute("responseformat_info", 
					(responseFormat == null ? "" : responseFormat));
			logger.info("Exiting from Controller - getContractBenefitInfo()");
			return mv;

		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw new Exception(e);
		} finally {
			time = System.currentTimeMillis() - time;
			logger
					.info(ESAPI.encoder().encodeForHTML(" The turn around time for static benefit information is "
							+ time
							+ " milliseconds for Contract Id ="
							+ contract.getContractId()));

		}

	}

	/**
	 * viewSimulationRequest mehtod is the entry point for the simulation Tool
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView viewSimulationRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("In Controller - viewSimulationRequest()");
		ModelAndView modelAndView = new ModelAndView("simulationrequest");
		String is4010Exists="false";
		if(simulationFacade.is4010Exists()){
			is4010Exists="true";
		}
		ResourceBundle resourceBundleBx = ResourceBundle.getBundle(DomainConstants.BX_PROP_FILE_NAME,Locale.getDefault());
        String newBXUrl = resourceBundleBx.getString(DomainConstants.NEW_BX_URL);
        request.setAttribute("newBXUrl", newBXUrl);
		request.setAttribute("systemForErrorValidation", errorValidationSystemList);
		request.setAttribute("contractInformationSystemList", contractInformationSystemList);
		request.setAttribute("startDates", getListOfStartDates(new ArrayList<String>()));
		request.setAttribute("is4010Exists", is4010Exists);
		return modelAndView;
	}

	/**
	 * @return Returns the simulationFacade.
	 */
	public SimulationFacade getSimulationFacade() {
		return simulationFacade;
	}

	/**
	 * @param simulationFacade
	 *            The simulationFacade to set.
	 */
	public void setSimulationFacade(SimulationFacade simulationFacade) {
		this.simulationFacade = simulationFacade;
	}

	public boolean isEBxReportFlag() {
		return eBxReportFlag;
	}

	public void setEBxReportFlag(boolean bxReportFlag) {
		eBxReportFlag = bxReportFlag;
	}
	
	public void validateContractId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String contractId = ESAPI.encoder().encodeForHTML(request.getParameter("contractId"));
		String system = request.getParameter("system");
		String warning = "";
		if(simulationFacade.isInactiveContract(system, contractId)){
			warning = getContractValidationWarning(contractId);
		}
		response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
		response.getWriter().write(warning);
		
	}
	
	private String getContractValidationWarning(String contractId){
		return "Archival Indicator of Contract with id "+contractId+" is INACTIVE";
	}
	// BXNI June Release change - Start
	/**
	 * Method to populate the start dates of the LG, ISG and eWPD contracts.
	 * This method also returns latest version of an eWPD contract.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView populateStartDateAndVersionOfContract(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String> ();
		String system = request.getParameter("systemName");
		String contractId = request.getParameter("contractIDMapping");
		String enviornment = request.getParameter("environment");
		String latestVersion = "";
		List<String> startDates = new ArrayList<String>();
		if (DomainConstants.SYSTEM_EWPD.equals(system)) {
			latestVersion = simulationFacade.getLatestVersion(contractId);
			startDates = simulationFacade.getStartDates(system, contractId,"");
			map.put("rows", getListOfStartDates(startDates));
			map.put("version", latestVersion);
			map.put("systemForContract", DomainConstants.SYSTEM_EWPD);
			
		} else {
			startDates = simulationFacade.getStartDates(system, contractId,enviornment);
			map.put("rows", getListOfStartDates(startDates));
		}
		return new ModelAndView("jsonView",map);
	}
	/**
	 * This method format the list input a corresponding HTML format for a drop down.
	 * @param list
	 * @return
	 */
	private String getListOfStartDates(List<String> list){
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<list.size(); i++){
			buffer.append("<option value = '").append(list.get(i)).append("'>").append(list.get(i)).append("</option>");	
		}
		return buffer.toString();
	}
	// BXNI June Release change - End
	
	public ModelAndView getBackEndRegionDescription(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		ModelAndView modelAndView = null;
		List<SystemConfigurationVO> systemConfigurationVOList = new ArrayList<SystemConfigurationVO>();
		try {
			SystemConfigurationVO systemConfigurationVO = null;
			modelAndView = new ModelAndView("jsonView");
			String system = (String) request.getParameter("system");
			String functionality = (String) request
					.getParameter("functionality");
			String environment = (String) request.getParameter("env");
			String backEndRegion = (String) request.getParameter("backEndRegion");			
			systemConfigurationVO = new SystemConfigurationVO();
			systemConfigurationVO.setFunctionality(functionality);
			systemConfigurationVO.setEnvironment(environment);
			systemConfigurationVO.setSystem(system);
			systemConfigurationVO.setBackEndRegion(backEndRegion);			
			systemConfigurationVOList = simulationFacade
					.getBackEndRegionDescription(systemConfigurationVO);
			JSONArray backEndRegionDetailsAsJson = JSONArray
			.fromObject(systemConfigurationVOList);
			modelAndView.addObject("backEndRegionDetailsAsJson",
			backEndRegionDetailsAsJson);
			
		} catch (Exception exception) {
			if (exception instanceof EBXException) {
				throw ((EBXException) exception);
			} else {
				logger
						.error("Unexpected exception caught at method getBackEndRegionDescription in "
								+ "SimulationController class");
				String message = "Unexpected Error";
				String displayMessage = "Unexpected Error. Contact System Administrator";
				String displayDescription = "Unexpected Error. Contact System Administrator";
				throw new EBXException(message, exception, displayMessage,
						displayDescription);
			}
		}
		return modelAndView;
	}

	/**
	 * This method gets called when user selects a System from the drop down list available in 
	 * 270/271 operation
	 * @param request
	 * @param response
	 * @return
	 * @throws EBXException
	 */
	public ModelAndView loadBackEndRegionBasedOnSystem(HttpServletRequest request,
			HttpServletResponse response) throws EBXException {
		ModelAndView modelAndView = null;
		List<SystemConfigurationVO> systemConfigurationVOList = null;
		String noConfigurationExists = "false";
		try {
			String system = (String) request.getParameter("system");
			String functionality = (String) request
					.getParameter("functionality");
			String environment = (String) request.getParameter("env");
			modelAndView = new ModelAndView("jsonView");
			systemConfigurationVOList = simulationFacade.loadBackEndRegionBasedOnSystem(
					functionality, system, environment);
			if (systemConfigurationVOList == null
					|| systemConfigurationVOList.size() == 0) {
				noConfigurationExists = "true";
			} else {
				JSONArray backEndRegionDetailsAsJson = JSONArray
						.fromObject(systemConfigurationVOList);
				modelAndView.addObject("backEndRegionDetailsAsJson",
						backEndRegionDetailsAsJson);
			}
			modelAndView.addObject("noConfigurationExists",
					noConfigurationExists);
		} catch (Exception exception) {
			if (exception instanceof EBXException) {
				List<String> errorMessageList = new ArrayList<String>();
				errorMessageList.add(exception.getMessage());
				modelAndView.addObject("error_messages", errorMessageList);
				modelAndView.addObject("loadingStatus", "loadingFailed");
			} else {
				logger
						.error("Unexpected exception caught at method loadBackEndRegionBasedOnSystem in "
								+ "SimulationController class");
				String message = "Unexpected Error";
				String displayMessage = "Unexpected Error. Contact System Administrator";
				String displayDescription = "Unexpected Error. Contact System Administrator";
				throw new EBXException(message, exception, displayMessage,
						displayDescription);
			}
		}
		return modelAndView;
	}
}
