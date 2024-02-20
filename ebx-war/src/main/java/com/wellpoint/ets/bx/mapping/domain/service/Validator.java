/*
 * Created on May 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class Validator {
    
    private static Logger log = Logger.getLogger(Validator.class);
    /**
     * This method will validate the selectedHippaCodes against the possibleHippaCodes
     * @param hippaSegment
     * @param possibleHippaCode
     * @param selectedHippaCode
     * @return List<HippaSegmentValidationResult>
     */
    protected List validate(String hippaSegment, List possibleHippaCodes, List selectedHippaCodes, Mapping mapping)throws DomainException {
        if(null == mapping){
		    throw new DomainException("Mapping object expected.");
		}
        List hippaSegmentValidationResultList = new ArrayList();
        HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
        short status = 0;
        hippaSegmentValidationResult.setStatus(status);
        boolean isPossibleHippaCodeListEmpty = false;
        List possibleHippaCodeValue = new ArrayList();
        List selectedHippaCodeValue = new ArrayList();
        //ValidationUtility validationUtility = new ValidationUtility();
        
        //remove all blank values
        if(possibleHippaCodes!= null && !possibleHippaCodes.isEmpty()){
            for(Iterator itr = possibleHippaCodes.iterator();itr.hasNext();){
                HippaCodeValue hippaCodeValue = (HippaCodeValue)itr.next();
                if(hippaCodeValue.getValue() != null && !"".equals(hippaCodeValue.getValue().trim()) ){
                    possibleHippaCodeValue.add((String)hippaCodeValue.getValue().trim());
                }
            }
        }
        
        if(possibleHippaCodeValue== null || possibleHippaCodeValue.isEmpty()){
            isPossibleHippaCodeListEmpty = true;
        }
        
        //remove all blank values
        if(selectedHippaCodes!=null && !selectedHippaCodes.isEmpty()){
            for(Iterator itr = selectedHippaCodes.iterator();itr.hasNext();){
                HippaCodeValue hippaCodeValue = (HippaCodeValue)itr.next();
                if(hippaCodeValue.getValue() != null && !"".equals(hippaCodeValue.getValue().trim()) ){
                    selectedHippaCodeValue.add((String)hippaCodeValue.getValue().trim());
                }
            }
        }
        
	    //possibleHippaCode empty && selectedHippaCode not empty : fail
	     if((isPossibleHippaCodeListEmpty) && (null != selectedHippaCodeValue && !selectedHippaCodeValue.isEmpty())  ){
	        hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
			            ValidatorConstants.INVALID_MAPPING,new String[]{hippaSegment});
			hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
	        return hippaSegmentValidationResultList;
	    }
	     
	    //possibleHippaCode empty && selectedHippaCode empty:pass
	    if((isPossibleHippaCodeListEmpty) && (null == selectedHippaCodeValue || selectedHippaCodeValue.isEmpty())){
	        status = 1;
	        hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
	                ValidatorConstants.VALIDATION_SUCCESS,new String[]{hippaSegment});
		    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		    return hippaSegmentValidationResultList;
	    }
	    
	    //possible not null && selected is null : pass
	    if(!isPossibleHippaCodeListEmpty && (selectedHippaCodeValue == null || selectedHippaCodeValue.isEmpty())){
	        status = 1;
	        hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
	                ValidatorConstants.VALIDATION_SUCCESS,new String[]{hippaSegment});
		    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		    return hippaSegmentValidationResultList;
	    }
	    if("ACCUM".equalsIgnoreCase(hippaSegment)){
	    	List invalidAccumList = new ArrayList();
	    	if(null != selectedHippaCodeValue && !selectedHippaCodeValue.isEmpty()){
		        Set possibleHippaCodeSet = new HashSet();
				for(Iterator itr = possibleHippaCodeValue.iterator();itr.hasNext();){
				        String obj = (String)itr.next();
				        possibleHippaCodeSet.add((String)obj.toUpperCase());
				}
				boolean pass = true;
				for(Iterator itr = selectedHippaCodeValue.iterator();itr.hasNext();){
				    String obj = (String)itr.next();
				    if(obj == null){
				        obj = "";
				    }else{
				        obj = obj.trim().toUpperCase();
				    }
				    hippaSegmentValidationResult = new HippaSegmentValidationResult();
				    if(!possibleHippaCodeSet.contains((String)obj)){
				    	invalidAccumList.add(obj);
				        
				    }
				}
				if(invalidAccumList.size() > 0){
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
				            ValidatorConstants.INVALID_ACCUM_VALUE, new String[]{BxUtil.listToString(invalidAccumList, ",")});
					hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
				pass = false;
				}
				if(pass){
				    status = 1;
				    hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
				            ValidatorConstants.VALIDATION_SUCCESS,new String[]{hippaSegment});
					hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
				}
			 }
	    } else {
	    //possible not null && selected not null : iterate and find
		    if(null != selectedHippaCodeValue && !selectedHippaCodeValue.isEmpty()){
		        Set possibleHippaCodeSet = new HashSet();
				for(Iterator itr = possibleHippaCodeValue.iterator();itr.hasNext();){
				        String obj = (String)itr.next();
				        possibleHippaCodeSet.add((String)obj.toUpperCase());
				}
				boolean pass = true;
				for(Iterator itr = selectedHippaCodeValue.iterator();itr.hasNext();){
				    String obj = (String)itr.next();
				    if(obj == null){
				        obj = "";
				    }else{
				        obj = obj.trim().toUpperCase();
				    }
				    hippaSegmentValidationResult = new HippaSegmentValidationResult();
				    if(!possibleHippaCodeSet.contains((String)obj)){
				        hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
						            ValidatorConstants.INVALID_HIPPACODE_VALUE, new String[]{obj,hippaSegment});
						hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
						pass = false;
				    }
				}
				if(pass){
				    status = 1;
				    hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
				            ValidatorConstants.VALIDATION_SUCCESS,new String[]{hippaSegment});
					hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
				}
			 }
	    }
	    return hippaSegmentValidationResultList;
	}
    
    
    protected HippaSegmentValidationResult setHippaSegVldnRslt(Mapping mapping, HippaSegmentValidationResult hippaSegmentValidationResult, 
            short status, String statusCode, String[] placeHoldValues)throws DomainException{
        if(null == mapping){
            throw new DomainException("Mapping object is expected.");
        }
        if(null == hippaSegmentValidationResult){
            log.info("HippaSegmentValidationResult object cannot be null.");
            throw new DomainException();
        }
        hippaSegmentValidationResult.setMapping(mapping);
        hippaSegmentValidationResult.setStatus(status);
        if(status == 0){
            if(null != placeHoldValues && placeHoldValues.length > 0){
                hippaSegmentValidationResult.addFailureCode(statusCode,placeHoldValues);
            }else{
                hippaSegmentValidationResult.addFailureCode(statusCode);
            }
        }else if(status == 1){
            if(null != placeHoldValues && placeHoldValues.length > 0){
                hippaSegmentValidationResult.addSuccessCode(statusCode,placeHoldValues);
            }else{
                hippaSegmentValidationResult.addSuccessCode(statusCode);
            }
        }else if(status == 2){//validation pass with a warning message
            if(null != placeHoldValues && placeHoldValues.length > 0){
                hippaSegmentValidationResult.addWarningCode(statusCode,placeHoldValues);
            }else{
                hippaSegmentValidationResult.addWarningCode(statusCode);
            }
        }
	    
	    return hippaSegmentValidationResult;
    }
    
    
}
