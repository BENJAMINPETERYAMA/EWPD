/*
 * BenefitHierarchySearchResponse.java
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

import java.util.List;


/**
 * Response for Benefit Hierarchy Search. 
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchySearchResponse extends WPDResponse{
	
	private BenefitHierarchyVO benefitHierarchyVO;
	private List benefitHierarchiesList;

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
	/**
	 * @return Returns the benefitHierarchiesList.
	 */
	public List getBenefitHierarchiesList() {
		return benefitHierarchiesList;
	}
	/**
	 * @param benefitHierarchiesList The benefitHierarchiesList to set.
	 */
	public void setBenefitHierarchiesList(List benefitHierarchiesList) {
		this.benefitHierarchiesList = benefitHierarchiesList;
	}
}
