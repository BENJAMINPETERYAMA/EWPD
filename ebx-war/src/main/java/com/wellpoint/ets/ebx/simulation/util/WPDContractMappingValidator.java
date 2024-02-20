/*
 * <WPDContractMappingValidator.java>
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

import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
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
 *         Error Validator class for WPD - LG/ISG system. This class will scan
 *         the contract for error codes and creates a contract object which will
 *         contain error code list for various levels
 * 
 */
public class WPDContractMappingValidator extends ContractMappingValidator {

	//Variables removed as part of BXNI December Release-- Remove E011
	
	//private boolean isPVA_E011_INOUT = false;

	//private boolean isPVA_E011_IN = false;

	//private boolean isPVA_E011_OUT = false;

	//private List variablesListE011 = new ArrayList();

	private boolean isPVA_E012_IN_UC = false;

	private boolean isPVA_E012_OUT_UC = false;

	private boolean isPVA_E012_INOUT_UC = false;

	// Changed UR to UC as mentioned in BTRD for june release
	private List variablesListEB03UC = new ArrayList();

	private boolean isEB0388 = false;

	private boolean e012UCContractLevelErrorFlag = false;

	private List variableWithEB01_NotBC_E010 = new ArrayList();

	private List variableWithEB01_BC_E010 = new ArrayList();
	
	private List variableListEB0360 = new ArrayList();

	private List accumIndList= new ArrayList();
	
	private static String NOT_APPLICABLE="NOT_APPLICABLE";
	
	boolean errorflagForE038 = false;
	
	boolean errorflagForE039 = false;
	
	// private boolean flagmedsup = false;

	public List getAccumIndList() {
		return accumIndList;
	}

	public void setAccumIndList(List accumIndList) {
		this.accumIndList = accumIndList;
	}

	/**
	 * Method for validating contract for errors
	 * 
	 * @param contract
	 * @return List
	 */
	public List validate(List contractVOList) {
		ContractMappingValidator contractMappingValidator = new ContractMappingValidator();
		Iterator contractListIter = contractVOList.iterator();
		List<String> eb03ValuesInContract;
		boolean eB03CodedAsVisionFlag = false;
		boolean visionBenefitFlag = false;
		
		boolean eB03CodedAsDentalFlag = false;
		boolean dentalBenefitFlag = false;
		

		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has a variable with EB03 = 88 
		//and same variable has Eb01 = BC and coded value = N
		 boolean isVariableNonCovered = false;
		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has a variable with EB03 = 88 
		//and same variable doesnot have  Eb01 = BC and coded value = N
		 boolean isVariableCovered = false;
		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has any of the benefits specified for reporting E008
		 boolean E008BenefitsFlag = false;
		//SSCR 16331, For Error Code E008, flag will be set to true if the contract has any variable mapped to EB03=88
		 boolean isEB03_88 = false;
		 Map<String,ContractMappingVO> nonCoveredInNetworkMap = new HashMap<String,ContractMappingVO>();
		Map<String,ContractMappingVO> nonCoveredOutNetworkMap = new HashMap<String,ContractMappingVO>();
		List<String> nonCoveredInNetworkList = new ArrayList<String>();
		List<String> nonCoveredOutNetworkList = new ArrayList<String>();
		String eb01Value = "";
		String iii02Value = "";
		String networkIndicator = "";
		String codedValue = "";
		while (contractListIter.hasNext()) {
			eb03ValuesInContract = new ArrayList<String>();
			ContractVO contract = (ContractVO) contractListIter.next();
			errorExclusionDetailVO = new ErrorExclusionDetailVO();
			contractMappingValidator.processExclusionForErrorValidation(
					contract, errorExclusionDetailVO);
			contract.setErrorExclusionDetailVO(errorExclusionDetailVO);
				//contract.getAccumIndList();
			
			// get MajorHeadings Map
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
					while (iteratorMinor.hasNext()) {
						// Map variableAndEB03Map_E010 = new HashMap();
						String keyMinor = (String) iteratorMinor.next();
						MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
								.get(keyMinor);
						//SSCR 16331, For Error Code E008, flag E008BenefitsFlag will set to true 
						//if the contract has any of the benefits specified for reporting E008
						if (getWPDMinorHeadingsForE008().contains(
								minorHeadingObj.getDescriptionText())) {
							E008BenefitsFlag = true;
						}						
						// get Mappings Map
						HashMap mappingsMap = (HashMap) minorHeadingObj
								.getMappings();
						Iterator iteratorMappings = mappingsMap.entrySet()
								.iterator();
						while (iteratorMappings.hasNext()) {
							// adding the ErrorCodes to the Variable object
							ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
									.next()).getValue();
							String variableId = "";
							String sensitiveBenefitVariable = "";
							if (null != mappingObj.getVariable()
									&& null != mappingObj.getVariable()
											.getVariableId()) {
								variableId = mappingObj.getVariable()
										.getVariableId();
							}
							if (null != mappingObj.getVariable()
									&& null != mappingObj.getVariable()
											.getSensitiveBenefitIndicator()) {
								sensitiveBenefitVariable = mappingObj
										.getVariable()
										.getSensitiveBenefitIndicator();
							}
							 /***************************SSCR 14181 April 2012 Release  START**********************************/
							//E038 - Check for Vision benefits in the contract.
						    if (getVisionMinorHeadingsForE038().contains(keyMinor.toUpperCase())) {
                            	visionBenefitFlag = true;
                            }
						    //E039 - Check for Dental benefits in the contract.
                            if (getVisionMinorHeadingsForE039().contains(keyMinor.toUpperCase())) {
                            	dentalBenefitFlag = true;
                            }
							if (null != mappingObj
									&& null != mappingObj.getVariable()
									&& !NOT_APPLICABLE
											.equalsIgnoreCase(mappingObj
													.getVariable()
													.getVariableStatus())) {
								// Checking if EB03 = AL for E038
								if (isEB03HasAL(mappingObj)) {
									eB03CodedAsVisionFlag = true;
								}
								// Checks for E038
	                            if(getMinorHeadingDescriptionsForE038().contains(minorHeadingObj.getDescriptionText())) {
	                            	if(null != mappingObj.getVariable() && null != mappingObj.getVariable().getDescription() ){
	                            		String varDescription = mappingObj.getVariable().getDescription().toUpperCase();
	                            		if ((-1 != varDescription.indexOf(DomainConstants.VARIABLE_DESCRIPTION_EYE_EXAM))
	                            				||(-1 !=  varDescription.indexOf(DomainConstants.VARIABLE_DESCRIPTION_VISION_EXAM))) {
	                            			visionBenefitFlag = true;
	                            		}
	                            	}
	                            } 
	                            // Checks for the variable "OPTOMSERVCVD" for E038.
	                            if(null != mappingObj.getVariable() && null != mappingObj.getVariable().getVariableId() && null != mappingObj.getVariable().getVariableValue()){
	                            	if (mappingObj.getVariable().getVariableId().equalsIgnoreCase(DomainConstants.VARIABLE_OPTOMSERVCVD) &&
	                            			mappingObj.getVariable().getVariableValue().equalsIgnoreCase("Y")) {
	                            		visionBenefitFlag = true;
	                            	}
	                            }
	                            
	                            // Checking if EB03 = 35 for E039
	                            if (isEB03Has35(mappingObj)) {
	                            	eB03CodedAsDentalFlag = true;
	                            }
	                            //Checks for the variable "DNTLSERVSCOV" for E039.
	                            if(null != mappingObj.getVariable() && null != mappingObj.getVariable().getVariableId() && null != mappingObj.getVariable().getVariableValue()){
	                            	if (mappingObj.getVariable().getVariableId().equalsIgnoreCase(DomainConstants.VARIABLE_DNTLSERVSCOV) &&
	                            			mappingObj.getVariable().getVariableValue().equalsIgnoreCase("Y")) {
	                            		dentalBenefitFlag = true;
	                            	}
	                            }
							}
                            /***************************SSCR 14181 April 2012 Release  END**********************************/
							if (null != mappingObj && mappingObj.isMapped()) {
								
								if (null != mappingObj.getEb03()) {
									List eb03ValuesList = mappingObj.getEb03()
											.getHippaCodeSelectedValues();
									Iterator eb03Iterator = eb03ValuesList.iterator();
									while (eb03Iterator.hasNext()) {
										HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
												.next();
										String hippaCodeValue = hippaCodeValue03.getValue();
										eb03AIN.add(hippaCodeValue);
									}
								}
								if (null != mappingObj.getVariable()) {
									if (!NOT_APPLICABLE
											.equalsIgnoreCase(mappingObj
													.getVariable()
													.getVariableStatus())) {
										//fetchEb03Value(eb03ValuesInContract,mappingObj);
										
										
										String ebol_Val = "";
										// EB01 null checking
										if (null != mappingObj.getEb01()) {
											HippaSegment eb01 = mappingObj
													.getEb01();
											HippaCodeValue hippaCodeValue01 = (HippaCodeValue) eb01
													.getHippaCodeSelectedValues()
													.get(0);
											ebol_Val = hippaCodeValue01
													.getValue();
										}
										if (null != mappingObj.getVariable()) {
											sensitiveBenefitVariable = mappingObj
													.getVariable()
													.getSensitiveBenefitIndicator();
											variableId = mappingObj
													.getVariable()
													.getVariableId();
										}
										// Checking if EB03 = 88
										isEB0388 = isEB03Has88(mappingObj);
										
										//SSCR 16331, For Error Code E008
										if(!isEB03_88){
											isEB03_88 = isEB03Has88(mappingObj);
										}
										if(!isVariableNonCovered){
											isVariableNonCovered = isVariableNonCovered(contract,mappingObj,variableId);
										}
										if(!isVariableCovered){
											isVariableCovered = isVariableCovered(contract,mappingObj,variableId);
										}			
										
										//Start **** E042
                                        eb01Value = mappingObj.getEB01Value();                                                    
                                        List<String> eb03Values = mappingObj.getEb03Values();
                                        
                                        networkIndicator = BxUtil.getHipaaSegmentValue(mappingObj.getEb12());
                                        codedValue = MappingUtil.getVariableCodedValueInContract(contract, mappingObj.getVariable().getVariableId());
                                        
                                        List <EB03Association> eb03Associations  = new ArrayList<EB03Association>();                                                       
                                        
                                        
                                        if("PAR".equals(mappingObj.getVariable().getPva())){
                                              networkIndicator = "Y";
                                        }else if("NPAR".equals(mappingObj.getVariable().getPva())){
                                              networkIndicator = "N";
                                        }else if("ALL".equals(mappingObj.getVariable().getPva())){
                                              networkIndicator = "W";
                                        }
                                        
                                        if(DomainConstants.EB01_BC.equals(eb01Value) 
                                                    && DomainConstants.N.equals(codedValue)){

                                              
                                              if(mappingObj.getEb03()!=null){
                                                    eb03Associations = mappingObj.getEb03().getEb03Association();
                                              }

                                              if(eb03Associations!=null){
                                                    for(EB03Association eb03Association:eb03Associations){
                                                          if (((null == eb03Association.getIii02List()) || (eb03Association.getIii02List().size() == 0) || 
                                                        		  (StringUtils.isBlank(eb03Association.getIii02List().get(0).getValue())))
                                                        		  && StringUtils.isBlank(eb03Association.getMessage())){
                                                                
                                                                if(DomainConstants.Y.equals(networkIndicator)){
                                                                      nonCoveredInNetworkMap.put(eb03Association.getEb03().getValue(), mappingObj);
                                                                }else if(DomainConstants.N.equals(networkIndicator)){
                                                                      nonCoveredOutNetworkMap.put(eb03Association.getEb03().getValue(), mappingObj);      
                                                                }else if(DomainConstants.W.equals(networkIndicator)){
                                                                      nonCoveredOutNetworkMap.put(eb03Association.getEb03().getValue(), mappingObj);
                                                                      nonCoveredInNetworkMap.put(eb03Association.getEb03().getValue(), mappingObj);
                                                                }
                                                          }
                                                    }
                                              }
                                        }else{
                                              if((DomainConstants.EB01_A.equals(eb01Value) || DomainConstants.EB01_B.equals(eb01Value)
                                                          || DomainConstants.EB01_C.equals(eb01Value) || DomainConstants.EB01_F.equals(eb01Value)
                                                          || DomainConstants.EB01_G.equals(eb01Value) || DomainConstants.EB01_U.equals(eb01Value)) 
                                                          || (DomainConstants.EB01_BC.equals(eb01Value) && DomainConstants.Y.equals(codedValue))){
                                                    for(String eb03:eb03Values){
                                                          if(DomainConstants.Y.equals(networkIndicator)){
                                                                if(!nonCoveredInNetworkList.contains(eb03)){
                                                                      nonCoveredInNetworkList.add(eb03);  
                                                                }     
                                                          }else if(DomainConstants.N.equals(networkIndicator) ){
                                                                if(!nonCoveredOutNetworkList.contains(eb03)){
                                                                      nonCoveredOutNetworkList.add(eb03); 
                                                                }
                                                          }else if(DomainConstants.W.equals(networkIndicator)){
                                                                if(!nonCoveredInNetworkList.contains(eb03)){
                                                                      nonCoveredInNetworkList.add(eb03);  
                                                                }
                                                                if(!nonCoveredOutNetworkList.contains(eb03)){
                                                                      nonCoveredOutNetworkList.add(eb03); 
                                                                }
                                                          }
                                                    }
                                              }
                                        }
                                  //End **** E042

										/**
										 * For error code E001 Exclusions:CDHP
										 * contract variables and Sensitive
										 * benefit variables and bnftval=0
										 */
										if (!contract.isCDHPFlag()
												&& (!DomainConstants.Y
														.equals(sensitiveBenefitVariable))
												&& !DomainConstants.ZERO
														.equals(mappingObj.getVariable().getVariableValue())) {
											if (isErrorToBeExcludedVariableLevel(
													errorExclusionDetailVO
															.isE001ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E001,
													errorExclusionDetailVO
															.getVariableExclusionsList(),
													variableId)) {
												//Added as a part of BXNI CR35
												/**
												 * For error code E001 Exclusions:
												 * Exclude E001 if EB03=AL,35,88
												 */
												List<String> eb03List = new ArrayList<String>();
												if (null != mappingObj.getEb03()) {
													List eb03ValuesList = mappingObj.getEb03()
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
															mappingObj = validateE001(
																	mappingObj, ebol_Val);
														}
														}
													}
												}
										
										// For error code E004
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE004ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E004,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE004(
													mappingObj, ebol_Val);
										}
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE005ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E005,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE005(
													mappingObj, ebol_Val);
										}
										// For error code E006
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE006ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E006,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE006(
													mappingObj, ebol_Val);
										}
										// For error code E007
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE007ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E007,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE007(
													mappingObj, ebol_Val);
										}
										// For error code E009
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE009ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E009,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE009(
													mappingObj, ebol_Val);
										}
										// For error code E020
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE020ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E020,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE020(mappingObj);
										}
										// for error E021
										// for LG
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE021ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E021,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											if (!contract.isMedSUPContract()
													&& !isEB0388) {
												mappingObj = validateE021(
														mappingObj, ebol_Val);
											}
										}
										// Check for HMO contract exclusion,E010
										if (!contract.isFlagHMO()) {
											// EB03 null checking
											// if(null != mappingObj.getEb03())
											// {
											// if variable have mapping other
											// than EB01=BC
											// String mapKey =
											// mappingObj.getVariable().getVariableId();
											if (!DomainConstants.EB01_BC
													.equals(ebol_Val)) {
												// Map key is VariableID,Object
												// is mappingobj
												// variableAndEB01_NotBCMap_E010.put(mapKey,
												// mappingObj);
												variableWithEB01_NotBC_E010
														.add(mappingObj);
											}
											// if variable have mapping EB01=BC
											else if (!isEB03ExclusionExistsForE010(mappingObj)
													&& DomainConstants.Y
															.equals(mappingObj
																	.getVariable()
																	.getVariableValue())) {
												// variableAndEB01_BCMap_E010.put(mapKey,
												// mappingObj);
												variableWithEB01_BC_E010
														.add(mappingObj);
											}	
										}
										if (!contract.isFlagHMO()) {
											// Setting flags for error code E011,E012
											setFlagForE011AndE012(mappingObj);
										} else {									
											setEB03ListForE010(mappingObj);
										}
										// For error code E014
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE014ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E014,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE014(
													mappingObj, ebol_Val);
										}

										// E016,E017
										if (contract.isFlagHCR_E016And17()) {// Exclusions:NON
											// HCR
											// Contracts
											// For error code E016
											if (isErrorToBeExcludedVariableLevel(
													errorExclusionDetailVO
															.isE016ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E016,
													errorExclusionDetailVO
															.getVariableExclusionsList(),
													variableId)) {
												mappingObj = validateE016(
														mappingObj, ebol_Val);
											}
											// For error code E017
											if (isErrorToBeExcludedVariableLevel(
													errorExclusionDetailVO
															.isE017ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E017,
													errorExclusionDetailVO
															.getVariableExclusionsList(),
													variableId)) {
												mappingObj = validateE017(
														mappingObj, ebol_Val);
											}
										}
										// For error code E018
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE018ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E018,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											if (contract.isFlagHCR_E018()) {// Exclusions:NON
												// HCR
												// Contracts
												mappingObj = validateE018(
														mappingObj, ebol_Val);
											}
										}

										/**
										 * For error code E022
										 */
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE022ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E022,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											if (!contract.isMedSUPContract()) {
												validateE022(mappingObj);
											}
										}
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE023ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E023,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											validateE023(mappingObj, contract);
										}

										/**
										 * For error code E026
										 */
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE026ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E026,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE026(
													mappingObj, ebol_Val);
										}
										/**
										 * For E027 June release. Creating
										 * required EB03 Maps
										 */
										getRequiredEB03ListForE027(mappingObj,
												ebol_Val);
										// E027 will be fired only for mapped variables -- Sep Rel.
										// For error Code E027 --June release
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE027ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E027,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											if (contract.isFlagEPO()) {
												validateE027ForEPO(contract, mappingObj);
											}
										}
										// For error Code E029 --June release
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO.isE029ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E029, errorExclusionDetailVO.getVariableExclusionsList(), variableId)) {
											mappingObj = validateE029(mappingObj,
													minorHeadingObj, majorHeadingObj);
										}
										/**
										 *  For error Code E030 --June release
										 *  Moved to here as part of July regression test to include NA check.
										 */
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE030ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E030,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											validateE030(mappingObj);
										}
										
										// For error Code E034 --Aug release
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO.isE034ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E034, errorExclusionDetailVO.getVariableExclusionsList(), variableId)) {
											validateE034(mappingObj, contract.getCopayIndicatorFlag(), contract.getDedIndicatorFlag());
										}

										// For error Code E035 --Aug release
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO.isE035ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E035, errorExclusionDetailVO.getVariableExclusionsList(), variableId)) {
											validateE035(mappingObj);
										}
										
										/**
										 * For error code E036
										 */
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE036ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E036,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											mappingObj = validateE036(
													minorHeadingObj, mappingObj);
										}
										
										/**
										 * For error code E041
										 */
										if (isErrorToBeExcludedVariableLevel(
												errorExclusionDetailVO
														.isE041ExclusionValidationEnableFlag(),
												DomainConstants.ERROR_E041,
												errorExclusionDetailVO
														.getVariableExclusionsList(),
												variableId)) {
											 validateE041(contract, mappingObj);
										}
										/**
										 * For error code E043
										 */
										if (errorExclusionDetailVO
												.isE043ExclusionValidationEnableFlag()) {
											validateE043(mappingObj);
										}
										
									}
								}
							}
							// For error code E025
							if (isErrorToBeExcludedVariableLevel(
									errorExclusionDetailVO
											.isE025ExclusionValidationEnableFlag(),
									DomainConstants.ERROR_E025,
									errorExclusionDetailVO
											.getVariableExclusionsList(),
									variableId)) {
								if (!(checkForE036ErrorCondition(minorHeadingObj, mappingObj)
										|| checkForRuleE029(mappingObj.getVariable().getDescription(),
												minorHeadingObj.getDescriptionText(),
												majorHeadingObj.getDescriptionText()))) {
									mappingObj = validateE025(mappingObj, keyMinor);
								}
							}
							// For error Code E031 --July release
							if (isErrorToBeExcludedVariableLevel(
									errorExclusionDetailVO
											.isE031ExclusionValidationEnableFlag(),
									DomainConstants.ERROR_E031,
									errorExclusionDetailVO
											.getVariableExclusionsList(),
									variableId)) {
								validateE031(mappingObj, accumIndList);
							}
							// For error Code E032 --July release
							if (isErrorToBeExcludedVariableLevel(
									errorExclusionDetailVO
											.isE032ExclusionValidationEnableFlag(),
									DomainConstants.ERROR_E032,
									errorExclusionDetailVO
											.getVariableExclusionsList(),
									variableId)) {
								validateE032(mappingObj, accumIndList);
							}
							// For error Code E031 --July release
							if (isErrorToBeExcludedVariableLevel(
									errorExclusionDetailVO
											.isE033ExclusionValidationEnableFlag(),
									DomainConstants.ERROR_E033,
									errorExclusionDetailVO
											.getVariableExclusionsList(),
									variableId)) {
								validateE033(mappingObj, accumIndList);
							}
						}// end of Mappings Map while loop

						// For error code E008
					/*	if (errorExclusionDetailVO
								.isE008ExclusionValidationEnableFlag()) {
							validateE008(minorHeadingObj, isEB0388);
						}*/
						// Validation for E010
						// validateE010(variableAndEB03Map_E010,contract,minorHeadingObj);
						// isEB01BC = false;
						// variableWithBC = null;
						// isEB01U = false;
					}// end of Minor Heading Map while loop
				} // end of Major Heading Map while loop
				// Validation for E010
				if (errorExclusionDetailVO
						.isE010ExclusionValidationEnableFlag()) {
					if (!contract.isFlagHMO()) {
						validateE010(contract);
					}
				}
				
				//E011 removed as part of BXNI Decmber release
				
				/*// Validation for E011,EB03=SS,SP
				if (!contract.isFlagHMO()) {
					if (errorExclusionDetailVO
							.isE011ExclusionValidationEnableFlag()) {
						//E011 validation for LG would exclude MCS TPA Contracts -- SSCR 16331 Nov Release
						if(DomainConstants.LG.equals(contract.getSystem()) && !contract.isContractMCSTPA()){
							validateE011(contract);
							variablesListE011 = new ArrayList();
						}else if(DomainConstants.ISG.equals(contract.getSystem())){
							validateE011(contract);
							variablesListE011 = new ArrayList();
						}
						
					}
				}*/

				//E012 validation for LG would exclude MCS TPA Contracts -- SSCR 16331 Nov Release
				//Exclude validation would be excluded for STAR(ISG) contracts -- SSCR 16331 Nov Release
				if(DomainConstants.LG.equals(contract.getSystem()) && !contract.isContractMCSTPA()){
				
				// Validation for E012,EB03=UC
				if (errorExclusionDetailVO
						.isE012ExclusionValidationEnableFlag()) {
					validateE012(contract);
					isPVA_E012_INOUT_UC = false;
					isPVA_E012_IN_UC = false;
					isPVA_E012_OUT_UC = false;
					// Changed UR to UC as mentioned in BTRD for june release
					variablesListEB03UC = new ArrayList();
				}
				if (!contract.isFlagHMO()) {
					if (errorExclusionDetailVO
							.isE012ExclusionValidationEnableFlag()) {
						setContractLevelErrorForE012(contract);
					}
				}
			}
		}
			if (errorExclusionDetailVO.isE027ExclusionValidationEnableFlag()) {
				validateE027(contract);
			}
			// For error code E038
            if (errorExclusionDetailVO
					.isE038ExclusionValidationEnableFlag()) {
            	/**
				 * Validation for E038 
				 */
				validateE038(contract,eB03CodedAsVisionFlag, visionBenefitFlag);
			}
         // For error code E039
            if (errorExclusionDetailVO
					.isE039ExclusionValidationEnableFlag()) {
            	/**
				 * Validation for E039
				 */
				validateE039(contract,eB03CodedAsDentalFlag, dentalBenefitFlag);
			}

            //SSCR 16331, For Error Code E008
            if (errorExclusionDetailVO
					.isE008ExclusionValidationEnableFlag() && E008BenefitsFlag) {
            	/**
				 * Validation for E008
				 */
            	validateE008(contract, isEB03_88,isVariableCovered, isVariableNonCovered);
			}
            
		}
		if (errorExclusionDetailVO
				.isE042ExclusionValidationEnableFlag()) {
			validateE042(nonCoveredOutNetworkMap,nonCoveredInNetworkMap,nonCoveredInNetworkList,nonCoveredOutNetworkList);	
		}
		
			
		//if E004, E006 and E007 present then E005 must be excluded.
		String exclusionArray[] = { DomainConstants.ERROR_E004,
				DomainConstants.ERROR_E006, DomainConstants.ERROR_E007 };
		String errorToBeExcluded = DomainConstants.ERROR_E005;
		contractVOList = errorExclusion(contractVOList, errorToBeExcluded,
				exclusionArray);
		return contractVOList;
	}
	

	private void validateE042(
            Map<String, ContractMappingVO> nonCoveredOutNetworkMap,
            Map<String, ContractMappingVO> nonCoveredInNetworkMap,
            List<String> nonCoveredInNetworkList,
            List<String> nonCoveredOutNetworkList) {

      String description = "";
      String eb03Value = null;
      List<String> eb03AndVariableIdList = new ArrayList<String>();
      boolean errorExists = false;
      ContractMappingVO mapping = null;
      for(Map.Entry<String, ContractMappingVO> entry : nonCoveredOutNetworkMap.entrySet()) { 
            eb03Value = entry.getKey();               

            mapping = entry.getValue();               
            String variableId ="";

            if(null !=mapping && null !=mapping.getVariable() && null !=mapping.getVariable().getVariableId()){
                  variableId = mapping.getVariable().getVariableId();
            }

            if(nonCoveredOutNetworkList.contains(eb03Value) && !eb03AndVariableIdList.contains(eb03Value +","+variableId)){
                  eb03AndVariableIdList.add(eb03Value +","+variableId);
                  errorExists = true;     
            }

            if(errorExists && !NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())){

                  description ="Discrepancy in service ("+eb03Value+"): Coded as Non-Covered with patient liability.";
                  errorExists = false;
                  ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
                  errorDetailsObject.setAssociatedEb03(eb03Value);
                  errorDetailsObject.setError(false);
                  errorDetailsObject.setErrorCode(DomainConstants.ERROR_E042);
                  errorDetailsObject.setErrorDesc(description);
                  entry.getValue().getErrorCodesList().add(errorDetailsObject);
            }
      }

      for(Map.Entry<String, ContractMappingVO> entry : nonCoveredInNetworkMap.entrySet()) {
            
            eb03Value = entry.getKey();

            mapping = entry.getValue();
            String variableId ="";
            if(null !=mapping && null !=mapping.getVariable() && null !=mapping.getVariable().getVariableId()){
                  variableId = mapping.getVariable().getVariableId();
            }

            if(nonCoveredInNetworkList.contains(eb03Value) && !eb03AndVariableIdList.contains(eb03Value +","+variableId)){
                  eb03AndVariableIdList.add(eb03Value +","+variableId);
                  errorExists = true;                             
            }

            if(errorExists && !NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())){
                  description ="Discrepancy in service ("+eb03Value+"): Coded as Non-Covered with patient liability.";
                  ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
                  errorDetailsObject.setError(false);
                  errorDetailsObject.setAssociatedEb03(eb03Value);
                  errorDetailsObject.setErrorCode(DomainConstants.ERROR_E042);
                  errorDetailsObject.setErrorDesc(description);
                  entry.getValue().getErrorCodesList().add(errorDetailsObject);
            }
      }

}

	private void validateE041(ContractVO contract, ContractMappingVO mappingObj) {
		String startAge = null;
		String endAge = null;
		String[] startAgeArray = {};
		String[] endAgeArray = {};
		boolean errorExistFlag = false;

		// Get the Start Age
		HippaSegment hippaSegmentStartAge = mappingObj.getStartAge();
		if (null != hippaSegmentStartAge
				&& !DomainConstants.EMPTY.equals(hippaSegmentStartAge)) {
			if (null != hippaSegmentStartAge.getHippaCodeSelectedValues()
					&& !(hippaSegmentStartAge.getHippaCodeSelectedValues()
							.isEmpty())) {
				HippaCodeValue hippaCodeValue = (HippaCodeValue) hippaSegmentStartAge
						.getHippaCodeSelectedValues().get(0);
				if (null != hippaCodeValue && null != hippaCodeValue.getValue()) {
					startAge = hippaCodeValue.getValue().trim();
					startAgeArray = startAge.split(",");
				}
			}
		}
		// Get the End Age
		HippaSegment hippaSegmentEndAge = mappingObj.getEndAge();
		if (null != hippaSegmentEndAge
				&& !DomainConstants.EMPTY.equals(hippaSegmentEndAge)) {
			if (null != hippaSegmentEndAge.getHippaCodeSelectedValues()
					&& !(hippaSegmentEndAge.getHippaCodeSelectedValues()
							.isEmpty())) {
				HippaCodeValue hippaCodeValue = (HippaCodeValue) hippaSegmentEndAge
						.getHippaCodeSelectedValues().get(0);
				if (null != hippaCodeValue && null != hippaCodeValue.getValue()) {
					endAge = hippaCodeValue.getValue().trim();
					endAgeArray = endAge.split(",");
				}
			}
		}
		int codedStartAgeCount=0;
		int codedEndAgeCount=0;
		// Split start end age and iterate

		if ((!StringUtils.isBlank(startAge) && startAgeArray.length > 1)
				|| ((!StringUtils.isBlank(endAge) && endAgeArray.length > 1))) {
			
			for (int i = 0; i < startAgeArray.length; i++) {
				String startAgeCodedValue = "";
				if(!StringUtils.isBlank(startAgeArray[i])){
					startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,startAgeArray[i].trim());
				}
					
				if (null != startAgeCodedValue
						&& !DomainConstants.EMPTY
								.equals(startAgeCodedValue)) {
					//to find if the count of coded start age variables in the contract
					codedStartAgeCount++;
				}
			}

			for (int j = 0; j < endAgeArray.length; j++) {
				String endAgeCodedValue = "";
				if(!StringUtils.isBlank(endAgeArray[j])){
					endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,endAgeArray[j].trim());
				}
				if (null != endAgeCodedValue
						&& !DomainConstants.EMPTY
								.equals(endAgeCodedValue)) {
					//to find if the count of coded start age variables in the contract
					codedEndAgeCount++;
				}
			}
			//Throw E041 if the number of coded start/end age variables is more than 1 in the contract -- CR18 Option 3
			if (codedStartAgeCount > 1 || codedEndAgeCount > 1){
				String errorDescrition = DomainConstants.MULTIPLE_AGE_VALUES;
				setE041ErrorAtVariableLevel(mappingObj, errorDescrition);
			}
		}
		//E041 conditions if both Start Age and End Age are present
		if ((null != startAge && !DomainConstants.EMPTY.equals(startAge))
				&& (null != endAge && !DomainConstants.EMPTY.equals(endAge))) {
			//iterate the start age array
			for (int i = 0; i < startAgeArray.length; i++) {
				//iterate the end age array
				for (int j = 0; j < endAgeArray.length; j++) {
					if (!errorExistFlag) {
						if (BxUtil.isInteger(startAgeArray[i])
								&& BxUtil.isInteger(endAgeArray[j])) {
							//Both Start Age and End Age are integers
							if (!isEndAgeHiger(startAgeArray[i], endAgeArray[j])) {
								String errorDescrition = DomainConstants.STARTAGE_LESS_ENDAGE;
								setE041ErrorAtVariableLevel(mappingObj,
										errorDescrition);
								errorExistFlag = true;
								break;
							}
						} else if (BxUtil.isInteger(startAgeArray[i])
								&& !BxUtil.isInteger(endAgeArray[j])) {
							// Start Age Integer and End Age Variable
							String endAgeCodedValue = "";
							if(!StringUtils.isBlank(endAgeArray[j])){
								endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,endAgeArray[j].trim());
							}
							if (null != endAgeCodedValue
									&& !DomainConstants.EMPTY
											.equals(endAgeCodedValue)) {
								mappingObj
										.setEndAgeCodedValue(endAgeCodedValue);
								if (BxUtil.isInteger(endAgeCodedValue)) {
									if (!isEndAgeHiger(startAgeArray[i],
											endAgeCodedValue)) {
										String errorDescrition = DomainConstants.STARTAGE_LESS_ENDAGE;
										setE041ErrorAtVariableLevel(mappingObj,
												errorDescrition);
										errorExistFlag = true;
										break;
									}
								} else {
									String errorDescrition = DomainConstants.INVALID_ENDAGE;
									setE041ErrorAtVariableLevel(mappingObj,
											errorDescrition);
									errorExistFlag = true;
									break;
								}
							}
							// Check whether End Age Variable is not coded in
							// the contract --BXNI Defect Fix
							else { 
								if (codedEndAgeCount == 0) {
								String errorDescrition = DomainConstants.END_AGE_VARIABLE_NOT_CODED;
								setE041ErrorAtVariableLevel(mappingObj,
										errorDescrition);
								errorExistFlag = true;
								break;
								}
							}
						} else if (!BxUtil.isInteger(startAgeArray[i])
								&& BxUtil.isInteger(endAgeArray[j])) {
							// Start Age Variable and End Age Integer
							String startAgeCodedValue = "";
							if(!StringUtils.isBlank(startAgeArray[i])){
								startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,startAgeArray[i].trim());
							}
							if (null != startAgeCodedValue
									&& !DomainConstants.EMPTY
											.equals(startAgeCodedValue)) {
								mappingObj
										.setStartAgeCodedValue(startAgeCodedValue);
								if (BxUtil.isInteger(startAgeCodedValue)) {
									if (!isEndAgeHiger(startAgeCodedValue,
											endAgeArray[j])) {
										String errorDescrition = DomainConstants.STARTAGE_LESS_ENDAGE;
										setE041ErrorAtVariableLevel(mappingObj,
												errorDescrition);
										errorExistFlag = true;
										break;
									}
								} else {
									String errorDescrition = DomainConstants.INVALID_STARTAGE;
									setE041ErrorAtVariableLevel(mappingObj,
											errorDescrition);
									errorExistFlag = true;
									break;
								}
							}
							// Check whether Start Age Variable is not coded in
							// the contract --BXNI Defect Fix
							else {
								if (codedStartAgeCount == 0) {
									String errorDescrition = DomainConstants.START_AGE_VARIABLE_NOT_CODED;
									setE041ErrorAtVariableLevel(mappingObj,
											errorDescrition);
									errorExistFlag = true;
									break;
								}
							}
						} else if (!BxUtil.isInteger(startAgeArray[i])
								&& !BxUtil.isInteger(endAgeArray[j])) {
							// Both Start Age and End Age Variables
							String startAgeCodedValue = "";
							if(!StringUtils.isBlank(startAgeArray[i])){
								startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,startAgeArray[i].trim());
							}
							String endAgeCodedValue = "";
							if(!StringUtils.isBlank(endAgeArray[j])){
								endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,endAgeArray[j].trim());
							}
							if (null != startAgeCodedValue
									&& !DomainConstants.EMPTY
											.equals(startAgeCodedValue)
									&& null != endAgeCodedValue
									&& !DomainConstants.EMPTY
											.equals(endAgeCodedValue)) {
								mappingObj
										.setStartAgeCodedValue(startAgeCodedValue);
								mappingObj
										.setEndAgeCodedValue(endAgeCodedValue);
								if (BxUtil.isInteger(startAgeCodedValue)
										&& BxUtil.isInteger(endAgeCodedValue)) {
									if (!isEndAgeHiger(startAgeCodedValue,
											endAgeCodedValue)) {
										String errorDescrition = DomainConstants.STARTAGE_LESS_ENDAGE;
										setE041ErrorAtVariableLevel(mappingObj,
												errorDescrition);
										errorExistFlag = true;
										break;
									}
								} else if (BxUtil.isInteger(startAgeCodedValue)
										&& !BxUtil.isInteger(endAgeCodedValue)) {
									String errorDescrition = DomainConstants.INVALID_ENDAGE;
									setE041ErrorAtVariableLevel(mappingObj,
											errorDescrition);
									errorExistFlag = true;
									break;
								} else if (!BxUtil
										.isInteger(startAgeCodedValue)
										&& BxUtil.isInteger(endAgeCodedValue)) {
									String errorDescrition = DomainConstants.INVALID_STARTAGE;
									setE041ErrorAtVariableLevel(mappingObj,
											errorDescrition);
									errorExistFlag = true;
									break;
								} else if (!BxUtil
										.isInteger(startAgeCodedValue)
										&& !BxUtil.isInteger(endAgeCodedValue)) {
									String errorDescrition = DomainConstants.INVALID_STARTAGE_ENDAGE;
									setE041ErrorAtVariableLevel(mappingObj,
											errorDescrition);
									errorExistFlag = true;
									break;
								}
							}
							// Check whether Start Age and End Age Variable is
							// not coded in the contract --BXNI Defect Fix
							else {
								if (StringUtils.isBlank(startAgeCodedValue)
										&& StringUtils
												.isBlank(endAgeCodedValue)) {
									if(codedStartAgeCount == 0 && codedEndAgeCount == 0){
										String errorDescrition = DomainConstants.START_AGE_END_AGE_VARIABLE_NOT_CODED;
										setE041ErrorAtVariableLevel(mappingObj,
												errorDescrition);
										errorExistFlag = true;
										break;
									}
								} else if (StringUtils
										.isBlank(startAgeCodedValue)) {
									if(codedStartAgeCount == 0){
										String errorDescrition = DomainConstants.START_AGE_VARIABLE_NOT_CODED;
										setE041ErrorAtVariableLevel(mappingObj,
												errorDescrition);
										errorExistFlag = true;
										break;
									}
									
								} else if (StringUtils
										.isBlank(endAgeCodedValue)) {
									if(codedEndAgeCount == 0){
										String errorDescrition = DomainConstants.END_AGE_VARIABLE_NOT_CODED;
										setE041ErrorAtVariableLevel(mappingObj,
												errorDescrition);
										errorExistFlag = true;
										break;
									}
									
								}
							}
						}
					}
				}
			}
		}else if(null != startAge && !DomainConstants.EMPTY.equals(startAge)){
			//Only Start Age is present
			//iterate the start age array
			for (int i = 0; i < startAgeArray.length; i++) {
				if (!errorExistFlag) {
					if (!BxUtil.isInteger(startAgeArray[i])){
						//Start Age variable
						String startAgeCodedValue = "";
						if(!StringUtils.isBlank(startAgeArray[i])){
							startAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,startAgeArray[i].trim());
						}
						if (null != startAgeCodedValue
								&& !DomainConstants.EMPTY
										.equals(startAgeCodedValue)) {
							mappingObj
									.setStartAgeCodedValue(startAgeCodedValue);
							if (!BxUtil.isInteger(startAgeCodedValue)) {
								String errorDescrition = DomainConstants.INVALID_STARTAGE;
								setE041ErrorAtVariableLevel(mappingObj,
										errorDescrition);
								errorExistFlag = true;
								break;
							}
						}	else {
							if (codedStartAgeCount == 0) {
								String errorDescrition = DomainConstants.START_AGE_VARIABLE_NOT_CODED;
								setE041ErrorAtVariableLevel(mappingObj,
										errorDescrition);
								errorExistFlag = true;
								break;
							}
						}
					}
				}
			}
		}else if(null != endAge && !DomainConstants.EMPTY.equals(endAge)){
			//Only End Age is present
			//iterate the start age array
			for (int j = 0; j < endAgeArray.length; j++) {
				if (!errorExistFlag) {
					if (!BxUtil.isInteger(endAgeArray[j])){
						//End Age variable
						String endAgeCodedValue = "";
						if(!StringUtils.isBlank(endAgeArray[j])){
							endAgeCodedValue = MappingUtil.getVariableCodedValueInContract(contract,endAgeArray[j].trim());
						}
						if (null != endAgeCodedValue
								&& !DomainConstants.EMPTY
										.equals(endAgeCodedValue)) {
							mappingObj
									.setEndAgeCodedValue(endAgeCodedValue);
							if (!BxUtil.isInteger(endAgeCodedValue)) {
								String errorDescrition = DomainConstants.INVALID_ENDAGE;
								setE041ErrorAtVariableLevel(mappingObj,
										errorDescrition);
								errorExistFlag = true;
								break;
							}
						}	else {
							if (codedEndAgeCount == 0) {
								String errorDescrition = DomainConstants.END_AGE_VARIABLE_NOT_CODED;
								setE041ErrorAtVariableLevel(mappingObj,
										errorDescrition);
								errorExistFlag = true;
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Method to set the E041 error.
	 * @param mappingObj
	 * @param errorDescrition
	 */
	private void setE041ErrorAtVariableLevel(ContractMappingVO mappingObj, String errorDescrition) {
		ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
		errorDetailsObject.setError(false);
		errorDetailsObject.setErrorCode(DomainConstants.ERROR_E041);
		errorDetailsObject.setErrorDesc(DomainConstants.E041_DESCRIPTION + " - " + errorDescrition);
		mappingObj.getErrorCodesList().add(errorDetailsObject);
		
	}

	/**
	 * Method to check the higher value.
	 * @param startAge
	 * @param endAge
	 * @return
	 */
	private boolean isEndAgeHiger(String startAge, String endAge) {
		boolean higher = false;
		int value1 = Integer.parseInt(startAge);
		int value2 = Integer.parseInt(endAge);
		if (value1 < value2) {
			higher = true;
		}
		return higher;
	}

	/**
	 * This method checks condition for Error Code - E001
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE001(ContractMappingVO mapping,
			String eb01_Val) {
		
		String eb06_Value = null;
		String hsd01_Value=null;
		String hsd05_Value=null;
		HippaSegment eb06 = mapping.getEb06();
		HippaSegment hsd01 = mapping.getHsd01();
		HippaSegment hsd05 = mapping.getHsd05();
		/**
		 * Checking if EB06 value is null
		 */
		if (null != eb06) {
			// Checking list is null
			if (null != eb06.getHippaCodeSelectedValues()) {
				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) eb06
						.getHippaCodeSelectedValues().get(0);
				eb06_Value = hippaCodeValue06.getValue();
			} else {
				eb06_Value = DomainConstants.EB06_BLANK;
			}
		} else {
			eb06_Value = DomainConstants.EB06_BLANK;
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
		
				/**
				 * Checking if EB01 = Deductible, Out-Of-Pocket or Limitation
				 * and EB06 = 22, 23, 25, 32,33 and no accumulator mapped
				 */
				if ((DomainConstants.DEDUCTIBLE.equals(eb01_Val)
						|| DomainConstants.STOP_LOSS.equals(eb01_Val) || DomainConstants.LIMITATIONS
						.equals(eb01_Val))
						&& ((getEB06ForE001().contains(eb06_Value) || DomainConstants.EB06_BLANK.equals(eb06_Value)))
						&& null == mapping.getAccum()) {
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
				
				//Added as a part of BXNI CR35
				/**
				 * Checking if EB01 = B/A/F, HSD01 is non blank
				 * and HSD05 = 22/23/25/32/Blank, and no accumulator mapped and sensitive indicator is N
				 */
				
				if(getEB01forE001().contains(eb01_Val)	&& !DomainConstants.HSD01_BLANK.equals(hsd01_Value) && ((getHSD05ForE001().contains(hsd05_Value))
						|| DomainConstants.HSD05_BLANK.equals(hsd05_Value))
						&& null == mapping.getAccum() && DomainConstants.N.equals(mapping.getVariable().getSensitiveBenefitIndicator())){
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
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E004
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE004(ContractMappingVO mapping,
			String eb01_Val) {
		String format = "";
		if (null != mapping.getVariable()) {
			format = mapping.getVariable().getVariableFormat();
		}
		/**
		 * checking if EB01=B, format is PCT,checking for exclusions
		 */
		if (null != format && DomainConstants.COPAYMENT.equals(eb01_Val)
				&& DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
				
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(false);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E004);
			errorDetailsObject.setErrorDesc(DomainConstants.E004_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E005
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE005(ContractMappingVO mapping,
			String eb01_Val) {
		String format = "";
		String variableValue = "";

		if (null != mapping.getVariable()) {
			format = mapping.getVariable().getVariableFormat();
			variableValue = mapping.getVariable().getVariableValue();
		}

		if (!BxUtil.isNumeric(variableValue)
				&& getJunkCheckForE005().contains(eb01_Val)) {
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E005);
			errorDetailsObject.setErrorDesc(DomainConstants.E005_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);

		}/**
		 * Check for boolean format,EB01 = F, BC, DW or U
		 */
		else if (!DomainConstants.BOOLEAN_FORMAT.equals(format)
				&& getEB01ForE005().contains(eb01_Val)) {
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E005);
			errorDetailsObject.setErrorDesc(DomainConstants.E005_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
			
		} else if (null == variableValue) {
			/**
			 * Object to hold error details Variable Level Error.
			 * if variable value is null we consider it as notes not attached
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E005);
			errorDetailsObject.setErrorDesc(DomainConstants.E005_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E006 For WPD if the BX
	 * mapping for a contract variable, has EB01 NOT = "A" or "B" or "I", but
	 * FORMAT = PCT then error is triggered.Exclusion - do not throw error if
	 * variable has EB03 = 88 or AL or 35
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE006(ContractMappingVO mapping,
			String eb01_Val) {

		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		boolean isEB03ExclusionsExist = false;
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

					if (getEB03ForE006().contains(hippaCodeValue03.getValue())) {
						isEB03ExclusionsExist = true;
						break;
					}
				}
			}
		}
		if (!isEB03ExclusionsExist) {
			String format = "";
			if (null != mapping.getVariable()) {
				format = mapping.getVariable().getVariableFormat();
			}
			// Checking if EB01 != A or B or I
			if (!DomainConstants.COINSURANCE.equals(eb01_Val)
					&& !DomainConstants.COPAYMENT.equals(eb01_Val)
					&& !DomainConstants.EB01_NON_COVERED.equals(eb01_Val)) {
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
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E007
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE007(ContractMappingVO mapping,
			String eb01_Val) {
		String format = "";
		if (null != mapping.getVariable()) {
			format = mapping.getVariable().getVariableFormat();
		}
		/**
		 * checking if format is not PCT and if EB01 = A
		 */
		if (null != format && DomainConstants.COINSURANCE.equals(eb01_Val)
				&& !DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E007);
			errorDetailsObject.setErrorDesc(DomainConstants.E007_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E008
	 * E008 is modified as part of SSCR 16331 Nov Release.
	 * E008 will be thrown if a non covered contract has a pharmacy benefit coded in it.
	 * if a Contract does not have a mapping Eb03=88, then it is treated as non covered.
	 * if a contract has a mapping Eb03 = 88,
	 * then all the variable having Eb03 =88 has Eb01 = BC and Coded value = N, then it is treated as non covered.
	 * isEB0388 flag holds true if there is a Eb03 = 88 mapping.
	 * isVariableCovered flag holds true if the Eb03= 88 mapped variable has Eb01=BC and coded value other than N.
	 * isVariableNonCovered flag holds true if the Eb03= 88 mapped variable has Eb01=BC and coded value = N.
	 * if the contract is non covered, then check for the pharmacy benefits list
	 * and check if a variable in a pharmacy benefit has value coded.
	 * if so, throw E008 at  Benefit Level.
	 * 
	 * @param ContractVO, isVariableCovered, isVariableNonCovered
	 * @param isEB0388
	 */
	
	private void validateE008(ContractVO contract, boolean isEB03_88, boolean isVariableCovered, boolean isVariableNonCovered) {
		log.debug("isEB0388 >>"+isEB03_88);
		log.debug("isVariableCovered >>"+isVariableCovered);
		log.debug("isVariableNonCovered >>"+isVariableNonCovered);
		Set<String> minorHeadingE008Set = new HashSet<String>();
		if (!isEB03_88 || (isVariableNonCovered && !isVariableCovered)) {
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
					while (iteratorMinor.hasNext()) {
						String keyMinor = (String) iteratorMinor.next();
						MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
						.get(keyMinor);
						if (getWPDMinorHeadingsForE008().contains(
								minorHeadingObj.getDescriptionText()) && !minorHeadingE008Set.contains(minorHeadingObj.getDescriptionText())) {
							HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
							Iterator iteratorMappings = mappingsMap.entrySet().iterator();
							while (iteratorMappings.hasNext()) {
								ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings.next()).getValue();
								if(null != mappingObj && null != mappingObj.getVariable()&& null != mappingObj.getVariable().getVariableId()) {
									String variableCodedValue = MappingUtil.getVariableCodedValueInContract(contract, mappingObj.getVariable().getVariableId());
									if(!StringUtils.isBlank(variableCodedValue) && !minorHeadingE008Set.contains(minorHeadingObj.getDescriptionText())){
										//Error
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
		}
	}

	/**
	 * This method checks condition for Error Code - E009
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	/**
	 * Logic:If variable format is any of (VST,HRS,DAY,DAYS,LEN,MTH,MTHS,YRS,AGE,OCC,OCRS) 
	 * and the EB01 is not 'F' then E009 error needs to be reported
	 */
	private ContractMappingVO validateE009(ContractMappingVO mapping,
			String eb01_Val) {
		String format = "";
		if (null != mapping.getVariable()) {
			format = mapping.getVariable().getVariableFormat();
		}
		/**
		 * Checking if variable data type is Visits, Hours, Day,Days,Len,Months, Years,Occurrence
		 * Occurrences and Age and if EB01 = F, if not error
		 */
		if (null != format && getFormatsForE009().contains(format)
				&& !DomainConstants.LIMITATIONS.equals(eb01_Val)) {
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E009);
			errorDetailsObject.setErrorDesc(DomainConstants.E009_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		return mapping;

	}

	/**
	 * The method checks for E010
	 * 
	 * @param variableAndEB03
	 *            - key: VariableId value: EB03 values
	 * @param contract
	 * @return
	 * 
	 */
	
	/**
	 * Logic: if a variable has EB01 = BC and value coded on variable is ‘Y’,
	 * check if any other variable with the same EB03 value has any other EB01
	 * mapping (for e.g deductible, copay etc). If there is, apply the network
	 * indicator logic. Network indicator logic – if the variable with EB01 =
	 * BC is an in-network variable, the other variable must be an in-network or
	 * in-out network variable. If not, an error will be reported.If the variable with
	 * EB01 = BC is an in-network variable, the other variable must be an in-network or 
	 * in-out network variable. If there is no such variable, there is at least one variable 
	 * with EB03 = 60 that is in-network or in-out network.Otherwise E010 error should be 
	 * reported.If the variable with the BC mapping is an out-network variable, the other
	 * variable must be an out-network or in-out network variable. If not, an
	 * error should be reported.If the variable with EB01 = BC is an out-network variable,
	 * the other variable must be an out-network or in-out network variable. If there is no
	 * such variable,there is at least one variable with EB03 = 60 that is out-network or in-out network.
	 * If a variable with BC mapping is an in-out network variable, do not throw an error 
	 * if the other variable is in-out or if there are variables of which one is an in-network
	 *  and other is out-network. The other variable can either have the same EB03 as the variable
	 * with EB01 = BC or it can have EB03 = 60. While looking for two variables 
	 * (one in-network and one out-network), an error needn’t be reported if one variable has
	 * the same EB03 as the variable with EB01 = BC and the other variable has EB03 = 60. 
	 * It is also not an error if both the variables have EB03 = 60.
	 * If any of above condition fails, throw an error.
	 */
	
	private void validateE010(ContractVO contract) {
		boolean isError = false;
		boolean inNetWorkExistsBC = false;
		boolean outNetWorkExistsBC = false;
		boolean InOutNetWorkExistsBC = false;
		boolean inNetWorkExists = false;
		boolean outNetWorkExists = false;
		boolean InOutNetWorkExists = false;
		boolean inNetWorkExists60 = false;
		boolean outNetWorkExists60 = false;
		boolean InOutNetWorkExists60 = false;
		
		if (null != variableWithEB01_BC_E010
				&& variableWithEB01_BC_E010.size() > 0) {
			Map varblAndListOfVarblsWithSameEB03 = new HashMap();
			ContractMappingVO mappingBC = null;
			ContractMappingVO mappingWithoutBC = null;
			List listWithSameEB03Vals = null;
			List eb03BCValues = null;
			List eb03WithoutBCVals = null;
			Iterator eb01BCListIterator = variableWithEB01_BC_E010.iterator();
			while (eb01BCListIterator.hasNext()) {
				mappingBC = (ContractMappingVO) eb01BCListIterator.next();
				listWithSameEB03Vals = new ArrayList();

				// For the Null check -- Prospective Sev 1 issue reported on LG
				// contracts. Start

				eb03BCValues = new ArrayList();
				if (null != mappingBC.getEb03()) {
					eb03BCValues = convertHippaCodeValuesToList(mappingBC
							.getEb03().getHippaCodeSelectedValues());
				}

				// For the Null check -- Prospective Sev 1 issue reported on LG
				// contracts. End

				if (null != variableWithEB01_NotBC_E010
						&& variableWithEB01_NotBC_E010.size() > 0) {
					Iterator eb01NotBCListIterator = variableWithEB01_NotBC_E010
							.iterator();
					while (eb01NotBCListIterator.hasNext()) {
						mappingWithoutBC = (ContractMappingVO) eb01NotBCListIterator
								.next();

						// For the Null check -- Prospective Sev 1 issue
						// reported on LG contracts. Start

						eb03WithoutBCVals = new ArrayList();
						if (null != mappingWithoutBC.getEb03()) {
							eb03WithoutBCVals = convertHippaCodeValuesToList(mappingWithoutBC
									.getEb03().getHippaCodeSelectedValues());
						}

						// For the Null check -- Prospective Sev 1 issue
						// reported on LG contracts. End

						if (BxUtil.listCompare(eb03BCValues, eb03WithoutBCVals)) {
							listWithSameEB03Vals.add(mappingWithoutBC);
						}
					}
					
				}
				varblAndListOfVarblsWithSameEB03.put(mappingBC,
						listWithSameEB03Vals);
			}
				
			Iterator iteratorBCMap = varblAndListOfVarblsWithSameEB03.keySet().iterator();
			ContractMappingVO mappingWithEB0360 = null;
			List variablesWithSameEB03 = null;
			boolean isSameEB03ValPresent = false;
			Iterator variablesWithEB0360 = null;
			while (iteratorBCMap.hasNext()) {
				mappingBC = (ContractMappingVO) iteratorBCMap.next();
				isError = false;
				variablesWithSameEB03 = new ArrayList();
				variablesWithSameEB03 = (ArrayList) varblAndListOfVarblsWithSameEB03.get(mappingBC);
				inNetWorkExistsBC = false;
				outNetWorkExistsBC = false;
				InOutNetWorkExistsBC = false;
				if (getInNetworkPVAMappingsForE010().contains(
						mappingBC.getVariable().getPva())) {
					inNetWorkExistsBC = true;
				} else if (getOutNetworkPVAMappingsForE010().contains(
						mappingBC.getVariable().getPva())) {
					outNetWorkExistsBC = true;
				} else if (getInOutNetworkPVAMappingsForE010().contains(
						mappingBC.getVariable().getPva())) {
					InOutNetWorkExistsBC = true;
				}
				
				if (variablesWithSameEB03.size() > 0) {
					Iterator variablesWithSameEB03Iter = variablesWithSameEB03.iterator();
					inNetWorkExists = false;
					outNetWorkExists = false;
					InOutNetWorkExists = false;
					while (variablesWithSameEB03Iter.hasNext()) {
						mappingWithoutBC = (ContractMappingVO) variablesWithSameEB03Iter.next();
						if (getInNetworkPVAMappingsForE010().contains(
								mappingWithoutBC.getVariable().getPva())) {
							inNetWorkExists = true;
						} else if (getOutNetworkPVAMappingsForE010().contains(
								mappingWithoutBC.getVariable().getPva())) {
							outNetWorkExists = true;
						} else if (getInOutNetworkPVAMappingsForE010().contains(
								mappingWithoutBC.getVariable().getPva())) {
							InOutNetWorkExists = true;
						}
					}
					isSameEB03ValPresent = false;
					if (inNetWorkExistsBC) {
						if (inNetWorkExists || InOutNetWorkExists) {
							isSameEB03ValPresent = true;
						}
					} else if (outNetWorkExistsBC) {
						if (outNetWorkExists || InOutNetWorkExists) {
							isSameEB03ValPresent = true;
						}
					} else if (InOutNetWorkExistsBC) {
						if (InOutNetWorkExists || (inNetWorkExists && outNetWorkExists)) {
							isSameEB03ValPresent = true;
						}
					}
					if (!isSameEB03ValPresent) {
						if (variableListEB0360.size() > 0) {
							variablesWithEB0360 = variableListEB0360.iterator();
							inNetWorkExists60 = false;
							outNetWorkExists60 = false;
							InOutNetWorkExists60 = false;
							while (variablesWithEB0360.hasNext()) {
								mappingWithEB0360 = (ContractMappingVO) variablesWithEB0360.next();
								if (getInNetworkPVAMappingsForE010().contains(
										mappingWithEB0360.getVariable().getPva())) {
									inNetWorkExists60 = true;
								} else if (getOutNetworkPVAMappingsForE010().contains(
										mappingWithEB0360.getVariable().getPva())) {
									outNetWorkExists60 = true;
								} else if (getInOutNetworkPVAMappingsForE010().contains(
										mappingWithEB0360.getVariable().getPva())) {
									InOutNetWorkExists60 = true;
								}
							}
							if (inNetWorkExistsBC) {
								if (!(inNetWorkExists60 || InOutNetWorkExists60)) {
									isError = true;
								}
							} else if (outNetWorkExistsBC) {
								if (!(outNetWorkExists60 || InOutNetWorkExists60)) {
									isError = true;
								}
							} else if (InOutNetWorkExistsBC) {
								if (!(InOutNetWorkExists60 || (inNetWorkExists60 && outNetWorkExists60))) {
									isError = true;
								}
							}
						} else {
							isError = true;
						}
					}
				} else if (variableListEB0360.size() > 0) {	// No variable with same EB03
						variablesWithEB0360 = variableListEB0360.iterator();
						inNetWorkExists60 = false;
						outNetWorkExists60 = false;
						InOutNetWorkExists60 = false;
						while (variablesWithEB0360.hasNext()) {
							mappingWithEB0360 = (ContractMappingVO) variablesWithEB0360.next();
							if (getInNetworkPVAMappingsForE010().contains(
									mappingWithEB0360.getVariable().getPva())) {
								inNetWorkExists60 = true;
							} else if (getOutNetworkPVAMappingsForE010().contains(
									mappingWithEB0360.getVariable().getPva())) {
								outNetWorkExists60 = true;
							} else if (getInOutNetworkPVAMappingsForE010().contains(
									mappingWithEB0360.getVariable().getPva())) {
								InOutNetWorkExists60 = true;
							}
						}
						if (inNetWorkExistsBC) {
							if (!(inNetWorkExists60 || InOutNetWorkExists60)) {
								isError = true;
							}
						} else if (outNetWorkExistsBC) {
							if (!(outNetWorkExists60 || InOutNetWorkExists60)) {
								isError = true;
							}
						} else if (InOutNetWorkExistsBC) {
							if (!(InOutNetWorkExists60 || (inNetWorkExists60 && outNetWorkExists60))) {
								isError = true;
							}
						}
					} else {
						isError = true;
					}
				if (isError) {
					HashMap majorHeadingsMap = (HashMap) contract
							.getMajorHeadings();
					if (majorHeadingsMap.size() > 0) {
						MajorHeadingsVO majorHeadingObj = null;
						HashMap minorHeadingsMap = null;
						HashMap mappingsMap = null;
						String keyMinor = null;
						ContractMappingVO mappingObj = null;
						MinorHeadingsVO minorHeadingObj = null;
						ErrorDetailVO errorDetailsObject = null;
						Iterator iteratorMajor = majorHeadingsMap
								.keySet().iterator();
						while (iteratorMajor.hasNext()) {
							String keyMajor = (String) iteratorMajor
									.next();
							majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
									.get(keyMajor);
							// get MinorHeadings Map
							minorHeadingsMap = (HashMap) majorHeadingObj
									.getMinorHeadings();
							Iterator iteratorMinor = minorHeadingsMap
									.keySet().iterator();
							while (iteratorMinor.hasNext()) {
								keyMinor = (String) iteratorMinor
										.next();
								minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
										.get(keyMinor);
								// get Mappings Map
								mappingsMap = (HashMap) minorHeadingObj
										.getMappings();
								Iterator iteratorMappings = mappingsMap
										.entrySet().iterator();
								while (iteratorMappings.hasNext()) {
									// adding the ErrorCodes to the Variable object
									mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
											.next()).getValue();
									if (null != mappingObj) {
										if (mappingObj.getVariable()
												.equals(mappingBC.getVariable())) {
											if (isErrorToBeExcludedVariableLevel(
													errorExclusionDetailVO
															.isE010ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E010,
													errorExclusionDetailVO
															.getVariableExclusionsList(),
													mappingBC.getVariable().getVariableId())) {
												errorDetailsObject = new ErrorDetailVO();
												errorDetailsObject.setError(true);
												errorDetailsObject.setErrorCode(DomainConstants.ERROR_E010);
												errorDetailsObject.setErrorDesc(DomainConstants.E010_DESCRIPTION);
												mappingObj.getErrorCodesList()
														.add(errorDetailsObject);
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
	}

	/**
	 * This method Sets flags for E011 and E012
	 * 
	 * @param mapping
	 * @return
	 */
	private void setFlagForE011AndE012(ContractMappingVO mapping) {
		/**
		 * Checking if EB03 is null
		 */
		HippaSegment hippaSegmentEB03 = mapping.getEb03();

		if (null != hippaSegmentEB03) {
			List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
			if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
				Iterator eb03Iterator = eb03ValuesList.iterator();

				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					//Removed as part of BXNI December Release 
					/*if (DomainConstants.EB03_SP.equals(hippaCodeValue)
							|| DomainConstants.EBO3_SS.equals(hippaCodeValue)) {
						variablesListE011.add(mapping.getVariable());
						break;
					} else*/ if (DomainConstants.EB03_UC.equals(hippaCodeValue)) {
						// Changed UR to UC as mentioned in BTRD for june
						// release
						variablesListEB03UC.add(mapping.getVariable());
						break;
					} else if (DomainConstants.EB03_60.equals(hippaCodeValue)) {
						// List of mapping where EB03 value is 60 for E010 error code.
						variableListEB0360.add(mapping);
					}
				}
			}
		}
	}
	
	/**
	 * This method Sets flags for E011 and E012
	 * 
	 * @param mapping
	 * @return
	 */
	private void setEB03ListForE010(ContractMappingVO mapping) {
		/**
		 * Checking if EB03 is null
		 */
		HippaSegment hippaSegmentEB03 = mapping.getEb03();

		if (null != hippaSegmentEB03) {
			List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
			if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					if (DomainConstants.EB03_60.equals(hippaCodeValue)) {
						// List of mapping where EB03 value is 60 for E010 error code.
						variableListEB0360.add(mapping);
						break;
					}
				}
			}
		}
	}
// This method is removed as part of BXNI December Release
	/**
	 * This method checks condition for Error Code - E011, EB03=SP
	 * Excluding EPO out network
	 * @param contract
	 * @return Variable Level Error
	 *//*
	private void validateE011(ContractVO contract) {
		isPVA_E011_INOUT = false;
		isPVA_E011_IN = false;
		isPVA_E011_OUT = false;
		if (variablesListE011.size() > 0) {
			Iterator variablesEB03Iter = variablesListE011.iterator();
			while (variablesEB03Iter.hasNext()) {
				Variable variableObj = (Variable) variablesEB03Iter.next();
				if (getInNetworkPVAMappingsForE010().contains(
						variableObj.getPva())) {
					isPVA_E011_IN = true;
				} else if (getOutNetworkPVAMappingsForE010().contains(
						variableObj.getPva())) {
					isPVA_E011_OUT = true;
				} else if (getInOutNetworkPVAMappingsForE010().contains(
						variableObj.getPva())) {
					isPVA_E011_INOUT = true;
				}
			}

			boolean isError = false;
			*//**
			 * If pva = all, no error
			 *//*
			if (!isPVA_E011_INOUT) {
				*//**
				 * Checking if PAR is coded
				 *//*
				if (isPVA_E011_IN) {
					if (!isPVA_E011_OUT) {
						*//**
						 * PAR is coded and NPAR is not coded
						 *//*
						isError = true;
					}
				} else {
					*//**
					 * PAR is not coded and NPAR is coded Object to hold error
					 * details Contract Level
					 *//*
					isError = true;
				}
			}
			if (isError) {
				Iterator variableEB03ListIter = variablesListE011.iterator();
				ErrorDetailVO errorDetailsObject = null;
				String keyMajor = null;
				String keyMinor = null;
				ContractMappingVO mappingObj = null;
				MajorHeadingsVO majorHeadingObj = null;
				MinorHeadingsVO minorHeadingObj = null;
				Variable variableEB03 = null;
				while (variableEB03ListIter.hasNext()) {
					variableEB03 = (Variable) variableEB03ListIter.next();
					HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
					if (majorHeadingsMap.size() > 0) {
						Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
						while (iteratorMajor.hasNext()) {
							keyMajor = (String) iteratorMajor.next();
							majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
									.get(keyMajor);
							// get MinorHeadings Map
							HashMap minorHeadingsMap = (HashMap) majorHeadingObj
									.getMinorHeadings();
							Iterator iteratorMinor = minorHeadingsMap.keySet()
									.iterator();
							while (iteratorMinor.hasNext()) {
								keyMinor = (String) iteratorMinor.next();
								minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
										.get(keyMinor);
								// get Mappings Map
								HashMap mappingsMap = (HashMap) minorHeadingObj
										.getMappings();
								Iterator iteratorMappings = mappingsMap
										.entrySet().iterator();
								while (iteratorMappings.hasNext()) {
									// adding the ErrorCodes to the Variable
									// object
									mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
											.next()).getValue();
									if (null != mappingObj) {
										if (mappingObj.getVariable().equals(
												variableEB03)) {
											if (!isErrorToBeExcludedVariableLevel(
													errorExclusionDetailVO
															.isE011ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E011,
													errorExclusionDetailVO
															.getVariableExclusionsList(),
													mappingObj.getVariable()
															.getVariableId())) {
												continue;
											}
											errorDetailsObject = new ErrorDetailVO();
											if (isPVA_E011_IN) {
												if (!contract.isFlagEPO()) {
													errorDetailsObject.setError(true);
													errorDetailsObject
														.setErrorCode(DomainConstants.ERROR_E011);
													errorDetailsObject
														.setErrorDesc(DomainConstants.E011_DESCRIPTION1);
													mappingObj.getErrorCodesList()
														.add(errorDetailsObject);
												}
												break;
											} else if (isPVA_E011_OUT) {
											//	if (!contract.isFlagEPO()) {
													errorDetailsObject.setError(true);
													errorDetailsObject
															.setErrorCode(DomainConstants.ERROR_E011);
													errorDetailsObject
															.setErrorDesc(DomainConstants.E011_DESCRIPTION2);
													mappingObj.getErrorCodesList()
															.add(errorDetailsObject);
													break;
											//	}
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
			// contractlevel error
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
	 * @param contract
	 * @return
	 */
	private void validateE012(ContractVO contract) {
		if (variablesListEB03UC.size() > 0) {
			Iterator variablesEB03UCIter = variablesListEB03UC.iterator();

			while (variablesEB03UCIter.hasNext()) {
				Variable variableUC = (Variable) variablesEB03UCIter.next();
				if (getInNetworkPVAMappingsForE010().contains(
						variableUC.getPva())) {
					isPVA_E012_IN_UC = true;
				} else if (getOutNetworkPVAMappingsForE010().contains(
						variableUC.getPva())) {
					isPVA_E012_OUT_UC = true;
				} else if (getInOutNetworkPVAMappingsForE010().contains(
						variableUC.getPva())) {
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
				Iterator variableEB03CIter = variablesListEB03UC.iterator();
				while (variableEB03CIter.hasNext()) {
					Variable variableUC = (Variable) variableEB03CIter.next();
					HashMap majorHeadingsMap = (HashMap) contract
							.getMajorHeadings();
					if (majorHeadingsMap.size() > 0) {
						Iterator iteratorMajor = majorHeadingsMap.keySet()
								.iterator();
						while (iteratorMajor.hasNext()) {
							String keyMajor = (String) iteratorMajor.next();
							MajorHeadingsVO majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
									.get(keyMajor);
							// get MinorHeadings Map
							HashMap minorHeadingsMap = (HashMap) majorHeadingObj
									.getMinorHeadings();
							Iterator iteratorMinor = minorHeadingsMap.keySet()
									.iterator();
							while (iteratorMinor.hasNext()) {
								String keyMinor = (String) iteratorMinor.next();
								MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
										.get(keyMinor);
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
									if (null != mappingObj) {
										if (mappingObj.getVariable().equals(
												variableUC)) {
											if (!isErrorToBeExcludedVariableLevel(
													errorExclusionDetailVO
															.isE012ExclusionValidationEnableFlag(),
													DomainConstants.ERROR_E012,
													errorExclusionDetailVO
															.getVariableExclusionsList(),
													mappingObj.getVariable()
															.getVariableId())) {
												continue;
											}

											ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
											if (getInNetworkPVAMappingsForE010()
													.contains(
															variableUC.getPva())) {
												if (!contract.isFlagEPO()) {
													errorDetailsObject.setError(true);
													errorDetailsObject
															.setErrorCode(DomainConstants.ERROR_E012);
													errorDetailsObject
															.setErrorDesc(DomainConstants.E012_DESCRIPTION1);
												}
											} else if (getOutNetworkPVAMappingsForE010()
													.contains(
															variableUC.getPva())) {
											//	if (!contract.isFlagEPO()) {
													errorDetailsObject.setError(true);
													errorDetailsObject
															.setErrorCode(DomainConstants.ERROR_E012);
													errorDetailsObject
															.setErrorDesc(DomainConstants.E012_DESCRIPTION2);
											//	}
											}
											mappingObj.getErrorCodesList().add(
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
		} else {
			// contractlevel error
			e012UCContractLevelErrorFlag = true;
		}
	}

	/**
	 * This method checks condition for Error Code - E014
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE014(ContractMappingVO mapping,
			String eb01_Val) {
		if (null != mapping.getEb03()) {
			HippaSegment hippaSegmentEB03 = mapping.getEb03();
			// Checking if EB03 is null
			if (null != hippaSegmentEB03.getHippaCodeSelectedValues()) {
				List eb03ValuesList = convertHippaCodeValuesToList(hippaSegmentEB03
						.getHippaCodeSelectedValues());
				/**
				 * variable has EB03 = SS and SP mapping, then throw error
				 * variable has EB03 = SS and EB01 != B, then throw error
				 */
				if (
					((eb03ValuesList.contains(DomainConstants.EB03_SP))
							&& (eb03ValuesList.contains(DomainConstants.EBO3_SS)))
					|| ((eb03ValuesList.contains(DomainConstants.EBO3_SS))
								&& !DomainConstants.COPAYMENT.equals(eb01_Val))) {
					/**
					 * Object to hold error details variable Level
					 */
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E014);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E014_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}
		}

		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E016
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO May release changes for E016 Validations:: For
	 *         WPD. To look for Variable format as well as
	 */
	private ContractMappingVO validateE016(ContractMappingVO mapping,
			String eb01_Val) {
		// checking if EB03 is any of the values matching the condition for E016
		boolean eb03Flag = false;
		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		if (null != hippaSegmentEB03) {
			List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
			if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					if (getEB03ForE016().contains(hippaCodeValue)) {
						eb03Flag = true;
						break;
					}
				}
			}
		}
		// Checking EB06
		boolean eb06Flag = false;
		HippaSegment hippaSegmentEB06 = mapping.getEb06();
		if (null != hippaSegmentEB06) {
			if (null != hippaSegmentEB06.getHippaCodeSelectedValues()) {
				HippaCodeValue hippaCodeValue06 = (HippaCodeValue) hippaSegmentEB06
						.getHippaCodeSelectedValues().get(0);
				if (DomainConstants.EB06_VALUE_LIFETIME.equals(hippaCodeValue06
						.getValue())) {
					eb06Flag = true;
				}
			}
		}
		// Checking for the variable format
		boolean formatFlag = false;
		String variableFormat = null;
		if (null != mapping.getVariable()) {
			variableFormat = mapping.getVariable().getVariableFormat();
		}
		if (DomainConstants.FORMAT_DOLLAR.equalsIgnoreCase(variableFormat)) {
			formatFlag = true;
		}
		// Checking if EB01 = F and check for EB03 and EB06 and Variable format
		// flags.
		if (DomainConstants.LIMITATIONS.equals(eb01_Val) && eb03Flag
				&& eb06Flag && formatFlag) {
			/**
			 * Object to hold error details variable Level
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E016);
			errorDetailsObject.setErrorDesc(DomainConstants.E016_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		return mapping;
	}

	/**
	 * THis method checks whether the error code is in the exclusion list If
	 * Yes, then sets the flag isErrorTobeExcluded as true.
	 * 
	 * @param List
	 *            , String, boolean, String
	 * @return boolean
	 */
	private boolean isErrorToBeExcludedVariableLevel(boolean errorFlag,
			String errorCode, List variablelist, String variableId) {
		if (errorFlag) {
			if (null != variablelist && variablelist.size() > 0) {
				Iterator itr = variablelist.iterator();
				ErrorExclusionVO errorExclusionVO = null;
				if (null != variableId
						&& !variableId.equals(DomainConstants.EMPTY)) {
					while (itr.hasNext()) {
						errorExclusionVO = (ErrorExclusionVO) itr.next();
						String variablesComaSeparatedPrimary = ReferenceDataUtil
								.trimAndNullToEmptyString(errorExclusionVO
										.getPrimaryValues());
						String variablesComaSeparatedSecondary = ReferenceDataUtil
								.trimAndNullToEmptyString(errorExclusionVO
										.getSecondaryValues());
						if (errorExclusionVO.getErrorCode().equals(errorCode)
								&& (variablesComaSeparatedPrimary
										.indexOf(variableId.toUpperCase()) != -1 || variablesComaSeparatedSecondary
										.indexOf(variableId.toUpperCase()) != -1)) {
							return false;
						}

					}
				}
			}
		}
		return errorFlag;
	}

	/**
	 * This method checks condition for Error Code - E017 The error is reported
	 * for HCR contracts if variable mapping has EB03= ('2 ' '4 ' '5 ' '7 ' '8
	 * ' '12' '13' '18' '33' '40' '45' '60' '73' '76' '78' '88' '98' 'A7' 'A8'
	 * 'AD' 'AE' 'AF' 'AI') and EB01=F and EB06=22 or 23and if format is dollar
	 * for variable
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE017(ContractMappingVO mapping,
			String eb01_Val) {
		// for variable with dollar format only this error applies.
		if (DomainConstants.FORMAT_DOLLAR.equals(mapping.getVariable()
				.getVariableFormat())) {
			boolean hippacodeEB03forE017 = false;
			boolean hippacodeEB06forE017 = false;

			if (null != mapping.getEb03()) {
				if (null != mapping.getEb03().getHippaCodeSelectedValues()
						&& mapping.getEb03().getHippaCodeSelectedValues()
								.size() > 0) {
					List eb03ValuesList = mapping.getEb03()
							.getHippaCodeSelectedValues();
					Iterator eb03Iterator = eb03ValuesList.iterator();
					while (eb03Iterator.hasNext()) {
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
								.next();
						String hippaCodeValue = hippaCodeValue03.getValue();
						if (getEB03ForE017().contains(hippaCodeValue)) {
							hippacodeEB03forE017 = true;
							break;
						}
					}
				}
			}
			// Check if EB06=22 or 23
			String hippaCode06Value = null;
			HippaSegment hippaSegmentEB06 = mapping.getEb06();
			if (null != hippaSegmentEB06) {
				if (null != hippaSegmentEB06.getHippaCodeSelectedValues()
						&& hippaSegmentEB06.getHippaCodeSelectedValues().size() > 0) {
					HippaCodeValue hippaCodeValue06 = (HippaCodeValue) hippaSegmentEB06
							.getHippaCodeSelectedValues().get(0);
					hippaCode06Value = hippaCodeValue06.getValue();
				} else {
					hippaCode06Value = DomainConstants.EB06_BLANK;
				}
			} else {
				hippaCode06Value = DomainConstants.EB06_BLANK;
			}
			if (DomainConstants.EB06_22.equals(hippaCode06Value)
					|| DomainConstants.EB06_23.equals(hippaCode06Value) 
					|| DomainConstants.EB06_BLANK.equals(hippaCode06Value)) {
				hippacodeEB06forE017 = true;
			}
			// Checking if EB01 = F
			if (hippacodeEB03forE017
					&& DomainConstants.LIMITATIONS.equals(eb01_Val)
					&& hippacodeEB06forE017) {
				/**
				 * Object to hold error details variable Level
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E017);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E017_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);

			}
		}
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E018 This error is thrown
	 * for HCR contracts if variable mapping EB03 is any of '68' 'BH' '80' '81'
	 *  BZ or CO or CL or CK
	 * and type is PCT or DOL. For PCT format (EB01 = A), throw error only if
	 * variable is IN-NETWORK or IN OUT NETWORK and value coded on benefit is
	 * not 100%. Throw an error if a DOL format value is coded for the above
	 * EB03s in the contract for IN-NETWORK or IN OUT NETWORK.
	 * 
	 * @param mapping
	 * @param eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE018(ContractMappingVO mapping,
			String eb01_Val) {
		// Checking for EB03 values
		boolean isEB03ValsExist = false;
		boolean isError = false;
		if (null != mapping.getEb03()) {
			if (null != mapping.getEb03().getHippaCodeSelectedValues()
					&& mapping.getEb03().getHippaCodeSelectedValues().size() > 0) {
				List eb03ValuesList = mapping.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					if (getEB03ForE018().contains(hippaCodeValue)) {
						isEB03ValsExist = true;
					}
				}
			}
		}
		String variableValue = mapping.getVariable().getVariableValue();
		String format = mapping.getVariable().getVariableFormat();
		String pva = mapping.getVariable().getPva();
		String eb0l_Val = "";

		if (null != mapping.getEb01()) {
			HippaSegment eb01 = mapping.getEb01();
			HippaCodeValue hippaCodeValue01 = (HippaCodeValue) eb01
					.getHippaCodeSelectedValues().get(0);
			eb0l_Val = hippaCodeValue01.getValue();
		}

		if (isEB03ValsExist && DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
			if (DomainConstants.COINSURANCE.equals(eb0l_Val)
					&& !DomainConstants.EXCLSN_E018_BENFTVAL
							.equals(variableValue)
					&& (getInNetworkPVAMappings().contains(pva) || getInOutNetworkPVAMappings()
							.contains(pva))) {
				/*
				 * ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				 * errorDetailsObject.setError(true);
				 * errorDetailsObject.setErrorCode(DomainConstants.ERROR_E018);
				 * errorDetailsObject
				 * .setErrorDesc(DomainConstants.E018_DESCRIPTION);
				 * mapping.getErrorCodesList().add(errorDetailsObject);
				 */
				isError = true;
			}
		} else if (isEB03ValsExist
				&& DomainConstants.FORMAT_DOLLAR.equals(format)
				&& (getInNetworkPVAMappings().contains(pva) || getInOutNetworkPVAMappings()
						.contains(pva))) {
			/*
			 * ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			 * errorDetailsObject.setError(true);
			 * errorDetailsObject.setErrorCode(DomainConstants.ERROR_E018);
			 * errorDetailsObject
			 * .setErrorDesc(DomainConstants.E018_DESCRIPTION);
			 * mapping.getErrorCodesList().add(errorDetailsObject);
			 */
			isError = true;
		}
		if (isError) {
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E018);
			errorDetailsObject.setErrorDesc(DomainConstants.E018_DESCRIPTION);
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		return mapping;
	}

	

	/**
	 * The method creates a contract level error in the contract object if there
	 * is no EB03=UC
	 * 
	 * @param contract
	 * @return
	 */
	private void setContractLevelErrorForE012(ContractVO contract) {
		if (e012UCContractLevelErrorFlag) {
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E012);
			errorDetailsObject.setErrorDesc(DomainConstants.E012_DESCRIPTION3);
			contract.getErrorCodesList().add(errorDetailsObject);
		}
	}

	/**
	 * This method checks condition for Error Code - E020
	 * 
	 * @param mapping
	 * @return
	 */
	/**
	 * Code logic is changed as part of BXNI December release
	 * The E020 error will be reported if the variable is mapped to 
	 * EB09 = DY and EB06 as 7 (Day ) or 13 (24 hour )
	 * EB09 = HS and EB06 as 6 (hour ) 
	 * EB09 = MN and EB06 as 34 (month ) 
	 * EB09 = VS and EB06 as 27 (visit ) 
	 * EB09 = YY and EB06 value is 21 (year) or 22 (service year) or 23 (calendar year) or blank
	 * 
	 * Below stated HSD Validations are covered in HSDValidator Class
	 * HSD01 = DY and HSD05 as 7 (Day ) or 13 (24 hour )
	 * HSD01 = HS and HSD05 as 6 (hour )
	 * HSD01 = MN and HSD05 as 34 (month) 
	 * HSD01 = VS and HSD05 as 27 (visit ) 
	 * 
	 */
	private ContractMappingVO validateE020(ContractMappingVO mapping) {
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

		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E021
	 * 
	 * @param ContractMappingVO
	 *            mapping
	 * @param String
	 *            ebol_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE021(ContractMappingVO mapping,
			String ebol_Val) {
		String linepvavalue = mapping.getVariable().getPva();
		String format = mapping.getVariable().getVariableFormat();
		String linevalue = mapping.getVariable().getVariableValue();
		int percentage = 0;
		boolean errorflag = false;
		if (null != mapping.getEb01()
				&& DomainConstants.EB01_A.equals(ebol_Val)) {
			if (null != format
					&& DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
				if (null != linevalue && !"".equals(linevalue)) {
					try {
						percentage = Integer.parseInt(linevalue);
					} catch (NumberFormatException e) {
						log.debug("Invalid linevalue value  format");
					}
				}
				if (null != linepvavalue) {
					linepvavalue = linepvavalue.toUpperCase();
				}
				if (getNetworkPVAMappingsForE021group1().contains(linepvavalue)) {
					if (percentage > 0 & percentage <= 40) {
						errorflag = true;
					}
				} else if (getNetworkPVAMappingsForE021group2().contains(
						linepvavalue)) {
					if (percentage > 0 & percentage <= 15) {
						errorflag = true;
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
		return mapping;
	}

	/**
	 * The method validates E022 If EB01=A and EB06 coded as 21/22/23/32/BLANK
	 * Error will not be reported for contract variables having 
	 * COINS, COINSURANCE, PENALTY, PENLTY, PNLTY in the description.
	 * @param mapping
	 * @return
	 */
	private void validateE022(ContractMappingVO mapping) {
		if (null != mapping) {
			
			boolean exclusionFlag = false;
			String variableDescription = null;
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
			// set the value to BLANK if the value is null or SPACE
			if (null != hippaCode06Value) {
				hippaCode06Value = hippaCode06Value.trim();
			} else {
				hippaCode06Value = "";
			}
			if ((null != mapping.getVariable() && null != mapping.getVariable().getDescription() 
					&& !DomainConstants.EMPTY.equals(mapping.getVariable().getDescription()))) {
				variableDescription = mapping.getVariable().getDescription().toUpperCase();
				// Exclusion for E022
				if (-1 != (variableDescription.indexOf(DomainConstants.SUBSTRING_COINS)) 
						|| -1 != (variableDescription.indexOf(DomainConstants.SUBSTRING_COINSURANCE))
						|| -1 != (variableDescription.indexOf(DomainConstants.SUBSTRING_PENALTY))
						|| -1 != (variableDescription.indexOf(DomainConstants.SUBSTRING_PENLTY))
						|| -1 != (variableDescription.indexOf(DomainConstants.SUBSTRING_PNLTY))) {
					exclusionFlag = true;
				}
			}
			// EB01=B and EB06=21/22/23/32/BLANK
			if (!exclusionFlag && DomainConstants.EB01_B.equals(hippaCode01Value)
					&& (DomainConstants.EB06_21.equals(hippaCode06Value)
							|| DomainConstants.EB06_22.equals(hippaCode06Value)
							|| DomainConstants.EB06_23.equals(hippaCode06Value)
							|| DomainConstants.EB06_32.equals(hippaCode06Value) || DomainConstants.EB06_BLANK
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
				if (DomainConstants.SYSTEM_LG.endsWith(contract.getSystem()
						.toUpperCase())) {
					if (DomainConstants.EB06_22.equals(hippaCode06Value)
							&& DomainConstants.BENEFITYEAR_N.equals(contract
									.getAnswerCalYearOrBenYear())
							|| DomainConstants.EB06_23.equals(hippaCode06Value)
							&& DomainConstants.BENEFITYEAR_Y.equals(contract
									.getAnswerCalYearOrBenYear())) {
						ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
						errorDetailsObject.setError(true);
						errorDetailsObject
								.setErrorCode(DomainConstants.ERROR_E023);
						errorDetailsObject
								.setErrorDesc(DomainConstants.E023_DESCRIPTION);
						mapping.getErrorCodesList().add(errorDetailsObject);
					}

				} else if (DomainConstants.SYSTEM_ISG.endsWith(contract
						.getSystem().toUpperCase())) {
					if (DomainConstants.EB06_22.equals(hippaCode06Value)
							&& DomainConstants.CALENDARYEAR.equals(contract
									.getAnswerCalYearOrBenYear())
							|| DomainConstants.EB06_23.equals(hippaCode06Value)
							&& DomainConstants.BENEFITYEAR.equals(contract
									.getAnswerCalYearOrBenYear())) {
						ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
						errorDetailsObject.setError(true);
						errorDetailsObject
								.setErrorCode(DomainConstants.ERROR_E023);
						errorDetailsObject
								.setErrorDesc(DomainConstants.E023_DESCRIPTION);
						mapping.getErrorCodesList().add(errorDetailsObject);
					}
				}
			}
		}
	}

	/**
	 * Method to check whether the mapping has EB03Exclusion for E010
	 * 
	 * @param mapping
	 * @return boolean
	 */
	private boolean isEB03ExclusionExistsForE010(ContractMappingVO mapping) {
		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		boolean isEB03ExclusionsExist = false;
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

					if (getEB03ForE010().contains(hippaCodeValue03.getValue())) {
						isEB03ExclusionsExist = true;
						break;
					}
				}
			}
		}
		return isEB03ExclusionsExist;
	}

	/**
	 * This method checks condition for Error Code - E026 the error is reported
	 * 
	 * If EB01=B & EB06=26 & EB03 = 98 / 99 / 68 / 80 / 81 / 82 / 83 / BH /
	 * ‘SS/SP’ / UC / 33 / A6 / AD / AE / AF / 6 / 48 / 47 / 69 / 65 / A7 / AI /
	 * 50 / 13 / 53 / 52 / 51 / 86 / A8 / 61 / 62 / 76 / 78 / 84 / 93 / A0 / A3
	 * / AG / BG / 12 / 18 / 45
	 * 
	 * If EB01=B & EB06=27 & EB03 = 48 / 47 / 65 / A7 / AG
	 * 
	 * If EB01=B & EB06=36 & EB03 = 50 / 52 / 51 / 86 / A8 / 12 / 18 / 62 / 4 /
	 * 5 / 73
	 * 
	 * If EB01=B & EB06=7 & EB03 = 50 / 52 / 51 / 86 / A8 / 62 / 4 / 5 / 73
	 * 
	 * @param mapping
	 *            , eb01_Val
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE026(ContractMappingVO mapping,
			String eb01_Val) {
		if (null != mapping.getEb03()) {
			if (null != mapping.getEb03().getHippaCodeSelectedValues()
					&& mapping.getEb03().getHippaCodeSelectedValues().size() > 0) {
				List eb03ValuesList = mapping.getEb03()
						.getHippaCodeSelectedValues();
				if (ContractMappingValidator.checkForRuleE026(eb01_Val,
						eb03ValuesList, mapping.getEB06Value())) {
					/**
					 * Object to hold error details variable Level
					 */
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E026);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E026_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}
		}
		return mapping;
	}

	/**
	 * Error Validation for E025. Excludes - category codes falls in
	 * "excluded.category.e025" in errorvalidator.properties Includes - No Bx
	 * mapping and and the data type format falls in "e025.data.type.format" in
	 * errorValidator.properties
	 * 
	 * @param mapping
	 * @param keyMinor
	 * @return
	 */
	
	/**
	 * Logic:The error must be reported for any contract variable with Data Type format
	 * is PCT,DOL,VST,OCRS,OCC,DAYS,DAY,LEN,HRS,AGE,MTHS,MTH,YRS and if there is no BX mapping. 
	 */
	
	private ContractMappingVO validateE025(ContractMappingVO mapping,
			String keyMinor) {
		boolean exclusionFlagE025 = false;
		String variableFormat = "";
		String categoryId = null;
		if (null != mapping.getVariable()) {
			if (null != mapping.getVariable().getVariableFormat()) {
				variableFormat = mapping.getVariable().getVariableFormat().trim();
				if (DomainConstants.FORMAT_DOLLAR.equalsIgnoreCase(variableFormat)) {
					String varVal =  mapping.getVariable().getVariableValue();
					if (null != varVal) {
						varVal = varVal.trim();
						if (BxUtil.isNumeric(varVal)) {
							if (50000 <= Double.parseDouble(varVal)) {
								exclusionFlagE025 = true;
							}
						}
					}
				}
			}
			if (null != mapping.getVariable().getLgCatagory()) {
				categoryId = mapping.getVariable().getLgCatagory().trim();
			}
			if (null != mapping.getVariable().getIsgCatagory()) {
				categoryId = mapping.getVariable().getIsgCatagory().trim();
			}
		}
		
		// Four conditions other than the Category Id, where E025 to be excluded
		if (DomainConstants.INDIV_AND_FAMILY_DEDUCTIBLES
				.equalsIgnoreCase(keyMinor)
				|| DomainConstants.STOP_LOSS_LIMITATIONS
						.equalsIgnoreCase(keyMinor)) {
			if (DomainConstants.OCRS.equalsIgnoreCase(variableFormat)
					|| DomainConstants.OCC.equalsIgnoreCase(variableFormat)) {
				exclusionFlagE025 = true;
			}
			if (DomainConstants.DAY.equalsIgnoreCase(variableFormat)
					|| DomainConstants.DAYS.equalsIgnoreCase(variableFormat)||
					DomainConstants.LEN.equalsIgnoreCase(variableFormat)) {
				exclusionFlagE025 = true;
			}
		}
		/*else if (DomainConstants.ELIGIBILITY_E025.equalsIgnoreCase(keyMinor)
				&& DomainConstants.AGE.equalsIgnoreCase(variableFormat)) {

			exclusionFlagE025 = true;
		}*/ else if (DomainConstants.ELIGIBILITY_E025.equalsIgnoreCase(keyMinor)) {

			if (DomainConstants.AGE.equalsIgnoreCase(variableFormat)
					|| DomainConstants.MTH.equalsIgnoreCase(variableFormat)
					|| DomainConstants.YRS.equalsIgnoreCase(variableFormat)
					|| DomainConstants.MTHS.equalsIgnoreCase(variableFormat)) {
				exclusionFlagE025 = true;
			}
		} else if (DomainConstants.CONTRACT_ADMINISTRATION
				.equalsIgnoreCase(keyMinor)) {
			if (DomainConstants.MTH.equalsIgnoreCase(variableFormat)
					|| DomainConstants.MTHS.equalsIgnoreCase(variableFormat)) {
				exclusionFlagE025 = true;
			}
				
		}

		if (!NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())) {
			if (!getExclusionListForE025().contains(categoryId)) {
				if (!mapping.isMapped()
						&& getDataTypeFormatForE025().contains(variableFormat)
						&& !exclusionFlagE025) {
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E025);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E025_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}			
			
		}
		return mapping;
	}

	/**
	 * Error Validation for E029. Includes - The rule shall be identified if the
	 * contract variable coded in the BX table has ‘worldwide’ or ‘WW’ in the
	 * variable description.
	 * 
	 * @param mapping
	 * @returnR
	 */
private ContractMappingVO validateE029(ContractMappingVO mapping,
			MinorHeadingsVO minorHeadingObj, MajorHeadingsVO majorHeadingObj ) {
		if (null != mapping && null != minorHeadingObj && null != majorHeadingObj 
				&& null != mapping.getVariable() && null != mapping.getVariable().getDescription()) {
			if (ContractMappingValidator.checkForRuleE029(mapping.getVariable().getDescription(), minorHeadingObj.getDescriptionText(), majorHeadingObj.getDescriptionText())) {
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E029);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E029_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
		}
		return mapping;
	}


	/**
	 * This method checks condition for Error Code - E030 If EB01 = 'G', and
	 * EB03 not in 30/60/88/48/CE/CF/CG/CH, E032 is thrown
	 * 
	 * @param mapping
	 * 
	 * @return
	 */
	private void validateE030(ContractMappingVO mapping) {
		if (null != mapping) {
			// Checking if EB01 is null and Empty
			if (null != mapping.getEb03()) {
				List eb03ValuesList = mapping.getEb03()
						.getHippaCodeSelectedValues();
				if (ContractMappingValidator.checkForRuleE030(mapping
						.getEB01Value(), eb03ValuesList)) {
					/**
					 * Object to hold error details variable Level
					 */
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E030);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E030_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}
		}
	}

	/**
	 * This method checks condition for Error Code - E031 
	 * If any of the below condition satisfies, Error E031 is generated
	 * 
	 * The error code E031 is reported when at least one  accumulator  mapped to the variable
	 * and if the EB06 and lookup Indicator of the mapped  accumulator satisfy the following condition. 
	 * EB06 = 32 and Look Up Indicator not equal to  LT
	 * EB06 = 22 and Look Up Indicator not equal to  TB/BY/CT
	 * EB06 = 23 Look Up Indicator not equal to TB/CY/CT
	 * EB06 = 25 or null  and Look Up Indicator not equal to TB/CY/CT/BY
	 * 
	 * @param ContractMappingVO
	 *             accumIndList
	 * 
	 * @return mapping
	 */
	private void validateE031(ContractMappingVO mapping, List accumDBList) {
		if (null != mapping && null != accumDBList && !accumDBList.isEmpty()) {
			// Checking if EB06 is null and Empty
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
					List accumList = mapping.getAccum()
							.getHippaCodeSelectedValues();
					for (int j = 0; j < accumList.size(); j++) {
						HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accumList
								.get(j);
						accumValue = hippaCodeValueAccum.getValue().trim();
						if (checkForRuleE031(eb06_Value, accumValue,
								accumDBList)) {
							if (E031AccumValue.length() == 0) {
								E031AccumValue.append(accumValue);
							} else if (E031AccumValue.indexOf(accumValue) == -1) {
								E031AccumValue.append(", ").append(accumValue);
							}
						}
					}
					if (!NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())) {
						if (E031AccumValue.length() != 0) {
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject
							.setErrorCode(DomainConstants.ERROR_E031);
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
	 * The error code E032 is reported when at least one  accumulator is mapped to the variable
	 * and contract data type not null and satisfies any of the below condition.
	 * Data type = DAY/DAYS/LEN and Days Flag = ‘N’ 
	 * Data type = VST/OCRS and Occurs Flag = ‘N’ 
	 * Data type = DOL and Monies Flag = ‘N’ 
	 * 
	 * @param ContractMappingVO
	 *           ,accumIndList
	 * 
	 * @return mapping
	 */
	private void validateE032(ContractMappingVO mapping, List accumDBList) {
		if (null != mapping && null != accumDBList && !accumDBList.isEmpty()) {

			String accumValue = null;
			StringBuffer E032AccumValue = new StringBuffer();
			if (null != mapping.getVariable().getVariableFormat()
					&& SimulationResourceValueLoader
							.getVariableFormatForE032()
							.contains(mapping.getVariable().getVariableFormat())) {
				// Checking list is null
				HippaSegment accum = mapping.getAccum();

				if (null != accum) {
					List accumList = mapping.getAccum()
							.getHippaCodeSelectedValues();
					for (int j = 0; j < accumList.size(); j++) {
						HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accumList
								.get(j);
						accumValue = hippaCodeValueAccum.getValue();
						if (checkForRuleE032(mapping.getVariable().getVariableFormat(), accumValue, accumDBList)) {
							if (E032AccumValue.length() == 0) {
								E032AccumValue.append(accumValue);
							} else if (E032AccumValue.indexOf(accumValue) == -1) {
								E032AccumValue.append(", ").append(accumValue);
							}
						}
					}
					if (!NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())) {
						if (E032AccumValue.length() != 0) {
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject
							.setErrorCode(DomainConstants.ERROR_E032);
							errorDetailsObject
							.setErrorDesc(DomainConstants.E032_DESCRIPTION_START
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
	 * The error code E033 is reported when at least one  accumulator is mapped to the variable
	 * and EB02 is not null and satisfies any of the below condition.
	 * 
	 * EB02= IND and ROOT-MBR-CODE = FAC
	 * EB02= FAM and ROOT-MBR-CODE = MAC
	 * 
	 * @param ContractMappingVO
	 *            , accumIndList
	 * 
	 * @return mapping
	 */
	private void validateE033(ContractMappingVO mapping, List accumDBList) {
		if (null != mapping && null != accumDBList && !accumDBList.isEmpty()) {
			HippaSegment eb02 = mapping.getEb02();
			String accumValue = null;
			StringBuffer E033AccumValue = new StringBuffer();
			/**
			 * Checking if EB02 value is null
			 */
			if (null != mapping.getEb02()) {
				HippaCodeValue hippaCodeValue02 = (HippaCodeValue)  eb02
				.getHippaCodeSelectedValues().get(0);
				String eb02_Value = hippaCodeValue02.getValue();
				HippaSegment accum = mapping.getAccum();

				if (null != accum) {
					List accumList = mapping.getAccum()
					.getHippaCodeSelectedValues();
					for (int j = 0; j < accumList.size(); j++) {
						HippaCodeValue hippaCodeValueAccum = (HippaCodeValue) accumList
						.get(j);
						accumValue = hippaCodeValueAccum.getValue();
						if (checkForRuleE033(eb02_Value, accumValue, accumDBList)) {
							if (E033AccumValue.length() == 0) {
								E033AccumValue.append(accumValue);
							} else if (E033AccumValue.indexOf(accumValue) == -1) {
								E033AccumValue.append(", ").append(accumValue);
							}
						}
					}
					if (!NOT_APPLICABLE.equals(mapping.getVariableMappingStatus())) {
						if (E033AccumValue.length() != 0) {
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
	 * This method will is to add the EB03 values to the corresponding Hash Set-
	 * if the required condition satisfies.
	 * 
	 * @param mappingObj
	 * @param ebol_Val
	 */
	private void getRequiredEB03ListForE027(ContractMappingVO mappingObj,
			String ebol_Val) {
		String netWorkIndicator = "";
		if (null != mappingObj.getVariable()
				&& null != mappingObj.getVariable().getPva()) {
			netWorkIndicator = mappingObj.getVariable().getPva().trim();
		}
		if (DomainConstants.EB01_A.equals(ebol_Val)
				&& getInNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03AIN.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_A.equals(ebol_Val)
				&& getOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03AOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_A.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03AINOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_B.equals(ebol_Val)
				&& getInNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03BIN.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_B.equals(ebol_Val)
				&& getOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03BOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_B.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03BINOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_BC.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03BCINOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_DW.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03DWINOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_G.equals(ebol_Val)
				&& getOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03GOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_G.equals(ebol_Val)
				&& getInNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03GIN.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_G.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03GINOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_F.equals(ebol_Val)
				&& getOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03FOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_F.equals(ebol_Val)
				&& getInNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03FIN.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_F.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03FINOUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_C.equals(ebol_Val)
				&& getOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03COUT.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_C.equals(ebol_Val)
				&& getInNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03CIN.add(hippaCodeValue);
				}
			}
		} else if (DomainConstants.EB01_C.equals(ebol_Val)
				&& getInOutNetworkPVAMappings().contains(netWorkIndicator)) {
			if (null != mappingObj.getEb03()) {
				List eb03ValuesList = mappingObj.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();
					String hippaCodeValue = hippaCodeValue03.getValue();
					eb03CINOUT.add(hippaCodeValue);
				}
			}
		}
	}

	/**
	 * This method will throw E027 against the SPS, if the SPS in an EPO.
	 * contract has a network indicator ALL for any of the service type. This
	 * validation applies only for WPD LG system
	 * 
	 * @param contract
	 * @param mapping
	 */
	private void validateE027ForEPO(ContractVO contract,
			ContractMappingVO mapping) {
		if (DomainConstants.SYSTEM_LG.endsWith(contract.getSystem()
				.toUpperCase())) {
			if (null != mapping) {
				String networkIndicator = "";
				if (null != mapping.getVariable()
						&& null != mapping.getVariable().getPva()) {
					networkIndicator = mapping.getVariable().getPva().trim();
				}
				if (DomainConstants.PVA_ALL.equalsIgnoreCase(networkIndicator)) {
					ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E027);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E027_DESCRIPTION);
					mapping.getErrorCodesList().add(errorDetailsObject);
				}
			}
		}
	}

	/**
	 * This method will validate the E027 error at contract level.
	 * 
	 * @param contract
	 */
	private void validateE027(ContractVO contract) {
		StringBuffer eb03ValForEb01BC = new StringBuffer();
		StringBuffer eb03ValForEb01DW = new StringBuffer();
		StringBuffer eb03ValForEb01G = new StringBuffer();
		StringBuffer eb03ValForEb01F = new StringBuffer();
		StringBuffer eb03ValForEb01C = new StringBuffer();

		if (null != eb03BCINOUT && eb03BCINOUT.size() > 0) { // Validating E027
			// for EB01= BC
			// and NI =
			// INOUT
			Iterator eb03BCALLIterator = eb03BCINOUT.iterator();
			while (eb03BCALLIterator.hasNext()) {
				String eb03Value = (String) eb03BCALLIterator.next();
				if (null != eb03Value) {
					if (checkInOutNetworkErrorConditionForE027(eb03Value)) { // Calling
						// error
						// check
						// function
						if (!"".equals(eb03ValForEb01BC.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) { // Add eb03Value to the
								// String, if not already
								// available.
								eb03ValForEb01BC.append(",");
								eb03ValForEb01BC.append(eb03Value);
							}
						} else { // Add eb03Value to the String, if string is
							// empty
							eb03ValForEb01BC.append(eb03Value);
						}
					}
				}
			}
		}
		if (null != eb03DWINOUT && eb03DWINOUT.size() > 0) { // Validating E027
			// for EB01= DW
			// and NI =
			// INOUT
			Iterator eb03DWALLIterator = eb03DWINOUT.iterator();
			while (eb03DWALLIterator.hasNext()) {
				String eb03Value = (String) eb03DWALLIterator.next();
				if (null != eb03Value) {
					if (checkInOutNetworkErrorConditionForE027(eb03Value)) {
						if (!"".equals(eb03ValForEb01DW.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) {
								eb03ValForEb01DW.append(",");
								eb03ValForEb01DW.append(eb03Value);
							}
						} else {
							eb03ValForEb01DW.append(eb03Value);
						}
					}
				}
			}
		}
		if (null != eb03GOUT && eb03GOUT.size() > 0) { // Validating E027 for
			// EB01= G and NI = OUT
			Iterator eb03GOUTIterator = eb03GOUT.iterator();
			while (eb03GOUTIterator.hasNext()) {
				String eb03Value = (String) eb03GOUTIterator.next();
				if (null != eb03Value) {
					if (checkOutNetworkErrorConditionForE027(eb03Value)) {
						if (!"".equals(eb03ValForEb01G.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) {
								eb03ValForEb01G.append(",");
								eb03ValForEb01G.append(eb03Value);
							}
						} else {
							eb03ValForEb01G.append(eb03Value);
						}
					}
				}
			}
		}
		if (null != eb03GIN && eb03GIN.size() > 0) { // Validating E027 for
			// EB01= G and NI = IN
			Iterator eb03GINIterator = eb03GIN.iterator();
			while (eb03GINIterator.hasNext()) {
				String eb03Value = (String) eb03GINIterator.next();
				if (null != eb03Value) {
					if (checkInNetworkErrorConditionForE027(eb03Value)) {
						if (!"".equals(eb03ValForEb01G.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) {
								eb03ValForEb01G.append(",");
								eb03ValForEb01G.append(eb03Value);
							}
						} else {
							eb03ValForEb01G.append(eb03Value);
						}
					}
				}
			}
		}
		if (null != eb03GINOUT && eb03GINOUT.size() > 0) { // Validating E027
			// for EB01= G and
			// NI = INOUT
			Iterator eb03GINOUTIterator = eb03GINOUT.iterator();
			while (eb03GINOUTIterator.hasNext()) {
				String eb03Value = (String) eb03GINOUTIterator.next();
				if (checkInOutNetworkErrorConditionForE027(contract, eb03Value)) {
					if (!"".equals(eb03ValForEb01G.toString())) {
						if (-1 == eb03ValForEb01C.toString().indexOf(eb03Value)) {
							eb03ValForEb01G.append(",");
							eb03ValForEb01G.append(eb03Value);
						}
					} else {
						eb03ValForEb01G.append(eb03Value);
					}
				}
			}
		}
		if (null != eb03FOUT && eb03FOUT.size() > 0) { // Validating E027 for
			// EB01= F and NI = OUT
			Iterator eb03FOUTIterator = eb03FOUT.iterator();
			while (eb03FOUTIterator.hasNext()) {
				String eb03Value = (String) eb03FOUTIterator.next();
				if (checkOutNetworkErrorConditionForE027(eb03Value)) {
					if (!"".equals(eb03ValForEb01F.toString())) {
						if (-1 == eb03ValForEb01C.toString().indexOf(eb03Value)) {
							eb03ValForEb01F.append(",");
							eb03ValForEb01F.append(eb03Value);
						}
					} else {
						eb03ValForEb01F.append(eb03Value);
					}
				}
			}
		}
		if (null != eb03FIN && eb03FIN.size() > 0) { // Validating E027 for
			// EB01= F and NI = IN
			Iterator eb03FINIterator = eb03FIN.iterator();
			while (eb03FINIterator.hasNext()) {
				String eb03Value = (String) eb03FINIterator.next();
				if (checkInNetworkErrorConditionForE027(eb03Value)) {
					if (!"".equals(eb03ValForEb01F.toString())) {
						if (-1 == eb03ValForEb01C.toString().indexOf(eb03Value)) {
							eb03ValForEb01F.append(",");
							eb03ValForEb01F.append(eb03Value);
						}
					} else {
						eb03ValForEb01F.append(eb03Value);
					}
				}
			}
		}
		if (null != eb03FINOUT && eb03FINOUT.size() > 0) { // Validating E027
			// for EB01= F and
			// NI = INOUT
			Iterator eb03FINOUTIterator = eb03FINOUT.iterator();
			while (eb03FINOUTIterator.hasNext()) {
				String eb03Value = (String) eb03FINOUTIterator.next();
				if (checkInOutNetworkErrorConditionForE027(contract, eb03Value)) {
					if (!"".equals(eb03ValForEb01F.toString())) {
						if (-1 == eb03ValForEb01C.toString().indexOf(eb03Value)) {
							eb03ValForEb01F.append(",");
							eb03ValForEb01F.append(eb03Value);
						}
					} else {
						eb03ValForEb01F.append(eb03Value);
					}
				}
			}
		}
		if (null != eb03COUT && eb03COUT.size() > 0) { // Validating E027 for
			// EB01= C and NI = OUT
			Iterator eb03COUTIterator = eb03COUT.iterator();
			while (eb03COUTIterator.hasNext()) {
				String eb03Value = (String) eb03COUTIterator.next();
				if (null != eb03Value) {
					if (checkOutNetworkErrorConditionForE027(eb03Value)) {
						if (!"".equals(eb03ValForEb01C.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) {
								eb03ValForEb01C.append(",");
								eb03ValForEb01C.append(eb03Value);
							}
						} else {
							eb03ValForEb01C.append(eb03Value);
						}
					}
				}
			}
		}
		if (null != eb03CIN && eb03CIN.size() > 0) { // Validating E027 for
			// EB01= C and NI = IN
			Iterator eb03CINIterator = eb03CIN.iterator();
			while (eb03CINIterator.hasNext()) {
				String eb03Value = (String) eb03CINIterator.next();
				if (null != eb03Value) {
					if (checkInNetworkErrorConditionForE027(eb03Value)) {
						if (!"".equals(eb03ValForEb01C.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) {
								eb03ValForEb01C.append(",");
								eb03ValForEb01C.append(eb03Value);
							}
						} else {
							eb03ValForEb01C.append(eb03Value);
						}
					}
				}
			}
		}
		if (null != eb03CINOUT && eb03CINOUT.size() > 0) { // Validating E027
			// for EB01= C and
			// NI = INOUT
			Iterator eb03CINOUTIterator = eb03CINOUT.iterator();
			while (eb03CINOUTIterator.hasNext()) {
				String eb03Value = (String) eb03CINOUTIterator.next();
				if (null != eb03Value) {
					if (checkInOutNetworkErrorConditionForE027(eb03Value)) {
						if (!"".equals(eb03ValForEb01C.toString())) {
							if (-1 == eb03ValForEb01C.toString().indexOf(
									eb03Value)) {
								eb03ValForEb01C.append(",");
								eb03ValForEb01C.append(eb03Value);
							}
						} else {
							eb03ValForEb01C.append(eb03Value);
						}
					}
				}

			}
		}
		if (!"".equals(eb03ValForEb01BC.toString())) { // eb03ValForEb01BC is
			// not null, means we
			// have EB03 values to
			// show error.
			setContractLevelErrorForE027(contract, eb03ValForEb01BC.toString(),
					"BC");
		}
		if (!"".equals(eb03ValForEb01DW.toString())) {
			setContractLevelErrorForE027(contract, eb03ValForEb01DW.toString(),
					"DW");
		}
		if (!"".equals(eb03ValForEb01G.toString())) {
			setContractLevelErrorForE027(contract, eb03ValForEb01G.toString(),
					"G");
		}
		if (!"".equals(eb03ValForEb01F.toString())) {
			setContractLevelErrorForE027(contract, eb03ValForEb01F.toString(),
					"F");
		}
		if (!"".equals(eb03ValForEb01C.toString())) {
			setContractLevelErrorForE027(contract, eb03ValForEb01C.toString(),
					"C");
		}
	}

	/**
	 * This method checks condition for Error Code - E034 
	 * 
	 * 2.	Error E034 will be set on a variable that is coded with EB01 = G, EB03 = (health benefit plan coverage or 
	 * general benefits) and format = (DOL)  by looking at the 'copay ind', 'Ded Ind'  (both set for the contract), and 
	 * the variable description (if applicable) and the message on the variable is anyone of the corresponding messages.
	 * |---------------------------------------------------------------------------------------------------------------------|
     * | CPY Ind | DED Ind | Contract variable description has                         | MSG                                 |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * |  Y      |  Y      | N/A                                                       | EXCLUDE COPAY                       |
     * |---------|---------|-----------------------------------------------------------| EXCLUDE DEDUCTIBLE                  |
     * | blank   | blank   | ‘INCLUDE DED & COP’ or ‘INCLUDE DED & COPAYS’             | EXCLUDE DEDUCTIBLE & COPAY          |
     * |---------|---------|-----------------------------------------------------------| EXCLUDE DEDUCTIBLE\COPAY            |
     * | Y       | blank   | ‘INCLUDE DED & COP’ or ‘INCLUDE DED & COPAYS’             | SPECIAL EXCLUDE COPAY               |
     * |---------|---------|-----------------------------------------------------------|                                     |
     * | blank   | Y       |  ‘INCLUDE DED & COP’ or ‘INCLUDE DED & COPAYS’            |                                     |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | N       | N       |  N/A                                                      | EXCLUDE COPAY                       |
     * |         |         |                                                           | EXCLUDE DEDUCTIBLE                  |
     * |         |         |                                                           | SPECIAL EXCLUDE COPAY               |
     * |         |         |                                                           | blank                               |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | Y       | N       |  N/A                                                      | EXCLUDE DEDUCTIBLE\COPAY            |
     * |---------|---------|-----------------------------------------------------------| EXCLUDE COPAY 	                     |
     * | blank   | blank   | ‘INCLUDES COPAY’ or ‘INCLUDES COPAYS’ or ‘INCL COPAY’     | EXCLUDE DEDUCTIBLE & COPAY          |
     * |---------|---------|-----------------------------------------------------------| SPECIAL EXCLUDE COPAY               |
     * | Y       | blank   | ‘INCLUDES COPAY’ or ‘INCLUDES COPAYS’ or ‘INCL COPAY’     | blank                               |
     * |---------|---------|-----------------------------------------------------------|                                     |
     * | blank   | N       | ‘INCLUDES COPAY’ or ‘INCLUDES COPAYS’ or ‘INCL COPAY’     |                                     |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | N       | Y       |  N/A                                                      | EXCLUDE DEDUCTIBLE                  |
     * |---------|---------|-----------------------------------------------------------| EXCLUDE DEDUCTIBLE & COPAY          |
     * | blank   | blank   | ‘INCLUDES DED’                                            | EXCLUDE DEDUCTIBLE\COPAY            |
     * |---------|---------|-----------------------------------------------------------| blank                               |
     * | N       | blank   | ‘INCLUDES DED’                                            |                                     |
     * |---------|---------|-----------------------------------------------------------|                                     |
     * | blank   | Y       | ‘INCLUDES DED’                                            |                                     |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | Y       | blank   | ‘INCLUDES DED’                                            | Any message                         |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | N       | blank   | ‘INCLUDES COPAY’ or ‘INCLUDES COPAYS’ or ‘INCL COPAY’     | Any message                         |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | N       | blank   | ‘INCLUDE DED & COP’ or ‘INCLUDE DED & COPAYS’             | Any message                         |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | blank   | Y       | ‘INCLUDES COPAY’ or ‘INCLUDES COPAYS’ or ‘INCL COPAY’     | Any message                         |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | blank   | N       | ‘INCLUDES DED’                                            | Any message                         |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     * | blank   | N       | ‘INCLUDE DED & COP’ or ‘INCLUDE DED & COPAYS’             | Any message                         |
     * |---------|---------|-----------------------------------------------------------|-------------------------------------|
     *
	 * @param mapping, copayIndFlag, dedIndFlag  
	 * 
	 * @return mapping
	 */
	private ContractMappingVO validateE034(ContractMappingVO mapping, String copayIndFlag, String dedIndFlag) {
		if (null != mapping) {
			boolean errorflag = false;
			String variableDesc = "";
			String message = "";
			List <EB03Association> eb03Associations=new ArrayList<EB03Association>();
			String variableID = "";
			 
			if(null != mapping.getVariable()){
				variableDesc = mapping.getVariable().getDescription() != null ? 
						mapping.getVariable().getDescription().toUpperCase() : "";
						
				variableID = mapping.getVariable().getVariableId();
			}

			if(checkErrorPreConditionsE034(mapping)){

				if (null != mapping.getEb03()) {
					eb03Associations = mapping.getEb03().getEb03Association();

					for(EB03Association eb03Association:eb03Associations){

						if (getEB03ForE034().contains(eb03Association.getEb03().getValue())){

							message = eb03Association.getMessage() != null ? eb03Association.getMessage().toUpperCase() : "";

							if(DomainConstants.Y.equalsIgnoreCase(copayIndFlag)){
								if(DomainConstants.Y.equalsIgnoreCase(dedIndFlag)){

									//copayIndFlag = Y & dedIndFlag = Y
									if(getMessage1ForE034().contains(message)){
										errorflag = true;
									}
								}else if(DomainConstants.N.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = Y & dedIndFlag = N
									if(getMessage3ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
										errorflag = true;
									}
								}else if(DomainConstants.EMPTY.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = Y & dedIndFlag = blank
									if(checkVariableDescription1ForE034(variableDesc)){
										if(getMessage1ForE034().contains(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription2ForE034(variableDesc)){
										if(getMessage3ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription3ForE034(variableDesc)){
										errorflag = true;						
									}else if(checkVariableDescription4ForE034(variableDesc)){
										errorflag = true;						
									}
								}
							}else if(DomainConstants.N.equalsIgnoreCase(copayIndFlag)){
								if(DomainConstants.Y.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = N & dedIndFlag = Y
									if(getMessage4ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
										errorflag = true;
									}
								}else if(DomainConstants.N.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = N & dedIndFlag = N
									if(getMessage2ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
										errorflag = true;
									}
								}else if(DomainConstants.EMPTY.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = N & dedIndFlag = blank
									if(checkVariableDescription1ForE034(variableDesc)){
										errorflag = true;	
									}else if(checkVariableDescription2ForE034(variableDesc)){
										errorflag = true;
									}else if(checkVariableDescription3ForE034(variableDesc)){
										if(getMessage4ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription4ForE034(variableDesc)){
										if(getMessage2ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}
								}
							}else if(DomainConstants.EMPTY.equalsIgnoreCase(copayIndFlag)){
								if(DomainConstants.Y.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = blank & dedIndFlag = Y
									if(checkVariableDescription1ForE034(variableDesc)){
										if(getMessage1ForE034().contains(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription2ForE034(variableDesc)){
										errorflag = true;	
									}else if(checkVariableDescription3ForE034(variableDesc)){
										if(getMessage4ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription4ForE034(variableDesc)){
										errorflag = true;						
									}
								}else if(DomainConstants.N.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = blank & dedIndFlag = N

									if(checkVariableDescription1ForE034(variableDesc)){
										errorflag = true;
									}else if(checkVariableDescription2ForE034(variableDesc)){
										if(getMessage3ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription3ForE034(variableDesc)){
										errorflag = true;	
									}else if(checkVariableDescription4ForE034(variableDesc)){
										if(getMessage2ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}
								}else if(DomainConstants.EMPTY.equalsIgnoreCase(dedIndFlag)){
									//copayIndFlag = blank & dedIndFlag = blank
									if(checkVariableDescription1ForE034(variableDesc)){
										if(getMessage1ForE034().contains(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription2ForE034(variableDesc)){
										if(getMessage3ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}else if(checkVariableDescription3ForE034(variableDesc)){
										if(getMessage4ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
											errorflag = true;
										}
									}
									else if(checkVariableDescription4ForE034(variableDesc)){
										if(getMessage2ForE034().contains(message) || DomainConstants.EMPTY.equalsIgnoreCase(message)){
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
							errorDetailsObject.setError(false);
							errorflag = false;
							errorDetailsObject.setErrorCode(DomainConstants.ERROR_E034);
							errorDetailsObject.setAssociatedEb03(eb03Association.getEb03().getValue());
							errorDetailsObject
							.setErrorDesc(DomainConstants.E034_DESCRIPTION);
							mapping.getErrorCodesList().add(errorDetailsObject);
						}
					}
				}
			}
		}
		return mapping;
	}
	
	/**
	 * Method to check whether 
	 * (i)   EB01 = G, 
	 * (ii)  EB03 = health benefit plan coverage/general benefits (30/60) 
	 *       and 
	 * (iii) format = (DOL)
	 * for a mapping
	 * @param mapping
	 * @return errorConditionsPresent
	 */
	private boolean checkErrorPreConditionsE034(ContractMappingVO mapping) {
		boolean errorConditionsPresent = false;
		String format = "";
		format = mapping.getVariable().getVariableFormat();
		// check whether format = (DOL)
		if (null != format && DomainConstants.FORMAT_DOLLAR.equals(format)) {

			String ebol_Val = "";
			// EB01 null checking
			if (null != mapping.getEb01()) {
				HippaSegment eb01 = mapping.getEb01();
				HippaCodeValue hippaCodeValue01 = (HippaCodeValue) eb01
						.getHippaCodeSelectedValues().get(0);
				ebol_Val = hippaCodeValue01.getValue();
				// check if EB01 = G
				if (DomainConstants.STOP_LOSS.equals(ebol_Val)) {

					if (null != mapping.getEb03()) {
						List eb03ValuesList = mapping.getEb03()
								.getHippaCodeSelectedValues();
						if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
							Iterator eb03Iterator = eb03ValuesList.iterator();

							while (eb03Iterator.hasNext()) {
								HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
										.next();

								String hippaCodeValue = hippaCodeValue03
										.getValue();
								// EB03 = 30 or 60
								if (getEB03ForE034().contains(hippaCodeValue)) {
									errorConditionsPresent = true;
								}

							}
						}
					}
				}
			}
		}
		return errorConditionsPresent;
	}
	/**
	 * This method checks condition for Error Code - E035 
	 * 
	 * If
	 * the variable format is PCT and variable value is greater than 100%.
	 * OR
	 * the variable format is PCT,  variable value is 0% and EB01 is coinsurance.
	 * E035 is thrown
	 * 
	 * @param mapping
	 * 
	 * @return
	 */
	private ContractMappingVO validateE035(ContractMappingVO mapping) {
		if (null != mapping) {
			String format = "";
			float percentage = 0;
			boolean errorflag = false;
			String value = "";
			format = mapping.getVariable().getVariableFormat();
			value = mapping.getVariable().getVariableValue();
			if (null != format
					&& DomainConstants.PERCENTAGE_FORMAT.equals(format)) {
				if (null != value && !"".equals(value)) {
					try {
						percentage = Float.parseFloat(value);
					} catch (NumberFormatException e) {
						log.debug("Invalid linevalue value  format");
					}
				}

				if (percentage > 100) {
					errorflag = true;
				} else if (percentage == 0.0) {

					String ebol_Val = "";
					// EB01 null checking
					if (null != mapping.getEb01()) {
						HippaSegment eb01 = mapping.getEb01();
						HippaCodeValue hippaCodeValue01 = (HippaCodeValue) eb01
								.getHippaCodeSelectedValues().get(0);
						ebol_Val = hippaCodeValue01.getValue();

						if (DomainConstants.COINSURANCE.equals(ebol_Val)) {
							errorflag = true;
						}
					}
				}
			}

			if (errorflag) {
				/**
				 * Object to hold error details Variable Level Error
				 */
				ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
				errorDetailsObject.setError(false);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E035);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E035_DESCRIPTION);
				mapping.getErrorCodesList().add(errorDetailsObject);
			}
		}
		return mapping;
	}

	/**
	 * This method checks condition for Error Code - E036
	 * If variable is mapped and not in 'NOT APPLICABLE' status
	 * &
	 * (minorHeadingDesc contains DIABETES/DIAB || variableDesc contains DIABETES/DIABETIC)
	 * 
	 * @param MinorHeadingsVO, ContractMappingVO
	 * @return ContractMappingVO
	 */
	private ContractMappingVO validateE036(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mappingObj) {
		if (checkForE036ErrorCondition(minorHeadingObj, mappingObj)) {
			/**
			 * Object to hold error details Variable Level Error
			 */
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(false);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E036);
			errorDetailsObject.setErrorDesc(DomainConstants.E036_DESCRIPTION);
			mappingObj.getErrorCodesList().add(errorDetailsObject);
		}
		return mappingObj;
	}
	private boolean checkForE036ErrorCondition(MinorHeadingsVO minorHeadingObj,
			ContractMappingVO mappingObj) {
		String minorHeadingDesc = "";
		String variableDesc = "";
		boolean errorflag = false;
		if ((null != minorHeadingObj) && (null != mappingObj)) {

			minorHeadingDesc = minorHeadingObj.getDescriptionText() != null ? 
					minorHeadingObj.getDescriptionText().toUpperCase() : "";
			if (minorHeadingDesc.indexOf(DomainConstants.DIAB) != -1) {
				errorflag = true;
			} else if (null != mappingObj.getVariable()) {
				variableDesc = mappingObj.getVariable().getDescription() != null ? mappingObj
						.getVariable().getDescription().toUpperCase() 
						: "";
				if ((variableDesc.indexOf(DomainConstants.DIABETES) != -1)
						|| (variableDesc.indexOf(DomainConstants.DIABETIC) != -1)) {
					errorflag = true;
				}

			}
		}
		return errorflag;
	}
	
	private boolean isEB03Has88(ContractMappingVO mapping) {
		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		boolean isEB0388 = false;
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
						isEB0388 = true; // if 88 then Error
						break;
					}
				}
			}
		}
		return isEB0388;
	}
	
/***************************SSCR 14181 April 2012 Release  START**********************************/
	/**
	 * The method returns the flag for the Error code E038 EB03=AL for vision Benefits
	 * @param mapping
	 * @return boolean
	 */
	private boolean isEB03HasAL(ContractMappingVO mapping) {
		boolean isEB03AL = false;
		if (mapping.isMapped()) {
			HippaSegment hippaSegmentEB03 = mapping.getEb03();
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
	private boolean isEB03Has35(ContractMappingVO mapping) {
		boolean isEB0335 = false;
		if (mapping.isMapped()) {
			HippaSegment hippaSegmentEB03 = mapping.getEb03();
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
	 * The method returns the flag for the Error code E008 EB03=AL for vision Benefits
	 * @param mapping
	 * @return boolean
	 */
	private void validateE038(ContractVO contract, Boolean eB03CodedAsVisionFlag, Boolean visionBenefitFlag) {	
		if(!errorflagForE038 && !eB03CodedAsVisionFlag && visionBenefitFlag ){
			setContractLevelErrorForE038(contract);
			errorflagForE038 = true;
		}
	}
	/**
	 * The method returns the flag for the Error code E008 EB03=35 for dental Benefits
	 * @param mapping
	 * @return boolean
	 */
	private void validateE039(ContractVO contract, Boolean eB03CodedAsDentalFlag, Boolean dentalBenefitFlag) {	
		if(!errorflagForE039 && !eB03CodedAsDentalFlag && dentalBenefitFlag ){
			setContractLevelErrorForE039(contract);
			errorflagForE039 = true;
		}
	}
/***************************SSCR 14181 April 2012 Release  END**********************************/

	/***START BXNI NOVEMBER RELEASE 2012***
	validateE043 method checks for the following checks.
	If EB03 PT is coded, then checks whether EB03 AE is coded or not.
	If EB03 AE is coded, then checks whether EB03 PT is coded or not.
	If EB03 98 is coded, then checks whether EB03 BY is coded or not.
	If EB03 BY is coded, then checks whether EB03 98 is coded or not.
	If EB03 4 is coded, then checks whether EB03 73 is coded or not.
	If EB03 73 is coded, then checks whether EB03 4 is coded or not.
	if any of the condition fails a error will be added to the list.
	*
	*/
	private void validateE043(ContractMappingVO mapping) {
		//Initializing variables.
		List<String> eb03List = new ArrayList<String>();
		
		//Get the list of EB03's for a variable.
		if (null != mapping) {
			if (null != mapping.getEb03()) {
				List eb03ValuesList = mapping.getEb03()
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
				isE043Exists(DomainConstants.EB03_PT, DomainConstants.EB03_AE, eb03List, mapping);
				
				//Check for E043 Error Condition. If AE Exists then PT should Exists.
				isE043Exists(DomainConstants.EB03_AE, DomainConstants.EB03_PT, eb03List, mapping);
				
				//Check for E043 Error Condition. If 98 Exists then BY should Exists.
				isE043Exists(DomainConstants.EB03_98, DomainConstants.EB03_BY, eb03List, mapping);
				
				//Check for E043 Error Condition. If BY Exists then 98 should Exists.
				isE043Exists(DomainConstants.EB03_BY, DomainConstants.EB03_98, eb03List, mapping);
				
				//Check for E043 Error Condition. If 4 Exists then 73 should Exists.
				isE043Exists(DomainConstants.EB03_4, DomainConstants.EB03_73, eb03List, mapping);
				
				//Check for E043 Error Condition. If 73 Exists then 4 should Exists.
				isE043Exists(DomainConstants.EB03_73, DomainConstants.EB03_4, eb03List, mapping);
				
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
	private void isE043Exists(String codedEB03, String eb03Exists, List<String> eb03List, ContractMappingVO mapping) {
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
		if(errorExists){
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E043);
			errorDetailsObject.setErrorDesc(errorDescription.toString());
			mapping.getErrorCodesList().add(errorDetailsObject);
		}
		
	}
	 /***END BXNI NOVEMBER RELEASE 2012**/
	/**
	 * The method returns the flag for the Error code E008
	 * If the contract has a variable mapping EB03 = 88, then the same variable should have EB01 = BC and coded value = N
	 * SSCR 16331 Nov Release
	 * @param mapping
	 * @return boolean
	 */
	private boolean isVariableNonCovered(ContractVO contract, ContractMappingVO mapping, String variable){
		
		boolean isVariableNonCovered = false;
		
		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		HippaSegment hippaSegmentEB01 = mapping.getEb01();
		String eb01 = "";
		
		if (null != hippaSegmentEB01) {
			HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSegmentEB01
					.getHippaCodeSelectedValues().get(0);
			eb01 = hippaCodeValue01.getValue();
		}
		
		if (null != hippaSegmentEB03) {
			List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
			if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
				Iterator eb03Iterator = eb03ValuesList.iterator();

				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();

					if (DomainConstants.EB03_88.equals(hippaCodeValue03.getValue()) 
							&& DomainConstants.EB01_BENEFIT_COVERED.equals(eb01)) {
						String variableCodedValue = MappingUtil.getVariableCodedValueInContract(contract, variable);
						if(!StringUtils.isBlank(variableCodedValue) && DomainConstants.VARIABLE_CODED_VALUE_N.equals(variableCodedValue)){
							isVariableNonCovered = true;
							break;
						}
					}
				}
			}
		}
		return isVariableNonCovered;
		
	}
	
	/**
	 * The method returns the flag for the Error code E008
	 * If the contract has a variable mapping EB03 = 88, then the same variable is not having EB01 = BC and coded value = N
	 * SSCR 16331 Nov Release
	 * @param mapping
	 * @return boolean
	 */
	private boolean isVariableCovered(ContractVO contract, ContractMappingVO mapping, String variable){
		
		boolean isVariableCovered = false;
		
		HippaSegment hippaSegmentEB03 = mapping.getEb03();
		HippaSegment hippaSegmentEB01 = mapping.getEb01();
		String eb01 = "";
		
		if (null != hippaSegmentEB01) {
			HippaCodeValue hippaCodeValue01 = (HippaCodeValue) hippaSegmentEB01
					.getHippaCodeSelectedValues().get(0);
			eb01 = hippaCodeValue01.getValue();
		}
		
		if (null != hippaSegmentEB03) {
			List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
			if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
				Iterator eb03Iterator = eb03ValuesList.iterator();

				while (eb03Iterator.hasNext()) {
					HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
							.next();

					if (DomainConstants.EB03_88.equals(hippaCodeValue03.getValue()) 
							&& DomainConstants.EB01_BENEFIT_COVERED.equals(eb01)) {
						String variableCodedValue = MappingUtil.getVariableCodedValueInContract(contract, variable);
						if(!StringUtils.isBlank(variableCodedValue) && !DomainConstants.VARIABLE_CODED_VALUE_N.equals(variableCodedValue)){
							isVariableCovered = true;
							break;
						}
					}
				}
			}
		}
		return isVariableCovered;
		
	}
}