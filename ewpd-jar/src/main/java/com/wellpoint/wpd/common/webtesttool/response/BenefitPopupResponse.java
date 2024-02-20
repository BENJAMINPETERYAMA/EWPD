/*
 * Created on Aug 7, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.webtesttool.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U20628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitPopupResponse extends WPDResponse{
	
	 private List expectedBenefitList;
	 private boolean success;

	/**
	 * @return Returns the expectedBenefitList.
	 */
	public List getExpectedBenefitList() {
		return expectedBenefitList;
	}
	/**
	 * @param expectedBenefitList The expectedBenefitList to set.
	 */
	public void setExpectedBenefitList(List expectedBenefitList) {
		this.expectedBenefitList = expectedBenefitList;
	}
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
