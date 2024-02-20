/*
 * Created on May 21, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccumsValidator extends Validator implements HippaSegmentValidator {

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public List validate(Mapping mapping)throws DomainException {
		
		if(null == mapping){
		    throw new DomainException("Mapping  object expected.");
		}
	    List hippaSegmentValidationResultList = new ArrayList();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		List hippaCodeSelectedValues = new ArrayList();
	
		ValidationUtility validationUtility = new ValidationUtility();
		//boolean accumNotRequired = false;
			String selectedEB01CodeValue = "";
			String selectedEB06CodeValue = "";
		//	List selectedEB03Codevalues = new ArrayList();
			
			short status = 2;
			selectedEB01CodeValue = mapping.getEB01Value();
			if(selectedEB01CodeValue == null){
			    selectedEB01CodeValue = "";
			}
	
			selectedEB06CodeValue = mapping.getEB06Value();
			if(selectedEB06CodeValue == null){
			    selectedEB06CodeValue = "";
			}
			
			selectedEB01CodeValue = selectedEB01CodeValue.trim();
			selectedEB06CodeValue = selectedEB06CodeValue.trim();			

			hippaCodeSelectedValues = mapping.getAccumValues();
			hippaCodeSelectedValues = validationUtility.removeBlankfromList(hippaCodeSelectedValues);
			if(hippaCodeSelectedValues == null || hippaCodeSelectedValues.size() == 0){
			    hippaCodeSelectedValues = new ArrayList();
			}
			// for variable validate the sensitive and eb01 , to autopopulate sensitive benefit
			// indicator
			if(null != mapping.getVariable()){
				List selectedEB03Codevalues = mapping.getHippaSegmentSelectedValues(mapping.getEb03());
				selectedEB03Codevalues = validationUtility.removeBlankfromList(selectedEB03Codevalues);
				if(selectedEB03Codevalues == null || selectedEB03Codevalues.size() == 0){
				    selectedEB03Codevalues = new ArrayList();
				}	
				
				if ( mapping.getVariable().getVariableFormat()
						.equalsIgnoreCase(ValidatorConstants.VAL) && hippaCodeSelectedValues.isEmpty()
						){
						status = 0;
						hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
					            ValidatorConstants.ACCUM_NOT_MAPPED_VAR_VAL,new String[]{" "});
					    hippaSegmentValidationResultList.add(hippaSegmentValidationResult); 
					    return hippaSegmentValidationResultList;
					}
				
				if(hippaCodeSelectedValues.size()>10){
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
				            ValidatorConstants.LIMIT_EXCEEDED,new String[]{DomainConstants.ACCUM_NAME});
				    hippaSegmentValidationResultList.add(hippaSegmentValidationResult); 
				}
				boolean isSensitivePresent = false;
				boolean isNonSensitivePresent = false;
			/*
			 * Auto populate accum <b>not</b> required indicator if there is sensitive benifit+ EB01 combination.
			 * If auto populated then by pass the EB06+EB01 validation of accum.
			 */
			if(selectedEB03Codevalues!=null){
				//Sensitive Benefits list is obtained from errorvalidator.properties property file 
				//BXNI June2012 Release
				List sensitiveBenefitsList = SimulationResourceBundle.getResourceBundle(
						DomainConstants.SENSITIVE_EB03,
						DomainConstants.PROPERTY_FILE_NAME);
				for(int i=0;i<sensitiveBenefitsList.size();i++){
					if(selectedEB03Codevalues.contains(sensitiveBenefitsList.get(i))){
						isSensitivePresent = true;
						break;
					}
				}
			    if(isSensitivePresent){
			    	for(int i=0;i<selectedEB03Codevalues.size();i++){
			    		if(!sensitiveBenefitsList.contains(selectedEB03Codevalues.get(i))){
			    			isNonSensitivePresent = true;
			    			break;
			    		}
			    	}
			    }
			}
			//Warning message is produced if EB03 contains a combination of Sensitive and Non Sensitive Benefits
			//Commented -- POR Wave 2 change to remove Warning of Sensitive Benefit Ind
			/*if(isSensitivePresent && isNonSensitivePresent){
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
			            ValidatorConstants.WARNING_MESSAGE_SENSITIVE_EB03_VARIABLE,new String[]{DomainConstants.ACCUM_NAME});
			    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
			}*/
			/**
			 * Removed the existing warning message in which warning is produced if there is a mismatch between Eb03value and indicator
			 */
/*				if(isSensitiveBenefit){
						if(("N").equalsIgnoreCase(mapping.getSensitiveBenefitIndicator().trim())){						   
						    //accumNotRequired = true;
							hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
						            ValidatorConstants.ACCUM_NOT_REQD_INDICATOR_WARNING,new String[]{DomainConstants.ACCUM_NAME});
						    hippaSegmentValidationResultList.add(hippaSegmentValidationResult); 
							
						}
			} */
				
/*				if(mapping.getSensitiveBenefitIndicator().equalsIgnoreCase("Y")){
				    accumNotRequired = true;
				}
*/			}// end of autopopulate	
			//by pass this validation if accum is not required.			
			/*if(!accumNotRequired){
				if(		(	ValidatorConstants.DEDUCTIBLE.equalsIgnoreCase(selectedEB01CodeValue) ||
				        ValidatorConstants.OUT_OF_POCKET.equalsIgnoreCase(selectedEB01CodeValue)|| 
				        ValidatorConstants.LIMITATION.equalsIgnoreCase(selectedEB01CodeValue)
			        )
			        &&(	ValidatorConstants.SERVICE_YEAR.equalsIgnoreCase(selectedEB06CodeValue) ||
			            ValidatorConstants.CALENDAR_YEAR.equalsIgnoreCase(selectedEB06CodeValue) ||
			            ValidatorConstants.CONTRACT.equalsIgnoreCase(selectedEB06CodeValue) ||
			            ValidatorConstants.LIFETIME.equalsIgnoreCase(selectedEB06CodeValue) ||
			            ValidatorConstants.LIFETIME_REMAINING.equalsIgnoreCase(selectedEB06CodeValue) ||
						("").equalsIgnoreCase(selectedEB06CodeValue)
					  )
					&& (null == hippaCodeSelectedValues || hippaCodeSelectedValues.isEmpty()))
					{
						status = 0;
						hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
					            ValidatorConstants.INVALID_ACCUM,new String[]{DomainConstants.ACCUM_NAME});
					    hippaSegmentValidationResultList.add(hippaSegmentValidationResult); 
					}
			}*/
		
		List hippaSegmentValidationList = validate(mapping.getAccum().getName(), mapping.getAccum().getHippaCodePossibleValues(), mapping.getAccum().getHippaCodeSelectedValues(), mapping);
		if(hippaSegmentValidationList!= null && !hippaSegmentValidationList.isEmpty()){
		   for(Iterator itr = hippaSegmentValidationList.iterator();itr.hasNext();){
		       HippaSegmentValidationResult result = (HippaSegmentValidationResult)itr.next();
		       hippaSegmentValidationResultList.add(result); 
		   }
		}
		validateEB01EB06BasedOnAccum(mapping,hippaSegmentValidationResultList);
		return hippaSegmentValidationResultList;
	}

	/*
	 * Accum must be mapped if: 
	 * 			mapping.getEB01Value() equals C or F or G  
				&&
				mapping.getEB06Value() equals 22 or 23 or 25 or 32 or Blank
				&&
				mapping.getSensitiveBenefitIndicator equals N
				
				OR 
				
				mapping.getEB01Value() equals F   
				&&
				mapping.getHsd01Value() not equals to Blank
				&&
				mapping.getSensitiveBenefitIndicator equals N
				&&
				mapping.getHsd05Value() equals 22 (BY) or 23 (CY) or 25 or 32 (LT) or blank
				
				OR 
				
				mapping.getEB01Value() equals B or A   
				&&
				mapping.getHsd01Value() not equals to Blank
				&&
				mapping.getSensitiveBenefitIndicator equals N
				&&
				mapping.getHsd05Value() equals 22 (BY) or 23 (CY) or 25 or 32 (LT) or blank
	 */
	
	private void validateEB01EB06BasedOnAccum(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		short status = 2;
		String warnMessage = "";
		String eb01Value = "";
		String accumNotReqdInd = "";
		String eb06Value = "";
		String hsd05Value = "";
		boolean hsd01ValueExists = false;
		boolean accumValidFlag = true;
		List<String> accumList = new ArrayList<String>();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		if (null != mapping && null != mapping.getVariable()) {
			if (null != mapping.getEB01Value()
					&& !mapping.getEB01Value().equalsIgnoreCase(
							DomainConstants.EMPTY)) {
				eb01Value = mapping.getEB01Value();
			}
			if (null != mapping.getSensitiveBenefitIndicator()
					&& !mapping.getSensitiveBenefitIndicator()
							.equalsIgnoreCase(DomainConstants.EMPTY)) {
				accumNotReqdInd = mapping.getSensitiveBenefitIndicator();
			}
			if (null != mapping.getAccum()
					&& null != mapping.getAccumValues()
					&& !mapping.getAccumValues().isEmpty()
					&& null != mapping.getAccumValues().get(0)
					&& !((mapping.getAccumValues().get(0).toString().trim())
							.equals(DomainConstants.EMPTY))) {
				accumList = mapping.getAccumValues();
			}
			if (null != mapping.getHsd01Value()
					&& !mapping.getHsd01Value().equalsIgnoreCase(
							DomainConstants.EMPTY)) {
				hsd01ValueExists = true;
			}
			if (null != mapping.getHsd05Value()
					&& !mapping.getHsd05Value().equalsIgnoreCase(
							DomainConstants.EMPTY)) {
				hsd05Value = mapping.getHsd05Value();
			}
			if (null != mapping.getEB06Value()
					&& !mapping.getEB06Value().equalsIgnoreCase(
							DomainConstants.EMPTY)) {
				eb06Value = mapping.getEB06Value();
			}
			/*
			 * mapping.getEB01Value() equals C or F or G 
			 * &&
			 * mapping.getEB06Value() equals 22 or 23 or 25 or 32 or Blank 
			 * &&
			 * mapping.getSensitiveBenefitIndicator equals N
			 */
			if ((eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)
					|| eb01Value
							.equalsIgnoreCase(DomainConstants.OUT_OF_POCKET) || eb01Value
					.equalsIgnoreCase(DomainConstants.DEDUCTIBLE))
					&& (eb06Value.equalsIgnoreCase(DomainConstants.EMPTY)
							|| eb06Value
									.equalsIgnoreCase(DomainConstants.EB06_22)
							|| eb06Value
									.equalsIgnoreCase(DomainConstants.EB06_23)
							|| eb06Value
									.equalsIgnoreCase(DomainConstants.EB06_25) || eb06Value
							.equalsIgnoreCase(DomainConstants.EB06_32))
					&& (accumNotReqdInd.equalsIgnoreCase(DomainConstants.N))) {
				if (null == accumList
						|| accumList.isEmpty()
						|| null == accumList.get(0)
						|| accumList.get(0).trim().equalsIgnoreCase(
								DomainConstants.EMPTY)) {

					
					warnMessage = ValidatorConstants.ACCUMULATOR_EB01_CGF_VALIDATION;
					accumValidFlag = false;
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status, warnMessage,
							new String[] {});
				}

			}

			/*
			 * mapping.getEB01Value() equals F 
			 * && 
			 * mapping.getHsd01Value() notequals to Blank 
			 * && 
			 * mapping.getSensitiveBenefitIndicator equals N
			 * && 
			 * mapping.getHsd05Value() equals 22 (BY) or 23 (CY) or 25 or 32(LT) or blank
			 */

			if ((eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS))
					&& hsd01ValueExists
					&& (accumNotReqdInd.equalsIgnoreCase(DomainConstants.N))
					&& (hsd05Value.equalsIgnoreCase(DomainConstants.EMPTY)
							|| hsd05Value
									.equalsIgnoreCase(DomainConstants.HSD05_22)
							|| hsd05Value
									.equalsIgnoreCase(DomainConstants.HSD05_23)
							|| hsd05Value
									.equalsIgnoreCase(DomainConstants.HSD05_25) || hsd05Value
							.equalsIgnoreCase(DomainConstants.HSD05_32))) {
				if (null == accumList
						|| accumList.isEmpty()
						|| accumList.get(0) == null
						|| accumList.get(0).trim().equalsIgnoreCase(
								DomainConstants.EMPTY)) {
					
					warnMessage = ValidatorConstants.ACCUMULATOR_EB01_F_VALIDATION;
					accumValidFlag = false;
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status, warnMessage,
							new String[] {});
				}

			}

			/*
			 * /mapping.getEB01Value() equals B or A 
			 * && 
			 * mapping.getHsd01Value()not equals to Blank 
			 * && 
			 * mapping.getSensitiveBenefitIndicator equals N 
			 * && 
			 * mapping.getHsd05Value() equals 22 (BY) or 23 (CY) or 25 or 32 (LT) or blank
			 */

			if ((eb01Value.equalsIgnoreCase(DomainConstants.COINSURANCE) || eb01Value
					.equalsIgnoreCase(DomainConstants.COPAYMENT))
					&& hsd01ValueExists
					&& (accumNotReqdInd.equalsIgnoreCase(DomainConstants.N))
					&& (hsd05Value.equalsIgnoreCase(DomainConstants.EMPTY)
							|| hsd05Value
									.equalsIgnoreCase(DomainConstants.HSD05_22)
							|| hsd05Value
									.equalsIgnoreCase(DomainConstants.HSD05_23)
							|| hsd05Value
									.equalsIgnoreCase(DomainConstants.HSD05_25) || hsd05Value
							.equalsIgnoreCase(DomainConstants.HSD05_32))) {
				if (null == accumList
						|| accumList.isEmpty()
						|| accumList.get(0) == null
						|| accumList.get(0).trim().equalsIgnoreCase(
								DomainConstants.EMPTY)) {
					
					warnMessage = ValidatorConstants.ACCUMULATOR_EB01_AB_VALIDATION;
					accumValidFlag = false;
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							hippaSegmentValidationResult, status, warnMessage,
							new String[] {});

				}
			}

			if (!accumValidFlag) {
					hippaSegmentValidationResultList
							.add(hippaSegmentValidationResult);
				
			}
		}
	}
}
