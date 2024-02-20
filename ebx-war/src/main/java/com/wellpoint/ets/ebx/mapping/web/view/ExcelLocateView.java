package com.wellpoint.ets.ebx.mapping.web.view;

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
public class ExcelLocateView extends AbstractExcelView{

	
	public List ruleIDList = null;
	public List spsIDList = null;
	public List customMessageList = null;
	
	public ExcelLocateView(List ruleIDList, List spsIDList, List customMessageList) {
		this.ruleIDList = ruleIDList;
		this.spsIDList = spsIDList;
		this.customMessageList = customMessageList;
	}

	protected void buildExcelDocument(Map model, HSSFWorkbook workBook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(ruleIDList != null){
			response.setHeader("content-disposition", "attachment; filename=" + "eBX Locate Rule Id Report.xls");
			HSSFSheet sheet = workBook.createSheet("eBX Locate Rule Id Report");
	        setReportProperties(sheet);
	        createRuleHeader(workBook,sheet);
	        populateRuleValues(ruleIDList, workBook, sheet);
		}else if(null != spsIDList){
			response.setHeader("content-disposition", "attachment; filename=" + "eBX Locate SPS ID Report.xls");
			HSSFSheet sheet = workBook.createSheet("eBX Locate SPS ID Report");
	        setReportProperties(sheet);
	        createSPSHeader(null,workBook,sheet);
	        populateSPSValues(spsIDList, workBook, sheet);
		}else if(null != customMessageList){
			response.setHeader("content-disposition", "attachment; filename=" + "eBX Locate Message Text Report.xls");
			HSSFSheet sheet = workBook.createSheet("eBX Locate Message Text Report");
	        setReportProperties(sheet);
	        createMessageTextHeader(null,workBook,sheet);
	        populateMessageTextValues(customMessageList, workBook, sheet);
		}
	}
	
	private static void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }

	private static void createRuleHeader(HSSFWorkbook workbook,HSSFSheet sheet) {
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
        /*HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);*/
        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3); 
        
        headers1.setCellValue("RULE ID");
        headers2.setCellValue("DESCRIPTION"); 
        headers3.setCellValue("DATE CREATED");
        headers4.setCellValue("STATUS");
		
		
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        
        
    }
	
	private static void createSPSHeader(SearchResult mapping, HSSFWorkbook workbook,HSSFSheet sheet) {
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
        /*HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);*/
        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3);
        
        headers1.setCellValue("SPS ID");
        headers2.setCellValue("DESCRIPTION"); 
        headers3.setCellValue("DATE CREATED");
        headers4.setCellValue("STATUS");
		
        
        headers1.setCellStyle(cs);
        headers2.setCellStyle(cs);
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        
    }
	
	private static void populateRuleValues(List searchingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 0, (short) size);
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 3, (short) size);

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

			
			cell0.setCellValue(searchResult.getHeaderRuleId());
			cell1.setCellValue(searchResult.getHeaderRuleDescription());
			cell2.setCellValue(searchResult.getFormattedDate());
			cell3.setCellValue(searchResult.getStatus());

			cs.setWrapText(true);
			cell1.setCellStyle(cs);

		}
	}
	private static void populateSPSValues(List searchingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 3, (short) size);

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
			
			cell0.setCellValue(searchResult.getSpsId());
			cell1.setCellValue(searchResult.getDescription());
			cell2.setCellValue(searchResult.getFormattedDate());
			cell3.setCellValue(searchResult.getStatus());
			
			cs.setWrapText(true);
			cell1.setCellStyle(cs);

		}
	}
		
		private static void createMessageTextHeader(SearchResult mapping, HSSFWorkbook workbook,HSSFSheet sheet) {
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
	        
	        HSSFCell headers1 = sheet.createRow((short)0).createCell((short)0);                
	        HSSFCell headers2 = sheet.createRow((short)0).createCell((short)1);
	        HSSFCell headers3 = sheet.createRow((short)0).createCell((short)2);
	        HSSFCell headers4 = sheet.createRow((short)0).createCell((short)3);        
	        HSSFCell headers5 = sheet.createRow((short)0).createCell((short)4);  
	        HSSFCell headers6 = sheet.createRow((short)0).createCell((short)5);  
	        HSSFCell headers7 = sheet.createRow((short)0).createCell((short)6);  
	        
	        headers1.setCellStyle(cs);
	        headers2.setCellStyle(cs);
	        headers3.setCellStyle(cs);
	        headers4.setCellStyle(cs);
	        headers5.setCellStyle(cs);
	        headers6.setCellStyle(cs);
	        headers7.setCellStyle(cs);
	        
	        headers1.setCellValue("RULE ID");
	        headers2.setCellValue("SPS ID"); 
	        headers3.setCellValue("MESSAGE TEXT");
	        headers4.setCellValue("RULE DESCRIPTION");
	        headers5.setCellValue("SPSID DESCRIPTION");
	        headers6.setCellValue("DATE CREATED");
	        headers7.setCellValue("STATUS");
	        
	       
	    }
		
		private static void populateMessageTextValues(List searchingList,
				HSSFWorkbook workbook, HSSFSheet sheet) {

			HSSFCellStyle cs = workbook.createCellStyle();

			int size = 256 * 25;
			sheet.setColumnWidth((short) 2, (short) size);
			sheet.setColumnWidth((short) 3, (short) size);
			sheet.setColumnWidth((short) 4, (short) size);
			sheet.setColumnWidth((short) 6, (short) size);

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
				HSSFCell cell5 = sheet.createRow((short) rowCount).createCell(
						(short) 5);
				HSSFCell cell6 = sheet.createRow((short) rowCount).createCell(
						(short) 6);
				
				cell0.setCellValue(searchResult.getHeaderRuleId());
				cell1.setCellValue(searchResult.getSpsId());
				cell2.setCellValue(searchResult.getMessageText());
				cell3.setCellValue(searchResult.getHeaderRuleDescription());
				cell4.setCellValue(searchResult.getSpsRuleDescription());
				cell5.setCellValue(searchResult.getFormattedDate());
				cell6.setCellValue(searchResult.getStatus());

				cs.setWrapText(true);
				cell3.setCellStyle(cs);
				cell4.setCellStyle(cs);

			}
		}
}
