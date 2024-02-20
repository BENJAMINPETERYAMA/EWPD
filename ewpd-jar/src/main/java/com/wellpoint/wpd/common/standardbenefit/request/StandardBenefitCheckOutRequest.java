/*
 * Created on Mar 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;


/**
 * @author u13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitCheckOutRequest extends WPDRequest{
	
	private StandardBenefitVO standardBenefitVO;
	/**
	 * @return Returns the standardBenefitVO.
	 */
	public StandardBenefitVO getStandardBenefitVO() {
		return standardBenefitVO;
	}
	/**
	 * @param standardBenefitVO The standardBenefitVO to set.
	 */
	public void setStandardBenefitVO(StandardBenefitVO standardBenefitVO) {
		this.standardBenefitVO = standardBenefitVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
