/*
 * Created on Mar 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U12238
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodValidationStatusResponse extends WPDResponse {
	
	private int status;
	
	public static final int VALIDATION_SUCCESS = 1;
	
	public static final int VALIDATION_WAITING = 2;
	
	public static final int VALIDATION_ERRORS  = 3;
	
	private String beneftiComponentName;
	
	private int benefitComponentId;

	/**
	 * @return Returns the status.
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the beneftiComponentName.
	 */
	public String getBeneftiComponentName() {
		return beneftiComponentName;
	}
	/**
	 * @param beneftiComponentName The beneftiComponentName to set.
	 */
	public void setBeneftiComponentName(String beneftiComponentName) {
		this.beneftiComponentName = beneftiComponentName;
	}
}
