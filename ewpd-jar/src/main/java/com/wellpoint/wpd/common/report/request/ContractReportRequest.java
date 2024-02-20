/*
 * ContractReportRequest.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.report.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

public class ContractReportRequest extends WPDRequest {
	public static final int VALIDATE_INPUTS = 1;
	public static final int LOAD_BENEFTS_AND_COMPONENTS = 2;
	
	private List contractCodes;
	private List benefitComponents;
	private List benefits;
	
	private int action;
	
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	public List getContractCodes() {
		return contractCodes;
	}

	public void setContractCodes(List contractCodes) {
		this.contractCodes = contractCodes;
	}

	public List getBenefitComponents() {
		return benefitComponents;
	}

	public void setBenefitComponents(List benefitComponents) {
		this.benefitComponents = benefitComponents;
	}

	public List getBenefits() {
		return benefits;
	}

	public void setBenefits(List benefits) {
		this.benefits = benefits;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
