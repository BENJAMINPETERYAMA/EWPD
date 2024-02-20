package com.wellpoint.wpd.web.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.report.bo.ContractReportBean;
import com.wellpoint.wpd.common.report.bo.ContractReportCriteria;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

public class ReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5341073074836112412L;
	
	private static final String DEFAULT_FILE_NAME = "eWPD Contract Extract";
	private static final String DEFAULT_SHEET_NAME = "Contract Extract";
	
	private static final String REQ_PARAM_CONTRACT = "contractIdHidden";
	private static final String REQ_PARAM_START_DATE = "startDateHidden";
	private static final String REQ_PARAM_COMPONENTS = "componentHidden";
	private static final String REQ_PARAM_BENEFITS = "benefitHidden";
	private static final String REQ_PARAM_HEADERRULE_FLAG = "headerRuleFlag";
	private static final String REQ_PARAM_LINE_FLAG = "benefitLineFlag";
	private static final String REQ_PARAM_QUESTION_FLAG = "quesitonFlag";
	private static final String REQ_PARAM_METHOD_FLAG = "adminMethodFlag";
	private static final String REQ_PARAM_NOTE_FLAG = "notesFlag";
	private static final String REQ_PARAM_SINGLE_SHEET_FLAG = "singleSheetFlag";

	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("application/vnd.ms-excel");
			/*
			 * Modified Code to Resolve http, https issue as part of
			 * WAS 7 Migration Project
			 */
			// Prevent Caching of this page.
			//response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store,no-cache");
			// Set the Header Info to tell the browser the format of the file.
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			
			response.addHeader("Content-Disposition",
					"attachment; filename=\""+ getFileName(req) +"\"");
			
			req.getSession().setAttribute(WebConstants.CONTRACT_REPORT_RUNNING, "Y");
			
			createReport(req, response);
			
		} catch (SevereException ex) {
			Logger.logException(new SevereException("Unknown Error on Service call",new ArrayList(),ex.getCause()));
		} catch (IOException ex) {
			Logger.logException(new SevereException("Unknown Error on Service call",new ArrayList(),ex.getCause()));
		} catch (WriteException ex) {
			Logger.logException(new SevereException("Unknown Error on Service call",new ArrayList(),ex.getCause()));			
		} finally {
			req.getSession().removeAttribute(WebConstants.CONTRACT_REPORT_RUNNING);
		}
	}	
	
	private String getFileName( HttpServletRequest req) {
        List contracts = WPDStringUtil.getSplittedValues(ESAPI.encoder().encodeForHTML(req.getParameter(REQ_PARAM_CONTRACT)), ",");
		
        String fileName = DEFAULT_FILE_NAME + ".xls";
        if (contracts.size() == 1) {
        	fileName = DEFAULT_FILE_NAME + "_" + ((String)contracts.get(0)).replaceAll("\\*", "_") + ".xls";
        }
        return fileName;
	}
	
	private void createReport( HttpServletRequest req, HttpServletResponse response) throws SevereException, IOException, WriteException {

		List contractReportData = getReportData(req);
        
		boolean outputInSingleSheet = !getOutputIndiator(req, REQ_PARAM_SINGLE_SHEET_FLAG );
        
        // Generating Excel
		ServletOutputStream out = response.getOutputStream();
		WritableWorkbook workbook = Workbook.createWorkbook(out);
		
		if(contractReportData.size() == 0) {
			
			WritableSheet wsheet = workbook.createSheet(DEFAULT_SHEET_NAME,1);
			createHeaders(wsheet, new String [] {"****** No Data ******"});
			
		} else {
			String currentContractId = "";
			String currentStartDt = "";
			int currentIndex = 0;
			int dataSize = contractReportData.size();
			int excelRowIndex = 1;
			int currentSheet = 0;
			
			WritableSheet wsheet = null;
			
			WritableFont font = new WritableFont(WritableFont.ARIAL, 9,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat fontFormat = new WritableCellFormat(font);
			
			while( currentIndex < dataSize) {
				ContractReportBean reportBean = (ContractReportBean)contractReportData.get(currentIndex);
				
				if(!outputInSingleSheet || (currentIndex == 0)) {
					if(!currentContractId.equals(reportBean.getContractId()) || !currentStartDt.equals(reportBean.getStartDate())) {
						
						currentContractId = reportBean.getContractId();
						currentStartDt = reportBean.getStartDate();
						currentSheet ++;
						
						if(!outputInSingleSheet) {
							wsheet = workbook.createSheet(currentContractId 
									+" - "
									+ (currentStartDt.replaceAll("/", "")).replaceAll("\\*", "_"),
									currentSheet);							
						} else {
							wsheet = workbook.createSheet(DEFAULT_SHEET_NAME, 1); 
						}

						
						excelRowIndex = 1;
						//Pravinth Change Here added End Date
						createHeaders(wsheet, new String [] {
								"Contract",
								"Version",
								"Start Date",
								"End Date",
								"Benefit Component",
								"Benefit",
								"Type",
								"Tier",
								"Header Rule/Admin Option",
								"Question/ Line/ SPS Id",
								"Qual Freqncy",
								"Value/ Answer/ Admin Method",
								"Ref ID",
								"Ref Description",
								"Note ID",
								"Note Text"});						
					}
				}
				
				if(outputInSingleSheet && excelRowIndex == 65534) {
					Label label = new Label(0, excelRowIndex, "****** Data Excceeding size of excel. Remaining data is ignored *******" , fontFormat);
					wsheet.addCell(label);
					break;
				}
				
				addExcelEntry(wsheet, 0, excelRowIndex, reportBean.getContractId() , fontFormat);
				
				addExcelEntry(wsheet, 1, excelRowIndex, reportBean.getVersion() , fontFormat);
				
				addExcelEntry(wsheet, 2, excelRowIndex, reportBean.getStartDate() , fontFormat);

				// Pravinth Change here added entrry for end date
				addExcelEntry(wsheet, 3, excelRowIndex, reportBean.getEndDate() , fontFormat);
								
				addExcelEntry(wsheet, 4, excelRowIndex, reportBean.getBenefitComponent() , fontFormat);
				
				addExcelEntry(wsheet, 5, excelRowIndex, reportBean.getBenefit() , fontFormat);
				
				addExcelEntry(wsheet, 6, excelRowIndex, reportBean.getType() , fontFormat);
				
				addExcelEntry(wsheet, 7, excelRowIndex, reportBean.getTier() , fontFormat);
				
				addExcelEntry(wsheet, 8, excelRowIndex, reportBean.getHeaderRule() , fontFormat);
				
				addExcelEntry(wsheet, 9, excelRowIndex, reportBean.getLine() , fontFormat);
				
				addExcelEntry(wsheet, 10, excelRowIndex, reportBean.getQualFreeq() , fontFormat);
				
				addExcelEntry(wsheet, 11, excelRowIndex, reportBean.getValue() , fontFormat);
				
				addExcelEntry(wsheet, 12, excelRowIndex, reportBean.getRefId() , fontFormat);
				
				addExcelEntry(wsheet, 13, excelRowIndex, reportBean.getRefDesc() , fontFormat);
				
				String noteText = reportBean.getNoteText();
				if(noteText != null) {
					// Replacing '\r\n' to '\n' for showing it correctly in excel sheet.
					noteText = noteText.replaceAll("\\r\\n", "\n");

					int tildIndex = noteText.indexOf('~');
					
					addExcelEntry(wsheet, 14, excelRowIndex, noteText.substring(0, tildIndex) , fontFormat);
					
					addExcelEntry(wsheet, 15, excelRowIndex, noteText.substring(tildIndex+1) , fontFormat);
				}
				
				excelRowIndex++;
				currentIndex++;
			}
		}
		
		workbook.write();
		out.flush();
		workbook.close();			
	}
	
	private void addExcelEntry(WritableSheet wsheet, int col, int row, String data, WritableCellFormat fontFormat) throws WriteException{
		if(data != null) {
			Label label = new Label(col, row, data , fontFormat);
			wsheet.addCell(label);
		}
	}
	
	private List getReportData(HttpServletRequest req) throws SevereException {
		ContractReportCriteria reportCriteria = new ContractReportCriteria();
		
        // Contract Id
        String inputContracts = req.getParameter(REQ_PARAM_CONTRACT ); 
        List contractList = WPDStringUtil.getSplittedValues(inputContracts, ",");
		reportCriteria.setContractId(contractList);
        
        
        // Start Date
        String inputStartDate = req.getParameter(REQ_PARAM_START_DATE );
        if(inputStartDate != null && !"".equals(inputStartDate.trim())) {
        	inputStartDate = inputStartDate.trim();
        	if(WPDStringUtil.isValidDate(inputStartDate)) {
        		inputStartDate = WPDStringUtil.getStringDate(WPDStringUtil.getDateFromString(inputStartDate));
            	reportCriteria.setStartDate(inputStartDate);
        	}
        }

        // Benefit Component
        String inputComponents = req.getParameter(REQ_PARAM_COMPONENTS );
        List componentList = WPDStringUtil.getSplittedValues(inputComponents, "\n");
        reportCriteria.setBenefitComponents(componentList);

        
        // Benefits
        String inputBenefits = req.getParameter(REQ_PARAM_BENEFITS );
        List benefitList = WPDStringUtil.getSplittedValues(inputBenefits, "\n");
        reportCriteria.setBenefits(benefitList);


        // Output Configuraitons
        boolean headerRuleFlag = getOutputIndiator(req, REQ_PARAM_HEADERRULE_FLAG );
        boolean benefitLineFlag = getOutputIndiator(req, REQ_PARAM_LINE_FLAG );
        boolean quesitonFlag = getOutputIndiator(req, REQ_PARAM_QUESTION_FLAG );
        boolean adminMethodFlag = getOutputIndiator(req, REQ_PARAM_METHOD_FLAG );
        boolean notesFlag = getOutputIndiator(req, REQ_PARAM_NOTE_FLAG );
        
        reportCriteria.setRetrieveHeaderRule(headerRuleFlag);
        reportCriteria.setRetrieveBenefitLines(benefitLineFlag);
        reportCriteria.setRetrieveQuestions(quesitonFlag);
        reportCriteria.setRetrieveAdminMethods(adminMethodFlag);
        reportCriteria.setRetrieveNotes(notesFlag);
		
        List result = new ContractBusinessObjectBuilder().getContractReport(reportCriteria);
        
        return result;
	}
	
	private boolean getOutputIndiator(HttpServletRequest request, String parameterName) {
		if("true".equals(request.getParameter(parameterName))) {
			return true;
		} else if ("false".equals(request.getParameter(parameterName))) {
			return false;
		}
		throw new IllegalArgumentException("Parameter " + parameterName + " does not have a valid value");
	}
	
	protected  void createHeaders(WritableSheet sheet, String [] headers) throws WriteException {
		WritableFont columnFont = new WritableFont(WritableFont.ARIAL, 9,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		WritableCellFormat columnHeadingFmt = new WritableCellFormat(columnFont);
		//System.out.println("SETTING COLOR");
		Logger.logInfo("SETTING COLOR");
		columnHeadingFmt.setBackground(jxl.format.Colour.GRAY_25);
		
		for (int i = 0; i < headers.length; i++) {
			Label label = new Label(i,0,headers[i], columnHeadingFmt);
			sheet.addCell(label);
		}
	}

}
