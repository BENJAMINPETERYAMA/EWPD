package com.wellpoint.ets.ebx.mapping.web.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;

public class ExcelPrintView extends AbstractExcelView{
	
	List spsMappingList = null;
	List ruleMappingList;
	List custMssageMappingList;
	
	public ExcelPrintView(List spsMappingList,List ruleMappingList,List custMssageMappingList){
		this.spsMappingList = spsMappingList;
		this.ruleMappingList = ruleMappingList;
		this.custMssageMappingList = custMssageMappingList;
	}
	
	public void buildExcelDocument(Map model, HSSFWorkbook workBook, HttpServletRequest request, HttpServletResponse response) {
	



		if(ruleMappingList != null){
			HSSFSheet sheet = workBook.createSheet("Header Rule");
	        setReportProperties(sheet);
			createRuleMappingHeader(null,workBook,sheet);
	        populateRuleMappingValues(ruleMappingList, workBook, sheet);


		}
		if(spsMappingList != null){
			HSSFSheet sheet = workBook.createSheet("SPS Id");
	        setReportProperties(sheet);
			createSpsMappingHeader(null,workBook,sheet);
	        populateSpsMappingValues(spsMappingList, workBook, sheet);


		}
		if(custMssageMappingList != null){
			HSSFSheet sheet = workBook.createSheet("Custom Message");
	        setReportProperties(sheet);
	        createCustomMessageMappingHeader(null,workBook,sheet);
			populateCustomMessageMappingValues(custMssageMappingList, workBook, sheet);


		}

	}
	
	private static void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }
	
	
	
	
	/*****************************************/
	private static void createSpsMappingHeader(Mapping mapping, HSSFWorkbook workbook,HSSFSheet sheet) {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);
        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3);        
        HSSFCell headers5 = sheet.createRow((short)0).createCell((short)4);    
        
        
        
        headers1.setCellValue("SPS Id");
        headers2.setCellValue("Description");
        headers3.setCellValue("Updated On");
        headers4.setCellValue("User Id");
        headers5.setCellValue("Status");
       
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        headers5.setCellStyle(cs);
        
    }
	
/***************************************************/
	private static void populateSpsMappingValues(List mappingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 4, (short) size);

		int rowCount = 0;
		Mapping mapping;
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		for (int i = 0; i < mappingList.size(); i++) {
			mapping = (Mapping) mappingList.get(i);
			rowCount++;
			HSSFCell cell0 = sheet.createRow((short) rowCount).createCell(
					(short) 0);
			HSSFCell cell1 = sheet.createRow((short) rowCount).createCell(
					(short) 1);
			HSSFCell cell2 = sheet.createRow((short) rowCount).createCell(
					(short) 2);
			HSSFCell cell3 = sheet.createRow((short) rowCount).createCell(
					(short) 3);
			HSSFCell cell4 = sheet.createRow((short) rowCount).createCell(
					(short) 4);

			cell0.setCellValue(mapping.getSpsId().getSpsId());
			cell1.setCellValue(mapping.getSpsId().getSpsDesc());

			cell2.setCellValue(sdf.format(mapping.getLastChangedTmStamp()));
			cell3.setCellValue(mapping.getUser().getLastUpdatedUserName());
			cell4.setCellValue(mapping.getVariableMappingStatus());

			cs.setWrapText(true);
			cell1.setCellStyle(cs);

		}

	}
	
	private static void createRuleMappingHeader(Mapping mapping, HSSFWorkbook workbook,HSSFSheet sheet) {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);
        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3);        
        HSSFCell headers5 = sheet.createRow((short)0).createCell((short)4);    
        
        
        
        headers1.setCellValue("Header Rule");
        headers2.setCellValue("Description");
        headers3.setCellValue("Updated On");
        headers4.setCellValue("User Id");
        headers5.setCellValue("Status");
       
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        headers5.setCellStyle(cs);
        
    }
	private static void populateRuleMappingValues(List mappingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 4, (short) size);

		int rowCount = 0;
		Mapping mapping;
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		for (int i = 0; i < mappingList.size(); i++) {
			mapping = (Mapping) mappingList.get(i);
			rowCount++;
			HSSFCell cell0 = sheet.createRow((short) rowCount).createCell(
					(short) 0);
			HSSFCell cell1 = sheet.createRow((short) rowCount).createCell(
					(short) 1);
			HSSFCell cell2 = sheet.createRow((short) rowCount).createCell(
					(short) 2);
			HSSFCell cell3 = sheet.createRow((short) rowCount).createCell(
					(short) 3);
			HSSFCell cell4 = sheet.createRow((short) rowCount).createCell(
					(short) 4);

			cell0.setCellValue(mapping.getRule().getHeaderRuleId());
			cell1.setCellValue(mapping.getRule().getRuleDesc());

			cell2.setCellValue(sdf.format(mapping.getLastChangedTmStamp()));
			cell3.setCellValue(mapping.getUser().getLastUpdatedUserName());
			cell4.setCellValue(mapping.getVariableMappingStatus());

			cs.setWrapText(true);
			cell1.setCellStyle(cs);

		}

	}
	/**************************/
	private static void createCustomMessageMappingHeader(Mapping mapping, HSSFWorkbook workbook,HSSFSheet sheet) {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);
        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3);        
        HSSFCell headers5 = sheet.createRow((short)0).createCell((short)4);   
        HSSFCell headers6 = sheet.createRow((short)0).createCell((short)5);  
        HSSFCell headers7 = sheet.createRow((short)0).createCell((short)6);  
        HSSFCell headers8 = sheet.createRow((short)0).createCell((short)7); 
        
        
        
        headers1.setCellValue("Rule Id");
        headers2.setCellValue("Rule Description");
        headers3.setCellValue("SPS Id");
        headers4.setCellValue("SPS Description");
        headers5.setCellValue("Messages");
        headers6.setCellValue("Updated On");
        headers7.setCellValue("User Id");
        headers8.setCellValue("Status");
        
       
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        headers5.setCellStyle(cs);
        headers6.setCellStyle(cs);
        headers7.setCellStyle(cs);
        headers8.setCellStyle(cs);
        
    }
	private static void populateCustomMessageMappingValues(List mappingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 3, (short) size);
		sheet.setColumnWidth((short) 4, (short) size);
		sheet.setColumnWidth((short) 7, (short) size);
		

		int rowCount = 0;
		Mapping mapping;
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		for (int i = 0; i < mappingList.size(); i++) {
			mapping = (Mapping) mappingList.get(i);
			rowCount++;
			HSSFCell cell0 = sheet.createRow((short) rowCount).createCell(
					(short) 0);
			HSSFCell cell1 = sheet.createRow((short) rowCount).createCell(
					(short) 1);
			HSSFCell cell2 = sheet.createRow((short) rowCount).createCell(
					(short) 2);
			HSSFCell cell3 = sheet.createRow((short) rowCount).createCell(
					(short) 3);
			HSSFCell cell4 = sheet.createRow((short) rowCount).createCell(
					(short) 4);
			HSSFCell cell5 = sheet.createRow((short) rowCount).createCell(
					(short) 5);
			HSSFCell cell6 = sheet.createRow((short) rowCount).createCell(
					(short) 6);
			HSSFCell cell7 = sheet.createRow((short) rowCount).createCell(
					(short) 7);

			//Added for SSCR 19537 changes
			String msgString = "";
			if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association() && !mapping.getEb03().getEb03Association().isEmpty()){
				List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
				eb03AssnList = mapping.getEb03().getEb03Association();
				for(EB03Association eb03AssnObj : eb03AssnList){
					if(null != eb03AssnObj && null != eb03AssnObj.getMessage() && !eb03AssnObj.getMessage().equals(DomainConstants.EMPTY)){
						if(msgString.equals(DomainConstants.EMPTY)){
							msgString = eb03AssnObj.getMessage();
						}else{
							msgString = msgString +", "+ eb03AssnObj.getMessage();
						}
					}
					
				}
			}
			
			//Ends here
			
			cell0.setCellValue(mapping.getRule().getHeaderRuleId());
			cell1.setCellValue(mapping.getRule().getRuleDesc());
			cell2.setCellValue(mapping.getSpsId().getSpsId());
			cell3.setCellValue(mapping.getSpsId().getSpsDesc());
			cell4.setCellValue(msgString);
			cell5.setCellValue(sdf.format(mapping.getLastChangedTmStamp()));
			cell6.setCellValue(mapping.getUser().getLastUpdatedUserName());
			cell7.setCellValue(mapping.getVariableMappingStatus());
			

			cs.setWrapText(true);
			cell1.setCellStyle(cs);
			cell3.setCellStyle(cs);
			cell4.setCellStyle(cs);

		}

	}
}
		

                	
            	