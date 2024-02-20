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
public class BenefitComponentPopupResponse extends WPDResponse{
	
	 private List expectedBenefitComponentList;
	 private boolean success;
	/**
	 * @return Returns the excpectedBenefitComponentList.
	 */
	public List getExpectedBenefitComponentList() {
		return expectedBenefitComponentList;
	}
	/**
	 * @param excpectedBenefitComponentList The excpectedBenefitComponentList to set.
	 */
	public void setExpectedBenefitComponentList(
			List excpectedBenefitComponentList) {
		this.expectedBenefitComponentList = excpectedBenefitComponentList;
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
