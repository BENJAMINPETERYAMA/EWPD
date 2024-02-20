/*
 * LocateComponentsBenefitDefinitionRequest.java
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
 * Requst for Locate Component Benefit Definition.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateComponentsBenefitDefinitionRequest extends WPDRequest{
	
	// variable declaration
	private int benefitId;
	private int benefitComponentId;
	
	//**Benefit level/line hide
	private boolean showHidden;
	
	

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
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
