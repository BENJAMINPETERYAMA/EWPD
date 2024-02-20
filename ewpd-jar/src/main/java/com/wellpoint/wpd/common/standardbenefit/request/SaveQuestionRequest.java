/*
 * SaveQuestionRequest.java
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
public class SaveQuestionRequest extends WPDRequest{
    
    private List question;
    
    private int action;
    
    private int standardBenefitKey;
    
    private String benefitIdentifier;
    
    private int version;
    
	private int standardBenefitParentKey;
	
	private String standardBenefitStatus;
	
	private List businessDomains;
    /**
     * 
     */
    public SaveQuestionRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * @return Returns the question.
     */
    public List getQuestion() {
        return question;
    }
    
    /**
     * @param question The question to set.
     */
    public void setQuestion(List question) {
        this.question = question;
    }
    /**
     * @return Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action The action to set.
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     * @return Returns the benefitIdentifier.
     */
    public String getBenefitIdentifier() {
        return benefitIdentifier;
    }
    /**
     * @param benefitIdentifier The benefitIdentifier to set.
     */
    public void setBenefitIdentifier(String benefitIdentifier) {
        this.benefitIdentifier = benefitIdentifier;
    }
    /**
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * @param standardBenefitKey The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
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
