/*
 * <ErrorValidationHelper.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.PricingInfoVO;

/**
 * @author UST-GLOBAL
 * 
 * Helper class for Error Validation - This class will have the associated methods,
 * which makes the error validation more easier.
 * 
 */

public class ErrorValidationHelper { 
	
	/**
	 * This method is to identify a WPD HMO contract.
	 * Conditions to identify the HMO contract in WPD are,
	 * 
	 * 1. Contract type is ‘H’ i.e. variable CONTBENTYPE is coded with a value H AND Corporate Plan is blank.
	 * 2. Benefit Structure is ‘CC’ AND Corporate Plan is blank
	 * 3. Product Code is any one of  ‘QHMO’, ‘HMUT’, ‘PSUT’, ‘PCBA’, ‘CCHS’, ‘CCBA’, ‘CCBH’, ‘CCSC’, ‘CNBH’, ‘CNSH’, ‘CNAH’, ‘MGMA’
	 * 4. Contract ID is either ‘PD02’ or ‘T769’
	 * 5. Contract type is not coded i.e. variable CONTBENTYPE has no value coded in contract AND Corporate Plan is ‘HMA’
	 * 
	 * @param contract
	 * @param variableMap 
	 * @return
	 */
	public static ContractVO setHMOFlag(ContractVO contract, Map variableMap) {
		if (getHMOProductCodeForWPD().contains(contract.getProductCode())) {
			contract.setFlagHMO(true);
		} else if (getHMOContractForWPD().contains(contract.getContractId())) {
			contract.setFlagHMO(true);
		} else if (DomainConstants.CC.equals(contract.getBenefitStructure())) {
			if (null == contract.getCorporatePlan() 
					|| DomainConstants.EMPTY.equals(contract.getCorporatePlan())) {
			contract.setFlagHMO(true);
			}	
		} else if (null == contract.getCorporatePlan() || DomainConstants.EMPTY.equals(contract.getCorporatePlan())) {
			if (null != variableMap && variableMap.size() > 0) {
				if (variableMap.containsKey(DomainConstants.CONTBENTYPE)) {
					String variableValue = (String) variableMap.get(DomainConstants.CONTBENTYPE);
						if (DomainConstants.H.equalsIgnoreCase(variableValue)) {
							contract.setFlagHMO(true);
					} 
				}
			}
		} else if (DomainConstants.HMA.equalsIgnoreCase(contract.getCorporatePlan())) {
			if (null != variableMap && variableMap.size() > 0) {
				if (variableMap.containsKey(DomainConstants.CONTBENTYPE)) {
					String variableValue = (String) variableMap.get(DomainConstants.CONTBENTYPE);
					if (null == variableValue || DomainConstants.EMPTY.equals(variableValue)) {
						contract.setFlagHMO(true);
					}
				} else {
					contract.setFlagHMO(true);
				}
			} else {
				contract.setFlagHMO(true);
			}
		}
		return contract;
	}
	
	/**
	 * This method is to identify an WPD EPO contract.
	 * Conditions to identify the EPO contract in LG is,
	 * 1. If the product code of the contract is any one of EPOM/ EGKD/ EGKM/ EPOD/ PBDX/ PGEP/ BCEM
	 * / PMBP/ PVEP/ PWBP/ PEMC/ PEVG/ PKBP/ PENY/ PEOH/ PEKY / PECA / BECA/ PBEX / BEHA/ PEHA/ PEIH
	 * 
	 * Conditions to identify the EPO contract in ISG are,
	 * 1. If the product code of the contract is any one of SGPN/ SGPU/ IPSG
	 * 2. If the Contract id is 02C0
	 * 
	 * @param contract
	 * @return
	 */
	public static ContractVO setEPOFlag (ContractVO contract, String system) {
		
		if (DomainConstants.SYSTEM_LG.equals(system)) {
			if (getEPOProductCodeForLG().contains(contract.getProductCode())) {
				contract.setFlagEPO(true);
			}
		} else if (DomainConstants.SYSTEM_ISG.equals(system)) {
			if (getEPOProductCodeForISG().contains(contract.getProductCode())) {
				contract.setFlagEPO(true);
			} else if (getEPOContractForISG().contains(contract.getContractId())) {
				contract.setFlagEPO(true);
			}
			
		}
		return contract;
	}
	/**
	 * This method will set the HCR flag for E016 and E017 validation.
	 * The flag will set, if the variable AHCRLMTRSTAP OR AHCRANNDOLA is coded as Y 
	 * @param contract
	 * @param variableMap
	 * @return
	 */
	public static ContractVO setFlagHCRForE016And17 (ContractVO contract, Map variableMap) {
		
		if (null != variableMap && variableMap.size() > 0) {
			String variableID = null;
			for (int j = 0; j < getHCRVariableForE016andE017().size(); j++) {
				variableID = (String) getHCRVariableForE016andE017().get(j);
				if (variableMap.containsKey(variableID)) {
					String variableValue = (String) variableMap.get(variableID);
					if (null != variableValue
							&& !DomainConstants.EMPTY.equals(variableValue)) {
						if (DomainConstants.Y.equalsIgnoreCase(variableValue)) {
							contract.setFlagHCR_E016And17(true);
							break;
						}
					}
				}
			}
		}	
		return contract;
	}
	
	/**
	 * This method will set the HCR flag for E018 validation.
	 * The flag will set, if the variable AHCRPREVAPL is coded as Y 
	 * @param contract
	 * @param variableMap
	 * @return
	 */
	public static ContractVO setFlagHCRForE018 (ContractVO contract, Map variableMap) {
		if (null != variableMap && variableMap.size() > 0) {
			String variableID = null;
			for (int j = 0; j < getHCRVariableForE018().size(); j++) {
				variableID = (String) getHCRVariableForE018().get(j);
				if (variableMap.containsKey(variableID)) {
					String variableValue = (String) variableMap.get(variableID);
					if (null != variableValue
							&& !DomainConstants.EMPTY.equals(variableValue)) {
						if (DomainConstants.Y.equalsIgnoreCase(variableValue)) {
							contract.setFlagHCR_E018(true);
							break;
						}
					}
				}
			}
		}
		return contract;
	}
	

	/**
	 * This method is to identify an LG HMO contract.
	 * SSCR 16331 November Release 
	 * Conditions to identify the HMO contract in WPD LG is,
	 * Product family of the Contract is either 'HMO' or 'CHMO'
	 * @param contract
	 * @return
	 */
	public static ContractVO setHMOFlagForLG (ContractVO contract) {
		
			if (getHMOProductFamilyForLG().contains(contract.getProductFamily())) {
				contract.setFlagHMO(true);
			}
		return contract;
	}
	/**
	 * This method is to identify an ISG HMO contract.
	 * SSCR 16331 November Release 
	 * Conditions to identify the HMO contract in WPD ISG is,
	 * Contract pricing Info Coverage Code = MED and Pricing Info Network = HMO
	 * Contract IDs PD02, 0261, T769 are always HMO Contract
	 * Contract IDs 2369, 7870, 7871  are always NON HMO Contracts.
	 * @param contract, pricingInfoVO
	 * @return contract
	 */
	public static ContractVO setHMOFlagForISG (ContractVO contract, List<PricingInfoVO> pricingInfoVOList) {
		if(getNonHMOContractsForISG().contains(contract.getContractId())){
			contract.setFlagHMO(false);
			return contract;
		}
		if(getHMOContractsForISG().contains(contract.getContractId())){
			contract.setFlagHMO(true);
			return contract;
		}
		if(null != pricingInfoVOList){
			Iterator<PricingInfoVO> pricingInfoVOListIter = pricingInfoVOList.iterator();
			while (pricingInfoVOListIter.hasNext()) {
				PricingInfoVO pricingInfoVO = (PricingInfoVO) pricingInfoVOListIter.next();
				if(DomainConstants.HMO_PRICING_INFO_COVERAGE_CODE.equals(pricingInfoVO.getCoverageCode())
						&& DomainConstants.HMO_PRICING_INFO_NETWORK_CODE.equals(pricingInfoVO.getNetworkCode())){
					contract.setFlagHMO(true);
					break;
				}
			}
		}
		
	return contract;
	}
	
	/**
	 * This method is to identify an EWPD HMO contract.
	 * SSCR 16331 November Release 
	 * Conditions to identify the HMO contract in EWPD is,
	 * Contract pricing Info Coverage Code = MED and Pricing Info Network = HMO
	 * @param contract, pricingInfoVO
	 * @return contract
	 */
	public static ContractVO setHMOFlagForEWPD (ContractVO contract, List<PricingInfoVO> pricingInfoVOList) {
		if(null != pricingInfoVOList){
			Iterator<PricingInfoVO> pricingInfoVOListIter = pricingInfoVOList.iterator();
			while (pricingInfoVOListIter.hasNext()) {
				PricingInfoVO pricingInfoVO = (PricingInfoVO) pricingInfoVOListIter.next();
				if(DomainConstants.HMO_PRICING_INFO_COVERAGE_CODE.equals(pricingInfoVO.getCoverageCode())
						&& DomainConstants.HMO_PRICING_INFO_NETWORK_CODE.equals(pricingInfoVO.getNetworkCode())){
					contract.setFlagHMO(true);
					break;
				}
			}
		}
	return contract;
	}
	/**
	 * This method identifies if an LG contract has an MCS/TPA arrangement
	 * SSCR 16331 November Release 
	 * Conditions to identify the HMO contract in EWPD is,
	 * 1. Contract group = MCS
	 * 2. Contract group != JAA and codedValueof the variable ACLMSTPA in the contract = Y
	 * @param contract, codedValueofACLMSTPA
	 * @return contract
	 */
	public static ContractVO setMCSTPAFlagForLG(ContractVO contract, String codedValueofACLMSTPA) {
		
		if(DomainConstants.CONTRACT_GROUP_NM_MCS.equals(contract.getGroupName())){
			contract.setContractMCSTPA(true);
		}else if(!DomainConstants.CONTRACT_GROUP_NM_JAA.equals(contract.getGroupName())
					&& DomainConstants.Y.equals(codedValueofACLMSTPA)){
			contract.setContractMCSTPA(true);
		}
	return contract;
	}

	
	protected static List getHMOProductCodeForWPD() {
		List productCodesList = SimulationResourceBundle.getResourceBundle(DomainConstants.HMO_PRODCODES_WPD,
																		DomainConstants.PROPERTY_FILE_NAME);
		return productCodesList;
	
	}
	protected static List getHMOContractForWPD() {
		List productCodesList = SimulationResourceBundle.getResourceBundle(DomainConstants.HMO_CONTRACT_WPD,
																		DomainConstants.PROPERTY_FILE_NAME);
		return productCodesList;
	
	}
	protected static List getEPOProductCodeForLG() {
		List productCodesList = SimulationResourceBundle.getResourceBundle(DomainConstants.EPO_PRODCODES_LG,
																		DomainConstants.PROPERTY_FILE_NAME);
		return productCodesList;
	
	}
	protected static List getEPOProductCodeForISG() {
		List productCodesList = SimulationResourceBundle.getResourceBundle(DomainConstants.EPO_PRODCODES_ISG,
																		DomainConstants.PROPERTY_FILE_NAME);
		return productCodesList;
	
	}
	protected static List getEPOContractForISG() {
		List productCodesList = SimulationResourceBundle.getResourceBundle(DomainConstants.EPO_CONTRACT_ISG,
																		DomainConstants.PROPERTY_FILE_NAME);
		return productCodesList;
	
	}
	protected static List getHCRVariableForE016andE017() {
		List varibleList = SimulationResourceBundle.getResourceBundle(DomainConstants.HCR_Variable_E016andE017,
																		DomainConstants.PROPERTY_FILE_NAME);
		return varibleList;
	}
	protected static List getHCRVariableForE018() {
		List varibleList = SimulationResourceBundle.getResourceBundle(DomainConstants.HCR_Variable_E018,
																		DomainConstants.PROPERTY_FILE_NAME);
		return varibleList;
	}
	//get the HMO Product family -- SSCR 16331
	protected static List getHMOProductFamilyForLG() {
		List productFamilyList = SimulationResourceBundle.getResourceBundle(DomainConstants.HMO_PROD_FAMILY_LG,
																		DomainConstants.PROPERTY_FILE_NAME);
		return productFamilyList;
	
	}
	//get the HMO Contracts List for ISG -- SSCR 16331
	protected static List getHMOContractsForISG(){
		List hmoContractsList = SimulationResourceBundle.getResourceBundle(DomainConstants.HMO_CONRACT_ISG,
																		DomainConstants.PROPERTY_FILE_NAME);
    	return hmoContractsList;
	}
	//get the Non HMO Contracts List for ISG -- SSCR 16331
	protected static List getNonHMOContractsForISG(){
		List nonHmoContractsList = SimulationResourceBundle.getResourceBundle(DomainConstants.NON_HMO_CONRACT_ISG,
																		DomainConstants.PROPERTY_FILE_NAME);
    	return nonHmoContractsList;
	}	
}
