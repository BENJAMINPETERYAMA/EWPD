/*
 * ContractReportService.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.report.request.ContractReportRequest;
import com.wellpoint.wpd.common.report.response.ContractReportResponse;

public class ContractReportService extends WPDBusinessService {

	public WPDResponse execute(ContractReportRequest request) throws ServiceException, SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		ContractReportResponse response = (ContractReportResponse)WPDResponseFactory.getResponse(WPDResponseFactory.CONTRACT_REPORT_RESPONSE);
		
		switch (request.getAction()) {
		case ContractReportRequest.LOAD_BENEFTS_AND_COMPONENTS:
				response.setComponentList(builder.getComponents(new ArrayList()));
				response.setBenefitList(builder.getBenefits(new ArrayList()));
			break;
		case ContractReportRequest.VALIDATE_INPUTS:
			List inputContracts = request.getContractCodes();
			List inputComponents = request.getBenefitComponents();
			List inputBenefits = request.getBenefits();
			
			if(inputContracts != null && inputContracts.size() > 0) {
		        response.setContractList(builder.getContracts(inputContracts));
			}
			if(inputComponents != null && inputComponents.size() > 0) {
				response.setComponentList(builder.getComponents(inputComponents));
			}
			
			if(inputBenefits != null && inputBenefits.size() > 0) {
				response.setBenefitList(builder.getBenefits(inputBenefits));
			}
			break;
		}
		return response;
	}
	

}
