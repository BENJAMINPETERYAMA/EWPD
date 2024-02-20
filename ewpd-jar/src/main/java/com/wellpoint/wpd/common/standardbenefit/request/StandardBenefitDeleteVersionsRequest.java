/*
 * Created on Apr 11, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author U11648
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitDeleteVersionsRequest extends WPDRequest {
	private int standardBenefitKey;
	private String standardBenefitName;
	private int standardBenefitParentKey;
	private List businessDomains;
	private String status;
	private int version;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the standardBenefitName.
	 */
	public String getStandardBenefitName() {
		return standardBenefitName;
	}
	/**
	 * @param standardBenefitName The standardBenefitName to set.
	 */
	public void setStandardBenefitName(String standardBenefitName) {
		this.standardBenefitName = standardBenefitName;
	}
	
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * @return Returns the standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * @param standardBenefitParentKey The standardBenefitParentKey to set.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
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
}
