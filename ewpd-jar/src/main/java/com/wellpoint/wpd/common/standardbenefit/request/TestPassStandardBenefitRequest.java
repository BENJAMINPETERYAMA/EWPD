/*
 * Created on Apr 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;


/**
 * @author U11648
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestPassStandardBenefitRequest extends WPDRequest {
	public int standardBenefitKey;
	public String standardBenefitIdentifier;
	public int standardBenefitVersion;
	private int standardBenefitParentKey;
	private String standardBenefitStatus;
	private List businessDomains;
	private StandardBenefitSearchVO standardBenefitSearchVO;		
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

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
	 * @return Returns the standardBenefitIdentifier.
	 */
	public String getStandardBenefitIdentifier() {
		return standardBenefitIdentifier;
	}
	/**
	 * @param standardBenefitIdentifier The standardBenefitIdentifier to set.
	 */
	public void setStandardBenefitIdentifier(String standardBenefitIdentifier) {
		this.standardBenefitIdentifier = standardBenefitIdentifier;
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
