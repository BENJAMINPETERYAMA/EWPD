/*
 * ServiceTypeMappingBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.blueexchange.builder.ServiceTypeMappingBusinessObjectBuilder;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.RuleMapping;
import com.wellpoint.wpd.common.blueexchange.bo.ServiceTypeMapping;
import com.wellpoint.wpd.common.blueexchange.request.SaveServiceTypeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.request.ViewServiceTypeCodeMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.SaveServiceTypeMappingResponse;
import com.wellpoint.wpd.common.blueexchange.response.ViewServiceTypeCodeMappingResponse;
import com.wellpoint.wpd.common.bo.DataFeedStatus;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMappingBusinessValidationService extends
		WPDBusinessValidationService {
	
	/**
	 * Validation service for Create/Update Service type mapping.
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(SaveServiceTypeMappingRequest request) throws SevereException {
		SaveServiceTypeMappingResponse response = (SaveServiceTypeMappingResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.SAVE_SERVICE_MAPPING_RESPONSE);
		ServiceTypeMapping serviceTypeMapping = new ServiceTypeMapping();
		ServiceTypeMappingBusinessObjectBuilder builder = new ServiceTypeMappingBusinessObjectBuilder();
		
		ServiceTypeMappingBusinessService.copyValues(request, serviceTypeMapping);

		boolean valid = true;
		List messages = new ArrayList();
		if (request.getAction() == SaveServiceTypeMappingRequest.CREATE_ACTION) {
			if (!builder.isRuleExist(request.getServiceTypeMappingVO().getHeaderRuleId())) {
				valid = false;
				messages.add(new ErrorMessage("invalid.rule"));
			}

			if (valid && builder.isRuleMapped(request.getServiceTypeMappingVO().getHeaderRuleId())) {
				valid = false;
				messages.add(new ErrorMessage("already.mapped"));
			}
		}
		
		if (request.getAction() == SaveServiceTypeMappingRequest.UPDATE_ACTION) {
			RuleMapping mapping = builder.retrieveRuleMapping(request.getServiceTypeMappingVO().getHeaderRuleId());
			if(mapping.getApplicableToBX().equalsIgnoreCase("N")&& request.getServiceTypeMappingVO().getBlueExcahngeApplicable().equals("Y")) {
				List eb03codes = request.getServiceTypeMappingVO().getEb03codeList();
				if(eb03codes == null || eb03codes.size() == 0) {
					valid = false;
					messages.add(new ErrorMessage("atleast.oneservicetypecode"));
				}
			}
		}
		
		response.setMessages(messages);
		response.setValid(valid);
		return response;
	}
	 
	public boolean isValidForDelete(List codeList,List totalList){
		boolean isValid = true;
		if(totalList.size()>codeList.size()){
			isValid = true;
		}else{
			isValid = false;
		}
		return isValid;
	}
	
	/**
	 * Validation service for Create/Update Service type mapping.
	 * @param request
	 * @return
	 * @throws SevereException
	 */
	public WPDResponse execute(ViewServiceTypeCodeMappingRequest request) throws SevereException {
		ViewServiceTypeCodeMappingResponse response = (ViewServiceTypeCodeMappingResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.VIEW_SERVICE_TYPE_CODE_RESPONSE);
		  List messages = new ArrayList();
		if(isDataFeedRunning()){
			response.setDataFeedRunning(true);
			messages.add(new InformationalMessage(BusinessConstants.DATAFEED_RUNNING));
		}
		
		response.setMessages(messages);
		return response;
	}
	/**
	 * This method checks for data feed run.
	 * if data feed is running this will return true else return false.
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean isDataFeedRunning() throws SevereException {
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		DataFeedStatus dataFeedStatus = new DataFeedStatus();
		dataFeedStatus.setFeedType("BX");
		dataFeedStatus = builder.getDataFeed(dataFeedStatus);
		if ((dataFeedStatus != null) && (1 == dataFeedStatus.getFeedRunFlag())) {
			return true;
		}
		return false;
	}
}
