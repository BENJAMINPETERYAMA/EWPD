/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.builder;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.wellpoint.wpd.business.contract.webservice.ContractRuleWebService;
import com.wellpoint.wpd.business.contract.webservice.ContractRuleWebServiceImpl;
import com.wellpoint.wpd.common.contract.model.Contract;
import com.wellpoint.wpd.common.contract.model.Messages;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContractRuleValidationHelper {
	/**
	 * 
	 * Helper Method for getting the rule set 
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	public static List getRuleSet() throws RemoteException, ServiceException {
		ContractRuleWebService contractRuleWebService = new ContractRuleWebServiceImpl();
		return contractRuleWebService.getRuleSet();
	}
	/**
	 * 
	 * Helper method for validating the contract
	 * 
	 * @param contract
	 * @param ruleList
	 * @return
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	public static Messages validate(Contract contract, List ruleList)
			throws RemoteException, ServiceException {
		ContractRuleWebService contractRuleWebService = new ContractRuleWebServiceImpl();
		return contractRuleWebService.validate(contract, ruleList);
	}

}