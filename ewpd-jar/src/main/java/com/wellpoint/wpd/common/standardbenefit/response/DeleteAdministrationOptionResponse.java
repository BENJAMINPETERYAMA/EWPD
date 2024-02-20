/*
 * DeleteAdministrationOptionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.AdministrationOptionBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteAdministrationOptionResponse extends WPDResponse {
	private AdministrationOptionBO administrationOptionBO;
	

	/**
	 * Returns the administrationOptionBO
	 * @return AdministrationOptionBO administrationOptionBO.
	 */
	public AdministrationOptionBO getAdministrationOptionBO() {
		return administrationOptionBO;
	}
	/**
	 * Sets the administrationOptionBO
	 * @param administrationOptionBO.
	 */
	public void setAdministrationOptionBO(
			AdministrationOptionBO administrationOptionBO) {
		this.administrationOptionBO = administrationOptionBO;
	}
}
