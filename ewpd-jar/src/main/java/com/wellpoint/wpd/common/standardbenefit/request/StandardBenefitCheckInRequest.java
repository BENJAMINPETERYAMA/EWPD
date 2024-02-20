/*
 * Created on Mar 23, 2007
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
public class StandardBenefitCheckInRequest extends WPDRequest{
	private StandardBenefitVO standardBenefitVO;
	private StandardBenefitVO oldKeystandardBenefitVO;
    private int action;

	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
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
	/**
	 * @return Returns the oldKeystandardBenefitVO.
	 */
	public StandardBenefitVO getOldKeystandardBenefitVO() {
		return oldKeystandardBenefitVO;
	}
	/**
	 * @param oldKeystandardBenefitVO The oldKeystandardBenefitVO to set.
	 */
	public void setOldKeystandardBenefitVO(
			StandardBenefitVO oldKeystandardBenefitVO) {
		this.oldKeystandardBenefitVO = oldKeystandardBenefitVO;
	}
}
