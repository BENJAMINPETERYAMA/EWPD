/*
 * BenefitComponentCopyRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request for Benefit Component Copy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentCopyRequest extends WPDRequest{
	
	private BenefitComponentVO benefitComponentVO;
	
	private BenefitComponentVO oldBenefitComponentVO;
	
	private boolean deleteBenefit;
	private boolean domainChange = false;
    /**
     * Returns the oldBenefitComponentVO
     * @return BenefitComponentVO oldBenefitComponentVO.
     */
    public BenefitComponentVO getOldBenefitComponentVO() {
        return oldBenefitComponentVO;
    }
    /**
     * Sets the oldBenefitComponentVO
     * @param oldBenefitComponentVO.
     */
    public void setOldBenefitComponentVO(
            BenefitComponentVO oldBenefitComponentVO) {
        this.oldBenefitComponentVO = oldBenefitComponentVO;
    }
	/**
	 * @return Returns the benefitComponentVO.
	 */
	public BenefitComponentVO getBenefitComponentVO() {
		return benefitComponentVO;
	}
	/**
	 * @param benefitComponentVO The benefitComponentVO to set.
	 */
	public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
		this.benefitComponentVO = benefitComponentVO;
	}
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		
	}

	/**
	 * @return Returns the deleteBenefit.
	 */
	public boolean isDeleteBenefit() {
		return deleteBenefit;
	}
	/**
	 * @param deleteBenefit The deleteBenefit to set.
	 */
	public void setDeleteBenefit(boolean deleteBenefit) {
		this.deleteBenefit = deleteBenefit;
	}
	/**
	 * @return Returns the domainChange.
	 */
	public boolean isDomainChange() {
		return domainChange;
	}
	/**
	 * @param domainChange The domainChange to set.
	 */
	public void setDomainChange(boolean domainChange) {
		this.domainChange = domainChange;
	}
}
