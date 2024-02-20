package com.wellpoint.ets.bx.mapping.web.view;

import java.text.SimpleDateFormat;
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

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

public class ExcelPrintView extends AbstractExcelView{
	
	List inProgressVariables = null;

	
	public ExcelPrintView(List inProgressVariables){
		this.inProgressVariables = inProgressVariables;

	}
	
	public void buildExcelDocument(Map model, HSSFWorkbook workBook, HttpServletRequest request, HttpServletResponse response) {
	



		if(inProgressVariables != null){
			HSSFSheet sheet = workBook.createSheet("Variable");
	        setReportProperties(sheet);
			createVariableMappingHeader(workBook,sheet);
	        populateVariableMappingValues(inProgressVariables, workBook, sheet);


		}
		

	}
	
	private static void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }
	
	
	
	
	
	
	
	private static void createVariableMappingHeader(HSSFWorkbook workbook,HSSFSheet sheet) {
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
        
        
        headers1.setCellValue("System");
        headers2.setCellValue("Variable");
        headers3.setCellValue("Description");
        headers4.setCellValue("Updated On");
        headers5.setCellValue("User Id");
        headers6.setCellValue("Status");
       
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        headers5.setCellStyle(cs);
        headers6.setCellStyle(cs);
        
    }
	private static void populateVariableMappingValues(List mappingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 2, (short) size);
		sheet.setColumnWidth((short) 5, (short) size);
	
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

			cell0.setCellValue(mapping.getVariable().getVariableSystem());
			cell1.setCellValue(mapping.getVariable().getVariableId());
			cell2.setCellValue(mapping.getVariable().getDescription());
			cell3.setCellValue(sdf.format(mapping.getVariable().getCreatedDate()));
			cell4.setCellValue(mapping.getUser().getLastUpdatedUserName());
			cell5.setCellValue(mapping.getVariableMappingStatus());

			cs.setWrapText(true);
			cell2.setCellStyle(cs);

		}

	}
	

	
}
		

                	
            	