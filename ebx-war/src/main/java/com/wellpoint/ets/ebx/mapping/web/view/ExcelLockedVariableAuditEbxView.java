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
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;

/**
 * @author UST-GLOBAL This is a class for generating Contract BX, Excel Report
 */
public class ExcelLockedVariableAuditEbxView extends AbstractJExcelView {

	List lockAuditList = null;

	/**
	 * Creating constructor
	 * 
	 * @param contractList
	 */
	public ExcelLockedVariableAuditEbxView(List lockAuditList) {
		this.lockAuditList = lockAuditList;
	}

	/**
	 * Method to set the header and values for Contract BX report based on the
	 * System.
	 */
	public void buildExcelDocument(Map model, WritableWorkbook workBook,
			HttpServletRequest request, HttpServletResponse response) {
		WritableSheet sheet = null;
		try {
			if (lockAuditList == null || lockAuditList.size() == 0) {				
				sheet = workBook.createSheet(DomainConstants.EBX, 1);
				sheet.setColumnView(0, 30);
				createHeaders(sheet, new String[] { "****** No Data ******" },0);
			} else {
				sheet = workBook.createSheet(DomainConstants.EBX, 1);
				sheet.setColumnView(0, 30);
				sheet.setColumnView(1, 50);
				sheet.setColumnView(2, 20);
				sheet.setColumnView(3, 25);
				createHeaders(sheet,
						new String[] { "Enterprise Blue Exchange" },0);
				createHeaders(sheet, new String[] { "Variable",
						"User Comments", "User Id", "Date and Time" },1);
				populateEbxAuditTrailValues(lockAuditList, workBook, sheet);
			}
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// columnHeadingFmt.setWrap(true);

		for (int i = 0; i < headers.length; i++) {
			Label label = new Label(i, row, headers[i], columnHeadingFmt);
			sheet.addCell(label);
		}
	}

	private void populateEbxAuditTrailValues(List lockAuditList,
			WritableWorkbook workbook, WritableSheet sheet) {
		try {
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
						.getUserComments(), fontFormat);

				addExcelEntry(sheet, 2, excelRowIndex, lockAuditTrail
						.getCreatedUser(), fontFormat);
				addExcelEntry(sheet, 3, excelRowIndex, lockAuditTrail
						.getCreatedAuditDate(), fontFormat);

				excelRowIndex++;
				currentIndex++;

			}
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
