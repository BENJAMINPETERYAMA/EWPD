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
public class StandardBenefitVersionsLifeCycleRequest extends WPDRequest {

	 public static final int SCHEDULE_FOR_TEST_STANDARD_BENEFIT = 1;
	 
	 public static final int TEST_PASS_STANDARD_BENEFIT = 2;
	 
	 public static final int TEST_FAIL_STANDARD_BENEFIT = 3;
	 
	 public static final int PUBLISH_STANDARD_BENEFIT = 4;
	 
	 public static final int APPROVE_STANDARD_BENEFIT = 5;
	 
	 public static final int REJECT_STANDARD_BENEFIT = 6;
	 
	 public static final int UNLOCK_STANDARD_BENEFIT = 7;
	
	 private int action;
	 private int standardBenefitKey;
	 private String standardBenefitName;
	 private int standardBenefitParentKey;
	 private String standardBenefitStatus;
	 private int standardBenefitVersion;
	 private List businessDomains;

	 
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * @param standardBenefitStatus The standardBenefitStatus to set.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
	/**
	 * @return Returns the standardBenefitVersion.
	 */
	public int getStandardBenefitVersion() {
		return standardBenefitVersion;
	}
	/**
	 * @param standardBenefitVersion The standardBenefitVersion to set.
	 */
	public void setStandardBenefitVersion(int standardBenefitVersion) {
		this.standardBenefitVersion = standardBenefitVersion;
	}
}
