/*
 * ImportIndicativeMappingResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.indicativemapping.response;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeDetailBO;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeMappingBO;

/**
 * 
 * @author U42506
 * 
 */
public class ImportIndicativeMappingResponse extends WPDResponse {
	private boolean success;
	private Map<Integer, List<IndicativeDetailBO>> indicativeSegment;

	/**
	 * Returns the success
	 * 
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success
	 * 
	 * @param success
	 *            .
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Returns the indicative Segment fields which got modified, new or deleted
	 * 
	 * @return Map
	 */
	public Map<Integer, List<IndicativeDetailBO>> getIndicativeSegment() {
		return indicativeSegment;
	}

	/**
	 * Sets the indicative Segment fields which got modified, new or deleted
	 * 
	 * @param indicativeSegment
	 */
	public void setIndicativeSegment(
			Map<Integer, List<IndicativeDetailBO>> indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
	}

}
