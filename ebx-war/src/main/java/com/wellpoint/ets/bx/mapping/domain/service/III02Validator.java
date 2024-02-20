/*
 * Created on May 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class III02Validator extends Validator implements HippaSegmentValidator{

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public List validate(Mapping mapping)throws DomainException {
		if(null == mapping){
		    throw new DomainException("Mapping  object expected.");
		}
		List <HippaSegmentValidationResult> hippaSegmentValidationResultList = new ArrayList <HippaSegmentValidationResult>();
	    List hippaCodeSelectedValues = new ArrayList();
	    List hippaCodePossibleValues = null;
	 
		if(mapping.getIi02() == null){
			return null;
		}
		
		if(null != mapping.getIi02().getHippaCodeSelectedValues()){
		    hippaCodeSelectedValues = mapping.getIi02().getHippaCodeSelectedValues();
		}
		
		if (null !=  mapping.getIi02().getHippaCodePossibleValues()) {
			hippaCodePossibleValues	= mapping.getIi02().getHippaCodePossibleValues();
		}
		
		if(null != mapping.getVariable()){
			//Added as part of SSCR 19537
			hippaSegmentValidationResultList = validateIII02ForValidHippaValues(hippaCodePossibleValues, mapping, hippaSegmentValidationResultList);
		}
		validateIII02BasedOnEB03(mapping, hippaSegmentValidationResultList);
		return hippaSegmentValidationResultList;
	}
	
	/**
	 * Method to validate III02 against EB03 values.
	 * Method will check whether III02 is mapped against given set of EB03s
	 * If Yes, throw error. 
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	public void validateIII02BasedOnEB03(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {

		short status = 0;
		List<String> restrictedEB03s = getRestrictedEB03();
		
		if (null != mapping) {
			// For variable
			if (null != mapping.getVariable() && !StringUtils.isEmpty(mapping.getVariable().getVariableId())) {
				validateForEb03(mapping, hippaSegmentValidationResultList, restrictedEB03s, status);
			}
			// For Header rule
			if (null != mapping.getRule()
					&& null != mapping.getRule().getHeaderRuleId()
					&& !StringUtils.isEmpty(mapping.getRule().getHeaderRuleId())) {
				validateForEb03(mapping, hippaSegmentValidationResultList, restrictedEB03s, status);
				

			}
		}
	}

	/**
	 * Method will check whether III02 is mapped against given set of EB03s
	 * Method will check at Individual EB03 level and also will check at header rule/ variable level.
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @param restrictedEB03s
	 * @param status
	 */
	private void validateForEb03(
			Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList,
			List<String> restrictedEB03s, short status) {
		String eb03IndAssnInd = mapping.getIndvdlEb03AssnIndicator();
		if (null != eb03IndAssnInd
				&& DomainConstants.Y.equalsIgnoreCase(eb03IndAssnInd)) {
			validateIII02OnEB03AtIndividualLevel(mapping,
					hippaSegmentValidationResultList, restrictedEB03s, status);

		} else {
			validateIII02OnEB03AtNonIndividualLevel(mapping,
					hippaSegmentValidationResultList, restrictedEB03s, status);
		}

	}

	/**
	 * Method will check whether III02 is mapped against given set of EB03s
	 * Method will check at header rule/ variable level
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @param restrictedEB03s
	 * @param status
	 */
	private void validateIII02OnEB03AtNonIndividualLevel(
			Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList,
			List<String> restrictedEB03s, short status) {
		
		List<String> eb03Values = mapping.getEb03Values();
		String iii02 = mapping.getIi02Value();
		for (String eb03Value : eb03Values) {
			if (!StringUtils.isEmpty(eb03Value) && !StringUtils.isEmpty(iii02) && restrictedEB03s.contains(eb03Value) ) {
				
				HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.ERROR_MSG_RESTRICTED_EB03_FOR_IIIO2,
						new String[] { DomainConstants.EMPTY });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
				break;
			}
		}
		
	}

	/**
	 * Method will check whether III02 is mapped against given set of EB03s
	 * Method will check at Individual EB03 level
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @param restrictedEB03s
	 * @param status
	 */
	private void validateIII02OnEB03AtIndividualLevel(
			Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList,
			List<String> restrictedEB03s, short status) {
		
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		if(null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()){
			List<EB03Association> eb03Associations = mapping.getEb03().getEb03Association();
			if (!eb03Associations.isEmpty()) {
				for (EB03Association eb03Assn : eb03Associations) {
					if (null != eb03Assn && null != eb03Assn.getEb03()) {
						String eb03Value = eb03Assn.getEb03().getValue();
						if(restrictedEB03s.contains(eb03Value)) {
							List <HippaCodeValue> iii02List = eb03Assn.getIii02List();
							if(!BxUtil.checkIsEmpty(iii02List)) {
								String errorMessage = ValidatorConstants.ERROR_MSG_RESTRICTED_EB03_FOR_IIIO2;
								hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
										hippaSegmentValidationResult, status,
										errorMessage,
										new String[] { DomainConstants.EMPTY });
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);
								break;
							}
							
						}
					}
				}
			}
		}
		
	}

	
	/**
	 * Method to check for valid III02 values.
	 * @param hippaCodePossibleValues
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * @return
	 */
	private List <HippaSegmentValidationResult> validateIII02ForValidHippaValues(
			List<HippaCodeValue> hippaCodePossibleValues, Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {

		String iii02Val = "";
		String errorMessage = "";
		short status = 0;
		List<String> possibleHippaCodesForIII02List = new ArrayList<String>();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		List<String> invalidIII02List = new ArrayList<String>();

			
		if (null != hippaCodePossibleValues && !hippaCodePossibleValues.isEmpty()) {
			for (HippaCodeValue possibleHippaCodesForIII02 : hippaCodePossibleValues) {
				if (null != possibleHippaCodesForIII02
						&& null != possibleHippaCodesForIII02.getValue()) {
					possibleHippaCodesForIII02List.add(possibleHippaCodesForIII02
							.getValue());
				}
			}
		}
		
		if (null != mapping.getIndvdlEb03AssnIndicator()
				&& mapping.getIndvdlEb03AssnIndicator().equalsIgnoreCase("Y")) {
			if (null != mapping.getEb03()
					&& null != mapping.getEb03().getEb03Association()
					&& !mapping.getEb03().getEb03Association().isEmpty()) {
				
				List<EB03Association> eb03Associations = mapping.getEb03().getEb03Association();
				
				if (!eb03Associations.isEmpty()) {
					for (EB03Association eb03Assn : eb03Associations) {
						if (null != eb03Assn && null != eb03Assn.getIii02List()&& !eb03Assn.getIii02List().isEmpty()) {
							for (HippaCodeValue iii02CodeValue : eb03Assn.getIii02List()) {
								if (null != iii02CodeValue && null != iii02CodeValue.getValue()) {
									iii02Val = iii02CodeValue.getValue().trim();
								}
								if (!DomainConstants.EMPTY.equals(iii02Val)) {
									if (!possibleHippaCodesForIII02List.contains(iii02Val)) {
										invalidIII02List.add(iii02Val);
									} 
								}
							}
						}
					}
				}
			}
			if (null != invalidIII02List && !invalidIII02List.isEmpty()) {
				String commaSeparatedinvalidIII02List = BxUtil
						.getAsCSV(invalidIII02List);
				errorMessage = ValidatorConstants.INVALID_III02;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status, errorMessage,
						new String[] { commaSeparatedinvalidIII02List });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
			return hippaSegmentValidationResultList;
			
		} else {
			
			String hipaaName = null;
			List<HippaCodeValue> iii02SelectedValues = new ArrayList<HippaCodeValue>();
			
			if (null != mapping.getIi02()) {
				hipaaName = mapping.getIi02().getName();
				if (null != mapping.getIi02().getHippaCodeSelectedValues() 
						&& !mapping.getIi02().getHippaCodeSelectedValues().isEmpty()) {
					iii02SelectedValues = mapping.getIi02().getHippaCodeSelectedValues();
				}
			}
			hippaSegmentValidationResultList = validate(hipaaName, hippaCodePossibleValues,iii02SelectedValues, mapping);
			return hippaSegmentValidationResultList;
		}
	}
	
	/** Method to get the list of EB03 values that should not be mapped to III02
	 * @return
	 */
	protected List<String> getRestrictedEB03() {
		List<String> restrictedEB03 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.EB03_III02_VALIDATION_LIST,
						DomainConstants.PROPERTY_FILE_NAME);
		return restrictedEB03;
	}
}



