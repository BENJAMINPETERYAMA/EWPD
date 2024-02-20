/*
 * EntityBenefitAdministrations.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.bo;

import java.util.List;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class EntityBenefitAdministrations {
	 private List benefitAdministrationList;
	/**
	 * Returns the benefitAdministrationList
	 * @return List benefitAdministrationList.
	 */
	public List getBenefitAdministrationList() {
		return benefitAdministrationList;
	}
	/**
	 * Sets the benefitAdministrationList
	 * @param benefitAdministrationList.
	 */
	public void setBenefitAdministrationList(List benefitAdministrationList) {
		this.benefitAdministrationList = benefitAdministrationList;
	}
}
