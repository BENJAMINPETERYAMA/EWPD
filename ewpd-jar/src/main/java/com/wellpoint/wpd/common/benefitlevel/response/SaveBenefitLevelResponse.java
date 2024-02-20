/*
 * Created on Mar 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.response;

import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveBenefitLevelResponse extends WPDResponse{
	private BenefitWrapperVO benefitWrapperVO;
	
	
	public BenefitWrapperVO getBenefitWrapperVO() {
		return benefitWrapperVO;
	}
	public void setBenefitWrapperVO(BenefitWrapperVO benefitWrapperVO) {
		this.benefitWrapperVO = benefitWrapperVO;
	}
}
