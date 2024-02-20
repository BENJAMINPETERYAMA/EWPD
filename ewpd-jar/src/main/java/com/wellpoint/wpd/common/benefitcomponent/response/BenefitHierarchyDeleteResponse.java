/*
 * BenefitHierarchyDeleteResponse.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * Response for Benefit Hierarchy Delete. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyDeleteResponse extends WPDResponse{
	
	private BenefitHierarchyVO benefitHierarchyVO;

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
