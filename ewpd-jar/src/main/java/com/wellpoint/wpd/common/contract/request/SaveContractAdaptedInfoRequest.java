/*
 * ContractSpecificInfoRequest.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.contract.bo.AdaptedInfoBO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractAdaptedInfoRequest extends ContractRequest {
	
	private AdaptedInfoBO adaptedInfoBO;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the adaptedInfoBO.
	 */
	public AdaptedInfoBO getAdaptedInfoBO() {
		return adaptedInfoBO;
	}
	/**
	 * @param adaptedInfoBO The adaptedInfoBO to set.
	 */
	public void setAdaptedInfoBO(AdaptedInfoBO adaptedInfoBO) {
		this.adaptedInfoBO = adaptedInfoBO;
	}
}
