/*
 * <ErrorDetailVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.webservices.vo;

/**
 * @author UST-GLOBAL
 * 
 * Value Object Class for storing  error details
 * 
 */
public class ErrorDetailWebServiceVO {

    private String errorCode;
    private String errorDesc;
    private boolean isError;
    private String networkDescription;
    private String associatedEb03;
    
	public String getNetworkDescription() {
		return networkDescription;
	}
	public void setNetworkDescription(String networkDescription) {
		this.networkDescription = networkDescription;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	/**
	 * @return the associatedEb03
	 */
	public String getAssociatedEb03() {
		return associatedEb03;
	}
	/**
	 * @param associatedEb03 the associatedEb03 to set
	 */
	public void setAssociatedEb03(String associatedEb03) {
		this.associatedEb03 = associatedEb03;
	}

}
