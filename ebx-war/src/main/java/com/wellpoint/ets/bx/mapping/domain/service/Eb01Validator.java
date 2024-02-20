package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

public class Eb01Validator extends Validator implements HippaSegmentValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate
	 * (com.wellpoint.ets.bx.mapping.domain.entity.Mapping) Eb01 is mandatory
	 * for all the variable mappings. Eb01 should have only one value.(not
	 * checked)
	 */
	public List validate(Mapping mapping) throws DomainException {
		if (null == mapping) {
			throw new DomainException("Mapping  object expected.");
		}
		List hippaSegmentValidationResultList = new ArrayList();
		String eb01Value = "";
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		ValidationUtility validationUtility = new ValidationUtility();
		short status = 0; // for failure message
		boolean eb01Empty = false;

		if (mapping.getEb01() == null) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.MAPPING_MANDATORY,
					new String[] { DomainConstants.EB01_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
			return hippaSegmentValidationResultList;
		}

		eb01Value = mapping.getEB01Value();
		if (eb01Value == null || eb01Value.trim().equals("")) {
			eb01Empty = true;
		}

		if (eb01Empty) {
			hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
					hippaSegmentValidationResult, status,
					ValidatorConstants.MAPPING_MANDATORY,
					new String[] { DomainConstants.EB01_NAME });
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
			return hippaSegmentValidationResultList;
		} else {
			List hippaCodePossibleValues = mapping.getEb01()
					.getHippaCodePossibleValues();
			List hippaCodeSelectedValues = mapping.getEb01()
					.getHippaCodeSelectedValues();
			List hippaSegmentValidationList = validate(mapping.getEb01()
					.getName(), hippaCodePossibleValues,
					hippaCodeSelectedValues, mapping);
			if (hippaSegmentValidationList != null
					&& !hippaSegmentValidationList.isEmpty()) {
				for (Iterator itr = hippaSegmentValidationList.iterator(); itr
						.hasNext();) {
					HippaSegmentValidationResult result = (HippaSegmentValidationResult) itr
							.next();
					hippaSegmentValidationResultList.add(result);
				}
			}
		}
		// section 3 changes

		List spsFormats = new ArrayList();
		if (null != mapping.getSpsId()) {

			if (null != mapping.getSpsId().getSpsDetail()
					&& (!mapping.getSpsId().getSpsDetail().isEmpty())) {

				spsFormats = validationUtility
						.getSpsFormatsFromSPSDetail(mapping.getSpsId()
								.getSpsDetail());
			}
		}
		// setting status = 2 to show a warning message
		status = 2;
		String variableFormat = "";
		String variableDescription = "";
		String spsDescription = "";
		if (null != mapping.getVariable()
				&& null != mapping.getVariable().getVariableFormat()) {
			variableFormat = mapping.getVariable().getVariableFormat().trim()
					.toUpperCase();

		}
		if (null != mapping.getVariable()
				&& null != mapping.getVariable().getDescription()) {

			variableDescription = mapping.getVariable().getDescription().trim()
					.toUpperCase();
		}
		if (null != mapping.getSpsId()
				&& null != mapping.getSpsId().getSpsDesc()) {

			spsDescription = mapping.getSpsId().getSpsDesc().trim()
					.toUpperCase();
		}

		// variable/sps parameter is mapped to EB01=B and has format PCT
		if (((null != variableFormat) && ValidatorConstants.FORMAT_PCT
				.equalsIgnoreCase(variableFormat))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_PCT)))) {
			if (eb01Value.equals(ValidatorConstants.CO_PAYMENT)) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_B_PCT,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
			// EB01 is not = A or B for FORMAT = PCT
			else if ((!eb01Value.equals(ValidatorConstants.CO_PAYMENT))
					&& (!eb01Value.equals(ValidatorConstants.COINSURANCE))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_A_B_PCT,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);

			}
		}

		// AUGUST RELEASE- EWPD SECTION

		// T8.1.5 SPS parameter is not mapped to EB01=C and has format DOL and
		// SPS ID
		// description contains DEDUCTIBLE/SERVICE DEDUCTIBLE
		if ((null != spsFormats)
				&& (!spsFormats.isEmpty())
				&& (spsFormats.contains(ValidatorConstants.FORMAT_DOL))
				&& ((spsDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_DED.toUpperCase()) != -1) || (spsDescription
						.toUpperCase()
						.indexOf(
								ValidatorConstants.DESCRIPTION_SERVICE_DEDUCTIBLE
										.toUpperCase()) != -1))) {
			// EB01 is not = C for FORMAT = DOL
			if ((!eb01Value.equals(ValidatorConstants.DEDUCTIBLE))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB01_C_DEDUCTIBLE_eWPD,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		// T8.1.7 SPS parameter is not mapped to EB01=F and has format DOL and
		// SPS ID
		// description contains MAXIMUM / LIMIT
		
	 
		if ((null != spsFormats)
				&& (!spsFormats.isEmpty())
				&& (spsFormats.contains(ValidatorConstants.FORMAT_DOL))
				&& ((spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_MAXIMUM.toUpperCase()) != -1) 
				   ||(spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_LIMIT.toUpperCase()) != -1))
			&&
			(!((spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_COPAY.toUpperCase()) != -1 
					&& spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_MAX.toUpperCase()) != -1)
					||
		           (spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_COPAY.toUpperCase()) != -1 
					   && spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_LIMIT.toUpperCase()) != -1)
					||
					(spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_PENALTY.toUpperCase()) != -1 
							&& spsDescription.toUpperCase().indexOf(ValidatorConstants.DESCRIPTION_MAX.toUpperCase()) != -1)))
		
		  )
		{
			// EB01 is not = F for FORMAT = DOL
			if ((!eb01Value.equals(ValidatorConstants.LIMITATION))) {

				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_F_MPL_eWPD,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);

			}
		} 
		
		
		
		// T8.1.9 SPS parameter is not mapped to EB01=G and has format DOL and
		// SPS ID
		// description indicates OUT-OF-POCKET or STL
		if ((null != spsFormats)
				&& (!spsFormats.isEmpty())
				&& (spsFormats.contains(ValidatorConstants.FORMAT_DOL))
				&& ((spsDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_OUT_OF_POCKET
								.toUpperCase()) != -1)
						|| (spsDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_OOP
										.toUpperCase()) != -1) || (spsDescription
						.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_STL
										.toUpperCase()) != -1))) {
			// EB01 is not = G for FORMAT = DOL
			if ((!eb01Value.equals(ValidatorConstants.OUT_OF_POCKET))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB01_G_OUT_OF_POCKET_STL_eWPD,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// T8.1.3 SPS parameter is not mapped to EB01=B and has format DOL and
		// SPS ID
		// description contains COPAY not followed by MAX/ MAXIMUM

		if ((null != spsDescription)
				&& (!eb01Value.equals(ValidatorConstants.CO_PAYMENT))
				&& (spsFormats.contains(ValidatorConstants.FORMAT_DOL))

		) {

			String check[] = { (ValidatorConstants.DESCRIPTION_COPAY),
					(ValidatorConstants.DESCRIPTION_COPAYMENT) };
			for (int index = 0; index < check.length; index++) {

				int indexOfStart = spsDescription.indexOf(check[index]);
				if (indexOfStart != -1) {

					boolean test = false;
					String temp = spsDescription.substring(indexOfStart)
							.toUpperCase();

					String checkMax[] = { ValidatorConstants.DESCRIPTION_MAX,
							ValidatorConstants.DESCRIPTION_MAXIMUM };
					for (int indexMax = 0; indexMax < checkMax.length; indexMax++) {
						if (temp.indexOf((checkMax[indexMax])) != -1) {

							test = true;
							break;
						}

					}
					if (test == false) {

						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping,
								new HippaSegmentValidationResult(),
								status,
								ValidatorConstants.WARNING_MESSAGE_EB01_B_COPAY_eWPD,
								new String[] {});
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					}
					break;
				}
			}

		}

		// WPD SECTION
		// T8.1.4 variable is not mapped to EB01=C and has format DOL and
		// variable
		// description contains DED/ DEDUCTIBLE
		if ((null != variableDescription)
				&& (variableDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_DED.toUpperCase()) != -1)
				&& ValidatorConstants.FORMAT_DOL
						.equalsIgnoreCase(variableFormat)) {
			// EB01 is not = C for FORMAT = DOL
			if ((!eb01Value.equals(ValidatorConstants.DEDUCTIBLE))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_C_DEDUCTIBLE,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		// T8.1.6 variable is not mapped to EB01=F and has format DOL and
		// variable
		// description contains MAXIMUM / LIMITATION / PENALTY
		if (((null != variableDescription)
				&& ((variableDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_PENALTY.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_PNLTY
										.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_PENLTY
										.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_MAX
										.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase()
								.indexOf(
										ValidatorConstants.DESCRIPTION_MX
												.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_LMT
										.toUpperCase()) != -1) || (variableDescription
						.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_LIM
										.toUpperCase()) != -1)) && ValidatorConstants.FORMAT_DOL
				.equalsIgnoreCase(variableFormat))) {

			// EB01 is not = F for FORMAT = DOL
			if ((!eb01Value.equals(ValidatorConstants.LIMITATION))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_F_MPL,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		// T8.1.8 variable/sps parameter is not mapped to EB01=G and has format
		// DOL and
		// variable description indicates OUT-OF-POCKET or STL
		if (((null != variableDescription)
				&& ((variableDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_OUT_OF_POCKET
								.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_OOP
										.toUpperCase()) != -1)|| (variableDescription.toUpperCase().indexOf(
												ValidatorConstants.DESCRIPTION_OUT_OF_POCKET1
												.toUpperCase()) != -1) || (variableDescription
						.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_SLL
										.toUpperCase()) != -1)) && ValidatorConstants.FORMAT_DOL
				.equalsIgnoreCase(variableFormat))) {
			// EB01 is not = G for FORMAT = DOL
			if ((!eb01Value.equals(ValidatorConstants.OUT_OF_POCKET))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB01_G_OUT_OF_POCKET_SLL,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		// T8.1.10 // variable/sps parameter is not mapped to EB01=DW and has
		// format Y/N and variable description DEDUCTIBLE WAIVED
		if (((null != variableDescription)
				&& ((variableDescription.toUpperCase().indexOf(
						ValidatorConstants.DESCRIPTION_DED_WAIVE.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_DEDUCT_WAIVED
										.toUpperCase()) != -1)
						|| (variableDescription
								.toUpperCase()
								.indexOf(
										ValidatorConstants.DESCRIPTION_DEDUCTIBLE_WAIVED
												.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_DED_WVD
										.toUpperCase()) != -1)
						|| (variableDescription.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_DED_WAIVE
										.toUpperCase()) != -1) || (variableDescription
						.toUpperCase().indexOf(
								ValidatorConstants.DESCRIPTION_DED_WAV
										.toUpperCase()) != -1)) && ValidatorConstants.FORMAT_YN
				.equalsIgnoreCase(variableFormat))) {
			// EB01 is not = DW for FORMAT = Y/N
			if ((!eb01Value.equals(ValidatorConstants.DEDUCTIBLE_WAIVED))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB01_DW_DEDUCTIBLE_WAIVED,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		// T8.1.11 variable/sps parameter is mapped to EB01=BC and has format
		// Y/N
		// and variable description COVERED/ PAYABLE
		String[] exclusions={ValidatorConstants.DESCRIPTION_COVERED.toUpperCase(),ValidatorConstants.DESCRIPTION_COVER.toUpperCase(),
				ValidatorConstants.DESCRIPTION_COVD.toUpperCase(),ValidatorConstants.DESCRIPTION_COV.toUpperCase(),
				ValidatorConstants.DESCRIPTION_PAY.toUpperCase(), ValidatorConstants.DESCRIPTION_PAYABLE.toUpperCase()};
		if (((null != variableDescription)
				&& ValidatorConstants.FORMAT_YN
						.equalsIgnoreCase(variableFormat) && (BxUtil.isWordExists(variableDescription, exclusions)))) {
			// EB01 = BC for FORMAT = Y/N
			if ((eb01Value.equals(ValidatorConstants.BENEFIT_COVERED))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB01_BC_COVERED_PAYABLE,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// T8.1.2 variable is not mapped to EB01=B and has format DOL and
		// variable
		// description contains COPAY NOT FOLLOWED BY MAX/MX/ MAXIMUM

		if ((null != variableDescription)
				&& (!eb01Value.equals(ValidatorConstants.CO_PAYMENT))
				&& (ValidatorConstants.FORMAT_DOL
						.equalsIgnoreCase(variableFormat))

		) {

			String check[] = { (ValidatorConstants.DESCRIPTION_COPAY),
					(ValidatorConstants.DESCRIPTION_COPAYMENT),
					(ValidatorConstants.DESCRIPTION_C0_PAYMENT),
					(ValidatorConstants.DESCRIPTION_CP),
					(ValidatorConstants.DESCRIPTION_COP),
					(ValidatorConstants.DESCRIPTION_CO_PAY) };
			for (int index = 0; index < check.length; index++) {

				int indexOfStart = variableDescription.indexOf(check[index]);
				if (indexOfStart != -1) {

					boolean test = false;
					String temp = variableDescription.substring(indexOfStart)
							.toUpperCase();

					String checkMax[] = { ValidatorConstants.DESCRIPTION_MAX,
							ValidatorConstants.DESCRIPTION_MAXIMUM,
							ValidatorConstants.DESCRIPTION_MX };
					for (int indexMax = 0; indexMax < checkMax.length; indexMax++) {
						if  (temp.indexOf((checkMax[indexMax])) != -1) {

							test = true;
							break;
						}

					}
					if (test == false) {

						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping,
								new HippaSegmentValidationResult(),
								status,
								ValidatorConstants.WARNING_MESSAGE_EB01_B_COPAY,
								new String[] {});
						hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
					}
					break;
				}
			}

		}

		// AUGUST RELEASE VALIDATION ENDS

		// if Y/N variables/sps parameters are not mapped to EB01 = F, BC, DW or
		// U
		if (((null != variableFormat) && ValidatorConstants.FORMAT_YN
				.equalsIgnoreCase(variableFormat))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_YN)))) {
			if ((!eb01Value.equals(ValidatorConstants.LIMITATION))
					&& (!eb01Value.equals(ValidatorConstants.DEDUCTIBLE_WAIVED))
					&& (!eb01Value.equals(ValidatorConstants.BENEFIT_COVERED))
					&& (!eb01Value
							.equals(ValidatorConstants.CONTACT_FOLLOWING_ENTITY_U))) {

				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_YN,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}

		// EB01 = U, then validation needs to be bypassed
		if (((null != variableFormat) && (ValidatorConstants.FORMAT_VISIT
				.equalsIgnoreCase(variableFormat)
				|| ValidatorConstants.FORMAT_HOURS
						.equalsIgnoreCase(variableFormat)
				|| ValidatorConstants.FORMAT_MTH
						.equalsIgnoreCase(variableFormat)
				|| ValidatorConstants.FORMAT_DAY
						.equalsIgnoreCase(variableFormat)
				|| ValidatorConstants.FORMAT_OCCURENCE
						.equalsIgnoreCase(variableFormat)
				|| ValidatorConstants.FORMAT_YEARS
						.equalsIgnoreCase(variableFormat) || ValidatorConstants.VARIABLE_FORMAT_AGE
				.equalsIgnoreCase(variableFormat)))
				|| ((null != spsFormats)
						&& (!spsFormats.isEmpty())
						&& (spsFormats
								.contains(ValidatorConstants.FORMAT_VISIT))
						|| (spsFormats
								.contains(ValidatorConstants.FORMAT_HOURS))
						|| (spsFormats.contains(ValidatorConstants.FORMAT_MTH))
						|| (spsFormats.contains(ValidatorConstants.FORMAT_DAY))
						|| (spsFormats
								.contains(ValidatorConstants.FORMAT_OCCURENCE))
						|| (spsFormats
								.contains(ValidatorConstants.FORMAT_YEARS)) || (spsFormats
						.contains(ValidatorConstants.VARIABLE_FORMAT_AGE)))) {
			// if U,
			if (!eb01Value.equals(ValidatorConstants.LIMITATION)) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_F,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// a warning message if variable/sps parameter had EB01='U' and has
		// other EB/HSD/III02 mappings
		boolean otherCodes = false;
		if (eb01Value.equals(ValidatorConstants.CONTACT_FOLLOWING_ENTITY_U)) {
			if (!validationUtility.isEmpty(mapping.getEB02Value())
					|| !validationUtility.isEmpty(mapping.getEB06Value())
					|| !validationUtility.isEmpty(mapping.getEB09Value())
					|| !validationUtility.isEmpty(mapping.getHsd01Value())
					|| !validationUtility.isEmpty(mapping.getHsd02Value())
					|| !validationUtility.isEmpty(mapping.getHsd03Value())
					|| !validationUtility.isEmpty(mapping.getHsd04Value())
					|| !validationUtility.isEmpty(mapping.getHsd05Value())
					|| !validationUtility.isEmpty(mapping.getHsd06Value())
					|| !validationUtility.isEmpty(mapping.getHsd07Value())
					|| !validationUtility.isEmpty(mapping.getHsd08Value())) {

				otherCodes = true;
			}

			if (null != mapping.getVariable()) {

				if (!validationUtility.isEmpty(mapping.getEb03Values())
						|| !validationUtility.isEmpty(mapping.getIi02Value())) {

					otherCodes = true;
				}
			}
			if (otherCodes) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB01_U,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		
		
		 //****** Start for November Release - BXNI.*********
		boolean errorExists = validateEB01valuesCFG(mapping,hippaSegmentValidationResultList);
		if(!errorExists && !StringUtils.isBlank(variableDescription)){
			validateEB01valuesCFGForAccum(mapping,hippaSegmentValidationResultList);	
		}
		//****** END for November Release - BXNI.*********
		
		/* Start June 2013 Release CR 47  - This validation is not required*/
		//validateProviderArrangementIsHMO(mapping, hippaSegmentValidationResultList);
		/* End June 2013 Release CR 47  - This validation is not required*/
		
		//POR Wave 2 Oct 2015 Release
		validateMessageTextForEb01Eb03(mapping,hippaSegmentValidationResultList);
		
		//Dec 2015 Release
		validateWINFERTILITYForEb01Eb03(mapping,hippaSegmentValidationResultList);		
		return hippaSegmentValidationResultList;
	}
	
	private boolean validateEB01valuesCFG(Mapping mapping,
			List hippaSegmentValidationResultList) {
		
		ValidationUtility validationUtility = new ValidationUtility();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String eb01Value = mapping.getEB01Value();
		short status = 0; 
		List<String> values = getHsd05OrEb06ValuesToCheck();
		String hsd05Value = "";
		String eb06Value = "";
		
		if ((eb01Value.equals(ValidatorConstants.DEDUCTIBLE) 
				|| eb01Value.equals(ValidatorConstants.OUT_OF_POCKET) 
				|| eb01Value.equals(ValidatorConstants.LIMITATION))) {
			if(!validationUtility.isEmpty(mapping.getHsd05Value()) 
					&& !validationUtility.isEmpty(mapping.getEB06Value())){
				hsd05Value = mapping.getHsd05Value();
				eb06Value = mapping.getEB06Value();
				if(!values.isEmpty() && values.contains(hsd05Value) && values.contains(eb06Value)){
								hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), status,
										ValidatorConstants.ERROR_MSG_REQUIRED_EB06_FOR_EB01,
										new String[] {eb01Value,eb06Value});
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);
								return true;
				}
			}
		}
		return false;
	}

	private void validateEB01valuesCFGForAccum(Mapping mapping,
            List hippaSegmentValidationResultList){
		
		ValidationUtility validationUtility = new ValidationUtility();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String eb01Value = mapping.getEB01Value();
		short status = 2; 
		String hsd05Value = "";
		String eb06Value = "";
		List<String> values = getHsd05OrEb06ValuesToCheck();
		
		if ((eb01Value.equals(ValidatorConstants.DEDUCTIBLE) 
				|| eb01Value.equals(ValidatorConstants.OUT_OF_POCKET) 
				|| eb01Value.equals(ValidatorConstants.LIMITATION))) {
			if(!validationUtility.isEmpty(mapping.getHsd05Value())){
				hsd05Value = mapping.getHsd05Value();
				if(!values.isEmpty() && values.contains(hsd05Value)){
						if("N".equals(mapping.getSensitiveBenefitIndicator())){
							if(null == mapping.getAccumValues().get(0) || mapping.getAccumValues().get(0).equals("") || mapping.getAccumValues().isEmpty()){
								hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), status,
										ValidatorConstants.WARN_MSG_ACCUM_REQUIRED_HSD05_FOR_EB01,
										new String[] {eb01Value,hsd05Value});
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);
							}
					}
				}
			}
			if(!validationUtility.isEmpty(mapping.getEB06Value())){
				eb06Value = mapping.getEB06Value();
				if(!values.isEmpty() && values.contains(eb06Value)){
					if("N".equals(mapping.getSensitiveBenefitIndicator())){
						if(null == mapping.getAccumValues().get(0) || mapping.getAccumValues().get(0).equals("") || mapping.getAccumValues().isEmpty()){
							hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
									new HippaSegmentValidationResult(), status,
									ValidatorConstants.WARN_MSG_ACCUM_REQUIRED_EB06_FOR_EB01,
									new String[] {eb01Value, eb06Value});
							hippaSegmentValidationResultList
									.add(hippaSegmentValidationResult);
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<String> getHsd05OrEb06ValuesToCheck() {

		return SimulationResourceBundle.getResourceBundle(
				DomainConstants.HSD05_EB06_VALUES,
				DomainConstants.PROPERTY_FILE_NAME);
	}
	
	/* Start June 2013 Release CR 47  - This validation is not required*/
	/*

	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 * Method to throw the error, if Eb01 is mapped to UM for a non-HMO variable.
	 * Criteria to identify the HMO Variable is PVA = H, HM, HMO.	 
	
	private void validateProviderArrangementIsHMO(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {

		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		short status = 0;
				
		if (null != mapping.getEB01Value() && null != mapping.getVariable() && null != mapping.getVariable().getPva()) {
			String pva = mapping.getVariable().getPva();

			if (mapping.getEB01Value().equalsIgnoreCase(DomainConstants.UM) 
					&& !(pva.equalsIgnoreCase(DomainConstants.HMO)
					|| pva.equalsIgnoreCase(DomainConstants.H)
					|| pva.equalsIgnoreCase(DomainConstants.HM))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,	new HippaSegmentValidationResult(), status,
						ValidatorConstants.ERROR_MSG_UM_MAPPED_FOR_NON_HMO,
						new String[] { mapping.getEB01Value(),
								mapping.getVariable().getPva() });
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
	}*/
	
	/* End June 2013 Release */
	
	//POR Wave 2 Oct 2015 Changes
		private void validateMessageTextForEb01Eb03(Mapping mapping,
	            List hippaSegmentValidationResultList){
			short status = 2;
			if(null != mapping){
				//Variable validation
				if(null != mapping.getVariable() && null != mapping.getVariable().getVariableId()
							&& !StringUtils.isEmpty(mapping.getVariable().getVariableId())){
					if(BxUtil.hasText(mapping.getEB01Value()) && mapping.getEB01Value().equalsIgnoreCase(DomainConstants.EB01_UM)){
						Set<String> warningMessageSet =  new HashSet<String>(6);
						
						//Individual:
						if (null != mapping.getIndvdlEb03AssnIndicator()
									&& DomainConstants.Y.equalsIgnoreCase(mapping.getIndvdlEb03AssnIndicator())) {
								if (null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
										&& !mapping.getEb03().getEb03Association().isEmpty()) {
								List<EB03Association> eb03Association = mapping.getEb03().getEb03Association();
								for(EB03Association eb03AssociationObject : eb03Association){
									
									if(null != eb03AssociationObject && null != eb03AssociationObject.getEb03()
											&& null != eb03AssociationObject.getEb03().getValue()){
									
										String eb03 = BxUtil.hasText(eb03AssociationObject.getEb03().getValue()) ? eb03AssociationObject.getEb03().getValue().trim() : "";
										String message = BxUtil.hasText(eb03AssociationObject.getMessage()) ? eb03AssociationObject.getMessage().trim() : "";
										String warningMsg = validateMessageText(eb03, message);
										
										if(!StringUtils.isEmpty(warningMsg)){
											warningMessageSet.add(warningMsg);
										}
										
										if(DomainConstants.MESSAGE_ORTHONET.equalsIgnoreCase(message)){
											warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_ORTHONET);
										}else if(DomainConstants.MESSAGE_AIM_PREAUTH.equalsIgnoreCase(message)){
											warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_AIM_SH);
										}else if(DomainConstants.MESSAGE_AMERICAN_SPECIALTY_HEALTH.equalsIgnoreCase(message)){
											warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_AMERICAN_SH);
										}
										validateEb03ForMessage(eb03, message, warningMessageSet);
									}
								}
							}
						}
						// Non Individual:
						else{
							if (null != mapping.getEb03Values()){
								List<String> eb03List = mapping.getEb03Values();
								String message = BxUtil.hasText(mapping.getMessage()) ? mapping.getMessage().trim() : "";
								
								//These Warning are added to set for Reverse Validation Scenario
								if(DomainConstants.MESSAGE_ORTHONET.equalsIgnoreCase(message)){
									warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_ORTHONET);
								}else if(DomainConstants.MESSAGE_AIM_PREAUTH.equalsIgnoreCase(message)){
									warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_AIM_SH);
								}else if(DomainConstants.MESSAGE_AMERICAN_SPECIALTY_HEALTH.equalsIgnoreCase(message)){
									warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_AMERICAN_SH);
								}
								
								for(String eb03Value : eb03List){
									String warningMsg = validateMessageText(eb03Value, message);
									if(!StringUtils.isEmpty(warningMsg)){
										warningMessageSet.add(warningMsg);
									}
									validateEb03ForMessage(eb03Value, message, warningMessageSet);
							}
								
						}
					}
						if(null != warningMessageSet && !warningMessageSet.isEmpty()){
							for(String warnMsg : warningMessageSet){
								hippaSegmentValidationResultList.add(setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), status,
										warnMsg,new String[] {}));
							}
						}
					}
				}
			}
		}
					
		/*
		 * If EB01 = UM & EB03 among values: AE/ PT/AD then message text should be ORTHONET. 
		 * Else eBX will display a warning message: “Since EB01=UM & EB03=AE / PT / AD , the message text ORTHONET required if SERVICE authorized by ORTHONET”
		 * 
		 * If EB01 = UM & EB03 = 33 then message text should be mapped to: AMERICAN SPECIALTY HEALTH 
		 * Else eBX will display the warning message “Since EB01=UM & EB03=33 , the message text  AMERICAN SPECIALTY HEALTH required if SERVICE authorized by AMERICAN SPECIALTY HEALTH”
		 * 
		 * If EB01 = UM & EB03 = 4/73/ 62/ ST/6/78 then message text should be mapped to AIM SPECIALITY HEALTH. 
		 * Else eBX will display the warning message “Since EB01=UM & EB03=4/ 73/ 62/ST/ 6/ 78 , the message text AIM PREAUTH  required if SERVICE authorized by AIM SPECIALITY HEALTH”
		 */


		private String validateMessageText(String eb03, String message) {
			List<String> eb03ValuesForOrthonet = SimulationResourceBundle.getResourceBundle(DomainConstants.EB03_ORTHONET_LIST, DomainConstants.PROPERTY_FILE_NAME);
			List<String> eb03ValuesForASH = SimulationResourceBundle.getResourceBundle(DomainConstants.EB03_ASH_LIST, DomainConstants.PROPERTY_FILE_NAME);
			String warningMessage = "";
			//Validation for Message: ORTHONET
			if(null != eb03ValuesForOrthonet && eb03ValuesForOrthonet.contains(eb03)){
				if(null != message && !message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_ORTHONET)){
					warningMessage = ValidatorConstants.WARN_MSG_ORTHONET;
				}
			}//Validation for Message: ASH
			else if(null != eb03ValuesForASH && eb03ValuesForASH.contains(eb03)){
				if(null != message && !message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_AIM_PREAUTH)){
					warningMessage = ValidatorConstants.WARN_MSG_AIM_SH;
				}
			}//Validation for Message: AMERICAN SPECIALTY HEALTH
			else if(eb03.equalsIgnoreCase(DomainConstants.EB03_33)){
				if(null != message && !message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_AMERICAN_SPECIALTY_HEALTH)){
					warningMessage = ValidatorConstants.WARN_MSG_AMERICAN_SH;
				}
			}
			return warningMessage;
		}
		
		/*
		 * If message text =ORTHONET and EB01=UM  and if EB03 is not equal to  AE/ PT/AD show a warning message “Message text, ORTHONET covers EB03’s  AE / PT / AD. Please make sure your EB03 mappings are correct.”
		 * If message text = AMERICAN SPECIALTY HEALTH and EB01=UM  and if EB03 is not equal to  33 show a warning message “Message text , AMERICAN SPECIALTY HEALTH covers EB03 33. Please make sure your EB03 mappings are correct.”
		 * If message text = AIM PREAUTH and EB01=UM  and if EB03 is not equal to  4/ 73/ 62/ ST/ 6/ 78  show a warning message “Message text, AIM PREAUTH covers EB03 4/ 73/ 62/ ST/ 6/ 78 . Please make sure your EB03 mappings are correct.”
		 */
		
		
		private void validateEb03ForMessage(String eb03, String message, Set<String> warnMsgSet){
			List<String> eb03ValuesForOrthonet = SimulationResourceBundle.getResourceBundle(DomainConstants.EB03_ORTHONET_LIST, DomainConstants.PROPERTY_FILE_NAME);
			List<String> eb03ValuesForASH = SimulationResourceBundle.getResourceBundle(DomainConstants.EB03_ASH_LIST, DomainConstants.PROPERTY_FILE_NAME);
					//Validation for Message: ORTHONET, EB03 = AE/PT/AD
					if(null != message && message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_ORTHONET)){
						if(null != eb03ValuesForOrthonet && eb03ValuesForOrthonet.contains(eb03)){
							warnMsgSet.remove(ValidatorConstants.WARN_MSG_EB03_ORTHONET);
						}
					}//Validation for Message: ASH, EB03 = 4/73/62/ST/6/78
					else if(null != message && message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_AIM_PREAUTH)){
						if(null != eb03ValuesForASH && eb03ValuesForASH.contains(eb03)){
							warnMsgSet.remove(ValidatorConstants.WARN_MSG_EB03_AIM_SH);
						}
					}//Validation for Message: AMERICAN SPECIALTY HEALTH, EB03 = 33
					else if(null != message && message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_AMERICAN_SPECIALTY_HEALTH)){
						if(eb03.equalsIgnoreCase(DomainConstants.EB03_33)){
							warnMsgSet.remove(ValidatorConstants.WARN_MSG_EB03_AMERICAN_SH);
						}
					}
		}
		
		
		//WINFERTILITY Validation --- Dec Release 2015
	
		private void validateWINFERTILITYForEb01Eb03(Mapping mapping,
	            List hippaSegmentValidationResultList){
			
			short warningStatus = 2;
			short errorStatus = 0;
			if(null != mapping){
				//Variable validation
				if(null != mapping.getVariable() && null != mapping.getVariable().getVariableId()
							&& !StringUtils.isEmpty(mapping.getVariable().getVariableId())){
					if(BxUtil.hasText(mapping.getEB01Value()) && mapping.getEB01Value().equalsIgnoreCase(DomainConstants.EB01_UM)){
						Set<String> warningMessageSet =  new HashSet<String>(6);
						Set<String> errorMsgSet =  new HashSet<String>(6);
						String errorMsg = "";
						String warningMsg = "";
						//Individual:
						if (null != mapping.getIndvdlEb03AssnIndicator()
									&& DomainConstants.Y.equalsIgnoreCase(mapping.getIndvdlEb03AssnIndicator())) {
							if (null != mapping.getEb03() && null != mapping.getEb03().getEb03Association()
									&& !mapping.getEb03().getEb03Association().isEmpty()) {
								List<EB03Association> eb03AssociationList = mapping.getEb03().getEb03Association();
								List<String> eb03List = new ArrayList<String>();
								boolean messageWinfertility = false;
								boolean validEB03Exist = false;
								boolean invalidEB03Exist = false; 
								boolean validEB03Values = false;
								for(EB03Association eb03Association: eb03AssociationList){
									eb03List.add(eb03Association.getEb03().getValue());
								}
								if(CollectionUtils.isNotEmpty(eb03List)){
									if(!eb03List.contains(DomainConstants.EB03_61) && !eb03List.contains(DomainConstants.EB03_83)){
										validEB03Values = true;
									}
								}
								
								for(EB03Association eb03AssociationObject : eb03AssociationList){
									if(null != eb03AssociationObject && null != eb03AssociationObject.getEb03()
											&& null != eb03AssociationObject.getEb03().getValue()){
										
										String eb03 = BxUtil.hasText(eb03AssociationObject.getEb03().getValue()) ? eb03AssociationObject.getEb03().getValue().trim() : "";
										String message = BxUtil.hasText(eb03AssociationObject.getMessage()) ? eb03AssociationObject.getMessage().trim() : "";
										warningMsg = validateWinfertilityMessageTextEmpty(eb03, message);
										errorMsg = validateWinfertilityErrorMessage(eb03,message);
										if(StringUtils.isNotEmpty(warningMsg)){
											warningMessageSet.add(warningMsg);
										}
										if(StringUtils.isNotEmpty(errorMsg)){
											errorMsgSet.add(errorMsg);
										}
										if(DomainConstants.MESSAGE_WINFERTILITY.equalsIgnoreCase(message)){
											messageWinfertility = true;
										}
										if (StringUtils.isNotEmpty(eb03) && validEB03Values) {
											validateEb03ForWinfertilityMessage(eb03, message, warningMessageSet);
										}
										if(messageWinfertility){
											if(eb03.equalsIgnoreCase(DomainConstants.EB03_61) || eb03.equalsIgnoreCase(DomainConstants.EB03_83)){
												 validEB03Exist = true;
											}else{
												invalidEB03Exist = true;
											}
										}
									}
								}
								if (validEB03Exist && invalidEB03Exist) {
									warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_WINFERTILITY3);
								}
								
							}
							
						}
						// Non Individual:
						else {
							if (null != mapping.getEb03Values()) {
								List<String> eb03List = mapping.getEb03Values();
								boolean validEB03Values = false;
								if(CollectionUtils.isNotEmpty(eb03List)){
									if(!eb03List.contains(DomainConstants.EB03_61) && !eb03List.contains(DomainConstants.EB03_83)){
										validEB03Values = true;
									}
								}
								String message = BxUtil.hasText(mapping
										.getMessage()) ? mapping.getMessage()
										.trim() : "";
								boolean messageWinfertility = false;
								boolean validEB03Exist = false;
								boolean invalidEB03Exist = false; 
								
								if (DomainConstants.MESSAGE_WINFERTILITY
										.equalsIgnoreCase(message)) {
									messageWinfertility = true;
								}
	
								for (String eb03Value : eb03List) {
									
									if(StringUtils.isNotEmpty(eb03Value)){
										warningMsg = validateWinfertilityMessageTextEmpty(eb03Value, message);
									}
									if(StringUtils.isNotEmpty(eb03Value)){
										errorMsg = validateWinfertilityErrorMessage(eb03Value,message);
									}
									if (StringUtils.isNotEmpty(warningMsg)) {
										warningMessageSet.add(warningMsg);
									}
									if (StringUtils.isNotEmpty(errorMsg)) {
										errorMsgSet.add(errorMsg);
									}
									if (StringUtils.isNotEmpty(eb03Value) && validEB03Values) {
										validateEb03ForWinfertilityMessage(eb03Value, message,
												warningMessageSet);
									}
									if(StringUtils.isNotEmpty(eb03Value) && messageWinfertility){
										if(eb03Value.equalsIgnoreCase(DomainConstants.EB03_61) || eb03Value.equalsIgnoreCase(DomainConstants.EB03_83)){
											 validEB03Exist = true;
										}else{
											invalidEB03Exist = true;
										}
									}
								}
								if (validEB03Exist && invalidEB03Exist) {
									warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_WINFERTILITY3);
								}
							}
						}
						if(null != warningMessageSet && !warningMessageSet.isEmpty()){
							for(String warnMsg : warningMessageSet){
								hippaSegmentValidationResultList.add(setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), warningStatus,
										warnMsg,new String[] {}));
							}
						}
						
						if(null != errorMsgSet && !errorMsgSet.isEmpty()){
							for(String errorMessage : errorMsgSet){
								hippaSegmentValidationResultList.add(setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), errorStatus,
										errorMessage,new String[] {}));
							}
						}
					}
				}
			}
		}

		/*
		 * If EB01 =  UM and EB03 = 61/83, the message text "WINFERTILITY" should be coded.
		 * If the message text is not coded for the above mappings, there should be a warning message 
		 * 	that says "Since EB01 = UM & EB03 = 61/83 the message text WINFERTILITY is required if SERVICE authorized by WINFERTILITY".
		 */
		
		private String validateWinfertilityMessageTextEmpty(String eb03,String message) {
			List<String> eb03ValuesForWinfertility = SimulationResourceBundle.getResourceBundle(DomainConstants.EB03_WINFERTILITY_LIST, DomainConstants.PROPERTY_FILE_NAME);
			String warningMessage = "";
			if(null != eb03ValuesForWinfertility && eb03ValuesForWinfertility.contains(eb03)){
				if(StringUtils.isEmpty(message)){
					warningMessage = ValidatorConstants.WARN_MSG_WINFERTILITY;
				}
			}
			return warningMessage;
		}
		
		/*
		 * If EB01 =  UM and message text = "WINFERTILITY" and EB03 should be either 61 or 83. Otherwise there 
		 * 	should be a warning message that says "Message text, WINFERTILITY covers EB03s  61/83. Please make sure your EB03 mappings are correct."
		 */
		
		private void validateEb03ForWinfertilityMessage(String eb03,String message, Set<String> warningMessageSet) {
			if (null != message && message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_WINFERTILITY)) {
				if (StringUtils.isNotEmpty(eb03)) {
					if (!eb03.equalsIgnoreCase(DomainConstants.EB03_61) && !eb03.equalsIgnoreCase(DomainConstants.EB03_83)) {
						warningMessageSet.add(ValidatorConstants.WARN_MSG_EB03_WINFERTILITY);
					}
				}
			}
		}
		
		/*
		 * If the message text is coded, it has to be exactly WINFERTILITY. Otherwise there should be a hard edit message that says 
		 *         "Since EB01 = UM & EB03 = 61/83 the message text must be either WINFERTILITY or blank".
		 */
		
		private String validateWinfertilityErrorMessage(String eb03,
				String message) {
			List<String> eb03ValuesForOrthonet = SimulationResourceBundle.getResourceBundle(DomainConstants.EB03_WINFERTILITY_LIST, DomainConstants.PROPERTY_FILE_NAME);
			String errorMessage = "";
			if(null != eb03ValuesForOrthonet && eb03ValuesForOrthonet.contains(eb03)){
				if(StringUtils.isNotEmpty(message) && !message.trim().equalsIgnoreCase(DomainConstants.MESSAGE_WINFERTILITY)){
					errorMessage = ValidatorConstants.ERROR_MSG_EB03_WINFERTILITY;
				}
			}
			return errorMessage;
		}
}
