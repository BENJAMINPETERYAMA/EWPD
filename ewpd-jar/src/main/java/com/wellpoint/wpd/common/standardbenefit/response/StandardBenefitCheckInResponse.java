/*
 * Created on Mar 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;


/**
 * @author u13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitCheckInResponse extends WPDResponse {
	private StandardBenefitBO standardBenefitBO;
	private boolean isValidationSuccess;
	private boolean isCheckinSuccess;
	private DomainDetail domainDetail = null;
	
	/**
	 * @return Returns the standardBenefitBO.
	 */
	public StandardBenefitBO getStandardBenefitBO() {
		return standardBenefitBO;
	}
	/**
	 * @param standardBenefitBO The standardBenefitBO to set.
	 */
	public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
		this.standardBenefitBO = standardBenefitBO;
	}
	/**
	 * @return Returns the isValidationSuccess.
	 */
	public boolean isValidationSuccess() {
		return isValidationSuccess;
	}
	/**
	 * @param isValidationSuccess The isValidationSuccess to set.
	 */
	public void setValidationSuccess(boolean isValidationSuccess) {
		this.isValidationSuccess = isValidationSuccess;
	}
	/**
	 * @return Returns the isCheckinSuccess.
	 */
	public boolean isCheckinSuccess() {
		return isCheckinSuccess;
	}
	/**
	 * @param isCheckinSuccess The isCheckinSuccess to set.
	 */
	public void setCheckinSuccess(boolean isCheckinSuccess) {
		this.isCheckinSuccess = isCheckinSuccess;
	}
	
	/**
	 * @return Returns the domainDetail.
	 */
	public DomainDetail getDomainDetail() {
		return domainDetail;
	}
	/**
	 * @param domainDetail The domainDetail to set.
	 */
	public void setDomainDetail(DomainDetail domainDetail) {
		this.domainDetail = domainDetail;
	}
}
