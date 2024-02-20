/*
 * <ExcelSearchView.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.web.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Orientation;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.wellpoint.ets.bx.mapping.domain.vo.ReportWrapper;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;
import com.wellpoint.ets.bx.mapping.domain.vo.VariableHdngVO;
import com.wellpoint.ets.bx.mapping.util.BxUtil;


/**
 * @author UST-GLOBAL This is an class for generating excel for advance search
 *       
 */
public class ExcelSearchView extends AbstractJExcelView {

	public List variableMappingList = null;
	SearchResult searchResult = null;
	boolean isUnMapped;
	private final static String CONTRACTVARIABLE = "CONTRACT VARIABLE";
	private final static String VARIABLEDESCRIPTION = "VARIABLE DESCRIPTION";
	private final static String PVA = "PVA";
	private final static String DATATYPE = "DATA TYPE / FORMAT";
	private final static String MAJORHEADING = "MAJOR HEADING";
	private final static String MINORHEADING = "MINOR HEADING";
	private final static String DATECREATED = "DATE CREATED";
	private final static String PROCEDURESEXCLUDEDINDICATOR="PROCEDURES EXCLUDED INDICATOR";
	private final static String USERID = "USER ID";
	private final static String STATUS = "STATUS";
	private final static String SYSTEM = "SYSTEM";
	private final static String BENEFIT_STRUCTURE = "BENEFIT STRUCTURE";
	
	private final static String EB01 = "EB01";
	private final static String EB02 = "EB02";
	private final static String EB03 = "EB03";
	private final static String EB06 = "EB06";
	private final static String EB09 = "EB09";
	private final static String III02 = "III02";
	private final static String MESSAGE = "MESSAGE";
	private final static String MESSAGEREQIND = "MESSAGE REQ IND";
	private final static String MESSAGETYPECODE = "MESSAGE TYPE CODE";
	private final static String HSD01 = "HSD01";
	private final static String HSD02 = "HSD02";
	private final static String HSD03 = "HSD03";
	private final static String HSD04 = "HSD04";
	private final static String HSD05 = "HSD05";
	private final static String HSD06 = "HSD06";
	private final static String HSD07 = "HSD07";
	private final static String HSD08 = "HSD08";
	private final static String ACCUMULATORS = "ACCUMULATORS";
	private final static String ACCUMNOTREQUIREDINDICATOR = "ACCUM NOT REQUIRED INDICATOR";
	private final static String FINALIZED = "FINALIZED";
	private final static String USERNAME = "USER NAME";
	//January Release
	private final static String AUDIT_LOCK = "AUDIT LOCK";
	
	//BXNI Nov Release
	private final static String CATEGORY_CODE = "CATEGORY CODE";
	private final static String START_AGE = "START AGE";
	private final static String END_AGE = "END AGE";
	private final static String UM_RULE = "UM RULE";
	
	
	
	private static Logger log = Logger
	.getLogger(ExcelSearchView.class);

	public ExcelSearchView(List variableMappingList, boolean isUnMapped) {
		this.variableMappingList = variableMappingList;
		this.isUnMapped = isUnMapped;
	}

	protected void buildExcelDocument(Map model, WritableWorkbook workBook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (variableMappingList != null) {
			long starttime = System.currentTimeMillis();
			response.setHeader("content-disposition", "attachment; filename=" + "eBX Search Variable Report.xls");
			WritableSheet sheet = workBook.createSheet("eBX Search Variable Report",0);
	       // setReportProperties(sheet);
	        createVariableReportHeader(workBook,sheet,isUnMapped);
	       populateVariableReportValues(variableMappingList, workBook, sheet, isUnMapped);
	        long endtime = System.currentTimeMillis();
			log.info(">>>>>>>>>> Time taken for advance search variable excel processing is "
					+ (endtime - starttime));
		}
		
	}
	
	/**
	 * Method to populate the advance search report header.
	 * @param workbook
	 * @param sheet
	 * @param isUnMapped
	 */
	private static void createVariableReportHeader(WritableWorkbook workbook,WritableSheet sheet, boolean isUnMapped) {
        
        int columnIndex = 0;
        Label label =null;
        
		try {
			 WritableFont wfont = new WritableFont(WritableFont.ARIAL, 
	        	      10, WritableFont.BOLD);
	        WritableCellFormat wCellFormat = new WritableCellFormat(wfont);	
	        wCellFormat.setBackground(Colour.GRAY_25);
	        wCellFormat.setBorder(Border.ALL,  BorderLineStyle.THIN, Colour.BLACK);
	        wCellFormat.setOrientation(Orientation.PLUS_90);
	        wCellFormat.setAlignment(Alignment.CENTRE);
	        label = new Label(columnIndex++,0,CONTRACTVARIABLE,wCellFormat);
			sheet.addCell(label);
			sheet.setColumnView(columnIndex, 25);
			label = new Label(columnIndex++,0,VARIABLEDESCRIPTION,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,PVA,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,DATATYPE,wCellFormat);
			sheet.addCell(label);
			//display category code in advance search report -- BXNI November
			label = new Label(columnIndex++,0,CATEGORY_CODE,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,SYSTEM,wCellFormat);
			sheet.addCell(label);
			sheet.setColumnView(columnIndex, 25);
			label = new Label(columnIndex++,0,BENEFIT_STRUCTURE,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,MAJORHEADING,wCellFormat);
			sheet.addCell(label);
			sheet.setColumnView(columnIndex, 25);
			label = new Label(columnIndex++,0,MINORHEADING,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,DATECREATED,wCellFormat);
			sheet.addCell(label);
			//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
			label = new Label(columnIndex++,0,PROCEDURESEXCLUDEDINDICATOR,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,USERID,wCellFormat);
			sheet.addCell(label);
			if (isUnMapped) {
				label = new Label(columnIndex++,0,USERNAME,wCellFormat);
				sheet.addCell(label);
			}
			label = new Label(columnIndex++,0,STATUS,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,EB01,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,EB02,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,EB03,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,EB06,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,EB09,wCellFormat);
			sheet.addCell(label);
			//display start age in advance search report -- BXNI November
			label = new Label(columnIndex++,0,START_AGE,wCellFormat);
			sheet.addCell(label);
			//display end age in advance search report -- BXNI November
			label = new Label(columnIndex++,0,END_AGE,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,III02,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,MESSAGE,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,MESSAGEREQIND,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,MESSAGETYPECODE,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,ACCUMNOTREQUIREDINDICATOR,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD01,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD02,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD03,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD04,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD05,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD06,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD07,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,HSD08,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,ACCUMULATORS,wCellFormat);
			sheet.addCell(label);
			//display UM rules as comma separated in advance search report -- BXNI November
			label = new Label(columnIndex++,0,UM_RULE,wCellFormat);
			sheet.addCell(label);
			
			label = new Label(columnIndex++,0,FINALIZED,wCellFormat);
			sheet.addCell(label);
			label = new Label(columnIndex++,0,AUDIT_LOCK,wCellFormat);
			sheet.addCell(label);
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * Method will populate the cell contents for each row in the excel sheet
	 * As part of SSCR 19537, if a variable has an EB03 association, 
	 * then the variable will be splitted into as many number of rows as the number of Eb03's it has.
	 * If the variable has two different major/minor headings also, 
	 * then each variable with each major/minor will be split into as many number of rows as the number of Eb03's it has.
	 * @param searchingList
	 * @param workbook
	 * @param sheet
	 * @param searchCriteria
	 */
	private static void populateVariableReportValues(List searchingList,
            WritableWorkbook workbook, WritableSheet sheet, boolean searchCriteria) {
      int rowCount = 1;
      ReportWrapper reportWrapper = (ReportWrapper) searchingList.get(0);
      List variableList  = reportWrapper.getVariableDetailList();
      Map headingsMap = reportWrapper.getVariableHeaderList();
      if (!variableList.isEmpty()) {
    	  Iterator variableItr = variableList.iterator();
    	  List headingList = null;
    	  SearchResult searchResult = null;
    	  VariableHdngVO searchResultSub = null;
    	  String variableId = "";
    	  
		  
    	  while (variableItr.hasNext()) {
        	  searchResult = (SearchResult) variableItr.next();
        	  variableId = searchResult.getVariableId();
        	  
        	  String eb03Array[] = {};
    		  String iii02Array[] = {};
    		  String noteTypeCodeArray[]= {};
    		  String messageArray[] = {};
    		  String messageReqdIndArray[]  = {};
        	  
        	  if(("Y").equals(searchResult.getIndividualEB03AssnInd())){
	        	  int eB03Count = (null != (searchResult.getEB03()) && null != searchResult.getEB03().split(",")
	        	  ? searchResult.getEB03().split(",").length : 0);
	        	  eb03Array = getValuesInArray(searchResult.getEB03(), eB03Count);
	    		  iii02Array = getValuesInArray(searchResult.getIII02(), eB03Count);
	    		  noteTypeCodeArray= getValuesInArray(searchResult.getNoteTypeCode(), eB03Count);
	    		  messageArray = getValuesInArray(searchResult.getMessageText(), eB03Count);
	    		  messageReqdIndArray = getValuesInArray(searchResult.getMsgReqrdIndctr(), eB03Count);
        	  }
        	  
        	       	  
        	  
        	  if (null != headingsMap) {
        		  searchResultSub = (VariableHdngVO) headingsMap.get(variableId);
    	    	  if (null!= searchResultSub) {
        	    	 // for (int itr = 0; itr < headingList.size(); itr++) {
        	    		 // searchResultSub = (VariableHdngVO) headingList.get(itr);
        	    		  /*if (itr == 0) {
        	    			  generateFullCell(sheet, rowCount,searchResult,
        	    					  searchCriteria, searchResultSub.getMajorHeading(),
        	    					  searchResultSub.getMinorHeading());
        	    		  } else {
        	    			  generateCellWithHeadings (sheet, rowCount,
        	    					  searchResultSub.getMajorHeading(),
        	    					  searchResultSub.getMinorHeading());
        	    		  }*/
        	    		  if(("Y").equals(searchResult.getIndividualEB03AssnInd())){
                			  /*String eb03Array[] = searchResult.getEB03().split(",");
                			  String iii02Array[] = searchResult.getIII02().split(",");
                			  String noteTypeCodeArray[] = searchResult.getNoteTypeCode().split(",");
                			  String messageArray[] = searchResult.getMessageText().split(",");
                			  String messageReqdIndArray[] = searchResult.getMsgReqrdIndctr().split(",");*/
                			  
                			  if(null != eb03Array){
                				  for(int i = 0; i< eb03Array.length; i++){
                					  
                    	    			  generateFullCell(sheet, rowCount,searchResult,
                    	    					  searchCriteria, searchResultSub.getMajorHeading(),
                    	    					  searchResultSub.getMinorHeading(), searchResultSub.getBenefitStructure());
                    	    			  generateCellForEB03Assn(sheet, rowCount,
                    	    					  eb03Array[i],iii02Array[i], messageArray[i], messageReqdIndArray[i],noteTypeCodeArray[i] );
                					  rowCount++;
                				  }
                			  }else{
                				  generateFullCell(sheet, rowCount,searchResult,
            	    					  searchCriteria, searchResultSub.getMajorHeading(),
            	    					  searchResultSub.getMinorHeading(), searchResultSub.getBenefitStructure());
                				  rowCount++;
                			  }
                		  }else{
                			  generateFullCell(sheet, rowCount,searchResult,
        	    					  searchCriteria, searchResultSub.getMajorHeading(),
        	    					  searchResultSub.getMinorHeading(), searchResultSub.getBenefitStructure());
                			  rowCount++;
                		  }
        	    		 
        	    	 // }
    	    	  } else {
    	    		  if(("Y").equals(searchResult.getIndividualEB03AssnInd())){
            			 /* String eb03Array[] = searchResult.getEB03().split(",");
            			  String iii02Array[] = searchResult.getIII02().split(",");
            			  String noteTypeCodeArray[] = searchResult.getNoteTypeCode().split(",");
            			  String messageArray[] = searchResult.getMessageText().split(",");
            			  String messageReqdIndArray[] = searchResult.getMsgReqrdIndctr().split(",");*/
            			  
            			  if(null != eb03Array){
            				  for(int i = 0; i< eb03Array.length; i++){
            					  generateFullCell(sheet, rowCount, searchResult, searchCriteria, "", "", "");
            					  generateCellForEB03Assn(sheet, rowCount,
            	    					  eb03Array[i],iii02Array[i], messageArray[i], messageReqdIndArray[i],noteTypeCodeArray[i]);
            					  rowCount++;
            				  }
            			  }
            		  }else{
            			  generateFullCell(sheet, rowCount, searchResult, searchCriteria, "", "", "");
        	    		  rowCount++;
            		  }
    	    		  
    	    	  }
        	  } else {
        		  if(("Y").equals(searchResult.getIndividualEB03AssnInd())){
        			 /* String eb03Array[] = searchResult.getEB03().split(",");
        			  String iii02Array[] = searchResult.getIII02().split(",");
        			  String noteTypeCodeArray[] = searchResult.getNoteTypeCode().split(",");
        			  String messageArray[] = searchResult.getMessageText().split(",");
        			  String messageReqdIndArray[] = searchResult.getMsgReqrdIndctr().split(",");*/
        			  
        			  if(null != eb03Array){
        				  for(int i = 0; i< eb03Array.length; i++){
        					  generateFullCell(sheet, rowCount, searchResult, searchCriteria, "", "", "");
        					  generateCellForEB03Assn(sheet, rowCount,
        	    					  eb03Array[i],iii02Array[i], messageArray[i], messageReqdIndArray[i],noteTypeCodeArray[i]);
        					  rowCount++;
        				  }
        			  }
        		  }else{
        			  generateFullCell(sheet, rowCount, searchResult, searchCriteria, "", "", "");
            		  rowCount++;
        		  }
        		 
        	  }
        	  if(null!=headingsMap){
        		  headingsMap.remove(variableId);
        	  }
        	  variableItr.remove();
          }
      }
	}
	
	private static String[] getValuesInArray(String val, int eB03Count) {
		  
		if(null != val && !val.equalsIgnoreCase("")){
			String hippaCodeArray[] = new String[eB03Count];
			String splitArray[] = val.split(",");
			if(null == hippaCodeArray || hippaCodeArray.length == 0){
				//hippaCodeArray= new String[eB03Count];
				int count = 0;
				while(count < eB03Count){
					hippaCodeArray[count] = "";
					count++;
				}//WLPRD00972125 -start
			}else {
				  
				  if(splitArray.length>eB03Count){
					  
					  System.arraycopy(splitArray,0,hippaCodeArray,0,eB03Count);
				  }else{
					  
					  System.arraycopy(splitArray,0,hippaCodeArray,0,splitArray.length);
					  
					  for(int i=splitArray.length;i<eB03Count;i++)
						{
							hippaCodeArray[i]="";
						}
				  }
		
				
				
			}//WLPRD00972125 -end
			return hippaCodeArray;
		}else{
			int count = 0;
			String hippaCodeArray[] = new String[eB03Count];
			while(count < eB03Count){
				hippaCodeArray[count] = "";
				count++;
			}
			return hippaCodeArray;
		}
	}

	/**SSCR 19537 Feb 2014
	 * This method is obsolete after this initiative changes, should be removed.
	 * Create additional row to display major and minor heading only
	 * @param sheet
	 * @param rowCount
	 * @param majorHeading
	 * @param minorHeading
	 */
	/*Changed column count as part of Existing Issue in EBX application in June Release*/
	private static void generateCellWithHeadings(WritableSheet sheet, int rowCount,
			String majorHeading, String minorHeading){
		
		try {
			Label label5 = new Label(6,rowCount,majorHeading);
			sheet.addCell(label5);
			Label label6 = new Label(7,rowCount,minorHeading);
			sheet.addCell(label6);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create new row for the variable along with first major and minor heading
	 * @param sheet
	 * @param rowCount
	 * @param searchResult
	 * @param searchCriteria
	 * @param majorHeading
	 * @param minorHeading
	 */
	private static void generateFullCell(WritableSheet sheet, int rowCount,
			SearchResult searchResult, boolean isUnMapped,
			String majorHeading, String minorHeading,  String benefitStucture) {
		int columnIndex = 0;
		Label label=null;
		try {
			label = new Label(columnIndex++,rowCount,searchResult.getVariableId());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getDescription());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getPva());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getDataType());
			sheet.addCell(label);
			//display category code in advance search report -- BXNI November
			label = new Label(columnIndex++,rowCount,searchResult.getCategoryCode());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getSystem());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,benefitStucture);
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,majorHeading);
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,minorHeading);
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getFormattedDate());
			sheet.addCell(label);
			//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
			label = new Label(columnIndex++,rowCount,searchResult.getProcedureExcludedInd());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getUser());
			sheet.addCell(label);
			if (isUnMapped) {
				
				label = new Label(columnIndex++,rowCount,searchResult.getUserName());
				sheet.addCell(label);
				}
			
			label = new Label(columnIndex++,rowCount,searchResult.getStatus());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getEB01());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getEB02());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getEB03());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getEB06());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getEB09());
			sheet.addCell(label);
			//display start age in advance search report -- BXNI November
			label = new Label(columnIndex++,rowCount,searchResult.getStartAge());
			sheet.addCell(label);
			//display end age in advance search report -- BXNI November
			label = new Label(columnIndex++,rowCount,searchResult.getEndAge());
			sheet.addCell(label);
			/**SSCR 19537 Feb 2014 changes -- START
			 * the Eb03 association values will be fetched from DB as comma seperated,
			 * for non individual mappings, the first value in the comma seperated string
			 * will be the mapping attached at variable level
			 **/
			//label = new Label(columnIndex++,rowCount,BxUtil.removeFirstCommaFromEB03Assn(searchResult.getIII02()));
			//sheet.addCell(label);
			if(StringUtils.isNotEmpty(searchResult.getIII02())){
				String[] lbelArray = {};
				if(null != searchResult.getIII02().split(",")){
					lbelArray = searchResult.getIII02().split(",");
				}
				label = new Label(columnIndex++,rowCount,(null != lbelArray && lbelArray.length > 0 && null != lbelArray[0]? lbelArray[0].trim() : ""));
				sheet.addCell(label);
			}
			//label = new Label(columnIndex++,rowCount,BxUtil.removeFirstCommaFromEB03Assn(searchResult.getMessageText()));
			//sheet.addCell(label);
			if(StringUtils.isNotEmpty(searchResult.getMessageText())){
				String[] lbelArray = {};
				if(null != searchResult.getMessageText().split(",")){
					lbelArray = searchResult.getMessageText().split(",");
				}
				label = new Label(columnIndex++,rowCount,(null != lbelArray && lbelArray.length > 0 && null != lbelArray[0]? lbelArray[0].trim() : ""));
				sheet.addCell(label);
			}
			//label = new Label(columnIndex++,rowCount,BxUtil.removeFirstCommaFromEB03Assn(searchResult.getMsgReqrdIndctr()));
			//sheet.addCell(label);
			if(StringUtils.isNotEmpty(searchResult.getMsgReqrdIndctr())){
				String[] lbelArray = {};
				if(null != searchResult.getMsgReqrdIndctr().split(",")){
					lbelArray = searchResult.getMsgReqrdIndctr().split(",");
				}
				label = new Label(columnIndex++,rowCount,(null != lbelArray && lbelArray.length > 0 && null != lbelArray[0]? lbelArray[0].trim() : ""));
				sheet.addCell(label);
			}
			//label = new Label(columnIndex++,rowCount,BxUtil.removeFirstCommaFromEB03Assn(searchResult.getNoteTypeCode()));
			//sheet.addCell(label);
			if(StringUtils.isNotEmpty(searchResult.getNoteTypeCode())){
				String[] lbelArray = {};
				if(null != searchResult.getNoteTypeCode().split(",")){
					lbelArray = searchResult.getNoteTypeCode().split(",");
				}
				label = new Label(columnIndex++,rowCount,(null != lbelArray && lbelArray.length > 0 && null != lbelArray[0]? lbelArray[0].trim() : ""));
				sheet.addCell(label);
			}
			/**SSCR 19537 Feb 2014 changes -- END**/
			
			label = new Label(columnIndex++,rowCount,searchResult.getAccumNotReqrdIndctr());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd01());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd02());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd03());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd04());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd05());         
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd06());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd07());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getHsd08());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getAccumulator());
			sheet.addCell(label);
			//display UM rules as comma separated in advance search report -- BXNI November
			label = new Label(columnIndex++,rowCount,searchResult.getCommaSeperatedUMRules());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getFinalizedFlag());
			sheet.addCell(label);
			label = new Label(columnIndex++,rowCount,searchResult.getAuditLock());
			sheet.addCell(label);
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * SSCR 19537 Feb 2014
	 * Method is called to create the cells for EB03 Associations 
	 * This method will rewrite the Eb03 association
	 * values written by he original method call generateFullCell()
	 * @param sheet
	 * @param rowCount
	 * @param eb03
	 * @param iii02
	 * @param msg
	 * @param msgReqdInd
	 * @param noteType
	 */
	private static void generateCellForEB03Assn(WritableSheet sheet, int rowCount,
			String eb03, String iii02, String msg, String msgReqdInd, String noteType) {
		if(StringUtils.isNotEmpty(eb03)){
			eb03 = eb03.trim();
		}
		if(StringUtils.isNotEmpty(iii02)){
			iii02 = iii02.trim();
		}
		if(StringUtils.isNotEmpty(msg)){
			msg = msg.trim();
		}
		if(StringUtils.isNotEmpty(msgReqdInd)){
			msgReqdInd = msgReqdInd.trim();
		}
		if(StringUtils.isNotEmpty(noteType)){
			noteType = noteType.trim();
		}
		Label label=null;
		try {
			label = new Label(14,rowCount,eb03);
			sheet.addCell(label);

			label = new Label(19,rowCount,iii02);
			sheet.addCell(label);

			label = new Label(20,rowCount,msg);
			sheet.addCell(label);

			label = new Label(21,rowCount,msgReqdInd);
			sheet.addCell(label);

			label = new Label(22,rowCount,noteType);
			sheet.addCell(label);


		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
}