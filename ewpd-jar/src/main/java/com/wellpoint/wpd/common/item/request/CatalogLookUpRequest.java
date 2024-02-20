/*
 * ItemSearchRequest.java
 *  © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.item.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

public class CatalogLookUpRequest extends WPDRequest {
      /*
     * validate
     */
private String srdaFlag;
	
    /**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}

	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}
    public void validate() throws ValidationException {
    }
}