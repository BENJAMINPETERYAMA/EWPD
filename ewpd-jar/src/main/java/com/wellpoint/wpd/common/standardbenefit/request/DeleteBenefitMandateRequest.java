/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteBenefitMandateRequest extends WPDRequest{
	private int mandateMasterKey;
	private int version;
	private int standardBenefitMasterKey;
	private String mandateIdentifier;
	
	private int standardBenefitParentKey;
	
	private String standardBenefitStatus;
	
	private List businessDomains;
	
	
	/**
	 * @return Returns the mandateIdentifier.
	 */
	public String getMandateIdentifier() {
		return mandateIdentifier;
	}
	/**
	 * @param mandateIdentifier The mandateIdentifier to set.
	 */
	public void setMandateIdentifier(String mandateIdentifier) {
		this.mandateIdentifier = mandateIdentifier;
	}
	/**
	 * @return Returns the mandateMasterKey.
	 */
	public int getMandateMasterKey() {
		return mandateMasterKey;
	}
	/**
	 * @param mandateMasterKey The mandateMasterKey to set.
	 */
	public void setMandateMasterKey(int mandateMasterKey) {
		this.mandateMasterKey = mandateMasterKey;
	}
	/**
	 * @return Returns the standardBenefitMasterKey.
	 */
	public int getStandardBenefitMasterKey() {
		return standardBenefitMasterKey;
	}
	/**
	 * @param standardBenefitMasterKey The standardBenefitMasterKey to set.
	 */
	public void setStandardBenefitMasterKey(int standardBenefitMasterKey) {
		this.standardBenefitMasterKey = standardBenefitMasterKey;
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
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
