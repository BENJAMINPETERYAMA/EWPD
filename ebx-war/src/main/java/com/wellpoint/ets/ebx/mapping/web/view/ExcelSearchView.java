package com.wellpoint.ets.ebx.mapping.web.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wellpoint.ets.bx.mapping.domain.service.Eb03Validator;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchCriteria;
import com.wellpoint.ets.bx.mapping.domain.vo.SearchResult;

/**
 * @author UST-GLOBAL This is an class for generating excel for search
 *       
 */


public class ExcelSearchView extends AbstractExcelView {

	public List ruleIDList = null;
	public List spsIDList = null;
	public List customMessageList = null;
	SearchCriteria searchCriteria = null;

	public ExcelSearchView(List ruleIDList, List spsIDList,
			List customMessageList, SearchCriteria searchCriteria) {
		this.ruleIDList = ruleIDList;
		this.spsIDList = spsIDList;
		this.customMessageList = customMessageList;
		this.searchCriteria = searchCriteria;

	}

	protected void buildExcelDocument(Map model, HSSFWorkbook workBook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (ruleIDList != null) {
			response.setHeader("content-disposition", "attachment; filename="
					+ "eBX Search Rule ID Report.xls");
			HSSFSheet sheet = workBook.createSheet("eBX Search Rule ID Report");
			setReportProperties(sheet);
			createRuleHeader(workBook, sheet, searchCriteria);
			populateRuleValues(ruleIDList, workBook, sheet, searchCriteria);
		} else if (null != spsIDList) {
			response.setHeader("content-disposition", "attachment; filename="
					+ "eBX Search SPS ID Report.xls");
			HSSFSheet sheet = workBook.createSheet("eBX Search SPS ID Report");
			setReportProperties(sheet);
			createSpsIdHeader( workBook, sheet, searchCriteria);
			populateSpsIdValues(spsIDList, workBook, sheet, searchCriteria);

		} else if (null != customMessageList) {
			response.setHeader("content-disposition", "attachment; filename="
					+ "eBX Search Message Text Report.xls");
			HSSFSheet sheet = workBook
					.createSheet("eBX Search Message Text Report");
			setReportProperties(sheet);
			createMessageTextHeader(workBook, sheet);
			populateMessageTextValues(customMessageList, workBook, sheet);
		}

	}

	private static void setReportProperties(HSSFSheet sheet) {
		sheet.setAutobreaks(true);
		sheet.setDefaultColumnWidth((short) 11);
		sheet.getPrintSetup().setFitHeight((short) 1);
		sheet.getPrintSetup().setFitWidth((short) 1);
	}

	private static void createRuleHeader(
			HSSFWorkbook workbook, HSSFSheet sheet,
			SearchCriteria searchCriteria) {
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
		/*HSSFCell cell = sheet.createRow((short) 2).createCell((short) 0);
		cell.setCellStyle(cs);*/
        int columnIndex = 0;
        HSSFRow row = sheet.createRow((short) 0);
		HSSFCell headers1 = row.createCell((short) columnIndex++);
		HSSFCell headers2 = row.createCell((short) columnIndex++);
		HSSFCell headers3 = row.createCell((short) columnIndex++);
		HSSFCell headers4 = row.createCell((short) columnIndex++);
		HSSFCell headers5 = row.createCell((short) columnIndex++);
		HSSFCell headers6 = row.createCell((short) columnIndex++);
		HSSFCell headers7 = row.createCell((short) columnIndex++);
		HSSFCell headers13 = row.createCell((short) columnIndex++);
		
		
		headers1.setCellValue("RULE ID");
		headers2.setCellValue("DESCRIPTION");
		headers3.setCellValue("EB03");
		headers4.setCellValue("III02");
		headers5.setCellValue("DATE CREATED");
		headers6.setCellValue("STATUS");
		headers7.setCellValue("SENSITIVE BENEFIT INDICATOR");
		//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
		headers13.setCellValue("PROCEDURES EXCLUDED INDICATOR");
		
		
		headers1.setCellStyle(cs);
		headers2.setCellStyle(cs);
		headers3.setCellStyle(cs);
		headers4.setCellStyle(cs);
		headers5.setCellStyle(cs);
		headers6.setCellStyle(cs);
		headers7.setCellStyle(cs);
		headers13.setCellStyle(cs);
		
		if (null != searchCriteria) {
			if(searchCriteria.isMapped() || searchCriteria.isNotApplicable()|| (!searchCriteria.isUnMapped()
					&& !searchCriteria.isMapped() && !searchCriteria
					.isNotApplicable())){
				HSSFCell headers8 = row.createCell((short) columnIndex++);
				headers8.setCellValue("USER ID");
				headers8.setCellStyle(cs);
			}
			if (searchCriteria.isUnMapped()
					|| (!searchCriteria.isUnMapped()
							&& !searchCriteria.isMapped() && !searchCriteria
							.isNotApplicable())) {
				HSSFCell headers9 = row.createCell((short) columnIndex++);
				HSSFCell headers10 = row.createCell((short) columnIndex++);
				HSSFCell headers11 = row.createCell((short) columnIndex++);
				headers9.setCellValue("BUSINESS ENTITY");
				headers10.setCellValue("BUSINESS GROUP ");
				headers11.setCellValue("MBU");
				headers9.setCellStyle(cs);
				headers10.setCellStyle(cs);
				headers11.setCellStyle(cs);
			}
		}
		//display UM rules as comma separated in advance search report -- BXNI November
		HSSFCell headers12 = row.createCell((short) columnIndex++);
		headers12.setCellValue("UM RULE");
		headers12.setCellStyle(cs);
	}

	private static void populateRuleValues(List searchingList,
			HSSFWorkbook workbook, HSSFSheet sheet,
			SearchCriteria searchCriteria) {

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 4, (short) size);
		int rowCount = 0;
		SearchResult searchResult;
		for (int i = 0; i < searchingList.size(); i++) {
			searchResult = (SearchResult) searchingList.get(i);
			if(i != 0 && null != searchingList.get(i-1)) {
				SearchResult prevSearchResult = (SearchResult)searchingList.get(i-1);
				if (searchResult.getHeaderRuleId().equalsIgnoreCase(prevSearchResult.getHeaderRuleId())) {
					continue;
				}
			}
			rowCount = createHeaderRuleRows(sheet, searchCriteria,
						rowCount, searchResult);
				
		}
	}

	/**
	 * @param sheet
	 * @param searchCriteria
	 * @param rowCount
	 * @param searchResult
	 * @return
	 */
	private static int createHeaderRuleRows(HSSFSheet sheet,
			SearchCriteria searchCriteria, int rowCount,
			SearchResult searchResult) {
		if(("Y").equalsIgnoreCase(searchResult.getIndividualEB03AssnInd())) {
			if(null != searchResult.getEB03()) {
				List<String> eb03List = new ArrayList<String>(Arrays.asList(searchResult.getEB03().split(",")));
				List<String> iii02List = new ArrayList<String>(Arrays.asList(searchResult.getIII02().split(";")));
				for(int i=0; i<eb03List.size(); i++) {
					int columnIndex = 0;
					rowCount++;
					HSSFRow row = sheet.createRow(rowCount);
					row.createCell((short) columnIndex++).setCellValue(searchResult.getHeaderRuleId());
					row.createCell((short) columnIndex++).setCellValue(searchResult.getDescription());
					row.createCell((short) columnIndex++).setCellValue(eb03List.get(i).trim());
					if(!"#".equals(iii02List.get(i).trim())) {
						row.createCell((short) columnIndex++).setCellValue(iii02List.get(i).trim());
					}else {
						row.createCell((short) columnIndex++).setCellValue("");
					}
					row.createCell((short) columnIndex++).setCellValue(searchResult.getFormattedDate());
					row.createCell((short) columnIndex++).setCellValue(searchResult.getStatus());
					row.createCell((short) columnIndex++).setCellValue(searchResult.getSensitiveBenefitIndicator());
					//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
					row.createCell((short) columnIndex++).setCellValue(searchResult.getProcedureExcludedInd());
					if (null != searchCriteria) {
						if(searchCriteria.isMapped() || searchCriteria.isNotApplicable()|| (!searchCriteria.isUnMapped()
								&& !searchCriteria.isMapped() && !searchCriteria
								.isNotApplicable())){
							row.createCell((short) columnIndex++).setCellValue(searchResult.getLastUpdatedUser());
						}
						if (searchCriteria.isUnMapped() || (!searchCriteria.isUnMapped()
								&& !searchCriteria.isMapped() && !searchCriteria
								.isNotApplicable())) {
							row.createCell((short) columnIndex++).setCellValue(searchResult.getBusinessEntity());
							row.createCell((short) columnIndex++).setCellValue(searchResult.getBusinessGroup());
							row.createCell((short) columnIndex++).setCellValue(searchResult.getContractMbu());
						}
					}
					//display UM rules as comma separated in advance search report -- BXNI November
					row.createCell((short) columnIndex++).setCellValue(searchResult.getCommaSeperatedUMRules());
				}
				
			}else {
				int columnIndex = 0;
				rowCount++;
				HSSFRow row = sheet.createRow(rowCount);
				row.createCell((short) columnIndex++).setCellValue(searchResult.getHeaderRuleId());
				row.createCell((short) columnIndex++).setCellValue(searchResult.getDescription());
				row.createCell((short) columnIndex++).setCellValue("");
				row.createCell((short) columnIndex++).setCellValue("");
				row.createCell((short) columnIndex++).setCellValue(searchResult.getFormattedDate());
				row.createCell((short) columnIndex++).setCellValue(searchResult.getStatus());
				row.createCell((short) columnIndex++).setCellValue(searchResult.getSensitiveBenefitIndicator());
				//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
				row.createCell((short) columnIndex++).setCellValue(searchResult.getProcedureExcludedInd());
				
				if (null != searchCriteria) {
					if(searchCriteria.isMapped() || searchCriteria.isNotApplicable()|| (!searchCriteria.isUnMapped()
							&& !searchCriteria.isMapped() && !searchCriteria
							.isNotApplicable())){
						row.createCell((short) columnIndex++).setCellValue(searchResult.getLastUpdatedUser());
					}
					if (searchCriteria.isUnMapped() || (!searchCriteria.isUnMapped()
							&& !searchCriteria.isMapped() && !searchCriteria
							.isNotApplicable())) {
						row.createCell((short) columnIndex++).setCellValue(searchResult.getBusinessEntity());
						row.createCell((short) columnIndex++).setCellValue(searchResult.getBusinessGroup());
						row.createCell((short) columnIndex++).setCellValue(searchResult.getContractMbu());
					}
				}
				//display UM rules as comma separated in advance search report -- BXNI November
				row.createCell((short) columnIndex++).setCellValue(searchResult.getCommaSeperatedUMRules());
			
			}
		}else {
			int columnIndex = 0;
			rowCount++;
			HSSFRow row = sheet.createRow(rowCount);
			row.createCell((short) columnIndex++).setCellValue(searchResult.getHeaderRuleId());
			row.createCell((short) columnIndex++).setCellValue(searchResult.getDescription());
			List<String> iii02List = new ArrayList<String>(Arrays.asList(searchResult.getIII02().split(";")));
			if(null != searchResult.getEB03()) {
				row.createCell((short) columnIndex++).setCellValue(searchResult.getEB03().trim());
				if(!"#".equals(iii02List.get(0).trim())) {
					row.createCell((short) columnIndex++).setCellValue(iii02List.get(0).trim());
				}else {
					row.createCell((short) columnIndex++).setCellValue("");
				}
			}else {
				row.createCell((short) columnIndex++).setCellValue("");
				row.createCell((short) columnIndex++).setCellValue("");
			}
			row.createCell((short) columnIndex++).setCellValue(searchResult.getFormattedDate());
			row.createCell((short) columnIndex++).setCellValue(searchResult.getStatus());
			row.createCell((short) columnIndex++).setCellValue(searchResult.getSensitiveBenefitIndicator());
			//display PROCEDURE EXCLUSIVE INDICATOR in advance search report -- BXNI JUNE RELEASE
			row.createCell((short) columnIndex++).setCellValue(searchResult.getProcedureExcludedInd());
			
			if (null != searchCriteria) {
				if(searchCriteria.isMapped() || searchCriteria.isNotApplicable()|| (!searchCriteria.isUnMapped()
						&& !searchCriteria.isMapped() && !searchCriteria
						.isNotApplicable())){
					row.createCell((short) columnIndex++).setCellValue(searchResult.getLastUpdatedUser());
				}
				if (searchCriteria.isUnMapped() || (!searchCriteria.isUnMapped()
						&& !searchCriteria.isMapped() && !searchCriteria
						.isNotApplicable())) {
					row.createCell((short) columnIndex++).setCellValue(searchResult.getBusinessEntity());
					row.createCell((short) columnIndex++).setCellValue(searchResult.getBusinessGroup());
					row.createCell((short) columnIndex++).setCellValue(searchResult.getContractMbu());
				}
			}
			//display UM rules as comma separated in advance search report -- BXNI November
			row.createCell((short) columnIndex++).setCellValue(searchResult.getCommaSeperatedUMRules());
		
		}
		return rowCount;
	}

	private static void createMessageTextHeader(
			HSSFWorkbook workbook, HSSFSheet sheet) {
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
		/*HSSFCell cell = sheet.createRow((short) 2).createCell((short) 0);
		cell.setCellStyle(cs);*/
		HSSFCell headers1 = sheet.createRow((short) 0).createCell((short) 0);
		HSSFCell headers2 = sheet.createRow((short) 0).createCell((short) 1);
		HSSFCell headers3 = sheet.createRow((short) 0).createCell((short) 2);
		HSSFCell headers4 = sheet.createRow((short) 0).createCell((short) 3);
		HSSFCell headers5 = sheet.createRow((short) 0).createCell((short) 4);
		HSSFCell headers6 = sheet.createRow((short) 0).createCell((short) 5);
		HSSFCell headers7 = sheet.createRow((short) 0).createCell((short) 6);
		HSSFCell headers8 = sheet.createRow((short) 0).createCell((short) 7);
		HSSFCell headers9 = sheet.createRow((short) 0).createCell((short) 8);
		HSSFCell headers10 = sheet.createRow((short) 0).createCell((short) 9);
		HSSFCell headers11 = sheet.createRow((short) 0).createCell((short) 10);

		headers1.setCellValue("HEADER RULE ID");
		headers2.setCellValue("HEADER RULE DESCRIPTION");
		headers3.setCellValue("SPS PARAMETER ID");
		headers4.setCellValue("SPS DESCRIPTION");
		headers5.setCellValue("EB03");
		headers6.setCellValue("MESSAGE TEXT");
		headers7.setCellValue("MESSAGE INDICATOR");
		headers8.setCellValue("NOTE TYPE CODE");
		headers9.setCellValue("DATE CREATED");
		headers10.setCellValue("STATUS");
		headers11.setCellValue("USER ID");

		headers1.setCellStyle(cs);
		headers2.setCellStyle(cs);
		headers3.setCellStyle(cs);
		headers4.setCellStyle(cs);
		headers5.setCellStyle(cs);
		headers6.setCellStyle(cs);
		headers7.setCellStyle(cs);
		headers8.setCellStyle(cs);
		headers9.setCellStyle(cs);
		headers10.setCellStyle(cs);
		headers11.setCellStyle(cs);

	}

	private static void populateMessageTextValues(List searchingList,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 3, (short) size);

		int rowCount = 0;
		SearchResult searchResult;
		for (int i = 0; i < searchingList.size(); i++) {
			searchResult = (SearchResult) searchingList.get(i);
			String eb03Array[] = searchResult.getEB03().replaceAll(",$|^,", "").split(",");
			String noteTypeCodeArray[] = searchResult.getNoteTypeCode().replaceAll(",$|^,", "").split(",");
			String messageArray[] = searchResult.getMessageText().replaceAll(",$|^,", "").split(",");
			/*String messageReqdIndArray[] = null;
			if(StringUtils.isNotEmpty(searchResult.getEB03())) {
				eb03Array = searchResult.getEB03().split(",");
			}
			if(StringUtils.isNotEmpty(searchResult.getNoteTypeCode())) {
				noteTypeCodeArray = searchResult.getNoteTypeCode().split(",");
			}
			  	if(StringUtils.isNotEmpty(searchResult.getMessageText())) {
			  		messageArray = searchResult.getMessageText().split(",");
			}
			  	if(StringUtils.isNotEmpty(searchResult.getMsgReqrdIndctr())) {
			  		messageReqdIndArray = searchResult.getMsgReqrdIndctr().split(",");
			}*/
			if("Y".equalsIgnoreCase(searchResult.getIndividualEB03AssnInd())) {
  			  	if(null != eb03Array){
  				  for(int j = 0; j< eb03Array.length; j++){
  					rowCount++;
  					generateFullCellsForMessage(sheet, cs, rowCount, searchResult);
  					String noteTypeCode;
  					String message;
  					//String messageReqdInd;
  					if(null == noteTypeCodeArray) {
  						noteTypeCode = null;
  					}else {
  						noteTypeCode = noteTypeCodeArray[j];
  					}
  					if(null == messageArray) {
  						message = null;
  					}else {
  						message = messageArray[j];
  					}
  					generateEb03AssociationCellsForEb03(sheet, rowCount, searchResult,eb03Array[j],noteTypeCode,
  								message,searchResult.getMessageIndicator(),searchResult.getIndividualEB03AssnInd());
  				  }
  			  	}else {
		  			  	rowCount++;
	  					generateFullCellsForMessage(sheet, cs, rowCount, searchResult);
	  					/*generateEb03AssociationCellsForEb03(sheet, rowCount, searchResult,null,noteTypeCodeArray[k],
								messageArray[k],searchResult.getMsgReqrdIndctr(),searchResult.getIndividualEB03AssnInd());*/
  				}
			}else {
					rowCount++;
					String csvEb03 = StringUtils.join(eb03Array, ",");
					String noteTypeCode;
  					String message;
					if(null == noteTypeCodeArray) {
  						noteTypeCode = null;
  					}else {
  						noteTypeCode = Arrays.asList(noteTypeCodeArray).get(0);
  					}
  					if(null == messageArray) {
  						message = null;
  					}else {
  						message = Arrays.asList(messageArray).get(0);
  					}
					generateFullCellsForMessage(sheet, cs, rowCount, searchResult);
					generateEb03AssociationCellsForEb03(sheet, rowCount, searchResult,csvEb03,noteTypeCode,
							message,searchResult.getMessageIndicator(),searchResult.getIndividualEB03AssnInd());
			}
		}
	}

	/**
	 * @param sheet
	 * @param cs
	 * @param rowCount
	 * @param searchResult
	 */
	private static void generateFullCellsForMessage(HSSFSheet sheet, HSSFCellStyle cs,
			int rowCount, SearchResult searchResult) {
		HSSFCell cell0 = sheet.createRow((short) rowCount).createCell(
				(short) 0);
		HSSFCell cell1 = sheet.createRow((short) rowCount).createCell(
				(short) 1);
		HSSFCell cell2 = sheet.createRow((short) rowCount).createCell(
				(short) 2);
		HSSFCell cell3 = sheet.createRow((short) rowCount).createCell(
				(short) 3);
		HSSFCell cell8 = sheet.createRow((short) rowCount).createCell(
				(short) 8);
		HSSFCell cell9 = sheet.createRow((short) rowCount).createCell(
				(short) 9);
		HSSFCell cell10 = sheet.createRow((short) rowCount).createCell(
				(short) 10);
		
		cell0.setCellValue(searchResult.getHeaderRuleId());
		cell1.setCellValue(searchResult.getHeaderRuleDescription());
		cell2.setCellValue(searchResult.getSpsId());
		cell3.setCellValue(searchResult.getSpsRuleDescription());
		cell8.setCellValue(searchResult.getFormattedDate());
		cell9.setCellValue(searchResult.getStatus());
		cell10.setCellValue(searchResult.getLastUpdatedUser());

		cs.setWrapText(true);
		cell1.setCellStyle(cs);
		cell3.setCellStyle(cs);
	}

	/**
	 * @param sheet
	 * @param rowCount
	 * @param searchResult
	 */
	private static void generateEb03AssociationCellsForEb03(HSSFSheet sheet,
			int rowCount, SearchResult searchResult,String eb03,String noteType,
			String message,String mesgReqdInd,String indvdlEb03Assn) {
		HSSFCell cell4 = sheet.createRow((short) rowCount).createCell(
				(short) 4);
		HSSFCell cell5 = sheet.createRow((short) rowCount).createCell(
				(short) 5);
		HSSFCell cell6 = sheet.createRow((short) rowCount).createCell(
				(short) 6);
		HSSFCell cell7 = sheet.createRow((short) rowCount).createCell(
				(short) 7);
		if(null != eb03) {
			cell4.setCellValue(eb03);
			cell5.setCellValue(message);
			cell6.setCellValue(mesgReqdInd);
			cell7.setCellValue(noteType);
		} else {
			cell4.setCellValue("");
			cell5.setCellValue("");
			cell6.setCellValue("");
			cell7.setCellValue("");
		}
	}

	private static void createSpsIdHeader(
			HSSFWorkbook workbook, HSSFSheet sheet,
			SearchCriteria searchCriteria) {
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
        int columnIndex = 0;
        HSSFRow row = sheet.createRow((short) 0);
		HSSFCell headers1 = row.createCell((short) columnIndex++);
		HSSFCell headers2 = row.createCell((short) columnIndex++);
		HSSFCell headers3 = row.createCell((short) columnIndex++);
		HSSFCell headers4 = row.createCell((short) columnIndex++);
		HSSFCell headers5 = row.createCell((short) columnIndex++);
		HSSFCell headers6 = row.createCell((short) columnIndex++);
		HSSFCell headers7 = row.createCell((short) columnIndex++);
		HSSFCell headers8 = row.createCell((short) columnIndex++);
		HSSFCell headers9 = row.createCell((short) columnIndex++);
		HSSFCell headers10 = row.createCell((short) columnIndex++);
		if (null != searchCriteria) {
			if (searchCriteria.isMapped()
					|| searchCriteria.isNotApplicable()
					|| (!searchCriteria.isUnMapped()
							&& !searchCriteria.isMapped() && !searchCriteria
							.isNotApplicable())) {
				HSSFCell headers11 = row.createCell((short) columnIndex++);
				headers11.setCellValue("USER ID");
				headers11.setCellStyle(cs);
			}
		}
		HSSFCell headers12 = row.createCell((short) columnIndex++);
		HSSFCell headers13 = row.createCell((short) columnIndex++);
		HSSFCell headers14 = row.createCell((short) columnIndex++);
		HSSFCell headers15 = row.createCell((short) columnIndex++);
		HSSFCell headers16 = row.createCell((short) columnIndex++);
		HSSFCell headers17 = row.createCell((short) columnIndex++);
		HSSFCell headers18 = row.createCell((short) columnIndex++);
		HSSFCell headers19 = row.createCell((short) columnIndex++);
		HSSFCell headers20 = row.createCell((short) columnIndex++);
		HSSFCell headers21 =row.createCell((short) columnIndex++);
		
		headers1.setCellValue("SPS ID");
		headers2.setCellValue("DESCRIPTION");
		headers3.setCellValue("PVA");
		headers4.setCellValue("DATA TYPE");
		headers5.setCellValue("EB01");
		headers6.setCellValue("EB02");
		headers7.setCellValue("EB06");
		headers8.setCellValue("EB09");
		headers9.setCellValue("DATE CREATED");
		headers10.setCellValue("STATUS");
		headers12.setCellValue("HSD01");
		headers13.setCellValue("HSD02");
		headers14.setCellValue("HSD03");
		headers15.setCellValue("HSD04");
		headers16.setCellValue("HSD05");
		headers17.setCellValue("HSD06");
		headers18.setCellValue("HSD07");
		headers19.setCellValue("HSD08");
		headers20.setCellValue("ACCUMULATOR SPS ID");
		headers21.setCellValue("FINALIZED");

		headers1.setCellStyle(cs);
		headers2.setCellStyle(cs);
		headers3.setCellStyle(cs);
		headers4.setCellStyle(cs);
		headers5.setCellStyle(cs);
		headers6.setCellStyle(cs);
		headers7.setCellStyle(cs);
		headers8.setCellStyle(cs);
		headers9.setCellStyle(cs);
		headers10.setCellStyle(cs);
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
		if (null != searchCriteria) {
			if (searchCriteria.isUnMapped() || (!searchCriteria.isUnMapped()
					&& !searchCriteria.isMapped() && !searchCriteria
					.isNotApplicable()&& !searchCriteria.isNotFinalized())) {
				HSSFCell headers22 = row.createCell((short) columnIndex++);
				HSSFCell headers23 = row.createCell((short) columnIndex++);
				HSSFCell headers24 = row.createCell((short) columnIndex++);
				headers22.setCellValue("Business Entity");
				headers23.setCellValue("Business Group ");
				headers24.setCellValue("MBU");
				headers22.setCellStyle(cs);
				headers23.setCellStyle(cs);
				headers24.setCellStyle(cs);
			}
		}

	}

	private static void populateSpsIdValues(List searchingList,
			HSSFWorkbook workbook, HSSFSheet sheet,
			SearchCriteria searchCriteria) {
		HSSFCellStyle cs = workbook.createCellStyle();

		int size = 256 * 25;
		sheet.setColumnWidth((short) 1, (short) size);

		int rowCount = 0;
		SearchResult searchResult;
		for (int i = 0; i < searchingList.size(); i++) {
			searchResult = (SearchResult) searchingList.get(i);
			rowCount++;
			int columnIndex = 0;
	        HSSFRow row = sheet.createRow((short) rowCount);
			HSSFCell cell0 = row.createCell((short) columnIndex++);
			HSSFCell cell1 = row.createCell((short) columnIndex++);
			HSSFCell cell2 = row.createCell((short) columnIndex++);
			HSSFCell cell3 = row.createCell((short) columnIndex++);
			HSSFCell cell4 = row.createCell((short) columnIndex++);
			HSSFCell cell5 = row.createCell((short) columnIndex++);
			HSSFCell cell6 = row.createCell((short) columnIndex++);
			HSSFCell cell7 = row.createCell((short) columnIndex++);
			HSSFCell cell8 = row.createCell((short) columnIndex++);
			HSSFCell cell9 = row.createCell((short) columnIndex++);
			if (null != searchCriteria) {
				if (searchCriteria.isMapped()
						|| searchCriteria.isNotApplicable()
						|| (!searchCriteria.isUnMapped()
								&& !searchCriteria.isMapped() && !searchCriteria
								.isNotApplicable())) {
			HSSFCell cell10 = row.createCell((short) columnIndex++);
			cell10.setCellValue(searchResult.getLastUpdatedUser());
				}
			}
			HSSFCell cell11 = row.createCell((short) columnIndex++);
			HSSFCell cell12 = row.createCell((short) columnIndex++);
			HSSFCell cell13 = row.createCell((short) columnIndex++);
			HSSFCell cell14 = row.createCell((short) columnIndex++);
			HSSFCell cell15 = row.createCell((short) columnIndex++);
			HSSFCell cell16 = row.createCell((short) columnIndex++);
			HSSFCell cell17 = row.createCell((short) columnIndex++);
			HSSFCell cell18 = row.createCell((short) columnIndex++);
			HSSFCell cell19 = row.createCell((short) columnIndex++);
			HSSFCell cell20 = row.createCell((short) columnIndex++);

			cell0.setCellValue(searchResult.getSpsId());
			cell1.setCellValue(searchResult.getSpsRuleDescription());
			cell2.setCellValue(searchResult.getPva());
			cell3.setCellValue(searchResult.getDataType());
			cell4.setCellValue(searchResult.getEB01());
			cell5.setCellValue(searchResult.getEB02());
			cell6.setCellValue(searchResult.getEB06());
			cell7.setCellValue(searchResult.getEB09());
			cell8.setCellValue(searchResult.getFormattedDate());
			cell9.setCellValue(searchResult.getStatus());
			
			cell11.setCellValue(searchResult.getHsd01());
			cell12.setCellValue(searchResult.getHsd02());
			cell13.setCellValue(searchResult.getHsd03());
			cell14.setCellValue(searchResult.getHsd04());
			cell15.setCellValue(searchResult.getHsd05());
			cell16.setCellValue(searchResult.getHsd06());
			cell17.setCellValue(searchResult.getHsd07());
			cell18.setCellValue(searchResult.getHsd08());
			cell19.setCellValue(searchResult.getAccumulator());
			cell20.setCellValue(searchResult.getFinalizedFlag());
			if (null != searchCriteria) {
				if (searchCriteria.isUnMapped() || (!searchCriteria.isUnMapped()
						&& !searchCriteria.isMapped() && !searchCriteria
						.isNotApplicable() && !searchCriteria.isNotFinalized())) {
					HSSFCell cell21 =row.createCell((short) columnIndex++);
					HSSFCell cell22 = row.createCell((short) columnIndex++);
					HSSFCell cell23 = row.createCell((short) columnIndex++);
					cell21.setCellValue(searchResult.getBusinessEntity());
					cell22.setCellValue(searchResult.getBusinessGroup());
					cell23.setCellValue(searchResult.getContractMbu());
				}
			}
			cs.setWrapText(true);
			cell1.setCellStyle(cs);
		}
	}
}
