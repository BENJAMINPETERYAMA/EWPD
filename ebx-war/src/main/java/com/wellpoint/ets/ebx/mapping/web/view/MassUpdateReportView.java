package com.wellpoint.ets.ebx.mapping.web.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.CreateOrEditMappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MappingResult;
import com.wellpoint.ets.bx.mapping.domain.vo.MassUpdateCriteria;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MassUpdateTracker;

/**
 * @author UST-GLOBAL
 * This is Model and View for Mass update result xl.
 * 
 */
public class MassUpdateReportView extends AbstractExcelView{

	private MassUpdateTracker massUpdateTracker;
	
	public MassUpdateReportView(MassUpdateTracker mut) {
		super();
		this.massUpdateTracker = mut;
	}
	protected void buildExcelDocument(Map arg0, HSSFWorkbook workBook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = "MassUpdateResult-"+sdf.format(new Date())+".xls";
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		HSSFSheet completed = workBook.createSheet("Completed");
		setReportProperties(completed);
		HSSFSheet inCompleted = workBook.createSheet("Failed");
		setReportProperties(inCompleted);
		createHeading(completed, inCompleted, workBook);
		populateValues(completed, inCompleted, workBook);
	}
	
	/**
	 * sets properties
	 * @param sheet
	 */
	private void setReportProperties(HSSFSheet sheet) {
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth((short) 11);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
    }
	
	/**
	 * this method prints the values to the xl sheets.
	 * @param completed
	 * @param inCompleted
	 * @param workbook
	 */
	private void populateValues(HSSFSheet completed,HSSFSheet inCompleted,  HSSFWorkbook workbook){
		
		HSSFCellStyle cs = getCellStyle(workbook);
		short rowNumCompeted  = 1;
		short rowNumInCompeted  = 1;
		Iterator itr = massUpdateTracker.getLastMassUpdate().iterator();
		
		while(itr.hasNext()){
			MassUpdateCriteria muc = (MassUpdateCriteria) itr.next();
			Object result = massUpdateTracker.getLastMassupdateResult().get(massUpdateTracker.findKey(muc));
			Mapping mapping = null;
			List errorList = new ArrayList();
			List warningList = new ArrayList();
			List statusList = new ArrayList();
			boolean status = true;
			if(null != result){
				if(result instanceof MappingResult){
					mapping = ((MappingResult)result).getMapping();
					errorList = ((MappingResult)result).getErrorMsgsList();
					warningList = ((MappingResult)result).getWarningMsgsList();
					status = ((MappingResult)result).isStatus();
				}else if(result instanceof CreateOrEditMappingResult){
					mapping = ((CreateOrEditMappingResult)result).getMapping();
					errorList = ((CreateOrEditMappingResult)result).getErrorMsgsList();
					warningList = ((CreateOrEditMappingResult)result).getWarningMsgsList();
					status = ((CreateOrEditMappingResult)result).isValidationSucess();
					statusList = ((CreateOrEditMappingResult)result).getStatusCodes();
				}
			}
			short i = 0;
			short j = 0;
			if(null == result){
				j = printId(inCompleted, cs, rowNumInCompeted++, j, muc);
				continue;
			}
			
			
			if(null == statusList){
				statusList = new ArrayList();
			}
			
			
			if(errorList.size()>0 || !status){
				j = printId(inCompleted, cs, rowNumInCompeted, j, muc);
				createCell(inCompleted, rowNumInCompeted, (short)(j), cs, mapping.getVariableMappingStatus());
				createCell(inCompleted, rowNumInCompeted, (short)(j+1), cs, mapping.getUser().getLastUpdatedUserName());
				createCell(inCompleted, rowNumInCompeted, (short)(j+2), cs, getError(errorList)+getError(statusList));
				rowNumInCompeted ++;
				
			}else{
				i = printId(completed, cs, rowNumCompeted, i, muc);
				rowNumCompeted = printCompletedValues(completed, cs, rowNumCompeted, i, muc, warningList);
			}			
		}
	}
	
	/**
	 * gets error string from error list
	 * @param list
	 * @return
	 */
	private String getError(List list){
		StringBuffer sb = new StringBuffer();
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Object obj = itr.next();
			if(obj instanceof String){
				sb.append((String) obj);
			}else if(obj instanceof List){
				sb.append(getError((List)obj));
			}
			if(itr.hasNext()){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	/**
	 * creates blank cell
	 * @param sheet
	 * @param cs
	 * @param startingRow
	 * @param currentRow
	 * @param colnum
	 */
	private void createBlankCell(HSSFSheet sheet, HSSFCellStyle cs, short startingRow, short currentRow, short colnum){
		if(currentRow > startingRow){
			createCell(sheet, currentRow, (short)(colnum-1), cs, "");
			createCell(sheet, currentRow, (short)(colnum+2), cs, "");
		}
	}
	
	/**
	 * prints mass update successfull values.
	 * @param sheet
	 * @param cs
	 * @param rownum
	 * @param colnum
	 * @param muc
	 * @param warningList
	 * @return
	 */
	private short printCompletedValues(HSSFSheet sheet, HSSFCellStyle cs,
			short rownum, short colnum, MassUpdateCriteria muc, List warningList) {
		createCell(sheet, rownum, (short) (colnum + 2), cs,
				getError(warningList));
		short startingRow = rownum;
		if ((null == muc.getUpdatedEb01()) && (null == muc.getUpdatedEb02())
				&& (null == muc.getUpdatedEb03())
				&& (null == muc.getUpdatedEb06())
				&& (null == muc.getUpdatedEb09())
				&& (null == muc.getUpdatedHsd01())
				&& (null == muc.getUpdatedHsd02())
				&& (null == muc.getUpdatedHsd03())
				&& (null == muc.getUpdatedHsd04())
				&& (null == muc.getUpdatedHsd05())
				&& (null == muc.getUpdatedHsd06())
				&& (null == muc.getUpdatedHsd07())
				&& (null == muc.getUpdatedHsd08())
				&& (null == muc.getUpdatedIII02())
				&& (null == muc.getUpdatedNoteType())
				&& (null == muc.getUpdatedMessage())
				&& (!BxUtil.hasText(muc.getUpdatedStatus()))) {
			rownum++;
		}
		if (null != muc.getUpdatedEb01()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.EB01_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedEb01().trim());
			rownum++;
		}
		if (null != muc.getUpdatedEb02()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.EB02_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedEb02().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedEb03()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.EB03_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedEb03().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedEb06()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.EB06_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedEb06().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedEb09()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.EB09_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedEb09().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (null != muc.getUpdatedHsd01()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD01_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd01().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (null != muc.getUpdatedHsd02()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD02_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd02().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (null != muc.getUpdatedHsd03()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD03_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd03().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (null != muc.getUpdatedHsd04()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD04_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd04().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedHsd05()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD05_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd05().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedHsd06()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD06_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd06().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedHsd07()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD07_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd07().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedHsd08()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.HSD08_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedHsd08().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (null != muc.getUpdatedIII02()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.III02_NAME);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedIII02().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (null != muc.getUpdatedNoteType()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					DomainConstants.NOTETYPECODE);
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedNoteType().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}
		if (null != muc.getUpdatedMessage()) {
			createCell(sheet, rownum, (short) (colnum), cs,
					"Message Text");
			createCell(sheet, rownum, (short) (colnum + 1), cs, muc
					.getUpdatedMessage().toUpperCase().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		if (BxUtil.hasText(muc.getUpdatedStatus())) {
			createCell(sheet, rownum, (short) (colnum), cs, "STATUS");
			createCell(
					sheet,
					rownum,
					(short) (colnum + 1),
					cs,
					muc.getUpdatedStatus().trim());
			createBlankCell(sheet, cs, startingRow, rownum, colnum);
			rownum++;
		}

		return rownum;
	}
	
	/***
	 * prints id's ie sps id , header rule id , variable id
	 * @param sheet
	 * @param cs
	 * @param rownum
	 * @param colnum
	 * @param muc
	 * @return
	 */
	private short printId(HSSFSheet sheet, HSSFCellStyle cs, short rownum, short colnum, MassUpdateCriteria muc){
		if(BxUtil.hasText(muc.getVariableId())){
			 createCell(sheet, rownum, colnum++, cs, muc.getVariableId().trim());
		}
		if(BxUtil.hasText(muc.getSpsId())){
			 createCell(sheet, rownum, colnum++, cs, muc.getSpsId().trim());
		}
		if(BxUtil.hasText(muc.getRuleId())){
			 createCell(sheet, rownum, colnum++, cs, muc.getRuleId().trim());
		}
		createCell(sheet, rownum, colnum, cs, "");
		createCell(sheet, rownum, (short)(colnum+1), cs, "");
		createCell(sheet, rownum, (short)(colnum+2), cs, "");
		return colnum;
	}
	
	/**
	 * creates heading for the xl sheet
	 * @param completed
	 * @param inCompleted
	 * @param workbook
	 */
	private void createHeading(HSSFSheet completed,HSSFSheet inCompleted,  HSSFWorkbook workbook){
		HSSFCellStyle cs = getHeaderStyle(workbook);
		if(null == massUpdateTracker.getLastMassUpdate() || massUpdateTracker.getLastMassUpdate().size() == 0){
			return;
		}
		MassUpdateCriteria muc = (MassUpdateCriteria)massUpdateTracker.getLastMassUpdate().get(0);
		short i = 0;
		short j = 0;
		if(BxUtil.hasText(muc.getVariableId())){
			 createCell(completed, (short)0, i++, cs, "Variable ID");
			 createCell(inCompleted, (short)0, j++, cs, "Variable ID");
			 
		}
		if(BxUtil.hasText(muc.getSpsId())){
			 createCell(completed, (short)0, i++, cs, "SPS ID");
			 createCell(inCompleted, (short)0, j++, cs, "SPS ID");
		}
		if(BxUtil.hasText(muc.getRuleId())){
			 createCell(completed, (short)0, i++, cs, "Header Rule ID");
			 createCell(inCompleted, (short)0, j++, cs, "Header Rule ID");
		}
		createCell(completed, (short)0, i++, cs, "Field");
		createCell(completed, (short)0, i++, cs, "Value");
		createCell(completed, (short)0, i++, cs, "Warning\\Information Details");
		
		int size = 256 * 30;
		completed.setColumnWidth((short) 3, (short) size);
		
		createCell(inCompleted, (short)0, j++, cs, "Status");
		createCell(inCompleted, (short)0, j++, cs, "User"); 
		createCell(inCompleted, (short)0, j++, cs, "Error Details");
		        
	}
	
	/**
	 * returns header style
	 * @param workbook
	 * @return
	 */
	private HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook){
		HSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cs.setFont(font);
        return cs;
	}
	
	/**
	 * returns normal cell style
	 * @param workbook
	 * @return
	 */
	private HSSFCellStyle getCellStyle(HSSFWorkbook workbook){
		HSSFCellStyle cs = workbook.createCellStyle();
        //cs.setFillForegroundColor(HSSFColor.WHITE.index);
        //cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cs.setFont(font);
        return cs;
	}
	
	/**
	 * creates normal cell.
	 * @param sheet
	 * @param rowNum
	 * @param cellNum
	 * @param cs
	 * @param value
	 */
	private void createCell(HSSFSheet sheet, short rowNum, short cellNum,HSSFCellStyle cs, String value){
		HSSFCell cell = sheet.createRow(rowNum).createCell(cellNum);
		cell.setCellValue(value);
		cell.setCellStyle(cs);
	}

}