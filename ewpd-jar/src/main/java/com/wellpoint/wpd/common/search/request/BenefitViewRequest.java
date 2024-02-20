/*
 * Created on Jul 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitViewRequest extends WPDRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
   private int standardBenefitKey;
	
	

		/**
		 * @return Returns the standardBenefitKey.
		 */
		public int getStandardBenefitKey() {
			return standardBenefitKey;
		}
		/**
		 * @param standardBenefitKey The standardBenefitKey to set.
		 */
		public void setStandardBenefitKey(int standardBenefitKey) {
			this.standardBenefitKey = standardBenefitKey;
		}
}
