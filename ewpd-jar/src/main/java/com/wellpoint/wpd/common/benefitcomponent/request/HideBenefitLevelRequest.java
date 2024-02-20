/*
 * HideBenefitLevelRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request for Hide Benefit Level
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class HideBenefitLevelRequest extends WPDRequest{
	
	// variable declarations
	private int benefitLevelId;
	private int benefitId;
	private int benefitComponentId;

	/**
	 * Returns the benefitComponentId
	 * @return int benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * Sets the benefitComponentId
	 * @param benefitComponentId.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * Returns the benefitId
	 * @return int benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * Sets the benefitId
	 * @param benefitId.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * Returns the benefitLevelId
	 * @return int benefitLevelId.
	 */
	public int getBenefitLevelId() {
		return benefitLevelId;
	}
	/**
	 * Sets the benefitLevelId
	 * @param benefitLevelId.
	 */
	public void setBenefitLevelId(int benefitLevelId) {
		this.benefitLevelId = benefitLevelId;
	}
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
