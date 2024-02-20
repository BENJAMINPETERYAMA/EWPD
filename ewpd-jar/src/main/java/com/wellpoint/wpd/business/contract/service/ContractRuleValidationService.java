/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.service;

import java.rmi.RemoteException;
import java.util.List;

import com.wellpoint.wpd.business.contract.builder.ContractModelBuilder;
import com.wellpoint.wpd.business.contract.builder.ContractRuleValidationHelper;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.common.contract.model.Contract;
import com.wellpoint.wpd.common.contract.model.Messages;
import com.wellpoint.wpd.common.contract.request.ContractRuleValidationRequest;
import com.wellpoint.wpd.common.contract.request.ContractRulesetRequest;
import com.wellpoint.wpd.common.contract.response.ContractRuleValidationResponse;
import com.wellpoint.wpd.common.contract.response.ContractRulesetResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


public class ContractRuleValidationService extends WPDBusinessValidationService {
	
	/**
	 * Service to get the Contract Rule validations
	 * 
	 * 
	 * @param contractRulesetRequest
	 * @return
	 * @throws ServiceException
	 */
    public WPDResponse execute(ContractRulesetRequest contractRulesetRequest) throws ServiceException {
    	
    	
    	ContractRulesetResponse contractRulesetResponse=(ContractRulesetResponse)WPDResponseFactory.getContractRuleSetResponse();
    	
    	List ruleSet = null;
    	
    	
    	try {
    		//invoke helper to get rule list
			ruleSet=ContractRuleValidationHelper.getRuleSet();
		} catch (RemoteException e) {
			ServiceException serviceException=new ServiceException
			("Exception occured in the execute(ContractRulesetRequest contractRulesetRequest)",null,e);
			throw serviceException;
		} catch (javax.xml.rpc.ServiceException e) {
			ServiceException serviceException=new ServiceException
			("Exception occured in the execute(ContractRulesetRequest contractRulesetRequest)",null,e);
			throw serviceException;
		}
    	//set value to response
		contractRulesetResponse.setSearchResults(ruleSet);

        return contractRulesetResponse;
    }

    /**
     * 
     * Service to validate the Contract with the Rules 
     * configured in Rule Engine
     * 
     * @param contractRuleValidationRequest
     * @return
     * @throws ServiceException
     */
    public WPDResponse execute(ContractRuleValidationRequest contractRuleValidationRequest) throws ServiceException {
    	
    	ContractModelBuilder contractModelBuilder=new ContractModelBuilder();
    	Contract contract=null;
		try {
			contract = contractModelBuilder.getContract(contractRuleValidationRequest.getContractSysId(),contractRuleValidationRequest.getEffDate());
		} catch (SevereException e1) {
			ServiceException serviceException=new ServiceException(null,null,null);
		}
		ContractRuleValidationResponse contractRuleValidationResponse=(ContractRuleValidationResponse)WPDResponseFactory.getContractRuleValidationResponse();
    	
    	List validationList = null;
    	if(contract!= null){
    		try {
    			validationList=((Messages)(ContractRuleValidationHelper.validate(contract,contractRuleValidationRequest.getContractRuleList()))).getMessages();
    		} catch (RemoteException e) {
    			ServiceException serviceException=new ServiceException
    			("Exception occured in the execute(ContractRuleValidationRequest contractRuleValidationRequest)",null,e);
    			throw serviceException;
    		} catch (javax.xml.rpc.ServiceException e) {
    			ServiceException serviceException=new ServiceException
    			("Exception occured in the execute(ContractRuleValidationRequest contractRuleValidationRequest)",null,e);
    			throw serviceException;
    		}
    		contractRuleValidationResponse.setValid(true);
    		contractRuleValidationResponse.setSearchResults(validationList);
    	}else {
    		contractRuleValidationResponse
			.addMessage(new ErrorMessage(
					WebConstants.CONTRACT_RULE_VALID_FAIL));
    		contractRuleValidationResponse.setValid(false);
    	}

        return contractRuleValidationResponse;
    }

	
	
	
	

}
