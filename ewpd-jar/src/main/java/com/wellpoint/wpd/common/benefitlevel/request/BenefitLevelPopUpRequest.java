/*
 * Created on Mar 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelPopUpRequest extends WPDRequest{
	
	private int popUpId;

	private int standardBenfefitId;
	/**
	 * @return Returns the standardBenfefitId.
	 */
	public int getStandardBenfefitId() {
		return standardBenfefitId;
	}
	/**
	 * @param standardBenfefitId The standardBenfefitId to set.
	 */
	public void setStandardBenfefitId(int standardBenfefitId) {
		this.standardBenfefitId = standardBenfefitId;
	}
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the popUpId.
	 */
	public int getPopUpId() {
		return popUpId;
	}
	/**
	 * @param popUpId The popUpId to set.
	 */
	public void setPopUpId(int popUpId) {
		this.popUpId = popUpId;
	}
}
