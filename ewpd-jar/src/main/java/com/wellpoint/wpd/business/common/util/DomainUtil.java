/*
 * DomainUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.ets.ebx.simulation.webservices.client.ContractMappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.Eb03AssociationWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.ErrorDetailWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.HippaCodeValueWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.HippaSegmentWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.MajorHeadingsWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.MappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.MinorHeadingsWebServiceVO;
import com.wellpoint.wpd.business.common.adapter.DomainAdapterManager;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainInfo;
import com.wellpoint.wpd.common.domain.bo.EntityDomainInfo;
import com.wellpoint.wpd.common.domain.bo.QuestionnaireDomainInfo;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.util.WebServiceConstants;
import com.wellpoint.wpd.web.contract.ContractKeyObject;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DomainUtil {
	
	
	public static boolean doesEBXErrorExist(
			List<ContractWebServiceVO> contractWSErrorList) {
		boolean canLoopFirst = true;
		boolean canLoopSecond = true;

		boolean canDisplayEBXErrors = false;
		if (contractWSErrorList != null && contractWSErrorList.size() > 0) {
			canDisplayEBXErrors = true;
			if (canLoopFirst) {
				for (int j = 0; j < contractWSErrorList.size(); j++) {
					if (contractWSErrorList.get(j).getErrorCodesList() != null
							&& contractWSErrorList.get(j).getErrorCodesList()
									.size() > 0) {
						canDisplayEBXErrors = true;
						canLoopSecond = false;
						break;
					}
					if (contractWSErrorList.get(j)
							.getContractMappingVOList() != null
							&& contractWSErrorList.get(j)
									.getContractMappingVOList().size() > 0) {
						
						for(ContractMappingWebServiceVO contractMappingWebServiceVO : contractWSErrorList.get(j).getContractMappingVOList()){
							if (contractMappingWebServiceVO.getErrorCodesList() != null
									&& contractMappingWebServiceVO.getErrorCodesList().size() > 0) {
								canDisplayEBXErrors = true;
								canLoopSecond = false;
								break;
							}
						}
						if (canDisplayEBXErrors) {
							break;
						}
					}
					if (!canDisplayEBXErrors) {
						if (canLoopSecond) {
							for (int i = 0; i < contractWSErrorList.get(j)
									.getContractMappingVOList().size(); i++) {
								if (contractWSErrorList.get(j)
										.getContractMappingVOList().get(i)
										.getErrorCodesList() != null
										&& contractWSErrorList.get(j)
												.getContractMappingVOList()
												.get(i).getErrorCodesList()
												.size() > 0) {
									canDisplayEBXErrors = true;
									canLoopFirst = false;
									break;
								}
							}
						}
						Iterator it1 = contractWSErrorList.get(j)
								.getMajorHeadings().getEntry().iterator();
						while (it1.hasNext()) {
							ContractWebServiceVO.MajorHeadings.Entry pairs1 = (ContractWebServiceVO.MajorHeadings.Entry) it1
									.next();
							Iterator it2 = pairs1.getValue().getMinorHeadings()
									.getEntry().iterator();
							while (it2.hasNext()) {
								MajorHeadingsWebServiceVO.MinorHeadings.Entry pairs2 = (MajorHeadingsWebServiceVO.MinorHeadings.Entry) it2
										.next();
								if (pairs2.getValue().getErrorCodesList() != null
										&& pairs2.getValue()
												.getErrorCodesList().size() > 0) {
									canDisplayEBXErrors = true;
									break;
								}
								Iterator it3 = pairs2.getValue().getMappings()
										.getEntry().iterator();
								while (it3.hasNext()) {
									MinorHeadingsWebServiceVO.Mappings.Entry pairs3 = (MinorHeadingsWebServiceVO.Mappings.Entry) it3
											.next();
									MappingWebServiceVO mappingWebServiceVO = (MappingWebServiceVO) pairs3
											.getValue();
									if (mappingWebServiceVO
											.getContractMapping() != null) {
										if (mappingWebServiceVO
												.getContractMapping()
												.getErrorCodesList() != null
												&& mappingWebServiceVO
														.getContractMapping()
														.getErrorCodesList()
														.size() > 0) {
											canDisplayEBXErrors = true;
											break;
										}
									}
									if (canDisplayEBXErrors) {
										break;
									}
									it3.remove(); // avoids a
													// ConcurrentModificationException
								}
								if (canDisplayEBXErrors) {
									break;
								}
								it2.remove(); // avoids a
												// ConcurrentModificationException
							}
							if (canDisplayEBXErrors) {
								break;
							}
							it1.remove(); // avoids a
											// ConcurrentModificationException
						}
					}
				}
			}
		}

		return canDisplayEBXErrors;

	}
	
	public static EbxWebSerPopupDisplayVO ebxPopupDisplayValues(ContractKeyObject contractKeyObject){
		
		EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO = new EbxWebSerPopupDisplayVO();
		
			ebxWebSerPopupDisplayVO.setEbxContract(contractKeyObject.getContractId());
			ebxWebSerPopupDisplayVO.setEbxVersion(contractKeyObject.getVersion());
			ebxWebSerPopupDisplayVO.setEbxEffectiveDate(contractKeyObject.getEffectiveDate());
			ebxWebSerPopupDisplayVO.setEbxExpiryDate(contractKeyObject.getExpiryDate());
		
		return ebxWebSerPopupDisplayVO;
	}
	
	public static List<EbxWebSerDisplayVO> processListDisplayScreen(List<ContractWebServiceVO> contractWSErrorList){
		
		List<EbxWebSerDisplayVO> ebxWebSerDisplayList = new ArrayList<EbxWebSerDisplayVO>();
		String tdValues[] = null;
		String tempArray[] = null;
		List<String[]> tdValueList = new ArrayList<String[]>();
		ContractWebServiceVO.MajorHeadings.Entry pairs1 = null;
		MajorHeadingsWebServiceVO.MinorHeadings.Entry pairs2 = null;
		MinorHeadingsWebServiceVO.Mappings.Entry pairs3 = null;
		Object obj = null;
		List<ErrorDetailWebServiceVO> errorDetailListAtMinorHeadingsLevel = null;
		List<ErrorDetailWebServiceVO> errorDetailListAtContractLevel = null;
		List<ErrorDetailWebServiceVO> errorDetailListAtMappingLevel = null;
		List<ErrorDetailWebServiceVO> errorDetailListAtMappingLevel1 = null;

		for (int i = 0; i < contractWSErrorList.size(); i++) {
			ContractWebServiceVO contractWebServiceVO = contractWSErrorList
					.get(i);
			// 1. Contract  ErrorList 
			errorDetailListAtContractLevel = contractWebServiceVO
					.getErrorCodesList();
			if (errorDetailListAtContractLevel != null
					&& errorDetailListAtContractLevel.size() > 0) {
				for (ErrorDetailWebServiceVO errorDetailWebServiceVO : errorDetailListAtContractLevel) {
					tempArray = new String[11];
					tdValues = new String[11];
					tdValues[9] = errorDetailWebServiceVO
							.getErrorCode();
					tdValues[10] = errorDetailWebServiceVO
							.getErrorDesc();
					tempArray[9] = tdValues[9];
					tempArray[10] = tdValues[10];
					tdValueList.add(tempArray);
				}
			}
			// 2. Contract  ContractMappingVOList  ErrorCodeList
			if (contractWebServiceVO.getContractMappingVOList() != null
					&& contractWebServiceVO
							.getContractMappingVOList().size() > 0) {
				for (ContractMappingWebServiceVO mappingVO : contractWebServiceVO
						.getContractMappingVOList()) {
					List<ErrorDetailWebServiceVO> errorDetailListAtContractMappingVOListLevel = mappingVO
							.getErrorCodesList();
					if (errorDetailListAtContractMappingVOListLevel != null
							&& errorDetailListAtContractMappingVOListLevel
									.size() > 0) {
					for (ErrorDetailWebServiceVO errorDetailWebServiceVO : errorDetailListAtContractMappingVOListLevel) {
								tempArray = new String[11];
							tdValues = new String[11];
							tdValues[9] = errorDetailWebServiceVO
									.getErrorCode();
							tdValues[10] = errorDetailWebServiceVO
									.getErrorDesc();

							//Newly Added to display details
							tdValues[3] = "";
							if (tdValues[9]
									.equalsIgnoreCase(WebServiceConstants.ERROR_E016)) {
								tdValues[6] = (null != mappingVO.
										getEb07() ? getDispalyValues(mappingVO
										.getEb07())
										: "");
							}

							if (tdValues[9]
									.equalsIgnoreCase(WebServiceConstants.ERROR_E024)) {
								String eb12Val = null != mappingVO
										.getEb12() ? getDispalyValues(mappingVO
										.getEb12())
										: "";
								if ("Y".equalsIgnoreCase(eb12Val)) {
									tdValues[3] = "DIFFVAL-PAR";
								} else {
									tdValues[3] = "DIFFVAL-NPR";
								}
							}

							if (tdValues[9]
									.equalsIgnoreCase(WebServiceConstants.ERROR_E019)) {
								String eB03 = null != mappingVO
										.getEb03() ? getDispalyValues(mappingVO
										.getEb03())
										: "";
								if (null != eB03
										&& !eB03.trim()
												.equalsIgnoreCase(
														"")
										&& null != errorDetailWebServiceVO
												.getNetworkDescription()) {
									tdValues[3] = eB03
											+ "-"
											+ errorDetailWebServiceVO
													.getNetworkDescription();
								}
							}
							if (tdValues[9]
									.equalsIgnoreCase(WebServiceConstants.ERROR_E028)) {
								String eB03 = null != mappingVO
										.getEb03() ? getDispalyValues(mappingVO
										.getEb03())
										: "";
								if (null != eB03
										&& !eB03.trim()
												.equalsIgnoreCase(
														"")
										&& null != errorDetailWebServiceVO
												.getNetworkDescription()) {
									tdValues[3] = "ER"
											+ eB03
											+ "-"
											+ errorDetailWebServiceVO
													.getNetworkDescription();
								}
							}

							if (tdValues[9]
									.equalsIgnoreCase(WebServiceConstants.ERROR_E038)) {
								tdValues[3] = WebServiceConstants.VISION;
							}
							if (tdValues[9]
									.equalsIgnoreCase(WebServiceConstants.ERROR_E039)) {
								tdValues[3] = WebServiceConstants.DENTAL;
							}
							tempArray[3] = tdValues[3];

							//Ends here

							tempArray[9] = tdValues[9];
							tempArray[10] = tdValues[10];
							tdValueList.add(tempArray);
						}
					}
				}
			}
			Iterator it1 = contractWSErrorList.get(i)
					.getMajorHeadings().getEntry().iterator();
			while (it1.hasNext()) {
				//System.out.println("it1.hasNext()*");
				tdValues = new String[11];
				obj = it1.next();
				pairs1 = (ContractWebServiceVO.MajorHeadings.Entry) obj;
				MajorHeadingsWebServiceVO majorHeadingsWebServiceVO = (MajorHeadingsWebServiceVO) pairs1
						.getValue();
				tdValues[0] = majorHeadingsWebServiceVO
						.getDescriptionText();
				Iterator it2 = pairs1.getValue().getMinorHeadings()
						.getEntry().iterator();
				while (it2.hasNext()) {
					//System.out.println("it2.hasNext()**");
					obj = it2.next();
					pairs2 = (MajorHeadingsWebServiceVO.MinorHeadings.Entry) obj;
					MinorHeadingsWebServiceVO minorHeadingsWebServiceVO = (MinorHeadingsWebServiceVO) pairs2
							.getValue();
					tdValues[1] = minorHeadingsWebServiceVO
							.getDescriptionText();
					//3. Contract  MajorHeading  MinorHeading ErrorCodeList
					errorDetailListAtMinorHeadingsLevel = minorHeadingsWebServiceVO
							.getErrorCodesList();
					if (errorDetailListAtMinorHeadingsLevel != null
							&& errorDetailListAtMinorHeadingsLevel
									.size() > 0
							&& !(errorDetailListAtMinorHeadingsLevel
									.get(0)).toString().trim()
									.equals("")) {
						for (ErrorDetailWebServiceVO errorDetailWebServiceVO : errorDetailListAtMinorHeadingsLevel) {
							//System.out.println("for()***");
							tempArray = new String[11];
							tempArray[0] = tdValues[0];
							tempArray[1] = tdValues[1];
							tdValues[9] = errorDetailWebServiceVO
									.getErrorCode();
							tdValues[10] = errorDetailWebServiceVO
									.getErrorDesc();
							tempArray[9] = tdValues[9];
							tempArray[10] = tdValues[10];
							tdValueList.add(tempArray);
							tdValues[9] = null;
							tdValues[10] = null;
							//errorDetailListAtMinorHeadingsLevel.remove(errorDetailWebServiceVO);
						}
					}
					Iterator it3 = minorHeadingsWebServiceVO
							.getMappings().getEntry().iterator();
					while (it3.hasNext()) {
						//System.out.println("it3.hasNext()****");
						tempArray = new String[11];
						obj = it3.next();
						pairs3 = (MinorHeadingsWebServiceVO.Mappings.Entry) obj;
						//System.out.println("pairs3"+pairs3);
						//System.out.println("pairs3.getValue()"+pairs3.getValue());
						//System.out.println("pairs3.getValue().getClass().getName()"+pairs3.getValue().getClass().getName());

						//System.out.println("pairs3.getValue().getClass().getName()"+pairs3.getValue().getClass().getName());
						ContractMappingWebServiceVO mappingWebServiceVO = (ContractMappingWebServiceVO) pairs3
								.getValue();
						
						tdValues[2] = (minorHeadingsWebServiceVO != null
								&& minorHeadingsWebServiceVO.getRuleMapping() != null
								&& minorHeadingsWebServiceVO.getRuleMapping()
										.getRule() != null && minorHeadingsWebServiceVO
								.getRuleMapping().getRule().getHeaderRuleId() != null) ? minorHeadingsWebServiceVO
								.getRuleMapping().getRule().getHeaderRuleId()
								: "";  // 16332 Production fix
						//System.out.println("mappingWebServiceVO.getSpsId().getSpsId()"+mappingWebServiceVO.getSpsId().getSpsId());
						tdValues[3] = mappingWebServiceVO
								.getSpsId().getSpsId();
						tdValues[4] = mappingWebServiceVO
								.getSpsId().getLinePVA();
						tdValues[5] = mappingWebServiceVO
								.getSpsId().getLineDataType();
						tdValues[6] = mappingWebServiceVO
								.getSpsId().getLineValue();
						/*tdValues[7] = mappingWebServiceVO
								.getMessage();
						tdValues[8] = mappingWebServiceVO
								.getNoteTypeCode()
								.getHippaCodeSelectedValuesForEwpd() != null
								&& mappingWebServiceVO
										.getNoteTypeCode()
										.getHippaCodeSelectedValuesForEwpd()
										.size() > 0 ? ((HippaCodeValueWebServiceVO) mappingWebServiceVO
								.getNoteTypeCode()
								.getHippaCodeSelectedValuesForEwpd()
								.get(0)).getValue()
								: "";*/
						//System.out.println("tdValues[2]"
						//		+ tdValues[2]);
						//System.out.println("tdValues[3]"
						//		+ tdValues[3]);
						//System.out.println("tdValues[8]" + tdValues[8]);
						tempArray[2] = tdValues[2];
						tempArray[3] = tdValues[3];
						tempArray[4] = tdValues[4];
						tempArray[5] = tdValues[5];
						tempArray[6] = tdValues[6];
						tempArray[7] = tdValues[7];
						tempArray[8] = tdValues[8];
						//tdValueList.add(tempArray); // temp code
						/*
						tempArray[2] = null;
						tempArray[3] = null;
						tempArray[4] = null;
						tempArray[5] = null;
						tempArray[6] = null;
						tempArray[7] = null;
						tempArray[8] = null;
						 */
						tdValues[2] = null;
						tdValues[3] = null;
						tdValues[4] = null;
						tdValues[5] = null;
						tdValues[6] = null;
						tdValues[7] = null;
						tdValues[8] = null;
						if (mappingWebServiceVO
								.getContractMapping() != null) {
							//4. Contract  MajorHeading  MinorHeading ContractMappingVO ErrorCodeList
							errorDetailListAtMappingLevel = mappingWebServiceVO
									.getContractMapping()
									.getErrorCodesList();
							if (errorDetailListAtMappingLevel != null
									&& errorDetailListAtMappingLevel
									//Added here
											.size() > 0
									&& !(errorDetailListAtMappingLevel
											.get(0)).toString()
											.trim().equals("")) {
								for (ErrorDetailWebServiceVO errorDetailWebServiceVO : errorDetailListAtMappingLevel) {
									//tempArray = new String[11];
									Eb03AssociationWebServiceVO finalEb03Assn = new Eb03AssociationWebServiceVO();
									if(null != mappingWebServiceVO.getEb03()) {
										for(Eb03AssociationWebServiceVO eb03Assn : mappingWebServiceVO.getEb03().getEb03Association()) {
											if(null != errorDetailWebServiceVO && null != errorDetailWebServiceVO.getAssociatedEb03() &&
													null != eb03Assn && null != eb03Assn.getEb03()) {
												if(errorDetailWebServiceVO.getAssociatedEb03().equalsIgnoreCase(eb03Assn.getEb03().getValue())) {
													finalEb03Assn = eb03Assn;
												}
											}
										}
									}
									if(null != finalEb03Assn) {
										tdValues[7] = finalEb03Assn.getMessage();
										if(null != finalEb03Assn.getNoteType()) {
											tdValues[8] = finalEb03Assn.getNoteType().getValue();
										}else {
											tdValues[8] = "";
										}
									}else {
										tdValues[7] = "";
										tdValues[8] = "";
									}		
									tempArray[0] = tdValues[0];
									tempArray[1] = tdValues[1];
									tdValues[9] = errorDetailWebServiceVO
											.getErrorCode();
									tdValues[10] = errorDetailWebServiceVO
											.getErrorDesc();
									tempArray[7] = tdValues[7];
									tempArray[8] = tdValues[8];
									tempArray[9] = tdValues[9];
									tempArray[10] = tdValues[10];
									tdValueList.add(tempArray);
									tdValues[9] = null;
									tdValues[10] = null;
								}
							}
						}

						if (mappingWebServiceVO.getErrorCodesList() != null) {
							//5. Contract  MajorHeading  MinorHeading ContractMappingVO ErrorCodeList
							errorDetailListAtMappingLevel1 = mappingWebServiceVO
									.getErrorCodesList();

							if (errorDetailListAtMappingLevel1 != null
									&& errorDetailListAtMappingLevel1
									//Added here
											.size() > 0
									&& !(errorDetailListAtMappingLevel1
											.get(0)).toString()
											.trim().equals("")) {
								for (ErrorDetailWebServiceVO errorDetailWebServiceVO1 : errorDetailListAtMappingLevel1) {
									Eb03AssociationWebServiceVO finalEb03Assn = new Eb03AssociationWebServiceVO();
									tempArray = new String[11];
									if(null != mappingWebServiceVO.getEb03()) {
										for(Eb03AssociationWebServiceVO eb03Assn : mappingWebServiceVO.getEb03().getEb03Association()) {
											if(null != errorDetailWebServiceVO1 && null != errorDetailWebServiceVO1.getAssociatedEb03() &&
													null != eb03Assn && null != eb03Assn.getEb03()) {
												if(errorDetailWebServiceVO1.getAssociatedEb03().equalsIgnoreCase(eb03Assn.getEb03().getValue())) {
													finalEb03Assn = eb03Assn;
												}
											}
										}
									}
									tdValues[2] = (minorHeadingsWebServiceVO != null
											&& minorHeadingsWebServiceVO
													.getRuleMapping() != null
											&& minorHeadingsWebServiceVO.getRuleMapping().getRule()!= null
											&& minorHeadingsWebServiceVO.getRuleMapping().getRule().getHeaderRuleId()!= null) ?
													minorHeadingsWebServiceVO.getRuleMapping().getRule().getHeaderRuleId(): ""; // 16332 Production fix
									//System.out.println("mappingWebServiceVO.getSpsId().getSpsId()"+mappingWebServiceVO.getSpsId().getSpsId());
									tdValues[3] = mappingWebServiceVO
											.getSpsId().getSpsId();
									tdValues[4] = mappingWebServiceVO
											.getSpsId()
											.getLinePVA();
									tdValues[5] = mappingWebServiceVO
											.getSpsId()
											.getLineDataType();
									tdValues[6] = mappingWebServiceVO
											.getSpsId()
											.getLineValue();
									/*tdValues[7] = mappingWebServiceVO
											.getMessage();
									tdValues[8] = mappingWebServiceVO
											.getNoteTypeCode()
											.getHippaCodeSelectedValuesForEwpd() != null
											&& mappingWebServiceVO
													.getNoteTypeCode()
													.getHippaCodeSelectedValuesForEwpd()
													.size() > 0 ? ((HippaCodeValueWebServiceVO) mappingWebServiceVO
											.getNoteTypeCode()
											.getHippaCodeSelectedValuesForEwpd()
											.get(0)).getValue()
											: "";*/
											
									if(null != finalEb03Assn) {
										tdValues[7] = finalEb03Assn.getMessage();
										if(null != finalEb03Assn.getNoteType()) {
											tdValues[8] = finalEb03Assn.getNoteType().getValue();
										}else {
											tdValues[8] = "";
										}
									}else {
										tdValues[7] = "";
										tdValues[8] = "";
									}

									tempArray[2] = tdValues[2];
									tempArray[3] = tdValues[3];
									tempArray[4] = tdValues[4];
									tempArray[5] = tdValues[5];
									tempArray[6] = tdValues[6];
									tempArray[7] = tdValues[7];
									tempArray[8] = tdValues[8];
									tempArray[0] = tdValues[0];
									tempArray[1] = tdValues[1];
									tdValues[9] = errorDetailWebServiceVO1
											.getErrorCode();
									tdValues[10] = errorDetailWebServiceVO1
											.getErrorDesc();
									tempArray[9] = tdValues[9];
									tempArray[10] = tdValues[10];
									tdValueList.add(tempArray);

									tdValues[9] = null;
									tdValues[10] = null;
								}
							}
						}

					}
				}
			}

		}
		Iterator itrTdValLst = tdValueList.iterator();
		while(itrTdValLst.hasNext()){
			EbxWebSerDisplayVO ebxWebSerDisplayVO = new EbxWebSerDisplayVO();
			String strArray[] = (String[])itrTdValLst.next();
			ebxWebSerDisplayVO.setBenefitComponent(strArray[0]);
			ebxWebSerDisplayVO.setBenefit(strArray[1]);
			ebxWebSerDisplayVO.setHeaderRule(strArray[2]);
			ebxWebSerDisplayVO.setSPSId(strArray[3]);
			ebxWebSerDisplayVO.setPVA(strArray[4]);
			ebxWebSerDisplayVO.setFormat(strArray[5]);
			ebxWebSerDisplayVO.setValueCoded(strArray[6]);
			ebxWebSerDisplayVO.setMessageText(strArray[7]);
			ebxWebSerDisplayVO.setNoteTypeCode(strArray[8]);
			ebxWebSerDisplayVO.setError(strArray[9]);
			ebxWebSerDisplayVO.setErrorDescription(strArray[10]);
			ebxWebSerDisplayList.add(ebxWebSerDisplayVO);
		}
		return ebxWebSerDisplayList;
	}
	
	public static List<EbxWebSerRuleIdDisplayVO> ruleIdPopupDisplay(List<ContractWebServiceVO> contractWSErrorList,String ruleId){
		
		List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst = null;
		ContractWebServiceVO.MajorHeadings.Entry pairs1 = null;
		MajorHeadingsWebServiceVO.MinorHeadings.Entry pairs2 = null;
		MinorHeadingsWebServiceVO.Mappings.Entry pairs3 = null;
		MappingWebServiceVO matchedMappingWebServiceVO = null;
		String isMatchedRuleId = null;
		boolean isLoopTerminate = false;
		Object obj = null;
		
		for(int i=0; i < contractWSErrorList.size(); i++){
				ContractWebServiceVO contractWebServiceVO = contractWSErrorList.get(i);
				Iterator it1 = contractWSErrorList.get(i).getMajorHeadings().getEntry().iterator();
				while (it1.hasNext()){
					obj = it1.next();
					pairs1 = (ContractWebServiceVO.MajorHeadings.Entry)obj;
					MajorHeadingsWebServiceVO majorHeadingsWebServiceVO = (MajorHeadingsWebServiceVO)pairs1.getValue();
					Iterator it2 = pairs1.getValue().getMinorHeadings().getEntry().iterator();
					while(it2.hasNext()){
						obj = it2.next();
						pairs2 = (MajorHeadingsWebServiceVO.MinorHeadings.Entry)obj;
						MinorHeadingsWebServiceVO minorHeadingsWebServiceVO = (MinorHeadingsWebServiceVO)pairs2.getValue();
						
						if(ruleId.equalsIgnoreCase(minorHeadingsWebServiceVO.getRuleMapping().getRule().getHeaderRuleId())){
							//System.out.println("matches");
							Logger.logInfo("matches");
							matchedMappingWebServiceVO = minorHeadingsWebServiceVO.getRuleMapping();
							isLoopTerminate = true;
							break;	
						}
							if(isLoopTerminate){
								break;
							}
					}
					if(isLoopTerminate){
						break;
					}
				}
				if(isLoopTerminate){
					break;
				}
		}
		if(null != matchedMappingWebServiceVO){
			ebxWebSerRuleIdDisplayLst = new ArrayList<EbxWebSerRuleIdDisplayVO>();
			EbxWebSerRuleIdDisplayVO ebxWebSerRuleIdDisplayVO;
			//ebxWebSerRuleIdDisplayVO.setEbB03(getDispalyValues(matchedMappingWebServiceVO.getEb03()));
			if(null != matchedMappingWebServiceVO.getEb03() && null != matchedMappingWebServiceVO.getEb03().getEb03Association()
					&& !matchedMappingWebServiceVO.getEb03().getEb03Association().isEmpty()) {
				for(Eb03AssociationWebServiceVO eb03Assn : matchedMappingWebServiceVO.getEb03().getEb03Association()) {
					ebxWebSerRuleIdDisplayVO = new EbxWebSerRuleIdDisplayVO();
					if(null != eb03Assn.getEb03()) {
						ebxWebSerRuleIdDisplayVO.setEbB03(eb03Assn.getEb03().getValue());
					}else {
						ebxWebSerRuleIdDisplayVO.setEbB03("");
					}
					if(null != eb03Assn.getIii02List() && !eb03Assn.getIii02List().isEmpty()) {
						ebxWebSerRuleIdDisplayVO.setIii02(convertHippaCodeValueToString(eb03Assn.getIii02List()));
					}else {
						ebxWebSerRuleIdDisplayVO.setIii02("");
					}
					ebxWebSerRuleIdDisplayVO.setUmRule(getDispalyValues(matchedMappingWebServiceVO.getUtilizationMgmntRule()));
					ebxWebSerRuleIdDisplayLst.add(ebxWebSerRuleIdDisplayVO);
				}
			}else {
				ebxWebSerRuleIdDisplayVO = new EbxWebSerRuleIdDisplayVO();
				ebxWebSerRuleIdDisplayVO.setEbB03("");
				ebxWebSerRuleIdDisplayVO.setIii02("");
				ebxWebSerRuleIdDisplayVO.setUmRule(getDispalyValues(matchedMappingWebServiceVO.getUtilizationMgmntRule()));
				ebxWebSerRuleIdDisplayLst.add(ebxWebSerRuleIdDisplayVO);
			}
		}
		return ebxWebSerRuleIdDisplayLst;
	}
	
	private static String convertHippaCodeValueToString(List<HippaCodeValueWebServiceVO> iii02List) {
		StringBuilder hippaCodeValue = new StringBuilder();
		String finalString = "";
		if(null != iii02List && iii02List.size() > 0) {
			for(HippaCodeValueWebServiceVO hippaValue : iii02List) {
				hippaCodeValue.append(hippaValue.getValue()).append(",");
			}
			finalString = hippaCodeValue.substring(0, hippaCodeValue.length()-1); 
		}
		return finalString;
	}
	
	public static List<EbxWebSerSPSIdDisplayVO> spsIdPopupDisplay(List<ContractWebServiceVO> contractWSErrorList,String spsId){
		
		List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst = null;
		ContractWebServiceVO.MajorHeadings.Entry pairs1 = null;
		MajorHeadingsWebServiceVO.MinorHeadings.Entry pairs2 = null;
		MinorHeadingsWebServiceVO.Mappings.Entry pairs3 = null;
		MappingWebServiceVO matchedMappingWebServiceVO = null;
		String isMatchedSpsId = null;
		boolean isLoopTerminate = false;
		boolean inValidRuleID = false;
		Object obj = null;
		
		for(int i=0; i < contractWSErrorList.size(); i++){
				ContractWebServiceVO contractWebServiceVO = contractWSErrorList.get(i);
				Iterator it1 = contractWSErrorList.get(i).getMajorHeadings().getEntry().iterator();
				while (it1.hasNext()){
					obj = it1.next();
					pairs1 = (ContractWebServiceVO.MajorHeadings.Entry)obj;
					MajorHeadingsWebServiceVO majorHeadingsWebServiceVO = (MajorHeadingsWebServiceVO)pairs1.getValue();
					Iterator it2 = pairs1.getValue().getMinorHeadings().getEntry().iterator();
					while(it2.hasNext()){
						obj = it2.next();
						pairs2 = (MajorHeadingsWebServiceVO.MinorHeadings.Entry)obj;
						MinorHeadingsWebServiceVO minorHeadingsWebServiceVO = (MinorHeadingsWebServiceVO)pairs2.getValue();
						Iterator it3 = minorHeadingsWebServiceVO.getMappings().getEntry().iterator();
							while(it3.hasNext()){
								obj = it3.next();
								pairs3 = (MinorHeadingsWebServiceVO.Mappings.Entry)obj;
								ContractMappingWebServiceVO mappingWebServiceVO = (ContractMappingWebServiceVO)pairs3.getValue();
									isMatchedSpsId = mappingWebServiceVO.getSpsId().getSpsId();
									if(isMatchedSpsId.equals(spsId)){
										matchedMappingWebServiceVO = (ContractMappingWebServiceVO)pairs3.getValue();
										isLoopTerminate = true;
										break;	
									}
							}
							if(isLoopTerminate){
								break;
							}
					}
					if(isLoopTerminate){
						break;
					}else{
						inValidRuleID = true;
					}
				}
				if(isLoopTerminate){
					break;
				}
		}
		EbxWebSerSPSIdDisplayVO ebxWebSerSPSIdDisplayVO = new EbxWebSerSPSIdDisplayVO();
		if(null != matchedMappingWebServiceVO){
			ebxWebSerSPSIdDisplayLst = new ArrayList<EbxWebSerSPSIdDisplayVO>();
			ebxWebSerSPSIdDisplayVO.setEbB01(getDispalyValues(matchedMappingWebServiceVO.getEb01()));
			ebxWebSerSPSIdDisplayVO.setEbB02(getDispalyValues(matchedMappingWebServiceVO.getEb02()));
			ebxWebSerSPSIdDisplayVO.setEbB06(getDispalyValues(matchedMappingWebServiceVO.getEb06()));
			ebxWebSerSPSIdDisplayVO.setEbB09(getDispalyValues(matchedMappingWebServiceVO.getEb09()));
			ebxWebSerSPSIdDisplayVO.setHsD01(getDispalyValues(matchedMappingWebServiceVO.getHsd01()));
			ebxWebSerSPSIdDisplayVO.setHsD02(getDispalyValues(matchedMappingWebServiceVO.getHsd02()));
			ebxWebSerSPSIdDisplayVO.setHsD03(getDispalyValues(matchedMappingWebServiceVO.getHsd03()));
			ebxWebSerSPSIdDisplayVO.setHsD04(getDispalyValues(matchedMappingWebServiceVO.getHsd04()));
			ebxWebSerSPSIdDisplayVO.setHsD05(getDispalyValues(matchedMappingWebServiceVO.getHsd05()));
			ebxWebSerSPSIdDisplayVO.setHsD06(getDispalyValues(matchedMappingWebServiceVO.getHsd06()));
			ebxWebSerSPSIdDisplayVO.setHsD07(getDispalyValues(matchedMappingWebServiceVO.getHsd07()));
			ebxWebSerSPSIdDisplayVO.setHsD08(getDispalyValues(matchedMappingWebServiceVO.getHsd08()));
			ebxWebSerSPSIdDisplayLst.add(ebxWebSerSPSIdDisplayVO);
		}		
		return ebxWebSerSPSIdDisplayLst;
	}
	
	
	public static String getDispalyValues(HippaSegmentWebServiceVO hippaSegment) {
		String hipaaValue = "";
		if (null != hippaSegment
				&& null != hippaSegment.getHippaCodeSelectedValuesForLgIsg()
				&& !hippaSegment.getHippaCodeSelectedValuesForLgIsg().isEmpty()) {
			for (int i = 0; i < hippaSegment
					.getHippaCodeSelectedValuesForLgIsg().size(); i++) {
				if(hippaSegment.getHippaCodeSelectedValuesForLgIsg().get(i) != null){
						hipaaValue += hippaSegment.getHippaCodeSelectedValuesForLgIsg()
						.get(i)
						+ ((i + 1) < hippaSegment
								.getHippaCodeSelectedValuesForLgIsg().size() ? ", "
								: "");
						
				}
				
			}
		} else if (null != hippaSegment
				&& null != hippaSegment.getHippaCodeSelectedValuesForEwpd()
				&& !hippaSegment.getHippaCodeSelectedValuesForEwpd().isEmpty()) {
			for (int i = 0; i < hippaSegment
					.getHippaCodeSelectedValuesForEwpd().size(); i++) {
				if( hippaSegment.getHippaCodeSelectedValuesForEwpd().get(i)!=null){
					if(hippaSegment.getHippaCodeSelectedValuesForEwpd().get(i).getValue()!=null){
						hipaaValue += hippaSegment.getHippaCodeSelectedValuesForEwpd()				
						.get(i).getValue()
						+ ((i + 1) < hippaSegment
								.getHippaCodeSelectedValuesForEwpd().size() ? ", "
								: "");
					}
			}
			}
		}
		
		return hipaaValue;
	}
	
	public static void persistAssociatedDomains(DomainDetail domainDetails,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		List domainList = domainDetails.getDomainList();
		String entityType = domainDetails.getEntityType();
		String entityName = domainDetails.getEntityName();
		int entityParentId = domainDetails.getEntityParentId();
		String lastUpdatedUser = domainDetails.getLastUpdatedUser();
		// Enhancement
		// int entityId = domainDetails.getEntityId();
		// End Enhancement

		Date lastUpdatedTime = domainDetails.getLastUpdatedTimeStamp();

		if (entityType == null || entityName == null || entityParentId == 0
				|| lastUpdatedUser == null || lastUpdatedTime == null) {

			Logger.logError("DomainUtil - Parameters are not valid......");
			throw new IllegalArgumentException();
		}

		Iterator iter;
		EntityDomainInfo entityDomainInfo = new EntityDomainInfo();
		Domain domain = null;

		DomainAdapterManager adapter = new DomainAdapterManager();
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

		// Removing the existing domain associations
		entityDomainInfo.setEntityType(entityType);

		entityDomainInfo.setEntitySystemId(entityParentId);

		adapter
				.remove(entityDomainInfo, adapterServicesAccess,
						lastUpdatedUser);

		for (iter = domainList.iterator(); iter.hasNext();) {
			domain = (Domain) iter.next();
			if (null == adapter.retrieve(domain, adapterServicesAccess)) {
				domain.setDomainSysId(sequenceAdapterManager
						.getNextDomainIdSequence(adapterServicesAccess));
				adapter.create(domain, adapterServicesAccess, lastUpdatedUser);
			}
			entityDomainInfo.setDomainId(domain.getDomainSysId());
			entityDomainInfo.setEntityName(entityName);
			entityDomainInfo.setEntityType(entityType);

			entityDomainInfo.setEntitySystemId(entityParentId);

			entityDomainInfo.setCreatedUser(lastUpdatedUser);
			entityDomainInfo.setCreatedTimeStamp(lastUpdatedTime);
			entityDomainInfo.setLastUpdatedUser(lastUpdatedUser);
			entityDomainInfo.setLastUpdatedTimeStamp(lastUpdatedTime);
			adapter.create(entityDomainInfo, adapterServicesAccess,
					lastUpdatedUser);
		}
	}

	public static void removeAssociatedDomains(String entityType,
			int entityParentId, String userId,
			AdapterServicesAccess adapterService) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		EntityDomainInfo entityDomainInfo = new EntityDomainInfo();
		entityDomainInfo.setEntityType(entityType);
		entityDomainInfo.setEntitySystemId(entityParentId);
		adapter.remove(entityDomainInfo, adapterService, userId);
	}

	public static void removeAssociatedDomains(DomainDetail businessDomain,
			AdapterServicesAccess adapterService) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		EntityDomainInfo entityDomainInfo = new EntityDomainInfo();
		entityDomainInfo.setEntityType(businessDomain.getEntityType());
		entityDomainInfo.setEntitySystemId(businessDomain.getEntityParentId());
		adapter.remove(entityDomainInfo, adapterService, businessDomain
				.getLastUpdatedUser());
	}

	public static List retrieveAssociatedDomains(String entityType,
			int entityParentId) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		List domainList = adapter.getAssociatedDomains(entityType,
				entityParentId);
		Collections.sort(domainList);
		return domainList;
	}

	public static List getLineOfBusiness(String entityType, int entityParentId)
			throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		return adapter.searchLineOfBusiness(entityType, entityParentId, 1925);
	}

	public static List getBusinessEntity(String entityType, int entityParentId)
			throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		return adapter.searchBusinessEntity(entityType, entityParentId, 1932);
	}

	public static List getBusinessGroup(String entityType, int entityParentId)
			throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		return adapter.searchBusinessGroup(entityType, entityParentId, 1933);
	}

	public static List getMarketBusinessUnit(String entityType,
			int entityParentId) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		return adapter.searchMarketBusinessUnit(entityType, entityParentId,
				3470);
	}

	public static DomainDetail retrieveDomainDetailInfo(String entityType,
			int entityParentId) throws SevereException {

		DomainDetail domainDetail = new DomainDetail();
		DomainAdapterManager adapter = new DomainAdapterManager();
		List lineOfBusinessList = adapter.searchLineOfBusiness(entityType,
				entityParentId, 1925);
		List businessEntityList = adapter.searchBusinessEntity(entityType,
				entityParentId, 1932);
		List businessGroupList = adapter.searchBusinessGroup(entityType,
				entityParentId, 1933);
		List marketBusinessUnitList = adapter.searchMarketBusinessUnit(
				entityType, entityParentId, 3470);

		domainDetail.setLineOfBusiness(lineOfBusinessList);
		domainDetail.setBusinessEntity(businessEntityList);
		domainDetail.setBusinessGroup(businessGroupList);
		domainDetail.setMarketBusinessUnit(marketBusinessUnitList);
		domainDetail.setDomainList(retrieveAssociatedDomains(entityType,
				entityParentId));
		return domainDetail;
	}

	public static List getDomainsForMigration(int entityParentId)
			throws SevereException {

		/*
		 * DomainDetail domainDetail = new DomainDetail(); DomainAdapterManager
		 * adapter = new DomainAdapterManager(); List lineOfBusinessList =
		 * adapter.searchLineOfBusiness("migration", entityParentId,1925); List
		 * businessEntityList = adapter.searchBusinessEntity("migration",
		 * entityParentId,1932); List businessGroupList =
		 * adapter.searchBusinessGroup("migration", entityParentId,1933);
		 * 
		 * domainDetail.setLineOfBusiness(lineOfBusinessList);
		 * domainDetail.setBusinessEntity(businessEntityList);
		 * domainDetail.setBusinessGroup(businessGroupList);
		 * domainDetail.setDomainList
		 * (retrieveAssociatedDomains("migration",entityParentId)); return
		 * domainDetail;
		 */
		DomainAdapterManager adapter = new DomainAdapterManager();
		List domainList = adapter.getDomainsForMigration(entityParentId);
		Collections.sort(domainList);
		return domainList;
	}

	public static boolean isEntityDuplicate(String entityType,
			String entityName, List domainList, int entityParentId)
			throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		List lineOfBusiness = new ArrayList();
		List businessEntity = new ArrayList();
		List businessGroup = new ArrayList();
		List marketBusinessUnit = new ArrayList();
		Domain domain = null;
		for (Iterator iter = domainList.iterator(); iter.hasNext();) {
			domain = (Domain) iter.next();
			lineOfBusiness.add(domain.getLineOfBusiness());
			businessEntity.add(domain.getBusinessEntity());
			businessGroup.add(domain.getBusinessGroup());
			marketBusinessUnit.add(domain.getMarketBusinessUnit());
		}
		List resultList = adapter.searchForDuplicateDomain(entityType,
				entityName, lineOfBusiness, businessEntity, businessGroup,
				marketBusinessUnit, entityParentId);
		if (resultList != null && resultList.size() > 0)
			return true;
		return false;
	}

	/**
	 * @deprecated
	 */
	public static void persistBusinessDomain(DomainDetail domainDetails,
			AdapterServicesAccess adapterService) throws SevereException {
		List lineOfbusinessList = null;
		List businessEntityList = null;
		List businessGroupList = null;

		lineOfbusinessList = domainDetails.getLineOfBusiness();
		businessEntityList = domainDetails.getBusinessEntity();
		businessGroupList = domainDetails.getBusinessGroup();
		String entityType = domainDetails.getEntityType();
		String entityName = domainDetails.getEntityName();
		int entitySystemId = domainDetails.getEntitySystemId();
		String createdUser = domainDetails.getCreatedUser();
		Date createdTime = domainDetails.getCreatedTimeStamp();
		String lastUpdatedUser = domainDetails.getLastUpdatedUser();
		Date lastUpdatedTime = domainDetails.getLastUpdatedTimeStamp();

		Iterator iter1, iter2, iter3;
		String lineOfBusinessId, businessEntityId, businessGroupId;
		DomainInfo domainInfo = new DomainInfo();
		EntityDomainInfo entityDomainInfo = new EntityDomainInfo();

		DomainAdapterManager adapter = new DomainAdapterManager();
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

		entityDomainInfo.setEntityName(entityName);
		entityDomainInfo.setEntityType(entityType);
		entityDomainInfo.setEntitySystemId(entitySystemId);
		adapter.remove(entityDomainInfo, adapterService, lastUpdatedUser);

		for (iter1 = lineOfbusinessList.iterator(); iter1.hasNext();) {
			lineOfBusinessId = (String) iter1.next();
			for (iter2 = businessEntityList.iterator(); iter2.hasNext();) {
				businessEntityId = (String) iter2.next();
				for (iter3 = businessGroupList.iterator(); iter3.hasNext();) {
					businessGroupId = (String) iter3.next();

					domainInfo.setLineOfBusinesId(lineOfBusinessId);
					domainInfo.setBusinessEntityId(businessEntityId);
					domainInfo.setBusinessGroupId(businessGroupId);
					if (null == adapter.retrieve(domainInfo, adapterService,
							lastUpdatedUser)) {
						domainInfo.setDomainId(sequenceAdapterManager
								.getNextDomainIdSequence(adapterService));
						adapter.create(domainInfo, adapterService);
					}
					entityDomainInfo.setDomainId(domainInfo.getDomainId());
					entityDomainInfo.setEntityName(entityName);
					entityDomainInfo.setEntityType(entityType);
					entityDomainInfo.setEntitySystemId(entitySystemId);
					entityDomainInfo.setCreatedUser(createdUser);
					entityDomainInfo.setCreatedTimeStamp(createdTime);
					entityDomainInfo.setLastUpdatedUser(lastUpdatedUser);
					entityDomainInfo.setLastUpdatedTimeStamp(lastUpdatedTime);
					adapter.create(entityDomainInfo, adapterService,
							lastUpdatedUser);
				}
			}
		}
	}

	private void validateBusinessDoamin(DomainDetail domainDetails) {
		List lineOfbusinessList = domainDetails.getLineOfBusiness();
		List businessEntityList = domainDetails.getBusinessEntity();
		List businessGroupList = domainDetails.getBusinessGroup();
		String entityType = domainDetails.getEntityType();

		if (lineOfbusinessList == null || businessEntityList == null
				|| businessGroupList == null)
			throw new IllegalArgumentException(
					"One of the domain attribute is null");

		Iterator iter = null;
		for (iter = lineOfbusinessList.iterator(); iter.hasNext();) {
			if (!(iter.next() instanceof String)) {
				throw new IllegalArgumentException(
						"Line of business values should be String");
			}
		}

		for (iter = businessEntityList.iterator(); iter.hasNext();) {
			if (!(iter.next() instanceof String)) {
				throw new IllegalArgumentException(
						"Business Entity values should be String");
			}
		}

		for (iter = businessGroupList.iterator(); iter.hasNext();) {
			if (!(iter.next() instanceof String)) {
				throw new IllegalArgumentException(
						"Business Group values should be String");
			}
		}

	}

	/**
	 * @deprecated
	 */
	public static void removeBusinessDomain(DomainDetail businessDomain,
			AdapterServicesAccess adapterService) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		EntityDomainInfo entityDomainInfo = new EntityDomainInfo();
		entityDomainInfo.setEntityName(businessDomain.getEntityName());
		entityDomainInfo.setEntityType(businessDomain.getEntityType());
		entityDomainInfo.setEntitySystemId(businessDomain.getEntitySystemId());
		adapter.remove(entityDomainInfo, adapterService, businessDomain
				.getLastUpdatedUser());
	}

	public static DomainDetail retrieveBusinessDomain(
			DomainDetail businessDomain) throws SevereException {
		String entityType = businessDomain.getEntityType();
		String entityName = businessDomain.getEntityName();
		int entitySystemId = businessDomain.getEntitySystemId();

		DomainAdapterManager adapter = new DomainAdapterManager();
		List lineOfBusinessList = adapter.searchLineOfBusiness(entityType,
				entitySystemId, 1925);
		List businessEntityList = adapter.searchBusinessEntity(entityType,
				entitySystemId, 1932);
		List businessGroupList = adapter.searchBusinessGroup(entityType,
				entitySystemId, 1933);
		List marketBusinessUnitList = adapter.searchMarketBusinessUnit(
				entityType, entitySystemId, 3470);

		businessDomain.setLineOfBusiness(lineOfBusinessList);
		businessDomain.setBusinessEntity(businessEntityList);
		businessDomain.setBusinessGroup(businessGroupList);
		businessDomain.setMarketBusinessUnit(marketBusinessUnitList);
		return businessDomain;
	}

	/**
	 * @deprecated
	 */
	public static boolean isEntityDuplicate(String entityType,
			String entityName, List lineOfBusiness, List businessEntity,
			List businessGroup, List marketBusinessUnit, int entityId)
			throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		List resultList = adapter.searchForDuplicateDomain(entityType,
				entityName, lineOfBusiness, businessEntity, businessGroup,
				marketBusinessUnit, entityId);
		if (resultList != null && resultList.size() > 0)
			return true;
		return false;
	}

	/**
	 * @deprecated
	 */
	public static boolean isEntityDuplicate(DomainDetail businessDomain)
			throws SevereException {
		return isEntityDuplicate(businessDomain.getEntityType(), businessDomain
				.getEntityName(), businessDomain.getLineOfBusiness(),
				businessDomain.getBusinessEntity(), businessDomain
						.getBusinessGroup(), businessDomain
						.getMarketBusinessUnit(), businessDomain
						.getEntitySystemId());
	}

	// Changes starts For QuestionnaireDomainInfo
	public static void persistQuestionnaireAssociatedDomains(
			DomainDetail domainDetails,
			AdapterServicesAccess adapterServicesAccess)
			throws SevereException, AdapterException {
		List domainList = domainDetails.getDomainList();
		int entityParentId = domainDetails.getEntityParentId();
		String lastUpdatedUser = domainDetails.getLastUpdatedUser();

		Date lastUpdatedTime = domainDetails.getLastUpdatedTimeStamp();

		if (entityParentId == 0 || lastUpdatedUser == null
				|| lastUpdatedTime == null) {

			Logger.logError("DomainUtil - Parameters are not valid......");
			throw new IllegalArgumentException();
		}

		Iterator iter;
		QuestionnaireDomainInfo questionnaireDomainInfo = new QuestionnaireDomainInfo();
		Domain domain = null;

		DomainAdapterManager adapter = new DomainAdapterManager();
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

		questionnaireDomainInfo.setQuestionnaireHrchySysId(entityParentId);

		adapter.removeQuestionnaireDomain(questionnaireDomainInfo,
				adapterServicesAccess, lastUpdatedUser);

		for (iter = domainList.iterator(); iter.hasNext();) {
			domain = (Domain) iter.next();
			if (null == adapter.retrieve(domain, adapterServicesAccess)) {
				domain.setDomainSysId(sequenceAdapterManager
						.getNextDomainIdSequence(adapterServicesAccess));
				adapter.create(domain, adapterServicesAccess, lastUpdatedUser);
			}
			questionnaireDomainInfo.setDomainId(domain.getDomainSysId());

			questionnaireDomainInfo.setQuestionnaireHrchySysId(entityParentId);

			questionnaireDomainInfo.setCreatedUser(lastUpdatedUser);
			questionnaireDomainInfo.setCreatedTimeStamp(lastUpdatedTime);
			questionnaireDomainInfo.setLastUpdatedUser(lastUpdatedUser);
			questionnaireDomainInfo.setLastUpdatedTimeStamp(lastUpdatedTime);
			adapter.createQuestionnaireDomain(questionnaireDomainInfo,
					adapterServicesAccess, lastUpdatedUser);
		}
	}

	public static DomainDetail retrieveQuestionnaireDomainDetailInfo(
			int entityParentId) throws SevereException {

		DomainDetail domainDetail = new DomainDetail();
		DomainAdapterManager adapter = new DomainAdapterManager();
		List lineOfBusinessList = adapter.searchQstnrLineOfBusiness(
				entityParentId, 1925);
		List businessEntityList = adapter.searchQstnrBusinessEntity(
				entityParentId, 1932);
		List businessGroupList = adapter.searchQstnrBusinessGroup(
				entityParentId, 1933);
		List marketBusinessUnit = adapter.searchQstnrMarketBusinessUnit(
				entityParentId, 3470);
		List functionalDomainList = adapter.getFunctionalDomain(entityParentId);

		/*
		 * if(null != functionalDomainList){ Iterator functionalDomainIterator =
		 * functionalDomainList.iterator(); List domainDescList = new
		 * ArrayList(); String domainDesc = null;
		 * while(functionalDomainIterator.hasNext()){ FunctionalDomainBO
		 * domainBO = (FunctionalDomainBO)functionalDomainIterator.next();
		 * domainDesc = domainBO.getFunctionalDomainDesc();
		 * domainDescList.add(domainDesc); }
		 */
		domainDetail.setFunctionalDomainList(functionalDomainList);

		domainDetail.setLineOfBusiness(lineOfBusinessList);
		domainDetail.setBusinessEntity(businessEntityList);
		domainDetail.setBusinessGroup(businessGroupList);
		domainDetail.setMarketBusinessUnit(marketBusinessUnit);
		domainDetail
				.setDomainList(retrieveQuestionnaireAssociatedDomains(entityParentId));

		return domainDetail;
	}

	public static List retrieveQuestionnaireAssociatedDomains(int entityParentId)
			throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		List domainList = adapter
				.getQuestionnairAssociatedDomains(entityParentId);
		Collections.sort(domainList);
		return domainList;
	}

	public static boolean isQuestionnaireEntityDuplicate(List domainList,
			int questionnaireHrchySysId) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		List lineOfBusiness = new ArrayList();
		List businessEntity = new ArrayList();
		List businessGroup = new ArrayList();
		List marketBusinessUnit = new ArrayList();
		Domain domain = null;
		for (Iterator iter = domainList.iterator(); iter.hasNext();) {
			domain = (Domain) iter.next();
			lineOfBusiness.add(domain.getLineOfBusiness());
			businessEntity.add(domain.getBusinessEntity());
			businessGroup.add(domain.getBusinessGroup());
			marketBusinessUnit.add(domain.getMarketBusinessUnit());
		}
		List resultList = adapter.searchForQuestionnaireDuplicateDomain(
				lineOfBusiness, businessEntity, businessGroup,
				marketBusinessUnit, questionnaireHrchySysId);
		if (resultList != null && resultList.size() > 0)
			return true;
		return false;
	}

	// changes ends here

	public static Domain retrieveDomain(Domain domain) throws SevereException {
		DomainAdapterManager adapter = new DomainAdapterManager();
		domain = adapter.retrieve(domain, adapter.getAdapterService());
		return domain;
	}
}
