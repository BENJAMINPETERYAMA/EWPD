/*
 * Created on Mar 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;


/**
 * @author u13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitSearchRequest extends WPDRequest{

	private StandardBenefitSearchVO standardBenefitSearchVO;
	
	/**
	 * 
	 */
	public StandardBenefitSearchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the standardBenefitSearchVO.
	 */
	public StandardBenefitSearchVO getStandardBenefitSearchVO() {
		return standardBenefitSearchVO;
	}
	/**
	 * @param standardBenefitSearchVO The standardBenefitSearchVO to set.
	 */
	public void setStandardBenefitSearchVO(
			StandardBenefitSearchVO standardBenefitSearchVO) {
		this.standardBenefitSearchVO = standardBenefitSearchVO;
	}
}
