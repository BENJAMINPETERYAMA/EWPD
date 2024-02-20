/*
 * RetrieveBenefitDefinitionRequest.java
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
public class RetrieveBenefitDefinitionRequest extends WPDRequest {

	private int benefitMasterSystemId;
	private int benefitDefinitionMasterKey;
	private int mainObjectVersion;
	private String benefitIdentifier;
	
	private int standardBenefitParentKey;
	private String standardBenefitStatus;
	private List businessDomains;
	/**
	 * Returns the benefitMasterSystemId
	 * @return int benefitMasterSystemId.
	 */
	public int getBenefitMasterSystemId() {
		return benefitMasterSystemId;
	}
	/**
	 * Sets the benefitMasterSystemId
	 * @param benefitMasterSystemId.
	 */
	public void setBenefitMasterSystemId(int benefitMasterSystemId) {
		this.benefitMasterSystemId = benefitMasterSystemId;
	}
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
}
