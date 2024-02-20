/*
 * IndicativeLayoutServlet.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.indicativelayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.wellpoint.wpd.business.indicativemapping.builder.IndicativeMappingBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.ExcelFileConstants;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeDetailBO;

/**
 * 
 * @author U42506
 * 
 */
public class IndicativeLayoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_FILE_NAME = "eWPD Indicative Mapping";
	private static final String DEFAULT_SHEET_NAME = "Export Worksheet";
	private static final String REQ_PARAM_INDICATIVE_SEGMENT = "indicativeSegmentHidden";
	private static final String REQ_PARAM_REGION = "applicationEnviromentHidden";

	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("application/vnd.ms-excel");
			// Prevent Caching of this page.
			// response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store,no-cache");
			// Set the Header Info to tell the browser the format of the file.
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ getFileName(req) + "\"");
			createIndicativeLayout(req, response);
		} catch (SevereException ex) {
			Logger.logException(new SevereException(
					"Unknown Error on Service call", new ArrayList(), ex
							.getCause()));
		} catch (IOException ex) {
			Logger.logError("Io exception" + ex.getMessage());
		} catch (WriteException ex) {
			Logger.logError("Unable to write: " + ex.getMessage());
		}
	}

	private void createIndicativeLayout(HttpServletRequest req,
			HttpServletResponse response) throws SevereException, IOException,
			WriteException {

		List<IndicativeDetailBO> indicativeLayoutData = getReportData(req);
		ServletOutputStream out = response.getOutputStream();
		WritableWorkbook workbook = Workbook.createWorkbook(out);

		if (indicativeLayoutData == null || indicativeLayoutData.size() == 0) {
			Logger.logInfo("NO DATA RECEIVED FROM BUILDER");
			WritableSheet wsheet = workbook.createSheet(DEFAULT_SHEET_NAME, 1);
			createHeaders(wsheet, new String[] { "****** No Data ******" });
		} else {
			int currentIndex = 0;
			int dataSize = indicativeLayoutData.size();
			int excelRowIndex = 1;
			WritableSheet wsheet = null;
			WritableFont font = new WritableFont(WritableFont.ARIAL, 9,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat fontFormat = new WritableCellFormat(font);
			wsheet = workbook.createSheet(DEFAULT_SHEET_NAME, 1);
			createHeaders(wsheet, new String[] { ExcelFileConstants.REGION,
					ExcelFileConstants.SEGMENT, ExcelFileConstants.SEQUENCE,
					ExcelFileConstants.INDICATIVE_CODE,
					ExcelFileConstants.INDICATIVE_DESC,
					ExcelFileConstants.IND_VALUE,
					ExcelFileConstants.DEFAULT_IND,
					ExcelFileConstants.INVERSION_IND,
					ExcelFileConstants.LENGTH, ExcelFileConstants.COMMENT });
			while (currentIndex < dataSize) {
				IndicativeDetailBO indicativeMappingBO = (IndicativeDetailBO) indicativeLayoutData
						.get(currentIndex);
				Logger.logDebug("INDICATIVE MAPPING Segment: "
						+ indicativeMappingBO.getIndicativeSegment());
				Logger.logDebug("INDICATIVE MAPPING Sequence: "
						+ indicativeMappingBO.getIndicativeSeq());

				addExcelEntry(wsheet, 0, excelRowIndex,
						indicativeMappingBO.getIndicativeRegion(),
						fontFormat);
				addExcelEntry(wsheet, 1, excelRowIndex,
						indicativeMappingBO.getIndicativeSegment(),
						fontFormat);

				addExcelEntry(wsheet, 2, excelRowIndex, ""
						+ indicativeMappingBO.getIndicativeSeq(),
						fontFormat);

				addExcelEntry(wsheet, 3, excelRowIndex,
						indicativeMappingBO.getIndicativeCode(),
						fontFormat);
				addExcelEntry(wsheet, 4, excelRowIndex,
						indicativeMappingBO.getIndicativeCodeDescText(), fontFormat);
				addExcelEntry(wsheet, 5, excelRowIndex,
						indicativeMappingBO.getIndValue(), fontFormat);
				addExcelEntry(wsheet, 6, excelRowIndex,
						indicativeMappingBO.getDfltIndicativeIndicator(),
						fontFormat);
				addExcelEntry(wsheet, 7, excelRowIndex,
						indicativeMappingBO.getLogicIndicator(), fontFormat);
				addExcelEntry(wsheet, 8, excelRowIndex, ""
						+ indicativeMappingBO.getFieldLength(),
						fontFormat);
				addExcelEntry(wsheet, 9, excelRowIndex,
						indicativeMappingBO.getIndComments(), fontFormat);

				excelRowIndex++;
				currentIndex++;
			}
		}
		workbook.write();
		out.flush();
		workbook.close();
	}

	private List<IndicativeDetailBO> getReportData(HttpServletRequest req)
			throws SevereException {
		IndicativeDetailBO indicativeMappingBo = new IndicativeDetailBO();
		String indicativeSegment = req
				.getParameter(REQ_PARAM_INDICATIVE_SEGMENT);
		Logger.logInfo("INDICATIVE SEGMENT FROM REQ: " + indicativeSegment);
		indicativeMappingBo.setIndicativeSegment(indicativeSegment);
		String applicationEnviroment = req.getParameter(REQ_PARAM_REGION);
		Logger.logInfo("REGION FROM REQ: " + applicationEnviroment);
		applicationEnviroment =applicationEnviroment.toUpperCase();
		indicativeMappingBo.setIndicativeRegion(applicationEnviroment);
		List<IndicativeDetailBO> result = new IndicativeMappingBusinessObjectBuilder()
				.exportIndicativeDetail(indicativeMappingBo);
		Logger.logInfo("BUILDER OUTPUT: " + result);
		return result;
	}

	private void addExcelEntry(WritableSheet wsheet, int col, int row,
			String data, WritableCellFormat fontFormat) throws WriteException {
		if (data != null) {
			Label label = new Label(col, row, data, fontFormat);
			wsheet.addCell(label);
		}
	}

	private void createHeaders(WritableSheet sheet, String[] headers)
			throws WriteException {
		WritableFont columnFont = new WritableFont(WritableFont.ARIAL, 9,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		WritableCellFormat columnHeadingFmt = new WritableCellFormat(columnFont);
		columnHeadingFmt.setBackground(jxl.format.Colour.GRAY_25);

		for (int i = 0; i < headers.length; i++) {
			Label label = new Label(i, 0, headers[i], columnHeadingFmt);
			sheet.addCell(label);
		}
	}

	private String getFileName(HttpServletRequest req) {
		String fileName = DEFAULT_FILE_NAME + ".xls";
		return fileName;
	}
}
