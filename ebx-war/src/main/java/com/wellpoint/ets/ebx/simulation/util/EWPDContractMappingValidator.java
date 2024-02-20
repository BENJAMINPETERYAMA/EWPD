/*
 * <EWPDContractMappingValidator.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */

package com.wellpoint.ets.ebx.simulation.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.owasp.esapi.ESAPI;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.referencedata.util.ReferenceDataUtil;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionDetailVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.ErrorDetailVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;

/**
 * @author UST-GLOBAL
 * 
 *         Error Validator class for eWPD system. This class will scan the
 *         contract for error codes and creates a contract object which will
 *         contain error code list for various levels
 * 
 */
public class EWPDContractMappingValidator extends ContractMappingValidator {

	private boolean isPVA_E012_IN_UC = false;

	private boolean isPVA_E012_OUT_UC = false;

	private boolean isPVA_E012_INOUT_UC = false;

	private boolean isE008HasEB03_88 = false;

	private boolean prescDrugPlanFlag = false;

	//Variables removed as part of BXNI December Release-- Remove E011
	//private boolean isPVA_E011_INOUT_SP = false;

	//private boolean isPVA_E011_IN_SP = false;

	//private boolean isPVA_E011_OUT_SP = false;

	private List spsWithEB03SPList = new ArrayList();

	private List spsWithEB03UCList = new ArrayList();

	private List accumIndList = new ArrayList();
	
	boolean errorflagForE038 = false;
	
	boolean errorflagForE039 = false;
	
	Map<String,ContractMappingVO> nonCoveredMap = new HashMap<String,ContractMappingVO>();
	List<String> coveredList = new ArrayList<String>();

	public List getAccumIndList() {
		return accumIndList;
	}

	public void setAccumIndList(List accumIndList) {
		this.accumIndList = accumIndList;
	}

	public static final Logger log = Logger
			.getLogger(EWPDContractMappingValidator.class);

	/**
	 * Method for validating contract for errors.
	 * 
	 * @param contract
	 * @param contractList
	 * @return List
	 */
	public List validate(List contractVOList) {
		ContractMappingValidator contractMappingValidator = new ContractMappingValidator();
		Iterator contractListIter = contractVOList.iterator();
		
		boolean eB03CodedAsVisionFlag = false;
		boolean visionCoveredForE038 = false;
		boolean eB03CodedAsDentalFlag = false;
		boolean dentalCoveredForE039 = false;
		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has a benefit with EB03 = 88 
		//and same benefit has benefit Covered Question answered as N in the contract
		 boolean isBenefitNonCovered = false;
		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has a benefit with EB03 = 88 
		//and same benefit does not have benefit Covered Question answered as N in the contract
		 boolean isBenefitCovered = false;
		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has any of the benefits specified for reporting E008
		 boolean E008BenefitsFlag = false;
		// flag will be set to true if the contract has an Eb03 = 88 header rule
		 boolean isEB03_88 = false;		
		List<String> eb03ValuesInContract;
		//minorHeadingE008Set is used to avoid duplication of Error E043 occurrence at Benefit level
		Set<String> minorHeadingE008Set = new HashSet<String>();
		
		while (contractListIter.hasNext()) {
			ContractVO contract = (ContractVO) contractListIter.next();
			eb03ValuesInContract = new ArrayList<String>();
			// Setting the exclusion details
			errorExclusionDetailVO = new ErrorExclusionDetailVO();
			contractMappingValidator.processExclusionForErrorValidation(
					contract, errorExclusionDetailVO);
			contract.setErrorExclusionDetailVO(errorExclusionDetailVO);
			// get MajorHeadings Map
			HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
			// verifying the business entity for contract (error 021)
			// MSUP
			if (null != contract.getLineOfBusiness()) {
				if (DomainConstants.LINEOFBUSINESS_MSUP.equals(contract
						.getLineOfBusiness())) {
					errorExclusionDetailVO
							.setE021ExclusionValidationEnableFlag(false);
				}
			}

			// setting error exclusion flag for EWPD

			Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
			
			if (majorHeadingsMap.size() > 0) {
				while (iteratorMajor.hasNext()) {
					String keyMajor = (String) iteratorMajor.next();
					MajorHeadingsVO majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
							.get(keyMajor);
					/** for E008 **/
					if (getEWPDBenefitComponentsForE008().contains(majorHeadingObj.getDescriptionText()) 
							|| DomainConstants.BC_EWPD_E008_OUTPATIENT_HOSP_BNFTS.equals(majorHeadingObj.getDescriptionText())
							|| DomainConstants.BC_EWPD_E008_SEXUAL_DISORDERS.equals(majorHeadingObj.getDescriptionText())
					) {
						E008BenefitsFlag = true;
					}
					// get MinorHeadings Map
					HashMap minorHeadingsMap = (HashMap) majorHeadingObj
							.getMinorHeadings();
					Iterator iteratorMinor = minorHeadingsMap.keySet()
							.iterator();
					while (iteratorMinor.hasNext()) {
						String keyMinor = (String) iteratorMinor.next();
						//boolean e011SPFlag = false;
						boolean e012UCFlag = false;
						boolean eb03ExclusionForE006Flag = false;
						MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
								.get(keyMinor);
						// Setting benefitCoveredFlag into MinorHeading object
						minorHeadingObj = setBenefitCoveredFlag(minorHeadingObj);

						// Checking the exclusion for the header rule associated
						// with the minor heading.
						List headerRuleErrorCodeExclusionsList = new ArrayList();
						if (null != minorHeadingObj
								&& null != minorHeadingObj.getRuleMapping()) {
							Rule currentRule = minorHeadingObj.getRuleMapping()
									.getRule();
							if (null != currentRule) {
								String currentRuleId = currentRule
										.getHeaderRuleId();
								log.info("The header rule " +ESAPI.encoder().encodeForHTML(currentRuleId));
								headerRuleErrorCodeExclusionsList = getHeaderRuleBasedErrorCodes(
										currentRuleId, errorExclusionDetailVO);
								log
										.info("The list of error codes excluded for header rule: "
												+ headerRuleErrorCodeExclusionsList);
								if (null == headerRuleErrorCodeExclusionsList) {
									headerRuleErrorCodeExclusionsList = new ArrayList();
								}
							}
						}
						// Setting Required Flags for E038 and E039
						if (null != minorHeadingObj) {
							// Check for the any of minor heading covered for VISION major heading	for E038
							if (getVisionMajorHeadingsForE038().contains(keyMajor.toUpperCase())) {
								if (minorHeadingObj.isFlagBenefitCovered()) {
									visionCoveredForE038 = true;
								}
							}
							// Check for the any of minor heading covered for DENTAL major heading	for E039
							if (getDentalMajorHeadingsForE039().contains(keyMajor.toUpperCase())) {
								if (minorHeadingObj.isFlagBenefitCovered()) {
									dentalCoveredForE039 = true;
								}
	                        }
							
							if (null != minorHeadingObj.getRuleMapping()
									&& !"NOT_APPLICABLE".equals(minorHeadingObj
											.getRuleMapping()
											.getVariableMappingStatus())) {
								// Check for EB03 = AL for E038
								if (isEB03HasAL(minorHeadingObj)) {
									eB03CodedAsVisionFlag = true;
								}
								
								// Check for EB03 = 35 for E039
								if (isEB03Has35(minorHeadingObj)) {
									eB03CodedAsDentalFlag = true;
								}
					// Check for EB03 = 88 for E008, SSCR 16331 nov Release
								if (!isEB03_88 && isEB03Has88(minorHeadingObj)) {
									isEB03_88 = true;
	
							}
								if(!isBenefitNonCovered){
									isBenefitNonCovered = isBenefitNonCovered(minorHeadingObj);
								}
								if(!isBenefitCovered){
									isBenefitCovered = isBenefitCovered(minorHeadingObj);
								}
							}	
						}
						
						
						if (null != minorHeadingObj && null != minorHeadingObj.getRuleMapping()
								&& minorHeadingObj.getRuleMapping().isMapped()) {

							if (!"NOT_APPLICABLE".equals(minorHeadingObj
									.getRuleMapping()
									.getVariableMappingStatus())) {
								

								/*String minorHeadingDesc = minorHeadingObj
										.getDescriptionText();
								/** for E008 **/
								if (prescDrugPlanFlag && !isE008HasEB03_88) {
									setE008HasEB03_88(minorHeadingObj);
								}
								if (null != minorHeadingObj.getRuleMapping()) {// for
									// E011,E012
									HippaSegment hippaSegmentEB03 = minorHeadingObj
											.getRuleMapping().getEb03();
									if (null != hippaSegmentEB03) {
										List eb03ValuesList = hippaSegmentEB03
												.getHippaCodeSelectedValues();
										Iterator eb03Iterator = eb03ValuesList
												.iterator();

										while (eb03Iterator.hasNext()) {
											HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
													.next();
											String hippaCodeValue = hippaCodeValue03
													.getValue();

											/*if (DomainConstants.EB03_SP
													.equals(hippaCodeValue)) {
												e011SPFlag = true;
											}*/
											if (DomainConstants.EB03_UC
													.equals(hippaCodeValue)) {
												e012UCFlag = true;
											}
											if (getEB03ForE006().contains(
													hippaCodeValue)) {
												eb03ExclusionForE006Flag = true;
											}
										}
									}
								}

								// get Mappings Map
								
								HashMap mappingsMap = (HashMap) minorHeadingObj
										.getMappings();
								Iterator iteratorMappings = mappingsMap
										.entrySet().iterator();
								while (iteratorMappings.hasNext()) {
									// adding the ErrorCodes to the Variable
									// object
									ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
											.next()).getValue();
									if (null != mappingObj
											&& mappingObj.isMapped()
											&& !(DomainConstants.NOT_APPLICABLE_STATUS).equals(mappingObj.getVariableMappingStatus())) {
										/**
										 * This variable has introduced in September release for E004.
										 * This temp variable will have the original(before EB06 auto-population logic)EB01 value. 
										 * Since as part of EB06 auto-population if EB01 = B and format = PCT (Condition to fire E004),
										 * we will change the value to EB01 = A and Eb06 = blank. 
										 * Now the E004 will fire, if the tempEB01ValueForE004 = B and hippaCodeValue01 = A
										 */
										String tempEB01ValueForE004 = null;
										HippaSegment tempHippaSegmentEB01 = mappingObj.getEb01();
										if (null != tempHippaSegmentEB01) {
											HippaCodeValue hippaCodeValue01 = (HippaCodeValue) tempHippaSegmentEB01
													.getHippaCodeSelectedValues()
													.get(0);
											tempEB01ValueForE004 = hippaCodeValue01
													.getValue().trim();
										}
										
										mappingObj = autoPopulateEB06(mappingObj, minorHeadingObj, contract);
										SPSId spsIdObj = mappingObj.getSpsId();
										// Getting EB01 value
										HippaSegment hippaSegmentEB01 = mappingObj
												.getEb01();
										String hippaCode01Value = "";
										if (null != hippaSegmentEB01) {
											HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSegmentEB01
													.getHippaCodeSelectedValues()
													.get(0);
											hippaCode01Value = hippaCodeValue01
													.getValue();
											if (isErrorToBeExcludedSPSLevel(
													errorExclusionDetailVO
															.isE021ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E021,
													errorExclusionDetailVO
															.getSpsExclusionsList(),
													spsIdObj.getSpsId(),
													headerRuleErrorCodeExclusionsList)) {
												if (!setHasEB03_88(minorHeadingObj)) {
													validateE021(mappingObj,
															hippaCode01Value);
												}

											}
										}
										String sensitiveBenefitVariable = "";
 										if (null != minorHeadingObj
												.getRuleMapping()) {
											sensitiveBenefitVariable = minorHeadingObj
													.getRuleMapping()
													.getSensitiveBenefitIndicator();
										}

										/**
										 * For error code E001
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE001ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E001,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											if (!DomainConstants.ZERO
													.equals(spsIdObj
															.getLineValue())
													&& !contract.isCDHPFlag()
													&& (!DomainConstants.Y
															.equals(sensitiveBenefitVariable))) {
												
												//Added as a part of BXNI CR35
												/**
												 * For error code E001 Exclusions:
												 * Exclude E001 if EB03=AL,35,88
												 */
												List<String> eb03List = new ArrayList<String>();
											if(null !=minorHeadingObj.getRuleMapping()){
												if (null !=  minorHeadingObj.getRuleMapping().getEb03()) {
													List eb03ValuesList = minorHeadingObj.getRuleMapping().getEb03()
															.getHippaCodeSelectedValues();
													if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
														Iterator eb03Iterator = eb03ValuesList.iterator();

														while (eb03Iterator.hasNext()) {
															HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
																	.next();
															eb03List.add(hippaCodeValue03.getValue());
														}
													}
														if(!eb03List.contains(DomainConstants.EB03_AL) && !eb03List.contains(DomainConstants.EB03_88) &&
																!eb03List.contains(DomainConstants.EB03_35)){
																validateE001(mappingObj, hippaCode01Value);
														}
													
											}
												}
										}
										}

										/**
										 * For error code E004
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE004ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E004,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE004(tempEB01ValueForE004,
													hippaCode01Value, mappingObj);
										}
										/**
										 * For error code E005
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE005ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E005,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE005(mappingObj,
													hippaCode01Value);
										}
										/**
										 * For error code E006
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE006ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E006,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											if (!eb03ExclusionForE006Flag) {
												validateE006(mappingObj,
														hippaCode01Value);
											}
										}
										/**
										 * For error code E007
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE007ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E007,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE007(mappingObj,
													hippaCode01Value);
										}
										/**
										 * For error code E009
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE009ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E009,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE009(mappingObj,
													hippaCode01Value);
										}
										// validation for E020
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE020ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E020,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE020(mappingObj);
										}

										/**
										 * Setting flags for error code E011 and
										 * E012
										 */

										if (!contract.isFlagHMO()) {
											/*if (e011SPFlag) { // for Error code E011
												spsWithEB03SPList.add(mappingObj);
											}*/											
											if (e012UCFlag) { // for Error code E012
												spsWithEB03UCList.add(mappingObj);
											}
										}
										
										/**
										 * For error code E016 and E017
										 */
										if (contract.isFlagHCR_E016And17()) {
											// Commented E016
											/**
											 * For error code E016
											 */
											/*
											 * if(e016ErrorFlag){
											 * validateE016(minorHeadingObj
											 * ,mappingObj); }
											 */
											/**
											 * For error code E017
											 */
											if (isErrorToBeExcludedSPSLevel(
													errorExclusionDetailVO
															.isE017ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E017,
													errorExclusionDetailVO
															.getSpsExclusionsList(),
													spsIdObj.getSpsId(),
													headerRuleErrorCodeExclusionsList)) {
												validateE017(minorHeadingObj,
														mappingObj);
											}
										}
										/**
										 * For error code E018
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE018ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E018,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											if (contract.isFlagHCR_E018()) {
												validateE018(minorHeadingObj,
														mappingObj);
											}
										}

										/**
										 * For error code E022
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE022ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E022,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											if (!DomainConstants.LINEOFBUSINESS_MSUP
															.equals(contract
																	.getLineOfBusiness())) {
												validateE022(mappingObj);
											}
										}

										/**
										 * For error code E023
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE023ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E023,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE023(mappingObj, contract);
										}

										/**
										 * For error code E026
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE026ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E026,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE026(minorHeadingObj,
													mappingObj);
										}
										/**
										 * For error code E030
										 */
										if (isErrorToBeExcludedSPSLevel(
												errorExclusionDetailVO
														.isE030ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E030,
												errorExclusionDetailVO
														.getSpsExclusionsList(),
												spsIdObj.getSpsId(),
												headerRuleErrorCodeExclusionsList)) {
											validateE030(minorHeadingObj,
													mappingObj);
										}
										
										if (errorExclusionDetailVO
												.isE042ExclusionValidationEnableFlag()) {
											validateE042(mappingObj);
										}
									}
									SPSId spsIdObj = new SPSId();
									if (null != mappingObj
											&& null != mappingObj.getSpsId()) {
										spsIdObj = mappingObj.getSpsId();
									}

									if (isErrorToBeExcludedSPSLevel(
											errorExclusionDetailVO
													.isE025ExclusionValidationEnableFlag(),
											DomainConstants.ERROR_E025,
											errorExclusionDetailVO
													.getSpsExclusionsList(),
											spsIdObj.getSpsId(),
											headerRuleErrorCodeExclusionsList)) {
										if (!checkForE036ErrorCondition(minorHeadingObj)) {
											validateE025(mappingObj, keyMinor);
										}
									}
									// validating error code E029.
									if (isErrorToBeExcludedSPSLevel(
											errorExclusionDetailVO
													.isE029ExclusionValidationEnableFlag(),
											DomainConstants.ERROR_E029,
											errorExclusionDetailVO
													.getSpsExclusionsList(),
											spsIdObj.getSpsId(),
											headerRuleErrorCodeExclusionsList)) {
										validateE029(mappingObj);
									}
									// For error Code E031 --July release
									if (isErrorToBeExcludedSPSLevel(
											errorExclusionDetailVO
													.isE031ExclusionValidationEnableFlag(),
											DomainConstants.ERROR_E031,
											errorExclusionDetailVO
													.getSpsExclusionsList(),
											spsIdObj.getSpsId(),
											headerRuleErrorCodeExclusionsList)) {
										validateE031(mappingObj,
												contract, accumIndList);
									}
									// For error Code E032 --July release
									if (isErrorToBeExcludedSPSLevel(
											errorExclusionDetailVO
													.isE032ExclusionValidationEnableFlag(),
											DomainConstants.ERROR_E032,
											errorExclusionDetailVO
													.getSpsExclusionsList(),
											spsIdObj.getSpsId(),
											headerRuleErrorCodeExclusionsList)) {
										validateE032(mappingObj,
												contract, accumIndList);
									}
									// For error Code E033 --July release
									if (isErrorToBeExcludedSPSLevel(
											errorExclusionDetailVO
													.isE033ExclusionValidationEnableFlag(),
											DomainConstants.ERROR_E033,
											errorExclusionDetailVO
													.getSpsExclusionsList(),
											spsIdObj.getSpsId(),
											headerRuleErrorCodeExclusionsList)) {
										validateE033(mappingObj,
												contract, accumIndList);
									}
									/**
									 * For error code E036
									 */
									if (isErrorToBeExcludedSPSLevel(
											errorExclusionDetailVO
													.isE036ExclusionValidationEnableFlag(),
											DomainConstants.ERROR_E036,
											errorExclusionDetailVO
													.getSpsExclusionsList(),
											spsIdObj.getSpsId(),
											headerRuleErrorCodeExclusionsList)) {
										validateE036(minorHeadingObj,
												mappingObj);
									}
									if (errorExclusionDetailVO
											.isE043ExclusionValidationEnableFlag()) {
										//validateE043(mappingObj,(ContractMappingVO) minorHeadingObj.getRuleMapping());
										//Error E043 is to be set at the Minor Heading level, 
										//hence passing the minorHeadingObj instead of mappingObj
										//minorHeadingE008Set is used to avoid duplication of Error occurrence at Benefit level
										//defect fixed as part of BXNI
										validateE043(minorHeadingObj,minorHeadingE008Set);
									}
									
									
								} // end of Mappings Map while loop
							}
						} else if (null != minorHeadingObj.getRuleMapping()) {
							// set E025 in all the variables associated with the
							// minor heading under consideration.
							// while iterating through spsID check for data
							// type.
							// For error code E025
							if (errorExclusionDetailVO
									.isE025ExclusionValidationEnableFlag()) {
								validateE025(minorHeadingObj, keyMinor,
										headerRuleErrorCodeExclusionsList);
							}
						} // end of Not Applicable check

					} // end of Minor Heading Map while loop
					prescDrugPlanFlag = false;
				} // end of Major Heading Map while loop
				/**
				 * Returning contract object with error details
				 */
				
				if (!contract.isFlagHMO()) {
				 /*BXNI December Release-- Remove E011
					if (errorExclusionDetailVO.isE011ExclusionValidationEnableFlag()) {
						
						 \\ Validation for E011, EB03=SP
						
						validateE011(contract);
					}*/
					
					if (errorExclusionDetailVO.isE012ExclusionValidationEnableFlag()) {
					
						 // Validation for E012,EB03=UC
						
						validateE012(contract);
					}
				}
			}
			// E038
			if (errorExclusionDetailVO.isE038ExclusionValidationEnableFlag()) {
				validateE038(contract,eB03CodedAsVisionFlag, visionCoveredForE038);
			}
			// E039
			if (errorExclusionDetailVO.isE039ExclusionValidationEnableFlag()) {
				validateE039(contract,eB03CodedAsDentalFlag, dentalCoveredForE039);
			}

			//E008
			if (errorExclusionDetailVO
					.isE008ExclusionValidationEnableFlag() && E008BenefitsFlag) {
            	/**
				 * Validation for E008
				 */
            	validateE008(contract, isEB03_88,isBenefitCovered, isBenefitNonCovered);
			}
			
		}
		if (errorExclusionDetailVO
				.isE042ExclusionValidationEnableFlag()) {
			isE042Exist(nonCoveredMap,coveredList);
		}
		//if E004, E006 and E007 present then E005 must be excluded.
		String exclusionArray[] = {DomainConstants.ERROR_E004,
				DomainConstants.ERROR_E006, DomainConstants.ERROR_E007 };
		String errorToBeExcluded = DomainConstants.ERROR_E005;
		contractVOList = errorExclusion(contractVOList,
				errorToBeExcluded,exclusionArray);
		return contractVOList;
	}
	
	private void validateE042(ContractMappingVO mappingObj) {

		if (null != mappingObj && null != mappingObj.getSpsId()) {
			SPSId spsIdObj = mappingObj.getSpsId();

			String valueCoded = spsIdObj.getLineValue();
			String eb01Value = mappingObj.getEB01Value();

			List <EB03Association> eb03Associations  = new ArrayList<EB03Association>();
			
			if(null !=mappingObj.getEb03()){
				eb03Associations = mappingObj.getEb03().getEb03Association();
			}

			
			if(DomainConstants.EB01_BC.equals(eb01Value) 
					&& DomainConstants.N.equals(valueCoded)){
				

				if(null != eb03Associations){

					for(EB03Association eb03Association:eb03Associations){

						if (((null == eb03Association.getIii02List()) || (0== eb03Association.getIii02List().size())) && (StringUtils.isBlank(eb03Association.getMessage()))){																	
							nonCoveredMap.put(eb03Association.getEb03().getValue().trim(), mappingObj);																		
						}

					}
				}		
			} else {
				if ((DomainConstants.EB01_A.equals(eb01Value)
						|| DomainConstants.EB01_B.equals(eb01Value)
						|| DomainConstants.EB01_C.equals(eb01Value)
						|| DomainConstants.EB01_F.equals(eb01Value)
						|| DomainConstants.EB01_G.equals(eb01Value) || DomainConstants.EB01_U
						.equals(eb01Value))
						|| (DomainConstants.EB01_BC.equals(eb01Value) && DomainConstants.Y
								.equals(valueCoded))) {
					if (null != eb03Associations) {
						for (EB03Association eb03Association : eb03Associations) {
							if (!coveredList.contains(eb03Association.getEb03()
									.getValue().trim())) {
								coveredList.add(eb03Association.getEb03()
										.getValue().trim());
							}
						}
					}
				}
			}
		}
	}
	
	private void isE042Exist(Map<String, ContractMappingVO> nonCoveredMap,
			List<String> coveredList) {

		String eb03Value = null;
		String description = "";					
		boolean errorExists = false;
		ContractMappingVO mapping = null;		

		for(Map.Entry<String, ContractMappingVO> entry : nonCoveredMap.entrySet()) { 
			eb03Value = entry.getKey();				
			mapping = entry.getValue();
			


			if(coveredList.contains(eb03Value)){
				
				errorExists = true;						
			}

			if(errorExists){

				description ="Discrepancy in service ("+eb03Value+"): Coded as Non-Covered with patient liability.";
				errorExists = false;
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E042);
				errorDetailsObject.setAssociatedEb03(eb03Value);
				errorDetailsObject.setErrorDesc(description);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E001
	 * 
	 * @param mapping
	 * @param String
	 * @return
	 */
	private void validateE001(ContractMappingVO mapping, String hippaCode01Value) {
		HippaSegment eb06 = mapping.getEb06();
		String hsd01_Value=null;
		String hsd05_Value=null;
		HippaSegment hsd01 = mapping.getHsd01();
		HippaSegment hsd05 = mapping.getHsd05();
		String hippaCodeAccumValue = null;
		HippaSegment accum = mapping.getAccum();
		
		if (null != accum) {
			HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accum.getHippaCodeSelectedValues().get(0);
			hippaCodeAccumValue =  hippaCodeValueAccum.getValue();
		}
		/**
		 * Checking if EB06 value is null
		 */
		if (null != eb06) {
			HippaCodeValue hippaCodeValue06 = (HippaCodeValue) eb06
					.getHippaCodeSelectedValues().get(0);
			String hippaCode06Value = hippaCodeValue06.getValue();
			// Checking if EB01 = Deductible, Out-Of-Pocket or Limitation
			if ((DomainConstants.DEDUCTIBLE.equals(hippaCode01Value)
					|| DomainConstants.STOP_LOSS.equals(hippaCode01Value) || DomainConstants.LIMITATIONS
					.equals(hippaCode01Value))) {
				
				// Checking if EB06 = 22, 23, 25, 32,33 and no accumulator
				// mapped
				if ((getEB06ForE001().contains(hippaCode06Value))
						&& (null == hippaCodeAccumValue || DomainConstants.EMPTY.equals(hippaCodeAccumValue))) {
					/**
					 * Object to hold error details Variable Level Error
					 */
				
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E001);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E001_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}

			}
		}
			
			//Added as a part of BXNI CR35
			if (null != hsd01) {
				// Checking list is null
				if (null != hsd01.getHippaCodeSelectedValues()) {
					HippaCodeValue hippaCodeHsdValue01 = (HippaCodeValue) hsd01
							.getHippaCodeSelectedValues().get(0);
					hsd01_Value = hippaCodeHsdValue01.getValue();
				} else {
					hsd01_Value = DomainConstants.HSD01_BLANK;
				}
			} else {
				hsd01_Value = DomainConstants.HSD01_BLANK;
			}
			//Added as a part of BXNI CR35
			if (null != hsd05) {
				// Checking list is null
				if (null != hsd05.getHippaCodeSelectedValues()) {
					HippaCodeValue hippaCodeHsdValue05 = (HippaCodeValue) hsd05
							.getHippaCodeSelectedValues().get(0);
					hsd05_Value = hippaCodeHsdValue05.getValue();
				} else {
					hsd05_Value = DomainConstants.HSD05_BLANK;
				}
			} else {
				hsd05_Value = DomainConstants.HSD05_BLANK;
			}
			
			//Added as a part of BXNI CR35
			/**
			 * Checking if EB01 = B/A/F, HSD01 is non blank
			 * and HSD05 = 22/23/25/32/Blank, and no accumulator mapped and sensitive indicator is N
			 */
			
			if(getEB01forE001().contains(hippaCode01Value)	&& !DomainConstants.HSD01_BLANK.equals(hsd01_Value) && ((getHSD05ForE001().contains(hsd05_Value))
					|| DomainConstants.HSD05_BLANK.equals(hsd05_Value))
					&& (null == hippaCodeAccumValue || DomainConstants.EMPTY.equals(hippaCodeAccumValue))){
				/**
				 * Object to hold error details Variable Level Error
				 */
				
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E001);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E001_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
			
		}
	

	/**
	 * This method checks condition for Error Code - E004
	 * E004 will fire, if the tempEB01ValueForE004 = B and hippaCodeValue01 = A
	 * @param tempEB01ValueForE004
	 * @param mappingObj 
	 * @param hippaCode01Value
	 * @return
	 */
	private void validateE004(String tempEB01ValueForE004, String hippaCode01Value, ContractMappingVO mappingObj) {
		
		if (null != tempEB01ValueForE004 && !DomainConstants.EMPTY.equals(tempEB01ValueForE004) 
				&& null != hippaCode01Value && !DomainConstants.EMPTY.equals(hippaCode01Value)) {
			if (DomainConstants.EB01_B.equalsIgnoreCase(tempEB01ValueForE004)
					&& DomainConstants.EB01_A.equalsIgnoreCase(hippaCode01Value
							.trim())) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(false);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E004);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E004_DESCRIPTION);
				mappingObj.getErrorCodesList().add(errorDetailsObject);
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E005
	 * 
	 * @param mapping
	 * @param String
	 * @return
	 */
	private void validateE005(ContractMappingVO mapping, String hippaCode01Value) {
		String format = "";
		String lineValue = "";

		if (null != mapping.getSpsId()) {
			format = mapping.getSpsId().getLineDataType();
			lineValue = mapping.getSpsId().getLineValue();
		}

		/**
		 * Check for Value coded is non numeric and
		 * EB01 = A or B or C or G or F .
		 */
		
		if (!BxUtil.isNumeric(lineValue)
				&& getJunkCheckForE005().contains(hippaCode01Value)) {
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E005);
			errorDetailsObject.setErrorDesc(DomainConstants.E005_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);

		}/**
		 * Check for Y/N variables in admin section should be mapped only to
		 * EB01 = BC or DW .
		 */
		else if (!DomainConstants.BOOLEAN_FORMAT.equals(format)
				&& getEB01ForE005().contains(hippaCode01Value)) {
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E005);
			errorDetailsObject.setErrorDesc(DomainConstants.E005_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);

		}
	}

	/**
	 * This method checks condition for Error Code - E006 For eWPD, if the BX
	 * mapping has EB01 NOT = "A" or "B" or "I",but FORMAT = PCT then error is
	 * triggered.Exclusion - do not throw error if variable has EB03 = 88 or AL
	 * or 35
	 * 
	 * @param mapping
	 * @param String
	 * @return
	 */
	private void validateE006(ContractMappingVO mapping, String hippaCode01Value) {
		String format = "";
		if (null != mapping.getSpsId()) {
			format = mapping.getSpsId().getLineDataType();
		}

		// Checking if EB01 != A or B or I
		if (!DomainConstants.COINSURANCE.equals(hippaCode01Value)
				&& !DomainConstants.COPAYMENT.equals(hippaCode01Value)
				&& !DomainConstants.EB01_NON_COVERED.equals(hippaCode01Value)) {
			// Checking if format is PCT
			if (null != format
					&& DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E006);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E006_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E007
	 * 
	 * @param mapping
	 * @param String
	 * @return
	 */
	private void validateE007(ContractMappingVO mapping, String hippaCode01Value) {
		String format = "";
		if (null != mapping.getSpsId()) {
			format = mapping.getSpsId().getLineDataType();
		}
		// Checking if EB01 = A
		if (DomainConstants.COINSURANCE.equals(hippaCode01Value)) {
			// Checking if format is not PCT
			if (null != format
					&& !DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E007);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E007_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
		}
	}

	/**
	 * The method checks whether the given Benefit has EB03=88 If Yes, then sets
	 * the flag isE008HasEB03_88 as true.
	 * 
	 * @param minorHeadingObj
	 * @return
	 */
	private void setE008HasEB03_88(MinorHeadingsVO minorHeadingObj) {
		if (null != minorHeadingObj.getRuleMapping()) {
			HippaSegment eb03 = minorHeadingObj.getRuleMapping().getEb03();
			if (null != eb03) {
				List hippaCodesList = eb03.getHippaCodeSelectedValues();
				Iterator eb03Iterator = hippaCodesList.iterator();

				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					if (hippaCodeValue03.getValue().equals(
							DomainConstants.EB03_88)) {
						isE008HasEB03_88 = true;
					}
				}
			}
		}
	}

	/**
	 * The method checks whether the given Benefit has EB03=88 If Yes, then sets
	 * the flag isE021HasEB03_88 as true.
	 * 
	 * @param minorHeadingObj
	 * @return boolean
	 */
	private boolean setHasEB03_88(MinorHeadingsVO minorHeadingObj) {
		boolean HasEB03_88 = false;
		if (null != minorHeadingObj.getRuleMapping()) {
			HippaSegment eb03 = minorHeadingObj.getRuleMapping().getEb03();
			if (null != eb03) {
				List hippaCodesList = eb03.getHippaCodeSelectedValues();
				Iterator eb03Iterator = hippaCodesList.iterator();

				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					if (hippaCodeValue03.getValue().equals(
							DomainConstants.EB03_88)) {
						HasEB03_88 = true;
					}
				}
			}
		}
		return HasEB03_88;
	}

	/**
	 * This method checks condition for Error Code - E008
	 * E008 is modified as part of SSCR 16331 Nov Release.
	 * E008 will be thrown if a non covered contract has a pharmacy benefit coded in it.
	 * if a Contract does not have a mapping Eb03=88, then it is treated as non covered.
	 * if a contract has a mapping Eb03 = 88,
	 * then all the benefits having EB03=88 has the Benefit Covered Question answered as N, then it is treated as non covered.
	 * isEB03_88 flag holds true if there is a Eb03 = 88 mapping.
	 * isBenefitCovered flag holds true if the Eb03= 88 mapped variable has Eb01=BC and coded value other than N.
	 * isBenefitNonCovered flag holds true if the Eb03= 88 mapped variable has Eb01=BC and coded value = N.
	 * if the contract is non covered, then check for the pharmacy benefit component list
	 * PRESCRIPTION DRUG PLAN
	 * PRESCRIPTION DRUG
	 * PRESCRIPTION DRUG BENEFIT
	 * If any benefit inside the above benefit component has Benefit Covered question answered as Y, then throw E008 at Benefit level
	 * If MEDICARE PART B DEDUCTIBLE benefit in OUTPATIENT HOSPITAL BENEFITS BC has Benefit Covered question answered as Y, 
	 * then throw E008 at Benefit level
	 * If SEXUAL DYSFUNCTION RX benefit in SEXUAL DISORDERS BC has Benefit Covered question answered as Y, 
	 * then throw E008 at Benefit level
	 * 
	 * @param ContractVO, isBenefitCovered, isBenefitNonCovered isEB03_88
	 */
	private void validateE008(ContractVO contract, boolean isEB03_88, boolean isBenefitCovered, boolean isBenefitNonCovered ) {
		log.debug("isEB0388 >>"+isEB03_88);
		log.debug("isVariableCovered >>"+isBenefitCovered);
		log.debug("isVariableNonCovered >>"+isBenefitNonCovered);
		Set<String> minorHeadingE008Set = new HashSet<String>();
		if (!isEB03_88 || (isBenefitNonCovered && !isBenefitCovered)) {
			HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
			if (majorHeadingsMap.size() > 0) {
				Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
				while (iteratorMajor.hasNext()) {
					String keyMajor = (String) iteratorMajor.next();
					MajorHeadingsVO majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
					.get(keyMajor);
					// Get MinorHeadings Map
					HashMap minorHeadingsMap = (HashMap) majorHeadingObj
					.getMinorHeadings();
					Iterator iteratorMinor = minorHeadingsMap.keySet()
					.iterator();
					if (getEWPDBenefitComponentsForE008().contains(
							majorHeadingObj.getDescriptionText())) {

						while (iteratorMinor.hasNext()) {
							String keyMinor = (String) iteratorMinor.next();
							MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
							.get(keyMinor);
							if (minorHeadingObj.isFlagBenefitCovered()) {
								ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
								errorDetailsObject.setError(true);
								errorDetailsObject.setErrorCode(DomainConstants.ERROR_E008);
								errorDetailsObject.setErrorDesc(DomainConstants.E008_DESCRIPTION);
								minorHeadingObj.getErrorCodesList().add(errorDetailsObject);
								minorHeadingE008Set.add(minorHeadingObj.getDescriptionText());
							}
						}
					}
					if(DomainConstants.BC_EWPD_E008_OUTPATIENT_HOSP_BNFTS.
							equals(majorHeadingObj.getDescriptionText())){
						while (iteratorMinor.hasNext()) {
							String keyMinor = (String) iteratorMinor.next();
							MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
							.get(keyMinor);
							if (DomainConstants.BNFT_EWPD_E008_MEDICARE.equals(minorHeadingObj.getDescriptionText()) 
									&& minorHeadingObj.isFlagBenefitCovered()) {
								ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
								errorDetailsObject.setError(true);
								errorDetailsObject.setErrorCode(DomainConstants.ERROR_E008);
								errorDetailsObject.setErrorDesc(DomainConstants.E008_DESCRIPTION);
								minorHeadingObj.getErrorCodesList().add(errorDetailsObject);
								minorHeadingE008Set.add(minorHeadingObj.getDescriptionText());
							}
						}
					}
					if(DomainConstants.BC_EWPD_E008_SEXUAL_DISORDERS.
							equals(majorHeadingObj.getDescriptionText())){
						while (iteratorMinor.hasNext()) {
							String keyMinor = (String) iteratorMinor.next();
							MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
							.get(keyMinor);
							if (DomainConstants.BNFT_EWPD_E008_SEXUAL_DYSFUNCTION.equals(minorHeadingObj.getDescriptionText()) 
									&& minorHeadingObj.isFlagBenefitCovered()) {
								ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
								errorDetailsObject.setError(true);
								errorDetailsObject.setErrorCode(DomainConstants.ERROR_E008);
								errorDetailsObject.setErrorDesc(DomainConstants.E008_DESCRIPTION);
								minorHeadingObj.getErrorCodesList().add(errorDetailsObject);
								minorHeadingE008Set.add(minorHeadingObj.getDescriptionText());
							}
						}
					}
				}
			}
		}
	}
	/**
	 * This method checks condition for Error Code - E009
	 * 
	 * @param mapping
	 * @param String
	 * @return
	 */
	private void validateE009(ContractMappingVO mapping, String hippaCode01Value) {
		String format = "";
		if (null != mapping.getSpsId()) {
			format = mapping.getSpsId().getLineDataType();
		}
		/**
		 * Checking if variable data type is Visits, Hours, Days, Months, Years,
		 * Occurrences and Age
		 */
		if (null != format && getFormatsForE009().contains(format)) {
			// Checking if EB01 = F, if not error
			if (!DomainConstants.LIMITATIONS.equals(hippaCode01Value)) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E009);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E009_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
		}
	}

	//BXNI December Release-- Remove E011
	/**
	 * This method checks condition for Error Code - E011, EB03=SP
	 * Excluding EPO out network
	 * @param contract
	 * @return Error
	 */
/*	private void validateE011(ContractVO contract) {

		if (spsWithEB03SPList.size() > 0) {
			isPVA_E011_IN_SP = false;
			isPVA_E011_OUT_SP = false;
			isPVA_E011_INOUT_SP = false;
			
			ContractMappingVO mappingObj = null;
			
			Iterator mappingObjectIter = spsWithEB03SPList.iterator();
			while (mappingObjectIter.hasNext()) {
				mappingObj = (ContractMappingVO) mappingObjectIter.next();
				if (getInNetworkPVAMappingsForE010().contains(mappingObj.getSpsId().getLinePVA())) {
					isPVA_E011_IN_SP = true;
				} else if (getOutNetworkPVAMappingsForE010().contains(
						mappingObj.getSpsId().getLinePVA())) {
					isPVA_E011_OUT_SP = true;
				} else if (getInOutNetworkPVAMappingsForE010().contains(
						mappingObj.getSpsId().getLinePVA())) {
					isPVA_E011_INOUT_SP = true;
				}
			}
			boolean isError = false;
			*//**
			 * If pva = all, no error
			 *//*
			if (!isPVA_E011_INOUT_SP) {
				*//**
				 * Checking if PAR is coded
				 *//*
				if (isPVA_E011_IN_SP) {
					if (!isPVA_E011_OUT_SP) {
						*//**
						 * PAR is coded and NPAR is not coded
						 *//*
						isError = true;
					}
				} else {
					isError = true;
				}
			}
			if (isError) {
				Iterator spsWithEB03SPIter = spsWithEB03SPList.iterator();
				ErrorDetailVO errorDetailsObject = null;
				ContractMappingVO mappingObjfromList = null;
				ContractMappingVO mappingObjContract = null;
				MajorHeadingsVO majorHeadingObj = null;
				MinorHeadingsVO minorHeadingObj = null;
				String keyMinor = null;
				String keyMajor = null;
				Rule currentRule = null;
				List headerRuleErrorCodeExclusionsList = null;
				while (spsWithEB03SPIter.hasNext()) {
					mappingObjfromList = (ContractMappingVO) spsWithEB03SPIter.next();
					HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
					Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
					if (majorHeadingsMap.size() > 0) {
						while (iteratorMajor.hasNext()) {
							keyMajor = (String) iteratorMajor.next();
							majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
												.get(keyMajor);
							HashMap minorHeadingsMap = (HashMap) majorHeadingObj.getMinorHeadings();
							Iterator iteratorMinor = minorHeadingsMap.keySet().iterator();
							while (iteratorMinor.hasNext()) {
								keyMinor = (String) iteratorMinor.next();
								minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
													.get(keyMinor);
								// Checking the exclusion for the header rule associated
								// with the minor heading.
								headerRuleErrorCodeExclusionsList = new ArrayList();
								if (null != minorHeadingObj && null != minorHeadingObj.getRuleMapping()) {
									currentRule = minorHeadingObj.getRuleMapping().getRule();
									if (null != currentRule) {
										headerRuleErrorCodeExclusionsList =
											getHeaderRuleBasedErrorCodes(
												currentRule.getHeaderRuleId(), errorExclusionDetailVO);
										if (null == headerRuleErrorCodeExclusionsList) {
											headerRuleErrorCodeExclusionsList = new ArrayList();
										}
									}
								}
								if(null != minorHeadingObj){
									HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
									if(null != mappingsMap){
										Iterator iteratorMappings = mappingsMap.entrySet().iterator();
										while (iteratorMappings.hasNext()) {
											mappingObjContract = (ContractMappingVO) ((Map.Entry) iteratorMappings.next()).getValue();
											if (null != mappingObjContract) {
												if (mappingObjContract.equals(mappingObjfromList)) {
													if (null != mappingObjContract  && null != mappingObjContract.getSpsId() && !isErrorToBeExcludedSPSLevel(
																	errorExclusionDetailVO.isE011ExclusionValidationEnableFlag(),DomainConstants.ERROR_E011,
																	errorExclusionDetailVO.getSpsExclusionsList(), mappingObjContract.getSpsId().getSpsId(),
																	headerRuleErrorCodeExclusionsList)) {
														continue;
													}
													errorDetailsObject = new ErrorDetailVO();
													
													if (getInNetworkPVAMappingsForE010().contains(mappingObjfromList.getSpsId().getLinePVA())) {
														if (!contract.isFlagEPO()) {
															errorDetailsObject.setError(true);
															errorDetailsObject.setErrorCode(DomainConstants.ERROR_E011);
															errorDetailsObject.setErrorDesc(DomainConstants.E011_DESCRIPTION1);
														}
													} else if (getOutNetworkPVAMappingsForE010().contains(
															mappingObjfromList.getSpsId().getLinePVA())) {
														//if (!contract.isFlagEPO()) {
															errorDetailsObject.setError(true);
															errorDetailsObject.setErrorCode(DomainConstants.ERROR_E011);
															errorDetailsObject.setErrorDesc(DomainConstants.E011_DESCRIPTION2);
														//}
													}
													mappingObjContract.getErrorCodesList().add(errorDetailsObject);
													break;
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
		} else { // contract level error
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E011);
			errorDetailsObject.setErrorDesc(DomainConstants.E011_DESCRIPTION3);
			contract.getErrorCodesList().add(errorDetailsObject);
		}
	}*/

	/**
	 * This method checks condition for Error Code - E012
	 * Excluding EPO out network
	 * @param mapping
	 * @return
	 */
	private void validateE012(ContractVO contract) {
		
		if (spsWithEB03UCList.size() > 0) {
			
			isPVA_E012_IN_UC = false;
			isPVA_E012_OUT_UC = false;
			isPVA_E012_INOUT_UC = false;
			ContractMappingVO mappingObj = null;
			
			Iterator mappingObjectIter = spsWithEB03UCList.iterator();
			while (mappingObjectIter.hasNext()) {
				mappingObj = (ContractMappingVO) mappingObjectIter.next();
				if (getInNetworkPVAMappingsForE010().contains(mappingObj.getSpsId().getLinePVA())) {
					isPVA_E012_IN_UC = true;
				} else if (getOutNetworkPVAMappingsForE010().contains(
						mappingObj.getSpsId().getLinePVA())) {
					isPVA_E012_OUT_UC = true;
				} else if (getInOutNetworkPVAMappingsForE010().contains(
						mappingObj.getSpsId().getLinePVA())) {
					isPVA_E012_INOUT_UC = true;
				}
			}
			boolean isError = false;
			/**
			 * If pva = all, no error
			 */
			if (!isPVA_E012_INOUT_UC) {
				/**
				 * Checking if PAR is coded
				 */
				if (isPVA_E012_IN_UC) {
					if (!isPVA_E012_OUT_UC) {
						/**
						 * PAR is coded and NPAR is not coded
						 */
						isError = true;
					}
				} else {
					isError = true;
				}
			}
			if (isError) {
				Iterator spsWithEB03UCIter = spsWithEB03UCList.iterator();
				ErrorDetailVO errorDetailsObject = null;
				ContractMappingVO mappingObjfromList = null;
				ContractMappingVO mappingObjContract = null;
				MajorHeadingsVO majorHeadingObj = null;
				MinorHeadingsVO minorHeadingObj = null;
				String keyMinor = null;
				String keyMajor = null;
				Rule currentRule = null;
				List headerRuleErrorCodeExclusionsList = null;
				while (spsWithEB03UCIter.hasNext()) {
					mappingObjfromList = (ContractMappingVO) spsWithEB03UCIter.next();
					HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
					Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
					if (majorHeadingsMap.size() > 0) {
						while (iteratorMajor.hasNext()) {
							keyMajor = (String) iteratorMajor.next();
							majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
												.get(keyMajor);
							HashMap minorHeadingsMap = (HashMap) majorHeadingObj.getMinorHeadings();
							Iterator iteratorMinor = minorHeadingsMap.keySet().iterator();
							while (iteratorMinor.hasNext()) {
								keyMinor = (String) iteratorMinor.next();
								minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
													.get(keyMinor);
								// Checking the exclusion for the header rule associated
								// with the minor heading.
								headerRuleErrorCodeExclusionsList = new ArrayList();
								if (null != minorHeadingObj && null != minorHeadingObj.getRuleMapping()) {
									currentRule = minorHeadingObj.getRuleMapping().getRule();
									if (null != currentRule) {
										headerRuleErrorCodeExclusionsList =
											getHeaderRuleBasedErrorCodes(
												currentRule.getHeaderRuleId(), errorExclusionDetailVO);
										if (null == headerRuleErrorCodeExclusionsList) {
											headerRuleErrorCodeExclusionsList = new ArrayList();
										}
									}
								}
								if(null != minorHeadingObj){
									HashMap mappingsMap = (HashMap) minorHeadingObj
									.getMappings();
									if(null !=mappingsMap){
										Iterator iteratorMappings = mappingsMap
										.entrySet().iterator();	
										while (iteratorMappings.hasNext()) {
											mappingObjContract = (ContractMappingVO) ((Map.Entry) iteratorMappings
															.next()).getValue();
											if (null != mappingObjContract) {
												if (mappingObjContract.equals(mappingObjfromList)) {
													if (null != mappingObjContract
															&& null != mappingObjContract.getSpsId()
															&& !isErrorToBeExcludedSPSLevel(
																	errorExclusionDetailVO
																			.isE012ExclusionValidationEnableFlag(),
																	DomainConstants.ERROR_E012,
																	errorExclusionDetailVO
																			.getSpsExclusionsList(), mappingObjContract
																			.getSpsId().getSpsId(),
																	headerRuleErrorCodeExclusionsList)) {
														continue;
													}
													errorDetailsObject = new ErrorDetailVO();
													
													if (getInNetworkPVAMappingsForE010().contains(mappingObjfromList.getSpsId().getLinePVA())) {
														if (!contract.isFlagEPO()) {
															errorDetailsObject.setError(true);
															errorDetailsObject
																	.setErrorCode(DomainConstants.ERROR_E012);
															errorDetailsObject.setErrorDesc(DomainConstants.E012_DESCRIPTION1);
														}
													} else if (getOutNetworkPVAMappingsForE010().contains(
															mappingObjfromList.getSpsId().getLinePVA())) {
														//if (!contract.isFlagEPO()) {
															errorDetailsObject.setError(true);
															errorDetailsObject
																	.setErrorCode(DomainConstants.ERROR_E012);
															errorDetailsObject.setErrorDesc(DomainConstants.E012_DESCRIPTION2);
														//}
													}
													mappingObjContract.getErrorCodesList().add(
															errorDetailsObject);
													break;
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
		} else {
			// contract level error
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E012);
			errorDetailsObject.setErrorDesc(DomainConstants.E012_DESCRIPTION3);
			contract.getErrorCodesList().add(errorDetailsObject);
		}
	}

	

	/**
	 * This method checks condition for Error Code - E017 the error is reported
	 * for HCR contracts if sps id mapping has EB03= ('2 ' '4 ' '5 ' '7 ' '8 '
	 * '12' '13' '18' '33' '40' '45' '60' '73' '76' '78' '88' '98' 
	 * 'AD' 'AE' 'AF' 'AI' 'CE' 'CF' 'CG' 'CH' 'CI' 'CJ') and EB01=F and EB06=22 or 23 and if format is DOL
	 * for sps id
	 * 
	 * @param minorHeadingObj
	 * @param mapping
	 * @return
	 */
	private void validateE017(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mapping) {
		String format = null;
		if (null != mapping.getSpsId()) {
			format = mapping.getSpsId().getLineDataType();
			// consider only dollar format for sps id for this error.
			if (DomainConstants.FORMAT_DOLLAR.equals(format)) {
				boolean hippacodeEB03forE017 = false;
				// Checking if EB03 is null
				if (null != minorHeadingObj.getRuleMapping()) {
					// Checking if EB06 is null
					if (null != mapping.getEb06()) {
						String hippaCode01Value = "";
						if (null != mapping.getEb01()) {
							HippaSegment hippaSEgmentEB01 = mapping.getEb01();
							HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSEgmentEB01
									.getHippaCodeSelectedValues().get(0);
							hippaCode01Value = hippaCodeValue01.getValue();
						}

						HippaCodeValue hippaCodeValue06 = (HippaCodeValue) mapping
								.getEb06().getHippaCodeSelectedValues().get(0);
						String hippaCode06Value = hippaCodeValue06.getValue();
						// Checking if EB01 = F
						if (DomainConstants.LIMITATIONS
								.equals(hippaCode01Value)) {
							// Check if EB06=22 or 23
							if (DomainConstants.EB06_22
									.equals(hippaCode06Value)
									|| DomainConstants.EB06_23
											.equals(hippaCode06Value)
											|| DomainConstants.EB06_BLANK.equals(hippaCode06Value)) {
								// Check for EB03 values
								if (null != minorHeadingObj.getRuleMapping()) {
									if (null != minorHeadingObj
											.getRuleMapping().getEb03()) {
										List eb03ValuesList = minorHeadingObj
												.getRuleMapping().getEb03()
												.getHippaCodeSelectedValues();
										Iterator eb03Iterator = eb03ValuesList
												.iterator();

										while (eb03Iterator.hasNext()) {
											HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
													.next();
											String hippaCodeValue = hippaCodeValue03
													.getValue();
											if (getEB03ForE017().contains(
													hippaCodeValue)) {
												hippacodeEB03forE017 = true;
												break;
											}
										}
									}

								}

								if (hippacodeEB03forE017) {
									/**
									 * Object to hold error details variable
									 * Level
									 */
									ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
									errorDetailsObject.setError(true);
									errorDetailsObject
											.setErrorCode(DomainConstants.ERROR_E017);
									errorDetailsObject
											.setErrorDesc(DomainConstants.E017_DESCRIPTION);
									mapping.getErrorCodesList().add(
											errorDetailsObject);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E018 E018 –error is thrown
	 * for HCR contracts if variable/sps id mapping EB03 is any of '68' 'BH'
	 * '80' '81' 'BZ' 'CO' 'CK' 'CL' and type is PCT or DOL. For PCT format (EB01 = A), throw error
	 * only if variable/sps id is IN-NETWORK or IN OUT NETWORK and value coded
	 * on benefit is not 100%. Throw an error if a DOL format value is coded for
	 * the above EB03s in the contract for IN-NETWORK or IN OUT NETWORK.
	 * 
	 * @param minorHeadingObj
	 * @param mapping
	 * @return
	 */
	private void validateE018(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mapping) {

		boolean isHippacodeEB03Conditions = false;
		boolean isError = false;
		String lineValue = "";
		String linePVA = "";
		String datatype = "";
		if (null != mapping.getSpsId()) {
			lineValue = mapping.getSpsId().getLineValue();
			linePVA = mapping.getSpsId().getLinePVA();
			datatype = mapping.getSpsId().getLineDataType();
		}
		String hippaCode01Value = "";
		if (null != mapping.getEb01()) {
			HippaSegment hippaSEgmentEB01 = mapping.getEb01();
			HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSEgmentEB01
					.getHippaCodeSelectedValues().get(0);
			hippaCode01Value = hippaCodeValue01.getValue();
		}

		if (null != minorHeadingObj.getRuleMapping()) {
			HippaSegment hippaSegmentEB03 = minorHeadingObj.getRuleMapping()
					.getEb03();
			if (null != hippaSegmentEB03) {
				List eb03ValuesList = hippaSegmentEB03
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();

				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					if (getEB03ForE018().contains(hippaCodeValue)) {
						isHippacodeEB03Conditions = true;
					}
				}
			}
		}
		if (isHippacodeEB03Conditions
				&& DomainConstants.PERCENTAGE_FORMAT.equals(datatype)) {
			if (DomainConstants.COINSURANCE.equals(hippaCode01Value)
					&& !DomainConstants.EXCLSN_E018_BENFTVAL.equals(lineValue)
					&& (getInNetworkPVAMappings().contains(linePVA) || getInOutNetworkPVAMappings()
							.contains(linePVA))) {
				isError = true;
			}
		} else if (isHippacodeEB03Conditions
				&& DomainConstants.FORMAT_DOLLAR.equals(datatype)
				&& (getInNetworkPVAMappings().contains(linePVA) || getInOutNetworkPVAMappings()
						.contains(linePVA))) {
			isError = true;
		}
		if (isError) {
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E018);
			errorDetailsObject.setErrorDesc(DomainConstants.E018_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}

	}

	


	/**
	 * This method checks condition for Error Code - E020
	 * 
	 * @param ContractMappingVO
	 *            mapping
	 *            
	 * * Code logic is changed as part of BXNI December release
	 * The E020 error will be reported if the sps is mapped to 
	 * EB09 = DY and EB06 as 7 (Day ) or 13 (24 hour )
	 * EB09 = HS and EB06 as 6 (hour ) 
	 * EB09 = MN and EB06 as 34 (month ) 
	 * EB09 = VS and EB06 as 27 (visit ) 
	 * EB09 = YY and EB06 value is 21 (year) or 22 (service year) or 23 (calendar year) or blank
	 * HSD01 = DY and HSD05 as 7 (Day ) or 13 (24 hour )
	 * HSD01 = HS and HSD05 as 6 (hour )
	 * HSD01 = MN and HSD05 as 34 (month) 
	 * HSD01 = VS and HSD05 as 27 (visit ) 
	 * 
	 * 
	 */
	 
	private void validateE020(ContractMappingVO mapping) {
		HippaSegment eb06 = null;
		HippaSegment eb09 = null;
		HippaSegment hsd05 = null;
		HippaSegment hsd01 = null;
		
		String hippaCode06Value = null;
		String hippaCode09Value = null;
		String hippaCodeHsd05Value = null;
		String hippaCodeHsd01Value = null;
		
		HippaCodeValue hippaCodeValue = new HippaCodeValue();
		
		if(null != mapping.getEb06()){
			eb06 = mapping.getEb06();
		}
		if(null != mapping.getEb09()){
			eb09 = mapping.getEb09();
		}
		if(null != mapping.getHsd01()){
			hsd01 = mapping.getHsd01();
		}
		if(null != mapping.getHsd05()){
			hsd05 = mapping.getHsd05();
		}
		
		/**
		 * Checking if EB06 value is null
		 */
		if (null != eb06) {
			hippaCodeValue = (HippaCodeValue) eb06
					.getHippaCodeSelectedValues().get(0);
			hippaCode06Value = hippaCodeValue.getValue();
		}
		/**
		 * Checking if EB09 value is null
		 */
		if(null != eb09){
			hippaCodeValue = (HippaCodeValue) eb09
					.getHippaCodeSelectedValues().get(0);
			hippaCode09Value = hippaCodeValue.getValue();
		}
		/**
		 * Checking if HSD01 value is null
		 */
		if (null != hsd01) {
			hippaCodeValue = (HippaCodeValue) hsd01
					.getHippaCodeSelectedValues().get(0);
			hippaCodeHsd01Value = hippaCodeValue.getValue();
		}
		/**
		 * Checking if HSD05 value is null
		 */
		if(null != hsd05){
			hippaCodeValue = (HippaCodeValue) hsd05
					.getHippaCodeSelectedValues().get(0);
			hippaCodeHsd05Value = hippaCodeValue.getValue();
		}
		// set the value to BLANK if the value is null or SPACE
		if (null != hippaCode06Value) {
			hippaCode06Value = hippaCode06Value.trim().toUpperCase();
		} else {
			hippaCode06Value = "";
		}
		if (null != hippaCode09Value) {
			hippaCode09Value = hippaCode09Value.trim().toUpperCase();
		} else {
			hippaCode09Value = "";
		}
		if (null != hippaCodeHsd01Value) {
			hippaCodeHsd01Value = hippaCodeHsd01Value.trim().toUpperCase();
		} else {
			hippaCodeHsd01Value = "";
		}
		if (null != hippaCodeHsd05Value) {
			hippaCodeHsd05Value = hippaCodeHsd05Value.trim().toUpperCase();
		} else {
			hippaCodeHsd05Value = "";
		}

		
		// EB09 and EB06 Validation
		if(!(DomainConstants.EMPTY).equalsIgnoreCase(hippaCode09Value)){
			
			if ((((hippaCode06Value.equalsIgnoreCase(DomainConstants.EB06_7)) || (hippaCode06Value
					.equalsIgnoreCase(DomainConstants.EB06_13))) && (hippaCode09Value
					.equalsIgnoreCase(DomainConstants.DY)))
					|| ((hippaCode06Value
							.equalsIgnoreCase(DomainConstants.EB06_6)) && (hippaCode09Value
							.equalsIgnoreCase(DomainConstants.HS)))
					|| ((hippaCode06Value
							.equalsIgnoreCase(DomainConstants.EB06_34)) && (hippaCode09Value
							.equalsIgnoreCase(DomainConstants.MN)))
					|| ((hippaCode06Value
							.equalsIgnoreCase(DomainConstants.EB06_27)) && (hippaCode09Value
							.equalsIgnoreCase(DomainConstants.VS)))
					|| (((hippaCode06Value
							.equalsIgnoreCase(DomainConstants.EB06_21))
							|| (hippaCode06Value
									.equalsIgnoreCase(DomainConstants.EB06_22))
							|| (hippaCode06Value
									.equalsIgnoreCase(DomainConstants.EB06_23)) || (hippaCode06Value
								.equalsIgnoreCase(DomainConstants.EMPTY))) && (hippaCode09Value
							.equalsIgnoreCase(DomainConstants.YY)))) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E020);
				errorDetailsObject.setErrorDesc(DomainConstants.E020_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			
			}
		}
		
		//HSD01 and HSD05 Validation
		if((!(DomainConstants.EMPTY).equalsIgnoreCase(hippaCodeHsd01Value)) && (!(DomainConstants.EMPTY).equalsIgnoreCase(hippaCodeHsd05Value))){
			
			if ((((hippaCodeHsd05Value.equalsIgnoreCase(DomainConstants.EB06_7)) || (hippaCodeHsd05Value
					.equalsIgnoreCase(DomainConstants.EB06_13))) && (hippaCodeHsd01Value
					.equalsIgnoreCase(DomainConstants.DY)))
					|| ((hippaCodeHsd05Value
							.equalsIgnoreCase(DomainConstants.EB06_6)) && (hippaCodeHsd01Value
							.equalsIgnoreCase(DomainConstants.HS)))
					|| ((hippaCodeHsd05Value
							.equalsIgnoreCase(DomainConstants.EB06_34)) && (hippaCodeHsd01Value
							.equalsIgnoreCase(DomainConstants.MN)))
					|| ((hippaCodeHsd05Value
							.equalsIgnoreCase(DomainConstants.EB06_27)) && (hippaCodeHsd01Value
							.equalsIgnoreCase(DomainConstants.VS)))) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E020);
				errorDetailsObject.setErrorDesc(DomainConstants.E020_DESCRIPTION_FOR_HSD);
				mapping.getErrorCodesList().add(errorDetailsObject);
			
			}
		}
		
		
	}
		
	
		

	/**
	 * This method checks condition for Error Code - E021
	 * 
	 * @param ContractMappingVO
	 *            mapping,String hippaCode01Value
	 * @return
	 */
	private void validateE021(ContractMappingVO mapping, String hippaCode01Value) {

		String spdpvavalue = mapping.getSpsId().getLinePVA();
		String format = mapping.getSpsId().getLineDataType();
		String spsvalue = mapping.getSpsId().getLineValue();
		int percentage = 0;
		boolean errorflag = false;

		if (null != mapping.getEb01()
				&& DomainConstants.EB01_A.equals(hippaCode01Value)) {
			if (null != format
					&& DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
				if (null != spsvalue && !"".equals(spsvalue)) {
					spsvalue = spsvalue.trim();
					try {
						percentage = Integer.parseInt(spsvalue);
					} catch (NumberFormatException e) {
						log.debug("Invalid linevalue value  format");
					}
				}

				if (null != spdpvavalue && !"".equals(spdpvavalue)) {
					spdpvavalue = spdpvavalue.toUpperCase();

					if (getNetworkPVAMappingsForE021group1().contains(
							spdpvavalue)) {
						if (percentage > 0 & percentage <= 40) {
							errorflag = true;
						}
					} else if (getNetworkPVAMappingsForE021group2().contains(
							spdpvavalue)) {
						if (percentage > 0 & percentage <= 15) {
							errorflag = true;
						}
					}
				}
			}
		}
		if (errorflag) {

			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E021);
			errorDetailsObject.setErrorDesc(DomainConstants.E021_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);

		}
	}

	/**
	 * The method validates E022 If EB01=A and EB06 coded as 21/22/23/32
	 * Error will not be reported for Benefits having  PENALTY in description 
	 * @param mapping
	 * @return
	 */
	private void validateE022(ContractMappingVO mapping) {
		if (null != mapping) {
			
			String spsDescription = null;
			boolean exclusionFlag = false;
			// Getting EB01,EB06 value
			HippaSegment hippaSegmentEB01 = mapping.getEb01();
			HippaSegment hippaSegmentEB06 = mapping.getEb06();
			String hippaCode01Value = null;
			String hippaCode06Value = null;
			if (null != hippaSegmentEB01) {
				HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSegmentEB01
						.getHippaCodeSelectedValues().get(0);
				hippaCode01Value = hippaCodeValue01.getValue();
			}
			if (null != hippaSegmentEB06) {
				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) hippaSegmentEB06
						.getHippaCodeSelectedValues().get(0);
				hippaCode06Value = hippaCodeValue06.getValue();

			}
			if (null != hippaCode06Value) {
				hippaCode06Value = hippaCode06Value.trim();
				if (null != mapping.getSpsId()) {
					if (null != mapping.getSpsId().getSpsDesc()
							&& !DomainConstants.EMPTY.equals(mapping.getSpsId().getSpsDesc())) {
						spsDescription = mapping.getSpsId().getSpsDesc().toUpperCase();
						if (-1 != spsDescription
								.indexOf(DomainConstants.SUBSTRING_PENALTY)) {
							exclusionFlag = true;
						}
					}
				}

				// EB01=B and EB06=21/22/23/32
				if (!exclusionFlag
						&& DomainConstants.EB01_B.equals(hippaCode01Value)
						&& (DomainConstants.EB06_21.equals(hippaCode06Value)
								|| DomainConstants.EB06_22
										.equals(hippaCode06Value)
								|| DomainConstants.EB06_23
										.equals(hippaCode06Value) || DomainConstants.EB06_32
								.equals(hippaCode06Value))) {
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E022);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E022_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}
		}
	}

	/**
	 * The method validates E023 If EB06=22 and adminoption coded=Calendar Year
	 * If EB06=23 and adminoption coded=Benefit Year
	 * 
	 * @param mapping
	 *            , contract
	 * @return
	 */
	private void validateE023(ContractMappingVO mapping, ContractVO contract) {
		if (null != mapping) {	
			// Getting EB06 value
			HippaSegment hippaSegmentEB06 = mapping.getEb06();
			String hippaCode06Value = null;
			if (null != hippaSegmentEB06) {
				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) hippaSegmentEB06
						.getHippaCodeSelectedValues().get(0);
				hippaCode06Value = hippaCodeValue06.getValue();
				if (DomainConstants.EB06_22.equals(hippaCode06Value)
						&& DomainConstants.CALENDARYEAR.equals(contract
								.getAnswerCalYearOrBenYear())
						|| DomainConstants.EB06_23.equals(hippaCode06Value)
						&& DomainConstants.BENEFITYEAR.equals(contract
								.getAnswerCalYearOrBenYear())) {
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E023);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E023_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}
		}
	}

	// method added for validation of E026
	/**
	 * This method checks condition for Error Code - E026 the error is reported
	 * 
	 * If EB01=B & EB06=26 & EB03 = 98 / 99 / 68 / 80 / 81 / 82 / 83 / BH /
	 * ‘SS/SP’ / UC / 33 / AD / AE / AF / 6 / 48 / 69 / 65 /  AI /
	 * 50 / 13 / 53 / 52 / 51 / 86 /  61 / 62 / 76 / 78 / 84 / 93 / A0 / A3
	 * / AG / BG / 12 / 18 / 45/BY/BZ
	 * 
	 * If EB01=B & EB06=27 & EB03 = 48 /  65 / AG
	 * 
	 * If EB01=B & EB06=36 & EB03 = 50 / 52 / 51 / 86 / 12 / 18 / 62 / 4 /
	 * 5 / 73/BY/BZ/CF/CH/CJ/CO
	 * 
	 * If EB01=B & EB06=7 & EB03 = 50 / 52 / 51 / 86 / 62 / 4 / 5 / 73 /BY/BZ/BT/BU/CO
	 * 
	 * @param minorHeadingObj
	 *            , mapping
	 * @return
	 */
	private void validateE026(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mapping) {
		boolean hippacodeEB03forE026 = false;
		// Checking if EB03 is null
		if (null != minorHeadingObj.getRuleMapping()) {
			// Checking if EB06 is null
			if (null != mapping.getEb06()) {
				String hippaCode01Value = "";
				if (null != mapping.getEb01()) {
					HippaSegment hippaSEgmentEB01 = mapping.getEb01();
					HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSEgmentEB01
							.getHippaCodeSelectedValues().get(0);
					hippaCode01Value = hippaCodeValue01.getValue();
				}

				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) mapping
						.getEb06().getHippaCodeSelectedValues().get(0);
				String hippaCode06Value = hippaCodeValue06.getValue();
				// Checking if EB01 = B
				if (DomainConstants.COPAYMENT.equals(hippaCode01Value)) {
					// Check if EB06=26
					if (DomainConstants.EB06_26.equals(hippaCode06Value)) {
						// Check for EB03 values
						if (null != minorHeadingObj.getRuleMapping().getEb03()) {
							List eb03ValuesList = minorHeadingObj
									.getRuleMapping().getEb03()
									.getHippaCodeSelectedValues();
							Iterator eb03Iterator = eb03ValuesList.iterator();

							while (eb03Iterator.hasNext()) {
								HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
										.next();
								String hippaCodeValue = hippaCodeValue03
										.getValue();
								if (getEB03ForE026_1().contains(hippaCodeValue)) {
									hippacodeEB03forE026 = true;
									break;
								}
							}
						}

					} else if (DomainConstants.EB06_27.equals(hippaCode06Value)) {
						// Check if EB06=27
						// Check for EB03 values
						if (null != minorHeadingObj.getRuleMapping().getEb03()) {
							List eb03ValuesList = minorHeadingObj
									.getRuleMapping().getEb03()
									.getHippaCodeSelectedValues();
							Iterator eb03Iterator = eb03ValuesList.iterator();

							while (eb03Iterator.hasNext()) {
								HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
										.next();
								String hippaCodeValue = hippaCodeValue03
										.getValue();
								if (getEB03ForE026_2().contains(hippaCodeValue)) {
									hippacodeEB03forE026 = true;
									break;
								}
							}
						}

					} else if (DomainConstants.EB06_36.equals(hippaCode06Value)) {
						// Check if EB06=36
						// Check for EB03 values
						if (null != minorHeadingObj.getRuleMapping().getEb03()) {
							List eb03ValuesList = minorHeadingObj
									.getRuleMapping().getEb03()
									.getHippaCodeSelectedValues();
							Iterator eb03Iterator = eb03ValuesList.iterator();

							while (eb03Iterator.hasNext()) {
								HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
										.next();
								String hippaCodeValue = hippaCodeValue03
										.getValue();
								if (getEB03ForE026_3().contains(hippaCodeValue)) {
									hippacodeEB03forE026 = true;
									break;
								}
							}
						}

					} else if (DomainConstants.EB06_7.equals(hippaCode06Value)) {
						// Check if EB06=7
						// Check for EB03 values
						if (null != minorHeadingObj.getRuleMapping().getEb03()) {
							List eb03ValuesList = minorHeadingObj
									.getRuleMapping().getEb03()
									.getHippaCodeSelectedValues();
							Iterator eb03Iterator = eb03ValuesList.iterator();

							while (eb03Iterator.hasNext()) {
								HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
										.next();
								String hippaCodeValue = hippaCodeValue03
										.getValue();
								if (getEB03ForE026_4().contains(hippaCodeValue)) {
									hippacodeEB03forE026 = true;
									break;
								}
							}
						}

					}

					if (hippacodeEB03forE026) {
						/**
						 * Object to hold error details variable Level
						 */
						ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
						errorDetailsObject.setError(true);
						errorDetailsObject
								.setErrorCode(DomainConstants.ERROR_E026);
						errorDetailsObject
								.setErrorDesc(DomainConstants.E026_DESCRIPTION);
						mapping.getErrorCodesList().add(errorDetailsObject);
					}

				}
			}
		}
	}

	/**
	 * The method generates E025 error if there no bx mapping and the data type
	 * format falls in "e025.data.type.format" in errorValidator.properties file
	 * against the SPS Id.
	 * 
	 * @param minorHeadingObj
	 * @return
	 */
	private void validateE025(MinorHeadingsVO minorHeadingObj, String keyMinor,
			List headerRuleErrorCodeExclusionsList) {

		if (isRuleIdNotEmpty(minorHeadingObj.getRuleMapping()) 
				&& !isRuleMappingNotApplicable(minorHeadingObj.getRuleMapping())) {
			String ruleId = minorHeadingObj.getRuleMapping().getRule().getHeaderRuleId();

			String minorHeadingDesc = minorHeadingObj.getDescriptionText() != null ? 
					minorHeadingObj.getDescriptionText().toUpperCase() : "";
			// change in E025 implementation as part of Aug Release
			if ((minorHeadingDesc.indexOf(
					DomainConstants.DIABETES) == -1)
					&& (minorHeadingDesc.indexOf(
							DomainConstants.DIABETIC) == -1)) {
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E025);
				errorDetailsObject
				.setErrorDesc(DomainConstants.E025_DESCRIPTION_1 + ruleId + DomainConstants.E025_DESCRIPTION_2);
				minorHeadingObj.getErrorCodesList().add(errorDetailsObject);
			}
			// End:Aug Release change

			HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
			Iterator iteratorMappings = mappingsMap.entrySet().iterator();
			while (iteratorMappings.hasNext()) {
				ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
						.next()).getValue();
				if (null != mappingObj
						&& null != mappingObj.getSpsId()
						&& isErrorToBeExcludedSPSLevel(errorExclusionDetailVO
								.isE025ExclusionValidationEnableFlag(),
								DomainConstants.ERROR_E025,
								errorExclusionDetailVO.getSpsExclusionsList(),
								mappingObj.getSpsId().getSpsId(),
								headerRuleErrorCodeExclusionsList)) {
					// Check for E036 error and exclude
					if (!checkForE036ErrorCondition(minorHeadingObj)) {
						validateE025(mappingObj, keyMinor);
					}
				}
			}
		}
	}
	private boolean isRuleIdNotEmpty(Mapping ruleMapping) {
		boolean isRuleIdNotEmpty = false;
		if (null != ruleMapping && null != ruleMapping.getRule() 
				&& null != ruleMapping.getRule().getHeaderRuleId() 
				&& ruleMapping.getRule().getHeaderRuleId().trim().length() != 0) {
			isRuleIdNotEmpty = true;
		}		
		return isRuleIdNotEmpty;
	}

	private boolean isRuleMappingNotApplicable(Mapping ruleMapping) {
		boolean isRuleMappingNotApplicable = false;
		if (null != ruleMapping && null != ruleMapping.getVariableMappingStatus() 
				&& DomainConstants.STATUS_NOT_APPLICABLE.equals(ruleMapping.getVariableMappingStatus())) {
			isRuleMappingNotApplicable = true;
		}		
		return isRuleMappingNotApplicable;
	}
	/**
	 * The method generates E025 error if there no bx mapping and the data type
	 * format falls in "e025.data.type.format" in errorValidator.properties file
	 * against the SPS Id.
	 * 
	 * @param mappingObj
	 * @param keyMinor
	 */
	private void validateE025(ContractMappingVO mappingObj, String keyMinor) {
		boolean exclusionFlagE025 = false;
		String spsFormat = "";
		// E025 need to be reported if E029 or E036 error is identified
		// Check for E029 error and exclude
		List <EB03Association> eb03Associations  = new ArrayList<EB03Association>();
		if (null != mappingObj) {
			if (null != mappingObj.getEb03()) {
				eb03Associations = mappingObj.getEb03().getEb03Association();
				
				if (null != eb03Associations){
					for(EB03Association eb03Association:eb03Associations){
						
						if (ContractMappingValidator.checkForRuleE029(eb03Association.getMessage())) {
							exclusionFlagE025 = true;
						}
						
					}
				}
			}			
		}
		
		if (null != mappingObj && null != mappingObj.getSpsId()) {
			if (null != mappingObj.getSpsId().getLineDataType()) {
				spsFormat = mappingObj.getSpsId().getLineDataType().trim();
				if (DomainConstants.FORMAT_DOLLAR.equalsIgnoreCase(spsFormat)) {
					String lineValue = mappingObj.getSpsId().getLineValue();
					if (null != lineValue) {
						lineValue = lineValue.trim();
						if (BxUtil.isNumeric(lineValue)) {
							if (50000 <= Double.parseDouble(lineValue)) {
								exclusionFlagE025 = true;
							}
						} else {
							log.error("The SPS line value should be numeric for format Dollar. But the value obtained : "
									+ lineValue);
						}
					}
				}
			}
		}
		if (!exclusionFlagE025) {
			// Conditions where E025 to be excluded.
			if (DomainConstants.GENERAL_BENEFIT_ADMINISTRATION
					.equalsIgnoreCase(keyMinor)) {
				if (DomainConstants.OCRS.equalsIgnoreCase(spsFormat)
						|| DomainConstants.OCC.equalsIgnoreCase(spsFormat)) {
					exclusionFlagE025 = true;
				}
			} else if (DomainConstants.ELIGIBILITY_E025.equalsIgnoreCase(keyMinor)) {
				if (DomainConstants.AGE.equalsIgnoreCase(spsFormat)
						|| DomainConstants.YRS.equalsIgnoreCase(spsFormat)
						|| DomainConstants.MTH.equalsIgnoreCase(spsFormat)
						|| DomainConstants.MTHS.equalsIgnoreCase(spsFormat)) {
					exclusionFlagE025 = true;
				}
			} else if (DomainConstants.CONTRACT_ADMINISTRATION
					.equalsIgnoreCase(keyMinor)) {
				if (DomainConstants.MTH.equalsIgnoreCase(spsFormat)
						|| DomainConstants.MTHS.equalsIgnoreCase(spsFormat)) {
					exclusionFlagE025 = true;
				}
			}
		}

		if (null !=mappingObj && !"NOT_APPLICABLE".equals(mappingObj.getVariableMappingStatus())) {
			if (!mappingObj.isMapped()
					&& getDataTypeFormatForE025().contains(spsFormat)
					&& !exclusionFlagE025) {
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E025);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E025_DESCRIPTION);
				mappingObj.getErrorCodesList().add(errorDetailsObject);
			}
		}
	}

	/**
	 * Error Validation for E029. Includes - The rule shall be identified for
	 * messages with substring ‘worldwide’ or ‘WW’ in the message text.
	 * 
	 * @param mapping
	 * @return
	 */
	private void validateE029(ContractMappingVO mapping) {


		List <EB03Association> eb03Associations  = new ArrayList<EB03Association>();

		if (null != mapping) {

			if (!DomainConstants.NOT_APPLICABLE_STATUS.equals(mapping.getVariableMappingStatus())){

				if(null != mapping.getEb03()){					
					eb03Associations = mapping.getEb03().getEb03Association();
				}

				if(null != eb03Associations){

					for(EB03Association eb03Association:eb03Associations){

						if(ContractMappingValidator.checkForRuleE029(eb03Association.getMessage())){							

							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject.setAssociatedEb03(eb03Association.getEb03().getValue());
							errorDetailsObject.setErrorCode(DomainConstants.ERROR_E029);
							errorDetailsObject
							.setErrorDesc(DomainConstants.E029_DESCRIPTION);
							mapping.getErrorCodesList().add(errorDetailsObject);
						}
					}

				}			

			}

		}
	}

	
	/**
	 * This method checks condition for Error Code - E032 If EB01 = 'G', and
	 * EB03 not in 30/60/88/A7/A8/48, E032 is thrown
	 * 
	 * @param MinorHeadingsVO
	 *            ,ContractMappingVO
	 * @return
	 */
	private void validateE030(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mapping) {
		if (null != mapping && !"NOT_APPLICABLE".equals(mapping.getVariableMappingStatus())) {
			if (null != minorHeadingObj && null != mapping) {
				if (null != mapping.getEb01()
						&& null != minorHeadingObj.getRuleMapping().getEb03()) {
					HippaSegment hippaSEgmentEB01 = mapping.getEb01();
					HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSEgmentEB01
							.getHippaCodeSelectedValues().get(0);
					if (null != hippaCodeValue01.getValue()
							&& !DomainConstants.EMPTY.equals(hippaCodeValue01
									.getValue())) {
						boolean hasE030Error = false;
						// Checking if EB01 = G
						if (DomainConstants.OUT_OF_POCKET.equals(hippaCodeValue01
								.getValue())) {
							hasE030Error = false;
							List eb03ValuesList = minorHeadingObj.getRuleMapping()
									.getEb03().getHippaCodeSelectedValues();
							Iterator eb03Iterator = eb03ValuesList.iterator();
							while (eb03Iterator.hasNext()) {
								HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
										.next();
								String hippaCodeValue = hippaCodeValue03.getValue();
								if (!SimulationResourceValueLoader.getEB03ForE030()
										.contains(hippaCodeValue)) {
									hasE030Error = true;
									break;
								}
							}

						}
						if (hasE030Error) {
							/**
							 * Object to hold error details variable Level
							 */
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject
									.setErrorCode(DomainConstants.ERROR_E030);
							errorDetailsObject
									.setErrorDesc(DomainConstants.E030_DESCRIPTION);
							mapping.getErrorCodesList().add(errorDetailsObject);
						}
					}
				}
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E031 
	 * If any of the below condition satisfies, Error E031 is generated
	 * 
	 * The error code E031 is reported when at least one  accum sps id  mapped to the sps id 
	 * and if the EB06 and lookup Indicator of the mapped  accumulator satisfy the following condition. 
	 * EB06 = 32 and Look Up Indicator not equal to  LT
	 * EB06 = 22 and Look Up Indicator not equal to  TB/BY/CT
	 * EB06 = 23 Look Up Indicator not equal to TB/CY/CT
	 * EB06 = 25 or null  and Look Up Indicator not equal to TB/CY/CT/BY
	 * 
	 * @param ContractMappingVO
	 *            , ContractVO, accumIndList
	 * 
	 * @return mapping
	 */
	private void validateE031(ContractMappingVO mapping,
			ContractVO contract, List accumIndList) {
		Map spsCodedValueMap = contract.getSpsCodedValue();
		String accumSpsId = null;
		if (null != mapping && null!=accumIndList && !accumIndList.isEmpty()) {
			String accumValue = null;
			String eb06_Value = null;
			HippaSegment eb06 = mapping.getEb06();
			StringBuffer E031AccumValue = new StringBuffer();
			if (null != eb06) {
				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) eb06
				.getHippaCodeSelectedValues().get(0);
				if(null!=hippaCodeValue06.getValue()){
				 eb06_Value = hippaCodeValue06.getValue().trim();
				}else{
					eb06_Value = DomainConstants.EB06_BLANK;
				}
			} else {
				eb06_Value = DomainConstants.EB06_BLANK;
			}
				HippaSegment accum = mapping.getAccum();


				/**
				 * Checking if EB06 = 22, 23, 25, 32 or null and no accumulator
				 * mapped
				 */
				if ((SimulationResourceValueLoader.getEB06ForE031()
						.contains(eb06_Value) || DomainConstants.EB06_BLANK.equals(eb06_Value))
						&& null != accum) {
					List accumSelectedValues = accum.getHippaCodeSelectedValues();
					for (int j = 0; j < accumSelectedValues.size(); j++) {
						HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accumSelectedValues.get(j);
						accumSpsId = hippaCodeValueAccum.getValue();
						Set accumValueList =  (Set) spsCodedValueMap.get(accumSpsId);
						if (null != accumValueList) {
							Iterator accumValueListItr = accumValueList.iterator();
							while (accumValueListItr.hasNext()) {
								accumValue = (String) accumValueListItr.next();
								if (checkForRuleE031(eb06_Value,
										accumValue, accumIndList)) {
									if (E031AccumValue.length() == 0) {
										E031AccumValue.append(accumValue);
									} else if (E031AccumValue.indexOf(accumValue) == -1) {
										E031AccumValue.append(", ").append(accumValue);
									}
								}
							}
						}
					}
					if (!"NOT_APPLICABLE".equals(mapping.getVariableMappingStatus())) {
						if (E031AccumValue.length() != 0 && mapping.isMapped()) {
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject.setErrorCode(DomainConstants.ERROR_E031);
							errorDetailsObject
									.setErrorDesc(DomainConstants.E031_DESCRIPTION_START
											+ E031AccumValue
											+ DomainConstants.E031_DESCRIPTION_END);
							mapping.getErrorCodesList().add(errorDetailsObject);
						}
						}
					}
				}
			}
	/**
	 * This method checks condition for Error Code - E032
	 * If any of the below condition satisfies, Error E032 is generated
	 * 
	 * The error code E032 is reported when at least one  accum sps id is mapped to the sps id
	 * and contract data type not null and satisfies any of the below condition.
	 * Data type = DAY/DAYS and Days Flag = ‘N’ 
	 * Data type = VST/OCRS and Occurs Flag = ‘N’ 
	 * Data type = DOL and Monies Flag = ‘N’ 
	 * 
	 * @param ContractMappingVO
	 *            , ContractVO, accumIndList
	 * 
	 * @return mapping
	 */
	private void validateE032(ContractMappingVO mapping,
			ContractVO contract, List accumIndList) {
		Map spsCodedValueMap = contract.getSpsCodedValue();
		if (null != mapping && null!=accumIndList && !accumIndList.isEmpty()) {
			String accumValue = null;
			StringBuffer E032AccumValue = new StringBuffer();

			if (null != mapping.getSpsId().getLineDataType()
					&& SimulationResourceValueLoader.getVariableFormatForE032()
					.contains(mapping.getSpsId().getLineDataType())) {
				// Checking list is null
				HippaSegment accum = mapping.getAccum();
				if (null != accum) {
					List accumSelectedValues = accum.getHippaCodeSelectedValues();
					for (int j = 0; j < accumSelectedValues.size(); j++) {
					HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accumSelectedValues.get(j);
						String accumSpsId = hippaCodeValueAccum.getValue();
						Set accumValueList =  (Set) spsCodedValueMap.get(accumSpsId);
						if(null!=accumValueList){
							Iterator accumValueListItr = accumValueList.iterator();
							while (accumValueListItr.hasNext()) {
								accumValue = (String) accumValueListItr.next();
								if (checkForRuleE032(mapping
										.getSpsId().getLineDataType(), accumValue,
										accumIndList)) {
									if (E032AccumValue.length() == 0) {
										E032AccumValue.append(accumValue);
									} else if (E032AccumValue.indexOf(accumValue) == -1) {
										E032AccumValue.append(", ").append(accumValue);
									}
								}
							}
						}
					}
					if (!"NOT_APPLICABLE".equals(mapping.getVariableMappingStatus())) {
						if (E032AccumValue.length() != 0 && mapping.isMapped()) {
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject
							.setErrorCode(DomainConstants.ERROR_E032);
							errorDetailsObject
							.setErrorDesc( DomainConstants.E032_DESCRIPTION_START
									+ E032AccumValue
									+ DomainConstants.E032_DESCRIPTION_END);
							mapping.getErrorCodesList().add(errorDetailsObject);
						}
						}
				}	
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E033
	 * If any of the below condition satisfies, Error E033 is generated
	 * 
	 * The error code E033 is reported when at least one  accum sps id is mapped to the sps id
	 * and EB02 is not null and satisfies any of the below condition.
	 * 
	 * EB02= IND and ROOT-MBR-CODE = FAC
	 * EB02= FAM and ROOT-MBR-CODE = MAC
	 * 
	 * @param ContractMappingVO
	 *            , ContractVO, accumIndList
	 * 
	 * @return mapping
	 */
	private void validateE033(ContractMappingVO mapping,
			ContractVO contract, List accumIndList) {
		Map spsCodedValueMap = contract.getSpsCodedValue();
		if (null != mapping && null!=accumIndList && !accumIndList.isEmpty()) {
			HippaSegment eb02 = mapping.getEb02();
			String accumValue = null;
			StringBuffer E033AccumValue = new StringBuffer();
			/**
			 * Checking if EB02 value is null
			 */
			if (null != mapping.getEb02()) {
				HippaCodeValue hippaCodeValue02 = (HippaCodeValue) eb02
				.getHippaCodeSelectedValues().get(0);
				String eb02_Value = hippaCodeValue02.getValue();
				HippaSegment accum = mapping.getAccum();

				if (null != accum) {
					List accumSelectedValues = accum.getHippaCodeSelectedValues();
					for(int j=0;j<accumSelectedValues.size();j++){
					HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accumSelectedValues.get(j);
						String accumSpsId = hippaCodeValueAccum.getValue();
						Set accumValueList =  (Set) spsCodedValueMap.get(accumSpsId);
						if(null!=accumValueList){
							Iterator accumValueListItr = accumValueList.iterator();
							while (accumValueListItr.hasNext()) {
								accumValue = (String) accumValueListItr.next();
								if (checkForRuleE033(eb02_Value,
										accumValue, accumIndList)) {
									if (E033AccumValue.length() == 0) {
										E033AccumValue.append(accumValue);
									} else if (E033AccumValue.indexOf(accumValue) == -1) {
										E033AccumValue.append(", ").append(accumValue);
									}
								}
							}
						}
					}
					if (!"NOT_APPLICABLE".equals(mapping.getVariableMappingStatus())) {
						if (E033AccumValue.length() != 0 && mapping.isMapped()) {
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject
							.setErrorCode(DomainConstants.ERROR_E033);
							errorDetailsObject
							.setErrorDesc(DomainConstants.E033_DESCRIPTION_START
									+ E033AccumValue
									+ DomainConstants.E033_DESCRIPTION_END);
							mapping.getErrorCodesList().add(errorDetailsObject);
						}
						}
				}
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E036 If Header rule is
	 * mapped and not in 'NOT APPLICABLE' status & Benefit name contains
	 * DIABETES/DIABETIC
	 * 
	 * @param MinorHeadingsVO
	 *            ,ContractMappingVO
	 * @return
	 */
	private void validateE036(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mappingObj) {
		
		if (!DomainConstants.NOT_APPLICABLE_STATUS.equals(mappingObj.getVariableMappingStatus())
				&& checkForE036ErrorCondition(minorHeadingObj)) {
			/**
			 * Object to hold error details variable Level
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E036);
			errorDetailsObject.setErrorDesc(DomainConstants.E036_DESCRIPTION);
			mappingObj.getErrorCodesList().add(errorDetailsObject);
		}
	}

	/**
	 * /**
	 * 
	 * @param headerRuleID
	 * @param errorExclusionDetailVO
	 * @return Header rules based validations.
	 */
	private List getHeaderRuleBasedErrorCodes(String headerRuleID,
			ErrorExclusionDetailVO errorExclusionDetailVO) {
		List headerRuleErrorCodesList = new ArrayList();
		List headerRulesList = errorExclusionDetailVO
				.getHeaderRuleExclusionsList();
		if (null != headerRulesList && headerRulesList.size() > 0) {
			for (int i = 0; i < headerRulesList.size(); i++) {
				ErrorExclusionVO errorExclusionVO = (ErrorExclusionVO) headerRulesList
						.get(i);
				String headerRuleComaSeparatedPrimary="";
				String headerRuleComaSeparatedSecondary="";
				if(null != errorExclusionVO){
					headerRuleComaSeparatedPrimary = ReferenceDataUtil
					.trimAndNullToEmptyString(errorExclusionVO
							.getPrimaryValues());
					headerRuleComaSeparatedSecondary = ReferenceDataUtil
					.trimAndNullToEmptyString(errorExclusionVO
							.getSecondaryValues());
				}
				if (null != errorExclusionVO) {
					if (headerRuleComaSeparatedPrimary.indexOf(headerRuleID
							.toUpperCase()) != -1
							|| headerRuleComaSeparatedSecondary
									.indexOf(headerRuleID.toUpperCase()) != -1) {
						headerRuleErrorCodesList.add(errorExclusionVO
								.getErrorCode());
					}
				}
			}
		}
		return headerRuleErrorCodesList;
	}

	/**
	 * THis method checks whether the error code is in the exclusion list If
	 * Yes, then sets the flag isErrorTobeExcluded as true.
	 * 
	 * @param List
	 *            , String, boolean, String
	 * @return boolean
	 */
	private boolean isErrorToBeExcludedSPSLevel(boolean errorFlag,
			String errorCode, List spsList, String spsId, List headerRuleList) {
		if (errorFlag) {
			if (headerRuleList.contains(errorCode)) {
				return false;
			}
			if (null != spsList && spsList.size() > 0) {
				Iterator itr = spsList.iterator();
				ErrorExclusionVO errorExclusionVO = null;
				if (null != spsId && !spsId.equals(DomainConstants.EMPTY)) {
					while (itr.hasNext()) {
						errorExclusionVO = (ErrorExclusionVO) itr.next();
						String spsComaSeparatedPrimary = ReferenceDataUtil
								.trimAndNullToEmptyString(errorExclusionVO
										.getPrimaryValues());
						String spsComaSeparatedSecondary = ReferenceDataUtil
								.trimAndNullToEmptyString(errorExclusionVO
										.getSecondaryValues());
						if (errorExclusionVO.getErrorCode().equals(errorCode)
								&& (spsComaSeparatedPrimary.indexOf(spsId
										.toUpperCase()) != -1 || spsComaSeparatedSecondary
										.indexOf(spsId.toUpperCase()) != -1)) {
							return false;
						}

					}
				}
			}
		}
		return errorFlag;
	}

	/**
	 * Method to set benefitCoveredFlag in a minor heading
     * based on the answer of 'BENEFIT COVERED' question  
	 * @param MinorHeadingsVO
	 * @return MinorHeadingsVO
	 */
	private MinorHeadingsVO setBenefitCoveredFlag(
			MinorHeadingsVO minorHeadingObj) {
		if (null != minorHeadingObj) {
			boolean benefitCovered = false;
			// get Mappings Map
			HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
			Iterator iteratorMappings = mappingsMap.entrySet().iterator();
			while (iteratorMappings.hasNext()) {
				// adding the ErrorCodes to the Variable
				// object
				ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
						.next()).getValue();
				if (null != mappingObj && null != mappingObj.getSpsId()) {
					SPSId spsIdObj = mappingObj.getSpsId();
					if (null != spsIdObj.getSpsDesc() && 
							DomainConstants.BENEFITCOVERED.equals(spsIdObj
							.getSpsDesc())) {
						if (DomainConstants.Y.equals(spsIdObj.getLineValue())) {
							benefitCovered = true;
							break;
						}
					}
				}
			}
			minorHeadingObj.setFlagBenefitCovered(benefitCovered);
		}
		return minorHeadingObj;
	}
	/**
	 * Method to identify E036 error condition.
	 * @param minorHeadingObj
	 * @return boolean
	 */
	private boolean checkForE036ErrorCondition(MinorHeadingsVO minorHeadingObj) {
		boolean errorFlag = false;
		String minorHeadingDesc = minorHeadingObj.getDescriptionText() != null ? 
				minorHeadingObj.getDescriptionText().toUpperCase() : "";
		if ((-1 != minorHeadingDesc.indexOf(DomainConstants.DIABETES))
				|| (-1 != minorHeadingDesc.indexOf(DomainConstants.DIABETIC))) {
			errorFlag = true;
		}
		return errorFlag;
	}
	
	/**
	 * This function will modify the ContractMappingVO object as per the below conditions.
	 * 1. If EB01=B and format=PCT, over-write EB01=A and EB06 as blank.
	 * 2. For an SPS Id if EB01=B and EB06 has no value, 
	 * 		a. If the same header rule has SPS IDs '0157' or '4830' with a value 
	 * 		   '2', '10', '21', '26', '31', '36', '41' or '46' then over write EB06 as VISIT(27)
	 * 		b. If the same header rule has SPS IDs '0157' or '4830' with a value 
	 * 		   '4', '7', '13', '23', '28', '34', '38', '43', '49' then over write EB06 as ADMISSION (36)
	 * 		c. If the same header rule has SPS IDs '0157' or '4830' with a value '18' then over write EB06 as DAY(7)
	 * 3. 	For an SPS Id if EB01 IS NOT B and EB06 has no value,
	 * 		 a. If the value coded in Contract Administration is BY, then over write EB06 as 22 (Service year)
	 * 		 b. If the value coded in Contract Administration is CY, then over write EB06 as 23 (Calendar year)	
	
	 * @param mappingObj
	 * @param minorHeadingObj 
	 * @param contract
	 * @return
	 */
private ContractMappingVO autoPopulateEB06(ContractMappingVO mappingObj, MinorHeadingsVO minorHeadingObj, ContractVO contract) {
		
		String hippaCode01Value = "";
		String hippaCode06Value = "";
		String spsFormat = "";
		String spsCodedValue = "";
		boolean eb06VisitFlag = false;
		boolean eb06AdmissionFlag = false;
		boolean eb06DayFlag = false;
		HippaCodeValue hippaCodeValue01 = null;
		
		HashMap adminMethodSpsMap = (HashMap) minorHeadingObj.getAdminMethodSPS();
		if (null != adminMethodSpsMap &&  !adminMethodSpsMap.isEmpty()) {
			if (adminMethodSpsMap.containsKey("157")) {
				spsCodedValue = (String)adminMethodSpsMap.get("157");
			}
			else if (adminMethodSpsMap.containsKey("4830")) {
				spsCodedValue = (String)adminMethodSpsMap.get("4830");
			}
			if (null != spsCodedValue) {
				if (getSPSValueForEB6AutoPopulateToVisit().contains(spsCodedValue)) {
					eb06VisitFlag = true;
				} else if (getSPSValueForEB6AutoPopulateToAdmission().contains(spsCodedValue)) {
					eb06AdmissionFlag = true;
				} else if (getSPSValueForEB6AutoPopulateToDay().contains(spsCodedValue)) {
					eb06DayFlag = true;
				}
			}
		}

		HippaSegment hippaSegmentEB01 = mappingObj.getEb01();
		if (null != hippaSegmentEB01) {
			if (null != hippaSegmentEB01.getHippaCodeSelectedValues()
					&& hippaSegmentEB01.getHippaCodeSelectedValues().size() > 0) {
				 hippaCodeValue01 = (HippaCodeValue) hippaSegmentEB01
						.getHippaCodeSelectedValues().get(0);
				hippaCode01Value = hippaCodeValue01.getValue().trim();
			}

		}
		HippaSegment hippaSegmentEB06 = mappingObj.getEb06();
		if (null != hippaSegmentEB06) {
			if (null != hippaSegmentEB06.getHippaCodeSelectedValues()
					&& hippaSegmentEB06.getHippaCodeSelectedValues().size() > 0) {
				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) hippaSegmentEB06
						.getHippaCodeSelectedValues().get(0);
				hippaCode06Value = hippaCodeValue06.getValue().trim();
			}
		}
		if (null != mappingObj.getSpsId() && null != mappingObj.getSpsId().getLineDataType()) {
			spsFormat = mappingObj.getSpsId().getLineDataType().trim();
		}
		
		if (DomainConstants.EB01_B.equals(hippaCode01Value)) {
			if(DomainConstants.PERCENTAGE_FORMAT.equals(spsFormat)) {
				this.updateEb01Value(mappingObj, DomainConstants.EB01_A);
				this.updateEb06Value(mappingObj, DomainConstants.EB06_BLANK);
			} else {
				if (DomainConstants.EB06_BLANK.equals(hippaCode06Value)) {
					if(eb06VisitFlag) {
						this.updateEb06Value(mappingObj, DomainConstants.EB06_27);
					} else if (eb06AdmissionFlag) {
						this.updateEb06Value(mappingObj, DomainConstants.EB06_36);
					} else if (eb06DayFlag) {
						this.updateEb06Value(mappingObj, DomainConstants.EB06_7);
					}
				}
			}
		} else {
			if (DomainConstants.EB06_BLANK.equals(hippaCode06Value)) {
				if (null != contract.getAnswerCalYearOrBenYear()) {
					if(DomainConstants.BENEFITYEAR.equalsIgnoreCase(contract.getAnswerCalYearOrBenYear().trim())) {
						this.updateEb06Value(mappingObj, DomainConstants.EB06_22);
					} else if (DomainConstants.CALENDARYEAR.equalsIgnoreCase(contract.getAnswerCalYearOrBenYear().trim())) {
						this.updateEb06Value(mappingObj, DomainConstants.EB06_23);
					}
				}
			}
		}
		return mappingObj;
	}
	/**
	 * This method is to over write the EB01 value.
	 * @param mappingObj
	 * @param eb01
	 */
	private void updateEb01Value(ContractMappingVO mappingObj, String eb01) {
		if (null != mappingObj.getEb01() && null != mappingObj.getEb01().getHippaCodeSelectedValues() 
				&& !mappingObj.getEb01().getHippaCodeSelectedValues().isEmpty()) {
			HippaCodeValue hippaCodeValue = (HippaCodeValue)mappingObj.getEb01().getHippaCodeSelectedValues().get(0);
			hippaCodeValue.setValue(eb01);
		}
	}
	/**
	 * This method is to over write the EB06 value.
	 * @param mappingObj
	 * @param eb06
	 */
	private void updateEb06Value(ContractMappingVO mappingObj, String eb06) {
		if (null != mappingObj.getEb06() && null != mappingObj.getEb06().getHippaCodeSelectedValues() 
				&& !mappingObj.getEb06().getHippaCodeSelectedValues().isEmpty()) {
			HippaCodeValue hippaCodeValue = (HippaCodeValue)mappingObj.getEb06().getHippaCodeSelectedValues().get(0);
			hippaCodeValue.setValue(eb06);
		} else {
			HippaSegment hippaSegment = BxUtil.createHippaSegment(DomainConstants.EB06_NAME, eb06);
			mappingObj.setEb06(hippaSegment);
		}
	}
	
	/***************************SSCR 14181 April 2012 Release  START**********************************/
	/**
	 * The method returns the flag for the Error code E038 EB03=AL for vision Benefits
	 * @param mapping
	 * @return boolean
	 */
	private boolean isEB03HasAL(MinorHeadingsVO minorHeadingsVO) {
		boolean isEB03AL = false;
		if(minorHeadingsVO.getRuleMapping().isMapped()) {
			HippaSegment hippaSegmentEB03 = minorHeadingsVO.getRuleMapping().getEb03();
			/**
			 * Checking if EB03 is null
			 */
			if (null != hippaSegmentEB03) {
				List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
				if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
					Iterator eb03Iterator = eb03ValuesList.iterator();

					while (eb03Iterator.hasNext()) {
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
								.next();

						if (DomainConstants.EB03_AL.equals(hippaCodeValue03
								.getValue())) {
							isEB03AL = true; 
							break;
						}
					}
				}
			}
		}
		return isEB03AL;
	}
	
	/**
	 * The method returns the flag for the Error code E039 EB03=35 for dental Benefits
	 * @param mapping
	 * @return boolean
	 */
	private boolean isEB03Has35(MinorHeadingsVO minorHeadingsVO) {
		boolean isEB0335 = false;
		if(minorHeadingsVO.getRuleMapping().isMapped()) {
			HippaSegment hippaSegmentEB03 = minorHeadingsVO.getRuleMapping().getEb03();
			/**
			 * Checking if EB03 is null
			 */
			if (null != hippaSegmentEB03) {
				List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
				if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
					Iterator eb03Iterator = eb03ValuesList.iterator();

					while (eb03Iterator.hasNext()) {
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
								.next();

						if (DomainConstants.EB03_35.equals(hippaCodeValue03
								.getValue())) {
							isEB0335 = true; 
							break;
						}
					}
				}
			}
		}
		return isEB0335;
	}
	/**
	 *  Method to set the E038 Error
	 * @param contract
	 * @param eB03CodedAsVisionFlag
	 * @param visionCoveredForE038
	 */
	private void validateE038(ContractVO contract,
			boolean eB03CodedAsVisionFlag, boolean visionCoveredForE038) {

		if (!errorflagForE038 && !eB03CodedAsVisionFlag && visionCoveredForE038) {
			setContractLevelErrorForE038(contract);
			errorflagForE038 = true;
		}
	}
	
	/**
	 * Method to set the E039 Error
	 * @param contract
	 * @param eB03CodedAsDentalFlag
	 * @param dentalCoveredForE039
	 */
	private void validateE039(ContractVO contract,
			boolean eB03CodedAsDentalFlag, boolean dentalCoveredForE039) {
		if (!errorflagForE039 && !eB03CodedAsDentalFlag && dentalCoveredForE039) {
			setContractLevelErrorForE039(contract);
			errorflagForE039 = true;
		}
	}
/***************************SSCR 14181 April 2012 Release  END**********************************/
	
	/***START BXNI NOVEMBER RELEASE 2012***
	E043 Error will be reported,
	If EB03 PT is coded, then checks whether EB03 AE is coded or not.
	If EB03 AE is coded, then checks whether EB03 PT is coded or not.
	If EB03 98 is coded, then checks whether EB03 BY is coded or not.
	If EB03 BY is coded, then checks whether EB03 98 is coded or not.
	If EB03 4 is coded, then checks whether EB03 73 is coded or not.
	If EB03 73 is coded, then checks whether EB03 4 is coded or not.
	if any of the condition fails a error will be added to the list.
	 * @param contractMappingVO 
	*
	*/
	private void validateE043(MinorHeadingsVO minorHeadingObj,Set<String> minorHeadingE008Set) {
		//Initializing variables.
		List<String> eb03List = new ArrayList<String>();
		//get the header rule mapping from the minor heading object
		ContractMappingVO contractMappingVO = (ContractMappingVO) minorHeadingObj.getRuleMapping();
		//Get the list of EB03's for a variable.
		if (null != contractMappingVO) {
			if (null != contractMappingVO.getEb03()) {
				List eb03ValuesList = contractMappingVO.getEb03()
						.getHippaCodeSelectedValues();
				if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
					Iterator eb03Iterator = eb03ValuesList.iterator();

					while (eb03Iterator.hasNext()) {
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
								.next();
						eb03List.add(hippaCodeValue03.getValue());
					}
				}
				//Check for E043 Error Condition. If PT Exists then AE should Exists.
				isE043Exists(DomainConstants.EB03_PT, DomainConstants.EB03_AE, eb03List, minorHeadingObj,minorHeadingE008Set);
				
				//Check for E043 Error Condition. If AE Exists then PT should Exists.
				isE043Exists(DomainConstants.EB03_AE, DomainConstants.EB03_PT, eb03List, minorHeadingObj,minorHeadingE008Set);
				
				//Check for E043 Error Condition. If 98 Exists then BY should Exists.
				isE043Exists(DomainConstants.EB03_98, DomainConstants.EB03_BY, eb03List, minorHeadingObj,minorHeadingE008Set);
				
				//Check for E043 Error Condition. If BY Exists then 98 should Exists.
				isE043Exists(DomainConstants.EB03_BY, DomainConstants.EB03_98, eb03List, minorHeadingObj,minorHeadingE008Set);
				
				//Check for E043 Error Condition. If 4 Exists then 73 should Exists.
				isE043Exists(DomainConstants.EB03_4, DomainConstants.EB03_73, eb03List, minorHeadingObj,minorHeadingE008Set);
				
				//Check for E043 Error Condition. If 73 Exists then 4 should Exists.
				isE043Exists(DomainConstants.EB03_73, DomainConstants.EB03_4, eb03List, minorHeadingObj,minorHeadingE008Set);
				
			}
		}
	}
	
	 /** @param mapping ****/
	/**
	 * This method checks if a EB03 is coded, it will check the associated EB03 is coded or not.
	 * @param codedEB03
	 * @param eb03Exists
	 * @param eb03List
	 * @param mapping
	 */
	private void isE043Exists(String codedEB03, String eb03Exists, List<String> eb03List, MinorHeadingsVO minorHeadingObj,Set<String> minorHeadingE008Set) {
		//Initializing Variables
		boolean isCodedEB03Exists=false;
		boolean isEb03Exists=false;
		boolean errorExists = false;
		StringBuffer errorDescription = new StringBuffer();
		
		//if a EB03 is coded, it will check for its associated EB03 is coded or not.
		if(!eb03List.isEmpty() && eb03List.contains(codedEB03)){
			isCodedEB03Exists = true;
			if(eb03List.contains(eb03Exists)){
				isEb03Exists = true;
			}
		}
		
		//Generating Error Description
		if((isCodedEB03Exists && !isEb03Exists)){ 
			errorExists = true;
			errorDescription.append("Service type code(s) ");
			errorDescription.append(eb03Exists);
			errorDescription.append(" is missing since service type code ");
			errorDescription.append(codedEB03);
			errorDescription.append(" is coded.");
		}
		
		//if a EB03 is coded, and its associated EB03 is not coded then a error will be added to the list.
		if(errorExists && !minorHeadingE008Set.contains(minorHeadingObj.getDescriptionText())){
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E043);
			errorDetailsObject.setErrorDesc(errorDescription.toString());
			minorHeadingObj.getErrorCodesList().add(errorDetailsObject);
			minorHeadingE008Set.add(minorHeadingObj.getDescriptionText());
		}
		
	}
	 /***END BXNI NOVEMBER RELEASE 2012**/
	
	
	/**
	 * The method returns the flag for the Error code E038 EB03=AL for vision Benefits
	 * @param mapping
	 * @return boolean
	 */
	private boolean isEB03Has88(MinorHeadingsVO minorHeadingsVO) {
		boolean isEB0388 = false;
		if(minorHeadingsVO.getRuleMapping().isMapped()) {
			HippaSegment hippaSegmentEB03 = minorHeadingsVO.getRuleMapping().getEb03();
			/**
			 * Checking if EB03 is null
			 */
			if (null != hippaSegmentEB03) {
				List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
				if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
					Iterator eb03Iterator = eb03ValuesList.iterator();

					while (eb03Iterator.hasNext()) {
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
								.next();

						if (DomainConstants.EB03_88.equals(hippaCodeValue03
								.getValue())) {
							isEB0388 = true; 
							break;
						}
					}
				}
			}
		}
		return isEB0388;
	}
	/**
	 * Method to check if the benefit is non covered
     * based on the answer of 'BENEFIT COVERED' question and EB03 value
     * If EB03=88, then if the Benefit covered question is answered as N, then Non Covered
     * SSCR 16331 Nov Release 
	 * @param MinorHeadingsVO
	 * @return MinorHeadingsVO
	 */
	private boolean isBenefitNonCovered(MinorHeadingsVO minorHeadingObj) {
		boolean isBenefitNonCovered = false;
		if (null != minorHeadingObj) {
			if(minorHeadingObj.getRuleMapping().isMapped()) {
				HippaSegment hippaSegmentEB03 = minorHeadingObj.getRuleMapping().getEb03();
				/**
				 * Checking if EB03 is null
				 */
				if (null != hippaSegmentEB03) {
					List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
					if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
						Iterator eb03Iterator = eb03ValuesList.iterator();

						while (eb03Iterator.hasNext()) {
							HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();

							if (DomainConstants.EB03_88.equals(hippaCodeValue03.getValue())) {
								// get Mappings Map
								HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
								Iterator iteratorMappings = mappingsMap.entrySet().iterator();
								while (iteratorMappings.hasNext()) {
									// adding the ErrorCodes to the Variable
									// object
									ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
											.next()).getValue();
									if (null != mappingObj && null != mappingObj.getSpsId()) {
										SPSId spsIdObj = mappingObj.getSpsId();
										if (null != spsIdObj.getSpsDesc() && 
												DomainConstants.BENEFITCOVERED.equals(spsIdObj
														.getSpsDesc())) {
											if (DomainConstants.N.equals(spsIdObj.getLineValue())) {
												isBenefitNonCovered = true;
												break;
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
		return isBenefitNonCovered;
	}
	/**
	 * Method to check if the benefit is covered
     * based on the answer of 'BENEFIT COVERED' question and EB03 value
     * If EB03=88, then if the Benefit covered question is not answered as N, then  Covered
     * SSCR 16331 Nov Release 
	 * @param MinorHeadingsVO
	 * @return MinorHeadingsVO
	 */
	private boolean isBenefitCovered(MinorHeadingsVO minorHeadingObj) {
		boolean isBenefitCovered = false;
		if (null != minorHeadingObj) {
			if(minorHeadingObj.getRuleMapping().isMapped()) {
				HippaSegment hippaSegmentEB03 = minorHeadingObj.getRuleMapping().getEb03();
				/**
				 * Checking if EB03 is null
				 */
				if (null != hippaSegmentEB03) {
					List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
					if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
						Iterator eb03Iterator = eb03ValuesList.iterator();

						while (eb03Iterator.hasNext()) {
							HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();

							if (DomainConstants.EB03_88.equals(hippaCodeValue03.getValue())) {
								// get Mappings Map
								HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
								Iterator iteratorMappings = mappingsMap.entrySet().iterator();
								while (iteratorMappings.hasNext()) {
									// adding the ErrorCodes to the Variable
									// object
									ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
											.next()).getValue();
									if (null != mappingObj && null != mappingObj.getSpsId()) {
										SPSId spsIdObj = mappingObj.getSpsId();
										if (null != spsIdObj.getSpsDesc() && 
												DomainConstants.BENEFITCOVERED.equals(spsIdObj
														.getSpsDesc())) {
											if (!DomainConstants.N.equals(spsIdObj.getLineValue())) {
												isBenefitCovered = true;
												break;
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
		return isBenefitCovered;
	}
}
