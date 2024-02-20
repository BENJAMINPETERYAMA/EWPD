/*
 * Created on Aug 7, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.webtesttool.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimLineVO;

/**
 * @author U20628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitComponentPopupRequest extends WPDRequest{
	
	private ClaimLineVO claimLineVO;
	

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the claimLineVO.
	 */
	public ClaimLineVO getClaimLineVO() {
		return claimLineVO;
	}
	/**
	 * @param claimLineVO The claimLineVO to set.
	 */
	public void setClaimLineVO(ClaimLineVO claimLineVO) {
		this.claimLineVO = claimLineVO;
	}
	
}
