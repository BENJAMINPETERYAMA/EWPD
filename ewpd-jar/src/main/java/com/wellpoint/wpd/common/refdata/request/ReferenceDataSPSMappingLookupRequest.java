/*
 * Created on Apr 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.refdata.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferenceDataSPSMappingLookupRequest extends WPDRequest{
	
	
	private String spsMappingType;
	
	/**
	 * @return Returns the spsMappingType.
	 */
	public String getSpsMappingType() {
		return spsMappingType;
	}
	/**
	 * @param spsMappingType The spsMappingType to set.
	 */
	public void setSpsMappingType(String spsMappingType) {
		this.spsMappingType = spsMappingType;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
