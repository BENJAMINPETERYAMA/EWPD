/*
 * HideQuestionRequest.java
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
public class HideQuestionRequest extends WPDRequest{
    
    // variable declaration
    private int adminOptionSysId;
    private int questionNumber;
    private int standardBenefitKey;
    private String benefitIdentifier;
    private int version;
    
	private int standardBenefitParentKey;
	
	private String standardBenefitStatus;
	
	private List businessDomains;
	private List questionNumbers;

	/**
	 * Returns the benefitIdentifier
	 * @return String benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * Sets the benefitIdentifier
	 * @param benefitIdentifier.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * Returns the standardBenefitKey
	 * @return int standardBenefitKey.
	 */
	public int getStandardBenefitKey() {
		return standardBenefitKey;
	}
	/**
	 * Sets the standardBenefitKey
	 * @param standardBenefitKey.
	 */
	public void setStandardBenefitKey(int standardBenefitKey) {
		this.standardBenefitKey = standardBenefitKey;
	}
	/**
	 * Returns the version
	 * @return int version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * Sets the version
	 * @param version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
    /**
     * @return Returns the adminOptionSysId.
     */
    public int getAdminOptionSysId() {
        return adminOptionSysId;
    }
    /**
     * @param adminOptionSysId The adminOptionSysId to set.
     */
    public void setAdminOptionSysId(int adminOptionSysId) {
        this.adminOptionSysId = adminOptionSysId;
    }
    /**
     * @return Returns the questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }
    /**
     * @param questionNumber The questionNumber to set.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
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
	/**
	 * @return Returns the questionNumbers.
	 */
	public List getQuestionNumbers() {
		return questionNumbers;
	}
	/**
	 * @param questionNumbers The questionNumbers to set.
	 */
	public void setQuestionNumbers(List questionNumbers) {
		this.questionNumbers = questionNumbers;
	}
}
