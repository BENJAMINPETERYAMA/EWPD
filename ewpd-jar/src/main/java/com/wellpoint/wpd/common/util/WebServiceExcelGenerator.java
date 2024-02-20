/**
 * 
 */
package com.wellpoint.wpd.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractMappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.ErrorDetailWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.HippaCodeValueWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.HippaSegmentWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.MajorHeadingsWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.MappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.MinorHeadingsWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplPortProxy;
import com.wellpoint.ets.ebx.simulation.webservices.client.SpsIdWebServiceVO;
import com.wellpoint.wpd.common.framework.logging.Logger;

/**
 * @author UST Global
 * 
 */
public class WebServiceExcelGenerator {

	int rowCount = 5;
	int totalRowCount = 0;
	int countOfDateSegments = 0;

	public XSSFWorkbook buildExcelDocument(ContractWebServiceVO contract,
			String backEndRegion) throws Exception {
		int countOfDateSegments = 0;
		String effectiveDate = "";

		/*
		 * ResourceBundle bundle = ResourceBundle.getBundle("webservice", Locale
		 * .getDefault());
		 * System.out.println(bundle.getString("webservice.ebx.url")); List
		 * dateSegmentList = new ArrayList(); //
		 * dateSegmentList.add("09/01/2010");
		 * dateSegmentList.add(contract.getEffectiveDate()); //
		 * dateSegmentList.add("06/01/2010");
		 * 
		 * Iterator effDateSegmentIterator = dateSegmentList.iterator();
		 */

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet1 = wb.createSheet("Error Validations");
		setReportProperties(sheet1);
		createEWPDReportHeader(wb, sheet1);
		populateEWPDContractValues2(contract, sheet1, backEndRegion);
		//System.out.println("Excel Generated");
		Logger.logInfo("Excel Generated");

		/*
		 * while (effDateSegmentIterator.hasNext()) { effectiveDate = (String)
		 * effDateSegmentIterator.next();
		 * contract.setEffectiveDate(effectiveDate); List<ContractWebServiceVO>
		 * contractList = new SimulationWebServiceImplPortProxy()
		 * .getContractInfo(contract, "Test", false); if (null != contractList
		 * && !contractList.isEmpty() && null != contractList.get(0)) {
		 * populateEWPDContractValues2(contractList.get(0), sheet1,
		 * backEndRegion);
		 * 
		 * }
		 * 
		 * }
		 */
		return wb;
	}

	private static void setReportProperties(Sheet sheet) {
		sheet.setAutobreaks(true);
		sheet.setDefaultColumnWidth((short) 11);
		// sheet.autoSizeColumn((short)1); //adjust width of the first column
		// sheet.autoSizeColumn(1);
		sheet.getPrintSetup().setFitHeight((short) 1);
		sheet.getPrintSetup().setFitWidth((short) 1);
	}

	private void createEWPDReportHeader(Workbook workbook, Sheet sheet) {
		sheet.createRow((short) 1).createCell((short) 0).setCellValue(
				WebServiceConstants.CONTRACT_ID);
		sheet.getRow((short) 1).createCell((short) 2).setCellValue(
				WebServiceConstants.VER);
		sheet.createRow((short) 2).createCell((short) 0).setCellValue(
				WebServiceConstants.BUSINESS_ENTITY);
		sheet.createRow((short) 3).createCell((short) 0).setCellValue(
				WebServiceConstants.DATE_SEGMENT);
		sheet.createRow((short) 5).createCell((short) 0).setCellValue(
				WebServiceConstants.BENEFIT_COMPONENT);
		sheet.getRow((short) 5).createCell((short) 1).setCellValue(
				WebServiceConstants.BENEFIT);
		sheet.getRow((short) 5).createCell((short) 2).setCellValue(
				WebServiceConstants.TIER_INFO);
		sheet.getRow((short) 5).createCell((short) 3).setCellValue(
				WebServiceConstants.SPSID);
		sheet.getRow((short) 5).createCell((short) 4).setCellValue(
				WebServiceConstants.SPS_DESCRIPTION);
		sheet.getRow((short) 5).createCell((short) 5).setCellValue(
				WebServiceConstants.PVA);
		sheet.getRow((short) 5).createCell((short) 6).setCellValue(
				WebServiceConstants.FORMAT);
		sheet.getRow((short) 5).createCell((short) 7).setCellValue(
				WebServiceConstants.VALUE_CODED);
		sheet.getRow((short) 5).createCell((short) 8).setCellValue(
				WebServiceConstants.EB01_NAME);
		sheet.getRow((short) 5).createCell((short) 9).setCellValue(
				WebServiceConstants.EB02_NAME);
		sheet.getRow((short) 5).createCell((short) 10).setCellValue(
				WebServiceConstants.EB03_NAME);
		sheet.getRow((short) 5).createCell((short) 11).setCellValue(
				WebServiceConstants.EB06_NAME);
		sheet.getRow((short) 5).createCell((short) 12).setCellValue(
				WebServiceConstants.EB09_NAME);
		sheet.getRow((short) 5).createCell((short) 13).setCellValue(
				WebServiceConstants.ERROR);
		sheet.getRow((short) 5).createCell((short) 14).setCellValue(
				WebServiceConstants.ERROR_DESCRIPTION);
	}

	public static String getHipaaSegmentValue(
			HippaSegmentWebServiceVO hippaSegment) {
		String hipaaValue = "";

		if (null != hippaSegment
				&& null != hippaSegment.getHippaCodeSelectedValuesForLgIsg()
				&& !hippaSegment.getHippaCodeSelectedValuesForLgIsg().isEmpty()) {
			hipaaValue = (String) hippaSegment
					.getHippaCodeSelectedValuesForLgIsg().get(0);

		} else if (null != hippaSegment
				&& null != hippaSegment.getHippaCodeSelectedValuesForEwpd()
				&& !hippaSegment.getHippaCodeSelectedValuesForEwpd().isEmpty()) {
			hipaaValue = (String) hippaSegment
					.getHippaCodeSelectedValuesForEwpd().get(0).getValue();

		}
		return hipaaValue;
	}

	private void populateEWPDContractValues2(ContractWebServiceVO contract,
			Sheet sheet, String backEndRegion) {

		countOfDateSegments++;
		if (countOfDateSegments == 1) {
			sheet.getRow((short) 1).createCell((short) 1).setCellValue(
					contract.getContractId());
			sheet.getRow((short) 1).createCell((short) 1).setCellValue(
					contract.getContractId());
			sheet.getRow((short) 1).createCell((short) 3).setCellValue(
					contract.getVersion());
			sheet.getRow((short) 2).createCell((short) 1).setCellValue(
					contract.getBusinessEntity());
			sheet.getRow((short) 3).createCell((short) 1).setCellValue(
					contract.getEffectiveDate() + "-"
							+ contract.getExpiryDate());
		} else {
			rowCount = totalRowCount + 5;
			sheet.createRow((short) rowCount).createCell((short) 0)
					.setCellValue(WebServiceConstants.DATE_SEGMENT);
			sheet.getRow((short) rowCount).createCell((short) 1).setCellValue(
					contract.getEffectiveDate() + "-"
							+ contract.getExpiryDate());
			rowCount = rowCount + 2;
		}
		int eb03Count;
		String eb03;
		boolean mQValidationExists = false;
		boolean contractLevelMappingError = false;
		String mQValidationSuccessMessage = "";

		// contractMappingVOList contains contract level errors with mapping.
		if (null != contract.getContractMappingVOList()) {
			contractLevelMappingError = true;
		}
		// contractErrCodeList contains contract level errors
		// 1. Contract --> ErrorList
		List contractErrCodeList = contract.getErrorCodesList();
		if (contractErrCodeList != null && !contractErrCodeList.isEmpty()) {
			Iterator contractErrCodeListIterator = contractErrCodeList
					.iterator();
			if (contractErrCodeListIterator != null) {
				while (contractErrCodeListIterator.hasNext()) {
					ErrorDetailWebServiceVO errorContract = (ErrorDetailWebServiceVO) contractErrCodeListIterator
							.next();
					if (errorContract.getErrorCode().equals(
							WebServiceConstants.ERROR_MQ)) {
						mQValidationExists = true;
						mQValidationSuccessMessage = errorContract
								.getErrorDesc();
					} else {
						if (!"E019".equalsIgnoreCase(errorContract
								.getErrorCode())) {
							rowCount++;
							sheet.createRow((short) rowCount).createCell(
									(short) 13).setCellValue(
									errorContract.getErrorCode());
							sheet.getRow((short) rowCount).createCell(
									(short) 14).setCellValue(
									errorContract.getErrorDesc());
						} else if ("E019".equalsIgnoreCase(errorContract
								.getErrorCode())) {
							mQValidationExists = false;
						}
					}
				}
			}
		}
		// 2. Contract --> ContractMappingVOList -- > ErrorCodeList
		if (!mQValidationExists || contractLevelMappingError) {
			StringBuffer commonErrorMessage = new StringBuffer();
			List contractMappingErrCodeList = contract
					.getContractMappingVOList();
			if (contractMappingErrCodeList != null
					&& !contractMappingErrCodeList.isEmpty()) {
				Iterator contractMappingErrCodeListIterator = contractMappingErrCodeList
						.iterator();
				if (contractMappingErrCodeListIterator != null) {
					while (contractMappingErrCodeListIterator.hasNext()) {
						rowCount++;
						ContractMappingWebServiceVO contractMappingVO = (ContractMappingWebServiceVO) contractMappingErrCodeListIterator
								.next();
						if (null != contractMappingVO) {
							List errorList = contractMappingVO
									.getErrorCodesList();
							if (null != errorList && errorList.size() > 0) {
								// Get the first element of the error
								ErrorDetailWebServiceVO detailVO = (ErrorDetailWebServiceVO) errorList
										.get(0);
								if (null != detailVO) {
									String eb01Val = getHipaaSegmentValue(contractMappingVO
											.getEb01());
									String eb03Val = getHipaaSegmentValue(contractMappingVO
											.getEb03());
									String eb07Val = getHipaaSegmentValue(contractMappingVO
											.getEb07());
									String eb02Val = getHipaaSegmentValue(contractMappingVO
											.getEb02());
									String eb06Val = getHipaaSegmentValue(contractMappingVO
											.getEb06());
									String eb09Val = getHipaaSegmentValue(contractMappingVO
											.getEb09());
									String eb12Val = getHipaaSegmentValue(contractMappingVO
											.getEb12());
									if (WebServiceConstants.ERROR_E016
											.equals(detailVO.getErrorCode())) {
										sheet.createRow((short) rowCount)
												.createCell((short) 7)
												.setCellValue(eb07Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(WebServiceConstants.ERROR_E016);
										} else {
											if (commonErrorMessage
													.indexOf(WebServiceConstants.ERROR_E016) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																WebServiceConstants.ERROR_E016);
											}
										}
									} else if (WebServiceConstants.ERROR_E024
											.equals(detailVO.getErrorCode())) {
										if ("Y".equalsIgnoreCase(eb12Val)) {
											eb12Val = "DIFFVAL-PAR";
										} else {
											eb12Val = "DIFFVAL-NPR";
										}
										sheet.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(eb12Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb02Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 11)
												.setCellValue(eb06Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 12)
												.setCellValue(eb09Val);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(WebServiceConstants.ERROR_E024);
										} else {
											if (commonErrorMessage
													.indexOf(WebServiceConstants.ERROR_E024) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																WebServiceConstants.ERROR_E024);
											}
										}
									} else if (WebServiceConstants.ERROR_E027
											.equals(detailVO.getErrorCode())) {
										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);
										sheet.getRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									} else if (WebServiceConstants.ERROR_E019
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(
														eb03Val
																+ "-"
																+ detailVO
																		.getNetworkDescription());
										sheet.getRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(WebServiceConstants.ERROR_E019);
										} else {
											if (commonErrorMessage
													.indexOf(WebServiceConstants.ERROR_E019) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																WebServiceConstants.ERROR_E019);
											}
										}
									} else if (WebServiceConstants.ERROR_E028
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(
														"ER"
																+ eb03Val
																+ "-"
																+ detailVO
																		.getNetworkDescription());
										sheet.getRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);

										sheet.getRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb02Val);

										sheet.getRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);

										sheet.getRow((short) rowCount)
												.createCell((short) 11)
												.setCellValue(eb06Val);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(WebServiceConstants.ERROR_E028);
										} else {
											if (commonErrorMessage
													.indexOf(WebServiceConstants.ERROR_E028) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																WebServiceConstants.ERROR_E028);
											}
										}
									} else if (WebServiceConstants.ERROR_E018
											.equals(detailVO.getErrorCode())) {

										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);

										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(WebServiceConstants.ERROR_E018);
										} else {
											if (commonErrorMessage
													.indexOf(WebServiceConstants.ERROR_E018) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																WebServiceConstants.ERROR_E018);
											}
										}
									} else if (WebServiceConstants.ERROR_E038
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(
														WebServiceConstants.VISION);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									} else if (WebServiceConstants.ERROR_E039
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(
														WebServiceConstants.DENTAL);
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									} else if (WebServiceConstants.ERROR_E040
											.equals(detailVO.getErrorCode())) {

										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									}
								}
							}
							// 3. Contract --> ContractMappingVO -->
							// ErroeCodeList
							if (null != contractMappingVO.getContractMapping()) {
								ContractMappingWebServiceVO contractMappingAtSecondLevel = contractMappingVO
										.getContractMapping();
								if (null != contractMappingAtSecondLevel
										&& null != contractMappingAtSecondLevel
												.getErrorCodesList()) {
									List errorListAtSecondLevel = contractMappingAtSecondLevel
											.getErrorCodesList();
									if (null != errorListAtSecondLevel
											&& !errorListAtSecondLevel
													.isEmpty()) {
										Iterator errorListAtSecondLevelIterator = errorListAtSecondLevel
												.iterator();
										while (errorListAtSecondLevelIterator
												.hasNext()) {
											ErrorDetailWebServiceVO errorAtSecondLevel = (ErrorDetailWebServiceVO) errorListAtSecondLevelIterator
													.next();
											if (null != errorAtSecondLevel) {
												sheet
														.createRow(
																(short) rowCount)
														.createCell((short) 13)
														.setCellValue(
																null != errorAtSecondLevel
																		.getErrorCode() ? errorAtSecondLevel
																		.getErrorCode()
																		: "");
												sheet
														.getRow(
																(short) rowCount)
														.createCell((short) 14)
														.setCellValue(
																null != errorAtSecondLevel
																		.getErrorDesc() ? errorAtSecondLevel
																		.getErrorDesc()
																		: "");

												// EB01 population logic
												if (null != contractMappingAtSecondLevel
														.getEb01()
														&& (null != contractMappingAtSecondLevel
																.getEb01()
																.getHippaCodeSelectedValuesForEwpd() || null != contractMappingAtSecondLevel
																.getEb01()
																.getHippaCodeSelectedValuesForLgIsg())) {
													if (!contractMappingAtSecondLevel
															.getEb01()
															.getHippaCodeSelectedValuesForEwpd()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 8)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb01()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb01()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0)
																				.getValue()
																				: "");

													} else if (!contractMappingAtSecondLevel
															.getEb01()
															.getHippaCodeSelectedValuesForLgIsg()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 8)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb01()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb01()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0)
																				: "");
													}

												} else {
													sheet.getRow(
															(short) rowCount)
															.createCell(
																	(short) 8)
															.setCellValue("");
												}

												// EB02 population logic

												if (null != contractMappingAtSecondLevel
														.getEb02()
														&& (null != contractMappingAtSecondLevel
																.getEb02()
																.getHippaCodeSelectedValuesForEwpd() || null != contractMappingAtSecondLevel
																.getEb02()
																.getHippaCodeSelectedValuesForLgIsg())) {
													if (!contractMappingAtSecondLevel
															.getEb02()
															.getHippaCodeSelectedValuesForEwpd()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 9)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb02()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb02()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0)
																				.getValue()
																				: "");

													} else if (!contractMappingAtSecondLevel
															.getEb02()
															.getHippaCodeSelectedValuesForLgIsg()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 9)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb02()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb02()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0)
																				: "");
													}

												} else {
													sheet.getRow(
															(short) rowCount)
															.createCell(
																	(short) 9)
															.setCellValue("");
												}

												if (null != contractMappingAtSecondLevel
														.getEb03()
														&& (null != contractMappingAtSecondLevel
																.getEb03()
																.getHippaCodeSelectedValuesForEwpd() || null != contractMappingAtSecondLevel
																.getEb03()
																.getHippaCodeSelectedValuesForLgIsg())
														&& (null != contractMappingAtSecondLevel
																.getEb03()
																.getHippaCodeSelectedValuesForEwpd()
																.get(0) || null != contractMappingAtSecondLevel
																.getEb03()
																.getHippaCodeSelectedValuesForLgIsg()
																.get(0))

												) {
													String eb03AtSecondLevel = "";
													List eB03List = (!contractMappingAtSecondLevel
															.getEb03()
															.getHippaCodeSelectedValuesForEwpd()
															.isEmpty() ? contractMappingAtSecondLevel
															.getEb03()
															.getHippaCodeSelectedValuesForEwpd()
															: contractMappingAtSecondLevel
																	.getEb03()
																	.getHippaCodeSelectedValuesForLgIsg());
													if (null != eB03List
															&& !eB03List
																	.isEmpty()) {
														Iterator eB03ListIterator = eB03List
																.iterator();
														while (eB03ListIterator
																.hasNext()) {
															if (!contractMappingAtSecondLevel
																	.getEb03()
																	.getHippaCodeSelectedValuesForEwpd()
																	.isEmpty()) {
																HippaCodeValueWebServiceVO eb03HippaCode = (HippaCodeValueWebServiceVO) eB03ListIterator
																		.next();
																if (eb03AtSecondLevel
																		.equalsIgnoreCase("")) {
																	eb03AtSecondLevel = eb03HippaCode
																			.getValue();
																} else {
																	eb03AtSecondLevel = eb03AtSecondLevel
																			+ ","
																			+ eb03HippaCode
																					.getValue();
																}
															} else {
																String eb03HippaCode = (String) eB03ListIterator
																		.next();
																if (eb03AtSecondLevel
																		.equalsIgnoreCase("")) {
																	eb03AtSecondLevel = eb03HippaCode;
																} else {
																	eb03AtSecondLevel = eb03AtSecondLevel
																			+ ","
																			+ eb03HippaCode;
																}
															}

														}

													}

													sheet
															.getRow(
																	(short) rowCount)
															.createCell(
																	(short) 10)
															.setCellValue(
																	eb03AtSecondLevel);

												}

												// EB06 population logic

												if (null != contractMappingAtSecondLevel
														.getEb06()
														&& (null != contractMappingAtSecondLevel
																.getEb06()
																.getHippaCodeSelectedValuesForEwpd() || null != contractMappingAtSecondLevel
																.getEb06()
																.getHippaCodeSelectedValuesForLgIsg())) {
													if (!contractMappingAtSecondLevel
															.getEb06()
															.getHippaCodeSelectedValuesForEwpd()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 11)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb06()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb06()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0)
																				.getValue()
																				: "");

													} else if (!contractMappingAtSecondLevel
															.getEb06()
															.getHippaCodeSelectedValuesForLgIsg()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 11)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb06()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb06()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0)
																				: "");
													}

												} else {
													sheet.getRow(
															(short) rowCount)
															.createCell(
																	(short) 11)
															.setCellValue("");
												}

												// EB09 population logic

												if (null != contractMappingAtSecondLevel
														.getEb09()
														&& (null != contractMappingAtSecondLevel
																.getEb09()
																.getHippaCodeSelectedValuesForEwpd() || null != contractMappingAtSecondLevel
																.getEb09()
																.getHippaCodeSelectedValuesForLgIsg())) {
													if (!contractMappingAtSecondLevel
															.getEb09()
															.getHippaCodeSelectedValuesForEwpd()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 12)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb09()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb09()
																				.getHippaCodeSelectedValuesForEwpd()
																				.get(
																						0)
																				.getValue()
																				: "");

													} else if (!contractMappingAtSecondLevel
															.getEb09()
															.getHippaCodeSelectedValuesForLgIsg()
															.isEmpty()) {
														sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 12)
																.setCellValue(
																		null != contractMappingAtSecondLevel
																				.getEb09()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0) ? (String) contractMappingAtSecondLevel
																				.getEb09()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.get(
																						0)
																				: "");
													}

												} else {
													sheet.getRow(
															(short) rowCount)
															.createCell(
																	(short) 12)
															.setCellValue("");
												}

												sheet
														.getRow(
																(short) rowCount)
														.createCell((short) 3)
														.setCellValue(
																null != contractMappingAtSecondLevel
																		.getSpsId()
																		&& null != contractMappingAtSecondLevel
																				.getSpsId()
																				.getSpsId() ? (String) contractMappingAtSecondLevel
																		.getSpsId()
																		.getSpsId()
																		.toString()
																		: "");

												sheet
														.getRow(
																(short) rowCount)
														.createCell((short) 4)
														.setCellValue(
																null != contractMappingAtSecondLevel
																		.getSpsId()
																		&& null != contractMappingAtSecondLevel
																				.getSpsId()
																				.getSpsDesc() ? (String) contractMappingAtSecondLevel
																		.getSpsId()
																		.getSpsDesc()
																		.toString()
																		: "");

												sheet
														.getRow(
																(short) rowCount)
														.createCell((short) 5)
														.setCellValue(
																null != contractMappingAtSecondLevel
																		.getSpsId()
																		&& null != contractMappingAtSecondLevel
																				.getSpsId()
																				.getLinePVA() ? (String) contractMappingAtSecondLevel
																		.getSpsId()
																		.getLinePVA()
																		: "");

												sheet
														.getRow(
																(short) rowCount)
														.createCell((short) 6)
														.setCellValue(
																null != contractMappingAtSecondLevel
																		.getSpsId()
																		&& null != contractMappingAtSecondLevel
																				.getSpsId()
																				.getLineDataType() ? (String) contractMappingAtSecondLevel
																		.getSpsId()
																		.getLineDataType()
																		: "");
												sheet
														.getRow(
																(short) rowCount)
														.createCell((short) 7)
														.setCellValue(
																null != contractMappingAtSecondLevel
																		.getSpsId()
																		&& null != contractMappingAtSecondLevel
																				.getSpsId()
																				.getLineValue() ? (String) contractMappingAtSecondLevel
																		.getSpsId()
																		.getLineValue()
																		: "");
											}

										}

									}
								}

							}

						}

					}

					if (commonErrorMessage.length() != 0) {
						mQValidationSuccessMessage = commonErrorMessage.append(
								" is based on the contract information in "
										+ contract.getBackEndRegion())
								.toString();
					}
				}
			}

			// 4. Contract --> MajorHeading --> MinorHeading -->ErrorCodeList
			// MinorHeadingError Code List populated
			Iterator majorHeadingsIterator = contract.getMajorHeadings()
					.getEntry().iterator();

			if (majorHeadingsIterator != null) {
				while (majorHeadingsIterator.hasNext()) { // <-- Major
					// HeadingObject
					// String majorHeadingDesc = (String)
					// majorHeadingsIterator.next();
					// MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO)
					// majorHeadings.get(majorHeadingDesc);
					MajorHeadingsWebServiceVO majorHeadingFromMap = (MajorHeadingsWebServiceVO) ((ContractWebServiceVO.MajorHeadings.Entry) majorHeadingsIterator
							.next()).getValue();
					// MajorHeadingsWebServiceVO.MinorHeadings
					Iterator minorHeadingsIterator = majorHeadingFromMap
							.getMinorHeadings().getEntry().iterator();

					if (minorHeadingsIterator != null) {
						while (minorHeadingsIterator.hasNext()) {
							// String minorHeadingDesc = (String)
							// minorHeadingsIterator.next();
							// MinorHeadingsVO minorHeadingFromMap =
							// (MinorHeadingsVO)
							// minorHeadings.get(minorHeadingDesc);
							MinorHeadingsWebServiceVO minorHeadingFromMap = (MinorHeadingsWebServiceVO) ((MajorHeadingsWebServiceVO.MinorHeadings.Entry) minorHeadingsIterator
									.next()).getValue();
							List minorHeadingsErrorCodeList = minorHeadingFromMap
									.getErrorCodesList();
							if (minorHeadingsErrorCodeList != null
									&& !minorHeadingsErrorCodeList.isEmpty()) {
								Iterator minorHeadingsErrorCodeListItr = minorHeadingsErrorCodeList
										.iterator();
								if (minorHeadingsErrorCodeListItr != null) {
									while (minorHeadingsErrorCodeListItr
											.hasNext()) {
										rowCount++;
										ErrorDetailWebServiceVO errorMinorHeading = (ErrorDetailWebServiceVO) minorHeadingsErrorCodeListItr
												.next();
										sheet
												.createRow((short) rowCount)
												.createCell((short) 0)
												.setCellValue(
														majorHeadingFromMap
																.getDescriptionText());
										// TODO null check
										sheet
												.getRow((short) rowCount)
												.createCell((short) 1)
												.setCellValue(
														minorHeadingFromMap
																.getDescriptionText());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														errorMinorHeading
																.getErrorCode());
										sheet
												.getRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														errorMinorHeading
																.getErrorDesc());

										// For Error E043 to be reported at
										// Benefit
										// level along with the Eb03 values
										// If the check for E043 is removed,
										// then
										// all the benefit level errors have
										// Eb03
										// values.
										// defect fixed as part of BXNI
										if (WebServiceConstants.ERROR_E043
												.equals(errorMinorHeading
														.getErrorCode())) {
											List eb03List = null;
											if (null != minorHeadingFromMap
													.getRuleMapping()
													&& null != minorHeadingFromMap
															.getRuleMapping()
															.getEb03()
													&& (null != minorHeadingFromMap
															.getRuleMapping()
															.getEb03()
															.getHippaCodeSelectedValuesForEwpd() || null != minorHeadingFromMap
															.getRuleMapping()
															.getEb03()
															.getHippaCodeSelectedValuesForLgIsg())) {
												eb03List = (!minorHeadingFromMap
														.getRuleMapping()
														.getEb03()
														.getHippaCodeSelectedValuesForEwpd()
														.isEmpty() ? minorHeadingFromMap
														.getRuleMapping()
														.getEb03()
														.getHippaCodeSelectedValuesForEwpd()
														: minorHeadingFromMap
																.getRuleMapping()
																.getEb03()
																.getHippaCodeSelectedValuesForLgIsg());
											}
											eb03 = "";
											eb03Count = 0;
											if (eb03List != null
													&& !eb03List.isEmpty()) {
												Iterator eb03ListIterator = eb03List
														.iterator();
												if (eb03ListIterator != null) {

													while (eb03ListIterator
															.hasNext()) {
														eb03Count++;
														if (!minorHeadingFromMap
																.getRuleMapping()
																.getEb03()
																.getHippaCodeSelectedValuesForEwpd()
																.isEmpty()) {
															HippaCodeValueWebServiceVO eb03Value = new HippaCodeValueWebServiceVO();
															eb03Value = (HippaCodeValueWebServiceVO) eb03ListIterator
																	.next();
															if (null != eb03Value) {
																String eb03Val = (String) eb03Value
																		.getValue()
																		.toString();

																if (eb03Count != 1) {
																	eb03 = eb03
																			+ ","
																			+ eb03Val;
																} else {
																	eb03 = eb03Val;

																}
															}
														} else if (!minorHeadingFromMap
																.getRuleMapping()
																.getEb03()
																.getHippaCodeSelectedValuesForLgIsg()
																.isEmpty()) {
															HippaCodeValueWebServiceVO eb03Value = new HippaCodeValueWebServiceVO();
															eb03Value = (HippaCodeValueWebServiceVO) eb03ListIterator
																	.next();
															if (null != eb03Value) {
																String eb03Val = (String) eb03Value
																		.getValue()
																		.toString();

																if (eb03Count != 1) {
																	eb03 = eb03
																			+ ","
																			+ eb03Val;
																} else {
																	eb03 = eb03Val;

																}
															}

														}
													}
													sheet.getRow(
															(short) rowCount)
															.createCell(
																	(short) 10)
															.setCellValue(eb03);

												}
											}

										}
									}
								}
							}

							// Contract --> Major Heading --> MinorHeading -->
							// Mapping --> ContractMapping --> ErrorCode List
							Iterator mappingsIterator = minorHeadingFromMap
									.getMappings().getEntry().iterator();

							if (mappingsIterator != null) {
								while (mappingsIterator.hasNext()) {
									// String mappingKey = (String)
									// mappingsIterator.next();
									// ContractMappingVO mappingFromMap =
									// (ContractMappingVO)
									// mappings.get(mappingKey);

									MinorHeadingsWebServiceVO.Mappings.Entry val1 = ((MinorHeadingsWebServiceVO.Mappings.Entry) mappingsIterator
											.next());
									if (null != val1) {
										MappingWebServiceVO mappingFromMap = (MappingWebServiceVO) val1
												.getValue();
										if ((mappingFromMap.getClass()
												.getName())
												.equalsIgnoreCase(WebServiceConstants.CONTRACT_MAPPING_VO_CLASS)) {
											if (mappingFromMap != null) {
												SpsIdWebServiceVO spsId = mappingFromMap
														.getSpsId();
												List errorCodeList = mappingFromMap
														.getContractMapping()
														.getErrorCodesList();
												if (errorCodeList != null
														&& !errorCodeList
																.isEmpty()) {
													Iterator errorCodeListIterator = errorCodeList
															.iterator();
													if (errorCodeListIterator != null) {
														while (errorCodeListIterator
																.hasNext()) {
															rowCount++;
															ErrorDetailWebServiceVO error = (ErrorDetailWebServiceVO) errorCodeListIterator
																	.next();
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 0)
																	.setCellValue(
																			majorHeadingFromMap
																					.getDescriptionText());
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 1)
																	.setCellValue(
																			minorHeadingFromMap
																					.getDescriptionText());
															// Code change to
															// include the
															// tier description-
															// July
															// release
															if (null != mappingFromMap
																	.getContractMapping()
																	.getContractMapping()
																	&& null != mappingFromMap
																			.getContractMapping()
																			.getContractMapping()
																			.getTierDescription()) {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 2)
																		.setCellValue(
																				mappingFromMap
																						.getContractMapping()
																						.getContractMapping()
																						.getTierDescription());  // 16332 Production fix

															}
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 3)
																	.setCellValue(
																			null != mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					&& null != mappingFromMap
																							.getContractMapping()
																							.getSpsId()
																							.getSpsId() ? (String) mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					.getSpsId()
																					: "");
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 4)
																	.setCellValue(
																			null != mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					&& null != mappingFromMap
																							.getContractMapping()
																							.getSpsId()
																							.getSpsDesc() ? (String) mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					.getSpsDesc()
																					: "");
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 5)
																	.setCellValue(
																			null != mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					&& null != mappingFromMap
																							.getContractMapping()
																							.getSpsId()
																							.getLinePVA() ? (String) mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					.getLinePVA()
																					: "");
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 6)
																	.setCellValue(
																			null != mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					&& null != mappingFromMap
																							.getContractMapping()
																							.getSpsId()
																							.getLineDataType() ? (String) mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					.getLineDataType()
																					: "");
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 7)
																	.setCellValue(
																			null != mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					&& null != mappingFromMap
																							.getContractMapping()
																							.getSpsId()
																							.getLineValue() ? (String) mappingFromMap
																					.getContractMapping()
																					.getSpsId()
																					.getLineValue()
																					: "");

															// EB01 population
															// logic

															if (null != mappingFromMap
																	.getContractMapping()
																	.getEb01()
																	&& (null != mappingFromMap
																			.getContractMapping()
																			.getEb01()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap
																			.getContractMapping()
																			.getEb01()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (!mappingFromMap
																		.getContractMapping()
																		.getEb01()
																		.getHippaCodeSelectedValuesForEwpd()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 8)
																			.setCellValue(
																					null != mappingFromMap
																							.getContractMapping()
																							.getEb01()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap
																							.getContractMapping()
																							.getEb01()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (!mappingFromMap
																		.getContractMapping()
																		.getEb01()
																		.getHippaCodeSelectedValuesForLgIsg()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 8)
																			.setCellValue(
																					null != mappingFromMap
																							.getContractMapping()
																							.getEb01()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap
																							.getContractMapping()
																							.getEb01()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 8)
																		.setCellValue(
																				"");
															}

															// EB02 population
															// logic

															if (null != mappingFromMap
																	.getContractMapping()
																	.getEb02()
																	&& (null != mappingFromMap
																			.getContractMapping()
																			.getEb02()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap
																			.getContractMapping()
																			.getEb02()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (!mappingFromMap
																		.getContractMapping()
																		.getEb02()
																		.getHippaCodeSelectedValuesForEwpd()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 9)
																			.setCellValue(
																					null != mappingFromMap
																							.getContractMapping()
																							.getEb02()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap
																							.getContractMapping()
																							.getEb02()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (!mappingFromMap
																		.getContractMapping()
																		.getEb02()
																		.getHippaCodeSelectedValuesForLgIsg()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 9)
																			.setCellValue(
																					null != mappingFromMap
																							.getContractMapping()
																							.getEb02()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap
																							.getContractMapping()
																							.getEb02()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 9)
																		.setCellValue(
																				"");
															}

															// EB03
															List eb03List = null;
															if (null != minorHeadingFromMap
																	.getRuleMapping()
																	&& null != minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																	&& (null != minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForEwpd() || null != minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																eb03List = (!minorHeadingFromMap
																		.getRuleMapping()
																		.getEb03()
																		.getHippaCodeSelectedValuesForEwpd()
																		.isEmpty() ? minorHeadingFromMap
																		.getRuleMapping()
																		.getEb03()
																		.getHippaCodeSelectedValuesForEwpd()
																		: minorHeadingFromMap
																				.getRuleMapping()
																				.getEb03()
																				.getHippaCodeSelectedValuesForLgIsg());
															}
															eb03 = "";
															eb03Count = 0;
															if (eb03List != null
																	&& !eb03List
																			.isEmpty()) {
																Iterator eb03ListIterator = eb03List
																		.iterator();
																while (eb03ListIterator
																		.hasNext()) {
																	eb03Count++;
																	if (!minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForEwpd()
																			.isEmpty()) {
																		HippaCodeValueWebServiceVO eb03Value = new HippaCodeValueWebServiceVO();
																		eb03Value = (HippaCodeValueWebServiceVO) eb03ListIterator
																				.next();
																		if (null != eb03Value) {
																			String eb03Val = (String) eb03Value
																					.getValue()
																					.toString();

																			if (eb03Count != 1) {
																				eb03 = eb03
																						+ ","
																						+ eb03Val;
																			} else {
																				eb03 = eb03Val;

																			}
																		}
																	} else if (!minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForLgIsg()
																			.isEmpty()) {
																		HippaCodeValueWebServiceVO eb03Value = new HippaCodeValueWebServiceVO();
																		eb03Value = (HippaCodeValueWebServiceVO) eb03ListIterator
																				.next();
																		if (null != eb03Value) {
																			String eb03Val = (String) eb03Value
																					.getValue()
																					.toString();

																			if (eb03Count != 1) {
																				eb03 = eb03
																						+ ","
																						+ eb03Val;
																			} else {
																				eb03 = eb03Val;

																			}
																		}
																	}
																}
															}

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 10)
																	.setCellValue(
																			eb03);
															// EB06 population
															// logic

															if (null != mappingFromMap
																	.getContractMapping()
																	.getEb09()
																	&& (null != mappingFromMap
																			.getContractMapping()
																			.getEb09()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap
																			.getContractMapping()
																			.getEb09()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (!mappingFromMap
																		.getContractMapping()
																		.getEb09()
																		.getHippaCodeSelectedValuesForEwpd()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 12)
																			.setCellValue(
																					null != mappingFromMap
																							.getContractMapping()
																							.getEb09()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap
																							.getContractMapping()
																							.getEb09()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (!mappingFromMap
																		.getContractMapping()
																		.getEb09()
																		.getHippaCodeSelectedValuesForLgIsg()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 12)
																			.setCellValue(
																					null != mappingFromMap
																							.getContractMapping()
																							.getEb09()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap
																							.getContractMapping()
																							.getEb09()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 12)
																		.setCellValue(
																				"");
															}

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 13)
																	.setCellValue(
																			error
																					.getErrorCode());
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 14)
																	.setCellValue(
																			error
																					.getErrorDesc());

														}
													}
												}

											}
										}

										ContractMappingWebServiceVO mappingFromMap1 = (ContractMappingWebServiceVO) val1
												.getValue();
										if ((mappingFromMap1.getClass()
												.getName())
												.equalsIgnoreCase(WebServiceConstants.CONTRACT_MAPPING_VO_CLASS)) {

											if (mappingFromMap1 != null) {
												SpsIdWebServiceVO spsId = mappingFromMap1
														.getSpsId();
												List errorCodeListFromMinorHeadingMapping = mappingFromMap1
														.getErrorCodesList();
												if (errorCodeListFromMinorHeadingMapping != null
														&& !errorCodeListFromMinorHeadingMapping
																.isEmpty()) {
													Iterator errorCodeListIterator = errorCodeListFromMinorHeadingMapping
															.iterator();
													if (errorCodeListIterator != null) {
														while (errorCodeListIterator
																.hasNext()) {
															rowCount++;
															ErrorDetailWebServiceVO errorFromMapping = (ErrorDetailWebServiceVO) errorCodeListIterator
																	.next();
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 0)
																	.setCellValue(
																			majorHeadingFromMap
																					.getDescriptionText());
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 1)
																	.setCellValue(
																			minorHeadingFromMap
																					.getDescriptionText());
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 13)
																	.setCellValue(
																			errorFromMapping
																					.getErrorCode());
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 14)
																	.setCellValue(
																			errorFromMapping
																					.getErrorDesc());

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 2)
																	.setCellValue(
																			null != mappingFromMap1
																					.getContractMapping()
																					.getTierDescription() ? mappingFromMap1
																					.getContractMapping()
																					.getTierDescription()
																					: "");  // 16332 production fix

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 3)
																	.setCellValue(
																			null != mappingFromMap1
																					.getSpsId()
																					&& null != mappingFromMap1
																							.getSpsId()
																							.getSpsId() ? mappingFromMap1
																					.getSpsId()
																					.getSpsId()
																					: "");

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 4)
																	.setCellValue(
																			null != mappingFromMap1
																					.getSpsId()
																					&& null != mappingFromMap1
																							.getSpsId()
																							.getSpsDesc() ? mappingFromMap1
																					.getSpsId()
																					.getSpsDesc()
																					: "");

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 5)
																	.setCellValue(
																			null != mappingFromMap1
																					.getSpsId()
																					&& null != mappingFromMap1
																							.getSpsId()
																							.getLinePVA() ? mappingFromMap1
																					.getSpsId()
																					.getLinePVA()
																					: "");

															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 6)
																	.setCellValue(
																			null != mappingFromMap1
																					.getSpsId()
																					&& null != mappingFromMap1
																							.getSpsId()
																							.getLineDataType() ? mappingFromMap1
																					.getSpsId()
																					.getLineDataType()
																					: "");
															sheet
																	.getRow(
																			(short) rowCount)
																	.createCell(
																			(short) 7)
																	.setCellValue(
																			null != mappingFromMap1
																					.getSpsId()
																					&& null != mappingFromMap1
																							.getSpsId()
																							.getLineValue() ? mappingFromMap1
																					.getSpsId()
																					.getLineValue()
																					: "");
															// EB01
															if (null != mappingFromMap1
																	.getEb01()
																	&& (null != mappingFromMap1
																			.getEb01()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap1
																			.getEb01()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (!mappingFromMap1
																		.getEb01()
																		.getHippaCodeSelectedValuesForEwpd()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 8)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb01()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb01()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (!mappingFromMap1
																		.getEb01()
																		.getHippaCodeSelectedValuesForLgIsg()
																		.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 8)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb01()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb01()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 8)
																		.setCellValue(
																				"");
															}

															// EB02
															if (null != mappingFromMap1
																	.getEb02()
																	&& (null != mappingFromMap1
																			.getEb02()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap1
																			.getEb02()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (null != mappingFromMap1
																		.getEb02()
																		.getHippaCodeSelectedValuesForEwpd()
																		&& !mappingFromMap1
																				.getEb02()
																				.getHippaCodeSelectedValuesForEwpd()
																				.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 9)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb02()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb02()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (null != mappingFromMap1
																		.getEb02()
																		.getHippaCodeSelectedValuesForLgIsg()
																		&& !mappingFromMap1
																				.getEb02()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 9)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb02()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb02()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 9)
																		.setCellValue(
																				"");
															}

															// EB03
															List eb03List = null;
															if (null != minorHeadingFromMap
																	.getRuleMapping()
																	&& null != minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																	&& (null != minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForEwpd() || null != minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																eb03List = (!minorHeadingFromMap
																		.getRuleMapping()
																		.getEb03()
																		.getHippaCodeSelectedValuesForEwpd()
																		.isEmpty() ? minorHeadingFromMap
																		.getRuleMapping()
																		.getEb03()
																		.getHippaCodeSelectedValuesForEwpd()
																		: minorHeadingFromMap
																				.getRuleMapping()
																				.getEb03()
																				.getHippaCodeSelectedValuesForLgIsg());
															}
															eb03 = "";
															eb03Count = 0;
															if (eb03List != null
																	&& !eb03List
																			.isEmpty()) {
																Iterator eb03ListIterator = eb03List
																		.iterator();
																while (eb03ListIterator
																		.hasNext()) {
																	eb03Count++;
																	if (!minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForEwpd()
																			.isEmpty()) {
																		HippaCodeValueWebServiceVO eb03Value = new HippaCodeValueWebServiceVO();
																		eb03Value = (HippaCodeValueWebServiceVO) eb03ListIterator
																				.next();
																		if (null != eb03Value) {
																			String eb03Val = (String) eb03Value
																					.getValue()
																					.toString();

																			if(WebServiceConstants.ERROR_E029.equals(errorFromMapping.getErrorCode())
																					|| WebServiceConstants.ERROR_E042.equals(errorFromMapping.getErrorCode())) {
																				eb03 = errorFromMapping.getAssociatedEb03();
																			}else if (eb03Count != 1) {
																				eb03 = eb03
																						+ ","
																						+ eb03Val;
																			} else {
																				eb03 = eb03Val;

																			}
																		}
																	} else if (!minorHeadingFromMap
																			.getRuleMapping()
																			.getEb03()
																			.getHippaCodeSelectedValuesForLgIsg()
																			.isEmpty()) {
																		HippaCodeValueWebServiceVO eb03Value = new HippaCodeValueWebServiceVO();
																		eb03Value = (HippaCodeValueWebServiceVO) eb03ListIterator
																				.next();
																		if (null != eb03Value) {
																			String eb03Val = (String) eb03Value
																					.getValue()
																					.toString();

																			if(WebServiceConstants.ERROR_E029.equals(errorFromMapping.getErrorCode())
																					|| WebServiceConstants.ERROR_E042.equals(errorFromMapping.getErrorCode())) {
																				eb03 = errorFromMapping.getAssociatedEb03();
																			}else if (eb03Count != 1) {
																				eb03 = eb03
																						+ ","
																						+ eb03Val;
																			} else {
																				eb03 = eb03Val;

																			}
																		}
																	}
																}
															}
															
															sheet
																.getRow(
																		(short) rowCount)
																.createCell(
																		(short) 10)
																.setCellValue(
																		eb03);

															// EB06
															if (null != mappingFromMap1
																	.getEb06()
																	&& (null != mappingFromMap1
																			.getEb06()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap1
																			.getEb06()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (null != mappingFromMap1
																		.getEb06()
																		.getHippaCodeSelectedValuesForEwpd()
																		&& !mappingFromMap1
																				.getEb06()
																				.getHippaCodeSelectedValuesForEwpd()
																				.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 11)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb06()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb06()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (null != mappingFromMap1
																		.getEb06()
																		.getHippaCodeSelectedValuesForLgIsg()
																		&& !mappingFromMap1
																				.getEb06()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 11)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb06()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb06()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 11)
																		.setCellValue(
																				"");
															}

															// EB09 population
															// logic

															if (null != mappingFromMap1
																	.getEb09()
																	&& (null != mappingFromMap1
																			.getEb09()
																			.getHippaCodeSelectedValuesForEwpd() || null != mappingFromMap1
																			.getEb09()
																			.getHippaCodeSelectedValuesForLgIsg())) {
																if (null != mappingFromMap1
																		.getEb09()
																		.getHippaCodeSelectedValuesForEwpd()
																		&& !mappingFromMap1
																				.getEb09()
																				.getHippaCodeSelectedValuesForEwpd()
																				.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 12)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb09()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb09()
																							.getHippaCodeSelectedValuesForEwpd()
																							.get(
																									0)
																							.getValue()
																							: "");

																} else if (null != mappingFromMap1
																		.getEb09()
																		.getHippaCodeSelectedValuesForLgIsg()
																		&& !mappingFromMap1
																				.getEb09()
																				.getHippaCodeSelectedValuesForLgIsg()
																				.isEmpty()) {
																	sheet
																			.getRow(
																					(short) rowCount)
																			.createCell(
																					(short) 12)
																			.setCellValue(
																					null != mappingFromMap1
																							.getEb09()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0) ? (String) mappingFromMap1
																							.getEb09()
																							.getHippaCodeSelectedValuesForLgIsg()
																							.get(
																									0)
																							: "");
																}

															} else {
																sheet
																		.getRow(
																				(short) rowCount)
																		.createCell(
																				(short) 12)
																		.setCellValue(
																				"");
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
			}
		}
		if (mQValidationExists) {
			rowCount = rowCount + 6;
			sheet.createRow((short) rowCount).createCell((short) 0)
					.setCellValue(mQValidationSuccessMessage);
		}

		totalRowCount = rowCount;
	}

}
