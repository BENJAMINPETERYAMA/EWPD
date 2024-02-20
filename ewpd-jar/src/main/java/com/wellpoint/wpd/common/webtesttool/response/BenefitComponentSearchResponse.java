/*
 * Created on Aug 26, 2009
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
public class BenefitComponentSearchResponse extends WPDResponse{
	
	private List benefitComponentResultList;
    private boolean success;	
	/**
	 * @return Returns the benefitComponentResultList.
	 */
	public List getBenefitComponentResultList() {
		return benefitComponentResultList;
	}
	/**
	 * @param benefitComponentResultList The benefitComponentResultList to set.
	 */
	public void setBenefitComponentResultList(List benefitComponentResultList) {
		this.benefitComponentResultList = benefitComponentResultList;
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
