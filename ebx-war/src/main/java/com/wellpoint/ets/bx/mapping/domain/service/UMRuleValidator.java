/**
 * 
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;

/**
 * @author U23641
 *
 */
public class UMRuleValidator extends Validator implements HippaSegmentValidator {

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator#validate(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public List validate(Mapping mapping) throws DomainException {
		List hippaSegmentValidationResultList = new ArrayList();
		HippaSegmentValidationResult hippaSegmentValidationResult = new HippaSegmentValidationResult();
		short status = 0;
		if(null != mapping.getRule()){
			
			//Check for duplicates
			List valueList = mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues();
			List duplicateValuesList = BxUtil.checkHippaCodeValueDuplicates(valueList);
		
			if(null != duplicateValuesList && !duplicateValuesList.isEmpty()){
				for(int i=0;i<duplicateValuesList.size();i++){
					String duplicateValue = (String)duplicateValuesList.get(i);
					hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, new HippaSegmentValidationResult(), status, 
				            ValidatorConstants.DUPLICATE_HIPPACODE_VALUE,new String[]{duplicateValue,DomainConstants.UMRULE_NAME});	
				    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
				}
			}
			
			if(null != mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues() 
					&& mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues().size() > 99){
				hippaSegmentValidationResult = setHippaSegVldnRslt(mapping, hippaSegmentValidationResult, status, 
			            ValidatorConstants.LIMIT_EXCEEDED,new String[]{DomainConstants.UMRULE_NAME});
			    hippaSegmentValidationResultList.add(hippaSegmentValidationResult);
		    }
			
		}
		
		List hippaSegmentValidationList = validate(mapping.getUtilizationMgmntRule().getName(), mapping.getUtilizationMgmntRule().getHippaCodePossibleValues(), mapping.getUtilizationMgmntRule().getHippaCodeSelectedValues(), mapping);
		if(hippaSegmentValidationList!= null && !hippaSegmentValidationList.isEmpty()){
		   for(Iterator itr = hippaSegmentValidationList.iterator();itr.hasNext();){
		       HippaSegmentValidationResult result = (HippaSegmentValidationResult)itr.next();
		       hippaSegmentValidationResultList.add(result); 
		   }
		}
		return hippaSegmentValidationResultList;
	}

}
