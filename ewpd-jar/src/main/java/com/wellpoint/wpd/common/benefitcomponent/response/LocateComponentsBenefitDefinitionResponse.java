/*
 * LocateComponentsBenefitDefinitionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.benefitcomponent.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.List;


/**
 * Response for Component Definition Locate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LocateComponentsBenefitDefinitionResponse extends WPDResponse{
	// variable declarations
	private List benefitDefinitionsList;
	/**
	 * Returns the benefitDefinitionsList
	 * @return List benefitDefinitionsList.
	 */
	public List getBenefitDefinitionsList() {
		return benefitDefinitionsList;
	}
	/**
	 * Sets the benefitDefinitionsList
	 * @param benefitDefinitionsList.
	 */
	public void setBenefitDefinitionsList(List benefitDefinitionsList) {
		this.benefitDefinitionsList = benefitDefinitionsList;
	}
}
