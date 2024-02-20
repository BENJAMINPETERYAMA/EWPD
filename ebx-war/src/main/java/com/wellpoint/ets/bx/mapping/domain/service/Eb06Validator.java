/*
 * <Eb06Validator.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

/**
 * @author u23641
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Eb06Validator extends Validator implements HippaSegmentValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */

	public List validate(Mapping mapping) throws DomainException {
		if (null == mapping) {
			throw new DomainException("Mapping  object expected.");
		}
		short status = 0;
		List<HippaSegmentValidationResult> hippaSegmentValidationResultList = new ArrayList<HippaSegmentValidationResult>();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		ValidationUtility validationUtility = new ValidationUtility();
		
		/* May Release Code modification -Begins */

		String eb06Value = "";
		eb06Value = mapping.getEB06Value();
		String eb01Value = "";
		eb01Value = mapping.getEB01Value();
		String hsd05Value = "";
		hsd05Value = mapping.getHsd05Value();
		String hsd01Value = "";
		hsd01Value = mapping.getHsd01Value();
		String eb09Value = "";
		eb09Value = mapping.getEB09Value();
		
		List spsFormats = new ArrayList();
		List hippaCodePossibleValues = null;
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
		if (null != mapping.getVariable()
				&& null != mapping.getVariable().getVariableFormat()) {
			variableFormat = mapping.getVariable().getVariableFormat().trim()
					.toUpperCase();
		}
		
		/**
		 * Code logic is changed as part of BXNI December release
		 * The screen side validation for E020 will be reported if the variable/sps is mapped to 
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
		
		
		
		if(!(DomainConstants.EMPTY).equalsIgnoreCase(eb09Value)){
			
			if ((((eb06Value.equalsIgnoreCase(DomainConstants.EB06_7)) || (eb06Value
					.equalsIgnoreCase(DomainConstants.EB06_13))) && (eb09Value
					.equalsIgnoreCase(DomainConstants.DY)))
					|| ((eb06Value
							.equalsIgnoreCase(DomainConstants.EB06_6)) && (eb09Value
							.equalsIgnoreCase(DomainConstants.HS)))
					|| ((eb06Value
							.equalsIgnoreCase(DomainConstants.EB06_34)) && (eb09Value
							.equalsIgnoreCase(DomainConstants.MN)))
					|| ((eb06Value
							.equalsIgnoreCase(DomainConstants.EB06_27)) && (eb09Value
							.equalsIgnoreCase(DomainConstants.VS)))
					|| (((eb06Value
							.equalsIgnoreCase(DomainConstants.EB06_21))
							|| (eb06Value
									.equalsIgnoreCase(DomainConstants.EB06_22))
							|| (eb06Value
									.equalsIgnoreCase(DomainConstants.EB06_23)) || (eb06Value
								.equalsIgnoreCase(DomainConstants.EMPTY))) && (eb09Value
							.equalsIgnoreCase(DomainConstants.YY)))) {
		
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB09_EB06_E020,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
		
			}
		}
		/*// variable/sps parameter is mapped to format DAY or DAYS and EB06=7 or 13
		if (((null != variableFormat) && (ValidatorConstants.FORMAT_DAY
				.equalsIgnoreCase(variableFormat)
				||ValidatorConstants.FORMAT_DAYS.equalsIgnoreCase(variableFormat)))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) 
						&& (spsFormats.contains(ValidatorConstants.FORMAT_DAY)
								||spsFormats.contains(ValidatorConstants.FORMAT_DAYS)))) {
			if ((eb06Value.equals(DomainConstants.EB06_7) || (eb06Value
					.equals(DomainConstants.EB06_13)))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(
						mapping,
						new HippaSegmentValidationResult(),
						status,
						ValidatorConstants.WARNING_MESSAGE_EB06_7or13_DAYorDAYS,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// variable/sps parameter is mapped to format HRS and EB06=6
		if (((null != variableFormat) && ValidatorConstants.FORMAT_HRS
				.equalsIgnoreCase(variableFormat))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_HRS)))) {
			if (eb06Value.equals(DomainConstants.EB06_6)) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB06_6_HRS,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// variable/sps parameter is mapped to format MTHS or MTH and EB06=34
		if (((null != variableFormat) && (ValidatorConstants.FORMAT_MTH
				.equalsIgnoreCase(variableFormat)
				||ValidatorConstants.FORMAT_MTHS
				.equalsIgnoreCase(variableFormat)))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_MTH)
						||spsFormats
						.contains(ValidatorConstants.FORMAT_MTHS)))) {
			if (eb06Value.equals(DomainConstants.EB06_34)) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB06_34_MTHSorMTH,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// variable/sps parameter is mapped to format VST and EB06=27
		if (((null != variableFormat) && ValidatorConstants.FORMAT_VISIT
				.equalsIgnoreCase(variableFormat))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_VISIT)))) {
			if (eb06Value.equals(DomainConstants.EB06_27)) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB06_27_VST,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		// variable/sps parameter is mapped to format OCRS or OCC and EB06=27
		if (((null != variableFormat) && ValidatorConstants.FORMAT_OCCURENCE
				.equalsIgnoreCase(variableFormat)||ValidatorConstants.FORMAT_OCRS
				.equalsIgnoreCase(variableFormat))
				|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_OCCURENCE))
						||spsFormats
						.contains(ValidatorConstants.FORMAT_OCRS))) {
			if (eb06Value.equals(DomainConstants.EB06_27)) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB06_27_OCRSorOCC,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}
		}
		*//**
		 * variable is mapped to format YRS and EB06=21,22,23 or blank
		 *//*
		if (eb06Value == null) {
			eb06Value = "";
		}
		if (((null != variableFormat) && ValidatorConstants.FORMAT_YEARS
				.equalsIgnoreCase(variableFormat))) {
			if ((eb06Value.equals(DomainConstants.EB06_21)
					|| (eb06Value.equals(DomainConstants.EB06_22))
					|| (eb06Value.equals(DomainConstants.EB06_23)) || (eb06Value
					.equals(DomainConstants.EMPTY)))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_EB06_YRS,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}

		}
		*//**
		 * sps parameter is mapped to format YRS and EB06=21,22,23
		 *//*
		if (((null != spsFormats) && (!spsFormats.isEmpty()) && (spsFormats
						.contains(ValidatorConstants.FORMAT_YEARS)))) {
			if ((eb06Value.equals(DomainConstants.EB06_21)
					|| (eb06Value.equals(DomainConstants.EB06_22))
					|| (eb06Value.equals(DomainConstants.EB06_23)))) {
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						new HippaSegmentValidationResult(), status,
						ValidatorConstants.WARNING_MESSAGE_eWPD_EB06_YRS,
						new String[] {});
				hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);
			}

		}*/
		
		// variable/sps parameter is mapped EB06=21,22,23,32 and EB01=B
		if (eb06Value != null
				&& null != eb01Value && null!= eb01Value.trim() && !eb01Value.trim().equals("")) {
			eb01Value = eb01Value.trim();
			eb06Value = eb06Value.trim();

			if (ValidatorConstants.CO_PAYMENT
					.equalsIgnoreCase(eb01Value)) {
				if ((eb06Value.equals(DomainConstants.EB06_21)
						|| (eb06Value.equals(DomainConstants.EB06_22))
						|| (eb06Value.equals(DomainConstants.EB06_23)) || (eb06Value
						.equals(DomainConstants.EB06_32)))) {
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							new HippaSegmentValidationResult(), status,
							ValidatorConstants.WARNING_MESSAGE_EB06_EB01,
							new String[] {});
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
				}
			}
		}

		// for EB01=co-payment,EB06=blank for all formats other than YRS
		/*SPS format condition is commented in the below validation as per the change in May release req T1.2.1.7*/

		if ((( null!= mapping.getVariable() && null != variableFormat) && !ValidatorConstants.FORMAT_YEARS
				.equalsIgnoreCase(variableFormat))
				/*|| ((null != spsFormats) && (!spsFormats.isEmpty()) && (!spsFormats
						.contains(ValidatorConstants.FORMAT_YEARS)))*/) {
			if ((eb01Value.equals(ValidatorConstants.CO_PAYMENT))) {
				if (eb06Value.equals(DomainConstants.EMPTY)) {
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							new HippaSegmentValidationResult(), status,
							ValidatorConstants.WARNING_MESSAGE_EB06_EB01,
							new String[] {});
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
				}
			}
		}
		//BXNI CR18 Option 3 Req#3 -- Change the warning message logic for specified formats.
		//HSD01 is empty, HSD05 is empty, for the variable/sps formats VST,HRS,DAYS,MTH,MTHS,OCRS,YRS

		//boolean hsd05WarningExists = false;
		
		boolean isEB06ValidationReq = true;
		
			List<String> formatsForEb06 = getFormatForEB06();
			String spsFormatFromList = "";
			//variables of mentioned format
			// if variable format in VST,HRS,DAYS,MTH,MTHS,OCRS,YRS and hsd05Value is empty and hsd01 is not empty.
			if(!StringUtils.isBlank(hsd01Value)){
				if (null != mapping.getVariable()) {
					if(formatsForEb06.contains(variableFormat)){
						isEB06ValidationReq = false;
						if (StringUtils.isBlank(hsd05Value)) {
							hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
									new HippaSegmentValidationResult(), status,
									ValidatorConstants.HSD05_WARNING_MESSAGE_VARIABLE,
									new String[] {});
							hippaSegmentValidationResultList
									.add(hippaSegmentValidationResult);						
						}
					}
				}else if(null != mapping.getSpsId()){
					boolean spsFormatFlag = false;
					if (null != spsFormats && !spsFormats.isEmpty()) {
						Iterator<String> spsFormatIterator = spsFormats.iterator();
						while (spsFormatIterator.hasNext()) {
							spsFormatFromList  =  (String) spsFormatIterator.next();
							spsFormatFromList = spsFormatFromList.trim().toUpperCase();
							if(formatsForEb06.contains(spsFormatFromList)){
								spsFormatFlag = true;
								break;
							}
							
						}
						if(spsFormatFlag){
							isEB06ValidationReq = false;
							if (StringUtils.isBlank(hsd05Value)) {
								hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), status,
										ValidatorConstants.HSD05_WARNING_MESSAGE_SPS,
										new String[] {});
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);
								//hsd05WarningExists = true;
								
							}
						}
					}
				}
			}
			
			
		// if eb01=deductible,out of pocket,limitations or decuctible weived and
		// eb06=blank.
			if(isEB06ValidationReq){
			if (ValidatorConstants.DEDUCTIBLE
					.equalsIgnoreCase(eb01Value)
					|| ValidatorConstants.LIMITATION
							.equalsIgnoreCase(eb01Value)
					|| ValidatorConstants.OUT_OF_POCKET
							.equalsIgnoreCase(eb01Value)
					|| ValidatorConstants.DEDUCTIBLE_WAIVED
							.equalsIgnoreCase(eb01Value)) {
				if (eb06Value.equals(DomainConstants.EMPTY)) {
					if(null != mapping.getVariable()){
						hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
								new HippaSegmentValidationResult(), status,
								ValidatorConstants.EB06_WARNING_MESSAGE_VAR,
								new String[] {});
						hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
					}else if(null != mapping.getSpsId()){
						hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
								new HippaSegmentValidationResult(), status,
								ValidatorConstants.EB06_WARNING_MESSAGE_SPS,
								new String[] {});
						hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
					}
				}
			}
		}
		
		//APRIL RELEASE: Validating for Rule 30
		validateForRule30(mapping,hippaSegmentValidationResultList);
	
		
		hippaCodePossibleValues = mapping.getEb06().getHippaCodePossibleValues();
		List hippaSegmentValidationList = validate(mapping.getEb06().getName(), hippaCodePossibleValues, mapping.getEb06().getHippaCodeSelectedValues(), mapping);
		if(hippaSegmentValidationList!= null && !hippaSegmentValidationList.isEmpty()){
	        for(Iterator<HippaSegmentValidationResult> itr = hippaSegmentValidationList.iterator();itr.hasNext();){
	            HippaSegmentValidationResult result = (HippaSegmentValidationResult)itr.next();
	            hippaSegmentValidationResultList.add(result); 
	        }
	    }
		validateEB06ToRestrictValue(mapping,hippaSegmentValidationResultList);
		validateEb06ForHSD01BasedOnEB01(mapping,hippaSegmentValidationResultList,spsFormats);
		validateEb06ForHSD01BasedOnFormat(mapping,hippaSegmentValidationResultList,spsFormats);
		return hippaSegmentValidationResultList;
	}

	/**
	 * Validation for Rule 30
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	private void validateForRule30(Mapping mapping,	List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {

		String eb06Value = "";
		String variableFormat = "";
		String variableDescription = "";
		
		if (null != mapping) {

			if(null != mapping.getEB06Value()){
				eb06Value = mapping.getEB06Value();
			}
			else{
				eb06Value = "";
			}

			if (null != mapping.getVariable()
					&& null != mapping.getVariable().getDescription()) {
				variableDescription = mapping.getVariable().getDescription()
						.trim().toUpperCase();
			}

			if (null != mapping.getVariable() 
					&& null != mapping.getVariable().getVariableFormat()) {
				variableFormat = mapping.getVariable().getVariableFormat()
						.trim().toUpperCase();
			}
			
			List tokenList = Arrays.asList(variableDescription.split(" "));
			for (int count = 0; count < tokenList.size(); count++) {
				String currentToken = (String) tokenList.get(count);
				String nextToken = "";
				if (tokenList.size() > count+1) {
					nextToken = (String) tokenList.get(count+1);
				}
				// The contract variable description has ‘BY’ or ‘BEN YR’
				// and EB06 is NOT equal to 22/blank

				if ((((DomainConstants.BY).equals(currentToken)) 
						|| ((DomainConstants.BEN).equals(currentToken) && (DomainConstants.YR).equals(nextToken)))
						&& !((DomainConstants.EB06_22.equals(eb06Value)) 
								|| DomainConstants.EB06_BLANK
								.equals(eb06Value))) {
					displayWarning(
							mapping,
							ValidatorConstants.EB06_WARNING_MESSAGE_FOR_BENEFIT_YEAR,
							hippaSegmentValidationResultList);
				}

				// The contract variable description has ‘CY’ or ‘CAL YR’ 
				// and EB06 is NOT equal to 23/blank

				if (((DomainConstants.CY).equals(currentToken) || ((DomainConstants.CAL).equals(currentToken) 
						&& (DomainConstants.YR).equals(nextToken)))
						&& !((DomainConstants.EB06_23.equals(eb06Value)) || DomainConstants.EB06_BLANK
								.equals(eb06Value))) {
					displayWarning(
							mapping,
							ValidatorConstants.EB06_WARNING_MESSAGE_FOR_CALENDAR_YEAR,
							hippaSegmentValidationResultList);
				}

				// The contract variable description has ‘DAY’ or ‘DY’ or DAYS or DYS
				// and format is NOT DAYS and EB06 is NOT equal to 7 or 13

				if ((DomainConstants.DAY).equals(currentToken)
						|| (DomainConstants.DAY_DY).equals(currentToken)
						|| (DomainConstants.DAY_DAYS).equals(currentToken)
						|| (DomainConstants.DAY_DYS).equals(currentToken)) {
					if (!((DomainConstants.DAY).equals(variableFormat)||(DomainConstants.LEN).equals(variableFormat))) {
						if (!(DomainConstants.EB06_13.equals(eb06Value))
								&& !(DomainConstants.EB06_7.equals(eb06Value))) {
								displayWarning(
										mapping,
										ValidatorConstants.EB06_WARNING_MESSAGE_FOR_DAYS,
										hippaSegmentValidationResultList);
						}
					}
				}

				// The contract variable description has ‘HRS’ or ‘HOUR’ or ‘HR’ or ‘HOURS’
				// and format is HRS and EB06 is NOT equal to 6

				if ((DomainConstants.HRS).equals(currentToken)
						|| (DomainConstants.HOUR_HR).equals(currentToken)
						|| (DomainConstants.HOUR).equals(currentToken)
						|| (DomainConstants.HOURS).equals(currentToken)) {
					if (!(DomainConstants.HRS).equals(variableFormat)) {
						if (!(DomainConstants.EB06_6.equals(eb06Value))) {
							displayWarning(
									mapping,
									ValidatorConstants.EB06_WARNING_MESSAGE_FOR_HOURS,
									hippaSegmentValidationResultList);
						}
					}
				}

				// The contract variable description has ‘MTHS’ or 'MTHS' or ‘MONTHS’ or ‘MONTH’ 
				// and format is MTHS and EB06 is NOT equal to 34

				if ((DomainConstants.MTH).equals(currentToken)
						|| (DomainConstants.MTHS).equals(currentToken)
						|| (DomainConstants.MONTHS).equals(currentToken)
						|| (DomainConstants.MONTH).equals(currentToken)) {
					if (!(DomainConstants.MTH).equals(variableFormat)) {
						if (!(DomainConstants.EB06_34.equals(eb06Value))) {
							displayWarning(
									mapping,
									ValidatorConstants.EB06_WARNING_MESSAGE_FOR_MONTHS,
									hippaSegmentValidationResultList);
						}
					}
				}

				// The contract variable description has ‘VISIT’ or ‘VST’
				// and format is not VST or OCRS and EB06 is NOT equal to 27

				if ((DomainConstants.VST).equals(currentToken)
						|| (DomainConstants.VISIT).equals(currentToken)
						|| (DomainConstants.VISITS).equals(currentToken)) {
					if (!(DomainConstants.VST).equals(variableFormat)
							&& !(DomainConstants.OCC).equals(variableFormat)) {
						if (!(DomainConstants.EB06_27.equals(eb06Value))) {
							displayWarning(
									mapping,
									ValidatorConstants.EB06_WARNING_MESSAGE_FOR_VISIT,
									hippaSegmentValidationResultList);
						}
					}
				}

				// The contract variable description has ‘LT’ or ‘LIFETIME’
				// or ‘LIFE’ or ‘LFT’ or ‘LFTMAX’ or ‘LIFET’ or ‘LIF’ or ‘LFTM’ or  ‘LTM’
				// and EB06 is NOT equal to 32

				if ((DomainConstants.LT).equals(currentToken)
						|| (DomainConstants.LIFETIME).equals(currentToken)
						|| (DomainConstants.LIFE).equals(currentToken)
						|| (DomainConstants.LFT).equals(currentToken)
						|| (DomainConstants.LFTMAX).equals(currentToken)
						|| (DomainConstants.LIFET).equals(currentToken)
						|| (DomainConstants.LIF).equals(currentToken)
						|| (DomainConstants.LFTM).equals(currentToken)
						|| (DomainConstants.LTM).equals(currentToken)
						|| (DomainConstants.LIFETME).equals(currentToken)) {
					if (!(DomainConstants.EB06_32.equals(eb06Value))) {
						displayWarning(
								mapping,
								ValidatorConstants.EB06_WARNING_MESSAGE_FOR_LIFETIME,
								hippaSegmentValidationResultList);
					}
				}

				// The contract variable description has ‘WEEK’ or ‘WK’ or 'WKS'
				// and EB06 is NOT equal to 35

				if ((DomainConstants.WEEK).equals(currentToken)
						|| (DomainConstants.WEEK_WK).equals(currentToken)
						|| (DomainConstants.WEEK_WKS).equals(currentToken)) {
					if (!(DomainConstants.EB06_35.equals(eb06Value))) {
						displayWarning(
								mapping,
								ValidatorConstants.EB06_WARNING_MESSAGE_FOR_WEEK,
								hippaSegmentValidationResultList);
					}
				}

				// The contract variable description has ‘ADMIT’ or ‘ADMISSION’ or ‘ADM’ or ‘ADMT’ 
				//and EB06 is NOT equal to 36

				if (((DomainConstants.ADMIT).equals(currentToken)
						|| (DomainConstants.ADMISSION).equals(currentToken)
						|| (DomainConstants.ADM).equals(currentToken) 
						|| (DomainConstants.ADMT)
						.equals(currentToken))) {
					if (!(DomainConstants.EB06_36.equals(eb06Value))) {
						displayWarning(
								mapping,
								ValidatorConstants.EB06_WARNING_MESSAGE_FOR_ADMIT,
								hippaSegmentValidationResultList);
					}
				}
			}
		}
	}
	/**
	 * Method to display the warning message for validations.
	 * @param mapping
	 * @param warningMessage
	 * @param hippaSegmentValidationResultList
	 */
	private void displayWarning(Mapping mapping, String warningMessage,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		short status = 2;
		String[] placeHoldValues = {};
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
				hippaSegmentValidationResult, status, warningMessage,
				placeHoldValues);
		hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
	}
	/**
	 * The method restricts the below values to be mapped to EB06 for a variable or sps id
	 * EB06 = 29, 33 cannot be mapped 
	 * BXNI November 2012Release 
	 * @param mapping
	 * @param hippaSegmentValidationResultList
	 */
	private void validateEB06ToRestrictValue(Mapping mapping,List<HippaSegmentValidationResult> hippaSegmentValidationResultList){
		List<String> restrictedEB06 = getRestrictedEB06();
		String eb06ValueFromPage = mapping.getEB06Value();
		HippaSegmentValidationResult hippaSegmentValidationResult =  new HippaSegmentValidationResult();
		
		if(restrictedEB06.contains(eb06ValueFromPage)){
			//Error
			    short status = 0;
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
						hippaSegmentValidationResult, status,
						ValidatorConstants.ERROR_MSG_RESTRICTED_EB06,
						new String[] { DomainConstants.EMPTY });
				hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		}
	}
	protected List<String> getRestrictedEB06() {
		List<String> restrictedEB06 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.RESTRICTED_EB06,
				DomainConstants.PROPERTY_FILE_NAME);

		return restrictedEB06;
	}
	

	private void validateEb06ForHSD01BasedOnFormat(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList, List<String> spsFormats) {
		String spsFormatFromList = DomainConstants.EMPTY;
		List<String> formatsForEb06 = getFormatForEB06();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		short status = 0;

		if (null != mapping && null != mapping.getVariable()
				&& null != mapping.getVariable().getVariableFormat()) {
			if (null != mapping.getHsd01() && null != mapping.getHsd01Value() && !mapping.getHsd01Value().equals("")) { // to check the hsd01 value
				if (null != mapping.getEB06Value() && !mapping.getEB06Value().equalsIgnoreCase("")) {
					String formatValue = mapping.getVariable().getVariableFormat();

					if (formatsForEb06.contains(formatValue)) {
						hippaSegmentValidationResult = setHippaSegVldnRslt(
								mapping,
								hippaSegmentValidationResult,
								status,
								ValidatorConstants.ERROR_MESSAGE_TO_MAP_EB06_TO_HSD05_BASED_ON_HSD01,
								new String[] {});
						hippaSegmentValidationResultList
						.add(hippaSegmentValidationResult);

					}
				}
			}
		} else if (null != mapping && null != mapping.getSpsId()) {
			if (null != spsFormats && !spsFormats.isEmpty()) {
				Iterator<String> spsFormatIterator = spsFormats.iterator();
				while (spsFormatIterator.hasNext()) {
					spsFormatFromList = (String) spsFormatIterator.next();
					spsFormatFromList = spsFormatFromList.trim().toUpperCase();
					if (formatsForEb06.contains(spsFormatFromList)) {
						if (null != mapping.getHsd01()
								&& null != mapping.getHsd01Value()
								&& !mapping.getHsd01Value()
										.equalsIgnoreCase(DomainConstants.EMPTY)) { // to check the
																	// hsd01
																	// value
							if (null != mapping.getEb06()
									&& null != mapping.getEB06Value()
									&& !mapping.getEB06Value()
											.equalsIgnoreCase(DomainConstants.EMPTY)) {

								hippaSegmentValidationResult = setHippaSegVldnRslt(
										mapping,
										hippaSegmentValidationResult,
										status,
										ValidatorConstants.ERROR_MESSAGE_TO_MAP_EB06_TO_HSD05_BASED_ON_HSD01,
										new String[] {});
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
	
	private void validateEb06ForHSD01BasedOnEB01(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList,List<String> spsFormats) {
		String eb01Value = "";
		if ((null != mapping && null != mapping.getVariable()) || (null != spsFormats) && (!spsFormats.isEmpty()) ) {
			if (null != mapping.getEB01Value()
					&& !mapping.getEB01Value().equalsIgnoreCase(
							DomainConstants.EMPTY)) {
				eb01Value = mapping.getEB01Value();
			if(eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)&&
					 (null != mapping.getEB09Value()&& !mapping.getEB09Value().equalsIgnoreCase("")))	
			     {
				if ((null != mapping.getEB06Value() && !mapping.getEB06Value().equalsIgnoreCase(DomainConstants.EMPTY)) &&
						(null != mapping.getHsd05Value() && !mapping.getHsd05Value().equalsIgnoreCase(""))) {
					displayWarning(
							mapping,
							ValidatorConstants.EB06_WARNING_MESSAGE_FOR_EB01,
							hippaSegmentValidationResultList);
				}
			}
		}
	}
}
	
 	protected List<String> getFormatForEB06(){
	 List<String> formatsForEB06 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.FORMAT_VALUES_FOR_EB06_HSD01_VALIDATION,
				DomainConstants.PROPERTY_FILE_NAME);

		return formatsForEB06;
}
 	}
	

