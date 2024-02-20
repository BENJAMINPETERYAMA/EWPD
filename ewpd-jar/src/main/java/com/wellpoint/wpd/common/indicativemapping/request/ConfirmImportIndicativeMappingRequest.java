/*
 * ConfirmImportIndicativeMappingRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.indicativemapping.request;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeDetailVO;

/**
 * 
 * @author U42506
 * 
 */

public class ConfirmImportIndicativeMappingRequest extends WPDRequest {
	private String indicativeSegmentNumber;
	private String region;

	private Map<String, List<IndicativeDetailVO>> processedExcelIndicativeSeg;

	public String getIndicativeSegmentNumber() {
		return indicativeSegmentNumber;
	}

	public void setIndicativeSegmentNumber(String indicativeSegmentNumber) {
		this.indicativeSegmentNumber = indicativeSegmentNumber;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * It will return all the indicative segment codes which are new,modified or
	 * deleted from the excel file where key is {@link BusinessConstants}
	 * .DELETED or {@link BusinessConstants}.ADDED or {@link BusinessConstants}
	 * .MODIFIED
	 * 
	 * @return Map<Integer, List<IndicativeMappingBO>>
	 */
	public Map<String, List<IndicativeDetailVO>> getProcessedExcelIndicativeSeg() {
		return processedExcelIndicativeSeg;
	}

	/**
	 * It will set all the indicative segment codes which are new,modified or
	 * deleted from the excel file where key is {@link BusinessConstants}
	 * .DELETED or {@link BusinessConstants}.ADDED or {@link BusinessConstants}
	 * .MODIFIED
	 * 
	 * @param processedExcelIndicativeSeg
	 */
	public void setProcessedExcelIndicativeSeg(
			Map<String, List<IndicativeDetailVO>> processedExcelIndicativeSeg) {
		this.processedExcelIndicativeSeg = processedExcelIndicativeSeg;
	}

	@Override
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

}
