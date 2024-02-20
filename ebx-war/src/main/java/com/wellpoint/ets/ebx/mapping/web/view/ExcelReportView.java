/*
 * <ExcelReportView.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.web.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;

/**
 * @author UST-GLOBAL This is a class for generating Contract BX, Excel Report
 */
public class ExcelReportView extends AbstractExcelView{
	
	List contractList = null;
	
	/**
	 * Creating constructor
	 * @param contractList
	 */
	public ExcelReportView(List contractList){
		this.contractList = contractList;
	}
	/**
	 * Method to set the header and values for Contract BX report based on the System.
	 */
	public void buildExcelDocument(Map model, HSSFWorkbook workBook, HttpServletRequest request, HttpServletResponse response) {
	
		if(contractList != null){
		for(int i=0;i<contractList.size();i++){
		ContractVO contract=(ContractVO)contractList.get(i);
		if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())) {
			HSSFSheet sheet = workBook.createSheet();
			setReportProperties(sheet);
			createEbxWpdReportHeader(contract,workBook,sheet);
			populateEbxWpdContractValues(contract,workBook, sheet);
		} else if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())){
			HSSFSheet sheet = workBook.createSheet();
			Calendar c1 = Calendar.getInstance(); // today
            Date date=null;
            SimpleDateFormat dateFormatter =new SimpleDateFormat(DomainConstants.DATE_FORMAT_2);
            SimpleDateFormat formatter = new SimpleDateFormat(DomainConstants.DATE_FORMAT);
            try {
                 date = formatter.parse(contract.getRevisionDate());
                 c1.setTime(date);
                 workBook.setSheetName(i,dateFormatter.format(c1.getTime()).toString());
             	} catch (ParseException e) {
                 
                }
                setReportProperties(sheet);
    			createEbxWpdReportHeader(contract,workBook,sheet);
    			populateEbxWpdContractValues(contract,workBook, sheet);
		} else if(DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(contract.getSystem())){
			HSSFSheet sheet = workBook.createSheet();
	        setReportProperties(sheet);
	        createEbxEwpdReportHeader(contract,workBook,sheet);
	        populateEbxEwpdContractValues(contract, workBook, sheet);
		}
		}
		}
	}
	
	private static void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }
	
	private static void createEbxWpdReportHeader(ContractVO contract, HSSFWorkbook workbook,HSSFSheet sheet) {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);
        int i;
        
        if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())){
        	HSSFCell header40 = sheet.createRow((short)2).createCell((short)3);
        	header40.setCellValue("Revision Date");
        }
        HSSFCell header1 = sheet.createRow((short)1).createCell((short)0);                
        HSSFCell header2 = sheet.createRow((short)2).createCell((short)0);  
        
        //HPN product change
        if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem()) 
        		 && contract.getHpnProduct()!=null && !contract.getHpnProduct().isEmpty()){
        	HSSFCell prd_cde = sheet.createRow((short)3).createCell((short)0);
            HSSFCell prd_sdesc = sheet.createRow((short)4).createCell((short)0);
            prd_cde.setCellValue("Product Code");
            prd_sdesc.setCellValue("Product Short Description");
            i=6;
        }
        else{
        	i=4;
        }
        
        HSSFCell header3 = sheet.createRow((short)i).createCell((short)0);  
        HSSFCell header4 = sheet.createRow((short)i).createCell((short)1);        
        HSSFCell header5 = sheet.createRow((short)i).createCell((short)2);
        HSSFCell header6 = sheet.createRow((short)i).createCell((short)3);
        HSSFCell header7 = sheet.createRow((short)i).createCell((short)4);
        HSSFCell header8 = sheet.createRow((short)i).createCell((short)5);
        HSSFCell header9 = sheet.createRow((short)i).createCell((short)6);
        HSSFCell header10 = sheet.createRow((short)i).createCell((short)7);
        HSSFCell header11 = sheet.createRow((short)i).createCell((short)8);
        HSSFCell header12 = sheet.createRow((short)i).createCell((short)9);
        HSSFCell header13 = sheet.createRow((short)i).createCell((short)10);
        HSSFCell header14 = sheet.createRow((short)i).createCell((short)11);
        HSSFCell header15 = sheet.createRow((short)i).createCell((short)12);
        HSSFCell header16 = sheet.createRow((short)i).createCell((short)13);
        HSSFCell header17 = sheet.createRow((short)i).createCell((short)14);
        HSSFCell header18 = sheet.createRow((short)i).createCell((short)15);
        HSSFCell header19 = sheet.createRow((short)i).createCell((short)16);
        HSSFCell header20 = sheet.createRow((short)i).createCell((short)17);
        HSSFCell header21 = sheet.createRow((short)i).createCell((short)18);
        HSSFCell header22 = sheet.createRow((short)i).createCell((short)19);
        HSSFCell header23 = sheet.createRow((short)i).createCell((short)20);
        HSSFCell header24 = sheet.createRow((short)i).createCell((short)21);
        HSSFCell header25 = sheet.createRow((short)i).createCell((short)22);
        HSSFCell header26 = sheet.createRow((short)i).createCell((short)23);
        HSSFCell header27 = sheet.createRow((short)i).createCell((short)24);
        HSSFCell header28 = sheet.createRow((short)i).createCell((short)25);
        HSSFCell header29 = sheet.createRow((short)i).createCell((short)26);
        HSSFCell header30 = sheet.createRow((short)i).createCell((short)27);
        HSSFCell header31 = sheet.createRow((short)i).createCell((short)28);
        HSSFCell header32 = sheet.createRow((short)i).createCell((short)29);
        HSSFCell header33 = sheet.createRow((short)i).createCell((short)30);
        HSSFCell header34 = sheet.createRow((short)i).createCell((short)31);
        HSSFCell header35 = sheet.createRow((short)i).createCell((short)32);
        HSSFCell header36 = sheet.createRow((short)i).createCell((short)33);
        HSSFCell header37 = sheet.createRow((short)i).createCell((short)34);
        HSSFCell header38 = sheet.createRow((short)i).createCell((short)35);
        HSSFCell header39 = sheet.createRow((short)i).createCell((short)36);
        HSSFCell header40 = sheet.createRow((short)i).createCell((short)37);
        HSSFCell header41 = sheet.createRow((short)i).createCell((short)38);
        HSSFCell header42 = sheet.createRow((short)i).createCell((short)39);
        HSSFCell header43 = sheet.createRow((short)i).createCell((short)40);
        HSSFCell header44 = sheet.createRow((short)i).createCell((short)41);
        HSSFCell header45 = sheet.createRow((short)i).createCell((short)42);
        HSSFCell header46 = sheet.createRow((short)i).createCell((short)43);
        HSSFCell header47 = sheet.createRow((short)i).createCell((short)44);
        HSSFCell header48 = sheet.createRow((short)i).createCell((short)45);
        HSSFCell header49 = sheet.createRow((short)i).createCell((short)46);
        HSSFCell header50 = sheet.createRow((short)i).createCell((short)47);
        HSSFCell header51 = sheet.createRow((short)i).createCell((short)48);
        HSSFCell header52 = sheet.createRow((short)i).createCell((short)49);
        header1.setCellValue("Contract Id");
        header2.setCellValue("Date Segment");      
        header3.setCellValue("Major Heading");
        header4.setCellValue("Minor Heading");
        header5.setCellValue("Variable Desc");
        header6.setCellValue("Variable");
        header7.setCellValue("PVA");
        header8.setCellValue("Format");
        header9.setCellValue("Coded Value");
        header10.setCellValue("EB01");
        header11.setCellValue("EB02");
        header12.setCellValue("EB03");
        header13.setCellValue("EB06");
        header14.setCellValue("EB09");
        header15.setCellValue("START AGE");
        header16.setCellValue("END AGE");
        header17.setCellValue("EB01 EXTD MSG A");
        header18.setCellValue("EB01 EXTD MSG B");
        header19.setCellValue("EB01 EXTD MSG C");
        header20.setCellValue("NW Reqd");
        header21.setCellValue("III02");
        header22.setCellValue("MESSAGE");
        header23.setCellValue("MSG REQ IND");
        header24.setCellValue("MESSAGE TYPE");
        header25.setCellValue("EB03 EXTD MSG A");
        header26.setCellValue("EB03 EXTD MSG B");
        header27.setCellValue("EB03 EXTD MSG C");
        header28.setCellValue("NW Reqd");
        header29.setCellValue("HIGH_PRFRMN_NON_TIERD_MSG_TXT");
        header30.setCellValue("HIGH_PRFRMN_TIERD_MSG_TXT");
        header31.setCellValue("ACCUM 1");
        header32.setCellValue("ACCUM 2");
        header33.setCellValue("ACCUM 3");
        header34.setCellValue("ACCUM 4");
        header35.setCellValue("ACCUM 5");
        header36.setCellValue("ACCUM 6");
        header37.setCellValue("ACCUM 7");
        header38.setCellValue("ACCUM 8");
        header39.setCellValue("ACCUM 9");
        header40.setCellValue("ACCUM 10");
        header41.setCellValue("SENSITIVE IND");
        header42.setCellValue("HSD01");
        header43.setCellValue("HSD02");
        header44.setCellValue("HSD03");
        header45.setCellValue("HSD04");
        header46.setCellValue("HSD05");
        header47.setCellValue("HSD06");
        header48.setCellValue("HSD07");
        header49.setCellValue("HSD08");
        header50.setCellValue("UM Rule");
        header51.setCellValue("Finalized");
        header52.setCellValue("Not Applicable");
        
        header3.setCellStyle(cs);
        header4.setCellStyle(cs);
        header5.setCellStyle(cs);
        header6.setCellStyle(cs);
        header7.setCellStyle(cs);
        header8.setCellStyle(cs);
        header9.setCellStyle(cs);
        header10.setCellStyle(cs);
        header11.setCellStyle(cs);
        header12.setCellStyle(cs);
        header13.setCellStyle(cs);
        header14.setCellStyle(cs);
        header15.setCellStyle(cs);
        header16.setCellStyle(cs);
        header17.setCellStyle(cs);
        header18.setCellStyle(cs);
        header19.setCellStyle(cs);
        header20.setCellStyle(cs);
        header21.setCellStyle(cs);
        header22.setCellStyle(cs);
        header23.setCellStyle(cs);
        header24.setCellStyle(cs);
        header25.setCellStyle(cs);
        header26.setCellStyle(cs);
        header27.setCellStyle(cs);
        header28.setCellStyle(cs);
        header29.setCellStyle(cs);
        header30.setCellStyle(cs);
        header31.setCellStyle(cs);
        header32.setCellStyle(cs);
        header33.setCellStyle(cs);
        header34.setCellStyle(cs);
        header35.setCellStyle(cs);
        header36.setCellStyle(cs);
        header37.setCellStyle(cs);
        header38.setCellStyle(cs);
        header39.setCellStyle(cs);
        header40.setCellStyle(cs);
        header41.setCellStyle(cs);
        header42.setCellStyle(cs);
        header43.setCellStyle(cs);
        header44.setCellStyle(cs);
        header45.setCellStyle(cs);
        header46.setCellStyle(cs);
        header47.setCellStyle(cs);
        header48.setCellStyle(cs);
        header49.setCellStyle(cs);
        header50.setCellStyle(cs);
        header51.setCellStyle(cs);
        header52.setCellStyle(cs);
    }
	
	private static void populateEbxWpdContractValues(ContractVO contract, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        int eb03Count;
        String eb03;
        int rowCount = 4;
        sheet.createRow((short)1).createCell((short)1).setCellValue(contract.getContractId());
        sheet.createRow((short)2).createCell((short)1).setCellValue(contract.getEffectiveDate());
        if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract.getSystem())){
        	sheet.createRow((short)2).createCell((short)4).setCellValue(contract.getRevisionDate());
        }
        if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem()) 
        		&& contract.getHpnProduct()!=null && !contract.getHpnProduct().isEmpty()){
        	sheet.createRow((short)3).createCell((short)1).setCellValue(contract.getHpnProduct());
        	sheet.createRow((short)4).createCell((short)1).setCellValue(contract.getHpnProductSDesc());
        	rowCount=6;
        }
        Map majorHeadings = contract.getMajorHeadings();
        if(majorHeadings != null) {        	
        //    Iterator majorHeadingsIterator = majorHeadings.keySet().iterator();
        	Iterator majorHeadingsIterator = majorHeadings.entrySet().iterator();
            if(majorHeadingsIterator != null) {
                while(majorHeadingsIterator.hasNext()) {
               //     String majorHeadingDesc = (String)majorHeadingsIterator.next();
               //     MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO)majorHeadings.get(majorHeadingDesc);
                	MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) ((Map.Entry) majorHeadingsIterator.next()).getValue();
                    Map minorHeadings = majorHeadingFromMap.getMinorHeadings();
                    if (minorHeadings != null) {                    	
                     // Iterator minorHeadingsIterator = minorHeadings.keySet().iterator();
                    	Iterator minorHeadingsIterator = minorHeadings.entrySet().iterator();
                        if(minorHeadingsIterator != null) {
                            while(minorHeadingsIterator.hasNext()) {
                             // String minorHeadingDesc = (String)minorHeadingsIterator.next();
                            //  MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO)minorHeadings.get(minorHeadingDesc);
                                MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
                                Map mappings = minorHeadingFromMap.getMappings();                                
                                if(mappings != null) {
                                    //Iterator mappingsIterator = mappings.keySet().iterator();
                                	Iterator mappingsIterator = mappings.entrySet().iterator();
                                    if(mappingsIterator != null) {
                                        while(mappingsIterator.hasNext()) {
                                           // String mappingKey = (String)mappingsIterator.next();
                                            //Mapping mappingFromMap = (Mapping)mappings.get(mappingKey);
                                        	Mapping mappingFromMap = (Mapping) ((Map.Entry) mappingsIterator.next()).getValue();
                                            if(mappingFromMap != null ) {
                                            	
                                            	if(mappingFromMap.isMapped()) {
                                            			if(null != mappingFromMap.getEb03()) {
                                                			List<String> associatedList = new ArrayList<String>();
                                                        	/*String unAssnEb03CSV = null;
                                        					List<String> unAssociatedList = new ArrayList<String>();
                                        					List<EB03Association> associatedEB03AssnList = new ArrayList<EB03Association>();*/
                                                			List<EB03Association> associatedEB03AssnList = getAssociatedEB03CodesForWpd(mappingFromMap,
                                                					associatedList,mappingFromMap.getIndvdlEb03AssnIndicator());
                                                        	/*if(null != unAssociatedList && unAssociatedList.size() != 0) {
                                                        		unAssnEb03CSV = StringUtils.join(unAssociatedList.toArray(), ",");
                                                        	}*/
                                                        	if(null != associatedEB03AssnList && associatedEB03AssnList.size() != 0) {
                                                        		for(EB03Association eb03AssnValue : associatedEB03AssnList)
                                                            	{
                                                        			rowCount++;
                                                                	createMappedRowsForWpd(
            																contract,
            																sheet,
            																rowCount,
            																majorHeadingFromMap,
            																minorHeadingFromMap,
            																mappingFromMap,
            																eb03AssnValue);
                                                                }
                                                            }else {
                                                            	rowCount++;
                                                            	createMappedRowsForWpd(
            															contract,
            															sheet,
            															rowCount,
            															majorHeadingFromMap,
            															minorHeadingFromMap,
            															mappingFromMap,
            															null);
                                                            }
                                                        	/*if(null != unAssnEb03CSV && !"".equals(unAssnEb03CSV)) {
                                                        		rowCount++;
                                                            	createRows(
            															contract,
            															sheet,
            															rowCount,
            															majorHeadingFromMap,
            															minorHeadingFromMap,
            															mappingFromMap,
            															null,
            															unAssnEb03CSV);
                                                        	}*/
                                                		}
                                                		else {
                                                			rowCount++;
                                                			createMappedRowsForWpd(
        															contract,
        															sheet,
        															rowCount,
        															majorHeadingFromMap,
        															minorHeadingFromMap,
        															mappingFromMap,
        															null);
                                                		}
                                            }
                                            else {
                                            	rowCount++;
                                            	
                                            	HSSFCell cell0 = sheet.createRow((short)rowCount).createCell((short)0);
                                            	HSSFCell cell1 = sheet.createRow((short)rowCount).createCell((short)1);
                                            	HSSFCell cell2 = sheet.createRow((short)rowCount).createCell((short)2);
                                            	HSSFCell cell3 = sheet.createRow((short)rowCount).createCell((short)3);
                                            	HSSFCell cell4 = sheet.createRow((short)rowCount).createCell((short)4);
                                            	HSSFCell cell5 = sheet.createRow((short)rowCount).createCell((short)5);
                                            	HSSFCell cell6 = sheet.createRow((short)rowCount).createCell((short)6);
                                            	HSSFCell cell7 = sheet.createRow((short)rowCount).createCell((short)7);
                                            	HSSFCell cell8 = sheet.createRow((short)rowCount).createCell((short)8);
                                            	HSSFCell cell9 = sheet.createRow((short)rowCount).createCell((short)9);
                                            	HSSFCell cell10 = sheet.createRow((short)rowCount).createCell((short)10);
                                            	HSSFCell cell11 = sheet.createRow((short)rowCount).createCell((short)11);
                                            	HSSFCell cell12 = sheet.createRow((short)rowCount).createCell((short)12);
                                            	HSSFCell cell13 = sheet.createRow((short)rowCount).createCell((short)13);
                                            	HSSFCell cell14 = sheet.createRow((short)rowCount).createCell((short)14);
                                            	HSSFCell cell15 = sheet.createRow((short)rowCount).createCell((short)15);
                                            	HSSFCell cell16 = sheet.createRow((short)rowCount).createCell((short)16);
                                            	HSSFCell cell17 = sheet.createRow((short)rowCount).createCell((short)17);
                                            	HSSFCell cell18 = sheet.createRow((short)rowCount).createCell((short)18);
                                            	HSSFCell cell19 = sheet.createRow((short)rowCount).createCell((short)19);
                                            	HSSFCell cell20 = sheet.createRow((short)rowCount).createCell((short)20);
                                            	HSSFCell cell21 = sheet.createRow((short)rowCount).createCell((short)21);
                                            	HSSFCell cell22 = sheet.createRow((short)rowCount).createCell((short)22);
                                            	HSSFCell cell23 = sheet.createRow((short)rowCount).createCell((short)23);
                                            	HSSFCell cell24 = sheet.createRow((short)rowCount).createCell((short)24);
                                            	HSSFCell cell25 = sheet.createRow((short)rowCount).createCell((short)25);
                                            	HSSFCell cell26 = sheet.createRow((short)rowCount).createCell((short)26);
                                            	HSSFCell cell27 = sheet.createRow((short)rowCount).createCell((short)27);
                                            	HSSFCell cell28 = sheet.createRow((short)rowCount).createCell((short)28);
                                            	HSSFCell cell29 = sheet.createRow((short)rowCount).createCell((short)29);
                                            	HSSFCell cell30 = sheet.createRow((short)rowCount).createCell((short)30);
                                            	HSSFCell cell31 = sheet.createRow((short)rowCount).createCell((short)31);
                                            	HSSFCell cell32 = sheet.createRow((short)rowCount).createCell((short)32);
                                            	HSSFCell cell33 = sheet.createRow((short)rowCount).createCell((short)33);
                                            	HSSFCell cell34 = sheet.createRow((short)rowCount).createCell((short)34);
                                            	HSSFCell cell35 = sheet.createRow((short)rowCount).createCell((short)35);
                                            	HSSFCell cell36 = sheet.createRow((short)rowCount).createCell((short)36);
                                            	HSSFCell cell37 = sheet.createRow((short)rowCount).createCell((short)37);
                                            	HSSFCell cell38 = sheet.createRow((short)rowCount).createCell((short)38);
                                            	HSSFCell cell39 = sheet.createRow((short)rowCount).createCell((short)39);
                                            	HSSFCell cell40 = sheet.createRow((short)rowCount).createCell((short)40);
                                            	HSSFCell cell41 = sheet.createRow((short)rowCount).createCell((short)41);
                                            	HSSFCell cell42 = sheet.createRow((short)rowCount).createCell((short)42);
                                            	HSSFCell cell43 = sheet.createRow((short)rowCount).createCell((short)43);
                                            	HSSFCell cell44 = sheet.createRow((short)rowCount).createCell((short)44);
                                            	HSSFCell cell45 = sheet.createRow((short)rowCount).createCell((short)45);
                                            	HSSFCell cell46 = sheet.createRow((short)rowCount).createCell((short)46);
                                            	HSSFCell cell47 = sheet.createRow((short)rowCount).createCell((short)47);
                                            	HSSFCell cell48 = sheet.createRow((short)rowCount).createCell((short)48);
                                            	HSSFCell cell49 = sheet.createRow((short)rowCount).createCell((short)49);
                                            	
                                            	cell0.setCellValue(majorHeadingFromMap.getDescriptionText());
                                            	cell1.setCellValue(minorHeadingFromMap.getDescriptionText());
                                            	Variable var = mappingFromMap.getVariable();  
                                            	if(var != null) {
                                            		cell2.setCellValue(var.getDescription());
                                            		cell3.setCellValue(var.getVariableId());	                                                        
                                            		cell4.setCellValue(var.getPva());
                                            		cell5.setCellValue(var.getVariableFormat());
                                            		cell6.setCellValue(var.getVariableValue());	
                                            		
                                            	}
                                            			
	                                                    		cell0.setCellStyle(cs);
	                                                    		cell1.setCellStyle(cs);
	                                                    		cell2.setCellStyle(cs);
	                                                    		cell3.setCellStyle(cs);
	                                                    		cell4.setCellStyle(cs);
	                                                    		cell5.setCellStyle(cs);
	                                                    		cell6.setCellStyle(cs);
	                                                    		cell7.setCellStyle(cs);
	                                                    		cell8.setCellStyle(cs);
	                                                    		cell9.setCellStyle(cs);
	                                                    		cell10.setCellStyle(cs);
	                                                    		cell11.setCellStyle(cs);
	                                                    		cell12.setCellStyle(cs);
	                                                    		cell13.setCellStyle(cs);
	                                                    		cell14.setCellStyle(cs);
	                                                    		cell15.setCellStyle(cs);
	                                                    		cell16.setCellStyle(cs);
	                                                    		cell17.setCellStyle(cs);
	                                                    		cell18.setCellStyle(cs);
	                                                    		cell19.setCellStyle(cs);
	                                                    		cell20.setCellStyle(cs);
	                                                    		cell21.setCellStyle(cs);
	                                                    		cell22.setCellStyle(cs);
	                                                    		cell23.setCellStyle(cs);
	                                                    		cell24.setCellStyle(cs);
	                                                    		cell25.setCellStyle(cs);
	                                                    		cell26.setCellStyle(cs);
	                                                    		cell27.setCellStyle(cs);
	                                                    		cell28.setCellStyle(cs);
	                                                    		cell29.setCellStyle(cs);
	                                                    		cell30.setCellStyle(cs);
	                                                    		cell31.setCellStyle(cs);
	                                                    		cell32.setCellStyle(cs);
	                                                    		cell33.setCellStyle(cs);
	                                                    		cell34.setCellStyle(cs);
	                                                    		cell35.setCellStyle(cs);
	                                                    		cell36.setCellStyle(cs);
	                                                    		cell37.setCellStyle(cs);
	                                                    		cell38.setCellStyle(cs);
	                                                    		cell39.setCellStyle(cs);
	                                                    		cell40.setCellStyle(cs);
	                                                    		cell41.setCellStyle(cs);
	                                                    		cell42.setCellStyle(cs);
	                                                    		cell43.setCellStyle(cs);
	                                                    		cell44.setCellStyle(cs);
	                                                    		cell45.setCellStyle(cs);
	                                                    		cell46.setCellStyle(cs);
	                                                    		cell47.setCellStyle(cs);
	                                                    		cell48.setCellStyle(cs);
	                                                    		cell49.setCellStyle(cs);
                                             	}
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
	/**
	 * @param contract
	 * @param sheet
	 * @param rowCount
	 * @param majorHeadingFromMap
	 * @param minorHeadingFromMap
	 * @param mappingFromMap
	 * @param eb03AssnValue
	 */
	private static void createMappedRowsForWpd(ContractVO contract, HSSFSheet sheet,
			int rowCount, MajorHeadingsVO majorHeadingFromMap,
			MinorHeadingsVO minorHeadingFromMap, Mapping mappingFromMap,
			EB03Association eb03AssnValue) {
		HSSFCell cell0 = sheet.createRow((short)rowCount).createCell((short)0);
		HSSFCell cell1 = sheet.createRow((short)rowCount).createCell((short)1);
		HSSFCell cell2 = sheet.createRow((short)rowCount).createCell((short)2);
		HSSFCell cell3 = sheet.createRow((short)rowCount).createCell((short)3);
		HSSFCell cell4 = sheet.createRow((short)rowCount).createCell((short)4);
		HSSFCell cell5 = sheet.createRow((short)rowCount).createCell((short)5);
		HSSFCell cell6 = sheet.createRow((short)rowCount).createCell((short)6);
		HSSFCell cell7 = sheet.createRow((short)rowCount).createCell((short)7);
		HSSFCell cell8 = sheet.createRow((short)rowCount).createCell((short)8);
		HSSFCell cell9 = sheet.createRow((short)rowCount).createCell((short)9);
		HSSFCell cell10 = sheet.createRow((short)rowCount).createCell((short)10);
		HSSFCell cell11 = sheet.createRow((short)rowCount).createCell((short)11);
		HSSFCell cell12 = sheet.createRow((short)rowCount).createCell((short)12);
		HSSFCell cell13 = sheet.createRow((short)rowCount).createCell((short)13);
		HSSFCell cell14 = sheet.createRow((short)rowCount).createCell((short)14);
		HSSFCell cell15 = sheet.createRow((short)rowCount).createCell((short)15);
		HSSFCell cell16 = sheet.createRow((short)rowCount).createCell((short)16);
		HSSFCell cell17 = sheet.createRow((short)rowCount).createCell((short)17);
		HSSFCell cell18 = sheet.createRow((short)rowCount).createCell((short)18);
		HSSFCell cell19 = sheet.createRow((short)rowCount).createCell((short)19);
		HSSFCell cell20 = sheet.createRow((short)rowCount).createCell((short)20);
		HSSFCell cell21 = sheet.createRow((short)rowCount).createCell((short)21);
		HSSFCell cell22 = sheet.createRow((short)rowCount).createCell((short)22);
		HSSFCell cell23 = sheet.createRow((short)rowCount).createCell((short)23);
		HSSFCell cell24 = sheet.createRow((short)rowCount).createCell((short)24);
		HSSFCell cell25 = sheet.createRow((short)rowCount).createCell((short)25);
		HSSFCell cell26 = sheet.createRow((short)rowCount).createCell((short)26);
		HSSFCell cell27 = sheet.createRow((short)rowCount).createCell((short)27);
		HSSFCell cell28 = sheet.createRow((short)rowCount).createCell((short)28);
		HSSFCell cell29 = sheet.createRow((short)rowCount).createCell((short)29);
		HSSFCell cell30 = sheet.createRow((short)rowCount).createCell((short)30);
		HSSFCell cell31 = sheet.createRow((short)rowCount).createCell((short)31);
		HSSFCell cell32 = sheet.createRow((short)rowCount).createCell((short)32);
		HSSFCell cell33 = sheet.createRow((short)rowCount).createCell((short)33);
		HSSFCell cell34 = sheet.createRow((short)rowCount).createCell((short)34);
		HSSFCell cell35 = sheet.createRow((short)rowCount).createCell((short)35);
		HSSFCell cell36 = sheet.createRow((short)rowCount).createCell((short)36);
		HSSFCell cell37 = sheet.createRow((short)rowCount).createCell((short)37);
		HSSFCell cell38 = sheet.createRow((short)rowCount).createCell((short)38);
		HSSFCell cell39 = sheet.createRow((short)rowCount).createCell((short)39);
		HSSFCell cell40 = sheet.createRow((short)rowCount).createCell((short)40);
		HSSFCell cell41 = sheet.createRow((short)rowCount).createCell((short)41);
		HSSFCell cell42 = sheet.createRow((short)rowCount).createCell((short)42);
		HSSFCell cell43 = sheet.createRow((short)rowCount).createCell((short)43);
		HSSFCell cell44 = sheet.createRow((short)rowCount).createCell((short)44);
		HSSFCell cell45= sheet.createRow((short)rowCount).createCell((short)45);
		HSSFCell cell46 = sheet.createRow((short)rowCount).createCell((short)46);
		HSSFCell cell47 = sheet.createRow((short)rowCount).createCell((short)47);
		HSSFCell cell48 = sheet.createRow((short)rowCount).createCell((short)48);
		HSSFCell cell49 = sheet.createRow((short)rowCount).createCell((short)49);
		cell0.setCellValue(majorHeadingFromMap.getDescriptionText());
		cell1.setCellValue(minorHeadingFromMap.getDescriptionText());
		Variable var = mappingFromMap.getVariable();  
		if(var != null) {
			cell2.setCellValue(var.getDescription());
			cell3.setCellValue(var.getVariableId());	                                                        
			cell4.setCellValue(var.getPva());
			cell5.setCellValue(var.getVariableFormat());
			cell6.setCellValue(var.getVariableValue());	
			if(var.isNotApplicable())
			{
				cell49.setCellValue(DomainConstants.Y);
			}
			else{
			cell49.setCellValue(DomainConstants.N);
			}
			cell38.setCellValue(var.getSensitiveBenefitIndicator());
		}
		cell7.setCellValue(mappingFromMap.getEB01Value());	                                                        
		cell8.setCellValue(mappingFromMap.getEB02Value());
		cell14.setCellValue(mappingFromMap.getEb01_extndMsgTxt1());
        cell15.setCellValue(mappingFromMap.getEb01_extndMsgTxt2());
        cell16.setCellValue(mappingFromMap.getEb01_extndMsgTxt3());
        cell17.setCellValue(mappingFromMap.getEb01_eb12ChangeInd());
		            
		            /*List eb03List = mappingFromMap.getEb03Values();
		            eb03 = "";
		            eb03Count = 0;
		            if(eb03List != null) {
		                Iterator eb03ListIterator = eb03List.iterator();
		                if(eb03ListIterator != null) {
		                    while(eb03ListIterator.hasNext()) {
		                        eb03Count++;
		                        if (eb03Count != 1) {
		                            eb03 = eb03+","+(String)eb03ListIterator.next();
		                        } else {
		                            eb03 = 	(String)eb03ListIterator.next();
		                        }
		                    }
		                }
		            }*/
					/*if(null != unAssnCSV && !"".equals(unAssnCSV))
					{
						cell9.setCellValue(unAssnCSV);
					}*/
					if(null != eb03AssnValue)
					{
						cell9.setCellValue(eb03AssnValue.getEb03().getValue());
						if(null != eb03AssnValue.getIii02List() && eb03AssnValue.getIii02List().size() > 0)
						{
							List<String> iii02ValueList = new ArrayList<String>();
							for(HippaCodeValue value : eb03AssnValue.getIii02List())
							{
								iii02ValueList.add(value.getValue()); 
							}
							String csvIii02 = StringUtils.join(iii02ValueList.toArray(), ",");
							cell18.setCellValue(csvIii02);
						}
						if(null != eb03AssnValue.getMessage() && !"-".equals(eb03AssnValue.getMessage()))
						{
							 cell19.setCellValue(eb03AssnValue.getMessage());
						}
			            if(null != eb03AssnValue.getMsgReqdInd() && !"-".equals(eb03AssnValue.getMsgReqdInd()))
						{
			            	 cell20.setCellValue(eb03AssnValue.getMsgReqdInd());
						}
			            if(null != eb03AssnValue.getNoteType())
						{
			            	 cell21.setCellValue(eb03AssnValue.getNoteType().getValue());//MESSAGE type
						}
			            if(null != eb03AssnValue.getExtndMsg1())
						{
			            	 cell22.setCellValue(eb03AssnValue.getExtndMsg1());
						}
			            if(null != eb03AssnValue.getExtndMsg2())
						{
			            	 cell23.setCellValue(eb03AssnValue.getExtndMsg2());
						}
			            if(null != eb03AssnValue.getExtndMsg3())
						{
			            	 cell24.setCellValue(eb03AssnValue.getExtndMsg3());
						}
			            if(null != eb03AssnValue.getNetworkInd())
						{
			            	 cell25.setCellValue(eb03AssnValue.getNetworkInd());
						}
			            if(null != eb03AssnValue.getHighPrfrmnNonTierdMsg())
						{
			            	 cell26.setCellValue(eb03AssnValue.getHighPrfrmnNonTierdMsg());
						}
			            if(null != eb03AssnValue.getHighPrfrmnTierdMsg())
						{
			            	 cell27.setCellValue(eb03AssnValue.getHighPrfrmnTierdMsg());
						}
			            
					}else {
							if(null != mappingFromMap.getEb03Values() && mappingFromMap.getEb03Values().size() != 0) {
								cell9.setCellValue(StringUtils.join(mappingFromMap.getEb03Values().toArray(), ","));
			        		}else {
			        			cell9.setCellValue(DomainConstants.EMPTY);
			        		}
							cell18.setCellValue(DomainConstants.EMPTY);
							cell19.setCellValue(DomainConstants.EMPTY);
							cell20.setCellValue(DomainConstants.EMPTY);
							cell21.setCellValue(DomainConstants.EMPTY);
							cell22.setCellValue(DomainConstants.EMPTY);
							cell23.setCellValue(DomainConstants.EMPTY);
							cell24.setCellValue(DomainConstants.EMPTY);
							cell25.setCellValue(DomainConstants.EMPTY);
							cell26.setCellValue(DomainConstants.EMPTY);
							cell27.setCellValue(DomainConstants.EMPTY);
					}
		            cell10.setCellValue(mappingFromMap.getEB06Value());
		            cell11.setCellValue(mappingFromMap.getEB09Value());
		            // BXNI - June- start and end age.
		          
		            //BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
		            String startAge = "";
		            List<String> startAgeList = null;
		            if(!StringUtils.isBlank(mappingFromMap.getStartAgeValue())){
		            	startAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getStartAgeValue());
		            }
		            int startAgeCount = 0;
		            if(startAgeList != null) {
		            	Collections.sort(startAgeList);
		                Iterator startAgeListIterator = startAgeList.iterator();
		                if(startAgeListIterator != null) {
		                    while(startAgeListIterator.hasNext()) {
		                    	startAgeCount++;
		                    	String startAgeTemp = (String)startAgeListIterator.next();
		                    	if (!BxUtil.isInteger(startAgeTemp)) {
		                    		String startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract, startAgeTemp);
		                    		if (null != startAgeCodedValue && !DomainConstants.EMPTY.equals(startAgeCodedValue)) {
		                    			startAgeTemp = startAgeTemp +  " (" + startAgeCodedValue + ")";
		                    		} 
		                    	}
		                        if (startAgeCount != 1) {
		                        	startAge = startAge+", "+startAgeTemp;
		                        } else {
		                        	startAge = 	startAgeTemp;
		                        }
		                    }
		                }
		            }
		            if(null != startAge && !DomainConstants.EMPTY.equals(startAge)) {
		            	cell12.setCellValue(startAge);
		            } else {
		            	cell12.setCellValue(DomainConstants.EMPTY);
		            }
		            
		          //BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
		            String endAge = "";
		            List<String> endAgeList = null;
		            if(!StringUtils.isBlank(mappingFromMap.getEndAgeValue())){
		            	endAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getEndAgeValue());
		            }
		            int endAgeCount = 0;
		            if(endAgeList != null) {
		            	Collections.sort(endAgeList);
		                Iterator endAgeListIterator = endAgeList.iterator();
		                if(endAgeListIterator != null) {
		                    while(endAgeListIterator.hasNext()) {
		                    	endAgeCount++;
		                    	String endAgeTemp = (String)endAgeListIterator.next();
		                    	if (!BxUtil.isInteger(endAgeTemp)) {
		                    		String endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract, endAgeTemp);
		                    		if (null != endAgeCodedValue && !DomainConstants.EMPTY.equals(endAgeCodedValue)) {
		                    			endAgeTemp = endAgeTemp +  " (" + endAgeCodedValue + ")";
		                    		} 
		                    	}
		                        if (endAgeCount != 1) {
		                        	endAge = endAge+", "+endAgeTemp;
		                        } else {
		                        	endAge = 	endAgeTemp;
		                        }
		                    }
		                }
		            }
		            if(null != endAge && !DomainConstants.EMPTY.equals(endAge)) {
		            	cell13.setCellValue(endAge);
		            } else {
		            	cell13.setCellValue(DomainConstants.EMPTY);
		            }
		            
		            
		        	HippaSegment accum = mappingFromMap.getAccum();
					if(accum != null) {
					List accumList = accum.getHippaCodeSelectedValues();
					if(accumList != null){
						Iterator accumListIterator = accumList.iterator();
						if(accumListIterator != null){
							List accumValue = new ArrayList();
							while(accumListIterator.hasNext()){
		        				HippaCodeValue accumCode = (HippaCodeValue)accumListIterator.next();
		        				accumValue.add(accumCode.getValue());
								}
							if(null != accumValue && !accumValue.isEmpty()) {
								for(int i=0; i<accumValue.size();i++) {
									switch (i) {
										case 0: cell28.setCellValue(accumValue.get(i).toString());
												break;
										case 1: cell29.setCellValue(accumValue.get(i).toString());
												break;
										case 2: cell30.setCellValue(accumValue.get(i).toString());
												break;
										case 3: cell31.setCellValue(accumValue.get(i).toString());
												break;
										case 4: cell32.setCellValue(accumValue.get(i).toString());
												break;
										case 5: cell33.setCellValue(accumValue.get(i).toString());
												break;
										case 6: cell34.setCellValue(accumValue.get(i).toString());
												break;
										case 7: cell35.setCellValue(accumValue.get(i).toString());
												break;
										case 8: cell36.setCellValue(accumValue.get(i).toString());
												break;
										case 9: cell37.setCellValue(accumValue.get(i).toString());
												break;
										default: 
												break;
									}
								}
							 }
							}
						}
					}
		            
					cell39.setCellValue(mappingFromMap.getHsd01Value());//HSD01
					if(null != mappingFromMap.getHsd02Value() && mappingFromMap.getHsd02Value().size() != 0) {
						cell40.setCellValue(StringUtils.join(mappingFromMap.getHsd02Value().toArray(), ","));
	        		}else {
	        			cell40.setCellValue(DomainConstants.EMPTY);
	        		}
					//cell30.setCellValue(mappingFromMap.getHsd02Value());//HSD02
					cell41.setCellValue(mappingFromMap.getHsd03Value());//HSD03
					cell42.setCellValue(mappingFromMap.getHsd04Value());//HSD04
					cell43.setCellValue(mappingFromMap.getHsd05Value());//HSD05
					cell44.setCellValue(mappingFromMap.getHsd06Value());//HSD06
					cell45.setCellValue(mappingFromMap.getHsd07Value());//HSD07
					cell46.setCellValue(mappingFromMap.getHsd08Value());//HSD08
					
					List umRuleList = mappingFromMap.getUmRuleValues();
		            String umRule = "";
		            int umRuleCount = 0;
		            if(umRuleList != null) {
		            	Collections.sort(umRuleList);
		                Iterator umRuleListIterator = umRuleList.iterator();
		                if(umRuleListIterator != null) {
		                    while(umRuleListIterator.hasNext()) {
		                    	umRuleCount++;
		                        if (umRuleCount != 1) {
		                        	umRule = umRule+", "+(String)umRuleListIterator.next();
		                        } else {
		                        	umRule = 	(String)umRuleListIterator.next();
		                        }
		                    }
		                }
		            }
					cell47.setCellValue(umRule);
					cell48.setCellValue(mappingFromMap.getMappingComplete());
	}
	/**
	 * @param mappingFromMap
	 */
	private static List<EB03Association> getAssociatedEB03CodesForWpd(Mapping mappingFromMap,List<String> associatedList,
			String indvdlEb03AssnInd) {
		List<EB03Association> associatedEb03AssnList = new ArrayList<EB03Association>();
		List<String> assnList = new ArrayList<String>();
		if(null!=mappingFromMap.getEb03())
		{
			if((null!=mappingFromMap.getEb03().getEb03Association()) && 
					(mappingFromMap.getEb03().getEb03Association().size() != 0))
					{
						associatedEb03AssnList = mappingFromMap.getEb03().getEb03Association();
						if(null != associatedEb03AssnList && associatedEb03AssnList.size() != 0)
						{
							for (EB03Association eb03Assn : associatedEb03AssnList) {
								if(null != eb03Assn.getEb03() && (null != eb03Assn.getEb03().getValue() || "".equals(eb03Assn.getEb03().getValue())))
								{
									 if (!assnList.contains(eb03Assn.getEb03().getValue())) {
									    	assnList.add(eb03Assn.getEb03().getValue());
									    }
								}
							}
						}
						/*List<EB03Association> eb03AssnList = mappingFromMap.getEb03().getEb03Association();
						for(EB03Association assn : eb03AssnList) {
							if((null != assn.getIii02()) && (null != assn.getIii02().getValue()) 
									&& (!"".equals(assn.getIii02().getValue()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null != assn.getNoteType()) && (null != assn.getNoteType().getValue()) 
									&& (!"".equals(assn.getNoteType().getValue()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null != assn.getMessage()) 
									&& (!"-".equals(assn.getMessage()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null != assn.getMsgReqdInd()) 
									&& (!"-".equals(assn.getMsgReqdInd()))) {
								associatedList.add(assn.getEb03String());
								associatedEb03AssnList.add(assn);
							}
							if((null == assn.getIii02()) && (null == assn.getNoteType()) &&
									("-".equals(assn.getMessage())) && ("-".equals(assn.getMsgReqdInd()))){
								unAssociatedList.add(assn.getEb03String());
							}*/
					}
		}
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		if("Y".equals(indvdlEb03AssnInd))
		{
			if(null != assnList && assnList.size() != 0)
			{
				for(String assnStr : assnList)
				{
					EB03Association eb03AssnObj = new EB03Association();
					for(EB03Association eb03Assoc : associatedEb03AssnList)
					{
						if(null != eb03Assoc && null != eb03Assoc.getEb03())
						{
							if(assnStr.equals(eb03Assoc.getEb03().getValue()))
							{
								eb03AssnObj.setEb03(eb03Assoc.getEb03());
								if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
									eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
								}
								if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
										&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
									eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
								}
								if((null != eb03Assoc.getMessage()) 
										&& (!"-".equals(eb03Assoc.getMessage()))) {
									eb03AssnObj.setMessage(eb03Assoc.getMessage());
								}
								if((null != eb03Assoc.getMsgReqdInd()) 
										&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
									eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
								}
								if(null != eb03Assoc.getExtndMsg1()){										 
									eb03AssnObj.setExtndMsg1(eb03Assoc.getExtndMsg1());
								}
								if(null != eb03Assoc.getExtndMsg2()){										 
									eb03AssnObj.setExtndMsg2(eb03Assoc.getExtndMsg2());
								}
								if(null != eb03Assoc.getExtndMsg3()){										 
									eb03AssnObj.setExtndMsg3(eb03Assoc.getExtndMsg3());
								}
								if(null != eb03Assoc.getNetworkInd()){										 
									eb03AssnObj.setNetworkInd(eb03Assoc.getNetworkInd());
								}
								if(null != eb03Assoc.getHighPrfrmnNonTierdMsg()){
									eb03AssnObj.setHighPrfrmnNonTierdMsg(eb03Assoc.getHighPrfrmnNonTierdMsg());
								}
								if(null != eb03Assoc.getHighPrfrmnTierdMsg()){
									eb03AssnObj.setHighPrfrmnTierdMsg(eb03Assoc.getHighPrfrmnTierdMsg());
								}
							}
						}
					}
					eb03AssnList.add(eb03AssnObj);
				}
			}
			//unAssociatedList.removeAll(assnList);
			/*List<String> tempUnassnList = new ArrayList<String>();
			if(null != associatedList && null != unAssociatedList)
			{
				for(String unAssociatedCode : unAssociatedList)
				{
					if(!associatedList.contains(unAssociatedCode))
					{
						tempUnassnList.add(unAssociatedCode);
					}
				}
			}*/
		}else {
			if(null != assnList && assnList.size() != 0)
			{
				EB03Association eb03AssnObj = new EB03Association();
				String eb03Str = StringUtils.join(assnList.toArray(), ",");
				HippaCodeValue eb03Code = new HippaCodeValue();
				eb03Code.setValue(eb03Str);
				eb03AssnObj.setEb03(eb03Code);
				String assnStr = assnList.get(0);
				for(EB03Association eb03Assoc : associatedEb03AssnList)
				{
					if(null != eb03Assoc && null != eb03Assoc.getEb03())
					{
						if(assnStr.equals(eb03Assoc.getEb03().getValue()))
						{
							if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
								eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
							}
							if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
									&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
								eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
							}
							if((null != eb03Assoc.getMessage()) 
									&& (!"-".equals(eb03Assoc.getMessage()))) {
								eb03AssnObj.setMessage(eb03Assoc.getMessage());
							}
							if((null != eb03Assoc.getMsgReqdInd()) 
									&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
								eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
							}
							if(null != eb03Assoc.getExtndMsg1()){										 
								eb03AssnObj.setExtndMsg1(eb03Assoc.getExtndMsg1());
							}
							if(null != eb03Assoc.getExtndMsg2()){										 
								eb03AssnObj.setExtndMsg2(eb03Assoc.getExtndMsg2());
							}
							if(null != eb03Assoc.getExtndMsg3()){										 
								eb03AssnObj.setExtndMsg3(eb03Assoc.getExtndMsg3());
							}
							if(null != eb03Assoc.getNetworkInd()){										 
								eb03AssnObj.setNetworkInd(eb03Assoc.getNetworkInd());
							}
							if(null != eb03Assoc.getHighPrfrmnNonTierdMsg()){
								eb03AssnObj.setHighPrfrmnNonTierdMsg(eb03Assoc.getHighPrfrmnNonTierdMsg());
							}
							if(null != eb03Assoc.getHighPrfrmnTierdMsg()){
								eb03AssnObj.setHighPrfrmnTierdMsg(eb03Assoc.getHighPrfrmnTierdMsg());
							}
						}
					}
				}
				eb03AssnList.add(eb03AssnObj);
			}
		}
		
		return eb03AssnList;
	}
	
	/**
	 * method to populate the headers in eBX report.
	 * @param contract
	 * @param workbook
	 * @param sheet
	 */
	private static void createEbxEwpdReportHeader(ContractVO contract, HSSFWorkbook workbook,HSSFSheet sheet) {
        HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        HSSFCell cell = sheet.createRow((short)2).createCell((short)0);
        cell.setCellStyle(cs);
        HSSFCell headers1 = sheet.createRow((short) 1).createCell((short) 0);
        HSSFCell headerVer = sheet.createRow((short) 1).createCell((short) 2);
        HSSFCell headers2 = sheet.createRow((short) 2).createCell((short) 0);
        HSSFCell headers3 = sheet.createRow((short) 4).createCell((short) 0);
        HSSFCell headers4 = sheet.createRow((short) 4).createCell((short) 1);
        HSSFCell headers5 = sheet.createRow((short) 4).createCell((short) 2);
        HSSFCell headers6 = sheet.createRow((short) 4).createCell((short) 3);
        HSSFCell headers7 = sheet.createRow((short) 4).createCell((short) 4);
        HSSFCell headers8 = sheet.createRow((short) 4).createCell((short) 5);
        HSSFCell headers9 = sheet.createRow((short) 4).createCell((short) 6);
        HSSFCell headers10 = sheet.createRow((short) 4).createCell((short) 7);
        HSSFCell headers11 = sheet.createRow((short) 4).createCell((short) 8);
        HSSFCell headers12 = sheet.createRow((short) 4).createCell((short) 9);
        HSSFCell headers13 = sheet.createRow((short) 4).createCell((short) 10);
        HSSFCell headers14 = sheet.createRow((short) 4).createCell((short) 11);
        HSSFCell headers15 = sheet.createRow((short) 4).createCell((short) 12);
        HSSFCell headers16 = sheet.createRow((short) 4).createCell((short) 13);
        HSSFCell headers17 = sheet.createRow((short) 4).createCell((short) 14);
        HSSFCell headers18 = sheet.createRow((short) 4).createCell((short) 15);
        HSSFCell headers19 = sheet.createRow((short) 4).createCell((short) 16);
        HSSFCell headers20 = sheet.createRow((short) 4).createCell((short) 17);
        HSSFCell headers21 = sheet.createRow((short) 4).createCell((short) 18);
        HSSFCell headers22 = sheet.createRow((short) 4).createCell((short) 19);
        HSSFCell headers23 = sheet.createRow((short) 4).createCell((short) 20);
        HSSFCell headers24 = sheet.createRow((short) 4).createCell((short) 21);
        HSSFCell headers25 = sheet.createRow((short) 4).createCell((short) 22);
        HSSFCell headers26 = sheet.createRow((short) 4).createCell((short) 23);
        HSSFCell headers27 = sheet.createRow((short) 4).createCell((short) 24);
        HSSFCell headers28 = sheet.createRow((short) 4).createCell((short) 25);
        HSSFCell headers29 = sheet.createRow((short) 4).createCell((short) 26);
        HSSFCell headers30 = sheet.createRow((short) 4).createCell((short) 27);
        HSSFCell headers31 = sheet.createRow((short) 4).createCell((short) 28);
        HSSFCell headers32 = sheet.createRow((short) 4).createCell((short) 29);
        HSSFCell headers33 = sheet.createRow((short) 4).createCell((short) 30);

        headers1.setCellValue("Contract Id");
        headerVer.setCellValue("Version");
        headers2.setCellValue("Date Segment");
        headers3.setCellValue("Benefit Component");
        headers4.setCellValue("Benefit");
        headers5.setCellValue("Header Rule");
        headers6.setCellValue("Tier Details");
        headers7.setCellValue("SPS Id Description");
        headers8.setCellValue("SPS Id");
        headers9.setCellValue("PVA");
        headers10.setCellValue("Format");
        headers11.setCellValue("Coded Value");
        headers12.setCellValue("EB01");
        headers13.setCellValue("EB02");
        headers14.setCellValue("EB03");
        headers15.setCellValue("EB06");
        headers16.setCellValue("EB09");
        headers17.setCellValue("III02");
        headers18.setCellValue("MESSAGE");
        headers19.setCellValue("MSG REQ IND");
        headers20.setCellValue("MESSAGE TYPE");
        headers21.setCellValue("ACCUM");
        headers22.setCellValue("SENSITIVE IND");
        headers23.setCellValue("HSD01");
        headers24.setCellValue("HSD02");
        headers25.setCellValue("HSD03");
        headers26.setCellValue("HSD04");
        headers27.setCellValue("HSD05");
        headers28.setCellValue("HSD06");
        headers29.setCellValue("HSD07");
        headers30.setCellValue("HSD08");
        headers31.setCellValue("UM Rule");
        headers32.setCellValue("Finalized");
        headers33.setCellValue("Not Applicable");
        headers3.setCellStyle(cs);
        headers4.setCellStyle(cs);
        headers5.setCellStyle(cs);
        headers6.setCellStyle(cs);
        headers7.setCellStyle(cs);
        headers8.setCellStyle(cs);
        headers9.setCellStyle(cs);
        headers10.setCellStyle(cs);
        headers11.setCellStyle(cs);
        headers12.setCellStyle(cs);
        headers13.setCellStyle(cs);
        headers14.setCellStyle(cs);
        headers15.setCellStyle(cs);
        headers16.setCellStyle(cs);
        headers17.setCellStyle(cs);
        headers18.setCellStyle(cs);
        headers19.setCellStyle(cs);
        headers20.setCellStyle(cs);
        headers21.setCellStyle(cs);
        headers22.setCellStyle(cs);
        headers23.setCellStyle(cs);
        headers24.setCellStyle(cs);
        headers25.setCellStyle(cs);
        headers26.setCellStyle(cs);
        headers27.setCellStyle(cs);
        headers28.setCellStyle(cs);
        headers29.setCellStyle(cs);
        headers30.setCellStyle(cs);
        headers31.setCellStyle(cs);
        headers32.setCellStyle(cs);
        headers33.setCellStyle(cs);
    }
	/**
	 * method to populate EWPD Contract Values to eBX report.
	 * @param contract
	 * @param workbook
	 * @param sheet
	 */
	private static void populateEbxEwpdContractValues(ContractVO contract, HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		int eb03Count;
		String eb03;
		int rowCount = 4;
		sheet.createRow((short) 1).createCell((short) 1).setCellValue(contract.getContractId());
		sheet.createRow((short) 1).createCell((short) 3).setCellValue(contract.getVersion());
		sheet.createRow((short) 2).createCell((short) 1).setCellValue(contract.getEffectiveDate());
		Map majorHeadings = contract.getMajorHeadings();
		if (majorHeadings != null) {
		//	Iterator majorHeadingsIterator = majorHeadings.keySet().iterator();
			Iterator majorHeadingsIterator = majorHeadings.entrySet().iterator();
			if (majorHeadingsIterator != null) {
				while (majorHeadingsIterator.hasNext()) {
				//	String majorHeadingDesc = (String) majorHeadingsIterator.next();
				//	MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) majorHeadings.get(majorHeadingDesc);
					MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) ((Map.Entry) majorHeadingsIterator.next()).getValue();
					Map minorHeadings = majorHeadingFromMap.getMinorHeadings();
					if (minorHeadings != null) {
					//	Iterator minorHeadingsIterator = minorHeadings.keySet().iterator();
						Iterator minorHeadingsIterator = minorHeadings.entrySet().iterator();
						if (minorHeadingsIterator != null) {
							while (minorHeadingsIterator.hasNext()) {
							//	String minorHeadingDesc = (String) minorHeadingsIterator.next();
							//	MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadings.get(minorHeadingDesc);
								MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
								Map mappings = minorHeadingFromMap.getMappings();
								Mapping ruleMapping = minorHeadingFromMap.getRuleMapping();
								
								if (mappings != null) {
									//Iterator mappingsIterator = mappings.keySet().iterator();
									Iterator mappingsIterator = mappings.entrySet().iterator();
									if (mappingsIterator != null) {
										while (mappingsIterator.hasNext()) {
											//String mappingKey = (String) mappingsIterator.next();
											ContractMappingVO mappingFromMap = (ContractMappingVO) ((Map.Entry) mappingsIterator.next()).getValue();
											if (mappingFromMap != null) {
												if (mappingFromMap.isMapped()) {
													if(null != ruleMapping.getEb03()) {
                                            			List<String> associatedList = new ArrayList<String>();
                                            			String indEb03Assn;
                                            			if("Y".equals(ruleMapping.getIndvdlEb03AssnIndicator()) ||
                                            					"Y".equals(mappingFromMap.getIndvdlEb03AssnIndicator())) {
                                            				indEb03Assn = "Y";
                                            			}else {
                                            				indEb03Assn = "N";
                                            			}
                                                    	/*String unAssnEb03CSV = null;
                                    					List<String> unAssociatedList = new ArrayList<String>();
                                    					List<EB03Association> associatedEB03AssnList = new ArrayList<EB03Association>();*/
                                            			List<EB03Association> associatedEB03AssnList = getAssociatedEB03CodesForEwpd(ruleMapping,mappingFromMap,
                                            					associatedList,indEb03Assn);
                                                    	/*if(null != unAssociatedList && unAssociatedList.size() != 0) {
                                                    		unAssnEb03CSV = StringUtils.join(unAssociatedList.toArray(), ",");
                                                    	}*/
                                                    	if(null != associatedEB03AssnList && associatedEB03AssnList.size() != 0) {
                                                    		for(EB03Association eb03AssnValue : associatedEB03AssnList)
                                                        	{
                                                    			rowCount++;
                                                    			createMappedRowsForEwpd(
                                                    					sheet,
                														cs, rowCount,
                														majorHeadingFromMap,
                														minorHeadingFromMap,
                														ruleMapping,
                														mappingFromMap,
        																eb03AssnValue);
                                                            }
                                                        }else {
                                                        	rowCount++;
                                                        	createMappedRowsForEwpd(
                                                        			sheet,
            														cs, rowCount,
            														majorHeadingFromMap,
            														minorHeadingFromMap,
            														ruleMapping,
            														mappingFromMap,
        															null);
                                                        }
                                                    	/*if(null != unAssnEb03CSV && !"".equals(unAssnEb03CSV)) {
                                                    		rowCount++;
                                                        	createRows(
        															contract,
        															sheet,
        															rowCount,
        															majorHeadingFromMap,
        															minorHeadingFromMap,
        															mappingFromMap,
        															null,
        															unAssnEb03CSV);
                                                    	}*/
                                            		}
                                            		else {
                                            			rowCount++;
                                            			createMappedRowsForEwpd(
                                            					sheet,
        														cs, rowCount,
        														majorHeadingFromMap,
        														minorHeadingFromMap,
        														ruleMapping,
        														mappingFromMap,
    															null);
                                            		}
												} else {
													rowCount++;
													HSSFCell cell0 = sheet.createRow((short) rowCount).createCell((short) 0);
	                                            	HSSFCell cell1 = sheet.createRow((short) rowCount).createCell((short) 1);
	                                            	HSSFCell cell2 = sheet.createRow((short) rowCount).createCell((short) 2);
	                                            	HSSFCell cell3 = sheet.createRow((short) rowCount).createCell((short) 3);
	                                            	HSSFCell cell4 = sheet.createRow((short) rowCount).createCell((short) 4);
	                                            	HSSFCell cell5 = sheet.createRow((short) rowCount).createCell((short) 5);
	                                            	HSSFCell cell6 = sheet.createRow((short) rowCount).createCell((short) 6);
	                                            	HSSFCell cell7 = sheet.createRow((short) rowCount).createCell((short) 7);
	                                            	HSSFCell cell8 = sheet.createRow((short) rowCount).createCell((short) 8);
	                                            	sheet.createRow((short) rowCount).createCell((short) 9);
	                                            	sheet.createRow((short) rowCount).createCell((short) 10);
	                                            	sheet.createRow((short) rowCount).createCell((short) 11);
	                                            	sheet.createRow((short) rowCount).createCell((short) 12);
	                                            	sheet.createRow((short) rowCount).createCell((short) 13);
	                                            	sheet.createRow((short) rowCount).createCell((short) 14);
	                                            	HSSFCell cell15 = sheet.createRow((short) rowCount).createCell((short) 15);
	                                            	HSSFCell cell16 = sheet.createRow((short) rowCount).createCell((short) 17);
	                                            	HSSFCell cell17 = sheet.createRow((short) rowCount).createCell((short) 18);
	                                            	//sheet.createRow((short) rowCount).createCell((short) 18);
	                                            	sheet.createRow((short) rowCount).createCell((short) 19);
	                                            	sheet.createRow((short) rowCount).createCell((short) 20);
	                                            	sheet.createRow((short) rowCount).createCell((short) 21);
	                                            	sheet.createRow((short) rowCount).createCell((short) 22);
	                                            	sheet.createRow((short) rowCount).createCell((short) 23);
	                                            	sheet.createRow((short) rowCount).createCell((short) 24);
	                                            	sheet.createRow((short) rowCount).createCell((short) 25);
	                                            	sheet.createRow((short) rowCount).createCell((short) 26);
	                                            	sheet.createRow((short) rowCount).createCell((short) 27);
	                                            	sheet.createRow((short) rowCount).createCell((short) 28);
	                                            	sheet.createRow((short) rowCount).createCell((short) 29);
	                                            	sheet.createRow((short) rowCount).createCell((short) 30);
	                                            	sheet.createRow((short) rowCount).createCell((short) 16);
	                                            	cell0.setCellValue(majorHeadingFromMap.getDescriptionText());
													cell1.setCellValue(minorHeadingFromMap.getDescriptionText());
													
													if(null != ruleMapping && null !=ruleMapping.getRule() ){
														cell2.setCellValue(ruleMapping.getRule().getHeaderRuleId());
													}
													// Code change to include the tier description- July release
													if (null != mappingFromMap.getContractMapping() && null != mappingFromMap.getContractMapping().getTierDescription()){
														cell3.setCellValue(mappingFromMap.getContractMapping().getTierDescription());
													}
													if (null != mappingFromMap.getSpsId()) {
														SPSId sps = mappingFromMap.getSpsId();
														cell4.setCellValue(sps.getSpsDesc());
                                                    	cell5.setCellValue(sps.getSpsId());
                                                    	cell6.setCellValue(sps.getLinePVA());
                                                    	cell7.setCellValue(sps.getLineDataType());
                                                    	cell8.setCellValue(sps.getLineValue());
                                                    	cell4.setCellStyle(cs);
    	                            					cell5.setCellStyle(cs);
													}
													cell15.setCellValue(mappingFromMap.getMessage());
                                                    cell16.setCellValue(mappingFromMap.getMsg_type_required());
                                                    cell17.setCellValue(mappingFromMap.getNoteTypeCodeValue()); //MESSAGE type
												}
                                        	}
                                    	}
                                	}
                            	}
                        	}
                    	}
                	}
            	}
        	}
    	}
	}
	/**
	 * @param sheet
	 * @param cs
	 * @param rowCount
	 * @param majorHeadingFromMap
	 * @param minorHeadingFromMap
	 * @param ruleMapping
	 * @param mappingFromMap
	 */
	private static void createMappedRowsForEwpd(HSSFSheet sheet,
			HSSFCellStyle cs, int rowCount,
			MajorHeadingsVO majorHeadingFromMap,
			MinorHeadingsVO minorHeadingFromMap, Mapping ruleMapping,
			ContractMappingVO mappingFromMap,EB03Association eb03AssnValue) {
		int eb03Count;
		String eb03;
		HSSFCell cell0 = sheet.createRow((short) rowCount).createCell((short) 0);
		HSSFCell cell1 = sheet.createRow((short) rowCount).createCell((short) 1);
		HSSFCell cell2 = sheet.createRow((short) rowCount).createCell((short) 2);
		HSSFCell cell3 = sheet.createRow((short) rowCount).createCell((short) 3);
		HSSFCell cell4 = sheet.createRow((short) rowCount).createCell((short) 4);
		HSSFCell cell5 = sheet.createRow((short) rowCount).createCell((short) 5);
		HSSFCell cell6 = sheet.createRow((short) rowCount).createCell((short) 6);
		HSSFCell cell7 = sheet.createRow((short) rowCount).createCell((short) 7);
		HSSFCell cell8 = sheet.createRow((short) rowCount).createCell((short) 8);
		HSSFCell cell9 = sheet.createRow((short) rowCount).createCell((short) 9);
		HSSFCell cell10 = sheet.createRow((short) rowCount).createCell((short) 10);
		HSSFCell cell11 = sheet.createRow((short) rowCount).createCell((short) 11);
		HSSFCell cell12 = sheet.createRow((short) rowCount).createCell((short) 12);
		HSSFCell cell13 = sheet.createRow((short) rowCount).createCell((short) 13);
		HSSFCell cell14 = sheet.createRow((short) rowCount).createCell((short) 14);
		HSSFCell cell15 = sheet.createRow((short) rowCount).createCell((short) 15);
		HSSFCell cell16 = sheet.createRow((short) rowCount).createCell((short) 16);
		HSSFCell cell17 = sheet.createRow((short) rowCount).createCell((short) 17);
		HSSFCell cell19 = sheet.createRow((short) rowCount).createCell((short) 19);
		HSSFCell cell20 = sheet.createRow((short) rowCount).createCell((short) 20);
		HSSFCell cell21 = sheet.createRow((short) rowCount).createCell((short) 21);
		HSSFCell cell22 = sheet.createRow((short) rowCount).createCell((short) 22);
		HSSFCell cell23 = sheet.createRow((short) rowCount).createCell((short) 23);
		HSSFCell cell24 = sheet.createRow((short) rowCount).createCell((short) 24);
		HSSFCell cell25 = sheet.createRow((short) rowCount).createCell((short) 25);
		HSSFCell cell26 = sheet.createRow((short) rowCount).createCell((short) 26);
		HSSFCell cell27 = sheet.createRow((short) rowCount).createCell((short) 27);
		HSSFCell cell28 = sheet.createRow((short) rowCount).createCell((short) 28);
		HSSFCell cell29 = sheet.createRow((short) rowCount).createCell((short) 29);
		HSSFCell cell30 = sheet.createRow((short) rowCount).createCell((short) 30);
		HSSFCell cellAccumsEwpd = sheet.createRow((short) rowCount).createCell((short) 18);
		SPSId sps = mappingFromMap.getSpsId();
		cell0.setCellValue(majorHeadingFromMap.getDescriptionText());
		cell1.setCellValue(minorHeadingFromMap.getDescriptionText());
		
		if(null != ruleMapping){
			Rule rule = ruleMapping.getRule();
			if(null != rule){
				cell2.setCellValue(rule.getHeaderRuleId());
			}
		}
		// Code change to include the tier description- July release
		if (null != mappingFromMap.getContractMapping() && null != mappingFromMap.getContractMapping().getTierDescription()){
			cell3.setCellValue(mappingFromMap.getContractMapping().getTierDescription());
		}
		        if (sps != null) {
		        	cell4.setCellValue(sps.getSpsDesc());
		        	cell5.setCellValue(sps.getSpsId());
		        	cell6.setCellValue(sps.getLinePVA());
		        	cell7.setCellValue(sps.getLineDataType());
		        	cell8.setCellValue(sps.getLineValue());
		        }
		        cell9.setCellValue(mappingFromMap.getEB01Value());
		        cell10.setCellValue(mappingFromMap.getEB02Value());
		        boolean isSPSNotApplicable = false;
		        boolean isRuleNotApplicable = false;
		        if (null != mappingFromMap.getVariableMappingStatus()
		        		&& DomainConstants.STATUS_NOT_APPLICABLE.equals(mappingFromMap.getVariableMappingStatus())) {
		        	isSPSNotApplicable = true;
		        }
		        if (null != ruleMapping && ruleMapping.isMapped()) {
		        	/*List eb03List = ruleMapping.getEb03Values();
		            eb03 = "";
		            eb03Count = 0;
		            if (eb03List != null) {
		                Iterator eb03ListIterator = eb03List.iterator();
		                if (eb03ListIterator != null) {
		                    while (eb03ListIterator.hasNext()) {
		                        eb03Count++;
		                        if (eb03Count != 1) {
		                            eb03 = eb03 + "," + (String) eb03ListIterator.next();
		                        } else {
		                            eb03 = 	(String) eb03ListIterator.next();
		                        }
		                    }
		                }
		            }
		            cell11.setCellValue(eb03);*/
		        	 if(null != eb03AssnValue)
						{
				        	cell11.setCellValue(eb03AssnValue.getEb03().getValue());
							if(null != eb03AssnValue.getIii02List() && eb03AssnValue.getIii02List().size() > 0)
							{
								List<String> iii02ValueList = new ArrayList<String>();
								for(HippaCodeValue value : eb03AssnValue.getIii02List())
								{
									iii02ValueList.add(value.getValue()); 
								}
								String csvIii02 = StringUtils.join(iii02ValueList.toArray(), ",");
								cell14.setCellValue(csvIii02);
							}
							if(null != eb03AssnValue.getMessage() && !("-".equals(eb03AssnValue.getMessage()) || 
									"".equals(eb03AssnValue.getMessage().trim())))
							{
								 cell15.setCellValue(eb03AssnValue.getMessage());
							}
				            if(null != eb03AssnValue.getMsgReqdInd() && !("-".equals(eb03AssnValue.getMsgReqdInd()) || 
									"".equals(eb03AssnValue.getMsgReqdInd().trim())))
							{
				            	 cell16.setCellValue(eb03AssnValue.getMsgReqdInd());
							}
				            if(null != eb03AssnValue.getNoteType())
							{
				            	 cell17.setCellValue(eb03AssnValue.getNoteType().getValue());//MESSAGE type
							}
							/*Mapping customMesgMapping = (Mapping) minorHeadingFromMap.getMappings().get(mappingFromMap.getSpsId().getSpsId());
							if(null != customMesgMapping.getEb03() &&  null != customMesgMapping.getEb03().getEb03Association() &&
									customMesgMapping.getEb03().getEb03Association().size() > 0){
								for(EB03Association mesgEb03 : customMesgMapping.getEb03().getEb03Association()) {
									if(null != mesgEb03.getEb03() && null != mesgEb03.getEb03().getValue()) {
										if(eb03AssnValue.getEb03().getValue().trim().equalsIgnoreCase(mesgEb03.getEb03().getValue().trim())) {
											if(null != mesgEb03.getMessage() && !"-".equals(mesgEb03.getMessage()))
											{
												 cell15.setCellValue(mesgEb03.getMessage());
											}
								            if(null != mesgEb03.getMsgReqdInd() && !"-".equals(mesgEb03.getMsgReqdInd()))
											{
								            	 cell16.setCellValue(mesgEb03.getMsgReqdInd());
											}
								            if(null != mesgEb03.getNoteType())
											{
								            	 cell17.setCellValue(mesgEb03.getNoteType().getValue());//MESSAGE type
											}
										}
									}
								}
							}*/
						}else {
							if(null != ruleMapping.getEb03Values() && ruleMapping.getEb03Values().size() != 0) {
								cell9.setCellValue(StringUtils.join(ruleMapping.getEb03Values().toArray(), ","));
			        		}else {
			        			cell9.setCellValue(DomainConstants.EMPTY);
			        		}
							cell14.setCellValue(DomainConstants.EMPTY);
							cell15.setCellValue(DomainConstants.EMPTY);
							cell16.setCellValue(DomainConstants.EMPTY);
							cell17.setCellValue(DomainConstants.EMPTY);
				        }
		            cell19.setCellValue(ruleMapping.getSensitiveBenefitIndicator());
		            if (null != ruleMapping
						.getVariableMappingStatus()
						&& DomainConstants.STATUS_NOT_APPLICABLE
								.equalsIgnoreCase(ruleMapping
										.getVariableMappingStatus())) {
					isRuleNotApplicable = true;
		            }
		        }
		        if (isSPSNotApplicable || isRuleNotApplicable) {
		        	cell30.setCellValue(DomainConstants.Y);
		        }
		        else {
		        	cell30.setCellValue(DomainConstants.N);
		        }
		        cell12.setCellValue(mappingFromMap.getEB06Value());
		        cell13.setCellValue(mappingFromMap.getEB09Value());
		        /*cell14.setCellValue(mappingFromMap.getIi02Value());
		        cell15.setCellValue(mappingFromMap.getMessage());
		        cell16.setCellValue(mappingFromMap.getMsg_type_required());
		        cell17.setCellValue(mappingFromMap.getNoteTypeCodeValue()); *///MESSAGE type
		        HippaSegment accum = mappingFromMap.getAccum();
				if (accum != null) {
				List accumList = accum.getHippaCodeSelectedValues();
				if (accumList != null) {
					Iterator accumListIterator = accumList.iterator();
					if (accumListIterator != null) {
						while (accumListIterator.hasNext()) {
		    				HippaCodeValue accumCode = (HippaCodeValue) accumListIterator.next();
		    				cellAccumsEwpd.setCellValue(accumCode.getValue()); //accum 1
							}
						}
					}
				}
				cell20.setCellValue(mappingFromMap.getHsd01Value()); //HSD01
				if(null != mappingFromMap.getHsd02Value() && mappingFromMap.getHsd02Value().size() != 0) {
					cell21.setCellValue(StringUtils.join(mappingFromMap.getHsd02Value().toArray(), ","));
        		}else {
        			cell21.setCellValue(DomainConstants.EMPTY);
        		}
				//cell21.setCellValue(mappingFromMap.getHsd02Value()); //HSD02
				cell22.setCellValue(mappingFromMap.getHsd03Value()); //HSD03
				cell23.setCellValue(mappingFromMap.getHsd04Value()); //HSD04
				cell24.setCellValue(mappingFromMap.getHsd05Value()); //HSD05
				cell25.setCellValue(mappingFromMap.getHsd06Value()); //HSD06
				cell26.setCellValue(mappingFromMap.getHsd07Value()); //HSD07
				cell27.setCellValue(mappingFromMap.getHsd08Value()); //HSD08
				
				String umRule = "";
				if (null != ruleMapping && ruleMapping.isMapped()) {
					List umRuleList = ruleMapping.getUmRuleValues();
		            if(umRuleList != null) {
		                Iterator umRuleListIterator = umRuleList.iterator();
		                if(umRuleListIterator != null) {
		                    while(umRuleListIterator.hasNext()) {
		                        umRule = 	(String)umRuleListIterator.next();
		                    }
		                }
		            }
				}
				cell28.setCellValue(umRule);
				cell29.setCellValue(mappingFromMap.getMappingComplete());
				 if (null != ruleMapping && !ruleMapping.isMapped()) {
						cell0.setCellStyle(cs);
						cell1.setCellStyle(cs);
						cell2.setCellStyle(cs);
						cell3.setCellStyle(cs);
						cell4.setCellStyle(cs);
						cell5.setCellStyle(cs);
						cell6.setCellStyle(cs);
						cell7.setCellStyle(cs);
						cell8.setCellStyle(cs);
						cell9.setCellStyle(cs);
						cell10.setCellStyle(cs);
						cell11.setCellStyle(cs);
						cell12.setCellStyle(cs);
						cell13.setCellStyle(cs);
						cell14.setCellStyle(cs);
						cell15.setCellStyle(cs);
						cell16.setCellStyle(cs);
						cell17.setCellStyle(cs);
						cell19.setCellStyle(cs);
						cell20.setCellStyle(cs);
						cell21.setCellStyle(cs);
						cell22.setCellStyle(cs);
						cell23.setCellStyle(cs);
						cell24.setCellStyle(cs);
						cell25.setCellStyle(cs);
						cell26.setCellStyle(cs);
						cell27.setCellStyle(cs);
						cell28.setCellStyle(cs);
						cell29.setCellStyle(cs);
						cell30.setCellStyle(cs);
						cellAccumsEwpd.setCellStyle(cs);
		            }
	}
	
	private static List<EB03Association> getAssociatedEB03CodesForEwpd(Mapping ruleMapping,Mapping msgMapping,List<String> associatedList,
			String indvdlEb03AssnInd) {
		List<EB03Association> associatedEb03AssnList = new ArrayList<EB03Association>();
		List<String> assnList = new ArrayList<String>();
		if(null!=ruleMapping.getEb03())
		{
			if((null!=ruleMapping.getEb03().getEb03Association()) && 
					(ruleMapping.getEb03().getEb03Association().size() != 0))
					{
						associatedEb03AssnList.addAll(ruleMapping.getEb03().getEb03Association());
						if(null != associatedEb03AssnList && associatedEb03AssnList.size() != 0)
						{
							for (EB03Association eb03Assn : associatedEb03AssnList) {
								if(null != eb03Assn.getEb03() && (null != eb03Assn.getEb03().getValue() || "".equals(eb03Assn.getEb03().getValue())))
								{
									 if (!assnList.contains(eb03Assn.getEb03().getValue())) {
									    	assnList.add(eb03Assn.getEb03().getValue());
									    }
								}
							}
						}
					}
		}
		if(null!=msgMapping.getEb03())
		{
			if((null!=msgMapping.getEb03().getEb03Association()) && 
					(msgMapping.getEb03().getEb03Association().size() != 0))
					{
						associatedEb03AssnList.addAll(msgMapping.getEb03().getEb03Association());
					}
		}
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		if("Y".equals(indvdlEb03AssnInd))
		{
			if(null != assnList && assnList.size() != 0)
			{
				for(String assnStr : assnList)
				{
					EB03Association eb03AssnObj = new EB03Association();
					for(EB03Association eb03Assoc : associatedEb03AssnList)
					{
						if(null != eb03Assoc && null != eb03Assoc.getEb03())
						{
							if(assnStr.equals(eb03Assoc.getEb03().getValue()))
							{
								eb03AssnObj.setEb03(eb03Assoc.getEb03());
								if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
									eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
								}
								if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
										&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
									eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
								}
								if((null != eb03Assoc.getMessage()) 
										&& (!"-".equals(eb03Assoc.getMessage()))) {
									eb03AssnObj.setMessage(eb03Assoc.getMessage());
								}
								if((null != eb03Assoc.getMsgReqdInd()) 
										&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
									eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
								}
							}
						}
					}
					eb03AssnList.add(eb03AssnObj);
				}
			}
			//unAssociatedList.removeAll(assnList);
			/*List<String> tempUnassnList = new ArrayList<String>();
			if(null != associatedList && null != unAssociatedList)
			{
				for(String unAssociatedCode : unAssociatedList)
				{
					if(!associatedList.contains(unAssociatedCode))
					{
						tempUnassnList.add(unAssociatedCode);
					}
				}
			}*/
		}else {
			if(null != assnList && assnList.size() != 0)
			{
				EB03Association eb03AssnObj = new EB03Association();
				String eb03Str = StringUtils.join(assnList.toArray(), ",");
				HippaCodeValue eb03Code = new HippaCodeValue();
				eb03Code.setValue(eb03Str);
				eb03AssnObj.setEb03(eb03Code);
				String assnStr = assnList.get(0);
				for(EB03Association eb03Assoc : associatedEb03AssnList)
				{
					if(null != eb03Assoc && null != eb03Assoc.getEb03())
					{
						if(assnStr.equals(eb03Assoc.getEb03().getValue()))
						{
							if(null != eb03Assoc.getIii02List() && eb03Assoc.getIii02List().size() > 0) {
								eb03AssnObj.setIii02List(eb03Assoc.getIii02List());
							}
							if((null != eb03Assoc.getNoteType()) && (null != eb03Assoc.getNoteType().getValue()) 
									&& (!"".equals(eb03Assoc.getNoteType().getValue()))) {
								eb03AssnObj.setNoteType(eb03Assoc.getNoteType());
							}
							if((null != eb03Assoc.getMessage()) 
									&& (!"-".equals(eb03Assoc.getMessage()))) {
								eb03AssnObj.setMessage(eb03Assoc.getMessage());
							}
							if((null != eb03Assoc.getMsgReqdInd()) 
									&& (!"-".equals(eb03Assoc.getMsgReqdInd()))) {
								eb03AssnObj.setMsgReqdInd(eb03Assoc.getMsgReqdInd());
							}
						}
					}
				}
				eb03AssnList.add(eb03AssnObj);
			}
		}
		
		return eb03AssnList;
	}
}
