package com.wellpoint.ets.ebx.mapping.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.ContractBxReportFacade;
import com.wellpoint.ets.ebx.mapping.web.view.ExcelReportView;
import com.wellpoint.ets.ebx.mapping.web.view.PdfReportView;
import com.wellpoint.ets.ebx.mapping.web.view.TextReportView;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.web.controller.SimulationController;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class VieworExportReportController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(SimulationController.class);

	private ContractBxReportFacade contractBxReportFacade;
	
	private List cntrctList = new ArrayList();
	
	private boolean eBxReportFlag = false;

	/**
	 * @return the cntrctList
	 */
	public List getCntrctList() {
		return cntrctList;
	}

	/**
	 * @param cntrctList the cntrctList to set
	 */
	public void setCntrctList(List cntrctList) {
		this.cntrctList = cntrctList;
	}

	/**
	 * @param 
	 * @return 
	 * @exception 
	 *
	 */
	public ModelAndView generateExcel(HttpServletRequest request, HttpServletResponse response) throws EBXException,Exception {
		
		long generateExcelStartTime = System.currentTimeMillis();	
		ContractVO contract = new ContractVO();
		String system = request.getParameter("systemTypeForEbx");
		String contractId = ESAPI.encoder().encodeForURL(request.getParameter("contractId"));
		String effectiveDate = request.getParameter("effectiveDatePicker");
		String versionNumber = request.getParameter("versionValue");
		ModelAndView modelAndView = null;
		StringBuffer fileName=new StringBuffer("BX Report");		
		try {
				if(!StringUtils.isEmpty(contractId) && !StringUtils.isEmpty(effectiveDate)){
					String newDate=effectiveDate.replaceAll("/", "-");
					fileName.append("_").append(contractId).append("_").append(newDate).append(".xls");
				}else{
					throw new EBXException("Contract Id and Effective date is mandatory");
				}
				contract.setSystem(system.trim());
				contract.setContractId(contractId.trim().toUpperCase());
				contract.setEffectiveDate(effectiveDate.trim());
				int version = 0;
				if (!StringUtils.isBlank(versionNumber)) {
					version = Integer.parseInt(request.getParameter("versionNumber").trim());
				}
				contract.setVersion(version);
				//List contractList = contractBxReportFacade.getReportInfo(contract, true);
				
				HttpSession session = request.getSession();
				List contractList = (List)session.getAttribute("CONTRACT_LIST");
				//the below line is for test and should be commented for production.
				contractList = getCntrctList();
				session.removeAttribute("CONTRACT_LIST");
				response.setHeader("content-disposition", "attachment; filename=" + fileName.toString());
				ExcelReportView wpdExcelView = new ExcelReportView(contractList);
				modelAndView = new ModelAndView(wpdExcelView);
				
		} catch (Exception e) {
            throw new Exception(e);
        }
		long generateExcelEndTime = System.currentTimeMillis();
		logger.info("Time taken for generateExcel method = "+ (generateExcelEndTime - generateExcelStartTime));
		return modelAndView;
		
	}

	/**
	 * @param 
	 * @return 
	 * @exception 
	 *
	 */
	public ModelAndView generatePdf(HttpServletRequest request, HttpServletResponse response) throws EBXException,Exception {
		
		long generatePdfStartTime = System.currentTimeMillis();
		ContractVO contract = new ContractVO();
		String viewName = null;
		String system = request.getParameter("systemTypeForEbx");
		String contractId = ESAPI.encoder().encodeForURL(request.getParameter("contractId"));
		String effectiveDate = request.getParameter("effectiveDatePicker");
		String versionNumber = request.getParameter("versionValue");
		ModelAndView modelAndView = null;
		StringBuffer fileName=new StringBuffer("BX_Report");		
		try {
				if(!StringUtils.isEmpty(contractId) && !StringUtils.isEmpty(effectiveDate)){
					String newDate=effectiveDate.replaceAll("/", "-");
					fileName.append("_").append(contractId).append("_").append(newDate).append(".pdf");
				}else{
					throw new EBXException("Contract Id and Effective date is mandatory");
				}
				contract.setSystem(system.trim());
				contract.setContractId(contractId.trim().toUpperCase());
				contract.setEffectiveDate(effectiveDate.trim());
				int version = 0;
				if (!StringUtils.isBlank(versionNumber)) {
					version = Integer.parseInt(request.getParameter("versionNumber").trim());
				}
				contract.setVersion(version);
				//List contractList = contractBxReportFacade.getReportInfo(contract, true);
				
				HttpSession session = request.getSession();
				List contractList = (List)session.getAttribute("CONTRACT_LIST");
				//the below line is for test and should be commented for production.
				contractList = getCntrctList();
				session.removeAttribute("CONTRACT_LIST");
				
				PdfReportView wpdPdfView = new PdfReportView(contractList);	
				
				Map modelParams = null;
				modelParams = wpdPdfView.getContractReportData(contractList);
		
				if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())){
					viewName = "wpdPdfReport";
				}  else if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())) {
					viewName = "isgPdfReport";
				}  else if(DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(contract.getSystem())){
					viewName = "ewpdPdfReport";
				}
				response.setContentType("application/octet-stream");
				response.setHeader("content-disposition", "attachment; filename=" + fileName.toString());
				modelAndView = new ModelAndView(viewName, modelParams);
		} catch (Exception e) {
            throw new Exception(e);
        }
		long generatePdfEndTime = System.currentTimeMillis();
		logger.info("Time taken for generatePdf method = "+ (generatePdfEndTime - generatePdfStartTime));
       return modelAndView;
	}	

	
	
	/**
	 * @throws EncodingException 
	 * @param 
	 * @return 
	 * @exception 
	 *
	 */
	public ModelAndView generateText(HttpServletRequest request, HttpServletResponse response) throws EBXException, EncodingException {
		
		long generateTextStartTime = System.currentTimeMillis();
		ContractVO contract = new ContractVO();
		String system = request.getParameter("systemTypeForEbx");
		String contractId = ESAPI.encoder().encodeForURL(request.getParameter("contractId")); 
		String effectiveDate = request.getParameter("effectiveDatePicker");
		String versionNumber = request.getParameter("versionValue");
		ModelAndView modelAndView = null;
		String viewName = null;
		StringBuffer fileName=new StringBuffer("BX_Report");		
		try {	
				if(!StringUtils.isEmpty(contractId) && !StringUtils.isEmpty(effectiveDate)){
					String newDate=effectiveDate.replaceAll("/", "-");
					fileName.append("_").append(contractId).append("_").append(newDate).append(".txt");
				}else{
					throw new EBXException("Contract Id and Effective date is mandatory");
				}
				contract.setSystem(system.trim());
				contract.setContractId(contractId.trim().toUpperCase());
				contract.setEffectiveDate(effectiveDate.trim());
				int version = 0;
				if (!StringUtils.isBlank(versionNumber)) {
					version = Integer.parseInt(request.getParameter("versionNumber").trim());
				}
				contract.setVersion(version);
				//List contractList = contractBxReportFacade.getReportInfo(contract, true);
				
				HttpSession session = request.getSession();
				List contractList = (List)session.getAttribute("CONTRACT_LIST");
				//the below line is for test and should be commented for production.
				contractList = getCntrctList();
				session.removeAttribute("CONTRACT_LIST"); 
				
				TextReportView textReport = new TextReportView(contractList);
				
				Map modelParams = null;
				modelParams = textReport.getContractTextData(contractList);
				
				if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())){
					viewName = "wpdTextReport";
				}  else if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())) {
					viewName = "isgTextReport";
				}  else if(DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(contract.getSystem())){
					viewName = "ewpdTextReport";
				}
				response.setHeader("content-disposition", "attachment; filename=" + fileName.toString());
				modelAndView = new ModelAndView(viewName, modelParams);
				
		} /*catch (EBXException ebx) {
            ModelAndView mv = new ModelAndView("ebxreportview");
            List list = new ArrayList(); 
            list.add(ebx.getMessage());
            mv.addObject(WebConstants.ERROR_MESSAGES, list);
            request.setAttribute("system_val", (system == null ? "" : system));
            request.setAttribute("contract_id", (contractId == null ? "" : contractId));
            request.setAttribute("eff_date", (effectiveDate == null ? "" : effectiveDate));
            return mv;

        }*/ catch (Exception e) {
            e.printStackTrace();
        }
		long generateTextEndTime = System.currentTimeMillis();
		logger.info("Time taken for generatePdf method = "+ (generateTextEndTime - generateTextStartTime));
		return modelAndView;
		
	}
	
	public ModelAndView contractBxMapping(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		return new ModelAndView("ebxreportview");
		
	}
	
	public ModelAndView invalidReportInputs(HttpServletRequest request,
			HttpServletResponse response) throws EBXException,Exception {
		
		long invalidReportInputsStartTime = System.currentTimeMillis();
			ContractVO contract = new ContractVO();
			SystemConfigurationVO systemConfigurationVo= new SystemConfigurationVO();
			String system = request.getParameter("systemType");
			String contractId = request.getParameter("contractId");
			String effectiveDate = request.getParameter("effectiveDatePicker");
			String versionNum = request.getParameter("versionValue");
			String environment = request.getParameter("environmentId");
			ModelAndView modelAndView = new ModelAndView("jsonView");
		try {
				
				contract.setSystem(system.trim());
				contract.setContractId(contractId.trim().toUpperCase());
				contract.setEffectiveDate(effectiveDate.trim());
				int version = 0;
				if (!StringUtils.isBlank(versionNum)) {
					version = Integer.parseInt(request.getParameter("versionValue").trim());
				}
				contract.setVersion(version);
				List contractList = contractBxReportFacade.getReportInfo(contract,environment, true,systemConfigurationVo);
				HttpSession session = request.getSession(); 
				session.setAttribute("CONTRACT_LIST", contractList);
				setCntrctList(contractList);
				long invalidReportInputsEndTime = System.currentTimeMillis();
				logger.info("Time taken for invalidReportInputs method = "+ (invalidReportInputsEndTime - invalidReportInputsStartTime));
				return modelAndView;
				
		} catch (EBXException ebx) {
			List list = new ArrayList();
            list.add(ebx.getMessage());
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, list);
            return modelAndView;

        }
		
	}

	public ContractBxReportFacade getContractBxReportFacade() {
		return contractBxReportFacade;
	}

	public void setContractBxReportFacade(
			ContractBxReportFacade contractBxReportFacade) {
		this.contractBxReportFacade = contractBxReportFacade;
	}

	public boolean isEBxReportFlag() {
		return eBxReportFlag;
	}

	public void setEBxReportFlag(boolean bxReportFlag) {
		eBxReportFlag = bxReportFlag;
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
		Map <String, String> map = new HashMap <String, String> ();
		String system = request.getParameter("systemName");
		String contractId = request.getParameter("contractIDMapping");
		String enviornment = request.getParameter("environment");
		String latestVersion = "";
		List<String> startDates = new ArrayList<String>();
		if (DomainConstants.SYSTEM_EWPD.equals(system)) {
			latestVersion = contractBxReportFacade.getLatestVersion(contractId);
			startDates = contractBxReportFacade.getStartDates(system, contractId, "");
			map.put("rows", getListOfStartDates(startDates));
			map.put("version", latestVersion);
			map.put("systemForContract", DomainConstants.SYSTEM_EWPD);
			
		} else {
			startDates = contractBxReportFacade.getStartDates(system, contractId, enviornment);
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

}
