/*
 * Created on Mar 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author U13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitMandateRequest extends WPDRequest{
	
	private int benefitMasterSystemId;
	private int maxSearchResultSize;
	
	
	/**
	 * @return Returns the benefitMasterSystemId.
	 */
	public int getBenefitMasterSystemId() {
		return benefitMasterSystemId;
	}
	/**
	 * @param benefitMasterSystemId The benefitMasterSystemId to set.
	 */
	public void setBenefitMasterSystemId(int benefitMasterSystemId) {
		this.benefitMasterSystemId = benefitMasterSystemId;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	
	

	/**
	 * @return Returns the maxSearchResultSize.
	 */
	public int getMaxSearchResultSize() {
		return maxSearchResultSize;
	}
	/**
	 * @param maxSearchResultSize The maxSearchResultSize to set.
	 */
	public void setMaxSearchResultSize(int maxSearchResultSize) {
		this.maxSearchResultSize = maxSearchResultSize;
	}
}
