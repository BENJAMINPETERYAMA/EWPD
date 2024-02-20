/*
 * BenefitHierarchySearchRequest.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * Request to Search Benefit Hierarchy.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchySearchRequest extends WPDRequest{

	private BenefitHierarchyVO benefitHierarchyVO;
	private String action;
	
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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
