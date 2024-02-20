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

/**
 * @author UST Global
 *
 */
public class NM1MessageSegmentValidator extends Validator implements HippaSegmentValidator  {

	@Override
	public List validate(Mapping mapping) throws DomainException {
		if (null == mapping) {
			throw new DomainException("Mapping object expected.");
		}
		List<HippaSegmentValidationResult> hippaSegmentValidationResultList = new ArrayList<HippaSegmentValidationResult>();

		ValidationUtility validationUtility = new ValidationUtility();
		short status = 0;

		List hippaCodeSelectedValues = validationUtility
				.removeBlankfromList(mapping
						.getHippaSegmentSelectedValues(mapping.getNm1MessageSegment()));
		List hippaCodePossibleValues = mapping.getNm1MessageSegment().getHippaCodePossibleValues();
		List hippaSegmentValidationList = validate(DomainConstants.LOOP2120NM1MESSAGESEGMENT, hippaCodePossibleValues, mapping.getNm1MessageSegment().getHippaCodeSelectedValues(), mapping);
		if(hippaSegmentValidationList!= null && !hippaSegmentValidationList.isEmpty()){
	        for(Iterator<HippaSegmentValidationResult> itr = hippaSegmentValidationList.iterator();itr.hasNext();){
	            HippaSegmentValidationResult result = (HippaSegmentValidationResult)itr.next();
	            hippaSegmentValidationResultList.add(result); 
	        }
	    }
		return hippaSegmentValidationResultList;
	}

}
