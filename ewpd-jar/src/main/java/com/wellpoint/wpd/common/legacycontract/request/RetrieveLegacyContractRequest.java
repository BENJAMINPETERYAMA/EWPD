/*
 * MigrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.legacycontract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.legacycontract.vo.LegacyContractVO;
import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveLegacyContractRequest extends MigrationContractRequest {

	LegacyContractVO legacyContractVO;

	boolean userConfirmToAddNewDS = false;

	

	/**
	 * @return legacyContractVO
	 * 
	 * Returns the legacyContractVO.
	 */
	public LegacyContractVO getLegacyContractVO() {
		return legacyContractVO;
	}

	/**
	 * @param legacyContractVO
	 * 
	 * Sets the legacyContractVO.
	 */
	public void setLegacyContractVO(LegacyContractVO legacyContractVO) {
		this.legacyContractVO = legacyContractVO;
	}

	public void validate() throws ValidationException {

	}
	/**
	 * Returns the userConfirmToAddNewDS
	 * @return boolean userConfirmToAddNewDS.
	 */
	public boolean isUserConfirmToAddNewDS() {
		return userConfirmToAddNewDS;
	}
	/**
	 * Sets the userConfirmToAddNewDS
	 * @param userConfirmToAddNewDS.
	 */
	public void setUserConfirmToAddNewDS(boolean userConfirmToAddNewDS) {
		this.userConfirmToAddNewDS = userConfirmToAddNewDS;
	}
}