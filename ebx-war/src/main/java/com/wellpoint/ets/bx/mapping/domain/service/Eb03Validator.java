/*
 * <Eb03Validator.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.simulation.util.ContractMappingValidator;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

public class Eb03Validator extends Validator implements HippaSegmentValidator {

	public List validate(Mapping mapping) throws DomainException {
		if (null == mapping) {
			throw new DomainException("Mapping  object expected.");
		}
		List hippaSegmentValidationResultList = new ArrayList();

		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		ValidationUtility validationUtility = new ValidationUtility();
		short status = 0;

		List hippaCodeSelectedValues = validationUtility
				.removeBlankfromList(mapping
						.getHippaSegmentSelectedValues(mapping.getEb03()));

		// VALIDATION FOR CATEGORY CODE PHO / PHOMM
		String variableDescription = "";

		if (null != mapping.getVariable()
				&& null != mapping.getVariable().getDescription()) {

			variableDescription = mapping.getVariable().getDescription().trim()
					.toUpperCase();
		}
		if ((null != variableDescription)
				&& ((variableDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_SPE.toUpperCase()) != -1) || (variableDescription
						.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_SPC
										.toUpperCase()) != -1))
				&& ((ValidatorConstants.CATEGORY_PHO.equals(mapping
						.getVariable().getIsgCatagory()))
						|| (ValidatorConstants.CATEGORY_PHO.equals(mapping
								.getVariable().getLgCatagory()))
						|| (ValidatorConstants.CATEGORY_PHOMM.equals(mapping
								.getVariable().getIsgCatagory())) || (ValidatorConstants.CATEGORY_PHOMM
						.equals(mapping.getVariable().getLgCatagory())))) {
			short status1 = 2;
			if (!((mapping.getEb03Values().contains(
					ValidatorConstants.TRUE_SPECIALIST) == true) || (mapping
					.getEb03Values().contains(
							ValidatorConstants.SHARED_SPECIALIST) == true))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status1,
						ValidatorConstants.WARNING_MESSAGE_EB03_SP_SS_PHO,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);

			}

		}
		// Validation for Category ends.
		if (hippaCodeSelectedValues == null
				|| hippaCodeSelectedValues.isEmpty()) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.MAPPING_MANDATORY,
					new String[] { DomainConstants.EB03_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
			return hippaSegmentValidationResultList;
		}
		// separating variable and rule validation for section 3
		if (null != mapping.getVariable()) {
			hippaSegmentValidationResultList = validateVariableEB03(
					hippaSegmentValidationResultList, mapping,
					hippaCodeSelectedValues);
		}
		// for rule EB03 validation
		else if (null != mapping.getRule()) {
			hippaSegmentValidationResultList = validateRuleEB03(
					hippaSegmentValidationResultList, mapping,
					hippaCodeSelectedValues);

		}// end of rule EB03 validation
		List hippaSegmentValidationList = validate(mapping.getEb03().getName(),
				mapping.getEb03().getHippaCodePossibleValues(), mapping
						.getEb03().getHippaCodeSelectedValues(), mapping);
		if (hippaSegmentValidationList != null
				&& !hippaSegmentValidationList.isEmpty()) {
			for (Iterator itr = hippaSegmentValidationList.iterator(); itr
					.hasNext();) {
				HippaSegmentValidationResult result = (HippaSegmentValidationResult) itr
						.next();
				hippaSegmentValidationResultList.add(result);
			}
		}
		status = 2;
		// for rule E030 validation
		if (validateForRuleE030(mapping)) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.E030_ERROR_WARNING,
					new String[] { DomainConstants.EMPTY });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}
		// for rule E026 validation
		hippaSegmentValidationResult = validateForRuleE026(mapping);
		if (null != hippaSegmentValidationResult) {
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}

		// BXNI Changes Begin For Service Type Code-November Release
		validateForRuleE043(mapping, hippaSegmentValidationResultList);
		// Restrict certain values to be mapped to EB03 --BXNI Movember 2012
		// Release
		validateEB03ToRestrictValue(mapping, hippaSegmentValidationResultList);
		// BXNI Code Ends Here-November Release
		return hippaSegmentValidationResultList;
	}

	/**
	 * validation for variable and eb03
	 * 
	 * @param hippaSegmentValidationResultList
	 * @param mapping
	 * @param hippaCodeSelectedValues
	 * @return List
	 */
	private List validateVariableEB03(List hippaSegmentValidationResultList,
			Mapping mapping, List hippaCodeSelectedValues) {

		boolean isError = true;
		String eb01SelectedValue = "";
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		short status = 0;
		eb01SelectedValue = mapping.getEB01Value();
		if (eb01SelectedValue == null) {
			eb01SelectedValue = "";
		}
		eb01SelectedValue = eb01SelectedValue.trim();
		if (hippaCodeSelectedValues.size() > 16) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.LIMIT_EXCEEDED,
					new String[] { DomainConstants.EB03_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}
		boolean eb03Val30 = false;
		boolean eb01ValU = false;
		if (hippaCodeSelectedValues
				.contains((String) ValidatorConstants.HEALTH_BENEFIT_PLAN_COVERAGE)) {
			eb03Val30 = true;
		}

		if (eb03Val30) {
			if (eb01SelectedValue.equals("")) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.EB03_INVALID, new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			} else if (ValidatorConstants.CONTACT_FOLLOWING_ENTITY_U
					.equalsIgnoreCase(eb01SelectedValue)) {
				eb01ValU = true;
			}
			if (!eb01ValU) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.EB03_INVALID, new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// if lg and isg category code not null, do the validation, else bypass
		// it
		if ((null != mapping.getVariable().getIsgCatagory() && (!""
				.equalsIgnoreCase(mapping.getVariable().getIsgCatagory().trim())))
				|| ((null != mapping.getVariable().getLgCatagory()) && (!""
						.equalsIgnoreCase(mapping.getVariable().getLgCatagory())))) {
			List eb03CategoryCodeList = mapping.getWpdValidationService()
					.getServiceTypeCode(mapping);
			hippaSegmentValidationResult = validateEB03(mapping,
					hippaCodeSelectedValues, eb03CategoryCodeList, isError);
			if (hippaSegmentValidationResult != null) {
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		return hippaSegmentValidationResultList;
	}

	/**
	 * Method for validating rule and EB03
	 * 
	 * @param hippaSegmentValidationResultList
	 * @param mapping
	 * @param hippaCodeSelectedValues
	 * @return List
	 */
	private List validateRuleEB03(List hippaSegmentValidationResultList,
			Mapping mapping, List hippaCodeSelectedValues) {
		boolean isError = false;
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		short status = 0;
		List valueList = mapping.getEb03().getHippaCodeSelectedValues();
		List duplicateValuesList = BxUtil
				.checkHippaCodeValueDuplicates(valueList);
		if (null != duplicateValuesList && !duplicateValuesList.isEmpty()) {
			for (Iterator itr = duplicateValuesList.iterator(); itr.hasNext();) {
				String duplicateValue = (String) itr.next();
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.DUPLICATE_HIPPACODE_VALUE,
						new String[] { duplicateValue,
								DomainConstants.EB03_NAME });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		if (null != mapping.getEb03().getHippaCodeSelectedValues()
				&& mapping.getEb03().getHippaCodeSelectedValues().size() > 99) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.LIMIT_EXCEEDED,
					new String[] { DomainConstants.EB03_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}
		// Warning message introduced in eBX eWPD if EB03 contains a sensitive
		// and non sensitive benefit -- July Release
		if (null != mapping.getEb03().getHippaCodeSelectedValues()) {
			List selectedEB03Codevalues = new ArrayList();
			ValidationUtility validationUtility = new ValidationUtility();
			short statusWarning = 2;
			selectedEB03Codevalues = mapping
					.getHippaSegmentSelectedValues(mapping.getEb03());
			selectedEB03Codevalues = validationUtility
					.removeBlankfromList(selectedEB03Codevalues);
			boolean isSensitivePresent = false;
			boolean isNonSensitivePresent = false;
			if (selectedEB03Codevalues != null) {
				// Sensitive Benefits list is obtained from
				// errorvalidator.properties property file
				// BXNI June2012 Release
				List sensitiveBenefitsList = SimulationResourceBundle
						.getResourceBundle(DomainConstants.SENSITIVE_EB03,
								DomainConstants.PROPERTY_FILE_NAME);
				for (int i = 0; i < sensitiveBenefitsList.size(); i++) {
					if (selectedEB03Codevalues.contains(sensitiveBenefitsList
							.get(i))) {
						isSensitivePresent = true;
						break;
					}
				}
				if (isSensitivePresent) {
					for (int i = 0; i < selectedEB03Codevalues.size(); i++) {
						if (!sensitiveBenefitsList
								.contains(selectedEB03Codevalues.get(i))) {
							isNonSensitivePresent = true;
							break;
						}
					}
				}
			}
			// Warning message is produced if EB03 contains a combination of
			// Sensitive and Non Sensitive Benefits -- July release
			//Commented -- POR Wave 2 change to remove Warning of Sensitive Benefit Ind
			/*if (isSensitivePresent && isNonSensitivePresent) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						hippaSegmentValidationResult,
						statusWarning,
						ValidatorConstants.WARNING_MESSAGE_SENSITIVE_EB03_HEADERRULE,
						new String[] { DomainConstants.EB03_NAME });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}*/
		}
		// getting header rule and its matching eb03 values
		List ruleEb03List = mapping.getEwpdValidationService()
				.getServiceTypeCode(mapping);
		if (null != ruleEb03List && !ruleEb03List.isEmpty()) {
			hippaSegmentValidationResult = validateEB03(mapping,
					hippaCodeSelectedValues, ruleEb03List, isError);
			if (hippaSegmentValidationResult != null) {
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		return hippaSegmentValidationResultList;
	}

	/**
	 * Method for validating the categorycode, EB03 and rule, EB03 values
	 * 
	 * @param mapping
	 * @param hippaCodeSelectedValues
	 * @param possibleHippaCodes
	 * @param isError
	 * @return HippaSegmentValidationResult
	 */
	private HippaSegmentValidationResult validateEB03(Mapping mapping,
			List hippaCodeSelectedValues, List possibleHippaCodes,
			boolean isError) {
		ValidationUtility validationUtility = new ValidationUtility();
		List possibleHippaCodeValue = new ArrayList();
		short status = 2;
		List invalidValues = new ArrayList();
		Set possibleHippaCodeSet = new HashSet();

		if (possibleHippaCodes != null && !possibleHippaCodes.isEmpty()) {
			for (Iterator itr = possibleHippaCodes.iterator(); itr.hasNext();) {
				String hippaCodeValue = (String) itr.next();
				if (hippaCodeValue != null && !"".equals(hippaCodeValue.trim())) {
					possibleHippaCodeValue.add(hippaCodeValue.trim());
					possibleHippaCodeSet.add(hippaCodeValue.trim()
							.toUpperCase());
				}
			}
		}
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		if (null != possibleHippaCodes) {
			String possibleEb03 = BxUtil.arrayToString(
					(String[]) possibleHippaCodes.toArray(new String[0]), ", ");

			boolean pass = true;
			for (Iterator itr = hippaCodeSelectedValues.iterator(); itr
					.hasNext();) {
				String obj = (String) itr.next();
				if (obj == null) {
					obj = "";
				} else {
					obj = obj.trim().toUpperCase();
				}
				hippaSegmentValidationResult = new HippaSegmentValidationResult();
				if (!possibleHippaCodeSet.contains((String) obj)) {
					invalidValues.add(obj);
					pass = false;
				}
			}
			
			if (mapping.getVariable().getVariableFormat()
						.equalsIgnoreCase(ValidatorConstants.VAL)) {
				//December changes
				if (mapping.getVariable().getVariableFormat()
						.equalsIgnoreCase(ValidatorConstants.VAL)
						&& hippaCodeSelectedValues.size() > 1) {
					status = 0;
					String invalidEB03 = BxUtil.arrayToString(
							(String[]) validationUtility.removeDuplicate(
									invalidValues).toArray(new String[0]), ",");

					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status,
							ValidatorConstants.WARNING_MESSAGE_EB03_MULTIPLE,
							new String[] { " ", " " });
					return hippaSegmentValidationResult;

				}
				if (mapping.getVariable().getVariableFormat()
						.equalsIgnoreCase(ValidatorConstants.VAL)
						&& !((String) hippaCodeSelectedValues.get(0))
								.equalsIgnoreCase("60")) {
					status = 0;

					String invalidEB03 = BxUtil.arrayToString(
							(String[]) validationUtility.removeDuplicate(
									invalidValues).toArray(new String[0]), ",");

					hippaSegmentValidationResult = setHippaSegVldnRslt(
							mapping,
							hippaSegmentValidationResult,
							status,
							ValidatorConstants.WARNING_MESSAGE_EB03_VAR_TYPE_VAL,
							new String[] { " ", " " });
					return hippaSegmentValidationResult;

				} else {
					status = 1;
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status,
							ValidatorConstants.VALIDATION_SUCCESS,
							new String[] { mapping.getEb03().getName() });
					return hippaSegmentValidationResult;
				}
			} 
			
			
			if (!pass) {
				if (isError) {
					status = 0;
				}
				if (null != invalidValues) {
					String invalidEB03 = BxUtil.arrayToString(
							(String[]) validationUtility.removeDuplicate(
									invalidValues).toArray(new String[0]), ",");
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status,
							ValidatorConstants.WARNING_MESSAGE_EB03,
							new String[] { invalidEB03, possibleEb03 });
				}
			}
			if (pass) {
				status = 1;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.VALIDATION_SUCCESS,
						new String[] { mapping.getEb03().getName() });
			}
		}
		return hippaSegmentValidationResult;
	}

	/**
	 * Method to implement screen validation for Rule E030
	 * 
	 * @param mapping
	 * @return boolean
	 */
	private boolean validateForRuleE030(Mapping mapping) {
		boolean hasE030Error = false;
		if (null != mapping) {
			// Checking if EB01 is null and Empty
			if (null != mapping.getEb03()) {
				List eb03ValuesList = mapping.getEb03()
						.getHippaCodeSelectedValues();
				hasE030Error = ContractMappingValidator.checkForRuleE030(
						mapping.getEB01Value(), eb03ValuesList);
			}
		}
		return hasE030Error;
	}

	/**
	 * Method to implement screen validation for Rule E026
	 * 
	 * @param mapping
	 * @return boolean
	 */
	private HippaSegmentValidationResult validateForRuleE026(Mapping mapping) {
		HippaSegmentValidationResult hippaSegmentValidationResult = null;
		if (null != mapping.getEb03()) {
			List eb03ValuesList = mapping.getEb03()
					.getHippaCodeSelectedValues();
			if (ContractMappingValidator.checkForRuleE026(mapping
					.getEB01Value(), eb03ValuesList, mapping.getEB06Value())) {
				hippaSegmentValidationResult = new HippaSegmentValidationResult();
				List possibleHippaCodes = new ArrayList();
				if (DomainConstants.EB06_26.equals(mapping.getEB06Value())) {
					possibleHippaCodes = ContractMappingValidator
							.getEB03ForE026_1();
				} else if (DomainConstants.EB06_27.equals(mapping
						.getEB06Value())) {
					possibleHippaCodes = ContractMappingValidator
							.getEB03ForE026_2();
				} else if (DomainConstants.EB06_36.equals(mapping
						.getEB06Value())) {
					possibleHippaCodes = ContractMappingValidator
							.getEB03ForE026_3();
				} else if (DomainConstants.EB06_7
						.equals(mapping.getEB06Value())) {
					possibleHippaCodes = ContractMappingValidator
							.getEB03ForE026_4();
				}
				String possibleEb03 = BxUtil.arrayToString(
						(String[]) possibleHippaCodes.toArray(new String[0]),
						", ");
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, (short) 2,
						ValidatorConstants.E026_ERROR_WARNING, new String[] {
								mapping.getEB06Value(), possibleEb03 });
			}
		}
		return hippaSegmentValidationResult;
	}

	// methods added for validation of E026 - start
	/**
	 * The method provides the EB03 values for E026 when EB06=26
	 * 
	 * @return List
	 */
	protected List getEB03ForE026_1() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_1,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the EB03 values for E026 when EB06=27
	 * 
	 * @return List
	 */
	protected List getEB03ForE026_2() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_2,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the EB03 values for E026 when EB06=36
	 * 
	 * @return List
	 */
	protected List getEB03ForE026_3() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_3,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the EB03 values for E026 when EB06=7
	 * 
	 * @return List
	 */
	protected List getEB03ForE026_4() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_4,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	// BXNI Changes Begin For New Service Type Code-November Release
	/**
	 * The method validates the new service type code BXNI November 2012Release
	   If EB03 PT is coded, then checks whether EB03 AE is coded or not.
	   If EB03 AE is coded, then checks whether EB03 PT is coded or not.
	   If EB03 98 is coded, then checks whether EB03 BY is coded or not.
	   If EB03 BY is coded, then checks whether EB03 98 is coded or not.
	   If EB03 4 is coded, then checks whether EB03 73 is coded or not.
	   If EB03 73 is coded, then checks whether EB03 4 is coded or not.
	   If any of the condition fails a error will be added to the list.
	   
	   2021 - July Rel - Below validations are commented
	   If EB03 98 is coded, then checks whether EB03 BY is coded or not.
	   If EB03 BY is coded, then checks whether EB03 98 is coded or not.
	 * 
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	
	private void validateForRuleE043(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		// Initializing variables.
		List<String> eb03List = new ArrayList<String>();

		// Get the list of EB03's for a variable.
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
				// Checking if EB03 PT is coded
				if (eb03List.contains(DomainConstants.EB03_PT)) {
					// Checking if EB03 AE is coded
					if (!eb03List.contains(DomainConstants.EB03_AE)) {
						// Error will be thrown if EB03 AE is not coded and PT is coded				
						errorE043(DomainConstants.EB03_PT,
								DomainConstants.EB03_AE, mapping,
								hippaSegmentValidationResultList);
					}
				}

				// Checking if EB03 AE is coded
				if (eb03List.contains(DomainConstants.EB03_AE)) {
					// Checking if EB03 PT is coded
					if (!eb03List.contains(DomainConstants.EB03_PT)) {
						// Error will be thrown if EB03 PT is not coded and AE is coded						
						errorE043(DomainConstants.EB03_AE,
								DomainConstants.EB03_PT, mapping,
								hippaSegmentValidationResultList);
					}
				}
				// Checking if EB03 98 is coded
				//if (eb03List.contains(DomainConstants.EB03_98)) {
					// Checking if EB03 BY is coded
					//if (!eb03List.contains(DomainConstants.EB03_BY)) {*/
						// Error will be thrown if EB03 BY is not coded and 98 is coded		
						/*errorE043(DomainConstants.EB03_98,
								DomainConstants.EB03_BY, mapping,
								hippaSegmentValidationResultList);
					}
				}*/
				// Checking if EB03 BY is coded
				//if (eb03List.contains(DomainConstants.EB03_BY)) {
					// Checking if EB03 98 is coded
					//if (!eb03List.contains(DomainConstants.EB03_98)) {
						// Error will be thrown if EB03 98 is not coded and BY is coded	
						/*errorE043(DomainConstants.EB03_BY,
								DomainConstants.EB03_98, mapping,
								hippaSegmentValidationResultList);
					}
				}*/
				// Checking if EB03 4 is coded
				if (eb03List.contains(DomainConstants.EB03_4)) {
					// Checking if EB03 73 is coded
					if (!eb03List.contains(DomainConstants.EB03_73)) {
						// Error will be thrown if EB03 73 is not coded and 4 is coded	
						errorE043(DomainConstants.EB03_4,
								DomainConstants.EB03_73, mapping,
								hippaSegmentValidationResultList);
					}
				}

				// Checking if EB03 73 is coded
				if (eb03List.contains(DomainConstants.EB03_73)) {
					//Checking if EB03 4 is coded
					if (!eb03List.contains(DomainConstants.EB03_4)) {
						// Error will be thrown if EB03 4 is not coded and 73 is coded
						errorE043(DomainConstants.EB03_73,
								DomainConstants.EB03_4, mapping,
								hippaSegmentValidationResultList);
					}
				}

			}
		}
	}
	
	/**
	 * The method is used for setting the error
	 * 
	 * @param codeExists
	 * @param codeNotExists
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */

	private void errorE043(String codeExists, String codeNotExists,
			Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		short status = 0;
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
				hippaSegmentValidationResult, status,
				ValidatorConstants.ERROR_MESSAGE01_FOR_EB03_SERVICE_TYPE_CODES,
				new String[] { codeNotExists, codeExists });
		hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
	}
                    			 
	
	/**
	 * The method restricts the below values to be mapped to EB03 for a variable
	 * or sps id EB03 = 1, A6, A7, A8, BV, DM, MH cannot be mapped BXNI November
	 * 2012Release
	 * 
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	private void validateEB03ToRestrictValue(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		List<String> restrictedEB03 = getRestrictedEB03();
		List<String> eb03ListFromPage = mapping.getEb03Values();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();

		for (String eb03Value : eb03ListFromPage) {
			if (restrictedEB03.contains(eb03Value)) {
				// Error
				short status = 0;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.ERROR_MSG_RESTRICTED_EB03,
						new String[] { DomainConstants.EMPTY });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
				break;
			}
		}
	}

	// get the list of restricted EB03's
	protected List<String> getRestrictedEB03() {
		List<String> restrictedEB03 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.RESTRICTED_EB03,
						DomainConstants.PROPERTY_FILE_NAME);

		return restrictedEB03;
	}
}
