/*
 * ProductBenefitComponentDeleteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitComponentDeleteResponse extends WPDResponse{

	
	private List benefitComponentsList = new ArrayList();
	
	/**
	 * @return Returns the benefitComponentsList.
	 */
	public List getBenefitComponentsList() {
		return benefitComponentsList;
	}
	/**
	 * @param benefitComponentsList The benefitComponentsList to set.
	 */
	public void setBenefitComponentsList(List benefitComponentsList) {
		this.benefitComponentsList = benefitComponentsList;
	}
}
