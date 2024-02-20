/*
 * RetrieveNonAdjMandateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveNonAdjMandateRequest extends WPDRequest {
    
    // variable declarations
    private int mandateId;
    private int mainObjectKey;
    private String mainObjectIdentifier;
    private int mainObjectVersion;
    
	private int standardBenefitParentKey;
	
	private String standardBenefitStatus;
	
	private List businessDomains;
    
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
	 * Returns the mainObjectVersion
	 * @return int mainObjectVersion.
	 */
	public int getMainObjectVersion() {
		return mainObjectVersion;
	}
	/**
	 * Sets the mainObjectVersion
	 * @param mainObjectVersion.
	 */
	public void setMainObjectVersion(int mainObjectVersion) {
		this.mainObjectVersion = mainObjectVersion;
	}
    /**
     * @return Returns the mandateId.
     */
    public int getMandateId() {
        return mandateId;
    }
    /**
     * @param mandateId The mandateId to set.
     */
    public void setMandateId(int mandateId) {
        this.mandateId = mandateId;
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

	/**
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * Returns the standardBenefitParentKey
	 * @return int standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * Sets the standardBenefitParentKey
	 * @param standardBenefitParentKey.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * Returns the standardBenefitStatus
	 * @return String standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * Sets the standardBenefitStatus
	 * @param standardBenefitStatus.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
}
