package com.wellpoint.ets.bx.mapping.web.view;

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

import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;

/**
 * @author UST-GLOBAL This is an class for generating excel for search
 *       
 */
public class ExcelLocateView extends AbstractExcelView {


	public List variableMappingList = null;

	public ExcelLocateView(List variableMappingList) {
		this.variableMappingList = variableMappingList;
	}

	protected void buildExcelDocument(Map model, HSSFWorkbook workBook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(variableMappingList != null){
			response.setHeader("content-disposition", "attachment; filename=" + "eBX Locate Variable Report.xls");
			HSSFSheet sheet = workBook.createSheet("eBX Locate Variable Report");
	        setReportProperties(sheet);
	        createVariableReportHeader(null,workBook,sheet);
	        populateVariableReportValues(variableMappingList, workBook, sheet);
		}
		
	}
	
	private static void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }
	
	private static void createVariableReportHeader(SearchResult mapping, HSSFWorkbook workbook,HSSFSheet sheet) {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        cs.setRotation((short)90);
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       /* HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);*/
        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3);        
        HSSFCell headers5 = sheet.createRow((short)0).createCell((short)4);  
        
        headers1.setCellValue("SYSTEM");
        headers2.setCellValue("VARIABLE ID"); 
        headers3.setCellValue("DESCRIPTION");
        headers4.setCellValue("DATE CREATED");
        headers5.setCellValue("STATUS");
        
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        headers5.setCellStyle(cs);
    }
	
	private static void populateVariableReportValues(List searchingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {
      HSSFCellStyle cs = workbook.createCellStyle();

      int size = 256 * 25;
  	sheet.setColumnWidth((short) 1, (short) size);
	sheet.setColumnWidth((short) 2, (short) size);

      int rowCount = 0;
      SearchResult searchResult;
      for (int i = 0; i < searchingList.size(); i++) {
            searchResult = (SearchResult) searchingList.get(i);
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
                  cell0.setCellValue(searchResult.getSystem());
                  cell1.setCellValue(searchResult.getVariableId());
                  cell2.setCellValue(searchResult.getDescription());
                  cell3.setCellValue(searchResult.getFormattedDate());
                  cell4.setCellValue(searchResult.getStatus());
            cs.setWrapText(true);
            cell2.setCellStyle(cs);

      }
}
}
