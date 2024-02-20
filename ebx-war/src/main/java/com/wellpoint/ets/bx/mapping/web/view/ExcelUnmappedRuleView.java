package com.wellpoint.ets.bx.mapping.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;

/*
 * This Class is used to generate ExcelSheet for unmapped rule
 */
public class ExcelUnmappedRuleView extends AbstractExcelView {
	
	List unMappedRuleList = null;
	
	

	public ExcelUnmappedRuleView(List unMappedRuleList) {
		this.unMappedRuleList = unMappedRuleList;
	}
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if(unMappedRuleList != null){
			HSSFSheet sheet = workbook.createSheet("Unmapped Rule");
			
			//Sheet Style
			 sheet.setAutobreaks(true);
		        sheet.setDefaultColumnWidth((short) 30);
		        sheet.getPrintSetup().setFitHeight((short)1);
		        sheet.getPrintSetup().setFitWidth((short)1);
		        
		        //Set Header Cell Style
		        HSSFCellStyle cs = workbook.createCellStyle();
		        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		        HSSFFont font = workbook.createFont();
		        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        cs.setFont(font);
			
			HSSFRow header  = sheet.createRow(0);
			
			HSSFCell cell0 = header.createCell((short) 0);
			HSSFCell cell1 =  header.createCell((short) 1);
			HSSFCell cell2 = header.createCell((short) 2);
			HSSFCell cell3 = header.createCell((short) 3);
			HSSFCell cell4 = header.createCell((short) 4);
			cell0.setCellStyle(cs);
			cell1.setCellStyle(cs);
			cell2.setCellStyle(cs);
			cell3.setCellStyle(cs);
			cell4.setCellStyle(cs);
			
			
			cell0.setCellValue("Rule ID");
            cell1.setCellValue("Rule Type ");
            cell2.setCellValue("Version");
            cell3.setCellValue("Rule Description");
            cell4.setCellValue("RMA Status");
            
            int rowCount = 1;
            
            for(int i=0;i<unMappedRuleList.size();i++){
            	HSSFRow ruleRow = sheet.createRow(rowCount++);
            	SpiderUMRuleMapping umRuleMapping  = (SpiderUMRuleMapping) unMappedRuleList.get(i);
            	ruleRow.createCell((short) 0).setCellValue(umRuleMapping.getUmRuleId());
            	ruleRow.createCell((short) 1).setCellValue(umRuleMapping.getUmRuleType());
            	ruleRow.createCell((short) 2).setCellValue(String.valueOf(umRuleMapping.getDefaultVersion()));
            	ruleRow.createCell((short) 3).setCellValue(umRuleMapping.getUmRuleDesc());
            	ruleRow.createCell((short) 4).setCellValue(umRuleMapping.getUmStatus());
            	
            }
			
			
		}
		
	}
	

}
