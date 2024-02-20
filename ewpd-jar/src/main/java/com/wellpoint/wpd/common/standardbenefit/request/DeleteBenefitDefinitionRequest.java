/*
 * DeleteBenefitDefinitionRequest.java
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
public class DeleteBenefitDefinitionRequest extends WPDRequest {
	private int benefitDefinitionMasterKey;
	private int version;
	private int standardBenefitMasterKey;
	private String benefitIdentifier;
	
	private int standardBenefitParentKey;
	private String standardBenefitStatus;
	private List businessDomains;
	private String benefitDefenitionKeys;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the benefitDefinitionMasterKey
	 * @return int benefitDefinitionMasterKey.
	 */
	public int getBenefitDefinitionMasterKey() {
		return benefitDefinitionMasterKey;
	}
	/**
	 * Sets the benefitDefinitionMasterKey
	 * @param benefitDefinitionMasterKey.
	 */
	public void setBenefitDefinitionMasterKey(int benefitDefinitionMasterKey) {
		this.benefitDefinitionMasterKey = benefitDefinitionMasterKey;
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
	 * Returns the standardBenefitMasterKey
	 * @return int standardBenefitMasterKey.
	 */
	public int getStandardBenefitMasterKey() {
		return standardBenefitMasterKey;
	}
	/**
	 * Sets the standardBenefitMasterKey
	 * @param standardBenefitMasterKey.
	 */
	public void setStandardBenefitMasterKey(int standardBenefitMasterKey) {
		this.standardBenefitMasterKey = standardBenefitMasterKey;
	}
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
	 * @return Returns the benefitDefenitionKeys.
	 */
	public String getBenefitDefenitionKeys() {
		return benefitDefenitionKeys;
	}
	/**
	 * @param benefitDefenitionKeys The benefitDefenitionKeys to set.
	 */
	public void setBenefitDefenitionKeys(String benefitDefenitionKeys) {
		this.benefitDefenitionKeys = benefitDefenitionKeys;
	}
}
