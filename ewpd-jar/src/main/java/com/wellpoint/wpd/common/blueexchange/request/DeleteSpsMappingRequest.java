/*
 * DeleteSpsMappingRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.request;



import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteSpsMappingRequest extends WPDRequest {

	
  private  String spsParameter;
  
/**
 * @return Returns the spsParameter.
 */
public String getSpsParameter() {
	return spsParameter;
}
/**
 * @param spsParameter The spsParameter to set.
 */
public void setSpsParameter(String spsParameter) {
	this.spsParameter = spsParameter;
}
/* (non-Javadoc)
 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
 */
public void validate() throws ValidationException {
	// TODO Auto-generated method stub
	
}
}
