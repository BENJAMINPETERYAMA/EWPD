/*
 * UpdateBenefitLinesRequest.java
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

import java.util.List;


/**
 * Request for Update Benefit Line
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class UpdateBenefitLinesRequest extends WPDRequest{
	
	// variable declarations
	private List updatedBenefitLines;
	private List hideBenefitLevels;
	private String mainObjectIdentifier;
	private int mainObjectKey;
	private int versionNumber;
	private List domainList;

    /**
     * @return Returns the domainList.
     */
    public List getDomainList() {
        return domainList;
    }
    /**
     * @param domainList The domainList to set.
     */
    public void setDomainList(List domainList) {
        this.domainList = domainList;
    }
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the updatedBenefitLines
	 * @return List updatedBenefitLines.
	 */
	public List getUpdatedBenefitLines() {
		return updatedBenefitLines;
	}
	/**
	 * Sets the updatedBenefitLines
	 * @param updatedBenefitLines.
	 */
	public void setUpdatedBenefitLines(List updatedBenefitLines) {
		this.updatedBenefitLines = updatedBenefitLines;
	}
	/**
	 * Returns the mainObjectIdentifier
	 * @return String mainObjectIdentifier.
	 */
	public String getMainObjectIdentifier() {
		return mainObjectIdentifier;
	}
	/**
	 * Sets the mainObjectIdentifier
	 * @param mainObjectIdentifier.
	 */
	public void setMainObjectIdentifier(String mainObjectIdentifier) {
		this.mainObjectIdentifier = mainObjectIdentifier;
	}
	/**
	 * Returns the mainObjectKey
	 * @return int mainObjectKey.
	 */
	public int getMainObjectKey() {
		return mainObjectKey;
	}
	/**
	 * Sets the mainObjectKey
	 * @param mainObjectKey.
	 */
	public void setMainObjectKey(int mainObjectKey) {
		this.mainObjectKey = mainObjectKey;
	}
	/**
	 * Returns the versionNumber
	 * @return int versionNumber.
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * Sets the versionNumber
	 * @param versionNumber.
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
    /**
     * @return Returns the hideBenefitLevels.
     */
    public List getHideBenefitLevels() {
        return hideBenefitLevels;
    }
    /**
     * @param hideBenefitLevels The hideBenefitLevels to set.
     */
    public void setHideBenefitLevels(List hideBenefitLevels) {
        this.hideBenefitLevels = hideBenefitLevels;
    }
}
