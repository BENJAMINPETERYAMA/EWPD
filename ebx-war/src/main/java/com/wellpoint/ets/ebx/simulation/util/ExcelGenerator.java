/*
 * <ExcelGenerator.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.owasp.esapi.ESAPI;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.EBConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.X12Parser;
import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.FunctionGroup;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Interchange;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2000;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2100;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2110;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2115;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2120;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Transaction;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.AAA;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.DTP;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.EB;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.HSD;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.MSG;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.PER;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.REF;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.ErrorDetailVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;

/**
 * @author UST-GLOBAL
 * 
 *         ExcelGenerator class. This class will create the Error Report Excel,
 *         X12 Response Excel and Benefit Accum Service Response Excel Report
 * 
 */
public class ExcelGenerator {

	public static final Logger log = Logger.getLogger(ExcelGenerator.class);
	
	/**
	 * 
	 * @param contract
	 * @param workbook
	 *            generate27xBenefitAccumExcel Method will create Excel for the
	 *            XML Response using contract Object.
	 */
	public static void generate27xBenefitAccumExcel(List contractList,
			HSSFWorkbook workbook) {
		log.debug("Entering Method generate27xBenefitAccumExcel");
		// initializing Variables
		boolean isExcel = true;
		int rowCount = 2;
		ContractMappingVO contractMapping = null;
		String contractId = "";
		boolean exists = false;
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		
		ResourceBundle rb = ResourceBundle.getBundle(
				DomainConstants.TWENTY_SEVEN_X_REQUEST, Locale.getDefault());
		
		log.debug("Getting the staticData Sheet");
		HSSFSheet sheet = generateStaticData(workbook, isExcel, null);
		HSSFCellStyle cellstyle = getStyle(workbook, HSSFColor.WHITE.index,
				HSSFFont.U_SINGLE);
		HSSFCellStyle cellstyleWrap;
		HSSFCellStyle cellstyleForServiceTypeCodeAs30 = getStyle(workbook, HSSFColor.LIGHT_YELLOW.index,
				HSSFFont.U_SINGLE);
		HSSFCellStyle style;
		boolean urgentOrSpecialistMessageExists;
		// Getting the MajorHeadingsMap from Contract
		if (contractList != null) {
			for (int cnt = 0; cnt < contractList.size(); cnt++) {
				ContractVO contract = (ContractVO) contractList.get(cnt);
				if (null != contract) {
					log.debug("Gettting the MajorHeadingsMap from Contract.");
					Map majorHeadingsMap = contract.getMajorHeadings();
					log.debug("Checking whether contractId exists or not");
					if (!StringUtils.isBlank(contract.getContractId())) {
						contractId = contract.getContractId();
						log.debug("contractId: " +ESAPI.encoder().encodeForHTML(contractId));
						if (cnt == 0) {
							sheet.createRow((short) 0).createCell((short) 0)
									.setCellValue(
											"Static Benefit Information for "
													+ contractId);
							sheet.getRow(0).getCell((short) 0).setCellStyle(
									getStyle(workbook, HSSFColor.WHITE.index,
											HSSFFont.BOLDWEIGHT_BOLD));
							sheet.getRow(0).setHeightInPoints((short) 30);
							
						}
					} else {
						log.debug("Contractid does not exist in the resposne.");
						throw new NullPointerException("ContractId is missing");
					}
					log.debug("majorHeadingsMap size:"
							+ majorHeadingsMap.size());
					// Iterating the MajorHeadings to get the MinorHeadings and
					// the Mappings.
					// Setting the EB Values and appending to the Excel.
					for (int i = 0; i < majorHeadingsMap.size(); i++) {
						String EB01 = "", EB02 = "", EB03 = "", EB04 = "", EB05 = "", EB06 = "", EB07 = "", EB08 = "", EB09 = "", EB10 = "", EB11 = "", EB12 = "",EB11Value="";
						float percent = 0;
						urgentOrSpecialistMessageExists=false;
						MajorHeadingsVO majorHeadings = (MajorHeadingsVO) majorHeadingsMap
								.get(Integer.valueOf(i));
						Map minorHeadings = majorHeadings.getMinorHeadings();
						MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadings
								.get(Integer.valueOf(i));
						Map mappings = minorHeadingFromMap.getMappings();
						exists = false;
						rowCount++;
						Mapping mapping = (Mapping) mappings
								.get(Integer.valueOf(i));
						if (mapping.getContractMapping() != null) {
							contractMapping = mapping.getContractMapping();
							exists = true;
						}
						if (mapping.getEb01() != null
								&& mapping.getEb01()
										.getHippaCodePossibleValues().size() > 0) {
							EB01 = mapping.getEb01()
									.getHippaCodePossibleValues().get(0)
									.toString();
						}
						if (mapping.getEb02() != null
								&& mapping.getEb02()
										.getHippaCodePossibleValues().size() > 0) {
							EB02 = mapping.getEb02()
									.getHippaCodePossibleValues().get(0)
									.toString();
						}
						if (mapping.getEb03() != null
								&& mapping.getEb03()
										.getHippaCodePossibleValues().size() > 0) {
							EB03 = mapping.getEb03()
									.getHippaCodePossibleValues().get(0)
									.toString();
						}
						if (exists) {
							if (cnt == 0) {
								String EB_04 = "", EB_05 = "";
								if (contractMapping.getEb04() != null
										&& contractMapping.getEb04()
												.getHippaCodePossibleValues()
												.size() > 0) {
									String val = (String) contractMapping
									.getEb04()
									.getHippaCodePossibleValues()
									.get(0);
									EB_04 =X12ParserUtil.getX12Description("EB04_"+val.trim());
									
									
									/*EB_04 = (String) EBConstants.EB04map
													.get(contractMapping
															.getEb04()
															.getHippaCodePossibleValues()
															.get(0));*/
										
								}
								if (contractMapping.getEb05() != null
										&& contractMapping.getEb05()
												.getHippaCodePossibleValues()
												.size() > 0) {
									EB_05 = contractMapping.getEb05()
											.getHippaCodePossibleValues().get(0)
											.toString();
										
								}
								
								HSSFCellStyle headercellstyle =   getStyle(workbook, HSSFColor.WHITE.index,
										HSSFFont.BOLDWEIGHT_BOLD);
								sheet.createRow((short) 1).createCell((short) 0).setCellValue("EB04 : ");
								sheet.getRow((short) 1).getCell((short) 0).setCellStyle(headercellstyle);
								
								sheet.getRow((short) 1).createCell((short) 1).setCellValue(EB_04);
								sheet.getRow((short) 1).getCell((short) 1).setCellStyle(headercellstyle);
								
								sheet.getRow((short) 1).createCell((short) 2).setCellValue("EB05 : ");
								sheet.getRow((short) 1).getCell((short) 2).setCellStyle(headercellstyle);
								
								sheet.getRow((short) 1).createCell((short) 3).setCellValue( EB_05);
								sheet.getRow((short) 1).getCell((short) 3).setCellStyle(headercellstyle);
								
								sheet.getRow(1).setHeightInPoints((short) 30);
								
								
							}
							if (contractMapping.getEb04() != null
									&& contractMapping.getEb04()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB04 = contractMapping.getEb04()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
							if (contractMapping.getEb05() != null
									&& contractMapping.getEb05()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB05 = contractMapping.getEb05()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
						}
						if (mapping.getEb06() != null
								&& mapping.getEb06()
										.getHippaCodePossibleValues().size() > 0) {
							EB06 = mapping.getEb06()
									.getHippaCodePossibleValues().get(0)
									.toString();
						}
						if (exists) {
							if (contractMapping.getEb07() != null
									&& contractMapping.getEb07()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB07 = contractMapping.getEb07()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
							if (contractMapping.getEb08() != null
									&& contractMapping.getEb08()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB08 = contractMapping.getEb08()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
							if (contractMapping.getEb09() != null
									&& contractMapping.getEb09()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB09 = contractMapping.getEb09()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
							if (contractMapping.getEb10() != null
									&& contractMapping.getEb10()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB10 = contractMapping.getEb10()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
						
							if (contractMapping.getEb11() != null
									&& contractMapping.getEb11()
											.getHippaCodePossibleValues()
											.size() > 0) {
								String value=contractMapping.getEb11()
								.getHippaCodePossibleValues().get(0)
								.toString();
								String expression = "[.]";
								String[] arr = value.split(expression);
								try{
									EB11 = rb.getString(value);
									EB11Value=arr[2];
								}catch(MissingResourceException e){
									EB11 = arr[2];
									EB11Value=arr[2];
								}
							}
							if (contractMapping.getEb12() != null
									&& contractMapping.getEb12()
											.getHippaCodePossibleValues()
											.size() > 0) {
								EB12 = contractMapping.getEb12()
										.getHippaCodePossibleValues().get(0)
										.toString();
							}
						}
						for(int msg=0;msg<getMsgList().size();msg++){
							String message=(String) getMsgList().get(msg);
							if(!StringUtils.isBlank(mapping.getMessage())
									&& (mapping.getMessage().indexOf(message)!=-1)){
								urgentOrSpecialistMessageExists=true;
								break;
							}
						}
						//removing highlighting of SPECIALIST/URGENT benefit row in the static report-- BXNI Dec
						if(getEB03List().contains(EB03)){
							style=cellstyleForServiceTypeCodeAs30;
							cellstyleWrap = getStyle(workbook, HSSFColor.LIGHT_YELLOW.index,
									HSSFFont.U_SINGLE);
						}else{
							cellstyleWrap = getStyle(workbook, HSSFColor.WHITE.index,
									HSSFFont.U_SINGLE);
							style=cellstyle;
						}
						cellstyleWrap.setWrapText(true);
						sheet.createRow((short) rowCount).createCell((short) 0)
								.setCellStyle(cellstyleWrap);	
						int ebStringSize = 256 * 50;
						sheet.setColumnWidth((short) 0, (short) ebStringSize);
						if (!StringUtils.isBlank(EB12)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.STAR_CHAR
													+ EB07
													+ DomainConstants.STAR_CHAR
													+ EB08
													+ DomainConstants.STAR_CHAR
													+ EB09
													+ DomainConstants.STAR_CHAR
													+ EB10
													+ DomainConstants.STAR_CHAR
													+ EB11
													+ DomainConstants.STAR_CHAR
													+ EB12
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB11)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.STAR_CHAR
													+ EB07
													+ DomainConstants.STAR_CHAR
													+ EB08
													+ DomainConstants.STAR_CHAR
													+ EB09
													+ DomainConstants.STAR_CHAR
													+ EB10
													+ DomainConstants.STAR_CHAR
													+ EB11
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB10)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.STAR_CHAR
													+ EB07
													+ DomainConstants.STAR_CHAR
													+ EB08
													+ DomainConstants.STAR_CHAR
													+ EB09
													+ DomainConstants.STAR_CHAR
													+ EB10
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB09)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													//*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.STAR_CHAR
													+ EB07
													+ DomainConstants.STAR_CHAR
													+ EB08
													+ DomainConstants.STAR_CHAR
													+ EB09
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB08)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.STAR_CHAR
													+ EB07
													+ DomainConstants.STAR_CHAR
													+ EB08
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB07)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.STAR_CHAR
													+ EB07
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB06)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05*/
													+ DomainConstants.STAR_CHAR
													+ EB06
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB05)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04 */
													+ DomainConstants.STAR_CHAR
													/*+ EB05 */
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB04)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.STAR_CHAR
													/*+ EB04*/
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB03)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.STAR_CHAR
													+ EB03
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB02)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.STAR_CHAR
													+ EB02
													+ DomainConstants.TILDA_CHAR);
						} else if (!StringUtils.isBlank(EB01)) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(
											DomainConstants.EB
													+ EB01
													+ DomainConstants.TILDA_CHAR);
						}
						
						String hsdString = constructX12forHSD(mapping);
						if (null != hsdString && !hsdString.trim().isEmpty()) {
							String eBstring = sheet
							.getRow((short) rowCount)
							.getCell((short) 0)
							.getStringCellValue();
							sheet
							.getRow((short) rowCount)
							.getCell((short) 0)
							.setCellValue(eBstring+"\n"+hsdString);
						}
						
						//Changed					
						if (!StringUtils.isBlank(mapping.getMessage())) {
							String msgString = mapping.getMessage();
							msgString = msgString.replaceAll("~", "~\n");
							msgString.trim();
							msgString = StringUtils.chomp(msgString);
							String eBstring = sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.getStringCellValue();
									sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(eBstring+"\n"+msgString);
						}
						
						/*if (!StringUtils.isBlank(mapping.getMessage())) {
							String eBstring = sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.getStringCellValue();
									sheet
									.getRow((short) rowCount)
									.getCell((short) 0)
									.setCellValue(eBstring+"\n"+mapping.getMessage());
						}*/
					
						if (mapping.getIi02() != null
								&& mapping.getIi02()
										.getHippaCodePossibleValues().size() > 0
								&& !StringUtils.isBlank(mapping.getIi02()
										.getHippaCodePossibleValues().get(0)
										.toString())) {
							String eBstring = sheet
							.getRow((short) rowCount)
							.getCell((short) 0)
							.getStringCellValue();
							sheet
							.getRow((short) rowCount)
							.getCell((short) 0)
							.setCellValue(eBstring+"\n"+DomainConstants.III
									+ DomainConstants.ZZ
									+ mapping
											.getIi02()
											.getHippaCodePossibleValues()
											.get(0)
									+ DomainConstants.TILDA_CHAR);
						}
						//Ended
						
						sheet.createRow((short) rowCount).createCell((short) 1)
								.setCellStyle(style);
						if (mapping.getEb03() != null
								&& mapping.getEb03()
										.getHippaCodePossibleValues().size() > 0) {
							String val = (String) mapping.getEb03()
							.getHippaCodePossibleValues().get(0);
							String EB_03 = "";
							
							EB_03 = X12ParserUtil.getX12Description("EB03_"+val.trim());
							
							sheet
									.getRow((short) rowCount)
									.getCell((short) 1)
									.setCellValue(
											EB_03);
						}
						sheet.createRow((short) rowCount).createCell((short) 2)
								.setCellStyle(style);
						if (mapping.getEb01() != null
								&& mapping.getEb01()
										.getHippaCodePossibleValues().size() > 0) {
							String val = (String) mapping.getEb01()
							.getHippaCodePossibleValues().get(0);
							String EB_01 = "";
							
							EB_01 = X12ParserUtil.getX12Description("EB01_"+val.trim());
							
							sheet
									.getRow((short) rowCount)
									.getCell((short) 2)
									.setCellValue(
											EB_01);
						}
						sheet.createRow((short) rowCount).createCell((short) 3)
								.setCellStyle(style);
						if (exists) {
							if (contractMapping.getEb12() != null
									&& contractMapping.getEb12()
											.getHippaCodePossibleValues()
											.size() > 0) {
								String val = (String) contractMapping.getEb12()
								.getHippaCodePossibleValues().get(0);
								String EB_12 = "";
								
								EB_12 = X12ParserUtil.getX12Description("EB12_"+val.trim());
					
									sheet.getRow((short) rowCount).getCell(
											(short) 3).setCellValue(
											EB_12);
								
							}
							sheet.createRow((short) rowCount).createCell(
									(short) 4).setCellStyle(style);
							if (contractMapping.getEb07() != null
									&& contractMapping.getEb07()
											.getHippaCodePossibleValues()
											.size() > 0) {
								sheet
										.getRow((short) rowCount)
										.getCell((short) 4)
										.setCellValue(
												DomainConstants.DOLLAR
														+ contractMapping
																.getEb07()
																.getHippaCodePossibleValues()
																.get(0)
																.toString());
							}
							if (contractMapping.getEb08() != null
									&& contractMapping.getEb08()
											.getHippaCodePossibleValues()
											.size() > 0) {
								
								String dolPercentHashValue = sheet
								.getRow((short) rowCount)
								.getCell((short) 4)
								.getStringCellValue();
								try {
									percent = Float.parseFloat(contractMapping
											.getEb08()
											.getHippaCodePossibleValues()
											.get(0).toString());
									percent = percent * 100;
									
									if(null != dolPercentHashValue && !dolPercentHashValue.equalsIgnoreCase("") ){
										sheet
										.getRow((short) rowCount)
										.getCell((short) 4)
										.setCellValue(dolPercentHashValue +", "+
												(int) percent
														+ DomainConstants.PERCENTAGE);
									}else{
									sheet
											.getRow((short) rowCount)
											.getCell((short) 4)
											.setCellValue(
													(int) percent
															+ DomainConstants.PERCENTAGE);
									}
								} catch (NumberFormatException e) {
									log
											.debug("NumberFormatException Caught for : "
													+ contractMapping
															.getEb08()
															.getHippaCodePossibleValues()
															.get(0).toString()
													+ DomainConstants.PERCENTAGE);
									
									if(null != dolPercentHashValue && !dolPercentHashValue.equalsIgnoreCase("") ){
										sheet
										.getRow((short) rowCount)
										.getCell((short) 4)
										.setCellValue(dolPercentHashValue +", "+
												contractMapping
															.getEb08()
															.getHippaCodePossibleValues()
															.get(0).toString()
															+ DomainConstants.PERCENTAGE);
									}else{
									sheet
											.getRow((short) rowCount)
											.getCell((short) 4)
											.setCellValue(
													contractMapping
															.getEb08()
															.getHippaCodePossibleValues()
															.get(0).toString()
															+ DomainConstants.PERCENTAGE);
								}
									
									
									/*sheet
											.getRow((short) rowCount)
											.getCell((short) 4)
											.setCellValue(
													contractMapping
															.getEb08()
															.getHippaCodePossibleValues()
															.get(0).toString()
															+ DomainConstants.PERCENTAGE);*/
								}
							}
							if (contractMapping.getEb10() != null
									&& contractMapping.getEb10()
											.getHippaCodePossibleValues()
											.size() > 0) {
								String dolPercentHashValue = sheet
								.getRow((short) rowCount)
								.getCell((short) 4)
								.getStringCellValue();
								String EB_09 = "";
								if (contractMapping.getEb09() != null 
										&& contractMapping.getEb09()
										.getHippaCodePossibleValues()
										.size() > 0) {
									String val = "";
									val = (String)contractMapping.getEb09()
									.getHippaCodePossibleValues().get(0);
									
									EB_09 = X12ParserUtil.getX12Description("EB09_"+val.trim());
									EB_09 = " ("+EB_09+")";
							}
								if(null != dolPercentHashValue && !dolPercentHashValue.equalsIgnoreCase("") ){
								sheet.getRow((short) rowCount).getCell(
										(short) 4).setCellValue( dolPercentHashValue+", "+
										contractMapping.getEb10()
												.getHippaCodePossibleValues()
												.get(0).toString()+EB_09);
								}else{
									sheet.getRow((short) rowCount).getCell(
											(short) 4).setCellValue(
											contractMapping.getEb10()
													.getHippaCodePossibleValues()
													.get(0).toString());
									
								}
								/*dolPercentHashValue = sheet
								.getRow((short) rowCount)
								.getCell((short) 4)
								.getStringCellValue();
								sheet.getRow((short) rowCount).getCell(
										(short) 4).setCellValue(dolPercentHashValue+" "+EB_09
										);*/
							
						}}
						/*sheet.createRow((short) rowCount).createCell((short) 5)
								.setCellStyle(cellstyle);
						if (ebOtherBuffer.toString() != null) {
							sheet.getRow((short) rowCount).getCell((short) 5)
									.setCellValue(ebOtherBuffer.toString());
						}*/
						
						
						//to insert here.....
						
						sheet.createRow((short) rowCount).createCell((short) 5)
						.setCellStyle(style);
						if (mapping.getEb02() != null 
								&& mapping.getEb02()
								.getHippaCodePossibleValues()
								.size() > 0) {
							String val = (String) mapping.getEb02()
							.getHippaCodePossibleValues().get(0);
							
							String EB_02 = "";
							EB_02 = X12ParserUtil.getX12Description("EB02_"+val.trim());
								
							
							sheet.getRow((short) rowCount).getCell((short) 5)
									.setCellValue(EB_02);
						}
						
						sheet.createRow((short) rowCount).createCell((short) 6)
						.setCellStyle(style);
						if (mapping.getEb06() != null 
								&& mapping.getEb06()
								.getHippaCodePossibleValues()
								.size() > 0) {
							String val = (String)mapping.getEb06()
							.getHippaCodePossibleValues().get(0);
							
							String EB_06 = "";
							EB_06 = X12ParserUtil.getX12Description("EB06_"+val.trim());

							
							sheet.getRow((short) rowCount).getCell((short) 6)
									.setCellValue(EB_06);
						}
						
						sheet.createRow((short) rowCount).createCell((short) 7)
						.setCellStyle(style);
						if(exists){
							if (contractMapping.getEb09() != null 
									&& contractMapping.getEb09()
									.getHippaCodePossibleValues()
									.size() > 0) {
								String val = "";
								val = (String)contractMapping.getEb09()
								.getHippaCodePossibleValues().get(0);
								
								String EB_09 = "";
								
								EB_09 = X12ParserUtil.getX12Description("EB09_"+val.trim());
								
								sheet.getRow((short) rowCount).getCell((short) 7)
										.setCellValue(EB_09);
							}
							
						}
						
						sheet.createRow((short) rowCount).createCell((short) 8)
						.setCellStyle(style);
							if (!StringUtils.isBlank(EB11Value)) {
								sheet.getRow((short) rowCount).getCell((short) 8)
										.setCellValue(EB11Value);
							
						}
						
						//new colummnd end here..
						sheet.createRow((short) rowCount).createCell((short) 9)
						.setCellStyle(style);
				      if (mapping.getHsd01() != null
						&& mapping.getHsd01()
								.getHippaCodePossibleValues().size() > 0) {
				    	  
				    	  String val = "";
							val = (String)mapping.getHsd01()
							.getHippaCodePossibleValues().get(0);
							
							String HSD_01 = "";
							
							HSD_01 = X12ParserUtil.getX12Description("HSD01_"+val.trim());
				    	  
					       sheet.getRow((short) rowCount)
							.getCell((short) 9)
							.setCellValue(
									HSD_01);
		                        }
						
						
				
				       sheet.createRow((short) rowCount).createCell((short) 10)
				       .setCellStyle(style);
		               if (mapping.getHsd02() != null
				         && mapping.getHsd02()
						.getHippaCodePossibleValues().size() > 0) {
			         sheet
					.getRow((short) rowCount)
					.getCell((short) 10)
					.setCellValue(
				            mapping.getHsd02()
							.getHippaCodePossibleValues()
							.get(0).toString());
		                   }
				
						
		           sheet.createRow((short) rowCount).createCell((short) 11)
		                 .setCellStyle(style);
                     if (mapping.getHsd03() != null
		              && mapping.getHsd03()
				      .getHippaCodePossibleValues().size() > 0) {
                    	 String val = "";
                    	 val = (String) mapping.getHsd03()
   				      .getHippaCodePossibleValues().get(0);
                    	
                    	String HSD_03 = "";
                    	HSD_03 = X12ParserUtil.getX12Description("HSD03_"+val.trim());
                    	 
	                  sheet
			          .getRow((short) rowCount)
			          .getCell((short) 11)
			          .setCellValue(
					   HSD_03);
                            }
					
                    sheet.createRow((short) rowCount).createCell((short) 12)
                           .setCellStyle(style);
                       if (mapping.getHsd04() != null
                          && mapping.getHsd04()
		                   .getHippaCodePossibleValues().size() > 0) {
                            sheet
	                        .getRow((short) rowCount)
	                        .getCell((short) 12)
	                        .setCellValue(
                             mapping.getHsd04()
			                 .getHippaCodePossibleValues()
			                 .get(0).toString());
                           }					
						
                      sheet.createRow((short) rowCount).createCell((short) 13)
                      .setCellStyle(style);
                       if (mapping.getHsd05() != null
                       && mapping.getHsd05()
		                .getHippaCodePossibleValues().size() > 0) {
                    	   String val = "";
                    	   val = (String)mapping.getHsd05()
   		                .getHippaCodePossibleValues().get(0);
                    	   
                    	   String HSD_05 = "";
                    	   HSD_05 = X12ParserUtil.getX12Description("HSD05_"+val.trim());
                    	   
                         sheet
	                    .getRow((short) rowCount)
	                    .getCell((short) 13)
	                    .setCellValue(
			            HSD_05);
		
			             }
          //BXNI Nov       
                        
				       sheet.createRow((short) rowCount).createCell((short) 14)
				       .setCellStyle(style);
		               if (mapping.getHsd06() != null
				         && mapping.getHsd06()
						.getHippaCodePossibleValues().size() > 0) {
			         sheet
					.getRow((short) rowCount)
					.getCell((short) 14)
					.setCellValue(
				            mapping.getHsd06()
							.getHippaCodePossibleValues()
							.get(0).toString());
		                   }
                    
                        sheet.createRow((short) rowCount).createCell((short) 15)
                       .setCellStyle(style);
                        if (mapping.getHsd07() != null
                        && mapping.getHsd07()
 		                .getHippaCodePossibleValues().size() > 0) {
                        	String val = "";
                        	val = (String) mapping.getHsd07()
     		                .getHippaCodePossibleValues().get(0);
                        	String HSD_07 = "";
                        	HSD_07 = X12ParserUtil.getX12Description("HSD07_"+val.trim());
                          sheet
 	                    .getRow((short) rowCount)
 	                    .getCell((short) 15)
 	                    .setCellValue(
 	                    		HSD_07);
 		
 			             }
                        
                        sheet.createRow((short) rowCount).createCell((short) 16)
                        .setCellStyle(style);
                         if (mapping.getHsd08() != null
                         && mapping.getHsd08()
  		                .getHippaCodePossibleValues().size() > 0) {
                        	 String val = "";
                         	val = (String) mapping.getHsd08()
      		                .getHippaCodePossibleValues().get(0);
                         	String HSD_08 = "";
                         	HSD_08 = X12ParserUtil.getX12Description("HSD08_"+val.trim());
                           sheet
  	                    .getRow((short) rowCount)
  	                    .getCell((short) 16)
  	                    .setCellValue(
  			            HSD_08);
  		
  			             }
                         //BXNI NOV end
                       /**
                        * code started for Start Date and End Date Display for November Release.
                        */
                       sheet.createRow((short) rowCount).createCell((short) 17)
						.setCellStyle(style);
                       if (contractMapping.getBenefitEffectiveStartDate() != null 
								&& contractMapping.getBenefitEffectiveStartDate()
								.getHippaCodePossibleValues()
								.size() > 0) {
                    	   Date date = null;
                    	   Calendar c1 = Calendar.getInstance();
                    	   try {
								date = formatter.parse(contractMapping
										.getBenefitEffectiveStartDate()
										.getHippaCodePossibleValues()
										.get(0).toString());
								c1.setTime(date);
								sheet.getRow((short) rowCount).getCell((short) 17)
								.setCellValue(dateFormatter.format(
										c1.getTime())
										.toString());
							} catch (ParseException e) {
								log.debug("ParseException Caught " + contractMapping
										.getBenefitEffectiveStartDate()
										.getHippaCodePossibleValues()
										.get(0).toString());
								sheet.getRow((short) rowCount).getCell((short) 17)
								.setCellValue(contractMapping
										.getBenefitEffectiveStartDate()
										.getHippaCodePossibleValues()
										.get(0).toString());
							}
						}
                       
                       sheet.createRow((short) rowCount).createCell((short) 18)
						.setCellStyle(style);
                      if (contractMapping.getBenefitEffectiveStartDate() != null 
								&& contractMapping.getBenefitEffectiveEndDate()
								.getHippaCodePossibleValues()
								.size() > 0) {
                       Date date = null;
                   	   Calendar c1 = Calendar.getInstance();
                   	   try {
								date = formatter.parse(contractMapping
										.getBenefitEffectiveEndDate()
										.getHippaCodePossibleValues()
										.get(0).toString());
								c1.setTime(date);
								sheet.getRow((short) rowCount).getCell((short) 18)
								.setCellValue(dateFormatter.format(
										c1.getTime())
										.toString());
							} catch (ParseException e) {
								log.debug("ParseException Caught " + contractMapping
										.getBenefitEffectiveStartDate()
										.getHippaCodePossibleValues()
										.get(0).toString());
								sheet.getRow((short) rowCount).getCell((short) 18)
								.setCellValue(contractMapping
										.getBenefitEffectiveEndDate()
										.getHippaCodePossibleValues()
										.get(0).toString());
							}
						}
                      /**
                       * code ended for Start Date and End Date Display for November Release.
                       */
						sheet.createRow((short) rowCount).createCell((short) 19)
								.setCellStyle(style);
						if (mapping.getIi02() != null
								&& mapping.getIi02()
										.getHippaCodePossibleValues().size() > 0) {
							String val = "";
							val = (String) mapping.getIi02()
							.getHippaCodePossibleValues().get(0);
							String III02 = "";
							III02 = X12ParserUtil.getX12Description("III02_"+val.trim());
							
							sheet
									.getRow((short) rowCount)
									.getCell((short) 19)
									.setCellValue(III02);
						}
						sheet.createRow((short) rowCount).createCell((short) 20)
						.setCellStyle(style);	
						int msgStringSize = 256 * 65;
						sheet.setColumnWidth((short) 20, (short) msgStringSize);
						//sheet.createRow((short) rowCount).createCell((short) 20)
						//		.setCellStyle(style);
						if (!StringUtils.isBlank(mapping.getMessage()) && null != mapping.getMessage()) {
							String msgString = mapping.getMessage();
							msgString = msgString.replaceAll("~", "~\n");
							msgString.trim();
							msgString = StringUtils.chomp(msgString);
							String updatedMessageString = StringUtils.replace(msgString.toUpperCase(),"MSG*","");
							updatedMessageString = updatedMessageString.trim();
							sheet.getRow((short) rowCount).getCell((short) 20).getCellStyle().setWrapText(true);
							sheet.getRow((short) rowCount).getCell((short) 20)
									.setCellValue(updatedMessageString);
						}
						sheet.createRow((short) rowCount).createCell((short) 21)
								.setCellStyle(style);
						if (mapping.getIi02() != null
								&& mapping.getIi02()
										.getHippaCodePossibleValues().size() > 0
								&& !StringUtils.isBlank(mapping.getIi02()
										.getHippaCodePossibleValues().get(0)
										.toString())) {
							sheet
									.getRow((short) rowCount)
									.getCell((short) 21)
									.setCellValue(
											DomainConstants.III
													+ DomainConstants.ZZ
													+ mapping
															.getIi02()
															.getHippaCodePossibleValues()
															.get(0)
													+ DomainConstants.TILDA_CHAR);
						}
						sheet.createRow((short) rowCount).createCell((short) 22)
								.setCellStyle(style);
						
						// 27x Transaction Changes
						sheet.createRow((short) rowCount).createCell((short) 23).setCellStyle(style);
						sheet.getRow((short) rowCount).getCell((short) 23).getCellStyle().setWrapText(true);
						if (null != mapping.getNm1MessageSegment() &&
								mapping.getNm1MessageSegment().getHippaCodePossibleValues().size() > 0) {
							String nm1MessageSegment = mapping.getNm1MessageSegment().getHippaCodePossibleValues().get(0).toString();
							sheet.getRow((short) rowCount).getCell((short) 23).setCellValue(nm1MessageSegment);
						}
						
					}
				}
			}
		}
		log.debug("Exiting Method generate27xBenefitAccumExcel");
	}

	private static String constructX12forHSD(Mapping mapping) {
		String hsdString = "HSD";
		Map<Integer,String> hsdMap = new HashMap<Integer, String>();
		int highestHsdPresent = 0;
		if (mapping.getHsd01() != null
				&& mapping.getHsd01().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 1;
			hsdMap.put(1, mapping.getHsd01().getHippaCodePossibleValues()
					.get(0).toString());
		}

		if (mapping.getHsd02() != null
				&& mapping.getHsd02().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 2;
			hsdMap.put(2, mapping.getHsd02().getHippaCodePossibleValues().get(0)
					.toString());

		}

		if (mapping.getHsd03() != null
				&& mapping.getHsd03().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 3;
			hsdMap.put(3, mapping.getHsd03().getHippaCodePossibleValues().get(0)
					.toString());
		}
		if (mapping.getHsd04() != null
				&& mapping.getHsd04().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 4;
			hsdMap.put(4, mapping.getHsd04().getHippaCodePossibleValues().get(0)
					.toString());
		}

		if (mapping.getHsd05() != null
				&& mapping.getHsd05().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 5;
			hsdMap.put(5, mapping.getHsd05().getHippaCodePossibleValues().get(0)
					.toString());
		}
		if (mapping.getHsd06() != null
				&& mapping.getHsd06().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 6;
			hsdMap.put(6, mapping.getHsd06().getHippaCodePossibleValues().get(0)
					.toString());
		}
		if (mapping.getHsd07() != null
				&& mapping.getHsd07().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 7;
			hsdMap.put(7, mapping.getHsd07().getHippaCodePossibleValues().get(0)
					.toString());
		}
		if (mapping.getHsd08() != null
				&& mapping.getHsd08().getHippaCodePossibleValues().size() > 0) {
			highestHsdPresent = 8;
			hsdMap.put(8, mapping.getHsd08().getHippaCodePossibleValues().get(0)
					.toString());
		}
		if (highestHsdPresent > 0) {
			String val;
			for (int i= 1; i<= highestHsdPresent ; i++) {
				val = "";
				if (null != hsdMap.get(i) && !hsdMap.get(i).trim().isEmpty()) {
					val = hsdMap.get(i);
				}
				hsdString = hsdString + "*"+ val;
			}
			hsdString = hsdString + "~";
		}
		else {
			hsdString = "";
		}
		return hsdString;
	}

	/**
	 * generate27xHIPAABXExcel generates excel for the X12 Request.
	 * 
	 * @param formattedResponse
	 * @param workbook
	 */
	public static void generate27xHIPAABXExcel(String formattedResponse,
			HSSFWorkbook workbook) {
		log.debug("Entering Method generate27xHIPAABXExcel");
		// initializing variables
		boolean isExcel = false;
		boolean dtpExists = false;
		boolean toExists = false;
		int i = 0;
		String msgBuffer = "";
		int rowCount = 7;
		log.debug("Getting the static Data");
		HSSFSheet sheet = generateStaticData(workbook, isExcel,
				formattedResponse);
		log.debug("Getting the String one by one");
		StringTokenizer st = new StringTokenizer(formattedResponse,
				DomainConstants.TILDA_CHAR, false);
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		Date date = null;
		// Iterating the String, and setting the values in the excel
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			if (key.trim().startsWith(DomainConstants.NM1)) {
				String[] splitEbKey = key.trim().split(
						"\\" + DomainConstants.STAR_CHAR);
				String name = "";
				String subscriberId = "";
				for (int k = 0; k < splitEbKey.length; k++) {
					if (k == 3) {
						name = splitEbKey[k] + " ";
					}
					if (k == 4) {
						name = name + splitEbKey[k] + " ";
					}
					if (k == 5) {
						name = name + splitEbKey[k] + " ";
					}
					if (k == 9) {
						subscriberId = splitEbKey[k] + " ";
					}
					sheet.getRow((short) 2).createCell((short) 1).setCellValue(
							name);
					sheet.getRow((short) 2).getCell((short) 1).setCellStyle(
							getStyle(workbook, HSSFColor.GREY_25_PERCENT.index,
									HSSFFont.U_SINGLE));
					sheet.getRow((short) 3).createCell((short) 1).setCellValue(
							subscriberId);
					sheet.getRow((short) 3).getCell((short) 1).setCellStyle(
							getStyle(workbook, HSSFColor.GREY_25_PERCENT.index,
									HSSFFont.U_SINGLE));
				}
			}
			if (key.trim().startsWith(DomainConstants.NM2)) {
				String[] splitEbKey = key.trim().split(
						"\\" + DomainConstants.STAR_CHAR);
				String name = "";
				for (int k = 0; k < splitEbKey.length; k++) {
					if (k == 3) {
						name = splitEbKey[k] + " ";
					}
					if (k == 4) {
						name = name + splitEbKey[k] + " ";
					}
					if (k == 5) {
						name = name + splitEbKey[k] + " ";
					}
					sheet.getRow((short) 4).createCell((short) 1).setCellValue(
							name);
					sheet.getRow((short) 4).getCell((short) 1).setCellStyle(
							getStyle(workbook, HSSFColor.GREY_25_PERCENT.index,
									HSSFFont.U_SINGLE));
				}
			}
			if (key.trim().startsWith(DomainConstants.DMG)) {
				String[] splitEbKey = key.trim().split(
						"\\" + DomainConstants.STAR_CHAR);
				String dob = "";
				String gender = "";
				for (int k = 0; k < splitEbKey.length; k++) {
					if (k == 2) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							dob = splitEbKey[k];
							try {
								date = formatter.parse(dob);
								c1.setTime(date);
								sheet.getRow((short) 2).createCell((short) 3)
										.setCellValue(
												dateFormatter.format(
														c1.getTime())
														.toString());
							} catch (ParseException e) {
								log.debug("ParseException Caught " + dob);
								sheet.getRow((short) 2).createCell((short) 3)
										.setCellValue(dob);
							}
						}
					}
					if (k == 3) {
						gender = splitEbKey[k] + " ";
					}

					HSSFCellStyle style = getStyle(workbook,
							HSSFColor.GREY_25_PERCENT.index, HSSFFont.U_SINGLE);
					sheet.getRow((short) 2).getCell((short) 3).setCellStyle(
							style);
					sheet.getRow((short) 2).createCell((short) 5).setCellValue(
							gender);
					sheet.getRow((short) 2).getCell((short) 5).setCellStyle(
							getStyle(workbook, HSSFColor.GREY_25_PERCENT.index,
									HSSFFont.U_SINGLE));
				}
			}
			if (key.trim().startsWith(DomainConstants.DTP) && !dtpExists) {
				String[] splitEbKey = key.trim().split(
						"\\" + DomainConstants.STAR_CHAR);
				String eligibility = "";
				StringBuffer dateBuffer = new StringBuffer();
				for (int k = 0; k < splitEbKey.length; k++) {
					if (k == 3) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							StringTokenizer eligibilityST = new StringTokenizer(
									splitEbKey[k], DomainConstants.HYPHEN,
									false);
							while (eligibilityST.hasMoreTokens()) {
								eligibility = eligibilityST.nextToken();
								try {
									date = formatter.parse(eligibility);
									c1.setTime(date);
									dateBuffer.append(dateFormatter.format(
											c1.getTime()).toString());
									if (!toExists) {
										dateBuffer.append(DomainConstants.TO);
										toExists = true;
									}
								} catch (ParseException e) {
									log.debug("Parse Exception Caught");
								}
							}
							if (dateBuffer.toString().length() > 0) {
								sheet.getRow((short) 2).createCell((short) 7)
										.setCellValue(dateBuffer.toString());
							}
						}
					}
				}
				sheet.getRow((short) 2).getCell((short) 7).setCellStyle(
						getStyle(workbook, HSSFColor.GREY_25_PERCENT.index,
								HSSFFont.U_SINGLE));
				dtpExists = true;
			}
			if (key.trim().startsWith(DomainConstants.III)) {
				if (!StringUtils.isBlank(key)) {
					String[] splitIIKey = key.trim().split(
							"\\" + DomainConstants.STAR_CHAR);
					for (int k = 0; k < splitIIKey.length; k++) {
						sheet.createRow((short) rowCount - 1).createCell(
								(short) 16).setCellValue(
								(String) EBConstants.III02map
										.get(splitIIKey[k]));
					}
					sheet.createRow((short) rowCount - 1).createCell((short) 18)
							.setCellValue(
									key.trim() + DomainConstants.TILDA_CHAR);
				}
			}
			/*if (key.trim().startsWith(DomainConstants.MSG) && !msgExists) {
				msgExists = true;
				sheet.createRow((short) rowCount - 1).createCell((short) 16)
						.setCellValue(key.trim() + DomainConstants.TILDA_CHAR);
			}*/
			if (key.trim().startsWith(DomainConstants.MSG)) {
				msgBuffer = msgBuffer+key.trim()+ DomainConstants.TILDA_CHAR;
				sheet.createRow((short) rowCount - 1).createCell((short) 17)
						.setCellValue(msgBuffer);
			}
			 if (key.trim().startsWith(DomainConstants.HSD)) {
					
					String[] splitHsdKey = key.trim().split(
							"\\" + DomainConstants.STAR_CHAR);
				
					
					sheet.createRow((short) rowCount).createCell((short) 0)
							.setCellValue(key.trim() + DomainConstants.TILDA_CHAR);
					for (int j = 0; j < splitHsdKey.length; j++) {
						
						if (j == 1) {
							if (!StringUtils.isBlank(splitHsdKey[j])) {
								sheet.createRow((short) rowCount).createCell((short) 11)
								.setCellValue( (String) EBConstants.HSD01map
										.get(splitHsdKey[j]));
							}
						}
						else if (j == 2) {
							if (!StringUtils.isBlank(splitHsdKey[j])) {
								sheet.createRow((short) rowCount).createCell((short) 12)
								.setCellValue(splitHsdKey[j]);
							}
						}
						else if (j == 3) {
							if (!StringUtils.isBlank(splitHsdKey[j])) {
								sheet.createRow((short) rowCount).createCell((short) 13)
								.setCellValue( (String) EBConstants.HSD03map
										.get(splitHsdKey[j]));
							}
						}
						
						else if (j == 4) {
							if (!StringUtils.isBlank(splitHsdKey[j])) {
								sheet.createRow((short) rowCount).createCell((short) 14)
								.setCellValue(splitHsdKey[j]);
							}
						}
						else if (j == 5) {
							if (!StringUtils.isBlank(splitHsdKey[j])) {
								sheet.createRow((short) rowCount).createCell((short) 15)
								.setCellValue( (String) EBConstants.HSD05map
										.get(splitHsdKey[j]));
							}
						}
				
					}
				}
				
			if (key.trim().startsWith(DomainConstants.EB)) {
				//msgExists = false;
				msgBuffer="";
				i = i + 1;
				float percent = 0;
				String[] splitEbKey = key.trim().split(
						"\\" + DomainConstants.STAR_CHAR);
				sheet.createRow((short) rowCount).createCell((short) 0)
						.setCellValue(key.trim() + DomainConstants.TILDA_CHAR);
				for (int k = 0; k < splitEbKey.length; k++) {
					if (k == 2) {
						
						if (!StringUtils.isBlank(splitEbKey[k])) {
							/*buffer.append(EBConstants.EB02map
									.get(splitEbKey[k])
									+ ",");*/
							sheet.createRow((short) rowCount).createCell((short) 5)
							.setCellValue((String)EBConstants.EB02map
									.get(splitEbKey[k]));
						}
					}
					
					if (k == 4) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							/*buffer.append(EBConstants.EB04map
									.get(splitEbKey[k])
									+ ",");*/
							sheet.createRow((short) rowCount).createCell((short) 6)
							.setCellValue((String)EBConstants.EB04map
									.get(splitEbKey[k]));
						}
					}
					if (k == 5) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							/*buffer.append(splitEbKey[k] + ",");*/
							sheet.createRow((short) rowCount).createCell((short) 7)
							.setCellValue((String)splitEbKey[k]);
						}
					}
					
					if (k == 6) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							/*buffer.append(EBConstants.EB06map
									.get(splitEbKey[k])
									+ ",");*/
							sheet.createRow((short) rowCount).createCell((short) 8)
							.setCellValue((String)EBConstants.EB06map
									.get(splitEbKey[k]));
						}
					}
					if (k == 9) {
						/*if (!StringUtils.isBlank((splitEbKey[k]))) {
							buffer.append(EBConstants.EB09map
									.get(splitEbKey[k])
									+ ",");*/
							sheet.createRow((short) rowCount).createCell((short) 9)
							.setCellValue((String)EBConstants.EB09map
									.get(splitEbKey[k]));
					}	
					if (k == 11) {
						/*if (!StringUtils.isBlank((splitEbKey[k]))) {
							buffer.append(EBConstants.EB09map
									.get(splitEbKey[k])
									+ ",");*/
							sheet.createRow((short) rowCount).createCell((short) 10)
							.setCellValue((String)splitEbKey[k]);
					}	
					if (k == 3) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							sheet.createRow((short) rowCount).createCell(
									(short) 1).setCellValue(
									(String) EBConstants.EB03map
											.get(splitEbKey[k]));
						}
					}
					if (k == 1) {
						if (!StringUtils.isBlank(splitEbKey[k])) {
							sheet.createRow((short) rowCount).createCell(
									(short) 2).setCellValue(
									(String) EBConstants.EB01map
											.get(splitEbKey[k]));
						}

					}
					if (k == 12) {
						if (splitEbKey[k].equals(DomainConstants.Y)) {
							sheet.createRow((short) rowCount).createCell(
									(short) 3).setCellValue(
									DomainConstants.IN_NETWORK);
						} else if (splitEbKey[k].equals(DomainConstants.N)) {
							sheet.createRow((short) rowCount).createCell(
									(short) 3).setCellValue(
									DomainConstants.OUT_OF_NETWORK);
						}
						//SSCR15637
						else if (splitEbKey[k].equals(DomainConstants.W)) {
							sheet.createRow((short) rowCount).createCell(
									(short) 3).setCellValue(
									DomainConstants.NOT_APPLICABLE);
						}
					}
					if (k == 7) {
						if (!StringUtils.isBlank((splitEbKey[k]))) {
							sheet.createRow((short) rowCount).createCell(
									(short) 4).setCellValue(
									DomainConstants.DOLLAR + splitEbKey[k]);
						}
					}
					if (k == 8) {
						if (!StringUtils.isBlank((splitEbKey[k]))) {
							String percentValue = "";
							String dolPercentHashValue = sheet
							.getRow((short) rowCount)
							.getCell((short) 4)
							.getStringCellValue();
							try {
								percent = Float.parseFloat(splitEbKey[k]);
								percent = percent * 100;
								percentValue = (int) percent
								+ DomainConstants.PERCENTAGE;
							} catch (NumberFormatException e) {
								log.debug("NumberFormatException Caught for : "
										+ splitEbKey[k]);
								percentValue = splitEbKey[k];
								sheet.createRow((short) rowCount).createCell(
										(short) 4).setCellValue(splitEbKey[k]);
							}
							finally{
								if(null != dolPercentHashValue && !dolPercentHashValue.equalsIgnoreCase("")){
								sheet.createRow((short) rowCount).createCell(
										(short) 4).setCellValue(dolPercentHashValue +", "+
												percentValue);
							}
							else{
								sheet.createRow((short) rowCount).createCell(
										(short) 4).setCellValue(
												percentValue);
							}
							}
						}
					}
					if (k == 10) {
						if (!StringUtils.isBlank((splitEbKey[k]))) {
							String dolPercentHashValue = sheet
							.getRow((short) rowCount)
							.getCell((short) 4)
							.getStringCellValue();
							String EB09Desc = "";
							EB09Desc = (String)EBConstants.EB09map
							.get(splitEbKey[k]);
							EB09Desc = " ("+EB09Desc+")";
							if(null != dolPercentHashValue && !dolPercentHashValue.equalsIgnoreCase("")){
								sheet.createRow((short) rowCount).createCell(
										(short) 4).setCellValue(dolPercentHashValue +", "+splitEbKey[k]+EB09Desc);
							}else{
								sheet.createRow((short) rowCount).createCell(
										(short) 4).setCellValue(splitEbKey[k]);
							}
							
						}
					}
					
					/*sheet.createRow((short) rowCount).createCell((short) 5)
							.setCellValue(buffer.toString());*/
				}
				rowCount++;
			}
		}
		log.debug("Exiting Method generate27xHIPAABXExcel");
	}

	/**
	 * Generates Excel, based on the system Type.
	 * 
	 * @param contract
	 * @param workbook
	 */
	public static void generateErrorReport(List contractList,
			HSSFWorkbook workbook, String backEndRegion) {
		for (int i = 0; i < contractList.size(); i++) {
			ContractVO contract = (ContractVO) contractList.get(i);
			if (DomainConstants.SYSTEM_LG
					.equalsIgnoreCase(contract.getSystem())) {
				HSSFSheet sheet = workbook.createSheet();
				setReportProperties(sheet);
				createWPDReportHeader(contract, workbook, sheet);
				populateWPDContractValues(contract, sheet, backEndRegion);
			} else if (DomainConstants.SYSTEM_ISG.equalsIgnoreCase(contract
					.getSystem())) {
				HSSFSheet sheet = workbook.createSheet();
				Calendar c1 = Calendar.getInstance(); // today
				Date date = null;
				SimpleDateFormat dateFormatter = new SimpleDateFormat(
						DomainConstants.DATE_FORMAT_2);
				SimpleDateFormat formatter = new SimpleDateFormat(
						DomainConstants.DATE_FORMAT);
				try {
					if (null != contract.getRevisionDate()) {
						date = formatter.parse(contract.getRevisionDate());
						c1.setTime(date);
						workbook.setSheetName(i, dateFormatter.format(
								c1.getTime()).toString());
					}
				} catch (ParseException e) {
					log.debug("Caught Date ParseException");
				} catch (Exception e) {
					log.debug("Caught Exception ");
				}
				setReportProperties(sheet);
				createWPDReportHeader(contract, workbook, sheet);
				populateWPDContractValues(contract, sheet,backEndRegion);
			} else if (DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(contract
					.getSystem())) {
				HSSFSheet sheet = workbook.createSheet();
				setReportProperties(sheet);
				createEWPDReportHeader(workbook, sheet);
				populateEWPDContractValues(contract, sheet, backEndRegion);
			}
		}

	}

	private static void setReportProperties(HSSFSheet sheet) {
		sheet.setAutobreaks(true);
		sheet.setDefaultColumnWidth((short) 11);
		// sheet.autoSizeColumn((short)1); //adjust width of the first column
		// sheet.autoSizeColumn(1);
		sheet.getPrintSetup().setFitHeight((short) 1);
		sheet.getPrintSetup().setFitWidth((short) 1);
	}

	private static void createWPDReportHeader(ContractVO contract,
			HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFCellStyle cs = workbook.createCellStyle();
		cs.setWrapText(true);
		HSSFCell cell = sheet.createRow((short) 2).createCell((short) 0);
		cell.setCellStyle(cs);

		sheet.createRow((short) 1).createCell((short) 0).setCellValue(
				DomainConstants.CONTRACT_ID);
		if (DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())) {
			sheet.createRow((short) 2).createCell((short) 0).setCellValue(
					DomainConstants.BUSINESS_ENTITY);
			sheet.createRow((short) 3).createCell((short) 0).setCellValue(
					DomainConstants.STATE_HQ);
		} else {
			sheet.createRow((short) 2).createCell((short) 0).setCellValue(
					DomainConstants.MBU);
			sheet.createRow((short) 3).createCell((short) 0).setCellValue(
					DomainConstants.GROUPSTATE_HQ);
			sheet.createRow((short) 4).createCell((short) 2).setCellValue(
					DomainConstants.REVISION_DATE);
			sheet.createRow((short) 4).createCell((short) 3).setCellValue(
					contract.getRevisionDate());
		}
		sheet.createRow((short) 4).createCell((short) 0).setCellValue(
				DomainConstants.DATE_SEGMENT);
		sheet.createRow((short) 6).createCell((short) 0).setCellValue(
				DomainConstants.MAJORHEADING);
		sheet.createRow((short) 6).createCell((short) 1).setCellValue(
				DomainConstants.MINORHEADING);
		sheet.createRow((short) 6).createCell((short) 2).setCellValue(
				DomainConstants.VARIABLE);
		sheet.createRow((short) 6).createCell((short) 3).setCellValue(
				DomainConstants.DESCRIPTION);
		sheet.createRow((short) 6).createCell((short) 4).setCellValue(
				DomainConstants.PVA);
		sheet.createRow((short) 6).createCell((short) 5).setCellValue(
				DomainConstants.FORMAT_N);
		sheet.createRow((short) 6).createCell((short) 6).setCellValue(
				DomainConstants.VALUE_CODED);
		sheet.createRow((short) 6).createCell((short) 7).setCellValue(
				DomainConstants.EB01_NAME);
		sheet.createRow((short) 6).createCell((short) 8).setCellValue(
				DomainConstants.EB02_NAME);
		sheet.createRow((short) 6).createCell((short) 9).setCellValue(
				DomainConstants.EB03_NAME);
		sheet.createRow((short) 6).createCell((short) 10).setCellValue(
				DomainConstants.EB06_NAME);
		sheet.createRow((short) 6).createCell((short) 11).setCellValue(
				DomainConstants.EB09_NAME);
		sheet.createRow((short) 6).createCell((short) 12).setCellValue(
				DomainConstants.START_AGE);
		sheet.createRow((short) 6).createCell((short) 13).setCellValue(
				DomainConstants.END_AGE);
		sheet.createRow((short) 6).createCell((short) 14).setCellValue(
				DomainConstants.ERROR);
		sheet.createRow((short) 6).createCell((short) 15).setCellValue(
				DomainConstants.ERROR_DESCRIPTION);
		// BXNI CR35 CHANGES- Added a new field WPDAccumulator
		sheet.createRow((short) 6).createCell((short) 16).setCellValue(
				DomainConstants.WPD_ACCUMULATOR);   
	}

	/**
	 * Method Generates the report for contract and variable level errors for a
	 * particular contract
	 * 
	 * @param contract
	 * @param sheet
	 */
	private static void populateWPDContractValues(ContractVO contract,
			HSSFSheet sheet, String backEndRegion) {

		int eb03Count;
		String eb03;
		int rowCount = 6;
		int accumCount;
		String accum;
		boolean mQValidationExists = false;
		boolean contractLevelMappingError = false;
		String mQValidationSuccessMessage = "";
		sheet.createRow((short) 1).createCell((short) 1).setCellValue(
				contract.getContractId());
		if (DomainConstants.SYSTEM_LG.equalsIgnoreCase(contract.getSystem())) {
			sheet.createRow((short) 2).createCell((short) 1).setCellValue(
					contract.getBusinessEntity());
			sheet.createRow((short) 3).createCell((short) 1).setCellValue(
					contract.getGroupStateHQ());
		} else {
			sheet.createRow((short) 2).createCell((short) 1).setCellValue(
					contract.getBusinessEntity());
			sheet.createRow((short) 3).createCell((short) 1).setCellValue(
					contract.getGroupStateHQ());
		}
		sheet.createRow((short) 4).createCell((short) 1).setCellValue(
				contract.getEffectiveDate() + "-" + contract.getExpiryDate());
		// contractMappingVOList contains contract level errors with mapping.
		if (null != contract.getContractMappingVOList()) {
			contractLevelMappingError = true;
		}
		// contractErrCodeList contains contract level errors
		List contractErrCodeList = contract.getErrorCodesList();
		if (contractErrCodeList != null) {
			Iterator contractErrCodeListIterator = contractErrCodeList
					.iterator();
			if (contractErrCodeListIterator != null) {
				while (contractErrCodeListIterator.hasNext()) {
					ErrorDetailVO errorContract = (ErrorDetailVO) contractErrCodeListIterator
							.next();
					if (errorContract.getErrorCode().equals(
							DomainConstants.ERROR_MQ)) {
						mQValidationExists = true;
						mQValidationSuccessMessage = errorContract
								.getErrorDesc();
					} else {
						rowCount++;
						sheet.createRow((short) rowCount)
								.createCell((short) 14).setCellValue(
										errorContract.getErrorCode());
						sheet.createRow((short) rowCount)
								.createCell((short) 15).setCellValue(
										errorContract.getErrorDesc());
					}
				}
			}
		}

		if (!mQValidationExists || contractLevelMappingError) {
			StringBuffer commonErrorMessage = new StringBuffer();
			List contractMappingErrCodeList = contract
					.getContractMappingVOList();
			if (contractMappingErrCodeList != null) {
				Iterator contractMappingErrCodeListIterator = contractMappingErrCodeList
						.iterator();
				if (contractMappingErrCodeListIterator != null) {
					while (contractMappingErrCodeListIterator.hasNext()) {
						rowCount++;
						ContractMappingVO contractMappingVO = (ContractMappingVO) contractMappingErrCodeListIterator
								.next();
						if (null != contractMappingVO) {
							List errorList = contractMappingVO
									.getErrorCodesList();
							if (null != errorList && errorList.size() > 0) {
								// Get the first element of the error
								ErrorDetailVO detailVO = (ErrorDetailVO) errorList
										.get(0);
								if (null != detailVO) {
									String eb01Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb01());
									String eb03Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb03());
									String eb07Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb07());
									String eb02Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb02());
									String eb06Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb06());
									String eb09Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb09());
									String eb12Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb12());
									if (DomainConstants.ERROR_E024
											.equals(detailVO.getErrorCode())) {
										if ("Y".equalsIgnoreCase(eb12Val)) {
											eb12Val = "DIFFVAL-PAR";
										} else {
											eb12Val = "DIFFVAL-NPR";
										}
										sheet.createRow((short) rowCount)
												.createCell((short) 2)
												.setCellValue(eb12Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 7)
												.setCellValue(eb01Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb02Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb03Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb06Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 11)
												.setCellValue(eb09Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E024);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E024) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E024);
											}
										}
									} else if (DomainConstants.ERROR_E027
											.equals(detailVO.getErrorCode())) {
										sheet.createRow((short) rowCount)
												.createCell((short) 7)
												.setCellValue(eb01Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb03Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
									} else if (DomainConstants.ERROR_E018
											.equals(detailVO.getErrorCode())) {
										
										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb03Val);

										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E018);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E018) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E018);
											}
										}
									} else if (DomainConstants.ERROR_E019
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 2)
												.setCellValue(
														eb03Val
																+ "-"
																+ detailVO
																		.getNetworkDescription());
										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb03Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E019);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E019) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E019);
											}
										}
									} else if (DomainConstants.ERROR_E028
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 2)
												.setCellValue(
														"ER"
																+ eb03Val
																+ "-"
																+ detailVO
																		.getNetworkDescription());
										sheet.createRow((short) rowCount)
												.createCell((short) 7)
												.setCellValue(eb01Val);

										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb02Val);

										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb03Val);

										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb06Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E028);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E028) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E028);
											}
										}
									}else if (DomainConstants.ERROR_E038
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 2)
												.setCellValue(DomainConstants.VISION);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
									}else if (DomainConstants.ERROR_E039
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 2)
												.setCellValue(DomainConstants.DENTAL);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
									}
									else if (DomainConstants.ERROR_E040
											.equals(detailVO.getErrorCode())) {
									
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 15)
												.setCellValue(
														detailVO.getErrorDesc());
									}
									
								
									
								
							}
						}
					}
					}
					if (commonErrorMessage.length() != 0) {
						mQValidationSuccessMessage = commonErrorMessage
								.append(
										" is based on the contract information in "+backEndRegion)
								.toString();
					}
				}
			}
		}

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
						//		String minorHeadingDesc = (String) minorHeadingsIterator.next();
						//		MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadings.get(minorHeadingDesc);
								MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
								List minorHeadingsErrorCodeList = minorHeadingFromMap
										.getErrorCodesList();
								if (minorHeadingsErrorCodeList != null) {
									Iterator minorHeadingsErrorCodeListItr = minorHeadingsErrorCodeList
											.iterator();
									if (minorHeadingsErrorCodeListItr != null) {
										while (minorHeadingsErrorCodeListItr
												.hasNext()) {
											rowCount++;
											ErrorDetailVO errorMinorHeading = (ErrorDetailVO) minorHeadingsErrorCodeListItr
													.next();
											sheet
													.createRow((short) rowCount)
													.createCell((short) 0)
													.setCellValue(
															majorHeadingFromMap
																	.getDescriptionText());
											sheet
													.createRow((short) rowCount)
													.createCell((short) 1)
													.setCellValue(
															minorHeadingFromMap
																	.getDescriptionText());
											sheet
													.createRow((short) rowCount)
													.createCell((short) 14)
													.setCellValue(
															errorMinorHeading
																	.getErrorCode());
											sheet
													.createRow((short) rowCount)
													.createCell((short) 15)
													.setCellValue(
															errorMinorHeading
																	.getErrorDesc());
										}
									}

								}
								Map mappings = minorHeadingFromMap
										.getMappings();
								if (mappings != null) {
									//Iterator mappingsIterator = mappings.keySet().iterator();
									Iterator mappingsIterator = mappings.entrySet().iterator();
									if (mappingsIterator != null) {
										while (mappingsIterator.hasNext()) {
										//	String mappingKey = (String) mappingsIterator.next();
										//	ContractMappingVO mappingFromMap = (ContractMappingVO) mappings.get(mappingKey);
											ContractMappingVO mappingFromMap = (ContractMappingVO) ((Map.Entry) mappingsIterator.next()).getValue();
											if (mappingFromMap != null) {
												Variable var = mappingFromMap
														.getVariable();
												List errorCodeList = mappingFromMap
														.getErrorCodesList();
												if (errorCodeList != null) {
													Iterator errorCodeListIterator = errorCodeList
															.iterator();
													if (errorCodeListIterator != null) {
														while (errorCodeListIterator
																.hasNext()) {

															ErrorDetailVO error = (ErrorDetailVO) errorCodeListIterator
																	.next();
															if (null != error) {
																rowCount++;
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 0)
																		.setCellValue(
																				majorHeadingFromMap
																						.getDescriptionText());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 1)
																		.setCellValue(
																				minorHeadingFromMap
																						.getDescriptionText());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 2)
																		.setCellValue(
																				var
																						.getVariableId());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 3)
																		.setCellValue(
																				var
																						.getDescription());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 4)
																		.setCellValue(
																				var
																						.getPva());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 5)
																		.setCellValue(
																				var
																						.getVariableFormat());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 6)
																		.setCellValue(
																				var
																						.getVariableValue());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 7)
																		.setCellValue(
																				mappingFromMap
																						.getEB01Value());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 8)
																		.setCellValue(
																				mappingFromMap
																						.getEB02Value());
																
																// BXNI CR35 CHANGES- Added a new field WPDAccumulator
																 String accumString = var.getWpdAccumulator();
																 if (DomainConstants.ERROR_E001
																			.equals(error
																					.getErrorCode())) { 
																	sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 16)
																	.setCellValue(
																			accumString);
																 }
																	
																List eb03List = mappingFromMap
																		.getEb03Values();
																eb03 = "";
																eb03Count = 0;
																if (eb03List != null) {
																	Iterator eb03ListIterator = eb03List
																			.iterator();
																	if (eb03ListIterator != null) {
																		while (eb03ListIterator
																				.hasNext()) {
																			eb03Count++;
																			if (eb03Count != 1) {
																				eb03 = eb03
																						+ ","
																						+ (String) eb03ListIterator
																								.next();
																			} else {
																				eb03 = (String) eb03ListIterator
																						.next();
																			}
																		}
																	}
																}
																if(DomainConstants.ERROR_E034.equals(error.getErrorCode())
																		|| DomainConstants.ERROR_E042.equals(error.getErrorCode())) {
																	sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 9)
																	.setCellValue(
																			error.getAssociatedEb03());
																}else {
																	sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 9)
																	.setCellValue(
																			eb03);
																}
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 10)
																		.setCellValue(
																				mappingFromMap
																						.getEB06Value());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 11)
																		.setCellValue(
																				mappingFromMap
																						.getEB09Value());
																									
																// BXNI- June - E041
																
																
																//BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
																List<String> startAgeList = null;
		                                                        if(!StringUtils.isBlank(mappingFromMap.getStartAgeValue())){
		                                                        	startAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getStartAgeValue());
		                                                        }
																String startAge = "";
																int startAgeCount = 0;
																if(startAgeList != null) {
																	Iterator startAgeListIterator = startAgeList.iterator();


																	if (startAgeListIterator != null) {
																		while (startAgeListIterator
																				.hasNext()) {
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
			                                                        	sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 12)
																		.setCellValue(startAge);
			                                                        } else {
			                                                        	sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 12)
																		.setCellValue(DomainConstants.EMPTY);
			                                                        }
																 
																
																
																//BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
																 	List<String> endAgeList = null;
			                                                        if(!StringUtils.isBlank(mappingFromMap.getEndAgeValue())){
			                                                        	endAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getEndAgeValue());
			                                                        }
																	String endAge = "";
																	int endAgeCount = 0;
																	if(endAgeList != null) {
																		Iterator endAgeListIterator = endAgeList.iterator();


																		if (endAgeListIterator != null) {
																			while (endAgeListIterator
																					.hasNext()) {
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
				                                                        	sheet
																			.createRow(
																					(short) rowCount)
																			.createCell(
																					(short) 13)
																			.setCellValue(endAge);
				                                                        } else {
				                                                        	sheet
																			.createRow(
																					(short) rowCount)
																			.createCell(
																					(short) 13)
																			.setCellValue(DomainConstants.EMPTY);
				                                                        }
																	 
																
																
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 14)
																		.setCellValue(
																				error
																						.getErrorCode());
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 15)
																		.setCellValue(
																				error
																						.getErrorDesc());
															}
														}

													}
												} else {
													rowCount++;
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 0)
															.setCellValue(
																	majorHeadingFromMap
																			.getDescriptionText());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 1)
															.setCellValue(
																	minorHeadingFromMap
																			.getDescriptionText());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 2)
															.setCellValue(
																	var
																			.getVariableId());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 3)
															.setCellValue(
																	var
																			.getDescription());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 4)
															.setCellValue(
																	var
																			.getPva());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 5)
															.setCellValue(
																	var
																			.getVariableFormat());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 6)
															.setCellValue(
																	var
																			.getVariableValue());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 7)
															.setCellValue(
																	mappingFromMap
																			.getEB01Value());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 8)
															.setCellValue(
																	mappingFromMap
																			.getEB02Value());
													
													// BXNI CR35 CHANGES- Added a new field WPDAccumulator
													/* if (DomainConstants.ERROR_E001
																.equals(error
																		.getErrorCode())) { 
													 String accumString = var.getWpdAccumulator();
														
														sheet
														.createRow(
																(short) rowCount)
														.createCell(
																(short) 16)
														.setCellValue(
																accumString);
													 }*/
													List eb03List = mappingFromMap
															.getEb03Values();
													eb03 = "";
													eb03Count = 0;
													if (eb03List != null) {
														Iterator eb03ListIterator = eb03List
																.iterator();
														if (eb03ListIterator != null) {
															while (eb03ListIterator
																	.hasNext()) {
																eb03Count++;
																if (eb03Count != 1) {
																	eb03 = eb03
																			+ ","
																			+ (String) eb03ListIterator
																					.next();
																} else {
																	eb03 = (String) eb03ListIterator
																			.next();
																}
															}
														}
													}
													sheet.createRow(
															(short) rowCount)
															.createCell(
																	(short) 9)
															.setCellValue(eb03);
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 10)
															.setCellValue(
																	mappingFromMap
																			.getEB06Value());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 11)
															.setCellValue(
																	mappingFromMap
																			.getEB09Value());
													// BXNI - June - E041
													
													//BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
													List<String> startAgeList = null;
                                                    if(!StringUtils.isBlank(mappingFromMap.getStartAgeValue())){
                                                    	startAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getStartAgeValue());
                                                    }
													String startAge = "";
													int startAgeCount = 0;
													if(startAgeList != null) {
														Iterator startAgeListIterator = startAgeList.iterator();


														if (startAgeListIterator != null) {
															while (startAgeListIterator
																	.hasNext()) {
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
                                                        	sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 12)
															.setCellValue(startAge);
                                                        } else {
                                                        	sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 12)
															.setCellValue(DomainConstants.EMPTY);
                                                        }
											
													
													//BXNI Nov CR18 Option 3 Multiple Start Age/End Age Changes
													 List<String> endAgeList = null;
                                                     if(!StringUtils.isBlank(mappingFromMap.getEndAgeValue())){
                                                     	endAgeList = BxUtil.convertCSVToArrayList(mappingFromMap.getEndAgeValue());
                                                     }
														String endAge = "";
														int endAgeCount = 0;
														if(endAgeList != null) {
															Iterator endAgeListIterator = endAgeList.iterator();


															if (endAgeListIterator != null) {
																while (endAgeListIterator
																		.hasNext()) {
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
	                                                        	sheet
																.createRow(
																		(short) rowCount)
																.createCell(
																		(short) 13)
																.setCellValue(endAge);
	                                                        } else {
	                                                        	sheet
																.createRow(
																		(short) rowCount)
																.createCell(
																		(short) 13)
																.setCellValue(DomainConstants.EMPTY);
	                                                        }
													
													sheet.createRow(
															(short) rowCount)
															.createCell(
																	(short) 14)
															.setCellValue("");
													sheet.createRow(
															(short) rowCount)
															.createCell(
																	(short) 15)
															.setCellValue("");
													
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
	}
	/**
	 * method to display the headings in online error report 
	 * @param workbook
	 * @param sheet
	 */
	private static void createEWPDReportHeader(HSSFWorkbook workbook,
			HSSFSheet sheet) {
		sheet.createRow((short) 1).createCell((short) 0).setCellValue(
				DomainConstants.CONTRACT_ID);
		sheet.createRow((short) 1).createCell((short) 2).setCellValue(
				DomainConstants.VER);
		sheet.createRow((short) 2).createCell((short) 0).setCellValue(
				DomainConstants.BUSINESS_ENTITY);
		sheet.createRow((short) 3).createCell((short) 0).setCellValue(
				DomainConstants.DATE_SEGMENT);
		sheet.createRow((short) 5).createCell((short) 0).setCellValue(
				DomainConstants.BENEFIT_COMPONENT);
		sheet.createRow((short) 5).createCell((short) 1).setCellValue(
				DomainConstants.BENEFIT);
		sheet.createRow((short) 5).createCell((short) 2).setCellValue(
				DomainConstants.TIER_INFO);
		sheet.createRow((short) 5).createCell((short) 3).setCellValue(
				DomainConstants.SPSID);
		sheet.createRow((short) 5).createCell((short) 4).setCellValue(
				DomainConstants.SPS_DESCRIPTION);
		sheet.createRow((short) 5).createCell((short) 5).setCellValue(
				DomainConstants.PVA);
		sheet.createRow((short) 5).createCell((short) 6).setCellValue(
				DomainConstants.FORMAT);
		sheet.createRow((short) 5).createCell((short) 7).setCellValue(
				DomainConstants.VALUE_CODED);
		sheet.createRow((short) 5).createCell((short) 8).setCellValue(
				DomainConstants.EB01_NAME);
		sheet.createRow((short) 5).createCell((short) 9).setCellValue(
				DomainConstants.EB02_NAME);
		sheet.createRow((short) 5).createCell((short) 10).setCellValue(
				DomainConstants.EB03_NAME);
		sheet.createRow((short) 5).createCell((short) 11).setCellValue(
				DomainConstants.EB06_NAME);
		sheet.createRow((short) 5).createCell((short) 12).setCellValue(
				DomainConstants.EB09_NAME);
		sheet.createRow((short) 5).createCell((short) 13).setCellValue(
				DomainConstants.ERROR);
		sheet.createRow((short) 5).createCell((short) 14).setCellValue(
				DomainConstants.ERROR_DESCRIPTION);
	}

	/**
	 * Method Generates the report for contract and variable level errors for a
	 * particular contract
	 *
	 * @param contract
	 * @param sheet
	 */
	private static void populateEWPDContractValues(ContractVO contract,
			HSSFSheet sheet, String backEndRegion) {

		int eb03Count;
		String eb03;
		int rowCount = 5;
		boolean mQValidationExists = false;
		boolean contractLevelMappingError = false;
		String mQValidationSuccessMessage = "";
		sheet.createRow((short) 1).createCell((short) 1).setCellValue(
				contract.getContractId());
		sheet.createRow((short) 1).createCell((short) 3).setCellValue(
				contract.getVersion());
		sheet.createRow((short) 2).createCell((short) 1).setCellValue(
				contract.getBusinessEntity());
		sheet.createRow((short) 3).createCell((short) 1).setCellValue(
				contract.getEffectiveDate() + "-" + contract.getExpiryDate());
		// contractMappingVOList contains contract level errors with mapping.
		if (null != contract.getContractMappingVOList()) {
			contractLevelMappingError = true;
		}
		// contractErrCodeList contains contract level errors
		List contractErrCodeList = contract.getErrorCodesList();
		if (contractErrCodeList != null) {
			Iterator contractErrCodeListIterator = contractErrCodeList
					.iterator();
			if (contractErrCodeListIterator != null) {
				while (contractErrCodeListIterator.hasNext()) {
					ErrorDetailVO errorContract = (ErrorDetailVO) contractErrCodeListIterator
							.next();
					if (errorContract.getErrorCode().equals(
							DomainConstants.ERROR_MQ)) {
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
							sheet.createRow((short) rowCount).createCell(
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
		if (!mQValidationExists || contractLevelMappingError) {
			StringBuffer commonErrorMessage = new StringBuffer();
			List contractMappingErrCodeList = contract
					.getContractMappingVOList();
			if (contractMappingErrCodeList != null) {
				Iterator contractMappingErrCodeListIterator = contractMappingErrCodeList
						.iterator();
				if (contractMappingErrCodeListIterator != null) {
					while (contractMappingErrCodeListIterator.hasNext()) {
						rowCount++;
						ContractMappingVO contractMappingVO = (ContractMappingVO) contractMappingErrCodeListIterator
								.next();
						if (null != contractMappingVO) {
							List errorList = contractMappingVO
									.getErrorCodesList();
							if (null != errorList && errorList.size() > 0) {
								// Get the first element of the error
								ErrorDetailVO detailVO = (ErrorDetailVO) errorList
										.get(0);
								if (null != detailVO) {
									String eb01Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb01());
									String eb03Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb03());
									String eb07Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb07());
									String eb02Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb02());
									String eb06Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb06());
									String eb09Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb09());
									String eb12Val = BxUtil
											.getHipaaSegmentValue(contractMappingVO
													.getEb12());
									if (DomainConstants.ERROR_E016
											.equals(detailVO.getErrorCode())) {
										sheet.createRow((short) rowCount)
												.createCell((short) 7)
												.setCellValue(eb07Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E016);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E016) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E016);
											}
										}
									} else if (DomainConstants.ERROR_E024
											.equals(detailVO.getErrorCode())) {
										if ("Y".equalsIgnoreCase(eb12Val)) {
											eb12Val = "DIFFVAL-PAR";
										} else {
											eb12Val = "DIFFVAL-NPR";
										}
										sheet.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(eb12Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb02Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 11)
												.setCellValue(eb06Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 12)
												.setCellValue(eb09Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E024);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E024) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E024);
											}
										}
									} else if (DomainConstants.ERROR_E027
											.equals(detailVO.getErrorCode())) {
										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);
										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									} else if (DomainConstants.ERROR_E019
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(
														eb03Val
																+ "-"
																+ detailVO
																		.getNetworkDescription());
										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E019);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E019) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E019);
											}
										}
									} else if (DomainConstants.ERROR_E028
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
										sheet.createRow((short) rowCount)
												.createCell((short) 8)
												.setCellValue(eb01Val);

										sheet.createRow((short) rowCount)
												.createCell((short) 9)
												.setCellValue(eb02Val);

										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);

										sheet.createRow((short) rowCount)
												.createCell((short) 11)
												.setCellValue(eb06Val);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E028);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E028) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E028);
											}
										}
									} else if(DomainConstants.ERROR_E018
											.equals(detailVO.getErrorCode())){

										sheet.createRow((short) rowCount)
												.createCell((short) 10)
												.setCellValue(eb03Val);

										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
										mQValidationExists = true;
										if (commonErrorMessage.length() == 0) {
											commonErrorMessage
													.append(DomainConstants.ERROR_E018);
										} else {
											if (commonErrorMessage
													.indexOf(DomainConstants.ERROR_E018) == -1) {
												commonErrorMessage
														.append(", ")
														.append(
																DomainConstants.ERROR_E018);
											}
										}
									}else if (DomainConstants.ERROR_E038
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(DomainConstants.VISION);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									}else if (DomainConstants.ERROR_E039
											.equals(detailVO.getErrorCode())) {
										sheet
												.createRow((short) rowCount)
												.createCell((short) 3)
												.setCellValue(DomainConstants.DENTAL);
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									}
									else if (DomainConstants.ERROR_E040
											.equals(detailVO.getErrorCode())) {
									
										sheet
												.createRow((short) rowCount)
												.createCell((short) 13)
												.setCellValue(
														detailVO.getErrorCode());
										sheet
												.createRow((short) rowCount)
												.createCell((short) 14)
												.setCellValue(
														detailVO.getErrorDesc());
									}
								}
							}
						}

					}
					if (commonErrorMessage.length() != 0) {
						mQValidationSuccessMessage = commonErrorMessage
								.append(
										" is based on the contract information in "+ backEndRegion)
								.toString();
					}
				}
			}
		}

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
						//Iterator minorHeadingsIterator = minorHeadings.keySet().iterator();
						Iterator minorHeadingsIterator = minorHeadings.entrySet().iterator();
						if (minorHeadingsIterator != null) {
							while (minorHeadingsIterator.hasNext()) {
							//	String minorHeadingDesc = (String) minorHeadingsIterator.next();
							//	MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadings.get(minorHeadingDesc);
								MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) ((Map.Entry) minorHeadingsIterator.next()).getValue();
								List minorHeadingsErrorCodeList = minorHeadingFromMap.getErrorCodesList();
								if (minorHeadingsErrorCodeList != null) {
									Iterator minorHeadingsErrorCodeListItr = minorHeadingsErrorCodeList
											.iterator();
									if (minorHeadingsErrorCodeListItr != null) {
										while (minorHeadingsErrorCodeListItr
												.hasNext()) {
											rowCount++;
											ErrorDetailVO errorMinorHeading = (ErrorDetailVO) minorHeadingsErrorCodeListItr
													.next();
											sheet
													.createRow((short) rowCount)
													.createCell((short) 0)
													.setCellValue(
															majorHeadingFromMap
																	.getDescriptionText());
											sheet
													.createRow((short) rowCount)
													.createCell((short) 1)
													.setCellValue(
															minorHeadingFromMap
																	.getDescriptionText());
											sheet
													.createRow((short) rowCount)
													.createCell((short) 13)
													.setCellValue(
															errorMinorHeading
																	.getErrorCode());
											sheet
													.createRow((short) rowCount)
													.createCell((short) 14)
													.setCellValue(
															errorMinorHeading
																	.getErrorDesc());
											//For Error E043 to be reported at Benefit level along with the Eb03 values
											//If the check for E043 is removed, then all the benefit level errors have Eb03 values.
											//defect fixed as part of BXNI
											if(DomainConstants.ERROR_E043.equals(errorMinorHeading.getErrorCode())){
												List eb03List = null;
												if (null != minorHeadingFromMap
														.getRuleMapping()) {
													eb03List = minorHeadingFromMap
															.getRuleMapping()
															.getEb03Values();
												}
												eb03 = "";
												eb03Count = 0;
												if (eb03List != null) {
													Iterator eb03ListIterator = eb03List
															.iterator();
													if (eb03ListIterator != null) {
														while (eb03ListIterator
																.hasNext()) {
															eb03Count++;
															if (eb03Count != 1) {
																eb03 = eb03
																		+ ","
																		+ (String) eb03ListIterator
																				.next();
															} else {
																eb03 = (String) eb03ListIterator
																		.next();
															}
														}
													}
												}
												sheet
														.createRow(
																(short) rowCount)
														.createCell(
																(short) 10)
														.setCellValue(
																eb03);
											}
										}
									}
								}
								Map mappings = minorHeadingFromMap
										.getMappings();
								if (mappings != null) {
									Iterator mappingsIterator = mappings
											.entrySet().iterator();
									if (mappingsIterator != null) {
										while (mappingsIterator.hasNext()) {
											//String mappingKey = (String) mappingsIterator.next();
											//ContractMappingVO mappingFromMap = (ContractMappingVO) mappings.get(mappingKey);
											ContractMappingVO mappingFromMap = (ContractMappingVO) ((Map.Entry) mappingsIterator.next()).getValue();
											if (mappingFromMap != null) {
												SPSId spsId = mappingFromMap
														.getSpsId();
												List errorCodeList = mappingFromMap
														.getErrorCodesList();
												if (errorCodeList != null) {
													Iterator errorCodeListIterator = errorCodeList
															.iterator();
													if (errorCodeListIterator != null) {
														while (errorCodeListIterator
																.hasNext()) {
															rowCount++;
															ErrorDetailVO error = (ErrorDetailVO) errorCodeListIterator
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
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 1)
																	.setCellValue(
																			minorHeadingFromMap
																					.getDescriptionText());
															// Code change to include the tier description- July release
															if (null != mappingFromMap
																	.getContractMapping()
																	&& null != mappingFromMap
																			.getContractMapping()
																			.getTierDescription()) {
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 2)
																		.setCellValue(
																				mappingFromMap
																						.getContractMapping()
																						.getTierDescription());

															}
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 3)
																	.setCellValue(
																			spsId
																					.getSpsId());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 4)
																	.setCellValue(
																			spsId
																					.getSpsDesc());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 5)
																	.setCellValue(
																			spsId
																					.getLinePVA());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 6)
																	.setCellValue(
																			spsId
																					.getLineDataType());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 7)
																	.setCellValue(
																			spsId
																					.getLineValue());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 8)
																	.setCellValue(
																			mappingFromMap
																					.getEB01Value());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 9)
																	.setCellValue(
																			mappingFromMap
																					.getEB02Value());
															List eb03List = null;
															if (null != minorHeadingFromMap
																	.getRuleMapping()) {
																eb03List = minorHeadingFromMap
																		.getRuleMapping()
																		.getEb03Values();
															}
															eb03 = "";
															eb03Count = 0;
															if (eb03List != null) {
																Iterator eb03ListIterator = eb03List
																		.iterator();
																if (eb03ListIterator != null) {
																	while (eb03ListIterator
																			.hasNext()) {
																		eb03Count++;
																		if (eb03Count != 1) {
																			eb03 = eb03
																					+ ","
																					+ (String) eb03ListIterator
																							.next();
																		} else {
																			eb03 = (String) eb03ListIterator
																					.next();
																		}
																	}
																}
															}
															if(DomainConstants.ERROR_E029.equals(error.getErrorCode())
																	|| DomainConstants.ERROR_E042.equals(error.getErrorCode())) {
																sheet
																.createRow(
																		(short) rowCount)
																.createCell(
																		(short) 10)
																.setCellValue(
																		error.getAssociatedEb03());
															}else {
																sheet
																		.createRow(
																				(short) rowCount)
																		.createCell(
																				(short) 10)
																		.setCellValue(
																				eb03);
															}
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 11)
																	.setCellValue(
																			mappingFromMap
																					.getEB06Value());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 12)
																	.setCellValue(
																			mappingFromMap
																					.getEB09Value());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 13)
																	.setCellValue(
																			error
																					.getErrorCode());
															sheet
																	.createRow(
																			(short) rowCount)
																	.createCell(
																			(short) 14)
																	.setCellValue(
																			error
																					.getErrorDesc());
														}
													}
												} else {
													rowCount++;
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 0)
															.setCellValue(
																	majorHeadingFromMap
																			.getDescriptionText());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 1)
															.setCellValue(
																	minorHeadingFromMap
																			.getDescriptionText());
													if (null != mappingFromMap
															.getContractMapping()
															&& null != mappingFromMap
																	.getContractMapping()
																	.getTierDescription()) {
														sheet
																.createRow(
																		(short) rowCount)
																.createCell(
																		(short) 2)
																.setCellValue(
																		mappingFromMap
																				.getContractMapping()
																				.getTierDescription());

													}
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 3)
															.setCellValue(
																	spsId
																			.getSpsId());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 4)
															.setCellValue(
																	spsId
																			.getSpsDesc());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 5)
															.setCellValue(
																	spsId
																			.getLinePVA());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 6)
															.setCellValue(
																	spsId
																			.getLineDataType());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 7)
															.setCellValue(
																	spsId
																			.getLineValue());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 8)
															.setCellValue(
																	mappingFromMap
																			.getEB01Value());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 9)
															.setCellValue(
																	mappingFromMap
																			.getEB02Value());
													List eb03List = null;
													if (null != minorHeadingFromMap
															.getRuleMapping()) {
														eb03List = minorHeadingFromMap
																.getRuleMapping()
																.getEb03Values();
													}
													eb03 = "";
													eb03Count = 0;
													if (eb03List != null) {
														Iterator eb03ListIterator = eb03List
																.iterator();
														if (eb03ListIterator != null) {
															while (eb03ListIterator
																	.hasNext()) {
																eb03Count++;
																if (eb03Count != 1) {
																	eb03 = eb03
																			+ ","
																			+ (String) eb03ListIterator
																					.next();
																} else {
																	eb03 = (String) eb03ListIterator
																			.next();
																}
															}
														}
													}
													sheet.createRow(
															(short) rowCount)
															.createCell(
																	(short) 10)
															.setCellValue(eb03);
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 11)
															.setCellValue(
																	mappingFromMap
																			.getEB06Value());
													sheet
															.createRow(
																	(short) rowCount)
															.createCell(
																	(short) 12)
															.setCellValue(
																	mappingFromMap
																			.getEB09Value());
													sheet.createRow(
															(short) rowCount)
															.createCell(
																	(short) 13)
															.setCellValue("");
													sheet.createRow(
															(short) rowCount)
															.createCell(
																	(short) 14)
															.setCellValue("");
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
	}

	public static HSSFSheet generateStaticData(HSSFWorkbook workbook,
			boolean isExcel, String formattedResponse) {

		Calendar c1 = Calendar.getInstance(); // today
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		String auditDate = formatter.format(c1.getTime());
		HSSFSheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth((short) 20);
		HSSFRow row = null;
		HSSFCell cell = null;
		int rownum = 2;
		if (!isExcel) {
			for (int j = 1; j < 7; j++) {
				row = sheet.createRow((short) j);
				for (int i = 0; i < 10; i++) {
					cell = row.createCell((short) i);
					if (i % 2 == 0) {
						cell.setCellStyle(getStyle(workbook,
								HSSFColor.GREY_25_PERCENT.index,
								HSSFFont.BOLDWEIGHT_BOLD));
					} else {
						cell.setCellStyle(getStyle(workbook,
								HSSFColor.GREY_25_PERCENT.index,
								HSSFFont.U_SINGLE));
					}
				}
				row.setHeightInPoints((short) 17);
			}
			sheet.setFitToPage(true);
			rownum = 1;
			sheet.getRow(rownum).getCell((short) 0).setCellValue(
					DomainConstants.AUDIT_DATE);
			sheet.getRow(rownum).getCell((short) 1).setCellValue(auditDate);
			rownum = rownum + 1;
			sheet.getRow(rownum).getCell((short) 0).setCellValue(
					DomainConstants.SUBSCRIBER_NAME);
			sheet.getRow(rownum).getCell((short) 2).setCellValue(
					DomainConstants.DOB);
			sheet.getRow(rownum).getCell((short) 4).setCellValue(
					DomainConstants.GENDER);
			sheet.getRow(rownum).getCell((short) 6).setCellValue(
					DomainConstants.ELIGIBILITY);
			rownum = rownum + 1;
			sheet.getRow(rownum).getCell((short) 0).setCellValue(
					DomainConstants.SUBSCRIBER_ID);
			if (formattedResponse.lastIndexOf("HL*4*3*23*0") > 1) {
				rownum = rownum + 1;
				sheet.getRow(rownum).getCell((short) 0).setCellValue(
						DomainConstants.MEMBER_NAME);
				rownum = rownum + 1;
			} else {
				rownum = rownum + 2;
			}
			sheet.getRow(rownum).getCell((short) 0).setCellValue(
					DomainConstants.HOSP_BEN_CODE);
			sheet.getRow(rownum).getCell((short) 2).setCellValue(
					DomainConstants.PROF_BEN_CODE);
			rownum = rownum + 1;
		}
		int cellItr = 0;
		
		//Cell 0
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.EB_STRING);
		HSSFCellStyle cellstyle = getStyle(workbook,
				HSSFColor.GREY_50_PERCENT.index, HSSFFont.BOLDWEIGHT_BOLD);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 1
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.SERVICE);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 2
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.ELIGIBILITY_BENEFIT_INF);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 3
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.INN_OON);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 4
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.PREFIX);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 5
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.EB_02);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		if (!isExcel) {
			//Cell 6
			cellItr+=1;
			sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
					DomainConstants.EB_04);
			sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
			
			//Cell 7
			cellItr+=1;
			sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
					DomainConstants.EB_05);
			sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		}
		//Cell 8
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.EB_06);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 9
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.EB_09);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 10
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.EB_11);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 11
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD01_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 12
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD02_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 13
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD03_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 14
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD04_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 15
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD05_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//cell hsd06
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD06_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//cell hsd07
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD07_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//cell hsd08
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.HSD08_NAME);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		if(isExcel){
			//Cell 16
			cellItr+=1;
			sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
							DomainConstants.BENEFIT_EFFECTIVE_DATE);
			sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
			
			//Cell 17
			cellItr+=1;
			sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
									DomainConstants.BENEFIT_TERMINATION_DATE);
			sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		}				
		//Cell 18
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.III_DECODED);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 19
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.MESSAGES);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 20
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.OTHER);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		//Cell 21
		cellItr+=1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.VALUE_AS_IN_ABS_PRODUCTION);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		
		// 27x Transaction changes - October 2014 Release
		cellItr += 1;
		sheet.createRow((short) rownum).createCell((short) cellItr).setCellValue(
				DomainConstants.LOOP2120NM1MESSAGESEGMENT);
		sheet.getRow(rownum).getCell((short) cellItr).setCellStyle(cellstyle);
		sheet.getRow(rownum).getCell((short) cellItr).getCellStyle().setWrapText(true);
		
		sheet.getRow(rownum).setHeightInPoints((short) 30);
		return sheet;

	}
	/* *********** BXNI November Release changes in 27X Starts * ****/
	

	public static void populate271Report(String formattedResponse,
			HSSFWorkbook workbook, HSSFSheet sheet) {

		X12Parser x12Parser = new X12Parser();
		Interchange interchange = null;

		try {
			interchange = x12Parser.parse271Response(formattedResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		List<Loop2110> loop2110C = extractLoop2110(interchange,
				HLLoopTypeEnum.SUBSCRIBER);
		List<Loop2110> loop2110D = extractLoop2110(interchange,
				HLLoopTypeEnum.DEPENDENT);

		List<Loop2110> searchingList = new ArrayList<Loop2110>();
		if (null != loop2110C) {
			searchingList.addAll(loop2110C);
		}
		if (null != loop2110D) {
			searchingList.addAll(loop2110D);
		}

		populate271ReportHeader(workbook, sheet , false, formattedResponse, interchange);
		populate271ReportValues(workbook, sheet, searchingList);
		
	}
	

	private static void populate271ReportValues(HSSFWorkbook workbook, HSSFSheet sheet,
			List<Loop2110> searchingList) {
		HSSFCellStyle cellstyle = getStyle(workbook, HSSFColor.WHITE.index,
				HSSFFont.U_SINGLE);
		cellstyle.setWrapText(false);
		HSSFCellStyle cellstyleWrap = getStyle(workbook, HSSFColor.WHITE.index,
				HSSFFont.U_SINGLE);
		cellstyleWrap.setWrapText(true);
		
		int size = 256 * 25;
		int ebStringSize = 256 * 65;
		int messageColSize = 256 * 65;
		sheet.setColumnWidth((short) 0, (short) ebStringSize);
		sheet.setColumnWidth((short) 1, (short) size);
		sheet.setColumnWidth((short) 23, (short) messageColSize);

		int rowCount = 6;
		Loop2110 searchResult;
		if (null != searchingList) {
			for (int i = 0; i < searchingList.size(); i++) {
				searchResult = (Loop2110) searchingList.get(i);
				rowCount++;

				String ebString = "";
				String eb03 = "";
				String eb01 = "";
				String eb12 = "";
				String eb07eb08eb10 = "";
				String eb02 = "";
				String eb04 = "";
				String eb05 = "";
				String eb06 = "";
				String eb09 = "";
				String eb11 = "";
				String contactInformation = "";
				String hsd01 = "";
				String hsd02 = "";
				String hsd03 = "";
				String hsd04 = "";
				String hsd05 = "";
				String hsd06 = "";
				String hsd07 = "";
				String hsd08 = "";
				String iii02 = "";
				
				String dateTimeQualifier = "";
				String dateTimePeriod = "";
				
				String other = "";
				String message = "";
				String nm1MessageSegment = "";

				if (null != searchResult) {
					ebString = searchResult.getExpression();
					ebString = ebString.replaceAll("~", "~\n");
					ebString.trim();
					ebString = StringUtils.chomp(ebString);
					if (null != searchResult.getEb()) {
						eb03 = searchResult.getEb().getEb03Desc();
						eb01 = searchResult.getEb().getEb01Desc();
						eb12 = searchResult.getEb().getEb12Desc();
						eb07eb08eb10 = translateEB07EB08EB10Value(searchResult
								.getEb());
						eb02 = searchResult.getEb().getEb02Desc();
						eb04 = searchResult.getEb().getEb04Desc();
						eb05 = searchResult.getEb().getEb05();
						eb06 = searchResult.getEb().getEb06Desc();
						eb09 = searchResult.getEb().getEb09Desc();
						eb11 = searchResult.getEb().getEb11Desc();
					}
					if (null != searchResult.getHsd()
							&& !searchResult.getHsd().isEmpty()
							&& searchResult.getHsd().size() > 0) {
						HSD hsd = searchResult.getHsd().get(0);
						hsd01 = hsd.getHsd01Desc();
						hsd02 = hsd.getHsd02();
						hsd03 = hsd.getHsd03Desc();
						hsd04 = hsd.getHsd04();
						hsd05 = hsd.getHsd05Desc();
						hsd06 = hsd.getHsd06();
						hsd07 = hsd.getHsd07Desc();
						hsd08 = hsd.getHsd08Desc();
					}
					if (null != searchResult.getLoop2115()
							&& !searchResult.getLoop2115().isEmpty()
							&& searchResult.getLoop2115().size() > 0) {
						Loop2115 loop2115 = searchResult.getLoop2115().get(0);
						if (null != loop2115 && null != loop2115.getIii()) {
							iii02 = loop2115.getIii().getIii02Desc();
							other = loop2115.getIii().getExpression() + "~";
						}
					}
					if (null != searchResult.getEb() && null != searchResult.getEb().getEb11()
							&& (searchResult.getEb().getEb11().equalsIgnoreCase("Y") ||
									searchResult.getEb().getEb11().equalsIgnoreCase("U"))
							&& null != searchResult.getLoop2120()
							&& !searchResult.getLoop2120().isEmpty()
							&& searchResult.getLoop2120().size() > 0) {
						Loop2120 loop2120 = searchResult.getLoop2120().get(0);
						if (null != loop2120 && null != loop2120.getPer() 
								&& !loop2120.getPer().isEmpty()) {							
							PER per = loop2120.getPer().get(0);		
							contactInformation = translatePERSegment(per);						
						}
					}
					// 27x Transaction Changes - October2014 Release
					if(null != searchResult.getLoop2120() && !searchResult.getLoop2120().isEmpty()) {
						List <Loop2120> loop2120List = searchResult.getLoop2120();
						
						for (Loop2120 loop : loop2120List) {
							String expression = loop.getExpression();
							
							StringTokenizer st = new StringTokenizer(expression, DomainConstants.TILDA_CHAR);
							while (st.hasMoreTokens()) {
								String string = st.nextToken();
								if(string.startsWith(DomainConstants.NM1_STRING)) {
									StringTokenizer nmString = new StringTokenizer(string, DomainConstants.STAR_CHAR);
									
									while (nmString.hasMoreTokens()) {
										String blueString = nmString.nextToken();
										if (null != blueString) {
											if (DomainConstants.BLUE_DISTINCTION_PLUS_SIGN.equalsIgnoreCase(blueString.trim())) {
												nm1MessageSegment = DomainConstants.BLUE_DISTINCTION_PLUS;
												break;
											} else if (DomainConstants.BLUE_DISTINCTION.equalsIgnoreCase(blueString.trim())) {
												nm1MessageSegment = DomainConstants.BLUE_DISTINCTION;
												break;
											}
										}
									}
									
								}
							}
						}
					}
					
					if (null != searchResult.getDtp()
							&& !searchResult.getDtp().isEmpty()
							&& searchResult.getDtp().size() > 0) {
						DTP dtp = searchResult.getDtp().get(0);
						dateTimeQualifier = dtp.getDtp01Desc();
						
						dateTimePeriod = translateEligibilityRange(dtp.getDtp03());
					}

					
					if (null != searchResult.getMsg()
							&& !searchResult.getMsg().isEmpty()) {
						StringBuffer msgbuff = new StringBuffer();
						for (MSG ms : searchResult.getMsg()) {
							msgbuff.append(ms.getMsg01());
							msgbuff.append("~\n");
						}
						message = msgbuff.toString();
					}
				}

				createCell(sheet, rowCount, 0, ebString, cellstyleWrap);

				createCell(sheet, rowCount, 1, eb03, cellstyle);
				createCell(sheet, rowCount, 2, eb01, cellstyle);
				createCell(sheet, rowCount, 3, eb12, cellstyle);
				createCell(sheet, rowCount, 4, eb07eb08eb10, cellstyle);
				createCell(sheet, rowCount, 5, eb02, cellstyle);
				createCell(sheet, rowCount, 6, eb04, cellstyle);
				createCell(sheet, rowCount, 7, eb05, cellstyle);
				createCell(sheet, rowCount, 8, eb06, cellstyle);
				createCell(sheet, rowCount, 9, eb09, cellstyle);
				createCell(sheet, rowCount, 10, eb11, cellstyle);
				
				createCell(sheet, rowCount, 11, contactInformation, cellstyleWrap);
				
				createCell(sheet, rowCount, 12, hsd01, cellstyle);
				createCell(sheet, rowCount, 13, hsd02, cellstyle);
				createCell(sheet, rowCount, 14, hsd03, cellstyle);
				createCell(sheet, rowCount, 15, hsd04, cellstyle);
				createCell(sheet, rowCount, 16, hsd05, cellstyle);				
				createCell(sheet, rowCount, 17, hsd06, cellstyle);
				createCell(sheet, rowCount, 18, hsd07, cellstyle);
				createCell(sheet, rowCount, 19, hsd08, cellstyle);				
				createCell(sheet, rowCount, 20, iii02, cellstyle);
				
				createCell(sheet, rowCount, 21, dateTimeQualifier, cellstyle);
				createCell(sheet, rowCount, 22, dateTimePeriod, cellstyle);
				
				createCell(sheet, rowCount, 23, message, cellstyleWrap);
				
				createCell(sheet, rowCount, 24, other, cellstyle);
				createCell(sheet, rowCount, 25, "", cellstyle);
				createCell(sheet, rowCount, 26, nm1MessageSegment, cellstyle);
			}
		}
		
	}

	private static String translatePERSegment(PER per) {
		String contactInformation = "";
		if (per.getPer02() != null
				&& null != per.getPer02().trim() 
				&& !per.getPer02().trim().isEmpty()) {
			contactInformation = per.getPer02() + "\n";
		}
		if (per.getPer03() != null
				&& null != per.getPer03().trim()
				&& !per.getPer03().trim().isEmpty()) {
			contactInformation = contactInformation
					+ buildQualContNumberPair(
							per.getPer03Desc(),
							per.getPer04());
		}
		if (null != per.getPer03() 
				&& "TE".equalsIgnoreCase(per.getPer03())
				&& null != per.getPer05() 
				&& "EX".equalsIgnoreCase(per.getPer05())) {
			contactInformation = contactInformation + " - ";
		}
		contactInformation = contactInformation + "\n";
		if (per.getPer05() != null
				&& null != per.getPer05().trim()
				&& !per.getPer05().trim().isEmpty()) {
			contactInformation = contactInformation
					+ buildQualContNumberPair(
							per.getPer05Desc(),
							per.getPer06());
		}
		if (null != per.getPer05() 
				&& "TE".equalsIgnoreCase(per.getPer05())
				&& null != per.getPer07()
				&& "EX".equalsIgnoreCase(per.getPer07())) {
			contactInformation = contactInformation + " - ";
		}
		contactInformation = contactInformation + "\n";
		if (per.getPer07() != null
				&& null != per.getPer07().trim()
				&& !per.getPer07().trim().isEmpty()) {
			contactInformation = contactInformation
					+ buildQualContNumberPair(
							per.getPer07Desc(),
							per.getPer08());
		}
		return contactInformation;
	}

	private static String buildQualContNumberPair(String qualifier, String contactDetail) {
		String pair = "";
		if (null != qualifier && !qualifier.isEmpty()) {
			pair = qualifier + " : " + contactDetail;
		}
		return pair;
	}

	private static void populate271ReportHeader(HSSFWorkbook workbook,
			HSSFSheet sheet, boolean isExcel, String formattedResponse, 
			Interchange interchange) {

		Calendar c1 = Calendar.getInstance(); // today
		
		// Date formats
		SimpleDateFormat formatter1 = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		// Styles
		HSSFCellStyle header_type1_bold = getStyle(workbook,
				HSSFColor.GREY_25_PERCENT.index,
				HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle header_type1_normal = getStyle(workbook,
				HSSFColor.GREY_25_PERCENT.index,
				HSSFFont.U_SINGLE);
		HSSFCellStyle header_type2_bold = getStyle(workbook,
				HSSFColor.GREY_50_PERCENT.index, HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFCellStyle header_type2_bold_Wrap = getStyle(workbook,
				HSSFColor.GREY_50_PERCENT.index, HSSFFont.BOLDWEIGHT_BOLD);
		header_type2_bold_Wrap.setWrapText(true);
		
		HSSFCellStyle errorStyle = getStyle(workbook,
				HSSFColor.RED.index,
				HSSFFont.BOLDWEIGHT_BOLD);
		
		sheet.setDefaultColumnWidth((short) 20);
		
		HLLoopTypeEnum responseType = identifyResponseType(interchange);
		
		Loop2100 loop2100c = extractLoop2100(interchange, HLLoopTypeEnum.SUBSCRIBER);
		
		Loop2100 loop2100 = extractLoop2100(interchange, responseType);
		SimulationHelper simulationHelper = new SimulationHelper();
		String auditDate = "";
		String subscriberName = "";
		String dateOfBirth = "";
		String gender = "";
		String subscriberId = "";
		String memberName = "";
		String eligibility = "";
		String groupNumber = "";
		String groupName = "";
		String aaaCode = "";

		
		auditDate = formatter1.format(c1.getTime());
		if (null != loop2100c && null != loop2100c.getNm1()) {
			if(null != loop2100c.getNm1().getNm103()){
				subscriberName = subscriberName+loop2100c.getNm1().getNm103()+ " ";
			}
			if(null != loop2100c.getNm1().getNm104()){
				subscriberName = subscriberName+loop2100c.getNm1().getNm104()+" ";
			}
			if(null != loop2100c.getNm1().getNm105()){
				subscriberName = subscriberName+loop2100c.getNm1().getNm105();
			}
			
			subscriberId = loop2100c.getNm1().getNm109();
		}
		if (null != loop2100) {
			if (null != loop2100.getDmg()) {
				try {
					c1.setTime(formatter.parse(loop2100.getDmg().getDmg02()));
					dateOfBirth = dateFormatter.format(
							c1.getTime())
							.toString();
				}
				catch (ParseException e) {
					log.debug("ParseException Caught " +ESAPI.encoder().encodeForHTML(loop2100.getDmg().getDmg02()));
					dateOfBirth = loop2100.getDmg().getDmg02();
				}
				gender = loop2100.getDmg().getDmg03();	
			}
			if (null != loop2100.getNm1() && HLLoopTypeEnum.DEPENDENT.equals(responseType)) {
				
				if (null != loop2100.getNm1().getNm103()){
					memberName = memberName+loop2100.getNm1().getNm103() + " ";
				}
				if(null != loop2100.getNm1().getNm104()){
					memberName = memberName+loop2100.getNm1().getNm104() + " ";
				}
				if(null != loop2100.getNm1().getNm105()){
					memberName = memberName+ loop2100.getNm1().getNm105();
				}
			}
			if (null != loop2100.getDtp() && !loop2100.getDtp().isEmpty() 
					&& null != loop2100.getDtp().get(0)) {
				eligibility = translateEligibilityRange(loop2100.getDtp().get(0).getDtp03());
				
			}	
			if (null != loop2100.getRef() && !loop2100.getRef().isEmpty()) {
				for (REF ref : loop2100.getRef()) {
					if (ref.getRef01().equalsIgnoreCase("6P")) {
						groupNumber = ref.getRef02();
						groupName = ref.getRef03();
						break;
					}
				}
			}		
		}
		if (null!= interchange) {				
			List<AAA> aaaList = simulationHelper.extractAllAAACodes(interchange);
			for (AAA aaa : aaaList) {
				if (null != aaa) {						
					aaaCode += aaa.getAaa03Desc()+ ", " + aaa.getAaa04Desc()+". ";
				}
			}
		}
		// Row 1
		createCell(sheet, 1, 0 , DomainConstants.AUDIT_DATE, header_type1_bold);
		createCell(sheet, 1, 1 , auditDate, header_type1_normal);
		for (int i=2; i<10; i++) {
			createCell(sheet, 1, i ,"", header_type1_normal);
		}
		sheet.getRow(1).setHeightInPoints((short) 17);
		
		// Row 2
		createCell(sheet, 2, 0 , DomainConstants.SUBSCRIBER_NAME, header_type1_bold);
		createCell(sheet, 2, 1 , subscriberName , header_type1_normal);
		createCell(sheet, 2, 2 , DomainConstants.DOB, header_type1_bold);
		createCell(sheet, 2, 3 , dateOfBirth , header_type1_normal);		
		createCell(sheet, 2, 4 , DomainConstants.GENDER, header_type1_bold);
		createCell(sheet, 2, 5 , gender , header_type1_normal);	
		createCell(sheet, 2, 6 , DomainConstants.ELIGIBILITY, header_type1_bold);
		createCell(sheet, 2, 7 , eligibility, header_type1_normal);
		for (int i=8; i<10; i++) {
			createCell(sheet, 2, i ,"", header_type1_normal);
		}
		sheet.getRow(2).setHeightInPoints((short) 17);
		
		// Row 3
		createCell(sheet, 3, 0 , DomainConstants.SUBSCRIBER_ID, header_type1_bold);
		createCell(sheet, 3, 1 , subscriberId , header_type1_normal);	
		
		for (int i=2; i<10; i++) {
			createCell(sheet, 3, i ,"", header_type1_normal);
		}
		sheet.getRow(3).setHeightInPoints((short) 17);
		
		// Row 4
		String memberLabel = "";

		if (HLLoopTypeEnum.DEPENDENT.equals(responseType)) {
			memberLabel = DomainConstants.MEMBER_NAME;

		}
		createCell(sheet, 4, 0 , memberLabel, header_type1_bold);
		createCell(sheet, 4, 1 , memberName, header_type1_normal);
		
		for (int i=2; i<10; i++) {
			createCell(sheet, 4, i ,"", header_type1_normal);
		}
		sheet.getRow(4).setHeightInPoints((short) 17);
		
		// Row 5
		createCell(sheet, 5, 0 , DomainConstants.HOSP_BEN_CODE, header_type1_bold);
		createCell(sheet, 5, 1 , "", header_type1_normal);
		createCell(sheet, 5, 2 , DomainConstants.PROF_BEN_CODE, header_type1_bold);
		createCell(sheet, 5, 3 , "", header_type1_normal);
		createCell(sheet, 5, 4 , "Group Number", header_type1_bold);
		createCell(sheet, 5, 5 , groupNumber, header_type1_normal);
		createCell(sheet, 5, 6 , "Group Name", header_type1_bold);
		createCell(sheet, 5, 7 , groupName, header_type1_normal);
		
		for (int i=8; i<10; i++) {
			createCell(sheet, 5, i ,"", header_type1_normal);
		}
		sheet.getRow(5).setHeightInPoints((short) 17);
		
		int row = 6;
		// If AAA code exists
		if (aaaCode.trim().length() > 0) {
			createCell(sheet, row, 1 , "", errorStyle);
			createCell(sheet, row, 2 , "", errorStyle);
			createCell(sheet, row, 3 , "", errorStyle);
			createCell(sheet, row, 4 , "", errorStyle);
			createCell(sheet, row, 5 , "", errorStyle);
			createCell(sheet, row, 6 , "", errorStyle);
			createCell(sheet, row, 7 , "", errorStyle);
			createCell(sheet, row, 0 , aaaCode , errorStyle);
			Region reg = new Region();
			reg.setRowFrom(row);
			reg.setRowTo(row);
			reg.setColumnFrom((short)0);
			reg.setColumnTo((short)7);
			sheet.addMergedRegion(reg);
			row++;
		}
		
		// Row 6	
		createCell(sheet, row, 0 , DomainConstants.EB_STRING, header_type2_bold);
		createCell(sheet, row, 1 , DomainConstants.SERVICE, header_type2_bold);
		createCell(sheet, row, 2 , DomainConstants.ELIGIBILITY_BENEFIT_INF, header_type2_bold);
		createCell(sheet, row, 3 , DomainConstants.INN_OON, header_type2_bold);
		createCell(sheet, row, 4 , DomainConstants.PREFIX, header_type2_bold);
		createCell(sheet, row, 5 , DomainConstants.EB_02, header_type2_bold);
		createCell(sheet, row, 6 , DomainConstants.EB_04, header_type2_bold);
		createCell(sheet, row, 7 , DomainConstants.EB_05, header_type2_bold);
		createCell(sheet, row, 8 , DomainConstants.EB_06, header_type2_bold);
		createCell(sheet, row, 9 , DomainConstants.EB_09, header_type2_bold);
		createCell(sheet, row, 10 , DomainConstants.EB_11, header_type2_bold);		
		createCell(sheet, row, 11 ,"Administrative \n Communications Contact",header_type2_bold_Wrap); 		
		createCell(sheet, row, 12 , DomainConstants.HSD01_NAME, header_type2_bold);
		createCell(sheet, row, 13 , DomainConstants.HSD02_NAME, header_type2_bold);
		createCell(sheet, row, 14 , DomainConstants.HSD03_NAME, header_type2_bold);
		createCell(sheet, row, 15 , DomainConstants.HSD04_NAME, header_type2_bold);
		createCell(sheet, row, 16 , DomainConstants.HSD05_NAME, header_type2_bold);		
		createCell(sheet, row, 17 , DomainConstants.HSD06_NAME, header_type2_bold);
		createCell(sheet, row, 18 , DomainConstants.HSD07_NAME, header_type2_bold);
		createCell(sheet, row, 19 , DomainConstants.HSD08_NAME, header_type2_bold);		
		createCell(sheet, row, 20 , DomainConstants.III_DECODED, header_type2_bold);		
		createCell(sheet, row, 21 , "Date/Time Qualifier", header_type2_bold);
		createCell(sheet, row, 22 , "Date Time Period", header_type2_bold);		
		createCell(sheet, row, 23 , DomainConstants.MESSAGES, header_type2_bold);
		createCell(sheet, row, 24 , DomainConstants.OTHER, header_type2_bold);
		createCell(sheet, row, 25 , DomainConstants.VALUE_AS_IN_ABS_PRODUCTION, header_type2_bold);
		// 27x Transaction changes - October 2014
		createCell(sheet, row, 26, DomainConstants.HEADER_2120_LOOP_NM1_MESSAGE_SEGMENT, header_type2_bold_Wrap);
				
		sheet.getRow(row).setHeightInPoints((short) 30);
				
	}
	private static String translateEligibilityRange(String dateRange) {
		
		Calendar c1 = Calendar.getInstance(); // today
		
		// Date formats
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		
		StringTokenizer eligibilityST = new StringTokenizer(
				dateRange, DomainConstants.HYPHEN,
				false);
		String eligibility = "";
		StringBuffer dateBuffer = new StringBuffer();
		boolean toExists = false;
		while (eligibilityST.hasMoreTokens()) {
			eligibility = eligibilityST.nextToken();
			try {
				Date date = formatter.parse(eligibility);
				c1.setTime(date);
				dateBuffer.append(dateFormatter.format(
						c1.getTime()).toString());
				if (!toExists) {
					dateBuffer.append(DomainConstants.TO);
					toExists = true;
				}
			} catch (ParseException e) {
				log.debug("Parse Exception Caught");
			}
		}
		return dateBuffer.toString();
	}
	private enum HLLoopTypeEnum{
		SUBSCRIBER, DEPENDENT;
	}

	private static String translateEB07EB08EB10Value(EB eb) {
		 //  $ / % / #
		String dol_per_quantity = "";
		if (null != eb) {
			 if (null != eb.getEb07() && !eb.getEb07().isEmpty()) {
		  		  dol_per_quantity = DomainConstants.DOLLAR + eb.getEb07();
		  	  }
		  	  if (null != eb.getEb08() && !eb.getEb08().isEmpty()) {
		  		  //dol_per_quantity = eb.getEb08();
		  		  String percentValue = "";
		  		try {
					float percent = Float.parseFloat(eb.getEb08());
					percent = percent * 100;
					percentValue = (int) percent
					+ DomainConstants.PERCENTAGE;
					
				} catch (NumberFormatException e) {
					log.debug("NumberFormatException Caught for : "+ESAPI.encoder().encodeForHTML(eb.getEb08()));
					 percentValue = eb.getEb08();
				}finally{
					if(!dol_per_quantity.equalsIgnoreCase("")){
						dol_per_quantity = dol_per_quantity+", "+percentValue;
					}
					else{
						dol_per_quantity = percentValue;
					}
				}
		  	  }
		  	  if (null != eb.getEb10() && !eb.getEb10().isEmpty()) {
		  		  String EB09Desc = "";
		  		  if(null != eb.getEb09() && !eb.getEb09().isEmpty() && null != eb.getEb09Desc()){
		  			EB09Desc = eb.getEb09Desc();
		  			EB09Desc = " ("+EB09Desc+")";
		  		  }
		  		if(!dol_per_quantity.equalsIgnoreCase("")){
					dol_per_quantity = dol_per_quantity+", "+eb.getEb10()+EB09Desc;
				}
				else{
					dol_per_quantity = eb.getEb10();
				}
		  	  }		
		}	
		return dol_per_quantity;		  	  
	}
	
	private static List<Loop2110> extractLoop2110(Interchange interchange,
			HLLoopTypeEnum subscriber) {
		List<Loop2110> searchingList = null;

		Loop2100 loop2100 = extractLoop2100(interchange, subscriber);

		if (null != loop2100 && null != loop2100.getLoop2110() 
				&& !loop2100.getLoop2110().isEmpty()) {
			searchingList = loop2100.getLoop2110();
		}		
		return searchingList;
	}

	private static Loop2100 extractLoop2100(Interchange interchange,
			HLLoopTypeEnum responseType) {
		Loop2100 loop2100 = null;
		if (null != interchange.getFunctionGroup() &&  
				!interchange.getFunctionGroup().isEmpty()) {
			FunctionGroup functionGroup = interchange.getFunctionGroup().get(0);
			if (null != functionGroup.getTransaction() && !functionGroup.getTransaction().isEmpty()) {
				Transaction transaction = functionGroup.getTransaction().get(0);
				if (null != transaction.getLoop2000() && !transaction.getLoop2000().isEmpty()) {
					List<Loop2000> loop2000 = transaction.getLoop2000();
					if (null != loop2000 && !loop2000.isEmpty()) {
						if(HLLoopTypeEnum.SUBSCRIBER.equals(responseType) 
								&& loop2000.size() >= 3 
								&& null != loop2000.get(2).getLoop2100()) {
							loop2100 = loop2000.get(2).getLoop2100().get(0);
						}
						if(HLLoopTypeEnum.DEPENDENT.equals(responseType)
								&& loop2000.size() >= 4 
								&& null != loop2000.get(3).getLoop2100()) {
							loop2100 = loop2000.get(3).getLoop2100().get(0);
						}
					}
				}
			}
		}
		return loop2100;
	}

	private static HLLoopTypeEnum identifyResponseType(Interchange interchange) {
		HLLoopTypeEnum responseTypeEnum = null;
		if (null != interchange.getFunctionGroup() &&  
				!interchange.getFunctionGroup().isEmpty()) {
			FunctionGroup functionGroup = interchange.getFunctionGroup().get(0);
			if (null != functionGroup.getTransaction() && !functionGroup.getTransaction().isEmpty()) {
				Transaction transaction = functionGroup.getTransaction().get(0);
				if (null != transaction.getLoop2000() && !transaction.getLoop2000().isEmpty()) {
					if (transaction.getLoop2000().size() == 3) {
						responseTypeEnum = HLLoopTypeEnum.SUBSCRIBER;
					}
					if (transaction.getLoop2000().size() == 4) {
						responseTypeEnum = HLLoopTypeEnum.DEPENDENT;
					}
				}
			}
		}
		return responseTypeEnum;
	}
	
	private static void createCell(HSSFSheet sheet, int row, int col,
			String value, HSSFCellStyle style) {
		HSSFCell cell = sheet.createRow((short) row).createCell(
                (short) col);
		cell.setCellValue(value);
		cell.setCellStyle(style);
		
	}
	/* *********** BXNI November Release changes in 27X ends * ****/
	private static HSSFCellStyle getStyle(HSSFWorkbook hwb, short index,
			short fontType) {
		HSSFCellStyle style = hwb.createCellStyle();
		style.setFillForegroundColor(index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = hwb.createFont();
		font.setFontName(DomainConstants.ARIAL);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(fontType);
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
		style.setVerticalAlignment(DomainConstants.VERTICAL_TOP);

		return style;
	}
	
	private static List getEB03List(){
    	return SimulationResourceBundle.getResourceBundle(DomainConstants.SERVICE_TYPE_CODES_30,DomainConstants.X12);
	}
	private static List getMsgList(){
    	return SimulationResourceBundle.getResourceBundle(DomainConstants.MESSAGE_TYPE_URGENT_OR_SPECIALIST,DomainConstants.X12);
	}
	
	
}
