package com.wellpoint.ets.bx.mapping.factory;

import com.wellpoint.ets.bx.mapping.domain.service.AccumsValidator;
import com.wellpoint.ets.bx.mapping.domain.service.NM1MessageSegmentValidator;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.Eb01Validator;
import com.wellpoint.ets.bx.mapping.domain.service.Eb02Validator;
import com.wellpoint.ets.bx.mapping.domain.service.Eb03Validator;
import com.wellpoint.ets.bx.mapping.domain.service.Eb06Validator;
import com.wellpoint.ets.bx.mapping.domain.service.Eb09Validator;
import com.wellpoint.ets.bx.mapping.domain.service.HSDCodesValidator;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator;
import com.wellpoint.ets.bx.mapping.domain.service.III02Validator;
import com.wellpoint.ets.bx.mapping.domain.service.NoteTypeIndValidator;
import com.wellpoint.ets.bx.mapping.domain.service.UMRuleValidator;

public class HippaSegmentValidationFactory {
    
    HippaSegmentValidator hippaSegmentValidator;
    public static final String HSD_CODES = "hsdcodes";
	
    public HippaSegmentValidator getValidator(String segmentName) {
	    
	    segmentName = segmentName.trim().toLowerCase();
		if("eb01".equals(segmentName) ){
		    hippaSegmentValidator = new Eb01Validator();
		}
		if("eb02".equals(segmentName)){
		    hippaSegmentValidator = new Eb02Validator();
		}
		if("eb03".equals(segmentName)){
		    hippaSegmentValidator = new Eb03Validator();
		}
		if("eb06".equals(segmentName)){
		    hippaSegmentValidator = new Eb06Validator();
		}
		if("eb09".equals(segmentName)){
		    hippaSegmentValidator = new Eb09Validator();
		}
		if("iii02".equals(segmentName)){
		    hippaSegmentValidator = new III02Validator();
		}
		if("note_type_code".equals(segmentName)){
		    hippaSegmentValidator = new NoteTypeIndValidator();
		}
		if("note type code".equals(segmentName)){			
			hippaSegmentValidator = new NoteTypeIndValidator();
		}
		if("hsdcodes".equals(segmentName)){
			
		    hippaSegmentValidator = new HSDCodesValidator();
		}
		
		if("accum".equals(segmentName)){
		    hippaSegmentValidator = new AccumsValidator();
		}
		if("accumulator reference".equals(segmentName)){
		    hippaSegmentValidator = new AccumsValidator();
		}
		if(DomainConstants.UMRULE_NAME.toLowerCase().equals(segmentName)){
		    hippaSegmentValidator = new UMRuleValidator();
		}
		// Added for NM1 Message Segment
		if(DomainConstants.NM1_MSG_SGMNT.equalsIgnoreCase(segmentName)){
		    hippaSegmentValidator = new NM1MessageSegmentValidator();
		}
		
		return hippaSegmentValidator;
	}
}
