/*
 * Created on Jul 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitComponentSearchViewRequest extends WPDRequest{

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	 private int benefitComponentKey;
	/**
	 * @return Returns the benefitComponentKey.
	 */
	public int getBenefitComponentKey() {
		return benefitComponentKey;
	}
	/**
	 * @param benefitComponentKey The benefitComponentKey to set.
	 */
	public void setBenefitComponentKey(int benefitComponentKey) {
		this.benefitComponentKey = benefitComponentKey;
	}
}
