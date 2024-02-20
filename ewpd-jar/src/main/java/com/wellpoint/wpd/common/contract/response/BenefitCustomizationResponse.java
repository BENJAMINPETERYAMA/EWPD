/*
 * Created on Dec 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitCustomizationResponse  extends ContractResponse{
	
	private boolean isSuccess;
	
	private boolean showHidden;
	
	private boolean benefitComponentHide;
	

	/**
	 * @return Returns the isSuccess.
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess The isSuccess to set.
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return Returns the showHidden.
	 */
	public boolean isShowHidden() {
		return showHidden;
	}
	/**
	 * @param showHidden The showHidden to set.
	 */
	public void setShowHidden(boolean showHidden) {
		this.showHidden = showHidden;
	}
	/**
	 * @return Returns the benefitComponentHide.
	 */
	public boolean isBenefitComponentHide() {
		return benefitComponentHide;
	}
	/**
	 * @param benefitComponentHide The benefitComponentHide to set.
	 */
	public void setBenefitComponentHide(boolean benefitComponentHide) {
		this.benefitComponentHide = benefitComponentHide;
	}
}
