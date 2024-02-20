/*
 * RetrieveBaseContractDateRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBaseContractDateRequest extends ContractRequest {

	private int contractSysId;

	private String datafeedIndicator;

	private int action;
	
	private String testProdIndicator;

	/**
	 * Returns the contractSysId
	 * 
	 * @return int contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}

	/**
	 * Sets the contractSysId
	 * 
	 * @param contractSysId.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the datafeedIndicator
	 * 
	 * @return String datafeedIndicator.
	 */
	public String getDatafeedIndicator() {
		return datafeedIndicator;
	}

	/**
	 * Sets the datafeedIndicator
	 * 
	 * @param datafeedIndicator.
	 */
	public void setDatafeedIndicator(String datafeedIndicator) {
		this.datafeedIndicator = datafeedIndicator;
	}

	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the testProdIndicator.
	 */
	public String getTestProdIndicator() {
		return testProdIndicator;
	}
	/**
	 * @param testProdIndicator The testProdIndicator to set.
	 */
	public void setTestProdIndicator(String testProdIndicator) {
		this.testProdIndicator = testProdIndicator;
	}
}