package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;

public class Eb02Validator extends Validator implements HippaSegmentValidator {

	public List validate(Mapping mapping)throws DomainException {
	    if(null == mapping){
	        throw new DomainException("Mapping  object expected.");
		}
	    short status = 0;
	    List hippaSegmentValidationResultList = new ArrayList();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		String selectedEB01CodeValue = "";
		String selectedEB02CodeValue = "";
		
		selectedEB02CodeValue = mapping.getEB02Value();
		selectedEB01CodeValue = mapping.getEB01Value();
		
//		if(selectedEB01CodeValue == null || selectedEB01CodeValue.trim().equals("")){
//		    hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, ValidatorConstants.DEPENDENCY_CHECK_FAILURE,new String[]{DomainConstants.EB01_NAME,DomainConstants.EB02_NAME});
//		    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
//		    
//		}
		if(selectedEB01CodeValue != null && !selectedEB01CodeValue.trim().equals("")){
				selectedEB01CodeValue = selectedEB01CodeValue.trim();
				if(selectedEB02CodeValue == null || selectedEB02CodeValue.trim().equals("")){
				    selectedEB02CodeValue = "";
				}
				
				if(
				    (((ValidatorConstants.DEDUCTIBLE).equalsIgnoreCase(selectedEB01CodeValue))    || 
				    ((ValidatorConstants.OUT_OF_POCKET).equalsIgnoreCase(selectedEB01CodeValue)) ||
				    ((ValidatorConstants.DEDUCTIBLE_WAIVED).equalsIgnoreCase(selectedEB01CodeValue)) ||
				    //BXNI CONDITION ADDED
				    ((ValidatorConstants.CO_PAYMENT).equalsIgnoreCase(selectedEB01CodeValue)) ||
				    ((ValidatorConstants.COINSURANCE).equalsIgnoreCase(selectedEB01CodeValue))
				    //CONDITION ENDS
					) 
						
				){
					
					//BXNI CHANGE
					if( ((selectedEB01CodeValue.equalsIgnoreCase(ValidatorConstants.CO_PAYMENT)) ||
									(selectedEB01CodeValue.equalsIgnoreCase(ValidatorConstants.COINSURANCE)))){
							if(selectedEB02CodeValue == null || selectedEB02CodeValue.trim().equals("")){
								String placeHoldValues[] = new String[1];
								placeHoldValues[0] = selectedEB01CodeValue;
								hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, ValidatorConstants.EB01_EB02_DEPENDENCY_VALIDATION,placeHoldValues);
							    hippaSegmentValidationResultList.add(hippaSegmentValidationResult); 
								
							}
						
					}
					
					// BXNI CHANGE ENDS
					
					else if(selectedEB02CodeValue == null || "".equals(selectedEB02CodeValue) ){
					    hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, ValidatorConstants.MAPPING_REQUIRED_FOR_EB02,new String[]{});
					    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
					}
				}else{
				    if(selectedEB02CodeValue != null && !"".equalsIgnoreCase(selectedEB02CodeValue) && !(ValidatorConstants.LIMITATION).equalsIgnoreCase(selectedEB01CodeValue)){
				        hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, ValidatorConstants.MAPPING_NOT_REQUIRED_FOR_EB02,new String[]{});
					    hippaSegmentValidationResultList.add(hippaSegmentValidationResult); 
				    }
				}
		}
		
		
		
		
		List hippaCodeSelectedValue = mapping.getEb02().getHippaCodeSelectedValues();
		List hippaSegmentValidationList = validate(mapping.getEb02().getName(), mapping.getEb02().getHippaCodePossibleValues(), hippaCodeSelectedValue, mapping);
		if(hippaSegmentValidationList!= null && !hippaSegmentValidationList.isEmpty()){
	        for(Iterator itr = hippaSegmentValidationList.iterator();itr.hasNext();){
	            HippaSegmentValidationResult result = (HippaSegmentValidationResult)itr.next();
	            hippaSegmentValidationResultList.add(result); 
	        }
	    }	
		    
		return hippaSegmentValidationResultList;
	}

}
