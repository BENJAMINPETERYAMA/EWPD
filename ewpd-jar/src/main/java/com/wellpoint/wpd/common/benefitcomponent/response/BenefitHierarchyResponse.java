/*
 * BenefitHierarchyResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Benefit Hierarchy. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyResponse extends WPDResponse{
	

	private BenefitHierarchyVO benefitHierarchyVO;
	
	private BenefitComponentBO benefitComponentBO;
	
	private boolean successFlag;
	
	private boolean maxFlag;

	/**
	 * @return Returns the maxFlag.
	 */
	public boolean isMaxFlag() {
		return maxFlag;
	}
	/**
	 * @param maxFlag The maxFlag to set.
	 */
	public void setMaxFlag(boolean maxFlag) {
		this.maxFlag = maxFlag;
	}
	/**
	 * @return Returns the benefitComponentBO.
	 */
	public BenefitComponentBO getBenefitComponentBO() {
		return benefitComponentBO;
	}
	/**
	 * @param benefitComponentBO The benefitComponentBO to set.
	 */
	public void setBenefitComponentBO(BenefitComponentBO benefitComponentBO) {
		this.benefitComponentBO = benefitComponentBO;
	}
	/**
	 * @return Returns the successFlag.
	 */
	public boolean isSuccessFlag() {
		return successFlag;
	}
	/**
	 * @param successFlag The successFlag to set.
	 */
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	/**
	 * @return Returns the benefitHierarchyVO.
	 */
	public BenefitHierarchyVO getBenefitHierarchyVO() {
		return benefitHierarchyVO;
	}
	/**
	 * @param benefitHierarchyVO The benefitHierarchyVO to set.
	 */
	public void setBenefitHierarchyVO(BenefitHierarchyVO benefitHierarchyVO) {
		this.benefitHierarchyVO = benefitHierarchyVO;
	}
}
