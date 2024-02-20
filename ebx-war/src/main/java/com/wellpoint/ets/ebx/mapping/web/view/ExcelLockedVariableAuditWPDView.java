/*
 * <ExcelReportView.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.mapping.web.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.wellpoint.ets.bx.mapping.domain.entity.LockAuditTrail;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;

/**
 * @author UST-GLOBAL This is a class for generating Contract BX, Excel Report
 */
public class ExcelLockedVariableAuditWPDView extends AbstractJExcelView {

	List lockAuditList = null;
	String systemName =null;

	/**
	 * Creating constructor
	 * 
	 * @param contractList
	 */
	public ExcelLockedVariableAuditWPDView(List lockAuditList) {
		this.lockAuditList = lockAuditList;
	}
	
	public ExcelLockedVariableAuditWPDView(List lockAuditList,String systemName) {
		this.lockAuditList = lockAuditList;
		this.systemName=systemName;
	}

	/**
	 * Method to set the header and values for Contract BX report based on the
	 * System.
	 */
	public void buildExcelDocument(Map model, WritableWorkbook workBook,
			HttpServletRequest request, HttpServletResponse response) throws EBXException {
		WritableSheet sheet = null;
		try {
			if (lockAuditList == null || lockAuditList.size() == 0) {
				sheet = workBook.createSheet(systemName, 1);
				sheet.setColumnView(0, 30);
				createHeaders(sheet, new String[] { "****** No Data ******" },0);
			} else {
				sheet = workBook.createSheet(((LockAuditTrail) lockAuditList
						.get(0)).getSystem(), 1);
				sheet.setColumnView(0, 30);
				sheet.setColumnView(1, 15);
				sheet.setColumnView(2, 15);
				sheet.setColumnView(3, 20);
				sheet.setColumnView(4, 20);
				sheet.setColumnView(5, 15);
				sheet.setColumnView(6, 25);
				sheet.setColumnView(7, 15);
				
				createHeaders(sheet,
						new String[] { "WellPoint Product Database – "
								+ ((LockAuditTrail) lockAuditList.get(0))
										.getSystem() },0);
				createHeaders(sheet, new String[] { "Variable", "Contract ID",
						"Date Segment", "Current Value", "New Value",
						"User Id", "Date and Time", "System Indicator" },1);
				populateWPDAuditTrailValues(lockAuditList, workBook, sheet);
			}
		} catch (WriteException e) {
			EBXException businessException = new EBXException(e.getMessage(), e, 
					"Report generation failed.", "Report generation failed due to failure in generating the excel document.");
			throw businessException;
		}
	}

	/**
	 * Creating the headers for excel work sheet
	 * 
	 * @param sheet
	 *            WritableSheet object
	 * @param headers
	 *            String array with the name of headers.
	 * @throws WriteException
	 */

	protected void createHeaders(WritableSheet sheet, String[] headers, int row)
			throws WriteException {
		WritableFont columnFont = new WritableFont(WritableFont.ARIAL, 9,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		WritableCellFormat columnHeadingFmt = new WritableCellFormat(columnFont);
		columnHeadingFmt.setBackground(jxl.format.Colour.GRAY_25);
		columnHeadingFmt.setAlignment(jxl.format.Alignment.JUSTIFY);
		// setWrap(true);

		for (int i = 0; i < headers.length; i++) {
			Label label = new Label(i, row, headers[i], columnHeadingFmt);
			sheet.addCell(label);
		}
	}

	private void populateWPDAuditTrailValues(List lockAuditList,
			WritableWorkbook workbook, WritableSheet sheet) throws WriteException {
			WritableFont font = new WritableFont(WritableFont.ARIAL, 9,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat fontFormat = new WritableCellFormat(font);
			fontFormat.setWrap(true);

			int currentIndex = 0;
			int excelRowIndex = 2;
			int dataSize = lockAuditList.size();

			while (currentIndex < dataSize) {

				LockAuditTrail lockAuditTrail = (LockAuditTrail) lockAuditList
						.get(currentIndex);

				addExcelEntry(sheet, 0, excelRowIndex, lockAuditTrail
						.getVariableId(), fontFormat);
				addExcelEntry(sheet, 1, excelRowIndex, lockAuditTrail
						.getContractId(), fontFormat);
				addExcelEntry(sheet, 2, excelRowIndex, lockAuditTrail
						.getDateSegment(), fontFormat);
				addExcelEntry(sheet, 3, excelRowIndex, lockAuditTrail
						.getCurrentValue(), fontFormat);
				addExcelEntry(sheet, 4, excelRowIndex, lockAuditTrail
						.getNewValue(), fontFormat);
				addExcelEntry(sheet, 5, excelRowIndex, lockAuditTrail
						.getCreatedUser(), fontFormat);
				addExcelEntry(sheet, 6, excelRowIndex, lockAuditTrail
						.getCreatedAuditDate(), fontFormat);
				addExcelEntry(sheet, 7, excelRowIndex,
						lockAuditTrail.getSystemIndicator().equalsIgnoreCase(
								"P") ? "Production" : "Test", fontFormat);

				excelRowIndex++;
				currentIndex++;

			}
	}

	/**
	 * method creates one excel cell
	 * 
	 * @throws WriteException
	 */
	private void addExcelEntry(WritableSheet wsheet, int col, int row,
			String data, WritableCellFormat fontFormat) throws WriteException {
		if (null != data) {
			Label label = new Label(col, row, data, fontFormat);
			wsheet.addCell(label);
		}
	}
}
