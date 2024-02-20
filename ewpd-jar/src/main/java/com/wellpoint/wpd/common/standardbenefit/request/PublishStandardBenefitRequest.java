/*
 * PublishStandardBenefitRequest.java
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
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PublishStandardBenefitRequest extends WPDRequest {
	public int standardBenefitKey;
	public String standardBenefitIdentifier;
	public int standardBenefitVersion;
	private int standardBenefitParentKey;
	private String standardBenefitStatus;
	private List businessDomains;
	private StandardBenefitSearchVO standardBenefitSearchVO;	
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the standardBenefitIdentifier
	 * @return String standardBenefitIdentifier.
	 */
	public String getStandardBenefitIdentifier() {
		return standardBenefitIdentifier;
	}
	/**
	 * Sets the standardBenefitIdentifier
	 * @param standardBenefitIdentifier.
	 */
	public void setStandardBenefitIdentifier(String standardBenefitIdentifier) {
		this.standardBenefitIdentifier = standardBenefitIdentifier;
	}
	/**
	 * Returns the standardBenefitVersion
	 * @return int standardBenefitVersion.
	 */
	public int getStandardBenefitVersion() {
		return standardBenefitVersion;
	}
	/**
	 * Sets the standardBenefitVersion
	 * @param standardBenefitVersion.
	 */
	public void setStandardBenefitVersion(int standardBenefitVersion) {
		this.standardBenefitVersion = standardBenefitVersion;
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
	 * @return Returns the standardBenefitSearchVO.
	 */
	public StandardBenefitSearchVO getStandardBenefitSearchVO() {
		return standardBenefitSearchVO;
	}
	/**
	 * @param standardBenefitSearchVO The standardBenefitSearchVO to set.
	 */
	public void setStandardBenefitSearchVO(
			StandardBenefitSearchVO standardBenefitSearchVO) {
		this.standardBenefitSearchVO = standardBenefitSearchVO;
	}
}
