/*
 * Created on May 18, 2010
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
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Eb09Validator extends Validator implements HippaSegmentValidator {

    /* (non-Javadoc)
     * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
     */
    public List validate(Mapping mapping)throws DomainException {
        if(null == mapping){
            throw new DomainException("Mapping  object expected.");
		}
        short status = 0;
        List<HippaSegmentValidationResult> hippaSegmentValidationResultList = new ArrayList<HippaSegmentValidationResult>();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String selectedEB09CodeValue = "";
		//boolean codeEB09 = false;
		List<String> spsFormats = new ArrayList<String>();			
		ValidationUtility validationUtility = new ValidationUtility();
		if(null != mapping.getSpsId() && null != mapping.getSpsId().getSpsDetail() && (!mapping.getSpsId().getSpsDetail().isEmpty())){				
			spsFormats = validationUtility.getSpsFormatsFromSPSDetail(mapping.getSpsId().getSpsDetail());	
		}		
		String variableFormat = "";
		if(null != mapping.getVariable() && !StringUtils.isBlank(mapping.getVariable().getVariableFormat())){
			variableFormat = mapping.getVariable().getVariableFormat().trim().toUpperCase();
		}
		selectedEB09CodeValue = mapping.getEB09Value();
		if(selectedEB09CodeValue == null){
		    selectedEB09CodeValue = "";
		}
		selectedEB09CodeValue = selectedEB09CodeValue.trim();
		
		//BXNI CR26 change, validation removed so as EB09 cannot be mapped to AGE format
		/*	selectedEB09CodeValue = mapping.getEB09Value();
		if(selectedEB09CodeValue == null){
		    selectedEB09CodeValue = "";
		}
		selectedEB09CodeValue = selectedEB09CodeValue.trim();
		String variableFormat = "";
		// for variable if the variable format is AGE , EB09 not coded, error message
	/*	if(null != mapping.getVariable() && null != mapping.getVariable().getVariableFormat()){
		
			variableFormat = mapping.getVariable().getVariableFormat().trim().toUpperCase();
		
			if(ValidatorConstants.VARIABLE_FORMAT_AGE.equalsIgnoreCase(variableFormat)){
			    if((selectedEB09CodeValue.equals(""))){
				    hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, new HippaSegmentValidationResult(), status, 
				            ValidatorConstants.EB09_NOT_CODED_FOR_AGE , new String[]{DomainConstants.EB09_NAME});
				    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
				    return hippaSegmentValidationResultList;
				}			  
			}
			else{
				 if(!selectedEB09CodeValue.equals("")){					 
					 codeEB09 = true;					
				}				
			}
		}
		if(null != mapping.getSpsId()){
					
			if(null != mapping.getSpsId().getSpsDetail() && (!mapping.getSpsId().getSpsDetail().isEmpty())){				
				spsFormats = validationUtility.getSpsFormatsFromSPSDetail(mapping.getSpsId().getSpsDetail());	
			}			
			String selectedEB01CodeValue = "";
			selectedEB01CodeValue = mapping.getEB01Value();
			if(selectedEB01CodeValue == null){
			    selectedEB01CodeValue = "";
			}
			selectedEB01CodeValue = selectedEB01CodeValue.trim();
			// for sps if sps format = AGE and EB01 = F and EB09 not coded, error message
			if((null != spsFormats) && (!spsFormats.isEmpty()) && 
					(spsFormats.contains(ValidatorConstants.VARIABLE_FORMAT_AGE)) 
					&& (ValidatorConstants.LIMITATION.equalsIgnoreCase(selectedEB01CodeValue))){
				
				if((selectedEB09CodeValue.equals(""))){
				    hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, new HippaSegmentValidationResult(), status, 
				            ValidatorConstants.ERROR_MESSAGE_EB09_SPS
				            , new String[]{DomainConstants.EB09_NAME});
				    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
				    return hippaSegmentValidationResultList;
				}
			}
			else {
				if(!selectedEB09CodeValue.equals("")){				
					 codeEB09 = true;	
				}			
			}
		}*/
		//Code changed a part of BXNI more Variable/SPS formats added to format AGE to code EB09
		//BXNI CR 26 change..EB09 cannot be mapped to AGE format
		
		 if(!StringUtils.isBlank(selectedEB09CodeValue)){
			 boolean doesNotHaveEB09Error = false;
			 String errorCode = "";
			 List formatsForEb09 = getFormatForEB09();
			 if(null != mapping.getVariable() && (!formatsForEb09.contains(variableFormat)				 
				 && !variableFormat.equalsIgnoreCase(DomainConstants.YR) && !variableFormat.equalsIgnoreCase(DomainConstants.YRS))){
				 
				 hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, new HippaSegmentValidationResult(), status, 
						 ValidatorConstants.EB09_INVALID, new String[]{DomainConstants.EB09_NAME});
				    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
			 }
			 else{
				 if(null != spsFormats && !spsFormats.isEmpty()) {
				 Iterator spsFormatIterator = spsFormats.iterator();
				 while(spsFormatIterator.hasNext()){
					 String spsFormat = (String)spsFormatIterator.next();
					 if(formatsForEb09.contains(spsFormat)
						 || spsFormat.equalsIgnoreCase(DomainConstants.YR) || spsFormat.equalsIgnoreCase(DomainConstants.YRS)){
						 doesNotHaveEB09Error = true;
						 break;
					 }
				 }
					 if(!doesNotHaveEB09Error){
						 hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, new HippaSegmentValidationResult(), status, 
								 ValidatorConstants.ERROR_MESSAGE_EB09_INVALID_SPS, new String[]{DomainConstants.EB09_NAME});
						    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
					 }
				 
				
			 }
			 }
		}
		List<HippaSegmentValidationResult> hippaSegmentValidationList = validate(mapping.getEb09().getName(), mapping.getEb09().getHippaCodePossibleValues(), mapping.getEb09().getHippaCodeSelectedValues(), mapping);
		  if(hippaSegmentValidationList!= null && !hippaSegmentValidationList.isEmpty()){
			   for(Iterator<HippaSegmentValidationResult> itr = hippaSegmentValidationList.iterator();itr.hasNext();){
			       HippaSegmentValidationResult result = (HippaSegmentValidationResult)itr.next();
			       hippaSegmentValidationResultList.add(result); 
			   }
		  }	
		  

		validateEb09Hsd01ForVariables(mapping, hippaSegmentValidationResultList);
		validateEb09Hsd01ForSPS(mapping, hippaSegmentValidationResultList);
		validateEb09AgeForVariable(mapping, hippaSegmentValidationResultList);
		return hippaSegmentValidationResultList;
	}

    
    //BXNI CR-18 CHANGES
    
	private void validateEb09Hsd01ForVariables(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		// BXNI EB09 Validation for Variable
		String variableFormat = DomainConstants.EMPTY;
		short statusForError = 0;
		short statusForWarning = 2;
		boolean warningExist = false;
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String selectedEB09CodeValue = DomainConstants.EMPTY;
		selectedEB09CodeValue = mapping.getEB09Value();
		String[] placeHoldValues = new String[2];

		if (selectedEB09CodeValue == null
				|| selectedEB09CodeValue.equalsIgnoreCase(DomainConstants.EMPTY)) {
			selectedEB09CodeValue = DomainConstants.EMPTY;
		}
		selectedEB09CodeValue = selectedEB09CodeValue.trim();

		if (null != mapping.getVariable()
				&& null != mapping.getVariable().getVariableFormat()
				&& !mapping.getVariable().getVariableFormat().equals(DomainConstants.EMPTY)) {

			variableFormat = mapping.getVariable().getVariableFormat().trim()
					.toUpperCase();
			warningExist = false;
			List formatsForEb09 = getFormatForEB09();
			if (null != mapping.getVariable() && null != variableFormat
					&& !variableFormat.equals(DomainConstants.EMPTY)) {

				if (formatsForEb09.contains(variableFormat)
						|| variableFormat.equalsIgnoreCase(DomainConstants.YR)
						|| variableFormat.equalsIgnoreCase(DomainConstants.YRS)) {

					if (((null == selectedEB09CodeValue || selectedEB09CodeValue
							.equalsIgnoreCase(DomainConstants.EMPTY)) && ((null != mapping
							.getHsd01Value() && !mapping.getHsd01Value()
							.equalsIgnoreCase(DomainConstants.EMPTY))))
							|| (((null != selectedEB09CodeValue && !selectedEB09CodeValue
									.equalsIgnoreCase(DomainConstants.EMPTY)) && ((null == mapping
									.getHsd01Value() || mapping.getHsd01Value()
									.equalsIgnoreCase(DomainConstants.EMPTY)))))) {

						if ((variableFormat
								.equalsIgnoreCase(DomainConstants.VST)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.VISIT) || variableFormat
								.equalsIgnoreCase(DomainConstants.VISITS))

								&& (((null == mapping.getHsd01Value() || mapping
										.getHsd01Value().equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
										|| selectedEB09CodeValue
												.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.VS))) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.EMPTY))
										&& null != mapping.getHsd01Value()
										&& !mapping.getHsd01Value()
												.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
										.getHsd01Value().equalsIgnoreCase(
												DomainConstants.VS)))) {
							placeHoldValues[0] = variableFormat;
							placeHoldValues[1] = DomainConstants.VS;
							warningExist = true;
						} else if ((variableFormat
								.equalsIgnoreCase(DomainConstants.HOUR_HR)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.HRS)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.HOUR) || variableFormat
								.equalsIgnoreCase(DomainConstants.HOURS))
								&& (((null == mapping.getHsd01Value() || mapping
										.getHsd01Value().equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
										|| selectedEB09CodeValue
												.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.HS))) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.EMPTY))
										&& null != mapping.getHsd01Value()
										&& !mapping.getHsd01Value()
												.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
										.getHsd01Value().equalsIgnoreCase(
												DomainConstants.HS)))) {
							placeHoldValues[0] = variableFormat;
							placeHoldValues[1] = DomainConstants.HS;
							warningExist = true;
						} else if ((variableFormat
								.equalsIgnoreCase(DomainConstants.DAY)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.DAYS)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.LEN)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.DAY_DYS) || variableFormat
								.equalsIgnoreCase(DomainConstants.DAY_DY))
								&& (((null == mapping.getHsd01Value() || mapping
										.getHsd01Value().equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
										|| selectedEB09CodeValue
												.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.DY))) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.EMPTY))
										&& null != mapping.getHsd01Value()
										&& !mapping.getHsd01Value()
												.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
										.getHsd01Value().equalsIgnoreCase(
												DomainConstants.DY)))) {
							placeHoldValues[0] = variableFormat;
							placeHoldValues[1] = DomainConstants.DY;
							warningExist = true;
						} else if ((variableFormat
								.equalsIgnoreCase(DomainConstants.MTH)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.MTHS)
								|| variableFormat
										.equalsIgnoreCase(DomainConstants.MONTH) || variableFormat
								.equalsIgnoreCase(DomainConstants.MONTHS))
								&& (((null == mapping.getHsd01Value() || mapping
										.getHsd01Value().equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
										|| selectedEB09CodeValue
												.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.MN))) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.EMPTY))
										&& null != mapping.getHsd01Value()
										&& !mapping.getHsd01Value()
												.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
										.getHsd01Value().equalsIgnoreCase(
												DomainConstants.MN)))) {
							placeHoldValues[0] = variableFormat;
							placeHoldValues[1] = DomainConstants.MN;
							warningExist = true;
						} else if ((variableFormat
								.equalsIgnoreCase(DomainConstants.YRS) || variableFormat
								.equalsIgnoreCase(DomainConstants.YR))
								&& (((null == mapping.getHsd01Value() || mapping
										.getHsd01Value().equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
										|| selectedEB09CodeValue
												.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.YY))))) {
							placeHoldValues[0] = variableFormat;
							placeHoldValues[1] = DomainConstants.YY;
							warningExist = true;
						}
					}

					if (null != selectedEB09CodeValue
							&& !selectedEB09CodeValue
									.equalsIgnoreCase(DomainConstants.EMPTY)) {
						if (null != mapping.getHsd01Value()
								&& !mapping.getHsd01Value().equalsIgnoreCase(
										DomainConstants.EMPTY)&& (variableFormat != DomainConstants.YRS && variableFormat != DomainConstants.YR)) {
							hippaSegmentValidationResult = setHippaSegVldnRslt(
									mapping,
									hippaSegmentValidationResult,
									statusForError,
									ValidatorConstants.ERROR_MESSAGE_BOTH_EB09_HSD01_CANNOT_BE_MAPPED_TOGETHER,
									new String[] {});
							hippaSegmentValidationResultList
									.add(hippaSegmentValidationResult);

						} else {
							if ((formatsForEb09.contains(variableFormat)
									|| variableFormat.equalsIgnoreCase(DomainConstants.YRS) || variableFormat.equalsIgnoreCase(DomainConstants.YR))
									&& !variableFormat.equalsIgnoreCase(DomainConstants.OCC)
									&& !variableFormat.equalsIgnoreCase(DomainConstants.OCRS)
									&& warningExist) {
								// Warning for EB09 considered YRS, donot
								// consider OCRS
								hippaSegmentValidationResult = setHippaSegVldnRslt(
										mapping,
										hippaSegmentValidationResult,
										statusForWarning,
										ValidatorConstants.WARNING_MESSAGE_FOR_EB09_BASED_ON_FORMAT,
										placeHoldValues);
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);

							}
						}
					} else {
						if (null != mapping.getHsd01Value()
								&& !mapping.getHsd01Value().equalsIgnoreCase(
										DomainConstants.EMPTY)
								&& (variableFormat != DomainConstants.YRS && variableFormat != DomainConstants.YR)) {
							// warning for HSD donot consider OCRS
							if ((formatsForEb09.contains(variableFormat)
									&& variableFormat != DomainConstants.YRS && variableFormat != DomainConstants.YR)
									&& variableFormat != DomainConstants.OCC
									&& variableFormat != DomainConstants.OCRS
									&& warningExist) {
								hippaSegmentValidationResult = setHippaSegVldnRslt(
										mapping,
										hippaSegmentValidationResult,
										statusForWarning,
										ValidatorConstants.WARNING_MESSAGE_FOR_HSD01_BASED_ON_FORMAT,
										placeHoldValues);
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);

							}
						} else {
							hippaSegmentValidationResult = setHippaSegVldnRslt(
									mapping,
									hippaSegmentValidationResult,
									statusForError,
									ValidatorConstants.ERROR_MESSAGE_EB09_OR_HSD01_SHOULD_BE_MAPPED,
									new String[] { variableFormat });
							hippaSegmentValidationResultList
									.add(hippaSegmentValidationResult);

						}
					}
				}
			}
		}

	}
    
    //ENDS

	private void validateEb09Hsd01ForSPS(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		// BXNI EB09 Validation for Variable
		String spsFormat = DomainConstants.EMPTY;
		short statusForError = 0;
		short statusForWarning = 2;
		boolean errorExist = false;
		boolean warningExist = false;
		List<String> spsFormatList = new ArrayList<String>();
		String spsFormatFromList = "";
		ValidationUtility validationUtility = new ValidationUtility();
		if (null != mapping.getSpsId()
				&& null != mapping.getSpsId().getSpsDetail()
				&& (!mapping.getSpsId().getSpsDetail().isEmpty())) {
			spsFormatList = validationUtility
					.getSpsFormatsFromSPSDetail(mapping.getSpsId()
							.getSpsDetail());
		}
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String selectedEB09CodeValue = DomainConstants.EMPTY;
		selectedEB09CodeValue = mapping.getEB09Value();
		String[] placeHoldValues = new String[2];
		if (null == selectedEB09CodeValue 
				|| selectedEB09CodeValue.equalsIgnoreCase(DomainConstants.EMPTY)) {
			selectedEB09CodeValue = DomainConstants.EMPTY;
		}
		selectedEB09CodeValue = selectedEB09CodeValue.trim();
		if (null != spsFormatList && !spsFormatList.isEmpty()) {
			Iterator<String> spsFormatIterator = spsFormatList.iterator();
			while (spsFormatIterator.hasNext()) {
				spsFormatFromList = (String) spsFormatIterator.next();
				spsFormatFromList = spsFormatFromList.trim().toUpperCase();
				warningExist = false;
				List<String> formatsForEb09 = getFormatForEB09();
				if (null != mapping.getSpsId() && null != spsFormatFromList
						&& !spsFormatFromList.equals(DomainConstants.EMPTY)) {

					if (formatsForEb09.contains(spsFormatFromList)
							|| spsFormatFromList
									.equalsIgnoreCase(DomainConstants.YR)
							|| spsFormatFromList
									.equalsIgnoreCase(DomainConstants.YRS)) {

						if (((null == selectedEB09CodeValue || selectedEB09CodeValue
								.equalsIgnoreCase(DomainConstants.EMPTY)) && ((null != mapping
								.getHsd01Value() && !mapping.getHsd01Value()
								.equalsIgnoreCase(DomainConstants.EMPTY))))
								|| (((null != selectedEB09CodeValue && !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.EMPTY)) && ((null == mapping
										.getHsd01Value() || mapping
										.getHsd01Value().equalsIgnoreCase(DomainConstants.EMPTY)))))) {

							if ((spsFormatFromList
									.equalsIgnoreCase(DomainConstants.VST)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.VISIT) || spsFormatFromList
									.equalsIgnoreCase(DomainConstants.VISITS))
									
									&& ( ((null == mapping.getHsd01Value() || mapping
											.getHsd01Value()
											.equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
											|| selectedEB09CodeValue
													.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.VS)
											)) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.EMPTY))
											&& null != mapping.getHsd01Value()
											&& !mapping.getHsd01Value()
													.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
											.getHsd01Value().equalsIgnoreCase(
													DomainConstants.VS)))) {
								placeHoldValues[0] = spsFormatFromList;
								placeHoldValues[1] = DomainConstants.VS;
								warningExist = true;
							} else if ((spsFormatFromList
									.equalsIgnoreCase(DomainConstants.HOUR_HR)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.HRS)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.HOUR) || spsFormatFromList
									.equalsIgnoreCase(DomainConstants.HOURS))
									&& ( ((null == mapping.getHsd01Value() || mapping
											.getHsd01Value()
											.equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
											|| selectedEB09CodeValue
													.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.HS)
											)) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.EMPTY))
											&& null != mapping.getHsd01Value()
											&& !mapping.getHsd01Value()
													.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
											.getHsd01Value().equalsIgnoreCase(
													DomainConstants.HS)))) {
								placeHoldValues[0] = spsFormatFromList;
								placeHoldValues[1] = DomainConstants.HS;
								warningExist = true;
							} else if ((spsFormatFromList
									.equalsIgnoreCase(DomainConstants.DAY)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.DAYS)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.LEN)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.DAY_DYS) || spsFormatFromList
									.equalsIgnoreCase(DomainConstants.DAY_DY))
									&& ( ((null == mapping.getHsd01Value() || mapping
											.getHsd01Value()
											.equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
											|| selectedEB09CodeValue
													.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.DY)
											)) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.EMPTY))
											&& null != mapping.getHsd01Value()
											&& !mapping.getHsd01Value()
													.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
											.getHsd01Value().equalsIgnoreCase(
													DomainConstants.DY)))) {
								placeHoldValues[0] = spsFormatFromList;
								placeHoldValues[1] = DomainConstants.DY;
								warningExist = true;
							} else if ((spsFormatFromList
									.equalsIgnoreCase(DomainConstants.MTH)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.MTHS)
									|| spsFormatFromList
											.equalsIgnoreCase(DomainConstants.MONTH) || spsFormatFromList
									.equalsIgnoreCase(DomainConstants.MONTHS))
									&& ( ((null == mapping.getHsd01Value() || mapping
											.getHsd01Value()
											.equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
											|| selectedEB09CodeValue
													.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.MN)
											)) || ((null == selectedEB09CodeValue || selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.EMPTY))
											&& null != mapping.getHsd01Value()
											&& !mapping.getHsd01Value()
													.equalsIgnoreCase(DomainConstants.EMPTY) && !mapping
											.getHsd01Value().equalsIgnoreCase(
													DomainConstants.MN)))) {
								placeHoldValues[0] = spsFormatFromList;
								placeHoldValues[1] = DomainConstants.MN;
								warningExist = true;
							} else if ((spsFormatFromList
									.equalsIgnoreCase(DomainConstants.YRS))
									&& ( ((null == mapping.getHsd01Value() || mapping
											.getHsd01Value()
											.equalsIgnoreCase(DomainConstants.EMPTY)) && (null == selectedEB09CodeValue
											|| selectedEB09CodeValue
													.equalsIgnoreCase(DomainConstants.EMPTY) || !selectedEB09CodeValue
											.equalsIgnoreCase(DomainConstants.YY)
											)))) {
								placeHoldValues[0] = spsFormatFromList;
								placeHoldValues[1] = DomainConstants.YY;
								warningExist = true;
							}
						}

						if (null != selectedEB09CodeValue
								&& !selectedEB09CodeValue
										.equalsIgnoreCase(DomainConstants.EMPTY)) {
							if (null != mapping.getHsd01Value()
									&& !mapping.getHsd01Value()
											.equalsIgnoreCase(
													DomainConstants.EMPTY)
									&& (spsFormatFromList != DomainConstants.YRS && spsFormatFromList != DomainConstants.YR)) {
								hippaSegmentValidationResult = setHippaSegVldnRslt(
										mapping,
										hippaSegmentValidationResult,
										statusForError,
										ValidatorConstants.ERROR_MESSAGE_BOTH_EB09_HSD01_CANNOT_BE_MAPPED_TOGETHER,
										new String[] {});
								hippaSegmentValidationResultList
										.add(hippaSegmentValidationResult);
								errorExist = true;

							} else {
								if ((formatsForEb09.contains(spsFormatFromList)
										|| spsFormatFromList.equalsIgnoreCase(DomainConstants.YRS) || spsFormatFromList.equalsIgnoreCase(DomainConstants.YR))
										&& spsFormatFromList != DomainConstants.OCC 
										&& spsFormatFromList != DomainConstants.OCRS && warningExist) {
									// Warning for EB09 considered YRS, donot
									// consider OCRS
									hippaSegmentValidationResult = setHippaSegVldnRslt(
											mapping,
											hippaSegmentValidationResult,
											statusForWarning,
											ValidatorConstants.WARNING_MESSAGE_FOR_EB09_BASED_ON_FORMAT,
											placeHoldValues);
									hippaSegmentValidationResultList
											.add(hippaSegmentValidationResult);
									
								}
							}
						} else {
							if (null != mapping.getHsd01Value()
									&& !mapping.getHsd01Value()
											.equalsIgnoreCase(
													DomainConstants.EMPTY) 
									&& (spsFormatFromList != DomainConstants.YRS && spsFormatFromList != DomainConstants.YR)) {
								// warning for HSD donot consider OCRS
								if ((formatsForEb09.contains(spsFormatFromList)
										&& spsFormatFromList != DomainConstants.YRS && spsFormatFromList != DomainConstants.YR)
										&& spsFormatFromList != DomainConstants.OCC
										&& spsFormatFromList != DomainConstants.OCRS && warningExist) {
									hippaSegmentValidationResult = setHippaSegVldnRslt(
											mapping,
											hippaSegmentValidationResult,
											statusForWarning,
											ValidatorConstants.WARNING_MESSAGE_FOR_HSD01_BASED_ON_FORMAT,
											placeHoldValues);
									hippaSegmentValidationResultList
											.add(hippaSegmentValidationResult);
									

								}} else {
									hippaSegmentValidationResult = setHippaSegVldnRslt(
											mapping,
											hippaSegmentValidationResult,
											statusForError,
											ValidatorConstants.ERROR_MESSAGE_EB09_OR_HSD01_SHOULD_BE_MAPPED,
											new String[] { spsFormatFromList });
									hippaSegmentValidationResultList
											.add(hippaSegmentValidationResult);
									errorExist = true;
								}
							}
						}if(errorExist == true){
						break;
						}
					}
				}

			}

	}
	// Removed BXNI December Release CR 26 Change -IF Start Age/End Age is mapped, then EB09 should not be mapped.
	//BXNI June Release CR 37 IF EB01 = F, variable format other than DOL, and start age and end age is mapped, and EB09 is not mapped.

	private void validateEb09AgeForVariable(Mapping mapping,
			List<HippaSegmentValidationResult> hippaSegmentValidationResultList) {
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		short statusForError = 0;
		String variableFormat = "";
		String eb01Value = "";
		if(null != mapping.getVariable() && !mapping.getVariable().equals("")
				&&  null != mapping.getVariable().getVariableFormat()){
			variableFormat = mapping.getVariable().getVariableFormat().trim().toUpperCase();
			List<String> formatsForEb09 = getFormatForEB09();
			
			
			if (null != mapping && null != mapping.getVariable()) {
				if (null != mapping.getEB01Value()
						&& !mapping.getEB01Value().equalsIgnoreCase(
								DomainConstants.EMPTY)) {
					eb01Value = mapping.getEB01Value();
				}
			}

			
			//BXNI June Release CR37 change, validation removed so as EB09 must be mapped IF EB01 = F, variable format other than DOL, and start age and end age is mapped, and EB09 is not mapped.

		/*	if (formatsForEb09.contains(variableFormat)
					|| variableFormat.equalsIgnoreCase(DomainConstants.YR)
					|| variableFormat.equalsIgnoreCase(DomainConstants.YRS)) {
						if ((null != mapping.getStartAgeValue() && !mapping.getStartAgeValue().equalsIgnoreCase("")) || 
								(null != mapping.getEndAgeValue() && !mapping.getEndAgeValue().equalsIgnoreCase(""))) {
							if (null != mapping.getEB09Value() && !mapping.getEB09Value().equals(DomainConstants.EMPTY)) {
								hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
										new HippaSegmentValidationResult(), statusForError,
										ValidatorConstants.ERROR_START_END_AGE_MAPPED_FOR_EB09,
										new String[] {});
								hippaSegmentValidationResultList
								.add(hippaSegmentValidationResult);
							}
						}
			}*/
			
			/* BXNI- CR37 June Release*/
			
			/*if(eb01Value.equalsIgnoreCase(DomainConstants.LIMITATIONS)&&
					(!variableFormat.equalsIgnoreCase(DomainConstants.DOL)) &&
							(null != mapping.getStartAgeValue() && !mapping.getStartAgeValue().equalsIgnoreCase(""))&&
							(null != mapping.getEndAgeValue() && !mapping.getEndAgeValue().equalsIgnoreCase("")))	{
				
				if (null == mapping.getEB09Value() || mapping.getEB09Value().equals(DomainConstants.EMPTY)) {
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping,
							new HippaSegmentValidationResult(), statusForError,
							ValidatorConstants.ERROR_START_END_AGE_MAPPED_AND_EB01F_FOR_EB09,
							new String[] {});
					hippaSegmentValidationResultList
					.add(hippaSegmentValidationResult);
				}
			}*/
			
		}
	}
    
	private List<String> getFormatForEB09() {
		List<String> formatsForEB09 = SimulationResourceBundle
				.getResourceBundle(
						DomainConstants.FORMAT_VALUES_FOR_EB06_EB09_VALIDATION,
						DomainConstants.PROPERTY_FILE_NAME);

		return formatsForEB09;
	}
}
