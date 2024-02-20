package com.wellpoint.wpd.web.report;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;

import jxl.Workbook;
//import jxl.format.Border;
//import jxl.format.BorderLineStyle;
import jxl.format.Alignment;
import jxl.format.Orientation;
import jxl.format.UnderlineStyle;

import jxl.write.Label;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


import com.wellpoint.wpd.business.common.util.RuleUtil;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.contract.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.RuleCodeRanges;
import com.wellpoint.wpd.business.contract.locateresult.RuleSequenceResults;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.report.bo.RuleReportBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleClaimCodeSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleClaimSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleCodeSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleDisplayBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleSequenceBO;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author UST Global Servlet for generating the extract rule report excel.
 *  
 */

public class RuleReportServlet extends HttpServlet {
	
	Map<Integer, RuleSequenceBO> ruleSequenceMap =null; 
	RuleSequenceBO ruleSequenceBO;
	
	
	private Map<Integer, RuleCodeSequenceBO> ruleCodeSequenceMap;
	private Map<Integer, RuleClaimSequenceBO> ruleClaimSequenceMap;
	private RuleCodeSequenceBO ruleCodeSequenceBO;
	private RuleClaimSequenceBO ruleClaimSequenceBO;
	private Map<Integer, RuleClaimCodeSequenceBO> ruleClaimCodeSequenceMap;
	private RuleClaimCodeSequenceBO ruleClaimCodeSequenceBO;
   // private RuleReportBO reportBO= null;
    RuleDisplayBO ruleDisplayBO= null;
	private static final long serialVersionUID = 9134950750448323500L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		long totalServletInvokeTimeStart = System.currentTimeMillis();
		try {
			response.setContentType("application/vnd.ms-excel");
			/*
			 * Modified Code to Resolve http, https issue as part of
			 * WAS 7 Migration Project
			 */
			/** Prevent Caching of this page. **/
			//response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store,no-cache");
			// Set the Header Info to tell the browser the format of the file.
			//response.setHeader("Content-Type", "application/vnd.ms-excel");

			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ getFileName(req) + "\"");

			//req.getSession().setAttribute(WebConstants.CONTRACT_REPORT_RUNNING,
			// "Y");

			createExcelReport(req, response);

		} catch (SevereException ex) {
			Logger.logException(new SevereException(
					"Unknown Error on Service call", new ArrayList(), ex
							.getCause()));
		} catch (IOException ex) {
			Logger.logException(new SevereException(
					"Unknown Error on Service call", new ArrayList(), ex
							.getCause()));
		} catch (WriteException ex) {
			Logger.logException(new SevereException(
					"Unknown Error on Service call", new ArrayList(), ex
							.getCause()));
		} catch (EncodingException ex) {
			Logger.logException(new SevereException(
					"Unknown Error on Service call", new ArrayList(), ex
							.getCause()));
		} finally {
			req.getSession().removeAttribute(
					WebConstants.CONTRACT_REPORT_RUNNING);

		}
		long totalServletInvokeTimeEnd = System.currentTimeMillis();
		Logger.logInfo("Total time invoked by the Servlet -->"+(totalServletInvokeTimeEnd-totalServletInvokeTimeStart));
	}
	/**
	 * Method for generating the file name
	 * @param req
	 * @return
	 * @throws EncodingException 
	 */
	private String getFileName(HttpServletRequest req) throws EncodingException {
		Date date = new Date();
		String fileName = null;
		if (WebConstants.CONTRACT_PAGE_FROM
				.equals(req.getParameter("pageFrom"))
				|| WebConstants.PRODUCT_PAGE_FROM.equals(req
						.getParameter("pageFrom"))) {
			fileName = WebConstants.DEFAULT_FILE_NAME + "_"
					+ ESAPI.encoder().encodeForURL(req.getParameter("entityName")) + "_"
					+ WPDStringUtil.convertDateToString(date) + ".xls";
		} else if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
				.getParameter("pageFrom"))) {
			fileName = WebConstants.DEFAULT_FILE_NAME + "_"
					+ ESAPI.encoder().encodeForURL(req.getParameter("entityId")) + "_"
					+ WPDStringUtil.convertDateToString(date) + ".xls";
		}
		return fileName;
	}

	/**
	 * Common method for generating excel
	 * 
	 * @param req
	 *            Request
	 * @param response
	 *            Response
	 * @throws SevereException
	 *             SevereException
	 * @throws IOException
	 *             I/O Exception
	 * @throws WriteException
	 *             WriteException
	 */
	private void createExcelReport(HttpServletRequest req,
			HttpServletResponse response) throws SevereException, IOException,
			WriteException {
		Map<String,RuleDisplayBO> ruleMap=new HashMap<String, RuleDisplayBO>();
		Map<String,RuleDisplayBO> ruleGrpMap=new HashMap<String, RuleDisplayBO>();
		String ruleId = null;
		String benefitComponent;
		String benefit;
		int ruleSeqNumber;
		
		List<RuleReportBO> ruleReportData = null;
		
		ruleDisplayBO= new RuleDisplayBO();
		List groupRuleReportData = null;
		

		Map listMap = null;
		WritableFont font;
		WritableCellFormat fontFormat;
		
		Logger.logInfo("Entity Sys ID:" + req.getParameter("entitySysId"));
		Logger.logInfo("Page From:" + req.getParameter("pageFrom"));
		Logger.logInfo("Extract All Rules:"
				+ req.getParameter("extractAllRules"));
		Logger.logInfo("Extract Exclusion Rules:"
				+ req.getParameter("extractExclusionRules"));
		Logger.logInfo("Extract Denial Rules:"
				+ req.getParameter("extractDenialRules"));
		Logger
				.logInfo("Extract UM Rules:"
						+ req.getParameter("extractUMRules"));
		Logger.logInfo("Extract Header Rules:"
				+ req.getParameter("extractHeaderRules"));
		Logger.logInfo("Individual Rule Extract:"
				+ req.getParameter("entityId"));

		RuleLocateCriteria ruleLocateCriteria = getRuleLocateCriteria(req);

		if (WebConstants.CONTRACT_PAGE_FROM
				.equals(req.getParameter("pageFrom"))) {
			listMap = getContractRuleDetails(ruleLocateCriteria);
			if (null != listMap) {
				ruleReportData = (List) listMap.get(WebConstants.RULES_LIST_KEY_NAME);
				groupRuleReportData = (List) listMap.get(WebConstants.GROUP_RULES_LIST_KEY_NAME);
			}
		}
		if (WebConstants.PRODUCT_PAGE_FROM.equals(req.getParameter("pageFrom"))) {
			listMap = getProductRuleDetails(ruleLocateCriteria);
			if (null != listMap) {
				ruleReportData = (List) listMap.get(WebConstants.RULES_LIST_KEY_NAME);
				groupRuleReportData = (List) listMap.get(WebConstants.GROUP_RULES_LIST_KEY_NAME);
			}
		}
		if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
				.getParameter("pageFrom")))
			ruleReportData = getIndividualRuleDetails(ruleLocateCriteria);

		
		// Generating Excel
		long beforeServletOutputStream = System.currentTimeMillis();
		ServletOutputStream out = response.getOutputStream();
		long afterTimeServletOutputStream = System.currentTimeMillis();
		Logger.logInfo("Time taken in ServletOutputStream   -->"+(afterTimeServletOutputStream-beforeServletOutputStream));
		WritableWorkbook workbook = Workbook.createWorkbook(out);
		long afterWorkBook = System.currentTimeMillis();
		Logger.logInfo("Time taken in WritableWorkbook   -->"+(afterWorkBook-afterTimeServletOutputStream));
		WritableSheet wsheet = null;
		WritableSheet wsheetGroupRule = null;

		
		int ruleExcelRowIndex1 = 1;
		int ruleExcelRowIndex2 = 1;
		int grpExcelRowIndex1 = 1;
		int grpExcelRowIndex2 = 1;
		boolean codeSequenceStatus = false;

		
		int dataSize = 0;
		
		
		if (null != ruleReportData){
			dataSize = ruleReportData.size();
		}
		
		int groupRuleDataSize = 0;
		
		if (null != groupRuleReportData){
			groupRuleDataSize = groupRuleReportData.size();
		}
		long startTimeExtractSheet1 = System.currentTimeMillis();
		if (0 == dataSize) {

			wsheet = workbook.createSheet(WebConstants.DEFAULT_SHEET_NAME, 1);

			createHeadersForRuleExtract(wsheet, new String[] { "****** No Data ******" });

		} else {
			wsheet = workbook.createSheet(WebConstants.DEFAULT_SHEET_NAME, 1);
			createHeadersForRuleExtract(wsheet, new String[] { "RULE_ID", "BENEFIT COMPONENT", "BENEFIT",
					"RULE_TYP_DESC","RULE_SHRT_DESC","RULE_VERSION", "TAGs",
					"RULE_GRP_IND", "RULE_APRVD_DT",
					"RULE_SQNC_NBR", "EXCLSN_IND", "CLAIM_TYPE",
					"PLC_OF_SRVC", "PAT_LOW_AGE", "PAT_HIGH_AGE", "GNDR_CD",
					"PRVDR_ID", "PRVDR_SPCLTY_CD", "BNFT_ACCUM_NM",
					"BNFT_ACCUM_LMT_AMT", "BNFT_ACCUM_LMT_NBR", "NTFCTN_IND",
					"CLNL_RVW_IND", "DOLLAR_LIMIT", "SERVICE_CODE",
					"GRP_STATE", "LEN_OF_STAY", "ITS_SPCLTY_CD",
					"MBR_RELSHP_CDE", "HCPCS_MDFR_2_CD", "HCPCS_MDFR_CD",
					"PRVDR_ST_CD", "BILL_TYP_CD", "TT_CD", "ATCHMT_IND",
					"PAT_MBR_CD", "HOSP_FCIL_CD", "DAYS_FROM_INJRY",
					"DAYS_FROM_ILNS", "HMO_CLS_CD","EDIT_CDE_1", "EDIT_CDE_2",
					"COPAY_IND", "PCT_100_IND","AGE_TYPE_CD", "SRVC_STRT_DT", "SRVC_END_DT",
					"TOTL_CHRG_SIGN_VAL", "TOTL_CHRG_AMT", "MEDCR_ASGNMNT_IND",
					"SPRTG_PROC_CD_IND", "DRG_CD", "DIAG_IND", "PCP_IND",
					"PROV_SPCLTY_IND", "PROV_LCNS_ID", "PROV_MEDCR_ID",
					"BILLG_PROV_NBR", "RNDRG_PROV_NBR", "BILLG_NPI_NBR",
					"RNDRG_NPI_NBR", "ELGBL_EXPNS_SIGN_CD", "ELGBL_EXPNS_AMT",
					"CODE_SQNC NUMER", "HCPT_LOW_VAL", "HCPT_HIGH_VAL",
					"REV_LOW_VAL", "REV_HIGH_VAL", "ICDP_LOW_VAL",
					"ICDP_HIGH_VAL", "ICDP_VRSN_IND", "DIAG_LOW_VAL",
					"DIAG_HIGH_VAL", "DIAG_VRSN_IND", "SRVC_CLS_LOW",
					"SRVC_CLS_HIGH", "LMT_CLS_LOW", "LMT_CLS_HIGH",
					"CLM_SEQ_NUMBER", "CLM_SRVC_CD", "CLM_PROC_MDFR_CD1",
					"CLM_PROC_MDFR_CD2", "CLM_TT_CD", "CLM_PLACE_OF_SRVC",
					"CLM_HMO_CLS_CD", "CLM_SAME_DAY_SVC_IND", 
					 "CLM_DIAG_IND",
					"CLM_SPRTG_PROC_CD_IND", "CLM_CODE_SEQ_NUMBER",
					"CLM_HCPT_LOW_VAL", "CLM_HCPT_HIGH_VAL", "CLM_REV_LOW_VAL",
					"CLM_REV_HIGH_VAL", "CLM_ICDP_LOW_VAL",
					"CLM_ICDP_HIGH_VAL", "CLM_ICDP_VRSN_IND",
					"CLM_DIAG_LOW_VAL", "CLM_DIAG_HIGH_VAL",
					"CLM_DIAG_VRSN_IND", "CLM_SRVC_CLS_LOW",
					"CLM_SRVC_CLS_HIGH", "CLM_LMT_CLS_LOW", "CLM_LMT_CLS_HIGH" });
			 font = new WritableFont(WritableFont.ARIAL, 9,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			 fontFormat = new WritableCellFormat(font);
			
			//fontFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			
			
			
			 ruleMap= RuleUtil.createRuleReportDisplayObj(ruleReportData);
			 
			 // Retriving the rules details and writing to excel
			 if(ruleMap.size()> 0){
				 
				Set ruleMapKeySet=ruleMap.keySet();
				Iterator ruleMapKeySetIterator = ruleMapKeySet.iterator();
				while(ruleMapKeySetIterator.hasNext()){
					Object ruleIdKey = ruleMapKeySetIterator.next();                
					String ruleidMap = ruleIdKey.toString();
					ruleDisplayBO = ruleMap.get(ruleIdKey);
				
               
				if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
						.getParameter("pageFrom"))) {
					addExcelEntry(wsheet, 3, ruleExcelRowIndex1, ruleLocateCriteria
							.getRuleTypeDescription(), fontFormat);
				} else {
					addExcelEntry(wsheet, 3, ruleExcelRowIndex1, ruleDisplayBO
							.getRuleTypeDesc(), fontFormat);
				}
				
				if(null != ruleDisplayBO.getRuleShortDesc()
						&& !"".equals(ruleDisplayBO.getRuleShortDesc())){
				addExcelEntry(wsheet, 4, ruleExcelRowIndex1,
						ruleDisplayBO.getRuleShortDesc(), fontFormat);
				}
				
				
				if(null != ruleDisplayBO.getRuleVersion() 
                		&& !"".equals(ruleDisplayBO.getRuleVersion())){
				addExcelEntry(wsheet, 5, ruleExcelRowIndex1, ruleDisplayBO
						.getRuleVersion(), fontFormat);
				}
				if(null != ruleDisplayBO.getTag() 
                		&& !"".equals(ruleDisplayBO.getTag())){
				addExcelEntry(wsheet, 6, ruleExcelRowIndex1, ruleDisplayBO
						.getTag(), fontFormat);
				
				}
				
					
					
					// Retriving the rule sequence details and writing to excel
					ruleSequenceMap=ruleDisplayBO.getRuleSequenceMap();
					if(null != ruleSequenceMap){
						Set ruleSequenceMapKeySet = ruleSequenceMap.keySet();
						Iterator ruleSequenceMapKeySetIterator = ruleSequenceMapKeySet.iterator();
						while(ruleSequenceMapKeySetIterator.hasNext()){
							
							boolean claimCodeSeqStatus = false;
							boolean claimSeqStatus = false;
							ruleExcelRowIndex2 = ruleExcelRowIndex1;
							Object ruleSequenceIdKey = ruleSequenceMapKeySetIterator.next();                
							String ruleSequenceNumber = ruleSequenceIdKey.toString();
							
							ruleSequenceBO = ruleSequenceMap.get(ruleSequenceIdKey);
							 if(null != ruleDisplayBO.getRuleId() 
				                		&& !"".equals(ruleDisplayBO.getRuleId())){
								addExcelEntry(wsheet, 0, ruleExcelRowIndex1, ruleDisplayBO.getRuleId(),
										fontFormat);
										ruleId=ruleDisplayBO.getRuleId();
				                }
							 
							 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
										.getParameter("pageFrom"))
										&& WebConstants.HEADER_RULE_ASSOCIATED_BC
												.equals(ruleLocateCriteria
														.getHeaderRuleAssociated())) {
									addExcelEntry(wsheet, 1, ruleExcelRowIndex1, ruleLocateCriteria
											.getBenefitComponentName(), fontFormat);
									benefitComponent=ruleLocateCriteria
											.getBenefitComponentName();
								} else {
									
									addExcelEntry(wsheet, 1, ruleExcelRowIndex1, ruleSequenceBO
											.getBenefitComponent(), fontFormat);
									benefitComponent=ruleSequenceBO
											.getBenefitComponent();
								}
							 
							 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
										.getParameter("pageFrom"))
										&& WebConstants.HEADER_RULE_ASSOCIATED_BNFT
												.equals(ruleLocateCriteria
														.getHeaderRuleAssociated())) {
									addExcelEntry(wsheet, 2, ruleExcelRowIndex1, ruleLocateCriteria
											.getBenefitName(), fontFormat);
									benefit=ruleLocateCriteria
											.getBenefitName();
								} else {
									addExcelEntry(wsheet, 2, ruleExcelRowIndex1, ruleSequenceBO
											.getBenefit(), fontFormat);
									benefit=ruleSequenceBO
											.getBenefit();
								}
							 
							 if(null != ruleSequenceBO.getRuleGrpInd() 
									 && !"".equals(ruleSequenceBO.getRuleGrpInd())){
							addExcelEntry(wsheet, 7, ruleExcelRowIndex1,
									ruleSequenceBO.getRuleGrpInd(), fontFormat);
							}
							 
							if(null != ruleSequenceBO.getRuleAprvdDate()
									&& !"".equals(ruleSequenceBO.getRuleAprvdDate())){
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								String appDate=sdf.format(ruleSequenceBO.getRuleAprvdDate());
							addExcelEntry(wsheet, 8, ruleExcelRowIndex1,
									appDate, fontFormat);
							}
							 
							 
							if (0 != ruleSequenceBO.getRuleSequenceNumber()) {
								addExcelEntry(wsheet, 9, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getRuleSequenceNumber()),
										fontFormat);
								ruleSeqNumber=ruleSequenceBO.getRuleSequenceNumber();
							}
							
							
							
							
							
							

							
							if (null != ruleSequenceBO.getExclsnInd()
									&& !"".equals(ruleSequenceBO.getExclsnInd())) {
								addExcelEntry(wsheet, 10, ruleExcelRowIndex1, ruleSequenceBO
										.getExclsnInd(), fontFormat);
							}
							if (null != ruleSequenceBO.getClmType()
									&& !"".equals(ruleSequenceBO.getClmType())) {
								addExcelEntry(wsheet, 11, ruleExcelRowIndex1, ruleSequenceBO
										.getClmType(), fontFormat);
							}
							
							if (null != ruleSequenceBO.getPlaceOfService()
									&& !"".equals(ruleSequenceBO.getPlaceOfService())) {
								addExcelEntry(wsheet, 12, ruleExcelRowIndex1, ruleSequenceBO
										.getPlaceOfService(), fontFormat);
							}
							if (0 != ruleSequenceBO.getPatLowAge()) {
								addExcelEntry(wsheet, 13, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getPatLowAge()),
										fontFormat);
							}
							if (0 != ruleSequenceBO.getPatHighAge()) {
								addExcelEntry(wsheet, 14, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getPatHighAge()),
										fontFormat);
							}
							if (null != ruleSequenceBO.getGenderCode()
									&& !"".equals(ruleSequenceBO.getGenderCode())) {
								addExcelEntry(wsheet, 15, ruleExcelRowIndex1, ruleSequenceBO
										.getGenderCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getProviderId()
									&& !"".equals(ruleSequenceBO.getProviderId())) {
								addExcelEntry(wsheet, 16, ruleExcelRowIndex1, ruleSequenceBO
										.getProviderId(), fontFormat);
							}
							if (null != ruleSequenceBO.getProviderSpecialityCode()
									&& !"".equals(ruleSequenceBO
											.getProviderSpecialityCode())) {
								addExcelEntry(wsheet, 17, ruleExcelRowIndex1, ruleSequenceBO
										.getProviderSpecialityCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getBenefitAccmNum()
									&& !"".equals(ruleSequenceBO.getBenefitAccmNum())) {
								addExcelEntry(wsheet, 18, ruleExcelRowIndex1,ruleSequenceBO
										.getBenefitAccmNum(), fontFormat);
							}
							if(null !=ruleSequenceBO.getBenfitAccumLimtAmnt() 
									&& !"".equals(ruleSequenceBO.getBenfitAccumLimtAmnt())){
							if (Float.parseFloat(ruleSequenceBO.getBenfitAccumLimtAmnt())>0) {
								addExcelEntry(wsheet, 19, ruleExcelRowIndex1, 
										ruleSequenceBO.getBenfitAccumLimtAmnt(),
										fontFormat);
							}
							}
							if (0 != ruleSequenceBO.getBenefitAccumLimtNum()) {
								addExcelEntry(wsheet, 20, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getBenefitAccumLimtNum()),
										fontFormat);
							}
							if (null != ruleSequenceBO.getNtfyOnlyInd()
									&& !"".equals(ruleSequenceBO.getNtfyOnlyInd())) {
								addExcelEntry(wsheet, 21, ruleExcelRowIndex1, ruleSequenceBO
										.getNtfyOnlyInd(), fontFormat);
							}
							if (null != ruleSequenceBO.getClnlRevwInd()
									&& !"".equals(ruleSequenceBO.getClnlRevwInd())) {
								addExcelEntry(wsheet, 22, ruleExcelRowIndex1, ruleSequenceBO
										.getClnlRevwInd(), fontFormat);
							}
							if(null !=ruleSequenceBO.getDlrLimit() 
									&& !"".equals(ruleSequenceBO.getDlrLimit()))
							if (Float.parseFloat(ruleSequenceBO.getDlrLimit())>0) {
								addExcelEntry(wsheet, 23, ruleExcelRowIndex1, ruleSequenceBO.getDlrLimit(),
										fontFormat);
							}
							if (null != ruleSequenceBO.getServiceCode()
									&& !"".equals(ruleSequenceBO.getServiceCode())) {
								addExcelEntry(wsheet, 24, ruleExcelRowIndex1, ruleSequenceBO
										.getServiceCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getGrpSt()
									&& !"".equals(ruleSequenceBO.getGrpSt())) {
								addExcelEntry(wsheet, 25, ruleExcelRowIndex1,ruleSequenceBO
										.getGrpSt(), fontFormat);
							}
							if (0 != ruleSequenceBO.getLenOfStay()) {
								addExcelEntry(wsheet, 26, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getLenOfStay()),
										fontFormat);
							}
							if (null != ruleSequenceBO.getItsSpecialityCode()
									&& !"".equals(ruleSequenceBO
											.getItsSpecialityCode())) {
								addExcelEntry(wsheet, 27, ruleExcelRowIndex1, ruleSequenceBO
										.getItsSpecialityCode(), fontFormat);
							}
							
							if (null != ruleSequenceBO.getMembrRelnCode()
									&& !"".equals(ruleSequenceBO.getMembrRelnCode())) {
								addExcelEntry(wsheet, 28, ruleExcelRowIndex1,ruleSequenceBO
										.getMembrRelnCode(), fontFormat);
							}
							if(null !=ruleSequenceBO.getProcedureModifierSecondaryCode()
									&& !"".equals(ruleSequenceBO.getProcedureModifierSecondaryCode())){
							addExcelEntry(wsheet, 29, ruleExcelRowIndex1, ruleSequenceBO
							.getProcedureModifierSecondaryCode(), fontFormat);
							}
							if(null !=ruleSequenceBO.getPrecedrModifierCode()
									&& !"".equals(ruleSequenceBO.getPrecedrModifierCode())){
					           addExcelEntry(wsheet, 30, ruleExcelRowIndex1, ruleSequenceBO
							.getPrecedrModifierCode(), fontFormat);
							}
							
							if (null != ruleSequenceBO.getProviderStCode()
									&& !"".equals(ruleSequenceBO.getProviderStCode())) {
								addExcelEntry(wsheet, 31, ruleExcelRowIndex1,ruleSequenceBO
										.getProviderStCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getBillTypeCode()
									&& !"".equals(ruleSequenceBO.getBillTypeCode())) {
								addExcelEntry(wsheet, 32, ruleExcelRowIndex1, ruleSequenceBO
										.getBillTypeCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getTtCode()
									&& !"".equals(ruleSequenceBO.getTtCode())) {
								addExcelEntry(wsheet, 33, ruleExcelRowIndex1, ruleSequenceBO.getTtCode(),
										fontFormat);
							}
							if (null != ruleSequenceBO.getAttachmentInd()
									&& !"".equals(ruleSequenceBO.getAttachmentInd())) {
								addExcelEntry(wsheet, 34, ruleExcelRowIndex1, ruleSequenceBO
										.getAttachmentInd(), fontFormat);
							}
							if (null != ruleSequenceBO.getPatMembrCode()
									&& !"".equals(ruleSequenceBO.getPatMembrCode())) {
								addExcelEntry(wsheet, 35, ruleExcelRowIndex1, ruleSequenceBO
										.getPatMembrCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getHosptlFacilCode()
									&& !"".equals(ruleSequenceBO.getHosptlFacilCode())) {
								addExcelEntry(wsheet, 36, ruleExcelRowIndex1, ruleSequenceBO
										.getHosptlFacilCode(), fontFormat);
							}
							if (0 != ruleSequenceBO.getDaysFrmInjury()) {
								addExcelEntry(wsheet, 37, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getDaysFrmInjury()),
										fontFormat);
							}
							if (0 != ruleSequenceBO.getDaysFrmIllness()) {
								addExcelEntry(wsheet, 38, ruleExcelRowIndex1, WPDStringUtil
										.convertIntToString(ruleSequenceBO.getDaysFrmIllness()),
										fontFormat);
							}
							if (null != ruleSequenceBO.getHmoClsCode()
									&& !"".equals(ruleSequenceBO.getHmoClsCode())) {
								addExcelEntry(wsheet, 39, ruleExcelRowIndex1, ruleSequenceBO
										.getHmoClsCode(), fontFormat);
							}
							
							if (null != ruleSequenceBO.getEditCode1()
									&& !"".equals(ruleSequenceBO.getEditCode1())) {
								addExcelEntry(wsheet, 40, ruleExcelRowIndex1, ruleSequenceBO
										.getEditCode1(), fontFormat);
							}
							if (null != ruleSequenceBO.getEditCode2()
									&& !"".equals(ruleSequenceBO.getEditCode2())) {
								addExcelEntry(wsheet, 41, ruleExcelRowIndex1, ruleSequenceBO
										.getEditCode2(), fontFormat);
							}
							if (null != ruleSequenceBO.getCopayIndicator()
									&& !"".equals(ruleSequenceBO.getCopayIndicator())) {
								addExcelEntry(wsheet, 42, ruleExcelRowIndex1,
										ruleSequenceBO.getCopayIndicator(), fontFormat);
							}
							if (null != ruleSequenceBO.getHunPerIndicator()
									&& !"".equals(ruleSequenceBO.getHunPerIndicator())) {
								addExcelEntry(wsheet, 43, ruleExcelRowIndex1, ruleSequenceBO
										.getHunPerIndicator(), fontFormat);
							}
							if(null != ruleSequenceBO.getAgeTypCode() 
									&& !"".equals(ruleSequenceBO.getAgeTypCode())){
								addExcelEntry(wsheet, 44, ruleExcelRowIndex1, ruleSequenceBO
										.getAgeTypCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getServcStartDate()
									&& !"".equals(ruleSequenceBO.getServcStartDate())) {
								
								SimpleDateFormat sdfOne = new SimpleDateFormat("MM/dd/yyyy");
								String strOne = sdfOne.format(ruleSequenceBO
										.getServcStartDate());
								addExcelEntry(wsheet, 45, ruleExcelRowIndex1, strOne
										, fontFormat);
							}
							if(null != ruleSequenceBO.getServcEndDate()
									&& !"".equals(ruleSequenceBO.getServcEndDate())) {
								SimpleDateFormat sdfTwo = new SimpleDateFormat("MM/dd/yyyy");
								String strTwo = sdfTwo.format(ruleSequenceBO
										.getServcEndDate());
								addExcelEntry(wsheet, 46, ruleExcelRowIndex1, strTwo
										, fontFormat);
							}
							
							if (null != ruleSequenceBO.getTotalChargeSign()
									&& !"".equals(ruleSequenceBO.getTotalChargeSign())) {
								addExcelEntry(wsheet, 47, ruleExcelRowIndex1, ruleSequenceBO
										.getTotalChargeSign(), fontFormat);
							}
							if(null != ruleSequenceBO.getTotalChargeAmnt()
									&& !"".equals(ruleSequenceBO.getTotalChargeAmnt())){
							if (Float.parseFloat(ruleSequenceBO.getTotalChargeAmnt())>0) {
								addExcelEntry(wsheet, 48, ruleExcelRowIndex1,ruleSequenceBO
										.getTotalChargeAmnt(), fontFormat);
							}
							}
							if (null != ruleSequenceBO.getMedicareAssgnIndicator()
									&& !"".equals(ruleSequenceBO
											.getMedicareAssgnIndicator())) {
								addExcelEntry(wsheet, 49, ruleExcelRowIndex1, ruleSequenceBO
										.getMedicareAssgnIndicator(), fontFormat);
							}
					        
							if(null != ruleSequenceBO.getSupportHcpIndicator()
									&& !"".equals(ruleSequenceBO.getSupportHcpIndicator())){
								addExcelEntry(wsheet, 50, ruleExcelRowIndex1, ruleSequenceBO
										.getSupportHcpIndicator(), fontFormat);
							}
							if(null != ruleSequenceBO.getDrugCode()
									&& !"".equals(ruleSequenceBO.getDrugCode())){
								addExcelEntry(wsheet, 51, ruleExcelRowIndex1, ruleSequenceBO
										.getDrugCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getDiagnosisIndicator() 
									&& !"".equals(ruleSequenceBO.getDiagnosisIndicator()))
							{
								addExcelEntry(wsheet, 52, ruleExcelRowIndex1,ruleSequenceBO
										.getDiagnosisIndicator(), fontFormat);
							}
							if (null != ruleSequenceBO.getPcpIndicator() 
									&& !"".equals(ruleSequenceBO.getPcpIndicator()))
							{
								addExcelEntry(wsheet, 53, ruleExcelRowIndex1, ruleSequenceBO
										.getPcpIndicator(), fontFormat);
							}
							
							if (null != ruleSequenceBO.getProviderSpecialityIndicator() 
									&& !"".equals(ruleSequenceBO.getProviderSpecialityIndicator()))
							{
								addExcelEntry(wsheet, 54, ruleExcelRowIndex1, ruleSequenceBO
										.getProviderSpecialityIndicator(), fontFormat);
							}
							
							if (ruleSequenceBO.getProvLncsId() != null)
							{
								addExcelEntry(wsheet, 55, ruleExcelRowIndex1, ruleSequenceBO
										.getProvLncsId()+"", fontFormat);
							}
							if (ruleSequenceBO.getProvMedcrId() != null)
							{
								addExcelEntry(wsheet, 56, ruleExcelRowIndex1, ruleSequenceBO
										.getProvMedcrId()+"", fontFormat);
							}
							if(ruleSequenceBO.getBillgProvNbr() !=null)
							{
								addExcelEntry(wsheet, 57, ruleExcelRowIndex1, ruleSequenceBO
										.getBillgProvNbr()+"", fontFormat);
							}
							if (ruleSequenceBO.getRndrgProvNbr() !=null)
							{
								addExcelEntry(wsheet, 58, ruleExcelRowIndex1, ruleSequenceBO
										.getRndrgProvNbr()+"", fontFormat);
							}
							if (ruleSequenceBO.getBillgNpiNbr() !=null)
							{
								addExcelEntry(wsheet, 59, ruleExcelRowIndex1, ruleSequenceBO
										.getBillgNpiNbr()+"", fontFormat);
							}
							if (ruleSequenceBO.getRndrgNpiNbr() !=null)
							{
								addExcelEntry(wsheet, 60, ruleExcelRowIndex1, ruleSequenceBO
										.getRndrgNpiNbr()+"", fontFormat);
							}
							if (null != ruleSequenceBO.getElgblExpansSignCode() 
									&& !"".equals(ruleSequenceBO.getElgblExpansSignCode()))
							{
								addExcelEntry(wsheet, 61, ruleExcelRowIndex1, ruleSequenceBO
										.getElgblExpansSignCode(), fontFormat);
							}
							if (null != ruleSequenceBO.getElgblExpansAmt() 
									&& !"".equals(ruleSequenceBO.getElgblExpansAmt())){
							if (Float.parseFloat(ruleSequenceBO.getElgblExpansAmt())>0)
							{
								addExcelEntry(wsheet, 62, ruleExcelRowIndex1, ruleSequenceBO
										.getElgblExpansAmt()+"", fontFormat);
							}
							}
						
							
							// Retriving the rule code details and writing to excel
							ruleCodeSequenceMap = ruleSequenceBO.getRuleCodeSequenceMap();
							Set ruleCodeSequenceMapKeySet = ruleCodeSequenceMap.keySet();
							Iterator ruleCodeSequenceMapKeySetIterator = ruleCodeSequenceMapKeySet.iterator();
							
							while(ruleCodeSequenceMapKeySetIterator.hasNext()){
								
								Object ruleCodeSequenceIdKey = ruleCodeSequenceMapKeySetIterator.next();                
								String ruleCodeSequenceNumber = ruleCodeSequenceIdKey.toString();
								ruleCodeSequenceBO = ruleCodeSequenceMap.get(ruleCodeSequenceIdKey);
								codeSequenceStatus = false;
								
								 if(null != ruleId 
					                		&& !"".equals(ruleId)){
									addExcelEntry(wsheet, 0, ruleExcelRowIndex1, ruleId,
											fontFormat);
					                }
								 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
											.getParameter("pageFrom"))
											&& WebConstants.HEADER_RULE_ASSOCIATED_BC
													.equals(ruleLocateCriteria
															.getHeaderRuleAssociated())) {
										addExcelEntry(wsheet, 1, ruleExcelRowIndex1, ruleLocateCriteria
												.getBenefitComponentName(), fontFormat);
									} else {
										
										addExcelEntry(wsheet, 1, ruleExcelRowIndex1, ruleSequenceBO
												.getBenefitComponent(), fontFormat);
									}
								 
								 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
											.getParameter("pageFrom"))
											&& WebConstants.HEADER_RULE_ASSOCIATED_BNFT
													.equals(ruleLocateCriteria
															.getHeaderRuleAssociated())) {
										addExcelEntry(wsheet, 2, ruleExcelRowIndex1, ruleLocateCriteria
												.getBenefitName(), fontFormat);
									} else {
										addExcelEntry(wsheet, 2, ruleExcelRowIndex1, ruleSequenceBO
												.getBenefit(), fontFormat);
									}
								 
								 
								if (0 != ruleSequenceBO.getRuleSequenceNumber()) {
									addExcelEntry(wsheet, 9, ruleExcelRowIndex1, WPDStringUtil
											.convertIntToString(ruleSequenceBO.getRuleSequenceNumber()),
											fontFormat);
								}
								
								
								 
								if (0 != ruleCodeSequenceBO.getCdSqncNbr()) {
									codeSequenceStatus = true;
									addExcelEntry(wsheet, 63, ruleExcelRowIndex1, WPDStringUtil
											.convertIntToString(ruleCodeSequenceBO.getCdSqncNbr()),
											fontFormat);
								}
								if (null != ruleCodeSequenceBO.getLineHcptLowVal() && !"".equals(ruleCodeSequenceBO.getLineHcptLowVal()) && 
										null != ruleCodeSequenceBO.getLineHcptHighVal() && !"".equals(ruleCodeSequenceBO.getLineHcptHighVal()))
								{
									codeSequenceStatus = true;
									addExcelEntry(wsheet, 64, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineHcptLowVal(), fontFormat);
									addExcelEntry(wsheet, 65, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineHcptHighVal(), fontFormat);
							    }
								if (null != ruleCodeSequenceBO.getLineRevLowVal() && !"".equals(ruleCodeSequenceBO.getLineRevLowVal()) && 
										null != ruleCodeSequenceBO.getLineRevHighVal() && !"".equals(ruleCodeSequenceBO.getLineRevHighVal()))
								{
									codeSequenceStatus = true;
									addExcelEntry(wsheet, 66, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineRevLowVal(), fontFormat);
									addExcelEntry(wsheet, 67, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineRevHighVal(), fontFormat);
								}
								
								
								List codeList = ruleCodeSequenceBO.getCodeList();
								if(null != codeList){
								for (int j = 0; j < codeList.size(); j++) {
									RuleCodeRanges ruleCodeRanges = (RuleCodeRanges) codeList
											.get(j);
									if ((null != ruleCodeRanges.getCodeType() && !ruleCodeRanges
											.getCodeType().equalsIgnoreCase("DUM"))
											&& !"".equals(ruleCodeRanges.getCodeType())) {
										
										if(ruleCodeRanges
											.getCodeType().equalsIgnoreCase("ICD")){
											codeSequenceStatus = true;
										addExcelEntry(wsheet, 68, ruleExcelRowIndex1, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
										addExcelEntry(wsheet,69, ruleExcelRowIndex1, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
										addExcelEntry(wsheet,70, ruleExcelRowIndex1, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
										}
										else{
											codeSequenceStatus = true;
											addExcelEntry(wsheet, 71, ruleExcelRowIndex1, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
											addExcelEntry(wsheet, 72, ruleExcelRowIndex1, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
											addExcelEntry(wsheet, 73, ruleExcelRowIndex1, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
										}
										
									}
								}
							  }
								
								
								if (null != ruleCodeSequenceBO.getLineSrvcClsLow() && !"".equals(ruleCodeSequenceBO.getLineSrvcClsLow()) && 
										null != ruleCodeSequenceBO.getLineSrvcClsHigh() && !"".equals(ruleCodeSequenceBO.getLineSrvcClsHigh()))
								{
									codeSequenceStatus = true;
									addExcelEntry(wsheet, 74, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineSrvcClsLow(), fontFormat);
									addExcelEntry(wsheet, 75, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineSrvcClsHigh(), fontFormat);
								}
								if (null != ruleCodeSequenceBO.getLineLmtClsLow() && !"".equals(ruleCodeSequenceBO.getLineLmtClsLow()) && 
										null != ruleCodeSequenceBO.getLineLmtClsHigh() && !"".equals(ruleCodeSequenceBO.getLineLmtClsHigh()))
								{
									codeSequenceStatus = true;
									addExcelEntry(wsheet, 76, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineLmtClsLow(), fontFormat);
									addExcelEntry(wsheet, 77, ruleExcelRowIndex1, ruleCodeSequenceBO
											.getLineLmtClsHigh(), fontFormat);
								}
								if(codeSequenceStatus)
									
									ruleExcelRowIndex1++;
								    
							}//rulecodesequenceBO
							
							//Retriving the rule claim details and writing to excel
							
							ruleClaimSequenceMap = ruleSequenceBO.getRuleClaimSequenceMap();
							Set ruleClaimSequenceMapKeySet = ruleClaimSequenceMap.keySet();
							Iterator ruleClaimSequenceMapKeySetIterator = ruleClaimSequenceMapKeySet.iterator();
							while(ruleClaimSequenceMapKeySetIterator.hasNext()){
								Object ruleClaimSequenceIdKey = ruleClaimSequenceMapKeySetIterator.next();                
								String ruleClaimSequenceNumber = ruleClaimSequenceIdKey.toString();
								ruleClaimSequenceBO = ruleClaimSequenceMap.get(ruleClaimSequenceIdKey);
								
								 if(null != ruleId 
					                		&& !"".equals(ruleId)){
									addExcelEntry(wsheet, 0, ruleExcelRowIndex2, ruleId,
											fontFormat);
					                }
								 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
											.getParameter("pageFrom"))
											&& WebConstants.HEADER_RULE_ASSOCIATED_BC
													.equals(ruleLocateCriteria
															.getHeaderRuleAssociated())) {
										addExcelEntry(wsheet, 1, ruleExcelRowIndex2, ruleLocateCriteria
												.getBenefitComponentName(), fontFormat);
									} else {
										
										addExcelEntry(wsheet, 1, ruleExcelRowIndex2, ruleSequenceBO
												.getBenefitComponent(), fontFormat);
									}
								 
								 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
											.getParameter("pageFrom"))
											&& WebConstants.HEADER_RULE_ASSOCIATED_BNFT
													.equals(ruleLocateCriteria
															.getHeaderRuleAssociated())) {
										addExcelEntry(wsheet, 2, ruleExcelRowIndex2, ruleLocateCriteria
												.getBenefitName(), fontFormat);
									} else {
										addExcelEntry(wsheet, 2, ruleExcelRowIndex2, ruleSequenceBO
												.getBenefit(), fontFormat);
									}
								 
								 
								if (0 != ruleSequenceBO.getRuleSequenceNumber()) {
									addExcelEntry(wsheet, 9, ruleExcelRowIndex2, WPDStringUtil
											.convertIntToString(ruleSequenceBO.getRuleSequenceNumber()),
											fontFormat);
								}
								if (0 != ruleClaimSequenceBO.getClmSqncNbr()) {
									
									claimSeqStatus = true;
									addExcelEntry(wsheet, 78, ruleExcelRowIndex2, WPDStringUtil
											.convertIntToString(ruleClaimSequenceBO.getClmSqncNbr()),
											fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmServiceCode() 
										&& !"".equals(ruleClaimSequenceBO.getClmServiceCode()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 79, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmServiceCode(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmProcessModifierCode() 
										&& !"".equals(ruleClaimSequenceBO.getClmProcessModifierCode()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 80, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmProcessModifierCode(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmProcessModifierSecondaryCode() 
										&& !"".equals(ruleClaimSequenceBO.getClmProcessModifierSecondaryCode()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 81, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmProcessModifierSecondaryCode(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmttCd() 
										&& !"".equals(ruleClaimSequenceBO.getClmttCd()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 82, ruleExcelRowIndex2,ruleClaimSequenceBO
											.getClmttCd(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmPosCd() 
										&& !"".equals(ruleClaimSequenceBO.getClmPosCd()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 83, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmPosCd(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmHMOClassCode() 
										&& !"".equals(ruleClaimSequenceBO.getClmHMOClassCode()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 84, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmHMOClassCode(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmSameDaySrvcInd() 
										&& !"".equals(ruleClaimSequenceBO.getClmSameDaySrvcInd()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 85, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmSameDaySrvcInd(), fontFormat);
								}
								
								
								if (null != ruleClaimSequenceBO.getClaimDiagnosisIndicator() 
										&& !"".equals(ruleClaimSequenceBO.getClaimDiagnosisIndicator()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 86, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClaimDiagnosisIndicator(), fontFormat);
								}
								if (null != ruleClaimSequenceBO.getClmSprtgProcCdInd() 
										&& !"".equals(ruleClaimSequenceBO.getClmSprtgProcCdInd()))
								{
									claimSeqStatus = true;
									addExcelEntry(wsheet, 87, ruleExcelRowIndex2, ruleClaimSequenceBO
											.getClmSprtgProcCdInd(), fontFormat);
								}
								
								// Retriving the rule claim code details and writing to excel
								
								ruleClaimCodeSequenceMap = ruleClaimSequenceBO.getRuleClaimCodeSequenceMap();
								Set ruleClaimCodeSequenceMapKeySet = ruleClaimCodeSequenceMap.keySet();
								Iterator ruleClaimCodeSequenceMapKeySetIterator = ruleClaimCodeSequenceMapKeySet.iterator();
								
								while(ruleClaimCodeSequenceMapKeySetIterator.hasNext()){
									Object ruleClaimCodeSequenceIdKey = ruleClaimCodeSequenceMapKeySetIterator.next();                
									String ruleClaimCodeSequenceNumber = ruleClaimCodeSequenceIdKey.toString();
									ruleClaimCodeSequenceBO = ruleClaimCodeSequenceMap.get(ruleClaimCodeSequenceIdKey);
									
									 if(null != ruleId 
						                		&& !"".equals(ruleId)){
										addExcelEntry(wsheet, 0, ruleExcelRowIndex2, ruleId,
												fontFormat);
						                }
									 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
												.getParameter("pageFrom"))
												&& WebConstants.HEADER_RULE_ASSOCIATED_BC
														.equals(ruleLocateCriteria
																.getHeaderRuleAssociated())) {
											addExcelEntry(wsheet, 1, ruleExcelRowIndex2, ruleLocateCriteria
													.getBenefitComponentName(), fontFormat);
										} else {
											
											addExcelEntry(wsheet, 1, ruleExcelRowIndex2, ruleSequenceBO
													.getBenefitComponent(), fontFormat);
										}
									 
									 if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
												.getParameter("pageFrom"))
												&& WebConstants.HEADER_RULE_ASSOCIATED_BNFT
														.equals(ruleLocateCriteria
																.getHeaderRuleAssociated())) {
											addExcelEntry(wsheet, 2, ruleExcelRowIndex2, ruleLocateCriteria
													.getBenefitName(), fontFormat);
										} else {
											addExcelEntry(wsheet, 2, ruleExcelRowIndex2, ruleSequenceBO
													.getBenefit(), fontFormat);
										}
									 
									 
									if (0 != ruleSequenceBO.getRuleSequenceNumber()) {
										addExcelEntry(wsheet, 9, ruleExcelRowIndex2, WPDStringUtil
												.convertIntToString(ruleSequenceBO.getRuleSequenceNumber()),
												fontFormat);
									}
									if (0 != ruleClaimSequenceBO.getClmSqncNbr()) {
										
										claimSeqStatus = true;
										addExcelEntry(wsheet, 78, ruleExcelRowIndex2, WPDStringUtil
												.convertIntToString(ruleClaimSequenceBO.getClmSqncNbr()),
												fontFormat);
									}
									 
									if (0 != ruleClaimCodeSequenceBO.getClmCdSqncNbr()) {
										claimCodeSeqStatus = true;
										addExcelEntry(wsheet, 88, ruleExcelRowIndex2, WPDStringUtil
												.convertIntToString(ruleClaimCodeSequenceBO.getClmCdSqncNbr()),
												fontFormat);
									}
									if (null != ruleClaimCodeSequenceBO.getClmHcptLowVal() && !"".equals(ruleClaimCodeSequenceBO.getClmHcptLowVal()) && 
											null != ruleClaimCodeSequenceBO.getClmHcptHighVal() && !"".equals(ruleClaimCodeSequenceBO.getClmHcptHighVal()))
									{
										claimCodeSeqStatus = true;
										addExcelEntry(wsheet, 89, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmHcptLowVal(), fontFormat);
										addExcelEntry(wsheet, 90, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmHcptHighVal(), fontFormat);
									}
									if (null != ruleClaimCodeSequenceBO.getClmRevLowVal() && !"".equals(ruleClaimCodeSequenceBO.getClmRevLowVal()) && 
											null != ruleClaimCodeSequenceBO.getClmRevHighVal() && !"".equals(ruleClaimCodeSequenceBO.getClmRevHighVal()))
									{
										claimCodeSeqStatus = true;
										addExcelEntry(wsheet, 91, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmRevLowVal(), fontFormat);
										addExcelEntry(wsheet, 92, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmRevHighVal(), fontFormat);
									}
									
									List claimCodeList = ruleClaimCodeSequenceBO.getClaimCodeList();
									if(null != claimCodeList){
									for (int j = 0; j < claimCodeList.size(); j++) {
										RuleCodeRanges ruleCodeRanges = (RuleCodeRanges) claimCodeList
												.get(j);
										if ((null != ruleCodeRanges.getCodeType() && !ruleCodeRanges
												.getCodeType().equalsIgnoreCase("DUM")) 
												&& !"".equals(ruleCodeRanges.getCodeType())) {
											if(ruleCodeRanges.getCodeType().equalsIgnoreCase("ICD")){
												claimCodeSeqStatus = true;
												addExcelEntry(wsheet, 93, ruleExcelRowIndex2, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
												addExcelEntry(wsheet, 94, ruleExcelRowIndex2, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
												addExcelEntry(wsheet, 95, ruleExcelRowIndex2, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
											}
											else{
												claimCodeSeqStatus = true;
												addExcelEntry(wsheet, 96, ruleExcelRowIndex2, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
												addExcelEntry(wsheet, 97, ruleExcelRowIndex2, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
												addExcelEntry(wsheet, 98, ruleExcelRowIndex2, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
												
											}
										}
									}
								  }
									
									if (null != ruleClaimCodeSequenceBO.getClmServiceClassLow() && !"".equals(ruleClaimCodeSequenceBO.getClmServiceClassLow()) && 
											null != ruleClaimCodeSequenceBO.getClmServiceClassHigh() && !"".equals(ruleClaimCodeSequenceBO.getClmServiceClassHigh()))
									{
										claimCodeSeqStatus = true;
										addExcelEntry(wsheet, 99, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmServiceClassLow(), fontFormat);
										addExcelEntry(wsheet, 100, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmServiceClassHigh(), fontFormat);
									}
									if (null != ruleClaimCodeSequenceBO.getClmLimitClassLow() && !"".equals(ruleClaimCodeSequenceBO.getClmLimitClassLow()) && 
											null != ruleClaimCodeSequenceBO.getClmLimitClassHigh() && !"".equals(ruleClaimCodeSequenceBO.getClmLimitClassHigh()))
									{
										claimCodeSeqStatus = true;
										addExcelEntry(wsheet, 101, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmLimitClassLow(), fontFormat);
										
										addExcelEntry(wsheet, 102, ruleExcelRowIndex2, ruleClaimCodeSequenceBO
												.getClmLimitClassHigh(), fontFormat);
									}
									if(claimCodeSeqStatus)
										
										ruleExcelRowIndex2++;							
								}//end ruleClaimCodeSequenceBO
								
								if(claimCodeSeqStatus)
								{
									ruleExcelRowIndex2=ruleExcelRowIndex2-1;
								}
								if(claimSeqStatus){
								 ruleExcelRowIndex2++;
								}
							} //end ruleClaimSequenceBO
							
						
							if(codeSequenceStatus)
							{
								ruleExcelRowIndex1 = ruleExcelRowIndex1-1;
							}
							if(ruleExcelRowIndex2 > ruleExcelRowIndex1){
								ruleExcelRowIndex1=ruleExcelRowIndex2;
							}
							else{
								ruleExcelRowIndex1++;
							}
				         
						}// end rule SequenceBO
					}//if ends
				 } //ruleMap while ends
		       }// ruleMap If ends	
		}// else
					
		long endTimeExtractSheet1 = System.currentTimeMillis();
		Logger.logInfo("Time taken to extract the first excel sheet -->"+(endTimeExtractSheet1-startTimeExtractSheet1));
		/**	A Second work sheet is created for Extract All functionality to extract the 
		 * Group Rules associated with the Contract/product */
		
		if (WebConstants.CONTRACT_PAGE_FROM
				.equals(req.getParameter("pageFrom"))
				|| WebConstants.PRODUCT_PAGE_FROM.equals(req
						.getParameter("pageFrom"))) {
			long startTimeExtractSheet2 = System.currentTimeMillis();
			if (null == groupRuleReportData || groupRuleReportData.size() == 0) {
				wsheetGroupRule = workbook.createSheet(
						WebConstants.DEFAULT_SHEET_NAME_GRP_RULE, 1);
				createHeadersForGroupRuleExtract(wsheetGroupRule,
						new String[] { "****** No Data ******" });

			} else {
				wsheetGroupRule = workbook.createSheet(
						WebConstants.DEFAULT_SHEET_NAME_GRP_RULE, 1);
				createHeadersForGroupRuleExtract(wsheetGroupRule, new String[] { "GRP_RULE_ID",
						"RULE_ID",  "BENEFIT COMPONENT", "BENEFIT",
						"RULE_TYP_DESC","RULE_SHRT_DESC",
						"RULE_VERSION", "TAGs","RULE_GRP_IND", "RULE_APRVD_DT",
						"RULE_SQNC_NBR","EXCLSN_IND", "RULE_TYP_CD", 
						 "CLAIM_TYPE",
						"PLC_OF_SRVC", "PAT_LOW_AGE", "PAT_HIGH_AGE",
						"GNDR_CD", "PRVDR_ID", "PRVDR_SPCLTY_CD",
						"BNFT_ACCUM_NM", "BNFT_ACCUM_LMT_AMT",
						"BNFT_ACCUM_LMT_NBR", "NTFY_ONLY_IND", "CLNL_RVW_IND",
						"DOLLAR_LIMIT", "SERVICE_CODE", "GRP_STATE",
						"LEN_OF_STAY", "ITS_SPCLTY_CD", "MBR_RELSHP_CDE",
						"HCPCS_MDFR_2_CD", "HCPCS_MDFR_CD", "PRVDR_ST_CD",
						"BILL_TYP_CD", "TT_CD", "ATCHMT_IND", "PAT_MBR_CD",
						"HOSP_FCIL_CD", "DAYS_FROM_INJRY", "DAYS_FROM_ILNS",
						"HMO_CLS_CD", "EDIT_CDE_1", "EDIT_CDE_2","COPAY_IND","PCT_100_IND",
						"AGE_TYPE_CD", "SRVC_STRT_DT", "SRVC_END_DT",
						"TOTL_CHRG_SIGN_VAL", "TOTL_CHRG_AMT",
						"MEDCR_ASGNMNT_IND", "SPRTG_PROC_CD_IND", "DRG_CD",
						"DIAG_IND", "PCP_IND", "PROV_SPCLTY_IND",
						"PROV_LCNS_ID", "PROV_MEDCR_ID", "BILLG_PROV_NBR",
						"RNDRG_PROV_NBR", "BILLG_NPI_NBR", "RNDRG_NPI_NBR",
						"ELGBL_EXPNS_SIGN_CD", "ELGBL_EXPNS_AMT", 
						"CODE_SQNC NUMER", "HCPT_LOW_VAL", "HCPT_HIGH_VAL",
						"REV_LOW_VAL", "REV_HIGH_VAL", "ICDP_LOW_VAL",
						"ICDP_HIGH_VAL", "ICDP_VRSN_IND", "DIAG_LOW_VAL",
						"DIAG_HIGH_VAL", "DIAG_VRSN_IND", "SRVC_CLS_LOW",
						"SRVC_CLS_HIGH", "LMT_CLS_LOW", "LMT_CLS_HIGH",
						"CLM_SEQ_NUMBER", "CLM_SRVC_CD", "CLM_PROC_MDFR_CD1",
						"CLM_PROC_MDFR_CD2", "CLM_TT_CD", "CLM_PLACE_OF_SRVC",
						"CLM_HMO_CLS_CD", "CLM_SAME_DAY_SVC_IND",
						"CLM_SPRTG_PROC_CD_IND", "CLM_CODE_SEQ_NUMBER",
						"CLM_HCPT_LOW_VAL", "CLM_HCPT_HIGH_VAL",
						"CLM_REV_LOW_VAL", "CLM_REV_HIGH_VAL",
						"CLM_ICDP_LOW_VAL", "CLM_ICDP_HIGH_VAL",
						"CLM_ICDP_VRSN_IND", "CLM_DIAG_LOW_VAL",
						"CLM_DIAG_HIGH_VAL", "CLM_DIAG_VRSN_IND",
						"CLM_SRVC_CLS_LOW", "CLM_SRVC_CLS_HIGH","CLM_LMT_CLS_LOW", "CLM_LMT_CLS_HIGH" });
				 font = new WritableFont(WritableFont.ARIAL, 9,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
				 fontFormat = new WritableCellFormat(font);
				
				 codeSequenceStatus = false;
				 ruleGrpMap=RuleUtil.createRuleReportDisplayObj(groupRuleReportData);
					
				 // Retriving the Group rules details and writing to excel
					if(ruleGrpMap.size()> 0){
						Set ruleMapKeySet=ruleGrpMap.keySet();
						Iterator ruleMapKeySetIterator = ruleMapKeySet.iterator();
						while(ruleMapKeySetIterator.hasNext()){
							Object ruleIdKey = ruleMapKeySetIterator.next();                
							String ruleidMap = ruleIdKey.toString();
							ruleDisplayBO = ruleGrpMap.get(ruleIdKey);
					
					if(null != ruleDisplayBO.getRuleTypeDesc()
							&& !"".equals(ruleDisplayBO.getRuleTypeDesc())){
					addExcelEntry(wsheetGroupRule, 4, grpExcelRowIndex1,
							ruleDisplayBO.getRuleTypeDesc(), fontFormat);
					}
					
					if(null != ruleDisplayBO.getRuleShortDesc()
							&& !"".equals(ruleDisplayBO.getRuleShortDesc())){
					addExcelEntry(wsheetGroupRule, 5, grpExcelRowIndex1,
							ruleDisplayBO.getRuleShortDesc(), fontFormat);
					}
					
					if(null != ruleDisplayBO.getRuleVersion()
							&& !"".equals(ruleDisplayBO.getRuleVersion())){
					addExcelEntry(wsheetGroupRule,6,grpExcelRowIndex1, 
							ruleDisplayBO.getRuleVersion(),fontFormat);
					}
					if(null != ruleDisplayBO.getTag()
							&& !"".equals(ruleDisplayBO.getTag())){
					addExcelEntry(wsheetGroupRule,7,grpExcelRowIndex1, 
							ruleDisplayBO.getTag(),fontFormat);
					}
					
					 // Retriving the Group rules sequence details and writing to excel
					
					ruleSequenceMap=ruleDisplayBO.getRuleSequenceMap();
					if(null != ruleSequenceMap){
						Set ruleSequenceMapKeySet = ruleSequenceMap.keySet();
						Iterator ruleSequenceMapKeySetIterator = ruleSequenceMapKeySet.iterator();
						while(ruleSequenceMapKeySetIterator.hasNext()){
							//tempCdCount = ruleExcelRowIndex1;
							//ruleExcelRowIndex2 = ruleExcelRowIndex1;
							boolean claimCodeSeqStatus = false;
							boolean claimSeqStatus = false;
							grpExcelRowIndex2 = grpExcelRowIndex1;
							Object ruleSequenceIdKey = ruleSequenceMapKeySetIterator.next();                
							String ruleSequenceNumber = ruleSequenceIdKey.toString();
							ruleSequenceBO = ruleSequenceMap.get(ruleSequenceIdKey);

							if(null != ruleDisplayBO.getGrpRuleId()
									&& !"".equals(ruleDisplayBO.getGrpRuleId())){
							addExcelEntry(wsheetGroupRule, 0, grpExcelRowIndex1,
									ruleDisplayBO.getGrpRuleId(), fontFormat);
							}
							
							if(null != ruleDisplayBO.getRuleId()
									 && !"".equals(ruleDisplayBO.getRuleId())){
							addExcelEntry(wsheetGroupRule, 1, grpExcelRowIndex1,
									ruleDisplayBO.getRuleId(), fontFormat);
							}
							
							if(null != ruleSequenceBO.getBenefitComponent()
									&& !"".equals(ruleSequenceBO.getBenefitComponent())){
							addExcelEntry(wsheetGroupRule, 2, grpExcelRowIndex1,
									ruleSequenceBO.getBenefitComponent(), fontFormat);
							}
							if(null != ruleSequenceBO.getBenefit()
									&& !"".equals(ruleSequenceBO.getBenefit())){
							addExcelEntry(wsheetGroupRule, 3, grpExcelRowIndex1,
									ruleSequenceBO.getBenefit(), fontFormat);
							}
							
							
							if(null != ruleSequenceBO.getRuleGrpInd() 
									 && !"".equals(ruleSequenceBO.getRuleGrpInd())){
							addExcelEntry(wsheetGroupRule, 8, grpExcelRowIndex1,
									ruleSequenceBO.getRuleGrpInd(), fontFormat);
							}
							if(null != ruleSequenceBO.getRuleAprvdDate()
									&& !"".equals(ruleSequenceBO.getRuleAprvdDate())){
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								String appDate=sdf.format(ruleSequenceBO.getRuleAprvdDate());
							addExcelEntry(wsheetGroupRule, 9, grpExcelRowIndex1,
									appDate, fontFormat);
							}
							
							
					  if(0 != ruleSequenceBO.getRuleSequenceNumber()){
					   addExcelEntry(wsheetGroupRule, 10, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getRuleSequenceNumber()), fontFormat);
					}
					
					if(null != ruleSequenceBO.getExclsnInd()
							 && !"".equals(ruleSequenceBO.getExclsnInd())){
					addExcelEntry(wsheetGroupRule, 11, grpExcelRowIndex1,
							ruleSequenceBO.getExclsnInd(), fontFormat);
					}
					if(null != ruleSequenceBO.getRuleTypeCode()
							&& !"".equals(ruleSequenceBO.getRuleTypeCode())){
					addExcelEntry(wsheetGroupRule, 12, grpExcelRowIndex1,
							ruleSequenceBO.getRuleTypeCode(), fontFormat);
					}
					
					if(null != ruleSequenceBO.getClmType()
							 && !"".equals(ruleSequenceBO.getClmType())){
					addExcelEntry(wsheetGroupRule, 13, grpExcelRowIndex1,
							ruleSequenceBO.getClmType(), fontFormat);
					}
					if(null != ruleSequenceBO.getPlaceOfService() 
							&& !"".equals(ruleSequenceBO.getPlaceOfService())){
					addExcelEntry(wsheetGroupRule, 14, grpExcelRowIndex1,
							ruleSequenceBO.getPlaceOfService(), fontFormat);
					}
					if(0 != ruleSequenceBO
									.getPatLowAge()){
					addExcelEntry(wsheetGroupRule, 15, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getPatLowAge()), fontFormat);
					}
					if(0 != ruleSequenceBO
									.getPatHighAge()){
					addExcelEntry(wsheetGroupRule, 16, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getPatHighAge()), fontFormat);
					}
					if(null != ruleSequenceBO.getGenderCode() 
							 && !"".equals(ruleSequenceBO.getGenderCode())){
					addExcelEntry(wsheetGroupRule, 17, grpExcelRowIndex1,
							ruleSequenceBO.getGenderCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getProviderId() 
							&& !"".equals(ruleSequenceBO.getProviderId())){
					addExcelEntry(wsheetGroupRule, 18, grpExcelRowIndex1,
							ruleSequenceBO.getProviderId(), fontFormat);
					}
					if(null != ruleSequenceBO.getProviderSpecialityCode()
							&& !"".equals(ruleSequenceBO.getProviderSpecialityCode())){
					addExcelEntry(wsheetGroupRule, 19, grpExcelRowIndex1,
							ruleSequenceBO.getProviderSpecialityCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getBenefitAccmNum()
							&& !"".equals(ruleSequenceBO.getBenefitAccmNum())){
					addExcelEntry(wsheetGroupRule, 20, grpExcelRowIndex1,
							ruleSequenceBO.getBenefitAccmNum(), fontFormat);
					}
					if(null != ruleSequenceBO.getBenfitAccumLimtAmnt()
							&& !"".equals(ruleSequenceBO.getBenfitAccumLimtAmnt())){
					if(Float.parseFloat(ruleSequenceBO.getBenfitAccumLimtAmnt())>0 ){
					addExcelEntry(wsheetGroupRule, 21, grpExcelRowIndex1,
							ruleSequenceBO
									.getBenfitAccumLimtAmnt(), fontFormat);
					}
					}
					if(0 != ruleSequenceBO.getBenefitAccumLimtNum()){
					addExcelEntry(wsheetGroupRule, 22, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getBenefitAccumLimtNum()), fontFormat);
					}
					if(null != ruleSequenceBO.getNtfyOnlyInd()
							&& !"".equals(ruleSequenceBO.getNtfyOnlyInd())){
					addExcelEntry(wsheetGroupRule, 23, grpExcelRowIndex1,
							ruleSequenceBO.getNtfyOnlyInd(), fontFormat);
					}
					if(null != ruleSequenceBO.getClnlRevwInd()
							 && !"".equals(ruleSequenceBO.getClnlRevwInd())){
					addExcelEntry(wsheetGroupRule, 24, grpExcelRowIndex1,
							ruleSequenceBO.getClnlRevwInd(), fontFormat);
					}
					if(null != ruleSequenceBO.getDlrLimit()
							 && !"".equals(ruleSequenceBO.getDlrLimit())){
					if(Float.parseFloat(ruleSequenceBO.getDlrLimit())>0 ){
					addExcelEntry(wsheetGroupRule, 25, grpExcelRowIndex1,
							ruleSequenceBO
									.getDlrLimit(), fontFormat);
					}
					}
					if(null != ruleSequenceBO.getServiceCode() 
							&& !"".equals(ruleSequenceBO.getServiceCode())){
					addExcelEntry(wsheetGroupRule, 26, grpExcelRowIndex1,
							ruleSequenceBO.getServiceCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getGrpSt() 
							&& !"".equals(ruleSequenceBO.getGrpSt())){
					addExcelEntry(wsheetGroupRule, 27, grpExcelRowIndex1,
							ruleSequenceBO.getGrpSt(), fontFormat);
					}
					if(0 != ruleSequenceBO
									.getLenOfStay() ){
					addExcelEntry(wsheetGroupRule, 28, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getLenOfStay()), fontFormat);
					}
					if(null != ruleSequenceBO.getItsSpecialityCode() 
							&& !"".equals(ruleSequenceBO.getItsSpecialityCode())){
					addExcelEntry(wsheetGroupRule, 29, grpExcelRowIndex1,
							ruleSequenceBO.getItsSpecialityCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getMembrRelnCode() 
							 && !"".equals(ruleSequenceBO.getMembrRelnCode())){
					addExcelEntry(wsheetGroupRule, 30, grpExcelRowIndex1,
							ruleSequenceBO.getMembrRelnCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getProcedureModifierSecondaryCode() 
							 && !"".equals(ruleSequenceBO.getProcedureModifierSecondaryCode())){
					addExcelEntry(wsheetGroupRule, 31, grpExcelRowIndex1,
							ruleSequenceBO.getProcedureModifierSecondaryCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getPrecedrModifierCode() 
							 && !"".equals(ruleSequenceBO.getPrecedrModifierCode())){
					addExcelEntry(wsheetGroupRule, 32, grpExcelRowIndex1,
							ruleSequenceBO.getPrecedrModifierCode(), fontFormat);
					}
				
					if(null != ruleSequenceBO.getProviderStCode() 
							&& !"".equals(ruleSequenceBO.getProviderStCode())){
					addExcelEntry(wsheetGroupRule, 33, grpExcelRowIndex1,
							ruleSequenceBO.getProviderStCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getBillTypeCode()
							&& !"".equals(ruleSequenceBO.getBillTypeCode())){ 
					addExcelEntry(wsheetGroupRule, 34, grpExcelRowIndex1,
							ruleSequenceBO.getBillTypeCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getTtCode()
							&& !"".equals(ruleSequenceBO.getTtCode())){
					addExcelEntry(wsheetGroupRule, 35, grpExcelRowIndex1,
							ruleSequenceBO.getTtCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getAttachmentInd()
							&& !"".equals(ruleSequenceBO.getAttachmentInd())){
					addExcelEntry(wsheetGroupRule, 36, grpExcelRowIndex1,
							ruleSequenceBO.getAttachmentInd(), fontFormat);
					}
					if(null !=ruleSequenceBO.getPatMembrCode()
							&& !"".equals(ruleSequenceBO.getPatMembrCode())){
					addExcelEntry(wsheetGroupRule, 37, grpExcelRowIndex1,
							ruleSequenceBO.getPatMembrCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getHosptlFacilCode()
							&& !"".equals(ruleSequenceBO.getHosptlFacilCode())){
					addExcelEntry(wsheetGroupRule, 38, grpExcelRowIndex1,
							ruleSequenceBO.getHosptlFacilCode(), fontFormat);
					}
					if(0 != ruleSequenceBO.getDaysFrmInjury()){
					addExcelEntry(wsheetGroupRule, 39, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getDaysFrmInjury()), fontFormat);
					}
					if(0 !=ruleSequenceBO.getDaysFrmIllness()){
					addExcelEntry(wsheetGroupRule, 40, grpExcelRowIndex1,
							WPDStringUtil.convertIntToString(ruleSequenceBO
									.getDaysFrmIllness()), fontFormat);
					}
					if(null != ruleSequenceBO.getHmoClsCode() 
							&& !"".equals(ruleSequenceBO.getHmoClsCode())){
					addExcelEntry(wsheetGroupRule, 41, grpExcelRowIndex1,
							ruleSequenceBO.getHmoClsCode(), fontFormat);
					}
					if(null !=ruleSequenceBO.getEditCode1()
							&& !"".equals(ruleSequenceBO.getEditCode1())){
					addExcelEntry(wsheetGroupRule, 42, grpExcelRowIndex1,
							ruleSequenceBO.getEditCode1(), fontFormat);
					}
					if(null !=ruleSequenceBO.getEditCode2()
							&& !"".equals(ruleSequenceBO.getEditCode2())){
					addExcelEntry(wsheetGroupRule, 43, grpExcelRowIndex1,
							ruleSequenceBO.getEditCode2(), fontFormat);
					}
					if (null != ruleSequenceBO.getCopayIndicator()
							&& !"".equals(ruleSequenceBO.getCopayIndicator())) {
						addExcelEntry(wsheet, 44, ruleExcelRowIndex1,
								ruleSequenceBO.getCopayIndicator(), fontFormat);
					}
					if (null != ruleSequenceBO.getHunPerIndicator()
							&& !"".equals(ruleSequenceBO.getHunPerIndicator())) {
						addExcelEntry(wsheet, 45, ruleExcelRowIndex1, ruleSequenceBO
								.getHunPerIndicator(), fontFormat);
					}
					
					if(null != ruleSequenceBO.getAgeTypCode() 
							&& !"".equals(ruleSequenceBO.getAgeTypCode())){
						addExcelEntry(wsheetGroupRule, 46, grpExcelRowIndex1, ruleSequenceBO
								.getAgeTypCode(), fontFormat);
					}
					if(null != ruleSequenceBO.getServcStartDate()
							&& !"".equals(ruleSequenceBO.getServcStartDate())){
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						String stDate=sdf.format(ruleSequenceBO
								.getServcStartDate());
						addExcelEntry(wsheetGroupRule, 47, grpExcelRowIndex1,stDate , fontFormat);
					}
					if(null != ruleSequenceBO.getServcEndDate()
							&& !"".equals(ruleSequenceBO.getServcEndDate())){
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						String endDate = sdf.format(ruleSequenceBO
								.getServcEndDate());
						addExcelEntry(wsheetGroupRule, 48, grpExcelRowIndex1,endDate , fontFormat);
					}
					
					if (null != ruleSequenceBO.getTotalChargeSign()
							&& !"".equals(ruleSequenceBO.getTotalChargeSign())) {
						addExcelEntry(wsheetGroupRule, 47, grpExcelRowIndex1, ruleSequenceBO
								.getTotalChargeSign(), fontFormat);
					}
					if (null != ruleSequenceBO.getTotalChargeAmnt()
							&& !"".equals(ruleSequenceBO.getTotalChargeAmnt())) {
					if (Float.parseFloat(ruleSequenceBO.getTotalChargeAmnt())>0) {
						addExcelEntry(wsheetGroupRule, 49, grpExcelRowIndex1,ruleSequenceBO
								.getTotalChargeAmnt(), fontFormat);
					}
					}
					if (null != ruleSequenceBO.getMedicareAssgnIndicator()
							&& !"".equals(ruleSequenceBO
									.getMedicareAssgnIndicator())) {
						addExcelEntry(wsheetGroupRule, 50, grpExcelRowIndex1, ruleSequenceBO
								.getMedicareAssgnIndicator(), fontFormat);
					}
					
					if (null != ruleSequenceBO.getSupportHcpIndicator()
							&& !"".equals(ruleSequenceBO
									.getSupportHcpIndicator())) {
						addExcelEntry(wsheetGroupRule, 51, grpExcelRowIndex1, ruleSequenceBO
								.getSupportHcpIndicator(), fontFormat);
					}
			
					
					if(null != ruleSequenceBO.getDrugCode()
							&& !"".equals(ruleSequenceBO.getDrugCode())){
						addExcelEntry(wsheetGroupRule, 52, grpExcelRowIndex1, ruleSequenceBO
								.getDrugCode(), fontFormat);
					}
					if (null != ruleSequenceBO.getDiagnosisIndicator() 
							&& !"".equals(ruleSequenceBO.getDiagnosisIndicator()))
					{
						addExcelEntry(wsheetGroupRule, 54, grpExcelRowIndex1,ruleSequenceBO
								.getDiagnosisIndicator(), fontFormat);
					}
					if (null != ruleSequenceBO.getPcpIndicator() 
							&& !"".equals(ruleSequenceBO.getPcpIndicator()))
					{
						addExcelEntry(wsheetGroupRule, 55, grpExcelRowIndex1, ruleSequenceBO
								.getPcpIndicator(), fontFormat);
					}
					
					if (null != ruleSequenceBO.getProviderSpecialityIndicator() 
							&& !"".equals(ruleSequenceBO.getProviderSpecialityIndicator()))
					{
						addExcelEntry(wsheetGroupRule, 56, grpExcelRowIndex1, ruleSequenceBO
								.getProviderSpecialityIndicator(), fontFormat);
					}
					
					if (ruleSequenceBO.getProvLncsId() != null)
					{
						addExcelEntry(wsheetGroupRule, 57, grpExcelRowIndex1, ruleSequenceBO
								.getProvLncsId()+"", fontFormat);
					}
					if (ruleSequenceBO.getProvMedcrId() != null)
					{
						addExcelEntry(wsheetGroupRule, 58, grpExcelRowIndex1, ruleSequenceBO
								.getProvMedcrId()+"", fontFormat);
					}
					if(ruleSequenceBO.getBillgProvNbr() !=null){
						addExcelEntry(wsheetGroupRule, 59, grpExcelRowIndex1, ruleSequenceBO
								.getBillgProvNbr()+"", fontFormat);
					}
					if (ruleSequenceBO.getRndrgProvNbr()!=null)
					{
						addExcelEntry(wsheetGroupRule, 60, grpExcelRowIndex1, ruleSequenceBO
								.getRndrgProvNbr()+"", fontFormat);
					}
					if (ruleSequenceBO.getBillgNpiNbr()!=null)
					{
						addExcelEntry(wsheetGroupRule, 61, grpExcelRowIndex1, ruleSequenceBO
								.getBillgNpiNbr()+"", fontFormat);
					}
					if (ruleSequenceBO.getRndrgNpiNbr()!=null)
					{
						addExcelEntry(wsheetGroupRule, 62, grpExcelRowIndex1, ruleSequenceBO
								.getRndrgNpiNbr()+"", fontFormat);
					}
					if (null != ruleSequenceBO.getElgblExpansSignCode() 
							&& !"".equals(ruleSequenceBO.getElgblExpansSignCode()))
					{
						addExcelEntry(wsheetGroupRule, 63, grpExcelRowIndex1, ruleSequenceBO
								.getElgblExpansSignCode(), fontFormat);
					}
					if (null != ruleSequenceBO.getElgblExpansAmt() 
							&& !"".equals(ruleSequenceBO.getElgblExpansAmt())){
					if (Float.parseFloat(ruleSequenceBO.getElgblExpansAmt())>0)
					{
						addExcelEntry(wsheetGroupRule, 64, grpExcelRowIndex1, ruleSequenceBO
								.getElgblExpansAmt()+"", fontFormat);
					}
					}
					
					
					 // Retriving the Group rule code details and writing to excel
					
					ruleCodeSequenceMap = ruleSequenceBO.getRuleCodeSequenceMap();
					Set ruleCodeSequenceMapKeySet = ruleCodeSequenceMap.keySet();
					Iterator ruleCodeSequenceMapKeySetIterator = ruleCodeSequenceMapKeySet.iterator();
					
					while(ruleCodeSequenceMapKeySetIterator.hasNext()){
						
						Object ruleCodeSequenceIdKey = ruleCodeSequenceMapKeySetIterator.next();                
						String ruleCodeSequenceNumber = ruleCodeSequenceIdKey.toString();
						ruleCodeSequenceBO = ruleCodeSequenceMap.get(ruleCodeSequenceIdKey);
						codeSequenceStatus = false;
						if(null != ruleDisplayBO.getGrpRuleId()
								&& !"".equals(ruleDisplayBO.getGrpRuleId())){
						addExcelEntry(wsheetGroupRule, 0, grpExcelRowIndex1,
								ruleDisplayBO.getGrpRuleId(), fontFormat);
						}
						
						if(null != ruleDisplayBO.getRuleId()
								 && !"".equals(ruleDisplayBO.getRuleId())){
						addExcelEntry(wsheetGroupRule, 1, grpExcelRowIndex1,
								ruleDisplayBO.getRuleId(), fontFormat);
						}
						
						if(null != ruleSequenceBO.getBenefitComponent()
								&& !"".equals(ruleSequenceBO.getBenefitComponent())){
						addExcelEntry(wsheetGroupRule, 2, grpExcelRowIndex1,
								ruleSequenceBO.getBenefitComponent(), fontFormat);
						}
						if(null != ruleSequenceBO.getBenefit()
								&& !"".equals(ruleSequenceBO.getBenefit())){
						addExcelEntry(wsheetGroupRule, 3, grpExcelRowIndex1,
								ruleSequenceBO.getBenefit(), fontFormat);
						}
						 if(0 != ruleSequenceBO.getRuleSequenceNumber()){
							   addExcelEntry(wsheetGroupRule, 10, grpExcelRowIndex1,
									WPDStringUtil.convertIntToString(ruleSequenceBO
											.getRuleSequenceNumber()), fontFormat);
							}
						 
						
					if (0 != ruleCodeSequenceBO.getCdSqncNbr()) {
						codeSequenceStatus = true;
						addExcelEntry(wsheetGroupRule, 65, grpExcelRowIndex1, WPDStringUtil
								.convertIntToString(ruleCodeSequenceBO.getCdSqncNbr()),
								fontFormat);
					}
					if (null != ruleCodeSequenceBO.getLineHcptLowVal() && !"".equals(ruleCodeSequenceBO.getLineHcptLowVal()) && 
							null != ruleCodeSequenceBO.getLineHcptHighVal() && !"".equals(ruleCodeSequenceBO.getLineHcptHighVal()))
					{
						codeSequenceStatus = true;
						addExcelEntry(wsheetGroupRule, 66, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineHcptLowVal(), fontFormat);
						addExcelEntry(wsheetGroupRule, 67, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineHcptHighVal(), fontFormat);
				    }
					if (null != ruleCodeSequenceBO.getLineRevLowVal() && !"".equals(ruleCodeSequenceBO.getLineRevLowVal()) && 
							null != ruleCodeSequenceBO.getLineRevHighVal() && !"".equals(ruleCodeSequenceBO.getLineRevHighVal()))
					{
						codeSequenceStatus = true;
						addExcelEntry(wsheetGroupRule, 68, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineRevLowVal(), fontFormat);
						addExcelEntry(wsheetGroupRule, 69, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineRevHighVal(), fontFormat);
					}
					
					List codeList = ruleCodeSequenceBO.getCodeList();
					if(null != codeList){
					for (int j = 0; j < codeList.size(); j++) {
						RuleCodeRanges ruleCodeRanges = (RuleCodeRanges) codeList
								.get(j);
						if ((null != ruleCodeRanges.getCodeType() && !ruleCodeRanges
								.getCodeType().equalsIgnoreCase("DUM"))
								&& !"".equals(ruleCodeRanges.getCodeType())) {
							
							if(ruleCodeRanges
								.getCodeType().equalsIgnoreCase("ICD")){
								codeSequenceStatus = true;
							addExcelEntry(wsheetGroupRule, 70, grpExcelRowIndex1, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
							addExcelEntry(wsheetGroupRule,71, grpExcelRowIndex1, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
							addExcelEntry(wsheetGroupRule,72, grpExcelRowIndex1, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
							}
							else{
								codeSequenceStatus = true;
								addExcelEntry(wsheetGroupRule, 73, grpExcelRowIndex1, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
								addExcelEntry(wsheetGroupRule, 74, grpExcelRowIndex1, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
								addExcelEntry(wsheetGroupRule, 75, grpExcelRowIndex1, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
							}
							
						}
					}
				  }
					
					
					if (null != ruleCodeSequenceBO.getLineSrvcClsLow() && !"".equals(ruleCodeSequenceBO.getLineSrvcClsLow()) && 
							null != ruleCodeSequenceBO.getLineSrvcClsHigh() && !"".equals(ruleCodeSequenceBO.getLineSrvcClsHigh()))
					{
						codeSequenceStatus = true;
						addExcelEntry(wsheetGroupRule, 76, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineSrvcClsLow(), fontFormat);
						addExcelEntry(wsheetGroupRule, 77, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineSrvcClsHigh(), fontFormat);
					}
					if (null != ruleCodeSequenceBO.getLineLmtClsLow() && !"".equals(ruleCodeSequenceBO.getLineLmtClsLow()) && 
							null != ruleCodeSequenceBO.getLineLmtClsHigh() && !"".equals(ruleCodeSequenceBO.getLineLmtClsHigh()))
					{
						codeSequenceStatus = true;
						addExcelEntry(wsheetGroupRule, 77, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineLmtClsLow(), fontFormat);
						addExcelEntry(wsheetGroupRule, 78, grpExcelRowIndex1, ruleCodeSequenceBO
								.getLineLmtClsHigh(), fontFormat);
					}
					if(codeSequenceStatus)
					grpExcelRowIndex1++;
				}// codesequence ends
					
					
					 // Retriving the Group rule claim details and writing to excel
					
					ruleClaimSequenceMap = ruleSequenceBO.getRuleClaimSequenceMap();
					Set ruleClaimSequenceMapKeySet = ruleClaimSequenceMap.keySet();
					Iterator ruleClaimSequenceMapKeySetIterator = ruleClaimSequenceMapKeySet.iterator();
					while(ruleClaimSequenceMapKeySetIterator.hasNext()){
						Object ruleClaimSequenceIdKey = ruleClaimSequenceMapKeySetIterator.next();                
						String ruleClaimSequenceNumber = ruleClaimSequenceIdKey.toString();
						ruleClaimSequenceBO = ruleClaimSequenceMap.get(ruleClaimSequenceIdKey);
						if(null != ruleDisplayBO.getGrpRuleId()
								&& !"".equals(ruleDisplayBO.getGrpRuleId())){
						addExcelEntry(wsheetGroupRule, 0, grpExcelRowIndex2,
								ruleDisplayBO.getGrpRuleId(), fontFormat);
						}
						
						if(null != ruleDisplayBO.getRuleId()
								 && !"".equals(ruleDisplayBO.getRuleId())){
						addExcelEntry(wsheetGroupRule, 1, grpExcelRowIndex2,
								ruleDisplayBO.getRuleId(), fontFormat);
						}
						
						if(null != ruleSequenceBO.getBenefitComponent()
								&& !"".equals(ruleSequenceBO.getBenefitComponent())){
						addExcelEntry(wsheetGroupRule, 2, grpExcelRowIndex2,
								ruleSequenceBO.getBenefitComponent(), fontFormat);
						}
						if(null != ruleSequenceBO.getBenefit()
								&& !"".equals(ruleSequenceBO.getBenefit())){
						addExcelEntry(wsheetGroupRule, 3, grpExcelRowIndex2,
								ruleSequenceBO.getBenefit(), fontFormat);
						}
						 if(0 != ruleSequenceBO.getRuleSequenceNumber()){
							   addExcelEntry(wsheetGroupRule, 10, grpExcelRowIndex2,
									WPDStringUtil.convertIntToString(ruleSequenceBO
											.getRuleSequenceNumber()), fontFormat);
							}
							
					
						 if (0 != ruleClaimSequenceBO.getClmSqncNbr()) {
								
								claimSeqStatus = true;
								addExcelEntry(wsheetGroupRule, 79, grpExcelRowIndex2, WPDStringUtil
										.convertIntToString(ruleClaimSequenceBO.getClmSqncNbr()),
										fontFormat);
							}
						 
					if (null != ruleClaimSequenceBO.getClmServiceCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmServiceCode()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 80, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmServiceCode(), fontFormat);
					}
					if (null != ruleClaimSequenceBO.getClmProcessModifierCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmProcessModifierCode()))
					{
					    claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 81, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmProcessModifierCode(), fontFormat);
					}
					if (null != ruleClaimSequenceBO.getClmProcessModifierSecondaryCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmProcessModifierSecondaryCode()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 82, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmProcessModifierSecondaryCode(), fontFormat);
					}
					if (null != ruleClaimSequenceBO.getClmttCd() 
							&& !"".equals(ruleClaimSequenceBO.getClmttCd()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 83, grpExcelRowIndex2,ruleClaimSequenceBO
								.getClmttCd(), fontFormat);
					}
					if (null != ruleClaimSequenceBO.getClmPosCd() 
							&& !"".equals(ruleClaimSequenceBO.getClmPosCd()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 84, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmPosCd(), fontFormat);
					}
					if (null != ruleClaimSequenceBO.getClmHMOClassCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmHMOClassCode()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 85, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmHMOClassCode(), fontFormat);
					}
					if (null != ruleClaimSequenceBO.getClmSameDaySrvcInd() 
							&& !"".equals(ruleClaimSequenceBO.getClmSameDaySrvcInd()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 86, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmSameDaySrvcInd(), fontFormat);
					}
					
					if (null != ruleClaimSequenceBO.getClaimDiagnosisIndicator() 
							&& !"".equals(ruleClaimSequenceBO.getClaimDiagnosisIndicator()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 87, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClaimDiagnosisIndicator(), fontFormat);
					}
					
					
					if (null != ruleClaimSequenceBO.getClmSprtgProcCdInd() 
							&& !"".equals(ruleClaimSequenceBO.getClmSprtgProcCdInd()))
					{
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 88, grpExcelRowIndex2, ruleClaimSequenceBO
								.getClmSprtgProcCdInd(), fontFormat);
					}
					
					 // Retriving the Group rule claim code details and writing to excel
					
					ruleClaimCodeSequenceMap = ruleClaimSequenceBO.getRuleClaimCodeSequenceMap();
					Set ruleClaimCodeSequenceMapKeySet = ruleClaimCodeSequenceMap.keySet();
					Iterator ruleClaimCodeSequenceMapKeySetIterator = ruleClaimCodeSequenceMapKeySet.iterator();
					
					while(ruleClaimCodeSequenceMapKeySetIterator.hasNext()){
						Object ruleClaimCodeSequenceIdKey = ruleClaimCodeSequenceMapKeySetIterator.next();                
						String ruleClaimCodeSequenceNumber = ruleClaimCodeSequenceIdKey.toString();
						ruleClaimCodeSequenceBO = ruleClaimCodeSequenceMap.get(ruleClaimCodeSequenceIdKey);
						if(null != ruleDisplayBO.getGrpRuleId()
								&& !"".equals(ruleDisplayBO.getGrpRuleId())){
						addExcelEntry(wsheetGroupRule, 0, grpExcelRowIndex2,
								ruleDisplayBO.getGrpRuleId(), fontFormat);
						}
						
						if(null != ruleDisplayBO.getRuleId()
								 && !"".equals(ruleDisplayBO.getRuleId())){
						addExcelEntry(wsheetGroupRule, 1, grpExcelRowIndex2,
								ruleDisplayBO.getRuleId(), fontFormat);
						}
						
						if(null != ruleSequenceBO.getBenefitComponent()
								&& !"".equals(ruleSequenceBO.getBenefitComponent())){
						addExcelEntry(wsheetGroupRule, 2, grpExcelRowIndex2,
								ruleSequenceBO.getBenefitComponent(), fontFormat);
						}
						if(null != ruleSequenceBO.getBenefit()
								&& !"".equals(ruleSequenceBO.getBenefit())){
						addExcelEntry(wsheetGroupRule, 3, grpExcelRowIndex2,
								ruleSequenceBO.getBenefit(), fontFormat);
						}
						 if(0 != ruleSequenceBO.getRuleSequenceNumber()){
							   addExcelEntry(wsheetGroupRule, 10, grpExcelRowIndex2,
									WPDStringUtil.convertIntToString(ruleSequenceBO
											.getRuleSequenceNumber()), fontFormat);
							}
							
					
					if (0 != ruleClaimSequenceBO.getClmSqncNbr()) {
						
						claimSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 79, grpExcelRowIndex2, WPDStringUtil
								.convertIntToString(ruleClaimSequenceBO.getClmSqncNbr()),
								fontFormat);
					}
					
					if (0 != ruleClaimCodeSequenceBO.getClmCdSqncNbr()) {
						claimCodeSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 89, grpExcelRowIndex2, WPDStringUtil
								.convertIntToString(ruleClaimCodeSequenceBO.getClmCdSqncNbr()),
								fontFormat);
					}
					if (null != ruleClaimCodeSequenceBO.getClmHcptLowVal() && !"".equals(ruleClaimCodeSequenceBO.getClmHcptLowVal()) && 
							null != ruleClaimCodeSequenceBO.getClmHcptHighVal() && !"".equals(ruleClaimCodeSequenceBO.getClmHcptHighVal()))
					{
						claimCodeSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 90, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmHcptLowVal(), fontFormat);
						addExcelEntry(wsheetGroupRule, 91, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmHcptHighVal(), fontFormat);
					}
					if (null != ruleClaimCodeSequenceBO.getClmRevLowVal() && !"".equals(ruleClaimCodeSequenceBO.getClmRevLowVal()) && 
							null != ruleClaimCodeSequenceBO.getClmRevHighVal() && !"".equals(ruleClaimCodeSequenceBO.getClmRevHighVal()))
					{
						claimCodeSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 92, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmRevLowVal(), fontFormat);
						addExcelEntry(wsheetGroupRule, 93, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmRevHighVal(), fontFormat);
					}
					
					List claimCodeList = ruleClaimCodeSequenceBO.getClaimCodeList();
					if(null != claimCodeList){
					for (int j = 0; j < claimCodeList.size(); j++) {
						RuleCodeRanges ruleCodeRanges = (RuleCodeRanges) claimCodeList
								.get(j);
						if ((null != ruleCodeRanges.getCodeType() && !ruleCodeRanges
								.getCodeType().equalsIgnoreCase("DUM"))
								&& !"".equals(ruleCodeRanges.getCodeType())) {
							if(ruleCodeRanges.getCodeType().equalsIgnoreCase("ICD")){
								claimCodeSeqStatus = true;
								addExcelEntry(wsheetGroupRule, 94, grpExcelRowIndex2, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
								addExcelEntry(wsheetGroupRule, 95, grpExcelRowIndex2, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
								addExcelEntry(wsheetGroupRule, 96, grpExcelRowIndex2, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
							}
							else{
								claimCodeSeqStatus = true;
								addExcelEntry(wsheetGroupRule, 97, grpExcelRowIndex2, ruleCodeRanges.getCodeTypeLowVal(), fontFormat);
								addExcelEntry(wsheetGroupRule, 98, grpExcelRowIndex2, ruleCodeRanges.getCodeTypeHighVal(), fontFormat);
								addExcelEntry(wsheetGroupRule, 99, grpExcelRowIndex2, ruleCodeRanges.getIcdVersionIndicator(), fontFormat);
								
							}
						}
					}
				  }
					
				
					if (null != ruleClaimCodeSequenceBO.getClmServiceClassLow() && !"".equals(ruleClaimCodeSequenceBO.getClmServiceClassLow()) && 
							null != ruleClaimCodeSequenceBO.getClmServiceClassHigh() && !"".equals(ruleClaimCodeSequenceBO.getClmServiceClassHigh()))
					{
						claimCodeSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 100, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmServiceClassLow(), fontFormat);
						addExcelEntry(wsheetGroupRule, 101, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmServiceClassHigh(), fontFormat);
					}
					if (null != ruleClaimCodeSequenceBO.getClmLimitClassLow() && !"".equals(ruleClaimCodeSequenceBO.getClmLimitClassLow()) && 
							null != ruleClaimCodeSequenceBO.getClmLimitClassHigh() && !"".equals(ruleClaimCodeSequenceBO.getClmLimitClassHigh()))
					{
						claimCodeSeqStatus = true;
						addExcelEntry(wsheetGroupRule, 102, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmLimitClassLow(), fontFormat);
						
						addExcelEntry(wsheetGroupRule, 103, grpExcelRowIndex2, ruleClaimCodeSequenceBO
								.getClmLimitClassHigh(), fontFormat);
					}
					
					if(claimCodeSeqStatus)
						
						grpExcelRowIndex2++;	
				}//claim code sequence ends
					
					if(claimCodeSeqStatus)
					{
						grpExcelRowIndex2=grpExcelRowIndex2-1;
					}
					if(claimSeqStatus){
					 grpExcelRowIndex2++;
					}
			}// claim sequence ends
					
					if(codeSequenceStatus)
					{
						grpExcelRowIndex1 = grpExcelRowIndex1-1;
					}
					if(grpExcelRowIndex2 > grpExcelRowIndex1){
						grpExcelRowIndex1=grpExcelRowIndex2;
					}
					else{
						grpExcelRowIndex1++;
					}	
				}//rule sequence ends
			}//if closes
		}//	ruleMap while ends			
	}// ruleMap if ends				
		}//else
			long endTimeExtractSheet2 = System.currentTimeMillis();
			Logger.logInfo("Time taken to extract the second excel sheet -->"+(endTimeExtractSheet2-startTimeExtractSheet2));

	}//if closes
		long beforeWrite = System.currentTimeMillis();
		workbook.write();
		long afterWrite = System.currentTimeMillis();
		Logger.logInfo("Time taken in Writing   -->"+(afterWrite-beforeWrite));
		out.flush();
		long afterFlush = System.currentTimeMillis();
		Logger.logInfo("Time taken in Flush   -->"+(afterFlush-afterWrite));	
		workbook.close();
		long afterClose = System.currentTimeMillis();
		Logger.logInfo("Time taken in Close   -->"+(afterClose-afterFlush));
		if (null != out)
			out.close();
		
		Logger.logInfo("Extract Rule Excel Generated.........");
	}
	
		
	/**
	 * Creating the headers for excel work sheet
	 * @param sheet WritableSheet object
	 * @param headers String array with the name of headers.
	 * @throws WriteException
	 */

	protected void createHeaders(WritableSheet sheet, String[] headers)
			throws WriteException {
		WritableFont columnFont = new WritableFont(WritableFont.ARIAL, 9,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		WritableCellFormat columnHeadingFmt = new WritableCellFormat(columnFont);
		columnHeadingFmt.setBackground(jxl.format.Colour.GRAY_25);
		columnHeadingFmt.setOrientation(Orientation.PLUS_90);
		
		

		for (int i = 0; i < headers.length; i++) {
			
				Label label = new Label(i, 0, headers[i], columnHeadingFmt);
				sheet.addCell(label);
			
			}
		
		
	}
	
	
	protected void createHeadersForRuleExtract(WritableSheet sheet, String[] headers)
			throws WriteException {
		WritableFont columnFont = new WritableFont(WritableFont.ARIAL, 9,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		WritableCellFormat columnHeadingFmt = new WritableCellFormat(columnFont);
		columnHeadingFmt.setBackground(jxl.format.Colour.GRAY_25);
		columnHeadingFmt.setOrientation(Orientation.PLUS_90);
		columnHeadingFmt.setAlignment(Alignment.LEFT);
		
		
		WritableCellFormat columnHeadingFmt1 = new WritableCellFormat(columnFont);
		columnHeadingFmt1.setBackground(jxl.format.Colour.GRAY_50);
		columnHeadingFmt1.setOrientation(Orientation.PLUS_90);
		columnHeadingFmt1.setAlignment(Alignment.LEFT);
		
		WritableCellFormat columnHeadingFmt3 = new WritableCellFormat(columnFont);
		columnHeadingFmt3.setBackground(jxl.format.Colour.GREY_40_PERCENT);
		columnHeadingFmt3.setOrientation(Orientation.PLUS_90);
		columnHeadingFmt3.setAlignment(Alignment.LEFT);
		
		
		
		//columnHeadingFmt.setBorder(Border.ALL, BorderLineStyle.THIN);//removed to improve the performance

		for (int i = 0; i < headers.length; i++) {
			if(i>=0 && i<=9){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt);
				sheet.addCell(label);
				}
			else if(i>9 && i<=62){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt3);
				sheet.addCell(label);
				}
			
			else if(i>62 && i<=77){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt1);
				sheet.addCell(label);
				}
			else if(i>77 && i<=87){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt);
				sheet.addCell(label);
				}
			else{
				Label label = new Label(i, 0, headers[i], columnHeadingFmt3);
				sheet.addCell(label);
				}
					
			
			}
		
		
	}

	
	protected void createHeadersForGroupRuleExtract(WritableSheet sheet, String[] headers)
			throws WriteException {
		WritableFont columnFont = new WritableFont(WritableFont.ARIAL, 9,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK);
		WritableCellFormat columnHeadingFmt = new WritableCellFormat(columnFont);
		columnHeadingFmt.setBackground(jxl.format.Colour.GRAY_25);
		columnHeadingFmt.setOrientation(Orientation.PLUS_90);
		columnHeadingFmt.setAlignment(Alignment.LEFT);
		
		WritableCellFormat columnHeadingFmt1 = new WritableCellFormat(columnFont);
		columnHeadingFmt1.setBackground(jxl.format.Colour.GRAY_50);
		columnHeadingFmt1.setOrientation(Orientation.PLUS_90);
		columnHeadingFmt1.setAlignment(Alignment.LEFT);
		
		WritableCellFormat columnHeadingFmt3 = new WritableCellFormat(columnFont);
		columnHeadingFmt3.setBackground(jxl.format.Colour.GREY_40_PERCENT);
		columnHeadingFmt3.setOrientation(Orientation.PLUS_90);
		columnHeadingFmt3.setAlignment(Alignment.LEFT);
		
		
		
		//columnHeadingFmt.setBorder(Border.ALL, BorderLineStyle.THIN);//removed to improve the performance

		for (int i = 0; i < headers.length; i++) {
			if(i>=0 && i<=10){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt);
				sheet.addCell(label);
				}
			else if(i>10 && i<=64){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt3);
				sheet.addCell(label);
				}
			
			else if(i>64 && i<=79){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt1);
				sheet.addCell(label);
				}
			else if(i>79 && i<=88){
				Label label = new Label(i, 0, headers[i], columnHeadingFmt);
				sheet.addCell(label);
				}
			else{
				Label label = new Label(i, 0, headers[i], columnHeadingFmt3);
				sheet.addCell(label);
				}
					
			
			}
		
		
	}

	/**
	 * Method creates RuleLocateCriteria object based on the input
	 * 
	 * @param req
	 * @return
	 */

	private RuleLocateCriteria getRuleLocateCriteria(HttpServletRequest req) {
		
		RuleLocateCriteria ruleLocateCriteria = new RuleLocateCriteria();
		
		int entitySysID = 0;
		String entityId = req.getParameter("entityId");
		
		if (null != req.getParameter("entitySysId"))
			entitySysID = Integer.parseInt(req.getParameter("entitySysId"));
		
		if (WebConstants.CONTRACT_PAGE_FROM
				.equals(req.getParameter("pageFrom")))
			ruleLocateCriteria.setDateSegmentId(entitySysID);
		
		if (WebConstants.PRODUCT_PAGE_FROM.equals(req.getParameter("pageFrom")))
			ruleLocateCriteria.setProductSysId(entitySysID);
		
		if (WebConstants.INDIVIDUAL_RULE_PAGE_FROM.equals(req
				.getParameter("pageFrom")))
			ruleLocateCriteria.setRuleId(entityId);
		/** Setting the search criteria to the object **/
		
		ruleLocateCriteria.setExtractAllChecked(req
				.getParameter("extractAllRules"));
		ruleLocateCriteria.setExtractExclusionChecked(req
				.getParameter("extractExclusionRules"));
		ruleLocateCriteria.setExtractDenialChecked(req
				.getParameter("extractDenialRules"));
		ruleLocateCriteria.setExtractUMChecked(req
				.getParameter("extractUMRules"));
		ruleLocateCriteria.setExtractHeaderChecked(req
				.getParameter("extractHeaderRules"));
		//Individual Rule Check
		if (WebConstants.TRUE.equals(req.getParameter("exclusionRuleSelected"))) {
			ruleLocateCriteria
					.setRuleTypeDescription(WebConstants.EXCLUSION_RULE_DESC);
		} else if (WebConstants.TRUE.equals(req
				.getParameter("denialRuleSelected"))) {
			ruleLocateCriteria
					.setRuleTypeDescription(WebConstants.DENIAL_RULE_DESC);
		} else if (WebConstants.TRUE.equals(req.getParameter("umRuleSelected"))) {
			ruleLocateCriteria
					.setRuleTypeDescription(WebConstants.UM_RULE_DESC);
		} else if (WebConstants.TRUE.equals(req
				.getParameter("headerRuleBCselected"))) {
			ruleLocateCriteria
					.setRuleTypeDescription(WebConstants.HEADER_RULE_DESC);
			ruleLocateCriteria
					.setHeaderRuleAssociated(WebConstants.HEADER_RULE_ASSOCIATED_BC);
			ruleLocateCriteria.setBenefitComponentName(req
					.getParameter("benefitComponentName"));
		} else if (WebConstants.TRUE.equals(req
				.getParameter("headerRuleBenefitSelected"))) {
			ruleLocateCriteria
					.setRuleTypeDescription(WebConstants.HEADER_RULE_DESC);
			ruleLocateCriteria
					.setHeaderRuleAssociated(WebConstants.HEADER_RULE_ASSOCIATED_BNFT);
			if (("null").equals(req.getParameter("benefitComponentId"))
					|| (null == req.getParameter("benefitComponentId"))) {
				ruleLocateCriteria.setBenefitComponentId(WebConstants.ZERO);
			} else {
				ruleLocateCriteria.setBenefitComponentId(req
						.getParameter("benefitComponentId"));
			}
			ruleLocateCriteria.setBenefitName(req.getParameter("benefitName"));
		}
		return ruleLocateCriteria;

	}

	/**
	 * Get the rule details for contract level
	 * 
	 * @param ruleLocateCriteria
	 * @return list
	 * @throws SevereException
	 */
	private Map getContractRuleDetails(RuleLocateCriteria ruleLocateCriteria)
			throws SevereException {
		Map result = null;
		result = new ContractBusinessObjectBuilder()
				.getRuleReport(ruleLocateCriteria);
		Logger.logInfo("getContractRuleDetails List Size--->" + result.size());
		return result;

	}

	/**
	 * Get the rule details for product level
	 * 
	 * @param ruleLocateCriteria
	 * @return list
	 * @throws SevereException
	 */
	private Map getProductRuleDetails(RuleLocateCriteria ruleLocateCriteria)
			throws SevereException {
		Map result = null;
		result = new ProductBusinessObjectBuilder()
				.getProductRuleReport(ruleLocateCriteria);
		Logger.logInfo("getProductRuleDetails List Size--->" + result.size());
		return result;

	}

	/**
	 * Get the rule details for individual level
	 * 
	 * @param ruleLocateCriteria
	 * @return list
	 * @throws SevereException
	 */
	private List getIndividualRuleDetails(RuleLocateCriteria ruleLocateCriteria)
			throws SevereException {

		List result = null;
		result = new ContractBusinessObjectBuilder()
				.getIndividualRuleReport(ruleLocateCriteria);
		Logger.logInfo("getIndividualRuleDetails List Size--->" + result.size());
		return result;

	}

	/**
	 * method creates one excel cell
	 * @throws WriteException
	 */
	private void addExcelEntry(WritableSheet wsheet, int col, int row,
			String data, WritableCellFormat fontFormat) throws WriteException {
		if(null != data){
			Label label = new Label(col, row, data, fontFormat);
			wsheet.addCell(label);
		}
	}
}