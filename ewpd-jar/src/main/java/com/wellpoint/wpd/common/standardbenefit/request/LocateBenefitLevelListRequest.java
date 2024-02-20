/*
 * Created on Mar 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitLevelListRequest extends WPDRequest{

	private int benefitsystemId;
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the benefitsystemId
	 * @return int benefitsystemId.
	 */
	public int getBenefitsystemId() {
		return benefitsystemId;
	}
	/**
	 * Sets the benefitsystemId
	 * @param benefitsystemId.
	 */
	public void setBenefitsystemId(int benefitsystemId) {
		this.benefitsystemId = benefitsystemId;
	}
}
