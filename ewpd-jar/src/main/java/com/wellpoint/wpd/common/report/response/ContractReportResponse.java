/*
 * ContractReportResponse.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.report.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

public class ContractReportResponse extends WPDResponse {
	private List componentList;
	private List benefitList;
	private List contractList;
	
	public List getComponentList() {
		return componentList;
	}
	public void setComponentList(List componentList) {
		this.componentList = componentList;
	}
	public List getBenefitList() {
		return benefitList;
	}
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	public List getContractList() {
		return contractList;
	}
	public void setContractList(List contractList) {
		this.contractList = contractList;
	}

}
