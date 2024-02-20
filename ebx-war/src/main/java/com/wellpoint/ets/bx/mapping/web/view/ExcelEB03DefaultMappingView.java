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
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class ExcelEB03DefaultMappingView extends AbstractExcelView {
	
List eb03DefaultMappingList = null;
	
	

	public ExcelEB03DefaultMappingView(List eb03DefaultMappingList) {
		this.eb03DefaultMappingList = eb03DefaultMappingList;
	}
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		if(eb03DefaultMappingList != null){
			HSSFSheet sheet = workbook.createSheet("EB03 Default Mapping Rule");
			
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
			
			
			cell0.setCellValue("UM Rule ID");
            cell1.setCellValue("Rule Description ");
            cell2.setCellValue("Status");
            cell3.setCellValue("EB03");
            cell4.setCellValue("EB03 Default");
            
            int rowCount = 1;
            
            for(int i=0;i<eb03DefaultMappingList.size();i++){
            	HSSFRow ruleRow = sheet.createRow(rowCount++);
            	SpiderUMRuleMappingVO umRuleMapping  = (SpiderUMRuleMappingVO) eb03DefaultMappingList.get(i);
            	ruleRow.createCell((short) 0).setCellValue(umRuleMapping.getUmRuleId());
            	ruleRow.createCell((short) 1).setCellValue(umRuleMapping.getUmRuleDescription());
            	ruleRow.createCell((short) 2).setCellValue(umRuleMapping.getStatus());
            	ruleRow.createCell((short) 3).setCellValue(umRuleMapping.getEb03Value());
            	ruleRow.createCell((short) 4).setCellValue(umRuleMapping.getEb03DefaultValue());
            	
            }
			
			
		}
		
	}

}
