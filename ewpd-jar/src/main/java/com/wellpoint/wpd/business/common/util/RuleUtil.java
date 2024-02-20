/*
 * RuleUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;

import com.wellpoint.wpd.business.contract.locateresult.RuleCodeRanges;
import com.wellpoint.wpd.business.contract.locateresult.RuleSequenceResults;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.report.bo.RuleReportBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleClaimCodeSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleClaimSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleCodeSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleDisplayBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleSequenceBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RuleUtil {

	/**
	 * This method perform create a RuledisplayBO object. This will return
	 * business object if the operation is successful and
	 */

	public static RuleDisplayBO createRuleDisplayObj(
			List<RuleSequenceResults> ruleSequenceLists) {
		RuleDisplayBO ruleDisplayBO = new RuleDisplayBO();
		if (null != ruleSequenceLists && ruleSequenceLists.size() > 0) {
			ruleDisplayBO.setRuleId(ruleSequenceLists.get(0).getRuleId());
			ruleDisplayBO.setRuleShortDesc(ruleSequenceLists.get(0)
					.getRuleShortDesc());
			ruleDisplayBO.setTag(ruleSequenceLists.get(0).getTag());
			ruleDisplayBO.setRuleVersion(ruleSequenceLists.get(0)
					.getRuleVersion());
			Iterator iterRuleSequence = ruleSequenceLists.iterator();
			int ruleSequenceNumber = 0;
			Map<Integer, RuleSequenceBO> ruleSequenceMap = new HashMap<Integer, RuleSequenceBO>();
			RuleSequenceBO ruleSequenceBO;
			RuleCodeSequenceBO ruleCodeSequenceBO;
			RuleClaimSequenceBO ruleClaimSequenceBO;
			RuleClaimCodeSequenceBO ruleClaimCodeSequenceBO;
			Map<Integer, RuleCodeSequenceBO> ruleCodeSequenceMap;
			Map<Integer, RuleClaimSequenceBO> ruleClaimSequenceMap;
			Map<Integer, RuleClaimCodeSequenceBO> ruleClaimCodeSequenceMap;
			try {
				while (iterRuleSequence.hasNext()) {
					RuleSequenceResults ruleSequenceResults = (RuleSequenceResults) iterRuleSequence
							.next();
					if (ruleSequenceResults.getRuleSequenceNumber() > -1) {
						ruleSequenceNumber = ruleSequenceResults
								.getRuleSequenceNumber();
						ruleSequenceMap = ruleDisplayBO.getRuleSequenceMap();
						if (ruleSequenceMap.containsKey(ruleSequenceNumber)) {
							ruleSequenceBO = ruleSequenceMap
									.get(ruleSequenceNumber);
							ruleCodeSequenceMap = ruleSequenceBO
									.getRuleCodeSequenceMap();
							if (ruleSequenceResults.getCdSqncNbr() > -1) {
								if (!ruleCodeSequenceMap
										.containsKey(ruleSequenceResults
												.getCdSqncNbr())) {
									ruleCodeSequenceBO = new RuleCodeSequenceBO();
									// set code sequence values to create cLine
									// level
									// obj
									if (0 != ruleSequenceResults.getCdSqncNbr())
										ruleCodeSequenceBO
												.setCdSqncNbr(ruleSequenceResults
														.getCdSqncNbr());
									// set vals 1
									List lineCodeList = new ArrayList();
									if (ruleSequenceResults
											.getLineDiagHighVal() != null
											&& ruleSequenceResults
													.getLineDiagLowVal() != null
											&& ruleSequenceResults
													.getLineDiagVrsnInd() != null) {

										RuleCodeRanges ruleCodeRanges = new RuleCodeRanges();
										ruleCodeRanges
												.setCodeTypeLowVal(ruleSequenceResults
														.getLineDiagLowVal());
										ruleCodeRanges
												.setCodeTypeHighVal(ruleSequenceResults
														.getLineDiagHighVal());
										ruleCodeRanges.setCodeType("DIA");
										ruleCodeRanges
												.setIcdVersionIndicator(ruleSequenceResults
														.getLineDiagVrsnInd());

										lineCodeList.add(ruleCodeRanges);
									}
									if (ruleSequenceResults
											.getLineIcdpHighVal() != null
											&& ruleSequenceResults
													.getLineIcdpLowVal() != null
											&& ruleSequenceResults
													.getLineIcdpVrsnInd() != null) {
										RuleCodeRanges lineIcdCodeRanges = new RuleCodeRanges();
										lineIcdCodeRanges
												.setCodeTypeLowVal(ruleSequenceResults
														.getLineIcdpLowVal());
										lineIcdCodeRanges
												.setCodeTypeHighVal(ruleSequenceResults
														.getLineIcdpHighVal());
										lineIcdCodeRanges.setCodeType("ICD");
										lineIcdCodeRanges
												.setIcdVersionIndicator(ruleSequenceResults
														.getLineIcdpVrsnInd());

										lineCodeList.add(lineIcdCodeRanges);
									}
									ruleCodeSequenceBO
											.setCodeList(lineCodeList);

									if (ruleSequenceResults
											.getLineHcptHighVal() != null)
										ruleCodeSequenceBO
												.setLineHcptHighVal(ruleSequenceResults
														.getLineHcptHighVal());
									if (ruleSequenceResults.getLineHcptLowVal() != null)
										ruleCodeSequenceBO
												.setLineHcptLowVal(ruleSequenceResults
														.getLineHcptLowVal());
									if (ruleSequenceResults.getLineLmtClsHigh() != null)
										ruleCodeSequenceBO
												.setLineLmtClsHigh(ruleSequenceResults
														.getLineLmtClsHigh());
									if (ruleSequenceResults.getLineLmtClsLow() != null)
										ruleCodeSequenceBO
												.setLineLmtClsLow(ruleSequenceResults
														.getLineLmtClsLow());

									if (ruleSequenceResults.getLineRevHighVal() != null)
										ruleCodeSequenceBO
												.setLineRevHighVal(ruleSequenceResults
														.getLineRevHighVal());
									if (ruleSequenceResults.getLineRevLowVal() != null)
										ruleCodeSequenceBO
												.setLineRevLowVal(ruleSequenceResults
														.getLineRevLowVal());
									/*if (ruleSequenceResults
											.getLineHcptHighVal() != null)
										ruleCodeSequenceBO
												.setLineHcptHighVal(ruleSequenceResults
														.getLineHcptHighVal());
									if (ruleSequenceResults.getLineHcptLowVal() != null)
										ruleCodeSequenceBO
												.setLineHcptLowVal(ruleSequenceResults
														.getLineHcptLowVal());*/
									if (ruleSequenceResults
											.getLineSrvcClsHigh() != null)
										ruleCodeSequenceBO
												.setLineSrvcClsHigh(ruleSequenceResults
														.getLineSrvcClsHigh());
									if (ruleSequenceResults.getLineSrvcClsLow() != null)
										ruleCodeSequenceBO
												.setLineSrvcClsLow(ruleSequenceResults
														.getLineSrvcClsLow());
									
									if (ruleSequenceResults.getLineIcdpCategoryCode() != null)
										ruleCodeSequenceBO
												.setLineIcdpCategoryCode(ruleSequenceResults
														.getLineIcdpCategoryCode());
									if (ruleSequenceResults.getLineIcdpClsfctnId() != null)
										ruleCodeSequenceBO
												.setLineIcdpClsfctnId(ruleSequenceResults
														.getLineIcdpClsfctnId());
									if (ruleSequenceResults.getLineDiagCategoryCode() != null)
										ruleCodeSequenceBO
												.setLineDiagCategoryCode(ruleSequenceResults
														.getLineDiagCategoryCode());
									if (ruleSequenceResults.getLineDiagClsfctnId() != null)
										ruleCodeSequenceBO
												.setLineDiagClsfctnId(ruleSequenceResults
														.getLineDiagClsfctnId());
									// set vals 1 end
									ruleCodeSequenceMap
											.put(ruleSequenceResults
													.getCdSqncNbr(),
													ruleCodeSequenceBO);
									ruleSequenceBO
											.setRuleCodeSequenceMap(ruleCodeSequenceMap);
								}
							}
							ruleClaimSequenceMap = ruleSequenceBO
									.getRuleClaimSequenceMap();
							if (ruleSequenceResults.getCdSqncNbr() > -1) {
								if (!ruleClaimSequenceMap
										.containsKey(ruleSequenceResults
												.getClmSqncNbr())) {
									ruleClaimSequenceBO = new RuleClaimSequenceBO();
									// set vals to prepare claim sequence code
									if (0 != ruleSequenceResults
											.getClmSqncNbr())
										ruleClaimSequenceBO
												.setClmSqncNbr(ruleSequenceResults
														.getClmSqncNbr());
									if (ruleSequenceResults.getClmServiceCode() != null)
										ruleClaimSequenceBO
												.setClmServiceCode(ruleSequenceResults
														.getClmServiceCode());
									if (ruleSequenceResults
											.getClmProcessModifierCode() != null)
										ruleClaimSequenceBO
												.setClmProcessModifierCode(ruleSequenceResults
														.getClmProcessModifierCode());
									if (ruleSequenceResults
											.getClmProcessModifierSecondaryCode() != null)
										ruleClaimSequenceBO
												.setClmProcessModifierSecondaryCode(ruleSequenceResults
														.getClmProcessModifierSecondaryCode());
									if (ruleSequenceResults.getClmttCd() != null)
										ruleClaimSequenceBO
												.setClmttCd(ruleSequenceResults
														.getClmttCd());
									if (ruleSequenceResults.getClmPosCd() != null)
										ruleClaimSequenceBO
												.setClmPosCd(ruleSequenceResults
														.getClmPosCd());
									if (ruleSequenceResults
											.getClmHMOClassCode() != null)
										ruleClaimSequenceBO
												.setClmHMOClassCode(ruleSequenceResults
														.getClmHMOClassCode());
									if (ruleSequenceResults
											.getClmSameDaySrvcInd() != null)
										ruleClaimSequenceBO
												.setClmSameDaySrvcInd(ruleSequenceResults
														.getClmSameDaySrvcInd());
									if (ruleSequenceResults
											.getClmSprtgProcCdInd() != null)
										ruleClaimSequenceBO
												.setClmSprtgProcCdInd(ruleSequenceResults
														.getClmSprtgProcCdInd());
									if (ruleSequenceResults.getClaimDiagnosisIndicator() != null)
										ruleClaimSequenceBO
												.setClaimDiagnosisIndicator(ruleSequenceResults
														.getClaimDiagnosisIndicator());
									// set vals end
									/*
									 * ruleClaimSequenceMap.put(ruleSequenceResults
									 * .getClmSqncNbr(), ruleClaimSequenceBO);
									 * ruleSequenceBO
									 * .setRuleClaimSequenceMap(ruleClaimSequenceMap
									 * );
									 */

								} else {
									ruleClaimSequenceBO = ruleClaimSequenceMap
											.get(ruleSequenceResults
													.getClmSqncNbr());
								}

								ruleClaimCodeSequenceMap = ruleClaimSequenceBO
										.getRuleClaimCodeSequenceMap();
								if (ruleSequenceResults.getClmCdSqncNbr() > -1) {
									if (!ruleClaimCodeSequenceMap
											.containsKey(ruleSequenceResults
													.getClmCdSqncNbr())) {
										ruleClaimCodeSequenceBO = new RuleClaimCodeSequenceBO();
										// set vals
										if (0 != ruleSequenceResults
												.getClmCdSqncNbr())
											ruleClaimCodeSequenceBO
													.setClmCdSqncNbr(ruleSequenceResults
															.getClmCdSqncNbr());

										if (ruleSequenceResults
												.getClmRevHighVal() != null)
											ruleClaimCodeSequenceBO
													.setClmRevHighVal(ruleSequenceResults
															.getClmRevHighVal());
										if (ruleSequenceResults
												.getClmRevLowVal() != null)
											ruleClaimCodeSequenceBO
													.setClmRevLowVal(ruleSequenceResults
															.getClmRevLowVal());
										if (ruleSequenceResults
												.getClmDiagHighVal() != null)
											ruleClaimCodeSequenceBO
													.setClmDiagHighVal(ruleSequenceResults
															.getClmDiagHighVal());
										if (ruleSequenceResults
												.getClmDiagLowVal() != null)
											ruleClaimCodeSequenceBO
													.setClmDiagLowVal(ruleSequenceResults
															.getClmDiagLowVal());

										List clmCodeList = new ArrayList();
										if (ruleSequenceResults
												.getClmDiagHighVal() != null
												&& ruleSequenceResults
														.getClmDiagLowVal() != null
												&& ruleSequenceResults
														.getClmDiagVrsnInd() != null) {

											RuleCodeRanges clmDiagCodeRanges = new RuleCodeRanges();
											clmDiagCodeRanges
													.setCodeTypeLowVal(ruleSequenceResults
															.getClmDiagLowVal());
											clmDiagCodeRanges
													.setCodeTypeHighVal(ruleSequenceResults
															.getClmDiagHighVal());
											clmDiagCodeRanges
													.setCodeType("DIA");
											clmDiagCodeRanges
													.setIcdVersionIndicator(ruleSequenceResults
															.getClmDiagVrsnInd());

											clmCodeList.add(clmDiagCodeRanges);
										}

										if (ruleSequenceResults
												.getClmIcdHighVal() != null
												&& ruleSequenceResults
														.getClmIcdLowVal() != null
												&& ruleSequenceResults
														.getClmIcdpVrsnInd() != null) {
											RuleCodeRanges clmIcdCodeRanges = new RuleCodeRanges();
											clmIcdCodeRanges
													.setCodeTypeLowVal(ruleSequenceResults
															.getClmIcdLowVal());
											clmIcdCodeRanges
													.setCodeTypeHighVal(ruleSequenceResults
															.getClmIcdHighVal());
											clmIcdCodeRanges.setCodeType("ICD");
											clmIcdCodeRanges
													.setIcdVersionIndicator(ruleSequenceResults
															.getClmIcdpVrsnInd());

											clmCodeList.add(clmIcdCodeRanges);
										}
										ruleClaimCodeSequenceBO
												.setClaimCodeList(clmCodeList);

										if (ruleSequenceResults
												.getClmHcptHighVal() != null)
											ruleClaimCodeSequenceBO
													.setClmHcptHighVal(ruleSequenceResults
															.getClmHcptHighVal());
										if (ruleSequenceResults
												.getClmHcptLowVal() != null)
											ruleClaimCodeSequenceBO
													.setClmHcptLowVal(ruleSequenceResults
															.getClmHcptLowVal());
										if (ruleSequenceResults
												.getClmServiceClassHigh() != null)
											ruleClaimCodeSequenceBO
													.setClmServiceClassHigh(ruleSequenceResults
															.getClmServiceClassHigh());
										if (ruleSequenceResults
												.getClmServiceClassLow() != null)
											ruleClaimCodeSequenceBO
													.setClmServiceClassLow(ruleSequenceResults
															.getClmServiceClassLow());
										if (ruleSequenceResults
												.getClmLimitClassHigh() != null)
											ruleClaimCodeSequenceBO
													.setClmLimitClassHigh(ruleSequenceResults
															.getClmLimitClassHigh());
										if (ruleSequenceResults
												.getClmLimitClassLow() != null)
											ruleClaimCodeSequenceBO
													.setClmLimitClassLow(ruleSequenceResults
															.getClmLimitClassLow());
										if (ruleSequenceResults
												.getClmIcdpCategoryCode() != null)
											ruleClaimCodeSequenceBO
													.setClmIcdpCategoryCode(ruleSequenceResults
															.getClmIcdpCategoryCode());
										if (ruleSequenceResults
												.getClmIcdpClsfctnId() != null)
											ruleClaimCodeSequenceBO
													.setClmIcdpClsfctnId(ruleSequenceResults
															.getClmIcdpClsfctnId());
										if (ruleSequenceResults
												.getClmDiagCategoryCode() != null)
											ruleClaimCodeSequenceBO
													.setClmDiagCategoryCode(ruleSequenceResults
															.getClmDiagCategoryCode());
										if (ruleSequenceResults
												.getClmDiagClsfctnId() != null)
											ruleClaimCodeSequenceBO
													.setClmDiagClsfctnId(ruleSequenceResults
															.getClmDiagClsfctnId());
										// set vals end
										ruleClaimCodeSequenceMap.put(
												ruleSequenceResults
														.getClmCdSqncNbr(),
												ruleClaimCodeSequenceBO);
										ruleClaimSequenceBO
												.setRuleClaimCodeSequenceMap(ruleClaimCodeSequenceMap);

									}
								}
								//
								ruleClaimSequenceMap.put(ruleSequenceResults
										.getClmSqncNbr(), ruleClaimSequenceBO);
								ruleSequenceBO
										.setRuleClaimSequenceMap(ruleClaimSequenceMap);
							}
						} else {
							ruleSequenceBO = new RuleSequenceBO();
							ruleCodeSequenceBO = new RuleCodeSequenceBO();
							ruleClaimSequenceBO = new RuleClaimSequenceBO();
							ruleClaimCodeSequenceBO = new RuleClaimCodeSequenceBO();
							ruleCodeSequenceMap = new HashMap<Integer, RuleCodeSequenceBO>();
							ruleClaimSequenceMap = new HashMap<Integer, RuleClaimSequenceBO>();
							ruleClaimCodeSequenceMap = new HashMap<Integer, RuleClaimCodeSequenceBO>();

							if (ruleSequenceResults != null) {
								if (ruleSequenceResults.getRuleSequenceNumber() > -1)// need
									// to
									// revisit
									ruleSequenceBO
											.setRuleSequenceNumber(ruleSequenceResults
													.getRuleSequenceNumber());
								if (ruleSequenceResults.getRuleId() != null)
									ruleSequenceBO
											.setRuleId(ruleSequenceResults
													.getRuleId());
								if (ruleSequenceResults.getExclsnInd() != null)
									ruleSequenceBO
											.setExclsnInd(ruleSequenceResults
													.getExclsnInd());
								if (ruleSequenceResults.getClmType() != null)
									ruleSequenceBO
											.setClmType(ruleSequenceResults
													.getClmType());
								if (ruleSequenceResults.getPlaceOfService() != null)
									ruleSequenceBO
											.setPlaceOfService(ruleSequenceResults
													.getPlaceOfService());
								if (ruleSequenceResults.getPatHighAge() > -1)
									ruleSequenceBO
											.setPatHighAge(ruleSequenceResults
													.getPatHighAge());
								if (ruleSequenceResults.getPatLowAge() > -1)
									ruleSequenceBO
											.setPatLowAge(ruleSequenceResults
													.getPatLowAge());
								if (ruleSequenceResults.getGenderCode() != null)
									ruleSequenceBO
											.setGenderCode(ruleSequenceResults
													.getGenderCode());
								if (ruleSequenceResults.getProviderId() != null)
									ruleSequenceBO
											.setProviderId(ruleSequenceResults
													.getProviderId());
								if (ruleSequenceResults
										.getProviderSpecialityCode() != null)
									ruleSequenceBO
											.setProviderSpecialityCode(ruleSequenceResults
													.getProviderSpecialityCode());
								if (ruleSequenceResults.getBenefitAccmNum() != null)
									ruleSequenceBO
											.setBenefitAccmNum(ruleSequenceResults
													.getBenefitAccmNum());
								if (ruleSequenceResults
										.getBenefitAccumLimtNum() > -1)// need
									// to
									// revisit
									ruleSequenceBO
											.setBenefitAccumLimtNum(ruleSequenceResults
													.getBenefitAccumLimtNum());
								if (Float.parseFloat(ruleSequenceResults
										.getBenfitAccumLimtAmnt()) > 0)// need
									// to
									// revisit
									ruleSequenceBO
											.setBenfitAccumLimtAmnt(ruleSequenceResults
													.getBenfitAccumLimtAmnt());
								if (ruleSequenceResults.getNtfyOnlyInd() != null)
									ruleSequenceBO
											.setNtfyOnlyInd(ruleSequenceResults
													.getNtfyOnlyInd());
								if (ruleSequenceResults.getClnlRevwInd() != null)
									ruleSequenceBO
											.setClnlRevwInd(ruleSequenceResults
													.getClnlRevwInd());
								if (Float.parseFloat(ruleSequenceResults.getDlrLimit()) > 0)
									ruleSequenceBO
											.setDlrLimit(ruleSequenceResults
													.getDlrLimit());
								if (ruleSequenceResults.getServiceCode() != null)
									ruleSequenceBO
											.setServiceCode(ruleSequenceResults
													.getServiceCode());
								if (ruleSequenceResults.getGrpSt() != null)
									ruleSequenceBO.setGrpSt(ruleSequenceResults
											.getGrpSt());
								if (ruleSequenceResults.getLenOfStay() > -1)
									ruleSequenceBO
											.setLenOfStay(ruleSequenceResults
													.getLenOfStay());
								if (ruleSequenceResults.getItsSpecialityCode() != null)
									ruleSequenceBO
											.setItsSpecialityCode(ruleSequenceResults
													.getItsSpecialityCode());
								if (ruleSequenceResults.getServcStartDate() != null)
									ruleSequenceBO
											.setServcStartDate(ruleSequenceResults
													.getServcStartDate());
								if (ruleSequenceResults.getServcEndDate() != null)
									ruleSequenceBO
											.setServcEndDate(ruleSequenceResults
													.getServcEndDate());
								if (ruleSequenceResults.getMembrRelnCode() != null)
									ruleSequenceBO
											.setMembrRelnCode(ruleSequenceResults
													.getMembrRelnCode());
								if (ruleSequenceResults
										.getPrecedrModifierCode() != null)
									ruleSequenceBO
											.setPrecedrModifierCode(ruleSequenceResults
													.getPrecedrModifierCode());
								if (ruleSequenceResults.getEditCode1() != null)
									ruleSequenceBO
											.setEditCode1(ruleSequenceResults
													.getEditCode1());
								if (ruleSequenceResults.getEditCode2() != null)
									ruleSequenceBO
											.setEditCode2(ruleSequenceResults
													.getEditCode2());
								if (ruleSequenceResults.getProviderStCode() != null)
									ruleSequenceBO
											.setProviderStCode(ruleSequenceResults
													.getProviderStCode());
								if (ruleSequenceResults.getBillTypeCode() != null)
									ruleSequenceBO
											.setBillTypeCode(ruleSequenceResults
													.getBillTypeCode());
								if (ruleSequenceResults.getTtCode() != null)
									ruleSequenceBO
											.setTtCode(ruleSequenceResults
													.getTtCode());
								if (ruleSequenceResults.getAttachmentInd() != null)
									ruleSequenceBO
											.setAttachmentInd(ruleSequenceResults
													.getAttachmentInd());
								if (ruleSequenceResults.getPatMembrCode() != null)
									ruleSequenceBO
											.setPatMembrCode(ruleSequenceResults
													.getPatMembrCode());
								if (ruleSequenceResults.getHosptlFacilCode() != null)
									ruleSequenceBO
											.setHosptlFacilCode(ruleSequenceResults
													.getHosptlFacilCode());
								if (ruleSequenceResults.getDaysFrmInjury() > -1)
									ruleSequenceBO
											.setDaysFrmInjury(ruleSequenceResults
													.getDaysFrmInjury());
								if (ruleSequenceResults.getDaysFrmIllness() > -1)
									ruleSequenceBO
											.setDaysFrmIllness(ruleSequenceResults
													.getDaysFrmIllness());
								if (ruleSequenceResults.getHmoClsCode() != null)
									ruleSequenceBO
											.setHmoClsCode(ruleSequenceResults
													.getHmoClsCode());
								if (ruleSequenceResults.getTotalChargeSign() != null)
									ruleSequenceBO
											.setTotalChargeSign(ruleSequenceResults
													.getTotalChargeSign());
								if (Float.parseFloat(ruleSequenceResults.getTotalChargeAmnt()) > 0)
									ruleSequenceBO
											.setTotalChargeAmnt(ruleSequenceResults
													.getTotalChargeAmnt());
								if (ruleSequenceResults.getWpdDelFlag() != null)
									ruleSequenceBO
											.setWpdDelFlag(ruleSequenceResults
													.getWpdDelFlag());
								if (ruleSequenceResults.getClmLevelDataInd() != null)
									ruleSequenceBO
											.setClmLevelDataInd(ruleSequenceResults
													.getClmLevelDataInd());
								if (ruleSequenceResults.getCopayIndicator() != null)
									ruleSequenceBO
											.setCopayIndicator(ruleSequenceResults
													.getCopayIndicator());
								if (ruleSequenceResults.getHunPerIndicator() != null)
									ruleSequenceBO
											.setHunPerIndicator(ruleSequenceResults
													.getHunPerIndicator());
								if (ruleSequenceResults
										.getMedicareAssgnIndicator() != null)
									ruleSequenceBO
											.setMedicareAssgnIndicator(ruleSequenceResults
													.getMedicareAssgnIndicator());
								if (ruleSequenceResults.getClmDrgCd() != null)
									ruleSequenceBO
											.setClmDrgCd(ruleSequenceResults
													.getClmDrgCd());
								if (ruleSequenceResults
										.getProcedureModifierSecondaryCode() != null)
									ruleSequenceBO
											.setProcedureModifierSecondaryCode(ruleSequenceResults
													.getProcedureModifierSecondaryCode());
								if (ruleSequenceResults
										.getSupportHcpIndicator() != null)
									ruleSequenceBO
											.setSupportHcpIndicator(ruleSequenceResults
													.getSupportHcpIndicator());
								if (ruleSequenceResults
										.getClaimSupportHcpIndicator() != null)
									ruleSequenceBO
											.setClaimSupportHcpIndicator(ruleSequenceResults
													.getClaimSupportHcpIndicator());
								if (ruleSequenceResults.getDiagnosisIndicator() != null)
									ruleSequenceBO
											.setDiagnosisIndicator(ruleSequenceResults
													.getDiagnosisIndicator());
								if (ruleSequenceResults.getPcpIndicator() != null)
									ruleSequenceBO
											.setPcpIndicator(ruleSequenceResults
													.getPcpIndicator());
								if(null != ruleSequenceResults.getProvLncsId())
									ruleSequenceBO
											.setProvLncsId(ruleSequenceResults.getProvLncsId());
								if(null != ruleSequenceResults.getProvMedcrId())
									ruleSequenceBO
											.setProvMedcrId(ruleSequenceResults.getProvMedcrId());
								if(ruleSequenceResults.getBillgProvNbr() !=null)
									ruleSequenceBO
											.setBillgProvNbr(ruleSequenceResults.getBillgProvNbr());
								if(ruleSequenceResults.getRndrgProvNbr()!=null)
									ruleSequenceBO
											.setRndrgProvNbr(ruleSequenceResults.getRndrgProvNbr());
								if(ruleSequenceResults.getBillgNpiNbr()!=null)
									ruleSequenceBO
											.setBillgNpiNbr(ruleSequenceResults.getBillgNpiNbr());
								if(ruleSequenceResults.getRndrgNpiNbr()!=null)
									ruleSequenceBO
											.setRndrgNpiNbr(ruleSequenceResults.getRndrgNpiNbr());
								if (ruleSequenceResults.getElgblExpansSignCode() != null)
									ruleSequenceBO
											.setElgblExpansSignCode(ruleSequenceResults.getElgblExpansSignCode());
								if(Float.parseFloat(ruleSequenceResults.getElgblExpansAmt()) > 0)
									ruleSequenceBO
											.setElgblExpansAmt(ruleSequenceResults.getElgblExpansAmt());
								if (ruleSequenceResults.getDrugCode() != null)
									ruleSequenceBO
											.setDrugCode(ruleSequenceResults.getDrugCode());
								if (ruleSequenceResults.getProviderSpecialityIndicator() != null)
									ruleSequenceBO
											.setProviderSpecialityIndicator(ruleSequenceResults.getProviderSpecialityIndicator());
								if (ruleSequenceResults.getAgeTypCode() != null)
									ruleSequenceBO
											.setAgeTypCode(ruleSequenceResults.getAgeTypCode());
								
								// Line level
								if (0 != ruleSequenceResults.getCdSqncNbr())
									ruleCodeSequenceBO
											.setCdSqncNbr(ruleSequenceResults
													.getCdSqncNbr());

								List lineCodeList = new ArrayList();
								if (ruleSequenceResults.getLineDiagHighVal() != null
										&& ruleSequenceResults
												.getLineDiagLowVal() != null
										&& ruleSequenceResults
												.getLineDiagVrsnInd() != null) {

									RuleCodeRanges ruleCodeRanges = new RuleCodeRanges();
									ruleCodeRanges
											.setCodeTypeLowVal(ruleSequenceResults
													.getLineDiagLowVal());
									ruleCodeRanges
											.setCodeTypeHighVal(ruleSequenceResults
													.getLineDiagHighVal());
									ruleCodeRanges.setCodeType("DIA");
									ruleCodeRanges
											.setIcdVersionIndicator(ruleSequenceResults
													.getLineDiagVrsnInd());

									lineCodeList.add(ruleCodeRanges);
								}
								if (ruleSequenceResults.getLineIcdpHighVal() != null
										&& ruleSequenceResults
												.getLineIcdpLowVal() != null
										&& ruleSequenceResults
												.getLineIcdpVrsnInd() != null) {
									RuleCodeRanges lineIcdCodeRanges = new RuleCodeRanges();
									lineIcdCodeRanges
											.setCodeTypeLowVal(ruleSequenceResults
													.getLineIcdpLowVal());
									lineIcdCodeRanges
											.setCodeTypeHighVal(ruleSequenceResults
													.getLineIcdpHighVal());
									lineIcdCodeRanges.setCodeType("ICD");
									lineIcdCodeRanges
											.setIcdVersionIndicator(ruleSequenceResults
													.getLineIcdpVrsnInd());

									lineCodeList.add(lineIcdCodeRanges);
								}
								ruleCodeSequenceBO.setCodeList(lineCodeList);

								if (ruleSequenceResults.getLineHcptHighVal() != null)
									ruleCodeSequenceBO
											.setLineHcptHighVal(ruleSequenceResults
													.getLineHcptHighVal());
								if (ruleSequenceResults.getLineHcptLowVal() != null)
									ruleCodeSequenceBO
											.setLineHcptLowVal(ruleSequenceResults
													.getLineHcptLowVal());
								if (ruleSequenceResults.getLineLmtClsHigh() != null)
									ruleCodeSequenceBO
											.setLineLmtClsHigh(ruleSequenceResults
													.getLineLmtClsHigh());
								if (ruleSequenceResults.getLineLmtClsLow() != null)
									ruleCodeSequenceBO
											.setLineLmtClsLow(ruleSequenceResults
													.getLineLmtClsLow());
								if (ruleSequenceResults.getLineRevHighVal() != null)
									ruleCodeSequenceBO
											.setLineRevHighVal(ruleSequenceResults
													.getLineRevHighVal());
								if (ruleSequenceResults.getLineRevLowVal() != null)
									ruleCodeSequenceBO
											.setLineRevLowVal(ruleSequenceResults
													.getLineRevLowVal());
								/*if (ruleSequenceResults.getLineHcptHighVal() != null)
									ruleCodeSequenceBO
											.setLineHcptHighVal(ruleSequenceResults
													.getLineHcptHighVal());
								if (ruleSequenceResults.getLineHcptLowVal() != null)
									ruleCodeSequenceBO
											.setLineHcptLowVal(ruleSequenceResults
													.getLineHcptHighVal());*/
								if (ruleSequenceResults.getLineSrvcClsHigh() != null)
									ruleCodeSequenceBO
											.setLineSrvcClsHigh(ruleSequenceResults
													.getLineSrvcClsHigh());
								if (ruleSequenceResults.getLineSrvcClsLow() != null)
									ruleCodeSequenceBO
											.setLineSrvcClsLow(ruleSequenceResults
													.getLineSrvcClsLow());

								if (ruleSequenceResults.getLineIcdpCategoryCode() != null)
									ruleCodeSequenceBO
											.setLineIcdpCategoryCode(ruleSequenceResults
													.getLineIcdpCategoryCode());
								if (ruleSequenceResults.getLineIcdpClsfctnId() != null)
									ruleCodeSequenceBO
											.setLineIcdpClsfctnId(ruleSequenceResults
													.getLineIcdpClsfctnId());
								if (ruleSequenceResults.getLineDiagCategoryCode() != null)
									ruleCodeSequenceBO
											.setLineDiagCategoryCode(ruleSequenceResults
													.getLineDiagCategoryCode());
								if (ruleSequenceResults.getLineDiagClsfctnId() != null)
									ruleCodeSequenceBO
											.setLineDiagClsfctnId(ruleSequenceResults
													.getLineDiagClsfctnId());
								// Claim level
								if (0 != ruleSequenceResults.getClmSqncNbr())
									ruleClaimSequenceBO
											.setClmSqncNbr(ruleSequenceResults
													.getClmSqncNbr());
								if (ruleSequenceResults.getClmServiceCode() != null)
									ruleClaimSequenceBO
											.setClmServiceCode(ruleSequenceResults
													.getClmServiceCode());
								if (ruleSequenceResults
										.getClmProcessModifierCode() != null)
									ruleClaimSequenceBO
											.setClmProcessModifierCode(ruleSequenceResults
													.getClmProcessModifierCode());
								if (ruleSequenceResults
										.getClmProcessModifierSecondaryCode() != null)
									ruleClaimSequenceBO
											.setClmProcessModifierSecondaryCode(ruleSequenceResults
													.getClmProcessModifierSecondaryCode());
								if (ruleSequenceResults.getClmttCd() != null)
									ruleClaimSequenceBO
											.setClmttCd(ruleSequenceResults
													.getClmttCd());
								if (ruleSequenceResults.getClmPosCd() != null)
									ruleClaimSequenceBO
											.setClmPosCd(ruleSequenceResults
													.getClmPosCd());
								if (ruleSequenceResults.getClmHMOClassCode() != null)
									ruleClaimSequenceBO
											.setClmHMOClassCode(ruleSequenceResults
													.getClmHMOClassCode());
								if (ruleSequenceResults.getClmSameDaySrvcInd() != null)
									ruleClaimSequenceBO
											.setClmSameDaySrvcInd(ruleSequenceResults
													.getClmSameDaySrvcInd());
								if (ruleSequenceResults.getClmSprtgProcCdInd() != null)
									ruleClaimSequenceBO
											.setClmSprtgProcCdInd(ruleSequenceResults
													.getClmSprtgProcCdInd());
								if (ruleSequenceResults.getClaimDiagnosisIndicator() != null)
									ruleClaimSequenceBO
											.setClaimDiagnosisIndicator(ruleSequenceResults
													.getClaimDiagnosisIndicator());

								// Claim code level
								if (0 != ruleSequenceResults.getClmCdSqncNbr())
									ruleClaimCodeSequenceBO
											.setClmCdSqncNbr(ruleSequenceResults
													.getClmCdSqncNbr());

								List clmCodeList = new ArrayList();
								if (ruleSequenceResults.getClmDiagHighVal() != null
										&& ruleSequenceResults
												.getClmDiagLowVal() != null
										&& ruleSequenceResults
												.getClmDiagVrsnInd() != null) {

									RuleCodeRanges clmDiagCodeRanges = new RuleCodeRanges();
									clmDiagCodeRanges
											.setCodeTypeLowVal(ruleSequenceResults
													.getClmDiagLowVal());
									clmDiagCodeRanges
											.setCodeTypeHighVal(ruleSequenceResults
													.getClmDiagHighVal());
									clmDiagCodeRanges.setCodeType("DIA");
									clmDiagCodeRanges
											.setIcdVersionIndicator(ruleSequenceResults
													.getClmDiagVrsnInd());

									clmCodeList.add(clmDiagCodeRanges);
								}

								if (ruleSequenceResults.getClmIcdHighVal() != null
										&& ruleSequenceResults
												.getClmIcdLowVal() != null
										&& ruleSequenceResults
												.getClmIcdpVrsnInd() != null) {
									RuleCodeRanges clmIcdCodeRanges = new RuleCodeRanges();
									clmIcdCodeRanges
											.setCodeTypeLowVal(ruleSequenceResults
													.getClmIcdLowVal());
									clmIcdCodeRanges
											.setCodeTypeHighVal(ruleSequenceResults
													.getClmIcdHighVal());
									clmIcdCodeRanges.setCodeType("ICD");
									clmIcdCodeRanges
											.setIcdVersionIndicator(ruleSequenceResults
													.getClmIcdpVrsnInd());

									clmCodeList.add(clmIcdCodeRanges);
								}
								ruleClaimCodeSequenceBO
										.setClaimCodeList(clmCodeList);

								if (ruleSequenceResults.getClmRevHighVal() != null)
									ruleClaimCodeSequenceBO
											.setClmRevHighVal(ruleSequenceResults
													.getClmRevHighVal());
								if (ruleSequenceResults.getClmRevLowVal() != null)
									ruleClaimCodeSequenceBO
											.setClmRevLowVal(ruleSequenceResults
													.getClmRevLowVal());

								if (ruleSequenceResults.getClmHcptHighVal() != null)
									ruleClaimCodeSequenceBO
											.setClmHcptHighVal(ruleSequenceResults
													.getClmHcptHighVal());
								if (ruleSequenceResults.getClmHcptLowVal() != null)
									ruleClaimCodeSequenceBO
											.setClmHcptLowVal(ruleSequenceResults
													.getClmHcptLowVal());
								if (ruleSequenceResults
										.getClmServiceClassHigh() != null)
									ruleClaimCodeSequenceBO
											.setClmServiceClassHigh(ruleSequenceResults
													.getClmServiceClassHigh());
								if (ruleSequenceResults.getClmServiceClassLow() != null)
									ruleClaimCodeSequenceBO
											.setClmServiceClassLow(ruleSequenceResults
													.getClmServiceClassLow());
								if (ruleSequenceResults.getClmLimitClassHigh() != null)
									ruleClaimCodeSequenceBO
											.setClmLimitClassHigh(ruleSequenceResults
													.getClmLimitClassHigh());
								if (ruleSequenceResults.getClmLimitClassLow() != null)
									ruleClaimCodeSequenceBO
											.setClmLimitClassLow(ruleSequenceResults
													.getClmLimitClassLow());
								if (ruleSequenceResults
										.getClmIcdpCategoryCode() != null)
									ruleClaimCodeSequenceBO
											.setClmIcdpCategoryCode(ruleSequenceResults
													.getClmIcdpCategoryCode());
								if (ruleSequenceResults
										.getClmIcdpClsfctnId() != null)
									ruleClaimCodeSequenceBO
											.setClmIcdpClsfctnId(ruleSequenceResults
													.getClmIcdpClsfctnId());
								if (ruleSequenceResults
										.getClmDiagCategoryCode() != null)
									ruleClaimCodeSequenceBO
											.setClmDiagCategoryCode(ruleSequenceResults
													.getClmDiagCategoryCode());
								if (ruleSequenceResults
										.getClmDiagClsfctnId() != null)
									ruleClaimCodeSequenceBO
											.setClmDiagClsfctnId(ruleSequenceResults
													.getClmDiagClsfctnId());
								// code level
								ruleCodeSequenceMap.put(ruleSequenceResults
										.getCdSqncNbr(), ruleCodeSequenceBO);
								ruleSequenceBO
										.setRuleCodeSequenceMap(ruleCodeSequenceMap);
								// claim code level
								ruleClaimCodeSequenceMap.put(
										ruleSequenceResults.getClmCdSqncNbr(),
										ruleClaimCodeSequenceBO);
								ruleClaimSequenceBO
										.setRuleClaimCodeSequenceMap(ruleClaimCodeSequenceMap);
								// claim level
								ruleClaimSequenceMap.put(ruleSequenceResults
										.getClmSqncNbr(), ruleClaimSequenceBO);
								ruleSequenceBO
										.setRuleClaimSequenceMap(ruleClaimSequenceMap);

							}

						}
						// rule seq.
						ruleSequenceMap.put(ruleSequenceResults
								.getRuleSequenceNumber(), ruleSequenceBO);
						ruleDisplayBO.setRuleSequenceMap(ruleSequenceMap);
					}
				}
			} catch (Exception ex) {
				Logger.logError("Exception occured while create RuleDisplayBo");
				System.out
						.println("Exception occured while create RuleDisplayBo");
				Logger.logError(ex);
			}

		}

		return ruleDisplayBO;
	}

	public static Map<String, RuleDisplayBO> createRuleReportDisplayObj(
			List<RuleReportBO> ruleReportSequenceLists) {
		List<RuleReportBO> tempList = new ArrayList<RuleReportBO>();
		LinkedHashMap<String, RuleDisplayBO> ruleMap = new LinkedHashMap<String, RuleDisplayBO>();
		RuleDisplayBO ruleDisplayBO =null;
		for (RuleReportBO ruleReportBO : ruleReportSequenceLists) {

			if (!ruleMap.containsKey(ruleReportBO.getRuleID())) {

				tempList = createTempList(ruleReportSequenceLists, ruleReportBO
						.getRuleID());

				if (tempList != null) {
					ruleDisplayBO = new RuleDisplayBO();
					ruleDisplayBO.setRuleId(tempList.get(0).getRuleID());
					ruleDisplayBO.setRuleTypeDesc(tempList.get(0)
							.getRuleTypeDesc());
					ruleDisplayBO.setTag(tempList.get(0).getTag());
					ruleDisplayBO.setRuleVersion(tempList.get(0)
							.getRuleVersion());
					ruleDisplayBO.setGrpRuleId(tempList.get(0).getGrpRuleId());
					ruleDisplayBO.setRuleShortDesc(tempList.get(0)
							.getRuleShortDesc());
					Iterator iterRuleSequence = tempList.iterator();
					int ruleSequenceNumber = 0;
					Map<Integer, RuleSequenceBO> ruleSequenceMap = new HashMap<Integer, RuleSequenceBO>();
					RuleSequenceBO ruleSequenceBO;
					RuleCodeSequenceBO ruleCodeSequenceBO;
					RuleClaimSequenceBO ruleClaimSequenceBO;
					RuleClaimCodeSequenceBO ruleClaimCodeSequenceBO;
					Map<Integer, RuleCodeSequenceBO> ruleCodeSequenceMap;
					Map<Integer, RuleClaimSequenceBO> ruleClaimSequenceMap;
					Map<Integer, RuleClaimCodeSequenceBO> ruleClaimCodeSequenceMap;
					try {
						while (iterRuleSequence.hasNext()) {
							RuleReportBO ruleReportResults = (RuleReportBO) iterRuleSequence
									.next();
							if (ruleReportResults.getRuleSequenceNo() > -1) {
								ruleSequenceNumber = ruleReportResults
										.getRuleSequenceNo();
								ruleSequenceMap = ruleDisplayBO
										.getRuleSequenceMap();
								if (ruleSequenceMap
										.containsKey(ruleSequenceNumber)) {
									ruleSequenceBO = ruleSequenceMap
											.get(ruleSequenceNumber);
									ruleCodeSequenceMap = ruleSequenceBO
											.getRuleCodeSequenceMap();
									if (ruleReportResults.getCodeSequenceNo() > -1) {
										if (!ruleCodeSequenceMap
												.containsKey(ruleReportResults
														.getCodeSequenceNo())) {
											ruleCodeSequenceBO = new RuleCodeSequenceBO();
											// set code sequence values to
											// create cLine level
											// obj
											if (0 != ruleReportResults
													.getCodeSequenceNo())
												ruleCodeSequenceBO
														.setCdSqncNbr(ruleReportResults
																.getCodeSequenceNo());
											// set vals 1
											ArrayList lineCodeList = new ArrayList();
											if (ruleReportResults
													.getDiagHighVal() != null
													&& ruleReportResults
															.getDiagLowVal() != null
													&& ruleReportResults
															.getDiagvrsnInd() != null) {

												RuleCodeRanges ruleCodeRanges = new RuleCodeRanges();
												ruleCodeRanges
														.setCodeTypeLowVal(ruleReportResults
																.getDiagLowVal());
												ruleCodeRanges
														.setCodeTypeHighVal(ruleReportResults
																.getDiagHighVal());
												ruleCodeRanges
														.setCodeType("DIA");
												ruleCodeRanges
														.setIcdVersionIndicator(ruleReportResults
																.getDiagvrsnInd());

												lineCodeList
														.add(ruleCodeRanges);
											}
											if (ruleReportResults
													.getIcdpHighVal() != null
													&& ruleReportResults
															.getIcdpLowVal() != null
													&& ruleReportResults
															.getIcdpVrsnInd() != null) {
												RuleCodeRanges lineIcdCodeRanges = new RuleCodeRanges();
												lineIcdCodeRanges
														.setCodeTypeLowVal(ruleReportResults
																.getIcdpLowVal());
												lineIcdCodeRanges
														.setCodeTypeHighVal(ruleReportResults
																.getIcdpHighVal());
												lineIcdCodeRanges
														.setCodeType("ICD");
												lineIcdCodeRanges
														.setIcdVersionIndicator(ruleReportResults
																.getIcdpVrsnInd());

												lineCodeList
														.add(lineIcdCodeRanges);
											}
											ruleCodeSequenceBO
													.setCodeList(lineCodeList);

											if (ruleReportResults
													.getHcptHighVal() != null)
												ruleCodeSequenceBO
														.setLineHcptHighVal(ruleReportResults
																.getHcptHighVal());
											if (ruleReportResults
													.getHcptLowVal() != null)
												ruleCodeSequenceBO
														.setLineHcptLowVal(ruleReportResults
																.getHcptLowVal());
											if (ruleReportResults
													.getLmtClassHigh() != null)
												ruleCodeSequenceBO
														.setLineLmtClsHigh(ruleReportResults
																.getLmtClassHigh());
											if (ruleReportResults
													.getLmtClassLow() != null)
												ruleCodeSequenceBO
														.setLineLmtClsLow(ruleReportResults
																.getLmtClassLow());

											if (ruleReportResults
													.getRevHighVal() != null)
												ruleCodeSequenceBO
														.setLineRevHighVal(ruleReportResults
																.getRevHighVal());
											if (ruleReportResults
													.getRevLowVal() != null)
												ruleCodeSequenceBO
														.setLineRevLowVal(ruleReportResults
																.getRevLowVal());
											/*
											 * if(ruleSequenceResults.
											 * getLineHcptHighVal() != null)
											 * ruleCodeSequenceBO
											 * .setLineHcptHighVal
											 * (ruleSequenceResults
											 * .getLineHcptHighVal()); if
											 * (ruleSequenceResults
											 * .getLineHcptLowVal() != null)
											 * ruleCodeSequenceBO
											 * .setLineHcptLowVal
											 * (ruleSequenceResults
											 * .getLineHcptHighVal());
											 */
											if (ruleReportResults
													.getSrvcClassHigh() != null)
												ruleCodeSequenceBO
														.setLineSrvcClsHigh(ruleReportResults
																.getSrvcClassHigh());
											if (ruleReportResults
													.getSrvcClassLow() != null)
												ruleCodeSequenceBO
														.setLineSrvcClsLow(ruleReportResults
																.getSrvcClassLow());

											// set vals 1 end
											ruleCodeSequenceMap
													.put(
															ruleReportResults
																	.getCodeSequenceNo(),
															ruleCodeSequenceBO);
											ruleSequenceBO
													.setRuleCodeSequenceMap(ruleCodeSequenceMap);
										}
									}
									ruleClaimSequenceMap = ruleSequenceBO
											.getRuleClaimSequenceMap();
									if (ruleReportResults.getCodeSequenceNo() > -1) {
										if (!ruleClaimSequenceMap
												.containsKey(ruleReportResults
														.getClmSeqNumber())) {
											ruleClaimSequenceBO = new RuleClaimSequenceBO();
											// set vals to prepare claim
											// sequence code
											if (0 != ruleReportResults
													.getClmSeqNumber())
												ruleClaimSequenceBO
														.setClmSqncNbr(ruleReportResults
																.getClmSeqNumber());
											if (ruleReportResults
													.getClmSrvcCode() != null)
												ruleClaimSequenceBO
														.setClmServiceCode(ruleReportResults
																.getClmSrvcCode());
											if (ruleReportResults
													.getClmProcdrModfrCode1() != null)
												ruleClaimSequenceBO
														.setClmProcessModifierCode(ruleReportResults
																.getClmProcdrModfrCode1());
											if (ruleReportResults
													.getClmProcdrModfrCode2() != null)
												ruleClaimSequenceBO
														.setClmProcessModifierSecondaryCode(ruleReportResults
																.getClmProcdrModfrCode2());
											if (ruleReportResults
													.getClmttCode() != null)
												ruleClaimSequenceBO
														.setClmttCd(ruleReportResults
																.getClmttCode());
											if (ruleReportResults
													.getClmPlaceOfSrvc() != null)
												ruleClaimSequenceBO
														.setClmPosCd(ruleReportResults
																.getClmPlaceOfSrvc());
											if (ruleReportResults
													.getClmHmoClassCode() != null)
												ruleClaimSequenceBO
														.setClmHMOClassCode(ruleReportResults
																.getClmHmoClassCode());
											if (ruleReportResults
													.getClmSameDaySvcInd() != null)
												ruleClaimSequenceBO
														.setClmSameDaySrvcInd(ruleReportResults
																.getClmSameDaySvcInd());
											if (ruleReportResults
													.getClmSprtgProcCdInd() != null)
												ruleClaimSequenceBO
														.setClmSprtgProcCdInd(ruleReportResults
																.getClmSprtgProcCdInd());
											if (ruleReportResults
													.getClmDiagInd() != null)
												ruleClaimSequenceBO
														.setClaimDiagnosisIndicator(ruleReportResults
																.getClmDiagInd());
										
											// set vals end
											/*
											 * ruleClaimSequenceMap.put(ruleSequenceResults
											 * .getClmSqncNbr(),
											 * ruleClaimSequenceBO);
											 * ruleSequenceBO
											 * .setRuleClaimSequenceMap
											 * (ruleClaimSequenceMap);
											 */

										} else {
											ruleClaimSequenceBO = ruleClaimSequenceMap
													.get(ruleReportResults
															.getClmSeqNumber());
										}

										ruleClaimCodeSequenceMap = ruleClaimSequenceBO
												.getRuleClaimCodeSequenceMap();
										if (ruleReportResults
												.getClmCdSeqNumber() > -1) {
											if (!ruleClaimCodeSequenceMap
													.containsKey(ruleReportResults
															.getClmCdSeqNumber())) {
												ruleClaimCodeSequenceBO = new RuleClaimCodeSequenceBO();
												// set vals
												if (0 != ruleReportResults
														.getClmCdSeqNumber())
													ruleClaimCodeSequenceBO
															.setClmCdSqncNbr(ruleReportResults
																	.getClmCdSeqNumber());

												if (ruleReportResults
														.getClmRevHighVal() != null)
													ruleClaimCodeSequenceBO
															.setClmRevHighVal(ruleReportResults
																	.getClmRevHighVal());
												if (ruleReportResults
														.getClmRevLowVal() != null)
													ruleClaimCodeSequenceBO
															.setClmRevLowVal(ruleReportResults
																	.getClmRevLowVal());
												if (ruleReportResults
														.getClmDiagHighVal() != null)
													ruleClaimCodeSequenceBO
															.setClmDiagHighVal(ruleReportResults
																	.getClmDiagHighVal());
												if (ruleReportResults
														.getClmDiagLowVal() != null)
													ruleClaimCodeSequenceBO
															.setClmDiagLowVal(ruleReportResults
																	.getClmDiagLowVal());

												ArrayList clmCodeList = new ArrayList();
												if (ruleReportResults
														.getClmDiagHighVal() != null
														&& ruleReportResults
																.getClmDiagLowVal() != null
														&& ruleReportResults
																.getClmDiagVrsnInd() != null) {

													RuleCodeRanges clmDiagCodeRanges = new RuleCodeRanges();
													clmDiagCodeRanges
															.setCodeTypeLowVal(ruleReportResults
																	.getClmDiagLowVal());
													clmDiagCodeRanges
															.setCodeTypeHighVal(ruleReportResults
																	.getClmDiagHighVal());
													clmDiagCodeRanges
															.setCodeType("DIA");
													clmDiagCodeRanges
															.setIcdVersionIndicator(ruleReportResults
																	.getClmDiagVrsnInd());

													clmCodeList
															.add(clmDiagCodeRanges);
												}

												if (ruleReportResults
														.getClmIcdpHighVal() != null
														&& ruleReportResults
																.getClmIcdpLowVal() != null
														&& ruleReportResults
																.getClmIcdVrsnInd() != null) {
													RuleCodeRanges clmIcdCodeRanges = new RuleCodeRanges();
													clmIcdCodeRanges
															.setCodeTypeLowVal(ruleReportResults
																	.getClmIcdpLowVal());
													clmIcdCodeRanges
															.setCodeTypeHighVal(ruleReportResults
																	.getClmIcdpHighVal());
													clmIcdCodeRanges
															.setCodeType("ICD");
													clmIcdCodeRanges
															.setIcdVersionIndicator(ruleReportResults
																	.getClmIcdVrsnInd());

													clmCodeList
															.add(clmIcdCodeRanges);
												}
												ruleClaimCodeSequenceBO
														.setClaimCodeList(clmCodeList);

												if (ruleReportResults
														.getClmHcptHighVal() != null)
													ruleClaimCodeSequenceBO
															.setClmHcptHighVal(ruleReportResults
																	.getClmHcptHighVal());
												if (ruleReportResults
														.getClmHcptLowVal() != null)
													ruleClaimCodeSequenceBO
															.setClmHcptLowVal(ruleReportResults
																	.getClmHcptLowVal());
												if (ruleReportResults
														.getClmSrvcClassHigh() != null)
													ruleClaimCodeSequenceBO
															.setClmServiceClassHigh(ruleReportResults
																	.getClmSrvcClassHigh());
												if (ruleReportResults
														.getClmSrvcClassLow() != null)
													ruleClaimCodeSequenceBO
															.setClmServiceClassLow(ruleReportResults
																	.getClmSrvcClassLow());
												if (ruleReportResults
														.getClmLmtClassHigh() != null)
													ruleClaimCodeSequenceBO
															.setClmLimitClassHigh(ruleReportResults
																	.getClmLmtClassHigh());
												if (ruleReportResults
														.getClmLmtClassLow() != null)
													ruleClaimCodeSequenceBO
															.setClmLimitClassLow(ruleReportResults
																	.getClmLmtClassLow());
												// set vals end
												ruleClaimCodeSequenceMap
														.put(
																ruleReportResults
																		.getClmCdSeqNumber(),
																ruleClaimCodeSequenceBO);
												ruleClaimSequenceBO
														.setRuleClaimCodeSequenceMap(ruleClaimCodeSequenceMap);

											}
										}
										//
										ruleClaimSequenceMap.put(
												ruleReportResults
														.getClmSeqNumber(),
												ruleClaimSequenceBO);
										ruleSequenceBO
												.setRuleClaimSequenceMap(ruleClaimSequenceMap);
									}
								} else {
									ruleSequenceBO = new RuleSequenceBO();
									ruleCodeSequenceBO = new RuleCodeSequenceBO();
									ruleClaimSequenceBO = new RuleClaimSequenceBO();
									ruleClaimCodeSequenceBO = new RuleClaimCodeSequenceBO();
									ruleCodeSequenceMap = new HashMap<Integer, RuleCodeSequenceBO>();
									ruleClaimSequenceMap = new HashMap<Integer, RuleClaimSequenceBO>();
									ruleClaimCodeSequenceMap = new HashMap<Integer, RuleClaimCodeSequenceBO>();

									if (ruleReportResults != null) {
										if (ruleReportResults
												.getRuleSequenceNo() > -1)// need
											// to
											// revisit
											ruleSequenceBO
													.setRuleSequenceNumber(ruleReportResults
															.getRuleSequenceNo());
										if (ruleReportResults.getRuleID() != null)
											ruleSequenceBO
													.setRuleId(ruleReportResults
															.getRuleID());
										if(ruleReportResults.getRuleGrpInd()!= null)
											ruleSequenceBO.setRuleGrpInd(ruleReportResults
													         .getRuleGrpInd());
										if(ruleReportResults.getRuleAprvdDate() != null)
											ruleSequenceBO.setRuleAprvdDate(ruleReportResults
													         .getRuleAprvdDate());
										if (ruleReportResults
												.getBenefitComponent() != null)
											ruleSequenceBO
													.setBenefitComponent(ruleReportResults
															.getBenefitComponent());
										if (ruleReportResults.getBenefit() != null)
											ruleSequenceBO
													.setBenefit(ruleReportResults
															.getBenefit());
										if (ruleReportResults.getExclusionInd() != null)
											ruleSequenceBO
													.setExclsnInd(ruleReportResults
															.getExclusionInd());
										if (ruleReportResults.getRuleTypeCode() != null)
											ruleSequenceBO.setRuleTypeCode(ruleReportResults
													        .getRuleTypeCode());
										if (ruleReportResults.getClaimType() != null)
											ruleSequenceBO
													.setClmType(ruleReportResults
															.getClaimType());
										if (ruleReportResults.getPlcOfSrvc() != null)
											ruleSequenceBO
													.setPlaceOfService(ruleReportResults
															.getPlcOfSrvc());
										if (ruleReportResults.getPatHighAge() > -1)
											ruleSequenceBO
													.setPatHighAge(ruleReportResults
															.getPatHighAge());
										if (ruleReportResults.getPatLowAge() > -1)
											ruleSequenceBO
													.setPatLowAge(ruleReportResults
															.getPatLowAge());
										if (ruleReportResults.getGenderCode() != null)
											ruleSequenceBO
													.setGenderCode(ruleReportResults
															.getGenderCode());
										if (ruleReportResults.getProviderID() != null)
											ruleSequenceBO
													.setProviderId(ruleReportResults
															.getProviderID());
										if (ruleReportResults
												.getProviderSpcltyCode() != null)
											ruleSequenceBO
													.setProviderSpecialityCode(ruleReportResults
															.getProviderSpcltyCode());
										if (ruleReportResults.getBnftAccumNm() != null)
											ruleSequenceBO
													.setBenefitAccmNum(ruleReportResults
															.getBnftAccumNm());
										if(ruleReportResults.getBnftAccumLmtAmt()!=null){
										if (Float.parseFloat(ruleReportResults
												.getBnftAccumLmtAmt()) > 0)// need
											// to
											// revisit
											ruleSequenceBO
													.setBenfitAccumLimtAmnt(ruleReportResults
															.getBnftAccumLmtAmt());
										}
										if (ruleReportResults
												.getBnftAccumLmtNmr() > -1)// need
											// to
											// revisit
											ruleSequenceBO
													.setBenefitAccumLimtNum(ruleReportResults
															.getBnftAccumLmtNmr());
										if (ruleReportResults
												.getNotifyOnlyInd() != null)
											ruleSequenceBO
													.setNtfyOnlyInd(ruleReportResults
															.getNotifyOnlyInd());
										if (ruleReportResults.getClnlRvwInd() != null)
											ruleSequenceBO
													.setClnlRevwInd(ruleReportResults
															.getClnlRvwInd());
										if(ruleReportResults.getDollarLimit() !=null){
										if (Float.parseFloat(ruleReportResults.getDollarLimit()) > 0)
											ruleSequenceBO
													.setDlrLimit(ruleReportResults
															.getDollarLimit());
										}
										if (ruleReportResults.getServiceCode() != null)
											ruleSequenceBO
													.setServiceCode(ruleReportResults
															.getServiceCode());
										if (ruleReportResults.getGroupState() != null)
											ruleSequenceBO
													.setGrpSt(ruleReportResults
															.getGroupState());
										if (ruleReportResults.getLenOfStay() > -1)
											ruleSequenceBO
													.setLenOfStay(ruleReportResults
															.getLenOfStay());
										if (ruleReportResults
												.getItsSpecltyCode() != null)
											ruleSequenceBO
													.setItsSpecialityCode(ruleReportResults
															.getItsSpecltyCode());
										if (ruleReportResults
												.getMbrRelshpCode() != null)
											ruleSequenceBO
													.setMembrRelnCode(ruleReportResults
															.getMbrRelshpCode());
										if(ruleReportResults.getHcpcsModfr2Code() !=null)
											ruleSequenceBO.
											setProcedureModifierSecondaryCode(ruleReportResults.getHcpcsModfr2Code());
										if(ruleReportResults.getHcpcsModfrCode() !=null)
											ruleSequenceBO.
											setPrecedrModifierCode(ruleReportResults.getHcpcsModfrCode());
										if (ruleReportResults
												.getProviderStateCode() != null)
											ruleSequenceBO
													.setProviderStCode(ruleReportResults
															.getProviderStateCode());
										if (ruleReportResults.getBillTypeCode() != null)
											ruleSequenceBO
													.setBillTypeCode(ruleReportResults
															.getBillTypeCode());
										if (ruleReportResults.getTtCode() != null)
											ruleSequenceBO
													.setTtCode(ruleReportResults
															.getTtCode());
										if (ruleReportResults.getAtchmtInd() != null)
											ruleSequenceBO
													.setAttachmentInd(ruleReportResults
															.getAtchmtInd());

										if (ruleReportResults.getPatmbrCode() != null)
											ruleSequenceBO
													.setPatMembrCode(ruleReportResults
															.getPatmbrCode());
										if (ruleReportResults.getHospFcilCode() != null)
											ruleSequenceBO
													.setHosptlFacilCode(ruleReportResults
															.getHospFcilCode());
										if (ruleReportResults
												.getDaysFromInjry() > -1)
											ruleSequenceBO
													.setDaysFrmInjury(ruleReportResults
															.getDaysFromInjry());
										if (ruleReportResults.getDaysFromIlns() > -1)
											ruleSequenceBO
													.setDaysFrmIllness(ruleReportResults
															.getDaysFromIlns());
										if (ruleReportResults.getHmoClassCode() != null)
											ruleSequenceBO
													.setHmoClsCode(ruleReportResults
															.getHmoClassCode());
										if (ruleReportResults.getEditCode1() != null)
											ruleSequenceBO
													.setEditCode1(ruleReportResults
															.getEditCode1());
										if (ruleReportResults.getEditCode2() != null)
											ruleSequenceBO
													.setEditCode2(ruleReportResults
															.getEditCode2());
										if (ruleReportResults.getCopayInd() != null)
											ruleSequenceBO
													.setCopayIndicator(ruleReportResults
															.getCopayInd());
										if (ruleReportResults.getPct100Ind() != null)
											ruleSequenceBO
													.setHunPerIndicator(ruleReportResults
															.getPct100Ind());
										if(ruleReportResults.getAgeTypCode() != null)
											ruleSequenceBO.setAgeTypCode(ruleReportResults
													        .getAgeTypCode());
										
										if (ruleReportResults.getSrvcStrtDate() != null)
											ruleSequenceBO
													.setServcStartDate(ruleReportResults
															.getSrvcStrtDate());
										if (ruleReportResults.getSrvcEndDate() != null)
											ruleSequenceBO
													.setServcEndDate(ruleReportResults
															.getSrvcEndDate());
										if (ruleReportResults
												.getTotlChrgSignVal() != null)
											ruleSequenceBO
													.setTotalChargeSign(ruleReportResults
															.getTotlChrgSignVal());
										if(ruleReportResults.getTotlChrgAmt() !=null){
										if (Float.parseFloat(ruleReportResults.getTotlChrgAmt()) > 0)
											ruleSequenceBO
													.setTotalChargeAmnt(ruleReportResults
															.getTotlChrgAmt());
										}
										/*
										 * if (ruleReportBO.getWpdDelFlag() !=
										 * null) ruleSequenceBO
										 * .setWpdDelFlag(ruleSequenceResults
										 * .getWpdDelFlag()); if
										 * (ruleReportBO.getClmLevelDataInd() !=
										 * null) ruleSequenceBO
										 * .setClmLevelDataInd
										 * (ruleSequenceResults
										 * .getClmLevelDataInd());
										 */
										
										
										if (ruleReportResults
												.getMedcrAsgnmentInd() != null)
											ruleSequenceBO
													.setMedicareAssgnIndicator(ruleReportResults
															.getMedcrAsgnmentInd());
										if(ruleReportResults.getSprtgProcCodeInd() !=null)
											ruleSequenceBO.
											setSupportHcpIndicator(ruleReportResults.getSprtgProcCodeInd());
										if (ruleReportResults.getDrugCode() != null)
											ruleSequenceBO
													.setDrugCode(ruleReportResults
															.getDrugCode());
										
										if (ruleReportResults.getDiagCode() != null)
											ruleSequenceBO
													.setDiagnosisIndicator(ruleReportResults
															.getDiagCode());
										if (ruleReportResults.getPcpInd() != null)
											ruleSequenceBO
													.setPcpIndicator(ruleReportResults
															.getPcpInd());
										if (ruleReportResults.getProviderSpecialityIndicator() != null)
											ruleSequenceBO
													.setProviderSpecialityIndicator(ruleReportResults
															.getProviderSpecialityIndicator());
										if (ruleReportResults.getProvLncsId() != null)
											ruleSequenceBO
													.setProvLncsId(ruleReportResults
															.getProvLncsId());
										if (ruleReportResults.getProvMedcrId() != null)
											ruleSequenceBO
													.setProvMedcrId(ruleReportResults
															.getProvMedcrId());
										if (ruleReportResults.getBillgProvNbr()!=null)
											ruleSequenceBO
													.setBillgProvNbr(ruleReportResults
															.getBillgProvNbr());
										if (ruleReportResults.getRndrgProvNbr()!=null)
											ruleSequenceBO
													.setRndrgProvNbr(ruleReportResults
															.getRndrgProvNbr());
										if (ruleReportResults.getBillgNpiNbr()!=null)
											ruleSequenceBO
													.setBillgNpiNbr(ruleReportResults
															.getBillgNpiNbr());
										if (ruleReportResults.getRndrgNpiNbr()!=null)
											ruleSequenceBO
													.setRndrgNpiNbr(ruleReportResults
															.getRndrgNpiNbr());
										if (ruleReportResults
												.getElgblExpansSignCode() != null)
											ruleSequenceBO
													.setElgblExpansSignCode(ruleReportResults
															.getElgblExpansSignCode());
										if(ruleReportResults
												.getElgblExpansAmt() !=null){
										if (Float.parseFloat(ruleReportResults
												.getElgblExpansAmt()) > 0)
											ruleSequenceBO
													.setElgblExpansAmt(ruleReportResults
															.getElgblExpansAmt());
										}

										// Line level
										if (0 != ruleReportResults
												.getCodeSequenceNo())
											ruleCodeSequenceBO
													.setCdSqncNbr(ruleReportResults
															.getCodeSequenceNo());

										ArrayList lineCodeList = new ArrayList();
										if (ruleReportResults.getDiagHighVal() != null
												&& ruleReportResults
														.getDiagLowVal() != null
												&& ruleReportResults
														.getDiagvrsnInd() != null) {

											RuleCodeRanges ruleCodeRanges = new RuleCodeRanges();
											ruleCodeRanges
													.setCodeTypeLowVal(ruleReportResults
															.getDiagLowVal());
											ruleCodeRanges
													.setCodeTypeHighVal(ruleReportResults
															.getDiagHighVal());
											ruleCodeRanges.setCodeType("DIA");
											ruleCodeRanges
													.setIcdVersionIndicator(ruleReportResults
															.getDiagvrsnInd());

											lineCodeList.add(ruleCodeRanges);
										}
										if (ruleReportResults.getIcdpHighVal() != null
												&& ruleReportResults
														.getIcdpLowVal() != null
												&& ruleReportResults
														.getIcdpVrsnInd() != null) {
											RuleCodeRanges lineIcdCodeRanges = new RuleCodeRanges();
											lineIcdCodeRanges
													.setCodeTypeLowVal(ruleReportResults
															.getIcdpLowVal());
											lineIcdCodeRanges
													.setCodeTypeHighVal(ruleReportResults
															.getIcdpHighVal());
											lineIcdCodeRanges
													.setCodeType("ICD");
											lineIcdCodeRanges
													.setIcdVersionIndicator(ruleReportResults
															.getIcdpVrsnInd());

											lineCodeList.add(lineIcdCodeRanges);
										}
										ruleCodeSequenceBO
												.setCodeList(lineCodeList);

										if (ruleReportResults.getHcptHighVal() != null)
											ruleCodeSequenceBO
													.setLineHcptHighVal(ruleReportResults
															.getHcptHighVal());
										if (ruleReportResults.getHcptLowVal() != null)
											ruleCodeSequenceBO
													.setLineHcptLowVal(ruleReportResults
															.getHcptLowVal());
										if (ruleReportResults.getLmtClassHigh() != null)
											ruleCodeSequenceBO
													.setLineLmtClsHigh(ruleReportResults
															.getLmtClassHigh());
										if (ruleReportResults.getLmtClassLow() != null)
											ruleCodeSequenceBO
													.setLineLmtClsLow(ruleReportResults
															.getLmtClassLow());
										if (ruleReportResults.getRevHighVal() != null)
											ruleCodeSequenceBO
													.setLineRevHighVal(ruleReportResults
															.getRevHighVal());
										if (ruleReportResults.getRevLowVal() != null)
											ruleCodeSequenceBO
													.setLineRevLowVal(ruleReportResults
															.getRevLowVal());
										
										if (ruleReportResults
												.getSrvcClassHigh() != null)
											ruleCodeSequenceBO
													.setLineSrvcClsHigh(ruleReportResults
															.getSrvcClassHigh());
										if (ruleReportResults.getSrvcClassLow() != null)
											ruleCodeSequenceBO
													.setLineSrvcClsLow(ruleReportResults
															.getSrvcClassLow());

										// Claim level
										if (0 != ruleReportResults
												.getClmSeqNumber())
											ruleClaimSequenceBO
													.setClmSqncNbr(ruleReportResults
															.getClmSeqNumber());
										if (ruleReportResults.getClmSrvcCode() != null)
											ruleClaimSequenceBO
													.setClmServiceCode(ruleReportResults
															.getClmSrvcCode());
										if (ruleReportResults
												.getClmProcdrModfrCode1() != null)
											ruleClaimSequenceBO
													.setClmProcessModifierCode(ruleReportResults
															.getClmProcdrModfrCode1());
										if (ruleReportResults
												.getClmProcdrModfrCode2() != null)
											ruleClaimSequenceBO
													.setClmProcessModifierSecondaryCode(ruleReportResults
															.getClmProcdrModfrCode2());
										if (ruleReportResults.getClmttCode() != null)
											ruleClaimSequenceBO
													.setClmttCd(ruleReportResults
															.getClmttCode());
										if (ruleReportResults
												.getClmPlaceOfSrvc() != null)
											ruleClaimSequenceBO
													.setClmPosCd(ruleReportResults
															.getClmPlaceOfSrvc());
										if (ruleReportResults
												.getClmHmoClassCode() != null)
											ruleClaimSequenceBO
													.setClmHMOClassCode(ruleReportResults
															.getClmHmoClassCode());
										if (ruleReportResults
												.getClmSameDaySvcInd() != null)
											ruleClaimSequenceBO
													.setClmSameDaySrvcInd(ruleReportResults
															.getClmSameDaySvcInd());
										if (ruleReportResults
												.getClmSprtgProcCdInd() != null)
											ruleClaimSequenceBO
													.setClmSprtgProcCdInd(ruleReportResults
															.getClmSprtgProcCdInd());
										if (ruleReportResults.getClmDiagInd() != null)
											ruleClaimSequenceBO
													.setClaimDiagnosisIndicator(ruleReportResults
															.getClmDiagInd());
										

										// Claim code level
										if (0 != ruleReportResults
												.getClmCdSeqNumber())
											ruleClaimCodeSequenceBO
													.setClmCdSqncNbr(ruleReportResults
															.getClmCdSeqNumber());

										ArrayList clmCodeList = new ArrayList();
										if (ruleReportResults
												.getClmDiagHighVal() != null
												&& ruleReportResults
														.getClmDiagLowVal() != null
												&& ruleReportResults
														.getClmDiagVrsnInd() != null) {

											RuleCodeRanges clmDiagCodeRanges = new RuleCodeRanges();
											clmDiagCodeRanges
													.setCodeTypeLowVal(ruleReportResults
															.getClmDiagLowVal());
											clmDiagCodeRanges
													.setCodeTypeHighVal(ruleReportResults
															.getClmDiagHighVal());
											clmDiagCodeRanges
													.setCodeType("DIA");
											clmDiagCodeRanges
													.setIcdVersionIndicator(ruleReportResults
															.getClmDiagVrsnInd());

											clmCodeList.add(clmDiagCodeRanges);
										}

										if (ruleReportResults
												.getClmIcdpHighVal() != null
												&& ruleReportResults
														.getClmIcdpLowVal() != null
												&& ruleReportResults
														.getClmIcdVrsnInd() != null) {
											RuleCodeRanges clmIcdCodeRanges = new RuleCodeRanges();
											clmIcdCodeRanges
													.setCodeTypeLowVal(ruleReportResults
															.getClmIcdpLowVal());
											clmIcdCodeRanges
													.setCodeTypeHighVal(ruleReportResults
															.getClmIcdpHighVal());
											clmIcdCodeRanges.setCodeType("ICD");
											clmIcdCodeRanges
													.setIcdVersionIndicator(ruleReportResults
															.getClmIcdVrsnInd());

											clmCodeList.add(clmIcdCodeRanges);
										}
										ruleClaimCodeSequenceBO
												.setClaimCodeList(clmCodeList);

										if (ruleReportResults
												.getClmRevHighVal() != null)
											ruleClaimCodeSequenceBO
													.setClmRevHighVal(ruleReportResults
															.getClmRevHighVal());
										if (ruleReportResults.getClmRevLowVal() != null)
											ruleClaimCodeSequenceBO
													.setClmRevLowVal(ruleReportResults
															.getClmRevLowVal());

										if (ruleReportResults
												.getClmHcptHighVal() != null)
											ruleClaimCodeSequenceBO
													.setClmHcptHighVal(ruleReportResults
															.getClmHcptHighVal());
										if (ruleReportResults
												.getClmHcptLowVal() != null)
											ruleClaimCodeSequenceBO
													.setClmHcptLowVal(ruleReportResults
															.getClmHcptLowVal());
										if (ruleReportResults
												.getClmSrvcClassHigh() != null)
											ruleClaimCodeSequenceBO
													.setClmServiceClassHigh(ruleReportResults
															.getClmSrvcClassHigh());
										if (ruleReportResults
												.getClmSrvcClassLow() != null)
											ruleClaimCodeSequenceBO
													.setClmServiceClassLow(ruleReportResults
															.getClmSrvcClassLow());
										if (ruleReportResults
												.getClmLmtClassHigh() != null)
											ruleClaimCodeSequenceBO
													.setClmLimitClassHigh(ruleReportResults
															.getClmLmtClassHigh());
										if (ruleReportResults
												.getClmLmtClassLow() != null)
											ruleClaimCodeSequenceBO
													.setClmLimitClassLow(ruleReportResults
															.getClmLmtClassLow());

										// code level
										ruleCodeSequenceMap.put(
												ruleReportResults
														.getCodeSequenceNo(),
												ruleCodeSequenceBO);
										ruleSequenceBO
												.setRuleCodeSequenceMap(ruleCodeSequenceMap);
										// claim code level
										ruleClaimCodeSequenceMap.put(
												ruleReportResults
														.getClmCdSeqNumber(),
												ruleClaimCodeSequenceBO);
										ruleClaimSequenceBO
												.setRuleClaimCodeSequenceMap(ruleClaimCodeSequenceMap);
										// claim level
										ruleClaimSequenceMap.put(
												ruleReportResults
														.getClmSeqNumber(),
												ruleClaimSequenceBO);
										ruleSequenceBO
												.setRuleClaimSequenceMap(ruleClaimSequenceMap);

									}

								}
								// rule seq.
								ruleSequenceMap.put(ruleReportResults
										.getRuleSequenceNo(), ruleSequenceBO);
								ruleDisplayBO
										.setRuleSequenceMap(ruleSequenceMap);

							}
						}
						// close of tempn list

					} catch (Exception ex) {
						Logger
								.logError("Exception occured while create RuleReportBo");
						System.out
								.println("Exception occured while create RuleReportBo");
						Logger.logError(ex);
					}

				}
				ruleMap.put(ruleDisplayBO.getRuleId(), ruleDisplayBO);
			}
			
		}// for each ends
		return ruleMap;
	}

	private static List<RuleReportBO> createTempList(
			List<RuleReportBO> ruleReportSequenceLists, String ruleId) {
		ArrayList<RuleReportBO> tempList = new ArrayList<RuleReportBO>();
		for (RuleReportBO ruleReportBO : ruleReportSequenceLists) {
			if (ruleReportBO.getRuleID().equals(ruleId)) {
				tempList.add(ruleReportBO);
			}
		}
		return tempList;
	}

}
