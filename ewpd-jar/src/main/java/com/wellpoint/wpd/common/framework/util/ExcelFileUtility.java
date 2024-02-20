/*
 * ExcelFileUtility.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeDetailVO;

public class ExcelFileUtility {
	
	public static Map<Integer, List<? extends Object>> processExcelToBeImported(UploadedFile upload, String segmentNumber, String region){
		Map<Integer, List<? extends Object>> processedResult = new HashMap<Integer, List<? extends Object>>();
		List<ErrorMessage> validatedErrorList = new ArrayList<ErrorMessage>();
		ErrorMessage errorMessage = null;
		InputStream inputStream = null;
		Workbook workbook = null;
		try{
			//System.out.println("File Name in Excel utility:"+ upload.getName());
			//System.out.println("Input STream in Excel utility:"+ upload.getInputStream());
			Logger.logInfo("File Name in Excel utility:"+ upload.getName());
			Logger.logInfo("Input STream in Excel utility:"+ upload.getInputStream());
			inputStream = upload.getInputStream();
			workbook = Workbook.getWorkbook(inputStream);
			final List<IndicativeDetailVO > indicativeDetailsVOList = new ArrayList<IndicativeDetailVO>();
			List<IndicativeDetailVO> duplicateIndicativeDetailsVoList = new ArrayList<IndicativeDetailVO>();
			final Sheet sheet = workbook.getSheet(0);
			final int lastRow = sheet.getRows() - 1;
			int rowNum = 1;
			if (lastRow > 0) {
				Map<String, IndicativeDetailVO> segCodes = new HashMap<String, IndicativeDetailVO>();
				final Map<String, Integer> columnHeaderMap = getColumnHeaders(sheet);
				while (rowNum <= lastRow) {
					final IndicativeDetailVO indicativeDetailVO = readIndicativeMappingAttributes(sheet, rowNum,  columnHeaderMap);
					if (null != indicativeDetailVO) {
						rowNum++;
						if (indicativeDetailVO.getIndicativeCode() == null || indicativeDetailVO.getIndicativeCode().equalsIgnoreCase("")) {
							errorMessage = new ErrorMessage(BusinessConstants.MANDATORY_FIELD_MISSING);
							validatedErrorList.add(errorMessage);	
							break;
						}
						if (segCodes.get(indicativeDetailVO.getIndicativeCode()) == null) {
								indicativeDetailVO.setIndicativeRegion(region);
								indicativeDetailVO.setIndicativeSegment(segmentNumber);
								indicativeDetailsVOList.add(indicativeDetailVO);
								segCodes.put(indicativeDetailVO.getIndicativeCode(), indicativeDetailVO);
						} else {
							duplicateIndicativeDetailsVoList.add(indicativeDetailVO);
						}
					}
				}
				if (duplicateIndicativeDetailsVoList.size() > 0) {
					errorMessage = new ErrorMessage(BusinessConstants.DUPLICATE_SEG_CODES_ERROR);
					StringBuffer duplicateSegmentCode = new StringBuffer();
					for (IndicativeDetailVO indicativeDetailVO : duplicateIndicativeDetailsVoList) {
						if(duplicateSegmentCode.length()>0){
							duplicateSegmentCode.append(", ");
						}
						duplicateSegmentCode.append(indicativeDetailVO.getIndicativeCode());
					}						
					errorMessage.setParameters(new String[] { duplicateSegmentCode.toString() });
					validatedErrorList.add(errorMessage);						
				}
				if (indicativeDetailsVOList.size() > 0) {
					processedResult.put(BusinessConstants.CORRECT_INDICATIVE_CODES,indicativeDetailsVOList);
				}					
			}else{
				errorMessage = new ErrorMessage(BusinessConstants.FILE_IS_EMPTY);
				errorMessage.setParameters(new String[] { upload.getName() });
				validatedErrorList.add(errorMessage);
			}
		}catch (FileNotFoundException fileNotFoundException) {
			errorMessage = new ErrorMessage(BusinessConstants.FILE_NOT_EXIST_ERROR);
			validatedErrorList.add(errorMessage);	
			processedResult.put(BusinessConstants.VALIDATIONS_WHILE_PROCESSING_IMPORT_EXCEL,validatedErrorList);
		}catch (Exception exception) {
			errorMessage = new ErrorMessage(BusinessConstants.UPLOAD_ERROR);
			validatedErrorList.add(errorMessage);
		}finally {
			try {
				if(null != workbook){
					workbook.close();
				}
				if(null != inputStream){
					inputStream.close();
				}
				processedResult.put(BusinessConstants.VALIDATIONS_WHILE_PROCESSING_IMPORT_EXCEL,validatedErrorList);					
			} catch (Exception e) {
				Logger.logError(e);
				errorMessage = new ErrorMessage(BusinessConstants.UPLOAD_ERROR);
				validatedErrorList.add(errorMessage);
			}
		}
		return processedResult;
	}

	private static IndicativeDetailVO readIndicativeMappingAttributes(
			final Sheet sheet, final int rowNum,
			final Map<String, Integer> columnHeaderMap) {

		final IndicativeDetailVO indicativeDetailVO = new IndicativeDetailVO();

		if (!columnHeaderMap.isEmpty()) {

			if (null != columnHeaderMap.get(ExcelFileConstants.REGION)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.REGION));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndicativeRegion(cellValue
							.toUpperCase().trim());
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.SEGMENT)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.SEGMENT));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndicativeSegment(cellValue
							.trim());
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.SEQUENCE)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.SEQUENCE));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndicativeSeq(rowNum);
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.INDICATIVE_CODE)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.INDICATIVE_CODE));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndicativeCode(cellValue);
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.INDICATIVE_DESC)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.INDICATIVE_DESC));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndicativeCodeDescText(cellValue);
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.IND_VALUE)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.IND_VALUE));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndValue(cellValue.trim());
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.DEFAULT_IND)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.DEFAULT_IND));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setDfltIndicativeIndicator(cellValue);
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.INVERSION_IND)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.INVERSION_IND));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setLogicIndicator(cellValue);
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.LENGTH)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.LENGTH));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setFieldLength(Integer
							.parseInt(cellValue.trim()));
				}
			}
			if (null != columnHeaderMap.get(ExcelFileConstants.COMMENT)) {
				final String cellValue = getCellValue(sheet, rowNum,
						columnHeaderMap.get(ExcelFileConstants.COMMENT));
				if ((null != cellValue) && !"".equals(cellValue)) {
					indicativeDetailVO.setIndComments(cellValue);
				}
			}

		}
		return indicativeDetailVO;
	}

	private static String getCellValue(final Sheet sheet, final int row, final int col) {
		final Cell cells[] = sheet.getRow(row);
		String value = "";
		if ((null != cells) && (cells.length > col) && (null != cells[col])
				&& (null != cells[col].getContents().trim())) {
			value = cells[col].getContents().trim();
		}
		return value;
	}

	private static Map<String, Integer> getColumnHeaders(final Sheet sheet) {
		final Cell cells[] = sheet.getRow(0);

		final Map<String, Integer> columnHeaderMap = new HashMap<String, Integer>();

		final int lastColumn = sheet.getColumns();
		for (int columnCount = 0; columnCount < lastColumn; columnCount++) {
			if (cells.length > columnCount) {
				final String cellValue = cells[columnCount].getContents()
						.trim();

				if (cellValue.equalsIgnoreCase(ExcelFileConstants.REGION)) {
					columnHeaderMap.put(ExcelFileConstants.REGION, columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.SEGMENT)) {
					columnHeaderMap
							.put(ExcelFileConstants.SEGMENT, columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.SEQUENCE)) {
					columnHeaderMap.put(ExcelFileConstants.SEQUENCE,
							columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.INDICATIVE_CODE)) {
					columnHeaderMap.put(ExcelFileConstants.INDICATIVE_CODE,
							columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.INDICATIVE_DESC)) {
					columnHeaderMap.put(ExcelFileConstants.INDICATIVE_DESC,
							columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.IND_VALUE)) {
					columnHeaderMap.put(ExcelFileConstants.IND_VALUE,
							columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.DEFAULT_IND)) {
					columnHeaderMap.put(ExcelFileConstants.DEFAULT_IND,
							columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.INVERSION_IND)) {
					columnHeaderMap.put(ExcelFileConstants.INVERSION_IND,
							columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.LENGTH)) {
					columnHeaderMap.put(ExcelFileConstants.LENGTH, columnCount);
				} else if (cellValue
						.equalsIgnoreCase(ExcelFileConstants.COMMENT)) {
					columnHeaderMap
							.put(ExcelFileConstants.COMMENT, columnCount);
				}
			}
		}
		return columnHeaderMap;
	}
}
