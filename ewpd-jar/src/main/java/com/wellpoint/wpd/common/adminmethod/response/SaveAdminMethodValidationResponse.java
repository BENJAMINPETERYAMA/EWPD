/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminMethodValidationResponse  extends WPDResponse{
	
	List validationSPSList;
	int benefitId;
	int benefitComponentId;
	boolean nextBcExists;

	/**
	 * @return Returns the validationSPSList.
	 */
	public List getValidationSPSList() {
		return validationSPSList;
	}
	/**
	 * @param validationSPSList The validationSPSList to set.
	 */
	public void setValidationSPSList(List validationSPSList) {
		this.validationSPSList = validationSPSList;
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
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the nextBcExists.
	 */
	public boolean isNextBcExists() {
		return nextBcExists;
	}
	/**
	 * @param nextBcExists The nextBcExists to set.
	 */
	public void setNextBcExists(boolean nextBcExists) {
		this.nextBcExists = nextBcExists;
	}
}
